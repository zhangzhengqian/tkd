package com.lc.zy.ball.boss.framework.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping
	public String doLogout() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		
		return "redirect:/";
	}

}
