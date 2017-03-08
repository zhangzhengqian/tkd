package com.lc.zy.ball.timer.service;

import com.lc.zy.ball.timer.common.AbstractCacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "core", readOnly = true)
public class PmwMemberTimerService extends AbstractCacheService {
	/*private Logger logger = LoggerFactory.getLogger(PmwMemberTimerService.class);

	@Resource(name = "tkdwebJdbcTemplate")
	private JdbcTemplate jdbcTempalte;

	*//**
	 * <同步场馆>
	 * 
	 * @Exception 异常对象
	 *//*
	@Transactional(value = "core", readOnly = false)
	public void synPmwMember() {
		try {
			// 获取场馆
			String sqlList = FreeMarkerUtils.format("/template/syn_pmw_member.ftl", null);
			logger.debug(sqlList);
			List<Map<String, Object>> pmwMemberMaps = jdbcTempalte.queryForList(sqlList);
			String results = MyGson.getInstance().toJson(pmwMemberMaps);
			Type classType = new TypeToken<List<PmwMember>>() {
			}.getType();
			List<PmwMember> pmwMembers = MyGson.getInstance().fromJson(results, classType);
			// 删除库数据
			for (PmwMember pmwMember : pmwMembers) {
				this.deleteByPrimaryKey(PmwMember.class, pmwMember.getId());
				this.insertSelective(pmwMember, pmwMember.getId());
//				this.updateByPrimaryKeySelective(pmwMember, pmwMember.getId());
			}

		} catch (Exception e) {
			logger.error("同步场馆", e);
			throw new RuntimeException(e);
		}
	}*/
}
