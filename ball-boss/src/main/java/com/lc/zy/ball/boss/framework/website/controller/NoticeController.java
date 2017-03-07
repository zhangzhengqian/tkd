package com.lc.zy.ball.boss.framework.website.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.website.service.NoticeService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.WebsiteNotice;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	
	/**
	 * 
	 * <公告列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午12:44:53
	 */
	@RequestMapping(value = "list")
	public String list(Model model,HttpServletRequest request){
		try {
			logger.info("NoticeController list method execute!");
			Map<String, Object> searchParams  = Servlets.getParametersStartingWith(request, "search_");
			CommonOAUtils.paramesTrim(searchParams);
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<WebsiteNotice> ongPage = noticeService.find(new PageRequest(page, size), searchParams);
			model.addAttribute("searchParams",Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
			model.addAttribute("data",ongPage);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error(e.getMessage(), e);
		}
		return "website/noticeList";
	}
	
	/**
	 * 
	 * <创建公告><功能具体实现>
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:18
	 */
	@RequestMapping(value = "createForm")
	public String createForm(Model model){
		return "website/noticeForm";
	}
	
	/**
	 * 
	 * <保存公告><功能具体实现>
	 * @param info
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:29
	 */
	@RequestMapping(value = "save")
	public String save(WebsiteNotice info,Model model){
		logger.info("NoticeController save execute!");
		if(info != null){
			noticeService.saveOrUpdate(info);
		}
		return "redirect:/notice/list";
	}
	
	/**
	 * 
	 * <公告详情><功能具体实现>
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:41:54
	 */
	@RequestMapping(value="detailForm ")
	public String detailForm(String id,Model model){
		try {
			if(StringUtils.isNotBlank(id)){
				WebsiteNotice info = noticeService.selectByPrimaryKey(WebsiteNotice.class,id);
				model.addAttribute("info", info);
			}
		} catch (Exception e) {
			e.printStackTrace();
            logger.error(e.getMessage(), e);
		}
		return "/website/noticeForm";
	}
	
	/**
	 * 
	 * <删除公告><功能具体实现>
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:53
	 */
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		logger.info("NoticeController delete execute!");
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(id)){
				WebsiteNotice infoNotice = noticeService.selectByPrimaryKey(WebsiteNotice.class,id);
				if(infoNotice != null ){
					noticeService.deleteByPrimaryKey(WebsiteNotice.class,id);
					result.put(Constants.Result.RESULT, true);
				}
			}else{
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
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
	 * <置顶><功能具体实现>
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:40:47
	 */
	@RequestMapping(value = "stick")
	public String stick(String id,RedirectAttributes redirectAttributes, Model model, ServletRequest request) {
		if (CommonUtils.isNotEmpty(id)) {
			WebsiteNotice notice = new WebsiteNotice();
			notice.setId(id);
			try {
				notice.setStick(Integer.MAX_VALUE);
				notice.setEb(SessionUtil.currentUserId());
				notice.setEt(new Date());
				noticeService.saveOrUpdate(notice);
			} catch (Exception e) {
				logger.debug("置顶 失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message", 
						FlashAttributeUtil.build(false, "置顶失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "赛事置顶成功"));
		return "redirect:/notice/list";
	}
	
	/**
	 * 
	 * <取消置顶><功能具体实现>
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:40:58
	 */
	@RequestMapping(value = "unstick ")
	public String unStick(RedirectAttributes redirectAttributes, Model model, ServletRequest request,String id) {
		if (CommonUtils.isNotEmpty(id)) {
			WebsiteNotice notice = new WebsiteNotice();
			notice.setId(id);
			try {
				notice.setStick(0);
				notice.setEb(SessionUtil.currentUserId());
				notice.setEt(new Date());
				noticeService.saveOrUpdate(notice);
			} catch (Exception e) {
				logger.debug("取消置失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message",
						FlashAttributeUtil.build(false, "取消置顶失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "取消置顶成功"));
		return "redirect:/notice/list";
	}


}
