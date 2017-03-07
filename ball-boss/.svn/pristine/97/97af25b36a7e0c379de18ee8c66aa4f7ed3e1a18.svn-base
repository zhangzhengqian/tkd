package com.lc.zy.ball.boss.framework.conpon.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.domain.oa.mapper.CouponHistoryMapper;
import com.lc.zy.ball.domain.oa.po.CouponHistory;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.UUID;

@Service
public class CouponHistoryService extends AbstractCacheService {
    private static final Logger logger = LoggerFactory.getLogger(CouponHistoryService.class);

    @Autowired
    private CouponHistoryMapper couponHistoryMapper;
    
    @Autowired
	private SsoUserService ssoUserService;

    /**
     * <获取优惠券列表><功能具体实现>
     * 
     * @param condition
     *            查询条件
     */
   /* public List<CouponHistory> getCouponHistoryList(Map<String, Object> condition) {
        try {
            return couponHistoryMapper.getCouponList(condition);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 
     * <优惠券><获取优惠券>
     *
     * @create：2015年6月26日 上午11:48:36
     * @author： lsh
     * @param example
     * @return
     */
   /* public List<CouponHistory> selectByExample(CouponHistoryExample example) {
        try {
            return couponHistoryMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }*/

    /**
     * <根据id更新优惠券信息><功能具体实现>
     * 
     * @param couponHistory
     *            查询条件
     */
    public int updateById(CouponHistory couponHistory) {
        try {
            return couponHistoryMapper.updateByPrimaryKeySelective(couponHistory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <优惠券><添加优惠券领取记录>
     *
     * @create：2015年6月26日 上午11:47:01
     * @author： lsh
     * @param couponHistory
     * @return
     * @throws Exception
     */
    @Transactional(value = "core", readOnly = false)
    public int insert(CouponHistory couponHistory) throws Exception {
        try {
            int num = couponHistoryMapper.insertSelective(couponHistory);
            return num;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <根据优惠券记录id获取优惠券><功能具体实现>
     *
     * @create：2015年6月29日 上午12:23:36
     * @author： cyy
     * @param id
     *            优惠券记录id
     * @return
     */
    public CouponHistory selectByPrimaryKey(String id) {
        try {
            return couponHistoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * <优惠券记录字段为空也更新><功能具体实现>
     *
     * @create：2015年6月29日 上午14:23:36
     * @author： cyy
     * @param couponHistory   优惠券对象
     *            
     * @return
     */
    public int updateByPrimaryKey(CouponHistory couponHistory) {
        try {
            return couponHistoryMapper.updateByPrimaryKey(couponHistory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * <优惠券><赠送优惠券流程>
     * @param couponId
     * @param receiveUserId
     * @throws Exception
     */
    /*@Transactional(value = "core", readOnly = false)
    public void giftingCoupon(String couponId,String phone) throws Exception {
    	CouponHistory couponHistory = selectByPrimaryKey(couponId);
		if (couponHistory==null) {
			throw new Exception("优惠券领取记录不存在！");
		}
		if(couponHistory.getIsUse()==3){
			throw new Exception("优惠券不能重复赠送！");
		}
    	String receiveUserId = ssoUserService.getReceiveUserId(phone);
    	CouponHistory couponHistoryReceive = new CouponHistory(); 
    	String id = UUID.get();
    	couponHistoryReceive.setId(id);
    	couponHistoryReceive.setUserId(receiveUserId);
    	couponHistoryReceive.setReceiveTime(DateUtil.nowDateTimeMilString());
    	couponHistoryReceive.setUpdateTime(DateUtil.nowDateTimeMilString());
    	couponHistoryReceive.setIsUse(0);
    	couponHistoryReceive.setAmount(couponHistory.getAmount());
    	couponHistoryReceive.setBallType(couponHistory.getBallType());
    	couponHistoryReceive.setCouponId(couponHistory.getCouponId());
    	couponHistoryReceive.setEndTime(couponHistory.getEndTime());
    	couponHistoryReceive.setReceiveCouponId(couponId);
    	int insertNum = couponHistoryMapper.insertSelective(couponHistoryReceive);
    	if(insertNum>0){
    		couponHistory.setIsUse(3);
    		int updateNum = couponHistoryMapper.updateByPrimaryKeySelective(couponHistory);
    		if(updateNum<=0){
    			throw new RuntimeException("优惠券领取记录状态更新失败！");
    		}
    		String msg = PropertiesUtil.getPropertiesValue(Constants.GIFTCOUPON_MESSAGE);
    		SmsUtil.sendMsg(phone, msg);
    	}else{
    		throw new RuntimeException("优惠券领取记录添加失败！");
    	}
    	
    }*/
    
    /**
     * <优惠券><根据ID获取优惠券信息>
     * @param couponId
     * @return
     * @throws Exception
     */
    /*public CouponHistory getCouponInfo(String couponId) throws Exception {
    	
    	CouponHistory couponHistory = couponHistoryMapper.getCouponInfo(couponId);
		if (couponHistory==null) {
			throw new Exception("优惠券领取记录不存在！");
		}
    	return couponHistory;
    }*/
    
    /**
     * 
     * <优惠券><获取优惠券>
     *
     * @create：2015年6月26日 上午11:48:36
     * @author： lsh
     * @param example
     * @return
     */
   /* public List<CouponHistory> selectByExampleNew(CouponHistoryExample example) {
        try {
            return couponHistoryMapper.selectByExampleNew(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }*/
    
    /**
     * 根据优惠券领取ID获取领取用户phone
     * @param couponId
     * @return
     * @throws Exception
     */
   /* public String getUserOfCouponHistory(String couponId) throws Exception {
    	String phone = couponHistoryMapper.getUserOfCouponHistory(couponId);
    	return phone;
    }*/
}
