package com.lc.zy.ball.boss.common.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lc.zy.ball.boss.common.SessionUtil;
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
				v = v+" 23:59:59";
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
	
	public void parseString(Map<String,Object> searchParams,String k) throws ParseException{
		if(searchParams.get(k)!=null&&!"".equals(searchParams.get(k).toString())){
			String v = (String)searchParams.get(k);
			if(k.startsWith("GTE")){
				v = v+" 00:00:00";
			}else if(k.startsWith("LTE")){
				v = v+" 23:59:59";
			}
			searchParams.put(k, v);
		}
	}
	//bhg 注册数统计专用 
	public void parseStringForReg(Map<String,Object> searchParams,String k) throws ParseException{
		if(searchParams.get(k)!=null&&!"".equals(searchParams.get(k).toString())){
			String v = (String)searchParams.get(k);
			if(k.startsWith("GTE")){
				v = v+" 00";
			}else if(k.startsWith("LTE")){
				v = v+" 23";
			}
			searchParams.put(k, v);
		}
	}
	
	/**
	 * 当 orgCode 为空时，设置成当前登陆人的 组织编码
	 * @param searchParams
	 */
	public void setDefaultOrgCode(Map<String,Object> searchParams){
		String k = "EQ_orgCode";
		if(searchParams.get(k)==null || "".equals(searchParams.get(k).toString())){
			String orgCode = SessionUtil.currentUser().getOrgCode();
			searchParams.put(k, orgCode);
		}
	}

}
