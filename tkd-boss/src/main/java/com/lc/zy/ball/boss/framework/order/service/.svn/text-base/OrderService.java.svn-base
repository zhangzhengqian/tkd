package com.lc.zy.ball.boss.framework.order.service;

import com.lc.zy.ball.boss.common.*;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.pay.alipay.config.AlipayConfig;
import com.lc.zy.ball.boss.common.pay.alipay.util.AlipaySubmit;
import com.lc.zy.ball.boss.common.pay.alipay.util.UtilDate;
import com.lc.zy.ball.boss.common.pay.wxap.util.HttpsPlatRequest;
import com.lc.zy.ball.boss.common.pay.wxap.util.HttpsRequest;
import com.lc.zy.ball.boss.common.pay.wxap.util.MD5Util;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.order.vo.OrderVo;
import com.lc.zy.ball.boss.framework.order.vo.RechargeOrderVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.*;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.payUtils.PayParameter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * <功能描述><功能具体实现>
 *
 * @create：2016年8月1日 上午10:52:10
 * @author：zzq
 */
@Service
@Transactional(readOnly=true)
public class OrderService extends AbstractCacheService{
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private CoachMapper coachMapper;
	
	@Autowired
	private StatiumActivityMapper statiumActivityMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SsoUserMapper ssoUserMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
	@Autowired
	private SsoUserAccountMapper ssoUserAccountMapper;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private SsoUserAccountLogMapper ssoUserAccountLogMapper;
	
	@Autowired
	private OrderLogMapper orderLogMapper;
	
	@Autowired
	private StatiumClassMemberMapper statiumClassMemberMapper;
	
	@Autowired
	private OrgCodeUtil orgCodeUtil;
	
	@Autowired
	private StatiumActivityMemberMapper statiumActivityMemberMapper;
	
	@Autowired
	private PayLogMapper payLogMapper;
	
	@Autowired
	private PayParameter payParameter;
	
	@Autowired
	private CrmUserCardAccountMapper crmUserCardAccountMapper;

	@Resource(name="configs")
	private Map<String,String> configs;
	
