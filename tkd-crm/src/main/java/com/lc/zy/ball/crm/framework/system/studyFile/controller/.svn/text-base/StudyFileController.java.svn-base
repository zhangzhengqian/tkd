package com.lc.zy.ball.crm.framework.system.studyFile.controller;

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

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.studyFile.service.StudyFileService;
import com.lc.zy.ball.domain.oa.po.CrmUserFile;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value="/studyFile")
public class StudyFileController extends AbstractController{
	private static Logger logger = LoggerFactory.getLogger(StudyFileController.class);
	
	@Autowired
	private StudyFileService studyFileService;
	/**
	 * 
	 * <学习资料列表><功能具体实现>
	 *
	 * @create：2017年4月10日 上午10:05:08
	 * @author：ywl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"","/","list"})
	public String list(HttpServletRequest request,Model model){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CrmUserFile> list = null;
		try {
			String statiumId = SessionUtil.currentStatium();
			list = studyFileService.list(new PageRequest(page, size), searchParams,statiumId);
			model.addAttribute("data", list);
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr);
		} catch (Exception e) {
			logger.debug("学习资料列表：{}"+e.getMessage());
		}
		return "/studyFile/studyFileList";
		
	}
	/**
	 * 
	 * <创建form><功能具体实现>
	 *
	 * @create：2017年4月10日 上午10:28:36
	 * @author：ywl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createForm")
	public String createForm(HttpServletRequest request,Model model){
		return "/studyFile/studyFileForm";
	}
	/**
	 * 
	 * <保存学习资料><功能具体实现>
	 *
	 * @create：2017年4月10日 下午12:52:38
	 * @author：ywl
	 * @param crmUserFile
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(CrmUserFile crmUserFile){
		try {
			studyFileService.save(crmUserFile);
		} catch (Exception e) {
			logger.debug("保存学习资料：{}"+e.getMessage());
		}
		return "redirect:/studyFile";
	}
	/**
	 * 
	 * <删除学习资料><功能具体实现>
	 *
	 * @create：2017年4月10日 下午12:57:53
	 * @author：ywl
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delFile/{id}")
	public String delFile(@PathVariable String id){
		try {
			studyFileService.delFile(id);
		} catch (Exception e) {
			logger.debug("删除学习资料：{}"+e.getMessage());
		}
		return "redirect:/studyFile/list";
	}
}
