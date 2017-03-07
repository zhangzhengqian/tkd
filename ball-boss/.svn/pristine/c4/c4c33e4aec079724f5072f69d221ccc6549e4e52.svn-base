package com.lc.zy.ball.boss.framework.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.OrderSmsMapper;
import com.lc.zy.ball.domain.oa.po.OrderSms;
import com.lc.zy.ball.domain.oa.po.OrderSmsCriteria;

@Service
public class OrderSmsService extends AbstractCacheService {
	@Autowired
	private OrderSmsMapper orderSmsMapper = null;
	
	public Map<String,String> getOrderSmsMap(String year){
		Map<String,String> map = new HashMap<String,String>();
		OrderSmsCriteria orderSmsCriteria = new OrderSmsCriteria();
		orderSmsCriteria.createCriteria().andYearEqualTo(year);
		List<OrderSms> list =  orderSmsMapper.selectByExample(orderSmsCriteria);
		for( OrderSms h : list ){
			h.getId();
			map.put(h.getId(), h.getYear());
		}
		return map;
	}
	
}
