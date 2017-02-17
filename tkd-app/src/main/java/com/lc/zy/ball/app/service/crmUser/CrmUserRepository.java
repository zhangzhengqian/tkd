package com.lc.zy.ball.app.service.crmUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.card.bean.CrmUserCardAccountVo;
import com.lc.zy.ball.app.service.crmUser.bean.BillDetailVo;
import com.lc.zy.ball.app.service.crmUser.bean.CrmBillVo;
import com.lc.zy.ball.app.service.order.bean.OrderListVo;
import com.lc.zy.ball.domain.oa.mapper.CrmUserMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderBillMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.CrmUserCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderBill;
import com.lc.zy.ball.domain.oa.po.OrderBillCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.OrderBillItemCriteria;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumActivity;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;
@Repository
@Transactional
public class CrmUserRepository extends AbstractCacheService{
	@Autowired
	public CrmUserMapper crmUserMapper;
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public OrderMapper orderMapper;
	
	@Autowired
	public OrderItemMapper orderItemMapper;
	
	@Autowired
	private StatiumInfosMapper statiuminfoMapper;
	
	@Autowired
	private OrderBillItemMapper oredrBillItemMapper;
	
	@Autowired
	private OrderBillMapper orderBillMapper;
	private static Logger logger = LoggerFactory.getLogger(CrmUserRepository.class);
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_ITERATIONS = 1024;
	/**
	 * 
	 * <返回道馆管理员><功能具体实现>
	 *
	 * @create：2016年11月14日 下午4:25:14
	 * @author：zzq
	 * @param loginName
	 * @param passWord
	 * @return
	 */
	public CrmUser login(String loginName){
		CrmUserCriteria crmUserCriteria = new CrmUserCriteria();
		crmUserCriteria.createCriteria().andLoginNameEqualTo(loginName);
		List<CrmUser> crmList = crmUserMapper.selectByExample(crmUserCriteria);
		CrmUser crmUser = crmList.get(0);
		return crmUser;
	}
	
	/**
	 * 
	 * <校验密码的正确性><功能具体实现>
	 *
	 * @create：2016年11月14日 下午5:18:39
	 * @author：zzq
	 * @param loginName
	 * @param passWord
	 * @return
	 */
	public Boolean checkPassword(String loginName,String passWord){
		Boolean flag = false;
		CrmUserCriteria crmUserCriteria = new CrmUserCriteria();
		crmUserCriteria.createCriteria().andLoginNameEqualTo(loginName);
		List<CrmUser> crmList = crmUserMapper.selectByExample(crmUserCriteria);
		CrmUser crmUser = crmList.get(0);
		String salt = crmUser.getSalt();
		//将传入的密码加密来比较
		String[] strs = this.hashPassword(passWord,salt);
		logger.debug("加密后的密码"+strs[0]);
		logger.debug("加密后的密码"+crmUser.getPassword());
		if(!strs[0].equals(crmUser.getPassword())){
			flag = true;
			return flag;
		}
		return flag;
	}
	
	/**
	 * 
	 * <判断该用户名是否存在><功能具体实现>
	 *
	 * @create：2016年11月14日 下午7:02:57
	 * @author：zzq
	 * @param loginName
	 * @return
	 */
	public Boolean hasLoginName(String loginName){
		Boolean flag = true;
		CrmUserCriteria crmUserCriteria = new CrmUserCriteria();
		crmUserCriteria.createCriteria().andLoginNameEqualTo(loginName);
		List<CrmUser> crmList = crmUserMapper.selectByExample(crmUserCriteria);
		if(crmList.size()!=0){
			flag = false;
		}
		return flag;
		}
	
	/**
	 * 生成安全哈希码. 
	 * @param plain 明文密码
	 * @return string[0]:HASH安全密码，string[1]:SALT值.
	 */
	public String[] hashPassword(String plain,String salt) {
		byte[] salts = Encodes.decodeHex(salt);
		byte[] hash = Digests.sha1(plain.getBytes(), salts, HASH_ITERATIONS);
		String hexHash = Encodes.encodeHex(hash);
		
		return new String[]{hexHash, salt};
	}
	
