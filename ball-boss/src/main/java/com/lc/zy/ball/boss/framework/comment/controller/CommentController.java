package com.lc.zy.ball.boss.framework.comment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.comment.service.CommentService;
import com.lc.zy.ball.boss.framework.comment.vo.commentVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.BookComment;
import com.lc.zy.ball.domain.oa.po.CoachComment;
import com.lc.zy.ball.domain.oa.po.Comment;
import com.lc.zy.ball.domain.oa.po.StatiumComment;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.common.coreservice.Scores;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/comment")
public class CommentController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@Autowired
	private Scores scores;

	/**
	 * 
	 * <><场馆评论列表>
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午2:36:32
	 */
	@RequestMapping("list/{type}")
	public String list(@PathVariable Integer type, HttpServletRequest request, Model model) {
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			parseDate(searchParams, "GTE_startTime");
			parseDate(searchParams, "LTE_endTime");
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			logger.debug(searchParams.toString());
			Page<commentVo> onePage = commentService.find(new PageRequest(page, size), searchParams, type);
			model.addAttribute("currentUser", SessionUtil.currentUser());
			model.addAttribute("data", onePage);
			if (type == null) {
				type = 0;
			}
			model.addAttribute("search_type", type);
			// 将搜索条件编码成字符串，用于排序，分页的URL //bhg 先把传页面参数处理过去
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage() + "获取评论列表失败！");
		}
		return "comment/commentList";
	}

	/**
	 * 
	 * <><删除场馆评论>
	 * 
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午2:40:46
	 */
	@RequestMapping(value = "delete/{type}", method = RequestMethod.POST)
	@ResponseBody
	public String del(@PathVariable Integer type, String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				if (type == 0) {
					StatiumComment comment = commentService.selectByPrimaryKey(StatiumComment.class, id);
					if (comment != null) {
						if (StringUtils.isNotBlank(comment.getSid())) {
							StatiumDetail statiumDetail = commentService.selectByPrimaryKey(StatiumDetail.class,
									comment.getSid());
							if (statiumDetail != null) {
								scores.inc(comment.getSid(), -1 * comment.getGrade()); // 减去场馆评星
							}
						}
						commentService.deleteByPrimaryKey(StatiumComment.class, id);
					}
				} else if (type == 1 || type == 2) {
					Comment comment = commentService.selectByPrimaryKey(Comment.class, Integer.valueOf(id));
					if (comment != null) {
						commentService.deleteByPrimaryKey(Comment.class, comment.getId());
					}
				} else if (type == 3) {
					CoachComment comment = commentService.selectByPrimaryKey(CoachComment.class, id);
					if (comment != null) {
						commentService.deleteByPrimaryKey(CoachComment.class, id);
					}
				} else if (type == 4) {
					BookComment comment = commentService.selectByPrimaryKey(BookComment.class, id);
					if (comment != null) {
						commentService.deleteByPrimaryKey(BookComment.class, id);
					}
				}
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <><删除场馆评论>
	 * 
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午2:40:46
	 */
	@RequestMapping(value = "deleteAll/{type}", method = RequestMethod.POST)
	@ResponseBody
	public String delAll(@PathVariable Integer type, String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(ids)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				String[] idsArray = ids.split(",");
				for (String id : idsArray) {
					if (StringUtils.isNotBlank(id)) {
						if (type == 0) {
							StatiumComment comment = commentService.selectByPrimaryKey(StatiumComment.class, id);
							if (comment != null) {
								if (StringUtils.isNotBlank(comment.getSid())) {
									StatiumDetail statiumDetail = commentService.selectByPrimaryKey(
											StatiumDetail.class, comment.getSid());
									if (statiumDetail != null) {
										scores.inc(comment.getSid(), -1 * comment.getGrade()); // 减去场馆评星
									}
								}
								commentService.deleteByPrimaryKey(StatiumComment.class, id);
							}
						} else if (type == 1 || type == 2) {
							Comment comment = commentService.selectByPrimaryKey(Comment.class, Integer.valueOf(id));
							if (comment != null) {
								commentService.deleteByPrimaryKey(Comment.class, Integer.valueOf(id));
							}
						} else if (type == 3) {
							CoachComment comment = commentService.selectByPrimaryKey(CoachComment.class, id);
							if (comment != null) {
								commentService.deleteByPrimaryKey(CoachComment.class, id);
							}
						} else if (type == 4) {
							BookComment comment = commentService.selectByPrimaryKey(BookComment.class, id);
							if (comment != null) {
								commentService.deleteByPrimaryKey(BookComment.class, id);
							}
						}
					}
				}
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
}
