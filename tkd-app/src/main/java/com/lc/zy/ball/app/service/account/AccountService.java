package com.lc.zy.ball.app.service.account;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.account.bean.UserAccountLogDetailVo;
import com.lc.zy.ball.app.service.account.bean.UserAccountLogVo;
import com.lc.zy.ball.app.service.account.bean.UserAccountVo;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <用户账户模块>
 * @author yankefei
 * @date 2015年10月22日 下午6:42:32
 */
@Service("account")
@Auth
public class AccountService {
    
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CommonService commonService;
    
    /**
     * <获取账户总额><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年10月22日 下午6:50:45
     */
    public Success getAccount(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
    	SsoUser user = commonService.getSsoUserByToken(token);
    	logger.info("token={}", token);
    	if(user!=null){
    		//0为未冻结，1为冻结
    		boolean isFreeze = accountRepository.isFreeze(user.getId());
    		UserAccountVo userAccountVo = accountRepository.totalAmount(user.getId());
    		KeyValueEntity entity = new KeyValueEntity("amount", userAccountVo);

            return new Success(sn, true, entity);
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }
    
    /**
     * <充值><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年10月26日 上午11:32:36
     */
    public Success recharge(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        String ip = request.getIp();
    	//充值金额，必须为整型
    	String amount = AppRequestUtil.getParameter(request, "amount");
    	//充值类型，1支付宝，2微信
    	Integer type = AppRequestUtil.getParameterInteger(request, "type");
    	
    	logger.debug("token={}, ip={}, amount={}, type={}, sign={}",	token, ip, amount, type);
    	
    	if(token==null || "".equals(token)){
    		return new Success(sn, false, new Reason("exception", "token不能为空"));
    	}else if(amount == null || "".equals(amount) || "0".equals(amount)){
    		return new Success(sn, false, new Reason("exception", "充值金额不能为空且不能为0"));
    	}else if(type==null){
    		return new Success(sn, false, new Reason("exception", "充值类型不能为空"));
    	}
    	SsoUser user = commonService.getSsoUserByToken(token);
    	if(user!=null){
    		//冻结账户无法操作
    		if(accountRepository.isFreeze(user.getId())){
    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
    		}
    		KeyValueEntity entity = accountRepository.recharge(user.getId(), type, amount, ip);
            return new Success(sn, true, entity);
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }
    
    /**
     * <提现><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年10月29日 下午3:29:03
     */
    /*@Auth(true)
    public Success withdrawCash(ClientRequest request){
    	String sn = request.getSn();
    	String token = request.getToken();
    	//用户输入提现金额
    	String amount = AppRequestUtil.getParameter(request, "amount");
    	//提现账户类型1支付宝，2微信
    	Integer type = AppRequestUtil.getParameterInteger(request, "type");
    	//支付宝账号，微信提现不传
    	String attrInfo = AppRequestUtil.getParameter(request, "attrInfo");
    	
    	if(StringUtils.isEmpty(amount)){
    		return new Success(sn, false, new Reason("exception", "提现金额不能为空"));
    	}
    	if(Double.parseDouble(amount) < 1.0d){
            return new Success(sn, false, new Reason("exception", "提现金额至少1元起"));
        }
    	if(type < 0){
    		return new Success(sn, false, new Reason("exception", "type不能为空"));
    	}
    	
    	logger.info("token={}, amount={}, type={}", token, amount, type);
    	SsoUser user = commonService.getSsoUserByToken(token);
    	if(user!=null){
    		//冻结账户无法操作
    		if(accountRepository.isFreeze(user.getId())){
    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
    		}
    		Map<String, String> map = accountRepository.withdrawCash(user.getId(), type, amount, attrInfo);
    		if("0".equals(map.get("success"))){
    			return new Success(sn, true, new KeyValueEntity("success", "提现申请已提交，请耐心等待系统处理..."));
    		}else if("1".equals(map.get("success"))){
    			//表示微信账号未绑定
    			return new Success(sn, false, new KeyValueEntity("noBind", map.get("reason")));
    		}else{
    			return new Success(sn, false, new KeyValueEntity("exception", map.get("reason")));
    		}
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }*/
    
    /**
     * <获取账户绑定的银行/支付宝/微信账号信息><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年10月28日 上午10:58:59
     */
//    public Success getAccountAttrs(ClientRequest request){
//    	String sn = request.getSn();
//    	String token = request.getToken();
//    	
//    	logger.info("token={}", token);
//    	SsoUser user = commonService.getSsoUserByToken(token);
//    	if(user!=null){
//    		//冻结账户无法操作
//    		if(accountRepository.isFreeze(user.getId())){
//    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
//    		}
//    		List<UserAccountAttrVo> list = accountRepository.getAccountAttrs(user.getId());
//            return new Success(sn, true, new KeyValueEntity("list", list));
//    	}else{
//    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
//    	}
//    }
    
    /**
     * <绑定银行卡/支付宝/微信账户信息><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年11月6日 下午7:14:11
     */
//    public Success bindAttr(ClientRequest request){
//    	String sn = request.getSn();
//    	String token = request.getToken();
//    	//类型支付宝、微信
//    	String type = AppRequestUtil.getParameter(request, "type");
//    	String attr = AppRequestUtil.getParameter(request, "attr");
//    	if(type==null){
//    		return new Success(sn, false, new Reason("exception", "type不能为空"));
//    	}else if(attr==null || "".equals(attr)){
//    		return new Success(sn, false, new Reason("exception", "支付宝账号或微信openId不能为空"));
//    	}
//    	
//    	logger.info("token={}, type={}, attr={}", token, type, attr);
//    	SsoUser user = commonService.getSsoUserByToken(token);
//    	if(user!=null){
//    		//冻结账户无法操作
//    		if(accountRepository.isFreeze(user.getId())){
//    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
//    		}
//    		String attrId = accountRepository.bindAttr(user.getId(), type, attr);
//    		return new Success(sn, true, new KeyValueEntity("attrId", attrId));
//    	}else{
//    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
//    	}
//    }
    
    /**
     * <账户明细><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年11月18日 下午3:53:07
     */
    @Auth(true)
    public Success accountList(ClientRequest request){
    	String sn = request.getSn();
    	String token = request.getToken();
    	
    	Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
    	Integer size = AppRequestUtil.getParameterInteger(request, "size");
    	logger.debug("token={}, begin={}, size={}", token, begin, size);
    	SsoUser user = commonService.getSsoUserByToken(token);
    	if(user!=null){
    		//冻结账户无法操作
    		if(accountRepository.isFreeze(user.getId())){
    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
    		}
    		List<UserAccountLogVo> list = accountRepository.accountLog(user.getId(), begin, size);
    		return new Success(sn, true, new KeyValueEntity("list", list));
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }
    
    /**
     * <提现记录><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年11月18日 下午2:23:24
     */
    /*public Success withdrawCashLog(ClientRequest request){
    	String sn = request.getSn();
    	String token = request.getToken();
    	Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
    	Integer size = AppRequestUtil.getParameterInteger(request, "size");
    	
    	logger.debug("token={}, begin={}, size={}", token, begin, size);
    	if(begin.equals("-1") || size.equals("-1")){
    		begin = size = null;
    	}
    	SsoUser user = commonService.getSsoUserByToken(token);
    	if(user!=null){
    		//冻结账户无法操作
    		if(accountRepository.isFreeze(user.getId())){
    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
    		}
    		List<UserAccountLogVo> list = accountRepository.withdrawCashLog(user.getId(), begin, size);
    		return new Success(sn, true, new KeyValueEntity("list", list));
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }*/
    
    /**
     * <账户明细详情><功能具体实现>
     * @param request
     * @return
     * @author yankefei
     * @date 2015年11月19日 下午6:53:21
     */
	@Auth
    public Success accountLogDetail(ClientRequest request){
    	String sn = request.getSn();
    	String token = request.getToken();
    	String logId = AppRequestUtil.getParameter(request, "logId");
    	
    	logger.debug("token={}, logId={}", token, logId);
    	SsoUser user = commonService.getSsoUserByToken(token);
    	if(user!=null){
    		//冻结账户无法操作
    		if(accountRepository.isFreeze(user.getId())){
    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
    		}
    		UserAccountLogDetailVo vo = accountRepository.accountLogDetail(logId);
    		return new Success(sn, true, new KeyValueEntity("data", vo));
    	}else{
    		return new Success(sn, false, new Reason("exception", "根据token未查询到用户信息"));
    	}
    }  
    
    /**
     * 
     * <设置支付密码><功能具体实现>
     *
     * @create：2015年11月20日 上午10:03:35
     * @author： CYY
     * @param request
     * @return
     */
     @Auth(true)
     public Success setPwd(ClientRequest request){
         String sn = request.getSn();
         String token = request.getToken();
         try {
             String password = AppRequestUtil.getParameter(request, "password"); 
             String repeatPwd = AppRequestUtil.getParameter(request, "repeatPwd");
             String checkToken = AppRequestUtil.getParameter(request, "checkToken");
             SsoUser user = commonService.getSsoUserByToken(token);
             logger.debug("password={},repeatPwd={}", password,repeatPwd);
             if(StringUtils.isEmpty(password)){
                 return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "支付密码不能为空"));
             }
             if(StringUtils.isEmpty(repeatPwd)){
                 return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "重复支付密码不能为空"));
             }
             if(!password.equals(repeatPwd)){
                 return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "支付密码和重复支付密码不一致"));
             }
