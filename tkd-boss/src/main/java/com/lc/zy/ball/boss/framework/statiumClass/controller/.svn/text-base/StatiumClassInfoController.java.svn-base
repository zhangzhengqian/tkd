package com.lc.zy.ball.boss.framework.statiumClass.controller;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statiumClass.service.StatiumClassInfoService;
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassInfoVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/statiumClassInfo")
public class StatiumClassInfoController extends AbstractController {

	@Autowired
	private StatiumClassInfoService statiumClassInfoService;

	private Logger logger = LoggerFactory
			.getLogger(StatiumClassInfoController.class);

	/**
	 * 
	 * <课时list><功能具体实现>
	 *
	 * @create：2016年5月4日 下午1:54:08
	 * @author：sl
	 * @param request
	 * @param model
	 * @param classId
	 * @param statiumId
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, String classId,
			String statiumId) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<StatiumClassInfoVo> statiumClassInfoVoPage = null;
		try {
			// 将日期字符串转换成对象,这里注意，
			// 页面上 name=search_GTE_startTime ,到了这里，需要去掉前缀
			parseDate(searchParams, "GTE_classDate");
			parseDate(searchParams, "LTE_classDate");
			searchParams.put("EQ_classId", classId);
			searchParams.put("EQ_statiumId", statiumId);
			statiumClassInfoVoPage = statiumClassInfoService.list(
					new PageRequest(page, size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", statiumClassInfoVoPage);
			//将searchParams中的日期格式从date 改成String
			//modify by zzq 2016-12-05
			if(searchParams.get("GTE_classDate")!=null&&!"".equals(searchParams.get("GTE_classDate").toString())){
				String gteClassDate = searchParams.get("GTE_classDate").toString();
				Date gteDate = statiumClassInfoService.parse(gteClassDate, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
				String gteStr = DateUtil.formatDate(gteDate, "yyyy-MM-dd");
				logger.debug(gteStr);
				searchParams.remove("GTE_classDate");
				searchParams.put("GTE_classDate", gteStr);
			}
			if(searchParams.get("LTE_classDate")!=null&&!"".equals(searchParams.get("LTE_classDate").toString())){
				String lteClassDate = searchParams.get("LTE_classDate").toString();
				Date lteDate = statiumClassInfoService.parse(lteClassDate, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
				String lteStr = DateUtil.formatDate(lteDate, "yyyy-MM-dd");
				logger.debug(lteStr);
				searchParams.remove("LTE_classDate");
				searchParams.put("LTE_classDate", lteStr);
			}
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(
					searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr + "&classId="
					+ classId + "&statiumId=" + statiumId);
		} catch (Exception e) {
			logger.debug("课时list:{}", e.getMessage());
			e.printStackTrace();
		}
		return "statiumClass/classInfoList";
	}

	/**
	 * 
	 * <课时初始化><功能具体实现>
	 *
	 * @create：2016年5月4日 下午1:53:38
	 * @author：sl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createForm")
	public String createForm(HttpServletRequest request, Model model) {
		return "statiumClass/classInfoForm";
	}

	/**
	 * 
	 * <课程详情保存><功能具体实现>
	 *
	 * @create：2016年5月4日 下午12:01:32
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(StatiumClassInfoVo myForm, HttpServletRequest request) {
		try {
			this.statiumClassInfoService.save(myForm);
		} catch (Exception e) {
			logger.debug("课程详情保存:{}", e.getMessage());
		}
		return "redirect:/statiumClassInfo/list?classId=" + myForm.getClassId() + "&statiumId=" + myForm.getStatiumId();
	}

	/**
	 * 
	 * <课时详情><功能具体实现>
	 *
	 * @create：2016年5月4日 下午2:49:31
	 * @author：sl
	 * @param request
	 * @param model
	 * @param id
	 * @param statiumId
	 * @return
	 */
	@RequestMapping(value = "/detailForm")
	public String detailForm(HttpServletRequest request, Model model,
			String id, Integer statiumId) {
		try {
			StatiumClassInfoVo infoVo = this.statiumClassInfoService
					.getClassInfoById(id, statiumId);
			model.addAttribute("statiumClassInfo", infoVo);
		} catch (Exception e) {
			logger.debug("课时详情:{}", e.getMessage());
		}
		return "statiumClass/classInfoForm";
	}

	/**
	 * 
	 * <课时删除><功能具体实现>
	 *
	 * @create：2016年5月4日 下午2:14:14
	 * @author：sl
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, String id, String classId,String statiumId) {
		try {
			statiumClassInfoService.deleteByPrimaryKey(StatiumClassInfo.class,
					id);
		} catch (Exception e) {
			logger.debug("课时删除:{}", e.getMessage());
		}
		return "redirect:/statiumClassInfo/list?classId=" + classId +"&statiumId=" + statiumId;
	}

	/**
	 * 
	 * <返回课程列表><功能具体实现>
	 *
	 * @create：2016年5月4日 下午3:10:58
	 * @author：sl
	 * @param request
	 * @param response
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/backClassList")
	public String backClassList(HttpServletRequest request,
			HttpServletResponse response, String classId) {
		int statiumId = 0;
		try {
			StatiumClass statiumClass = statiumClassInfoService
					.classById(classId);
			statiumId = statiumClass.getStatiumId();
		} catch (Exception e) {
			logger.debug("返回课程列表:{}", e.getMessage());
		}
		return "redirect:/statiumClass/list?dgid=" + statiumId;
	}
}
