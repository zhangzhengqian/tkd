package com.lc.zy.ball.crm.common.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("uriPermsFilter")
public class UriPermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {
	
	private final static Logger log = LoggerFactory.getLogger(UriPermissionsAuthorizationFilter.class);
	
	@Autowired
	private FunctionProvider functionProvider;
	
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		
		String perm = neededPermission(request);
		
		log.debug("请求 '{}', 需要权限: {}",  getURI(request), perm);
		
		if (StringUtils.isNotBlank(perm)) {
			return SecurityUtils.getSubject().isPermitted(perm);
		}
		return true;
	}
	
	private Object getURI(ServletRequest request) {
		return ((HttpServletRequest)request).getServletPath();
	}

	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	private String neededPermission(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String servletPath = req.getServletPath();
		
		List<String> actions = functionProvider.getActions();
		Map<String, String> actionPermissionMap = functionProvider.getActionPermissionMap();
		
		//路径与功能定义中的action完全匹配，则直接返回.
		if (actions.contains(servletPath)) {
			return actionPermissionMap.get(servletPath);
		}
		
		String perm = null;
		
		for (String action : functionProvider.getActions()) {
			if (pathMatcher.match(action, servletPath))
				perm = actionPermissionMap.get(action);
		}
	
		return perm;
	}
	
}
