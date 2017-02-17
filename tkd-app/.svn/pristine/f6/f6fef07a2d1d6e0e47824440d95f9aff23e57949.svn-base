package com.lc.zy.ball.app.service.pay;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.account.AccountRepository;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("accountPay")
public class AccountPayService {
	
	@Autowired
	private PayRepository payRepository = null;
	@Autowired
	private CommonService commonService = null;
	@Autowired
	private AccountRepository accountRepository = null; 
	
	private static Logger logger = LoggerFactory.getLogger(AccountPayService.class);

	/**
	 * 账户支付
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
    		if(accountRepository.isFreeze(user.getId())){
    			logger.error("账户被冻结，请选择其他支付方式!");
                return new Success(sn, false, new Reason("account_pay_exception", "账户被冻结，请联系客服!"));
    		}
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
