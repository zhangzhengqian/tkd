package com.lc.zy.ball.boss.framework.activity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.BarcodeFactory;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityItemVo;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityMemberVo;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ActivityItemMapper;
import com.lc.zy.ball.domain.oa.mapper.ActivityMapper;
import com.lc.zy.ball.domain.oa.mapper.MemberListMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.ActivityMapperEx;
import com.lc.zy.ball.domain.oa.po.Activity;
import com.lc.zy.ball.domain.oa.po.ActivityCriteria;
import com.lc.zy.ball.domain.oa.po.ActivityItem;
import com.lc.zy.ball.domain.oa.po.ActivityItemCriteria;
import com.lc.zy.ball.domain.oa.po.MemberList;
import com.lc.zy.ball.domain.oa.po.MemberListCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.Constants;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly = true)
public class ActivityService extends AbstractCacheService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private MemberListMapper memberListMapper;

    @Autowired
    private ActivityItemMapper activityItemMapper;

    @Autowired
    private ActivityMapperEx activityMapperEx;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisService redisService;
    
    @Resource(name = "configs")
    private Map<String, String> configs;

    private static Logger logger = LoggerFactory.getLogger(ActivityService.class);

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
    public Page<ActivityVo> find(Map<String, Object> searchParams, int page, int size, boolean isPage,
            boolean isHasCount) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        ActivityCriteria cc = new ActivityCriteria();
        if (isPage) {
            cc.setMysqlOffset(pageable.getOffset());
            cc.setMysqlLength(pageable.getPageSize());
        }
        ActivityCriteria.Criteria cri = cc.createCriteria();

        User currentUser = SessionUtil.currentUser();
        if (!SecurityUtils.getSubject().hasRole(Roles.CUSTOMER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.CUSTOMER_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.SUPPORT_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.ADMIN.getCode())
                && !"root".equals(currentUser.getLoginName())) {
            // 设置默认组织条件
            setDefaultOrgCode(searchParams);
        }

        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }

        cri.andDeleteFlagEqualTo(0);
        cc.setOrderByClause("stick,create_time desc");
        List<Activity> list = activityMapper.selectByExample(cc);
        ArrayList<ActivityVo> arrayList = new ArrayList<ActivityVo>();
        if (list != null) {
            for (Activity c : list) {
                ActivityVo vo = new ActivityVo();
                BeanUtils.copyProperties(c, vo);
                if (CommonUtils.isNotEmpty(vo.getUserid())) {
                    User createUser = selectByPrimaryKey(User.class, vo.getUserid());
                    vo.setCreateUserName(createUser != null ? createUser.getNickname() : "");
                }
                arrayList.add(vo);
            }
        }
        int total = activityMapper.countByExample(cc);
        return new PageImpl<>(arrayList, pageable, total);
    }

    /**
     * 当 orgCode 为空时，设置成当前登陆人的 组织编码
     * 
     * @param searchParams
     */
    public void setDefaultOrgCode(Map<String, Object> searchParams) {
        String k = "EQ_orgCode";
        if (searchParams.get(k) == null || "".equals(searchParams.get(k).toString())) {
            String orgCode = SessionUtil.currentUser().getOrgCode();
            searchParams.put(k, orgCode);
        }
    }

    /**
     * <获取活动信息><功能具体实现>
     * 
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午8:16:46
     */
    public Activity getActivity(String activityId) {
        try {
            return this.selectByPrimaryKey(Activity.class, activityId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询活动失败");
        }
        return null;
    }

    /**
     * 根据主键查询activityItem
     * 
     * @param activityId
     * @return
     * @throws Exception
     * @author yankefei
     */
    public ActivityItem getActivityItem(String activityItemId) throws Exception {
        return this.selectByPrimaryKey(ActivityItem.class, activityItemId);
    }

    /**
     * 插入或者更新对象.
     * 
     * @param String coachId.
     * @return 活动.
     * @throws Exception
     */
    public String insertOrUpdateActivity(Activity activity) throws Exception {
        String id = "";
        if (CommonUtils.isNotEmpty(activity.getId())) {// update
            id = activity.getId();
            this.updateByPrimaryKeySelective(activity, id);
            return id;
        } else {// insert
            id = UUID.get();
            activity.setId(id);
            this.insertSelective(activity, id);
            return id;
        }
    }

    /**
     * 删除活动
     * 
     * @param id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void deleteActivity(String id) throws Exception {
        Activity updateActivity = new Activity();
        updateActivity.setDeleteFlag(1);
        updateActivity.setId(id);
        this.insertOrUpdateActivity(updateActivity);
    }

    /**
     * <创建或更新活动><功能具体实现>
     * 
     * @param activityVo
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午11:42:47
     */
    @Transactional(readOnly = false)
    public String createOrUpdateActivity(ActivityVo activityVo) throws Exception {
        String activityId = null;
        User user = SessionUtil.currentUser();
        if (activityVo != null) {
            Activity activity = new Activity();
            ActivityItem item = null;
            activityId = activityVo.getId();
            if (StringUtils.isNotEmpty(activityId)) {
                // 活动更新
                BeanUtils.copyProperties(activityVo, activity);

                // 报名金额设置
                int free = activityVo.getFree();
                if (free == 1) {
                    // 报名金额*100
                    Double fee = activityVo.getPrice();
                    if(fee!=null){
                    	BigDecimal bigDecimal = new BigDecimal(fee);
                    	BigDecimal bigDecimal2 = new BigDecimal(100);
                    	activity.setMoney(bigDecimal.multiply(bigDecimal2).intValue());
                    }
                } else {
                    activity.setMoney(0);
                }

                // 设置活动地点
                String areaCode = activityVo.getAreaCode();
                Map<String, String> areaMap = Zonemap.split(areaCode);
                activity.setProvince(areaMap.get("province"));
                activity.setCity(areaMap.get("city"));
                activity.setArea(areaMap.get("area"));

                // 经纬度设置
                String lnglat = activityVo.getLnglat();
                if (StringUtils.isNotEmpty(lnglat)) {
                    activity.setLng(Double.valueOf(lnglat.split(",")[0]));
                    activity.setLat(Double.valueOf(lnglat.split(",")[1]));
                }
                activity.setUpdateTime(new Date());
//                String url = BarcodeFactory.encode(configs.get("weixinDomain")+"WXGZPT/activity/activeDetail.html?id="+activity.getId(),300, 300, activity.getLogo());
//                activity.setQrUrl(url);
                this.updateByPrimaryKeySelective(activity, activity.getId());
            } else {
                // 新增活动
                activityId = UUID.get();
                Date now = new Date();
                activity.setId(activityId);
                activity.setCreateTime(now);
                activity.setUpdateTime(now);
                activity.setOrgCode(user.getOrgCode());
                // 创建人
                activity.setUserid(SessionUtil.currentUserId());

                // 活动名称
                activity.setName(activityVo.getName());
                // 是否重复报名
                activity.setIsRepeat(activityVo.getIsRepeat());
                // 主办方logo
                activity.setLogo(activityVo.getLogo());
                // 主办方名称
                activity.setUndertake(activityVo.getUndertake());
                // 联系电话
                activity.setTel(activityVo.getTel());
                // 主办方介绍
                activity.setIntroduction(activityVo.getIntroduction());
                // 海报
                activity.setPhoto(activityVo.getPhoto());
                // 活动人数
                activity.setTotalNumber(activityVo.getTotalNumber());
                // 活动类型
                activity.setType(activityVo.getType());
                // 是否免费
                int free = activityVo.getFree();
                activity.setFree(free);
                if (free == 1) {
                    // 报名金额*100
                    Double fee = activityVo.getPrice();
                    BigDecimal bigDecimal = new BigDecimal(fee);
                    BigDecimal bigDecimal2 = new BigDecimal(100);
                    activity.setMoney(bigDecimal.multiply(bigDecimal2).intValue());
                } else {
                    activity.setMoney(0);
                }
                // 活动介绍
                activity.setBriefdesc(activityVo.getBriefdesc());

                // 活动时间
                activity.setStartTime(activityVo.getStartTime());
                activity.setEndTime(activityVo.getEndTime());

                // 设置活动报名截止日期
                activity.setExpiryType(activityVo.getExpiryType());
                activity.setExpiryDate(activityVo.getExpiryDate());

                // 设置活动地点
                String areaCode = activityVo.getAreaCode();
                activity.setAreaCode(areaCode);
                Map<String, String> areaMap = Zonemap.split(areaCode);
                activity.setProvince(areaMap.get("province"));
                activity.setCity(areaMap.get("city"));
                activity.setArea(areaMap.get("area"));
                activity.setAddress(activityVo.getAddress());

                // 设置排序值stick
                int stick = getMaxStick();
                activity.setStick(stick + 1);

                // 设置经纬度
                String lnglat = activityVo.getLnglat();
                activity.setLng(Double.valueOf(lnglat.split(",")[0]));
                activity.setLat(Double.valueOf(lnglat.split(",")[1]));
                
                // 活动推送 add by sl 2015-12-15
                activity.setIsPush(activityVo.getIsPush());
                activity.setPushTime(activityVo.getPushTime());
                if (activityVo.getIsPush() == 0) {
                	activity.setPushState(1);
                } else if(activityVo.getIsPush() == 1){
                	activity.setPushState(0);
                }
                logger.debug("二维码开始={}",SessionUtil.currentUser().getUserId());
//                String url = BarcodeFactory.encode(configs.get("weixinDomain")+"WXGZPT/activity/activeDetail.html?id="+activity.getId(),300, 300, activity.getLogo());
//                activity.setQrUrl(url);
                logger.debug("二维码结束={}",SessionUtil.currentUser().getUserId());
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
                String endTime = DateUtils.formatTime(DateUtils.getDateTime(activityVo.getEndTime()));
                for (int i = 0; i < startDateList.size(); i++) {
                    item = new ActivityItem();
                    item.setActivityId(activityId);
                    item.setStartDate(startDateList.get(i));
                    item.setEndDate(DateUtils.getDateTime(DateUtils.formatDate(startDateList.get(i)) + " " + endTime));
                    item.setCt(now);
                    item.setEt(now);
                    item.setId(UUID.get());
                    item.setState(0); // 默认0可用
                    activityItemMapper.insertSelective(item);
                }
            }
        }
        return activityId;
    }

    /**
     * <获取活动vo详情><功能具体实现>
     * 
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月20日 上午10:34:22
     */
    public ActivityVo getActivityVo(String activityId) {
        ActivityVo activityVo = new ActivityVo();
        try {
            Activity activity = this.getActivity(activityId);
            if (activity == null) {
                logger.error("未查询到活动activityId={}", activityId);
                return null;
            } else {
                BeanUtils.copyProperties(activity, activityVo);
                // 报名金额设置
                int free = activity.getFree();
                if (free == 1) {
                    // 报名金额*100
                    int money = activity.getMoney();
                    BigDecimal bigDecimal = new BigDecimal(money);
                    BigDecimal bigDecimal2 = new BigDecimal(100);
                    activityVo.setPrice(bigDecimal.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                } else {
                    activityVo.setPrice(0.00);
                }
                ActivityItemCriteria activityItemCriteria = new ActivityItemCriteria();
                ActivityItemCriteria.Criteria cri = activityItemCriteria.createCriteria();
                cri.andActivityIdEqualTo(activityId);
                cri.andStateEqualTo(0);
                List<ActivityItem> activityItemList = activityItemMapper.selectByExample(activityItemCriteria);

                // 获取活动的已报名用户
                if (!CollectionUtils.isEmpty(activityItemList)) {
                    List<String> itemIdList = new ArrayList<>();
                    for (ActivityItem item : activityItemList) {
                        itemIdList.add(item.getId());
                    }
                    MemberListCriteria memberListCriteria = new MemberListCriteria();
                    MemberListCriteria.Criteria cri2 = memberListCriteria.createCriteria();
                    cri2.andAidIn(itemIdList);
                    List<Integer> states = new ArrayList<Integer>();
                    states.add(1);
                    states.add(2);
                    cri2.andStateIn(states);// 未审核和已审核通过的
                    activityVo.setRegistrationNumber(memberListMapper.countByExample(memberListCriteria));
                } else {
                    activityVo.setRegistrationNumber(0);
                }
            }
        } catch (BeansException e) {
            e.printStackTrace();
            logger.error("查询活动信息异常={}", e.getMessage());
        }
        return activityVo;
    }

    /**
     * 
     * <活动开始时间><功能具体实现>
     *
     * @create：2015年10月13日 下午3:45:07
     * @author： CYY
     * @param id
     * @param date
     * @return
     * @throws Exception
     */
    public String getDate(String id) throws Exception {
        if (CommonUtils.isNotEmpty(id)) {
            Activity activity = this.selectByPrimaryKey(Activity.class, id);
            return activity.getStartTime();
        } else {
            throw new RuntimeException("活动id不能为空！");
        }
    }

    /**
     * <activityItem成员列表><功能具体实现>
     * 
     * @param activityItemId
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午10:36:06
     */
    public List<ActivityMemberVo> itemUserList(String activityItemId) throws Exception {
        List<ActivityMemberVo> itemMemberList = new ArrayList<ActivityMemberVo>();
        if (CommonUtils.isNotEmpty(activityItemId)) {
            MemberListCriteria memberListCriteria = new MemberListCriteria();
            MemberListCriteria.Criteria cri = memberListCriteria.createCriteria();
            cri.andAidEqualTo(activityItemId);
            List<MemberList> memberList = memberListMapper.selectByExample(memberListCriteria);
            if (CollectionUtils.isNotEmpty(memberList)) {
                ActivityMemberVo activityMember = null;
                SsoUser user = null;
                for (MemberList menber : memberList) {
                    user = this.selectByPrimaryKey(SsoUser.class, menber.getUid());
                    activityMember = new ActivityMemberVo();
                    BeanUtils.copyProperties(user, activityMember);
                    activityMember.setRegistrationTime(menber.getCreateTime());
                    activityMember.setState(menber.getState());
                    activityMember.setOrderId(menber.getOrderid());
                    if (user != null) {
                        itemMemberList.add(activityMember);
                    }
                }
            }
            return itemMemberList;
        } else {
            throw new RuntimeException("activityItemId不能为空！");
        }
    }

    /**
     * 
     * <活动item列表><功能具体实现>
     * 
     * @param activityId
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年10月15日 上午10:43:50
     */
    public List<ActivityItemVo> getActivityItemList(String activityId) throws Exception {
        if (CommonUtils.isNotEmpty(activityId)) {
            ActivityItemCriteria activityItemCriteria = new ActivityItemCriteria();
            ActivityItemCriteria.Criteria cri = activityItemCriteria.createCriteria();
            cri.andActivityIdEqualTo(activityId);
            cri.andStateEqualTo(0); // 0为可用，1为不可用
            activityItemCriteria.setOrderByClause("start_date asc");
            List<ActivityItem> activityItemList = activityItemMapper.selectByExample(activityItemCriteria);
            List<ActivityItemVo> activityItemVoList = new ArrayList<ActivityItemVo>();
            if (CollectionUtils.isNotEmpty(activityItemList)) {
                ActivityItemVo activityItemVo = null;
                for (ActivityItem activityItem : activityItemList) {
                    activityItemVo = new ActivityItemVo();
                    activityItemVo.setId(activityItem.getId());
                    activityItemVo.setState(activityItem.getState());
                    activityItemVo.setDate(DateUtils.formatDate(activityItem.getStartDate()));
                    MemberListCriteria memberListCriteria = new MemberListCriteria();
                    MemberListCriteria.Criteria cri1 = memberListCriteria.createCriteria();
                    cri1.andAidEqualTo(activityItem.getId());
                    cri1.andStateNotEqualTo(2);
                    activityItemVo.setNumber(memberListMapper.countByExample(memberListCriteria));
                    activityItemVoList.add(activityItemVo);
                }
            }
            return activityItemVoList;
        } else {
            throw new RuntimeException("活动id不能为空！");
        }
    }

    /**
     * <报名用户审核><功能具体实现>
     * 
     * @param userId
     * @param activityItemId
     * @param state
     * @throws Exception
     * @author yankefei
     * @date 2015年10月16日 下午3:23:28
     */
    @Transactional(readOnly = false)
    public void updateState(String userId, String activityItemId, Integer state) {
        try {
            MemberListCriteria memberListCriteria = new MemberListCriteria();
            MemberListCriteria.Criteria cri = memberListCriteria.createCriteria();
            cri.andUidEqualTo(userId);
            cri.andAidEqualTo(activityItemId);
            List<MemberList> memberList = memberListMapper.selectByExample(memberListCriteria);
            if (CollectionUtils.isNotEmpty(memberList)) {
                MemberList member = memberList.get(0);
                member.setState(state);
                member.setUpdateTime(new Date());
                this.updateByPrimaryKey(member, member.getId());

                String orderId = member.getOrderid();
                Order order = this.selectByPrimaryKey(Order.class, orderId);
                if (state == 2) {
                    // 审核通过，修改订单状态为"已确认"
                    if (order.getStatus().equals(Constants.OrderStatus.PAIED)) {
                        order.setStatus(Constants.OrderStatus.VERIFY);
                        this.updateByPrimaryKeySelective(order, orderId);
                    }
                } else if (state == 3) {
                    // 审核未通过，修改订单状态为"退款中"
                    if (order.getStatus().equals(Constants.OrderStatus.PAIED)
                            || order.getStatus().equals(Constants.OrderStatus.VERIFY)) {
                    	String activityId = order.getStatiumId();
                    	Activity act = this.selectByPrimaryKey(Activity.class, activityId);
                    	if(act.getFree()==0||order.getFinalFee()==0){
                    		order.setStatus(Constants.OrderStatus.REFUNDED);
                    	}else{
                    		order.setStatus(Constants.OrderStatus.REFUNDING);
                    	}
                        this.updateByPrimaryKeySelective(order, orderId);
                    }
                }
            } else {
                logger.warn("根据userId和activityItemId未获取到用户信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新用户审核状态异常={}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <获取用户报名记录><功能具体实现>
     * 
     * @param userId
     * @param activityItemId
     * @return
     * @author yankefei
     * @date 2015年10月15日 下午6:20:31
     */
    public MemberList findActivityMember(String userId, String activityItemId) {
        try {
            MemberListCriteria memberListCriteria = new MemberListCriteria();
            MemberListCriteria.Criteria criteria = memberListCriteria.createCriteria();
            criteria.andUidEqualTo(userId);
            criteria.andAidEqualTo(activityItemId);
            List<MemberList> list = memberListMapper.selectByExample(memberListCriteria);
            if (!CollectionUtils.isEmpty(list)) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <获取活动stick最大值><功能具体实现>
     * 
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午2:34:53
     */
    public int getMaxStick() {
        try {
            ActivityCriteria activityCriteria = new ActivityCriteria();
            ActivityCriteria.Criteria criteria = activityCriteria.createCriteria();
            criteria.andDeleteFlagEqualTo(0);
            Integer maxStick = activityMapperEx.findMaxStick(activityCriteria);
            if (maxStick == null) {
                return -1;
            } else {
                return maxStick;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取stick最大值异常");
        }
        return -1;
    }

    /**
     * <根据stick值获取活动信息><功能具体实现>
     * 
     * @param stick
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午5:04:48
     */
    public Activity getActivityByStick(int stick) {
        try {
            ActivityCriteria activityCriteria = new ActivityCriteria();
            ActivityCriteria.Criteria criteria = activityCriteria.createCriteria();
            criteria.andDeleteFlagEqualTo(0);
            criteria.andStickEqualTo(stick);
            List<Activity> list = activityMapper.selectByExample(activityCriteria);
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else if (list.size() > 1) {
                logger.warn("根据stick值获取有效活动为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取stick最大值异常");
        }
        return null;
    }

    /**
     * <置底><功能具体实现>
     * 
     * @param activityId
     * @author yankefei
     * @date 2015年10月16日 下午5:11:03
     */
    @Transactional(readOnly = false)
    public void unStick(String activityId) {
        try {
            ActivityVo activityVo = getActivityVo(activityId);
            ActivityCriteria activityCriteria = new ActivityCriteria();
            ActivityCriteria.Criteria criteria = activityCriteria.createCriteria();
            criteria.andDeleteFlagEqualTo(0);
            criteria.andStickGreaterThan(0);
            activityMapperEx.stickSubOne(activityCriteria);

            int maxStick = getMaxStick();
            activityVo.setStick(maxStick + 1);
            createOrUpdateActivity(activityVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("置底操作异常");
            throw new RuntimeException(e);
        }
    }

    /**
     * <置顶><功能具体实现>
     * 
     * @param activityId
     * @author yankefei
     * @date 2015年10月16日 下午5:11:43
     */
    @Transactional(readOnly = false)
    public void stick(String activityId) {
        try {
            ActivityVo activityVo = getActivityVo(activityId);
            ActivityCriteria activityCriteria = new ActivityCriteria();
            ActivityCriteria.Criteria criteria = activityCriteria.createCriteria();
            criteria.andDeleteFlagEqualTo(0);
            criteria.andStickLessThan(activityVo.getStick());
            activityMapperEx.stickPlusOne(activityCriteria);

            activityVo.setStick(0);
            createOrUpdateActivity(activityVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("置顶操作异常");
            throw new RuntimeException(e);
        }
    }

    /**
     * <上移操作><功能具体实现>
     * 
     * @param activityId
     * @author yankefei
     * @date 2015年10月16日 下午5:25:33
     */
    @Transactional(readOnly = false)
    public void moveUp(String activityId) {
        try {
            ActivityVo activityVo = getActivityVo(activityId);
            int oldStick = activityVo.getStick();
            Activity activityUp = getActivityByStick(oldStick - 1);
            ActivityVo activityUpVo = new ActivityVo();

            if (activityUp == null) {
                logger.info("未查询到上一个活动");
            } else {
                BeanUtils.copyProperties(activityUp, activityUpVo);
                activityVo.setStick(oldStick - 1);
                activityUpVo.setStick(oldStick);

                createOrUpdateActivity(activityVo);
                createOrUpdateActivity(activityUpVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <下移操作><功能具体实现>
     * 
     * @param activityId
     * @author yankefei
     * @date 2015年10月16日 下午5:29:32
     */
    @Transactional(readOnly = false)
    public void moveDown(String activityId) {
        try {
            ActivityVo activityVo = getActivityVo(activityId);
            int oldStick = activityVo.getStick();
            Activity activityDown = getActivityByStick(oldStick + 1);
            ActivityVo activityDownVo = new ActivityVo();

            if (activityDown == null) {
                logger.info("此活动已是最后一个，不做下移操作");
            } else {
                // BeanUtils.copyProperties(activityDown, activityDownVo);
                activityVo.setStick(oldStick + 1);
                activityDownVo = getActivityVo(activityDown.getId());
                activityDownVo.setStick(oldStick);

                createOrUpdateActivity(activityVo);
                createOrUpdateActivity(activityDownVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <活动item状态更新><功能具体实现>
     * 
     * @param activityItemId
     * @author yankefei
     * @date 2015年10月16日 下午7:18:03
     */
    @Transactional(readOnly = false)
    public boolean updateItemState(String activityItemId) {
        try {
            ActivityItem item = activityItemMapper.selectByPrimaryKey(activityItemId);
            int state = item.getState();
            if (state == 0) {
                item.setState(1);
            } else {
                item.setState(0);
            }
            activityItemMapper.updateByPrimaryKey(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新活动item状态异常={}", e.getMessage());
        }
        return false;
    }

    /**
     * <获取所有活动Item><功能具体实现>
     * 
     * @param activityId
     * @return
     * @author yankefei
     * @date 2015年10月16日 下午7:44:54
     */
    public List<ActivityItem> getAllActivityItemList(String activityId) {
        List<ActivityItem> activityItemList = null;
        try {
            ActivityItemCriteria activityItemCriteria = new ActivityItemCriteria();
            ActivityItemCriteria.Criteria cri = activityItemCriteria.createCriteria();
            cri.andActivityIdEqualTo(activityId);
            activityItemCriteria.setOrderByClause("date asc");
            activityItemList = activityItemMapper.selectByExample(activityItemCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityItemList;
    }
    
    /**
     * <活动统计>
     * 
     * @param searchParams 查询条件.
     * @param page 分页页号, 基于0.
     * @param size 分页大小.
     * @param isPage 是否分页显示.
     * @return 分页数据.
     * @throws Exception
     */
    public Page<ActivityVo> activityStatisticsList(Map<String, Object> searchParams, int page, int size,boolean isPage) throws Exception {
        PageRequest pageable = new PageRequest(page, size);
        ActivityCriteria cc = new ActivityCriteria();
        if (isPage) {
            cc.setMysqlOffset(pageable.getOffset());
            cc.setMysqlLength(pageable.getPageSize());
        }
        ActivityCriteria.Criteria cri = cc.createCriteria();

        User currentUser = SessionUtil.currentUser();
        if (!SecurityUtils.getSubject().hasRole(Roles.CUSTOMER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.CUSTOMER_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.SUPPORT_MANAGER.getCode())
                && !SecurityUtils.getSubject().hasRole(Roles.ADMIN.getCode())
                && !"root".equals(currentUser.getLoginName())) {
            // 设置默认组织条件
            setDefaultOrgCode(searchParams);
        }

        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }

        cri.andDeleteFlagEqualTo(0);
        cc.setOrderByClause("stick,create_time desc");
        List<Activity> list = activityMapper.selectByExample(cc);
        ArrayList<ActivityVo> arrayList = new ArrayList<ActivityVo>();
        ActivityItemCriteria activityItemCriteria;
        ActivityItemCriteria.Criteria cri1;
        for (Activity c : list) {
            ActivityVo vo = new ActivityVo();
            BeanUtils.copyProperties(c, vo);
            if (CommonUtils.isNotEmpty(vo.getUserid())) {
                User createUser = selectByPrimaryKey(User.class, vo.getUserid());
                vo.setCreateUserName(createUser != null ? createUser.getNickname() : "");
            }
            activityItemCriteria = new ActivityItemCriteria();
            cri1 = activityItemCriteria.createCriteria();
            cri1.andActivityIdEqualTo(c.getId());
            cri1.andStateEqualTo(0);
            List<ActivityItem> activityItemList = activityItemMapper.selectByExample(activityItemCriteria);

            // 获取活动的已报名用户
            if (!CollectionUtils.isEmpty(activityItemList)) {
                List<String> itemIdList = new ArrayList<>();
                for (ActivityItem item : activityItemList) {
                    itemIdList.add(item.getId());
                }
                MemberListCriteria memberListCriteria = new MemberListCriteria();
                MemberListCriteria.Criteria cri2 = memberListCriteria.createCriteria();
                cri2.andAidIn(itemIdList);
                cri2.andStateNotEqualTo(2);// 未审核和已审核通过的
                vo.setRegistrationNumber(memberListMapper.countByExample(memberListCriteria));
            } else {
                vo.setRegistrationNumber(0);
            }
            arrayList.add(vo);
        }
        int total = activityMapper.countByExample(cc);
        return new PageImpl<>(arrayList, pageable, total);
    }
    
    /**
     * 
     * <修改统计><功能具体实现>
     *
     * @create：2015年12月7日 下午4:32:33
     * @author： CYY
     * @param vo
     * @return
     */
    @Transactional(readOnly = false)
    public boolean updateActivityStatistics(ActivityVo vo) {
        try {
            Activity activity = this.selectByPrimaryKey(Activity.class, vo.getId());
            if (vo.getCostPrice() != null) {
                activity.setCostPrice(vo.getCostPrice() * 100);
            }
            if (vo.getExpenditure() != null) {
                activity.setExpenditure(vo.getExpenditure() * 100);
            }
            if (vo.getProfit() != null) {
                activity.setProfit(vo.getProfit() * 100);
            }
            activity.setRemark(vo.getRemark());
            activity.setUpdateTime(new Date());
            User user = SessionUtil.currentUser();
            activity.setEb(user.getUserId());
            this.updateByPrimaryKeySelective(activity, activity.getId());
            return true;
        } catch (Exception e) {
            logger.error("updateActivityStatistics={}", e.getMessage());
        }
        return false;
    }
}
