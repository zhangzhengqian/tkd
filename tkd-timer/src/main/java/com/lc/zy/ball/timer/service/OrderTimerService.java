package com.lc.zy.ball.timer.service;

import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.ball.timer.common.AbstractCacheService;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(value = "core", readOnly = true)
public class OrderTimerService extends AbstractCacheService {
    private Logger logger = LoggerFactory.getLogger(OrderTimerService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private StatiumActivityMemberMapper statiumActivityMemberMapper;

    @Autowired
    private StatiumClassMemberMapper statiumClassMemberMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private StatiumInfosMapper statiumInfosMapper;

    @Autowired
    private MessageUtil messageUtil;

    /**
     * <扫描全部订单为新下订单状态是否超时>
     * 
     * @return String DOM对象
     * @Exception 异常对象
     */
    @Transactional(value = "core", readOnly = false)
    public void updateOrderStatus() {
        try {
            OrderCriteria criteria = new OrderCriteria();
            OrderCriteria.Criteria c = criteria.createCriteria();
            c.andStatusEqualTo(Constants.OrderStatus.NEW);
            c.andOrderTypeEqualTo(Constants.BookType.APP);
            List<Order> list = orderMapper.selectByExample(criteria);
            Calendar calendar = Calendar.getInstance();
            Integer timeout = Constants.TIMEOUT;
            for (Order order : list) {
                calendar.setTime(order.getCt());
                calendar.add(Calendar.MINUTE, timeout); // 订单默认超时为15分钟
                Date currDate = new Date();
                if (currDate.compareTo(calendar.getTime()) > 0) {
                    // 更新订单信息
                    order.setStatus(Constants.OrderStatus.CANCELED);
                    this.updateByPrimaryKeySelective(order, order.getId());
                    // 活动删除超时报名
                    if (order.getOrdersType() == Constants.OrderType.ACTIVITY) {
                        deleteStatiumActivityMember(order.getId(), order.getUserId());
                    } else if (order.getOrdersType() == Constants.OrderType.STATIUM) {
                        // 删除课程超时报名人员
                        deleteClassMember(order.getId(), order.getUserId());
                        // 返回1元体验次数
                        /*if (order.getIsExperience() == 1 && order.getOrdersType() == Constants.OrderType.STATIUM) {
                            try {
                                SsoUser user = this.selectByPrimaryKey(SsoUser.class, order.getUserId());
                                int experience = user.getExperience();
                                user.setExperience(experience + 1);
                                this.updateByPrimaryKey(SsoUser.class, user.getId());
                            } catch (Exception e) {
                                logger.debug("订单超时,返回课程订单1元体验次数", e.getMessage());
                                throw new RuntimeException(e);
                            }

                        }*/
                    }
                    try {
                        // 记录订单日志
                        OrderLog log = new OrderLog();
                        log.setId(UUID.get());
                        log.setCt(new Date());
                        log.setOrderId(order.getId());
                        log.setUserId(order.getUserId());
                        log.setAction(Constants.OrderAction.TIMEOUT);
                        log.setComment("订单超时，订单下单");
                        orderLogMapper.insert(log);
                    } catch (Exception e) {
                        logger.debug("订单号：{} 超时取消订单日志记录失败！", order.getId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("扫描全部订单为新下订单状态是否超时订单定时任务执行失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <删除课程订单超时报名人员><功能具体实现>
     *
     * @create：2016-08-06 20:51:20
     * @author：sl
     * @param orderId
     * @param userId
     * @return void
     */
    private void deleteClassMember(String orderId, String userId) {
        try {
            StatiumClassMemberCriteria statiumClassMemberCriteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria criteria = statiumClassMemberCriteria.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            criteria.andUserIdEqualTo(userId);
            statiumClassMemberMapper.deleteByExample(statiumClassMemberCriteria);
        } catch (Exception e) {
            logger.debug("删除课程订单超时报名人员", e.getMessage());
        }
    }

    /**
     *
     * <删除活动订单超时人员报名信息><功能具体实现>
     *
     * @create：2016-08-06 20:48:13
     * @author：sl
     * @param orderId
     * @param userId
     * @return void
     */
    private void deleteStatiumActivityMember(String orderId, String userId) {
        try {
            StatiumActivityMemberCriteria statiumActivityMemberCriteria = new StatiumActivityMemberCriteria();
            StatiumActivityMemberCriteria.Criteria criteria = statiumActivityMemberCriteria.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andOrderIdEqualTo(orderId);
            statiumActivityMemberMapper.deleteByExample(statiumActivityMemberCriteria);
        } catch (Exception e) {
            logger.debug("删除活动订单超时人员报名信息", e.getMessage());
        }
    }

    /**
     *
     * <订单开场前2小时提醒><功能具体实现>
     *
     * @create：2016-08-08 17:57:11
     * @author：sl
     * @param
     * @return void
     */
    @Transactional(value = "core", readOnly = false)
    public void tipOrder() {
        try {
            OrderCriteria criteria = new OrderCriteria();
            OrderCriteria.Criteria c = criteria.createCriteria();
            List<String> statuss = new ArrayList<String>();
            statuss.add(Constants.OrderStatus.PAIED);
            c.andStatusIn(statuss);
            List<Integer> orderTypes = new ArrayList<Integer>();
            orderTypes.add(Constants.OrderType.STATIUM);
            c.andOrdersTypeIn(orderTypes);
            c.andOrderTypeEqualTo(Constants.BookType.APP);
            List<Order> orders = orderMapper.selectByExample(criteria);
            for (Order order : orders) {
                String orderId = order.getId();
                OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
                OrderItemCriteria.Criteria criteriaOrderItem = orderItemCriteria.createCriteria();
                criteriaOrderItem.andOrderIdEqualTo(orderId);
                orderItemCriteria.setOrderByClause("start_time");
                List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemCriteria);
                OrderItem orderItem = orderItemList.get(0);
                Integer statiumId = order.getStatiumId();
                StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
                StatiumInfosCriteria.Criteria criteria1 = statiumInfosCriteria.createCriteria();
                criteria1.andDgIdEqualTo(statiumId);
                List<StatiumInfos> statiumInfoses = statiumInfosMapper.selectByExample(statiumInfosCriteria);
                String statiumName = statiumInfoses.get(0).getDgName();
                String userId = order.getUserId();
                SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                logger.debug("hour {}", hour);
                // 订单开始时间
                String signDate = DateUtils.formatDate(orderItem.getSignDate()) + " " + orderItem.getStartTime();
                Date startTime = DateUtils.getDate(signDate, "yyyy-MM-dd HH:mm");
                if(( DateUtils.formatDate(new Date(), "yyyy-MM-dd")).equals(DateUtils.formatDate(orderItem.getSignDate(), "yyyy-MM-dd"))){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startTime);
                    int hour_ = cal.get(Calendar.HOUR_OF_DAY);
                    logger.debug("hour_ {}", hour_);
                    if(hour_-hour==1){
                        logger.debug("------------------发送提醒短信-----------------");
                        Map<String, Object> root_ = new HashMap<String, Object>();
                        root_.put("statiumName", statiumName);
                        root_.put("playTime", hour_);
                        String msg = FreeMarkerUtils.format("/template/sms/playNotify.ftl", root_);
                        logger.debug("提醒短信内容 {}", msg);
                        messageUtil.sendSms(user.getPhone(), msg);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("订单开场前2小时提醒温馨提示订单定时任务执行失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <扫描支付完成play完成订单><功能具体实现>
     *
     * @create：16/9/6 11:49
     * @author：sl
     * @param
     * @return void
     */
    @Transactional(value = "core", readOnly = false)
    public void updateOrderFinishStatus() {
        try {
            OrderCriteria criteria = new OrderCriteria();
            OrderCriteria.Criteria c = criteria.createCriteria();
            List<String> statuss = new ArrayList<String>();
            statuss.add(Constants.OrderStatus.PAIED);
            statuss.add(Constants.OrderStatus.SIGN);
            c.andStatusIn(statuss);
            List<Integer> orderTypes = new ArrayList<Integer>();
            orderTypes.add(Constants.OrderType.STATIUM);
            orderTypes.add(Constants.OrderType.ACTIVITY);
            c.andOrdersTypeIn(orderTypes);
            c.andOrderTypeEqualTo(Constants.BookType.APP);
            List<Order> orders = orderMapper.selectByExample(criteria);
            for (Order order : orders) {
                String orderId = order.getId();
                OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
                OrderItemCriteria.Criteria criteriaOrderItem = orderItemCriteria.createCriteria();
                criteriaOrderItem.andOrderIdEqualTo(orderId);
                orderItemCriteria.setOrderByClause("start_time");
                List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemCriteria);
                OrderItem orderItem = orderItemList.get(0);
                // 订单开始时间
                String signDate = DateUtils.formatDate(orderItem.getSignDate()) + " " + orderItem.getStartTime();
                Date startTime = DateUtils.getDate(signDate, "yyyy-MM-dd HH:mm");
                Date currDate = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTime);
                if (currDate.compareTo(cal.getTime()) > 0) {
                    // 更新订单信息(订单完成)
                    order.setStatus(Constants.OrderStatus.BILLED);
                    if (Constants.OrderType.STATIUM == order.getOrdersType()){
                        // 待评价
                        order.setIsComment(0);
                    }
                    this.updateByPrimaryKeySelective(order, order.getId());
                }
            }
        } catch (Exception e) {
            logger.error("扫描支付完成play完成订单订单定时任务执行失败", e);
            throw new RuntimeException(e);
        }
    }
}