	/**
	 * 
	 * <获取订单管理list><功能具体实现>
	 *
	 * @create：2016年11月15日 下午7:01:56
	 * @author：zzq
	 * @param statiumId
	 * @param begin
	 * @param size
	 * @return
	 */
	public List<OrderListVo>  getOrderList(Integer statiumId,int begin,int size){
		List<OrderListVo> orderList1 = new ArrayList<OrderListVo>();
		
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.setMysqlLength(size);
		orderCriteria.setMysqlOffset(begin);
		orderCriteria.setOrderByClause("ct desc");
		
		List<String> list = new ArrayList<String>();
		list.add(Constants.OrderStatus.BILLED);
		list.add(Constants.OrderStatus.PAIED);
		orderCriteria.createCriteria().andStatiumIdEqualTo(statiumId).andStatusIn(list).andOrderTypeEqualTo("BOOK_APP");
		List<Order> orderList = orderMapper.selectByExample(orderCriteria);
		
		for(Order order:orderList){
			OrderListVo vo = new OrderListVo();
			
			//预定时间 预定日期 课程名称 
			OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
			orderItemCriteria.createCriteria().andOrderIdEqualTo(order.getId());
			List<OrderItem> items;
			items = orderItemMapper.selectByExample(orderItemCriteria);
				if(items.size()!=0){
					OrderItem item = items.get(0);
					Date signDate1 = item.getSignDate();
					String signDate = "";
					if(signDate1!=null){
						signDate = DateUtil.formatDate(signDate1, "yyyy-MM-dd");
					}
					String startTime = item.getStartTime(); 
					String className = item.getClassName();
					if(item.getActivityId()!=null){
						try {
							StatiumActivity statiumActivity = this.selectByPrimaryKey(StatiumActivity.class, item.getActivityId());
							//活动名称
							className = statiumActivity.getActivityTopic();
						} catch (Exception e) {
							logger.error("主键查询道馆活动"+e.getMessage());
						}
					}
					vo.setSignDate(signDate);
					vo.setStartTime(startTime);
					//vo.setClassName(className);
					vo.setOrderName(className);
					//预定时间  课程活动订单预约时间 会员卡充值订单会员卡购买日期
					if(order.getOrdersType()==0||order.getOrdersType()==1){
						vo.setSignTime(signDate+ " " +startTime);
					}else if(order.getOrdersType()==2){
						String buyDate = DateUtils.formatDate(item.getCardBuyDate(), "yyyy-MM-dd HH:mm");
						vo.setSignTime(buyDate);
					}
					
					//会员卡购买日期
					
				}
			
			String orderId = order.getId();
			String status = order.getStatus();
			//支付类型 1支付宝 2 微信 3 会员卡支付
			//订单类型 会员卡充值的 返回充值金额
			Integer payType = order.getPayType();
			if(payType!=null){
				if(payType == 1){
					vo.setPayType(Constants.PayType.ALI);
				}else if(payType == 2){
					vo.setPayType(Constants.PayType.WEIXIN);
				}else if(payType == 3){
					vo.setPayType(Constants.PayType.CARD);
				}
			}
			//订单价格
			vo.setPrice(order.getFinalFee()/100);
			
			//返回订单类型
			if(order.getOrdersType()==2){
				//充值订单
				vo.setOrderType(2);
				vo.setOrderName("会员卡");
				//订单支付完时间
				if(items.size()!=0){
					if(items.get(0)!=null){
						Date buyDate = items.get(0).getCardBuyDate();
						String etStr = DateUtils.formatDate(buyDate,"yyyy-MM-dd HH:mm");
						//办卡时间
						vo.setSignTime(etStr);
					}
				}
				
			}else if(order.getOrdersType()==1){
				vo.setOrderType(1);
			}else if(order.getOrdersType()==0){
				vo.setOrderType(0);
			}
			
			if(status.equals(Constants.OrderStatus.BILLED)){
				vo.setStatus(Constants.OrderStatus.BILLED);
				vo.setStatusName("已完成");
			}
			else if(status.equals(Constants.OrderStatus.PAIED)){
				vo.setStatus(Constants.OrderStatus.PAIED);
				vo.setStatusName("已付款");
			}
			//封装一个orderListVo类型的集合
			//订单id
			vo.setOrderId(orderId);
			
			//道馆id
			vo.setDgId(order.getStatiumId());
			orderList1.add(vo);
		}
		return orderList1;
		
	}
	
