package com.lc.zy.ball.crm.framework.system.cardManage.controller;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.cardManage.service.CardManageService;
import com.lc.zy.ball.crm.framework.system.cardManage.vo.*;
import com.lc.zy.ball.domain.oa.po.CrmCard;
import com.lc.zy.ball.domain.oa.po.CrmUserCardLog;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转
 */
@Controller
@RequestMapping(value = "/cardManage")
public class CardManageController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(CardManageController.class);

	@Autowired
	private CardManageService cardManageService = null;

	/**
	 *
	 * <获取会员卡列表><功能具体实现>
	 *
	 * @create：2016/12/15 下午4:02
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/cardList")
	public String cardList(Model model, HttpServletRequest request) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<CrmCard> pageData = null;
		try {
			searchParams.put("EQ_statiumId", SessionUtil.currentStatium());
			// 获取订单list
			pageData = cardManageService.findCard(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("获取会员卡列表失败：{}",e.getMessage());
		}
		return "/cardManage/cardList";
	}

	/**
	 *
	 * <初始化careForm><功能具体实现>
	 *
	 * @create：2016/11/29 上午10:26
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/initCardForm")
	public String initCardForm(Model model, HttpServletRequest request) throws Exception {
		return "/cardManage/cardForm";
	}

	/**
	 *
	 * <卡片信息><功能具体实现>
	 *
	 * @create：2016/11/29 上午10:31
	 * @author：sl
	 * @param model
	 * @param request
	 * @param cardId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/cardForm")
	public String cardForm(Model model, HttpServletRequest request, String cardId) throws Exception{
		CrmCard crmCard = cardManageService.getCardFormById(cardId);
		model.addAttribute("card", crmCard);
		return "/cardManage/cardForm";
	}

	/**
	 *
	 * <保存卡片信息><功能具体实现>
	 *
	 * @create：2016/11/29 上午11:15
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/saveCard")
	public String saveCard(CrmCard myForm, HttpServletRequest request) throws Exception{
		cardManageService.saveCard(myForm);
		return "redirect:/cardManage/cardList";
	}

	/**
	 *
	 * <激活卡片><功能具体实现>
	 *
	 * @create：2016/11/29 上午11:26
	 * @author：sl
	 * @param request
	 * @param cardId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/activeCard")
	@ResponseBody
	public String activeCard(HttpServletRequest request, String cardId) throws Exception{
		Map<String, String> result = new HashMap<String, String >();
		try {
			cardManageService.activeCard(cardId);
			result.put(Constants.RESULT, Constants.SUCCESS);
		} catch (Exception e) {
		    logger.debug("激活卡片 {}", e.getMessage());
			result.put(Constants.RESULT, Constants.FAIL);
			result.put(Constants.DATA, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <冻结卡片><功能具体实现>
	 *
	 * @create：2016/11/29 上午11:26
	 * @author：sl
	 * @param request
	 * @param cardId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/freezeCard")
	@ResponseBody
	public String freezeCard(HttpServletRequest request, String cardId) throws Exception{
		Map<String, String > result = new HashMap<String, String >();
		try {
			cardManageService.freezeCard(cardId);
			result.put(Constants.RESULT, Constants.SUCCESS);
		} catch (Exception e) {
			logger.debug("冻结卡片 {}", e.getMessage());
			result.put(Constants.RESULT, Constants.FAIL);
			result.put(Constants.DATA, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <会员卡list><功能具体实现>
	 *
	 * @create：2016/11/29 下午2:30
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/cardUser")
	public String cardUser(Model model, HttpServletRequest request) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<UserCardVo> pageData = null;
		try {
			// 获取订单list
			pageData = cardManageService.findUserCard(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("会员卡list：{}",e.getMessage());
		}
		return "/cardManage/cardUser";
	}

    /**
     *
     * <初始化购卡页面><功能具体实现>
     *
     * @create：2016/12/1 下午1:43
     * @author：sl
     * @param model
     * @param request
     * @return java.lang.String
     */
	@RequestMapping(value="/initCardBuy")
	public String initCardBuy(Model model, HttpServletRequest request) throws Exception {
		return "/cardManage/cardBuyForm";
	}

	/**
	 *
	 * <获取会员卡><功能具体实现>
	 *
	 * @create：2016/12/1 上午10:35
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/card_query_dlg")
	public String cardQueryDlg(Model model, HttpServletRequest request) throws Exception {
		try {
			List<CrmCard> cards = cardManageService.getCards();
			model.addAttribute("cards", cards);
		} catch (Exception e) {
			logger.error("获取卡片失败: {}", e);
		}
		return "/cardManage/card_query_dlg";
	}

	/**
	 *
	 * <用户购卡><功能具体实现>
	 *
	 * @create：2016/12/5 下午7:06
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/saveCardUser")
    public String saveCardUser(UserCardVo myForm, HttpServletRequest request) throws Exception{
        cardManageService.saveCardUser(myForm);
        return "redirect:/cardManage/cardUser";
    }

    /**
     *
     * <用户卡片明细><功能具体实现>
     *
     * @create：2016/12/6 下午2:28
     * @author：sl
     * @param request
     * @param accountId
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(value = "/cardUserDetail/{accountId}")
    public String dateCardUserDetail(HttpServletRequest request,
									 @PathVariable("accountId") String accountId, Model model) throws Exception{
        UserCardVo userCardVo = cardManageService.cardUser(accountId);
        model.addAttribute("userVo", userCardVo);
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<CrmUserCardLog> pageData = cardManageService.cardUserLog(new PageRequest(page, size), accountId);
        model.addAttribute("data", pageData);
        return "/cardManage/cardUserDetail";
    }

	/**
	 *
	 * <转卡form初始化><功能具体实现>
	 *
	 * @create：2016/12/7 下午2:05
	 * @author：sl
	 * @param model
	 * @param request
	 * @param accountId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/initTurnCard/{accountId}")
	public String initTurnCard(Model model, HttpServletRequest request,
							   @PathVariable("accountId")String accountId) throws Exception {
		UserCardVo userCardVo = cardManageService.cardUser(accountId);
		model.addAttribute("userVo", userCardVo);
		return "/cardManage/turnCardForm";
	}

	/**
	 *
	 * <转卡><功能具体实现>
	 *
	 * @create：2016/12/13 上午9:42
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/saveTurnCard")
	public String saveTurnCard (TurnCardVo myForm, HttpServletRequest request) {
		cardManageService.saveTurnCard(myForm);
		return "redirect:/cardManage/cardTurnList";
	}

	/**
	 *
	 * <查询转卡历史><功能具体实现>
	 *
	 * @create：2016/12/13 上午9:50
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/cardTurnList")
	public String cardTurnList (Model model, HttpServletRequest request) {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<CardTurnListVo> pageData = null;
		try {
			// 获取订单list
			pageData = cardManageService.findCardTurn(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("获取订单列表失败：{}",e.getMessage());
		}
		return "/cardManage/cardTurnList";
	}

	/**
	 *
	 * <充值／延期初始化><功能具体实现>
	 *
	 * @create：2016/12/14 下午2:02
	 * @author：sl
	 * @param model
	 * @param request
	 * @param accountId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/initRechargeCard/{accountId}")
	public String initRechargeCard(Model model, HttpServletRequest request,
							   @PathVariable("accountId")String accountId) throws Exception {
		UserCardVo userCardVo = cardManageService.cardUser(accountId);
		model.addAttribute("userVo", userCardVo);
		return "/cardManage/rechargeCardForm";
	}

	/**
	 *
	 * <账户充值／续费><功能具体实现>
	 *
	 * @create：2016/12/14 下午3:19
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/saveRechargeCard")
	public String saveRechargeCard (RechargeCardVo myForm, HttpServletRequest request) throws Exception {
		cardManageService.saveRechargeCard(myForm);
		return "redirect:/cardManage/cardUserDetail/" + myForm.getAccountId();
	}

	/**
	 *
	 * <获取卡片购买历史><功能具体实现>
	 *
	 * @create：2016/12/15 下午4:07
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/cardHistoryList/{cardId}")
	public String cardHistoryList(Model model, HttpServletRequest request, @PathVariable("cardId")String cardId) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<CardHistoryVo> pageData = null;
		try {
			searchParams.put("EQ_statiumId", SessionUtil.currentStatium());
			searchParams.put("EQ_cardId", cardId);
			// 获取订单list
			pageData = cardManageService.findCardHistoty(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
			model.addAttribute("cardId", cardId);
		} catch (Exception e) {
			logger.error("获取卡片购买历史列表失败：{}", e.getMessage());
		}
		return "/cardManage/cardHistory";
	}
}