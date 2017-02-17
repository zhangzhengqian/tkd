package com.lc.zy.ball.domain.oa.mapper.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author yankefei
 * @date 2015年11月13日 上午9:49:09
 */
public interface StatisticUserMobileMapperEx{
	
	List<String> findAllChannels();
	
	List<String> findPhonesByChannel(@Param(value="channel") String channel);
	
	int countOrdersByPhone(Map<String, Object> map);
	
}