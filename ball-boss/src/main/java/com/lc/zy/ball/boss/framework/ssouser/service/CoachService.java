package com.lc.zy.ball.boss.framework.ssouser.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.web.FunctionTag;
import com.lc.zy.ball.boss.framework.ssouser.vo.CoachResumeVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.CoachVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CoachBankMapper;
import com.lc.zy.ball.domain.oa.mapper.CoachResumeMapper;
import com.lc.zy.ball.domain.oa.mapper.CoachStatiumMapper;
import com.lc.zy.ball.domain.oa.mapper.CoacherMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.CoachBank;
import com.lc.zy.ball.domain.oa.po.CoachBankCriteria;
import com.lc.zy.ball.domain.oa.po.CoachResume;
import com.lc.zy.ball.domain.oa.po.CoachResumeCriteria;
import com.lc.zy.ball.domain.oa.po.CoachStatium;
import com.lc.zy.ball.domain.oa.po.CoachStatiumCriteria;
import com.lc.zy.ball.domain.oa.po.Coacher;
import com.lc.zy.ball.domain.oa.po.CoacherCriteria;
import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

@Component
@Transactional(readOnly = true)
public class CoachService extends AbstractCacheService {

    private static final Logger logger = LoggerFactory.getLogger(CoachService.class);

    @Autowired
    private CoacherMapper coacherMapper;

    @Autowired
    private SsoUserMapper ssoUserMapper;

    @Autowired
    private SsoUserService ssoUserService;

    @Autowired
    private CoachResumeMapper coachResumeMapper;
    
    @Autowired
    private SEQGenerate seqGenerate;
    
    @Autowired
    private CoachStatiumMapper coachStatiumMapper;
    
    @Autowired
    private CoachBankMapper coachBankMapper;
    
    @Autowired
    private QueueProducer queueProducer;
    
    /**
     * 页面查询对象.
     * 
     * @param searchParams 查询条件.
     * @param page 分页页号, 基于0.
     * @param size 分页大小.
     * @param isPage 是否分页显示.
     * @return 分页数据.
     * @throws Exception
     */
    public Page<CoachVo> find(Map<String, Object> searchParams, int page, int size, boolean isPage, boolean isHasCount)
            throws Exception {

        PageRequest pageable = new PageRequest(page, size);

        CoacherCriteria cc = new CoacherCriteria();
        if (isPage) {
            cc.setMysqlOffset(pageable.getOffset());
            cc.setMysqlLength(pageable.getPageSize());
        }

        CoacherCriteria.Criteria cri = cc.createCriteria();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            if(StringUtils.isNotBlank((String)searchParams.get("LIKE_area"))){
            	cri.andAreaLike((String)searchParams.get("LIKE_area"));
            	searchParams.remove("LIKE_area");
            }
            Criterias.bySearchFilter(cri, filters.values());
        }

        // 用户状态 正常1 删除 0冻结2
        cri.andUserStateNotEqualTo("0");
        cc.setOrderByClause("top desc,submit_time desc");
        int total = 0;
        if (isHasCount) {
            total = coacherMapper.countByExample(cc);
        }

        List<Coacher> list = coacherMapper.selectByExample(cc);

