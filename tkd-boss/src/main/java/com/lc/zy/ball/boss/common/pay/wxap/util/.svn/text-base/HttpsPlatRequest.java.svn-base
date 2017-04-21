package com.lc.zy.ball.boss.common.pay.wxap.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lc.zy.common.util.SpringUtils;


/**
 * 
 *     
 * <微信公众平台支付><功能具体实现> 
 *  
 * @author：cyy    
 * @create：2015年7月7日 下午2:26:03        
 * @version     
 *
 */
public class HttpsPlatRequest {

    public interface ResultListener {

        public void onConnectionPoolTimeoutError();
    }

    // 表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    // 连接超时时间，默认10秒
    private int socketTimeout = 10000;

    // 传输超时时间，默认30秒
    private int connectTimeout = 30000;

    // 请求器的配置
    private RequestConfig requestConfig;

    // HTTP请求器
    private CloseableHttpClient httpClient;
    
    private Map<String, String> configs = (Map<String, String>)SpringUtils.getBean("configs");
    
    private static Logger logger = LoggerFactory.getLogger(HttpsPlatRequest.class);
    
    public HttpsPlatRequest(String wxCertPassword, String wxCertLocalPath) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, IOException {
    	logger.debug("wxCertPassword {}", wxCertPassword + "------------------------------");
    	logger.debug("Path {}", configs.get("certPath") + wxCertLocalPath + "------------------------------");
        init(wxCertPassword, wxCertLocalPath);
    }

    private void init(String wxCertPassword, String wxCertLocalPath) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        InputStream instream = HttpsPlatRequest.class.getClassLoader().getResourceAsStream(WxapConfig.wx_certLocalPath);
//        InputStream instream = HttpsPlatRequest.class.getClassLoader().getResourceAsStream(configs.get("certPath") + wxCertLocalPath);
        logger.debug("Path {}", configs.get("certPath") + wxCertLocalPath + "------------------------------");
        File f = new File(configs.get("certPath") + wxCertLocalPath);
        InputStream instream = new FileInputStream(f);
        try {
//            keyStore.load(instream, WxapConfig.wx_certPassword.toCharArray());// 设置证书密码
        	keyStore.load(instream, wxCertPassword.toCharArray());// 设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wxCertPassword.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();

        hasInit = true;
    }

    /**
     * 通过Https往API post xml数据
     *
     * @param url
     *            API地址
     * @param xmlObj
     *            要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */

    public String sendPost(String url, String xmlObj, String wxCertPassword, String wxCertLocalPath) throws IOException, KeyStoreException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyManagementException {

        if (!hasInit) {
            init(wxCertPassword, wxCertLocalPath);
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        // 解决XStream对出现双下划线的bug
        // XStream xStreamForRequestPostData = new XStream(new
        // DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        // 将要提交给API的数据对象转换成XML格式数据Post给API
        // String postDataXML = xStreamForRequestPostData.toXML(xmlObj);

        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        // 设置请求器的配置
        httpPost.setConfig(requestConfig);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout
     *            连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout
     *            传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig
     *            设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        requestConfig = requestConfig;
    }
}
