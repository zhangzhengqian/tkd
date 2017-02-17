package com.lc.zy.ball.crm.framework.system.cardPay.controller;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.cardPay.service.CardPayManageService;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.CardPayVo;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.ClassVo;
import com.lc.zy.ball.crm.framework.system.cardPay.vo.UserAccountVo;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转
 */
@Controller
@RequestMapping(value = "/cardPay")
public class CardPayController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(CardPayController.class);

	@Autowired
	private CardPayManageService cardPayManageService = null;

	/**
	 *
	 * <获取app支付待签到订单><功能具体实现>
	 *
	 * @create：2016/12/21 上午11:50
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/appPay")
	public String appPay(Model model, HttpServletRequest request) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<CardPayVo> pageData = null;
		try {
			searchParams.put("EQ_statiumId", SessionUtil.currentStatium());
			// 获取订单list
			pageData = cardPayManageService.findOrder(searchParams, page, size);

			model.addAttribute("data", pageData);
			// searchParam用于分页时进行查询
			model.addAttribute("searchParams", Servlets
					.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.error("获取会员卡列表失败：{}",e.getMessage());
		}
		return "/cardPay/appPay";
	}

	/**
	 *
	 * <获取签到app订单信息><功能具体实现>
	 *
	 * @create：2016/12/21 下午5:42
	 * @author：sl
	 * @param orderId
	 * @param model
	 * @return java.lang.String
	 */
	@RequestMapping(value = "confirm_order_dlg/{orderId}")
	public String confirmOrder(@PathVariable("orderId") String orderId, Model model) {
		CardPayVo vo = cardPayManageService.orderByOrderId(orderId);
		model.addAttribute("order", vo);
		return "/cardPay/confirm_order_dlg";
	}

	/**
	 *
	 * <课时信息><功能具体实现>
	 *
	 * @create：2016/12/23 上午10:47
	 * @author：sl
	 * @param classInfoId
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "classPay/{classInfoId}")
	public String classPay(@PathVariable("classInfoId") String classInfoId, Model model, HttpServletRequest request) {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 课时信息
		ClassVo classVo = cardPayManageService.classInfo(classInfoId);
		model.addAttribute("classInfo", classVo);
		// 课时报名人员
		Page<ClassVo> pageData = null;
		try {
			// 获取订单list
			pageData = cardPayManageService.members(classInfoId, page, size);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("获取课时报名人员信息失败：{}",e.getMessage());
		}
		// 页面样式
		model.addAttribute("classInfoId", classInfoId);
		return "/cardPay/classPay";
	}

	/**
	 *
	 * <签到确认><功能具体实现>
	 *
	 * @create：2016/12/22 下午3:41
	 * @author：sl
	 * @param orderId
	 * @param model
	 * @return java.lang.String
	 */
	@RequestMapping(value = "confirmOrderAction/{orderId}")
	@ResponseBody
	public String confirmOrderAction(@PathVariable("orderId") String orderId, Model model) {
		Map<String, String> result = new HashMap<String, String >();
		try {
			cardPayManageService.confirmOrder(orderId);
			result.put(Constants.RESULT, Constants.SUCCESS);
		} catch (Exception e) {
			logger.debug("签到确认 {}", e.getMessage());
			result.put(Constants.RESULT, Constants.FAIL);
			result.put(Constants.DATA, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <刷卡init><功能具体实现>
	 *
	 * @create：2016/12/23 上午11:17
	 * @author：sl
	 * @param classInfoId
	 * @param model
	 * @return java.lang.String
	 */
	@RequestMapping(value = "card_pay_dlg/{classInfoId}")
	public String card_pay_dlg(@PathVariable("classInfoId") String classInfoId, Model model) {
		model.addAttribute("classInfoId", classInfoId);
		return "/cardPay/card_pay_dlg";
	}

	/**
	 *
	 * <根据手机号匹配用户><功能具体实现>
	 *
	 * @create：2016/12/23 下午1:48
	 * @author：sl
	 * @param q
	 * @return java.lang.String
	 */
	@RequestMapping(value = "userInfo")
	@ResponseBody
	public String userInfo (String q) {
		logger.debug("匹配条件 {}", q);
		List<UserAccountVo> accountVoList = cardPayManageService.users(q);
		if (!accountVoList.isEmpty()){
			String r = MyGson.getInstance().toJson(accountVoList);
			logger.debug(r);
			return r;
		}
		return null;
	}

	/**
	 *
	 * <获取用户账户信息><功能具体实现>
	 *
	 * @create：2016/12/23 下午2:19
	 * @author：sl
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "userAccount")
	@ResponseBody
	public String userAccount(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		UserAccountVo vo = null;
		try {
			// 获取订单list
			vo = cardPayManageService.userAccounts(searchParams);
			if (vo == null) {
				result.put(Constants.Result.RESULT, Constants.FAIL);
				result.put(Constants.Result.REASON, "无此用户！");
				return MyGson.getInstance().toJson(result);
			} else {
				result.put(Constants.Result.RESULT, Constants.SUCCESS);
				result.put(Constants.Result.DATA, vo);
				return MyGson.getInstance().toJson(result);
			}
		} catch (Exception e) {
			logger.error("获取会员卡列表失败：{}",e.getMessage());
			result.put(Constants.Result.RESULT, Constants.FAIL);
			result.put(Constants.Result.REASON, "查询用户失败！");
			return MyGson.getInstance().toJson(result);
		}
	}

	/**
	 *
	 * <刷卡save><功能具体实现>
	 *
	 * @create：2016/12/26 上午10:57
	 * @author：sl
	 * @param request
	 * @param userId
	 * @param classInfoId
	 * @param cardType
	 * @return java.lang.String
	 */
	@RequestMapping(value = "paySubmit/{userId}/{classInfoId}/{cardType}")
	@ResponseBody
	public String paySubmit(HttpServletRequest request,@PathVariable("userId") String userId,
							@PathVariable("classInfoId")String classInfoId, @PathVariable("cardType")int cardType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 判断用户是否报名
			if (cardPayManageService.checkMember(userId, classInfoId)) {
				result.put(Constants.Result.RESULT, Constants.FAIL);
				result.put(Constants.Result.REASON, "此学员已报名！");
				return MyGson.getInstance().toJson(result);
			}
			// 获取课时信息
			StatiumClassInfo info = cardPayManageService.classInfoById(classInfoId);
			// 获取课程信息
			StatiumClass statiumClass = cardPayManageService.classById(info.getClassId());
			// 获取账户信息
			CrmUserCardAccount account = null;
			// 获取期限卡信息
			CrmUserCardAccount dateAccount = null;
			if (cardType == Constants.cardType.CARD) {
				// 获取账户信息
				account = cardPayManageService.account(userId, cardType);
				// 判断用户账户余额是否不足
				if (statiumClass.getDiscountPrice() > account.getBalance()) {
					result.put(Constants.Result.RESULT, Constants.FAIL);
					result.put(Constants.Result.REASON, "账户余额不足！");
					return MyGson.getInstance().toJson(result);
				}
			} else if (cardType == Constants.cardType.DATECARD) {
				// 获取期限卡信息
				dateAccount = cardPayManageService.account(userId, cardType);
			}
			// 账户更新
			if (cardType == Constants.cardType.CARD) {
				account.setBalance(account.getBalance() - statiumClass.getDiscountPrice());
				account.setEt(new Date());
				account.setEb(SessionUtil.currentUserId());
				cardPayManageService.updateAccout(account);
			}
			// 创建订单
			Order order = new Order();
			order.setUserId(userId);
			order.setFee(statiumClass.getPrice());
			order.setFinalFee(statiumClass.getDiscountPrice());
			order.setClassType(statiumClass.getType());
			String orderId = cardPayManageService.creatOrder(order, cardType);
			// 创建orderItem
			OrderItem item = new OrderItem();
			item.setOrderId(orderId);
			item.setClassId(statiumClass.getId());
			item.setClassInfoId(info.getId());
			item.setClassName(statiumClass.getClassTitle());
			item.setClassInfoId(classInfoId);
			item.setStartTime(info.getClassStartTime());
			item.setEndTime(info.getClassEndTime());
			item.setSignDate(info.getClassDate());
			item.setPrice(statiumClass.getDiscountPrice());
			item.setOrdersType(String .valueOf(Constants.OrdersType.CLASS));
			cardPayManageService.saveItem(item);
			// 课时报名人员
			StatiumClassMember member = new StatiumClassMember();
			member.setUserId(userId);
			member.setClassInfoId(classInfoId);
			member.setOrderId(orderId);
			cardPayManageService.saveClassMember(member);
			// orderLog
			OrderLog orderlog = new OrderLog();
			orderlog.setOrderId(orderId);
			order.setUserId(userId);
			cardPayManageService.saveOrderLog(orderlog);
			// userCardLog
			CrmUserCardLog  crmUserCardLog = new CrmUserCardLog();
			crmUserCardLog.setOrderId(orderId);
			crmUserCardLog.setUserId(userId);
			// 判断支付类型（储值卡或期限卡）
			if (cardType == Constants.cardType.CARD) {
				crmUserCardLog.setAmount(-statiumClass.getDiscountPrice());
				crmUserCardLog.setBalance(account.getBalance()-statiumClass.getDiscountPrice());
				crmUserCardLog.setAccountId(account.getId());
			} else if (cardType == Constants.cardType.DATECARD) {
				crmUserCardLog.setAccountId(dateAccount.getId());
				crmUserCardLog.setCardId(dateAccount.getCardId());
				crmUserCardLog.setStartDate(dateAccount.getStartDate());
				crmUserCardLog.setEndDate(dateAccount.getEndDate());
			}
			crmUserCardLog.setDescription(Constants.cardHandel.PAYCARD);
			crmUserCardLog.setType(Constants.cardOpType.PAY);
			crmUserCardLog.setCardType(cardType);
			cardPayManageService.saveCrmUserCardLog(crmUserCardLog);

			result.put(Constants.Result.RESULT, Constants.SUCCESS);
			return MyGson.getInstance().toJson(result);

		} catch (Exception e) {
			logger.error("刷卡save：{}",e.getMessage());
			result.put(Constants.Result.RESULT, Constants.FAIL);
			result.put(Constants.Result.REASON, "刷卡失败！");
			return MyGson.getInstance().toJson(result);
		}
	}

}