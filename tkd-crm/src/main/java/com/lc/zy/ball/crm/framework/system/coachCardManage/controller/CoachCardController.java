package com.lc.zy.ball.crm.framework.system.coachCardManage.controller;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.cardManage.vo.CardHistoryVo;
import com.lc.zy.ball.crm.framework.system.coachCardManage.service.CoachCardService;
import com.lc.zy.ball.crm.framework.system.coachCardManage.vo.UserClassVo;
import com.lc.zy.ball.domain.oa.mapper.CoachClassCardMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.CoachClassCard;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
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
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "/userClassManage")
public class CoachCardController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(CoachCardController.class);

	@Autowired
	private CoachClassCardMapper coachCardMapper;
	
	@Autowired
	private CoachCardService coachCardService;
	
	@Autowired
	private SsoUserMapper ssoUserMapper;
	
	/**
	 * 
	 * <获取卡片列表><功能具体实现>
	 *
	 * @create：2017年4月12日 下午4:15:24
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
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
		Page<CoachClassCard> pageData = null;
		try {
			// 获取订单list
			pageData = coachCardService.findCoachCard(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("获取会员卡列表失败：{}",e.getMessage());
		}
		return "/coachCardManage/cardList";
	}

	/**
	 *
	 * <初始化添加私教卡><功能具体实现>
	 *
	 * @create：2016/11/29 上午10:26
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/initCardForm")
	public String initCardForm(Model model, HttpServletRequest request) throws Exception {
		return "/coachCardManage/coachCardForm";
	}
	
	/**
	 * 
	 * <初始化学员购买课程><功能具体实现>
	 *
	 * @create：2017年4月12日 上午11:59:04
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/initClassBuy")
	public String initClassBuy(Model model, HttpServletRequest request) throws Exception {
		return "/coachCardManage/classBuyForm";
	}

	/**
	 *
	 * <私教卡片信息><功能具体实现>
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
		Integer unitPrice = 0;
		CoachClassCard coachCard = coachCardService.getCardFormById(cardId);
		if(!coachCard.getClassId().equals("")){
			unitPrice = coachCardService.getUnitPrice(coachCard.getClassId());
		}
		model.addAttribute("unitPrice", unitPrice/100);
		model.addAttribute("coachCard", coachCard);
		return "/coachCardManage/coachCardForm";
	}

	/**
	 *
	 * <保存卡片信息><功能具体实现>
	 *
	 * @create：2016/11/29 上午11:15
	 * @author：
	 * @param myForm
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/saveCoachCard")
	public String saveCard(CoachClassCard myForm, HttpServletRequest request) throws Exception{
		coachCardService.saveCard(myForm);
		return "redirect:/userClassManage/cardList";
	}


	/**
	 * 
	 * <获取用户课程list><功能具体实现>
	 *
	 * @create：2017年4月11日 上午9:44:42
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userClassList")
	public String cardUser(Model model, HttpServletRequest request) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<UserClassVo> pageData = null;
		try {
			// 获取订单list
			pageData = coachCardService.findUserClassCard(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("用户课程list：{}",e.getMessage());
		}
		return "/coachCardManage/userClassList";
	}


	/**
	 *
	 * <获取课程><功能具体实现>
	 *
	 * @create：2016/12/1 上午10:35
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/statium_query_dlg")
	public String statiumQueryDlg(Model model, HttpServletRequest request) throws Exception {
		try {
			//获取当前道馆id
			String statiumId = SessionUtil.currentStatium();
			List<StatiumClass> classList = coachCardService.getStatiumClass(statiumId);
			model.addAttribute("classList", classList);
		} catch (Exception e) {
			logger.error("获取卡片失败: {}", e);
		}
		return "/coachCardManage/statium_query_dlg";
	}
	
	/**
	 * 
	 * <激活私教卡><功能具体实现>
	 *
	 * @create：2017年4月10日 下午4:41:30
	 * @author：zzq
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="/activeCard")
	@ResponseBody
	public String activeCoachCard(String cardId){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			coachCardService.activeCard(cardId);
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
	 * @create：2017年4月10日 下午4:56:13
	 * @author：zzq
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="/freezeCard")
	@ResponseBody
	public String freezeCard(String cardId){
		Map<String, String > result = new HashMap<String, String >();
		try {
			coachCardService.freezeCard(cardId);
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
	 * <获取私教卡><功能具体实现>
	 *
	 * @create：2017年4月11日 上午10:20:49
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/card_query_dlg")
	public String cardQueryDlg(Model model, HttpServletRequest request) throws Exception {
		try {
			//获取私教卡
			List<CoachClassCard> cards = coachCardService.getCoachCards();
			model.addAttribute("coachCard", cards);
		} catch (Exception e) {
			logger.error("获取卡片失败: {}", e);
		}
		return "/coachCardManage/coachCard_query_dlg";
	}
	
	/**
	 * 
	 * <学员买次数卡><功能具体实现>
	 *
	 * @create：2017年4月11日 上午11:28:29
	 * @author：zzq
	 * @param myForm
	 * @return
	 */
	@RequestMapping(value="/saveCardUser")
	public String saveUserClass(UserClassVo myForm){
		//学员买课
		coachCardService.saveUserClass(myForm);
		return "redirect:/userClassManage/userClassList";
	}
	
	/**
	 * 
	 * <手机号返回用户><功能具体实现>
	 *
	 * @create：2017年4月11日 下午1:04:43
	 * @author：zzq
	 * @param phone
	 * @return
	 */
	public SsoUser checkUserByPhone(String phone) {
        SsoUser user = null;
        try {
            // 判断此用户是否存在
            SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
            SsoUserCriteria.Criteria criteria = ssoUserCriteria.createCriteria();
            criteria.andPhoneEqualTo(phone);
            List<SsoUser> users = ssoUserMapper.selectByExample(ssoUserCriteria);
            if (!users.isEmpty()) {
                user =  users.get(0);
            }
        } catch (Exception e) {
            logger.debug("根据手机号获取用户信息 {}", e.getMessage());
        }
        return user;
    }
	
	/**
	 * 
	 * <获取某张私教卡卡片的转卡历史><功能具体实现>
	 *
	 * @create：2017年4月17日 下午5:39:57
	 * @author：zzq
	 * @param cardId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cardHistoryList/{cardId}")
	public String cardHistoryList(@PathVariable String cardId,HttpServletRequest request,Model model){
		logger.debug(cardId);
			
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<CardHistoryVo> pageData = null;
		try {
			searchParams.put("EQ_cardId", cardId);
			// 获取订单list
			pageData = coachCardService.findCardHistoty(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
			model.addAttribute("cardId", cardId);
		} catch (Exception e) {
			logger.error("获取卡片购买历史列表失败：{}", e.getMessage());
		}
		return "/coachCardManage/cardHistory";
	
		
	}
	
}	
	