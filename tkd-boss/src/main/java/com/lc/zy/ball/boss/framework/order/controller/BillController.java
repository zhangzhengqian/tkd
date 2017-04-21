package com.lc.zy.ball.boss.framework.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.framework.order.service.BillService;
import com.lc.zy.ball.boss.framework.order.vo.BillTermVo;
import com.lc.zy.ball.boss.framework.order.vo.OrderBillVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.po.OrderBill;
import com.lc.zy.ball.domain.oa.po.OrderBillCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.OrderBillItemCriteria;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value="/bill")
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private OrderBillMapper orderBillMapper;
	
	@Autowired
	private OrderBillItemMapper orderBillItermMapper;
	
	Gson gson = new Gson();
	
	private static Logger logger = LoggerFactory.getLogger(BillController.class);
	/**
	 * 
	 * <道馆结账列表显示><功能具体实现>
	 *
	 * @create：2016年9月19日 下午5:11:47
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderBill")
	public String list(Model model,HttpServletRequest request){
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		Page<BillTermVo> data = billService.find(page, pageSize);
		model.addAttribute("data", data);
		return "order/orderBillList";
	}
	
	/**
	 * 
	 * <生成账单><功能具体实现>
	 *
	 * @create：2016年9月22日 上午9:26:25
	 * @author：zzq
	 * @param startDate
	 * @param endDate
	 * @param ordersType
	 * @return
	 */
	@RequestMapping(value="/billBuild")
	public String billBuild(String startDate,String endDate,Integer ordersType){
		billService.billBuild(startDate,endDate,ordersType);
		return "redirect:/bill/orderBill";
	}
	
	/**
	 * 
	 * <是否可以生成账单><功能具体实现>
	 *
	 * @create：2016年9月23日 上午10:55:17
	 * @author：zzq
	 * @param startDate
	 * @param ordersType
	 * @return
	 */
	@RequestMapping(value="/isBuildOk")
	@ResponseBody
	public String isBuildOk(String startDate,Integer ordersType){
		//课程活动 ordersType 0  充值订单 ordersType 1 
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Boolean flag = billService.checkAdd(startDate,ordersType);
			if(flag==true){
				//可以生成
				result.put(Constants.Result.RESULT, true);
			}
			else if(flag==false){
				//不可以生成
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "生成账单日期不连续");
			}
		} catch (ParseException e) {
			e.getMessage();
		}
		return gson.toJson(result);
	}
	
	/**
	 * 
	 * <删除订单统计><功能具体实现>
	 *
	 * @create：2016年9月23日 上午11:25:03
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delBill")
	public String delBill(String id){
		logger.debug(id);
		billService.deleteBill(id);
		return "redirect:/bill/orderBill";
	}
	
	/**
	 * 
	 * <道馆结账详情><功能具体实现>
	 *
	 * @create：2016年9月23日 下午7:09:26
	 * @author：zzq
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/statiumBill/{id}")
	public String billDetail(@PathVariable String id,Model model,HttpServletRequest request){
		try {
			logger.debug("结账id"+id);
			int page = WebUtils.getPage(request);
			int pageSize = WebUtils.getPageSize(request);
			Page<OrderBillVo> data = billService.findDetail(page, pageSize,id,"","");
			model.addAttribute("data", data);
			model.addAttribute("billId", id);
		} catch (Exception e) {
			e.getMessage();
		}
		return "order/billDetail";
	}
	
	/**
	 * 
	 * <每日详情><功能具体实现>
	 *
	 * @create：2016年9月23日 下午7:10:14
	 * @author：zzq
	 * @param model
	 * @param billd
	 * @return
	 */
	@RequestMapping(value="/init_orderBill_dlg")
	public String getOrderDetail(Model model,String billId){
		OrderBillItemCriteria orderBillItemCriteria = new OrderBillItemCriteria();
		orderBillItemCriteria.createCriteria().andOrderBillIdEqualTo(billId);
		orderBillItemCriteria.setOrderByClause("start_date desc");
		List<OrderBillItem> itemList = orderBillItermMapper.selectByExample(orderBillItemCriteria);
		Integer totalFee = 0;
		int orderCount = 0;
		for(OrderBillItem item:itemList){
			totalFee+=item.getTotalFee();
			orderCount+= item.getOrderCount();
			item.setTotalFee(new BigDecimal(item.getTotalFee()).divide(new BigDecimal(100)).intValue());
		}
		model.addAttribute("items", itemList);
		model.addAttribute("totalFee", new BigDecimal(totalFee).divide(new BigDecimal(100)));
		model.addAttribute("orderCount", orderCount);
		return "order/init_orderBill_dlg";
	}
	
	/**
	 * 
	 * <条件查询订单详情><功能具体实现>
	 *
	 * @create：2016年9月26日 下午5:07:33
	 * @author：zzq
	 * @param billId
	 * @param queryCondition
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/statiumBillSearchByStatiumName/{billId}/{queryCondition}")
	public String statiumBillSearchByStatiumName(@PathVariable String billId,@PathVariable String queryCondition,Model model,HttpServletRequest request){
		logger.debug(billId+queryCondition);
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		Page<OrderBillVo> data = billService.findDetail(page, pageSize,billId,queryCondition,"");
		model.addAttribute("data", data);
		model.addAttribute("billId", billId);
		return "order/billDetail";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	}
	
	/**
	 * 
	 * <单独确认和批量确认道馆结账><功能具体实现>
	 *
	 * @create：2016年9月27日 上午10:18:18
	 * @author：zzq
	 * @param model
	 * @param id
	 * @param billId
	 * @return
	 */
	@RequestMapping(value="/verifyBillSel")
	public String verifyBillSel(Model model,String id,String billId){
		logger.debug(id+billId);
		String[] ids = id.split(",", -1);
		billService.verifyBill(ids);
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <结算或者批量结算账单><功能具体实现>
	 *
	 * @create：2016年9月27日 下午3:11:06
	 * @author：zzq
	 * @param model
	 * @param id
	 * @param billId
	 * @return
	 */
	@RequestMapping(value="/balanceBillSel")
	public String balanceBillSel(Model model,String id,String billId){
		String[] ids = id.split(",", -1);
		billService.balanceBill(ids);
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <全部确认><功能具体实现>
	 *
	 * @create：2016年9月27日 下午4:14:54
	 * @author：zzq
	 * @param billId
	 * @return
	 */
	@RequestMapping(value="/verifyBillAll")
	public String verifyBillAll(String billId){
		billService.verifyBillAll(billId);
		return "redirect:/bill/statiumBill/"+billId;
	} 
	
	/**
	 * 
	 * <全部结算><功能具体实现>
	 *
	 * @create：2016年9月27日 下午5:01:26
	 * @author：zzq
	 * @param billId
	 * @return
	 */
	@RequestMapping(value="/balanceBillAll")
	public String balanceBillAll(String billId){
		billService.balanceBillAll(billId);
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <导出新的账单><功能具体实现>
	 *
	 * @create：2016年9月28日 上午10:10:27
	 * @author：zzq
	 * @param billId
	 * @param type
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/statiumBillExport/{billId}/0")
	public String statiumBillExport0(@PathVariable String billId,HttpServletResponse response) throws IOException{
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andBillIdEqualTo(billId).andStatusEqualTo(0);
		List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
		if(billList.size()!=0){
			billService.writeExcel(response, billList);
		}
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <导出已确认待结算账单><功能具体实现>
	 *
	 * @create：2016年9月28日 上午10:56:40
	 * @author：zzq
	 * @param billId
	 * @param type
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/statiumBillExport/{billId}/1")
	public String statiumBillExport1(@PathVariable String billId,HttpServletResponse response) throws IOException{
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andBillIdEqualTo(billId).andStatusEqualTo(1);
		List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
		if(billList.size()!=0){
			billService.writeExcel(response, billList);
		}
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <导出已结算账单><功能具体实现>
	 *
	 * @create：2016年9月28日 上午11:00:16
	 * @author：zzq
	 * @param billId
	 * @param type
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/statiumBillExport/{billId}/2")
	public String statiumBillExport2(@PathVariable String billId,HttpServletResponse response) throws IOException{
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andBillIdEqualTo(billId).andStatusEqualTo(2);
		List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
		if(billList.size()!=0){
			billService.writeExcel(response, billList);
		}
		return "redirect:/bill/statiumBill/"+billId;
	}
	
	/**
	 * 
	 * <查询新账单><功能具体实现>
	 *
	 * @create：2016年9月28日 上午11:20:56
	 * @author：zzq
	 * @param billId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/statiumBillSearch/{billId}/0")
	public String statiumBillSearch0(@PathVariable String billId,Model model,HttpServletRequest request){
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		Page<OrderBillVo> data = billService.findDetail(page, pageSize,billId,"","0");
		model.addAttribute("data", data);
		model.addAttribute("billId", billId);
		return "order/billDetail";
	}
	
	/**
	 * 
	 * <查询待结算><功能具体实现>
	 *
	 * @create：2016年9月28日 上午11:32:50
	 * @author：zzq
	 * @param billId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/statiumBillSearch/{billId}/1")
	public String statiumBillSearch1(@PathVariable String billId,Model model,HttpServletRequest request){
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		Page<OrderBillVo> data = billService.findDetail(page, pageSize,billId,"","1");
		model.addAttribute("data", data);
		model.addAttribute("billId", billId);
		return "order/billDetail";
	}
	
	/**
	 * 
	 * <异步校验有无理想账单><功能具体实现>
	 *
	 * @create：2016年11月4日 下午6:40:22
	 * @author：zzq
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/isExportOk")
	@ResponseBody
	public String isExportOk(String billId,String type){
		Map<String,Object> result = new HashMap<String,Object>();
		Boolean flag = false;
		try {
			flag = billService.isExportOk(type, billId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!flag){
			result.put("reason", "无该类型的账单 无法导出");
		}
		result.put("result", flag);
		try {
			logger.debug(gson.toJson(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(result);
	}
}
