package com.lc.zy.ball.boss.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class IndexController extends AbstractController{

	@RequestMapping(value = "")
	public String index(HttpServletRequest request,Model model)
			throws Exception {
		return "redirect:/admin/userinfo";
	}
}
