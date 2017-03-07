package com.lc.zy.ball.boss.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController extends AbstractController{

	@RequestMapping(value = "")
	public String index(HttpServletRequest request,Model model)
			throws Exception {
		return "redirect:/statisticRegisterUser/registerList";
	}
}
