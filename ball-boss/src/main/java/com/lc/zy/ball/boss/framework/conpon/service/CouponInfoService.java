 /**
 * <优惠券详细><功能具体实现>
 *
 * @create：2015年7月17日 下午3:15:05
 * @author： lsh
 */
package com.lc.zy.ball.boss.framework.conpon.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.CouponInfoMapper;
import com.lc.zy.ball.domain.oa.po.CouponCriteria;
import com.lc.zy.ball.domain.oa.po.CouponInfo;
import com.lc.zy.ball.domain.oa.po.CouponInfoCriteria;
import com.lc.zy.common.data.Criterias;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


@Service
public class CouponInfoService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CouponInfoService.class);

	@Autowired
	CouponInfoMapper couponInfoMapper;
	
	
	/**
	 * 
	 * <优惠券><获取所有符合条件的优惠券>
	 *
	 * @create：2015年7月17日 下午3:20:43
	 * @author： lsh
	 * @param example
	 * @return
	 */
	/*public List<CouponInfo> selectByExample(CouponInfoExample example){
		logger.debug("获取优惠券礼包详细CouponInfoService.class selectByExample");
		return couponInfoMapper.selectByExample(example);
	}*/

	
	/**
	 * 
	 * <根据礼包id获取优惠券信息><功能具体实现>
	 *
	 * @create：2015年8月7日 下午5:16:46
	 * @author： sl
	 * @param searchParams
	 * @param couponid
	 * @return
	 */
	public List<CouponInfo> selectInfo(String couponid){
		List<CouponInfo> list = new ArrayList<CouponInfo>();
		try {
			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			CouponInfoCriteria.Criteria cri = couponInfoCriteria.createCriteria();
			couponInfoCriteria.setOrderByClause("type");
			cri.andCouponIdEqualTo(couponid);
			list = couponInfoMapper.selectByExample(couponInfoCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户list(CouponInfoService--selectInfo)" + e.getMessage());
		}
		
		return list;
	}
	
	

	
	/**
	 * 
	 * <优惠券><根据优惠券id获取优惠券 >
	 *
	 * @create：2015年7月17日 下午5:17:52
	 * @author： lsh
	 * @param id
	 * @return
	 */
	public CouponInfo selectByPrimaryKey(String id){
		CouponInfo couponInfo = new CouponInfo();
		try {
			couponInfo = this.selectByPrimaryKey(CouponInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据优惠券id获取优惠券(CouponInfoService--selectByPrimaryKey)" + e.getMessage());
		}
		return couponInfo;
	}
	
	/**
	 * 
	 * <删除优惠券><将del_flag置0>
	 *
	 * @create：2015年7月18日 上午10:45:59
	 * @author： zw
	 * @param couponInfoId
	 */
	public void deleteCouponInfo(String couponInfoId){
		CouponInfo couponInfo = new CouponInfo();
		try {
			couponInfo.setId(couponInfoId);
			couponInfo.setDelFlag(Integer.valueOf(Constants.couponInfo.DELETE));
			this.updateByPrimaryKeySelective(couponInfo, couponInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<删除优惠券><将del_flag置0>(CouponInfoService--deleteCouponInfo)" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * <停用优惠券><status置为2>
	 *
	 * @create：2015年7月18日 上午11:45:07
	 * @author： zw
	 * @param couponId
	 */
	public void stopCouponInfo(String couponInfoId,Integer  status){
		CouponInfo couponInfo = new CouponInfo();
		try {
			couponInfo.setStatus(status);
			couponInfo.setId(couponInfoId);
			this.updateByPrimaryKeySelective(couponInfo, couponInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<停用优惠券><status置为2>(CouponInfoService--stopCouponInfo)" + e.getMessage());
		}
	}



	/*public void deleteinfo(String infoid) {
		//批量删除info
		if(infoid.contains(",")){
			CouponInfoExample example =new CouponInfoExample();
			com.lc.zy.oa.system.po.CouponInfoExample.Criteria criteria = example.createCriteria();
			String[] ids = infoid.split(",");
			criteria.andIdIn(new ArrayList(Arrays.asList(ids)));
			couponInfoMapper.deleteByExample(example);
		}else{
			couponInfoMapper.deleteByPrimaryKey(infoid);
		}
	}*/
 }

