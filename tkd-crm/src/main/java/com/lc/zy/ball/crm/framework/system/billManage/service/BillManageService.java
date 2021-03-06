package com.lc.zy.ball.crm.framework.system.billManage.service;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.billManage.vo.BillManageVo;
import com.lc.zy.ball.crm.framework.system.order.vo.OrderVo;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BillManageService extends AbstractCacheService{
	
	private static Logger logger = LoggerFactory.getLogger(BillManageService.class);
	
	@Autowired
	private OrderBillMapper orderBillMapper;
	
	@Autowired
	private OrderBillItemMapper orderBillItemMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	/**
	 * 
	 * <账单列表展示><功能具体实现>
	 *
	 * @create：2016年12月16日 下午3:07:48
	 * @author：zzq
	 * @param pageRequest
	 * @param billType
	 * @return
	 */
	public Page<BillManageVo> list(PageRequest pageRequest,Integer billType){
		logger.debug("账单类型"+billType);
		String statiumId = SessionUtil.currentStatium();
		OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
		orderBillCriteria.createCriteria().andStatiumIdEqualTo(statiumId).andStatusNotEqualTo(0);
		orderBillCriteria.setMysqlLength(pageRequest.getPageSize());
		orderBillCriteria.setMysqlOffset(pageRequest.getOffset());
		orderBillCriteria.setOrderByClause("ct desc");
		//查询类型是两种类型的结果集
		List<OrderBill> billList = orderBillMapper.selectByExample(orderBillCriteria);
		//封装两个泛型是BillManageVo的list  一种存放type是0的结果集 一种存放type是1的结果集
		List<BillManageVo> listTypeApp = new ArrayList<BillManageVo>();
		List<BillManageVo> listTypeCard = new ArrayList<BillManageVo>();
		
		int total1 = 0;
		int total2 = 0;
		
		for(OrderBill vo : billList){
			BillManageVo billVo = new BillManageVo();
			String startStr = vo.getBillId().substring(0, 8);
			String endStr = vo.getBillId().substring(8, 16);
			
			//账单类型
			Integer type = Integer.parseInt(vo.getBillId().substring(16, 17));
			long startTime = Long.valueOf(startStr)*100000;
			long endTime = Long.valueOf(endStr)*100000;
			Date startDate = new Date(startTime);
			Date endDate = new Date(endTime);
			String start = DateUtils.formatDate(startDate, "yyyy-MM-dd");
			String end = DateUtils.formatDate(endDate, "yyyy-MM-dd");
			//开始时间
			billVo.setStartDate(start);
			//结束时间
			billVo.setEndDate(end);
			//订单总数
			billVo.setTotalNum(vo.getOrderNum());
			//订单金额
			billVo.setTotalAmount(vo.getFee()/100);
			//创建时间
			billVo.setBillCt(vo.getCt());
			//道馆id
			billVo.setStatiumId(statiumId);
			
			//billId
			billVo.setBillId(vo.getBillId());
			if(type == 0){
				++total1;
				listTypeApp.add(billVo);
				
			}else if(type == 1){
				++total2;
				listTypeCard.add(billVo);
			}
		}
		if(billType == 0){
			return new PageImpl<>(listTypeApp, pageRequest, total1);
		}else{
			return new PageImpl<>(listTypeCard, pageRequest, total2);
		}
	}
	
	/**
	 * 
	 * <一段时间内的账单详情><功能具体实现>
	 *
	 * @create：2016年12月16日 下午3:09:46
	 * @author：zzq
	 * @param orderBillId
	 * @return
	 */
	public Page<OrderBillItem> getBillItem(PageRequest pageRequest,String orderBillId){
		OrderBillItemCriteria orderBillItemCriteria = new OrderBillItemCriteria();
		orderBillItemCriteria.createCriteria().andOrderBillIdEqualTo(orderBillId);
		orderBillItemCriteria.setOrderByClause("start_date desc");
		orderBillItemCriteria.setMysqlLength(pageRequest.getPageSize());
		orderBillItemCriteria.setMysqlOffset(pageRequest.getOffset());
		List<OrderBillItem> items = orderBillItemMapper.selectByExample(orderBillItemCriteria);
		for(OrderBillItem item : items){
			item.setTotalFee(item.getTotalFee()/100);
		}
		int total = orderBillItemMapper.countByExample(orderBillItemCriteria);
		return new PageImpl<>(items, pageRequest, total);
	}
	
	/**
	 * 
	 * <统计这段时间内的账单总金额><功能具体实现>
	 *
	 * @create：2016年12月16日 下午3:50:56
	 * @author：zzq
	 * @param orderBillId
	 * @return
	 */
	public int getBillTotalAmount(String orderBillId){
		int total = 0;
		OrderBillItemCriteria orderBillItemCriteria = new OrderBillItemCriteria();
		orderBillItemCriteria.createCriteria().andOrderBillIdEqualTo(orderBillId);
		orderBillItemCriteria.setOrderByClause("start_date desc");
		List<OrderBillItem> items = orderBillItemMapper.selectByExample(orderBillItemCriteria);
		for(OrderBillItem item : items){
			total += item.getTotalFee()/100;
		}
		return total;
	}
	
	/**
	 * 
	 * <订单list><功能具体实现>
	 *
	 * @create：2016年12月16日 下午5:15:00
	 * @author：zzq
	 * @param type
	 * @param signDate
	 * @param pageRequest
	 * @return
	 */
	public Page<OrderVo> findOrderList(String type,String signDate,PageRequest pageRequest){
		Map<String,Object> paras = new HashMap<String,Object>();
		String statiumId = SessionUtil.currentStatium();
		
			if(type!=null){
				if(type.equals("app")){
					//app订单 ordersType封装数组 存0 1
					int app[] = {0,1};
					paras.put("ordersTypeIn", app);
					if(StringUtils.isNotEmpty(signDate)){
						paras.put("signDate", signDate);
					}
				}else if(type.equals("card")){
					paras.put("ordersType", 2);
					if(StringUtils.isNotEmpty(signDate)){
						paras.put("cardBuyDate", signDate);
					}
				}
			}
			if(StringUtils.isNotEmpty(statiumId)){
				paras.put("statiumId", statiumId);
			}
		
		paras.put("pageSize", pageRequest.getPageSize());
		paras.put("offset",pageRequest.getOffset());
		//生成复杂sql  根据类型使用不同的sql
		String sqlList = "";
		String sqlForCount = "";
		if(type.equals("app")){
			sqlList = FreeMarkerUtils.format("/template/order/order_search_bill0.ftl", paras);
			sqlForCount = FreeMarkerUtils.format("/template/order/order_search_bill_count0.ftl", paras);
		}else if(type.equals("card")){
			sqlList = FreeMarkerUtils.format("/template/order/order_search_bill1.ftl", paras);
			sqlForCount = FreeMarkerUtils.format("/template/order/order_search_bill_count1.ftl", paras);
		}
		
		logger.debug(sqlList);
		logger.debug(sqlForCount);
		
		//jdbcTemplate查询方法
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlList);
		List<Map<String,Object>> countlist = jdbcTemplate.queryForList(sqlForCount);
		Integer total = 0;
		if(countlist!=null){
			total = Integer.valueOf(countlist.get(0).get("cont").toString());
		}
		OrderVo vo = null;
		List<OrderVo> orderList = new ArrayList<OrderVo>();
		for(Map<String,Object> order : list){

			vo = new OrderVo();
			Date et = (Date)order.get("et");
			String orderId = (String)order.get("id");
			// 订单ID
			vo.setId(orderId);
			// 订单下单时间
			vo.setEt(et);
			// 订单金额
			if (order.get("finalFee") != null) {
				Double finel = BigDecimal.valueOf((Integer)order.get("finalFee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
				vo.setFinalFee(finel.intValue());
			}
			// 用户手机号
			if (order.get("userId") != null) {
				String userId = (String)order.get("userId");
				// 获取用户信息
				SsoUser ssoUser = null;
				try {
					ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
				} catch (Exception e) {
					logger.debug("获取用户信息失败 {}", e.getMessage());
				}
				if (ssoUser != null) {
					String phone = ssoUser.getPhone();
					vo.setPhone(phone);
				}
			}
			// 获取订单详情
			OrderItemCriteria itemCriteria = new OrderItemCriteria();
			OrderItemCriteria.Criteria itemCri = itemCriteria.createCriteria();
			itemCri.andOrderIdEqualTo(orderId);
			List<OrderItem> items = orderItemMapper.selectByExample(itemCriteria);
			if (CollectionUtils.isNotEmpty(items)) {
				// 预约时间
				String signTime = "";
				for(OrderItem item:items){
					if (item.getStartTime() != null) {
						signTime = item.getStartTime() + "~";
					}
					if (item.getEndTime() !=null) {
						signTime += item.getEndTime();
					}
					vo.setSignTime(signTime);
				}
				vo.setSignDate(DateUtils.getDate(signDate, "yyyy-MM-dd"));
				vo.setSignTime(signTime);
				
				//赠送金额
				String cardId = items.get(0).getCardId();
				if(StringUtils.isNotEmpty(cardId)){
					try {
						CrmCard crmCard = this.selectByPrimaryKey(CrmCard.class, cardId);
						if(crmCard!=null){
							vo.setCardName(crmCard.getCardName());
                            // 面值 moidify by sl 2016-12-21
                            vo.setCardAmount(crmCard.getCardAmount());
                            // modify by sl 2016-12-21 页面处理
							vo.setGiftFee(crmCard.getCardGift());
						}
					} catch (Exception e) {
						logger.error("查询卡片"+e.getMessage());
					}
					
				}
			}
			
			
			
			orderList.add(vo);
		
		}
		return new PageImpl<>(orderList,pageRequest,total);
	}
}
