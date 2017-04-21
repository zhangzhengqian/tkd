package com.lc.zy.ball.boss.framework.statium.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.Sequence;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.security.ShiroCacheManager;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CrmFunctionMapper;
import com.lc.zy.ball.domain.oa.mapper.CrmUserFuncMapper;
import com.lc.zy.ball.domain.oa.mapper.CrmUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumAuditLogMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.po.CrmFunction;
import com.lc.zy.ball.domain.oa.po.CrmFunctionCriteria;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.CrmUserCriteria;
import com.lc.zy.ball.domain.oa.po.CrmUserFunc;
import com.lc.zy.ball.domain.oa.po.CrmUserFuncCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumAuditLog;
import com.lc.zy.ball.domain.oa.po.StatiumAuditLogCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfoCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class StatiumOaInfoService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory
			.getLogger(StatiumOaInfoService.class);

	@Autowired
	private StatiumInfosMapper statiumInfosMapper;

	@Autowired
	private StatiumClassMapper statiumClassMapper;

	@Autowired
	private QueueProducer queueProducer = null;
	
	@Autowired
	private StatiumAuditLogMapper statiumAuditLogMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CrmUserMapper crmUserMapper;
	
	@Autowired
    private ShiroCacheManager shiroCacheManager;
	
	@Autowired 
	private CrmFunctionMapper crmFunctionMapper;
	
	@Autowired
	private CrmUserFuncMapper crmUserFuncMapper;
	
	/**
	 * 
	 * <获取oa道馆list><功能具体实现>
	 *
	 * @create：2016年5月18日 下午5:29:21
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatiumInfos> list(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumInfos> statiumInfos = null;
		try {
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			statiumInfosCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumInfosCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumInfosCriteria.Criteria criteria = statiumInfosCriteria
					.createCriteria();
			Criterias.bySearchFilter(criteria, filters.values());
			statiumInfosCriteria.setOrderByClause("et desc");
			total = statiumInfosMapper.countByExample(statiumInfosCriteria);
			statiumInfos = statiumInfosMapper
					.selectByExample(statiumInfosCriteria);
			for(StatiumInfos statInfos:statiumInfos){
				String cb = statInfos.getCb();
				this.selectByPrimaryKey(User.class, statInfos.getId());
				User user = userMapper.selectByPrimaryKey(cb);
				if(user!=null){
					statInfos.setCb(user.getNickname());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			logger.debug("获取oa道馆list:{}", e.getMessage());
		}
		return new PageImpl<>(statiumInfos, pageRequest, total);
	}

	/**
	 * 
	 * <道馆编辑信息初始化><功能具体实现>
	 *
	 * @create：2016年5月18日 下午5:35:17
	 * @author：sl
	 * @param id
	 * @return
	 */
	public StatiumInfos getStatiumInfosById(String id) {
		StatiumInfos statiumInfos = new StatiumInfos();
		try {
			statiumInfos = this.selectByPrimaryKey(StatiumInfos.class, id);
		} catch (Exception e) {
			logger.debug("道馆编辑信息初始化:{}", e.getMessage());
		}
		return statiumInfos;
	}

	/**
	 * 
	 * <OA道馆信息保存><功能具体实现>
	 *
	 * @create：2016年5月18日 下午5:45:40
	 * @author：sl
	 * @param myForm
	 */
	@Transactional(readOnly = false)
	public void save(StatiumInfosVo myForm) {
		try {
			StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
			StatiumInfos statiumInfos = new StatiumInfos();
			BeanUtils.copyProperties(myForm, statiumInfos);
			String id = statiumInfos.getId();
			// date
			Date now = new Date();
			// userId
			String uId = SessionUtil.currentUserId();
			statiumAuditLog.setCb(uId);
			statiumAuditLog.setCt(now);
			//对审核日志表进行操作
			statiumAuditLog.setAction(Constants.StatiumStatus.STATUS_NEW);
			//logId
			String logId = UUID.get();
			statiumAuditLog.setId(logId);
			if (StringUtils.isBlank(id)) {
				// id
				id = UUID.get();
				statiumInfos.setId(id);
				// ct
				statiumInfos.setCt(now);
				// cb
				statiumInfos.setCb(uId);
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				// dgid
				statiumInfos.setDgId(Integer.valueOf(Sequence.genDgNumber()));
				//审核状态
				statiumInfos.setStatus(0);
				//dgId 为空不能保存成功
				statiumAuditLog.setStatiumId(statiumInfos.getDgId().toString());
				statiumAuditLog.setDescription("对道馆进行添加，待审核");
				this.insertSelective(statiumAuditLog,logId);
				try {
					this.insertSelective(statiumInfos, id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			 else {
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				//审核状态
				//statiumInfos.setStatus(0);
				//statiumAuditLog.setDescription("对道馆进行修改，待审核");
				//dgId 为空不能保存成功
				statiumInfos.setStatus(1);
				statiumAuditLog.setDescription("对道馆进行修改");
				statiumAuditLog.setStatiumId(statiumInfos.getDgId().toString());
				try {
					this.insertSelective(statiumAuditLog,logId);
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("增加审核流水"+e.getMessage());
				}
				this.updateByPrimaryKeySelective(statiumInfos, id);
			}
			queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE,
					String.valueOf(statiumInfos.getDgId()));
		} catch (Exception e) {
			logger.debug("OA道馆信息保存:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <删除OA场馆><功能具体实现>
	 *
	 * @create：2016年5月18日 下午6:45:41
	 * @author：sl
	 * @param statiumId
	 */
	@Transactional(readOnly = false)
	public void deleteStatiumOa(String statiumId) {
		try {
			this.deleteByPrimaryKey(StatiumInfos.class, statiumId);
			// 删除道馆课程
			deleteClass(statiumId);
		} catch (Exception e) {
			logger.debug("删除OA场馆:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <删除道馆课程><功能具体实现>
	 *
	 * @create：2016年5月19日 下午2:45:27
	 * @author：sl
	 * @param statiumId
	 */
	@Transactional(readOnly = false)
	private void deleteClass(String statiumId) {
		try {
			StatiumClassCriteria statiumClassCriteria = new StatiumClassCriteria();
			StatiumClassCriteria.Criteria criteria = statiumClassCriteria
					.createCriteria();
			criteria.andStatiumIdEqualTo(Integer.valueOf(statiumId));
			List<StatiumClass> statiumClasses = statiumClassMapper
					.selectByExample(statiumClassCriteria);
			for (StatiumClass statiumClass : statiumClasses) {
				// 删除课时
				StatiumClassInfoCriteria statiumClassInfoCriteria = new StatiumClassInfoCriteria();
				StatiumClassInfoCriteria.Criteria criteria2 = statiumClassInfoCriteria
						.createCriteria();
				criteria2.andClassIdEqualTo(statiumClass.getId());
				// 删除课程
				this.deleteByPrimaryKey(StatiumClassInfo.class,
						statiumClass.getId());
			}
		} catch (Exception e) {
			logger.debug("删除道馆课程:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <设置推荐功能><功能具体实现>
	 *
	 * @create：2016年8月12日 下午2:32:38
	 * @author：zzq
	 * @param dgId
	 */
	@Transactional(readOnly = false)
	public void setRecommend(String dgId, Integer recommendId) {
		// 获取前台传来的dgId recommendId字段
		StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
		statiumInfosCriteria.createCriteria().andDgIdEqualTo(
				Integer.valueOf(dgId).intValue());
		List<StatiumInfos> statiumInfos = statiumInfosMapper
				.selectByExample(statiumInfosCriteria);
		// 推荐为1 取消推荐为0
		if (statiumInfos != null) {
			if (statiumInfos.size() == 1) {
				if (recommendId == 0) {
					statiumInfos.get(0).setRecommend(0);
				}
				if (recommendId == 1) {
					statiumInfos.get(0).setRecommend(1);
				}

			}
		}
		String id = statiumInfos.get(0).getId();
		StatiumInfos newsStatiumInfos = statiumInfos.get(0);
		try {
			this.updateByPrimaryKeySelective(newsStatiumInfos, id);
		} catch (Exception e) {
			logger.debug("设置推荐道馆", e.getMessage());
		}
		queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, newsStatiumInfos
				.getDgId().toString());

	}
	
	/**
	 * 
	 * <记录审核日志><功能具体实现>
	 *
	 * @create：2016年9月5日 下午2:36:46
	 * @author：zzq
	 * @param statiumAuditLog
	 */
	@Transactional(readOnly=false)
	public void saveAuditLog(StatiumAuditLog statiumAuditLog){
		//保存道馆表的审核状态
		String action = statiumAuditLog.getAction();
		//根据状态存值
		int status =0;
		if(action.equals(Constants.StatiumStatus.STATUS_NEW)){
			status=0;
		}
		if(action.equals(Constants.StatiumStatus.STATUS_SUCCESS)){
			status=1;
		}
		if(action.equals(Constants.StatiumStatus.STATUS_REFUSED)){
			status=2;
		}		
		String statiumId = statiumAuditLog.getStatiumId();
		StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
		statiumInfosCriteria.createCriteria().andDgIdEqualTo(Integer.parseInt(statiumId));
		List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
		//改审核状态
		if(statiumInfos!=null){
			statiumInfos.get(0).setStatus(status);
			try {
				this.updateByPrimaryKeySelective(statiumInfos.get(0), statiumInfos.get(0).getId());
			} catch (Exception e) {
				logger.error("改变审核状态"+e.getMessage());
			}
		}
		//id为空 添加操作 
		if(statiumAuditLog.getId()==null){
			//id
			String id = UUID.get();
			statiumAuditLog.setId(id);
			//cb
			String cb = SessionUtil.currentUserId();
			//ct
			Date   ct = new Date();
			statiumAuditLog.setCb(cb);
			statiumAuditLog.setCt(ct);
			try {
				this.insertSelective(statiumAuditLog, id);
				//statiumAuditLogMapper.insertSelective(statiumAuditLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE,
					String.valueOf(statiumAuditLog.getStatiumId()));
	}
	
	/**
	 * 
	 * <获取审核日志><功能具体实现>
	 *
	 * @create：2016年9月5日 下午3:49:19
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public StatiumAuditLog getauditLog(String id){
		StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
		StatiumInfos statiumInfos;
		try {
			statiumInfos = this.selectByPrimaryKey(StatiumInfos.class, id);
			if(statiumInfos!=null){
				Integer dgId = statiumInfos.getDgId();
				StatiumAuditLogCriteria statiumAuditLogCriteria = new StatiumAuditLogCriteria();
				statiumAuditLogCriteria.createCriteria().andStatiumIdEqualTo(dgId.toString());
				//按照编辑时间排序 时间最近的展示 倒叙展示
				statiumAuditLogCriteria.setOrderByClause("ct desc");
				List<StatiumAuditLog> statAuditLogs = statiumAuditLogMapper.selectByExample(statiumAuditLogCriteria);
				if(statAuditLogs.size()!=0){
					 statiumAuditLog = statAuditLogs.get(0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statiumAuditLog;
	}
	
	/**
	 * 
	 * <审核日志><功能具体实现>
	 *
	 * @create：2016年9月5日 下午6:27:10
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public List<StatiumAuditLog> getAuditLogList(String id){
		List<StatiumAuditLog> auditLogs = new ArrayList<StatiumAuditLog>();
		StatiumInfos statiumInfos = statiumInfosMapper.selectByPrimaryKey(id);
			if(statiumInfos!=null){
				Integer dgId = statiumInfos.getDgId();
				StatiumAuditLogCriteria statiumAuditLogCriteria = new StatiumAuditLogCriteria();
				statiumAuditLogCriteria.createCriteria().andStatiumIdEqualTo(dgId.toString());
				//按照编辑时间排序 时间最近的展示 倒叙展示
				statiumAuditLogCriteria.setOrderByClause("ct desc");
				auditLogs = statiumAuditLogMapper.selectByExample(statiumAuditLogCriteria);
				for(StatiumAuditLog auditLog:auditLogs){
					String cb = auditLog.getCb();
					UserCriteria userCriteria = new UserCriteria();
					userCriteria.createCriteria().andUserIdEqualTo(cb);
					List<User> userList = userMapper.selectByExample(userCriteria);
					if(userList!=null){
						auditLog.setCb(userList.get(0).getNickname());
					}
				}
			}
		return auditLogs;
	}
	
	/**
	 * 
	 * <保存二维码url><功能具体实现>
	 *
	 * @create：2016年10月18日 下午5:00:00
	 * @author：zzq
	 * @param url
	 * @param dgId
	 */
	@Transactional(readOnly=false)
	public String addQrCode(String url,String dgId){
		StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
		statiumInfosCriteria.createCriteria().andDgIdEqualTo(Integer.parseInt(dgId));
		List<StatiumInfos> statiumList = statiumInfosMapper.selectByExample(statiumInfosCriteria);
		if(statiumList!=null){
			StatiumInfos statiumInfos = statiumList.get(0);
			//判断url是否为空 为空才会更新
			if(statiumInfos.getQrCode()==null||"".equals(statiumInfos.getQrCode())){
				statiumInfos.setQrCode(url);
				try {
					this.updateByPrimaryKeySelective(statiumInfos, statiumInfos.getId());
				} catch (Exception e) {
					logger.error("更改二维码url"+e.getMessage());
				}
			}
			
		}
		return statiumList.get(0).getId();
		
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
     * <显示道馆名称><功能具体实现>
     *
     * @create：2017年2月4日 上午10:47:26
     * @author：zzq
     * @param statiumId
     * @return
     */
    public String findDgName(String statiumId){
    	StatiumInfosCriteria s1 = new StatiumInfosCriteria();
    	s1.createCriteria().andDgIdEqualTo(Integer.valueOf(statiumId));
    	List<StatiumInfos> list = statiumInfosMapper.selectByExample(s1);
    	if(CollectionUtils.isNotEmpty(list)){
    		return list.get(0).getDgName();
    	}
    	return "";
    }
	
    /**
     * 
     * <检查crm管理员是否存在><功能具体实现>
     *
     * @create：2016年11月2日 下午4:45:51
     * @author：zzq
     * @param userName
     * @param userId
     * @return
     * @throws Exception
     */
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
    			CrmUser user = crmUserMapper.selectByPrimaryKey(userId);
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
     * <添加管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午4:51:54
     * @author： liangsh
     * @param statiumId
     */
    @Transactional(readOnly = false)
    public void addCrmUser(CrmUser user) {
        if (StringUtils.isBlank(user.getUserId())) {
            // 创建用户时需要加密明文密码，修改用户信息时不修改密码
            entryptPassword(user);
            user.setUserId(UUID.get());
        }
        //nickName为道馆名字
        StatiumInfos statiumInfos = statiumInfosMapper.selectByPrimaryKey(user.getStatiumId());
        if(statiumInfos!=null){
        	user.setNickname(statiumInfos.getDgName());
        }
        try {
			this.insertSelective(user, user.getUserId());
		} catch (Exception e) {
			logger.error("增加一条记录"+e.getMessage());
		}
        
        List<CrmFunction> crmFuncs = crmFunctionMapper.selectByExample(new CrmFunctionCriteria());
        CrmUserFunc uf = null;
        String userId = user.getUserId();
        for (CrmFunction func : crmFuncs) {
            uf = new CrmUserFunc();
            uf.setFuncId(func.getFuncId());
            uf.setUserId(userId);
            crmUserFuncMapper.insertSelective(uf);
        }
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
         * <修改管理员信息><功能具体实现>
         *
         * @create：2015年9月17日 下午4:52:52
         * @author： liangsh
         * @param statiumId
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
        }
        
        /**
         * 
         * <判断有无道馆管理员><功能具体实现>
         *
         * @create：2017年2月4日 下午2:57:35
         * @author：zzq
         * @param dgId
         * @return
         */
        public int isCrmUser(String dgId){
        	logger.debug("statiumId={}", dgId);
            CrmUserCriteria c = new CrmUserCriteria();
            CrmUserCriteria.Criteria cri = c.createCriteria();
            cri.andStatiumIdEqualTo(dgId);
            cri.andDeleteFlagIsNull();
            List<CrmUser> list = crmUserMapper.selectByExample(c);
            if (!CollectionUtils.isEmpty(list)) {
                return 1;
            } else {
                return 0;
            }
        }
        
        /**
         * 
         * <馆长电话是否录入过><功能具体实现>
         *
         * @create：2017年2月4日 下午5:02:06
         * @author：zzq
         * @param tel
         * @return
         */
        public boolean checkTel(String tel,Integer dgId){
        	boolean flag = true;
        	StatiumInfosCriteria s1 = new StatiumInfosCriteria();
        	if(dgId!=null){
        		s1.createCriteria().andDgIdNotEqualTo(dgId);
        	}
        	List<StatiumInfos> list = statiumInfosMapper.selectByExample(s1);
        	for(StatiumInfos s:list){
        		if(tel.equals(s.getTel())){
        			flag = false;
        		}
        	}
        	return flag;
        }
}    
