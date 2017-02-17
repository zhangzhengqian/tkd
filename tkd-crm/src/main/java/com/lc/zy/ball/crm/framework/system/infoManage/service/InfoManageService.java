package com.lc.zy.ball.crm.framework.system.infoManage.service;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.infoManage.vo.CrmUserVo;
import com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumClassInfoVo;
import com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumClassVo;
import com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumInfosVo;
import com.lc.zy.ball.domain.oa.mapper.CoachMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class InfoManageService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory
			.getLogger(InfoManageService.class);

	@Autowired
	private StatiumInfosMapper statiumInfosMapper;

    @Autowired
    private QueueProducer queueProducer = null;

    @Autowired
    private StatiumClassMapper statiumClassMapper = null;

    @Autowired
    private StatiumClassInfoMapper statiumClassInfoMapper = null;

    @Autowired
    private CoachMapper coachMapper = null;

    /**
     *
     * <根据道馆id获取道馆信息><功能具体实现>
     *
     * @create：2016年5月3日 下午3:15:50
     * @author：sl
     * @param id
     * @return
     */
    public StatiumInfos getStatiumInfosByDgid(Integer id) {
        StatiumInfos statiumInfos = new StatiumInfos();
        try {
            StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
            StatiumInfosCriteria.Criteria statiumInfosCa = statiumInfosCriteria
                    .createCriteria();
            statiumInfosCa.andDgIdEqualTo(id);
            List<StatiumInfos> statiumInfoList = statiumInfosMapper
                    .selectByExample(statiumInfosCriteria);
            if (statiumInfoList.size() > 0) {
                statiumInfos = statiumInfoList.get(0);
            }
        } catch (Exception e) {
            logger.debug("根据道馆id获取道馆信息:{}", e.getMessage());
        }
        return statiumInfos;
    }

    /**
     *
     * <更新道馆信息><功能具体实现>
     *
     * @create：2016/11/25 下午7:54
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void save(StatiumInfosVo myForm) {
        try {
            StatiumInfos statiumInfos = new StatiumInfos();
            BeanUtils.copyProperties(myForm, statiumInfos);
            // date
            Date now = new Date();
            // userId
            String uId = SessionUtil.currentUserId();
            // et
            statiumInfos.setEt(now);
            // eb
            statiumInfos.setEb(uId);
            this.updateByPrimaryKeySelective(statiumInfos, myForm.getId());
            // MQ同步道馆信息
            queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, myForm.getDgId()
                    .toString());
        } catch (Exception e) {
            logger.debug("道馆信息保存", e.getMessage());
            throw new RuntimeException("更新道馆信息失败");
        }
    }

    /**
     *
     * <更新用户密码><功能具体实现>
     *
     * @create：2016/11/28 上午10:44
     * @author：sl
     * @param crmUser
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveUser(CrmUserVo crmUser) throws Exception{
            String userId = SessionUtil.currentUserId();
            // 根据登录用户id获取用户原始信息
            CrmUser realUser = this.selectByPrimaryKey(CrmUser.class, userId);
            String password = crmUser.getPassword();
            String hash = Encrypts.hashPassword(password, realUser.getSalt());
            if (!hash.equals(realUser.getPassword())) {
                throw new Exception("旧密码不正确!");
            }

            // 更新用户
            CrmUser userUpdate = new CrmUser();
            userUpdate.setUserId(userId);
            String[] hashNew = Encrypts.hashPassword(crmUser.getNewPassword());

            userUpdate.setPassword(hashNew[0]);
            userUpdate.setSalt(hashNew[1]);
            try {
                updateByPrimaryKeySelective(userUpdate,userUpdate.getUserId());
            } catch (Exception e) {
                logger.debug("更新用户密码失败 {}", e.getMessage());
                throw new Exception("服务器异常！");
            }
    }

    /**
     *
     * <根据登录用户id获取用户信息><功能具体实现>
     *
     * @create：2016/11/28 上午10:48
     * @author：sl
     * @param userId
     * @return com.lc.zy.ball.domain.oa.po.CrmUser
     */
    public CrmUser getUserInfo(String userId) {
        CrmUser crmUser = new CrmUser();
        try {
            crmUser = this.selectByPrimaryKey(CrmUser.class, userId);
        } catch (Exception e) {
            logger.debug("获取用户信息 {}", e.getMessage());
        }
        return crmUser;
    }

    /**
     *
     * <功能描述><功能具体实现>
     *
     * @create：2016/11/28 下午4:00
     * @author：sl
     * @param searchParams
     * @param pageRequest
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumClassVo>
     */
    public Page<StatiumClassVo> findClass(PageRequest pageRequest, Map<String, Object> searchParams) {
        int total = 0;
        List<StatiumClassVo> voList = new ArrayList<StatiumClassVo>();
        try {
            Map<String, SearchFilter> filters = SearchFilter
                    .parse(searchParams);
            StatiumClassCriteria statiumClassCriteria = new StatiumClassCriteria();
            statiumClassCriteria.setMysqlLength(pageRequest.getPageSize());
            statiumClassCriteria.setMysqlOffset(pageRequest.getOffset());
            StatiumClassCriteria.Criteria cri = statiumClassCriteria
                    .createCriteria();

            Criterias.bySearchFilter(cri, filters.values());
            statiumClassCriteria.setOrderByClause("et desc");
            total = statiumClassMapper.countByExample(statiumClassCriteria);
            List<StatiumClass> list = statiumClassMapper
                    .selectByExample(statiumClassCriteria);
            for (StatiumClass sc : list) {
                StatiumClassVo classVo = new StatiumClassVo();
                ConvertStatiumClassVo(classVo, sc);
                voList.add(classVo);
            }
        } catch (Exception e) {
            logger.error("道馆class list:{}" + e.getMessage());
        }
        return new PageImpl<>(voList, pageRequest, total);
    }

    /**
     *
     * <课程价格处理><功能具体实现>
     *
     * @create：2016年5月3日 下午5:52:22
     * @author：sl
     * @param classVo
     * @param sc
     */
    private void ConvertStatiumClassVo(StatiumClassVo classVo, StatiumClass sc) {
        try {
            BeanUtils.copyProperties(sc, classVo);
            classVo.setFlPrice(sc.getPrice() / 100F);
            if (sc.getDiscount() != null) {
                classVo.setFlDiscount(sc.getDiscount());
            }
            if (sc.getDiscountPrice() != null) {
                classVo.setFlDiscountPrice(sc.getDiscountPrice() / 100F);
            }
        } catch (Exception e) {
            logger.debug("课程价格处理:{}", e.getMessage());
        }
    }

    /**
     *
     * <课程详情><功能具体实现>
     *
     * @create：2016年5月4日 下午2:03:48
     * @author：sl
     * @param id
     * @return
     */
    public StatiumClass getStatiumClassById(String id) {
        StatiumClass statiumClass = new StatiumClass();
        try {
            statiumClass = this.selectByPrimaryKey(StatiumClass.class, id);
            statiumClass.setPrice(statiumClass.getPrice()/100);
            statiumClass.setDiscountPrice(statiumClass.getDiscountPrice()/100);
        } catch (Exception e) {
            logger.debug("课程详情:{}", e.getMessage());
        }
        return statiumClass;
    }

    /**
     *
     * <保存课程信息><功能具体实现>
     *
     * @create：2016/11/28 下午4:43
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveClass(StatiumClass myForm) {
        try {
            String statiumId = SessionUtil.currentStatium();
            // 道馆id
            myForm.setStatiumId(Integer.valueOf(statiumId));
            // 原价
            myForm.setPrice(myForm.getPrice() * 100);
            // 签约价
            myForm.setDiscountPrice(myForm.getDiscountPrice() * 100);
            // 判断是私教还是普通
            if (myForm.getType() == 1) {
                myForm.setMaxPeople(0);
            }
            myForm.setMinPeople(0);
            Date date = new Date();
            if (StringUtils.isNotBlank(myForm.getId())) {
                myForm.setEt(date);
                myForm.setEb(statiumId);
                this.updateByPrimaryKey(myForm, myForm.getId());
            } else {
                myForm.setCt(date);
                myForm.setCb(statiumId);
                myForm.setEt(date);
                myForm.setEb(statiumId);
                myForm.setId(UUID.get());
                this.insertSelective(myForm, myForm.getId());
            }
        } catch (Exception e) {
            logger.debug("保存课程信息 {}", e.getMessage());
            throw new RuntimeException("保存课程信息失败");
        }
    }

    /**
     *
     * <删除课程><功能具体实现>
     *
     * @create：2016/11/28 下午5:47
     * @author：sl
     * @param classId
     * @return void
     */
    @Transactional(readOnly = false)
    public void deleteClass(String classId) throws Exception{
        try {
            this.deleteByPrimaryKey(StatiumClass.class, classId);
        } catch (Exception e) {
            logger.debug("删除课程 {}", e.getMessage());
            throw new Exception("删除课程失败");
        }
    }

    /**
     *
     * <课时list><功能具体实现>
     *
     * @create：2016/11/28 下午6:23
     * @author：sl
     * @param pageRequest
     * @param searchParams
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumClassInfoVo>
     */
    public Page<StatiumClassInfoVo> classInfo(PageRequest pageRequest, Map<String, Object> searchParams) {
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
                voList.add(vo);
            }
        } catch (Exception e) {
            logger.debug("课时列表:{}", e.getMessage());
        }
        return new PageImpl<>(voList, pageRequest, total);
    }

    /**
     *
     * <删除课时><功能具体实现>
     *
     * @create：2016/11/28 下午6:32
     * @author：sl
     * @param classInfoId
     * @return void
     */
    @Transactional(readOnly = false)
    public void deleteClassInfo(String classInfoId) throws Exception{
        try {
            this.deleteByPrimaryKey(StatiumClassInfo.class, classInfoId);
        } catch (Exception e) {
            logger.debug("删除课时 {}", e.getMessage());
            throw new Exception("删除课时失败");
        }
    }

    /**
     *
     * <课时详情><功能具体实现>
     *
     * @create：2016/11/28 下午6:52
     * @author：sl
     * @param id
     * @param statiumId
     * @return com.lc.zy.ball.crm.framework.system.infoManage.vo.StatiumClassInfoVo
     */
    public StatiumClassInfoVo getClassInfoById(String id, Integer statiumId) {
        StatiumClassInfoVo infoVo = new StatiumClassInfoVo();
        try {
            StatiumClassInfo classInfo = this.selectByPrimaryKey(StatiumClassInfo.class, id);
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
     * <获取道馆教练><功能具体实现>
     *
     * @create：2016/11/28 下午7:06
     * @author：sl
     * @param
     * @return java.util.List<com.lc.zy.ball.domain.oa.po.Coach>
     */
    public List<Coach> getCoach() {
        List<Coach> coaches = new ArrayList<Coach>();
        try {
            CoachCriteria coachCriteria = new CoachCriteria();
            CoachCriteria.Criteria criteria = coachCriteria.createCriteria();
            criteria.andDgIdEqualTo(Integer.valueOf(SessionUtil.currentStatium()));
            coachCriteria.setMysqlOffset(0);
            coachCriteria.setMysqlLength(20);
            coaches = coachMapper.selectByExample(coachCriteria);
        } catch (Exception e) {
            logger.debug("获取道馆教练 {}", e.getMessage());
        }
        return coaches;
    }

    /**
     *
     * <保存课时><功能具体实现>
     *
     * @create：2016/11/28 下午7:23
     * @author：sl
     * @param myForm
     * @return void
     */
    @Transactional(readOnly =  false)
    public void savaClassInfo(StatiumClassInfoVo myForm) {
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
            // statiumId
            myForm.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            if (!id.equals("")) {
                // et
                info.setEt(now);
                // eb
                info.setEb(uId);
                this.updateByPrimaryKeySelective(info, id);
            } else {
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
        } catch (Exception e) {
            logger.debug("课时保存:{}", e.getMessage());
            throw new RuntimeException("课时保存失败");
        }
    }
}
