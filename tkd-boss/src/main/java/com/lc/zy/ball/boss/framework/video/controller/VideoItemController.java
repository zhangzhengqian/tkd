package com.lc.zy.ball.boss.framework.video.controller;

import com.lc.zy.ball.boss.framework.video.service.VideoItemService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.TrainVideo;
import com.lc.zy.common.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping(value="/videoItem")
public class VideoItemController {
	private static Logger logger = LoggerFactory.getLogger(VideoItemController.class);
	
	@Autowired
	private VideoItemService videoItemService;

	/**
	 * 
	 * <视频集列表><功能具体实现>
	 *
	 * @create：2016年12月22日 上午9:19:00
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/videoItemList/{parentId}")
	public String list(Model model,HttpServletRequest request,@PathVariable String parentId){
		
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<TrainVideo> itemList = null;
		try {
			itemList = videoItemService.findCarouselList(new PageRequest(page, size), searchParams,parentId);
		} catch (Exception e) {
			logger.error("carousalList error:" + e.getMessage());
		}
		model.addAttribute("data", itemList);
		model.addAttribute("parentId", parentId);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/video/videoItemList";
	}
	
	/**
	 * 
	 * <教学视频添加页面初始化><功能具体实现>
	 *
	 * @create：2016年12月21日 下午4:00:07
	 * @author：zzq
	 * @return
	 */
	@RequestMapping(value="videoItemSign/{parentId}", method = RequestMethod.GET)
	public String signTrain(@PathVariable String parentId,Model model){
		model.addAttribute("parentId", parentId);
		return "/video/videoItemForm";
	}
	
	/**
	 * 
	 * <添加或更新教学视频><功能具体实现>
	 *
	 * @create：2016年12月21日 下午4:06:27
	 * @author：zzq
	 * @param myForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String trainForm(TrainVideo myForm, Model model, HttpServletRequest request) {
		try {
			videoItemService.saveOrUpdate(myForm);
		} catch (Exception e) {
			logger.debug("saveOrUpdate视频集item {}", e.getMessage());
		}
		return "redirect:/videoItem/videoItemList/" + myForm.getParentId();
	}
	
	/**
	 * 
	 * <视频Item删除><功能具体实现>
	 *
	 * @create：2016年12月22日 上午10:55:11
	 * @author：zzq
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteVideoItem/{id}", method = RequestMethod.POST)
	public String deleteVideoItem(@PathVariable String id,Model model) {
		String parentId = "";
		if (id != null && !"".equals(id)) {
			try {
				parentId = videoItemService.getItemParentId(id);
				if(parentId!=null){
					model.addAttribute("parentId", parentId);
				}
				
				// 删除
				videoItemService.deleteVideoById(id);
			} catch (Exception e) {
				logger.error("视频Item删除:" + e.getMessage());
			}
		}
		return "redirect:/videoItem/videoItemList/"+parentId;
	}

	/**
	 * 
	 * <视频集初始化><功能具体实现>
	 *
	 * @create：2016年12月21日 下午4:45:21
	 * @author：zzq
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateVideoItem/{id}", method = RequestMethod.GET)
	public String updatetrain(@PathVariable String id, Model model) {
		try {
			TrainVideo trainVideo = videoItemService.selectById(id);
			model.addAttribute("parentId", trainVideo.getParentId());
			model.addAttribute("video", trainVideo);
		} catch (Exception e) {
			logger.error("视频集item初始化:" + e.getMessage());
		}
		return "/video/videoItemForm";
	}

}
