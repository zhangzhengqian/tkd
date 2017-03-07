package com.lc.zy.ball.boss.framework.conpon.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.conpon.vo.CouponHistoryVo;
import com.lc.zy.ball.boss.framework.conpon.vo.CouponVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CouponHistoryMapper;
import com.lc.zy.ball.domain.oa.mapper.CouponInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.CouponMapper;
import com.lc.zy.ball.domain.oa.mapper.DeviceMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.po.Coupon;
import com.lc.zy.ball.domain.oa.po.CouponCriteria;
import com.lc.zy.ball.domain.oa.po.CouponHistory;
import com.lc.zy.ball.domain.oa.po.CouponHistoryCriteria;
import com.lc.zy.ball.domain.oa.po.CouponInfo;
import com.lc.zy.ball.domain.oa.po.CouponInfoCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.FreeMarkerUtils;

@Service
public class CouponService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CouponService.class);

	@Autowired
	private CouponMapper couponMapper = null;

	@Autowired
	private CouponHistoryMapper couponHistoryMapper;

	@Autowired
	private CouponInfoService couponInfoService;

	// @Autowired
	// private PushMessageService pushMessageService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private CouponInfoMapper couponInfoMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private final Gson gson = new Gson();

	/**
	 * 优惠券通知定时任务
	 */
	@Transactional(readOnly = true)
	/*
	 * public void couponTaskWork() { List<CouponTaskVo> couponTaskVos =
	 * couponMapper.couponForNotice();
	 * 
	 * for (CouponTaskVo couponTaskVo : couponTaskVos) { // 时间类型:从领取开始计算时长 if
	 * (couponTaskVo.getTimeType() == Constants.couponInfo.UNFIXTIME) { try {
	 * int days = DateUtil.daysBetween(couponTaskVo.getReceiveTime(), new
	 * Date()); if ((couponTaskVo.getDuration() - days) ==
	 * Constants.couponInfo.NOTICE) { try { // 对即将过期的优惠券用户发送短信提醒
	 * SmsUtil.sendMsg(couponTaskVo.getPhone(),
	 * Constants.notice.COUPONMSG.replaceAll("{price}",
	 * couponTaskVo.getAmount().toString()));
	 * pushMessageService.sendPushMessage(couponTaskVo.getId(),
	 * Constants.PushMessage.TICKER,
	 * Constants.PushMessage.COUPONTITLE,Constants.
	 * notice.COUPONMSG.replaceAll("{price}"
	 * ,couponTaskVo.getAmount().toString()), Constants.umeng.GOAPP,
	 * Constants.umeng.NOTIFICATION, Constants.umeng.PRODUCTIONMODETRUE,
	 * Constants.umeng.BADGE); } catch (Exception e) {
	 * logger.error("can't send msg,the phone is" + couponTaskVo.getPhone());
	 * logger.error(e.getMessage()); continue; } } } catch (ParseException e) {
	 * logger.error("receiveTime:" + couponTaskVo.getReceiveTime());
	 * logger.error(e.getMessage()); continue; } } // 时间类型:固定时间范围 if
	 * (couponTaskVo.getTimeType() == Constants.couponInfo.FIXTIME) { try { int
	 * days = DateUtil.daysBetween(new Date(), couponTaskVo.getEndTime()); if
	 * (days == Constants.couponInfo.NOTICE) { try { // 对即将过期的优惠券用户发送短信提醒
	 * SmsUtil.sendMsg(couponTaskVo.getPhone(),
	 * Constants.notice.COUPONMSG.replaceAll("{price}",
	 * couponTaskVo.getAmount().toString())); } catch (Exception e) {
	 * logger.error("can't send msg,the phone is" + couponTaskVo.getPhone());
	 * logger.error(e.getMessage()); continue; } } } catch (ParseException e) {
	 * logger.error("receiveTime:" + couponTaskVo.getReceiveTime());
	 * logger.error(e.getMessage()); continue; } } } }
	 */
	/**
	 * 
	 * <优惠券><根据优惠券主键获取优惠券信息>
	 *
	 * @create：2015年6月26日 上午11:42:01
	 * @author： lsh
	 * @param id
	 * @return
	 */
	public Coupon selectById(String id) {
		Coupon coupon = new Coupon();
		try {
			coupon = this.selectByPrimaryKey(Coupon.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据优惠券主键获取优惠券信息(CouponService--selectById)" + e.getMessage());
		}
		return coupon;
	}

	/**
	 * 
	 * <根据条件查询优惠券><功能具体实现>
	 *
	 * @create：2015年6月26日 上午11:21:38
	 * @author： cyy
	 * @param example 查询条件
	 * @return List<Coupon>
	 */
	/*
	 * public List<Coupon> selectByExample(CouponExample example) { return
	 * couponMapper.selectByExample(example); }
	 */

	/**
	 * 优惠券增加
	 * 
	 * @param record
	 * @author sl
	 */
	public void insertCoupon(Coupon record) {
		try {
			this.insertSelective(record, record.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("优惠券添加(CouponService--insertCoupon)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年7月18日 下午2:49:32
	 * @author： liuquan
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param suitType
	 * @return
	 * @throws Exception
	 */
	public Page<Coupon> find(Map<String, Object> searchParams, int page, int size, String suitType) throws Exception {
		PageRequest pageable = new PageRequest(page, size);
		CouponCriteria couponCriteria = new CouponCriteria();
		CouponCriteria.Criteria criteria = couponCriteria.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(criteria, filters.values());
		}
		couponCriteria.setMysqlOffset(pageable.getOffset());
		couponCriteria.setMysqlLength(pageable.getPageSize());
		couponCriteria.setOrderByClause(" c.create_time desc");
		int total = couponMapper.countByExample(couponCriteria);
		List<Coupon> list = couponMapper.selectByExample(couponCriteria);

		// 组装所有的适用类型
		/*
		 * for (Coupon coupon : list) { List<CouponInfo> infolist =
		 * couponInfoMapper.selectByCouponId(coupon.getId()); // 拼接礼包的优惠券类型
		 * String type = ""; if (!infolist.isEmpty()) { for (int i = 0; i <
		 * infolist.size(); i++) { String typeindex =
		 * infolist.get(i).getCouponType(); String coupontype = ""; switch
		 * (typeindex) { case "0": coupontype = "通用"; break; case "1":
		 * coupontype = "羽毛球"; break; case "2": coupontype = "网球"; break; case
		 * "3": coupontype = "篮球"; break; case "4": coupontype = "乒乓球"; break;
		 * case "5": coupontype = "高尔夫"; break; case "6": coupontype = "足球";
		 * break; case "7": coupontype = "台球"; break; case "8": coupontype =
		 * "保龄球"; break; case "9": coupontype = "预约教练"; break; case "10":
		 * coupontype = "活动报名"; break; case "11": coupontype = "赛事报名"; break; }
		 * if (i > 0) { type += "," + coupontype; } else { type = coupontype; }
		 * } coupon.setSuitType(type); } }
		 */

		return new PageImpl<>(list, pageable, total);
	}

	/**
	 * 
	 * <优惠券><获取优惠卷列表>
	 *
	 * @create：2015年6月30日 下午2:04:53
	 * @author： lsh
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	/**
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年7月18日 下午2:49:32
	 * @author： liuquan
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param suitType
	 * @return
	 * @throws Exception
	 */
	public Page<CouponVo> findCoupon(PageRequest pageRequest, Map<String, Object> searchParams,String ballType) throws Exception {
		CouponCriteria couponCriteria = new CouponCriteria();
		// 查询条件
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// 分页
		couponCriteria.setMysqlLength(pageRequest.getPageSize());
		couponCriteria.setMysqlOffset(pageRequest.getOffset());
		CouponCriteria.Criteria cri = couponCriteria.createCriteria();
		Criterias.bySearchFilter(cri, filters.values());
		cri.andDelFlagNotEqualTo(0);
		
		//bhg 搜索条件为优惠券类型
		if(ballType!=null){
			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			couponInfoCriteria.createCriteria().andCouponTypeEqualTo(Integer.parseInt(ballType));
			List<CouponInfo> couponInfoList = couponInfoMapper.selectByExample(couponInfoCriteria);
			List<String> couponIdList = new ArrayList<String>();
			for(CouponInfo ci :couponInfoList){
				couponIdList.add(ci.getCouponId());
			}
			cri.andIdIn(couponIdList);
		}
		
		
		// 排序
		couponCriteria.setOrderByClause("update_time desc");

		// 根据条件获取优惠券数量
		int total = couponMapper.countByExample(couponCriteria);
		// 根据条件获取优惠券列表
		List<Coupon> list = couponMapper.selectByExample(couponCriteria);
		List<CouponVo> listVos = new ArrayList<CouponVo>();
		for (int i = 0; i < list.size(); i++) {
			Coupon coupon = list.get(i);
			if (coupon == null) {
				continue;
			}
			CouponVo couponVo = new CouponVo();
			// 优惠券id
			couponVo.setCouponVoId(coupon.getId());
			// 优惠券描述
			couponVo.setCouponVoDescribe(coupon.getCouponDesc());
			// 优惠券名称
			couponVo.setCouponVoName(coupon.getName());
			// 优惠券总金额
			couponVo.setVoTotalAmount(coupon.getTotalAmount());
			// 优惠券上传时间
			couponVo.setVoCreateTime(coupon.getCreateTime());
			// 优惠券上传人员
			couponVo.setVoCreatePerson(coupon.getCreatePerson());
			// 优惠券状态
			couponVo.setVoStatus(coupon.getStatus());
			// 获取优惠券详细信息
			List<CouponInfo> couponInfos = couponInfoService.selectInfo(coupon.getId());
			// 有效期
			couponVo.setDuration(coupon.getDuration());
			// 城市
			couponVo.setCity(coupon.getCity());
			// 优惠券类型
			String couponInfoType = "";
			// 领取数量
			int couponCount=0;
			// 优惠券数量
			// 领取数量（首单优惠）
			int realCouponCount = 0;
			// 领取数量（分享赠送）
			// 领取金额（首单优惠）
			int couponMoney = 0;
			// 消费数量
			int useNum = 0;
			// 未消费数量
			int unUseNum = 0;
			// 消费金额
			int useAmount = 0;
			// 未消费金额
			int unUseAmount = 0;
			// 优惠券领取面值
			BigDecimal couponRealValue = new BigDecimal(0);
			for (CouponInfo couponInfo : couponInfos) {
				String couponType = String.valueOf(couponInfo.getCouponType());
				couponType = com.lc.zy.common.Constants.CouponType.getCouponType(couponType).getValue();
				String type = com.lc.zy.common.Constants.CouponTotalType.getCouponTotalType(String.valueOf(couponInfo.getType())).getValue();
				couponInfoType = couponInfoType +type+"-"+couponType+",";
				Map<String, Object> paras = new HashMap<String, Object>();
				paras.put("couponId", couponInfo.getId());
				paras.put("couponType", coupon.getType());
	            String sqlList = FreeMarkerUtils.format("/template/coupon/coupon_history.ftl", paras);
	            List<Map<String,Object>> results = jdbcTemplate.queryForList(sqlList);
	            if(CollectionUtils.isNotEmpty(results)){
	            	Map<String,Object> result = results.get(0);
	            	if(result.get("cont")!=null){
	            		realCouponCount = realCouponCount+((Long)result.get("cont")).intValue();
	            	}
	            	if(result.get("sumamount")!=null){
	            		couponMoney = couponMoney+((BigDecimal)result.get("sumamount")).intValue();
	            		couponRealValue = couponRealValue.add((BigDecimal)result.get("sumamount"));
	            	}
	            	if(result.get("used")!=null){
	            		useNum = useNum+((Long)result.get("used")).intValue();
	            	}
					if(result.get("unused")!=null){
						unUseNum = unUseNum+((Long)result.get("unused")).intValue();
	            	}
					if(result.get("usedamount")!=null){
						useAmount = useAmount+((Double)result.get("usedamount")).intValue();
					}
					if(result.get("unusedamount")!=null){
						unUseAmount = unUseAmount + ((BigDecimal)result.get("unusedamount")).intValue();
					}
	            }
			}
			// 优惠券领取数量
			couponCount = couponCount + useNum + unUseNum;
			// 优惠券已使用金额
			couponVo.setRealAmount(useAmount);
			// 领取数量
			couponVo.setGetCount(couponCount);
			// 运动类型
			couponVo.setCouponInfoType(couponInfoType.substring(0, couponInfoType.length() - 1));
			// 最大金额
			couponVo.setMaxAmount(String.valueOf(unUseAmount + useAmount));
			listVos.add(couponVo);
		}
		return new PageImpl<>(listVos, pageRequest, total);
	}

	/**
	 * 
	 * <通过优惠券的id查询优惠券的领取记录><优惠领取记录表和订单表，sso_user表，场馆表连个查询>
	 *
	 * @create：2015年7月1日 下午6:01:16
	 * @author： liuquan
	 * @param status
	 * @param start
	 * @param end
	 * @param couponid
	 * @param page
	 * @param size
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public Page<CouponHistoryVo> findHistory(String status, String start,
	 * String end, String couponid, int page, int size) throws ParseException {
	 * PageRequest pageable = new PageRequest(page, size); CouponHistoryExample
	 * couponHistoryExample = new CouponHistoryExample();
	 * couponHistoryExample.setMysqlOffset(pageable.getOffset());
	 * couponHistoryExample.setMysqlLength(pageable.getPageSize());
	 * com.lc.zy.oa.system.po.CouponHistoryExample.Criteria criteria =
	 * couponHistoryExample.createCriteria(); if (!StringUtils.isEmpty(start)) {
	 * DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date startd
	 * = fmt.parse(start); criteria.andReceiveTimeGreaterThanOrEqualTo(startd);
	 * } if (!StringUtils.isEmpty(end)) { DateFormat fmt = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date endd = fmt.parse(end);
	 * criteria.andReceiveTimeLessThanOrEqualTo(endd); } if
	 * (!StringUtils.isEmpty(status)) { int statue = Integer.parseInt(status);
	 * criteria.andIsUseEqualTo(statue); }
	 * criteria.andCouponIdEqualTo(couponid); int total =
	 * couponHistoryMapper.countRecord(couponHistoryExample);
	 * List<CouponHistoryVo> list =
	 * couponHistoryMapper.recordByExample(couponHistoryExample); return new
	 * PageImpl<>(list, pageable, total); }
	 */

	/**
	 * 
	 * <优惠券><更新优惠券>
	 *
	 * @create：2015年6月30日 下午2:10:09
	 * @author： lsh
	 * @param record
	 * @return
	 */
	public int updateConpon(Coupon record) throws Exception {
		return this.updateByPrimaryKeySelective(record, record.getId());
	}

	/**
	 * 
	 * <优惠券过期定时任务><功能具体实现>
	 *
	 * @create：2015年7月17日 下午4:57:35
	 * @author： zw
	 */

	/*
	 * public void updateStatusForWork() { try { CouponExample couponExample =
	 * new CouponExample(); Criteria criteria = couponExample.createCriteria();
	 * List<Integer> list = new ArrayList<Integer>(); List<String> keyList = new
	 * ArrayList<String>(); list.add(Constants.couponInfo.status.START);
	 * list.add(Constants.couponInfo.status.STOP); criteria.andStatusIn(list);
	 * criteria.andDelFlagEqualTo(Constants.couponInfo.NORMAL); String endTime =
	 * null; Date endDate = null; String couponKey = null; String giveKey =
	 * null; String orderKey = null; List<Coupon> coupons =
	 * couponMapper.selectByExample(couponExample); for (Coupon coupon :
	 * coupons) { endTime = coupon.getEndTime(); endDate =
	 * DateUtil.formatString(endTime, "yyyy-MM-dd"); endDate =
	 * DateUtil.getNextDate(endDate); if (DateUtil.compareDate(endDate, new
	 * Date()) < 1) { coupon.setStatus(Constants.couponInfo.status.END);
	 * couponMapper.updateByPrimaryKeySelective(coupon); CouponInfoExample
	 * couponInfoExample = new CouponInfoExample(); CouponInfoExample.Criteria
	 * couponInfoCriteria = couponInfoExample.createCriteria();
	 * couponInfoCriteria.andCouponIdEqualTo(coupon.getId()); List<CouponInfo>
	 * couponInfos = couponInfoMapper.selectByExample(couponInfoExample); for
	 * (CouponInfo couponInfo : couponInfos) { // 修改优惠劵信息的状态位
	 * couponInfoMapper.updateCouponInfoStatus(couponInfo.getId()); //
	 * 将优惠劵信息从redis中移除 couponKey = Constants.couponInfo.COUPON_KEY +
	 * couponInfo.getId(); giveKey = Constants.couponInfo.GIVE_KEY +
	 * couponInfo.getId(); orderKey = Constants.couponInfo.ORDER_KEY +
	 * couponInfo.getId(); keyList.clear(); keyList.add(couponKey);
	 * keyList.add(giveKey); keyList.add(orderKey); redisService.del(keyList);
	 * // 修改使用中的优惠券的状态为过期状态
	 * couponHistoryMapper.updateCouponHistoryIsUse(couponInfo.getId()); } } } }
	 * catch (Exception e) { e.printStackTrace(); logger.error("优惠券过期时间检测定时任务失败"
	 * + e.getMessage()); throw new RuntimeException(); } }
	 */

	/**
	 * 
	 * <删除大礼包><将del_flag置0>
	 *
	 * @create：2015年7月18日 上午10:35:35
	 * @author： zw
	 * @param couponId
	 */
	public void deleteCoupon(String couponId) {
		try {
			// 更新优惠券表id
			Coupon coupon = new Coupon();
			coupon.setId(couponId);
			coupon.setDelFlag(Integer.valueOf(Constants.couponInfo.DELETE));
			this.updateByPrimaryKeySelective(coupon, couponId);
			// 更新优惠券礼包各优惠券状态
			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			CouponInfoCriteria.Criteria criteria = couponInfoCriteria.createCriteria();
			criteria.andCouponIdEqualTo(couponId);
			List<CouponInfo> cList = couponInfoMapper.selectByExample(couponInfoCriteria);
			for (CouponInfo couponInfo : cList) {
				CouponInfo couponInfoSave = new CouponInfo();
				couponInfoSave.setDelFlag(Integer.valueOf(Constants.couponInfo.DELETE));
				couponInfoSave.setId(couponInfo.getId());
				this.updateByPrimaryKeySelective(couponInfoSave, couponInfo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除大礼包(CouponService--deleteCoupon)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <停用大礼包><status置为2>
	 *
	 * @create：2015年7月18日 上午11:45:07
	 * @author： zw
	 * @param couponId
	 */
	public void stopCoupon(String couponId, Integer status) {
		try {
			// 更新优惠券表状态
			Coupon coupon = new Coupon();
			coupon.setId(couponId);
			coupon.setStatus(status);
			this.updateByPrimaryKeySelective(coupon, couponId);

			// 更新优惠券信息表状态

			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			CouponInfoCriteria.Criteria criteria = couponInfoCriteria.createCriteria();
			criteria.andCouponIdEqualTo(couponId);
			List<CouponInfo> cList = couponInfoMapper.selectByExample(couponInfoCriteria);
			for (CouponInfo cInfo : cList) {
				cInfo.setStatus(status);
				this.updateByPrimaryKeySelective(cInfo, cInfo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("停用大礼包(CouponService--stopCoupon)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <优惠券table添加>
	 *
	 * @create：2015年7月18日 下午12:11:01
	 * @author： sl
	 * @param record
	 */
	public void insertCouponInfo(CouponInfo record) {
		try {
			this.insertSelective(record, record.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("优惠券table添加(CouponService--insertCouponInfo)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <更新table>
	 *
	 * @create：2015年7月18日 下午12:14:19
	 * @author： sl
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public void updateConponInfo(CouponInfo record) throws Exception {
		try {
			this.updateByPrimaryKeySelective(record, record.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("优惠券table更新(CouponService--insertCouponInfo)" + e.getMessage());
		}
	}

	/**
	 * 
	 * <根据优惠券id获取优惠券信息list>
	 *
	 * @create：2015年7月18日 下午4:13:28
	 * @author： sl
	 * @param id
	 * @return
	 */
	public List<CouponInfo> selectInfosById(String id,String code) {
		List<CouponInfo> couponInfos = new ArrayList<CouponInfo>();
		try {
			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			CouponInfoCriteria.Criteria criteria = couponInfoCriteria.createCriteria();
			criteria.andCouponIdEqualTo(id);
			if(StringUtils.isNotBlank(code)){
				criteria.andTypeEqualTo(Integer.parseInt(code));
			}
			couponInfos = couponInfoMapper.selectByExample(couponInfoCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据优惠券id获取优惠券信息list(CouponService--selectInfosById)" + e.getMessage());
		}
		return couponInfos;
	}

	/**
	 * 
	 * <优惠券信息删除CouponInfo>
	 *
	 * @create：2015年7月19日 下午5:13:11
	 * @author： sl
	 * @param id
	 */
	public void deleteCouponInfos(String id) {
		try {
			this.deleteByPrimaryKey(CouponInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("优惠券信息删除CouponInfo(CouponService--deleteCouponInfos)" + e.getMessage());
		}
	}
	
	/**
	 * <根据couponId查询优惠券领取列表>
	 * 
	 * @param status
	 * @param start
	 * @param end
	 * @param couponid
	 * @param page
	 * @param size
	 * @return
	 * @throws ParseException
	 */
	public Page<CouponHistoryVo> findCouponReceivings(String status, String start, String end, String couponid,
			int page, int size) throws ParseException {
		PageRequest pageable = new PageRequest(page, size);
		CouponHistoryCriteria couponHistoryCriteria = new CouponHistoryCriteria();
		couponHistoryCriteria.setMysqlLength(pageable.getPageSize());
		couponHistoryCriteria.setMysqlOffset(pageable.getOffset());
		CouponHistoryCriteria.Criteria criteria = couponHistoryCriteria.createCriteria();
		
		if (!StringUtils.isEmpty(start)) {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date startd = fmt.parse(start);
			criteria.andReceiveTimeGreaterThanOrEqualTo(startd);
		}
		if (!StringUtils.isEmpty(end)) {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date endd = fmt.parse(end);
			criteria.andReceiveTimeLessThanOrEqualTo(endd);
		}
		if (!StringUtils.isEmpty(status)) {
			int statue = Integer.parseInt(status);
			criteria.andIsUseEqualTo(statue);
		}
		if (StringUtils.isNotEmpty(couponid)) {
			criteria.andCouponIdEqualTo(couponid);
		}
		couponHistoryCriteria.setOrderByClause("receive_time desc");
		int total = couponHistoryMapper.countByExample(couponHistoryCriteria);
		List<CouponHistory> list = couponHistoryMapper.selectByExample(couponHistoryCriteria);
		List<CouponHistoryVo> listVos = new ArrayList<CouponHistoryVo>();
		for (CouponHistory ch : list) {
			// 获取用户信息
			SsoUser ssoUser = new SsoUser();
			CouponHistoryVo chVo = new CouponHistoryVo();
			try {
				ssoUser = this.selectByPrimaryKey(SsoUser.class, ch.getUserId());
				if (ssoUser != null) {
					if (ssoUser.getNickName() != null) {
						chVo.setUsername(ssoUser.getNickName());
					}
					// 用户手机号
					if (ssoUser.getPhone() != null) {
						chVo.setUserphone(ssoUser.getPhone());
					}
				}
				// 用户名称
				// 场馆名称
				if (ch.getOccName() != null) {
					chVo.setStatiumname(ch.getOccName());
				}
				// 获取优惠券名称
				Coupon coupon = this.selectByPrimaryKey(Coupon.class, ch.getCouponId());
				if (coupon != null) {
					chVo.setCouponname(coupon.getName());
				}
				// 领取时间
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (ch.getReceiveTime() != null) {
					chVo.setGettime(df.format(ch.getReceiveTime()));
				}
				// 优惠券状态
				chVo.setStatue(ch.getIsUse());
				listVos.add(chVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new PageImpl<>(listVos, pageable, total);
	}

	/**
	 * <根据couponId查询优惠券列表>
	 * 
	 * @param status
	 * @param couponType
	 * @param couponid
	 * @param page
	 * @param size
	 * @return
	 * @throws ParseException
	 */
	public Page<CouponInfo> findCouponInfos(String status, String couponType, String couponid, int page, int size)
			throws ParseException {
		PageRequest pageable = new PageRequest(page, size);
		int total = 0;
		List<CouponInfo> list = null;
		try {
			CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
			couponInfoCriteria.setMysqlLength(pageable.getPageSize());
			couponInfoCriteria.setMysqlOffset(pageable.getOffset());
			CouponInfoCriteria.Criteria criteria = couponInfoCriteria.createCriteria();
			if (!StringUtils.isEmpty(status)) {
				criteria.andStatusEqualTo(Integer.valueOf(status));
			}
			if (StringUtils.isNotEmpty(couponid)) {
				criteria.andCouponIdEqualTo(couponid);
			}
			if (!StringUtils.isEmpty(couponType)) {
				Integer couponTypeInt = Integer.parseInt(couponType);
				criteria.andCouponTypeEqualTo(couponTypeInt);
			}
			total = couponInfoMapper.countByExample(couponInfoCriteria);
			list = couponInfoMapper.selectByExample(couponInfoCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据couponId查询优惠券列表(CouponService--findCouponInfos)" + e.getMessage());
		}
		return new PageImpl<>(list, pageable, total);
	}

}
