package com.lc.zy.ball.boss.framework.info.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.info.service.InfoService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.ZoneInfo;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/info")
public class InfoController {

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    /**
     * 资讯列表
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "list")
    public String list(Model model, HttpServletRequest request) throws Exception {
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<ZoneInfo> onePage = infoService.list(new PageRequest(page, size));
        model.addAttribute("data", onePage);
        return "info/list";
    }
    
    /**
     * 新增资讯
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add")
    public String add(Model model, HttpServletRequest request) throws Exception {
        return "info/add";
    }
    
    /**
     * 保存资讯
     * @param model
     * @param request
     * @param info
     * @param oper
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save")
    public String save(Model model, HttpServletRequest request,ZoneInfo info,Integer oper,String sendTime_) throws Exception {
    	//立即发送
    	boolean newFlag = false;
    	if(StringUtils.isBlank(info.getId())){
    		info.setId(UUID.get());
    		newFlag = true;
    	}
    	if(StringUtils.isNotBlank(sendTime_)){
    		info.setSendTime(DateUtil.parse(sendTime_, "yyyy-MM-dd HH:mm:ss", null));
    	}
    	if(oper!=10){
    		info.setSendType(oper);
    	}
    	if(oper==1){
    		info.setSendTime(new Date());
    		info.setCb(SessionUtil.currentUserId());
    		info.setCt(new Date());
    	}else if(oper==0){
    		info.setCb(SessionUtil.currentUserId());
    		info.setCt(new Date());
    	}else if(oper==3){
    		info.setTimerFlag(1);
    		info.setCb(SessionUtil.currentUserId());
    		info.setCt(new Date());
    	}
    	if(newFlag){
    		info.setStick(0);
    		info.setTimerFlag(0);
        	info.setLikeNum(0);
        	info.setLookNum(0);
        	info.setCommentNum(0);
        	info.setShareNum(0);
    		infoService.insertSelective(info, info.getId());
    	}else{
    		info.setEb(SessionUtil.currentUserId());
    		info.setEt(new Date());
    		infoService.updateByPrimaryKeySelective(info, info.getId());
    	}
        return "redirect:/info/list";
    }
    
    /**
     * 置顶资讯
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "stick/{id}")
    public String stick(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	infoService.stick(id);
    	return "redirect:/info/list";
    }
    
    /**
     * 置顶资讯
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "revoke/{id}")
    public String revoke(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	infoService.revoke(id);
    	return "redirect:/info/list";
    }
    
    /**
     * 删除资讯
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete/{id}")
    public String delete(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	infoService.delete(id);
    	return "redirect:/info/list";
    }
    
    /**
     * 删除资讯
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "view/{id}")
    public String view(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	model.addAttribute("info", infoService.view(id));
    	return "info/add";
    }

}
