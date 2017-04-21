package com.lc.zy.ball.boss.framework.video.controller;

import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.framework.video.service.VideoService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.videoGroup;
import com.lc.zy.common.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Autowired
	private VideoService videoService;

	/**
	 * 
	 * <视频集列表展示><功能具体实现>
	 *
	 * @create：2016年12月23日 上午9:25:05
	 * @author：zzq
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,HttpServletRequest request){
		System.out.println("进入");
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		PageRequest pageRequest = new PageRequest(page, pageSize);
		Page<videoGroup> groups = videoService.list(pageRequest,searchParams);
		Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("data", groups);
		return "/video/videoList";
		
	}
	
	/**
	 * 
	 * <视频集添加页面初始化><功能具体实现>
	 *
	 * @create：2016年12月23日 上午10:39:54
	 * @author：zzq
	 * @return
	 */
	@RequestMapping(value="/createGroup")
	public String createVideoGroup(Model model,String action){
		model.addAttribute("action", action);
		return "/video/videoForm";
	}
	
	/**
	 * 
	 * <保存视频集><功能具体实现>
	 *
	 * @create：2016年12月23日 上午11:07:32
	 * @author：zzq
	 * @param group
	 * @return
	 */
	@RequestMapping(value="/save")
	public String save(videoGroup group){
		try {
			videoService.saveOrUpdate(group);
		} catch (Exception e) {
			logger.debug("saveOrUpdate视频集 {}", e.getMessage());
		}

		return "redirect:/video/list";
	}
	
	/**
	 * 
	 * <删除视频集><功能具体实现>
	 *
	 * @create：2016年12月23日 上午11:08:28
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteGroup")
	public void deleteGroup(String id,HttpServletResponse response){
		boolean flag = false;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			videoGroup group = videoService.selectGroup(id);
			if(group!=null){
				flag = videoService.deleteGroup(group);
			}
		} catch (Exception e) {
			logger.debug("删除课程:{}", e.getMessage());
			e.printStackTrace();
		}
		String results = FlashAttributeUtil.build(flag, "");
		out.write(results);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	 * <详情><功能具体实现>
	 *
	 * @create：2016年12月23日 上午11:16:13
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@RequestMapping(value="detailVideo")
	public String detailVideo(Model model,String id,String action){
		videoGroup group = videoService.selectGroup(id);
		if(group!=null){
			model.addAttribute("videoGroup", group);
			model.addAttribute("action",action);
		}else{
			return "redirect:/video/list";
		}
		
		return "/video/videoForm";
	}
	
}