	/**
	 * 
	 * <馆长登录获取账单列表><功能具体实现>
	 *
	 * @create：2016年11月24日 上午10:02:41
	 * @author：zzq
	 * @param ssoUser
	 * @return
	 */
	public List<CrmBillVo> getCrmBillList(Integer dgId,Integer begin,Integer size){
		List<CrmBillVo>  billList = new ArrayList<CrmBillVo>();
			///道馆名称
			OrderBillCriteria orderBillCriteria = new OrderBillCriteria();
			orderBillCriteria.setOrderByClause("begin_date desc");
			orderBillCriteria.setMysqlLength(size);
			orderBillCriteria.setMysqlOffset(begin);
			orderBillCriteria.createCriteria().andStatiumIdEqualTo(dgId.toString()).andStatusNotEqualTo(0);
			List<OrderBill> bills = orderBillMapper.selectByExample(orderBillCriteria);
			if(bills.size()!=0){
				for(OrderBill bill:bills){
					//只展示课程活动订单
					if(bill.getBillId().endsWith("0")){
						CrmBillVo billVo = new CrmBillVo();
						String st = bill.getBillId().substring(0, 8);
						String ed = bill.getBillId().substring(8, 16);
						String billType = bill.getBillId().substring(16, 17);
						long stLong = Long.valueOf(st)*100000;
						long edLong = Long.valueOf(ed)*100000;
						Date stDate = new Date(stLong);
						Date edDate = new Date(edLong);
						String startTime = DateUtils.formatDate(stDate, "yyyy-MM-dd");
						String endTime = DateUtils.formatDate(edDate, "yyyy-MM-dd");
						//账单id  是由开始时间加结束时间的和拼接道馆id再拼接账单类型
						billVo.setBillId(String.valueOf(Integer.valueOf(st)+Integer.valueOf(ed))+bill.getStatiumId()+billType);
						//开始时间
						billVo.setStartTime(startTime);
						//结束时间
						billVo.setEndTime(endTime);
						//订单数量
						billVo.setOrderNum(bill.getOrderNum());
						//总金额
						billVo.setAmount(new BigDecimal(bill.getFee()).divide(new BigDecimal(100)).intValue());
						//状态
						billVo.setType(bill.getStatus());
						
						billList.add(billVo);
					}
					
				}
				
			}
			
		return billList;
	}
	
	/**
	 * 
	 * <账单详情><功能具体实现>
	 *
	 * @create：2016年11月24日 下午4:15:43
	 * @author：zzq
	 * @param billId
	 * @return
	 */
	public List<BillDetailVo> getBillDetail(String billId,Integer begin,Integer size){
		logger.debug(billId);
		OrderBillItemCriteria orderBillItemCriteria = new OrderBillItemCriteria();
		orderBillItemCriteria.setOrderByClause("start_date desc");
		orderBillItemCriteria.setMysqlLength(size);
		orderBillItemCriteria.setMysqlOffset(begin);
		orderBillItemCriteria.createCriteria().andOrderBillIdEqualTo(billId);
		List<OrderBillItem> items = oredrBillItemMapper.selectByExample(orderBillItemCriteria);
		List<BillDetailVo> list = new ArrayList<BillDetailVo>();
		if(items.size()!=0){
			for(OrderBillItem item:items){
				BillDetailVo vo = new BillDetailVo();
				//id
				String id = UUID.get();
				vo.setBillId(id);
				//完成时间
				String signDate = item.getStartDate();
				vo.setSignDate(signDate);
				//订单数量
				vo.setOrderCount(item.getOrderCount());
				//订单费用
				vo.setTotalFee(new BigDecimal(item.getTotalFee()).divide(new BigDecimal(100)).intValue());
				list.add(vo);
			}
		}
		return list;
	}
}

