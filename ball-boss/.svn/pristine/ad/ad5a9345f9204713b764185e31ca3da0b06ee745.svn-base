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
import com.lc.zy.ball.domain.oa.mapper.ex.StatisticSmsMapperEx;
import com.lc.zy.ball.domain.oa.po.StatisticSmsCriteria;
import com.lc.zy.ball.domain.oa.po.ex.StatisticSmsEx;
import com.lc.zy.common.data.Criterias;

@Service
public class StatisticSmsService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory.getLogger(StatisticSmsService.class);

	@Autowired
	private StatisticSmsMapperEx statisticSmsMapperEx;

	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：09-21
	 * @author： bhg
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatisticSmsEx> findSmsList(PageRequest pageRequest, Map<String, Object> searchParams, boolean isPage,
			boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<StatisticSmsEx> list = new ArrayList<StatisticSmsEx>();
		try {
			StatisticSmsCriteria statisticSmsCriteria = new StatisticSmsCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				statisticSmsCriteria.setMysqlLength(pageRequest.getPageSize());
				statisticSmsCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticSmsCriteria.Criteria cri = statisticSmsCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
//			statisticSmsCriteria.setOrderByClause("counter desc");
			// 数量
//			if (isHasCount) {
//					total = statisticSmsMapperEx.findSmsExAll(statisticSmsCriteria);
				
//			}
			// list
				list = statisticSmsMapperEx.findSmsExAll(statisticSmsCriteria);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取短信list(StatisticSmsService--findSmsList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	

}
