package com.lc.zy.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertyUtils extends PropertyPlaceholderConfigurer {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
	private static Properties props;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String f = "/Users/liangc/dev/qiuyouquan/oa/src/main/resources/properties/application_errorcode.properties";
		Properties p = new Properties();
		p.load(new FileInputStream(f));
		p.elements();
	}
	public PropertyUtils() {
		props = new Properties();
	}

	@Override
	public void setLocation(Resource location) {
		super.setLocation(location);
		fillProperties(location);
	}

	private void fillProperties(Resource location) {
		try {
			PropertiesLoaderUtils.fillProperties(props, location);
		} catch (IOException e) {
			logger.debug("Resource not found: " + location, e);
		}
	}

	@Override
	public void setLocations(Resource... locations) {
		super.setLocations(locations);
		for (Resource location : locations) {
			fillProperties(location);
		}
	}

	/**
	 * @see java.util.Properties#getProperty(String)
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * @see java.util.Properties#getProperty(String, String)
	 */
	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	/**
	 * 在 spring 的 configs 中获取属性，configs 是 HashMap 类型
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		return getConfig(key,null);
	}
	public static String getConfig(String key,String def) {
		try{
			Map<String, String> config = (HashMap<String, String>) SpringUtils.getBean("configs");
			logger.debug("configs ==> "+config.toString());
			String v = config.get(key);
			if(StringUtils.isNotEmpty(v)){
				return v;
			}
		}catch(Exception e){
			e.printStackTrace();
			return def;
		}
		return def;
	}

}