        ArrayList<CoachVo> arrayList = new ArrayList<CoachVo>();
        List<DicItem> sprotTypeDic = FunctionTag.getDicItems("SPORT_TYPE");
        List<DicItem> registResoucreDic = FunctionTag.getDicItems("USER_SOURCE");
        if (list != null) {
            for (Coacher c : list) {
                CoachVo vo = new CoachVo();
                BeanUtils.copyProperties(c, vo);
                StringBuffer sb = new StringBuffer();
                if (CommonUtils.isNotEmpty(vo.getSportType())) {
                    String[] v = vo.getSportType().split(";;");
                    if (v != null && v.length > 0) {
                        for (String b : v) {
                            for (DicItem d : sprotTypeDic) {
                                if (b.equals(d.getItemCode())) {
                                    sb.append(d.getItemName()).append(",");
                                }
                            }
                        }
                    }
                    vo.setSprotTypeZWStr(sb.toString().replaceAll(",$", ""));
                }
                if (CommonUtils.isNotEmpty(c.getRegistSource())) {
                    for (DicItem d : registResoucreDic) {
                        if (vo.getRegistSource().contains(d.getItemCode())) {
                            vo.setRegistSourceStr(d.getItemName());
                            break;
                        }
                    }
                }
                if (CommonUtils.isNotEmpty(c.getSignatory())) {
                    User signUser = selectByPrimaryKey(User.class, c.getSignatory());
                    vo.setSignUserName(signUser != null ? signUser.getNickname() : "");
                }
                if(StringUtils.isNotBlank(vo.getArea())){
                	Map<String,String> zoneMap = Zonemap.split(vo.getArea());
                    vo.setAreaStr(Zonemap.toString(vo.getArea()));
                    vo.setProvince(zoneMap.get("province"));
                    vo.setCity(zoneMap.get("city"));
                    vo.setArea(zoneMap.get("area"));
                }
                String userState = vo.getUserState();
                if(vo.getPrice()!=null){
                	vo.setPrice(vo.getPrice()/100);
                }
                if("0".equals(userState)){
                	vo.setUserStateStr("删除");
                }else if("1".equals(userState)){
                	vo.setUserStateStr("正常");
                }else if("2".equals(userState)){
                	vo.setUserStateStr("冻结");
                }
                arrayList.add(vo);
            }
        }
        return new PageImpl<>(arrayList, pageable, total);
    }

    /**
     * 根据主键查询对象.
     * 
     * @param String coachId.
     * @return 教陪练.
     * @throws Exception
     */
    public Coacher getCoach(String id) throws Exception {
        return this.selectByPrimaryKey(Coacher.class, id);
    }

    /**
     * 插入或者更新对象.
     * 
     * @param String coachId.
     * @return 教陪练.
     * @throws Exception
     */
    public void insertOrUpdateCoach(Coacher coach) throws Exception {
        String id = "";
        if (coach != null && StringUtils.isNotBlank(coach.getId())) {// update
            id = coach.getId();
            coach.setUpdateUser(SessionUtil.currentUserId());
            coach.setEt(new Date());
            this.updateByPrimaryKeySelective(coach, id);
        } else {// insert
            id = UUID.get();
            coach.setId(id);
            coach.setCt(new Date());
            this.insertSelective(coach, id);
        }
    }

    /**
     * 删除教陪练
     * 
     * @param id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void deleteCoach(String id) throws Exception {
    	Coacher user = getCoach(id);

        if (user != null) {
            // 更新Coach的user_state
        	Coacher updateCoach = new Coacher();
            updateCoach.setUserState("0");// 代表删除
            updateCoach.setId(id);

            this.insertOrUpdateCoach(updateCoach);
            // 更新SSoUser的
            ssoUserService.deleteSsoUserById(user.getSsoUserId());
        }
    }

    /**
     * 批量删除教陪练
     * 
     * @param id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void deleteBatchCoach(String ids) throws Exception {
        String[] idsList = ids.split(";");
        idsList = ids.split(";");
        if (idsList != null && idsList.length > 0) {
            for (String id : idsList) {
            	Coacher user = getCoach(id);

                // 更新Coach的user_state
            	Coacher updateCoach = new Coacher();
                updateCoach.setUserState("0");// 代表删除
                updateCoach.setId(id);

                this.insertOrUpdateCoach(updateCoach);
                // 更新SSoUser的
                ssoUserService.deleteSsoUserById(user.getSsoUserId());
                //更新教陪信息到solr
                queueProducer.push(QueueNames.SYNC_COACH_QUEUE,user.getSsoUserId() );
            }
        }
    }

    /**
     * 修改状态
     * 
     * @param userId
     * @param status
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void updateStatus(String userId, String status) throws Exception {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(status)) {
        	Coacher user = getCoach(userId);

            if (user != null) {
            	Coacher updateCoach = new Coacher();
                updateCoach.setId(userId);
                if ("1".equals(status)) {
                    updateCoach.setUserState("2");// 代表冻结
                } else {
                    updateCoach.setUserState("1");// 代表解冻
                }
                // 更新Coach的user_state
                this.insertOrUpdateCoach(updateCoach);
            }
        }
    }

    /**
     * 待审核的教陪练数据
     * 
     * @return
     */
    public int getUnCheckNum() {
        CoacherCriteria cc = new CoacherCriteria();
        CoacherCriteria.Criteria cri = cc.createCriteria();
        List<String> list = new ArrayList<String>();
        list.add("1");// 待审核
        list.add("4");// 修改待审核
        cri.andStatusIn(list);
        cri.andUserStateNotEqualTo("0");
        return coacherMapper.countByExample(cc);
    }

    /**
     * 添加教陪练
     * 
     * @param coachVo
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public Map<String, String> createOrUpdateCoach(CoachVo coachVo) throws Exception {
    	Map<String,String> result = new HashMap<String, String>();
    	String uid = null;
    	SsoUser ssoUser = null;
    	SsoUserCriteria cc = new SsoUserCriteria();
        SsoUserCriteria.Criteria cri = cc.createCriteria();
        cri.andPhoneEqualTo(coachVo.getPhone());
        List<SsoUser> users = ssoUserMapper.selectByExample(cc);
    	if(StringUtils.isNotEmpty(coachVo.getId())){
    		coachVo.setUpdateUser(SessionUtil.currentUserId());
    		coachVo.setEt(new Date());
    		if(coachVo.getOldPhone().equals(coachVo.getPhone())){
    			ssoUser = users.get(0);
    			coachVo.setSsoUserId(ssoUser.getId());
    		}else{
    			if(CollectionUtils.isNotEmpty(users)){
    				throw new Exception("手机号已存在，不可变更...");
    			}else{
    				//如果更新的电话号码，已有ssouser使用，则更改该用户类型为'普通'
    		    	SsoUserCriteria cc2 = new SsoUserCriteria();
    		        SsoUserCriteria.Criteria cri2 = cc.createCriteria();
    				cri2.andPhoneEqualTo(coachVo.getOldPhone());
    				List<SsoUser> oldUserList = ssoUserMapper.selectByExample(cc2);
    				if(oldUserList!=null && oldUserList.size()>0){
    					SsoUser oldUser = oldUserList.get(0);
        				//对旧有用户修改
        				oldUser.setProperty("普通");
    					this.updateByPrimaryKeySelective(oldUser, oldUser.getId());
    					result.put("old", oldUser.getId());
    				}
    				
					//绑定新用户
    				ssoUser = new SsoUser();
    				String qiuyouno = seqGenerate.genQiuyouNumber();
    				ssoUser.setQiuyouno(qiuyouno);
    				ssoUser.setNickName("球友"+ssoUser.getQiuyouno());
    				coachVo.setQiuyouno(qiuyouno);
    			}
    		}
    	}else{
    		if(CollectionUtils.isNotEmpty(users)){
    			ssoUser = users.get(0);
    			coachVo.setSsoUserId(ssoUser.getId());
    			coachVo.setQiuyouno(ssoUser.getQiuyouno());
			}else {
				ssoUser = new SsoUser();
				String qiuyouno = seqGenerate.genQiuyouNumber();
				ssoUser.setQiuyouno(qiuyouno);
				ssoUser.setNickName("球友"+ssoUser.getQiuyouno());
				coachVo.setSsoUserId(ssoUser.getId());
				coachVo.setQiuyouno(qiuyouno);
			}
    	}
    	
        List<String> personStyles = coachVo.getPersonStyles();
        String photos = "";
        for(String personStyle:personStyles){
        	if(StringUtils.isNotEmpty(personStyle)){
        		photos+=personStyle+";";
        	}
        }
        coachVo.setPersonStyle(photos);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
        if (StringUtils.isNotBlank(coachVo.getArea())) {
            coachVo.setCity(coachVo.getArea().substring(0, 4) + "00");
            coachVo.setProvince(coachVo.getArea().substring(0, 2) + "0000");
        }
        
        coachVo.setPrice(coachVo.getPrice()*100);
        
        ssoUser.setName(coachVo.getName());
        ssoUser.setSex(coachVo.getSex());
        ssoUser.setCardId(coachVo.getCardId());
        ssoUser.setProperty(coachVo.getUserType());
        
        Coacher updateCoach = new Coacher();
        if (CommonUtils.isNotEmpty(coachVo.getBirthday())) {
        	ssoUser.setBirthday(DateUtils.getDate(coachVo.getBirthday()));
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            cal.setTime(sdf.parse(coachVo.getBirthday()));
            int yearBirth = cal.get(Calendar.YEAR);
            ssoUser.setAge(yearNow - yearBirth);
        }
        
        //后台操作的用户都为已认证
        coachVo.setIsattestation(1);
        if (CommonUtils.isNotEmpty(coachVo.getId())) {
            // 只修改教练信息和关联的用户信息
            ssoUser.setUpdateTime(new Date());
            ssoUser.setUpdateUser(SessionUtil.currentUserId());
            /*if ("3".equals(old.getUserState())) {// 原状态为未通过 则为待审核
                coachVo.setUserState("1");
            } else if ("2".equals(old.getUserState())) {// 原状态为已通过 则为修改待审核
                coachVo.setUserState("4");
            }
            coachVo.setSubmitTime(new Date());*/
            CoachResumeCriteria resumeCriteria = new CoachResumeCriteria();
            CoachResumeCriteria.Criteria reCriteria = resumeCriteria.createCriteria();
            reCriteria.andCoachIdEqualTo(coachVo.getId());
            coachResumeMapper.deleteByExample(resumeCriteria);
            
            CoachStatiumCriteria coachStatiumCriteria = new CoachStatiumCriteria();
            CoachStatiumCriteria.Criteria c = coachStatiumCriteria.createCriteria();
            c.andCoachIdEqualTo(coachVo.getId());
            coachStatiumMapper.deleteByExample(coachStatiumCriteria);
            
            if(StringUtils.isEmpty(ssoUser.getId())){
            	ssoUser.setId(UUID.get());
            	ssoUser.setPhone(coachVo.getPhone());
                ssoUser.setActiveState(0);
                Date time = sdf.parse(sdf.format(new Date()));
                ssoUser.setRegistTime(time);
                ssoUser.setState(1);
                ssoUser.setUsername(coachVo.getPhone());
                ssoUser.setCreateTime(new Date());
                ssoUser.setCreateUser(SessionUtil.currentUserId());
                ssoUser.setRegistSource(Constants.UserRegistSource.PINGTAI);
            	this.insertSelective(ssoUser, ssoUser.getId());// 添加用户
            }else{
            	this.updateByPrimaryKeySelective(ssoUser, ssoUser.getId());// 修改用户
            }
            coachVo.setSsoUserId(ssoUser.getId());
            BeanUtils.copyProperties(coachVo, updateCoach);
            //更新教陪练
            this.updateByPrimaryKeySelective(updateCoach, updateCoach.getId());
            uid = updateCoach.getSsoUserId();
            result.put("new", uid);
        } else {
            if (CommonUtils.isNotEmpty(ssoUser.getId())) {
                ssoUser.setUpdateTime(new Date());
                ssoUser.setUpdateUser(SessionUtil.currentUserId());
                coachVo.setUserState("1");
                coachVo.setSubmitTime(new Date());
                //从后台录入默认审核通过
                coachVo.setStatus("2");
                coachVo.setPassTime(new Date());
                coachVo.setCreateUser(SessionUtil.currentUserId());
                coachVo.setRegistSource(Constants.UserRegistSource.PINGTAI+"");
                try {
                	this.updateByPrimaryKeySelective(ssoUser, ssoUser.getId());// 修改用户
				} catch (Exception e) {
					e.printStackTrace();
				}
            } else {
                ssoUser.setPhone(coachVo.getPhone());
                Date time = sdf.parse(sdf.format(new Date()));
                ssoUser.setRegistTime(time);
                ssoUser.setState(1);
                ssoUser.setUsername(coachVo.getPhone());
                ssoUser.setCreateTime(new Date());
                ssoUser.setCreateUser(SessionUtil.currentUserId());
                ssoUser.setRegistSource(Constants.UserRegistSource.PINGTAI);
                ssoUser.setId(UUID.get());
                //从后台录入默认审核通过
                coachVo.setStatus("2");
                coachVo.setPassTime(new Date());
                coachVo.setUserState("1");
                coachVo.setCreateUser(SessionUtil.currentUserId());
                coachVo.setRegistSource(Constants.UserRegistSource.PINGTAI+"");
                coachVo.setSubmitTime(new Date());
                try {
                	this.insertSelective(ssoUser, ssoUser.getId());// 添加用户
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
            coachVo.setId(UUID.get());
            BeanUtils.copyProperties(coachVo, updateCoach);
            updateCoach.setSsoUserId(ssoUser.getId());
            //插入教陪练
            this.insertSelective(updateCoach, updateCoach.getId());
            uid = updateCoach.getSsoUserId();
            result.put("new", uid);
        }
        /*
         * 下面都是教陪关系表更新或新增
         */
        String resumes = coachVo.getResumeJsons();
    	Type type = new TypeToken<List<CoachResumeVo>>() {
    	}.getType();
    	List<CoachResumeVo> coachResumes = MyGson.getInstance().fromJson(resumes, type);
    	for(CoachResumeVo resumeVo:coachResumes){
    		CoachResume coachResume = new CoachResume();
    		BeanUtils.copyProperties(resumeVo, coachResume);
    		coachResume.setStartTime(DateUtil.parse(resumeVo.getStartTimeStr(), "yyyy-MM-dd", null));
    		coachResume.setEndTime(DateUtil.parse(resumeVo.getEndTimeStr(), "yyyy-MM-dd", null));
    		coachResume.setId(UUID.get());
    		coachResume.setCt(new Date());
    		coachResume.setCoachId(coachVo.getId());
    		try {
    			this.insertSelective(coachResume, coachResume.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	if(coachVo.getOccType().equals("驻场")){
    		String [] statiumIds = coachVo.getStatiumIds().split(";;");
        	for(String statiumId:statiumIds){
        		if(StringUtils.isEmpty(statiumId)){
        			continue;
        		}
        		CoachStatium statium = new CoachStatium();
        		statium.setCoachId(coachVo.getId());
        		statium.setId(UUID.get());
        		statium.setCt(new Date());
        		statium.setStatiumId(statiumId);
        		this.insertSelective(statium, statium.getId());
        	}
    	}
    	
    	//教陪练银行信息
    	CoachBankCriteria coachBankCriteria = new CoachBankCriteria();
    	CoachBankCriteria.Criteria criteria = coachBankCriteria.createCriteria();
    	criteria.andCoachIdEqualTo(coachVo.getId());
    	List<CoachBank> coachBanks = coachBankMapper.selectByExample(coachBankCriteria);
    	if(coachBanks!=null && coachBanks.size()>0){
    		CoachBank cb = coachBanks.get(0);
    		cb.setPayee(coachVo.getPayee());
    		cb.setCardId(coachVo.getCardNo());
    		cb.setBankName(coachVo.getBankName());
    		cb.setCity(coachVo.getBankCity());
    		cb.setEt(new Date());
    		this.updateByPrimaryKey(cb, cb.getId());
    	}else{
    		CoachBank cb = new CoachBank();
    		cb.setId(UUID.get());
    		cb.setCoachId(coachVo.getId());
    		cb.setPayee(coachVo.getPayee());
    		cb.setCardId(coachVo.getCardNo());
    		cb.setBankName(coachVo.getBankName());
    		cb.setCity(coachVo.getBankCity());
    		cb.setCt(new Date());
    		cb.setEt(new Date());
    		this.insert(cb, cb.getId());
    	}
    	//返回教陪关联的ssoUserId，同步solr
    	return result;
    }

    public boolean checkPhone(String phone) {
        CoacherCriteria cc = new CoacherCriteria();
        CoacherCriteria.Criteria cri = cc.createCriteria();
        cri.andPhoneEqualTo(phone);
        // cri.andUserStateNotEqualTo("0");
        return coacherMapper.countByExample(cc) == 0 ? false : true;
    }

    public CoachVo getCoachVo(String id) throws Exception {
        List<DicItem> sprotTypeDic = FunctionTag.getDicItems("SPORT_TYPE");
        CoachVo coachVo = new CoachVo();
        if (CommonUtils.isNotEmpty(id)) {
        	Coacher coach = this.getCoach(id);
            if (coach == null) {
                return null;
            }
            StringBuffer sb = new StringBuffer();
            BeanUtils.copyProperties(coach, coachVo);
            if (CommonUtils.isNotEmpty(coachVo.getSportType())) {
                String[] v = coachVo.getSportType().split(";;");
                if (v != null && v.length > 0) {
                    for (String b : v) {
                        for (DicItem d : sprotTypeDic) {
                            if (b.equals(d.getItemCode())) {
                                sb.append(d.getItemName()).append(",");
                            }
                        }
                    }
                }
                coachVo.setSprotTypeZWStr(sb.toString().replaceAll(",$", ""));
//                for (DicItem d : sprotTypeDic) {
//                    if (coachVo.getSportType().equals(d.getItemCode())) {
//                        sb = d.getItemName();
//                    }
//                }
//                coachVo.setSprotTypeZWStr(sb);
            }
          //设置头像，取个人风采第一张
            String personStyle = coach.getPersonStyle();
            if(personStyle!=null && !"".equals(personStyle)){
            	coachVo.setPhoto(personStyle.split(";")[0]);
            }
            BigDecimal decimal1 = new BigDecimal(coach.getPrice().intValue());
            BigDecimal decimal2 = new BigDecimal(100);
            coachVo.setPrice(decimal1.divide(decimal2).intValue());
        }
        CoachResumeCriteria coachResumeCriteria = new CoachResumeCriteria();
        CoachResumeCriteria.Criteria criteria = coachResumeCriteria.createCriteria();
        criteria.andCoachIdEqualTo(coachVo.getId());
        List<CoachResume> resumes = coachResumeMapper.selectByExample(coachResumeCriteria);
        List<CoachResumeVo> coachResumeVos = new ArrayList<CoachResumeVo>();
        for(CoachResume resume:resumes){
        	CoachResumeVo r = new CoachResumeVo();
        	r.setResume(resume.getResume());
        	r.setStartTimeStr(resume.getStartTime()==null?"":DateUtil.formatDate(resume.getStartTime(), "yyyy-MM-dd"));
        	r.setEndTimeStr(resume.getEndTime()==null?"":DateUtil.formatDate(resume.getEndTime(), "yyyy-MM-dd"));
        	coachResumeVos.add(r);
        }
        CoachStatiumCriteria coachStatiumCriteria = new CoachStatiumCriteria();
        CoachStatiumCriteria.Criteria c = coachStatiumCriteria.createCriteria();
        c.andCoachIdEqualTo(coachVo.getId());
        List<CoachStatium> statiums = coachStatiumMapper.selectByExample(coachStatiumCriteria);
        String statiumIds = "";
        String statiumViews = "";
        String zhcStatiums = "";
        for(CoachStatium coachStatium:statiums){
        	String statiumId = coachStatium.getStatiumId();
        	StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
        	if(detail==null){
        		continue;
        	}
        	statiumViews+=statiumId+"_"+detail.getName()+";;";
        	statiumIds+=statiumId+";;";
        	zhcStatiums+="【"+detail.getName()+"】";
        }
        if(StringUtils.isNotEmpty(statiumIds)){
        	statiumIds = statiumIds.substring(0,statiumIds.length()-2);
        }
        if(StringUtils.isNotEmpty(statiumViews)){
        	statiumViews = statiumViews.substring(0,statiumViews.length()-2);
        }
        if(StringUtils.isNotEmpty(zhcStatiums)){
        	coachVo.setZhcStatiums(zhcStatiums);
        }
        coachVo.setStatiumIds(statiumIds);
        coachVo.setStatiumViews(statiumViews);
        if(CollectionUtils.isNotEmpty(coachResumeVos)){
        	coachVo.setResumeJsons(MyGson.getInstance().toJson(coachResumeVos));
        }
        String techArea = coachVo.getTeachArea();
        String areaName = "";
        if(StringUtils.isNotEmpty(techArea)){
        	for(String area:techArea.split(";;")){
        		areaName+=Zonemap.split(area).get("area")+",";
        	}
        	coachVo.setAreaStr(Zonemap.split(techArea.split(";;")[0]).get("province")+" "+Zonemap.split(techArea.split(";;")[0]).get("city")+":"+areaName.substring(0,areaName.length()-1));
        }
        //获取教陪银行卡相关信息
        CoachBankCriteria coachBankCriteria = new CoachBankCriteria();
        CoachBankCriteria.Criteria cri = coachBankCriteria.createCriteria();
        cri.andCoachIdEqualTo(id);
        List<CoachBank> coachBankList = coachBankMapper.selectByExample(coachBankCriteria);
        if(coachBankList!=null && coachBankList.size()>0){
        	coachVo.setPayee(coachBankList.get(0).getPayee());
        	coachVo.setCardNo(coachBankList.get(0).getCardId());
        	coachVo.setBankName(coachBankList.get(0).getBankName());
        	coachVo.setBankCity(coachBankList.get(0).getCity());
        }
        return coachVo;
    }

    public String transformAddress(String province, String city, String area) {
        StringBuffer sb = new StringBuffer();
        if (province != null) {
            if (province.equals("北京市") || province.equals("天津市") || province.equals("上海市") || province.equals("重庆市")) {
                sb.append(province);
                if (StringUtils.isNotBlank(area)) {
                    sb.append(area);
                }
                return sb.toString();
            }
            if (province.equals("台湾省") || province.equals("香港特别行政区") || province.equals("澳门特别行政区")) {
                return province;
            }
            if (StringUtils.isNotBlank(city)) {
                sb.append(city);
            }
            if (StringUtils.isNotBlank(area)) {
                sb.append(area);
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 
     * <保存执教经历><功能具体实现>
     *
     * @create：2015年9月3日 下午9:35:08
     * @author： CYY
     * @param coachResume
     */
    public void saveResume(CoachResume coachResume) {
        try {
            if (StringUtils.isNotEmpty(coachResume.getId())) {
                coachResume.setEt(new Date());
                coachResumeMapper.updateByPrimaryKeySelective(coachResume);
            } else {
                coachResume.setId(UUID.get());
                coachResume.setEt(new Date());
                coachResume.setCt(new Date());
                coachResumeMapper.insertSelective(coachResume);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 教陪练审核
     * @param coachId
     * @throws Exception
     * @author yankefei
     * @param level 
     */
    @Transactional(readOnly=false)
	public String audit(String coachId, Integer level) throws Exception{
		try {
			Coacher coacher = coacherMapper.selectByPrimaryKey(coachId);
			if(level==1){
				coacher.setCoachLevel("初级教练");
			}else if(level==2){
				coacher.setCoachLevel("中级教练");
			}else if(level==3){
				coacher.setCoachLevel("高级教练");
			}else if(level==4){
				coacher.setCoachLevel("金牌教练");
			}
			coacher.setStatus("2");
			coacher.setPassTime(new Date());
			this.updateByPrimaryKeySelective(coacher, coachId);
			return coacher.getSsoUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * 
     * <教陪管理><根据ssouserid获取教陪信息>
     *
     * @create：2015年9月23日 下午1:23:29
     * @author： liangsh
     * @param ssoUserId
     * @return
     */
    public Coacher findBySsoUserId(String ssoUserId){
    	CoacherCriteria c = new CoacherCriteria();
    	CoacherCriteria.Criteria cir = c.createCriteria();
    	cir.andSsoUserIdEqualTo(ssoUserId);
    	List<Coacher> list = coacherMapper.selectByExample(c);
    	if(!CollectionUtils.isEmpty(list)){
    		return list.get(0);
    	}else{
    		return null;
    	}
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
    public Map<String, Object> top(String coacherId, Map<String, Object> result, Integer type) {
        try {
        	Coacher coach = this.selectByPrimaryKey(Coacher.class, coacherId);
        	coach.setUpdateUser(SessionUtil.currentUserId());
            if (type == 0) {
                if (coach != null) {
                    CoacherCriteria coacherCriteria = new CoacherCriteria();
                    CoacherCriteria.Criteria cri = coacherCriteria.createCriteria();
                    if(StringUtils.isBlank(coach.getArea())){
                    	result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "该教陪所在地区信息不全，请确认");
                        return result;
                    }
            		Map<String, String> zone = com.lc.zy.common.util.Zonemap.split(coach.getArea());
            		
            		if(zone.get("province")==null){
            			result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "该教陪所在地区信息不全，请确认");
                        return result;
            		}
            		
            		if(zone.get("city")==null){
            			result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "该教陪所在地区信息不全，请确认");
                        return result;
            		}
            		String area = "";
            		if("市辖区".equals(zone.get("city"))){
            			area = coach.getArea().substring(0,2);
            		}else{
            			area = coach.getArea().substring(0,4);
            		}
            		cri.andAreaLike(area+"%");
                    cri.andTopEqualTo(1);
                    List<Coacher> coachers = coacherMapper.selectByExample(coacherCriteria);
                    if (coachers.size() < 4) {
                    	coach.setTop(1);
                        Date now = new Date();
                        coach.setTopTime(now);
                        coach.setEt(now);
                        this.updateByPrimaryKeySelective(coach, coacherId);
                        result.put(Constants.Result.RESULT, true);
                    } else {
                        result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "一个城市最多只能置顶四个场馆，请先解除其他场馆");
                    }
                } else {
                    result.put(Constants.Result.RESULT, false);
                    result.put(Constants.Result.REASON, "该教陪不存在，请确认");
                }
            } else {
            	coach.setTop(0);
                Date now = new Date();
                coach.setTopTime(null);
                coach.setEt(now);
                this.updateByPrimaryKey(coach, coacherId);
                result.put(Constants.Result.RESULT, true);
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
    	Map<String, String> zone = com.lc.zy.common.util.Zonemap.split("110000");
    	System.out.println(zone.get("area"));
	}
}
