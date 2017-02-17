package com.lc.zy.ball.crm.framework.system.order.controller;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.order.service.OrderService;
import com.lc.zy.ball.crm.framework.system.order.vo.OrderVo;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 *
	 * <获取订单list><功能具体实现>
	 *
	 * @create：2016/11/23 上午9:24
	 * @author：sl
	 * @param request
	 * @param model
	 * @return java.lang.String
	 */
	@RequestMapping(value = {"","/","/list"})
	public String list(HttpServletRequest request, Model model) {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<OrderVo> pageData = null;
		try {
			// 获取订单list
			pageData = orderService.find(searchParams, page, size,true);
			model.addAttribute("data", pageData);

			// 将搜索条件编码成字符串，用于排序，分页的URL
			String as[] = { "GTE_ct", "LTE_ct", "GTE_et", "LTE_et" };
			for (String s : as) {
				if (null != searchParams.get(s)
						&& searchParams.get(s) instanceof Date) {
					searchParams.put(s, sdf.format(searchParams.get(s)));
				}
			}
			// searchParam用于分页时进行查询
			model.addAttribute("searchParams", Servlets
					.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.error("{获取订单列表失败：}",e.getMessage());
		}
		String channel = "";
		if (searchParams.get("EQ_orderType") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_orderType"))) {
			channel = (String) searchParams.get("EQ_orderType");
		} else {
			channel = Constants.BookType.APP;
		}
		String returnUrl = "/order/appOrderList";
		if (Constants.BookType.LIVE.equals(channel)) {
			returnUrl = "/order/liveOrderList";
		}
		return returnUrl;
	}
	
	/**
	 * 
	 * <导出订单excel><功能具体实现>
	 *
	 * @create：2016年12月27日 下午3:23:56
	 * @author：zzq
	 * @param request
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value="/exportQueryData")
	public void exportQueryData(HttpServletRequest request, Model model,HttpServletResponse response) throws Exception{
		
		try {
			List<OrderVo> orderList = new ArrayList<OrderVo>();
			// 根据查询条件查询
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(
					request, "search_");
			// 参数去空
			CommonOAUtils.paramesTrim(searchParams);
			Page<OrderVo> myPage;
			//查询全部的订单 page pageSize都为0
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			myPage = orderService.find(searchParams, page, size,false);
			orderList = myPage.getContent();
			for(OrderVo orderVo : orderList){
				//订单类型
				Integer ordersType = orderVo.getOrdersType();
				if(ordersType==0){
					orderVo.setOrderType("课程");
				}
				if(ordersType==1){
					orderVo.setOrderType("活动");
				}else if(ordersType ==2){
					orderVo.setOrderType("充值");
				}
				//支付类型
				Integer payType = orderVo.getPayType();
				if(payType!=null){
					 if(payType==1){
						 //String payType1 = Constants.PayType.ALIPAY;
						 orderVo.setPayTypeStr("支付宝支付");
					 }
					 if(payType==2){
						 //String payType2 = Constants.PayType.WEIXINPAY;
						 orderVo.setPayTypeStr("微信支付");
					 }
					 if(payType==3){
						 //String payType2 = Constants.PayType.ACCOUNTPAY;
						 orderVo.setPayTypeStr("会员卡账户支付");
					 }
				 }
			}
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String as[] = { "GTE_ct", "LTE_ct", "GTE_et", "LTE_et" };
				for (String s : as) {
					if (null != searchParams.get(s)
							&& searchParams.get(s) instanceof Date) {
						searchParams.put(s, sdf.format(searchParams.get(s)));
					}
				}
			ExcelUtil excelUtil = new ExcelUtil();
			String fileName = excelUtil.createtFileName("导出查询订单数据");
			try {
				fileName = new String(fileName.getBytes("GBK"),"iso8859-1");
				response.reset();
				//指定下载的文件名
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
			    response.setDateHeader("Expires", 0);
			    
			    String title="订单列表";
			    String[] headers = {
			    		"订单号::id","教练名::coachName","用户名::userName","联系电话::phone","预定日期::signDate",
			    		"预定时间::signTime","实付费用::finalFee","订单创建时间::ct","订单编辑时间::et",
			    		"订单状态::status","支付类型::payTypeStr","订单类型::orderType",
			    };
			    excelUtil.exportExcel(title, headers, orderList, response.getOutputStream(), "yyyy-MM-dd");
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
