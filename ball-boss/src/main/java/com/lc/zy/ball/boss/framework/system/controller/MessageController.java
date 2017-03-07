package com.lc.zy.ball.boss.framework.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.system.service.MessageService;
import com.lc.zy.ball.domain.oa.po.Message;
import com.lc.zy.common.util.FreeMarkerUtils;
/**
 * 站内信
 * @author liangc
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(MessageController.class);

	
	@Autowired
	private MessageService messageService = null;

	@RequestMapping(value="index_dlg",method=RequestMethod.GET)
	public String index_dlg(Model model, ServletRequest request) throws Exception {
		logger.debug("message_index_dlg");	
		return "admin/message/index_dlg";
	}
	
	@RequestMapping(value="content")
	@ResponseBody
	public String content(String id, ServletRequest request) throws Exception {
		logger.debug(id);
		Message message = messageService.selectByPrimaryKey(Message.class,id);
		if("new".equals(message.getStatus())){
			message.setStatus("read");
			messageService.updateByPrimaryKey(message,id);
			logger.debug("[已读] message_id={},status={}",message.getId(),message.getStatus());
		}
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("message", message);
		String html = new FreeMarkerUtils("/template/message_dlg/content.ftl",root).getText();
		return html;
	}
	
	@RequestMapping(value="load_more")
	@ResponseBody
	public String loadMore(int pageNo,int type, ServletRequest request) throws Exception {
		Map<String,Object> root = new HashMap<String,Object>();
		List<Message> list = messageService.select(pageNo,type);
		root.put("messageList", list);
		String html = new FreeMarkerUtils("/template/message_dlg/items.ftl",root).getText();
		return html;
	}
	
	@RequestMapping(value = "/badge", method = RequestMethod.POST)
	@ResponseBody
	public String badge(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject success = new JSONObject();
		String uid = SessionUtil.currentUserId();
		success.put("success", true);
		try{
			JSONObject entity = new JSONObject();
			int badge = messageService.badge(uid);
			entity.put("badge", badge);
			if(badge>0){
				entity.put("message", messageService.getMessageForEntity(uid));
			}
			success.put("entity", entity);
		}catch(Exception e){
			logger.error("message_badge_error",e);
			success.put("success", false);
			success.put("entity", e.getMessage());
		}
		String res = success.toString();
		//logger.debug("user="+SessionUtil.currentUsername()+" ; badge="+res); 
		return success.toString();
	}
	
	/**
	 * 
	 * <站内消息><刷新消息数量>
	 *
	 * @create：2015年9月22日 下午2:55:06
	 * @author： liangsh
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="load_count")
	@ResponseBody
	public String loadCount() throws Exception {
		int count0 = messageService.msgCount(0);
		int count1 = messageService.msgCount(1);
		return count1+"_"+count0;
	}
	
	/**
	 * 
	 * <站内消息><将所有未读消息设为已读>
	 *
	 * @create：2015年9月22日 下午3:11:24
	 * @author： liangsh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="read_all")
	@ResponseBody
	public String readAll(ServletRequest request) throws Exception {
		List<Message> list = messageService.selectAll();
		for (Message message : list) {
			if("new".equals(message.getStatus())){
				message.setStatus("read");
				messageService.updateByPrimaryKey(message,message.getId());
				logger.debug("[已读] message_id={},status={}",message.getId(),message.getStatus());
			}
		}
		return "success";
	}

	@RequestMapping(value = "/badge")
	public void badgeForPost(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//重定向到首页去
		response.sendRedirect(request.getContextPath()+"/");
	}

}
