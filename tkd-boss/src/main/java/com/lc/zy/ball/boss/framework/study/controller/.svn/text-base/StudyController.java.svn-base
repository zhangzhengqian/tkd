package com.lc.zy.ball.boss.framework.study.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.UploaderController;
import com.lc.zy.ball.boss.framework.study.service.StudyService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.UserFile;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value="/study")
public class StudyController {
	private static Logger logger = LoggerFactory.getLogger(UploaderController.class);
	
	@Autowired
	private StudyService studyService;
	@RequestMapping(value = {"","/","/list"})
	public String getStudyList(HttpServletRequest request,Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<UserFile> userFile = null;
		try {
			userFile = studyService.list(new PageRequest(page, size), searchParams);
			model.addAttribute("data", userFile);
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr);
		} catch (Exception e) {
			logger.debug("获取学习资料列表:"+e.getMessage());
		}
		return "/study/studyList";
		
	}
	/**
	 * 
	 * <添加学习资料><功能具体实现>
	 *
	 * @create：2017年4月7日 下午2:24:31
	 * @author：ywl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createForm")
    public String createForm(HttpServletRequest request,Model model){
    	System.out.println("添加学习资料");
    	model.addAttribute("action", "create");
    	return "/study/studyForm";
    }
	/**
	 * 
	 * <保存学习资料><功能具体实现>
	 *
	 * @create：2017年4月7日 下午2:24:43
	 * @author：ywl
	 * @param myForm
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(UserFile myForm){
		try {
			
			studyService.save(myForm);
		} catch (Exception e) {
			logger.debug("上传学习资料：{}"+e.getMessage());
		}
		return "redirect:/study";
		
	}
	
	/**
	 * 
	 * <主键删除文件><功能具体实现>
	 *
	 * @create：2017年4月8日 下午5:15:22
	 * @author：zzq
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deleteStudy/{id}",method=RequestMethod.POST)
	public String deleteFile(@PathVariable String id,Model model){
		
		studyService.deleteFile(id);
		
		return "redirect:/study/list";
		
	}
}
