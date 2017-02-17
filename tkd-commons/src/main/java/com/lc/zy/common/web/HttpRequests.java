package com.lc.zy.common.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class HttpRequests {
	
	private static final Logger log = LoggerFactory.getLogger(HttpRequests.class);

	public static String doGet(String url, Map<String, String> params) throws IOException {
		
		log.debug("=====>> doGet url: {}", url);
		log.debug("=====>> params: {}", forLog(params));

		return getContent(new HttpGet(buildURI(url, params)));

	}
	
	private static URI buildURI(String url, Map<String, String> params) {
		URI uri = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					String key = entry.getKey();
					String[] values = entry.getValue().split(",");
					for(String value : values) {
						builder.addParameter(key, value.trim());
					}
				}
			}
			uri = builder.build();
		} catch (URISyntaxException e) {
			log.error("URI语法不正确", e);
		}
		
		log.debug("=====>> doGet uri: {}", uri);
		
		return uri;
	}

	
	/**
	 * 发起HTTP请求。
	 * 参数格式说明：
	 * <ul>
	 * 	<li>key为参数名，value为参数值；
	 * 	<li>多个同名请求参数的值以逗号分隔，如：domain=abc.com,xyz.com
	 * </ul>
	 * 
	 * @param url 请求地址.
	 * @param params 请求参数.
	 * @return 响应的文本内容.
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> params) throws IOException {
		List<NameValuePair> paramList =  null;
		
		if (params != null) {
			paramList = getParamList(params);
		}

		return doPost(url, paramList);
	}
	
	
	/**
	 * @see {@link com.lc.zy.common.web.HttpRequests#doPost(String, Map)}
	 */
	public static String doPost(String url, List<NameValuePair> params) throws IOException {
		log.debug("=====>> doPost url: {}", url);
		log.debug("=====>> params: {}", forLog(params));
		
		HttpPost httppost = new HttpPost(url);
		
		if (params != null) {
			httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF8")));  
		}
		
		return getContent(httppost);
	}


	private static List<NameValuePair> getParamList(Map<String, String> params) {
		List<NameValuePair> paramList = new ArrayList <NameValuePair>();  
		for(Entry<String, String> entry: params.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue().split(",");
			for(String value : values) {
				paramList.add(new BasicNameValuePair(key, value.trim()));
			}
		}
		return paramList;
	}


	private static String getContent(HttpUriRequest request) throws IOException {
		
		HttpClient httpclient = new DefaultHttpClient();
		setConnectionParams(httpclient);
		
		HttpResponse response = httpclient.execute(request);
		
		log.debug("=====>> response status: {}", response.getStatusLine());

		HttpEntity entity = response.getEntity();
		
		String content = null;

		if (entity != null) {
			try {
				
				content = EntityUtils.toString(entity);
				
			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				log.error("=====>> HTTP请求失败！", ex);
				throw ex;
			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				request.abort();
				log.error("=====>> HTTP请求失败！", ex);
				throw ex;
			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		
		if (content != null) {
			log.debug("=====>> response content: {}", content.substring(0, Math.min(100, content.length())));
		}
		
		return content;
	}

	public static final int CONNECTION_TIMEOUT = 5000;
	
	public static final int SO_TIMEOUT = 30000;
	
	private static void setConnectionParams(HttpClient httpclient) {
		HttpParams params = httpclient.getParams();
		
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
	}
	
	
	private static final String REGEX = "password|passwd";
	
	/**
	 * 生成一个用于输出日志目的的map，隐藏其中的隐私信息.
	 * @param src 源map
	 * @return 不包含隐私信息的map.
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> forLog(Map<String, String> src) {
		if (src == null) {
			return Collections.EMPTY_MAP;
		}
		
		Map<String, String> copy = new HashMap<>(src);
		Set<String> keys = copy.keySet();
		
		Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		for(String key : keys) {
			Matcher m = p.matcher(key);
			if (m.find()) {
				copy.put(key, "******");
			}
		}
		return copy;
	}
	


	/**
	 * 生成一个用于输出日志目的的参数列表，隐藏其中的隐私信息.
	 * @param params 源参数列表
	 * @return 不包含隐私信息的参数列表.
	 */
	private static List<NameValuePair> forLog(List<NameValuePair> params) {
		Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		List<NameValuePair> copy = new ArrayList<>(params);
		List<NameValuePair> replaces = new ArrayList<>();
		for(Iterator<NameValuePair> it = copy.iterator(); it.hasNext();) {
			NameValuePair pair = it.next();
			Matcher m = p.matcher(pair.getName());
			if (m.find()) {
				it.remove();
				replaces.add(new BasicNameValuePair(pair.getName(), "******"));
			}
		}
		
		copy.addAll(replaces);
		
		return copy;
	}

	
	/** 
	 * 用于文件上传.
	 * @param url
	 * @param entity 包含上传文件的MultipartEntity实例
	 * @return JSON 响应字符串.
	 * @throws IOException
	 */
	public static String doPost(String url, MultipartEntity entity) throws IOException {
		log.debug("=====>> doPost url: {}", url);
		log.debug("=====>> entity : {}", entity);
		
		HttpPost httppost = new HttpPost(url);
		
		if (entity != null) {
			httppost.setEntity(entity);  
		}
		
		return getContent(httppost);
	}
	
}
