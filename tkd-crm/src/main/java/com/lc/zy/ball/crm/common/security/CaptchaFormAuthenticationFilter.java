package com.lc.zy.ball.crm.common.security;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.zy.ball.crm.common.UserContext;
import com.lc.zy.ball.crm.framework.system.user.service.UserService;
import com.lc.zy.ball.domain.oa.po.CrmUser;

@Component("captchaFormFilter")
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {  
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class); 
    
    @Autowired
    private UserService userService;
    
    @Resource(name="configs")
	private Map<String,String> configs;
    
    public CaptchaFormAuthenticationFilter() {
    }
    
    /** 
     * 登录验证 
     */  
    @Override  
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {  
    	UsernamePasswordToken token = (UsernamePasswordToken)createToken(request, response);  
        try {
        	//图形验证码校验
            doCaptchaValidate(request);
            //新增判断删除标志位
            //userService.isDeleteUser(token.getUsername());
            
            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证 
            LOG.info(token.getUsername() + "登录成功");
            return onLoginSuccess(token, subject, request, response); 
            
        } catch (AuthenticationException e) {
            LOG.debug("用户：["+token.getUsername()+"]---------------登录失败---------------");
            return onLoginFailure(token, e, request, response);  
        }
    }
    
	public boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        updateUserLoginInfo(request);
        HttpServletResponse res = (HttpServletResponse)response;
        Cookie cookie_login = new Cookie("isLogin", "1");
        cookie_login.setPath("/");
        String wsUrl = configs.get("wsUrl");
        Cookie cookie_websocketUrl = new Cookie("websocketUrl", wsUrl);
        cookie_websocketUrl.setPath("/");
        CrmUser user = getCurrentUser();
        String statiumId = user.getStatiumId();
        Cookie cookie_statium = new Cookie("statiumId", "\""+statiumId+"\"");
        cookie_statium.setPath("/");
        res.addCookie(cookie_statium);
        res.addCookie(cookie_login);
        res.addCookie(cookie_websocketUrl);
        return super.onLoginSuccess(token, subject, request, response);
	}

	private void updateUserLoginInfo(ServletRequest request) {
		String userId = getCurrentUser().getUserId();
		String ip = request.getRemoteAddr();
		userService.updateUserLoginInfo(userId, ip);
	}

	private CrmUser getCurrentUser() {
		return UserContext.getCurrent();
	}

	
  
    // 验证码校验  
    protected void doCaptchaValidate(ServletRequest request) {
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	//session中的图形码字符串  
        String captcha = (String) httpRequest.getSession().getAttribute(  
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
        //比对  
/*        if (captcha != null && !captcha.equalsIgnoreCase(getCaptcha(request))) {  
            throw new IncorrectCaptchaException("验证码错误！");  
        }  */
    } 
  
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha"; 
  
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;  
  
    public String getCaptchaParam() {  
        return captchaParam;  
    }  
  
    public void setCaptchaParam(String captchaParam) {  
        this.captchaParam = captchaParam;  
    }  
  
    protected String getCaptcha(ServletRequest request) {  
        return WebUtils.getCleanParam(request, getCaptchaParam());  
    }  
      
    //保存异常对象到request  
    @Override  
    protected void setFailureAttribute(ServletRequest request,  
            AuthenticationException ae) {  
        request.setAttribute(getFailureKeyAttribute(), ae);  
    }

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse res = (HttpServletResponse)response;
        Cookie cookie_login = new Cookie("isLogin", null);
        cookie_login.setPath("/");
        cookie_login.setMaxAge(-1);
        Cookie cookie_websocketUrl = new Cookie("websocketUrl", null);
        cookie_websocketUrl.setPath("/");
        cookie_websocketUrl.setMaxAge(-1);
        Cookie cookie_statium = new Cookie("statiumId", null);
        cookie_statium.setPath("/");
        cookie_statium.setMaxAge(-1);
        res.addCookie(cookie_statium);
        res.addCookie(cookie_login);
        res.addCookie(cookie_websocketUrl);
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		// TODO Auto-generated method stub
		HttpServletResponse res = (HttpServletResponse)response;
        Cookie cookie_login = new Cookie("isLogin", null);
        cookie_login.setPath("/");
        cookie_login.setMaxAge(-1);
        Cookie cookie_websocketUrl = new Cookie("websocketUrl", null);
        cookie_websocketUrl.setPath("/");
        cookie_websocketUrl.setMaxAge(-1);
        Cookie cookie_statium = new Cookie("statiumId", null);
        cookie_statium.setPath("/");
        cookie_statium.setMaxAge(-1);
        res.addCookie(cookie_statium);
        res.addCookie(cookie_login);
        res.addCookie(cookie_websocketUrl);
		return super.onLoginFailure(token, e, request, response);
	}
    
}
