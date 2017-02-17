package com.lc.zy.ball.app.service.pay;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.service.order.OrderRepository;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.AppRequestUtil;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.payUtils.PayParameter;
import com.lc.zy.common.util.wxap.util.MD5Util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service("wx")
public class WxService {
	
	private static Logger logger = LoggerFactory.getLogger(WxService.class);

	private static String NOTIFY_URL_NEW = "wxpay/notify_new";
	
	private static String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	// 获取token信息url
    private static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx22d352797b1b6b96&secret=7382b7171871bbd871990f586be51300";
	
    // 获取ticket信息url
    private static String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
    
    private PayRepository payRepository = null;
	
	private CommonService commonService = null;
	
	private PayParameter payParameter = null;
	
	private OrderRepository orderRepository = null;
	
	@Autowired
	private RedisPool redisPool = null;
	
	@Resource(name="configs")
	private Map<String,String> configs;
	
	@Autowired
	public WxService(PayRepository payRepository,CommonService commonService, PayParameter payParameter, OrderRepository orderRepository){
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
    	String code = AppRequestUtil.getParameter(request, "code");
    	SsoUser user = commonService.getSsoUserByToken(token);
    	Map<String, String> data = null;
    	try {
    		data = payRepository.validatePay(orderId,user.getId(),request.getChannel(),request.getVersion(), false);
    	} catch (Exception e) {
    		logger.error("tenPay_exception:" + sn, e.getMessage());
            return new Success(sn, false, new Reason("wxpay_prepay_exception", e.getMessage()));
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
        String appid = organization.getWxAppid();
        String mchId = organization.getWxMchId();
        String appKey = organization.getWxAppKey();
        String subject = AppRequestUtil.getParameter(request, "subject");
        SortedMap<String, String> signParams = new TreeMap<String, String>();
//        signParams.put("appid", WxapConfig.appid);
        signParams.put("appid", appid);
//        signParams.put("mch_id", WxapConfig.mch_id);
        signParams.put("mch_id", mchId);
        signParams.put("nonce_str", UUID.get());
        signParams.put("body", subject);
        String ip = request.getIp();
        //String ip = "192.168.1.18";
        logger.debug("ip new = {}", ip);
        signParams.put("spbill_create_ip", ip);
        signParams.put("out_trade_no", outTradeNo);
        signParams.put("trade_type", "JSAPI");
        signParams.put("device_info", "WEB");
        
        logger.debug("token {} ", token);
        logger.debug("userInfo {} ", user.toString());
        String openId = redisPool.getStr(token+"_"+organization.getId());
        if(StringUtils.isEmpty(openId)){
        	String url = configs.get("common.weixin_oauth2")
        			+ "?appid="+appid+"&secret="+organization.getWxAppSecret()+"&code=" + code
        			+ "&grant_type=authorization_code";
        	logger.debug("CreateWixinSignController url {}", url);
        	Type type = new TypeToken<Map<String, String>>() {
        	}.getType();
        	String result = sendUrl(url);
        	logger.debug("CreateWixinSignController result {}", result);
        	Map<String, String> oauthMap = MyGson.getInstance().fromJson(result, type);
        	openId = oauthMap.get("openid");
        	redisPool.setStr(token+"_"+organization.getId(), openId);
        }
        signParams.put("openid", openId);
        String payfee = finalFee.toString().split("\\.")[0]; // 因为金额乘100把小数的全部不要
        signParams.put("total_fee", payfee);
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
			return new Success(sn, false, new Reason("tenpay_prepare_exception", "微信公众平台预支付失败"));
		}
        if ("SUCCESS".equals(responseMap.get("return_code"))) {
            if ("SUCCESS".equals(responseMap.get("result_code"))) {
                // 把客户端需要的参数MD5加密
            	Map<String, Object> params = new TreeMap<String, Object>();
                String prepayId = responseMap.get("prepay_id");
                // 把客户端需要的参数MD5加密
                Long timestamp = System.currentTimeMillis() / 1000;
                String noncestr = UUID.get(); //
                params.put("appId", appid);
                params.put("timeStamp", timestamp);
                params.put("nonceStr", noncestr);
                params.put("package", "prepay_id=" + prepayId);
                params.put("signType", "MD5");

                StringBuffer build = new StringBuffer();
                for (String key : params.keySet()) {
                    build.append(key).append("=").append(params.get(key)).append("&");
                }
                build.append("key=").append(appKey);
                logger.debug("client sign=" + build.toString());
                // 返回客户端加密串
                String returnSign = MD5Util.MD5Encode(build.toString(), "UTF-8");
                KeyValueEntity entity = new KeyValueEntity();
                entity.append("prepay_id", prepayId);
                entity.append("sign", returnSign);
                entity.append("timestamp", timestamp);
                entity.append("noncestr", noncestr);
                return new Success(sn, true, entity);
            } else {
            	return new Success(sn, false, new Reason("wxpay_sign_exception", responseMap.get("err_code_des")));
            }
        } else {
            return new Success(sn, false, new Reason("wxpay_sign_exception", responseMap.get("return_msg")));
        }
	}
	
	/*private String getAccessToken() throws Exception{
		String appKey = "5f5b0dde9d92450698ca696c9eb01eb2";
		String url = "https://180.76.186.123/get_access_token/";
		
		HttpClient client = new HttpClient();
		Protocol wxHttps = new Protocol("https", new WxSSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", wxHttps);
		PostMethod post = new PostMethod(url);
		post.addParameter("appkey", appKey);
		client.executeMethod(post);
		String rtn = post.getResponseBodyAsString();
		Type type = new TypeToken<Success>() {
    	}.getType();
    	Success success = MyGson.getInstance().fromJson(rtn, type);
    	return (((Map<String, String>) success.getEntity())).get("access_token");
	}*/
	
	
	
