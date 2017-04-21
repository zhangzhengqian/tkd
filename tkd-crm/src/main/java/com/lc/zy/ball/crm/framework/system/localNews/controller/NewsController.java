package com.lc.zy.ball.crm.framework.system.localNews.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.localNews.service.NewsService;
import com.lc.zy.ball.crm.framework.system.localNews.vo.NewsVo;
import com.lc.zy.ball.domain.oa.po.News;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;


@Controller
@RequestMapping(value = "/localNews")
public class NewsController extends AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public String sign() {
		return "/news/newsForm";
	}

	/**
	 * 
	 * <新闻资讯list><功能具体实现>
	 *
	 * @create：2016年5月4日 下午6:48:33
	 * @author：sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "list")
	public String newsList(Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<NewsVo> newsPage = null;
		try {
			newsPage = newsService.findNewsList(new PageRequest(page, size),
					searchParams);
			System.out.println(newsPage.getNumber() + "***"
					+ newsPage.getNumberOfElements() + "***"
					+ newsPage.getSize() + "***" + newsPage.getTotalElements()
					+ "***" + newsPage.getTotalPages());
		} catch (Exception e) {
			logger.error("新闻资讯list:{}", e.getMessage());
		}
		model.addAttribute("data", newsPage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/news/newsList";
	}

	/**
	 * 
	 * <本地新闻资讯保存><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:21:59
	 * @author：sl
	 * @param myForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(NewsVo myForm, Model model, HttpServletRequest request) {
		try {
			//保存道馆id
			myForm.setStatiumid(SessionUtil.currentStatium());
			
			// 图片处理
			if (myForm.getPhoto() != null && myForm.getPhoto().length > 0) {
				String photos = "";
				for (String p : myForm.getPhoto()) {
					if (StringUtils.isNotEmpty(p)) {
						photos = photos + p + "__";
					}
				}
				if (StringUtils.isNotEmpty(photos)) {
					photos = photos.substring(0, photos.length() - 2);
				}
				myForm.setImages(photos);
			}
			newsService.save(myForm);
		} catch (Exception e) {
			logger.debug("新闻资讯保存:{}", e.getMessage());
		}
		return "redirect:/localNews/list";
	}

	/**
	 * 
	 * <删除新闻资讯><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:24:04
	 * @author：sl
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteNews", method = RequestMethod.POST)
	public String deleteCarousel(String id, Model model) {
		
		 Map<String,String> result = new HashMap<String, String>();
	        try {
	        	if (id != null && !"".equals(id)) {
	    			try {
	    				newsService.deleteById(id);
	    			} catch (Exception e) {
	    				logger.error("删除新闻资讯:{}", e.getMessage());
	    			}
	    		}
	            result.put(Constants.RESULT, Constants.SUCCESS);
	        } catch (Exception e) {
	            logger.debug("删除课程失败 {}", e.getMessage());
	            result.put(Constants.RESULT, Constants.FAIL);
	            result.put(Constants.DATA, e.getMessage());
	        }
	        return MyGson.getInstance().toJson(result);
		
	}

	/**
	 * 
	 * <更新信息初始化><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:27:16
	 * @author：sl
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateNews/{id}", method = RequestMethod.GET)
	public String updateCarousel(@PathVariable String id, Model model) {
		if (id != null && !id.equals("")) {
			try {
				News news = newsService.newsById(id);
				NewsVo newsVo = new NewsVo();
				BeanUtils.copyProperties(news, newsVo);
				newsVo.setpDate(DateUtils.forDatetime(news.getPubDate()));
				model.addAttribute("news", newsVo);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateCarousel error:" + e.getMessage());
				return "redirect:/news/list";
			}
		}
		return "/news/newsForm";
	}

}