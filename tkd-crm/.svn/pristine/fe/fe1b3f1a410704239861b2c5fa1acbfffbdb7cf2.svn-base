package com.lc.zy.ball.crm.framework.system.statiumManage.controller;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.crm.common.Constants;
import com.lc.zy.ball.crm.common.SessionUtil;
import com.lc.zy.ball.crm.common.Zonemap;
import com.lc.zy.ball.crm.common.web.AbstractController;
import com.lc.zy.ball.crm.framework.system.statiumManage.service.StatiumManageService;
import com.lc.zy.ball.crm.framework.system.statiumManage.vo.CoachVo;
import com.lc.zy.ball.crm.framework.system.statiumManage.vo.StudentVo;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 页面跳转
 */
@Controller
@RequestMapping(value = "/statiumManage")
public class StatiumManageController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(StatiumManageController.class);

	@Autowired
	private StatiumManageService statiumManageService = null;

	/**
	 *
	 * <学员list><功能具体实现>
	 *
	 * @create：2016/12/16 上午10:19
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/student")
	public String student(Model model, HttpServletRequest request) throws Exception {
		// 根据查询条件查
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		// 参数去空
		CommonOAUtils.paramesTrim(searchParams);
		Page<StudentVo> pageData = null;
		try {
			searchParams.put("EQ_statiumId", SessionUtil.currentStatium());
			// 获取订单list
			pageData = statiumManageService.findStudent(new PageRequest(page, size), searchParams);
			model.addAttribute("data", pageData);
		} catch (Exception e) {
			logger.error("获取学员列表失败：{}",e.getMessage());
		}
		return "/statiumManage/student";
	}

	/**
	 *
	 * <学员detail><功能具体实现>
	 *
	 * @create：2016/12/19 下午2:21
	 * @author：sl
	 * @param model
	 * @param request
	 * @param userId
	 * @return java.lang.String
	 */
	@RequestMapping(value="/studentDetail/{userId}")
	public String studentDetail(Model model, HttpServletRequest request, @PathVariable("userId") String userId) throws Exception {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		StudentVo vo = statiumManageService.studentDetail(new PageRequest(page, size), userId);
		model.addAttribute("studentVo", vo);
		model.addAttribute("data", vo.getPageData());
		return "/statiumManage/studentDetail";
	}

	/**
	 *
	 * <获取教练list><功能具体实现>
	 *
	 * @create：2016/12/19 下午4:32
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/coach")
	public String coach(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CoachVo> coachPage = null;
		try {
			coachPage = statiumManageService.coachList(new PageRequest(page, size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", coachPage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr);
		} catch (Exception e) {
			logger.error("CoachController list error:" + e.getMessage());
		}
		return "/statiumManage/coach";
	}

	/**
	 *
	 * <新增教练初始化><功能具体实现>
	 *
	 * @create：2016/12/19 下午4:41
	 * @author：sl
	 * @param model
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value="/initCoachForm")
	public String initCoachForm(Model model, HttpServletRequest request) throws Exception {
		return "/statiumManage/coachForm";
	}

	/**
	 *
	 * <教练信息保存><功能具体实现>
	 *
	 * @create：2016/12/19 下午4:39
	 * @author：sl
	 * @param coachVo
	 * @param request
	 * @return java.lang.String
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
			statiumManageService.saveCoach(coachVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("coachForm error:" + e.getMessage());
		}

		return "redirect:/statiumManage/coach";
	}

	/**
	 *
	 * <根据教练id获取教练信息><功能具体实现>
	 *
	 * @create：2016/12/19 下午4:42
	 * @author：sl
	 * @param request
	 * @param model
	 * @param coachId
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/detailForm/{coachId}")
	public String detailForm(HttpServletRequest request, Model model, @PathVariable("coachId")String coachId) {
		Coach coach = statiumManageService.getCoachById(coachId);
		model.addAttribute("coach", coach);
		return "/statiumManage/coachForm";
	}

	/**
	 *
	 * <删除教练><功能具体实现>
	 *
	 * @create：2016/12/19 下午4:47
	 * @author：sl
	 * @param coachId
	 * @param request
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/deleteCoach/{coachId}")
	@ResponseBody
	public String deleteCoach(@PathVariable("coachId") String coachId, HttpServletRequest request) throws Exception{
		Map<String, String > result = new HashMap<String, String >();
		try {
			statiumManageService.deleteCoachByid(coachId);
			result.put(Constants.RESULT, Constants.SUCCESS);
		} catch (Exception e) {
			logger.debug("删除教练:{}", e.getMessage());
			result.put(Constants.RESULT, Constants.FAIL);
			result.put(Constants.DATA, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
}