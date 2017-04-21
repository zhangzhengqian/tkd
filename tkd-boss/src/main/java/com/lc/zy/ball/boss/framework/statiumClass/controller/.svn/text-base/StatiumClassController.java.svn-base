package com.lc.zy.ball.boss.framework.statiumClass.controller;

import java.io.PrintWriter;
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

import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statium.controller.StatiumController;
import com.lc.zy.ball.boss.framework.statiumClass.service.StatiumClassService;
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.common.web.WebUtils;

/**
 * 20160425 add 10:04 道馆课程设置Controller
 * 
 * @author flq
 *
 */
@Controller
@RequestMapping("/statiumClass")
public class StatiumClassController extends AbstractController {

	@Autowired
	private StatiumClassService statiumClassService;

	private static Logger logger = LoggerFactory.getLogger(StatiumController.class);

	/**
	 * 
	 * <道馆class list ><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:46:22
	 * @author：sl
	 * @param request
	 * @param model
	 * @param dgid
	 * @return
	 */
	@RequestMapping(value = { "", "/", "/list" })
	public String list(HttpServletRequest request, Model model, Integer dgid) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<StatiumClassVo> pmwMemberPage = null;
		try {
			searchParams.put("EQ_statiumId", dgid);
			pmwMemberPage = statiumClassService.list(new PageRequest(page, size), searchParams);
			model.addAttribute("position");
			model.addAttribute("data", pmwMemberPage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
			model.addAttribute("searchParams", searchParamsStr + "&dgid=" + dgid);
		} catch (Exception e) {
			logger.error("道馆class list:{}" + e.getMessage());
		}
		return "statiumClass/classList";
	}

	/**
	 * 
	 * <课程成绩添加初始化><功能具体实现>
	 *
	 * @create：2016年5月3日 下午4:02:17
	 * @author：sl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createForm")
	public String createForm(HttpServletRequest request, Model model) {
		return "statiumClass/classForm";
	}
	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2016年7月22日 上午11:54:14
	 * @author：zzq
	 * @param myForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(StatiumClassVo myForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 原价
			int price = Float.valueOf(myForm.getFlPrice()).intValue();
			if (myForm.getFlPrice() == null) {
				myForm.setPrice(0);
			} else {
				myForm.setPrice(price * 100);
			//折扣价
			if(myForm.getFlDiscountPrice()==null){
				myForm.setDiscountPrice(0);
			}else{
				myForm.setDiscountPrice(Float.valueOf(myForm.getFlDiscountPrice()).intValue()*100);
			}
			// XXX modify by zzq 160722：处理折扣为null的情况
			// 折扣
			/*int discount=myForm.getDiscount()==null?0:myForm.getDiscount();
		    discount = Float.valueOf(discount).intValue();
			if (myForm.getFlDiscount() == null) {
				myForm.setDiscount(0);
			} else {
				myForm.setDiscount(discount);
			}*/
			// 折扣价
			/*if (discount < 10) {
				myForm.setDiscountPrice(price * discount * 10);
			} else {
				myForm.setDiscountPrice(price * discount);
			}*/
			// 执教类型
			if (myForm.getType() == null) {
				myForm.setType(0);
			}
			if(myForm.getType()==1){
				myForm.setMaxPeople(1);
			}
			// 限额（暂时屏蔽）
			/*
			 * if (myForm.getFlLimitPrice() == null) { myForm.setLimitPrice(0);
			 * } else {
			 * myForm.setLimitPrice(Float.valueOf(myForm.getFlLimitPrice() *
			 * 100).intValue()); }
			 */
			statiumClassService.save(myForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("功能描述:{}", e.getMessage());
		}
		return "redirect:/statiumClass?dgid=" + myForm.getStatiumId();
	}

	/**
	 * 
	 * <删除课程><功能具体实现>
	 *
	 * @create：2016年5月4日 下午1:58:32
	 * @author：sl
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String id) {
		boolean flag = false;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (id != null && !id.equals("")) {
				flag = statiumClassService.deleteClassInfos(id);
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
	 * <课程详情><功能具体实现>
	 *
	 * @create：2016年5月4日 下午2:02:27
	 * @author：sl
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detailForm")
	public String detailForm(HttpServletRequest request, Model model, String id) {
		try {
			StatiumClassVo classVo = this.statiumClassService.getStatiumClassById(id);
			model.addAttribute("statiumClass", classVo);
		} catch (Exception e) {
			logger.debug("课程详情:{}", e.getMessage());
		}
		return "statiumClass/classForm";
	}
}
