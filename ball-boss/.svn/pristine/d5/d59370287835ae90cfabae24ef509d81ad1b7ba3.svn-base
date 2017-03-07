package com.lc.zy.ball.boss.common.security;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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

import com.lc.zy.ball.boss.common.Constants.ActionType;
import com.lc.zy.ball.boss.common.UserContext;
import com.lc.zy.ball.boss.framework.system.service.OpLoggingService;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.ball.domain.oa.po.OpLogging;
import com.lc.zy.ball.domain.oa.po.User;

@Component("captchaFormFilter")
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {  
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class); 
    
    @Autowired
    private OpLoggingService loggingService;
    
    @Autowired
    private UserService userService;
    
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
            userService.isDeleteUser(token.getUsername());
            
            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证 
            LOG.info(token.getUsername() + "登录成功");
            return onLoginSuccess(token, subject, request, response); 
            
        } catch (AuthenticationException e) {
            LOG.debug("用户：["+token.getUsername()+"]---------------登录失败---------------");
            return onLoginFailure(token, e, request, response);  
        }
    }
    
	public boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		saveLoginLog(request, loginName + " - 登录成功");
        updateUserLoginInfo(request);
		return super.onLoginSuccess(token, subject, request, response);
	}

	private void updateUserLoginInfo(ServletRequest request) {
		String userId = getCurrentUser().getUserId();
		String ip = request.getRemoteAddr();
		userService.updateUserLoginInfo(userId, ip);
	}

	private User getCurrentUser() {
		return UserContext.getCurrent();
	}

	private void saveLoginLog(ServletRequest request, String description) throws Exception {
		OpLogging opLog = OpLoggingService.newOpLogging(request);
		opLog.setActionType(ActionType.LOGIN);
		opLog.setDescription(description);
		loggingService.save(opLog);
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
    
    /**
     * 系统管理员登录成功后的跳转地址.
     */
//    @Value("#{application['security.sso.login.success.url.4.admin']}")
//    public static String adminUri = "/";
    
    @Resource(name="successUrl")
    private String successUrl = "/";

	@Override
	public String getSuccessUrl() {
		/*
		Subject subject = null;
		try {
			subject = SecurityUtils.getSubject();
		} catch (UnavailableSecurityManagerException e) {
			LOG.warn("Can't get subject.", e);
		}
		
		String url = null;
		
		if (subject != null) {
			
			if (UserContext.isSysUser()) {
				url = adminUri;
			}
			
			if (url != null) {
				subject.getSession().removeAttribute(WebUtils.SAVED_REQUEST_KEY);
				return url;
			}
		}
		*/
		
		String url = successUrl;
		System.out.println(url);
		System.out.println(url);
		System.out.println(url);
		System.out.println(url);
		System.out.println(url);
		return url;
			
	}
    
}
