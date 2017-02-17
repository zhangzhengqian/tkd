/**
 * <从poperties文件获取value><功能具体实现>
 *
 * @create：2015年7月8日 上午11:19:51
 * @author： lsh
 */
package com.lc.zy.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProperUtil {
	private static Logger logger = LoggerFactory.getLogger(ProperUtil.class);

	public String getValue(String pro, String key) {
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("properties/"+pro+".properties");
		String value = "";
		try {
			prop.load(in);
			value = prop.getProperty(key).trim();
			logger.debug("从poperties文件获取value");
		} catch (IOException e) {
			logger.error("从poperties文件获取value异常：" + e.getMessage(), e);
		}
		return value;
	}
	
	/**
	 * 
	 * <获取property文件属性值><功能具体实现>
	 *
     * @create：2015年7月8日 上午11:19:51
	 * @author： lsh
	 * @param proName properties文件名字
	 * @param key  属性
	 * @return
	 */
   public static String getProper(String proName,String key){
	   if(!StringUtils.isEmpty(key)){
		   return new ProperUtil().getValue(proName,key);
	   }else{
		   return "";
	   }
   }
}