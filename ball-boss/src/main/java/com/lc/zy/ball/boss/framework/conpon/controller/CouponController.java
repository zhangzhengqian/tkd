package com.lc.zy.ball.boss.framework.conpon.controller;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.carousel.service.CarouselService;
import com.lc.zy.ball.boss.framework.conpon.service.CouponInfoService;
import com.lc.zy.ball.boss.framework.conpon.service.CouponService;
import com.lc.zy.ball.boss.framework.conpon.vo.CouponHistoryVo;
import com.lc.zy.ball.boss.framework.conpon.vo.CouponVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CouponInfoMapper;
import com.lc.zy.ball.domain.oa.po.Carousel;
import com.lc.zy.ball.domain.oa.po.Coupon;
import com.lc.zy.ball.domain.oa.po.CouponInfo;
import com.lc.zy.ball.domain.oa.po.CouponInfoCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/coupon")
public class CouponController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponInfoMapper couponInfoMapper;

	@Autowired
	private CouponInfoService couponInfoService;

	@Autowired
	private RedisService redisService = null;

	@Autowired
	private CarouselService carouselService;

	/**
	 * 
	 * 优惠券页面初始化
	 *
	 * @create：2015年6月26日 上午11:38:01
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public String sign(String id, Model model, ServletRequest request) throws Exception {
		if (StringUtils.isNotEmpty(id)) {
			Coupon coupon = couponService.selectById(id);
			request.setAttribute("coupon", coupon);
			CouponVo couponVo = new CouponVo();
			int a = String.valueOf(coupon.getTotalAmount()).lastIndexOf(".");
			String ta = String.valueOf(coupon.getTotalAmount()).substring(0, a);
			couponVo.settAmount(ta);
			request.setAttribute("couponVo", couponVo);
		}
		List<Map<String,String>> couponTypes = new ArrayList<Map<String,String>>();
		for (com.lc.zy.common.Constants.CouponType s : EnumSet.allOf(com.lc.zy.common.Constants.CouponType.class)) {
			Map<String,String> couponType = new HashMap<String, String>();
			couponType.put("t",s.getType());
			couponType.put("v",s.getValue());
			couponTypes.add(couponType);
        }
		request.setAttribute("couponTypes", MyGson.getInstance().toJson(couponTypes));
		
		List<Map<String,String>> couponTotalTypes = new ArrayList<Map<String,String>>();
		Map<String,String> couponTotalTypeMap = new HashMap<String, String>();
		for (com.lc.zy.common.Constants.CouponTotalType s : EnumSet.allOf(com.lc.zy.common.Constants.CouponTotalType.class)) {
			Map<String,String> couponTotalType = new HashMap<String, String>();
			couponTotalType.put("t",s.getType());
			couponTotalType.put("v",s.getValue());
			couponTotalTypes.add(couponTotalType);
			couponTotalTypeMap.put(s.getType(), s.getValue());
        }
		request.setAttribute("couponTotalTypes", couponTotalTypes);
		request.setAttribute("couponTotalTypeMap", MyGson.getInstance().toJson(couponTotalTypeMap));
		// 获取优惠券table
		Set<String> couponTotalType = new HashSet<String>();
		if (StringUtils.isNotEmpty(id)) {
			List<CouponInfo> couponInfos = couponService.selectInfosById(id,null);
			Map<Integer, Map<Integer,CouponInfo>> couponInfoMap = new HashMap<Integer, Map<Integer,CouponInfo>>();
			for (CouponInfo info : couponInfos) {
				couponTotalType.add(String.valueOf(info.getType()));
				if(!couponInfoMap.containsKey(info.getType())){
					Map<Integer,CouponInfo> couponMapSub = new HashMap<Integer, CouponInfo>();
					couponMapSub.put(info.getCouponType(), info);
					couponInfoMap.put(info.getType(), couponMapSub);
				}else{
					Map<Integer,CouponInfo> couponMapSub = couponInfoMap.get(info.getType());
					couponMapSub.put(info.getCouponType(), info);
					couponInfoMap.put(info.getType(), couponMapSub);
				}
			}
			request.setAttribute("couponTotalType", couponTotalType);
			request.setAttribute("couponInfoMap", MyGson.getInstance().toJson(couponInfoMap));
		}
		return "/coupon/couponForm";
	}

	/**
	 * 
	 * 优惠券添加
	 *
	 * @create：2015年6月26日 上午11:37:22
	 * @author： sl
	 * @param myForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/couponForm/{objContent}", method = RequestMethod.POST)
	public String couponForm(@PathVariable String objContent, CouponVo myForm, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Coupon coupon = new Coupon();
			// 开始时间
			if (myForm.getVoStartTime() != null && !"".equals(myForm.getVoStartTime())) {
				coupon.setStartTime(sdf.parse(myForm.getVoStartTime()));
			} else {
				coupon.setStartTime(null);
			}
			// 结束时间
			if (myForm.getVoEndTime() != null && !"".equals(myForm.getVoEndTime())) {
				coupon.setEndTime(sdf.parse(myForm.getVoEndTime()));
			} else {
				coupon.setEndTime(null);
			}
			// 当前时间
			Date nowTime = sdf.parse(sdf.format(new Date()));
			// 更新时间
			coupon.setUpdateTime(nowTime);
			// 当前用户
			User user = SessionUtil.currentUser();
			// 更新人
			coupon.setUpdatePerson(user.getUserId());
			// 名称
			coupon.setName(myForm.getName());
			// 优惠券描述
			coupon.setCouponDesc(myForm.getCouponDesc());
			// 优惠券用途
			coupon.setType(myForm.getType());
			// 发放总额
			coupon.setTotalAmount(myForm.getTotalAmount());
			// 发放方式
			coupon.setAmountType(myForm.getAmountType());
			// 随机发放区间
			coupon.setStartAmount(myForm.getStartAmount());
			coupon.setEndAmount(myForm.getEndAmount());
			// 优惠券有效时长
			coupon.setDuration(myForm.getDuration()==null?null:Integer.valueOf(myForm.getDuration()));
			// 优惠券地区编码
			String areaCode = myForm.getAreaCode();
			coupon.setAreaCode(areaCode);
			// 优惠券城市
			if (StringUtils.isNotEmpty(areaCode)) {
				Map<String, String> areaMap = Zonemap.split(areaCode);
				if ("市辖区".equals(areaMap.get("city")) || areaMap.get("city") == null) {
					coupon.setCity(areaMap.get("province"));
				} else {
					coupon.setCity(areaMap.get("city"));
				}
			} else {
				// 城市
				coupon.setCity("0");
			}

			String id = UUID.get();
			if (StringUtils.isEmpty(myForm.getId())) { // 添加优惠券
				// 优惠券id
				coupon.setId(id);
				// 优惠券状态（0：未开始1：启用2：停用3：结束）默认为0
				coupon.setStatus(0);
				// 创建时间
				coupon.setCreateTime(nowTime);
				// 创建人
				coupon.setCreatePerson(user.getUserId());
				// 数据库insert coupon
				couponService.insertCoupon(coupon);
				redirectAttributes.addFlashAttribute("id", id);
			} else {// 更新优惠券
				coupon.setId(myForm.getId());
				couponService.updateConpon(coupon);
				redirectAttributes.addFlashAttribute("id", myForm.getId());
			}

			// 定额发放
			if (myForm.getAmountType() == 2 || myForm.getAmountType() == 1) {
				Type type = new TypeToken<List<Map<String,Object>>>() {}.getType();
				List<Map<String,Object>> tables = MyGson.getInstance().fromJson(objContent, type);
				for(Map<String,Object> tableInfoMap:tables){
					List<String> notRemoveIds = new ArrayList<String>();
					// 根据优惠券id查询优惠券礼包信息
					List<CouponInfo> couponInfos = null;
					if (StringUtils.isNotEmpty(coupon.getId())) {
						couponInfos = couponService.selectInfosById(coupon.getId(),(String)tableInfoMap.get("code"));
					}
					List<List> tableInfo = (List<List>)tableInfoMap.get("obj");
					for (List info : tableInfo) {
						CouponInfo couponInfo = new CouponInfo();
						couponInfo.setType(Integer.parseInt((String)tableInfoMap.get("code")));
						// id
						if (StringUtils.isBlank((String)info.get(5))) {
							String tableId = UUID.get();
							couponInfo.setId(tableId);
						} else {
							for (int i = 0; i < couponInfos.size(); i++) {
								if (((String)info.get(5)).equals(couponInfos.get(i).getId())) {
									notRemoveIds.add(couponInfos.get(i).getId());
								}
							}
							couponInfo.setId((String)info.get(5));
						}
						// 对应优惠券id
						couponInfo.setCouponId(coupon.getId());
						// 优惠券类型
						couponInfo.setCouponType(((Double)info.get(0)).intValue());
						// 　优惠券面值
						couponInfo.setCouponValue(((Double)info.get(1)).intValue());
						// 优惠券赠送面值
						couponInfo.setGiveValue(((Double)info.get(2)).intValue());
						// 订单分享面值
						couponInfo.setOrderValue(((Double)info.get(3)).intValue());
						// 优惠券数量
						couponInfo.setCouponCount(((Double)info.get(4)).intValue());

						if (StringUtils.isBlank((String)info.get(5))) {
							couponService.insertCouponInfo(couponInfo);
						} else {
							couponService.updateConponInfo(couponInfo);
						}
					}
					if (!StringUtils.isEmpty(coupon.getId())) {
						// 删除取消的优惠券
						for (CouponInfo temp : couponInfos) {
							if(!notRemoveIds.contains(temp.getId())){
								couponService.deleteCouponInfos(temp.getId());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("优惠券添加(couponForm)" + e.getMessage());
		}
		return "redirect:/coupon/list";
	}

	/**
	 * 
	 * <优惠券><获取优惠券列表>
	 *
	 * @create：2015年6月26日 下午2:37:28
	 * @author： lsh
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "list" })
	public String manager(Model model, ServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug("获取优惠券列表");
		logger.debug(searchParams.toString());
		// 将日期字符串转换成对象,这里注意，
		// 页面上 name=search_GTE_startTime ,到了这里，需要去掉前缀
		parseDate(searchParams, "GTE_startTime");
		parseDate(searchParams, "LTE_endTime");

		// 将搜索条件编码成字符串，用于排序，分页的URL //bhg 先把传页面参数处理过去
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CouponVo> data = null;
		// if (model.containsAttribute("id")) {
		// searchParams.put("EQ_id", model.asMap().get("id").toString());
		// data = couponService.findCoupon(new PageRequest(page, size),
		// searchParams);
		// } else {
		String ballType = "";
		if (searchParams.get("ballType") != null) {
			ballType = searchParams.get("ballType").toString();
		}

		if (StringUtils.isEmpty(ballType)) {
			ballType = null;
		} else {
			// 去掉特殊条件 券类型，单独去子表查询用
			searchParams.remove("ballType");
		}

		data = couponService.findCoupon(new PageRequest(page, size), searchParams, ballType);
		// }
		model.addAttribute("data", data);

		return "/coupon/couponList";
	}

	/**
	 * 根据活动的id查发放记录
	 * 
	 * @throws ParseException
	 */
	/*
	 * @RequestMapping(value="/record/{couponid}") public String record(Model
	 * model,ServletRequest request,@PathVariable String couponid,String
	 * start,String end,String status) throws ParseException{ int page =
	 * WebUtils.getPage(request); int size = WebUtils.getPageSize(request);
	 * Page<CouponHistoryVo> data =
	 * couponService.findHistory(status,start,end,couponid,page,size);
	 * model.addAttribute("data", data);
	 * model.addAttribute("couponid",couponid); return "/coupon/couponHistory";
	 * }
	 */

	/**
	 * 
	 * <优惠券><更新优惠券状态>
	 *
	 * @create：2015年6月30日 下午2:54:25
	 * @author： lsh
	 * @param couponId
	 * @param status
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public String updateCouponSatus(String couponId, String status, Model model, ServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		if (StringUtils.isEmpty(status)) { // 优惠券状态为必选参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包状态没有获取到，请联系技术人员！");
		} else if (StringUtils.isEmpty(couponId)) {// 优惠券id为必须参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包id没有获取到，请联系技术人员！");
		} else {
			Coupon coupon = couponService.selectById(couponId);
			if (coupon != null) {
				// 1：启用 2：停用 3:结束
				if (Constants.couponInfo.status.START == Integer.valueOf(status)
						|| Constants.couponInfo.status.STOP == Integer.valueOf(status)
						|| Constants.couponInfo.status.END == Integer.valueOf(status)) {
					// coupon.setStatus(Integer.valueOf(status));
					try {
						CouponInfoCriteria couponInfoCriteria = new CouponInfoCriteria();
						CouponInfoCriteria.Criteria criteria = couponInfoCriteria.createCriteria();
						criteria.andCouponIdEqualTo(coupon.getId());
						List<CouponInfo> couponInfos = couponInfoMapper.selectByExample(couponInfoCriteria);
						couponService.stopCoupon(couponId, Integer.valueOf(status));
						if (Constants.couponInfo.status.START == Integer.valueOf(status)) {
							for (CouponInfo couponInfo : couponInfos) {
								redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.COUPON_KEY
										+ couponInfo.getId(), couponInfo.getCouponCount());
								redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.GIVE_KEY
										+ couponInfo.getId(), couponInfo.getCouponCount());
								redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.ORDER_KEY
										+ couponInfo.getId(), couponInfo.getCouponCount());
							}

							// 启动优惠券时自动关联到我的轮播图中优惠券对应的信息
							// 获取app轮播图url
							List<Carousel> list = carouselService.selectByExample();
							if (!CollectionUtils.isEmpty(list)) {// 存在轮播图
																	// 默认第一轮播图
								Carousel info = new Carousel();
								if (list.get(0).getResourceId().contains("=")
										&& list.get(0).getResourceId().contains("couponId")) {
									StringBuilder url_new = new StringBuilder();
									url_new.append(
											list.get(0).getResourceId()
													.substring(0, list.get(0).getResourceId().lastIndexOf("=") + 1))
											.append(couponId);
									logger.debug("url_new=" + url_new.toString());
									info.setResourceId(url_new.toString());
									info.setId(list.get(0).getId());
									carouselService.updateCarousel(info);
								}
							}
							// 获取微信公众平台url
							List<Carousel> list_weixin = carouselService.selectWinxinUrl();
							if (!CollectionUtils.isEmpty(list_weixin)) {// 存在轮播图
																		// 默认第一轮播图
								Carousel info_weixin = new Carousel();
								if (list_weixin.get(0).getResourceId().contains("=")
										&& list.get(0).getResourceId().contains("couponId")) {
									StringBuilder url_weixin_new = new StringBuilder();
									url_weixin_new.append(
											list_weixin
													.get(0)
													.getResourceId()
													.substring(0,
															list_weixin.get(0).getResourceId().lastIndexOf("=") + 1))
											.append(couponId);
									logger.debug("url_new=" + url_weixin_new.toString());
									info_weixin.setResourceId(url_weixin_new.toString());
									info_weixin.setId(list_weixin.get(0).getId());
									carouselService.updateCarousel(info_weixin);
								}
							}

						}
						if (Constants.couponInfo.status.STOP == Integer.valueOf(status)) {
							for (CouponInfo couponInfo : couponInfos) {
								redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.COUPON_KEY
										+ couponInfo.getId());
								redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.GIVE_KEY
										+ couponInfo.getId());
								redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.ORDER_KEY
										+ couponInfo.getId());
							}
						}
						// couponService.updateConpon(coupon);
						// 将优惠劵信息添加到redis
						// String key = Constants.couponInfo.REDIS_KEY +
						// couponId;
						// redisService.set(key, gson.toJson(coupon));
						redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
						redirectAttributes.addFlashAttribute(Constants.Result.REASON, "");
						redirectAttributes.addFlashAttribute("id", couponId);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("更新优惠券updateCouponSatus" + e.getMessage());
					}

				}
			} else {
				redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
				redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包信息获取失败，请联系技术人员！");
			}
		}
		redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
		redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包状态更改成功");
		return "redirect:/coupon/list";
	}

	/**
	 * 
	 * <删除大礼包><couponId>
	 *
	 * @create：2015年7月18日 下午3:15:07
	 * @author： zw
	 * @param couponId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/deleteCoupon", method = RequestMethod.POST)
	public ModelAndView deleteCoupon(String couponId, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("redirect:/coupon/list");
		if (StringUtils.isBlank(couponId)) {// 优惠券id为必须参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包id没有获取到，请联系技术人员！");
			return model;
		}
		Coupon coupon = couponService.selectById(couponId);
		if (coupon == null) {
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包id没有获取到，请联系技术人员！");
			return model;
		}
		try {
			couponService.deleteCoupon(couponId);
		} catch (Exception e) {
			logger.error(e.getMessage() + "大礼包删除失败，请联系技术人员！couponId:" + couponId);
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包删除失败，请联系技术人员！couponId:" + couponId);
			return model;
		}
		redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
		redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包成功删除");
		return model;
	}

	/**
	 * 
	 * <删除优惠券><couponInfoId>
	 *
	 * @create：2015年7月18日 下午3:15:07
	 * @author： zw
	 * @param couponId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/deleteCouponInfo", method = RequestMethod.POST)
	public ModelAndView deleteCouponInfo(String couponInfoId, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("redirect:/admin/coupon");
		if (StringUtils.isBlank(couponInfoId)) {// 优惠券id为必须参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券id没有获取到，请联系技术人员！");
			return model;
		}
		CouponInfo couponInfo = couponInfoService.selectByPrimaryKey(couponInfoId);
		if (couponInfo == null) {
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券id没有获取到，请联系技术人员！");
			return model;
		}
		try {
			couponInfoService.deleteCouponInfo(couponInfoId);
		} catch (Exception e) {
			logger.error(e.getMessage() + "大礼包删除失败，请联系技术人员！couponId:" + couponInfoId);
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包删除失败，请联系技术人员！couponId:" + couponInfoId);
			return model;
		}
		redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
		redirectAttributes.addFlashAttribute(Constants.Result.REASON, "大礼包成功删除");
		return model;
	}

	/**
	 * 
	 * <优惠券><更新优惠券状态>
	 *
	 * @create：2015年6月30日 下午2:54:25
	 * @author： lsh
	 * @param couponId
	 * @param status
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCouponInfoStatus", method = RequestMethod.POST)
	public String updateCouponInfoSatus(String couponInfoId, String status, Model model, ServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		if (StringUtils.isEmpty(status)) { // 优惠券状态为必选参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券状态没有获取到，请联系技术人员！");
		} else if (StringUtils.isEmpty(couponInfoId)) {// 优惠券id为必须参数
			redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
			redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券id没有获取到，请联系技术人员！");
		} else {
			CouponInfo couponInfo = couponInfoService.selectByPrimaryKey(couponInfoId);
			if (couponInfo != null) {
				// 1：启用 2：停用 3:结束
				if (Constants.couponInfo.status.START == Integer.valueOf(status)
						|| Constants.couponInfo.status.STOP == Integer.valueOf(status)
						|| Constants.couponInfo.status.END == Integer.valueOf(status)) {
					// coupon.setStatus(Integer.valueOf(status));
					try {
						couponInfoService.stopCouponInfo(couponInfoId, Integer.valueOf(status));
						if (Constants.couponInfo.status.START == Integer.valueOf(status)) {
							redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.COUPON_KEY
									+ couponInfo.getId(), couponInfo.getCouponCount());
							redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.GIVE_KEY
									+ couponInfo.getId(), couponInfo.getCouponCount());
							redisService.zsetAdd(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.ORDER_KEY
									+ couponInfo.getId(), couponInfo.getCouponCount());
						}
						if (Constants.couponInfo.status.STOP == Integer.valueOf(status)) {
							redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.COUPON_KEY
									+ couponInfo.getId());
							redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.GIVE_KEY
									+ couponInfo.getId());
							redisService.zsetRemove(Constants.couponInfo.REDIS_KEY, Constants.couponInfo.ORDER_KEY
									+ couponInfo.getId());
						}
						redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
						redirectAttributes.addFlashAttribute(Constants.Result.REASON, "");
						redirectAttributes.addFlashAttribute("id", couponInfoId);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("更新优惠券updateCouponSatus" + e.getMessage());
					}

				}
			} else {
				redirectAttributes.addFlashAttribute(Constants.Result.RESULT, false);
				redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券信息获取失败，请联系技术人员！");
			}
		}
		redirectAttributes.addFlashAttribute(Constants.Result.RESULT, true);
		redirectAttributes.addFlashAttribute(Constants.Result.REASON, "优惠券状态更改成功");
		return "redirect:/coupon/list";
	}

	/**
	 * <根据couponId查询优惠券领取列表>
	 * 
	 * @param model
	 * @param couponid
	 * @param couponInfoid
	 * @param start
	 * @param end
	 * @param status
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/couponReceivingsByCouponId/{couponid}/{couponInfoid}")
	public String couponReceivingsByCouponId(Model model, @PathVariable String couponid,
			@PathVariable String couponInfoid, HttpServletRequest request) throws ParseException {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		// parseDate(searchParams, "GTE_receiveTime");
		// parseDate(searchParams, "LTE_receiveTime");

		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		String state = "";
		String start = "";
		String end = "";
		if (!searchParams.isEmpty()) {
			state = searchParams.get("EQ_state").toString();
			start = searchParams.get("GTE_receiveTime").toString();
			end = searchParams.get("LTE_receiveTime").toString();
		}
		Page<CouponHistoryVo> data = couponService.findCouponReceivings(state, start, end, couponInfoid, page, size);
		model.addAttribute("data", data);
		model.addAttribute("couponid", couponid);
		model.addAttribute("couponInfoid", couponInfoid);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/coupon/couponReceivings";
	}

	/**
	 * <根据couponId查询优惠券列表>
	 * 
	 * @param model
	 * @param couponid
	 * @param couponType
	 * @param status
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/couponInfosByCouponId/{couponid}")
	public String couponInfosByCouponId(Model model, @PathVariable String couponid, String couponType, String status,
			ServletRequest request) throws ParseException {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CouponInfo> data = couponService.findCouponInfos(status, couponType, couponid, page, size);
		model.addAttribute("data", data);
		model.addAttribute("couponid", couponid);
		return "/coupon/couponInfos";
	}

}
