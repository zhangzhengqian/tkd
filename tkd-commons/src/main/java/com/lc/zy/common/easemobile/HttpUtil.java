package com.lc.zy.common.easemobile;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpUtil {

    public static boolean sendJson(String url, JSONArray params) throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/json");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            HttpEntity entity = response.getEntity();
            return true;
        } finally {
            httpost.releaseConnection();
        }
    }

    public static boolean sendJson1(String url, JSONArray params) throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/x-www-form-urlencoded");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            HttpEntity entity = response.getEntity();
            return true;
        } finally {
            httpost.releaseConnection();
        }
    }

    public static String sendQuery(String url, List<NameValuePair> params) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        UrlEncodedFormEntity form = new UrlEncodedFormEntity(params, Charset.forName("UTF8"));
        form.setContentType("application/x-www-form-urlencoded");
        httpost.setEntity(form);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("HTTP failed: " + response.getStatusLine());
            }
            HttpEntity entity = response.getEntity();
            String resultString = EntityUtils.toString(entity);
            JSONObject jsonResponse =new JSONObject(resultString);
            return jsonResponse.toString();
        } finally {
            httpost.releaseConnection();
        }
    }
    
    /**
     * <发送请求不返回请求对象>
     * @param url           请求路径
     * @param statusCode    请求参数
     * @return status       返回状态
     */
    public static String sendRequest(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        
        HttpGet httpGet = new HttpGet(url);
//        UrlEncodedFormEntity form = new UrlEncodedFormEntity(params, Charset.forName("UTF8"));
//        form.setContentType("application/x-www-form-urlencoded");
//        httpost.setEntity(form);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("HTTP failed: " + response.getStatusLine());
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            httpGet.releaseConnection();
        }
    }
    
    /**
     * <发送请求不返回请求对象>
     * @param url           请求路径
     * @param statusCode    请求参数
     * @return status       返回状态
     */
    public static String sendRequest(String url, List<NameValuePair> params) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        UrlEncodedFormEntity form = new UrlEncodedFormEntity(params, Charset.forName("UTF8"));
        form.setContentType("application/x-www-form-urlencoded");
        httpost.setEntity(new UrlEncodedFormEntity(params));
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("HTTP failed: " + response.getStatusLine());
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            httpost.releaseConnection();
        }
    }

    public static String sendJson(String url, Map<String, Object> header, JSONObject params, Map<String, Object> map)
            throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/json");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        Iterator<String> iter = header.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httpost.addHeader(key, value.toString());
        }
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put("reason", "HTTP failed: " + response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } finally {
            httpost.releaseConnection();
        }
    }
    
    public static String delete(
            String url,
            Map<String, Object> header,
            Map<String, Object> map) 
        throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        Iterator<String> iter = header.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httpDelete.addHeader(key, value.toString());
        }
        try {
            HttpResponse response = httpClient.execute(httpDelete);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put("reason", "HTTP failed: "+ response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity(); 
            return EntityUtils.toString(entity);
            

        } finally {
            httpDelete.releaseConnection();
        }
    }
}
