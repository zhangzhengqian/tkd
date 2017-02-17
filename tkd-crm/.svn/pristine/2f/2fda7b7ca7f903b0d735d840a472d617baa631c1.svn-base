package com.lc.zy.ball.crm.common.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lc.zy.common.util.DateUtils;

/**
 * 公共的 controller
 * @author liangc
 *
 */
public abstract class AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	public void parseDate(Map<String,Object> searchParams,String k) throws ParseException{
		String format = "yyyy-MM-dd HH:mm:ss";
		if(searchParams.get(k)!=null&&!"".equals(searchParams.get(k).toString())){
			String v = (String)searchParams.get(k);
			if(k.startsWith("GTE")){
				v = v+" 00:00:00";
			}else if(k.startsWith("LTE")){
				v = v+" 59:59:59";
			}else if(v.length()<18){
				format = "yyyy-MM-dd";
			}
			SimpleDateFormat df = new SimpleDateFormat(format);
			df.setTimeZone(TimeZone.getDefault());
			Date d = DateUtils.getDate(v,format);
			logger.debug("s="+v+" ; d="+d+" ; format="+format);
			searchParams.put(k, d);
		}
	}

}
