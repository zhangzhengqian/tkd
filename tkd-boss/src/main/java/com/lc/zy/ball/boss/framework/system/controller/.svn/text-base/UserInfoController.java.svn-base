package com.lc.zy.ball.boss.framework.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.common.web.Token;
import com.lc.zy.common.web.Token.Type;

/**
 * 用户信息管理
 * 
 * @author wang.haibin
 *
 */
@Controller
@RequestMapping(value = {"/member/userinfo", "/admin/userinfo"})
public class UserInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户个人信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"", "/"})
	public String index(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("user", SessionUtil.currentUser());
		model.addAttribute("path", request.getServletPath());
		return "member/userinfo/index";
	}
	
	/**
	 * 更新个人信息表单
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Token
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(Model model) throws Exception {
		model.addAttribute("user", SessionUtil.currentUser());
		return "member/userinfo/form";
	}

	/**
	 * 更新个人信息
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@Token(Type.REMOVE)
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String create(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			userService.updateCurrentUserInfo(user);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "更新个人信息成功!"));
		} catch (Exception e) {
			logger.debug("更新个人信息失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "更新个人信息失败:" + e.getMessage()));
		}

		return "redirect:" + StringUtils.substringBefore(request.getServletPath(), "/update");
	}
	
	/**
	 * 修改密码表单
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Token
	@RequestMapping(value = "updatePwd")
	public String updatePwdForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("user", SessionUtil.currentUser());
		return "member/userinfo/pwdform";
	}

	/**
	 * 更新个人密码
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@Token(Type.REMOVE)
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(String old, String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			userService.changePassword(SessionUtil.currentUserId(), old, password);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "更新密码成功!"));
		} catch (Exception e) {
			logger.debug("更新密码失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "更新密码失败:" + e.getMessage()));
		}

		return "redirect:" + StringUtils.substringBefore(request.getServletPath(), "/update");
	}
	

}
