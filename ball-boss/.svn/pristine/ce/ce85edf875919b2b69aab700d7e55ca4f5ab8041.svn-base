package com.lc.zy.ball.boss.framework.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.HolidayMapper;
import com.lc.zy.ball.domain.oa.po.Holiday;
import com.lc.zy.ball.domain.oa.po.HolidayCriteria;

@Service
public class HolidayService extends AbstractCacheService {
	@Autowired
	private HolidayMapper holidayMapper = null;
	
	public Map<String,String> getHolidayMap(String year){
		Map<String,String> map = new HashMap<String,String>();
		HolidayCriteria holidayCriteria = new HolidayCriteria();
		holidayCriteria.createCriteria().andYearEqualTo(year);
		List<Holiday> list =  holidayMapper.selectByExample(holidayCriteria);
		for( Holiday h : list ){
			h.getId();
			map.put(h.getId(), h.getYear());
		}
		return map;
	}
	
}
