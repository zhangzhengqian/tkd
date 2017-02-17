package com.lc.zy.common.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.lc.zy.common.util.Globals;
import com.lc.zy.common.util.SpringUtils;

public abstract class WebUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

	public static int getInt(ServletRequest request, String name) {
		HttpServletRequest req = (HttpServletRequest)request;
		try {
			return Integer.parseInt(req.getParameter(name));
		} catch (NumberFormatException e) {
			log.debug("Parameter: {} = [{}], return defalut value 0.", 
					name, req.getParameter(name));
			
			return 0;
		}
	}
	
	public static String getString(ServletRequest request, String name) {
		HttpServletRequest req = (HttpServletRequest)request;
		return req.getParameter(name);
	}
	
	public static String[] getArray(ServletRequest request, String name) {
		HttpServletRequest req = (HttpServletRequest)request;
		return req.getParameterValues(name);
	}
	
	public static int getPage(ServletRequest request) {
		int page = getInt(request, "page") - 1;
		page = page < 0 ? 0 : page;
		return page;
	}
	
	public static int getPageSize(ServletRequest request) {
		int size = getInt(request, "size");
		size = size == 0 ? Globals.PAGE_SIZE : size;
		return size;
	}
	
	public static int getPageSize(ServletRequest request,int def) {
		int size = getInt(request, "size");
		size = size == 0 ? def : size;
		return size;
	}
	
	public static ServletContext getServletContext() {
		return ((WebApplicationContext)SpringUtils.context).getServletContext();
	}
}
