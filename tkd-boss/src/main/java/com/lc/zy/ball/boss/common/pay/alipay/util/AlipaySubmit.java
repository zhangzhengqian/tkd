package com.lc.zy.ball.boss.common.pay.alipay.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.lc.zy.ball.boss.common.pay.alipay.config.AlipayConfig;
import com.lc.zy.ball.boss.common.pay.alipay.sign.RSA;
import com.lc.zy.ball.boss.common.pay.alipay.util.httpClient.HttpProtocolHandler;
import com.lc.zy.ball.boss.common.pay.alipay.util.httpClient.HttpRequest;
import com.lc.zy.ball.boss.common.pay.alipay.util.httpClient.HttpResponse;
import com.lc.zy.ball.boss.common.pay.alipay.util.httpClient.HttpResultType;
import com.lc.zy.common.util.HttpUtil;


/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipaySubmit {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     * _input_charset=utf-8   必须加上这个参数不然会有乱码问题，导致签名失败
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?_input_charset=utf-8";

    /**
     * 生成签名结果
     * 
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara, String privateKey) {
        String prestr = AlipayCore.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if (AlipayConfig.sign_type.equals("RSA")) {
//            mysign = RSA.sign(prestr, AlipayConfig.private_key, AlipayConfig.input_charset);
        	mysign = RSA.sign(prestr, privateKey, AlipayConfig.input_charset);
        }
        return mysign;
    }

    /**
     * 生成要请求给支付宝的参数数组
     * 
     * @param sParaTemp
     *            请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
    	String privateKey = sParaTemp.get("private_key");
    	sParaTemp.remove("private_key");
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = buildRequestMysign(sPara, privateKey);
        
        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * 
     * @param sParaTemp
     *            请求参数数组
     * @param strMethod
     *            提交方式。两个值可选：post、get
     * @param strButtonName
     *            确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
//        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }

    /**
     * <建立post请求>
     * 
     * @param sParaTemp
     *            请求参数数组
     * @return
     */
    public static String buildPostRequest(Map<String, String> sParaTemp) {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        try {
            StringBuilder sb = new StringBuilder();
            for (String key : sPara.keySet()) {
                sb.append(key).append("=").append(sPara.get(key)).append("&");
            }
            String param = sb.toString();
            param = param.substring(0, param.length() - 1);
            System.out.println("param " + param);
            param = URLEncoder.encode(sb.toString(), "UTF-8");
            String url = ALIPAY_GATEWAY_NEW + param;
            return HttpUtil.sendRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <建立post请求>
     * 
     * @param sParaTemp
     *            请求参数数组
     * @return
     */
    public static String postRequest(Map<String, String> sParaTemp) {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        sParaTemp.remove("private_key");
        try {
            return HttpUtil.sendRequest(ALIPAY_GATEWAY_NEW, getNameValuePairList(sPara));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 建立请求，以表单HTML形式构造，带文件上传功能
     * 
     * @param sParaTemp
     *            请求参数数组
     * @param strMethod
     *            提交方式。两个值可选：post、get
     * @param strButtonName
     *            确认按钮显示文字
     * @param strParaFileName
     *            文件上传的参数名
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName,
            String strParaFileName) {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\""
                + ALIPAY_GATEWAY_NEW + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");

        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        return sbHtml.toString();
    }

    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值 如：buildRequest("",
     * "",sParaTemp)
     * 
     * @param strParaFileName
     *            文件类型的参数名
     * @param strFilePath
     *            文件路径
     * @param sParaTemp
     *            请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String strParaFileName, String strFilePath, Map<String, String> sParaTemp)
            throws Exception {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编码集
        request.setCharset(AlipayConfig.input_charset);

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW + "_input_charset=" + AlipayConfig.input_charset);

        HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
        if (response == null) {
            return null;
        }

        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * 
     * @param properties
     *            MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }

    /**
     * MAP类型数组转换成BasicNameValuePair类型
     * 
     * @param properties
     *            MAP类型数组
     * @return NameValuePair类型数组
     */
    private static List<org.apache.http.NameValuePair> getNameValuePairList(Map<String, String> properties) {
        List<org.apache.http.NameValuePair> nameValuePairList = new ArrayList<org.apache.http.NameValuePair>();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nameValuePairList;
    }

    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * 
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public static String query_timestamp() throws MalformedURLException, DocumentException, IOException {

        // 构造访问query_timestamp接口的URL串
//        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner
//                + "&_input_charset" + AlipayConfig.input_charset;
    	String strUrl = "";
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
}
