package com.lc.zy.ball.boss.framework.statistic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ex.StatisticUalogMapperEx;
import com.lc.zy.ball.domain.oa.po.StatisticUalogCriteria;
import com.lc.zy.ball.domain.oa.po.ex.StatisticUalogEx;
import com.lc.zy.common.data.Criterias;

@Service
public class StatisticUalogService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory.getLogger(StatisticUalogService.class);

	@Autowired
	private StatisticUalogMapperEx statisticUalogMapperEx;

	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：09-14
	 * @author： bhg
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatisticUalogEx> findUalogList(PageRequest pageRequest, Map<String, Object> searchParams, boolean isPage,
			boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<StatisticUalogEx> list = new ArrayList<StatisticUalogEx>();
		try {
			StatisticUalogCriteria statisticUalogCriteria = new StatisticUalogCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				statisticUalogCriteria.setMysqlLength(pageRequest.getPageSize());
				statisticUalogCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticUalogCriteria.Criteria cri = statisticUalogCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			statisticUalogCriteria.setOrderByClause("counter desc");
			// 数量
			if (isHasCount) {
					total = statisticUalogMapperEx.countUalogExNow(statisticUalogCriteria);
			}
			// list
				list = statisticUalogMapperEx.findUalogExNow(statisticUalogCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取list(StatisticUalogService--findUalogList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：09-14
	 * @author： bhg
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public int otherTotal() {
		// 数量
		int total = 0;
		// list
		try {
			// 数量
					total = statisticUalogMapperEx.countOther();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：09-14
	 * @author： bhg
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatisticUalogEx> findUalogListByChannel(PageRequest pageRequest, Map<String, Object> searchParams, boolean isPage,
			boolean isHasCount,int flagType) {
		// 数量
		int total = 0;
		// list
		List<StatisticUalogEx> list = new ArrayList<StatisticUalogEx>();
		try {
			StatisticUalogCriteria StatisticUalogCriteria = new StatisticUalogCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				StatisticUalogCriteria.setMysqlLength(pageRequest.getPageSize());
				StatisticUalogCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticUalogCriteria.Criteria cri = StatisticUalogCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			StatisticUalogCriteria.setOrderByClause("ct asc");
			// 数量
			if (isHasCount) {
				if(flagType ==1){
					total = statisticUalogMapperEx.countUalogExByHour(StatisticUalogCriteria);
				}else if(flagType ==2){
					total = statisticUalogMapperEx.countUalogExByDay(StatisticUalogCriteria);
				}
			}
			// list
			if(flagType ==1){
				list = statisticUalogMapperEx.findUalogExByHour(StatisticUalogCriteria);
			}else if(flagType ==2){
				list = statisticUalogMapperEx.findUalogExByDay(StatisticUalogCriteria);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(StatisticRegisterUserService--findUalogListByChannel)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}


}
