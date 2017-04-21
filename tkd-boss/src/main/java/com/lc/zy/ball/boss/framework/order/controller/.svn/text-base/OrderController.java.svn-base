package com.lc.zy.ball.boss.framework.order.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.order.service.OrderService;
import com.lc.zy.ball.boss.framework.order.vo.OrderVo;
import com.lc.zy.ball.boss.framework.order.vo.RechargeOrderVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.mapper.CrmUserCardAccountMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserAccountMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMemberMapper;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderLog;
import com.lc.zy.ball.domain.oa.po.StatiumActivity;
import com.lc.zy.ball.domain.oa.po.StatiumClassMemberCriteria;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.web.WebUtils;

/**
 * <功能描述><功能具体实现>
 *
 * @create：2016年7月29日 下午4:18:19
 * @author：zzq
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends AbstractController {
	private static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private SsoUserAccountMapper ssoUserAccountMapper;
	
	@Autowired
	private CrmUserCardAccountMapper crmUserCardAccountMapper;
	
	@Autowired
	private StatiumClassMemberMapper statiumClassMemberMapper;

	private Gson gson = new Gson();
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * <订单列表展示><功能具体实现>
	 *
	 * @create：2016年7月29日 下午4:35:21
	 * @author：zzq
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"","/","/list"})
	public String list(HttpServletRequest request, Model model)
			 {
		// 根据查询条件查询
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<OrderVo> myPage;
		try {
			myPage = orderService.find(searchParams, page, size,
					true, true);
			
			System.out.println(myPage.getNumber() + "***"
					+ myPage.getNumberOfElements() + "***" + myPage.getSize()
					+ "***" + myPage.getTotalElements() + "***"
					+ myPage.getTotalPages());
			model.addAttribute("data", myPage);
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
			e.printStackTrace();
		}
		
		return "order/orderList";
	}

	/**
	 * 
	 * <获取订单><功能具体实现>
	 *
	 * @create：2016年8月4日 下午7:52:45
	 * @author：zzq
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String detailOrder(@PathVariable String id, Model model,
			ServletRequest request) {
		OrderVo vo = new OrderVo();
		StatiumInfosVo statiumInfosVo = new StatiumInfosVo();
		Order order = orderMapper.selectByPrimaryKey(id);
		Integer dgId = order.getStatiumId();
		vo = orderService.orderDetail(id);
		// 课程订单
		statiumInfosVo = orderService.statiumInfosDetail(id, dgId);
		// 活动订单
		StatiumActivity activity = orderService.findActivityDetail(id);
		//充值订单
		RechargeOrderVo rechargeVo = orderService.findRechargeDetail(id);
		//详情页面获取订单流水日志
		List<OrderLog> orderLogList = orderService.getOrderLogList(id);
		// 前台赋值
		model.addAttribute("orderLogList", orderLogList);
		model.addAttribute("order", vo);
		model.addAttribute("statiumInfo", statiumInfosVo);
		model.addAttribute("activity", activity);
		model.addAttribute("rechargeVo", rechargeVo);
		return "order/orderDetail";
	}
	
	/**
	 * 
	 * <判断是否可以申请退款><功能具体实现>
	 *
	 * @create：2016年9月8日 下午6:47:44
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/isRefundedOk")
	@ResponseBody
	public String isRefundedOk(String id){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (orderService.comparedToNow(id)==false){
				logger.debug("不能进行退款");
				result.put(Constants.Result.REASON, "还有不到24h开场，不能申请退款");
				result.put(Constants.Result.RESULT,false);
			} 
			else{
				logger.debug("可以进行退款");
				result.put(Constants.Result.RESULT,true);
			}
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("确认退款失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "还有不到24h开场，不能申请退款");
			//json转map
			//Map<String,Object> map=gson.fromJson
			//map转json
			logger.debug(""+gson.toJson(result));
			return gson.toJson(result);
		}
		
		
	}
	
	
	/**
	 * 
	 * <申请退款><功能具体实现>
	 *
	 * @create：2016年9月2日 下午3:45:51
	 * @author：zzq
	 * @param id
	 * @param reason
	 * @return
	 */
	@RequestMapping(value="/applyRefund")
	public String applyRefund(String orderId,String reason,Model model){
		logger.debug(orderId+"******"+reason);
		Order order = orderMapper.selectByPrimaryKey(orderId);
		//true 可以退款
			try {
				orderService.updateOrder(order,reason);
				//增加申请退款流水
				orderService.addApplyLog(orderId);
				
			} catch (Exception e) {
				e.getMessage();
			}
		return "redirect:/order/view/"+orderId;
	}
	
	/**
	 * 
	 * <确认退款><功能具体实现>
	 *
	 * @create：2016年8月30日 下午3:06:54
	 * @author：zzq
	 * @param id
	 * @param reason
	 * @return
	 */
	@RequestMapping(value="/confirmRefund/{id}")
	public String confirmRefund(@PathVariable String id,Model model){
		Order order = orderMapper.selectByPrimaryKey(id);
		//支付宝支付的退款至支付宝账户 微信支付的退款至微信支付 会员卡支付的退款至会员卡账户
		Integer payType = order.getPayType();
		//退款至支付宝和微信  只有会员卡支付的订单退款需要记录在日志表里
		if(payType == 1||payType ==2){
			logger.debug("*****支付类型"+payType);
			orderService.refundToAliWeixin(order);
		}else if(payType == 3){
			//退款至会员卡账户
			boolean flag = orderService.refundToAccount(order);
			if(flag){
				//增加道馆端会员卡日志表
				orderService.addCrmCardLog(order);
				//增加会员卡记录表
				orderService.addCrmUserCardLog(order);
			}
		}
		
		//判断订单类型课程  删除相应的报名人数 
		if(order.getOrdersType()==com.lc.zy.common.Constants.OrderType.STATIUM){
            // 删除课程超时报名人员
            deleteClassMember(order.getId(), order.getUserId());
		}
		
		return "redirect:/order/view/"+id;
	}
	
	/**
	 * 
	 * <删除课程订单的报名人员><功能具体实现>
	 *
	 * @create：2017年4月6日 上午10:55:52
	 * @author：zzq
	 * @param orderId
	 * @param userId
	 */
	private void deleteClassMember(String orderId, String userId) {
        try {
            StatiumClassMemberCriteria statiumClassMemberCriteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria criteria = statiumClassMemberCriteria.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            criteria.andUserIdEqualTo(userId);
            statiumClassMemberMapper.deleteByExample(statiumClassMemberCriteria);
        } catch (Exception e) {
            logger.debug("删除课程订单超时报名人员", e.getMessage());
        }
    
	}

	/**
	 * 
	 * <导出订单至excel><功能具体实现>
	 *
	 * @create：2016年9月12日 下午6:39:07
	 * @author：zzq
	 * @param ids
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value="/export")
	public void exportData(String ids,HttpServletResponse response) throws Exception{
		String[] newIds = ids.split(";");
		List<OrderVo> orderList = new ArrayList<OrderVo>();
		for(String newId:newIds){
			OrderVo orderVo = orderService.getOrderVo(newId);
			orderList.add(orderVo);
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("导出订单数据");
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
	        		"订单号::id","教练名::coachName","用户名::userName","联系电话::phone","道馆地区::areaStr","道馆名称::statiumName",
	        		"道馆地址::address","预定日期::orderDate","预定时间::orderTime","实付费用::finalFee","订单创建时间::ct","订单编辑时间::et",
	        		"订单状态::status","支付类型::payType1","订单类型::orderType","退款原因::reason",
	        };
	        excelUtil.exportExcel(title, headers, orderList, response.getOutputStream(), "yyyy-MM-dd");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * <导出查询到的订单至excel><功能具体实现>
	 *
	 * @create：2016年9月12日 下午6:39:07
	 * @author：zzq
	 * @param ids
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value="/exportQueryData")
	public void exportQueryData(HttpServletRequest request, Model model,HttpServletResponse response) throws Exception{
		List<OrderVo> orderList = new ArrayList<OrderVo>();
		// 根据查询条件查询
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<OrderVo> myPage;
		//查询全部的订单 page pageSize都为0
		myPage = orderService.find(searchParams, 0, 0,
					false, true);
		orderList = myPage.getContent();
		for(OrderVo orderVo : orderList){
			//订单类型
			Integer ordersType = orderVo.getOrdersType();
			if(ordersType==0){
				orderVo.setOrderType(Constants.OrdersType.STATIUM);
			}
			if(ordersType==1){
				orderVo.setOrderType(Constants.OrdersType.ACTIVITY);
			}
			//支付类型
			Integer payType = orderVo.getPayType();
			if(payType!=null){
				 if(payType==1){
					 //String payType1 = Constants.PayType.ALIPAY;
					 orderVo.setPayType1("支付宝支付");
				 }
				 if(payType==2){
					 //String payType2 = Constants.PayType.WEIXINPAY;
					 orderVo.setPayType1("微信支付");
				 }
				 if(payType==3){
					 //String payType2 = Constants.PayType.ACCOUNTPAY;
					 orderVo.setPayType1("会员卡账户支付");
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
	        		"订单号::id","教练名::coachName","用户名::userName","联系电话::phone","道馆地区::areaStr","道馆名称::statiumName",
	        		"道馆地址::address","预定日期::orderDate","预定时间::orderTime","实付费用::finalFee","订单创建时间::ct","订单编辑时间::et",
	        		"订单状态::status","支付类型::payType1","订单类型::orderType","退款原因::reason",
	        };
	        excelUtil.exportExcel(title, headers, orderList, response.getOutputStream(), "yyyy-MM-dd");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * <订单查询页面 初始查询对状态进行处理><功能具体实现>
	 *
	 * @create：2016年9月13日 下午4:42:42
	 * @author：zzq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/orderList")
	public String orderQuery(HttpServletRequest request,Model model){
		// 根据查询条件查询
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<OrderVo> myPage;
		try {
			myPage = orderService.find(searchParams, page, size,
					true, true);
			
			System.out.println(myPage.getNumber() + "***"
					+ myPage.getNumberOfElements() + "***" + myPage.getSize()
					+ "***" + myPage.getTotalElements() + "***"
					+ myPage.getTotalPages());
			model.addAttribute("data", myPage);
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
			e.printStackTrace();
		}
		
		return "order/orderQuery";
	}
}
