package com.lc.zy.ball.boss.framework.assessment.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.assessment.service.AssessmentService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Assessment;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;
    
    /**
     * 测评列表
     * @Title: list
     * @author：wpengcheng
     * @date： 2016年10月18日 下午3:22:09  
     * @param model
     * @param request
     * @return
     * @throws Exception
     * @return: String
     * @throws:
     */
    @RequestMapping(value = "list")
    public String list(Model model, HttpServletRequest request) throws Exception {
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<Assessment> onePage = assessmentService.list(new PageRequest(page, size));
        model.addAttribute("data", onePage);
        return "assessment/list";
    }
    /**
     * 跳转测评添加页面
     * @Title: add
     * @author：wpengcheng
     * @date： 2016年10月18日 下午3:25:56  
     * @param model
     * @param request
     * @return
     * @throws Exception
     * @return: String
     * @throws:
     */
    @RequestMapping(value = "add")
    public String add(Model model, HttpServletRequest request) throws Exception {
        return "assessment/add";
    }
    /**
     * 添加测评
     * @Title: save
     * @author：wpengcheng
     * @date： 2016年10月18日 下午3:26:12  
     * @param model
     * @param request
     * @param assessment
     * @param oper
     * @param sendTime_
     * @return
     * @throws Exception
     * @return: String
     * @throws:
     */
    @RequestMapping(value = "save")
    public String save(Model model, HttpServletRequest request,Assessment assessment,Integer oper) throws Exception {
    	//立即发送
    	boolean newFlag = false;
    	if(StringUtils.isBlank(assessment.getId())){
    		assessment.setId(UUID.get());
    		newFlag = true;
    	}
    	Map<String,Object> params1=new HashMap<>();
    	params1.put("videoFile", assessment.getVideoFile());
    	params1.put("image", assessment.getVideoImage());
    	String video=FreeMarkerUtils.format("/template/assessment/video.ftl", params1);
    	Map<String, Object> params=new HashMap<>();
    	if (StringUtils.isNotEmpty(assessment.getVideoFile())) {
    		params.put("video", video);
		}else{
			params.put("video", "");
		}
    	params.put("content", assessment.getContent());
    	String html = FreeMarkerUtils.format("/template/assessment/view.ftl", params);
    	generateFiles(html, assessment.getId());
    	if (oper==1) {
			assessment.setSendTime(new Date());
		}
    	if(newFlag){
    		assessment.setSendType(1);
    		assessment.setSendTime(new Date());
        	assessment.setLikeNum(0);
        	assessment.setLookNum(0);
        	assessment.setCommentNum(0);
        	assessment.setShareNum(0);
        	assessment.setCt(new Date());
        	assessment.setCb(SessionUtil.currentUserId());
        	assessment.setEt(new Date());
        	assessment.setEb(SessionUtil.currentUserId());
    		assessmentService.insertSelective(assessment, assessment.getId());
    	}else{
    		assessment.setEb(SessionUtil.currentUserId());
    		assessment.setEt(new Date());
    		assessmentService.updateByPrimaryKeySelective(assessment, assessment.getId());
    	}
        return "redirect:/assessment/list";
    }
    
    
    @RequestMapping(value = "delete/{id}")
    public String delete(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	assessmentService.delete(id);
    	deleteFiles(id);
    	return "redirect:/assessment/list";
    }
    
    @RequestMapping(value = "revoke/{id}")
    public String revoke(Model model,@PathVariable String id,HttpServletRequest request)throws Exception{
    	assessmentService.revoke(id);
    	return "redirect:/assessment/list";
    }
    
    @RequestMapping(value = "send/{id}")
    public String send(Model model,@PathVariable String id,HttpServletRequest request)throws Exception{
    	assessmentService.send(id);
    	return "redirect:/assessment/list";
    }
   
    @RequestMapping(value = "view/{id}")
    public String view(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	model.addAttribute("assessment", assessmentService.view(id));
    	return "assessment/add";
    }
    
    public void generateFiles(String html,String id) throws Exception{
    	String fileFix = "/home/appusr/www/assessments/";
    	File folder = new File(fileFix+id);
    	File newFile = new File(fileFix+id+"/index.html");
    	if(folder.exists()){
    		
    	}else{
    		folder.mkdir();
    	}
    	FileOutputStream fos = new FileOutputStream(newFile);
    	fos.write(html.getBytes("utf-8"));
    	fos.flush();
    	fos.close();
    }
    
    public void deleteFiles(String id) throws Exception{
    	String fileFix = "/home/appusr/www/assessments/";
    	File folder = new File(fileFix+id);
    	File newFile = new File(fileFix+id+"/index.html");
    	if(folder.exists()){
    		newFile.delete();
    		folder.delete();
    	}
    }
}
