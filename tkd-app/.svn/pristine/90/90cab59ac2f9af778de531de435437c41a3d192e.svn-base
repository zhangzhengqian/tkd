package com.lc.zy.ball.app.service.card;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.account.bean.UserAccountLogDetailVo;
import com.lc.zy.ball.app.service.card.bean.CrmCardTradeVo;
import com.lc.zy.ball.app.service.card.bean.CrmCardVo;
import com.lc.zy.ball.app.service.card.bean.CrmUserCardAccountVo;
import com.lc.zy.ball.app.service.pay.PayRepository;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.CrmUserCardLog;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserAccountLog;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.AppRequestUtil;


/**
 * 
 * create by zzq
 *
 * @create：2016年11月22日 下午8:16:09
 * @author：zzq
 */
@Service("card")
public class CardService {
	 private static Logger logger = LoggerFactory.getLogger(CardService.class);
	 
	 @Autowired
	 private CommonService commonService;
	 
	 @Autowired
	 private CardRepository cardRepository;
	 
	 @Autowired
	 private SsoUserMapper ssoUserMapper;
	 
	 @Autowired
	 private PayRepository payRepository;
	 
	 /**
	  * 
	  * <获取卡片列表><功能具体实现>
	  *
	  * @create：2016年11月23日 下午2:18:30
	  * @author：zzq
	  * @param request
	  * @return
	  */
	 @Auth
	 public Success getCardList(ClientRequest request){
		 String sn = request.getSn();
		 String token = request.getToken();
		 SsoUser ssoUser = commonService.getSsoUserByToken(token);
		 if(ssoUser!=null){
			 List<CrmCardVo> cards = cardRepository.getCardList(ssoUser);
			 logger.debug("返回卡片集合");
			 return new Success(sn,true,new KeyValueEntity("cards",cards));
		 }else{
			 return new Success(sn,false,new Reason("USER_NOTFOUND", "根据token无法查到有效的用户信息"));
		 }
		 
	 }
	 
	 /**
	  * 
	  * <会员卡余额，如果是新账户则要创建账户 状态为未激活><功能具体实现>
	  *
	  * @create：2016年11月23日 下午2:31:56
	  * @author：zzq
	  * @param request
	  * @return
	  */
	 @Auth
	 public Success getCardAccount(ClientRequest request){
		 String sn = request.getSn();
		 String token = request.getToken();
		 SsoUser ssoUser = commonService.getSsoUserByToken(token);
		 if(ssoUser!=null){
			 CrmUserCardAccountVo userAccount = cardRepository.getCrmUserAccount(ssoUser);
			 return new Success(sn,true,new KeyValueEntity("userAccount", userAccount));
			 
		 }else{
			 return new Success(sn,false,new Reason("USER_NOTFOUND", "根据token无法查到有效的用户信息"));
		 }
		 
	 }
	 
	 /**
	  * 
	  * <获取会员卡交易记录表><功能具体实现>
	  *
	  * @create：2016年11月25日 下午7:57:59
	  * @author：zzq
	  * @param request
	  * @return
	  */
	 @Auth(true)
	 public Success getTradeList(ClientRequest request){
		 String sn = request.getSn();
		 String token = request.getToken();		 
		 SsoUser ssoUser = commonService.getSsoUserByToken(token);
		 Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
		 Integer size = AppRequestUtil.getParameterInteger(request, "size");
		 if(ssoUser!=null){
			 List<CrmCardTradeVo> tradeList = cardRepository.getTradeList(ssoUser,begin,size);
			 return new Success(sn,true,new KeyValueEntity("tradeList", tradeList));
			 
		 }else{
			 return new Success(sn,false,new Reason("USER_NOTFOUND", "根据token无法查到有效的用户信息"));
		 }
	 }
	 
	 /**
	  * 
	  * <获取会员卡交易详情><功能具体实现>
	  *
	  * @create：2016年11月28日 上午10:28:36
	  * @author：zzq
	  * @param request
	  * @return
	  */
	 @Auth(true)
	 public Success getTradeDetail(ClientRequest request){
	    	String sn = request.getSn();
	    	String token = request.getToken();
	    	String logId = AppRequestUtil.getParameter(request, "logId");
	    	
	    	logger.debug("token={}, logId={}", token, logId);
	    	SsoUser user = commonService.getSsoUserByToken(token);
	    	if(user!=null){
	    		//冻结账户无法操作
	    		/*if(cardRepository.isFreeze(user.getId())){
	    			return new Success(sn, false, new Reason("exception", "账户已被冻结，请联系客服"));
	    		}*/
	    		CrmCardTradeVo vo = cardRepository.tradeLogDetail(logId);
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
	             //SsoUser ssoUser = ssoUserMapper.selectByPrimaryKey("f987c26abd1249c58ef000fdb52a59d3");
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
	             Success success = cardRepository.setPwd(user,password);
	             success.setSn(sn);
	             return success;
	         } catch (Exception e) {
	             logger.error("setPwd_error:" + sn, e);
	             return new Success(sn, false, new Reason(Constants.SYSTEM_KEY,Constants.SYSTEM_VALUE));
	         }
	     }
	     
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
	              //SsoUser ssoUser = ssoUserMapper.selectByPrimaryKey("50161b66f754439cb729abea50dc0d8c");
	              SsoUser user = commonService.getSsoUserByToken(token);
	              logger.debug("password={}", password);
	              if (StringUtils.isEmpty(password)) {
	                  return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, "支付密码不能为空"));
	              }
	              Success success = cardRepository.validatePayPwd(user, password,token);
	              success.setSn(sn);
	              return success;
	          } catch (Exception e) {
	              logger.error("validatePayPwd_error:" + sn, e);
	              return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
	          }
	      }
	      
	    /**
	     * 
	     * <会员卡支付><功能具体实现>
	     *
	     * @create：2016年11月28日 上午11:46:40
	     * @author：zzq
	     * @param request
	     * @return
	     * @throws Exception
	     */
	  	@Auth
	  	public Success pay(ClientRequest request) throws Exception {
	  		String sn = request.getSn();
	        String token = request.getToken();
	      	String orderId = AppRequestUtil.getParameter(request, "orderId");
	      	SsoUser user = commonService.getSsoUserByToken(token);
	          Boolean payType = AppRequestUtil.getParameterBoolean(request, "payType");
	          Map<String, String> data = null;
	      	try {
	      		/*if(cardRepository.isFreeze(user.getId())){
	      			logger.error("账户被冻结，请选择其他支付方式!");
	                  return new Success(sn, false, new Reason("account_pay_exception", "账户被冻结，请联系客服!"));
	      		}*/
	      		data = payRepository.validatePay(orderId, user.getId(),request.getChannel(),request.getVersion(), payType);
	      	} catch (Exception e) {
	              logger.error("订单校验失败：{}", e);
	              return new Success(sn, false, new Reason("account_pay_exception", e.getMessage()));
	          }
	      	String finalFee = data.get("finalFee");
	      	String tradeNo = data.get("tradeNo");
	      	data = null;
	      	try {
	      		Order order = payRepository.getOrderById(orderId);
	  			payRepository.payNotify(tradeNo, null, Constants.PayType.CARD, finalFee, order);
	  			return new Success(sn, true, new KeyValueEntity());
	      	} catch (Exception e) {
	  			logger.error("account_pay_exception:" + sn, e.getMessage());
	  			return new Success(sn, false, new Reason("account_pay_exception", e.getMessage()));
	  		}
	      }
}
