package com.lc.zy.ball.boss.framework.coach.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.coach.service.CoachClassInfoService;
import com.lc.zy.ball.boss.framework.statiumClass.service.StatiumClassInfoService;
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassInfoVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.common.web.WebUtils;

/**
 * <查看课时controoler><功能具体实现>
 *
 * @create：2016年5月9日 下午4:29:22
 * @author：wangp
 * @return
 */
@Controller
@RequestMapping("/coachClassInfo")
public class CoachClassInfoController extends AbstractController {
	@Autowired
	private StatiumClassInfoService statiumClassInfoService;
	@Autowired
	private StatiumClassMapper statiumClassMapper;
	@Autowired
	private CoachClassInfoService coachClassInfoService;

	private static Logger logger = LoggerFactory
			.getLogger(CoachClassInfoController.class);

	@RequestMapping(value = { "", "/", "/list" })
	public String list(HttpServletRequest request, Model model, String id) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<StatiumClassInfoVo> pmwMemberPage = null;
		try {
			searchParams.put("EQ_coachId", id);
			pmwMemberPage = coachClassInfoService.list(new PageRequest(page,
					size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", pmwMemberPage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(
					searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr + "&id=" + id);
		} catch (Exception e) {
			logger.debug("课时list:{}", e.getMessage());
			e.printStackTrace();
		}
		return "coachClass/classInfoList";
	}

	/**
	 * <返回教练列表><功能具体实现>
	 *
	 * @create：2016年5月9日 下午4:30:22
	 * @author：wangp
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/backClassList")
	public String backClassList(HttpServletRequest request,
			HttpServletResponse response, String id) {

		try {
			Coach coach = coachClassInfoService.coachById(id);
			id = coach.getId();
		} catch (Exception e) {
			logger.debug("返回教练列表:{}", e.getMessage());
		}
		return "redirect:/coach/list";
	}

}