//             if("weixin".equals(request.getChannel())){
//            	 if(StringUtils.isEmpty(checkToken)){
//            		 return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "非法操作"));
//            	 }else{
//            		 String checkToken_ = commonService.getStr("checkToken_"+token);
//            		 if(checkToken.equals(checkToken_)){
//
//            		 }else{
//            			 return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "操作已超时或非法操作"));
//            		 }
//            	 }
//             }
             Success success = accountRepository.setPwd(user,password);
             success.setSn(sn);
             return success;
         } catch (Exception e) {
             logger.error("setPwd_error:" + sn, e);
             return new Success(sn, false, new Reason(Constants.SYSTEM_KEY,Constants.SYSTEM_VALUE));
         }
     }
     
     /**
      *
      * <修改支付密码><功能具体实现>
      *
      * @create：2015年11月20日 上午10:03:35
      * @author： CYY
      * @param request
      * @return
      */
//      @Auth(true)
//      public Success modifyPwd(ClientRequest request){
//        String sn = request.getSn();
//        String token = request.getToken();
//        try {
//            String oldPwd = AppRequestUtil.getParameter(request, "oldPwd");
//            String password = AppRequestUtil.getParameter(request, "password");
//            String repeatPwd = AppRequestUtil.getParameter(request, "repeatPwd");
//            SsoUser user = commonService.getSsoUserByToken(token);
//            logger.debug("oldPwd={},password={},repeatPwd={}", oldPwd, password, repeatPwd);
//            if (StringUtils.isEmpty(oldPwd)) {
//                return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "旧支付密码不能为空"));
//            }
//            if (StringUtils.isEmpty(password)) {
//                return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "新支付密码不能为空"));
//            }
//            if (StringUtils.isEmpty(repeatPwd)) {
//                return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "重复新支付密码不能为空"));
//            }
//            if (!password.equals(repeatPwd)) {
//                return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "支付密码和重复支付密码不一致"));
//            }
//            Success success = accountRepository.modifyPwd(user, oldPwd, password);
//            success.setSn(sn);
//            return success;
//        } catch (Exception e) {
//            logger.error("setPwd_error:" + sn, e);
//            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
//        }
//    }
     

   /**
    * 
    * <验证支付密码><功能具体实现>
    *
    * @create：2015年11月20日 上午10:03:35
    * @author： CYY
    * @param request
    * @return
    */
    @Auth(true)
    public Success validatePayPwd(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            String password = AppRequestUtil.getParameter(request, "password");
            SsoUser user = commonService.getSsoUserByToken(token);
            logger.debug("password={}", password);
            if (StringUtils.isEmpty(password)) {
                return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "支付密码不能为空"));
            }
            Success success = accountRepository.validatePayPwd(user, password,token);
            success.setSn(sn);
            return success;
        } catch (Exception e) {
            logger.error("validatePayPwd_error:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }
    
    /**
     * 
     * <支付密码是否可用><功能具体实现>
     *
     * @create：2015年12月03日 上午10:03:35
     * @author： CYY
     * @param request
     * @return
     */
     @Auth(true)
     public Success isPayPwd(ClientRequest request) {
         String sn = request.getSn();
         String token = request.getToken();
         try {
             SsoUser user = commonService.getSsoUserByToken(token);
             Success success = accountRepository.isPayPwd(user.getId());
             success.setSn(sn);
             return success;
         } catch (Exception e) {
             logger.error("isPayPwd_error:" + sn, e);
             return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
         }
     }
}
