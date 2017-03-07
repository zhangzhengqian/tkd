package com.lc.zy.ball.boss.framework.orders.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.zy.ball.boss.framework.statium.service.StatiumPriceTmplService;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.orders.service.BillService;
import com.lc.zy.ball.boss.framework.orders.service.OrderSendMsgService;
import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.boss.framework.orders.vo.EnjoyOrderSearch;
import com.lc.zy.ball.boss.framework.orders.vo.OrderDate;
import com.lc.zy.ball.boss.framework.orders.vo.OrderLogVo;
import com.lc.zy.ball.boss.framework.orders.vo.OrderParam;
import com.lc.zy.ball.boss.framework.orders.vo.OrderVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.mapper.EnjoyGameSiteMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumDetailMapper;
import com.lc.zy.ball.domain.oa.po.EnjoyGameSite;
import com.lc.zy.ball.domain.oa.po.EnjoyGameSiteCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.OrderNotifyUtil;
import com.lc.zy.common.web.WebUtils;

/**
 * 订单管理
 * @author chenglong
 */
@Controller
@RequestMapping(value="/orders")
public class OrderController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private BillService billService;
	@Autowired
	private StatiumDetailMapper statiumDetailMapper;
	@Autowired
	private OrderBillMapper orderBillMapper;
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	@Autowired
	private OrderNotifyUtil orderNotifyUtil;

	private Gson gson = new Gson();

	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private OrderSendMsgService orderSendMsgService;
	
	@Autowired
	private EnjoyGameSiteMapper enjoyGameSiteMapper;

	@Autowired
	private StatiumPriceTmplService statiumPriceTmplService;
	
	 /**
     * 订单分页展示
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="list")
	public String ordersList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("OrderController OrdersList method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		
		Page<OrderVo> onePage = orderService.find(searchParams, page, size,true,true);
	
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		String as[] = {"GTE_ct","LTE_ct","GTE_et","LTE_et"};
		for(String s :as ){
			if(null != searchParams.get(s) && searchParams.get(s) instanceof Date){
				searchParams.put(s, sdf.format(searchParams.get(s)));
			}
		}
		
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "orders/ordersList";
	}
	
	/**
     * 订单分页展示
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="enjoylist")
	public String enjoyOrders(Model model,EnjoyOrderSearch sercher,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("OrderController OrdersList method execute!");
		EnjoyGameSiteCriteria enjoyGameSiteCriteria = new EnjoyGameSiteCriteria();
        List<EnjoyGameSite> enjoyGameSiteList = enjoyGameSiteMapper.selectByExample(enjoyGameSiteCriteria);
        Set<String> siteList = new HashSet<String>();
        for (EnjoyGameSite site : enjoyGameSiteList) {
            if (com.lc.zy.common.Constants.MUNICIPALITY_LIST.contains(site.getProvince())) {
                siteList.add(site.getProvince());
            } else {
                siteList.add(site.getCity());
            }
        }
		//根据查询条件查询数据
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<OrderVo> onePage = orderService.findEnjoyOrders(sercher, page, size,true);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("sercher", sercher);
		model.addAttribute("siteList",siteList);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(getValueMap(sercher), ""));
		return "orders/enjoyOrders";
	}

	/**
	 * 糯米订单
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
     * @throws Exception
     */
	@RequestMapping(value="nuomiList")
	public String nuomiList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("OrderController nuomiList method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空

		Page<OrderVo> onePage = orderService.findNuomiList(searchParams, page, size,true,true);

		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		String as[] = {"GTE_ct","LTE_ct","GTE_et","LTE_et"};
		for(String s :as ){
			if(null != searchParams.get(s) && searchParams.get(s) instanceof Date){
				searchParams.put(s, sdf.format(searchParams.get(s)));
			}
		}

		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "orders/nuomiOrdersList";
	}
	
	/**
	 * 糯米订单
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
     * @throws Exception
     */
	@RequestMapping(value="taipingList")
	public String taipingList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("OrderController nuomiList method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空

		Page<OrderVo> onePage = orderService.findTaipingList(searchParams, page, size,true,true);

		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		String as[] = {"GTE_ct","LTE_ct","GTE_et","LTE_et"};
		for(String s :as ){
			if(null != searchParams.get(s) && searchParams.get(s) instanceof Date){
				searchParams.put(s, sdf.format(searchParams.get(s)));
			}
		}

		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "orders/taipingOrdersList";
	}

	@RequestMapping(value="nuomiexport")
	public String nuomiExport(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
		logger.debug("OrderController OrdersList method execute!");
		//根据查询条件查询数据
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		Page<OrderVo> onePage = orderService.findNuomiList(searchParams, page, size,false,false);
		List<OrderVo> orders = onePage.getContent();
		for (OrderVo vo: orders) {
			vo.setCostPrice(statiumPriceTmplService.getCostPriceByOrderId(vo.getId()));
			vo.setSubsidies(statiumPriceTmplService.getSubsidiesByOrderId(vo.getId()));
			String statiusSTr = "";
			if(vo.getStatus().equals("ORDER_NEW")){
				statiusSTr = "待付款";
			}else if(vo.getStatus().equals("ORDER_VERIFY")){
				statiusSTr = "已确认";
			}else if(vo.getStatus().equals("ORDER_PLAYING")){
				statiusSTr = "交易成功";
			}else if(vo.getStatus().equals("ORDER_PAIED")){
				statiusSTr = "已付款";
			}else if(vo.getStatus().equals("ORDER_REFUNDING")){
				statiusSTr = "退款中";
			}else if(vo.getStatus().equals("ORDER_REFUNDED")){
				statiusSTr = "已退款";
			}else if(vo.getStatus().equals("ORDER_CANCELED")){
				statiusSTr = "交易关闭";
			}
			vo.setStatusStr(statiusSTr);
			String ordersTypeStr = "";
			switch (vo.getOrdersType()){
				case 0:
					 ordersTypeStr = "订场";
					break;
				case 1:
					  ordersTypeStr = "教/陪练";
					break;
				case 3:
					ordersTypeStr = "活动";
					break;
				case 4:
					ordersTypeStr = "赛事";
					break;
				case 5:
			}
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("糯米订单列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		//指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String[] headers = {"订单号::id","下单时间::ctStr","合作次数::cooperateNum","场馆::name","地区::areaStr","联系电话::phone","预约日期::orderDate","预约时间::orderTime","实付（元）::finalFee","成本（元）::costPrice","补贴金额（元）::subsidies","订单状态::statusStr","品类::sportType","处理人::handleName","处理状态::handleStatusStr","处理结果::reason","类型::ordersTypeStr"};

		excelUtil.exportExcel("糯米订单", headers, orders, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}
	@RequestMapping(value="lemiexport")
	public String lemiExport(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
		logger.debug("OrderController OrdersList method execute!");
		//根据查询条件查询数据
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		Page<OrderVo> onePage = orderService.findTaipingList(searchParams, page, size,false,false);
		List<OrderVo> orders = onePage.getContent();
		for (OrderVo vo: orders) {
			vo.setCostPrice(statiumPriceTmplService.getCostPriceByOrderId(vo.getId()));
			vo.setSubsidies(statiumPriceTmplService.getSubsidiesByOrderId(vo.getId()));
			String statiusSTr = "";
			if(vo.getStatus().equals("ORDER_NEW")){
				statiusSTr = "待付款";
			}else if(vo.getStatus().equals("ORDER_VERIFY")){
				statiusSTr = "已确认";
			}else if(vo.getStatus().equals("ORDER_PLAYING")){
				statiusSTr = "交易成功";
			}else if(vo.getStatus().equals("ORDER_PAIED")){
				statiusSTr = "已付款";
			}else if(vo.getStatus().equals("ORDER_REFUNDING")){
				statiusSTr = "退款中";
			}else if(vo.getStatus().equals("ORDER_REFUNDED")){
				statiusSTr = "已退款";
			}else if(vo.getStatus().equals("ORDER_CANCELED")){
				statiusSTr = "交易关闭";
			}
			vo.setStatusStr(statiusSTr);
			String ordersTypeStr = "";
			switch (vo.getOrdersType()){
			case 0:
				ordersTypeStr = "订场";
				break;
			case 1:
				ordersTypeStr = "教/陪练";
				break;
			case 3:
				ordersTypeStr = "活动";
				break;
			case 4:
				ordersTypeStr = "赛事";
				break;
			case 5:
			}
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("太平订单列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		//指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String[] headers = {"订单号::id","场馆::name","地区::areaStr","联系电话::phone","预约日期::orderDate","预约时间::orderTime","实付（元）::finalFee","乐米币（元）::lxFee","成本（元）::costPrice","补贴金额（元）::subsidies","下单时间::ct ","订单状态::statusStr","品类::sportType","处理人::handleName","处理状态::handleStatusStr","处理结果::reason","类型::ordersTypeStr"};
		
		excelUtil.exportExcel("太平订单", headers, orders, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}

	private Map<String, Object> getValueMap(Object obj) {

		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println(obj.getClass());
		// 获取f对象对应类中的所有属性域
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = fields[i].isAccessible();
				// 修改访问控制权限
				fields[i].setAccessible(true);
				// 获取在对象f中属性fields[i]对应的对象中的变量
				Object o = fields[i].get(obj);
				if (o != null)
					map.put(varName, o);
				// System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
				// 恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return map;

	}
	
	/**
     * 订单分页展示
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="enjoyexport")
	public String enjoyExport(Model model,EnjoyOrderSearch sercher,HttpServletRequest request,RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
		logger.debug("OrderController OrdersList method execute!");
		//根据查询条件查询数据
		Page<OrderVo> onePage = orderService.findEnjoyOrders(sercher, 0, 0,false);
		List<OrderVo> orders = onePage.getContent();
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("乐享赛订单列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		//指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String[] headers = {"订单号::id","场馆::name","地区::areaStr","联系地址::address","赛事名称::enjoyName",
				"联系电话::phone","开始时间::startTime","结束时间::endTime","报名人::pname","报名时间::orderDate","报名次数::pnum","身份证号::pcard","手机号::pphone","实收金额::finalFee","账户支付::acountFee","奖金账户支付::bounsAccountFee","球友卡金额::qiuyouFee","优惠券金额::subAmount","场地费::cdFee","报名费::bmFee","收入::srFee","报名日期::pct","报名种类::gameType","报名类别::gameLevel","奖金::gameBonus","订单状态::status"};

		excelUtil.exportExcel("乐享赛订单", headers, orders, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}
	
	
	
	/**
     * 订单详情
     * @param model
     * @return
     */
	@RequestMapping(value="view/{id}")
	public String OrdersView(Model model,@PathVariable String id) throws Exception {
		OrderVo orderVo;
		List<OrderLogVo> orderLogVoList; 
		try {
			orderVo = orderService.getOrderVo(id);
			orderLogVoList = orderService.getOrderLogVoList(id);
			model.addAttribute("order", orderVo);
			model.addAttribute("orderLogVoList", orderLogVoList);
		} catch (Exception e) {
			logger.error("查看订单失败！",e);
		}
		return "/orders/orderView";
	}

	/**
	 *
	 * @param model
	 * @param id
	 * @return
     * @throws Exception
     */
	@RequestMapping(value="nuomiView/{id}")
	public String nuomiView(Model model,@PathVariable String id) throws Exception {
		OrderVo orderVo;
		List<OrderLogVo> orderLogVoList;
		try {
			orderVo = orderService.getOrderVo(id);
			orderLogVoList = orderService.getOrderLogVoList(id);
			model.addAttribute("order", orderVo);
			model.addAttribute("orderLogVoList", orderLogVoList);
		} catch (Exception e) {
			logger.error("查看订单失败！",e);
		}
		return "/orders/nuomiView";
	}

	@RequestMapping(value="nuomiVerify/{id}")
	public String orderVerifyNuomi(Model model,HttpServletRequest request,@PathVariable String id, RedirectAttributes redirectAttributes) throws Exception {
		try {
				orderService.orderVerifyNuomi(id,false);
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "确认订单成功!"));
		} catch (Exception e) {
			logger.error("确认订单失败！",e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "确认订单失败:" + e.getMessage()));
		}
		return "redirect:/orders/nuomiList";
	}

	
	/**
     * 订单详情
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="update/{id}")
	public String update(Model model,HttpServletRequest request,@PathVariable String id) throws Exception {
		OrderVo orderVo;
		try {
			orderVo = orderService.getOrderVo(id);
			Calendar cal = Calendar.getInstance();
			List<OrderDate> dates = new ArrayList<OrderDate>();
			OrderDate orderDate = null;
			for(int i=0;i<15;i++){
				orderDate = new OrderDate();
				Date date = cal.getTime();
				orderDate.setDateValue(DateUtil.formatDate(date, "yyyy-MM-dd"));
				orderDate.setDateDisplay(DateUtil.formatDate(date, "M-d"));
				orderDate.setWeek(DateUtils.getWeek(cal.getTime()));
				dates.add(orderDate);
				cal.add(Calendar.DATE, 1);
			}
			model.addAttribute("orderDate",dates);
			model.addAttribute("order", orderVo);
		} catch (Exception e) {
			logger.error("查看订单失败！",e);
		}
		return "/orders/update";
	}
	
	@RequestMapping(value="modify")
	public String modify(Model model,HttpServletRequest request,String orderId,String orderParam,String orderDate,RedirectAttributes redirectAttributes) throws Exception {
		try {
			Type type = new TypeToken<List<OrderParam>>() {}.getType();
			List<OrderParam> params = MyGson.getInstance().fromJson(orderParam, type);
			orderService.orderModify(orderId, params, orderDate);
		} catch (Exception e) {
			logger.error("修改订单失败！",e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "修改订单失败:" + e.getMessage()));
		}
		return "redirect:/orders/list";
	}
	
	/**
     * 确认订单
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="orderVerify/{id}/{viewId}")
	public String orderVerify(Model model,HttpServletRequest request,@PathVariable String id,@PathVariable String viewId, RedirectAttributes redirectAttributes) throws Exception {
		try {
			orderService.orderVerify(id,false);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "确认订单成功!"));
		} catch (Exception e) {
			logger.error("确认订单失败！",e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "确认订单失败:" + e.getMessage()));
		}
		return "redirect:/orders/view/"+viewId;
	}
	
	/**
     * 确认订单
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="orderVerify/{id}")
	public String orderVerify(Model model,HttpServletRequest request,@PathVariable String id, RedirectAttributes redirectAttributes) throws Exception {
		try {
			String[] ids = id.split(",");
			for(String d:ids){
				orderService.orderVerify(d,false);
			}
			// 发送短信
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "确认订单成功!"));
		} catch (Exception e) {
			logger.error("确认订单失败！",e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "确认订单失败:" + e.getMessage()));
		}
		return "redirect:/orders/list";
	}

	/**
	 * 申请退款
	 * @param orderId
	 * @param viewId
	 * @param reason1
	 * @param reason
	 * @param request
	 * @param redirectAttributes
     * @return
     * @throws Exception
     */
	@RequestMapping(value="applyRefund")
	public String applyRefund(String orderId,String viewId,String reason1,String reason,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		try {
//			//2.0上线去掉开场前4小时不允许退款的限制
//			//查看订单是否允许退款，活动和赛事开场前4个小时不能退款
//			if(orderService.allownRefund(orderId)){
				orderService.applyRefund(orderId,CommonUtils.isNotEmpty(reason)?reason:reason1);
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "申请退款成功!"));	
//			}else{
//				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "申请退款失败!开场前4小时之内不能退款！"));
//			}

		} catch (Exception e) {
			logger.error("申请退款失败！",e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "申请退款失败:" + e.getMessage()));
		}
		return "redirect:/orders/view/"+orderId;
	}

	/**
	 * 确认退款
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="confirmRefund/{id}")
	@ResponseBody
	public String confirmRefund(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (!CommonUtils.isNotEmpty(id)) {
				result.put(Constants.Result.REASON, "退款失败，订单号为空！");
				return gson.toJson(result);
			}
			return gson.toJson(orderService.confirmRefund(id));
		} catch (Exception e) {
			logger.error("确认退款失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "退款失败，请联系工作人员!");
			return gson.toJson(result);
		}
	}
	
	/**
	 * <确认退款至账户><功能具体实现>
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年12月4日 下午4:04:53
	 */
	@RequestMapping(value="confirmRefundNew/{id}")
	@ResponseBody
	public String confirmRefundNew(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (!CommonUtils.isNotEmpty(id)) {
				result.put(Constants.Result.REASON, "退款至账户失败，订单号为空！");
				return gson.toJson(result);
			}
			return gson.toJson(orderService.confirmRefundNew(id));
		} catch (Exception e) {
			logger.error("确认退款至账户失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "退款至账户失败，请联系工作人员!");
			return gson.toJson(result);
		}
	}
	
	/**
	 * <确认退款至账户><功能具体实现>
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年12月4日 下午4:04:53
	 */
	@RequestMapping(value="confirmRefundCompany/{id}")
	@ResponseBody
	public String confirmRefundCompany(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (!CommonUtils.isNotEmpty(id)) {
				result.put(Constants.Result.REASON, "退款至账户失败，订单号为空！");
				return gson.toJson(result);
			}
			return gson.toJson(orderService.confirmRefundCompany(id));
		} catch (Exception e) {
			logger.error("确认退款至账户失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "退款至账户失败，请联系工作人员!");
			return gson.toJson(result);
		}
	}
	/**
	 * <确认退款至账户><功能具体实现>
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年12月4日 下午4:04:53
	 */
	@RequestMapping(value="confirmRefundCmb/{id}")
	@ResponseBody
	public String confirmRefundCmb(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (!CommonUtils.isNotEmpty(id)) {
				result.put(Constants.Result.REASON, "退款至招行失败，订单号为空！");
				return gson.toJson(result);
			}
			return gson.toJson(orderService.confirmRefundCmb(id));
		} catch (Exception e) {
			logger.error("确认退款至招行失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "退款至招行失败，请联系工作人员!");
			return gson.toJson(result);
		}
	}
	/**
	 * <确认退款至账户><功能具体实现>
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年12月4日 下午4:04:53
	 */
	@RequestMapping(value="confirmRefundJd/{id}")
	@ResponseBody
	public String confirmRefundJd(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put(Constants.Result.RESULT, false);
			if (!CommonUtils.isNotEmpty(id)) {
				result.put(Constants.Result.REASON, "退款至京东失败，订单号为空！");
				return gson.toJson(result);
			}
			return gson.toJson(orderService.confirmRefundJd(id));
		} catch (Exception e) {
			logger.error("确认退款至京东失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "退款至京东失败，请联系工作人员!");
			return gson.toJson(result);
		}
	}
	
	/**
	 * 处理订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="handleList")
	public String handleOrders(Model model){
		User user = SessionUtil.currentUser();
		String id = user.getUserId();
		try {
			List<OrderVo> orderHandling = orderService.getHandling(id);
			List<Map<String,String>> orderHandler = new ArrayList<Map<String,String>>();
			model.addAttribute("HandleOrder", orderHandler);
			model.addAttribute("HandlingOrder", orderHandling);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/orders/handle";
	}
	
	@RequestMapping(value="handleTask")
	@ResponseBody
	public String handleTask() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = SessionUtil.currentUser();
		String id = user.getUserId();
		try {
			List<Map<String,String>> orderHandler = orderService.handle(id);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, orderHandler);
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("获取订单任务失败！",e);
			if("not_get_task".equals(e.getMessage())){
				result.put(Constants.Result.RESULT, true);
				result.put(Constants.Result.DATA, "not_get_task");
			}else{
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "获取订单任务失败！");
			}
			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value="item/{id}")
	@ResponseBody
	public String item(@PathVariable String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> orderHandler = orderService.item(id);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, orderHandler);
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("获取订单详情失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "获取订单详情失败！");
			return gson.toJson(result);
		}
	}

	/**
	 * 确认订单
	 * @param id
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="orderVerifyAjx/{id}")
	@ResponseBody
	public String orderVerifyAjx(@PathVariable String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			orderService.orderVerify(id,true);
			result.put(Constants.Result.RESULT, true);
			// 发送短信
			orderSendMsgService.sendMsg(id);
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("确认订单失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "确认订单失败！");

			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value="backHandel")
	@ResponseBody
	public String backHandel(String orderId,String reason) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			orderService.backHandel(orderId,reason);
			result.put(Constants.Result.RESULT, true);
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("确认订单失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "稍后处理失败！");
			return gson.toJson(result);
		}
	}

	/**
	 * 丢弃订单
	 * @param id
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="discard/{id}")
	@ResponseBody
	public String discard(@PathVariable String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Order order = orderService.selectByPrimaryKey(Order.class, id);
			order.setHandler(null);
			orderService.updateByPrimaryKey(order, id);
			result.put(Constants.Result.RESULT, true);
			return gson.toJson(result);
		} catch (Exception e) {
			logger.error("丢弃订单失败！",e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "丢弃订单失败！");
			return gson.toJson(result);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("2016-03-10 15:54".compareTo(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")));
	}
}
