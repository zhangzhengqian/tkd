package com.lc.zy.ball.boss.framework.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lc.zy.ball.boss.framework.system.service.EmsgService;
import com.lc.zy.ball.domain.oa.po.EmsgServer;

@Controller
@RequestMapping(value = "/admin/emsg")
public class EmsgController {
	
	@Autowired
	private EmsgService emsgService;
	
	@RequestMapping(value = "server/get", method = RequestMethod.GET)
	public String emsgServerForm(Model model) {
		try {
			EmsgServer emsgServer = emsgService.get();
			if(emsgServer==null){
				model.addAttribute("emsgServer", new EmsgServer());
			}else{
				model.addAttribute("emsgServer", emsgServer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/emsgServerForm";
	}
	
	@RequestMapping(value = "server/save", method = RequestMethod.POST)
	public String save(Model model, EmsgServer emsgServer) {
		try {
			emsgService.save(emsgServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/emsgServerForm";
	}
	
}
