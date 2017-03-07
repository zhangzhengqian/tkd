package com.lc.zy.ball.boss.framework.website.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.website.service.SEOService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.WebsiteSeo;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/seo")
public class SEOController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(SEOController.class);
	
	@Autowired
	private SEOService sEOService;
	
	
	/**
	 * 
	 * <列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午12:44:53
	 */
	@RequestMapping(value = "list/{type}")
	public String list(@PathVariable Integer type,Model model,HttpServletRequest request){
		try {
			logger.info("SEOController list method execute!");
			Map<String, Object> searchParams  = Servlets.getParametersStartingWith(request, "search_");
			CommonOAUtils.paramesTrim(searchParams);
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<WebsiteSeo> ongPage = sEOService.find(new PageRequest(page, size), searchParams,type);
			model.addAttribute("searchParams",Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
			model.addAttribute("data",ongPage);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error(e.getMessage(), e);
		}
		return "website/seoList";
	}
	
	/**
	 * 
	 * <创建><功能具体实现>
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:18
	 */
	@RequestMapping(value = "createForm")
	public String createForm(Model model){
		return "website/seoForm";
	}
	
	/**
	 * 
	 * <保存><功能具体实现>
	 * @param info
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:29
	 */
	@RequestMapping(value = "save")
	public String save(WebsiteSeo info,Model model){
		logger.info("SEOController save execute!");
		if(info != null){
			sEOService.saveOrUpdate(info);
		}
		return "redirect:/seo/list/0";
	}
	
	/**
	 * 
	 * <详情><功能具体实现>
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:41:54
	 */
	@RequestMapping(value="detailForm")
	public String detailForm(String id,Model model){
		try {
			if(StringUtils.isNotBlank(id)){
				WebsiteSeo info = sEOService.selectByPrimaryKey(WebsiteSeo.class,id);
				model.addAttribute("info", info);
			}
		} catch (Exception e) {
			e.printStackTrace();
            logger.error(e.getMessage(), e);
		}
		return "/website/seoForm";
	}
	
	/**
	 * 
	 * <删除><功能具体实现>
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午3:31:53
	 */
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		logger.info("SEOController delete execute!");
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(id)){
				WebsiteSeo infoseSeo = sEOService.selectByPrimaryKey(WebsiteSeo.class,id);
				if(infoseSeo != null ){
					sEOService.deleteByPrimaryKey(WebsiteSeo.class,id);
					result.put(Constants.Result.RESULT, true);
				}
			}else{
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
}
