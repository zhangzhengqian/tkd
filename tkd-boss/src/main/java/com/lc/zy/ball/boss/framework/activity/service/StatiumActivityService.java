/**
 *
 * @create：2016年7月22日 下午4:45:56
 * @author：zzq

 */
package com.lc.zy.ball.boss.framework.activity.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.activity.vo.StatiumActivityVo;
import com.lc.zy.ball.boss.framework.statium.service.StatiumOaInfoService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OaContestActivityMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumActivityMapper;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.po.OaContestActivity;
import com.lc.zy.ball.domain.oa.po.OaContestActivityCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumActivity;
import com.lc.zy.ball.domain.oa.po.StatiumActivityCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

/**
 *
 * @create：2016年7月22日 下午4:45:56
 * @author：zzq

 */
@Service
@Transactional(readOnly = true)
public class StatiumActivityService extends AbstractCacheService{
	private static final Logger logger = LoggerFactory.getLogger(StatiumOaInfoService.class);
    @Autowired
    private StatiumActivityMapper activityMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private OaContestActivityMapper contestActivityMapper;
    
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
    public Page<StatiumActivityVo> find(Map<String, Object> searchParams, int page, int size, boolean isPage,
            boolean isHasCount) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        StatiumActivityCriteria cc = new StatiumActivityCriteria();
        if (isPage) {
            cc.setMysqlOffset(pageable.getOffset());
            cc.setMysqlLength(pageable.getPageSize());
        }
        StatiumActivityCriteria.Criteria cri = cc.createCriteria();

