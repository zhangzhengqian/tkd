package com.lc.zy.ball.crm.framework.system.statium.service;

import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.statium.service.vo.ClassNavVo;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.SpringUtils;

import org.apache.cxf.common.i18n.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatiumService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory
			.getLogger(StatiumService.class);

	@Autowired
	private StatiumInfosMapper statiumInfosMapper;

   /* @Autowired
    private StatiumClassMapper statiumClassMapper;*/

    @Autowired
    private StatiumClassInfoMapper statiumClassInfoMapper;

    /**
     *
     * <根据道馆id获取道馆信息><功能具体实现>
     *
     * @create：2016/11/21 下午2:58
     * @author：sl
     * @param statiumId
     * @return com.lc.zy.ball.domain.oa.po.StatiumInfos
     */
	public StatiumInfos getStatiumInfoById(String statiumId) throws Exception{
        StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
        StatiumInfosCriteria.Criteria criteria = statiumInfosCriteria.createCriteria();
        criteria.andDgIdEqualTo(Integer.valueOf(statiumId));
        List<StatiumInfos> list = statiumInfosMapper.selectByExample(statiumInfosCriteria);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     *
     * <获取道馆课程><功能具体实现>
     *
     * @create：2016/11/24 下午2:35
     * @author：sl
     * @param statiumId
     * @return java.util.List<com.lc.zy.ball.domain.oa.po.StatiumClass>
     */
    public List<ClassNavVo> getClass(String statiumId) throws Exception{
        List<ClassNavVo> classNavVos = new ArrayList<ClassNavVo>();
        // 获取道馆课程
        StatiumClassCriteria statiumClassCriteria = new StatiumClassCriteria();
        StatiumClassCriteria.Criteria criteria = statiumClassCriteria.createCriteria();
        criteria.andStatiumIdEqualTo(Integer.valueOf(statiumId));
        StatiumClassMapper statiumClassMapper = SpringUtils.getBean(StatiumClassMapper.class);
        logger.debug("***"+statiumClassMapper);
        List<StatiumClass> list = statiumClassMapper.selectByExample(statiumClassCriteria);
        if (!list.isEmpty()){
            for (StatiumClass statiumClass : list){
                // 获取当天课时
                StatiumClassInfoCriteria cri = new StatiumClassInfoCriteria();
                StatiumClassInfoCriteria.Criteria criteria1 = cri.createCriteria();
                criteria1.andClassDateEqualTo(new Date());
                criteria1.andClassIdEqualTo(statiumClass.getId());
                cri.setOrderByClause("class_start_time asc");
                List<StatiumClassInfo> infos = statiumClassInfoMapper.selectByExample(cri);
                if (!infos.isEmpty()){
                    for (StatiumClassInfo info : infos) {
                        ClassNavVo classNavVo = new ClassNavVo();
                        // 课程id
                        classNavVo.setClassId(statiumClass.getId());
                        // 课程名称
                        classNavVo.setClassName(statiumClass.getClassTitle());
                        // 课时id
                        classNavVo.setClassInfoId(info.getId());
                        // 课时时间
                        classNavVo.setClassTime(info.getClassStartTime() + " ~ " + info.getClassEndTime());

                        classNavVos.add(classNavVo);
                    }
                }
            }
            return classNavVos;
        }
        return null;
    }

}
