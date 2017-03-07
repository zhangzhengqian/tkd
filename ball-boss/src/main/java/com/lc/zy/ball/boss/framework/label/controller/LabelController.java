package com.lc.zy.ball.boss.framework.label.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.label.service.LabelService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.Label;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.web.WebUtils;

/**
 * 运营管理 - 标签管理
 * @author yankefei
 *
 */
@Controller
@RequestMapping(value="/label")
public class LabelController extends AbstractController{
	
	@Autowired
	private LabelService labelService;
	
	private static Logger logger = LoggerFactory.getLogger(LabelController.class);
	
	private static Gson gson = new Gson();
	
	/**
	 * 标签 分页展示
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="list")
	public String ActivityList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		
		Page<Label> onePage = labelService.find(searchParams, page, size, true, true);
		
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "label/labelList";
	}
	
	/**
	 * 获取创建标签Form
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="create")
	public String createForm(Model model) throws Exception {
		model.addAttribute("action", "create");
		return "label/labelForm";
	}
	
	/**
	 * 获取修改标签Form
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="create/{id}")
	public String createForm(@PathVariable String id, Model model) throws Exception {
		Label label = labelService.getLabel(id);
		model.addAttribute("action", "edit");
		model.addAttribute("label", label);
		return "label/labelForm";
	}
	
	/**
	 * 创建标签
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String create(Label label, RedirectAttributes redirectAttributes) throws Exception{
		try {
			if(label!=null){
				if(labelService.checkName(label.getName(), label.getType())){
					labelService.insertOrUpdateLabel(label);
					redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "创建标签成功!"));
				}else{
					redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "标签名称已存在！"));
				}
			}else{
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "输入参数为空！"));
			}
		} catch (Exception e) {
			logger.debug("创建标签失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "创建标签失败！"));
		}
		return "redirect:/label/list";
	}
	
	/**
	 * 修改标签
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String edit(Label label, RedirectAttributes redirectAttributes) throws Exception{
		try {
			if(labelService.checkName(label.getName(), label.getType())){
				labelService.insertOrUpdateLabel(label);
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "修改标签成功!"));
			}else{
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "标签名称已存在！"));
			}
		} catch (Exception e) {
			logger.debug("修改标签失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "修改标签失败！"));
		}
		return "redirect:/label/list";
	}
	
	/**
	 * 删除标签
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable String id) throws Exception{
		Map<String,String> result = new HashMap<String,String>();
		try {
			labelService.deleteLabel(id);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "error");
			logger.error("删除标签失败: {}", e.getMessage());
		}
		return gson.toJson(result);
	}
	
	/**
	 * 更改标签状态
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @author yankefei
	 */
	@RequestMapping(value="status/{id}")
	public String status(@PathVariable String id, RedirectAttributes redirectAttributes){
		try {
			Label label = labelService.getLabel(id);
			if(label!=null){
				if(label.getStatus()==0){
					//启用
					label.setStatus(1);
					label.setEt(new Date());
				}else{
					//停用
					label.setStatus(0);
					label.setEt(new Date());
				}
				labelService.insertOrUpdateLabel(label);
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "更改标签状态成功!"));
			}else{
				redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "未查询到该标签！"));
			}
		} catch (Exception e) {
			logger.debug("更改标签状态失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "redirect:/label/list";
	}
}
