package com.lc.zy.ball.boss.framework.statium.service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.coreservice.FreeSpaceCounter;
import com.lc.zy.common.mq.bean.OrdeNotifyrMessage;
import com.lc.zy.common.util.*;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.time.*;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.service.CommonService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumAccreditVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumDetailVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.StatiumPriceTmplCriteria.Criteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;

@Service
@Transactional(readOnly = true)
public class StatiumDetailService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(StatiumDetailService.class);

    @Autowired
    private MessageUtil messageUtil = null;

    @Autowired
    private CommonService commonService = null;

    @Autowired
    private StatiumDetailMapper statiumDetailMapper;

    @Autowired
    private StatiumAuditLogMapper statiumAuditLogMapper;

    @Autowired
    private StatiumPriceTmplService statiumPriceTmplService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CrmUserMapper crmUserMapper;

    @Autowired
    private CrmUserFuncMapper crmUserFuncMapper;

    @Autowired
    private CrmFunctionMapper crmFunctionMapper;

    @Resource(name = "oaJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatiumSpaceMapper statiumSpaceMapper;

    @Autowired
    private CrmStatiumSpacePriceMapper crmStatiumSpacePriceMapper;

    @Autowired
    private CrmStatiumPriceTmplMapper crmStatiumPriceTmplMapper;

    @Autowired
    private CrmSpacePriceMapper crmSpacePriceMapper;

    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    private SpacePriceMapper spacePriceMapper;
    
    @Autowired
    private StatiumVisitLogMapper statiumVisitLogMapper;
    
    @Autowired
    private StatiumPriceMapper statiumPriceMapper;
    
    @Autowired
    private StatiumPriceTmplMapper statiumPriceTmplMapper;
    
    @Autowired
    private StatiumSpacePriceMapper statiumSpacePriceMapper;
    
    @Autowired
    private EnjoyGameMapper enjoyGameMapper;

    @Autowired
    private NuomiTpDealIdMapper nuomiTpDealIdMapper;

    @Resource(name = "configs")
    private Map<String, String> configs;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private FreeSpaceCounter freeSpaceCounter;

    @Autowired
    private OrderNotifyUtil orderNotifyUtil;

    @Autowired
    private OrderLogMapper orderLogMapper;
    
    /**
     * 
     * <获取场地列表><功能具体实现>
     *
     * @create：2015年8月3日 下午8:57:26
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @return
     */
    public Page<StatiumDetailVo> find(PageRequest pageable, Map<String, Object> searchParams) {
        logger.debug(searchParams.toString());
        StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
        statiumDetailCriteria.setMysqlLength(pageable.getPageSize());
        statiumDetailCriteria.setMysqlOffset(pageable.getOffset());
        StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
        // modify by liangc 150823:
        // cri.andDeleteFlagEqualTo(0);
        cri.andDeleteFlagIsNull();
        Object tpPoiId = searchParams.get("EQ_tpPoiId");
        if (tpPoiId != null && CommonUtils.isNotEmpty((String)tpPoiId)) {
            if(Integer.valueOf((String) searchParams.get("EQ_tpPoiId")) == 1){
                cri.andTpPoiIdIsNotNull();
            }else{
                cri.andTpPoiIdIsNull();
            }
        }
        searchParams.remove("EQ_tpPoiId");
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Criterias.bySearchFilter(cri, filters.values());
        // modify by liangc 150823 : 最后修改时间是排序条件。。。
        statiumDetailCriteria.setOrderByClause(" top desc ,et desc");
        int total = statiumDetailMapper.countByExample(statiumDetailCriteria);
        List<StatiumDetail> list = statiumDetailMapper.selectByExample(statiumDetailCriteria);
        // add by sl 针对场馆列表中场馆都回访记录展示
        List<StatiumDetailVo> listVos = new ArrayList<StatiumDetailVo>();
        for (StatiumDetail sDetail : list){
        	StatiumDetailVo statiumDetailVo = new StatiumDetailVo();
        	StatiumVisitLogCriteria statiumVisitLogCriteria = new StatiumVisitLogCriteria();
        	statiumVisitLogCriteria.createCriteria().andStatiumIdEqualTo(sDetail.getId());
        	statiumVisitLogCriteria.setOrderByClause("ct desc");
        	List<StatiumVisitLog> statiumVisitLogs = statiumVisitLogMapper.selectByExample(statiumVisitLogCriteria);
        	if (statiumVisitLogs.size() > 0) {
        		StatiumVisitLog statiumVisitLog = statiumVisitLogs.get(0);
        		// 回访日期
        		statiumDetailVo.setVisitDate(DateUtils.formatDate(statiumVisitLog.getCt()));
        		// 回访时间是否大于30天(0:否,1:是)
        		int day = DateUtils.intervalDay(statiumVisitLog.getCt());
        		if (day >= 30) {
        			statiumDetailVo.setIsVisit("1");
        		} else {
        			statiumDetailVo.setIsVisit("0");
        		}
        	}
        	BeanUtils.copyProperties(sDetail, statiumDetailVo);
        	listVos.add(statiumDetailVo);
        }
        searchParams.put("EQ_tpPoiId",tpPoiId);
        return new PageImpl<>(listVos, pageable, total);
    }
    
    @Transactional(readOnly = false)
    public void sendMsg(Map<String, Object> searchParams,String msg) {
        logger.debug(searchParams.toString());
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
        StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
        cri.andDeleteFlagIsNull();
        Criterias.bySearchFilter(cri, filters.values());
        List<StatiumDetail> list = statiumDetailMapper.selectByExample(statiumDetailCriteria);
        for (StatiumDetail sDetail : list){
        	String phone = sDetail.getMasterTel();
        	Pattern pattern = Pattern.compile("1[0-9]{10}");
        	Matcher matcher = pattern.matcher(phone);
        	if(!matcher.matches()){
        		continue;
        	}
        	logger.debug("场馆短信,场馆id={},手机号={},短信内容={}",sDetail.getId(),phone,msg);
        	messageUtil.sendSms(phone, msg);
        	try {
				createAuditLog(sDetail.getId(), SessionUtil.currentUserId(), "发送短信", msg);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
        }
        
    }

    /**
     * 
     * <添加或者更新场馆信息><功能具体实现>
     *
     * @create：2015年8月4日 下午12:00:28
     * @author： CYY
     * @param list
     * @param myForm
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void save(List<StatiumPriceTmplVo> list, StatiumDetailVo myForm) throws Exception {
        // add by liangc : 擦屁股
        try {
            int deleteFlag = myForm.getDeleteFlag();
            if (deleteFlag == 0) {
                myForm.setDeleteFlag(null);
            }
        } catch (Exception e) {
        }

        User user = SessionUtil.currentUser();
        StatiumDetail statiumDetail = new StatiumDetail();
        BeanUtils.copyProperties(myForm, statiumDetail);
        insertOrUpdate(statiumDetail, user);
        StatiumPriceTmpl statiumPriceTmpl = null;
        Date date = new Date();
        StatiumPriceTmplVo vo = null;

        // 保存价格之前先查询是否有历史记录，存在就删除
        String[] sportTypes = statiumDetail.getSportType().split(";;");
        List<String> sportList = Arrays.asList(sportTypes);
        StatiumPriceTmplCriteria sptc = new StatiumPriceTmplCriteria();
        Criteria cri = sptc.createCriteria();
        cri.andStatiumIdEqualTo(statiumDetail.getId());
        cri.andSportTypeIn(sportList);
        statiumPriceTmplService.deleteByExample(sptc);

        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            for (int j = 0; j < vo.getNoWorkingDays().size(); j++) {
                statiumPriceTmpl = vo.getNoWorkingDays().get(j);
                statiumPriceTmpl.setId(UUID.get());
                statiumPriceTmpl.setIsWorkday(0);
                statiumPriceTmpl.setSportType(vo.getBallType());
                statiumPriceTmpl.setStatiumId(statiumDetail.getId());
                statiumPriceTmpl.setEb(user.getUserId());
                statiumPriceTmpl.setCb(user.getUserId());
                statiumPriceTmpl.setCt(date);
                statiumPriceTmpl.setEt(date);
                // 补贴金额
                statiumPriceTmpl.setSubsidies(vo.getSubsidies());
                statiumPriceTmplService.save(statiumPriceTmpl);
            }
            for (int j = 0; j < vo.getWorkingDays().size(); j++) {
                statiumPriceTmpl = vo.getWorkingDays().get(j);
                statiumPriceTmpl.setId(UUID.get());
                statiumPriceTmpl.setIsWorkday(1);
                statiumPriceTmpl.setSportType(vo.getBallType());
                statiumPriceTmpl.setStatiumId(statiumDetail.getId());
                statiumPriceTmpl.setEb(user.getUserId());
                statiumPriceTmpl.setCb(user.getUserId());
                statiumPriceTmpl.setCt(date);
                statiumPriceTmpl.setEt(date);
                // 补贴金额
                statiumPriceTmpl.setSubsidies(vo.getSubsidies());
                statiumPriceTmplService.save(statiumPriceTmpl);
            }
        }
    }

    /**
     * 
     * <添加或者更新场馆信息><功能具体实现>
     *
     * @create：2015年8月4日 下午12:00:28
     * @author： CYY
     * @param po
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public String insertOrUpdate(StatiumDetail po, User user) throws Exception {
        String id = po.getId();
        Date now = new Date();
        String areaCode = po.getAreaCode();
        if (StringUtils.isNotEmpty(areaCode)) {
            Map<String, String> areaMap = Zonemap.split(areaCode);
            po.setProvince(areaMap.get("province"));
            po.setCity(areaMap.get("city"));
            po.setArea(areaMap.get("area"));
        }
        if (StringUtils.isEmpty(id)) {
            // insert
            id = UUID.get();
            po.setId(id);
            po.setCt(now);
            po.setEt(now);
            po.setCb(user.getUserId());
            po.setEb(user.getUserId());
            po.setSb(user.getUserId());
            po.setOrgCode(user.getOrgCode());
            // 合并存储区号和电话
            // XXX liangc : 没有做非空判断，上线后有异常 ,不能在 po 里加属性，除非改表
            // po.setTel(po.getTelHead()+"-"+po.getTel());

            this.insertSelective(po, id);
            createAuditLog(po.getId(), user.getUserId(), "提交审核", "新增场馆");
        } else {
            po.setEt(now);
            po.setEb(user.getUserId());
            // 合并存储区号和电话
            // XXX liangc : 没有做非空判断，上线后有异常 ,不能在 po 里加属性，除非改表
            // po.setTel(po.getTelHead()+"-"+po.getTel());
            if (po.getStatus()!=null&&po.getStatus() == 0) {
                queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, id);
            }
            this.updateByPrimaryKeySelective(po, po.getId());
            logger.debug("po==" + po.toString());
            // if(StringUtils.isEmpty(po.getPhotos())){
            // StatiumDetail newPo =
            // this.selectByPrimaryKey(StatiumDetail.class, po.getId());
            // newPo.setPhotos("");
            // statiumDetailMapper.updateByPrimaryKeyWithBLOBs(newPo);
            // this.updateByPrimaryKey(newPo, newPo.getId());
            // }
            createAuditLog(po.getId(), user.getUserId(), "提交审核", "修改场馆信息");
        }
        // modify by liangc 150823: 如果是待审核，则通知客服，进行审核处理
        // TODO : 需要审核的球馆，范围是否锁定为已签约呢？？
        if (po.getStatus() != null && 0 == po.getStatus()) {
            logger.debug("场馆需要审核 : {}", po.getName());
            String subject = po.getName() + " 待审核";
            // 找到客服经理
            List<User> userList = commonService.findUserByRoleAndOrgCode(Roles.CUSTOMER_MANAGER, null);
            // 通知给客服经理
            for (User _user : userList) {
                Map<String, Object> root = new HashMap<String, Object>();
                root.put("statium", po);
                String message = FreeMarkerUtils.format("/template/statium/statium_audit.ftl", root);
                messageUtil.sendLoc(SessionUtil.currentUserId(), _user.getUserId(), subject, message);
            }
        }
        return id;
    }

    /**
     * 
     * <获取场地列表><功能具体实现>
     *
     * @create：2015年8月6日 下午8:57:26
     * @author： CYY
     * @param statiumDetailCriteria
     * @return
     */
    public List<StatiumDetail> find(StatiumDetailCriteria statiumDetailCriteria) {
        return statiumDetailMapper.selectByExample(statiumDetailCriteria);
    }
    
    /**
     * 
     * <验证场馆是否存在><功能具体实现>
     * @param statiumName
     * @return
     * @author liangsh
     * @date 2016年4月11日 下午5:22:02
     */
    public boolean checkStatium(String statiumName){
    	StatiumDetailCriteria c  = new StatiumDetailCriteria();
		StatiumDetailCriteria.Criteria cri = c.createCriteria();
		cri.andNameEqualTo(statiumName);
		return statiumDetailMapper.countByExample(c) > 0 ? true : false;
    }
    
    /**
     * 
     * <校验场馆发布赛事是否已达到上限><功能具体实现>
     * @param statiumId
     * @param siteId
     * @return
     * @author liangsh
     * @date 2016年4月11日 下午5:23:05
     */
    public boolean chcekHoldTimes(String statiumId,String siteId) {
    	EnjoyGameCriteria c = new EnjoyGameCriteria();
    	EnjoyGameCriteria.Criteria cri = c.createCriteria();
    	cri.andStatiumIdEqualTo(statiumId);
    	cri.andSiteIdEqualTo(siteId);
    	cri.andDeleteFlagNotEqualTo(1);
		return enjoyGameMapper.countByExample(c) > 0 ? true : false;
	}

    /**
     * 球馆审核
     * 
     * @author liangc 150823
     * @param id 球馆id
     * @param audit 审核结果 1 通过，2 拒绝
     * @param reason 拒绝原因
     * @param uid 签约人
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void audit(String id, String audit, String uid, String reason) throws Exception {
        Date now = DateUtils.now();
        String userId = SessionUtil.currentUserId();
        StatiumDetail statiumDetail = super.selectByPrimaryKey(StatiumDetail.class, id);
        // 通知信息提交人
        String action = "审核拒绝";
        if ("1".equals(audit)) {
            action = "审核通过";
            // modify by caiyy 150826: 审核通过，指定新的签约人把场馆的签约人改为指定的签约人
            if (StringUtils.isNotEmpty(uid)) {
                statiumDetail.setSb(uid);
            }
        }
        // 处理完成后，通知这个人，因为上次数据是他修改的；
        String messageTo = statiumDetail.getEb();
        statiumDetail.setStatus(Integer.parseInt(audit));
        statiumDetail.setEb(userId);
        statiumDetail.setEt(now);
        super.updateByPrimaryKeySelective(statiumDetail, id);

        createAuditLog(statiumDetail.getId(), userId, action, reason);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("statium", statiumDetail);
        root.put("action", action);
        root.put("reason", reason);
        String subject = statiumDetail.getName() + " [" + action + "]";
        String message = FreeMarkerUtils.format("/template/statium/statium_audit_end.ftl", root);
        messageUtil.sendLoc(userId, messageTo, subject, message);
    }

    /**
     * 场馆审核日志
     * 
     * @author liangc
     * @param cb
     * @param action
     * @param reason
     * @throws Exception
     */
    private void createAuditLog(String statiumId, String cb, String action, String reason) throws Exception {
        Date now = DateUtils.now();
        StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
        statiumAuditLog.setId(UUID.get());
        statiumAuditLog.setCb(cb);
        statiumAuditLog.setCt(now);
        statiumAuditLog.setDescription(reason);
        statiumAuditLog.setAction(action);
        statiumAuditLog.setStatiumId(statiumId);
        super.insertSelective(statiumAuditLog, statiumAuditLog.getId());
    }

    /**
     * 获取审核日志
     * 
     * @param statiumId
     * @return
     */
    public List<StatiumAuditLog> getAuditLog(String statiumId) {
        StatiumAuditLogCriteria statiumAuditLogCriteria = new StatiumAuditLogCriteria();
        statiumAuditLogCriteria.createCriteria().andStatiumIdEqualTo(statiumId);
        statiumAuditLogCriteria.setOrderByClause("ct desc");
        return statiumAuditLogMapper.selectByExample(statiumAuditLogCriteria);
    }

    /**
     * 获取签约人列表
     * 
     * @return
     */
    public List<User> getSignUser() {
        RoleCriteria roleCriteria = new RoleCriteria();
        RoleCriteria.Criteria c1 = roleCriteria.createCriteria();
        c1.andRoleCodeIn(Arrays.asList(Roles.SUPPORT.getCode(), Roles.SUPPORT_MANAGER.getCode()));
        List<Role> roleList = roleMapper.selectByExample(roleCriteria);

        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        UserRoleCriteria.Criteria c2 = userRoleCriteria.createCriteria();
        List<String> roleIdList = new ArrayList<String>();
        for (Role r : roleList) {
            roleIdList.add(r.getRoleId());
        }
        c2.andRoleIdIn(roleIdList);
        List<UserRoleKey> userRoleList = userRoleMapper.selectByExample(userRoleCriteria);

        UserCriteria userCriteria = new UserCriteria();
        UserCriteria.Criteria c3 = userCriteria.createCriteria();
        List<String> userIdList = new ArrayList<String>();
        for (UserRoleKey key : userRoleList) {
            userIdList.add(key.getUserId());
        }
        c3.andUserIdIn(userIdList);
        userCriteria.setOrderByClause(" NICKNAME asc");
        return userMapper.selectByExample(userCriteria);
    }

    @Transactional(readOnly = false)
    public void updateRating(StatiumDetail po) throws Exception {
        this.updateByPrimaryKeySelective(po, po.getId());
    }

    /**
     * 
     * <管理员详情><功能具体实现>
     *
     * @create：2015年9月17日 下午4:50:33
     * @author： liangsh
     * @param statiumId
     * @return
     */
    public CrmUser detail(String statiumId) {
        logger.debug("statiumId={}", statiumId);
        CrmUserCriteria c = new CrmUserCriteria();
        CrmUserCriteria.Criteria cri = c.createCriteria();
        cri.andStatiumIdEqualTo(statiumId);
        cri.andDeleteFlagIsNull();
        List<CrmUser> list = crmUserMapper.selectByExample(c);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * <添加管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午4:51:54
     * @author： liangsh
     * @param user
     */
    @Transactional(readOnly = false)
    public void addCrmUser(CrmUser user) {
        if (StringUtils.isBlank(user.getUserId())) {
            // 创建用户时需要加密明文密码，修改用户信息时不修改密码
            entryptPassword(user);
            user.setUserId(UUID.get());
        }
        List<CrmFunction> crmFuncs = crmFunctionMapper.selectByExample(new CrmFunctionCriteria());
        crmUserMapper.insertSelective(user);
        CrmUserFunc uf = null;
        String userId = user.getUserId();
        for (CrmFunction func : crmFuncs) {
            uf = new CrmUserFunc();
            uf.setFuncId(func.getFuncId());
            uf.setUserId(userId);
            crmUserFuncMapper.insertSelective(uf);
        }
     // 删除现有场馆模板价格、场地模板价格、场地价格
        StatiumSpaceCriteria spaceCriteria = new StatiumSpaceCriteria();
        StatiumSpaceCriteria.Criteria spaceCri = spaceCriteria.createCriteria();
        spaceCri.andStatiumIdEqualTo(user.getStatiumId());
        List<StatiumSpace> statiumSpaces = statiumSpaceMapper.selectByExample(spaceCriteria);
        for (StatiumSpace ssp : statiumSpaces) {
            CrmStatiumSpacePriceCriteria crmStatiumSpacePriceCriteria = new CrmStatiumSpacePriceCriteria();
            CrmStatiumSpacePriceCriteria.Criteria crmStatiumSpacePriceCri = crmStatiumSpacePriceCriteria
                    .createCriteria();
            crmStatiumSpacePriceCri.andSpaceIdEqualTo(ssp.getId());
            crmStatiumSpacePriceMapper.deleteByExample(crmStatiumSpacePriceCriteria);

            CrmSpacePriceCriteria crmSpacePriceCriteria = new CrmSpacePriceCriteria();
            CrmSpacePriceCriteria.Criteria crmSpacePriceCri = crmSpacePriceCriteria.createCriteria();
            crmSpacePriceCri.andSpaceIdEqualTo(ssp.getId());
            crmSpacePriceMapper.deleteByExample(crmSpacePriceCriteria);
        }
        CrmStatiumPriceTmplCriteria crmStatiumPriceTmplCriteria = new CrmStatiumPriceTmplCriteria();
        CrmStatiumPriceTmplCriteria.Criteria crmStatiumPriceTmplCri = crmStatiumPriceTmplCriteria.createCriteria();
        crmStatiumPriceTmplCri.andStatiumIdEqualTo(user.getStatiumId());
        crmStatiumPriceTmplMapper.deleteByExample(crmStatiumPriceTmplCriteria);
        
        /*Map<String, String> paras = new HashMap<String, String>();
        paras.put("statiumId", user.getStatiumId());
        String sql = FreeMarkerUtils.format("/template/crm_statium/crm_statium_price_tmpl.ftl", paras);
        jdbcTemplate.update(sql);*/
        
        //复制价格模板
        StatiumPriceTmplCriteria priceTmplCriteria = new StatiumPriceTmplCriteria();
        StatiumPriceTmplCriteria.Criteria priceTmplCri = priceTmplCriteria.createCriteria();
        priceTmplCri.andStatiumIdEqualTo(user.getStatiumId());
        List<StatiumPriceTmpl> priceTemps = statiumPriceTmplMapper.selectByExample(priceTmplCriteria);
        CrmStatiumPriceTmpl crmTemp = null;
        for(StatiumPriceTmpl temp:priceTemps){
        	crmTemp = new CrmStatiumPriceTmpl();
        	BeanUtils.copyProperties(temp, crmTemp);
        	crmTemp.setId(UUID.get());
        	crmStatiumPriceTmplMapper.insert(crmTemp);
        }
        
        StatiumSpaceCriteria spaceCriteria_ = new StatiumSpaceCriteria();
        StatiumSpaceCriteria.Criteria spaceCri_ = spaceCriteria_.createCriteria();
        spaceCri_.andStatiumIdEqualTo(user.getStatiumId());
        List<StatiumSpace> statiumSpaces_ = statiumSpaceMapper.selectByExample(spaceCriteria_);
        List<String> spaceIds = new ArrayList<String>();
        for (StatiumSpace ssp : statiumSpaces_) {
            /*paras.clear();
            paras.put("spaceId", ssp.getId());*/
        	spaceIds.add(ssp.getId());
 /*           jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_statium_space_price.ftl", paras));
            jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_space_price.ftl", paras));
*/        }
        //拷贝场地价格模板
        StatiumSpacePriceCriteria statiumSpacePriceCriteria = new StatiumSpacePriceCriteria();
        StatiumSpacePriceCriteria.Criteria statiumSpacePriceCri = statiumSpacePriceCriteria.createCriteria();
        statiumSpacePriceCri.andSpaceIdIn(spaceIds);
        List<StatiumSpacePrice> statiumSpacePrices = statiumSpacePriceMapper.selectByExample(statiumSpacePriceCriteria);
        CrmStatiumSpacePrice crmStatiumSpacePrice = null;
        for(StatiumSpacePrice statiumSpacePrice:statiumSpacePrices){
        	crmStatiumSpacePrice = new CrmStatiumSpacePrice();
        	BeanUtils.copyProperties(statiumSpacePrice, crmStatiumSpacePrice);
        	crmStatiumSpacePrice.setId(UUID.get());
        	crmStatiumSpacePriceMapper.insert(crmStatiumSpacePrice);
        }
        
        //拷贝场地价格
        SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
        SpacePriceCriteria.Criteria spacePriceCri = spacePriceCriteria.createCriteria();
        spacePriceCri.andSpaceIdIn(spaceIds);
        List<SpacePrice> spacePrices = spacePriceMapper.selectByExample(spacePriceCriteria);
        CrmSpacePrice crmSpacePrice = null;
        for(SpacePrice spacePrice:spacePrices){
        	crmSpacePrice = new CrmSpacePrice();
        	BeanUtils.copyProperties(spacePrice,crmSpacePrice);
        	crmSpacePrice.setId(UUID.get());
        	crmSpacePriceMapper.insert(crmSpacePrice);
        }
        
    }

    /**
     * 
     * <修改管理员信息><功能具体实现>
     *
     * @create：2015年9月17日 下午4:52:52
     * @author： liangsh
     * @param crmUser
     */
    @Transactional(readOnly = false)
    public void upateUser(CrmUser crmUser) {
        String userId = crmUser.getUserId();
        // 删除现有权限
        CrmUserFuncCriteria funcCriteria = new CrmUserFuncCriteria();
        CrmUserFuncCriteria.Criteria c = funcCriteria.createCriteria();
        c.andUserIdEqualTo(userId);
        crmUserFuncMapper.deleteByExample(funcCriteria);
        
        entryptPassword(crmUser);
        crmUserMapper.updateByPrimaryKeySelective(crmUser);

        // 重新添加权限
        List<CrmFunction> crmFuncs = crmFunctionMapper.selectByExample(new CrmFunctionCriteria());
        CrmUserFunc uf = null;
        for (CrmFunction func : crmFuncs) {
            uf = new CrmUserFunc();
            uf.setFuncId(func.getFuncId());
            uf.setUserId(userId);
            crmUserFuncMapper.insertSelective(uf);
        }

        // 删除现有场馆模板价格、场地模板价格、场地价格
        StatiumSpaceCriteria spaceCriteria = new StatiumSpaceCriteria();
        StatiumSpaceCriteria.Criteria spaceCri = spaceCriteria.createCriteria();
        spaceCri.andStatiumIdEqualTo(crmUser.getStatiumId());
        List<StatiumSpace> statiumSpaces = statiumSpaceMapper.selectByExample(spaceCriteria);
        for (StatiumSpace ssp : statiumSpaces) {
            CrmStatiumSpacePriceCriteria crmStatiumSpacePriceCriteria = new CrmStatiumSpacePriceCriteria();
            CrmStatiumSpacePriceCriteria.Criteria crmStatiumSpacePriceCri = crmStatiumSpacePriceCriteria
                    .createCriteria();
            crmStatiumSpacePriceCri.andSpaceIdEqualTo(ssp.getId());
            crmStatiumSpacePriceMapper.deleteByExample(crmStatiumSpacePriceCriteria);

            CrmSpacePriceCriteria crmSpacePriceCriteria = new CrmSpacePriceCriteria();
            CrmSpacePriceCriteria.Criteria crmSpacePriceCri = crmSpacePriceCriteria.createCriteria();
            crmSpacePriceCri.andSpaceIdEqualTo(ssp.getId());
            crmSpacePriceMapper.deleteByExample(crmSpacePriceCriteria);
        }
        CrmStatiumPriceTmplCriteria crmStatiumPriceTmplCriteria = new CrmStatiumPriceTmplCriteria();
        CrmStatiumPriceTmplCriteria.Criteria crmStatiumPriceTmplCri = crmStatiumPriceTmplCriteria.createCriteria();
        crmStatiumPriceTmplCri.andStatiumIdEqualTo(crmUser.getStatiumId());
        crmStatiumPriceTmplMapper.deleteByExample(crmStatiumPriceTmplCriteria);

        /*// 重新添加场馆模板价格、场地模板价格、场地价格
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("statiumId", crmUser.getStatiumId());
        String sql = FreeMarkerUtils.format("/template/crm_statium/crm_statium_price_tmpl.ftl", paras);
        jdbcTemplate.update(sql);
        paras.clear();
        for (StatiumSpace ssp : statiumSpaces) {
            paras.put("spaceId", ssp.getId());
            jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_statium_space_price.ftl", paras));
            jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_space_price.ftl", paras));
        }*/
        
      //复制价格模板
        StatiumPriceTmplCriteria priceTmplCriteria = new StatiumPriceTmplCriteria();
        StatiumPriceTmplCriteria.Criteria priceTmplCri = priceTmplCriteria.createCriteria();
        priceTmplCri.andStatiumIdEqualTo(crmUser.getStatiumId());
        List<StatiumPriceTmpl> priceTemps = statiumPriceTmplMapper.selectByExample(priceTmplCriteria);
        CrmStatiumPriceTmpl crmTemp = null;
        for(StatiumPriceTmpl temp:priceTemps){
        	crmTemp = new CrmStatiumPriceTmpl();
        	BeanUtils.copyProperties(temp, crmTemp);
        	crmTemp.setId(UUID.get());
        	crmStatiumPriceTmplMapper.insert(crmTemp);
        }
        
        StatiumSpaceCriteria spaceCriteria_ = new StatiumSpaceCriteria();
        StatiumSpaceCriteria.Criteria spaceCri_ = spaceCriteria_.createCriteria();
        spaceCri_.andStatiumIdEqualTo(crmUser.getStatiumId());
        List<StatiumSpace> statiumSpaces_ = statiumSpaceMapper.selectByExample(spaceCriteria_);
        List<String> spaceIds = new ArrayList<String>();
        for (StatiumSpace ssp : statiumSpaces_) {
            /*paras.clear();
            paras.put("spaceId", ssp.getId());*/
        	spaceIds.add(ssp.getId());
 /*           jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_statium_space_price.ftl", paras));
            jdbcTemplate.update(FreeMarkerUtils.format("/template/crm_statium/crm_space_price.ftl", paras));
*/        }
        //拷贝场地价格模板
        StatiumSpacePriceCriteria statiumSpacePriceCriteria = new StatiumSpacePriceCriteria();
        StatiumSpacePriceCriteria.Criteria statiumSpacePriceCri = statiumSpacePriceCriteria.createCriteria();
        statiumSpacePriceCri.andSpaceIdIn(spaceIds);
        List<StatiumSpacePrice> statiumSpacePrices = statiumSpacePriceMapper.selectByExample(statiumSpacePriceCriteria);
        CrmStatiumSpacePrice crmStatiumSpacePrice = null;
        for(StatiumSpacePrice statiumSpacePrice:statiumSpacePrices){
        	crmStatiumSpacePrice = new CrmStatiumSpacePrice();
        	BeanUtils.copyProperties(statiumSpacePrice, crmStatiumSpacePrice);
        	crmStatiumSpacePrice.setId(UUID.get());
        	crmStatiumSpacePriceMapper.insert(crmStatiumSpacePrice);
        }
        
        //拷贝场地价格
        SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
        SpacePriceCriteria.Criteria spacePriceCri = spacePriceCriteria.createCriteria();
        spacePriceCri.andSpaceIdIn(spaceIds);
        List<SpacePrice> spacePrices = spacePriceMapper.selectByExample(spacePriceCriteria);
        CrmSpacePrice crmSpacePrice = null;
        for(SpacePrice spacePrice:spacePrices){
        	crmSpacePrice = new CrmSpacePrice();
        	BeanUtils.copyProperties(spacePrice,crmSpacePrice);
        	crmSpacePrice.setId(UUID.get());
        	crmSpacePriceMapper.insert(crmSpacePrice);
        }
    }
    
    public boolean checkCrmUser(String userName,String userId) throws Exception{
    	boolean flag = true;
    	if(StringUtils.isBlank(userName)){
    		return flag;
    	}
    	CrmUserCriteria criteria = new CrmUserCriteria();
    	CrmUserCriteria.Criteria cri = criteria.createCriteria();
    	cri.andLoginNameEqualTo(userName);
    	int cont = crmUserMapper.countByExample(criteria);
    	if(cont!=0){
    		if(StringUtils.isNotBlank(userId)){
    			CrmUser user = this.selectByPrimaryKey(CrmUser.class, userId);
    			if(user.getLoginName().equals(userName)){
    				return true;
    			}
    		}
    		flag = false;
    	}
    	return flag;
    }

    /**
     * 
     * <删除管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午4:53:13
     * @author： liangsh
     * @param userId
     */
    @Transactional(readOnly = false)
    public void delUser(String userId) {
        CrmUserFuncCriteria funcCriteria = new CrmUserFuncCriteria();
        CrmUserFuncCriteria.Criteria c = funcCriteria.createCriteria();
        c.andUserIdEqualTo(userId);
        crmUserFuncMapper.deleteByExample(funcCriteria);
        crmUserMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(CrmUser user) {
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(null);
            user.setSalt(null);
            return;
        }
        String[] hash = Encrypts.hashPassword(user.getPassword());
        user.setPassword(hash[0]);
        user.setSalt(hash[1]);
    }

    /**
     * 
     * <功能描述><功能具体实现>
     *
     * @create：2015年9月17日 下午3:52:55
     * @author： liangsh
     * @param name
     * @return
     */
    public CrmUser getByLoginName(String name) {
        CrmUserCriteria rc = new CrmUserCriteria();
        CrmUserCriteria.Criteria cri = rc.createCriteria();
        cri.andLoginNameEqualTo(name);
        List<CrmUser> users = crmUserMapper.selectByExample(rc);
        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     * 
     * <场馆置顶><功能具体实现>
     *
     * @create：2015年9月17日 下午3:52:55
     * @author： CYY
     * @param statiumId 场馆id
     * @param result 返回结果
     * @param type 0为置顶1为解除
     * @return
     */
    @Transactional(readOnly = false)
    public Map<String, Object> top(String statiumId, Map<String, Object> result, Integer type) {
        try {
            StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
            if (type == 0) {
                if (statiumDetail != null && statiumDetail.getDeleteFlag() == null) {
                    StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
                    StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
                    if (com.lc.zy.common.Constants.MUNICIPALITY_LIST.contains(statiumDetail.getProvince())) {
                        cri.andProvinceEqualTo(statiumDetail.getProvince());
                    } else {
                        cri.andCityEqualTo(statiumDetail.getCity());
                    }
                    cri.andDeleteFlagIsNull();
                    cri.andTopEqualTo(1);
                    List<StatiumDetail> statiumDetailList = statiumDetailMapper.selectByExample(statiumDetailCriteria);
                    if (statiumDetailList.size() < 10) {
                        statiumDetail.setTop(1);
                        Date now = new Date();
                        statiumDetail.setTopTime(now);
                        statiumDetail.setEt(now);
                        this.updateByPrimaryKeySelective(statiumDetail, statiumId);
                        result.put(Constants.Result.RESULT, true);
                    } else {
                        result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "一个城市最多只能置顶十个场馆，请先解除其他场馆");
                    }
                } else {
                    result.put(Constants.Result.RESULT, false);
                    result.put(Constants.Result.REASON, "该场馆不存在，请确认");
                }
            } else {
                statiumDetail.setTop(0);
                Date now = new Date();
                statiumDetail.setTopTime(null);
                statiumDetail.setEt(now);
                this.updateByPrimaryKey(statiumDetail, statiumId);
                result.put(Constants.Result.RESULT, true);
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <修改场馆签约信息><功能具体实现>
     *
     * @create：2015年11月10日 下午3:37:44
     * @author： CYY
     * @param statiumId
     * @param result
     * @return
     */
    @Transactional(readOnly = false)
    public List<String> updateStatium(String statiumId, Map<String, Object> result) {
        try {
            SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
            SpacePriceCriteria.Criteria cri = spacePriceCriteria.createCriteria();
            cri.andSpaceIdIsNull();
            if (StringUtils.isNotEmpty(statiumId)) {
                cri.andStatiumIdEqualTo(statiumId);
            }
            List<SpacePrice> spacePriceList = spacePriceMapper.selectByExample(spacePriceCriteria);
            Set<String> set = new HashSet<String>();
            for (SpacePrice apace : spacePriceList) {
                StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, apace.getStatiumId());
                if (statiumDetail == null
                        || (statiumDetail.getDeleteFlag() != null && statiumDetail.getDeleteFlag() == 1)) {
                    continue;
                }
                set.add(apace.getStatiumId());
                statiumDetail.setIsSigned(0);
                statiumDetail.setEt(new Date());
                this.updateByPrimaryKeySelective(statiumDetail, statiumDetail.getId());
            }
            result.put(Constants.Result.RESULT, true);
            return new ArrayList<String>(set);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <场馆回访记录><功能具体实现>
     *
     * @create：2015年12月4日 上午10:45:34
     * @author： sl
     * @param id
     * @param visitReason
     * @param visitResult
     */
    @Transactional(readOnly = false)
	public void visit(String id, String visitReason, String visitResult) {
		try {
			Date now = DateUtils.now();
			String userId = SessionUtil.currentUserId();
			StatiumVisitLog statiumVisitLog = new StatiumVisitLog();
			statiumVisitLog.setId(UUID.get());
			statiumVisitLog.setCb(userId);
			statiumVisitLog.setCt(now);
			statiumVisitLog.setStatiumId(id);
			statiumVisitLog.setVisitReason(visitReason);
			statiumVisitLog.setVisitResult(visitResult);
	        this.insertSelective(statiumVisitLog, statiumVisitLog.getId());
		} catch (Exception e) {
			logger.debug("场馆回访记录", e.getMessage());
		}
	}
	
	/**
	 * 
	 * <获取场馆回访记录><功能具体实现>
	 *
	 * @create：2015年12月4日 上午10:52:56
	 * @author： sl
	 * @param statiumId
	 * @return
	 */
    @Transactional(readOnly = true)
	public List<StatiumVisitLog> getVisitLog(String statiumId) {
		StatiumVisitLogCriteria statiumVisitLogCriteria = new StatiumVisitLogCriteria();
		statiumVisitLogCriteria.createCriteria().andStatiumIdEqualTo(statiumId);
		statiumVisitLogCriteria.setOrderByClause("ct desc");
		return statiumVisitLogMapper.selectByExample(statiumVisitLogCriteria);
    }

    /**
     * <获取指定场馆所有类型上单情况></>
     * @param statiumId
     * @return
     */
    @Transactional(readOnly = false)
    public List<NuomiTpDealId> getUploadData(String statiumId){
        try{
            StatiumDetail statium = this.selectByPrimaryKey(StatiumDetail.class,statiumId);
            List<NuomiTpDealId> list = new ArrayList<NuomiTpDealId>();
            String[] sports = statium.getSportType().split(";;");
            for (String sport:sports) {
                NuomiTpDealIdCriteria c = new NuomiTpDealIdCriteria();
                NuomiTpDealIdCriteria.Criteria cri = c.createCriteria();
                cri.andStatiumIdEqualTo(statiumId);
                cri.andSportTypeEqualTo(sport);
                if(nuomiTpDealIdMapper.countByExample(c) > 0 ){
                    //已经同步过且同步进度为空的查询进度
                    NuomiTpDealId info = nuomiTpDealIdMapper.selectByExample(c).get(0);
                    if(info.getStatus() != 1){
                        //查询进度
                        String url = configs.get("data.server")+"/app/nuomi/report";
                        logger.debug(url);
                        PostMethod method = new PostMethod(url);
                        method.addParameter("statiumId",statiumId);
                        method.addParameter("sportType",sport);
                        HttpClient client = new HttpClient();
                        client.executeMethod(method);
                        String res = method.getResponseBodyAsString();
                        Type type = new TypeToken<Map<String,Object>>() { }.getType();
                        Map<String,Object> resultMap = MyGson.getInstance().fromJson(res, type);
                        logger.debug("dataMap={}",resultMap.toString());
                        Map<String,Object> data = (Map<String,Object>)resultMap.get("data");
                        if(data.containsKey("bc_status")) {
                            int bc_status = Integer.valueOf(data.get("bc_status").toString());
                            info.setStatus(bc_status);
                        }else{
                            info.setStatus(9);
                        }
                        //更新进度值
                        this.updateByPrimaryKeySelective(info,info.getId());
                    }
                    list.add(info);
                }else{
                    NuomiTpDealId info = new NuomiTpDealId();
                    info.setStatiumId(statiumId);
                    info.setSportType(sport);
                    list.add(info);
                }
            }
            return  list;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * <将场馆指定运动类型上单至糯米></>
     * @param sport
     * @param statiumId
     * @param isHidden
     * @return
     */
    @Transactional(readOnly = false)
    public  Map<String, Object>  uploadData(String sport,String statiumId,Integer isHidden){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            NuomiTpDealIdCriteria c = new NuomiTpDealIdCriteria();
            NuomiTpDealIdCriteria.Criteria cri = c.createCriteria();
            cri.andStatiumIdEqualTo(statiumId);
            cri.andSportTypeEqualTo(sport);
            Integer uploadType = 0; //0:上报  2:隐藏   3:更新
            NuomiTpDealId nuomiTpDealId = null;
            int total = nuomiTpDealIdMapper.countByExample(c);
            if(total > 0) {
                nuomiTpDealId = nuomiTpDealIdMapper.selectByExample(c).get(0);
            }
            if (isHidden == 1) {
                uploadType = 2;
            } else {
                    if(nuomiTpDealId != null && nuomiTpDealId.getStatus() == 1) {
                        //只有上单处理成功的团单再同步是更新其他都是上报模式
                        uploadType = 3;
                    }
            }
            //post请求app上单接口
            String url = configs.get("data.server")+"/app/nuomi/upload";
            logger.debug(url);
            PostMethod method = new PostMethod(url);
            method.addParameter("statiumId", statiumId);
            method.addParameter("type", sport);
            String userId = SessionUtil.currentUserId();
            if(org.apache.commons.lang.StringUtils.isEmpty(userId)){
                userId = "1";
            }
            method.addParameter("userId",userId);
            method.addParameter("uploadType", uploadType.toString());
            HttpClient client = new HttpClient();
            client.executeMethod(method);
            String res = method.getResponseBodyAsString();
//            成功
//            {
//                    "cached": 0,
//                    "data": {
//                             "command_id": "266462"
//                              },
//                    "errmsg": "success",
//                    "errno": 0,
//                    "msg": "success",
//                    "serverlogid": 1373303043,
//                    "serverstatus": 0,
//                    "timestamp": 1471504973
//            }
            Type type = new TypeToken<Map<String,Object>>() { }.getType();
            Map<String,Object> resultMap = MyGson.getInstance().fromJson(res, type);
            logger.debug("dataMap={}",resultMap.toString());
            String msg = "";
            if(resultMap.get("errno").toString().equals("0.0")){
                msg =  "上单提交成功！";
                if(nuomiTpDealId != null) {
                    nuomiTpDealId.setEb(SessionUtil.currentUserId());
                    nuomiTpDealId.setEt(new Date());
                    nuomiTpDealId.setStatus(0);
                    this.updateByPrimaryKeySelective(nuomiTpDealId, nuomiTpDealId.getId());
                }
                result.put(Constants.Result.RESULT, true);
            }else{
                result.put(Constants.Result.RESULT, false);
                if(!resultMap.containsKey("errmsg")){
                    msg =  resultMap.get("msg").toString();
                }else{
                    msg =  resultMap.get("errmsg").toString();
                }
            }
            result.put(Constants.Result.REASON,msg);

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * <球友圈取消订单调用糯米取消订单接口></>
     * @param orderId
     * @return
     */
    @Transactional(readOnly = false)
    public  Map<String, Object>  nuomiOrderCancel(String orderId,String reason){
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "";
        try {
            if(org.apache.commons.lang.StringUtils.isEmpty(orderId)) {
                result.put(Constants.Result.RESULT, false);
                msg = "订单Id不能为空";
            }
            Order order = this.selectByPrimaryKey(Order.class, orderId);
            order.setExternalStatus(5);   //球友圈申请退款
            order.setReason(reason);
            order.setStatus("ORDER_REFUNDED");
            //post请求糯米取消订单接口
            String url = configs.get("data.server")+"/app/nuomi/tpDealCancel";
            logger.debug(url);
            PostMethod method = new PostMethod(url);
            method.addParameter("orderId", orderId);
            method.addParameter("reason",reason);
            HttpClient client = new HttpClient();
            client.executeMethod(method);
            String res = method.getResponseBodyAsString();
//            {
//                "cached": 0,
//                    "data": true,
//                    "errmsg": "success",
//                    "errno": 0,
//                    "msg": "success",
//                    "serverlogid": 619868325,
//                    "serverstatus": 0,
//                    "timestamp": 1471925419
//            }
            Type type = new TypeToken<Map<String,Object>>() { }.getType();
            Map<String,Object> resultMap = MyGson.getInstance().fromJson(res, type);
            logger.debug("dataMap={}",resultMap.toString());
            if(resultMap.get("errno").toString().equals("0.0")){
                msg =  "订单取消成功！";
                this.updateByPrimaryKeySelective(order,orderId);
                this.notifyStatium(order);
                this.writeLog(order);
                result.put(Constants.Result.RESULT, true);
            }else{
                result.put(Constants.Result.RESULT, false);
                if(!resultMap.containsKey("errmsg")){
                    msg =  resultMap.get("msg").toString();
                }else{
                    msg =  resultMap.get("errmsg").toString();
                }
            }
            result.put(Constants.Result.REASON,msg);

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * <球友圈审核糯米退款申请接口></>
     * @param orderId
     * @return
     */
    @Transactional(readOnly = false)
    public  Map<String, Object>  nuomiOrderAudi(String orderId,String reason,Integer status){
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "";
        try {
            if(org.apache.commons.lang.StringUtils.isEmpty(orderId)) {
                result.put(Constants.Result.RESULT, false);
                msg = "订单Id不能为空";
            }
            Order order = this.selectByPrimaryKey(Order.class, orderId);
            if(order != null){
                order.setExternalStatus(status);   //审核通过
                if(status == 4){
                    order.setReason(reason);
                }else{
                    order.setStatus("ORDER_REFUNDING");
                }
                this.updateByPrimaryKeySelective(order,orderId);

                msg =  "订单审核成功！";
                result.put(Constants.Result.RESULT, true);
            }else{
                result.put(Constants.Result.RESULT, false);
                msg =  "订单审核失败";
            }
            result.put(Constants.Result.REASON,msg);

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * 通知球馆
     * @param order
     */
    private void notifyStatium(Order order){
        OrdeNotifyrMessage notifyMessage = new OrdeNotifyrMessage();
        List<OrdeNotifyrMessage.SpaceBean> spaces = new ArrayList<OrdeNotifyrMessage.SpaceBean>();
        OrderItemCriteria itemCriteria = new OrderItemCriteria();
        OrderItemCriteria.Criteria criteria = itemCriteria.createCriteria();
        criteria.andOrderIdEqualTo(order.getId());
        List<OrderItem> orderItems = orderItemMapper.selectByExample(itemCriteria);
        OrdeNotifyrMessage.SpaceBean spaceBean = null;
        for (OrderItem orderItem : orderItems) {
            freeSpaceCounter.inc(DateFormatUtils.format(orderItem.getStartDate(), "yyyy-MM-dd"),
                    order.getStatiumId(), orderItem.getSpaceId(), orderItem.getSportType(), -1);
            Date startTime = new Date(orderItem.getStartTime() * 1000L);
            Double startHour = org.apache.commons.lang.time.DateUtils.getFragmentInHours(startTime, Calendar.DATE) / 1d;
            int startH = startHour.intValue();
            spaceBean = new OrdeNotifyrMessage.SpaceBean();
            spaceBean.setSpaceId(orderItem.getSpaceId());
            String orderTime = startH < 10 ? ("0" + startH + ":00") : (startH + ":00");
            spaceBean.setTime(orderTime);
            spaces.add(spaceBean);
            spaceBean = new OrdeNotifyrMessage.SpaceBean();
            spaceBean.setSpaceId(orderItem.getSpaceId());
            orderTime = startH < 10 ? ("0" + startH + ":30") : (startH + ":30");
            spaceBean.setTime(orderTime);
            spaces.add(spaceBean);
            notifyMessage.setOrderDate(DateUtil.formatDate(orderItem.getStartDate(), "yyyy-MM-dd"));
        }
        notifyMessage.setSpaces(spaces);
        notifyMessage.setStatiumId(order.getStatiumId());
        notifyMessage.setType("cancel");
        notifyMessage.setOrderId(order.getId());
        orderNotifyUtil.notifyOrder(MyGson.getInstance().toJson(notifyMessage));
    }

    /**
     * 记录订单日志
     * @param order
     */
    private void writeLog(Order order){
        OrderLog log = new OrderLog();
        log.setId(UUID.get());
        log.setCt(new Date());
        log.setOrderId(order.getId());
        log.setUserId(order.getCustomerId());
        log.setAction(Constants.OrderAction.CANCEL);
        log.setComment(order.getStatus());
        orderLogMapper.insert(log);
    }
    
    public StatiumAccreditVo getAccredit(String id) throws Exception{
    	StatiumAccredit item = this.selectByPrimaryKey(StatiumAccredit.class, id);
    	StatiumAccreditVo accredit = new StatiumAccreditVo();
    	BeanUtils.copyProperties(item, accredit);
    	String statiumId = item.getStatiumId();
    	if(StringUtils.isNotBlank(statiumId)){
    		StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
    		String name = detail.getName();
    		accredit.setStatiumName(name);
    	}
    	return accredit;
    }
}
