package com.lc.zy.ball.boss.framework.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.OrderSmsService;
import com.lc.zy.ball.boss.framework.system.vo.Day;
import com.lc.zy.ball.boss.framework.system.vo.Month;
import com.lc.zy.ball.domain.oa.po.OrderSms;
import com.lc.zy.common.util.DateUtils;

/**
 * 员工管理
 * @author liangc
 */
@Controller
@RequestMapping(value = "/admin/orderSms")
public class OrderSmsController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderSmsController.class);
	
	@Autowired
	private OrderSmsService orderSmsService = null;
	
	@RequestMapping(value={"", "/"})
	public String orderSms(String year,HttpServletRequest request) throws Exception {
		if( StringUtils.isEmpty(year) ){
			Date now = DateUtils.now();
			year = DateUtils.formatDate(now, "yyyy");
		}
		request.setAttribute("orderSms", orderSmsService.getOrderSmsMap(year));
		request.setAttribute("year", year);
		
		//启始日期
		String yy = year;
		String y = year+"-01-01";
		Date d = DateUtils.getDate(y);
		LinkedHashMap<String, Month> months = new LinkedHashMap<String, Month>();
		while(true){
			yy = DateUtils.formatDate(d, "yyyy");
			String mm = DateUtils.formatDate(d, "MM");
			if(!year.equals(yy)){
				break;
			}
			Month month = null;
			if(months.get(mm)==null){
				month = new Month();
			}else{
				month = months.get(mm);
			}
			month.setMonth(mm);
			
			List<Day> dayList = null;
			if( month.getDayList() == null ){
				dayList = new ArrayList<Day>();
			}else{
				dayList = month.getDayList();
			}
			Day day = new Day(d);
			dayList.add(day);
			month.setDayList(dayList);
			months.put( mm, month );
			d = DateUtils.plusDays(d, 1);
		}
		logger.debug(months.toString());
		request.setAttribute("months", months);
		return "admin/orderSms";
	}
	
	@RequestMapping(value="save")
	@ResponseBody
	public String save( String action, String date, HttpServletRequest request ) throws Exception {
		logger.debug("action={} , date={}",action,date);
		String rtn = null;
		if("add".equals(action)){
			rtn = date + " 设为自动发送";
			OrderSms orderSms = new OrderSms();
			orderSms.setId(date);
			orderSms.setYear(date.split("-")[0]);
			orderSmsService.insertSelective(orderSms, orderSms.getId());
		}else{
			rtn = date + " 取消自动发送";
			orderSmsService.deleteByPrimaryKey(OrderSms.class, date);
		}
		return rtn;
	}
	
	
}
