package com.lc.zy.ball.boss.framework.statistic.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statistic.service.StatisticSmsService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.po.ex.StatisticSmsEx;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.web.WebUtils;

/**
 * 注册用户分析
 * 
 * @author bhg
 */
@Controller
@RequestMapping(value = "/statisticSms")
public class SmsController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(SmsController.class);
	
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RedisPool redisPool = null;
	
	@Autowired
	private OrderMapper orderMapper = null;
	
	@Autowired
	private StatisticSmsService statisticSmsService;

	
	/**
	 * 
	 * 表格数据
	 *
	 * @create：09-21
	 * @author： bhg
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "smsList")
	public String smsList(HttpServletRequest request,Model model)
			throws Exception {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		//bhg 时间参数直接传递回去，不做类型转变
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		// 分页
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		
		logger.debug(searchParams.toString());
		Page<StatisticSmsEx> statisticSmsPage = null;
		try {
			statisticSmsPage = statisticSmsService.findSmsList(new PageRequest(page, size), searchParams, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("smsList error:" + e.getMessage());
		}
		model.addAttribute("data", statisticSmsPage);
		// 页面用16#
		return "/statistic/smsStatistic";
	}
	
	
}
