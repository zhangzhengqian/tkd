package com.lc.zy.ball.boss.framework.orders.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.BookBallMapper;
import com.lc.zy.ball.domain.oa.mapper.GamesMemberMapper;
import com.lc.zy.ball.domain.oa.mapper.MemberListMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.RoleMapper;
import com.lc.zy.ball.domain.oa.mapper.UserRoleMapper;
import com.lc.zy.ball.domain.oa.po.BookBall;
import com.lc.zy.ball.domain.oa.po.BookBallCriteria;
import com.lc.zy.ball.domain.oa.po.Coacher;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.mq.bean.OrdeNotifyrMessage;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.OrderNotifyUtil;

@Component
@Transactional(readOnly = true)
public class OrderSendMsgService extends AbstractCacheService {
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private BookBallMapper bookBallMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private QueueProducer queueProducer;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private MemberListMapper memberListMapper;

	@Autowired
	private GamesMemberMapper gamesMemberMapper;

	@Autowired
	private OrderNotifyUtil orderNotifyUtil;

	private static Logger logger = LoggerFactory.getLogger(OrderSendMsgService.class);

	/**
	 * 
	 * <订单确认后发送短信><订场和约球订场>
	 *
	 * @create：2015年12月3日 下午6:49:26
	 * @author： sl
	 * @param orderId
	 */
	@Transactional(readOnly = true)
	public void sendMsg(String orderId) {
		logger.debug("--------------订单确认后发送短信-----------------");
		try {
			// 获取订单信息
			Order order = this.selectByPrimaryKey(Order.class, orderId);
			Map<String, Object> root = new HashMap<String, Object>();
			String msg = "";
			if (Constants.OrderType.STATIUM == order.getOrdersType()
					|| Constants.OrderType.BOOKBALL == order.getOrdersType()) {
				logger.debug("-------------------------------" + order.getOrdersType());
				// 获取场馆信息
				StatiumDetail detail = null;
				try {
					detail = this.selectByPrimaryKey(StatiumDetail.class, order.getStatiumId());
				} catch (Exception e) {
					logger.error("查询场馆信息失败", e);
					throw new ServiceException();
				}
				// 获取用户信息
				SsoUser user = null;
				try {
					user = this.selectByPrimaryKey(SsoUser.class, order.getCustomerId());
				} catch (Exception e) {
					logger.error("查询用户信息失败", e);
					throw new ServiceException();
				}
				logger.debug("phone {}", user.getPhone());
				String phone = user.getPhone();

				OrdeNotifyrMessage notifyMessage = new OrdeNotifyrMessage();
				List<OrdeNotifyrMessage.SpaceBean> spaces = new ArrayList<OrdeNotifyrMessage.SpaceBean>();

				OrderItemCriteria oiCriteria = new OrderItemCriteria();
				OrderItemCriteria.Criteria oic = oiCriteria.createCriteria();
				oic.andOrderIdEqualTo(order.getId());
				// 获取订单详细信息
				List<OrderItem> orderItemList = orderItemMapper.selectByExample(oiCriteria);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 短信参数
				Integer start_Time = null;
		        String statiumName = "";
				if (CollectionUtils.isNotEmpty(orderItemList)) {
					OrderItem orderItem = orderItemList.get(0);
					root.put(
							"date",
							sdf.format(orderItem.getStartDate()).split("-")[1] + "月"
									+ sdf.format(orderItem.getStartDate()).split("-")[2] + "日");
					root.put("week", DateUtils.getWeek(orderItem.getStartDate()));
					root.put("statiumName", detail.getName());
					statiumName = detail.getName();
					start_Time = orderItem.getStartTime();
					sdf.applyPattern("HH:mm");
					StringBuilder str = new StringBuilder();
					OrdeNotifyrMessage.SpaceBean spaceBean = null;
					String sportType_ = orderItem.getSportType();
					for (OrderItem item : orderItemList) {
						str.append("(");
						str.append(sdf.format(new Date(item.getStartTime() * 1000L)));
						str.append("-");
						str.append(sdf.format(new Date(item.getEndTime() * 1000L)));
						str.append(")");
						str.append(item.getSpaceName());
						Date startTime = new Date(item.getStartTime() * 1000L);
						Double startHour = org.apache.commons.lang.time.DateUtils.getFragmentInHours(startTime,
								Calendar.DATE) / 1d;
						int startH = startHour.intValue();
						spaceBean = new OrdeNotifyrMessage.SpaceBean();
						spaceBean.setSpaceId(item.getSpaceId());
						String orderTime = startH < 10 ? ("0" + startH + ":00") : (startH + ":00");
						spaceBean.setTime(orderTime);
						spaces.add(spaceBean);
						spaceBean = new OrdeNotifyrMessage.SpaceBean();
						spaceBean.setSpaceId(item.getSpaceId());
						orderTime = startH < 10 ? ("0" + startH + ":30") : (startH + ":30");
						spaceBean.setTime(orderTime);
						spaces.add(spaceBean);
						notifyMessage.setOrderDate(DateUtil.formatDate(item.getStartDate(), "yyyy-MM-dd"));
					}
					root.put("orderItem", str.toString());
					root.put("orderId", order.getId());
					root.put("serviceHotline", Constants.SERVICE_HOTLINE);
					List<String> sportTypes = new ArrayList<String>();
                    sportTypes.add("TENNIS");
                    sportTypes.add("BADMINTON");
                    sportTypes.add("BASKETBALL");
                    sportTypes.add("TABLE_TENNIS");
                    if("BILLIARDS".equals(sportType_)){
                    	root.put("hour", "4");
                	}else{
                		root.put("hour", "24");
                	}
					if(sportTypes.contains(sportType_)){
                    	root.put("tip", "请务必穿着浅色鞋底运动鞋进场，");
                    }
				}
				if (Constants.OrderType.STATIUM == order.getOrdersType()) {
					msg = FreeMarkerUtils.format("/template/sms/orderStatiumSuccess.ftl", root);
				} else {
					// 约球 发短信
					/*BookBallCriteria bookBallCriteria = new BookBallCriteria();
					BookBallCriteria.Criteria cri = bookBallCriteria.createCriteria();
					cri.andOrderIdEqualTo(order.getId());
					cri.andStatiumIdEqualTo(order.getStatiumId());
					List<BookBall> bookList = bookBallMapper.selectByExample(bookBallCriteria);
					if (CollectionUtils.isNotEmpty(bookList)) {
						BookBall book = bookList.get(0);
						book.setState(Constants.BookBallState.PUBLISHED);
						book.setEt(new Date());
						this.updateByPrimaryKeySelective(book, book.getId());
						queueProducer.push(QueueNames.SYNC_BOOK_QUEUE, book.getId());
					}*/
					msg = FreeMarkerUtils.format("/template/sms/bookSuccess.ftl", root);
				}
				root.put("phone", StringUtils.isEmpty(phone) ? " " : phone);
				logger.debug("短信内容{}", msg);
				// 发送短信
				if (StringUtils.isNotEmpty(phone)) {
					int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					messageUtil.sendSms(phone, msg);
        			Date startT = new Date(start_Time*1000L);
        			if((DateUtils.formatDate(new Date(), "yyyy-MM-dd")).equals(DateUtils.formatDate(startT, "yyyy-MM-dd"))){
        				Calendar cal = Calendar.getInstance();
        				cal.setTime(startT);
        				int hour_ = cal.get(Calendar.HOUR_OF_DAY);
        				if(hour_-hour<2){
        					Map<String, Object> root_ = new HashMap<String, Object>();
        					root_.put("statiumName", statiumName);
        					root_.put("playTime", hour_);
        					msg = FreeMarkerUtils.format("/template/sms/playNotify.ftl", root_);
        					messageUtil.sendSms(phone, msg);
        				}
        			}
				}
				logger.debug("发送成功订单到手机号{}", phone);
			}else if(Constants.OrderType.COACH == order.getOrdersType()){
				Coacher coach = this.selectByPrimaryKey(Coacher.class, order.getStatiumId());
				String phone = coach.getLinkphone();
				OrderItemCriteria oiCriteria = new OrderItemCriteria();
				OrderItemCriteria.Criteria oic = oiCriteria.createCriteria();
				oic.andOrderIdEqualTo(order.getId());
				// 获取订单详细信息
				List<OrderItem> orderItemList = orderItemMapper.selectByExample(oiCriteria);
				OrderItem item = orderItemList.get(0);
				String name = item.getSpaceName();
				root.put("coachName", name);
				String sportType = CommonOAUtils.sportsEToC(item.getSportType());
				root.put("sportType", sportType);
				root.put("phone", phone);
				root.put("hotline", Constants.SERVICE_HOTLINE);
				root.put("coachType", coach.getUserType());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				root.put(
						"date",
						sdf.format(item.getStartDate()).split("-")[1] + "月"
								+ sdf.format(item.getStartDate()).split("-")[2] + "日");
				root.put("week", DateUtils.getWeek(item.getStartDate()));
				sdf.applyPattern("HH:mm");
				StringBuilder str = new StringBuilder();
				for(OrderItem it:orderItemList){
					str.append(sdf.format(new Date(it.getStartTime() * 1000L)));
					str.append("-");
					str.append(sdf.format(new Date(it.getEndTime() * 1000L)));
					str.append("、");
				}
				if(str.lastIndexOf("、")==str.length()-1){
					root.put("time", str.substring(0, str.length()-1));
				}else{
					root.put("time", str.toString());
				}
				root.put("orderId", order.getId());
				msg = FreeMarkerUtils.format("/template/sms/orderCoachSuccess.ftl", root);
				logger.debug("短信内容,{}",msg);
				// 获取用户信息
				SsoUser user = null;
				try {
					user = this.selectByPrimaryKey(SsoUser.class, order.getCustomerId());
				} catch (Exception e) {
					logger.error("查询用户信息失败", e);
					throw new ServiceException();
				}
				logger.debug("phone {}", user.getPhone());
				messageUtil.sendSms(user.getPhone(), msg);
			}
		} catch (Exception e) {
			logger.debug("短信发送", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("coachName", "1");
		root.put("sportType", "2");
		root.put("phone", "3");
		root.put("hotline", "4");
		root.put("coachType", "5");
		root.put("date","6");
		root.put("week", "7");
		root.put("time", "8");
		root.put("orderId", "9");
		String msg = FreeMarkerUtils.format("/template/sms/orderCoachSuccess.ftl", root);
		System.out.println(msg);
	}
}
