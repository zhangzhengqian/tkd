package com.lc.zy.ball.app.service.order;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.order.bean.OrderCommentVo;
import com.lc.zy.ball.app.service.order.bean.OrderDetailVo;
import com.lc.zy.ball.app.service.order.bean.OrderListVo;
import com.lc.zy.ball.app.service.order.bean.OrderParam;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sl on 16/7/22.
 */
@Service("order")
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private CommonService commonService = null;

    private OrderRepository orderRepository = null;
    
    @Autowired
    private SsoUserMapper ssoUserMapper ;

    @Autowired
    public OrderService(CommonService commonService, OrderRepository orderRepository) {
        super();
        this.commonService = commonService;
        this.orderRepository = orderRepository;
    }

    /**
     * <创建订单><功能具体实现>
     *
     * @param request
     * @return Success
     * @create：2016-07-22 11:40:43
     * @modify by zzq 2016-11-18
     * @author：sl
     */
    @Auth
    public Success create(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        
        //modify by zzq 
        String newOrderId = "";
        try {
            // 未支付订单id
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            //modify by zzq
            // 获取用户信息
            SsoUser user = commonService.getSsoUserByToken(token);
            if (StringUtils.isEmpty(user.getPhone())) {
                return new Success(sn, false, new Reason(Constants.ERROR, "您还没有绑定手机号无法下单，请先去“设置”里面绑定手机号"));
            }
            String userId = user.getId();
            Map<String, Object> data = null;
            // 订单类型:0: 课程 、1：活动  2 会员卡充值
            String ordersType = AppRequestUtil.getParameter(request, "ordersType");
            // 下单时如果有未支付的订单，点击重新预约时要取消的订单
            if (StringUtils.isNotEmpty(orderId)) {
                orderRepository.cancelStatium(orderId, userId);
            } else {// 下单时判断是否有为支付的订单
                List<Order> orderList = orderRepository.getNotPaid(userId, Integer.valueOf(ordersType));
                if (orderList != null && orderList.size() > 0) {
                    data = new HashMap<String, Object>();
                    data.put("orderId", orderList.get(0).getId());
                    if (orderList.get(0).getOrdersType() == Constants.OrderType.RECHARGE){
                        data.put("reason_", new Reason("create_order_error_has_notPaid", "您有未付款的充值订单,请前往支付"));
                    } else if (orderList.get(0).getOrdersType() == Constants.OrderType.ACTIVITY){
                        data.put("reason_", new Reason("create_order_error_has_notPaid", "您有未付款的活动订单,请前往支付"));
                    } else {
                        data.put("reason_", new Reason("create_order_error_has_notPaid", "您有未付款的订场订单,请前往支付"));
                    }
                    return new Success(sn, false, data);
                }
            }
           
            // 创建订单
            //modify by zzq 2016-11-17 会员卡充值
            if(ordersType.equals("0")||ordersType.equals("1")){
            	 // 获取下单内容
                List<OrderParam> orderParams = AppRequestUtil.getParameterList(request, "orderParam", OrderParam.class);
            	newOrderId = orderRepository.create(orderParams, user.getId(), ordersType);
            }else if(ordersType.equals("2")){
            	//会员卡id
                String cardId = AppRequestUtil.getParameter(request, "cardId");
            	//会员卡充值
            	newOrderId = orderRepository.createRechargeOrder(userId, cardId);
            }
            return new Success(sn, true, new KeyValueEntity("orderId", newOrderId));
        } catch (Exception e) {
            logger.error("createOrder_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, e.getMessage()));
        }
    }

    /**
     *
     * <订单详情><功能具体实现>
     *
     * @create：2016-07-25 19:47:59
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth
    public Success orderDetail(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            logger.debug("orderId={} ", orderId);
            if (StringUtils.isEmpty(orderId)) {
                return new Success(sn, false, new Reason("orderDetail_isnull", "订单ID无效，为空"));
            }
            SsoUser user = commonService.getSsoUserByToken(token);
            OrderDetailVo orderDetailVo = orderRepository.getOrderDetail(user.getId(), orderId);
            return new Success(sn, true, new KeyValueEntity("orderDetail", orderDetailVo));
        } catch (Exception e) {
            logger.error("orderDetail_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderDetail_exception", e.getMessage()));
        }
    }
    
    /**
     * 
     * <馆长登录后点击订单详情><功能具体实现>
     *
     * @create：2016年11月21日 上午10:48:50
     * @author：zzq
     * @param request
     * @return
     */
    public Success crmLoginOrderDetail(ClientRequest request){
    	String sn = request.getSn();
		String orderId = AppRequestUtil.getParameter(request, "orderId");
    	try {
			if(orderId.isEmpty()){
				return new Success(sn, false, new Reason("orderDetail_isnull", "订单ID无效，为空"));
			}
			OrderDetailVo vo = orderRepository.getCrmOrderDetail(orderId);
			return new Success(sn, true, new KeyValueEntity("orderDetail", vo));
		} catch (Exception e) {
			logger.error("order_DetailException"+sn+e.getMessage());
			return new Success(sn, false, new Reason("orderDetail_exception", e.getMessage()));
		}
    }

    /**
     *
     * <获取订单list><功能具体实现>
     *
     * @create：2016-07-25 20:03:33
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth
    public Success orderList(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            // 订单状态: false:未支付,true:已预约,空为全部
            Boolean paied = null;
            if (request.getParams().get("paied") != null && "" != request.getParams().get("paied")) {
                paied = AppRequestUtil.getParameterBoolean(request, "paied");
            }
            // 订单类型:0: 课程 、1：活动 ,2:充值  空为全部
            String type = AppRequestUtil.getParameter(request, "type");
            // 评价
            String comment = AppRequestUtil.getParameter(request, "comment");
            // 分页
            Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
            Integer size = AppRequestUtil.getParameterInteger(request, "size");
            logger.debug("token={} , paied={} , type={}  , begin={} , size={}, comment={}", token,paied, type, begin, size, comment);
            // 获取用户信息
            SsoUser user = commonService.getSsoUserByToken(token);
            List<OrderListVo> orderListVos = orderRepository.getOrderList(user, paied, type, begin, size, comment);
            // 评价总数
            Integer commentNum = orderRepository.commentNum(user.getId());
            return new Success(sn, true, new KeyValueEntity("orderList", orderListVos).append("commentNum", commentNum));
        } catch (Exception e) {
            logger.error("orderList_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderList_exception", e.getMessage()));
        }
    }

    /**
     *
     * <取消订单><功能具体实现>
     *
     * @create：2016-07-30 11:49:31
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth
    public Success orderCancel(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            // 订单id
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            // 订单类型:0: 课程 、1：活动 ,空为全部
            Integer type = AppRequestUtil.getParameterInteger(request, "type");
            logger.debug("orderId={}, type={} ", orderId, type);
            if (StringUtils.isEmpty(orderId)) {
                return new Success(sn, false, new Reason("orderCancel_isnull", "订单ID无效，为空"));
            }
            if (type == null) {
                return new Success(sn, false, new Reason("orderCancel_isnull", "订单类型无效，为空"));
            }
            // 获取用户信息
            SsoUser user = commonService.getSsoUserByToken(token);
            // 取消订单
            orderRepository.cancel(orderId, type, user.getId());
            return new Success(sn, true, new KeyValueEntity("orderId", orderId));
        } catch (Exception e) {
            logger.error("orderCancel_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderCancel_exception", e.getMessage()));
        }
    }

    /**
     *
     * <订单评论><功能具体实现>
     *
     * @create：
     * @author：sl
     * @return
     */
    @Auth
    public Success orderComment(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            // 订单id
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            // 教练id
            String coachId = AppRequestUtil.getParameter(request, "coachId");
            // 评论类型
            Integer commentType = AppRequestUtil.getParameterInteger(request, "commentType");
            // 道馆评星
            Integer statiumGrade = AppRequestUtil.getParameterInteger(request, "statiumGrade");
            // 教练评星
            Integer coachGrade = AppRequestUtil.getParameterInteger(request, "coachGrade");
            logger.debug("orderId={}, coachId={}, commentType={}, statiumGrader={}, coachGrade={}",
                    orderId, coachId, commentType, statiumGrade, coachGrade);
            if (StringUtils.isEmpty(orderId)) {
                return new Success(sn, false, new Reason("orderComment_isnull", "订单ID无效，为空"));
            }
            // 获取用户信息
            SsoUser user = commonService.getSsoUserByToken(token);
            // 教练评论
            orderRepository.commentCoach(orderId, user.getId(), coachId, coachGrade);
            if (commentType == 1) {
                orderRepository.commentStatium(orderId, user.getId(), statiumGrade);
            }
            // 更新订单评论状态
            orderRepository.updateOrderComment(orderId);
            return new Success(sn, true);
        } catch (Exception e) {
            logger.debug("orderComment_exception:{}" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderComment_exception", e.getMessage()));
        }
    }

    /**
     *
     * <获取评论内容><功能具体实现>
     *
     * @create：16/9/8 14:36
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth
    public Success orderCommentInfo(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            // 订单id
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            logger.debug("orderId={}", orderId);
            if (StringUtils.isEmpty(orderId)) {
                return new Success(sn, false, new Reason("orderCommentInfo_isnull", "订单ID无效，为空"));
            }
            // 获取用户信息
            SsoUser user = commonService.getSsoUserByToken(token);
            // 获取订单评论信息
            OrderCommentVo orderCommentVo = orderRepository.orderCommentInfo(orderId, user.getId());
            return new Success(sn, true, new KeyValueEntity("commentInfo", orderCommentVo));
        } catch (Exception e) {
            logger.debug("orderComment_exception:{}" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderComment_exception", e.getMessage()));
        }
    }

    /**
     *
     * <用户订单退款><功能具体实现>
     *
     * @create：16/9/9 14:46
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth
    public Success orderRefund(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            // 订单id
            String orderId = AppRequestUtil.getParameter(request, "orderId");
            logger.debug("orderId={}", orderId);
            if (StringUtils.isEmpty(orderId)) {
                return new Success(sn, false, new Reason("orderRefund_isnull", "订单ID无效，为空"));
            }
            // 判断用户是否能退款
            Boolean flag = orderRepository.checkOrderRefund(orderId);
            if (flag) {
                // 用户退款
                orderRepository.orderRefund(orderId);
                return new Success(sn, true);
            } else {
                return new Success(sn, false, new KeyValueEntity(Constants.ERROR, "开场前24小时内,不能退款"));
            }
        } catch (Exception e) {
            logger.debug("orderComment_exception:{}" + sn, e.getMessage());
            return new Success(sn, false, new Reason("orderComment_exception", e.getMessage()));
        }
    }

}
