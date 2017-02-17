package com.lc.zy.ball.crm.framework.system.cardPay.service;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.CardPayVo;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.ClassVo;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.UserAccountVo;
import com.lc.zy.ball.domain.oa.mapper.CrmUserCardAccountMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMemberMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class CardPayManageService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CardPayManageService.class);

    @Autowired
    private SsoUserMapper ssoUserMapper = null;

    @Resource(name="oaJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderItemMapper orderItemMapper = null;

    @Autowired
    private StatiumClassMemberMapper statiumClassMemberMapper = null;

    @Autowired
    private CrmUserCardAccountMapper crmUserCardAccountMapper = null;

    @Autowired
    private SEQGenerate seqGenerate = null;

    /**
     *
     * <获取app支付待签到订单><功能具体实现>
     *
     * @create：2016/12/21 上午11:50
     * @author：sl
     * @param searchParams
     * @param page
     * @param size
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardPay.vo.CardPayVo>
     */
    public Page<CardPayVo> findOrder(Map<String, Object> searchParams, int page, int size) {
        logger.debug(searchParams.toString());
        int total = 0;
        List<CardPayVo> orderList = new ArrayList<CardPayVo>();
        PageRequest pageable = new PageRequest(page, size);
        try {
            // 获取道馆id
            CrmUser user = SessionUtil.currentUser();
            int statiumId = Integer.valueOf(user.getStatiumId());
            // 查询参数
            Map<String, Object> paras = new HashMap<String, Object>();
            // 道馆id
            paras.put("statiumId", statiumId);
            // 用户手机号查询
            if (searchParams.get("LIKE_phone") != null && CommonUtils.isNotEmpty((String) searchParams.get("LIKE_phone"))) {
                String phone = (String) searchParams.get("LIKE_phone");
                // 根据手机号获取用户信息
                SsoUserCriteria criteria = new SsoUserCriteria();
                SsoUserCriteria.Criteria c = criteria.createCriteria();
                c.andPhoneLike("%" + phone + "%");
                List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
                if (users.size() == 0) {
                    return new PageImpl<>(new ArrayList<CardPayVo>(), pageable, 0);
                } else {
                    if(users.size()>1){
                        int num = users.size();
                        String[] userIds = new String[num];
                        for (int i =0; i < num; i++) {
                            userIds[i] = users.get(i).getId();
                        }
                        paras.put("userIdIn", userIds);
                    }else{
                        paras.put("userId", users.get(0).getId());
                    }
                }
            }
            // 订单状态
            String orderStatus [] = {Constants.OrderStatus.PAIED, Constants.OrderStatus.SIGN};
            paras.put("statusIn", orderStatus);
            // 订单ID
            if (searchParams.get("LIKE_id") != null && CommonUtils.isNotEmpty((String) searchParams.get("LIKE_id"))) {
                paras.put("orderId", (String) searchParams.get("LIKE_id"));
            }
            //订单渠道
            paras.put("order_type", Constants.BookType.APP);
            // 获取订单数量
            String sqlCount = FreeMarkerUtils.format("/template/cardPay/order_sign_count.ftl", paras);
            logger.debug(sqlCount);
            logger.debug(paras.toString());
            if(pageable.getOffset()>= 1000){
                paras.put("offset", String.valueOf(pageable.getOffset()).replace(",",""));
            }else{
                paras.put("offset", pageable.getOffset());
            }
            paras.put("pageSize", pageable.getPageSize());
            List<Map<String,Object>> countMap = jdbcTemplate.queryForList(sqlCount);
            long total_ = (long)countMap.get(0).get("cont");
            total = (int)total_;
            // 获取订单信息
            String sqlList = FreeMarkerUtils.format("/template/cardPay/order_sign.ftl", paras);
            logger.debug(sqlList);
            logger.debug(paras.toString());
            List<Map<String,Object>> orders = jdbcTemplate.queryForList(sqlList);
            CardPayVo vo = null;
            if (!orders.isEmpty()) {
                for (Map<String, Object> order : orders) {
                    vo = new CardPayVo();
                    Date ct = (Date)order.get("ct");
                    Date et = (Date)order.get("et");
                    String orderId = (String)order.get("id");
                    String status = (String)order.get("status");
                    // 订单ID
                    vo.setId(orderId);
                    // 下单日期
                    vo.setCt(ct);
                    // 订单完成时间
                    vo.setEt(et);
                    // 订单状态
                    vo.setStatus(status);
                    // 订单金额
                    if (order.get("finalFee") != null) {
                        Double finel = BigDecimal.valueOf((Integer)order.get("finalFee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
                        vo.setFinalFee((Integer)order.get("finalFee"));
                    }
                    if (order.get("fee") != null) {
                        Double finel = BigDecimal.valueOf((Integer)order.get("fee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
                        vo.setFee((Integer)order.get("fee"));
                    }
                    // 订单类型
                    Integer ordersType = (Integer) order.get("ordersType");
                    vo.setOrdersType(ordersType);
                    // 用户信息
                    if (order.get("userId") != null) {
                        String userId = (String) order.get("userId");
                        vo.setUserId(userId);
                    }

                    // 获取订单详情
                    orderList.add(packItem(vo));
                }
            } else {
                return new PageImpl<>(new ArrayList<CardPayVo>(), pageable, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("获取app支付待签到订单 {}", e.getMessage());
        }

        return new PageImpl<>(orderList, pageable, total);
    }

    /**
     *
     * <封装item><功能具体实现>
     *
     * @create：2016/12/21 下午5:39
     * @author：sl
     * @param vo
     * @return com.lc.zy.ball.crm.framework.system.cardPay.vo.CardPayVo
     */
    public CardPayVo packItem(CardPayVo vo) {
        try {
            // 获取用户信息
            SsoUser ssoUser = null;
            try {
                ssoUser = this.selectByPrimaryKey(SsoUser.class, vo.getUserId());
            } catch (Exception e) {
                logger.debug("获取用户信息失败 {}", e.getMessage());
            }
            if (ssoUser != null) {
                String phone = ssoUser.getPhone();
                vo.setPhone(phone);
                if (ssoUser.getUsername() != null){
                    vo.setName(ssoUser.getUsername());
                } else {
                    vo.setName(ssoUser.getNickName());
                }
            }
            // 根据订单号获取item
            OrderItemCriteria criteria = new OrderItemCriteria();
            OrderItemCriteria.Criteria cri = criteria.createCriteria();
            cri.andOrderIdEqualTo(vo.getId());
            List<OrderItem> items = orderItemMapper.selectByExample(criteria);
            for (OrderItem item : items) {
                if (vo.getOrdersType() == Constants.OrdersType.CLASS) {
                    if (item.getSignDate() != null) {
                        vo.setSignDate(item.getSignDate());
                    }
                    if (item.getStartTime() != null) {
                        vo.setStartTime(item.getStartTime());
                    }
                    if (item.getEndTime() !=null) {
                        vo.setEndTime(item.getEndTime());
                    }

                    // 获取课程信息
                    try {
                        StatiumClass statiumClass = this.selectByPrimaryKey(StatiumClass.class, item.getClassId());
                        // 课程名称
                        vo.setOrderName(statiumClass.getClassTitle());
                    } catch (Exception e) {
                        logger.debug("获取订单课程名称 {}", e.getMessage());
                    }
                    // 获取教练名称
                    try {
                        Coach coach = this.selectByPrimaryKey(Coach.class, item.getCoachId());
                        // 教练名称
                        vo.setCoachName(coach.getName());
                    } catch (Exception e) {
                        logger.debug("获取订单教练名称 {}", e.getMessage());
                    }
                } else if (vo.getOrdersType() == Constants.OrdersType.ACTIVITY) {
                    try {
                        StatiumActivity activity = this.selectByPrimaryKey(StatiumActivity.class, item.getActivityId());
                        // 活动名称
                        vo.setOrderName(activity.getActivityTopic());
                        // 开始日期
                        vo.setAsTime(DateUtils.getDate(activity.getStartTime(), "yyyy-MM-dd HH:mm"));
                        // 结束日期
                        vo.setAeTime(DateUtils.getDate(activity.getEndTime(), "yyyy-MM-dd HH:mm"));
                    } catch (Exception e) {
                        logger.debug("获取订单活动名称 {}", e.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            logger.debug("封装item {}", e.getMessage());
        }
        return vo;
    }

    /**
     *
     * <根据订单id获取订单详情><功能具体实现>
     *
     * @create：2016/12/21 下午5:40
     * @author：sl
     * @param orderId
     * @return com.lc.zy.ball.crm.framework.system.cardPay.vo.CardPayVo
     */
    public CardPayVo orderByOrderId(String orderId) {
        CardPayVo vo = new CardPayVo();
        try {
            // 根据订单号获取订单信息
            Order order = this.selectByPrimaryKey(Order.class, orderId);
            BeanUtils.copyProperties(order, vo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("根据订单id获取订单详情 {}", e.getMessage());
        }
        return packItem(vo);
    }

    /**
     *
     * <app订单签到><功能具体实现>
     *
     * @create：2016/12/22 下午2:06
     * @author：sl
     * @param orderId
     * @return void
     */
    @Transactional(readOnly = false)
    public void confirmOrder(String orderId) throws Exception{
        try {
            // 更新app订单签到状态
            confirmOrderStatus(orderId);
            // 记录订单日志
            orderLog(orderId);
        } catch (Exception e) {
            logger.debug("app订单签到 {}", e.getMessage());
            throw new RuntimeException("签到失败");
        }
    }

    /**
     *
     * <更新app订单签到状态><功能具体实现>
     *
     * @create：2016/12/22 下午1:49
     * @author：sl
     * @param orderId
     * @return void
     */
    @Transactional(readOnly = false)
    public void confirmOrderStatus(String orderId){
        try {
            Order order = new Order();
            // id
            order.setId(orderId);
            // status
            order.setStatus(Constants.OrderStatus.SIGN);
            this.updateByPrimaryKeySelective(order, order.getId());
        } catch (Exception e) {
            logger.debug("更新app订单签到状态 {}", e.getMessage());
            throw new RuntimeException("更新app订单签到状态失败");
        }
    }

    /**
     *
     * <app订单签到日志记录><功能具体实现>
     *
     * @create：2016/12/22 下午1:57
     * @author：sl
     * @param orderId
     * @return void
     */
    @Transactional(readOnly = false)
    public void orderLog (String orderId) {
        try {
            OrderLog log = new OrderLog();
            log.setId(com.lc.zy.common.util.UUID.get());
            log.setOrderId(orderId);
            log.setUserId(SessionUtil.currentStatium());
            log.setAction(Constants.OrderAction.SIGN);
            log.setComment("app签到");
            log.setCt(new Date());
            this.insertSelective(log, log.getId());
        } catch (Exception e) {
            logger.debug("app订单签到日志记录 {}", e.getMessage());
            throw new RuntimeException("app订单签到日志记录失败");
        }
    }

    /**
     *
     * <获取课时信息><功能具体实现>
     *
     * @create：2016/12/23 上午10:10
     * @author：sl
     * @param classId
     * @return com.lc.zy.ball.crm.framework.system.cardPay.vo.ClassVo
     */
    public ClassVo classInfo(String classId) {
        ClassVo vo = new ClassVo();
        try {
            // 获取课时信息
            StatiumClassInfo info = this.selectByPrimaryKey(StatiumClassInfo.class, classId);
            BeanUtils.copyProperties(info, vo);
            // 获取教练名称
            Coach coach = this.selectByPrimaryKey(Coach.class, info.getCoachId());
            vo.setCoachName(coach.getName());
            // 课程价格
            StatiumClass statiumClass = this.selectByPrimaryKey(StatiumClass.class, info.getClassId());
            vo.setClassAmount(statiumClass.getDiscountPrice());
        } catch (Exception e) {
            logger.debug("获取课时信息 {}", e.getMessage());
        }
        return vo;
    }

    /**
     *
     * <获取课时报名人员信息><功能具体实现>
     *
     * @create：2016/12/23 上午10:47
     * @author：sl
     * @param classInfoId
     * @param page
     * @param size
     * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.cardPay.vo.ClassVo>
     */
    public Page<ClassVo> members(String classInfoId, int page, int size) {
        int total = 0;
        List<ClassVo> memberList = new ArrayList<ClassVo>();
        PageRequest pageable = new PageRequest(page, size);
        try {
            StatiumClassMemberCriteria criteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria cri = criteria.createCriteria();
            cri.andClassInfoIdEqualTo(classInfoId);
            criteria.setOrderByClause("ct desc");
            total = statiumClassMemberMapper.countByExample(criteria);
            List<StatiumClassMember> members = statiumClassMemberMapper.selectByExample(criteria);
            for (StatiumClassMember member : members) {
                ClassVo vo = new ClassVo();
                // 获取报名人员信息
                SsoUser user = this.selectByPrimaryKey(SsoUser.class, member.getUserId());
                if (user.getUsername() != null) {
                    vo.setName(user.getUsername());
                } else {
                    vo.setName(user.getNickName());
                }
                vo.setPhone(user.getPhone());
                // 获取订单信息
                Order order = this.selectByPrimaryKey(Order.class, member.getOrderId());
                // 渠道
                vo.setOrderType(order.getOrderType());
                // 支付方式
                vo.setPayType(order.getPayType());
                // 订单号
                vo.setOrderId(member.getOrderId());
                // 报名日期
                vo.setOrderDate(member.getCt());

                memberList.add(vo);
            }
        } catch (Exception e) {
            logger.debug("获取课时报名人员信息 {}", e.getMessage());
        }
        return new PageImpl<>(memberList, pageable, total);
    }

    /**
     *
     * <根据手机号匹配用户><功能具体实现>
     *
     * @create：2016/12/23 下午1:47
     * @author：sl
     * @param phone
     * @return java.util.List<com.lc.zy.ball.crm.framework.system.cardPay.vo.UserAccountVo>
     */
    public List<UserAccountVo> users(String phone) {
        List<UserAccountVo> vos = new ArrayList<UserAccountVo>();
        try {
            // 根据手机号获取用户信息
            SsoUserCriteria criteria = new SsoUserCriteria();
            SsoUserCriteria.Criteria c = criteria.createCriteria();
            c.andPhoneLike("%" + phone + "%");
            List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
            for (SsoUser user : users) {
                UserAccountVo vo = new UserAccountVo();
                vo.setUserId(user.getId());
                vo.setPhone(user.getPhone());
                if (user.getUsername() != null) {
                    vo.setName(user.getUsername());
                } else {
                    vo.setName(user.getNickName());
                }

                vos.add(vo);
            }
        } catch (Exception e) {
            logger.debug("根据手机号匹配用户 {}", e.getMessage());
        }
        return vos;
    }

    /**
     *
     * <获取用户账户信息><功能具体实现>
     *
     * @create：2016/12/23 下午2:18
     * @author：sl
     * @param searchParams
     * @return com.lc.zy.ball.crm.framework.system.cardPay.vo.UserAccountVo
     */
    public UserAccountVo userAccounts(Map<String, Object> searchParams) {
        UserAccountVo vo = new UserAccountVo();
        try {
            CrmUserCardAccountCriteria criteria = new CrmUserCardAccountCriteria();
            CrmUserCardAccountCriteria.Criteria cri = criteria.createCriteria();
            cri.andStatusEqualTo(Constants.cardStatus.ACTIVE);
            //modify by zzq 同一个手机号在两家道馆购买会员卡 此时需对道馆id进行校验
            cri.andStatiumIdEqualTo(Integer.parseInt(SessionUtil.currentStatium()));
            
            if (searchParams != null) {
                Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
                Criterias.bySearchFilter(cri, filters.values());
            }
            List<CrmUserCardAccount> accounts = crmUserCardAccountMapper.selectByExample(criteria);
            for (CrmUserCardAccount account : accounts) {
                if (account.getCardType() == Constants.cardType.CARD) {
                    vo.setBalance(account.getBalance());
                } else if (account.getCardType() == Constants.cardType.DATECARD) {
                    String sDate = DateUtils.formatDate(account.getStartDate(), "yyyy/MM/dd");
                    String eDate = DateUtils.formatDate(account.getEndDate(), "yyyy/MM/dd");
                    vo.setCardDate(sDate + " ~ " + eDate);
                }
            }
            // 获取用户信息
            String userId = (String)searchParams.get("EQ_userId");
            SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
            if (user != null) {
                // 姓名
                if (user.getUsername() != null) {
                    vo.setName(user.getUsername());
                } else {
                    vo.setName(user.getNickName());
                }
                // 手机
                vo.setPhone(user.getPhone());
            }
        } catch (Exception e) {
            logger.debug("获取用户账户信息 {}", e.getMessage());
        }
        return vo;
    }

    /**
     *
     * <根据课时id获取课时信息><功能具体实现>
     *
     * @create：2016/12/23 下午4:11
     * @author：sl
     * @param classInfoId
     * @return com.lc.zy.ball.domain.oa.po.StatiumClassInfo
     */
    public StatiumClassInfo classInfoById(String classInfoId) {
        StatiumClassInfo info = null;
        try {
            info = this.selectByPrimaryKey(StatiumClassInfo.class, classInfoId);
        } catch (Exception e) {
            logger.debug("根据课时id获取课时信息 {}", e.getMessage());
        }
        return info;
    }

    /**
     *
     * <根据课程ID获取课程信息><功能具体实现>
     *
     * @create：2016/12/23 下午4:14
     * @author：sl
     * @param classId
     * @return com.lc.zy.ball.domain.oa.po.StatiumClass
     */
    public StatiumClass classById(String classId) {
        StatiumClass statiumClass = null;
        try {
            statiumClass = this.selectByPrimaryKey(StatiumClass.class, classId);
        } catch (Exception e) {
            logger.debug("根据课程ID获取课程信息 {}", e.getMessage());
        }
        return statiumClass;
    }

    /**
     *
     * <获取用户账户><功能具体实现>
     *
     * @create：2016/12/23 下午4:19
     * @author：sl
     * @param userId
     * @param cardType
     * @return com.lc.zy.ball.domain.oa.po.CrmUserCardAccount
     */
    public CrmUserCardAccount account(String userId, int cardType) {
        CrmUserCardAccount account = null;
        try {
            CrmUserCardAccountCriteria criteria = new CrmUserCardAccountCriteria();
            CrmUserCardAccountCriteria.Criteria cri = criteria.createCriteria();
            cri.andUserIdEqualTo(userId);
            cri.andCardTypeEqualTo(cardType);
            //modify by zzq 同一个手机号在两家道馆购买会员卡 此时需对道馆id进行校验
            cri.andStatiumIdEqualTo(Integer.parseInt(SessionUtil.currentStatium()));
            List<CrmUserCardAccount> accounts = crmUserCardAccountMapper.selectByExample(criteria);
            if (!accounts.isEmpty()) {
                account = accounts.get(0);
            }
        } catch (Exception e) {
            logger.debug("获取用户账户 {}", e.getMessage());
        }
        return account;
    }

    /**
     *
     * <更新用户账户信息><功能具体实现>
     *
     * @create：2016/12/23 下午4:32
     * @author：sl
     * @param account
     * @return void
     */
    @Transactional(readOnly = false)
    public void updateAccout(CrmUserCardAccount account) {
        try {
            this.updateByPrimaryKeySelective(account, account.getId());
        } catch (Exception e) {
            logger.debug("更新用户账户信息 {}", e.getMessage());
            throw new RuntimeException("更新用户账户信息失败");
        }
    }

    /**
     *
     * <线下买课创建订单><功能具体实现>
     *
     * @create：2016/12/23 下午5:12
     * @author：sl
     * @param order
     * @param cardType
     * @return java.lang.String
     */
    @Transactional(readOnly = false)
    public String creatOrder(Order order, int cardType) {
        String orderId = "";
        try {
            orderId = seqGenerate.genLiveOrderId();
            // id
            order.setId(orderId);
            // statiumId
            order.setStatiumId(Integer.valueOf(SessionUtil.currentStatium()));
            // status
            order.setStatus(Constants.OrderStatus.PAIED);
            // orderType
            order.setOrderType(Constants.BookType.LIVE);
            // payType
            if (cardType == Constants.cardType.CARD) {
                order.setPayType(Constants.orderPayType.ACCOUNT);
            } else {
                order.setPayType(Constants.orderPayType.CARDDATE);
            }
            // ordersType
            order.setOrdersType(Constants.OrdersType.CLASS);
            // ct
            order.setCt(new Date());
            // cb
            order.setCb(SessionUtil.currentUserId());
            // et
            order.setEt(new Date());
            // eb
            order.setEb(SessionUtil.currentUserId());

            this.insertSelective(order, orderId);
        } catch (Exception e) {
            logger.debug("线下买课创建订单 {}", e.getMessage());
            throw new RuntimeException("线下买课创建订单失败");
        }
        return orderId;
    }

    /**
     *
     * <线下道馆订单item><功能具体实现>
     *
     * @create：2016/12/26 上午9:49
     * @author：sl
     * @param item
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveItem(OrderItem item) {
        try {
            item.setId(UUID.get());
            item.setCt(new Date());
            item.setCb(SessionUtil.currentUserId());
            item.setEt(new Date());
            item.setEb(SessionUtil.currentUserId());

            this.insertSelective(item, item.getId());
        } catch (Exception e) {
            logger.debug("线下道馆订单item {}", e.getMessage());
            throw  new RuntimeException("线下买课订单详情失败");
        }
    }

    /**
     *
     * <线下买课订单日志><功能具体实现>
     *
     * @create：2016/12/26 上午10:02
     * @author：sl
     * @param orderlog
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveOrderLog(OrderLog orderlog) {
        try {
            orderlog.setId(UUID.get());
            orderlog.setAction(Constants.OrderAction.PAY);
            orderlog.setComment(Constants.orderLogComment.LIVECOMMENT);
            orderlog.setCt(new Date());
            this.insertSelective(orderlog, orderlog.getId());
        } catch (Exception e) {
            logger.debug("线下买课订单日志 {}", e.getMessage());
            throw new RuntimeException("线下买课订单日志失败");
        }
    }

    /**
     *
     * <线下买课消费卡片日志><功能具体实现>
     *
     * @create：2016/12/26 上午10:56
     * @author：sl
     * @param crmUserCardLog
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveCrmUserCardLog(CrmUserCardLog crmUserCardLog) {
        try {
            crmUserCardLog.setId(UUID.get());
            crmUserCardLog.setCt(new Date());
            crmUserCardLog.setOrderType(Constants.orderType.ONLINE);
            this.insertSelective(crmUserCardLog, crmUserCardLog.getId());
        } catch (Exception e) {
            logger.debug("线下买课消费卡片日志 {}", e.getMessage());
            throw new RuntimeException("线下买课卡片消费日志失败");
        }
    }

    /**
     *
     * <线下道馆买课报名学员save><功能具体实现>
     *
     * @create：2016/12/26 下午1:23
     * @author：sl
     * @param member
     * @return void
     */
    @Transactional(readOnly = false)
    public void saveClassMember(StatiumClassMember member) {
        try {
            SsoUser user = this.selectByPrimaryKey(SsoUser.class, member.getUserId());
            member.setName(user.getNickName());
            if (!user.getPhone().isEmpty()) {
                member.setPhoto(user.getPhoto());
            }
            member.setCt(new Date());
            member.setEt(new Date());
            member.setId(UUID.get());
            this.insertSelective(member, member.getId());
        } catch (Exception e) {
            logger.debug("线下道馆买课报名学员save {}", e.getMessage());
            throw new RuntimeException("线下买课学员保存失败");
        }
    }

    /**
     *
     * <验证用户是否报名><功能具体实现>
     *
     * @create：2016/12/26 下午2:28
     * @author：sl
     * @param userId
     * @param classInfoId
     * @return boolean
     */
    public boolean checkMember(String userId, String classInfoId) {
        Boolean flag = false;
        try {
            StatiumClassMemberCriteria criteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria cri = criteria.createCriteria();
            cri.andUserIdEqualTo(userId);
            cri.andClassInfoIdEqualTo(classInfoId);
            List<StatiumClassMember> members = statiumClassMemberMapper.selectByExample(criteria);
            if (!members.isEmpty()){
                flag = true;
            }
        } catch (Exception e) {
            logger.debug("验证用户是否报名 {}", e.getMessage());
        }
        return flag;
    }
}
