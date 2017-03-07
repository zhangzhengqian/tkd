package com.lc.zy.ball.boss.framework.orders.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	/**
	 * 发送json格式请求到指定地址
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String sendRequest(String url, String content, String contentType) {
		int timeout = 5000; // 超时时间
		String strResult = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpParams httpParams = httpClient.getParams();
			httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
			HttpPost httpPost = new HttpPost(url); // 创建HttpPost
			StringEntity se = new StringEntity(content, "UTF-8");
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
			se.setContentEncoding("UTF-8");
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost); // 执行POST请求

			// 若状态码为200 ok
			if (response.getStatusLine().getStatusCode() == 200) {
				// 取出回应字串
				strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			System.out.println("请求地址: " + httpPost.getURI());
			System.out.println("响应状态: " + response.getStatusLine());
			System.out.println("响应内容: " + strResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();// 关闭连接,释放资源
		}
		return strResult;
	}

}
