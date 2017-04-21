package com.lc.zy.ball.boss.framework.statium.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statium.service.PmwMemberService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumInfoService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.PmwMember;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.common.web.WebUtils;

/**
 * 场馆管理Controller
 * 
 * @author fanlq
 *
 */

@Controller
@RequestMapping(value = "/statium")
public class StatiumController extends AbstractController {

	@Autowired
	private StatiumInfoService statiumInfoService;

	@Autowired
	private PmwMemberService pmwMemberService;

	private static Logger logger = LoggerFactory
			.getLogger(StatiumController.class);

	/**
	 * 
	 * <道馆list><功能具体实现>
	 *
	 * @create：2016年5月3日 下午2:21:18
	 * @author：sl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String index(HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<PmwMember> pmwMemberPage = null;
		try {
			searchParams.put("EQ_pid", "0");
			pmwMemberPage = pmwMemberService.findPmwMemberlList(
					new PageRequest(page, size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", pmwMemberPage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets
					.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.error("道馆list:{}" + e.getMessage());
		}
		return "statium/statiumList";
	}

	/**
	 * 
	 * <道馆编辑><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:15:19
	 * @author：sl
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detailForm")
	public String detailForm(HttpServletRequest request, Model model, Integer id) {
		StatiumInfos statiumInfos = statiumInfoService
				.getStatiumInfosByDgid(id);
		model.addAttribute("statium", statiumInfos);
		return "statium/statiumForm";
	}

	/**
	 * 
	 * <道馆信息保存><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:11:35
	 * @author：sl
	 * @param myForm
	 * @param priceTemps
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(StatiumInfosVo myForm, String priceTemps,
			HttpServletRequest request) {
		try {
			if (myForm.getLnglat() != null && !"".equals(myForm.getLnglat())) {
				String[] lnglat = myForm.lnglat.split(",");
				myForm.setLng(Double.valueOf(lnglat[0]));
				myForm.setLat(Double.valueOf(lnglat[1]));
			}
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
				myForm.setPhotos(photos);
			}
			if (StringUtils.isNotEmpty(myForm.getAreacode())) {
				Map<String, String> areaMap = Zonemap.split(myForm
						.getAreacode());
				if ("市辖区".equals(areaMap.get("city"))
						|| areaMap.get("city") == null) {
					myForm.setCity(areaMap.get("province"));
				} else {
					myForm.setCity(areaMap.get("city"));
				}
				myForm.setProvince(areaMap.get("province"));
				myForm.setArea(areaMap.get("area"));
			}
			statiumInfoService.save(myForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("道馆信息保存:{}", e.getMessage());
		}
		return "redirect:/statium";
	}

	/**
	 * 
	 * <新增道馆><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:02:41
	 * @author：sl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createForm")
	public String createForm(HttpServletRequest request, Model model) {
		return "statium/statiumForm";
	}

}
