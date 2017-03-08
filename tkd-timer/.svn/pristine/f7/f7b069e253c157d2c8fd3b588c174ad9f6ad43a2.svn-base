package com.lc.zy.ball.timer.service;

import com.lc.zy.ball.timer.common.AbstractCacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "core", readOnly = true)
public class PmwInfoimgTimerService extends AbstractCacheService {
	/*private Logger logger = LoggerFactory.getLogger(PmwInfoimgTimerService.class);

	@Resource(name = "tkdwebJdbcTemplate")
	private JdbcTemplate jdbcTempalte;

	*//**
	 * <同步场馆>
	 *
	 * @Exception 异常对象
	 *//*
	@Transactional(value = "core", readOnly = false)
	public void synPmwInfoimg() {
		try {
			// 获取场馆
			String sqlList = FreeMarkerUtils.format("/template/syn_pmw_infoimg.ftl", null);
			logger.debug(sqlList);
			List<Map<String, Object>> pmwInfoimgMaps = jdbcTempalte.queryForList(sqlList);
			String results = MyGson.getInstance().toJson(pmwInfoimgMaps);
			Type classType = new TypeToken<List<PmwInfoimg>>() {
			}.getType();
			List<PmwInfoimg> pmwInfoimgs = MyGson.getInstance().fromJson(results, classType);
			// 删除库数据
			for (PmwInfoimg pmwInfoimg : pmwInfoimgs) {
				this.deleteByPrimaryKey(PmwInfoimg.class, pmwInfoimg.getId());
				this.insertSelective(pmwInfoimg, pmwInfoimg.getId());
			}

		} catch (Exception e) {
			logger.error("同步pmwInfoimg", e);
			throw new RuntimeException(e);
		}
	}*/
}
