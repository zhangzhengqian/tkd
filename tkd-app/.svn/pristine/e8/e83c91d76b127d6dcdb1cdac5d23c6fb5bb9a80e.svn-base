package com.lc.zy.ball.app.controller;

import com.lc.zy.ball.app.common.AbstractController;
import com.lc.zy.ball.app.common.AlipayNotify;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.pay.PayService;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.security.AES;
import com.lc.zy.common.util.*;
import com.lc.zy.common.util.payUtils.PayParameter;
import com.lc.zy.common.util.wxap.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 转发服务 将一个 http request 请求转发到对应的 业务层方法，并把返回值，回写到 response 中
 *
 * @author liangc 参数文档：http://180.76.153.246:81/wiki/doku.php?id=wiki:app%E6%
 *         8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3
 */
@Controller
public class HubController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(HubController.class);

    @Autowired
    private PayService payService = null;

    @Autowired
    private PayParameter payParameter = null;

    /**
     * 系统版本号：对应 svn 版本
     */
    @Resource(name = "version")
    private String source_version = null;

    /**
     * 获取集合配置信息
     */
    @RequestMapping("/request")
    public void request(String body, String secret, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("[INPUT]:body=" + body);
        logger.debug("[INPUT]:secret=" + secret);
        String rtn = null;
        long start = System.currentTimeMillis();
        String service = null;
        String method = null;
        String sn = null;
        String token = null;
        String imei = null;
        String ip = null;
        String channel = null;
        String version = null;
        String ua = request.getHeader("User-Agent");
        try {
            if(checkUa(ua)){
                //aes＝true 表示 body 是用 aes 算法做的加密
                //使用 body 前要对其进行解密先
                if ("true".equalsIgnoreCase(secret)) {
                    try {
                        body = AES.decrypt(body);
                        logger.debug("[INPUT DECRYPT]:body=" + body);
                    } catch (Exception e) {
                        logger.error("解密失败", e);
                    }
                }
                ClientRequest clientRequest = AppRequestUtil.reviceClientRequest(body);
                token = clientRequest.getToken();
                String i = request.getParameter("imei");
                String c = request.getParameter("channel");
                String v = request.getParameter("version");
                if (StringUtils.isNotEmpty(i))
                    clientRequest.setImei(i);
                if (StringUtils.isNotEmpty(v))
                    clientRequest.setVersion(v);
                if (StringUtils.isNotEmpty(c))
                    clientRequest.setChannel(c);
                imei = clientRequest.getImei();
                channel = clientRequest.getChannel();
                version = clientRequest.getVersion();
                sn = clientRequest.getSn();
                service = clientRequest.getService();
                method = clientRequest.getMethod();
                ip = request.getHeader("X-Real-IP");
                clientRequest.setIp(ip);
                Object serviceObj = SpringUtils.getBean(service);
                String className = serviceObj.getClass().getName();
                Class<?> serviceClass = Class.forName(className);
                Method serviceMethod = serviceClass.getMethod(method, ClientRequest.class);
                // 根据 Auth 注解判断方法是否需要token，是否限制访问
                if (checkToken(serviceClass, serviceMethod, token)) {
                    Object success = serviceMethod.invoke(serviceObj, clientRequest);
                    rtn = success.toString();
                } else {
                    response.setHeader("errorCode", "ERROR_TOKEN");
                    rtn = new Success(sn, false, new Reason("ERROR_TOKEN", "登入会话已过期，请重新登入", source_version,getLocalIps())).toString();
                }
            }else{
                logger.error("fail_ua", ua);
                rtn = new Success(sn, true).toString();
            }
        } catch (BeansException e0) {
            logger.error("hub_get_service_exception", e0);
            writeLog("error", sn, service, method, e0.getMessage());
            rtn = new Success(sn, false, new Reason("NOT_FOUND_SERVICE", "找不到该服务", source_version,getLocalIps())).toString();
        } catch (Exception e) {
            logger.error("hub_error", e);
            writeLog("error", sn, service, method, e.getMessage());
            rtn = new Success(sn, false, new Reason("HUB_ERROR", "后台异常:" + e.getMessage(), source_version,getLocalIps())).toString();
        } finally {
            logger.debug("[OUTPUT]:rtn=" + rtn);
            // publishEvent(body,rtn);
            // request.setAttribute("rtn", rtn);//向拦截器传递输出值
            long end = System.currentTimeMillis();
            logger.info("[" + service + "." + method + "]-" + (end - start) + "ms.");
            // 如果输入参数进行了加密，则输出参数也要加密后再返回
            if ("true".equalsIgnoreCase(secret)) {
                // 返回一个 aes 加密后的输出参数，序列化成 base64
                AppRequestUtil.writeStringToResponse(AES.encrypt(rtn), response, "text/plain");
            } else {
                AppRequestUtil.writeStringToResponse(rtn, response);
            }
            // Cookie
            // Host
            // Accept
            // Accept-Language
            // User-Agent
            // Connection
            // Cache-Control
            // Accept-Encoding
            Map<String, Object> ualog = new HashMap<String, Object>();
            ualog.put("ip", ip);
            ualog.put("ua", ua);
            ualog.put("imei", imei);
            ualog.put("service", service);
            ualog.put("method", method);
            ualog.put("channel", channel);
            ualog.put("version", version);
            ualog.put("dt", DateUtil.nowDateString());
            ualog.put("rt", end - start);
            String log = MyGson.getInstance().toJson(ualog);
            bizLog.write("ualog", log);
        }
    }

    /**
     * <支付宝付款成功回调><功能具体实现>
     *
     * @param request
     * @param response
     * @throws Exception
     * @create：2015年9月22日 上午10:37:26
     * @author： buyanping
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/alipay/notify_new", method = RequestMethod.POST)
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String key : requestParams.keySet()) {
            String[] values = requestParams.get(key);
            params.put(key, StringUtils.join(values, ','));
        }
        logger.debug("alipay notify params: {}", params);
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        logger.debug("out_trade_no: {}", out_trade_no);
        // 支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        logger.debug("trade_no: {}", trade_no);
        // 交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        // 交易状态
        String price = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
        logger.debug("trade_status: {}", trade_status);

        if ("WAIT_BUYER_PAY".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
            AppRequestUtil.writeStringToResponse(("success"), response);// 请不要修改或删除
            return;
        }

        // 获取组织账户信息
        String orgCode = "";
        String aliPublicKey = "";
        String partner = "";
        Order order = null;
        if (StringUtils.isNotEmpty(out_trade_no)) {
            order = payService.orderByTradeNo(params.get("out_trade_no"), price, trade_no, Constants.PayType.ALI);
            if (order == null) {
                AppRequestUtil.writeStringToResponse(("success"), response);
                return;
            }
            // 获取支付帐号信息
            orgCode = "master";
            Organization organization = payParameter.payKeys(orgCode);
            aliPublicKey = organization.getAliPublicKey();
            partner = organization.getPartner();
            logger.debug("aliPublicKey: {}, partner: {}", aliPublicKey, partner);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        logger.debug("pay_callback: params={} ; aliPublicKey={}", params, aliPublicKey);
        // ////////////////////////////////////////////////////////////////////////////////////////
        if (AlipayNotify.verify(params, aliPublicKey, partner)) {// 验证成功
            // 请在这里加上商户的业务逻辑程序代码
            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 该种交易状态只在两种情况下出现
                // 1、开通了普通即时到账，买家付款成功后。
                // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
                try {
                    payService.payNotify(out_trade_no, trade_no, Constants.PayType.ALI, price, order);
                } catch (Exception e) {
                    logger.error("支付失败: {}", e);
                    AppRequestUtil.writeStringToResponse(("fail"), response); // 请不要修改或删除
                }
            }
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            logger.debug("支付成功");
            AppRequestUtil.writeStringToResponse(("success"), response);// 请不要修改或删除

        } else {// 验证失败
            logger.error("验证失败");
            AppRequestUtil.writeStringToResponse(("fail"), response);
        }
    }

    /**
     * <微信付款成功回调><功能具体实现>
     *
     * @param request
     * @param response
     * @throws Exception
     * @create：2015年9月22日 上午10:38:01
     * @author： buyanping
     */
    @RequestMapping(value = "/tenpay/notify_new", method = RequestMethod.POST)
    public void tenPayNotify(ServletRequest request, HttpServletResponse response) throws Exception {
        String body;
        Map<String, String> result = new HashMap<>();
        try {
            body = CommonOAUtils.getRequestBody(request);
            logger.info("tenpay notify params: {}", body);
            Map<String, String> params = CommonOAUtils.converteToMap(body);

            String sign = params.remove("sign");
            logger.debug("sign: {}", sign);
            StringBuffer sb = new StringBuffer();
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            // 获取组织账户信息
            String orgCode = "";
            Order order = null;
            if (StringUtils.isNotEmpty(params.get("out_trade_no"))) {
                order = payService.orderByTradeNo(params.get("out_trade_no"), params.get("total_fee"),
                        params.get("transaction_id"), Constants.PayType.WEIXIN);
                if (order == null) {
                    result.put("return_code", "SUCCESS");
                    result.put("return_msg", "OK");
                    AppRequestUtil.writeStringToResponse(CommonOAUtils.converteToXml(result), response);
                    return;
                }
                orgCode = "master";
                Organization organization = payParameter.payKeys(orgCode);
                String appKey = organization.getAppKey();
                sb.append("key=").append(appKey);
            }
            // sb.append("key=").append(WxapConfig.app_key);
            logger.debug("params : {}", sb);
            String sign1 = MD5Util.MD5Encode(sb.toString(), "UTF-8");
            logger.debug("verify sign: {}", sign1);
            if (sign.equalsIgnoreCase(sign1) && "SUCCESS".equals(params.get("result_code"))) { // 微信sign返回的加密串都转换成大写
                payService.payNotify(params.get("out_trade_no"), params.get("transaction_id"),
                        Constants.PayType.WEIXIN, params.get("total_fee"), order);
                logger.debug("支付成功：{}", params.get("out_trade_no"));
                result.put("return_code", "SUCCESS");
                result.put("return_msg", "OK");
            } else {
                result.put("return_code", "FAIL");
                result.put("return_msg", "签名失败");
            }
            result.put("return_code", "SUCCESS");
            result.put("return_msg", "OK");
        } catch (Exception e) {
            logger.error("支付失败: {}", e);
            result.put("return_code", "FAIL");
            result.put("return_msg", e.getMessage());
        }
        AppRequestUtil.writeStringToResponse(CommonOAUtils.converteToXml(result), response);
    }


}
