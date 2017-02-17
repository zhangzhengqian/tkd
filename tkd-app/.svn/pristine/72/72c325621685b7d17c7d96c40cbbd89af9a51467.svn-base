package com.lc.zy.ball.app.service.coach;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.service.coach.bean.CoachVo;
import com.lc.zy.ball.app.service.statium.bean.ClassDetalVo;
import com.lc.zy.ball.domain.oa.mapper.CoachMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CoachRepository extends AbstractCacheService implements CacheKeys {

	private static Logger logger = LoggerFactory.getLogger(CoachRepository.class);

	@Resource(name = "configs")
	private Map<String, String> configs;

	private CoachMapper coachMapper;

	private StatiumInfosMapper statiumInfosMapper;

	private StatiumClassInfoMapper statiumClassInfoMapper;

	public CoachRepository() {

	}

	@Autowired
	public CoachRepository(CoachMapper coachMapper, StatiumInfosMapper statiumInfosMapper,
			StatiumClassInfoMapper statiumClassInfoMapper) {
		super();
		this.coachMapper = coachMapper;
		this.statiumInfosMapper = statiumInfosMapper;
		this.statiumClassInfoMapper = statiumClassInfoMapper;
	}

	/**
	 * 
	 * <获取教练list><功能具体实现>
	 *
	 * @create：2016年4月28日 下午2:42:08
	 * @author：sl
	 * @param begin
	 * @param size
	 * @param type
	 * @param city
	 * @return
	 */
	public List<Coach> getCoachList(Integer begin, Integer size, String type, String city) {
		List<Coach> coachs = new ArrayList<Coach>();
		try {
			CoachCriteria coachCriteria = new CoachCriteria();
			CoachCriteria.Criteria criteria = coachCriteria.createCriteria();
			if (!StringUtils.isEmpty(city)) {
				criteria.andCityLike("%" + city + "%");
			}
			if (!StringUtils.isEmpty(type)) {
				criteria.andTypeEqualTo(type);
			}
			coachCriteria.setMysqlLength(size);
			coachCriteria.setMysqlOffset(begin);
			coachs = coachMapper.selectByExample(coachCriteria);
		} catch (Exception e) {
			logger.debug("获取教练list：｛｝", e.getMessage());
		}
		return coachs;
	}

	/**
	 * 
	 * <教练详情><功能具体实现>
	 *
	 * @create：2016年4月28日 下午4:10:16
	 * @author：sl
	 * @param coachId
	 * @return
	 */
	public CoachVo getCoachInfo(String coachId) {
		CoachVo coachVo = new CoachVo();
		try {
			// 获取教练信息
			Coach coach = this.selectByPrimaryKey(Coach.class, coachId);
			// 图片处理
			if (coach.getPhotos() != null) {
				coach.setPhotos(coach.getPhotos().replace("__", ";"));
			}
			BeanUtils.copyProperties(coach, coachVo);
			// 根据道馆id获取道馆信息
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			StatiumInfosCriteria.Criteria criteria = statiumInfosCriteria.createCriteria();
			criteria.andDgIdEqualTo(coach.getDgId());
			List<StatiumInfos> statiumInfosList = statiumInfosMapper.selectByExample(statiumInfosCriteria);
			if (!statiumInfosList.isEmpty()) {
				coachVo.setIntroduce(statiumInfosList.get(0).getRemark());
			}
			List<ClassDetalVo> classDetalVos = new ArrayList<ClassDetalVo>();
			// 根据教练id获取课程
			List<StatiumClassInfo> classInfos = classInfos(coachId);
			for (StatiumClassInfo statiumClassInfo : classInfos) {
				
				// 根据课程id获取课程信息
				//modify by zzq 2016-11-30 classId为空的情况
				StatiumClass statiumClass = new StatiumClass();
				if(statiumClassInfo.getClassId()!=null&&!statiumClassInfo.getClassId().equals("")){
					System.out.println(!statiumClassInfo.getClassId().equals(""));
					 statiumClass = classById(statiumClassInfo.getClassId());
					 if(statiumClass!=null){
						 ClassDetalVo classDetalVo = new ClassDetalVo();
						 BeanUtils.copyProperties(statiumClassInfo, classDetalVo);
							// 课时名称
							classDetalVo.setClassName(statiumClass.getClassTitle());
							// 课程最小人数
							if (statiumClass.getMinPeople() != null) {
								classDetalVo.setMinPeople(statiumClass.getMinPeople());
							}
							// 课程最大人数
							if (statiumClass.getMaxPeople() != null) {
								classDetalVo.setMaxPeople(statiumClass.getMaxPeople());
							}
							// 课程类型
							if (statiumClass.getType() != null) {
								classDetalVo.setType(statiumClass.getType());
							}
							// 折扣价格
							classDetalVo.setDiscountPrice(statiumClass.getDiscountPrice()/100);
							
							// 价格
							classDetalVo.setPrice(statiumClass.getPrice()/100);
							classDetalVos.add(classDetalVo);
					 }
					
				}
			}
			coachVo.setClassDetalVos(classDetalVos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("教练详情：｛｝", e.getMessage());
		}
		return coachVo;
	}

	/**
	 * 
	 * <根据教练id获取教练1周内课程><功能具体实现>
	 *
	 * @create：2016年4月28日 下午4:01:57
	 * @author：sl
	 * @param coachId
	 * @return
	 */
	public List<StatiumClassInfo> classInfos(String coachId) {
		List<StatiumClassInfo> classInfos = new ArrayList<StatiumClassInfo>();
		try {
			Date now = DateUtils.getDate(DateUtils.formatDate(DateUtils.getToday()));
			StatiumClassInfoCriteria statiumClassInfoCriteria = new StatiumClassInfoCriteria();
			StatiumClassInfoCriteria.Criteria criteria = statiumClassInfoCriteria.createCriteria();
			statiumClassInfoCriteria.setOrderByClause("class_date asc, class_start_time desc");
			criteria.andClassDateBetween(now, DateUtils.plusDaysToday(7));
			criteria.andCoachIdEqualTo(coachId);
			classInfos = statiumClassInfoMapper.selectByExample(statiumClassInfoCriteria);
		} catch (Exception e) {
			logger.debug("根据教练id获取教练1周内课程：｛｝", e.getMessage());
		}
		return classInfos;
	}

	/**
	 * 
	 * <根据课程id获取课程信息><功能具体实现>
	 *
	 * @create：2016年4月28日 下午4:06:51
	 * @author：sl
	 * @param classId
	 * @return
	 */
	public StatiumClass classById(String classId) {
		StatiumClass statiumClass = new StatiumClass();
		try {
			statiumClass = this.selectByPrimaryKey(StatiumClass.class, classId);
		} catch (Exception e) {
			logger.debug("根据课程id获取课程信息：｛｝", e.getMessage());
		}
		return statiumClass;
	}

}