	@Resource(name="oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=LoggerFactory.getLogger(OrderService.class);
	
	/**
	 * 
	 * <订单页面列表显示><功能具体实现>
	 *
	 * @create：2016年8月16日 上午9:52:51
	 * @author：zzq
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @throws Exception
	 */
	public PageImpl<OrderVo> find(Map<String, Object> searchParams, int page, int size, boolean isPage, boolean isHasCount) throws Exception{
		//数量
		int total=0;
		//新的list
		 List<OrderVo> orderList = new ArrayList<OrderVo>();
		 PageRequest pageable = null;
		 if(isPage){
			 pageable = new PageRequest(page, size); 
		 }
		 logger.debug(searchParams.toString());
		 Map<String,Object> paras=new HashMap<String, Object>();
		 System.out.println(searchParams.size());
		 if(!searchParams.isEmpty()){
			//订单号
			 String orderId = searchParams.get("LIKE_id").toString();
			 //订单状态
			 String orderStatus = searchParams.get("EQ_status").toString();
			 //订单类型
			 String ordersType =searchParams.get("EQ_ordersType").toString();
			 //球馆名称
			 String statiumName = searchParams.get("LIKE_statiumId").toString();
			 //联系电话
			 //教练名称
			 String coachName=searchParams.get("LIKE_coachName").toString();
			 //活动名称
			 String activityName=searchParams.get("LIKE_activityName").toString();
			 //地域
			 String areaCode=searchParams.get("LIKE_areaCode").toString();
			 //按照道馆名称查询
			 if(StringUtils.isNotEmpty(statiumName)){
				 StatiumInfosCriteria statiumInfosCriteria=new StatiumInfosCriteria();
				 statiumInfosCriteria.createCriteria().andDgNameLike("%"+statiumName+"%");
				 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
				 if(statiumInfos.size()==1){
					 paras.put("statiumId", statiumInfos.get(0).getDgId().toString());
				 }
				 else if(statiumInfos.size()>1){
					 String[] infos = new String[statiumInfos.size()];
					 for(int i=0;i<infos.length;i++){
						 infos[i]=statiumInfos.get(i).getDgId().toString();
					 }
					 paras.put("statiumIdIn", infos);
				 }
				 else if(statiumInfos.size()==0){
					 paras.put("statiumId",  UUID.get());
				 }
			 }
			 
			 //按教练名字查询
			 if(StringUtils.isNotEmpty(coachName)){
				 CoachCriteria coachCriteria=new CoachCriteria();
				 CoachCriteria.Criteria cri=coachCriteria.createCriteria();
				 //对教练名字进行模糊查询
				 cri.andNameLike("%"+coachName+"%");
				 List<Coach> coachs = coachMapper.selectByExample(coachCriteria);
				 if(coachs.size()==0){
					 return new PageImpl<OrderVo>(new ArrayList<OrderVo>(), pageable, 0);
				 }
				 //集合size是1
				 if(coachs.size()==1){
						 String coachId=coachs.get(0).getId();
						 OrderItemCriteria orderItemCriteria =new OrderItemCriteria();
						 //查询时对查询数据条数进行限制
						 if(pageable!=null){
							 orderItemCriteria.setMysqlLength(pageable.getPageSize());
							 orderItemCriteria.setMysqlOffset(pageable.getOffset()); 
						 }
						 orderItemCriteria.createCriteria().andCoachIdEqualTo(coachId);
						 List<OrderItem> items= orderItemMapper.selectByExample(orderItemCriteria);
						 //计算total用来分页
						 total=orderItemMapper.countByExample(orderItemCriteria);
						 if(CollectionUtils.isNotEmpty(items)){
							 for(OrderItem orderItem:items){
								 String orderId1 = orderItem.getOrderId();
								 //Order order = orderMapper.selectByPrimaryKey(orderId1);
								 Order order = this.selectByPrimaryKey(Order.class, orderId1);
								 if(order!=null){
									 OrderVo vo   = new OrderVo();
									 StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
									 statiumInfosCriteria.createCriteria().andDgIdEqualTo(order.getStatiumId());
									 //关联coach表和statiuminfo表查找需要信息
									 //Coach coach=coachMapper.selectByPrimaryKey(orderItem.getCoachId());
									 Coach coach = this.selectByPrimaryKey(Coach.class, orderItem.getCoachId());
									 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
									 if(order.getFinalFee()!=null){
											Double finel = BigDecimal.valueOf((Integer)order.getFinalFee()).multiply(BigDecimal.valueOf(0.01)).doubleValue();
											vo.setFinalFee(finel.intValue());
										}
									 String orderDate = "";
									 String orderTime = "";
									 if(orderItem!=null){
										 		vo.setPeriodNum(1);
										 		if("1".equals(orderItem.getOrdersType())||"0".equals(orderItem.getOrdersType())){
													orderDate = DateUtil.formatDate(orderItem.getSignDate(),"yyyy-MM-dd");
												}
												String temp = "";
												if(orderItem.getStartTime()!=null){
							                 		temp = orderItem.getStartTime()+":00";
							                 	}
							                 	if(orderItem.getEndTime()!=null){
							                 		temp += "～"+orderItem.getEndTime()+":00";
							                 	}
							                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
							                 	
							                 	orderTime+=temp;
											}
									 Integer handleStatus = order.getHandleStatus();
									 String handler = order.getHandler();
									 if(handleStatus==null){
											if(handler!=null){
												vo.setHandleStatusStr("处理中");
											}
											else{
												
											}
										}
										else if(handleStatus==0){
											vo.setHandleStatusStr("未处理");
										}
										else if(handleStatus==1){
											vo.setHandleStatusStr("已处理");
										}
										if(order.getHandler()!=null){
											String handler1 = (String)order.getHandler();
											//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
											if(order.getStatus().equals("ORDER_REFUNDING")&&("app用户申请退款").equals(order.getReason())){
												SsoUser user = this.selectByPrimaryKey(SsoUser.class, order.getUserId());
												if(user!=null){
													vo.setHandleName(user.getNickName());
												}
											}
											if(order.getStatus().equals("ORDER_REFUNDED")||order.getStatus().equals("ORDER_REFUNDING")){
												User user = this.selectByPrimaryKey(User.class, handler1);
												if(user!=null){
													vo.setHandleName(user.getNickname());
												}
											}
										}
									 String userId = order.getUserId();
									 SsoUser user = this.selectByPrimaryKey(SsoUser.class, userId);
									 if(user!=null){
										//合作次数
										 OrderCriteria orderCriteria = new OrderCriteria();
										 orderCriteria.createCriteria().andUserIdEqualTo(order.getUserId());
										 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order.getCt());
										 List<String> statusList = new ArrayList<String>();
										 statusList.add("ORDER_BILLED");
										 statusList.add("ORDER_PAIED");
										 orderCriteria.createCriteria().andStatusIn(statusList);
										 int count = orderMapper.countByExample(orderCriteria);
										 vo.setCooperateNum(count);
										 vo.setPhone(user.getPhone());
										 vo.setUserName(user.getNickName());
									 }
									 vo.setStatus(order.getStatus());
									 vo.setOrdersType(order.getOrdersType());
									 vo.setCt(order.getCt());
									 vo.setOrderDate(orderDate);
									 vo.setOrderTime(orderTime);
									 if(statiumInfos.size()==1){
											String areaCode1 = statiumInfos.get(0).getAreacode();
											Map<String,String> zoneMap = Zonemap.split(areaCode1);
											vo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
											vo.setStatiumName(statiumInfos.get(0).getDgName());
											vo.setAddress(statiumInfos.get(0).getAddress());
									 }
									 vo.setId(order.getId());
									 
									 if(coach!=null){
										 vo.setCoachName(coach.getName());
									 }
									 orderList.add(vo);
								 }
							 }
							 
							 
						 }
				 }
				 //对教练名字模糊查询查询的教练集合size大于一
				 else if(coachs.size()>1){
					 for(Coach coach:coachs){
						 String coachId=coach.getId();
						 OrderItemCriteria orderItemCriteria =new OrderItemCriteria();
						 if(pageable!=null){
							 orderItemCriteria.setMysqlLength(pageable.getPageSize());
							 orderItemCriteria.setMysqlOffset(pageable.getOffset());
						 }
						 orderItemCriteria.createCriteria().andCoachIdEqualTo(coachId);
						 List<OrderItem> items= orderItemMapper.selectByExample(orderItemCriteria);
						 int total1=orderItemMapper.countByExample(orderItemCriteria);
						 total+=total1;
						 if(CollectionUtils.isNotEmpty(items)){
							 for(OrderItem orderItem:items){
								 String orderId1 = orderItem.getOrderId();
								 //Order order = orderMapper.selectByPrimaryKey(orderId1);
								 Order order = this.selectByPrimaryKey(Order.class, orderId1);
								 if(order!=null){
									 OrderVo vo   = new OrderVo();
									 vo.setPeriodNum(1);
									 StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
									 statiumInfosCriteria.createCriteria().andDgIdEqualTo(order.getStatiumId());
									 //Coach coach1=coachMapper.selectByPrimaryKey(orderItem.getCoachId());
									 Coach coach1 = this.selectByPrimaryKey(Coach.class, orderItem.getCoachId());
									 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
									 if(order.getFinalFee()!=null){
											Double finel = BigDecimal.valueOf((Integer)order.getFinalFee()).multiply(BigDecimal.valueOf(0.01)).doubleValue();
											vo.setFinalFee(finel.intValue());
										}
									 String orderDate = "";
									 String orderTime = "";
									 if(orderItem!=null){
										 if("1".equals(orderItem.getOrdersType())||"0".equals(orderItem.getOrdersType())){
												orderDate = DateUtil.formatDate(orderItem.getSignDate(),"yyyy-MM-dd");
											}
												String temp = "";
												if(orderItem.getStartTime()!=null){
							                 		temp = orderItem.getStartTime()+":00";
							                 	}
							                 	if(orderItem.getEndTime()!=null){
							                 		temp += "～"+orderItem.getEndTime()+":00";
							                 	}
							                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
							                 	
							                 	orderTime+=temp;
											}
									 Integer handleStatus = order.getHandleStatus();
									 String handler = order.getHandler();
									 if(handleStatus==null){
											if(handler!=null){
												vo.setHandleStatusStr("处理中");
											}
											else{
												
											}
										}
										else if(handleStatus==0){
											vo.setHandleStatusStr("未处理");
										}
										else if(handleStatus==1){
											vo.setHandleStatusStr("已处理");
										}
										if(order.getHandler()!=null){
											String handler1 = (String)order.getHandler();
											//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
											if(order.getStatus().equals("ORDER_REFUNDING")&&("app用户申请退款").equals(order.getReason())){
												SsoUser user = this.selectByPrimaryKey(SsoUser.class, order.getUserId());
												if(user!=null){
													vo.setHandleName(user.getNickName());
												}
											}
											if(order.getStatus().equals("ORDER_REFUNDED")||order.getStatus().equals("ORDER_REFUNDING")){
												User user = this.selectByPrimaryKey(User.class, handler1);
												if(user!=null){
													vo.setHandleName(user.getNickname());
												}
											}
										}
										String userId = order.getUserId();
										 //SsoUser user = ssoUserMapper.selectByPrimaryKey(userId);
										SsoUser user = this.selectByPrimaryKey(SsoUser.class,userId);
										 if(user!=null){
											//合作次数
											 OrderCriteria orderCriteria = new OrderCriteria();
											 orderCriteria.createCriteria().andUserIdEqualTo(order.getUserId());
											 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order.getCt());
											 List<String> statusList = new ArrayList<String>();
											 statusList.add("ORDER_BILLED");
											 statusList.add("ORDER_PAIED");
											 orderCriteria.createCriteria().andStatusIn(statusList);
											 int count = orderMapper.countByExample(orderCriteria);
											 vo.setCooperateNum(count);
											 vo.setPhone(user.getPhone());
											 vo.setUserName(user.getNickName());
										 }
									 vo.setStatus(order.getStatus());
									 vo.setOrdersType(order.getOrdersType());
									 vo.setCt(order.getCt());
									 vo.setOrderDate(orderDate);
									 vo.setOrderTime(orderTime);
									 if(statiumInfos.size()==1){
											String areaCode1 = statiumInfos.get(0).getAreacode();
											Map<String,String> zoneMap = Zonemap.split(areaCode1);
											vo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
											vo.setStatiumName(statiumInfos.get(0).getDgName());
											vo.setAddress(statiumInfos.get(0).getAddress());
									 }
									 vo.setId(order.getId());
									 
									 if(coach1!=null){
										 vo.setCoachName(coach1.getName());
									 }
									 orderList.add(vo);
								 }
							 }
							 
							 
						 }
				 
					 }
				 }
			 }
			 //按活动名称查询
			 if(StringUtils.isNotEmpty(activityName)){
				 StatiumActivityCriteria activityCriteria = new StatiumActivityCriteria();
				 StatiumActivityCriteria.Criteria cri=activityCriteria.createCriteria();
				 cri.andActivityTopicLike("%"+activityName+"%");
				 List<StatiumActivity> activityList = statiumActivityMapper.selectByExample(activityCriteria);
				 if(activityList.size()==0){
					 return new PageImpl<OrderVo>(new ArrayList<OrderVo>(), pageable, 0);
				 }
				 //活动集合size=1
				 else if(activityList.size()==1){
					 String activityId = activityList.get(0).getId();
					 OrderItemCriteria orderItemCriteria= new OrderItemCriteria();
					 if(pageable!=null){
						 orderItemCriteria.setMysqlLength(pageable.getPageSize());
						 orderItemCriteria.setMysqlOffset(pageable.getOffset()); 
					 }
					 orderItemCriteria.createCriteria().andActivityIdEqualTo(activityId);
					 List<OrderItem> items = orderItemMapper.selectByExample(orderItemCriteria);
					 total=orderItemMapper.countByExample(orderItemCriteria);
					 if(CollectionUtils.isNotEmpty(items)){
						 for(OrderItem orderItem:items){
							 String orderId1 = orderItem.getOrderId();
							 //Order order = orderMapper.selectByPrimaryKey(orderId1);
							 Order order = this.selectByPrimaryKey(Order.class,orderId1);
							 if(order!=null){
								 OrderVo vo = new OrderVo();
								 vo.setPeriodNum(1);
								 StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
								 statiumInfosCriteria.createCriteria().andDgIdEqualTo(order.getStatiumId());
								 StatiumActivity activity = null;
								 if(orderItem.getActivityId()!=null){
									  //activity = statiumActivityMapper.selectByPrimaryKey(orderItem.getActivityId());
									 activity = this.selectByPrimaryKey(StatiumActivity.class,orderItem.getActivityId());
								 }
								 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
								 if(order.getFinalFee()!=null){
										Double finel = BigDecimal.valueOf((Integer)order.getFinalFee()).multiply(BigDecimal.valueOf(0.01)).doubleValue();
										vo.setFinalFee(finel.intValue());
									}
								 String orderDate = "";
								 String orderTime = "";
								 if(orderItem!=null){
									 if("1".equals(orderItem.getOrdersType())||"0".equals(orderItem.getOrdersType())){
											orderDate = DateUtil.formatDate(orderItem.getSignDate(),"yyyy-MM-dd");
										}
											String temp = "";
											if(orderItem.getStartTime()!=null){
						                 		temp = orderItem.getStartTime()+":00";
						                 	}
						                 	if(orderItem.getEndTime()!=null){
						                 		temp += "～"+orderItem.getEndTime()+":00";
						                 	}
						                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
						                 	
						                 	orderTime+=temp;
										}
								 Integer handleStatus = order.getHandleStatus();
								 String handler = order.getHandler();
								 if(handleStatus==null){
										if(handler!=null){
											vo.setHandleStatusStr("处理中");
										}
										else{
											
										}
									}
									else if(handleStatus==0){
										vo.setHandleStatusStr("未处理");
									}
									else if(handleStatus==1){
										vo.setHandleStatusStr("已处理");
									}
									if(order.getHandler()!=null){
										String handler1 = (String)order.getHandler();
										//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
										if(order.getStatus().equals("ORDER_REFUNDING")&&("app用户申请退款").equals(order.getReason())){
											SsoUser user = this.selectByPrimaryKey(SsoUser.class, order.getUserId());
											if(user!=null){
												vo.setHandleName(user.getNickName());
											}
										}
										if(order.getStatus().equals("ORDER_REFUNDED")||order.getStatus().equals("ORDER_REFUNDING")){
											User user = this.selectByPrimaryKey(User.class, handler1);
											if(user!=null){
												vo.setHandleName(user.getNickname());
											}
										}
									}
									String userId = order.getUserId();
									 SsoUser user = this.selectByPrimaryKey(SsoUser.class,userId);
									 if(user!=null){
										//合作次数
										 OrderCriteria orderCriteria = new OrderCriteria();
										 orderCriteria.createCriteria().andUserIdEqualTo(order.getUserId());
										 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order.getCt());
										 List<String> statusList = new ArrayList<String>();
										 statusList.add("ORDER_BILLED");
										 statusList.add("ORDER_PAIED");
										 orderCriteria.createCriteria().andStatusIn(statusList);
										 int count = orderMapper.countByExample(orderCriteria);
										 vo.setCooperateNum(count);
										 vo.setPhone(user.getPhone());
										 vo.setUserName(user.getNickName());
									 }	
								 vo.setStatus(order.getStatus());
								 vo.setOrdersType(order.getOrdersType());
								 vo.setCt(order.getCt());
								 vo.setOrderDate(orderDate);
								 vo.setOrderTime(orderTime);
								 if(statiumInfos.size()==1){
										String areaCode1 = statiumInfos.get(0).getAreacode();
										Map<String,String> zoneMap = Zonemap.split(areaCode1);
										vo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
										vo.setStatiumName(statiumInfos.get(0).getDgName());
								 }
								 vo.setId(order.getId());
								 if(activity!=null){
									 vo.setActivityTopic(activity.getActivityTopic());
								 }
								 orderList.add(vo);
							 }
						 
						 }
					 }
					 
				 }
				 //活动集合size大于一
				 else if(activityList.size()>1){
					 for(StatiumActivity activity1:activityList){
					 String activityId = activity1.getId();
					 OrderItemCriteria orderItemCriteria= new OrderItemCriteria();
					 if(pageable!=null){
						 orderItemCriteria.setMysqlLength(pageable.getPageSize());
						 orderItemCriteria.setMysqlOffset(pageable.getOffset()); 
					 }
					 orderItemCriteria.createCriteria().andActivityIdEqualTo(activityId);
					 List<OrderItem> items = orderItemMapper.selectByExample(orderItemCriteria);
					 int total1=orderItemMapper.countByExample(orderItemCriteria);
					 total+=total1;
					 if(CollectionUtils.isNotEmpty(items)){
						 for(OrderItem orderItem:items){
							 String orderId1 = orderItem.getOrderId();
							 //Order order = orderMapper.selectByPrimaryKey(orderId1);
							 Order order = this.selectByPrimaryKey(Order.class,orderId1);
							 if(order!=null){
								 OrderVo vo = new OrderVo();
								 vo.setPeriodNum(1);
								 StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
								 statiumInfosCriteria.createCriteria().andDgIdEqualTo(order.getStatiumId());
								 StatiumActivity activity = null;
								 if(orderItem.getActivityId()!=null){
									  activity = this.selectByPrimaryKey(StatiumActivity.class,orderItem.getActivityId());
								 }
								 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
								 if(order.getFinalFee()!=null){
										Double finel = BigDecimal.valueOf((Integer)order.getFinalFee()).multiply(BigDecimal.valueOf(0.01)).doubleValue();
										vo.setFinalFee(finel.intValue());
									}
								 String orderDate = "";
								 String orderTime = "";
								 if(orderItem!=null){
									 if("1".equals(orderItem.getOrdersType())||"0".equals(orderItem.getOrdersType())){
											orderDate = DateUtil.formatDate(orderItem.getSignDate(),"yyyy-MM-dd");
										}
											String temp = "";
											if(orderItem.getStartTime()!=null){
						                 		temp = orderItem.getStartTime()+":00";
						                 	}
						                 	if(orderItem.getEndTime()!=null){
						                 		temp += "～"+orderItem.getEndTime()+":00";
						                 	}
						                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
						                 	
						                 	orderTime+=temp;
										}
								 Integer handleStatus = order.getHandleStatus();
								 String handler = order.getHandler();
								 if(handleStatus==null){
										if(handler!=null){
											vo.setHandleStatusStr("未处理");
										}
										else{
											
										}
									}
								 	else if(handleStatus==0){
										vo.setHandleStatusStr("未处理");
									}
									else if(handleStatus==1){
										vo.setHandleStatusStr("已处理");
									}
									if(order.getHandler()!=null){
										String handler1 = (String)order.getHandler();
										//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
										if(order.getStatus().equals("ORDER_REFUNDING")&&("app用户申请退款").equals(order.getReason())){
											SsoUser user = this.selectByPrimaryKey(SsoUser.class, order.getUserId());
											if(user!=null){
												vo.setHandleName(user.getNickName());
											}
										}
										if(order.getStatus().equals("ORDER_REFUNDED")||order.getStatus().equals("ORDER_REFUNDING")){
											User user = this.selectByPrimaryKey(User.class, handler1);
											if(user!=null){
												vo.setHandleName(user.getNickname());
											}
										}
									}
									String userId = order.getUserId();
									 SsoUser user = this.selectByPrimaryKey(SsoUser.class,userId);
									 if(user!=null){
										//合作次数
										 OrderCriteria orderCriteria = new OrderCriteria();
										 orderCriteria.createCriteria().andUserIdEqualTo(order.getUserId());
										 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order.getCt());
										 List<String> statusList = new ArrayList<String>();
										 statusList.add("ORDER_BILLED");
										 statusList.add("ORDER_PAIED");
										 orderCriteria.createCriteria().andStatusIn(statusList);
										 int count = orderMapper.countByExample(orderCriteria);
										 vo.setCooperateNum(count);
										 vo.setPhone(user.getPhone());
										 vo.setUserName(user.getNickName());
									 }
								 vo.setStatus(order.getStatus());
								 vo.setOrdersType(order.getOrdersType());
								 vo.setCt(order.getCt());
								 vo.setOrderDate(orderDate);
								 vo.setOrderTime(orderTime);
								 if(statiumInfos.size()==1){
										String areaCode1 = statiumInfos.get(0).getAreacode();
										Map<String,String> zoneMap = Zonemap.split(areaCode1);
										vo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
										vo.setStatiumName(statiumInfos.get(0).getDgName());
										vo.setAddress(statiumInfos.get(0).getAddress());
								 }
								 vo.setId(order.getId());
								 if(activity!=null){
									 vo.setActivityTopic(activity.getActivityTopic());
								 }
								 orderList.add(vo);
							 }
						 }
						 }
					 }
					 
				 
				 }
			 }
			if (orderStatus != null && CommonUtils.isNotEmpty(orderStatus)) {
					paras.put("status", orderStatus);
				}
				
			if (orderId != null && CommonUtils.isNotEmpty(orderId)) {
					paras.put("orderId", orderId);
				}
				
			if (ordersType != null && CommonUtils.isNotEmpty(ordersType)) {
				paras.put("ordersType", ordersType);
			}else{
				paras.put("ordersTypeIn", new Integer[]{0,1,2});
			}
			
			if(areaCode != null && CommonUtils.isNotEmpty(areaCode)){
	    		if(areaCode.endsWith("0000")){
	    			areaCode = areaCode.substring(0,2)+"%";
	    		}else if(areaCode.endsWith("00")){
	    			areaCode = areaCode.substring(0,4)+"%";
	    		}else{
	    			
	    		}
	        	paras.put("areaCode", areaCode);
	        }
			if (searchParams.get("GTE_ct") != null && CommonUtils.isNotEmpty((String) searchParams.get("GTE_ct"))) {
				paras.put("GTE_createTime", ((String)searchParams.get("GTE_ct"))+" 00:00:00");
			}
			
			if (searchParams.get("LTE_ct") != null && CommonUtils.isNotEmpty((String) searchParams.get("LTE_ct"))) {
				paras.put("LTE_createTime", ((String)searchParams.get("LTE_ct"))+" 23:59:59");
			}
			
			if (searchParams.get("GTE_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("GTE_et"))) {
				paras.put("GTE_editTime", ((String)searchParams.get("GTE_et"))+" 00:00:00");
			}
			
			if (searchParams.get("LTE_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("LTE_et"))) {
				paras.put("LTE_editTime", ((String)searchParams.get("LTE_et"))+" 23:59:59");
			}
			
			String sqlCount = FreeMarkerUtils.format("/template/order/order_search_count.ftl", paras);
			logger.debug(sqlCount);
			logger.debug(paras.toString());
			logger.debug(searchParams.get("LIKE_coachName")+"***"+searchParams.get("LIKE_activityName"));
			if (isHasCount&&searchParams.get("LIKE_coachName")==""&&searchParams.get("LIKE_activityName")=="") {
	        	List<Map<String,Object>> countMap = jdbcTemplate.queryForList(sqlCount);
	        	total = new BigDecimal((long)countMap.get(0).get("cont")).intValue();
			}
	        if (isPage) {
	        	if(pageable.getOffset()>= 1000){
	            	paras.put("offset", String.valueOf(pageable.getOffset()).replace(",",""));
	            }else{
	            	paras.put("offset", pageable.getOffset());
	            }
	            paras.put("pageSize", pageable.getPageSize());
	        }
	   
	        //在教练名字和活动名字为""而其他条件不为空条件下进行查询
	         
	        logger.debug(searchParams.get("EQ_coachName")+"***"+searchParams.get("EQ_activityName"));
	        if(searchParams.get("LIKE_coachName")==""&&searchParams.get("LIKE_activityName")==""){
	        	String sqlList = FreeMarkerUtils.format("/template/order/order_search.ftl", paras);
		        List<Map<String,Object>> orders = jdbcTemplate.queryForList(sqlList);
		        OrderVo vo =null;
		        for(Map<String, Object> order:orders){
		        	vo = new OrderVo();
		        	Date ct = (Date)order.get("ct");
					Date et = (Date)order.get("et");
					String id = (String)order.get("id");
					String status = (String)order.get("status");
					Integer payType = (Integer)order.get("payType");
					Integer ordersType1 = (Integer)order.get("ordersType");
					Integer verifyFlag1 = (Integer)order.get("verifyFlag");
					Integer finalFee =(Integer)order.get("finalFee");
					Integer handleStatus = (Integer)order.get("handleStatus");
					String handler = (String)order.get("handler");
					String reason =(String)order.get("reason");
					vo.setVerifyFlag(verifyFlag1);
					vo.setPeriodNum(1);
					vo.setId(id);
					vo.setCt(ct);
					vo.setEt(et);
					vo.setStatus(status);
					vo.setPayType(payType);
					vo.setFinalFee(finalFee);
					vo.setOrdersType(ordersType1);
					vo.setReason(reason);
					Integer statiumId = (Integer) order.get("statiumId");
					if(statiumId!=0){
						StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
						statiumInfosCriteria.createCriteria().andDgIdEqualTo(statiumId);
						List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
						if(statiumInfos.size()==1){
							String areaCode1 = statiumInfos.get(0).getAreacode();
							Map<String,String> zoneMap = Zonemap.split(areaCode1);
							vo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
							vo.setStatiumName(statiumInfos.get(0).getDgName());
							vo.setAddress(statiumInfos.get(0).getAddress());
						}
						else{
							break;
						}
					}
					vo.setStatiumId(statiumId);
					if(handleStatus==null){
						if(handler!=null){
							vo.setHandleStatusStr("处理中");
						}
						else{
							
						}
					}else if(handleStatus==0){
						vo.setHandleStatusStr("未处理");
					}
					else if(handleStatus==1){
						vo.setHandleStatusStr("已处理");
					}
					if(order.get("handler")!=null){
						String handler1 = (String)order.get("handler");
						//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
						if(status.equals("ORDER_REFUNDING")&&("app用户申请退款").equals(reason)){
							SsoUser user = this.selectByPrimaryKey(SsoUser.class, handler1);
							if(user!=null){
								vo.setHandleName(user.getNickName());
							}
						}
						if(status.equals("ORDER_REFUNDED")||reason.equals("ORDER_REFUNDING")){
							User user = this.selectByPrimaryKey(User.class, handler1);
							if(user!=null){
								vo.setHandleName(user.getNickname());
							}
						}
					
					}
					//查询教练名称
					OrderItemCriteria orderItemCriteria=new OrderItemCriteria();
					orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
					String coachName1="";
					List<OrderItem> items1 = orderItemMapper.selectByExample(orderItemCriteria);
					if(items1.size()!=0){
						String coachId=items1.get(0).getCoachId();
						if(coachId!=null){
							//Coach coach = coachMapper.selectByPrimaryKey(coachId);
							Coach coach = this.selectByPrimaryKey(Coach.class,coachId);
							if(coach!=null){
								coachName1=coach.getName();
							}
						}
						
					}
					else{
					}
					vo.setCoachName(coachName1);
					//查询活动名称
					OrderItemCriteria orderItemCriteria2 = new OrderItemCriteria();
					orderItemCriteria2.createCriteria().andActivityIdEqualTo(id);
					List<OrderItem> items2 = orderItemMapper.selectByExample(orderItemCriteria);
					String activityTopic="";
					if(items2.size()!=0){
						String activityId=items2.get(0).getActivityId();
						if(activityId!=null){
							//StatiumActivity activity = statiumActivityMapper.selectByPrimaryKey(activityId);
							StatiumActivity activity = this.selectByPrimaryKey(StatiumActivity.class,activityId);
							if(activity!=null){
								activityTopic=activity.getActivityTopic();
							}
						}
						else{
							activityTopic="";
						}
						vo.setActivityTopic(activityTopic);
					}
					if(order.get("finalFee")!=null){
						Double finel = BigDecimal.valueOf((Integer)order.get("finalFee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
						vo.setFinalFee(finel.intValue());
					}
					OrderItemCriteria itemCriteria = new OrderItemCriteria();
					OrderItemCriteria.Criteria itemCri = itemCriteria.createCriteria();
					itemCri.andOrderIdEqualTo(vo.getId());
					List<OrderItem> items = orderItemMapper.selectByExample(itemCriteria);
					if(CollectionUtils.isNotEmpty(items)){
						int periodNum = items.size();
						vo.setPeriodNum(periodNum);
						String orderDate = "";
						String orderTime = "";
						for(OrderItem item:items){
							if(item!=null){
								if(item.getSignDate()!=null){
									orderDate = DateUtil.formatDate(item.getSignDate(),"yyyy-MM-dd");
								}
								String temp = "";
								if(item.getStartTime()!=null){
			                 		temp = item.getStartTime()+":00";
			                 	}
			                 	if(item.getEndTime()!=null){
			                 		temp += "～"+item.getEndTime()+":00";
			                 	}
			                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
			                 	
			                 	orderTime+=temp;
							}
						}
						Order order1 = this.selectByPrimaryKey(Order.class,id);
						if(order1!=null){
							String userId = order1.getUserId();
							SsoUser user = this.selectByPrimaryKey(SsoUser.class,userId);
							 if(user!=null){
								//合作次数
								 OrderCriteria orderCriteria = new OrderCriteria();
								 orderCriteria.createCriteria().andUserIdEqualTo(order1.getUserId());
								 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order1.getCt());
								 List<String> statusList = new ArrayList<String>();
								 statusList.add("ORDER_BILLED");
								 statusList.add("ORDER_PAIED");
								 orderCriteria.createCriteria().andStatusIn(statusList);
								 int count = orderMapper.countByExample(orderCriteria);
								 vo.setCooperateNum(count);
								 vo.setPhone(user.getPhone());
								 vo.setUserName(user.getNickName());
							 }
						}
						
						vo.setOrderTime(orderTime);
						vo.setOrderDate(orderDate);
					}
					orderList.add(vo);
	        }
	        
	        }
	        
		 }
		 /**
		  * 没有查询条件下进行查询
		  */
		 else if(searchParams.isEmpty()){
			 String sqlCount = FreeMarkerUtils.format("/template/order/order_search_count.ftl", paras);
		     logger.debug(sqlCount);
			 if (isHasCount) {
		        	List<Map<String,Object>> countMap = jdbcTemplate.queryForList(sqlCount);
		        	total = new BigDecimal((long)countMap.get(0).get("cont")).intValue();
				}
			 if (isPage) {
		        	if(pageable.getOffset()>= 1000){
		            	paras.put("offset", String.valueOf(pageable.getOffset()).replace(",",""));
		            }else{
		            	paras.put("offset", pageable.getOffset());
		            }
		            paras.put("pageSize", pageable.getPageSize());
		        }
		        String sqlList = FreeMarkerUtils.format("/template/order/order_search.ftl", paras);
		        List<Map<String,Object>> orders = jdbcTemplate.queryForList(sqlList);
		        OrderVo vo =null;
		        for(Map<String, Object> order:orders){
		        	vo = new OrderVo();
		        	Date ct = (Date)order.get("ct");
					Date et = (Date)order.get("et");
					String id = (String)order.get("id");
					String status = (String)order.get("status");
					Integer payType = (Integer)order.get("payType");
					Integer ordersType1 = (Integer)order.get("ordersType");
					/*Integer verifyFlag1 = (Integer)order.get("verifyFlag");*/
					Integer finalFee = (Integer)order.get("finalFee");
					String reason = (String)order.get("reason");
					/*vo.setVerifyFlag(verifyFlag1);*/
					vo.setId(id);
					vo.setCt(ct);
					vo.setEt(et);
					vo.setStatus(status);
					vo.setPayType(payType);
					vo.setOrdersType(ordersType1);
					vo.setFinalFee(finalFee);
					 vo.setPeriodNum(1);
					Integer statiumId = (Integer) order.get("statiumId");
					//查询道馆名称
				if (statiumId != 0) {
					StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
					statiumInfosCriteria.createCriteria().andDgIdEqualTo(
							statiumId);
					List<StatiumInfos> statiumInfos = statiumInfosMapper
							.selectByExample(statiumInfosCriteria);
					if (statiumInfos.size() == 1) {
						String areaCode = statiumInfos.get(0).getAreacode();
						Map<String, String> zoneMap = Zonemap.split(areaCode);
						vo.setAreaStr(zoneMap.get("province")
								+ zoneMap.get("city") + zoneMap.get("area"));
						vo.setStatiumName(statiumInfos.get(0).getDgName());
						vo.setAddress(statiumInfos.get(0).getAddress());
					} else {
						break;
					}
				}
					vo.setStatiumId(statiumId);
					//查询教练名称
					OrderItemCriteria orderItemCriteria=new OrderItemCriteria();
					orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
					String coachName="";
					List<OrderItem> items1 = orderItemMapper.selectByExample(orderItemCriteria);
					if(items1.size()!=0){
						String coachId=items1.get(0).getCoachId();
						if(coachId!=null){
							//Coach coach = coachMapper.selectByPrimaryKey(coachId);
							Coach coach = this.selectByPrimaryKey(Coach.class, coachId);
							if(coach!=null){
								coachName=coach.getName();
							}
						}
						
					}
					else{
						coachName="";
					}
					vo.setCoachName(coachName);
					//查询活动名称
					OrderItemCriteria orderItemCriteria2 = new OrderItemCriteria();
					orderItemCriteria2.createCriteria().andActivityIdEqualTo(id);
					List<OrderItem> items2 = orderItemMapper.selectByExample(orderItemCriteria);
					String activityTopic="";
				if (items2.size() != 0) {
					String activityId = items2.get(0).getActivityId();
					if (activityId != null) {
						//StatiumActivity activity = statiumActivityMapper.selectByPrimaryKey(activityId);
						StatiumActivity activity = this.selectByPrimaryKey(StatiumActivity.class, activityId);
						if (activity != null) {
							activityTopic = activity.getActivityTopic();
						}
					} else {
						activityTopic = "";
					}
					vo.setActivityTopic(activityTopic);
				}
					
					if(order.get("finalFee")!=null){
						Double finel = BigDecimal.valueOf((Integer)order.get("finalFee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
						vo.setFinalFee(finel.intValue());
					}
					OrderItemCriteria itemCriteria = new OrderItemCriteria();
					OrderItemCriteria.Criteria itemCri = itemCriteria.createCriteria();
					itemCri.andOrderIdEqualTo(vo.getId());
					List<OrderItem> items = orderItemMapper.selectByExample(itemCriteria);
					if(CollectionUtils.isNotEmpty(items)){
						String orderDate = "";
						String orderTime = "";
						for(OrderItem item:items){
							if(item!=null){
								if(item.getSignDate()!=null){
									orderDate = DateUtil.formatDate(item.getSignDate(),"yyyy-MM-dd");
								}
								
								String temp = "";
								if(item.getStartTime()!=null){
			                 		temp = item.getStartTime()+":00";
			                 	}
			                 	if(item.getEndTime()!=null){
			                 		temp += "～"+item.getEndTime()+":00";
			                 	}
			                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
			                 	
			                 	orderTime+=temp;
							}
						}
						vo.setOrderTime(orderTime);
						vo.setOrderDate(orderDate);
					}
					Integer handleStatus = (Integer)order.get("handleStatus");
					if(handleStatus==null){
						if(order.get("handler")!=null){
							vo.setHandleStatusStr("处理中");
						}else{
							
						}
					}else if(handleStatus==0){
						vo.setHandleStatusStr("未处理");
					}
					
					else if(handleStatus==1){
						vo.setHandleStatusStr("已处理");
					}
					
						if(order.get("handler")!=null){
							String handler1 = (String)order.get("handler");
							//app用户申请退款 处理人是用户  oa用户申请退款处理人是oa用户
							if(status.equals("ORDER_REFUNDING")&&reason.equals("app用户申请退款")){
								SsoUser user = this.selectByPrimaryKey(SsoUser.class, handler1);
								if(user!=null){
									vo.setHandleName(user.getNickName());
								}
							}
							if(status.equals("ORDER_REFUNDED")||reason.equals("ORDER_REFUNDING")){
								User user = this.selectByPrimaryKey(User.class, handler1);
								if(user!=null){
									vo.setHandleName(user.getNickname());
								}
							}
						
						}
					 Order order1 = this.selectByPrimaryKey(Order.class,id);
					 String userId = order1.getUserId();
					 SsoUser user = this.selectByPrimaryKey(SsoUser.class,userId);
					 if(user!=null){
						 //合作次数
						 OrderCriteria orderCriteria = new OrderCriteria();
						 orderCriteria.createCriteria().andUserIdEqualTo(order1.getUserId());
						 orderCriteria.createCriteria().andCtLessThanOrEqualTo(order1.getCt());
						 List<String> statusList = new ArrayList<String>();
						 statusList.add("ORDER_BILLED");
						 statusList.add("ORDER_PAIED");
						 orderCriteria.createCriteria().andStatusIn(statusList);
						 int count = orderMapper.countByExample(orderCriteria);
						 vo.setCooperateNum(count);
						 vo.setPhone(user.getPhone());
						 vo.setUserName(user.getNickName());
					 }
					orderList.add(vo);
		        }
		        
			 
		        
		 } 	
		 
		 return new PageImpl<>(orderList, pageable, total);
		 
	}
	
	/**
	 * 
	 * <订单详情><功能具体实现>
	 *
	 * @create：2016年8月16日 上午9:52:17
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public OrderVo orderDetail(String id) {
		OrderVo orderVo = new OrderVo();
		if (CommonUtils.isNotEmpty(id)) {
			try {
				Order order = this.selectByPrimaryKey(Order.class,id);
				if (order == null) {
					return null;
				}
				BeanUtils.copyProperties(order, orderVo);
			} catch (Exception e) {
				logger.error("根据id获取订单"+e.getMessage());
			}
		}
		int finalFee = orderVo.getFinalFee();
		orderVo.setFinalFee(finalFee/100);
		//对于app申请的退款 退款原因写 app用户申请退款
		String reason = orderVo.getReason();
		// modify by sl 2016-11-23 针对申请退款用户
		if(StringUtils.isBlank(reason) && Constants.OrderStatus.STATUS_REFUNDING.equals(orderVo.getStatus())){
			orderVo.setReason("app用户申请退款");
		}
		String userId = orderVo.getUserId();
		SsoUser user;
		try {
			user = this.selectByPrimaryKey(SsoUser.class, userId);
			if (user != null) {
				orderVo.setUserName(user.getNickName());
				orderVo.setPhone(user.getPhone());
				logger.debug(id);
			}
		} catch (Exception e) {
			logger.error("根据id查用户"+e.getMessage());
		}
		return orderVo;
	}
	
	/**
	 * 
	 * <课程订单详情><功能具体实现>
	 *
	 * @create：2016年8月5日 下午2:11:18
	 * @author：zzq
	 * @param id
	 * @param dgId
	 * @return
	 */
	public StatiumInfosVo statiumInfosDetail(String id, Integer dgId) {
		StatiumInfosVo statiumInfos = new StatiumInfosVo();
		// 查询课程订单详细信息
		OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
		orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
		List<OrderItem> orderItems = orderItemMapper
				.selectByExample(orderItemCriteria);
		if (orderItems.size() == 1) {
			try {
				Date date = orderItems.get(0).getSignDate();
				String startTime = orderItems.get(0).getStartTime();
				String endTime = orderItems.get(0).getEndTime();
				String newDate = DateUtils.formatDate(date, "yyyy-MM-dd") + "  "
						+ startTime + "～" + endTime;
				statiumInfos.setSignDate(newDate);
				Coach coach = null;
				if(orderItems.get(0).getCoachId()!=null){
					coach = this.selectByPrimaryKey(Coach.class,orderItems.get(0)
							.getCoachId());
				}
				String coachName = "";
				String coachTel = "";
				if (coach != null) {
					coachName = coach.getName();
					coachTel = coach.getPhone();
				}
				StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
				statiumInfosCriteria.createCriteria().andDgIdEqualTo(dgId);
				List<StatiumInfos> statiumInfosList = statiumInfosMapper
						.selectByExample(statiumInfosCriteria);
				if (statiumInfosList.size() != 0) {
					statiumInfos.setDgName(statiumInfosList.get(0).getDgName());
					statiumInfos.setTel(statiumInfosList.get(0).getTel());
					statiumInfos.setAddress(statiumInfosList.get(0).getAddress());
				}
				statiumInfos.setCoachName(coachName);
				statiumInfos.setCoachPhone(coachTel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("课程订单详情"+e.getMessage());
			}

		}
		return statiumInfos;
	}
	
	/**
	 * 
	 * <活动订单详情><功能具体实现>
	 *
	 * @create：2016年8月5日 下午2:12:19
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public StatiumActivity findActivityDetail(String id) {
		StatiumActivity statiumActivity = null;
		OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
		orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
		List<OrderItem> orderItems = orderItemMapper
				.selectByExample(orderItemCriteria);
		try {
			if (orderItems.size() != 0) {
				String activityId = orderItems.get(0).getActivityId();
				if(activityId!=null){
					this.selectByPrimaryKey(StatiumActivity.class, activityId);
					statiumActivity = this
							.selectByPrimaryKey(StatiumActivity.class,activityId);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("课程订单详情"+e.getMessage());
		}
		return statiumActivity;
	}
	
	/**
	 * 
	 * <会员卡充值订单详情><功能具体实现>
	 *
	 * @create：2016年12月2日 上午10:14:48
	 * @author：zzq
	 * @param orderId
	 * @return
	 */
	public RechargeOrderVo findRechargeDetail(String orderId){
		OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
		orderItemCriteria.createCriteria().andOrderIdEqualTo(orderId);
		List<OrderItem> items = orderItemMapper.selectByExample(orderItemCriteria);
		RechargeOrderVo vo = null;
		if(items!=null){
			vo = new RechargeOrderVo();
			OrderItem orderItem = items.get(0);
			//卡片名称
			String cardId = orderItem.getCardId();
			if(cardId!=null){
				CrmCard crmCard = null;
				try {
					crmCard = this.selectByPrimaryKey(CrmCard.class, cardId);
				} catch (Exception e) {
					logger.error("查询卡片"+e.getMessage());
				}
				if(crmCard!=null){
					vo.setCardName(crmCard.getCardName());
					vo.setFinalFee(crmCard.getCardAmount()/100);
					vo.setGiftFee(crmCard.getCardGift()/100);
					vo.setCardType(crmCard.getCardType());
					Integer dgId = crmCard.getStatiumId();
					StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
					statiumInfosCriteria.createCriteria().andDgIdEqualTo(dgId);
					List<StatiumInfos> statiumList = statiumInfosMapper.selectByExample(statiumInfosCriteria);
					if(statiumList!=null){
						vo.setStatiumName(statiumList.get(0).getDgName());
					}
				}
			}
			vo.setCardBuyDate(DateUtils.formatDate(orderItem.getCardBuyDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		return vo;
	}
	
	/**
	 * 
	 * <订单确认退款><功能具体实现>
	 *
	 * @create：2016年8月30日 下午5:22:18
	 * @author：zzq
	 * @param id
	 * @param order
	 * @return
	 */
	@Transactional(readOnly=false)
	public void verifyOrder(String id,Order order){
		try {
			order.setStatus(Constants.OrderStatus.STATUS_REFUNNED);
			order.setEt(new Date());
			this.updateByPrimaryKeySelective(order, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * <退款至用户账号><功能具体实现>
	 *
	 * @create：2016年8月31日 下午6:29:36
	 * @author：zzq
	 * @param order
	 * @param orderId
	 * @return
	 */
	@Transactional(readOnly=false)
	public SsoUserAccount refundAccount(Order order,String orderId){
		SsoUserAccount ssoUserAccount=null;
		String userId = order.getUserId();
		try {
			if(CommonUtils.isNotEmpty(userId)){
				ssoUserAccount = this.selectByPrimaryKey(SsoUserAccount.class,userId);
				if(ssoUserAccount==null){
					ssoUserAccount = new SsoUserAccount();
					ssoUserAccount.setUserId(userId);
					//冻结状态 存0
					ssoUserAccount.setIsfreeze(0);
					Integer finalFee = order.getFinalFee();
					ssoUserAccount.setBalance(finalFee);
					ssoUserAccount.setCb(userId);
					ssoUserAccount.setCt(new Date());
					ssoUserAccount.setEb(userId);
					ssoUserAccount.setEt(new Date());
					this.insertSelective(ssoUserAccount, userId);
				}
				else{
					Integer finalFee = order.getFinalFee();
					Integer balance = ssoUserAccount.getBalance();
					balance+=finalFee;
					ssoUserAccount.setBalance(balance);
					ssoUserAccount.setEb(userId);
					ssoUserAccount.setEt(new Date());
					this.updateByPrimaryKeySelective(ssoUserAccount, userId);
				}
				//改变订单的编辑人和编辑时间
				order.setEb(SessionUtil.currentUserId());
				order.setEt(new Date());
				this.updateByPrimaryKeySelective(order, order.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("退款至用户账号"+e.getMessage());
		}
			return ssoUserAccount;
	}
	
	/**
	 * 
	 * <对用户账单流水表进行操作><功能具体实现>
	 *
	 * @create：2016年8月31日 下午6:29:52
	 * @author：zzq
	 * @param order
	 * @param orderId
	 * @return
	 */
	@Transactional(readOnly=false)
	public List<SsoUserAccountLog> addAccountLog(Order order,String orderId){
		String userId = order.getUserId();
		String description = "退款至钱包";
		List<SsoUserAccountLog> newLogList = null;
		try {
			SsoUserAccount ssoUserAccount = this.selectByPrimaryKey(SsoUserAccount.class,userId);
			SsoUserAccountLogCriteria ssoUserAccountLogCriteria = new SsoUserAccountLogCriteria();
			ssoUserAccountLogCriteria.setOrderByClause("ct desc");
			ssoUserAccountLogCriteria.createCriteria().andUserIdEqualTo(userId);
			List<SsoUserAccountLog> logList = ssoUserAccountLogMapper.selectByExample(ssoUserAccountLogCriteria);
			SsoUserAccountLog accountLog = new SsoUserAccountLog();
			accountLog.setId(UUID.get());
			accountLog.setUserId(userId);
			accountLog.setType(3);
			accountLog.setAmount(order.getFinalFee());
			accountLog.setDescription(description);
			if(logList.size()==0){
				accountLog.setBalance(order.getFinalFee());
			}
			else{
				accountLog.setBalance(ssoUserAccount.getBalance());
			}
			accountLog.setStatus(1);
			accountLog.setCt(new Date());
			accountLog.setOrderId(orderId);
			this.insert(accountLog, UUID.get());
			logList.add(accountLog);
			newLogList = ssoUserAccountLogMapper.selectByExample(ssoUserAccountLogCriteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("账户流水列表展示"+e.getMessage());
		}
		return newLogList;
	}
	
	/**
	 * 
	 * <获取订单流水日志><功能具体实现>
	 *
	 * @create：2016年9月1日 上午9:37:01
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public List<OrderLog> getOrderLogList(String id){
		OrderLogCriteria orderLogCriteria = new OrderLogCriteria();
		orderLogCriteria.createCriteria().andOrderIdEqualTo(id).andActionNotEqualTo(Constants.OrderLog.ACTION_PREPAY);
		//展示的时候以创建时间倒序排序
		orderLogCriteria.setOrderByClause("ct desc");
		//创建人以姓名形式展示
		//先判断创建人是用户还是客户
		List<OrderLog> orderLogList = orderLogMapper.selectByExample(orderLogCriteria);
		try {
			for(OrderLog orderLog:orderLogList){
				if(orderLog.getAction().equals(Constants.OrderLog.ACTION_REFUNDED)||orderLog.getAction().equals(Constants.OrderLog.ACTION_REFUNDING)){
					if(orderLog.getUserId()!=null){
						User user = this.selectByPrimaryKey(User.class, orderLog.getUserId());
						if(user!=null){
							orderLog.setUserId(user.getNickname());
						}else{
							//app用户申请退款  操作者是客户
							SsoUser ssoUser = this.selectByPrimaryKey(SsoUser.class, orderLog.getUserId());
							if(ssoUser!=null){
								orderLog.setUserId(ssoUser.getNickName());
							}
						}
					}
				} else if(orderLog.getAction().equals(Constants.OrderLog.ACTION_SIGN)){
					orderLog.setUserId("道馆");
				}
				else{
					SsoUser ssoUser = this.selectByPrimaryKey(SsoUser.class, orderLog.getUserId());
					if(ssoUser!=null){
						orderLog.setUserId(ssoUser.getNickName());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("订单流水展示"+e.getMessage());
		}
		return orderLogList;
	}
	
	/**
	 * 
	 * <增加订单流水表的退款流水><功能具体实现>
	 *
	 * @create：2016年9月1日 上午11:00:07
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false)
	public void addVerifyLog(String id){
		try {
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderId(id);
			orderLog.setComment("退款成功");
			orderLog.setId(UUID.get());
			orderLog.setAction(Constants.OrderLog.ACTION_REFUNDED);
			orderLog.setCt(new Date());
			User user = SessionUtil.currentUser();
			orderLog.setUserId(user.getUserId());
			this.insert(orderLog, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("增加退款流水"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <订单处理人和处理状态改变><功能具体实现>
	 *
	 * @create：2016年9月1日 下午12:10:16
	 * @author：zzq
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void changeHandler(String id){
		try {
			Order order = orderMapper.selectByPrimaryKey(id);
			if(order.getStatus().equals(Constants.OrderStatus.STATUS_REFUNNED)){
				order.setEb(SessionUtil.currentUserId());
				//处理人为当前用户
				order.setHandler(SessionUtil.currentUserId());
				//该状态为已处理
				order.setHandleStatus(1);
			}
			this.updateByPrimaryKeySelective(order, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("退款操作改变处理人处理状态"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <增加订单流水中的申请退款流水><功能具体实现>
	 *
	 * @create：2016年9月7日 下午3:02:09
	 * @author：zzq
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void addApplyLog(String id){
		try {
			OrderLog orderLog = new OrderLog();
			OrderLogCriteria orderLogCriteria = new OrderLogCriteria();
			orderLogCriteria.createCriteria().andActionEqualTo(Constants.OrderLog.ACTION_REFUNDING).andOrderIdEqualTo(id);
			List<OrderLog> orderLogList = orderLogMapper.selectByExample(orderLogCriteria);
			if(orderLogList.size()==0){
				String orderLogId = UUID.get();
				orderLog.setOrderId(id);
				orderLog.setComment("申请退款");
				orderLog.setId(orderLogId);
				orderLog.setAction(Constants.OrderLog.ACTION_REFUNDING);
				orderLog.setCt(new Date());
				User user = SessionUtil.currentUser();
				orderLog.setUserId(user.getUserId());
				this.insertSelective(orderLog,orderLogId);
			}
		} catch (Exception e) {
			logger.error("订单流水"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <与当前时间比较，若当前时间与预定时间比较不大于一天，返回false，不能申请退款><功能具体实现>
	 *
	 * @create：2016年9月8日 下午2:31:46
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public boolean comparedToNow(String id){
		boolean flag = true;
		OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
		orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
		List<OrderItem> itemList = orderItemMapper.selectByExample(orderItemCriteria);
		if(itemList.size()==1){
			OrderItem orderItem = itemList.get(0);
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            logger.debug("hour {}", hour);
            //上课时间
            //同一天退款 判断小时相减大于0
            /*if(( DateUtils.formatDate(new Date(), "yyyy-MM-dd")).equals(DateUtils.formatDate(orderItem.getSignDate(), "yyyy-MM-dd"))){
                return false;
                }*/
            //判断小时相差小于0或者预定日期超过当前时间
			long newDate = new Date().getTime();
			long Date = orderItem.getSignDate().getTime();
			long between = Date -newDate;
            if(between<24*(3600000)){
            	return false;
			}
            
		
		//获得两个日期之间相差的分钟数。如果小于60*24，则不能进行退款
		//当前时间
		/*Date now = new Date();
		logger.debug("当前时间"+now.getTime()+"预定时间"+startTime.getTime());
		int minus = DateUtils.intervalDay(startTime, now);
		if(minus/60<24){
			flag = false;
		}*/
		}
		return flag;
	}
	
	/**
	 * 
	 * <导出excel封装orderVo><功能具体实现>
	 *
	 * @create：2016年9月13日 下午4:26:07
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public OrderVo getOrderVo(String id){
		OrderVo orderVo = new OrderVo();
		try {
			Order order = this.selectByPrimaryKey(Order.class, id);
			//订单号id
			String orderId = order.getId();
			orderVo.setId(orderId);
			//教练名
			//查询教练名称
			OrderItemCriteria orderItemCriteria=new OrderItemCriteria();
			orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
			String coachName="";
			List<OrderItem> items1 = orderItemMapper.selectByExample(orderItemCriteria);
			if(items1.size()!=0){
				String coachId=items1.get(0).getCoachId();
				if(coachId!=null){
					Coach coach = coachMapper.selectByPrimaryKey(coachId);
					if(coach!=null){
						coachName=coach.getName();
					}
				}
				
			}
			else{
				coachName="";
			}
			orderVo.setCoachName(coachName);
			//用户名
			 String userId = order.getUserId();
			 SsoUser user = ssoUserMapper.selectByPrimaryKey(userId);
			 if(user!=null){
				 //用户联系电话
				 orderVo.setPhone(user.getPhone());
				 //用户名
				 orderVo.setUserName(user.getNickName());
			 }
			 Integer dgId = order.getStatiumId();
			 StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			 statiumInfosCriteria.createCriteria().andDgIdEqualTo(dgId);
			 List<StatiumInfos> statiumInfos = statiumInfosMapper.selectByExample(statiumInfosCriteria);
			 if(statiumInfos.size()==1){
					String areaCode1 = statiumInfos.get(0).getAreacode();
					//道馆名称地域地址
					Map<String,String> zoneMap = Zonemap.split(areaCode1);
					orderVo.setAreaStr(zoneMap.get("province")+zoneMap.get("city")+zoneMap.get("area"));
					orderVo.setStatiumName(statiumInfos.get(0).getDgName());
					orderVo.setAddress(statiumInfos.get(0).getAddress());
			 }
			 //预定日期 预定时间
			 String orderDate = "";
			 String orderTime = "";
			 OrderItemCriteria orderItemCriteria1 = new OrderItemCriteria();
			 orderItemCriteria.createCriteria().andOrderIdEqualTo(id);
			 List<OrderItem> itemList = orderItemMapper.selectByExample(orderItemCriteria1);
			 if(itemList!=null){
				 		OrderItem orderItem = itemList.get(0);
				 		if("1".equals(orderItem.getOrdersType())||"0".equals(orderItem.getOrdersType())){
							orderDate = DateUtil.formatDate(orderItem.getSignDate(),"yyyy-MM-dd");
						}
						String temp = "";
						if(orderItem.getStartTime()!=null){
	                 		temp = orderItem.getStartTime()+":00";
	                 	}
	                 	if(orderItem.getEndTime()!=null){
	                 		temp += "～"+orderItem.getEndTime()+":00";
	                 	}
	                 	//String spaceName = item.getSpaceName()+"【"+item.getSpaceCode()+"】";
	                 	
	                 	orderTime+=temp;
					}
			 orderVo.setOrderDate(orderDate);
			 orderVo.setOrderTime(orderTime);
			 //订单类型
			 String status = order.getStatus();
			 if(status.equals("ORDER_BILLED")){
				 orderVo.setStatus(Constants.OrderStatus.STATUS_BILLED);
			 }
			 if(status.equals("ORDER_CANCELED")){
				 orderVo.setStatus(Constants.OrderStatus.STATUS_CANCELED);
			 }
			 if(status.equals("ORDER_REFUNDING")){
				 orderVo.setStatus(Constants.OrderStatus.STATUS_REFUNDING);
			 }
			 if(status.equals("ORDER_REFUNDED")){
				 orderVo.setStatus(Constants.OrderStatus.STATUS_REFUNNED);
			 }
			 if(status.equals("ORDER_PAIED")){
				 orderVo.setStatus(Constants.OrderStatus.STATUS_PAIED);
			 }
			 //支付类型
			 Integer payType = order.getPayType();
			 if(payType!=null){
				 if(payType==1){
					 //String payType1 = Constants.PayType.ALIPAY;
					 orderVo.setPayType1("支付宝支付");
				 }
				 if(payType==2){
					 //String payType2 = Constants.PayType.WEIXINPAY;
					 orderVo.setPayType1("微信支付");
				 }
				 if(payType==3){
					 //String payType2 = Constants.PayType.ACCOUNTPAY;
					 orderVo.setPayType1("会员卡账户支付");
				 }
			 }
			 //订单类型 
			 Integer orderType = order.getOrdersType();
			 String orderType1 ="课程";
			 String orderType2 ="活动";
			 if(orderType==0){
				 orderVo.setOrderType(orderType1);
			 }
			 if(orderType==1){
				 orderVo.setOrderType(orderType2);
			 }
			 //实付费用
			 Integer finalFee = order.getFinalFee();
			 orderVo.setFinalFee(finalFee/100);
			 //订单创建时间
			 Date ct = order.getCt();
			 orderVo.setCt(ct);
			 //订单编辑时间
			 Date et = order.getEt();
			 orderVo.setEt(et);
			 //退款原因
			 String reason = order.getReason();
			 orderVo.setReason(reason);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderVo;
		
	}
	
	/**
	 * 
	 * <退款后体验次数+1><功能具体实现>
	 *
	 * @create：2016年9月21日 上午10:55:20
	 * @author：zzq
	 * @param userId
	 */
	@Transactional(readOnly=false)
	public SsoUser addExperience(String userId,Order order){
		SsoUser user = ssoUserMapper.selectByPrimaryKey(userId);
		int experience = user.getExperience();
		experience++;
		user.setExperience(experience);
		try {
			this.updateByPrimaryKeySelective(user, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("次数+1"+e.getMessage());
		}
		return user;
	}
	
	/**
	 * 
	 * <申请退款删除报名人数><功能具体实现>
	 *
	 * @create：2016年9月22日 上午10:58:27
	 * @author：zzq
	 * @param order
	 */
	@Transactional(readOnly=false)
	public void deleteMember(Order order){
		String orderId = order.getId();
		Integer orderType = order.getOrdersType();
		if(orderType==0){
			//课程订单
			StatiumClassMemberCriteria statiumClassMemberCriteria = new StatiumClassMemberCriteria();
			statiumClassMemberCriteria.createCriteria().andOrderIdEqualTo(orderId);
			List<StatiumClassMember> memberList = statiumClassMemberMapper.selectByExample(statiumClassMemberCriteria);
			String id ="";
			if(memberList!=null){
				 id = memberList.get(0).getId();
			}
			try {
				this.deleteByPrimaryKey(StatiumClassMember.class, id);
			} catch (Exception e) {
				logger.debug("删除报名人数"+e.getMessage());
			}
		}
		if(orderType==1){
			//活动订单
			StatiumActivityMemberCriteria statiumActivityMemberCriteria = new StatiumActivityMemberCriteria();
			statiumActivityMemberCriteria.createCriteria().andOrderIdEqualTo(orderId);
			List<StatiumActivityMember> memberList = statiumActivityMemberMapper.selectByExample(statiumActivityMemberCriteria);
			String id = "";
			if(memberList!=null){
				id = memberList.get(0).getId();
			}
			try {
				this.deleteByPrimaryKey(StatiumActivityMember.class, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("活动删除报名人数"+e.getMessage());
			}
		}
		
	}
	
	/**
	 * 
	 * <修改订单><功能具体实现>
	 *
	 * @create：2016年10月19日 下午6:01:32
	 * @author：zzq
	 * @param order
	 */
	@Transactional(readOnly=false)
	public void updateOrder(Order order,String reason){
		try {
			//修改订单状态保存原因
			order.setEb(SessionUtil.currentUserId());
			order.setEt(new Date());
			order.setReason(reason);
			order.setStatus(Constants.OrderStatus.STATUS_REFUNDING);
			//改变处理人和处理状态 处理人是当前用户 处理状态为处理中
			order.setHandler(SessionUtil.currentUserId());
			this.updateByPrimaryKeySelective(order, order.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("修改订单"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <确认退款至微信or支付宝><功能具体实现>
	 *
	 * @create：2016年12月7日 下午2:18:30
	 * @author：zzq
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String,Object> refundToAliWeixin(Order order){
		// 批次号
		String batch_no = "";
		//评论
		String comment = "";
		// 获取组织账户信息
		String orgCode = "master";
		boolean b = false;
		Map<String, Object> result = new HashMap<String,Object>();;
		try {
			Organization organization = payParameter.payKeys(orgCode);
			logger.debug("公司名称"+organization.getOrgName());
			// 支付宝退款
			// modify by sl 2016-12-10
			if (Constants.PayType.ALIPAY == order.getPayType()) {
				logger.debug("***进入***");
				StringBuffer detail = new StringBuffer();
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(order);
				for (Order or : orderList) {
					logger.debug("订单id"+or.getId());
					detail.append(or.getNumber());
					detail.append("^");
					if (or.getFinalFee() != null) {
//						String finalFee = "0.01";
//						detail.append(finalFee);
						//detail.append(1/100.0D);
						detail.append((or.getFinalFee() / 100.0D));
					} else {
						//String finalFee = "0.01";
						//detail.append(finalFee);
						detail.append((or.getFee() / 100.0D));
					}
					if (StringUtils.isEmpty(or.getReason())) {
						detail.append("^协商退款#");
					} else {
						detail.append("^");
						detail.append(or.getReason());
						detail.append("#");
					}
				}
				// 退款详细数据
				String detailData = detail.substring(0, detail.length() - 1);
				logger.debug(detailData);
				try {
					batch_no = Sequence.getId();
				} catch (Exception e) {
					logger.error("利用时间戳生成订单ID或者流水ID发生错误"+e.getMessage());
				}
				Map<String, Object> res = alipayRefund(orderList, detailData, batch_no, organization);
				if (res == null || res.get(Constants.Result.RESULT) == null || false == (Boolean) res.get(Constants.Result.RESULT)) {
					comment = "退款失败，支付宝退款失败！";
					logger.debug(comment);
				} else {
					order.setRefundBatchNo(batch_no); // 保存退款批次号
					result.put(Constants.Result.RESULT, true);
					logger.debug(result.toString());
					b = true;
				}
				// modify by sl 2016-12-10
			} else if (Constants.PayType.WEIXINPAY == order.getPayType()) {// 微信退款
				Map<String, Object> res = wxapRefund(order, organization);
				if (res == null || res.get(Constants.Result.RESULT) == null || false == (Boolean) res.get(Constants.Result.RESULT)) {
					comment = "退款失败，微信退款失败！";
				} else {
					//退款批次号
					batch_no = (String) res.get("out_trade_no");
					order.setRefundBatchNo(batch_no);
					
					b = true;
					result.put(Constants.Result.RESULT, true);
				}
			}else {
				comment = "退款失败，无法找到订单的付款方式！";
			}

			result.put(Constants.Result.REASON, comment);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//退款成功后的操作
		if (order != null) {
			if (b) {
				//记录订单日志 以及订单支付日志
				returnOrderLog(SessionUtil.currentUserId(), order, "退款成功！", true, batch_no, Constants.OrderLog.ACTION_REFUNDED, Constants.PayStatus.REFUND_SUCCESS);
				// 退款成功之后的动作
				SsoUser user = ssoUserMapper.selectByPrimaryKey(order.getUserId());
				String phone = user.getPhone();
				
				int isExperience = order.getIsExperience();
				if(isExperience==1&&order.getOrdersType()==0){
					this.addExperience(order.getUserId(),order);
				}
				
				try {
					//修改订单
					order.setStatus(Constants.OrderStatus.STATUS_REFUNNED);
					order.setEt(new Date());
					order.setEb(SessionUtil.currentUserId());
					//处理人为当前用户
					order.setHandler(SessionUtil.currentUserId());
					//该状态为已处理
					order.setHandleStatus(1);
					this.updateByPrimaryKeySelective(order, order.getId());
					// orderMapper.updateByPrimaryKeySelective(order);
					//课程活动人数-1
					//对于退款的订单 活动订单报名人数-1 课程订单参加人数-1
					this.deleteMember(order);

					// 给用户发短息
					StringBuffer msg = new StringBuffer();
					msg.append("尊敬的用户，您的订单号：");
					msg.append(order.getId());
					msg.append(" 已成功退款，请注意查收，最晚三个工作日到账！");
					messageUtil.sendSms(phone, msg.toString());
				} catch (Exception e) {
					logger.error("退款时，退款成功后修改订单状态或者发送短信失败！订单ID：" + order.getId(), e);
				}
			} 
		}
	
		return result;
	}
	
	/**
	 * 支付宝退款
	 * 
	 * @param orderList
	 * @param detail
	 * @return
	 */
	public Map<String, Object> alipayRefund(List<Order> orderList, String detail, String batch_no, Organization organization) {
		logger.debug("进入支付宝退款");
		Map<String, Object> result = new HashMap<>();
		try {
			// 退款当天日期
			String refund_date = UtilDate.getDateFormatter();
			// 退款笔数
			String batch_num = orderList.size() + "";
			logger.debug("detail_data {}", detail);
			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			// sParaTemp.put("service",
			// "refund_fastpay_by_platform_pwd"); //
			// 有密退款
			sParaTemp.put("service", "refund_fastpay_by_platform_nopwd"); // 无密退款
//			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("partner", organization.getPartner());
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//			sParaTemp.put("seller_user_id", AlipayConfig.partner);
			sParaTemp.put("seller_user_id", organization.getPartner());
			sParaTemp.put("refund_date", refund_date);
			sParaTemp.put("batch_no", batch_no);
			sParaTemp.put("batch_num", batch_num);
			sParaTemp.put("detail_data", detail);
			sParaTemp.put("private_key", organization.getPrivateKey());
			logger.debug("sParaTemp {}",sParaTemp.toString());
			String data = AlipaySubmit.postRequest(sParaTemp);
			Map<String, String> resultMap = CommonOAUtils.converteToMap(data);
			if ("T".equalsIgnoreCase(resultMap.get("is_success"))) {
				result.put(Constants.Result.RESULT, true);
				logger.debug("退款成功***");
			} else {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, resultMap.get("error"));
				logger.debug("alipayRefund pay error,the error is {}", resultMap.get("error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("退款是发生异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 微信退款
	 * 
	 * @param order
	 *                订单对象
	 * @return String
	 */
	public Map<String, Object> wxapRefund(Order order,Organization organization) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.Result.RESULT, false);
		try {
			SortedMap<String, String> signParams = new TreeMap<String, String>();
			if (Constants.PayType.WEIXINPAY .equals(order.getPayType())) { // 判断是否为微信退款还是微信公众平台退款
//				signParams.put("appid", WxapConfig.appid);
				signParams.put("appid", organization.getAppid());
//				signParams.put("mch_id", WxapConfig.mch_id);
				signParams.put("mch_id", organization.getMchId());
//				signParams.put("op_user_id", WxapConfig.mch_id); // 操作员帐号,默认为商户号
				signParams.put("op_user_id", organization.getMchId()); // 操作员帐号,默认为商户号
			} else {
//				signParams.put("appid", WxapConfig.WX_APP_ID);
				signParams.put("appid", organization.getWxAppid());
//				signParams.put("mch_id", WxapConfig.WX_MCH_ID);
				signParams.put("mch_id", organization.getWxMchId());
//				signParams.put("op_user_id", WxapConfig.WX_MCH_ID); // 操作员帐号,默认为商户号
				signParams.put("op_user_id", organization.getWxMchId()); // 操作员帐号,默认为商户号
			}
			signParams.put("nonce_str", UUID.get());
			signParams.put("transaction_id", order.getNumber()); // 微信订单号
			String tradeNo = order.getTradeNo();
			if (StringUtils.isEmpty(tradeNo)) {
				tradeNo = order.getId();
			}
			result.put("out_trade_no", tradeNo);
			signParams.put("out_trade_no", tradeNo); // 商户订单号
			signParams.put("out_refund_no", order.getId()); // 商户退款单号
			Integer fee = null;
			if (order.getFinalFee() != null) {
				fee = order.getFinalFee();
			} else {
				fee = order.getFee();
			}
			signParams.put("total_fee", fee + ""); // 总金额
			signParams.put("refund_fee", fee + ""); // 退款金额

			StringBuffer sb = new StringBuffer();
			for (String key : signParams.keySet()) {
				sb.append(key).append("=").append(signParams.get(key)).append("&");
			}
//			sb.append("key=").append(WxapConfig.app_key);
			sb.append("key=").append(organization.getAppKey());
			logger.debug("params : {}", sb);
			String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8");
			signParams.put("sign", sign.toUpperCase());
			logger.debug("sign : {}", sign);
			String requestBody = CommonOAUtils.converteToXml(signParams);
			logger.debug("xml : {}", requestBody);
			String res;
			String url = configs.get("url.refund");
			logger.debug("url.refund:{}",url);
			logger.debug("getCertPassword:{}",organization.getCertPassword());
			logger.debug("getCertlocalPath:{}",organization.getCertlocalPath());
			if (Constants.PayType.WEIXINPAY.equals(order.getPayType())) {
				HttpsRequest req = new HttpsRequest(organization.getCertPassword(), organization.getCertlocalPath());
				res = req.sendPost(url, requestBody, organization.getCertPassword(), organization.getCertlocalPath());
			} else {
				HttpsPlatRequest req = new HttpsPlatRequest(organization.getWxCertPassword(), organization.getWxCertlocalPath());
				res = req.sendPost(url, requestBody, organization.getCertPassword(), organization.getWxCertlocalPath());
			}
			logger.debug("res {}", res);
			Map<String, String> ret = CommonOAUtils.converteToMap(res);
			logger.debug("return_code {}", ret.get("return_code"));
			if ("SUCCESS".equals(ret.get("return_code"))) {
				logger.debug("result_code {}", ret.get("result_code"));
				if ("SUCCESS".equals(ret.get("result_code"))) {
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.REASON, ret.get("err_code_des"));
					logger.debug("wxapRefund pay error,the error is {}", ret.get("err_code_des"));
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * <确认退款成功后记录日志><功能具体实现>
	 *
	 * @create：2016年12月7日 下午3:14:54
	 * @author：zzq
	 * @param userId
	 * @param order
	 * @param comment
	 * @param isPayLog
	 * @param batch_no
	 * @param orderLogAction
	 * @param payLogStatus
	 */
	public void returnOrderLog(String userId, Order order, String comment, boolean isPayLog, String batch_no, String orderLogAction, String payLogStatus) {
		// 记录订单日志
		OrderLog log = new OrderLog();
		log.setId(UUID.get());
		log.setCt(new Date());
		log.setOrderId(order.getId());
		log.setUserId(userId);
		log.setAction(orderLogAction);
		log.setComment(comment);
		orderLogMapper.insert(log);

		if (isPayLog) {// 是否记录支付日志 成功才记录
			// 记录支付日志
			PayLog payLog = new PayLog();
			payLog.setId(UUID.get());
			payLog.setTradeNo(order.getTradeNo());
			payLog.setOrderId(order.getId());
			payLog.setUserId(order.getUserId());
			if (Constants.PayStatus.REFUND_SUCCESS.equals(payLogStatus)) {
				payLog.setFee(-order.getFee());
				payLog.setFinalFee(-order.getFinalFee());
			} else {
				payLog.setFee(order.getFee());
				payLog.setFinalFee(order.getFinalFee());
			}

			payLog.setStatus(payLogStatus);
			payLog.setCreateTime(new Date());
			payLog.setOutTradeNo(batch_no);
			payLog.setPayType(order.getPayType());
			payLogMapper.insertSelective(payLog);
		}
	}
	
	/**
	 * 
	 * <退款至会员卡账户><功能具体实现>
	 *
	 * @create：2016年12月7日 下午4:16:39
	 * @author：zzq
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean refundToAccount(Order order){
	
		String userId = order.getUserId();
		Integer finalFee = order.getFinalFee();
		String batch_no = "";
		CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
		//抽储值卡类型 状态是激活的指定用户
		crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(1).andStatusEqualTo(1).andStatiumIdEqualTo(order.getStatiumId());
		List<CrmUserCardAccount> accountList = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
		boolean flag = false;
		if(accountList!=null){
			//修改会员卡
			CrmUserCardAccount myAccount = accountList.get(0);
			//保存余额 是*100的形式  退款是余额加上订单金额
			myAccount.setBalance(new BigDecimal(finalFee).add(new BigDecimal(myAccount.getBalance())).intValue());
			myAccount.setEb(SessionUtil.currentUserId());
			myAccount.setEt(new Date());
			try {
				this.updateByPrimaryKeySelective(myAccount, myAccount.getId());
			} catch (Exception e) {
				logger.error("更新会员卡"+e.getMessage());
			}
			flag = true;
		}
		if(flag){
			
			order.setStatus(Constants.OrderStatus.STATUS_REFUNNED);
			order.setEt(new Date());
			order.setEb(SessionUtil.currentUserId());
			//处理人为当前用户
			order.setHandler(SessionUtil.currentUserId());
			//该状态为已处理
			order.setHandleStatus(1);
			try {
				this.updateByPrimaryKeySelective(order, order.getId());
			} catch (Exception e) {
				logger.error("更新订单"+e.getMessage());
			}
			
			int isExperience = order.getIsExperience();
			if(isExperience==1&&order.getOrdersType()==0){
				this.addExperience(userId,order);
			}
			
			//记录订单日志和支付日志
			returnOrderLog(SessionUtil.currentUserId(), order, "退款成功！", true, batch_no, Constants.OrderLog.ACTION_REFUNDED, Constants.PayStatus.REFUND_SUCCESS);
		
			//对于退款的订单 活动订单报名人数-1 课程订单参加人数-1
			this.deleteMember(order);
		}
		
		return flag;
		
	}
	
	/**
	 * 
	 * <确认退款时增加道馆端会员卡日志表><功能具体实现>
	 *
	 * @create：2016年12月7日 下午3:59:42
	 * @author：zzq
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void addCrmCardLog(Order order){
		CrmCardLog crmCardLog = new CrmCardLog();
		crmCardLog.setId(UUID.get());
		crmCardLog.setUserId(order.getUserId());
		crmCardLog.setStatiumId(order.getStatiumId());
		crmCardLog.setCardType(Constants.CardType.PAIEDCARD);
		crmCardLog.setAmount(order.getFinalFee());
		crmCardLog.setType(Constants.CrmUserCardStatus.REFUEND);
		crmCardLog.setCt(new Date());
		crmCardLog.setBuyType(0);
		try {
			this.insertSelective(crmCardLog,crmCardLog.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <确认退款增加会员卡记录表><功能具体实现>
	 *
	 * @create：2016年12月7日 下午4:18:13
	 * @author：zzq
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void addCrmUserCardLog(Order order){
		String userId = order.getUserId();
		CrmUserCardAccountCriteria crmUserCardAccountCriteria = new CrmUserCardAccountCriteria();
		//抽储值卡类型 状态是激活的指定用户
		crmUserCardAccountCriteria.createCriteria().andUserIdEqualTo(userId).andCardTypeEqualTo(1).andStatusEqualTo(1).andStatiumIdEqualTo(order.getStatiumId());
		List<CrmUserCardAccount> accountList = crmUserCardAccountMapper.selectByExample(crmUserCardAccountCriteria);
		if(accountList!=null){
			CrmUserCardLog cardLog = new CrmUserCardLog();
			cardLog.setId(UUID.get());
			cardLog.setUserId(order.getUserId());
			cardLog.setType(Constants.CrmUserCardStatus.REFUEND);
			Integer amount = new BigDecimal(order.getFinalFee()).intValue();
			//退款时记录金额显示正数
			cardLog.setAmount(amount);
			
			cardLog.setOrderId(order.getId());
			cardLog.setTradetype(Constants.CardType.PAIEDCARD);
			cardLog.setTradeno(order.getTradeNo());
			cardLog.setNumber(order.getNumber());
			cardLog.setDescription("退款");
			cardLog.setStatus(1);
			cardLog.setCt(new Date());
			cardLog.setOrderType(0);
			cardLog.setAccountId(accountList.get(0).getId());
			
			//之前已经改变了金额 此处直接取数据库的值
			Integer balance = accountList.get(0).getBalance();
			cardLog.setBalance(balance);
			
			try {
				this.insertSelective(cardLog, cardLog.getId());
			} catch (Exception e) {
				logger.error("保存日志"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
