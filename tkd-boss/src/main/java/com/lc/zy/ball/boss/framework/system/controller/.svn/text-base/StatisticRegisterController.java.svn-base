package com.lc.zy.ball.boss.framework.system.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.StatisticRegisterService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUser;
import com.lc.zy.common.web.WebUtils;


@Controller
@RequestMapping(value="/admin/statisticRegisterUser")
public class StatisticRegisterController extends AbstractController{
	
	@Autowired
	private StatisticRegisterService statisticRegisterService;
	private static Logger logger = LoggerFactory.getLogger(StatisticRegisterController.class);
	/**
	 * 
	 * <用户统计分页展示><功能具体实现>
	 *
	 * @create：2016年10月26日 下午2:21:46
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public String list(Model model,HttpServletRequest request){
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		PageRequest pageRequest = new PageRequest(page, pageSize);
		//分页展示
		Page<StatisticRegisterUser> list = statisticRegisterService.list(pageRequest);
		//注册总数
		int totalAmount = statisticRegisterService.countTotal(list);
		model.addAttribute("data", list);
		model.addAttribute("totalAmount", totalAmount);
		return "/admin/statisticRegisterList";
	}
	
	/**
	 * 
	 * <获取渠道详细信息><功能具体实现>
	 *
	 * @create：2016年10月26日 下午8:15:10
	 * @author：zzq
	 * @param channel
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detailStatisticRegisterUser")
	public String detailStatisticRegisterUser(String channel,Model model,HttpServletRequest request){
		logger.debug("获取的渠道是"+channel);
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		PageRequest pageRequest = new PageRequest(page, pageSize);
		Page<StatisticRegisterUser> detailPage = statisticRegisterService.detailStatisticByChannel(pageRequest,channel);
		model.addAttribute("data", detailPage);
		return "/admin/detailStatisticRegisterUser";
	}
	
	/**
	 * 
	 * <所有渠道的显示><功能具体实现>
	 *
	 * @create：2016年10月27日 上午10:48:06
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/allChannelRegisterUser")
	public String allChannelRegisterUser(Model model,HttpServletRequest request){
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		PageRequest pageRequest = new PageRequest(page, pageSize);
		//分页展示
		Page<StatisticRegisterUser> list = statisticRegisterService.detailStatisticByChannel(pageRequest, "");
		model.addAttribute("data", list);
		return "/admin/detailStatisticRegisterUser";
		
	}
	
	/**
	 * 
	 * <查找某个渠道某一天的注册信息><功能具体实现>
	 *
	 * @create：2016年10月27日 下午2:23:13
	 * @author：zzq
	 * @param channel
	 * @param ct
	 * @return
	 */
	@RequestMapping(value="/selectUserByHour")
	public String selectUserByHour(String channel,String ct,HttpServletRequest request,Model model){
		logger.debug("渠道为"+channel+"创建时间为"+ct);
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		PageRequest pageRequest = new PageRequest(page, pageSize);
		//某个渠道某一天的注册用户
		Page<StatisticRegisterUser> list = statisticRegisterService.selectByHourChannel(pageRequest, channel, ct);
		model.addAttribute("data", list);
		return "/admin/statisticRegisterByHour";
	}
}
