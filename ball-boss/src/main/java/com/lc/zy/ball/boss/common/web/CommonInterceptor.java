package com.lc.zy.ball.boss.common.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.boss.common.Constants.UserStatus;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.SpringContextHelper;
import com.lc.zy.ball.boss.framework.system.service.RoleService;
import com.lc.zy.ball.boss.framework.system.service.UserService;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	@Autowired
	private UserService userService = null;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String _pass = getPass(request); 
    	HttpSession session = request.getSession();
    	String uri = request.getRequestURI();
    	logger.debug("request_uri__"+uri);
    	if(uri.endsWith("logout"))
    		return true;
    	
    	User user = SessionUtil.currentUser();
    	String userStatus = null;
    	if(user!=null)
    		userStatus = user.getStatus();
    	logger.debug("current_user_status = "+userStatus);
    	
    	try{
    		//每次请求都刷新 session 中的 user 状态 >>>>> 
    		String userId = SessionUtil.currentUserId();
    		if(session.getAttribute("roleMap")==null){
    			Map<String,String> roleMap = new HashMap<String,String>();
    			RoleService roleService = SpringContextHelper.getBean(RoleService.class);
    			List<Role> roleList = roleService.findByUserId(userId);
    			for(Role r : roleList){
    				roleMap.put(r.getRoleCode(), r.getRoleName());
    				roleMap.put(r.getRoleId(), r.getRoleName());
    			}		
    			session.setAttribute("roleMap", roleMap);
    		}

    		//审核通过之后，就不需要再走这个逻辑了; 审核拒绝之后也不需要了
    		if(!UserStatus.ENABLE.equals(userStatus)&&!UserStatus.UNPASS.equals(userStatus)){
    	
    			User u = userService.getUser(userId);
    			SessionUtil.currentUser().setStatus(u.getStatus());
    			//每次请求都刷新 session 中的 user 状态 <<<<<
    			
    			if(_pass==null){
    				Map<String,String> rm = (Map)session.getAttribute("roleMap");
    				logger.debug("cccccccc__"+rm);
    				if(UserStatus.AUDIT.equals(userStatus)){
    					String ctx = request.getContextPath();
    					logger.debug("rrrrrrr__/register/pushForm?_pass=true");
    					response.sendRedirect(ctx+"/register/pushForm?_pass=true");
    					return false;
    				}
    				if(UserStatus.NEW.equals(userStatus)){
    					String ctx = request.getContextPath();
    					logger.debug("rrrrrrr__/register/push_audit?_pass=true");
    					response.sendRedirect(ctx+"/register/push_audit?_pass=true");
    					return false;
    				}
    			}
    		}
    	}catch(Exception e){}
    	return super.preHandle(request, response, handler);
    }
    private String getPass(HttpServletRequest request){
    	String _pass = request.getParameter("_pass");
    	if(StringUtils.isEmpty(_pass)){
    		_pass = (String)request.getAttribute("_pass");
    	}
    	logger.debug("_pass=="+_pass);
    	return _pass;
    }
    
}
