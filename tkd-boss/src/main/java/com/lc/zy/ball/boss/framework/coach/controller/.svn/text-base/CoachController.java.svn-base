package com.lc.zy.ball.boss.framework.coach.controller;

import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.framework.coach.service.CoachService;
import com.lc.zy.ball.boss.framework.coach.vo.CoachVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.common.web.WebUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/coach")
public class CoachController {

	private Logger logger = LoggerFactory.getLogger(CoachController.class);

	@Autowired
	private CoachService coachService;

	/**
	 * 
	 * <教练列表><功能具体实现>
	 *
	 * @create：2016年5月12日 下午1:51:10
	 * @author：sl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "", "/", "/list" })
	public String list(HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CoachVo> coachPage = null;
		try {
			coachPage = coachService.list(new PageRequest(page, size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", coachPage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr);
		} catch (Exception e) {
			logger.error("CoachController list error:" + e.getMessage());
		}
		return "coach/coachList";
	}

	@RequestMapping(value = "/createForm")
	public String createForm(HttpServletRequest request, Model model) {
		return "coach/coachForm";
	}

	/**
	 * <教练保存><功能具体实现>
	 *
	 * @create：2016年5月9日 下午4:34:22
	 * @author：wangp
	 * @param coachVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(CoachVo coachVo, HttpServletRequest request) {
		try {
			if (coachVo.getPhoto() != null && coachVo.getPhoto().length > 0) {
				String photos = "";
				for (String p : coachVo.getPhoto()) {
					if (StringUtils.isNotEmpty(p)) {
						photos = photos + p + "__";
					}
				}
				if (StringUtils.isNotEmpty(photos)) {
					photos = photos.substring(0, photos.length() - 2);
				}
				coachVo.setPhotos(photos);
			}
			if (StringUtils.isNotEmpty(coachVo.getAreacode())) {
				Map<String, String> areaMap = Zonemap.split(coachVo
						.getAreacode());
				if ("市辖区".equals(areaMap.get("city"))
						|| areaMap.get("city") == null) {
					coachVo.setCity(areaMap.get("province"));
				} else {
					coachVo.setCity(areaMap.get("city"));
				}
				coachVo.setProvince(areaMap.get("province"));
				coachVo.setArea(areaMap.get("area"));
			}
			coachService.save(coachVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("coachForm error:" + e.getMessage());
		}

		return "redirect:/coach";
	}

	/**
	 * <教练编辑><功能具体实现>
	 *
	 * @create：2016年5月9日 下午4:35:20
	 * @author：wangp
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detailForm")
	public String detailForm(HttpServletRequest request, Model model, String id) {
		Coach coach = coachService.getCoachById(id);
		model.addAttribute("coach", coach);
		return "coach/coachForm";
	}

	/**
	 * 
	 * <删除教练><功能具体实现>
	 *
	 * @create：2016年5月12日 上午11:50:51
	 * @author：sl
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteCoach/{id}")
	public String deleteCoach(@PathVariable String id, HttpServletRequest request) {
		try {
			coachService.deleteCoachByid(id);
		} catch (Exception e) {
			logger.debug("删除教练:{}", e.getMessage());
		}
		return "redirect:/coach";
	}
}
