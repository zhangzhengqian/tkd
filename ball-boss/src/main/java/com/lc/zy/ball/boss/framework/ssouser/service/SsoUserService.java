package com.lc.zy.ball.boss.framework.ssouser.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.SearchFilter;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.ssouser.vo.FeedbackVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CouponHistoryMapper;
import com.lc.zy.ball.domain.oa.mapper.CouponInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.CouponMapper;
import com.lc.zy.ball.domain.oa.mapper.FeedbackMapper;
import com.lc.zy.ball.domain.oa.mapper.ManageSsoMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.ProvinceCityMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.Coupon;
import com.lc.zy.ball.domain.oa.po.CouponCriteria;
import com.lc.zy.ball.domain.oa.po.CouponHistory;
import com.lc.zy.ball.domain.oa.po.CouponHistoryCriteria;
import com.lc.zy.ball.domain.oa.po.CouponInfo;
import com.lc.zy.ball.domain.oa.po.CouponInfoCriteria;
import com.lc.zy.ball.domain.oa.po.Feedback;
import com.lc.zy.ball.domain.oa.po.FeedbackCriteria;
import com.lc.zy.ball.domain.oa.po.ManageSso;
import com.lc.zy.ball.domain.oa.po.ManageSsoCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.ProvinceCity;
import com.lc.zy.ball.domain.oa.po.ProvinceCityCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.Constants;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.HttpUtil;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.UUID;

