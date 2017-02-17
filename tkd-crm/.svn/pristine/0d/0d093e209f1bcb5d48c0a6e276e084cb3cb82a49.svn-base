package com.lc.zy.ball.crm.framework.system.order.service;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.crm.framework.system.order.vo.OrderVo;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(readOnly=true)
public class OrderService extends AbstractCacheService {

	private static Logger logger=LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Resource(name="oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;


	/**
	 *
	 * <获取订单详情><功能具体实现>
	 *
	 * @create：2016/11/22 下午5:51
	 * @author：sl
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return com.lc.zy.ball.common.data.pageable.Page<com.lc.zy.ball.crm.framework.system.order.vo.OrderVo>
	 */
    public Page<OrderVo> find(Map<String, Object> searchParams, int page, int size,boolean isPage) {
		logger.debug(searchParams.toString());
		int total = 0;
		// 获取道馆id
		CrmUser user = SessionUtil.currentUser();
		int statiumId = Integer.valueOf(user.getStatiumId());
		PageRequest pageable = new PageRequest(page, size);
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
				return new PageImpl<>(new ArrayList<OrderVo>(), pageable, 0);
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
		if (searchParams.get("EQ_status") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_status"))) {
			paras.put("status", (String) searchParams.get("EQ_status"));
		}
		// 订单ID
		if (searchParams.get("LIKE_id") != null && CommonUtils.isNotEmpty((String) searchParams.get("LIKE_id"))) {
			paras.put("orderId", (String) searchParams.get("LIKE_id"));
		}
		// 订单时间区间
		if (searchParams.get("GTE_ct") != null && CommonUtils.isNotEmpty((String) searchParams.get("GTE_ct"))) {
			paras.put("GTE_ct", ((String)searchParams.get("GTE_ct"))+" 00:00:00");
		}

		if (searchParams.get("LTE_ct") != null && CommonUtils.isNotEmpty((String) searchParams.get("LTE_ct"))) {
			paras.put("LTE_ct", ((String)searchParams.get("LTE_ct"))+" 23:59:59");
		}

		if (searchParams.get("GTE_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("GTE_et"))) {
			paras.put("GTE_et", ((String)searchParams.get("GTE_et"))+" 00:00:00");
		}

		if (searchParams.get("LTE_et") != null && CommonUtils.isNotEmpty((String) searchParams.get("LTE_et"))) {
			paras.put("LTE_et", ((String)searchParams.get("LTE_et"))+" 23:59:59");
		}
		//订单渠道
		if (searchParams.get("EQ_orderType") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_orderType"))) {
			paras.put("order_type", (String) searchParams.get("EQ_orderType"));
		} else {
			paras.put("order_type", Constants.BookType.APP);
		}
		// 支付方式
		if (searchParams.get("EQ_payType") != null && CommonUtils.isNotEmpty((String) searchParams.get("EQ_payType"))) {
			paras.put("payType", (String)searchParams.get("EQ_payType"));
		}
		// 获取订单数量
		String sqlCount = FreeMarkerUtils.format("/template/order/order_search_count.ftl", paras);
		logger.debug(sqlCount);
		logger.debug(paras.toString());
		if(isPage){
			if(pageable.getOffset()>= 1000){
				paras.put("offset", String.valueOf(pageable.getOffset()).replace(",",""));
			}else{
				paras.put("offset", pageable.getOffset());
			}
			paras.put("pageSize", pageable.getPageSize());
		}
		
		List<Map<String,Object>> countMap = jdbcTemplate.queryForList(sqlCount);
		long total_ = (long)countMap.get(0).get("cont");
		total = (int)total_;
		// 获取订单信息
		String sqlList = FreeMarkerUtils.format("/template/order/order_search.ftl", paras);
		List<Map<String,Object>> orders = jdbcTemplate.queryForList(sqlList);
		List<OrderVo> orderList = new ArrayList<OrderVo>();
		OrderVo vo = null;
		for (Map<String, Object> order : orders) {
			vo = new OrderVo();
			Date ct = (Date)order.get("ct");
			Date et = (Date)order.get("et");
			String orderId = (String)order.get("id");
			String status = (String)order.get("status");
			Integer payType = (Integer)order.get("payType");
			// 订单ID
			vo.setId(orderId);
			// 下单日期
			vo.setCt(ct);
			// 订单完成时间
			vo.setEt(et);
			// 订单状态
			vo.setStatus(status);
			//支付类型
			vo.setPayType(payType);
			// 订单类型
			Integer ordersType = (Integer) order.get("ordersType");
			vo.setOrdersType(ordersType);
			// 订单金额
			if (order.get("finalFee") != null) {
				Double finel = BigDecimal.valueOf((Integer)order.get("finalFee")).multiply(BigDecimal.valueOf(0.01)).doubleValue();
				vo.setFinalFee(finel.intValue());
			}
			// 用户手机号
			if (order.get("userId") != null) {
				String userId = (String)order.get("userId");
				// 获取用户信息
				SsoUser ssoUser = null;
				try {
					ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
				} catch (Exception e) {
					logger.debug("获取用户信息失败 {}", e.getMessage());
				}
				if (ssoUser != null) {
					String phone = ssoUser.getPhone();
					vo.setPhone(phone);
					//用户 名
					vo.setUserName(ssoUser.getNickName());
				}
			}
			// 获取订单详情
			OrderItemCriteria itemCriteria = new OrderItemCriteria();
			OrderItemCriteria.Criteria itemCri = itemCriteria.createCriteria();
			itemCri.andOrderIdEqualTo(orderId);
			List<OrderItem> items = orderItemMapper.selectByExample(itemCriteria);
			if (CollectionUtils.isNotEmpty(items)) {
				// 预约时间
				String signTime = "";
				for(OrderItem item:items){
					if (item.getSignDate() != null){
						vo.setSignDate(item.getSignDate());
					}
					if (item.getStartTime() != null) {
						signTime = item.getStartTime() + " ~ ";
					}
					if (item.getEndTime() !=null) {
						signTime += item.getEndTime();
					}
					vo.setSignTime(signTime);
					if (ordersType == Constants.OrdersType.CLASS) {
						// 获取课程信息
						try {
							StatiumClass statiumClass = this.selectByPrimaryKey(StatiumClass.class, item.getClassId());
							// 课程名称
							vo.setClassName(statiumClass.getClassTitle());
						} catch (Exception e) {
							logger.debug("获取订单课程名称 {}", e.getMessage());
						}
						// 获取教练名称
						try {
							if(item.getCoachId()!=null){
								Coach coach = this.selectByPrimaryKey(Coach.class, item.getCoachId());
								// 教练名称
								vo.setCoachName(coach.getName());
							}
						} catch (Exception e) {
							logger.debug("获取订单教练名称 {}", e.getMessage());
						}
					} else if (ordersType == Constants.OrdersType.ACTIVITY) {
						try {
							StatiumActivity activity = this.selectByPrimaryKey(StatiumActivity.class, item.getActivityId());
							// 活动名称
							vo.setClassName(activity.getActivityTopic());
							// 开始日期
							vo.setAsTime(DateUtils.getDate(activity.getStartTime(), "yyyy-MM-dd HH:mm"));
							// 结束日期
							vo.setAeTime(DateUtils.getDate(activity.getEndTime(), "yyyy-MM-dd HH:mm"));
						} catch (Exception e) {
							logger.debug("获取订单活动名称 {}", e.getMessage());
						}
					} else if (ordersType == Constants.OrdersType.CARD) {
							vo.setClassName("会员卡");
					}
				}
			}
			orderList.add(vo);
		}
		return new PageImpl<>(orderList, pageable, total);
	}
}
