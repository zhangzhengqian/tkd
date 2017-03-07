package com.lc.zy.ball.boss.framework.accounts.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lc.zy.ball.domain.oa.po.SsoUserBonusAccountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.accounts.service.AccountService;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountDetailVo;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountLogVo;
import com.lc.zy.ball.boss.framework.accounts.vo.UserAccountVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.UserAccountLog;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * <账户管理>
 * @author yankefei
 * @date 2015年11月26日 下午5:43:39
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountController extends AbstractController{
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * <账户列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author yankefei
	 * @date 2015年11月26日 下午5:50:54
	 */
    @RequestMapping(value = "list")
    public String accountList(Model model, HttpServletRequest request) throws Exception {
        //根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Page<UserAccountVo> onePage = accountService.find(searchParams, page, size, true, true);
        
        model.addAttribute("data", onePage);
        //将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "accounts/accountList";
    }

    /**
     * <奖金账户列表><>
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bonusList")
    public String bonusAccountList(Model model, HttpServletRequest request) throws Exception {
        //根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Map<String,Object> res =  accountService.findBonus(searchParams, page, size, true, true);
        model.addAttribute("data", res.get("data"));
        if(res.containsKey("0")){
            model.addAttribute("ffze", res.get("0"));
        }else{
            model.addAttribute("ffze",0);
        }
        if(res.containsKey("1")){
            model.addAttribute("xfze", res.get("1"));
        }else{
            model.addAttribute("xfze",0);
        }
        if(res.containsKey("3")){
            model.addAttribute("tkze", res.get("3"));
        }else{
            model.addAttribute("tkze",0);
        }
        //将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "accounts/bonusAccountList";
    }

    @RequestMapping(value = "bonusAccountView/{userId}")
    public String bonusAccountDetail(@PathVariable String userId, Model model, HttpServletRequest request){
        int begin = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        UserAccountDetailVo data = accountService.accountDetail(userId);
        Page<SsoUserBonusAccountLog> list = accountService.bonusAccountLogs(userId, begin, size);
        model.addAttribute("data", data);
        model.addAttribute("logs", list);
        return "accounts/bonusAccountDetail";
    }

    /**
     * <冻结账户><功能具体实现>
     * @param userId
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月27日 下午3:38:08
     */
    @RequestMapping(value = "freeze/{userId}")
    public String freeze(@PathVariable String userId, RedirectAttributes redirectAttributes) throws Exception{
    	boolean result = accountService.freeze(userId);
    	if(!result){
    		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "冻结账户失败！"));
    	}
    	return "redirect:/accounts/list";
    }
    
    /**
     * <解冻账户><功能具体实现>
     * @param userId
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月27日 下午4:52:27
     */
    @RequestMapping(value = "unfreeze/{userId}")
    public String unfreeze(@PathVariable String userId, RedirectAttributes redirectAttributes) throws Exception{
    	boolean result = accountService.unfreeze(userId);
    	if(!result){
    		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "解冻账户失败！"));
    	}
    	return "redirect:/accounts/list";
    }
    
    
    /**
     * <提现><功能具体实现>
     * @param userId
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月27日 下午4:52:27
     */
    @RequestMapping(value = "withdrawCash")
    public @ResponseBody String withdrawCash(String userId, String amount,String accountInfo,RedirectAttributes redirectAttributes) throws Exception{
    	Map<String, String> result = accountService.withdrawCash(userId,amount,accountInfo);
    	return MyGson.getInstance().toJson(result);
    }
    
    /**
     * <提现记录列表><功能具体实现>
     * @param model
     * @param request
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月30日 下午12:01:16
     */
    @RequestMapping(value = "withdrawCashList")
    public String withdrawCashList(Model model, HttpServletRequest request) throws Exception {
        //根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Page<UserAccountLogVo> onePage = accountService.findWithdrawCash(searchParams, page, size, true, true);
        
        model.addAttribute("data", onePage);
        //将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "accounts/withdrawCashList";
    }
    
    /**
     * <提现审核><功能具体实现>
     * @param logId
     * @param reason
     * @param redirectAttributes
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月27日 下午4:52:27
     */
    @RequestMapping(value = "audit/{action}/{logId}", method=RequestMethod.POST)
    public String audit(@PathVariable Integer action, @PathVariable String logId,String reason,RedirectAttributes redirectAttributes) throws Exception{
    	Map<String, String> map = accountService.audit(action, logId, reason);
    	if(!map.get("result").equals("0")){
    		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, map.get("reason")));
    	}
    	return "redirect:/accounts/withdrawCashList";
    }
    
    /**
     * <提现财务处理列表><功能具体实现>
     * @param model
     * @param request
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年12月2日 上午10:46:15
     */
    @RequestMapping(value = "withdrawCashFinance")
    public String withdrawCashFinance(Model model, HttpServletRequest request) throws Exception {
        //根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        CommonOAUtils.paramesTrim(searchParams);
        Page<UserAccountLogVo> onePage = accountService.findWithdrawCashFinance(searchParams, page, size, true, true);
        
        model.addAttribute("data", onePage);
        //将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "accounts/withdrawCashFinance";
    }
    
    /**
     * <提现确认转账><功能具体实现>
     * @return
     * @throws Exception
     * @author yankefei
     * @date 2015年11月27日 下午4:52:27
     */
    @RequestMapping(value = "process/{logId}", method=RequestMethod.POST)
    public String process(@PathVariable String logId, RedirectAttributes redirectAttributes) throws Exception{
    	Map<String, String> map = accountService.process(logId);
    	if(!map.get("result").equals("0")){
    		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, map.get("reason")));
    	}
    	return "redirect:/accounts/withdrawCashFinance";
    }
    
    /**
     * <查看账户详情><功能具体实现>
     * @param userId
     * @param model
     * @param request
     * @return
     * @author yankefei
     * @date 2015年12月7日 下午3:17:07
     */
    @RequestMapping(value = "accountView/{userId}")
    public String accountDetail(@PathVariable String userId, Model model, HttpServletRequest request){
        int begin = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        
    	UserAccountDetailVo data = accountService.accountDetail(userId);
    	Page<UserAccountLog> list = accountService.accountLogs(userId, begin, size);
    	model.addAttribute("data", data);
    	model.addAttribute("logs", list);
    	return "accounts/accountDetail";
    }


}
