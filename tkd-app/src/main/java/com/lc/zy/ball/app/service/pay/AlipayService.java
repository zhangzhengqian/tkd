package com.lc.zy.ball.app.service.pay;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.service.order.OrderRepository;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import com.lc.zy.common.util.alipay.config.AlipayConfig;
import com.lc.zy.common.util.alipay.util.SignUtils;
import com.lc.zy.common.util.payUtils.PayParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 支付宝支付
 */

@Service("alipay")
public class AlipayService {
	private static Logger logger = LoggerFactory.getLogger(AlipayService.class);

	
	private static String NOTIFY_URL_NEW = "alipay/notify_new";
	
	private PayRepository payRepository = null;
	
	private CommonService commonService = null;
	
	private PayParameter payParameter = null;
	

	private OrderRepository orderRepository = null;
	
	@Resource(name="configs")
	private Map<String,String> configs;
	
	@Autowired
	public AlipayService(PayRepository payRepository,CommonService commonService, PayParameter payParameter, OrderRepository orderRepository){
		this.payRepository = payRepository;
		this.commonService = commonService;
		this.payParameter = payParameter;
		this.orderRepository = orderRepository;
	}
	
	@Auth
	public Success sign(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
    	String orderId = AppRequestUtil.getParameter(request, "orderId");
    	SsoUser user = commonService.getSsoUserByToken(token);
    	Map<String, String> data = null;
    	try {
			// 验证支付
    		data = payRepository.validatePay(orderId, user.getId(),request.getChannel(),request.getVersion(), false);
    	} catch (Exception e) {
    		logger.error("aliPay_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("alipay_sign_exception", e.getMessage()));
        }
    	String subject = AppRequestUtil.getParameter(request, "subject");
    	String body = AppRequestUtil.getParameter(request, "body");
        String finalFee = data.get("finalFee");
//		String finalFee = "0.01";
        String outTradeNo = data.get("tradeNo");
		// 获取支付
		String orgCode = "master";
        Organization organization = payParameter.payKeys(orgCode);
        String partner = organization.getPartner();
        String SELLER = organization.getSellerId();
        String privateKey = organization.getPrivateKey();
        
        // 签约合作者身份ID
//        String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";
        String orderInfo = "partner=" + "\"" + partner + "\"";
        // 签约卖家支付宝账号
//        orderInfo += "&seller_id=" + "\"" + AlipayConfig.SELLER + "\"";
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\""; // 自己的订单号
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\""; // 场馆名称
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\""; // 场馆地址
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + finalFee + "\"";
        //服务器异步通知页面路径 orderInfo += "&notify_url=" + "\"" +
        //"http://notify.msp.hk/notify.htm" + "\"";
        orderInfo += "&notify_url=" + "\"" + configs.get("callbackServer")+NOTIFY_URL_NEW + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"" + AlipayConfig.input_charset + "\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&show_url=\"m.alipay.com\"";

        try {
//        	String sign = SignUtils.sign(orderInfo, AlipayConfig.private_key);
        	logger.debug("pay_sign : params={} ; privateKey={}",orderInfo,privateKey); 
        	String sign = SignUtils.sign(orderInfo, privateKey);
        	
        	sign = URLEncoder.encode(sign, "UTF-8"); // 签完名转换编码
        	KeyValueEntity entity = new KeyValueEntity();
            entity.append("sign", sign);
            entity.append("orderInfo", orderInfo);
            return new Success(sn, true, entity);
    	} catch (Exception e) {
    		logger.error("aliPay_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("alipay_sign_exception", "支付宝签名失败"));
        }

    }
	
}
