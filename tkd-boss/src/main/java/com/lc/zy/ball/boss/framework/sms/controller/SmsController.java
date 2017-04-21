package com.lc.zy.ball.boss.framework.sms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.sms.service.SmsService;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.Constants;
import com.lc.zy.common.util.MyGson;

@Controller
@RequestMapping(value = "/sms")
public class SmsController extends AbstractController {
	// log
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private SmsService smsService;

	@RequestMapping(value = { "/", "/list" })
	public String list(HttpServletRequest request, Model model) throws Exception {

		return "/sms/list";
	}

	@RequestMapping(value = "/send")
	public String send(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String content = request.getParameter("content");
		String linkmans = request.getParameter("linkmans");
		logger.info("短信群发内容：{}", content);
		logger.info("短信群发联系人：{}", linkmans);
		smsService.sendSms(content, linkmans);
		return "redirect:/sms/list";
	}

	@RequestMapping(value = "ssouser_query_dlg")
	public String ssouserQueryDlg(HttpServletRequest request, Model model) throws Exception {
		List<SsoUser> ssoUsers = null;
		try {
			ssoUsers = smsService.findSsoUserList(null, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssoUserList error:" + e.getMessage());
		}
		model.addAttribute("ssoUsers", ssoUsers);
		return "/sms/queryUser";
	}

	@RequestMapping(value="ajax_ssouser_query_dlg")
	@ResponseBody
	public String ajaxSsouserQueryDlg(HttpServletRequest request,Model model,String statium,String name) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<SsoUser> ssoUsers = smsService.findSsoUserList(statium,name);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, ssoUsers);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(Constants.Result.RESULT, false);
			logger.error("ssoUserList error:" + e.getMessage());
		}

		return MyGson.getInstance().toJson(result);
	}
}
