package com.lc.zy.ball.boss.framework.qiuyouzone.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.qiuyouzone.service.QiuyouZoneService;
import com.lc.zy.ball.boss.framework.qiuyouzone.vo.QIuyouzoneVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.domain.oa.po.ManageSso;
import com.lc.zy.ball.domain.oa.po.Qiuyouzone;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneComment;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabel;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLike;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * 球友圈 - 球友圈管理
 *
 */
@Controller
@RequestMapping(value="/qiuyouzone")
public class QiuyouZoneController extends AbstractController{
	
	@Autowired
	private  QiuyouZoneService qiuyouZoneService;
	
	private static Logger logger = LoggerFactory.getLogger(QiuyouZoneController.class);
	
	
	/**
	 * 球友圈列表
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		Page<Map<String,Object>> onePage = qiuyouZoneService.find(searchParams, page, size);
		model.addAttribute("data", onePage);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "qiuyouzone/list";
	}
	
	
	/**
	 * 查看
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="view/{id}")
	public String view(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Qiuyouzone zone = qiuyouZoneService.selectByPrimaryKey(Qiuyouzone.class, id);
		model.addAttribute("zone", zone);
		
		String userid = zone.getUserId();
		SsoUser user = qiuyouZoneService.selectByPrimaryKey(SsoUser.class, userid);
		model.addAttribute("user", user);
		
		List<QiuyouzoneComment> comments = qiuyouZoneService.getComments(zone.getId());
		model.addAttribute("comments", comments);
		
		List<QiuyouzoneLike> likes = qiuyouZoneService.getLikes(zone.getId());
		model.addAttribute("likes", likes);
		
		
		return "qiuyouzone/view";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Qiuyouzone zone = qiuyouZoneService.selectByPrimaryKey(Qiuyouzone.class, id);
		Integer state = zone.getStatus();
		Qiuyouzone zone_ = new Qiuyouzone();
		zone_.setId(id);
		zone_.setEb(SessionUtil.currentUserId());
		zone_.setEt(new Date());
		if(state==0||state==1){
			zone_.setStatus(2);
		}else{
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "状态不正确，操作失败！"));
			return "redirect:/qiuyouzone/list";
		}
		qiuyouZoneService.updateByPrimaryKeySelective(zone_, id);
		return "redirect:/qiuyouzone/list";
	}
	
	/**
	 * 冻结
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="frozen/{id}")
	public String frozen(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Qiuyouzone zone = qiuyouZoneService.selectByPrimaryKey(Qiuyouzone.class, id);
		Integer state = zone.getStatus();
		Qiuyouzone zone_ = new Qiuyouzone();
		zone_.setId(id);
		zone_.setEb(SessionUtil.currentUserId());
		zone_.setEt(new Date());
		if(state==0){
			zone_.setStatus(1);
		}else if(state==1){
			zone_.setStatus(0);
		}else{
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "状态不正确，操作失败！"));
			return "redirect:/qiuyouzone/list";
		}
		qiuyouZoneService.updateByPrimaryKeySelective(zone_, id);
		return "redirect:/qiuyouzone/list";
	}
	
	/**
	 * 删除指定评论
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="comment/delete/{id}")
	@ResponseBody
	public String deleteComment(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		QiuyouzoneComment comment = qiuyouZoneService.getComment(id);
		Integer state = comment.getState();
		QiuyouzoneComment comment_ = new QiuyouzoneComment();
		comment_.setId(id);
		comment_.setEb(SessionUtil.currentUserId());
		comment_.setEt(new Date());
		if(state==0){
			comment_.setState(1);
		}else{
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "状态不正确，操作失败！"));
			return "redirect:/qiuyouzone/list";
		}
		qiuyouZoneService.updateComment(comment_);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constants.Result.RESULT, true);
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 营销列表
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/list")
	public String marketList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		Page<Qiuyouzone> onePage = qiuyouZoneService.findMarkets(searchParams, page, size);
		model.addAttribute("data", onePage);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "qiuyouzone/market/list";
	}
	
	/**
	 * 营销账户列表
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/user/list")
	public String marketUserList(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		CommonOAUtils.paramesTrim(searchParams);//参数去空
		Page<ManageSso> onePage = qiuyouZoneService.findMarketUsers(searchParams, page, size);
		model.addAttribute("data", onePage);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "qiuyouzone/market/user/list";
	}
	
	/**
	 * 删除运营账号
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/user/delete/{id}")
	public String deleteMarketUser(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		qiuyouZoneService.deleteMarketUser(id);
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "删除成功！"));
		return "redirect:/qiuyouzone/market/user/list";
	}
	
	/**
	 * 添加球友圈
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/add")
	public String addMarket(Model model,HttpServletRequest request) throws Exception {
		List<SsoUser> users = qiuyouZoneService.getMarketUsers();
		List<QiuyouzoneLabel> labels = qiuyouZoneService.getLabels();
		model.addAttribute("users", users);
		model.addAttribute("labels", labels);
		return "qiuyouzone/market/add";
	}
	
	/**
	 * 添加球友圈
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/save")
	public String saveMarket(QIuyouzoneVo form,Model model,HttpServletRequest request) throws Exception {
		String [] photos = form.getPhoto();
		String photo_ = "";
		for(String photo:photos){
			if(StringUtils.isNotBlank(photo)){
				photo_ += photo+";";
			}
		}
		photo_ = photo_.substring(0,photo_.length()-1);
		String statiumId = form.getStatiumId();
		StatiumDetail detail = qiuyouZoneService.selectByPrimaryKey(StatiumDetail.class, statiumId);
		
		String tempTime = form.getTempTime();
		Date sendTime_ = DateUtil.parse(tempTime, "yyyy-MM-dd HH", null);
		Qiuyouzone qiuyouzone_ = new Qiuyouzone();
		BeanUtils.copyProperties(form, qiuyouzone_);
		qiuyouzone_.setSendTime(sendTime_);
		qiuyouzone_.setIsLabel(1);
		int sendType = form.getSendType();
		if(sendType==1){
			qiuyouzone_.setCt(new Date());
		}else{
			qiuyouzone_.setCt(sendTime_);
		}
		qiuyouzone_.setEt(new Date());
		String userId = SessionUtil.currentUserId();
		qiuyouzone_.setCb(userId);
		qiuyouzone_.setEb(userId);
		qiuyouzone_.setSendMan(userId);
		qiuyouzone_.setImage(photo_);
		qiuyouzone_.setLat(detail.getLat());
		qiuyouzone_.setLng(detail.getLng());
		qiuyouzone_.setAddress(detail.getAddress());
		qiuyouZoneService.saveQiuyouzone(qiuyouzone_,form.getLabelId());
		return "redirect:/qiuyouzone/market/list";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/delete/{id}")
	public String deleteMarket(@PathVariable String id,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Qiuyouzone zone = qiuyouZoneService.selectByPrimaryKey(Qiuyouzone.class, id);
		Integer state = zone.getStatus();
		Qiuyouzone zone_ = new Qiuyouzone();
		zone_.setId(id);
		zone_.setEb(SessionUtil.currentUserId());
		zone_.setEt(new Date());
		if(state==0||state==1){
			zone_.setStatus(2);
		}else{
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "状态不正确，操作失败！"));
			return "redirect:/qiuyouzone/list";
		}
		qiuyouZoneService.updateByPrimaryKeySelective(zone_, id);
		return "redirect:/qiuyouzone/market/list";
	}
	
	/**
	 * 获取球友圈详情
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="market/update/{id}")
	public String updateMarket(@PathVariable String id,Model model,HttpServletRequest request) throws Exception {
		List<SsoUser> users = qiuyouZoneService.getMarketUsers();
		model.addAttribute("users", users);
		List<QiuyouzoneLabel> labels = qiuyouZoneService.getLabels();
		model.addAttribute("labels", labels);
		Qiuyouzone zone_ = qiuyouZoneService.selectByPrimaryKey(Qiuyouzone.class, id);
		QIuyouzoneVo zone = new QIuyouzoneVo();
		BeanUtils.copyProperties(zone_, zone);
		String labelId = qiuyouZoneService.getLabelId(id);
		if(zone_.getSendTime()!=null){
			zone.setTempTime(DateUtil.formatDate(zone_.getSendTime(), "yyyy-MM-dd HH"));
		}
		String photos = zone.getImage();
		String photos_[] = photos.split(";");
		zone.setPhoto(photos_);
		zone.setLabelId(labelId);
		String statiumId = zone.getStatiumId();
		StatiumDetail detail = qiuyouZoneService.selectByPrimaryKey(StatiumDetail.class, statiumId);
		zone.setStatiumName(detail.getName());
		model.addAttribute("zone", zone);
		return "qiuyouzone/market/update";
	}
	
}
