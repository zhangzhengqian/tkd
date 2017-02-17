package com.lc.zy.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lc.zy.common.bean.ClientRequest;


public class AppRequestUtil  {

	private static Logger logger = LoggerFactory.getLogger(AppRequestUtil.class);
	
	
	public static String getParameter(ClientRequest request, String key) {
		Object value = request.getParams().get(key);
		return value!=null&&!"".equals(value)?value.toString():null;
	}
	
	public static Object getParameterObject(ClientRequest request, String key) {
		Object value = request.getParams().get(key);
		return value!=null?value:null;
	}
	
	public static <T> T getParameterBean(ClientRequest request, String key,Class<T> t) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		HashMap  m = (HashMap)request.getParams().get(key);
		T _t = (T)toBean(t,m);
		return _t;
	}
	
	public static <T> List<T> getParameterList(ClientRequest request, String key,Class<T> t) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		ArrayList<?> list = (ArrayList<?>)request.getParams().get(key);
		ArrayList<T> tl = new ArrayList<T>();
		for(Object o :list){
			HashMap m = (HashMap)o;
			T _t = (T)toBean(t,m);
			tl.add(_t);
		}
		return tl;
	}
	
	public static final Object toBean(Class<?> type, Map<String, ? extends Object> map)   throws IntrospectionException, IllegalAccessException,  InstantiationException, InvocationTargetException {  
        BeanInfo beanInfo = Introspector.getBeanInfo(type);  
        Object obj = type.newInstance();  
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
        for (int i = 0; i< propertyDescriptors.length; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName();  
            if (map.containsKey(propertyName)) {  
                Object value = map.get(propertyName);  
                Object[] args = new Object[1];  
                args[0] = value;  
                descriptor.getWriteMethod().invoke(obj, args);  
            }  
        }  
        return obj;  
    }
	
	public static long getParameterLong(ClientRequest request, String key) {
		
		try {
			if(null==getParameter(request, key)||""==getParameter(request, key)){
				return Long.parseLong("-1");
			}
			return Long.parseLong(getParameter(request, key));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean getParameterBoolean(ClientRequest request, String key) {
		
		try {
			if(null==getParameter(request, key)||""==getParameter(request, key)){
				return false;
			}
			return Boolean.parseBoolean(getParameter(request, key));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Double getParameterDouble(ClientRequest request, String key) {
		
		try {
			if(null==getParameter(request, key)||""==getParameter(request, key)){
				return Double.parseDouble("-1");
			}
			return Double.parseDouble(getParameter(request, key));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0.0;
		}
	}
	
	public static Integer getParameterInteger(ClientRequest request, String key) {
		
		try {
			if(null==getParameter(request, key)||""==getParameter(request, key)){
				return Integer.parseInt("-1");
			}
			return Integer.parseInt(getParameter(request, key));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Date getParameterDate(ClientRequest request, String key)
			throws ParseException {
		request.getParams().get(key);
		Object value = request.getParams().get(key);
		if (null == value) {
			return null;
		} else {
			return new Date(getLong(value));
		}
	}
	
	public static Timestamp getParameterTimestamp(ClientRequest request, String key)
			throws ParseException {
		request.getParams().get(key);
		Object value = request.getParams().get(key);
		if (null == value) {
			return null;
		} else {
			return new Timestamp(getLong(value));
		}
	}
	
	private static Long getLong(Object obj){
        if (obj==null || false == NumberUtils.isNumber(obj+"")) return 0L;
        return Long.valueOf(obj+"");
	}
	
	public static void main(String[] args) {
		double latitude1 = 39.980782, longitude1 = 116.311073;
		double latitude2 = 39.990674, longitude2 = 116.318913;
		
		double r = getDistance(latitude1,longitude1,latitude2,longitude2);
		System.out.println(r);
	}
	
	public static double getDistance(double wd1, double jd1, double wd2,double jd2) {
		double x, y, out;
		double PI = 3.14159265;
		double R = 6.371229 * 1e6;

		x = (jd2 - jd1) * PI * R * Math.cos(((wd1 + wd2) / 2) * PI / 180) / 180;
		y = (wd2 - wd1) * PI * R / 180;
		out = Math.hypot(x, y);
		return out;
	}
	
	//add by liangc >>>>>>>>>>>>>>
	public static void writeStringToResponse(String str,HttpServletResponse response) throws Exception{
		writeStringToResponse(str,response,"text/json");
	}
	public static void writeStringToResponse(String str,HttpServletResponse response,String contentType) throws Exception{
		response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
		try {
			OutputStream os = response.getOutputStream();
			IOUtils.write(str, os);
		} catch (IOException e) {
			logger.error("写入 response 失败 : ",e);
			throw new Exception("写入 response 失败 : ",e);
		}
	}
	
	public static void writeStringToResponse(Object obj,HttpServletResponse response) throws Exception{
		writeStringToResponse(obj.toString(),response);
	}
	
	public static ClientRequest reviceClientRequest(String body) throws Exception{
		try{
			ClientRequest clientRequest = new ObjectMapper().reader(ClientRequest.class).readValue(body);
			return clientRequest;
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("body = "+body);
			logger.error("ClientRequest 参数反序列化异常",e);
			throw new Exception("ClientRequest 参数反序列化异常",e);
		}
	}
	
	//add by liangc <<<<<<<<<<<<<<
	
}
