package com.lc.zy.ball.crm.framework.system.user.controller;

import com.lc.zy.ball.crm.common.web.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value={"", "/"})
	public String index(Model model, HttpServletRequest request) throws Exception {
		return "";
	}
}
