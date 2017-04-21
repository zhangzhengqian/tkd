package com.lc.zy.ball.crm.common.security;

import java.util.Arrays;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("rolesOrFilter")
public class RolesOrAuthorizationFilter extends AuthorizationFilter {

	private static final Logger log = LoggerFactory.getLogger(RolesOrAuthorizationFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

		
        if (rolesArray == null || rolesArray.length == 0) {
        	log.debug("请求 '{}', 无须特别角色，允许访问！",  ((HttpServletRequest)request).getServletPath(), Arrays.toString(rolesArray));
            return true;
        }

        log.debug("请求 '{}', 需要拥有 {} 角色之一.",  ((HttpServletRequest)request).getServletPath(), Arrays.toString(rolesArray));
        
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for (String role : roles) {
        	if (subject.hasRole(role)) {
        		log.debug("当前用户-'{}' 拥有角色 '{}', 允许访问！", subject.getPrincipal(), role);
        		return true;
        	}
        }
        
        log.debug("当前用户-'{}' 拥有角色 '{}', 禁止访问！", subject.getPrincipal(), Arrays.toString(rolesArray));
        
        return false;
	}

}
