package com.lc.zy.ball.boss.framework.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.boss.common.UserContext;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.MessageBean;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.Token;

@Controller
@RequestMapping("/security")
public class SecurityController {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("checkPassword")
	@ResponseBody
	public boolean checkPassword(String oldPwd) {
		
		return isPasswordOK(oldPwd);
	}
	
	
	@Token
	@RequestMapping(value="changePassword", method=RequestMethod.GET)
	public String passwordForm() {
		return "security/changePassword";
	}
	
	
	
	/**
	 * 修改当前登录用户的密码.
	 * 
	 * @param oldPwd
	 * @param password
	 * @return JSON消息.
	 */
	@Token
	@RequestMapping(value="changePassword", method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(String oldPwd, String password) {
		Gson gson = MyGson.getInstance();
		if (!isPasswordOK(oldPwd)) {
			return gson.toJson(new MessageBean(0, "原始密码不正确"));
		}
		
		try {
			User user = userService.changePassword(UserContext.getCurrent().getUserId(), password);
			
			//更新缓存中的密码
			UserContext.getCurrent().setPassword(user.getPassword());
			UserContext.getCurrent().setSalt(user.getSalt());
			
			return gson.toJson(new MessageBean(1, "修改密码成功"));
			
		} catch (Exception e) {
			log.error("修改密码失败", e);
			
			return gson.toJson(new MessageBean());
		}
	}

	private boolean isPasswordOK(String oldPwd) {
		oldPwd = Encrypts.hashPassword(oldPwd, UserContext.getCurrent().getSalt());
		return UserContext.getCurrent().getPassword().equals(oldPwd);
	}

}