@Service
public class SsoUserService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory.getLogger(SsoUserService.class);

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Autowired
	private ManageSsoMapper manageSsoMapper;
	
	@Autowired
    private MessageUtil messageUtil;
	
	@Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private CouponMapper couponMapper;
	
	@Autowired
    private CouponHistoryMapper couponHistoryMapper;
	
	@Autowired
	private ProvinceCityMapper  provinceCityMapper;

	/**
	 * 
	 * <获取用户list>
	 *
	 * @create：2015年8月4日 上午10:55:33
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<SsoUser> findSsoUserList(PageRequest pageRequest, Map<String, Object> searchParams, boolean isPage,
			boolean isHasCount) {
		// 数量
		int total = 0;
		// list
		List<SsoUser> list = new ArrayList<SsoUser>();
		try {
			SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
			// 查询条件
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			// 分页
			if (isPage) {
				ssoUserCriteria.setMysqlLength(pageRequest.getPageSize());
				ssoUserCriteria.setMysqlOffset(pageRequest.getOffset());
			}
			SsoUserCriteria.Criteria cri = ssoUserCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			// 用户状态(正常:1、删除:2、冻结:3)
			cri.andStateNotEqualTo(2);
			// 排序
			ssoUserCriteria.setOrderByClause("update_time desc");
			// 数量
			if (isHasCount) {
				total = ssoUserMapper.countByExample(ssoUserCriteria);
			}
			// list
			list = ssoUserMapper.selectByExample(ssoUserCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(SsoUserService--findSsoUserList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	
	
	/**
     * 
     * <优惠卷><向新注册用户账户发放优惠劵>
     *
     * @create：2015年9月2日 下午3:37:37
     * @author： liangsh
     * @param token
     * @return
     */
    public Map<String, Object> receivingCouponRegister(String uid, String city) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	CouponHistoryCriteria criteria = new CouponHistoryCriteria();
        	CouponHistoryCriteria.Criteria criteria2 = criteria.createCriteria();
        	criteria2.andUserIdEqualTo(uid);
        	criteria2.andReceivingTypeEqualTo(0);
        	List<CouponHistory> historyList = this.couponHistoryMapper.selectByExample(criteria);
        	if(CollectionUtils.isNotEmpty(historyList)){
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "该用户已领取优惠券");
                logger.debug("该用户已领取优惠券uid={}", uid);
                return result;
        	}
        	
            // 获取所有启动状态的优惠券礼包，按发布时间升序排列
            CouponCriteria c = new CouponCriteria();
            CouponCriteria.Criteria cri = c.createCriteria();
            // 未删除的
            cri.andDelFlagEqualTo(Constants.couponInfo.delFlag.NORMAL);
            // 启动状态的
            cri.andStatusEqualTo(Constants.couponInfo.status.START);
            // 城市
            if (city != null) {
                cri.andCityLike(city);
            } else {// 针对老版本
                cri.andCityEqualTo("0");
            }
            // 按创建时间升序
            c.setOrderByClause("create_time desc");
            // 获取优惠券礼包信息
            List<Coupon> couponList = this.couponMapper.selectByExample(c);
            // 判断优惠券是否存在
            if (CollectionUtils.isNotEmpty(couponList)) {
                Coupon coupon = couponList.get(0);
                logger.debug("查询到优惠券={},{}", uid, coupon.getId());
                // 判断礼包是否已经结束
                if (coupon.getStatus().equals(Constants.couponInfo.status.END)) {
                    result.put(Constants.Result.RESULT, false);
                    result.put(Constants.Result.REASON, "活动已结束");
                } else if (coupon.getStatus().equals(Constants.couponInfo.status.STOP)) {
                    result.put(Constants.Result.RESULT, false);
                    result.put(Constants.Result.REASON, "活动已停止");
                } else {
                    // 根据优惠券礼包ID获取礼包中优惠券详细信息
                    CouponInfoCriteria c_info = new CouponInfoCriteria();
                    CouponInfoCriteria.Criteria cri_info = c_info.createCriteria();
                    // 优惠券id
                    cri_info.andCouponIdEqualTo(coupon.getId());
                    // 获取优惠券信息
                    List<CouponInfo> couponInfoList = couponInfoMapper.selectByExample(c_info);
                    // 判断优惠券礼包是否存在优惠券
                    if (CollectionUtils.isNotEmpty(couponInfoList)) {
                        // 礼包中包含的优惠券信息
                        BigDecimal amount = new BigDecimal(0);
                        String sportType = "";
                        String expireDate = "";
                        for (CouponInfo couponInfo : couponInfoList) {
                            if (couponInfo.getCouponValue() > 0) {
                                // 追加优惠券发放记录
                                CouponHistory info = new CouponHistory();
                                info.setId(UUID.get());
                                info.setAmount(BigDecimal.valueOf(couponInfo.getCouponValue()));
                                info.setCouponId(couponInfo.getId());
                                // 已领取
                                info.setIsUse(Constants.couponInfo.isUse.ISRECIVING);
                                info.setReceiveTime(new Date());
                                info.setUpdateTime(new Date());
                                // 计算有效期
                                info.setEndTime(DateUtils.plusDays(DateUtils.getDate(DateUtils.getTodayStr()),
                                        coupon.getDuration()));
                                info.setUserId(uid);
                                // 领取类型 优惠券领取类型 0：首单优惠 1：分享赠送 2：下单成功分享
                                info.setReceivingType(Constants.couponInfo.givingType.FIRST);
                                // 城市
                                if (city != null) {
                                    info.setCity(city);
                                } else {
                                    info.setCity("0");
                                }
                                // 添加发放记录
                                amount = amount.add(info.getAmount());
                                sportType += CommonOAUtils.sportTypeTransform(couponInfo.getCouponType()) + "、";
                                expireDate = DateUtil.formatDate(info.getEndTime(), "yyyy年MM月dd日");
                                this.insertSelective(info, info.getId());
                            }
                        }
                        Map<String, String> root = new HashMap<String, String>();
                        root.put("count", String.valueOf(couponInfoList.size()));
                        root.put("amount", (amount
                                .multiply(new BigDecimal(com.lc.zy.ball.boss.common.Constants.MAX_BASE))).toString());
                        root.put("expireDate", expireDate);
                        root.put("sportType", sportType.substring(0, sportType.length() - 1));
                        SsoUser user = this.selectByPrimaryKey(SsoUser.class, uid);
                        String phone = user.getPhone();
                        String msg = FreeMarkerUtils.format("/template/sms/couponSuccess.ftl", root);
                        logger.debug(msg);
                        if (StringUtils.isNotEmpty(phone)) {
                            messageUtil.sendSms(phone, msg);
                        }
                        result.put(Constants.Result.RESULT, true);
                        result.put(Constants.Result.REASON, "领取成功！");
                    } else {
                        result.put(Constants.Result.RESULT, false);
                        result.put(Constants.Result.REASON, "优惠券不存在");
                    }
                }
                return result;
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "暂时无惠券活动");
                logger.debug("暂时无惠券活动city={}", city);
                return result;
            }
        } catch (Exception e) {
            logger.error("receivingCouponRegister_exception", e);
            throw new RuntimeException(e);
        }
    }

	/**
	 * 
	 * <删除用户>
	 *
	 * @create：2015年8月4日 下午2:25:32
	 * @author： sl
	 * @param id
	 */
	public void deleteSsoUserById(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(2);
			// 删除用户
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户(SsoUserService--deleteSsoUser)" + e.getMessage());
		}
	}
	
	/**
	 * 添加营销账号
	 * @param id
	 */
	public void addMarketUser(String id){
		ManageSso sso = new ManageSso();
		sso.setId(UUID.get());
		sso.setSsoUserId(id);
		sso.setUserId(SessionUtil.currentUserId());
		manageSsoMapper.insert(sso);
	}
	
	/**
	 * 判断是否已经添加
	 * @param id
	 */
	public boolean checkMarketUser(String id){
		ManageSsoCriteria criteria = new ManageSsoCriteria();
		ManageSsoCriteria.Criteria cri = criteria.createCriteria();
		cri.andSsoUserIdEqualTo(id);
		cri.andUserIdEqualTo(SessionUtil.currentUserId());
		if(manageSsoMapper.countByExample(criteria)>0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 
	 * <冻结用户>
	 *
	 * @create：2015年8月4日 下午3:36:38
	 * @author： sl
	 * @param id
	 */
	public void freezeSsoUser(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(3);
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("冻结用户(SsoUserService--freezeSsoUser)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <解冻用户>
	 *
	 * @create：2015年8月4日 下午3:38:48
	 * @author： sl
	 * @param id
	 */
	public void unfreezeSsoUser(String id) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(id);
			ssoUser.setState(1);
			this.updateByPrimaryKeySelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解冻用户(SsoUserService--freezeSsoUser)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <查看用户信息>
	 *
	 * @create：2015年8月4日 下午5:20:50
	 * @author： sl
	 * @param id
	 * @return
	 */
	public SsoUser viewSsoUser(String id) {
		SsoUser ssoUser = new SsoUser();
		try {
			ssoUser = this.selectByPrimaryKey(SsoUser.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查看用户信息(SsoUserService--viewSsoUser)" + e.getMessage());
		}
		return ssoUser;
	}

	/**
	 * 
	 * <用户添加>
	 *
	 * @create：2015年8月4日 下午8:49:55
	 * @author： sl
	 * @param ssoUser
	 */
	public void ssoUserForm(SsoUser ssoUser) {
		try {
			String id = UUID.get();
			ssoUser.setId(id);
			this.insertSelective(ssoUser, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户添加(SsoUserService--ssoUserForm)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <更新用户信息>
	 *
	 * @create：2015年8月5日 下午8:41:29
	 * @author： sl
	 * @param ssoUser
	 */
	public void updateSsoUser(SsoUser ssoUser) {
		try {
			this.updateByPrimaryKeySelective(ssoUser, ssoUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户添加(SsoUserService--ssoUserForm)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <根据用户id获取用户消费订单>
	 *
	 * @create：2015年8月5日 上午11:20:02
	 * @author： sl
	 * @param id
	 * @return
	 */
	public List<Order> findOrdersById(String id) {
		List<Order> orders = new ArrayList<Order>();
		OrderCriteria orderCriteria = new OrderCriteria();
		try {
			OrderCriteria.Criteria cri = orderCriteria.createCriteria();
			cri.andCustomerIdEqualTo(id);
			orders = orderMapper.selectByExample(orderCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据用户id获取用户消费订单(SsoUserService--findOrdersById)" + e.getMessage());
		}
		return orders;
	}
	
	/**
	 * 
	 * <用户反馈列表><功能具体实现>
	 * @param pageRequest
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @author liangsh
	 * @date 2016年1月12日 下午2:29:30
	 */
	public Page<FeedbackVo> findFeedbackList(PageRequest pageRequest) {
		int total = 0;
		List<FeedbackVo> listVo = new ArrayList<FeedbackVo>();
		List<Feedback> list = new ArrayList<Feedback>();

		try {
			FeedbackCriteria feedbackCriteria = new FeedbackCriteria();
			feedbackCriteria.setMysqlLength(pageRequest.getPageSize());
			feedbackCriteria.setMysqlOffset(pageRequest.getOffset());
			feedbackCriteria.setOrderByClause("ct desc");
			total = feedbackMapper.countByExample(feedbackCriteria);
			list = feedbackMapper.selectByExample(feedbackCriteria);
			if(CollectionUtils.isNotEmpty(list)){
				for (Feedback feedback : list) {
					if(feedback != null){
						FeedbackVo vo = new FeedbackVo();
						BeanUtils.copyProperties(vo,feedback);
						SsoUser user = ssoUserMapper.selectByPrimaryKey(feedback.getUserId());
						if(user != null){
							vo.setQiuyouno(user.getQiuyouno());
							vo.setNickName(user.getNickName());
							vo.setSex(user.getSex());
							vo.setType(user.getProperty());
							vo.setPhone(user.getPhone());
						}
						listVo.add(vo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户反馈list(SsoUserService--findFeedbackList)" + e.getMessage());
		}
		return new PageImpl<>(listVo, pageRequest, total);
	}
	
	public String getProvincCityByPhone(String phone)throws Exception{
		Map<String,Object> map = HttpUtil.getCityByphone(phone);
		if(map != null){
			String province = map.get("province").toString();
			String city = map.get("city").toString();
			ProvinceCityCriteria cityCriteria = new ProvinceCityCriteria();
			ProvinceCityCriteria.Criteria criteria = cityCriteria.createCriteria();
			criteria.andProvinceLike(province+"%");
			criteria.andCityLike(city+"%");
			if(provinceCityMapper.countByExample(cityCriteria) > 0){
				ProvinceCity info = provinceCityMapper.selectByExample(cityCriteria).get(0);
				return info.getProvince()+info.getCity();
			}else{
				return "";
			}
		}else{
			return "";
		}
	}

}
