package com.lc.zy.ball.app.service.pay;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.service.order.OrderRepository;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.payUtils.PayParameter;
import com.lc.zy.common.util.wxap.util.MD5Util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信app支付
 */
@Service("tenpay")
public class TenpayService {
	
	private static Logger logger = LoggerFactory.getLogger(TenpayService.class);

	private static String NOTIFY_URL_NEW = "tenpay/notify_new";
	
	private static String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	private PayRepository payRepository = null;
	
	private CommonService commonService = null;
	
	private PayParameter payParameter = null;
	
	private OrderRepository orderRepository = null;
	
	@Resource(name="configs")
	private Map<String,String> configs;
	
	@Autowired
	public TenpayService(PayRepository payRepository,CommonService commonService, PayParameter payParameter, OrderRepository orderRepository){
		this.payRepository = payRepository;
		this.commonService = commonService;
		this.payParameter = payParameter;
		this.orderRepository = orderRepository;
	}
	
	@Auth
	public Success prePay(ClientRequest request) {
		PostMethod method = null;
		String sn = request.getSn();
        String token = request.getToken();
    	String orderId = AppRequestUtil.getParameter(request, "orderId");
    	SsoUser user = commonService.getSsoUserByToken(token);
    	Map<String, String> data = null;
    	try {
            // 验证支付
    		data = payRepository.validatePay(orderId, user.getId(),request.getChannel(),request.getVersion(), false);
    	} catch (Exception e) {
    		logger.error("tenPay_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("tenpay_prepay_exception", e.getMessage()));
        }
        BigDecimal decimal1 = new BigDecimal(data.get("finalFee"));
        BigDecimal decimal2 = new BigDecimal(100);
        decimal1 = decimal1.multiply(decimal2);
        Double finalFee = decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); // 四舍五入，保留两位小数
        String outTradeNo = data.get("tradeNo").toString();
        data = null;
        // 获取订单场馆id
        String orgCode = "master";
        Organization organization = payParameter.payKeys(orgCode);
        String appid = organization.getAppid();
        String mchId = organization.getMchId();
        String appKey = organization.getAppKey();
        String subject = AppRequestUtil.getParameter(request, "subject");
        SortedMap<String, String> signParams = new TreeMap<String, String>();
//        signParams.put("appid", WxapConfig.appid);
        signParams.put("appid", appid);
//        signParams.put("mch_id", WxapConfig.mch_id);
        signParams.put("mch_id", mchId);
        signParams.put("nonce_str", UUID.get());
        signParams.put("body", subject);
        String ip = request.getIp();
//        String ip = "192.168.1.18";
        logger.debug("ip new = {}", ip);
        signParams.put("spbill_create_ip", ip);
        signParams.put("out_trade_no", outTradeNo);
        signParams.put("trade_type", "APP");
        String payfee = finalFee.toString().split("\\.")[0]; // 因为金额乘100把小数的全部不要
        signParams.put("total_fee", payfee);
//        signParams.put("total_fee", "1");
        signParams.put("notify_url", configs.get("callbackServer")+NOTIFY_URL_NEW);

        StringBuffer sb = new StringBuffer();
        for (String key : signParams.keySet()) {
            sb.append(key).append("=").append(signParams.get(key)).append("&");
        }
//        sb.append("key=").append(WxapConfig.app_key);
        sb.append("key=").append(appKey);
        logger.debug("params : {}", sb);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8");
        signParams.put("sign", sign.toUpperCase());
        logger.debug("sign : {}", sign);

        String requestBody = CommonOAUtils.converteToXml(signParams);
        logger.debug("xml : {}", requestBody);

        Map<String, String> responseMap = null;
        try {
        	method = new PostMethod(PREPAY_URL);
        	method.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
        	RequestEntity req = new StringRequestEntity(requestBody, "text/xml", "UTF-8");
        	method.setRequestEntity(req);
        	HttpClient client = new HttpClient();
        	client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
			client.executeMethod(method);
			String responseData = method.getResponseBodyAsString();
			responseData = new String(responseData.getBytes("ISO-8859-1"), "UTF-8");
			logger.debug("responseData=" + responseData);
			responseMap = CommonOAUtils.converteToMap(responseData);
		} catch (Exception e) {
			logger.error("tenPay_exception:" + sn, e.getMessage());
			return new Success(sn, false, new Reason("tenpay_prepare_exception", "微信预支付失败"));
		}


        if ("SUCCESS".equals(responseMap.get("return_code"))) {
            if ("SUCCESS".equals(responseMap.get("result_code"))) {
                // 把客户端需要的参数MD5加密
                Long timestamp = System.currentTimeMillis() / 1000;
                String noncestr = signParams.get("nonce_str");
                StringBuilder build = new StringBuilder();
                build.append("appid");
                build.append('=');
//                build.append(WxapConfig.appid);
                build.append(appid);
                build.append('&');
                build.append("noncestr");
                build.append('=');
                build.append(noncestr);
                build.append('&');
                build.append("package");
                build.append('=');
                build.append("Sign=WXpay");
                build.append('&');
                build.append("partnerid");
                build.append('=');
//                build.append(WxapConfig.mch_id);
                build.append(mchId);
                build.append('&');
                build.append("prepayid");
                build.append('=');
                build.append(responseMap.get("prepay_id"));
                build.append('&');
                build.append("timestamp");
                build.append('=');
                build.append(timestamp);
                build.append('&');
                build.append("key=");
//                build.append(WxapConfig.app_key);
                build.append(appKey);
                logger.debug("client sign=" + build.toString());
                // 返回客户端加密串
                String returnSign = MD5Util.MD5Encode(build.toString(), "UTF-8");
                KeyValueEntity entity = new KeyValueEntity();
                entity.append("prepay_id", responseMap.get("prepay_id"));
                entity.append("sign", returnSign);
                entity.append("timestamp", timestamp);
                entity.append("noncestr", noncestr);
                return new Success(sn, true, entity);
            } else {
            	return new Success(sn, false, new Reason("tenpay_sign_exception", responseMap.get("err_code_des")));
            }
        } else {
            return new Success(sn, false, new Reason("tenpay_sign_exception", responseMap.get("return_msg")));
        }
	}
	
}