	/*public Success getSign(ClientRequest request) throws Exception {
		String sn = request.getSn();
		try {
			String url = AppRequestUtil.getParameter(request, "url");
			String orderId = AppRequestUtil.getParameter(request, "orderId");
			url = url.replaceAll("[|][|]", "&");
			logger.debug("注册页面，{}",url);
	        // 获取缓存中的jsapi_ticket
	        String jsapi_ticket = "";
    		// 获取token
            String access_token = getAccessToken();
            logger.debug("access_token,{}",access_token);
            // 获取ticket
            String ticket = sendUrl(ticketUrl + access_token + "&type=jsapi");
            Type ticketKey = new TypeToken<Map<String,Object>>() {}.getType();
            Map<String,Object> ticketObj = MyGson.getInstance().fromJson(ticket,ticketKey);
            jsapi_ticket = ticketObj.get("ticket").toString();
            
	        // noncestr
	        String nonce_str = UUID.get();
	        // timestamp
	        Long timestamp = System.currentTimeMillis() / 1000;
	        String time = timestamp.toString();
	        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + time + "&url=" + url;
	        KeyValueEntity entity = new KeyValueEntity();
	        // appId
	        // 获取订单场馆id
	        String orgCode = "master";
			Organization organization = payParameter.payKeys(orgCode);
			String appid = organization.getWxAppid();
			entity.append("appId", appid);
	        // timestamp
	        entity.append("timestamp", timestamp.toString());
	        // nonceStr
	        entity.append("nonce_str", nonce_str);
	        String shaSgin = Sha1Util.getSha1(sign);
	        // signature
	        entity.append("signature", shaSgin);
	        // jsApiList(分享微信、微博、qq、获取地理位置接口)
	        String jsApiList = "['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','openLocation','getLocation']";
	        entity.append("jsApiList", jsApiList);
	        return new Success(sn, true, entity);
		} catch (Exception e) {
			return new Success(sn, false, new Reason("wxpay_sign_exception", "微信公众平台支付签名失败"));
		}
		
    }*/
	
	public Success getOpenId(ClientRequest request) {
		String code = AppRequestUtil.getParameter(request, "code");
		String sn = request.getSn();
        try {
            String url = configs.get("common.weixin_oauth2")
                    + "?appid=wx22d352797b1b6b96&secret=7382b7171871bbd871990f586be51300&code=" + code
                    + "&grant_type=authorization_code";
            logger.debug("CreateWixinSignController url {}", url);
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            String result = sendUrl(url);
            logger.debug("CreateWixinSignController result {}", result);
            Map<String, String> oauthMap = MyGson.getInstance().fromJson(result, type);
            logger.debug(oauthMap.toString());
            String openId = oauthMap.get("openid");
            KeyValueEntity entity = new KeyValueEntity();
            entity.append("openId", openId);
            String accessToken = oauthMap.get("access_token");
            entity.append("accessToken", accessToken);
            return new Success(sn, true, entity);
        } catch (Exception e) {
        	return new Success(sn, false, new Reason("wx_getOpenId_exception", "微信公众平台获取openID失败"));
        }
    }
	
	public Success getUserinfo(ClientRequest request) {
		String sn = request.getSn();
		try {
			String code = AppRequestUtil.getParameter(request, "code");
			String rtn = redisPool.getStr("wx_code_"+code);
			if(StringUtils.isEmpty(rtn)){
				String url = configs.get("common.weixin_oauth2")
	                    + "?appid=wx22d352797b1b6b96&secret=7382b7171871bbd871990f586be51300&code=" + code
	                    + "&grant_type=authorization_code";
	            logger.debug("CreateWixinSignController url {}", url);
	            Type type = new TypeToken<Map<String, String>>() {
	            }.getType();
	            String result = sendUrl(url);
	            logger.debug("CreateWixinSignController result {}", result);
	            Map<String, String> oauthMap = MyGson.getInstance().fromJson(result, type);
	            logger.debug(oauthMap.toString());
	            String openId = oauthMap.get("openid");
	            String accessToken = oauthMap.get("access_token");
	            url = "https://api.weixin.qq.com/sns/userinfo"
	                    + "?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
	            logger.debug("CreateWixinSignController url {}", url);
	            result = sendUrl(url);
	            redisPool.setStr("wx_code_"+code, result);
	            logger.debug("CreateWixinSignController result {}", result);
	            KeyValueEntity entity = new KeyValueEntity();
	            entity.append("userInfo", result);
	            return new Success(sn, true, entity);
			}else{
				KeyValueEntity entity = new KeyValueEntity();
	            entity.append("userInfo", rtn);
	            return new Success(sn, true, entity);
			}
            
        } catch (Exception e) {
        	logger.debug(e.getMessage(),e);
        	return new Success(sn, false, new Reason("wx_getOpenId_exception", "微信公众平台获取userInfo失败"));
        }
	}
	
	private String sendUrl(String url) {
        String res = null;
        GetMethod method = new GetMethod(url);
        method.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        try {
            client.executeMethod(method);
            res = method.getResponseBodyAsString();
            if (method.getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("HTTP failed: " + method.getStatusLine());
            }
            res = new String(res.getBytes("ISO-8859-1"), "UTF-8");
            logger.debug("res=" + res);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }
	
}
