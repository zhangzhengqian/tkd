package com.lc.zy;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lc.zy.ball.boss.framework.orders.service.OrderService;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.po.Holiday;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.FreeMarkerUtils;

public class InitPrice {
	private static BufferedReader br;
	private static String paths[] = { "classpath:spring/applicationContext.xml" };
	public static ApplicationContext ctx = null;

	public static void main(String[] args) throws Exception {
		ctx = new ClassPathXmlApplicationContext(paths);
		OrderMapper mapper = ctx.getBean(OrderMapper.class);
		OrderItemMapper mapper_ = ctx.getBean(OrderItemMapper.class);
		OrderService mapper__ = ctx.getBean(OrderService.class);
		JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("oaJdbcTemplate");
		Map<String, Integer> subMap = getStatiumSubsidiesMap(jdbcTemplate);
		Map<String, List<Integer>> costMap = getSpaceCostMap(jdbcTemplate);
		OrderCriteria criteria = new OrderCriteria();
		OrderCriteria.Criteria cri = criteria.createCriteria();
		cri.andOrdersTypeEqualTo(0);
		List<Order> orders = mapper.selectByExample(criteria);
		for (Order order : orders) {
			OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
			OrderItemCriteria.Criteria orderItemCri = orderItemCriteria.createCriteria();
			orderItemCri.andOrderIdEqualTo(order.getId());
			List<OrderItem> orderItems = mapper_.selectByExample(orderItemCriteria);
			if(CollectionUtils.isEmpty(orderItems)){
				continue;
			}
			int workDay = checkWorkday(DateUtil.formatDate(orderItems.get(0).getStartDate(), "yyyy-MM-dd"),mapper__);
			int cost = 0;
			for(OrderItem item_:orderItems){
				Date startTime = new Date(item_.getStartTime() * 1000L);
				Long startHour = DateUtils.getFragmentInHours(startTime, Calendar.DATE);
				List<Integer> costs = costMap.get(item_.getSpaceId()+String.valueOf(workDay));
				if(CollectionUtils.isNotEmpty(costs)){
					cost = costs.get(startHour.intValue());
					OrderItem newItem = new OrderItem();
					newItem.setId(item_.getId());
					newItem.setCostPrice(cost);
					mapper__.updateByPrimaryKeySelective(newItem, newItem.getId());
				}else{
					
				}
			}
			
			int sub = 0;
			if(cost!=0){
				if(subMap.containsKey(order.getStatiumId()+"_"+orderItems.get(0).getSportType())){
					sub = subMap.get(order.getStatiumId()+"_"+orderItems.get(0).getSportType());
					Order newOrder = new Order();
					newOrder.setId(order.getId());
					newOrder.setSubsidies(sub);
					mapper__.updateByPrimaryKeySelective(newOrder, order.getId());
				}
			}
			
			
		}
	}

	private static Map<String, Integer> getStatiumSubsidiesMap(JdbcTemplate jdbcTemplate) {
		Map<String, Object> paras = new HashMap<String, Object>();
		String sql = FreeMarkerUtils.format("/template/order/statium_subsidies.ftl", paras);
		List<Map<String, Object>> statiumSubsidiesMaps = jdbcTemplate.queryForList(sql);
		Map<String, Integer> statiumSubsidiesMap = new HashMap<String, Integer>();
		for (Map<String, Object> map : statiumSubsidiesMaps) {
			String key = (String) map.get("k");
			Integer value = (Integer) map.get("v");
			statiumSubsidiesMap.put(key, (new BigDecimal(value).multiply(new BigDecimal(100))).intValue());
		}
		return statiumSubsidiesMap;
	}

	private static Map<String, List<Integer>> getSpaceCostMap(JdbcTemplate jdbcTemplate) {
		Map<String, Object> paras = new HashMap<String, Object>();
		String sql = FreeMarkerUtils.format("/template/order/space_costvalue.ftl", paras);
		List<Map<String, Object>> spaceCostMaps = jdbcTemplate.queryForList(sql);
		Map<String, List<Integer>> spaceCostMap = new HashMap<String, List<Integer>>();
		for (Map<String, Object> map : spaceCostMaps) {
			String key = (String) map.get("k");
			String value = (String) map.get("v");
			String[] prices = value.split(",", -1);
			List<Integer> newPrice = new ArrayList<Integer>();
			for (int i = 0; i < prices.length; i++) {
				int price = 0;
				if (!"".equals(prices[i]) && !"0".equals(prices[i])) {
					price = Integer.parseInt(prices[i]);
				}
				newPrice.add(price);
			}
			spaceCostMap.put(key, newPrice);
		}
		return spaceCostMap;
	}
	
	private static Integer checkWorkday(String startDate,OrderService mapper__) throws Exception {
        // 是否是工作日0：否，1： 是
        Integer isWorkday = 1;
        // holiday为null是工作日，不为null节假日
        Holiday holiday = mapper__.selectByPrimaryKey(Holiday.class, startDate);
        if (holiday != null) {// 节假日
            isWorkday = 0;
        }
        return isWorkday;
    }
}
