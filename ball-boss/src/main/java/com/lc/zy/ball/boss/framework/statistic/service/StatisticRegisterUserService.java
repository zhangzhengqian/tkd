package com.lc.zy.ball.boss.framework.statistic.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.SearchFilter;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statistic.vo.StatisticUserMobileVo;
import com.lc.zy.ball.boss.framework.statistic.vo.regUserVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatisticRegisterUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatisticsRegisteredUserDateMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.StatisticActiveUserIpMapperEx;
import com.lc.zy.ball.domain.oa.mapper.ex.StatisticRegisterUserMapperEx;
import com.lc.zy.ball.domain.oa.mapper.ex.StatisticUserMobileMapperEx;
import com.lc.zy.ball.domain.oa.po.StatisticActiveUserIpCriteria;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUserCriteria;
import com.lc.zy.ball.domain.oa.po.ex.StatisticActiveUserIpVo;
import com.lc.zy.ball.domain.oa.po.ex.StatisticRegisterUserEx;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;

@Service
public class StatisticRegisterUserService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory.getLogger(StatisticRegisterUserService.class);

	@Autowired
	private StatisticRegisterUserMapperEx statisticRegisterUserMapperEx;
	@Autowired
	private StatisticRegisterUserMapper statisticRegisterUserMapper;
	@Autowired
	private StatisticActiveUserIpMapperEx statisticActiveUserIpMapperEx;
	@Autowired
	private StatisticUserMobileMapperEx statisticUserMobileMapperEx;
	@Autowired
	private StatisticsRegisteredUserDateMapper statisticsRegisteredUserDateMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

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
	public Page<StatisticRegisterUserEx> findRegUserList(PageRequest pageRequest, Map<String, Object> searchParams,
			boolean isPage, boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<StatisticRegisterUserEx> list = new ArrayList<StatisticRegisterUserEx>();
		try {
			StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
				statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticRegisterUserCriteria.Criteria cri = statisticRegisterUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			statisticRegisterUserCriteria.setOrderByClause("avcounter desc");
			// 数量
			if (isHasCount) {
				total = statisticRegisterUserMapperEx.countChannelTotal(statisticRegisterUserCriteria);
			}
			// list
			list = statisticRegisterUserMapperEx.findRegUserExNow(statisticRegisterUserCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(StatisticRegisterUserService--findRegUserList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 获取7天、30天各个渠道注册用户数、激活数
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @author yankefei
	 */
	public Page<StatisticRegisterUserEx> findRegUserListEX(PageRequest pageRequest, Map<String, Object> searchParams,
			boolean isPage, boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<StatisticRegisterUserEx> list = new ArrayList<StatisticRegisterUserEx>();
		try {
			StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
				statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticRegisterUserCriteria.Criteria cri = statisticRegisterUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			statisticRegisterUserCriteria.setOrderByClause("convertratetotal desc");
			if (isHasCount) {
				total = statisticRegisterUserMapperEx.countChannelTotal(statisticRegisterUserCriteria);
			}
			// list
			list = statisticRegisterUserMapperEx.findRegUserExNowEx(statisticRegisterUserCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取注册用户listEx(StatisticRegisterUserService--findRegUserListEx)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	/**
	 * 
	 * <获取总数>
	 *
	 * @create：09-22
	 * @author： bhg
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public StatisticRegisterUserEx findRegUserTotal(Map<String, Object> searchParams) {

		StatisticRegisterUserEx ruTotal = null;
		try {
			StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

			StatisticRegisterUserCriteria.Criteria cri = statisticRegisterUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			ruTotal = statisticRegisterUserMapperEx.findRegUserExTotal(statisticRegisterUserCriteria).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户TOTAL(StatisticRegisterUserService--findRegUserTotalt)" + e.getMessage());
		}
		return ruTotal;
	}

	/**
	 * 最近7天、30天注册用户总数及激活总数
	 * 
	 * @param searchParams
	 * @return
	 * @author yankefei
	 */
	public StatisticRegisterUserEx findRegUserTotalEx(Map<String, Object> searchParams) {

		StatisticRegisterUserEx ruTotal = null;
		try {
			StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

			StatisticRegisterUserCriteria.Criteria cri = statisticRegisterUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			ruTotal = statisticRegisterUserMapperEx.findRegUserExTotalEx(statisticRegisterUserCriteria).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户totalEx(StatisticRegisterUserService--findRegUserTotalEx)" + e.getMessage());
		}
		return ruTotal;
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
	public Page<StatisticRegisterUserEx> findRegUserListByChannel(PageRequest pageRequest,
			Map<String, Object> searchParams, boolean isPage, boolean isHasCount, int flagType) {
		// 数量
		int total = 0;
		// list
		List<StatisticRegisterUserEx> list = new ArrayList<StatisticRegisterUserEx>();
		try {
			StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
				statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			StatisticRegisterUserCriteria.Criteria cri = statisticRegisterUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 排序
			statisticRegisterUserCriteria.setOrderByClause("ct desc");
			// 数量
			if (isHasCount) {
				if (flagType == 1) {
					total = statisticRegisterUserMapperEx.countRegUserExByHour(statisticRegisterUserCriteria);
					// }else if(flagType ==2){
					// total =
					// statisticRegisterUserMapperEx.countRegUserExByDay(statisticRegisterUserCriteria);
				} else if (flagType == 3) {
					total = statisticRegisterUserMapperEx.countRegUserExByMonth(statisticRegisterUserCriteria);
					// }else if(flagType ==4){
					// total =
					// statisticRegisterUserMapperEx.countAllRegUserExByDay(statisticRegisterUserCriteria);
				} else if (flagType == 5) {
					// 根据日期统计24小时记录总数，固定24小时
					total = 24;
				}
			}
			// list
			if (flagType == 1) {
				// 根据channel查询某天每个小时的注册数和激活数
				SearchFilter searchFilter = filters.get("GTE_ct");
				SearchFilter searchFilter2 = filters.get("EQ_channel");
				String dateStr = (String) searchFilter.value;
				dateStr = dateStr.substring(0, 10);
				String channel = (String) searchFilter2.value;
				List<StatisticRegisterUserEx> list1 = new ArrayList<StatisticRegisterUserEx>();
				List<StatisticRegisterUserEx> list2 = new ArrayList<StatisticRegisterUserEx>();
				list1 = statisticRegisterUserMapperEx.findRegUserExByHour(statisticRegisterUserCriteria);
				list2 = statisticRegisterUserMapperEx.findActivityUserExByHour(statisticRegisterUserCriteria);
				Map<String, Integer> map1 = convertion(list1, true);
				Map<String, Integer> map2 = convertion(list2, false);

				String key = null;
				for (int i = 0; i < 24; i++) {
					if (i < 10) {
						key = dateStr + " 0" + i;
					} else {
						key = dateStr + " " + i;
					}

					StatisticRegisterUserEx tempEx = new StatisticRegisterUserEx();
					tempEx.setChannel(channel);
					tempEx.setCt(key);
					tempEx.setCounter(map1.get(key));
					tempEx.setAvcounter(map2.get(key) == null ? null : String.valueOf(map2.get(key)));
					list.add(tempEx);
				}
			} else if (flagType == 2) {
				// 根据channel查询每天注册数和激活数
				List<StatisticRegisterUserEx> list1 = new ArrayList<StatisticRegisterUserEx>();
				List<StatisticRegisterUserEx> list2 = new ArrayList<StatisticRegisterUserEx>();
				list1 = statisticRegisterUserMapperEx.findRegUserExByDay(statisticRegisterUserCriteria);
				list2 = statisticRegisterUserMapperEx.findActivityUserExByDay(statisticRegisterUserCriteria);

				// 把注册用户表中数据根据ct时间放入map中
				Map<String, StatisticRegisterUserEx> map = new HashMap<String, StatisticRegisterUserEx>();
				if (list1 != null && list1.size() > 0) {
					for (StatisticRegisterUserEx user : list1) {
						map.put(user.getCt(), user);
					}
				}
				// 遍历激活用户表中数据，如果map中存在key，则设置激活数，如果没有，则新建记录
				if (list2 != null && list2.size() > 0) {
					for (StatisticRegisterUserEx user : list2) {
						if (map.containsKey(user.getCt())) {
							StatisticRegisterUserEx ex = map.get(user.getCt());
							user.setCounter(ex.getCounter());
							map.put(user.getCt(), user);
						} else {
							user.setCounter(null);
							map.put(user.getCt(), user);
						}
					}
				}

				// 按ct时间逆序输出
				list = new ArrayList<StatisticRegisterUserEx>(map.values());
				total = list.size();
				for (StatisticRegisterUserEx user : list) {
					if (user.getAvcounter() != null && !user.getAvcounter().equals("0")) {
						if (user.getCounter() != null) {
							BigDecimal decimal = new BigDecimal(user.getCounter());
							BigDecimal decimal1 = new BigDecimal(user.getAvcounter());
							user.setConvertrate(decimal.divide(decimal1, 4, BigDecimal.ROUND_HALF_UP).toString());
						}
					}
				}
				Collections.sort(list, new Comparator<StatisticRegisterUserEx>() {
					public int compare(StatisticRegisterUserEx o1, StatisticRegisterUserEx o2) {
						String code1 = o1.getCt();
						String code2 = o2.getCt();
						return code2.compareTo(code1);
					}
				});
			} else if (flagType == 3) {
				list = statisticRegisterUserMapperEx.findRegUserExByMonth(statisticRegisterUserCriteria);
			} else if (flagType == 4) {
				// 查询allChannel每天的注册数和激活数
				List<StatisticRegisterUserEx> list1 = new ArrayList<StatisticRegisterUserEx>();
				List<StatisticRegisterUserEx> list2 = new ArrayList<StatisticRegisterUserEx>();
				list1 = statisticRegisterUserMapperEx.findAllChannelRegUserExByDay(statisticRegisterUserCriteria);
				list2 = statisticRegisterUserMapperEx.findAllChannelActivityUserExByDay(statisticRegisterUserCriteria);

				// 把注册用户表中数据根据ct时间放入map中
				Map<String, StatisticRegisterUserEx> map = new HashMap<String, StatisticRegisterUserEx>();
				if (list1 != null && list1.size() > 0) {
					for (StatisticRegisterUserEx user : list1) {
						map.put(user.getCt(), user);
					}
				}
				// 遍历激活用户表中数据，如果map中存在key，则设置激活数，如果没有，则新建记录
				if (list2 != null && list2.size() > 0) {
					for (StatisticRegisterUserEx user : list2) {
						if (map.containsKey(user.getCt())) {
							StatisticRegisterUserEx ex = map.get(user.getCt());
							user.setCounter(ex.getCounter());
							map.put(user.getCt(), user);
						} else {
							user.setCounter(null);
							map.put(user.getCt(), user);
						}
					}
				}

				// 按ct时间逆序输出
				list = new ArrayList<StatisticRegisterUserEx>(map.values());
				total = list.size();
				for (StatisticRegisterUserEx user : list) {
					if (user.getAvcounter() != null && !user.getAvcounter().equals("0")) {
						if (user.getCounter() != null) {
							BigDecimal decimal = new BigDecimal(user.getCounter());
							BigDecimal decimal1 = new BigDecimal(user.getAvcounter());
							user.setConvertrate(decimal.divide(decimal1, 4, BigDecimal.ROUND_HALF_UP).toString());
						}
					}
				}
				Collections.sort(list, new Comparator<StatisticRegisterUserEx>() {
					public int compare(StatisticRegisterUserEx o1, StatisticRegisterUserEx o2) {
						String code1 = o1.getCt();
						String code2 = o2.getCt();
						return code2.compareTo(code1);
					}
				});
			} else if (flagType == 5) {
				// 查询allChannel某天每个小时的注册数和激活数
				SearchFilter searchFilter = filters.get("GTE_ct");
				String dateStr = (String) searchFilter.value;
				dateStr = dateStr.substring(0, 10);
				List<StatisticRegisterUserEx> list1 = new ArrayList<StatisticRegisterUserEx>();
				List<StatisticRegisterUserEx> list2 = new ArrayList<StatisticRegisterUserEx>();
				list1 = statisticRegisterUserMapperEx.findAllChannelRegUserExByHour(statisticRegisterUserCriteria);
				list2 = statisticRegisterUserMapperEx.findAllChannelActivityUserExByHour(statisticRegisterUserCriteria);
				Map<String, Integer> map1 = convertion(list1, true);
				Map<String, Integer> map2 = convertion(list2, false);

				String key = null;
				for (int i = 0; i < 24; i++) {
					if (i < 10) {
						key = dateStr + " 0" + i;
					} else {
						key = dateStr + " " + i;
					}

					StatisticRegisterUserEx tempEx = new StatisticRegisterUserEx();
					tempEx.setChannel("allChannel");
					tempEx.setCt(key);
					tempEx.setCounter(map1.get(key));
					tempEx.setAvcounter(map2.get(key) == null ? null : String.valueOf(map2.get(key)));
					list.add(tempEx);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(StatisticRegisterUserService--findRegUserListByChannel)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * <转换list为以时间点为key,注册数或激活数为value的map><功能具体实现>
	 * 
	 * @param list
	 * @param type
	 *            true为注册数转换,false为激活数转换
	 * @return
	 * @author yankefei
	 * @date 2015年10月19日 下午5:49:43
	 */
	private Map<String, Integer> convertion(List<StatisticRegisterUserEx> list, Boolean type) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (StatisticRegisterUserEx user : list) {
			if (!map.containsKey(user.getCt())) {
				if (type) {
					map.put(user.getCt(), user.getCounter());
				} else {
					if (user.getAvcounter() == null || "".equals(user.getAvcounter())) {
						map.put(user.getCt(), 0);
					} else {
						map.put(user.getCt(), Integer.parseInt(user.getAvcounter()));
					}
				}
			}
		}
		return map;
	}

	/**
	 * <统计地区激活数><功能具体实现>
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 * @author yankefei
	 * @date 2015年11月12日 上午10:29:45
	 */
	public Page<StatisticActiveUserIpVo> activityAreaQuery(PageRequest pageRequest, Map<String, Object> searchParams) {
		List<StatisticActiveUserIpVo> list = null;
		int total = 0;
		try {
			StatisticActiveUserIpCriteria criteria = new StatisticActiveUserIpCriteria();
			StatisticActiveUserIpCriteria.Criteria cri = criteria.createCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());

			criteria.setOrderByClause("total desc");
			// criteria.setMysqlOffset(pageRequest.getOffset());
			// criteria.setMysqlLength(pageRequest.getPageSize());

			// 地区总数
			total = statisticActiveUserIpMapperEx.countActivityAreaByIP(criteria);
			list = statisticActiveUserIpMapperEx.statisticActivityAreaByIP(criteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * <统计渠道订单量><功能具体实现>
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 * @author yankefei
	 * @date 2015年11月12日 下午6:17:58
	 */
	public Page<StatisticUserMobileVo> channelOrdersQuery(PageRequest pageRequest, String date) {
		List<StatisticUserMobileVo> list = new ArrayList<StatisticUserMobileVo>();
		int total = 0;
		try {
			List<String> channels = null;
			// 地区总数
			channels = statisticUserMobileMapperEx.findAllChannels();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String channel : channels) {
				StatisticUserMobileVo vo = new StatisticUserMobileVo();
				List<String> phones = statisticUserMobileMapperEx.findPhonesByChannel(channel);

				map.clear();
				map.put("phoneList", phones);
				if (date != null && !"".equals(date)) {
					map.put("ltime", date + " 23:59:59");
					map.put("gtime", date + " 00:00:00");
				} else {
					map.put("ltime", null);
					map.put("gtime", null);
				}
				Integer num = statisticUserMobileMapperEx.countOrdersByPhone(map);
				vo.setChannel(channel);
				vo.setNum(num);
				list.add(vo);
			}
			Collections.sort(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Integer uvCount(String date) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("searchDay", date);
		String sqlList = FreeMarkerUtils.format("/template/statistic/uv_count.ftl", paras);
		List<Map<String, Object>> uvs = jdbcTemplate.queryForList(sqlList);
		if (CollectionUtils.isEmpty(uvs)) {
			return 0;
		}
		Map<String, Object> uv = uvs.get(0);
		Integer cont = (Integer) uv.get("cont");
		return cont;
	}
	
	/**
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Integer pvCount(String date) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("searchDay", date);
		String sqlList = FreeMarkerUtils.format("/template/statistic/pv_count.ftl", paras);
		List<Map<String, Object>> uvs = jdbcTemplate.queryForList(sqlList);
		if (CollectionUtils.isEmpty(uvs)) {
			return 0;
		}
		Map<String, Object> uv = uvs.get(0);
		BigDecimal cont = (BigDecimal) uv.get("cont");
		return cont.intValue();
	}

	/**
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public List<Map<String, Object>> uvCounts(String from, String to) {
		Map<String, Object> paras = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(from)) {
			paras.put("from", from);
		}
		if (StringUtils.isNotBlank(to)) {
			paras.put("to", to);
		}
		String sqlList = FreeMarkerUtils.format("/template/statistic/uv_counts.ftl", paras);
		List<Map<String, Object>> uvs = jdbcTemplate.queryForList(sqlList);
		return uvs;
	}
	
	/**
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public List<Map<String, Object>> pvCounts(String from, String to) {
		Map<String, Object> paras = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(from)) {
			paras.put("from", from);
		}
		if (StringUtils.isNotBlank(to)) {
			paras.put("to", to);
		}
		String sqlList = FreeMarkerUtils.format("/template/statistic/pv_counts.ftl", paras);
		List<Map<String, Object>> uvs = jdbcTemplate.queryForList(sqlList);
		return uvs;
	}

	/**
	 * 
	 * <统计昨日所有城市的注册和激活用户数><功能具体实现>
	 * 
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 * @author liangsh
	 * @date 2015年12月22日 下午2:53:01
	 */
	public List<regUserVo> queryByCityAll(){
		Map<String,Object> paras = new HashMap<String, Object>();
		 paras.put("searchDate",DateUtils.formatDate(DateUtils.minusDaysToday(1)));
		 //paras.put("searchDate", "2015-10-10");
		String sqlList_reg = FreeMarkerUtils.format("/template/statistic/reg_num_allCity.ftl", paras);
		logger.info(sqlList_reg.toString());
	    List<Map<String,Object>> reg = jdbcTemplate.queryForList(sqlList_reg);
	    Map<String,regUserVo> regActMap = new HashMap<String, regUserVo>();
	    if(CollectionUtils.isNotEmpty(reg)){
	    	for (Map<String, Object> map : reg) {
	    		if(StringUtils.isNotBlank((String)map.get("city"))){
	    			String key = (String)map.get("cpc");
		            regUserVo vo = new regUserVo();
		            vo.setCity((String)map.get("city"));
		            vo.setDate((String)map.get("ct"));
		            Object countObj = map.get("_count");
		            if(countObj != null){
		            	vo.setRegNum(Integer.parseInt(countObj.toString()));
		            }else{
		            	vo.setRegNum(0);
		            }
		            vo.setProvince((String)map.get("province"));
		            vo.setActNum(0);
		            regActMap.put(key,vo);
	    		}
			}
	    }
	    String sqlList_act = FreeMarkerUtils.format("/template/statistic/act_num_allCity.ftl", paras);
		logger.info(sqlList_act.toString());

	    List<Map<String,Object>> act = jdbcTemplate.queryForList(sqlList_act);
		if(CollectionUtils.isNotEmpty(act)){
			for (Map<String, Object> map : act) {
				String city = (String)map.get("_city");
				if(StringUtils.isNotBlank(city)){
					String key = (String)map.get("cpc");
		            Object countObj = map.get("_count");
					if(regActMap.containsKey(key)){
		    			if(countObj != null){
		    				regActMap.get(key).setActNum(Integer.parseInt(countObj.toString()));
			            }else{
			            	regActMap.get(key).setActNum(0);
			            }
		    		}else{
			            regUserVo vo = new regUserVo();
			            vo.setCity(city);
			            vo.setDate((String)map.get("ct"));
			            vo.setRegNum(0);
			            if(countObj != null){
			            	vo.setActNum(Integer.parseInt(countObj.toString()));
			            }else{
			            	vo.setActNum(0);
			            }
			            vo.setProvince((String)map.get("province"));
			            regActMap.put(key,vo);
		    		}
				}
			}
		}
		
        List<Map.Entry<String, regUserVo>> entryList = new ArrayList<Map.Entry<String, regUserVo>>(regActMap.entrySet());
        Collections.sort(entryList,new Comparator<Map.Entry<String,regUserVo>>() {
        	 @Override
 		    public int compare(Entry<String, regUserVo> me1, Entry<String, regUserVo> me2) {
 		        return me1.getValue().getProvince().compareTo(me2.getValue().getProvince());
 		    }
        });
        
        List<regUserVo> list = new ArrayList<regUserVo>();
        for (Entry<String, regUserVo> entry : entryList) {
        	list.add(entry.getValue());
		}
		return list;
	}

	public List<regUserVo> queryByCity(String province, String city, String date, boolean isTotal) {
		Map<String, Object> paras = new HashMap<String, Object>();
		if (!isTotal) {
			paras.put("searchDate", date);
		}
		paras.put("searchProvince", province);

		String sqlList_act = FreeMarkerUtils.format("/template/statistic/act_num_city2.ftl", paras);

		if (!province.contains("北京") && !province.contains("天津") && !province.contains("重庆")
				&& !province.contains("上海")) {
			paras.put("searchCity", city);
			sqlList_act = FreeMarkerUtils.format("/template/statistic/act_num_city.ftl", paras);
		}

		String sqlList_reg = FreeMarkerUtils.format("/template/statistic/reg_num_city.ftl", paras);
		logger.info(sqlList_reg.toString());
		List<Map<String, Object>> reg = jdbcTemplate.queryForList(sqlList_reg);
		Map<String, regUserVo> regActMap = new HashMap<String, regUserVo>();
		if (CollectionUtils.isNotEmpty(reg)) {
			for (Map<String, Object> map : reg) {
				String key = (String) map.get("ct");
				regUserVo vo = new regUserVo();
				vo.setCity((String) map.get("city"));
				vo.setDate((String) map.get("ct"));
				Object countObject = map.get("_count");
				if (countObject != null && key != null) {
					vo.setRegNum(Integer.parseInt(map.get("_count").toString()));
				} else {
					vo.setRegNum(0);
				}
				vo.setActNum(0);
				if (isTotal) {
					regActMap.put(province, vo);
				} else {
					regActMap.put(key, vo);
				}
			}
		}
		logger.info(sqlList_act.toString());

		List<Map<String, Object>> act = jdbcTemplate.queryForList(sqlList_act);
		if (CollectionUtils.isNotEmpty(act)) {
			for (Map<String, Object> map : act) {
				String key = "";
				if (isTotal) {
					key = province;
				} else {
					key = (String) map.get("ct");
				}
				if (regActMap.containsKey(key)) {
					regActMap.get(key).setActNum(Integer.parseInt(map.get("_count").toString()));
				} else {
					regUserVo vo = new regUserVo();
					vo.setCity((String) map.get("_city"));
					vo.setDate((String) map.get("ct"));
					vo.setRegNum(0);
					Object countObject = map.get("_count");
					if (countObject != null && key != null) {
						vo.setActNum(Integer.parseInt(map.get("_count").toString()));
					} else {
						vo.setActNum(0);
					}
					regActMap.put(key, vo);
				}
			}
		}
		List<Map.Entry<String, regUserVo>> entryList = new ArrayList<Map.Entry<String, regUserVo>>(regActMap.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<String, regUserVo>>() {
			@Override
			public int compare(Entry<String, regUserVo> me1, Entry<String, regUserVo> me2) {
				return me2.getValue().getDate().compareTo(me1.getValue().getDate());
			}
		});

		List<regUserVo> list = new ArrayList<regUserVo>();
		for (Entry<String, regUserVo> entry : entryList) {
			list.add(entry.getValue());
		}
		return list;
	}
}
