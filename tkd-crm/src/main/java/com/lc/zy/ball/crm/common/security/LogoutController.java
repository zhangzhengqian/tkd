package com.lc.zy.ball.crm.common.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping
	public String doLogout(HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		Cookie cookie_login = new Cookie("isLogin", null);
        cookie_login.setPath("/");
        cookie_login.setMaxAge(-1);
        Cookie cookie_websocketUrl = new Cookie("websocketUrl", null);
        cookie_websocketUrl.setPath("/");
        cookie_websocketUrl.setMaxAge(-1);
        Cookie cookie_statium = new Cookie("statiumId", null);
        cookie_statium.setPath("/");
        cookie_statium.setMaxAge(-1);
        response.addCookie(cookie_statium);
        response.addCookie(cookie_login);
        response.addCookie(cookie_websocketUrl);
		return "redirect:/login";
	}

}
