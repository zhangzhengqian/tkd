package com.lc.zy.ball.boss.framework.statiumClass.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassInfoVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfoCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class StatiumClassInfoService extends AbstractCacheService {

	private static final Logger logger = LoggerFactory
			.getLogger(StatiumClassInfoService.class);

	@Autowired
	private StatiumClassInfoMapper statiumClassInfoMapper;
	@Autowired
	private StatiumClassMapper statiumClassMapper;

	/**
	 * 
	 * <课时列表><功能具体实现>
	 *
	 * @create：2016年5月4日 下午12:20:49
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @param statiumId
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
			try {
				total = statiumClassInfoMapper
						.countByExample(statiumClassInfoCriteria);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<StatiumClassInfo> list = new ArrayList <StatiumClassInfo>();
			try {
				list = statiumClassInfoMapper
						.selectByExample(statiumClassInfoCriteria);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (StatiumClassInfo info : list) {
				String classId = info.getClassId();
				StatiumClassInfoVo vo = new StatiumClassInfoVo();
				if(StringUtils.isNotEmpty(classId)){
					StatiumClass statiumClass = statiumClassMapper.selectByPrimaryKey(classId);
					if(statiumClass!=null){
						vo.setStatiumId(statiumClass.getStatiumId());
					}
				}
				
				BeanUtils.copyProperties(info, vo);
				voList.add(vo);
			}
		} catch (Exception e) {
			logger.debug("课时列表:{}", e.getMessage());
		}
		return new PageImpl<>(voList, pageRequest, total);
	}

	/**
	 * 
	 * <课时保存><功能具体实现>
	 *
	 * @create：2016年5月4日 下午12:02:55
	 * @author：sl
	 * @param myForm
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(StatiumClassInfoVo myForm) {
		String id = "";
		try {
			id = myForm.getId();
			StatiumClassInfo info = new StatiumClassInfo();
			BeanUtils.copyProperties(myForm, info);
			info.setClassDate(DateUtils.getDate(myForm.getcDate()));
			// date
			Date now = new Date();
			
			// userId
			String uId = SessionUtil.currentUserId();
			if (!id.equals("")) {
					// et
					info.setEt(now);
					// eb
					info.setEb(uId);
					this.updateByPrimaryKeySelective(info, id);
			} else {
 				//判断是否按周添加 是的话则添加一个月的课时 1添加一个月 0否
				if(info.getIsWeek()==1){
					//按月添加 一次添加四节课程
					Date nowDate = info.getClassDate();
					Date secondDate = DateUtils.plusDays(nowDate, 7);
					Date thirdDate = DateUtils.plusDays(nowDate, 14);
					Date fourthDate = DateUtils.plusDays(nowDate, 21);
					
					String nowDateStr = DateUtils.formatDate(nowDate, "yyyy-MM-dd");
					String secondDateStr = DateUtils.formatDate(secondDate, "yyyy-MM-dd");
					String thirdDateStr = DateUtils.formatDate(thirdDate, "yyyy-MM-dd");
					String fourthDateStr = DateUtils.formatDate(fourthDate, "yyyy-MM-dd");
					
					String[] dateStr = {nowDateStr,secondDateStr,thirdDateStr,fourthDateStr};
					
					//添加
					for(String s :dateStr){
						id = UUID.get();
						info.setId(id);
						//课程开始时间
						info.setClassDate(DateUtils.getDate(s, "yyyy-MM-dd"));
						
						// ct
						info.setCt(now);
						// cb
						info.setCb(uId);
						// et
						info.setEt(now);
						// eb
						info.setEb(uId);
						this.insertSelective(info, id);
					}
				}else if(info.getIsWeek()==0){
					//只添加当天的课时
					id = UUID.get();
					info.setId(id);
					// ct
					info.setCt(now);
					// cb
					info.setCb(uId);
					// et
					info.setEt(now);
					// eb
					info.setEb(uId);
					this.insertSelective(info, id);
				}
			}
		} catch (Exception e) {
			logger.debug("课时存:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <课时详情><功能具体实现>
	 *
	 * @create：2016年5月4日 下午2:51:40
	 * @author：sl
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
	 * 
	 * <返回课程列表><功能具体实现>
	 *
	 * @create：2016年5月4日 下午3:11:13
	 * @author：sl
	 * @param classId
	 * @return
	 */
	public StatiumClass classById(String classId) {
		StatiumClass statiumClass = new StatiumClass();
		try {
			statiumClass = this.selectByPrimaryKey(StatiumClass.class, classId);
		} catch (Exception e) {
			logger.debug("返回课程列表:{}", e.getMessage());
		}
		return statiumClass;
	}
	
	/**
	 * 
	 * <String类型的日期格式转化为日期格式><功能具体实现>
	 *
	 * @create：2016年12月5日 上午10:47:20
	 * @author：zzq
	 * @param str
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public  Date parse(String str, String pattern, Locale locale) {
        if(str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
