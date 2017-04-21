package com.lc.zy.ball.boss.framework.coach.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassInfoVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfoCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;

@Service
@Transactional(readOnly = true)
public class CoachClassInfoService extends AbstractCacheService {

	private static final Logger logger = LoggerFactory
			.getLogger(CoachClassInfoService.class);

	@Autowired
	private StatiumClassInfoMapper statiumClassInfoMapper;
	@Autowired
	private StatiumClassMapper statiumClassMapper;

	/**
	 * <课时列表><功能具体实现>
	 *
	 * @create：2016年5月9日 上午11:21:38
	 * @author：wangp
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatiumClassInfoVo> list(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumClassInfoVo> voList = new ArrayList<StatiumClassInfoVo>();
		try {
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			StatiumClassInfoCriteria statiumClassInfoCriteria = new StatiumClassInfoCriteria();
			statiumClassInfoCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumClassInfoCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumClassInfoCriteria.Criteria cri = statiumClassInfoCriteria
					.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			statiumClassInfoCriteria.setOrderByClause("class_date desc");
			total = statiumClassInfoMapper
					.countByExample(statiumClassInfoCriteria);
			List<StatiumClassInfo> list = statiumClassInfoMapper
					.selectByExample(statiumClassInfoCriteria);
			for (StatiumClassInfo info : list) {
				StatiumClassInfoVo vo = new StatiumClassInfoVo();
				BeanUtils.copyProperties(info, vo);
				StatiumClass classesss = statiumClassMapper
						.selectByPrimaryKey(vo.getClassId());
				vo.setClassTitle(classesss.getClassTitle());
				voList.add(vo);
			}
		} catch (Exception e) {
			logger.debug("课时列表:{}", e.getMessage());
		}
		return new PageImpl<>(voList, pageRequest, total);
	}

	/**
	 * <课时详情><功能具体实现>
	 *
	 * @create：2016年5月9日 上午11:21:16
	 * @author：wangp
	 * @param id
	 * @param statiumId
	 * @return
	 */
	public StatiumClassInfoVo getClassInfoById(String id, Integer statiumId) {
		StatiumClassInfoVo infoVo = new StatiumClassInfoVo();
		try {
			StatiumClassInfo classInfo = this.statiumClassInfoMapper
					.selectByPrimaryKey(id);
			infoVo.setcDate(DateUtils.formatDate(classInfo.getClassDate()));
			BeanUtils.copyProperties(classInfo, infoVo);
			infoVo.setStatiumId(statiumId);
		} catch (Exception e) {
			logger.debug("课时详情:{}", e.getMessage());
		}
		return infoVo;
	}

	/**
	 * <返回教练列表><功能具体实现>
	 *
	 * @create：2016年5月9日 下午4:38:22
	 * @author：wangp
	 * @param id
	 * @return
	 */

	public Coach coachById(String id) {
		Coach coach = new Coach();

		try {
			coach = this.selectByPrimaryKey(Coach.class, id);
		} catch (Exception e) {
			logger.debug("返回教练列表:{}", e.getMessage());
		}
		return coach;
	}
}