        User currentUser = SessionUtil.currentUser();
        if (!SecurityUtils.getSubject().hasRole(Roles.CUSTOMER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.CUSTOMER_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.SUPPORT_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.ADMIN.getCode())
                && !"root".equals(currentUser.getLoginName())) {
        }

        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        cc.setOrderByClause("ct desc");
        List<StatiumActivity> list = activityMapper.selectByExample(cc);
        ArrayList<StatiumActivityVo> arrayList = new ArrayList<StatiumActivityVo>();
        if (list != null) {
            for (StatiumActivity c : list) {
            	StatiumActivityVo vo = new StatiumActivityVo();
                BeanUtils.copyProperties(c, vo);
                arrayList.add(vo);
            }
        }
        int total = activityMapper.countByExample(cc);
        return new PageImpl<>(arrayList, pageable, total);
    }

	/**
	 * <根据id查找活动实体><功能具体实现>
	 *
	 * @create：2016年7月22日 下午5:33:23
	 * @author：zzq
	 * @param id
	 * @param model
	 * @return
	
	 */
	public StatiumActivity getActivityById(String id) {
		// TODO Auto-generated method stub
		StatiumActivity statiumActivity =new StatiumActivity();
		try {
			statiumActivity = this.selectByPrimaryKey(StatiumActivity.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return statiumActivity;
	}
	
	/**
	 * 创建或更新活动
	 */
	 @Transactional(readOnly = false)
	    public String createOrUpdateActivity(StatiumActivityVo activityVo) throws Exception {
	        String activityId = null;
	        if (activityVo != null) {
	            StatiumActivity activity = new StatiumActivity();
//	            ActivityItem item = null;
	            activityId = activityVo.getId();
	            if (StringUtils.isNotEmpty(activityId)) {
	                // 活动更新
	                BeanUtils.copyProperties(activityVo, activity);
	                // 报名金额设置
	                int free = activityVo.getFree();
	                if (free == 1) {
	                    // 报名金额*100
	                    int fee = activityVo.getPrice();
	                    if(fee!=0){
	                    	activity.setPrice(fee*100);
	                    }
	                } else {
	                    activity.setPrice(0);
	                }
	                //编辑时间
	                activity.setEt(new Date());;
	                //编辑人
	                activity.setEb(SessionUtil.currentUserId());
	                this.updateByPrimaryKeySelective(activity, activity.getId());
	            } else {
	                // 新增活动
	                activityId = UUID.get();
	                Date now = new Date();
	                activity.setId(activityId);
	                activity.setCt(now);
	                activity.setEt(now);
	                // 创建人
	                activity.setCb(SessionUtil.currentUserId());
	                activity.setEb(SessionUtil.currentUserId());
	                // 活动名称
	                activity.setActivityTopic(activityVo.getActivityTopic());
	                // 主办方logo
	                activity.setPhoto(activityVo.getPhoto());
	                // 道馆名称
	                activity.setDgName(activityVo.getDgName());
	                //道馆id
	                activity.setStatiumId(activityVo.getStatiumId());
	                //主办方介绍
	                activity.setHostIntroduce(activityVo.getHostIntroduce());
	                // 活动介绍
	                activity.setActivityIntroduce(activityVo.getActivityIntroduce());
	                // 活动人数
	                activity.setAmount(activityVo.getAmount());
	                //主办方名称
	                activity.setHostName(activityVo.getHostName());
	                // 是否免费
	                int free = activityVo.getFree();
	                if (free == 1) {
	                    // 报名金额*100
	                    int fee = activityVo.getPrice();
	                    activity.setPrice(fee*100);
	                } else {
	                    activity.setPrice(0);
	                }
	                // 活动介绍
	                activity.setActivityIntroduce(activityVo.getActivityIntroduce());

	                // 活动时间
	                activity.setStartTime(activityVo.getStartTime());
	                activity.setEndTime(activityVo.getEndTime());
	                //是否免费
	                activity.setFree(activityVo.getFree());
	                //报名类型
	                activity.setExpiryType(activityVo.getExpiryType());
	                // 设置活动报名截止日期
	                activity.setExpiryData(activityVo.getExpiryData());
	                this.insertSelective(activity, activityId);
	                Date startDate = DateUtils.getDateTime(activityVo.getStartTime());
	                Integer day = DateUtils.intervalDay(DateUtils.getDateTime(activityVo.getEndTime()), startDate);
	                List<Date> startDateList = new ArrayList<>();
	                Calendar calendar = Calendar.getInstance();
	                calendar.setTime(startDate);
	                startDateList.add(startDate);
	                for (int i = 0; i < day; i++) {
	                    calendar.add(Calendar.DAY_OF_YEAR, 1);
	                    startDateList.add(calendar.getTime());
	                }
	            }
	        }
	        return activityId;
	    }
	 
	 /**
	  * @author zzq
	  * @删除功能
	  */
	 @Transactional(readOnly = false)
		public void deleteActivity(String statiumId) {
			try {
				this.deleteByPrimaryKey(StatiumActivity.class, statiumId);
			} catch (Exception e) {
				logger.debug("删除OA场馆:{}", e.getMessage());
			}
		}
	 
	 /**
	  * 
	  * <活动列表展示><功能具体实现>
	  *
	  * @create：2016年8月18日 上午10:31:12
	  * @author：zzq
	  * @param pageRequest
	  * @param searchParams
	  * @return
	  */
	public Page<StatiumActivity> list(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumActivity> activityList = null;
		try {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			StatiumActivityCriteria statiumActivityCriteria = new StatiumActivityCriteria();
			statiumActivityCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumActivityCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumActivityCriteria.Criteria  criteria = statiumActivityCriteria.createCriteria();
			Criterias.bySearchFilter(criteria, filters.values());
			System.out.println(filters.values());
			statiumActivityCriteria.setOrderByClause("ct desc");
			total = activityMapper.countByExample(statiumActivityCriteria);
			activityList = activityMapper.selectByExample(statiumActivityCriteria);
			for(StatiumActivity statiumActivity:activityList){
				Integer oldPrice = statiumActivity.getPrice();
				statiumActivity.setPrice(oldPrice/100);
			}
		} catch (Exception e) {
			//打印异常
			e.printStackTrace();
			logger.debug("获取oa道馆list:{}", e.getMessage());
		}
		return new PageImpl<>(activityList, pageRequest, total);
	}
	
	/**
	 * 
	 * <获取赛事活动列表><功能具体实现>
	 *
	 * @create：2017年2月13日 下午5:40:09
	 * @author：zzq
	 * @return
	 */
	public Page<OaContestActivity> getContestActivityList(PageRequest pageRequest){
		int total = 0;
		List<OaContestActivity> activityList = null;
		try {
			OaContestActivityCriteria c1 = new OaContestActivityCriteria();
			c1.setMysqlLength(pageRequest.getPageSize());
			c1.setMysqlOffset(pageRequest.getOffset());
			OaContestActivityCriteria.Criteria  criteria = c1.createCriteria();
			c1.setOrderByClause("sort asc");
			total = contestActivityMapper.countByExample(c1);
			activityList = contestActivityMapper.selectByExample(c1);
		} catch (Exception e) {
			//打印异常
			e.printStackTrace();
			logger.debug("获取oa道馆list:{}", e.getMessage());
		}
		return new PageImpl<>(activityList, pageRequest, total);
	}
	
	/**
	 * 
	 * <保存竞赛活动><功能具体实现>
	 *
	 * @create：2017年2月14日 下午1:51:00
	 * @author：zzq
	 * @param contest
	 */
	@Transactional(readOnly = false)
	public void saveContest(OaContestActivity contest){
		String userId = SessionUtil.currentUserId();
		Date time = new Date();
		//修改
		if(StringUtils.isNotBlank(contest.getId())){
			contest.setEb(userId);
			contest.setEt(time);
			try {
				this.updateByPrimaryKeySelective(contest, contest.getId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateContest"+e.getMessage());
			}
		}else{
			contest.setId(UUID.get());
			contest.setCt(time);
			contest.setCb(userId);
			contest.setEb(userId);
			contest.setEt(time);
			try {
				this.insertSelective(contest, contest.getId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("insertContest"+e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * <查询竞赛活动><功能具体实现>
	 *
	 * @create：2017年2月14日 下午2:16:10
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public OaContestActivity getContest(String id){
		OaContestActivity contest = new OaContestActivity();
		if(StringUtils.isNotBlank(id)){
			try {
				 contest = this.selectByPrimaryKey(OaContestActivity.class, id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("selectContest"+e.getMessage());
			}
		}
		return contest;
	}
	
	/**
	 * 
	 * <删除竞赛活动><功能具体实现>
	 *
	 * @create：2017年2月14日 下午3:13:56
	 * @author：zzq
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteContest(String id){
		try {
			this.deleteByPrimaryKey(OaContestActivity.class,id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteContest"+e.getMessage());
		}
	}
	
}    