package com.lc.zy.ball.boss.framework.event.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.zy.ball.boss.framework.orders.vo.EnjoyOrderSearch;
import com.lc.zy.ball.boss.framework.orders.vo.OrderVo;
import com.lc.zy.common.util.*;
import com.lc.zy.common.web.EnjoyGameSiteManage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jasypt.commons.CommonUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.event.service.EnjoyRaceService;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameSiteVo;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameVo;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.EventMsgVo;
import com.lc.zy.ball.boss.framework.event.vo.ExportPlayer;
import com.lc.zy.ball.boss.framework.event.vo.GameScheduleVo;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.EnjoyElimination;
import com.lc.zy.ball.domain.oa.po.EnjoyGame;
import com.lc.zy.ball.domain.oa.po.EnjoyGameSite;
import com.lc.zy.ball.domain.oa.po.EnjoyGroupSchedule;
import com.lc.zy.ball.domain.oa.po.EnjoyMember;
import com.lc.zy.ball.domain.oa.po.GamesPlayer;
import com.lc.zy.ball.domain.oa.po.PushLog;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.common.gamegroup.GameDraw;
import com.lc.zy.common.gamegroup.bean.GamesScheduleModel;
import com.lc.zy.common.gamegroup.bean.GroupGameVo;
import com.lc.zy.common.push.PushPacket;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "/enjoyRace")
public class EnjoyRaceController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(EnjoyRaceController.class);

	@Autowired
	private EnjoyRaceService enjoyRaceService;

	@Autowired
	private PushPacket pushPacket;
	
	@Autowired
	private StatiumDetailService statiumDetailService;

	@Autowired
	private EnjoyGameSiteManage enjoyGameSiteManage;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * <乐享赛列表><功能具体实现>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午3:52:12
	 */
	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyGameVo> onePage = enjoyRaceService.list(new PageRequest(page, size), searchParams,true);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "enjoyrace/list";
	}

	/**
	 * 
	 * <创建乐享赛><功能具体实现>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午3:51:58
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		model.addAttribute("active", "create");
		return "enjoyrace/form";
	}
	

	/**
	 * 
	 * <保存乐享赛><功能具体实现>
	 * 
	 * @param gameVo
	 * @param redirectAttributes
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:51:50
	 */
	@RequestMapping(value = "save")
	public String save(EnjoyGameVo gameVo, RedirectAttributes redirectAttributes) {
		try {
			if (gameVo.getLngLat() != null && !"".equals(gameVo.getLngLat())) {
				String[] lnglat = gameVo.getLngLat().split(",");
				gameVo.setLng(Double.parseDouble(lnglat[0]));
				gameVo.setLat(Double.parseDouble(lnglat[1]));
			}
			if(gameVo != null){
				int size = 1;
				if(StringUtils.isBlank(gameVo.getId())){
				  size = gameVo.getGameTypes().length;
				}
				String eventId="";
				String id = "";
				for (int i = 0; i < size; i++) {
					//获取原赛事项目类型
					EnjoyGame game = new EnjoyGame();
					if(i == 0){
						BeanUtils.copyProperties(gameVo, game);
					}
					
					//只更新第一条赛事基本信息
					if(StringUtils.isBlank(game.getId())){
						game.setLevel(gameVo.getLevels()[gameVo.getGameTypes()[i]-1]);
						game.setGameType(gameVo.getGameTypes()[i]);
						game.setPrice(new Double(Double.valueOf(gameVo.getShowPrices()[gameVo.getGameTypes()[i]-1]) * 100).intValue());
						game.setGameFormat2(gameVo.getGameFormat2s()[gameVo.getGameTypes()[i]-1]);
						EnjoyGameSite site = enjoyRaceService.selectByPrimaryKey(EnjoyGameSite.class,gameVo.getSiteId());
						game.setLogo(site.getLogo());
						game.setSiteId(gameVo.getSiteId());
					}
					eventId = enjoyRaceService.createOrUpdate(game,id);
					if(i == 0){
						 id = eventId;
						if (gameVo.getIsPush() == 0) {
							String areaCode = gameVo.getAreaCode();
							pushPacket.sendPush(areaCode, com.lc.zy.common.Constants.pushMsgType.PUSH_TYPE_EVENT, eventId,
									gameVo.getName(), SessionUtil.currentUserId(),
									com.lc.zy.common.Constants.isPush.PUSH_TYPE_UNLIMIT, "");
						}
					}
				}
			}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存赛事成功!"));
		} catch (Exception e) {    
			logger.debug("保存赛事失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存赛事失败！"));
		}
		return "redirect:/enjoyRace/list";
	}

	
	/**
	 * 
	 * <查看赛事><功能具体实现>
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * 
	 * @date 2016年2月23日 下午3:51:39
	 */
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable String id, Model model) {
		try {
			EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,id);
			Map<Integer,EnjoyGame> gameMap =  enjoyRaceService.gameVo(game);
			StringBuilder sb = new StringBuilder(game.getGameType());
			for(Map.Entry<Integer,EnjoyGame> entry:gameMap.entrySet()){    
			    sb.append(",").append(entry.getKey());
			} 
		    model.addAttribute("gameMap",gameMap);
			model.addAttribute("event", gameMap.get(game.getGameType()));
			model.addAttribute("mapKeys", sb.toString());
		} catch (Exception e) {
			logger.error("查看赛事失败！", e);
		}
		return "enjoyrace/form";
	}

	/**
	 * 
	 * <根据id获取赛事><功能具体实现>
	 * 
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午3:51:13
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,id);
		Map<Integer,EnjoyGame> gameMap =  enjoyRaceService.gameVo(game);
		StringBuilder sb = new StringBuilder(game.getGameType());
		for(Map.Entry<Integer,EnjoyGame> entry:gameMap.entrySet()){    
		    sb.append(",").append(entry.getKey());
		} 
	    model.addAttribute("gameMap",gameMap);
		model.addAttribute("event", gameMap.get(game.getGameType()));
		model.addAttribute("mapKeys", sb.toString());
		model.addAttribute("action", "update");
		return "enjoyrace/form";
	}

	/**
	 * 
	 * <显示隐藏赛事><功能具体实现>
	 * 
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:51:03
	 */
	@RequestMapping(value = "show")
	public String show(RedirectAttributes redirectAttributes, Model model, ServletRequest request) {
		try {
			String eventId = request.getParameter("eventId");
			Integer isShow = Integer.valueOf(request.getParameter("isShow").toString());
			if (StringUtils.isNotBlank(eventId)) {
				EnjoyGame games = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, eventId);
				games.setIsShow(isShow);
				enjoyRaceService.updateByPrimaryKeySelective(games, eventId);
			}
		} catch (Exception e) {
			logger.debug("修改失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "修改失败:" + e.getMessage()));
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "修改成功"));
		return "redirect:/enjoyRace/list";
	}

	/**
	 * 
	 * <赛事置顶><功能具体实现>
	 * 
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:50:56
	 */
	@RequestMapping(value = "stick/{id}")
	public String stick(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id) {
		if (CommonUtils.isNotEmpty(id)) {
			EnjoyGame games = new EnjoyGame();
			games.setId(id);
			try {
				games.setStick(Integer.MAX_VALUE);
				enjoyRaceService.updateByPrimaryKeySelective(games,id);
			} catch (Exception e) {
				logger.debug("置顶赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message",
						FlashAttributeUtil.build(false, "置顶赛事失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "赛事置顶成功"));
		return "redirect:/enjoyRace/list";
	}
	
	/**
	 * 
	 * <取消置顶><功能具体实现>
	 * 
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:50:44
	 */
	@RequestMapping(value = "unstick/{id}")
	public String unStick(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id) {
		if (CommonUtils.isNotEmpty(id)) {
			EnjoyGame games = new EnjoyGame();
			games.setId(id);
			try {
				games.setStick(0);
				enjoyRaceService.updateByPrimaryKeySelective(games,id);
			} catch (Exception e) {
				logger.debug("取消置赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message",
						FlashAttributeUtil.build(false, "取消置顶赛事失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "取消赛事置顶成功"));
		return "redirect:/enjoyRace/list";
	}
	
	/**
	 * 
	 * <审核乐享赛><功能具体实现>
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:01:25
	 */
	@RequestMapping(value = "audit", method = RequestMethod.POST)
	@ResponseBody
	public String audit(ServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String id =  request.getParameter("id").toString();
			Integer state = Integer.valueOf(request.getParameter("state").toString());
			List<EnjoyGame> gameList =  enjoyRaceService.gameList(id);
			if(gameList != null){
				for (EnjoyGame enjoyGame : gameList) {
					enjoyGame.setGameState(0);
					enjoyGame.setState(state);
					enjoyGame.setEb(SessionUtil.currentUserId());
					enjoyGame.setEt(new Date());
					enjoyRaceService.updateByPrimaryKeySelective(enjoyGame,id);
				}
			}
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <暂停或开启乐享赛><功能具体实现>
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:01:45
	 */
	@RequestMapping(value = "colse")
	public String colse(ServletRequest request) {
		try {
			String id =  request.getParameter("id").toString();
			Integer state = Integer.valueOf(request.getParameter("state").toString());
			String status =  request.getParameter("status").toString();
			List<EnjoyGameSite> siteList = enjoyRaceService.siteList(id, status);
			for (EnjoyGameSite enjoyGameSite : siteList) {
				enjoyGameSite.setStatus(state);
				enjoyRaceService.updateByPrimaryKeySelective(enjoyGameSite,enjoyGameSite.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "redirect:/enjoyRace/siteList";	
	}
	
	/**
	 * 
	 * <暂停或开启乐享赛><功能具体实现>
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:01:45
	 */
	@RequestMapping(value = "pause")
	public String pause(ServletRequest request) {
		try {
			String id =  request.getParameter("id").toString();
			Integer state = Integer.valueOf(request.getParameter("state").toString());
			if(StringUtils.isNotBlank(id)){
				EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,id);
				if(game != null){
					game.setPauseFlag(state);
					game.setEt(new Date());
					game.setEb(SessionUtil.currentUserId());
					enjoyRaceService.updateByPrimaryKeySelective(game,game.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return "redirect:/enjoyRace/list";	
	}
	
	/**
	 * 
	 * <删除乐享赛><功能具体实现>
	 * 
	 * @param id
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:50:35
	 */
	@RequestMapping(value = "delGames", method = RequestMethod.POST)
	@ResponseBody
	public String delGames(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, id);
				if (game != null) {
					game.setDeleteFlag(1);
					game.setEb(SessionUtil.currentUserId());
					game.setEt(new Date());
					enjoyRaceService.updateByPrimaryKeySelective(game, id);
					enjoyRaceService.updateSiteholkTimes(game.getSiteId(), -1);
					enjoyGameSiteManage.updateEnjoyGameHoldTimes(game.getSiteId());
				}
				result.put(Constants.Result.RESULT, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <参赛成员><功能具体实现>
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月19日 下午3:50:04
	 */
	@RequestMapping(value = "memberList/{id}")
	public String memberList(@PathVariable String id, Model model, HttpServletRequest request) throws Exception {
		logger.info("EnjoyRaceController memberList method execute!");
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyMemberVo> onePage = enjoyRaceService.findMemberList(new PageRequest(page, size), searchParams, id);
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, id);
		model.addAttribute("data", onePage);
		model.addAttribute("id", id);
		model.addAttribute("gameState", game.getGameState());
		if (onePage != null && onePage.getContent().size() > 0) {
			Integer doubleFlag = 0;
			for (EnjoyMemberVo enjoyMemberVo : onePage.getContent()) {
				 if(enjoyMemberVo.getDoubleFlag() == 1){
					 doubleFlag = 1;
				 }
			}
			
			model.addAttribute("doubleFlag", doubleFlag);
		}
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "enjoyrace/memberList";
	}
	


	/**
	 * 
	 * <参赛成员审核><功能具体实现>
	 * 
	 * @param eventId
	 * @param id
	 * @param status
	 * @param redirectAttributes
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:49:28
	 */
	@RequestMapping(value = "updateMemberState/{eventId}/{id}/{status}/{reason}")
	public String updateMemberState(@PathVariable String eventId, @PathVariable String id,
			@PathVariable Integer status,@PathVariable String reason,RedirectAttributes redirectAttributes) {
		logger.info("成员审核 id={} , status={} ", id, status);
		try {
			eventId = enjoyRaceService.updateMember(id, status,reason);
		} catch (Exception e) {
			logger.error("查看用户审核列表时报", e);
		}
		if (StringUtils.isBlank(eventId)) {
			redirectAttributes.addAttribute("error", "订单未支付请支付后再审核");
		}
		return "redirect:/enjoyRace/memberList/" + eventId;
	}

	/**
	 *
	 * <拒绝报名原因><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年5月9日 下午1:40:18
	 */
	@RequestMapping(value = "/refuseSigned_dlg")
	public String refuseSigned(HttpServletRequest request, Model model) {
		String id  = request.getParameter("id").toString();
		String idA = request.getParameter("idA").toString();
		Integer state = Integer.valueOf(request.getParameter("state"));
		model.addAttribute("id",id);
		model.addAttribute("idA",idA);
		model.addAttribute("state",state);
		return "enjoyrace/refuseSigned";
	}

	@RequestMapping(value = "auditMember", method = RequestMethod.POST)
	@ResponseBody
	public String auditMember(HttpServletRequest request) {
		String id  = request.getParameter("id").toString();
		String idA = request.getParameter("idA").toString();
		Integer state = Integer.valueOf(request.getParameter("state"));
		String reason = String.valueOf(request.getParameter("reason"));
		logger.info("auditMember id={}, idA={} , state={},reason={} ",id,idA,state,reason);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "订单未支付请支付后再审核");
			}else if (StringUtils.isBlank(idA)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "memberId不能为空");
			} else  if (state == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "state不能为空");
			}else {
				enjoyRaceService.updateMember(idA,state,reason);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <种子设置><功能具体实现>
	 * 
	 * @param eventId
	 * @param userAId
	 * @param isSeed
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午3:49:43
	 */
	@RequestMapping(value = "mark", method = RequestMethod.POST)
	@ResponseBody
	public String markSeed(String eventId,String userAId, Integer isSeed) {
		logger.info("标记获取取消种子 eventId={} , userAId={},isSeed={} ", eventId, userAId, isSeed);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(eventId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "eventId不能为空");
			} else if (StringUtils.isBlank(userAId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "userAId不能为空");
			} else  if (isSeed == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "isSeed不能为空");
			}else if(isSeed == 1 && !enjoyRaceService.getSeedSetable(eventId)){
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "设置失败，原因种子个数不合法");
			}else {
				enjoyRaceService.markSeed(userAId,eventId,isSeed);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <获取种子列表><功能具体实现>
	 * 
	 * @param eventId
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月23日 下午3:50:00
	 */
	@RequestMapping(value = "reorder/{eventId}")
	public String reorder(@PathVariable String eventId, Model model) throws Exception {
		logger.info("获取种子列表");
		List<EnjoyMemberVo> list = enjoyRaceService.getSeedList(eventId);
		model.addAttribute("list", list);
		model.addAttribute("eventId", eventId);
		if (list != null && list.size() > 0) {
			model.addAttribute("doubleFlag", list.get(0).getDoubleFlag());
		}
		return "enjoyrace/reorder";

	}

	/**
	 * 
	 * <保存种子排位><功能具体实现>
	 * 
	 * @param ids
	 * @return
	 * @author liangsh
	 * @date 2016年2月23日 下午3:54:53
	 */
	@RequestMapping(value = "saveReOrder", method = RequestMethod.POST)
	@ResponseBody
	public String saveReOrder(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArrary = ids.split("[|]");
				for (int i = 1; i <= idsArrary.length; i++) {
					EnjoyMember member = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class, idsArrary[i - 1]);
					member.setSeedNum(i);
					member.setEb(SessionUtil.currentUserId());
					member.setEt(new Date());
					enjoyRaceService.updateByPrimaryKeySelective(member, idsArrary[i - 1]);
				}
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *<获取赛程><功能具体实现>
	 * @param gameFormat
	 * @param gameId
	 * @param type
	 * @return
	 * @date 2016年2月23日 下午6:40:00
     * @throws Exception
     */
	@RequestMapping(value = "/scheduleList")
	@ResponseBody
	public String getSchedule(Integer gameFormat, String gameId, Integer type) throws Exception {
		logger.info("获取赛程");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			GameDraw gamedraw = new GameDraw();
			// 根据赛事id及赛制获取赛程
			// 获取 循环+淘汰赛 二叉树 gameFormat = 1
			if (gameFormat == 1) { // 循环赛+淘汰赛
				// 按场地显示（默认）
				GroupGameVo vo = gamedraw.getScheduleGroupByField(gameId);
				if (vo != null) {
					result.put(Constants.Result.DATA, vo);
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
				}
			}
			// 获取 淘汰赛 二叉树 gameFormat = 2
			else if (gameFormat == 2) {
				GamesScheduleModel treeMap = gamedraw.getTree(gameId);
				if (treeMap != null) {
					result.put(Constants.Result.DATA, treeMap);
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
				}
			}
			// 获取 循环赛 二叉树 gameFormat = 3
			else {
				GroupGameVo groupGameVo = gamedraw.getScheduleGroupByField(gameId);
				if (groupGameVo != null) {
					result.put(Constants.Result.DATA, groupGameVo);
					result.put(Constants.Result.RESULT, true);
				} else {
					result.put(Constants.Result.RESULT, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	@RequestMapping(value = "/scheduleView/{gameId}", method = RequestMethod.GET)
	public String scheduleView(Model model, @PathVariable String gameId) throws Exception {
		model.addAttribute("schedules", enjoyRaceService.getSchedules(gameId));
		model.addAttribute("gameId", gameId);
		return "enjoyrace/scheduleView";
	}

	@RequestMapping(value = "/schedule/{gameId}")
	public String schedule(Model model, @PathVariable String gameId) throws Exception {
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
		String gid = "";
		if(StringUtils.isNotBlank(game.getGid())){
			gid = game.getGid();
			game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gid);
		}else{
			gid = game.getId();
		}
		List<EnjoyGame> games = enjoyRaceService.getSub(gid);
		games.add(game);
		Collections.sort(games, new Comparator<EnjoyGame>() {
			@Override
			public int compare(EnjoyGame arg0, EnjoyGame arg1) {
				if(arg0.getGameType()>arg1.getGameType()){
					return 1;
				}else if(arg0.getGameType()<arg1.getGameType()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		List<GameScheduleVo> vos = new ArrayList<GameScheduleVo>();
		for(EnjoyGame g:games){
			GameScheduleVo schedule = new GameScheduleVo();
			schedule.setGameFormat(String.valueOf(g.getGameFormat()));
			schedule.setGameFormat1(String.valueOf(g.getGameFormat2()));
			schedule.setGameType(g.getGameType());
			schedule.setGameId(g.getId());
			vos.add(schedule);
		}
		model.addAttribute("schedules", vos);
		return "enjoyrace/schedule";
	}
	
	/**
	 * 设置赛程
	 * @param gameSchedules
	 * @return
	 */
	@RequestMapping(value = "setGameSchedule", method = RequestMethod.POST)
	public @ResponseBody String setGameSchedule(@RequestBody List<GameScheduleVo> gameSchedules){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			result = enjoyRaceService.setGameSchedule(gameSchedules);
		} catch (Exception e) {
			logger.debug("设置赛程失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "服务器异常，请稍后再试！");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 保存循环赛程
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "saveSchedule/{gameId}", method = RequestMethod.POST)
	public @ResponseBody String saveSchedule(@PathVariable String gameId,@RequestBody Map<String,Object> gameSchedule){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			enjoyRaceService.saveSchedule(gameId, gameSchedule);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			logger.debug("保存赛程失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "系统异常，请稍后再试！");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <检查是否可生成赛程><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月14日 下午2:16:35
	 */
	@RequestMapping(value = "checkSignedCount", method = RequestMethod.POST)
	@ResponseBody
	public String checkSignedCount(Model model, HttpServletRequest request) throws Exception {
		logger.info("开始检查赛事报名情况");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String gameId = request.getParameter("gameId");
			if(StringUtils.isBlank(gameId)){
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "生成失败，赛事ID不可空");
			}else{
				List<EnjoyGame> gameList = enjoyRaceService.gameList(gameId);
				boolean isAllow = true;
				for (EnjoyGame enjoyGame : gameList) {
					if(StringUtils.isNotBlank(enjoyGame.getExpiryDate())){
						Date expiryDate = DateUtil.parseStr(enjoyGame.getExpiryDate(),new Date());
						Date nowDate = DateUtil.parseStr(DateUtil.nowDateTimeStringHHmm(),new Date());
						// -1 ： 开始日期小于结束日期 0 ： 开始日期等于结束日期 1 ： 开始日期大于结束日期
						int c = DateUtil.compareDate(expiryDate,nowDate);
						if(c == 1){
							isAllow = false;
							break;
						}
					}
				}
				if(isAllow){
					 if(enjoyRaceService.checkSignedCount(gameList)){
						    result.put(Constants.Result.DATA, "");
							result.put(Constants.Result.RESULT, true);
						}else{
							result.put(Constants.Result.RESULT, false);
							result.put(Constants.Result.REASON,"赛事每个项目报名人数不能少于8人");
						}
				}else{
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON,"报名未截止");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
		
	}

	/**
	 * 
	 * <生成赛程><功能具体实现>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月24日 下午4:56:49
	 */
	@RequestMapping(value = "createSche", method = RequestMethod.POST)
	@ResponseBody
	public String createSchedule(Model model, HttpServletRequest request) throws Exception {
		logger.info("开始生成赛程");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String eventId = request.getParameter("eventId");
			Integer gameFormat = Integer.valueOf(request.getParameter("gameFormat").toString());
			Integer snum = Integer.valueOf(request.getParameter("sNum").toString());
			String gnumStr = request.getParameter("gNum");
			String typeStr = request.getParameter("type");

			Integer gnum = null;
			if (StringUtils.isNotBlank(gnumStr)) {
				gnum = Integer.valueOf(gnumStr);
			}
			Integer type = 1;
			if (StringUtils.isNotBlank(typeStr)) {
				type = Integer.valueOf(typeStr);
			}
			//先更新赛事gameFormat
			EnjoyGame enjoyGame = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,eventId);
			enjoyGame.setGameFormat(gameFormat);
			enjoyRaceService.updateByPrimaryKeySelective(enjoyGame,eventId);
			if (gameFormat == 1) {// 循环赛+淘汰赛
				if (type == 2) {
					String tip = enjoyRaceService.checkOutable(eventId);
					if(StringUtils.isNotBlank(tip)){
						result.put(Constants.Result.DATA, tip);
						result.put(Constants.Result.RESULT, false);
						return MyGson.getInstance().toJson(result);
					}
					Map<String,Map<String,String>> drawResult = new HashMap<String, Map<String,String>>();
					String drawJson = request.getParameter("drawResult");
					if(StringUtils.isNotBlank(drawJson)){
						Type type_ = new TypeToken<Map<String,Map<String,String>>>() {}.getType();
						drawResult = MyGson.getInstance().fromJson(drawJson, type_);
					}
					GamesScheduleModel treeMap = enjoyRaceService.createSchedule12(gameFormat, snum, gnum, eventId,drawResult);
					result.put(Constants.Result.DATA, treeMap);
					result.put(Constants.Result.RESULT, true);
					logger.debug(treeMap.toString());
				} else {
					GroupGameVo groupGameVo = enjoyRaceService.createSchedule1(gameFormat, snum, gnum, eventId);
					result.put(Constants.Result.DATA, groupGameVo);
					result.put(Constants.Result.RESULT, true);
					logger.debug(groupGameVo.toString());
				}
			} else if (gameFormat == 2) { // 淘汰赛
				GamesScheduleModel treeMap = enjoyRaceService.createSchedule2(gameFormat, snum, eventId);
				result.put(Constants.Result.DATA, treeMap);
				result.put(Constants.Result.RESULT, true);
				logger.debug(treeMap.toString());
			} else { // 循环赛
				GroupGameVo map = enjoyRaceService.createSchedule3(gameFormat, snum, gnum, eventId);
				result.put(Constants.Result.DATA, map);
				result.put(Constants.Result.RESULT, true);
				logger.debug(map.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <保存赛程><功能具体实现>
	 * @param data eventram groupGameVo
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年2月24日 下午5:52:56
	 */
	@RequestMapping(value = "saveSche1")
	@ResponseBody
	public String saveSchedule1(@RequestBody GroupGameVo data) throws Exception {
		logger.info("开始保存赛程");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			enjoyRaceService.saveSchedule1(data);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <保存赛程2><功能具体实现>
	 * @param data
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月14日 下午3:35:44
	 */
	@RequestMapping(value = "saveSche2")
	@ResponseBody
	public String saveSchedule2(@RequestBody GamesScheduleModel data) throws Exception {
		logger.info("开始保存赛程");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			enjoyRaceService.saveSchedule2(data);
			result.put(Constants.Result.RESULT, true);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <保存赛程3><功能具体实现>
	 * @param data
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月14日 下午3:36:12
	 */
	@RequestMapping(value = "saveSche3")
	@ResponseBody
	public String saveSchedule3(@RequestBody GroupGameVo data) throws Exception {
		logger.info("开始保存赛程");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			enjoyRaceService.saveSchedule3(data);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <更新赛事阶段状态><功能具体实现>
	 * @param gameId
	 * @param state
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月27日 下午3:04:19
	 */
	@RequestMapping(value = "updateGameSate")
	@ResponseBody
	public String updateGameSate(String gameId, Integer state) throws Exception {
		logger.info("保存赛程成功后跟新赛事阶段状态");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
			game.setGameState(state);
			game.setEb(SessionUtil.currentUserId());
			game.setEt(new Date());
			enjoyRaceService.updateByPrimaryKeySelective(game,gameId);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 *
	 * <获取赛程分组><功能具体实现>
	 *
	 * @create：2016年2月25日 下午4:32:01
	 * @author： sl
	 * @param model
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "score")
	public String score(Model model, String gameId) {
		try {
			EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
			if(StringUtils.isNotBlank(game.getGid())){
				EnjoyGame tmp = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,game.getGid());
				game.setGameLevel(tmp.getGameLevel());
			}
			// GroupGameVo groupGameVo =
			// gameDraw.getScheduleGroupByGroup(gameId);
			GameDraw gamedraw = new GameDraw();
			GroupGameVo groupGameVo = gamedraw.getScheduleGroupByField(gameId);
			model.addAttribute("gameGroup", groupGameVo);
			// 分组
			model.addAttribute("groupView", groupGameVo.getGroupsView());
			// 对战
			model.addAttribute("fields", groupGameVo.getFields());
			model.addAttribute("gameId", gameId);
			model.addAttribute("gnum", groupGameVo.getGroupsView().size());
			model.addAttribute("snum", groupGameVo.getFields().size());
			model.addAttribute("gameState", game.getGameState());
			model.addAttribute("gameFormat", game.getGameFormat());
			model.addAttribute("gameFormat2", game.getGameFormat2());
			model.addAttribute("gameType", game.getGameType());
			model.addAttribute("gameLevel", game.getGameLevel());
			model.addAttribute("isScoreNotice", game.getIsScoreNotice());
			model.addAttribute("groupSchedules", MyGson.getInstance().toJson(groupGameVo.getGroupScheduleList()));

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("获取赛程分组：{}", e.getMessage());
		}
		return "enjoyrace/score";
	}
	
	/**
	 * 
	 * <获取对战信息><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年3月1日 上午10:55:54
	 */
	@RequestMapping(value = "/entryScore_dlg")
	public String entryScore(HttpServletRequest request, Model model) {
		try {
			String id = request.getParameter("id");
			String lId = request.getParameter("lid");
			String rId = request.getParameter("rid");
			if(StringUtils.isNotBlank(lId) && StringUtils.isNotBlank(rId)){
				EnjoyMemberVo vo =  enjoyRaceService.getEliDetail(id,lId,rId);
				model.addAttribute("socreEli", vo);
				model.addAttribute("gameFormat2", request.getParameter("gameFormat2"));
			}
		} catch (Exception e) {
			logger.error("比分录入失败！", e);
		}
		return "enjoyrace/entryScore";
	}
	
	/**
	 * 
	 * <保存比分><功能具体实现>
	 * @param elimination
	 * @return
	 * @author liangsh
	 * @date 2016年4月27日 下午3:03:57
	 */
	@RequestMapping(value="saveScoreEli")
	@ResponseBody
	public String saveScoreEli(EnjoyElimination elimination){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(elimination != null){
				enjoyRaceService.saveScoreEli(elimination);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("比分保存", e.getMessage());
			result.put(Constants.Result.RESULT, false);
		}
		return MyGson.getInstance().toJson(result);
	}
	

	/**
	 * 
	 * <查看场馆是否已经超过举办次数><功能具体实现>
	 * @param statiumId
	 * @param siteId
	 * @return
	 * @author liangsh
	 * @date 2016年4月27日 下午3:03:25
	 */
	@RequestMapping(value="chcekHoldTimes")
	@ResponseBody
	public String chcekHoldTimes(String statiumId,String siteId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(siteId)){
				logger.debug("siteId={}",siteId);
				result.put(Constants.Result.RESULT, enjoyRaceService.chcekHoldTimes(siteId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("场馆发布赛事次数校验", e.getMessage());
			result.put(Constants.Result.RESULT, false);
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <发布成绩公告><功能具体实现>
	 * @param eventId
	 * @param gameType
	 * @param gameLevel
	 * @return
	 * @author liangsh
	 * @date 2016年4月11日 下午7:00:01
	 */
	@RequestMapping(value = "notice")
	@ResponseBody
	public String notice(String eventId,Integer gameType,Integer gameLevel){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(eventId)){
				enjoyRaceService.notice(eventId,gameType,gameLevel);
				result.put(Constants.Result.RESULT,true) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("场馆发布赛事次数校验", e.getMessage());
			result.put(Constants.Result.DATA, e.getMessage());
			result.put(Constants.Result.RESULT, false);
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <比分保存><功能具体实现>
	 *
	 * @create：2016年2月26日 上午10:35:55
	 * @author： sl
	 * @param schedule
	 * @return
	 */
	@RequestMapping(value = "scoreSave")
	@ResponseBody
	public String scoreSave(EnjoyGroupSchedule schedule) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			enjoyRaceService.saveGroupScore(schedule);
			// 比分排名
			GameDraw gameDraw = new GameDraw();
			GroupGameVo groupGameVo = gameDraw.getScheduleGroupByField(schedule.getGameId());
			result.put(Constants.Result.DATA, groupGameVo.getGroupsView());
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("比分保存", e.getMessage());
			result.put(Constants.Result.RESULT, false);
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <赛事通知><功能具体实现>
	 *
	 * @create：2016年2月29日 下午3:54:49
	 * @author： sl
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "eventMsg")
	public String eventMsg(String gameId,Model model,HttpServletRequest request){
		try {
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			// 根据id获取乐享赛通知
			Page<PushLog> pushPage = enjoyRaceService.eventMsg(new PageRequest(page, size), gameId);
			model.addAttribute("data", pushPage);
			model.addAttribute("gameId", gameId);
		} catch (Exception e) {
			logger.debug("赛事通知：{}", e.getMessage());
		}
		return "enjoyrace/eventMsg";
	}
	
	/**
	 * 
	 * <赛事通知发布初始化><功能具体实现>
	 *
	 * @create：2016年2月29日 下午4:35:11
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = "msgForm")
	public String msgForm(Model model, String gameId){
		model.addAttribute("gameId", gameId);
		return "enjoyrace/msgForm";
	}
	
	/**
	 * 
	 * <赛事通知发送><功能具体实现>
	 *
	 * @create：2016年3月1日 上午10:30:25
	 * @author： sl
	 * @param eventMsgVo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "sendMsg")
	public String sendMsg(EventMsgVo eventMsgVo, RedirectAttributes redirectAttributes){
		try {
			String gameId = eventMsgVo.getMsgId();
			String msg = eventMsgVo.getMsg();
			// 推送
			pushPacket.sendPush("", com.lc.zy.common.Constants.pushMsgType.PUSH_TYPE_EVENT_TEXT, gameId,
					msg, SessionUtil.currentUserId(),
					com.lc.zy.common.Constants.isPush.PUSH_TYPE_UNLIMIT, "");
		} catch (Exception e) {
			logger.debug("赛事通知发送：{}", e.getMessage());
		}
		return "redirect:/enjoyRace/eventMsg";
	}
	
	/**
	 * 
	 * <站点列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月30日 下午6:56:56
	 */
	@RequestMapping(value="siteList")
	public String siteList(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyGameSiteVo> onePage = enjoyRaceService.siteList(new PageRequest(page, size), searchParams);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "enjoyrace/siteList";
	}
	
	/**
	 * 
	 * <新增站点><功能具体实现>
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月30日 下午7:25:46
	 */
	@RequestMapping(value= "createSite",method = RequestMethod.GET)
	public String createSite(Model model) throws Exception{
		return "enjoyrace/siteForm";
	}
	
	/**
	 * 
	 * <保存站点><功能具体实现>
	 * @param gameSiteVo
	 * @param redirectAttributes
	 * @return
	 * @author liangsh
	 * @date 2016年3月30日 下午7:32:53
	 */
	@RequestMapping(value = "saveSite", method = RequestMethod.POST)
	public String saveSite(EnjoyGameSiteVo gameSiteVo, RedirectAttributes redirectAttributes) {
		try {
			if(gameSiteVo != null){
				EnjoyGameSite site = new EnjoyGameSite();
				BeanUtils.copyProperties(gameSiteVo,site);
				site.setEb(SessionUtil.currentUserId());
				site.setEt(new Date());
				if(StringUtils.isBlank(gameSiteVo.getId())){
					site.setId(UUID.get());
					site.setCb(SessionUtil.currentUserId());
					site.setCt(new Date());
					Calendar a=Calendar.getInstance();
					site.setYear(String.valueOf(a.get(Calendar.YEAR)));
					site.setStatus(0);
					if (StringUtils.isNotEmpty(gameSiteVo.getCode())) {
						Map<String, String> areaMap = Zonemap.split(gameSiteVo.getCode());
						site.setProvince(areaMap.get("province"));
						site.setCity(areaMap.get("city"));
					}
					enjoyRaceService.insertSelective(site, site.getId());
				}else{
					enjoyRaceService.updateByPrimaryKeySelective(site, site.getId());
				}
			}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "成功!"));
		} catch (Exception e) {    
			logger.debug("失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "失败！"));
		}
		return "redirect:/enjoyRace/siteList";
	}

	/**
	 * 
	 * <站点详情><功能具体实现>
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年4月6日 下午3:27:41
	 */
	@RequestMapping(value = "siteView/{id}")
	public String siteView(@PathVariable String id, Model model) {
		try {
			EnjoyGameSite site = enjoyRaceService.selectByPrimaryKey(EnjoyGameSite.class,id);
			EnjoyGameSiteVo siteVo = new EnjoyGameSiteVo();
			BeanUtils.copyProperties(site,siteVo);
			StatiumDetail statium = enjoyRaceService.selectByPrimaryKey(StatiumDetail.class,siteVo.getStatiumId());
			if(statium != null){
				siteVo.setStatiumName(statium.getName());
			}
			model.addAttribute("info", siteVo);
		} catch (Exception e) {
			logger.error("查看赛事失败！", e);
		}
		return "enjoyrace/siteForm";
	}
	
	/**
	 * 
	 * <奖金管理列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月11日 下午7:38:38
	 */
	@RequestMapping(value = "bonusList")
	public String bonusList(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyGameVo> onePage = enjoyRaceService.bonusList(new PageRequest(page, size), searchParams,true);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "enjoyrace/bonusList";
	}
	
	/**
	 * 
	 * <奖金管理成员列表><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年4月11日 下午7:43:41
	 */
	@RequestMapping(value = "bonusManage")
	public String bonusManage(HttpServletRequest request,Model model) throws Exception {
		logger.info("EnjoyRaceController bonusManage method execute!");
		String gameId = request.getParameter("gameId");
		Integer gameLevel = Integer.valueOf(request.getParameter("gameLevel"));
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyMemberVo> onePage = enjoyRaceService.bonusMemberList(new PageRequest(page, size), searchParams, gameId,gameLevel);
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
		model.addAttribute("data", onePage);
		model.addAttribute("id", gameId);
		model.addAttribute("gameState", game.getGameState());
		if (onePage != null && onePage.getContent().size() > 0) {
			model.addAttribute("doubleFlag", onePage.getContent().get(0).getDoubleFlag());
		}
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "enjoyrace/bonusMemberList";
	}
	
	/**
	 * 
	 * <校验成绩公告是否已满48小时><功能具体实现>
	 * @param gameId
	 * @return
	 * @author liangsh
	 * @date 2016年4月27日 下午2:38:32
	 */
	@RequestMapping(value = "checkTime", method = RequestMethod.POST)
	@ResponseBody
	public String checkTime(String gameId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(gameId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,gameId);
				//
				if(DateUtil.compareDate(game.getEt(),DateUtils.minusDaysToday(2)) == 1){
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "成绩公示不满48小时不能审核");
				}else{
					result.put(Constants.Result.RESULT, true);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <修改奖金><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年4月27日 下午3:59:27
	 */
	@RequestMapping(value = "/udpateBonus_dlg")
	public String udpateBonus(HttpServletRequest request, Model model) {
		try {
			String AId = request.getParameter("AId");
			String BId = request.getParameter("BId");
			String nameA = request.getParameter("nameA");
			String nameB = request.getParameter("nameB");
			String bonus = request.getParameter("bonus");
			if(StringUtils.isNotBlank(AId) && StringUtils.isNotBlank(nameA)){
				model.addAttribute("AId", AId);
				model.addAttribute("BId", BId);
				model.addAttribute("nameA", nameA);
				model.addAttribute("nameB", nameB);
				model.addAttribute("bonus", bonus);
			}
		} catch (Exception e) {
			logger.error("修改奖金失败！", e);
		}
		return "enjoyrace/updateBonus";
	}
	
	/**
	 * 
	 * <保存修改的奖金><功能具体实现>
	 * @param vo
	 * @return
	 * @author liangsh
	 * @date 2016年4月27日 下午4:54:51
	 */
	@RequestMapping(value = "saveBonus", method = RequestMethod.POST)
	@ResponseBody
	public String saveBonus(EnjoyMemberVo vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (vo == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "获取信息失败");
			} else {
				Integer bonus = vo.getBonus();
				if(StringUtils.isNotBlank(vo.getIdB())){
					EnjoyMember memberB = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,vo.getIdB());
					memberB.setBonus(bonus);
					memberB.setEt(new Date());
					memberB.setEb(SessionUtil.currentUserId());
					enjoyRaceService.updateByPrimaryKeySelective(memberB,vo.getIdB());
				}
				EnjoyMember memberA = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,vo.getIdA());
				memberA.setBonus(bonus);
				memberA.setEt(new Date());
				memberA.setEb(SessionUtil.currentUserId());
				enjoyRaceService.updateByPrimaryKeySelective(memberA,vo.getIdA());
				result.put(Constants.Result.RESULT, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <运营审核奖金发放信息><功能具体实现>
	 * @param memberAId
	 * @return
	 * @author liangsh
	 * @date 2016年4月26日 下午6:04:07
	 */
	@RequestMapping(value = "auditBonus", method = RequestMethod.POST)
	@ResponseBody
	public String auditBonus(String memberAId) {
		logger.info("运营审核奖金memberAId={}  ", memberAId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(memberAId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "memberId不能为空");
			}else{
				enjoyRaceService.auditBonus(memberAId);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
	
	
	/**
	 * 
	 * <财务确定发放奖金至用户账户><功能具体实现>
	 * @param memberAId
	 * @param gameId
	 * @return
	 * @author liangsh
	 * @date 2016年4月14日 上午10:34:46
	 */
	@RequestMapping(value = "confirmBonus", method = RequestMethod.POST)
	@ResponseBody
	public String confirmBonus(String memberAId,String gameId) {
		logger.info("发放奖金memberAId={}  ", memberAId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(memberAId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "memberId不能为空");
			}else{
				enjoyRaceService.confirmBonus(memberAId,gameId);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * <积分汇总><>
	 * @param id
	 * @return
     */
	@RequestMapping(value = "collectIntegral/{id}")
	@ResponseBody
	public String collectIntegral(@PathVariable String id){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			enjoyRaceService.collectIntegral(id);
			result.put(Constants.Result.RESULT,true);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			result.put(Constants.Result.RESULT,false);
			result.put(Constants.Result.REASON,e.getMessage());
		}

		return MyGson.getInstance().toJson(result);

	}


	@RequestMapping(value = "exportScore/{id}")
	public String exportScore(@PathVariable String id, Model model,HttpServletRequest request,HttpServletResponse response){
		BufferedOutputStream bufferedOutPut = null;
		try{
			Map<String,List<EnjoyGroupSchedule>> scoreByField = enjoyRaceService.getScoreByField(id);

			String uuid = UUID.get();
			String fileName = uuid+".xls";
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);// 指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			OutputStream output = response.getOutputStream();
			bufferedOutPut = new BufferedOutputStream(output);

			HSSFWorkbook wb = new HSSFWorkbook();

			// 创建单元格样式
			HSSFCellStyle cellStyleTitle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyleTitle.setWrapText(true);
			// ------------------------------------------------------------------
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			// ------------------------------------------------------------------
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			cellStyleTitle.setFont(font);

			for(Map.Entry<String,List<EnjoyGroupSchedule>> entry:scoreByField.entrySet()){
				String id_title = "ID";
				String name_title = "选手";
				String score_title = "比分";
				String score_title1 = "比分";
				String name_title1 = "选手";
				String seven_score = "抢七分";
				HSSFSheet sheet = wb.createSheet("场地"+entry.getKey());

				HSSFRow row1 = sheet.createRow(0);
				HSSFCell cell1 = row1.createCell(0);
				//第一行第1列
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(id_title));

				//第一行第2列
				cell1 = row1.createCell(1);
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(name_title));

				//第一行第3列
				cell1 = row1.createCell(2);
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(score_title));

				//第一行第5列
				cell1 = row1.createCell(3);
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(name_title1));
				//第一行第4列
				cell1 = row1.createCell(4);
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(score_title1));
				
				//第一行第4列
				cell1 = row1.createCell(5);
				cell1.setCellStyle(cellStyleTitle);
				cell1.setCellValue(new HSSFRichTextString(seven_score));


				int i = 1;
				for(EnjoyGroupSchedule schedule : entry.getValue()){
					row1 = sheet.createRow(i++);

					cell1 = row1.createCell(0);
					cell1.setCellStyle(cellStyleTitle);
					cell1.setCellValue(new HSSFRichTextString(schedule.getId()));

					cell1 = row1.createCell(1);
					cell1.setCellStyle(cellStyleTitle);
					String cli1 = schedule.getClasli1();
					EnjoyMember member = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,cli1);
					String p1 = member.getPlayerId();
					GamesPlayer pl = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class,p1);

					cell1.setCellValue(new HSSFRichTextString(pl.getName()));

					cell1 = row1.createCell(2);
					cell1.setCellStyle(cellStyleTitle);
					cell1.setCellValue(new HSSFRichTextString(schedule.getScore1()==null?"":schedule.getScore1()+""));


					cell1 = row1.createCell(3);
					cell1.setCellStyle(cellStyleTitle);
					String cli2 = schedule.getClasli2();
					EnjoyMember member1 = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,cli2);
					String p2 = member1.getPlayerId();
					GamesPlayer pl1 = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class,p2);

					cell1.setCellValue(new HSSFRichTextString(pl1.getName()));

					cell1 = row1.createCell(4);
					cell1.setCellStyle(cellStyleTitle);
					cell1.setCellValue(new HSSFRichTextString(schedule.getScore2()==null?"":schedule.getScore2()+""));
					
					if(schedule.getSmallscore()!=null){
						cell1 = row1.createCell(5);
						cell1.setCellStyle(cellStyleTitle);
						cell1.setCellValue(new HSSFRichTextString(schedule.getSmallscore()+""));
					}
				}
			}
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			return null;
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			if(bufferedOutPut!=null){
				try {
					bufferedOutPut.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}

			}
		}

	}

	@RequestMapping("/uploader")
	@ResponseBody
	public String uploadFile(String gameId,boolean pathFlag,HttpServletRequest request,HttpServletResponse response)throws Exception {
		logger.debug("修改人：,{}",SessionUtil.currentUsername());
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class,gameId);
		int gameType = game.getGameFormat2();
		String availableScoreFor4 = "4:0,4:1,4:2,4:3,0:4,1:4,2:4,3:4";
		String availableScoreFor6 = "6:0,6:1,6:2,6:3,6:4,0:6,1:6,2:6,3:6,4:6,7:5,5:7,7:6,6:7";
		String coreRules = "";
		if(gameType==0){
			coreRules = availableScoreFor4;
		}else{
			coreRules = availableScoreFor6;
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject json = new JSONObject();
		json.put("success", true);
		try{
			for(Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();){
				String fileName = it.next();
				logger.debug("-------------->{}",fileName);
				MultipartFile file = multipartRequest.getFile(fileName);
				logger.debug("-----------------------------------");
				logger.debug("file_key : "+fileName);
				logger.debug("name : "+file.getName());
				logger.debug("size : "+file.getSize());
				logger.debug("contentType : "+file.getContentType());
				logger.debug("originalFilename : "+file.getOriginalFilename());
				logger.debug("-----------------------------------");
				HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
				Map<String,String> result = new HashMap<String,String>();
				List<EnjoyGroupSchedule> saveing = new ArrayList<EnjoyGroupSchedule>();
				boolean flag_ = false;
				for (int i=0;i<wb.getNumberOfSheets();i++){
					boolean flag = false;
					HSSFSheet sheet = wb.getSheetAt(i);
					for(int j=1;j<=sheet.getLastRowNum();j++){
						HSSFRow row = sheet.getRow(j);
						String id = row.getCell(0).getStringCellValue();
						if(StringUtils.isBlank(id)){
							break;
						}
						String score1 = row.getCell(2).getCellType()==0?String.valueOf((int)(row.getCell(2).getNumericCellValue())):row.getCell(2).getStringCellValue();
						String score2 = row.getCell(4).getCellType()==0?String.valueOf((int)(row.getCell(4).getNumericCellValue())):row.getCell(4).getStringCellValue();
						if(coreRules.indexOf(score1+":"+score2)==-1){
							flag = true;
							result.put("result",row.getCell(1).getStringCellValue()+"VS"+row.getCell(3).getStringCellValue()+"比分录入不合法");
							break;
						}
						EnjoyGroupSchedule schedule = new EnjoyGroupSchedule();
						if((score1+":"+score2).equals("4:3")||(score1+":"+score2).equals("3:4")||(score1+":"+score2).equals("7:6")||(score1+":"+score2).equals("6:7")){
							String smallScore = row.getCell(5)==null?"":row.getCell(5).getCellType()==0?String.valueOf((int)(row.getCell(5).getNumericCellValue())):row.getCell(5).getStringCellValue();
							if(StringUtils.isBlank(smallScore)){
								flag = true;
								result.put("result",row.getCell(1).getStringCellValue()+"VS"+row.getCell(3).getStringCellValue()+"抢七分未录入");
								break;
							}
							Pattern p_ = Pattern.compile("^(-)?\\d{1,10}$");  
                            Matcher matcher_ = p_.matcher(smallScore);  
                            if (!matcher_.matches()){  
                            	flag = true;
								result.put("result",row.getCell(1).getStringCellValue()+"VS"+row.getCell(3).getStringCellValue()+"抢七分录入不合法");
								break;  
                            }
							
							schedule.setSmallscore(Integer.parseInt(smallScore,10));
						}
						schedule.setId(id);
						schedule.setScore1(Integer.parseInt(score1,10));
						schedule.setScore2(Integer.parseInt(score2,10));
						saveing.add(schedule);
					}
					if(flag){
						flag_ = true;
						break;
					}
				}

				if(!flag_){
					for(EnjoyGroupSchedule schedule:saveing){
						enjoyRaceService.updateByPrimaryKeySelective(schedule,schedule.getId());
					}
				}else{
					json.put("success", false);
					json.put("result",result);
				}

			}
		}catch(Exception e){
			logger.error("",e);
			json.put("success", false);
		}
		String res = json.toString();
		logger.debug(res);
		return res;
	}

	
	/**
	 * 
	 * <查看赛事><功能具体实现>
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * 
	 * @date 2016年2月23日 下午3:51:39
	 */
	@RequestMapping(value = "export/{id}")
	public String export(@PathVariable String id, Model model,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<ExportPlayer> players = enjoyRaceService.getMembers(id);
			EnjoyGame game = enjoyRaceService.getGameById(id);
			String gameLevel = "";
			String gameType = "";
			if(game != null){
				switch (game.getGameType()) {
					case 1:
						gameType = "男单";
						break;
					case 2:
						gameType = "女单";
						break;
					case 3:
						gameType = "男双";
						break;
					case 4:
						gameType = "女双";
						break;
					case 5:
						gameType = "混双";
						break;
					case 6:
						gameType = "混单";
						break;
					case 7:
						gameType = "无性别限制双打";
						break;
				}

				switch (game.getGameLevel()) {
					case 1:
						gameLevel = "乐享一级";
						break;
					case 2:
						gameLevel = "乐享二级";
						break;
					case 3:
						gameLevel = "乐享三级";
						break;
					case 4:
						gameLevel = "乐享四级";
						break;
				}

			}
			if(CollectionUtils.isNotEmpty(players)){
				
				String path = WebUtils.getServletContext().getRealPath("/static/template/");
				boolean hasTeammate = players.get(0).isHasTeammate();
				if(hasTeammate){
					path += "/sd.xls";
				}else{
					path += "/dd.xls";
				}
				InputStream is = new FileInputStream(path);
				String fileName = FilenameUtils.getName(path);  
		        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");  
		        response.reset();  
		        response.setHeader("Content-Disposition", "attachment;filename="  
		                + fileName);// 指定下载的文件名  
		        response.setContentType("application/vnd.ms-excel");  
		        response.setHeader("Pragma", "no-cache");  
		        response.setHeader("Cache-Control", "no-cache");  
		        response.setDateHeader("Expires", 0);  
		        OutputStream output = response.getOutputStream();
		        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
		        HSSFWorkbook wb = new HSSFWorkbook(is);
		        HSSFSheet sheet = wb.getSheetAt(0);
		        HSSFCellStyle cellStyle = wb.createCellStyle();  
		        // 指定单元格居中对齐  
		        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		        // 指定单元格垂直居中对齐  
		        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		        // 指定当单元格内容显示不下时自动换行  
		        cellStyle.setWrapText(true);
		        int i = 1;
		        HSSFCell cell =null;
				for(ExportPlayer player:players){
					//定义第二行
			        HSSFRow row = sheet.createRow(i);
			        
			        cell = row.createCell(0);  
		            cell.setCellStyle(cellStyle);  
		            cell.setCellValue(new HSSFRichTextString(player.getName() + ""));
		            
		            cell = row.createCell(1);  
		            cell.setCellStyle(cellStyle);  
		            cell.setCellValue(new HSSFRichTextString(player.getGender() + ""));
		            
		            cell = row.createCell(2);  
		            cell.setCellStyle(cellStyle);
		            cell.setCellValue(new HSSFRichTextString("身份证"));
		            
		            cell = row.createCell(3);  
		            cell.setCellStyle(cellStyle);  
		            cell.setCellValue(new HSSFRichTextString(player.getCardNo().toUpperCase() + ""));
		            
		            if(hasTeammate){
		            	cell = row.createCell(4);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getName1()+""));
			            
			            cell = row.createCell(5);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getGender1()+""));
			            
			            cell = row.createCell(6);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString("身份证"));
			            
			            cell = row.createCell(7);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getCardNo1().toUpperCase()+""));
			            
			            cell = row.createCell(8);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(gameLevel));
			            
			            cell = row.createCell(9);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(gameType));
			            
			            cell = row.createCell(10);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getPhone()+""));
			            
			            cell = row.createCell(11);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getPhone1()+""));

						cell = row.createCell(12);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(new HSSFRichTextString(player.getSeedNum()+""));
		            }else{
		            	cell = row.createCell(4);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(gameLevel));
			            cell = row.createCell(5);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(gameType));
			            cell = row.createCell(6);  
			            cell.setCellStyle(cellStyle);  
			            cell.setCellValue(new HSSFRichTextString(player.getPhone()+""));
						cell = row.createCell(7);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(new HSSFRichTextString(player.getSeedNum()+""));
		            }
		            i++;
				}
				try {  
		            bufferedOutPut.flush();  
		            wb.write(bufferedOutPut);
		            return null;
		        }
		        catch (IOException e) { 
		            logger.error(e.getMessage());
		            return null;
		        } finally {
		        	bufferedOutPut.close();
		        }
			}
			
		} catch (Exception e) {
			logger.error("查看赛事失败！", e);
		}
		return null;
	}
	
	/**
	 * 
	 * <修改乐享赛报名人信息><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年5月5日 下午6:45:48
	 */
	@RequestMapping(value = "/updatePlayer_dlg")
	public String updatePlayer(HttpServletRequest request, Model model){
		try {
			String id = request.getParameter("id");
			String gameId = request.getParameter("gameId");
			EnjoyMember member =  enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,id);
			GamesPlayer player = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class, member.getPlayerId());
			SsoUser userA = enjoyRaceService.selectByPrimaryKey(SsoUser.class,player.getUserId());
			EnjoyMemberVo vo = new EnjoyMemberVo();
			vo.setIdA(player.getId());
			vo.setNameA(player.getName());
			vo.setCardNoA(player.getCardNo());
			vo.setPhoneA(userA.getPhone());
			vo.setSexA(player.getSex());
			vo.setCtaIntegral(String.valueOf(member.getCtaIntegral()) == "null"?"0":String.valueOf(member.getCtaIntegral()));
			vo.setEliId(gameId);
			if(member.getDoubleFlag() == 1){
				GamesPlayer player2 = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class,member.getTeammate());
				vo.setIdB(player2.getId());
				vo.setNameB(player2.getName());
				vo.setCardNoB(player2.getCardNo());
				vo.setSexB(player2.getSex());
				SsoUser userB = enjoyRaceService.selectByPrimaryKey(SsoUser.class,player2.getUserId());
				if(userB != null){
					vo.setPhoneB(userB.getPhone());
				}
			}
			model.addAttribute("player", vo);
			model.addAttribute("doubleFlag", member.getDoubleFlag());
		} catch (Exception e) {
			logger.error("修改人员信息失败！", e);
		}
		return "enjoyrace/updatePlayer";
	}
	
	/**
	 * 
	 * <保存报名人信息><功能具体实现>
	 * @param vo
	 * @return
	 * @author liangsh
	 * @date 2016年5月7日 下午6:31:53
	 */
	@RequestMapping(value = "savePlayer", method = RequestMethod.POST)
	@ResponseBody
	public String savePlayer(EnjoyMemberVo vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (vo == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "获取信息失败");
			} else {
				enjoyRaceService.updatePlayer(vo);
				result.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <拒绝奖金发放原因><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年5月9日 下午1:40:18
	 */
	@RequestMapping(value = "/refuse_dlg")
	public String refuse(HttpServletRequest request, Model model) {
		try {
			String mAId = request.getParameter("mAId");
			String mBId = request.getParameter("mBId");
			model.addAttribute("mAId", mAId);
			model.addAttribute("mBId", mBId);
		} catch (Exception e) {
			logger.error("拒绝失败！", e);
		}
		return "enjoyrace/refuse";
	}
	
	/**
	 * 
	 * <保存拒绝原因><功能具体实现>
	 * @param mAid
	 * @param mBid
	 * @param reason
	 * @return
	 * @author liangsh
	 * @date 2016年5月9日 下午2:16:21
	 */
	@RequestMapping(value = "saveRefuse", method = RequestMethod.POST)
	@ResponseBody
	public String saveRefuse(String mAid,String mBid,String reason) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (reason == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "获取拒绝原因失败");
			} else {
				if(StringUtils.isNotBlank(mBid)){
					EnjoyMember memberB = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,mBid);
			        memberB.setIsIssueBonus(3); //审核拒绝
					memberB.setEt(new Date());
					memberB.setEb(SessionUtil.currentUserId());
					enjoyRaceService.updateByPrimaryKeySelective(memberB,mBid);
				}
				if(StringUtils.isNotBlank(mAid)){
					EnjoyMember memberA = enjoyRaceService.selectByPrimaryKey(EnjoyMember.class,mAid);
					memberA.setIsIssueBonus(3); //审核拒绝
					memberA.setEb(SessionUtil.currentUserId());
					enjoyRaceService.updateByPrimaryKeySelective(memberA,mAid);
					enjoyRaceService.updateByPrimaryKeySelective(memberA,mAid);
					enjoyRaceService.createAuditLog(memberA.getId(), SessionUtil.currentUserId(), "审核拒绝", reason);
					result.put(Constants.Result.RESULT, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * <乐享赛历史报名人列表><>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = "playersList")
	public String playersList( Model model, HttpServletRequest request) throws Exception {
		logger.info("EnjoyRaceController memberList method execute!");
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EnjoyMemberVo> onePage = enjoyRaceService.playersList(new PageRequest(page, size), searchParams);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return   "enjoyrace/playersList";
	}

	/**
	 *
	 * <修改乐享赛报名人身份证号><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年5月5日 下午6:45:48
	 */
	@RequestMapping(value = "/updatePlayerCardNo_dlg")
	public String updatePlayerCardNoDlg(HttpServletRequest request, Model model){
		try {
			String id = request.getParameter("id");
			EnjoyMemberVo vo = new EnjoyMemberVo();
			if(org.apache.commons.lang.StringUtils.isNotEmpty(id)) {
				GamesPlayer player = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class, id);
				if(player != null){
					if(org.apache.commons.lang.StringUtils.isNotEmpty(player.getUserId())) {
						SsoUser user = enjoyRaceService.selectByPrimaryKey(SsoUser.class, player.getUserId());
						if(user != null){
							vo.setIdA(player.getId());
							vo.setNameA(player.getName());
							vo.setCardNoA(player.getCardNo());
							vo.setSexA(player.getSex());
							vo.setPhoneA(user.getPhone());
						}
					}
				}
			}
			model.addAttribute("player", vo);
		} catch (Exception e) {
			logger.error("修改失败！", e);
		}
		return "enjoyrace/player";
	}

	/**
	 * 保存报名人身份证号
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "updatePlayerCardNo", method = RequestMethod.POST)
	@ResponseBody
	public String updatePlayerCardNo(EnjoyMemberVo vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (vo == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "获取信息失败");
			} else {
				if(org.apache.commons.lang.StringUtils.isNotEmpty(vo.getIdA())){
					GamesPlayer player = enjoyRaceService.selectByPrimaryKey(GamesPlayer.class,vo.getIdA());
					if(player != null){
						player.setCardNo(vo.getCardNoA());
						player.setEb(SessionUtil.currentUserId());
						player.setEt(new Date());
						enjoyRaceService.updateByPrimaryKeySelective(player,player.getId());
						result.put(Constants.Result.RESULT, true);
					}else{
						result.put(Constants.Result.RESULT, false);
						result.put(Constants.Result.REASON, "获取信息失败");
					}
				}else{
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "获取信息失败");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * <授权或取消授权app用户修改身份号><>
	 * @param userId
	 * @param state
     * @return
     */
	@RequestMapping(value = "authority", method = RequestMethod.POST)
	@ResponseBody
	public String authority(String userId, Integer state) {
		logger.info("授权或取消授权app用户修改身份证号 userId={} , state={} ", userId, state);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(userId)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "userId不能为空");
			} else if (state == null) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "state不能为空");
			}else {
				SsoUser user = enjoyRaceService.selectByPrimaryKey(SsoUser.class,userId);
				if(user != null){
					user.setAuthority(state);
					user.setUpdateTime(new Date());
					user.setUpdateUser(SessionUtil.currentUserId());
					enjoyRaceService.updateByPrimaryKeySelective(user,userId);
					result.put(Constants.Result.RESULT, true);
				}else {
					result.put(Constants.Result.RESULT, false);
					result.put(Constants.Result.REASON, "用户不存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "getScheduleByType/{gameId}", method = RequestMethod.POST)
	public @ResponseBody String getScheduleByType(@PathVariable String gameId){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
			int gameFormat = game.getGameFormat();
			GameDraw draw = new GameDraw();
			Map<String,Object> data = new HashMap<String, Object>();
			if(gameFormat==3){
				List<Object[][]> schedule = draw.getGroupScheduleTable(gameId);
				data.put("groups", schedule);
			}else if(gameFormat==1){
				List<Object[][]> schedule = draw.getGroupScheduleTable(gameId);
				data.put("groups", schedule);
				if(game.getGameState()==2){
					GamesScheduleModel tree = draw.getTree(gameId);
					data.put("tree", tree);
				}
			}else if(gameFormat==2){
				GamesScheduleModel tree = draw.getTree(gameId);
				data.put("tree", tree);
			}
			result.put(Constants.Result.DATA, data);
			result.put(Constants.Result.RESULT, true);
		} catch (Exception e) {
			logger.debug("获取赛程失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "系统异常，请稍后再试!");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	@RequestMapping(value = "balanceList/{gameId}")
	public String balanceList(@PathVariable String gameId,Model model, HttpServletRequest request) throws Exception {
		List<EnjoyGameVo> balanceList = enjoyRaceService.balanceList(gameId);
		model.addAttribute("balanceList", balanceList);
		model.addAttribute("gameId", gameId);
		return "enjoyrace/balanceList";
	}
	
	@RequestMapping(value = "balance/{gameId}")
	public String balance(@PathVariable String gameId,Model model, HttpServletRequest request) throws Exception {
		EnjoyGame game = enjoyRaceService.selectByPrimaryKey(EnjoyGame.class, gameId);
		game.setBalanceFlag(1);
		game.setBalanceOper(SessionUtil.currentUserId());
		enjoyRaceService.updateByPrimaryKey(game, game.getId());
		return "redirect:/enjoyRace/list";
	}
	
	
	@RequestMapping(value = "exportSchedule/{id}")
	public String exportSchedule(@PathVariable String id, Model model,HttpServletRequest request,HttpServletResponse response){
		BufferedOutputStream bufferedOutPut = null;
		BufferedInputStream bis = null;
		try{
			GameDraw draw = new GameDraw();
            List<Object[][]> groupScheduleList = draw.getGroupScheduleTable(id);
            Type type = new TypeToken<ArrayList<ArrayList<ArrayList<String>>>>() {}.getType();
    		ArrayList<ArrayList<ArrayList<String>>> dataList = MyGson.getInstance().fromJson(MyGson.getInstance().toJson(groupScheduleList), type);

    		ExcelUtil4CTA ctaExport = new ExcelUtil4CTA();
    		
    		String uuid = UUID.get();
    		ctaExport.exportGroups("小组赛", dataList, "/tmp/"+uuid+".xls");
    		
			String fileName = uuid+".xls";
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);// 指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			OutputStream output = response.getOutputStream();
			bufferedOutPut = new BufferedOutputStream(output);
			bis = new BufferedInputStream(new FileInputStream("/tmp/"+uuid+".xls"));
	        byte[] byteArray=new byte[1024];
	        while(bis.read(byteArray)!=-1){ 
	        	bufferedOutPut.write(byteArray);
	        }
	        bufferedOutPut.flush();
			return null;
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			if(bufferedOutPut!=null){
				try {
					bufferedOutPut.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}

	}
	
	@RequestMapping(value = "exportScheduleFields/{id}")
	public String exportScheduleFields(@PathVariable String id, Model model,HttpServletRequest request,HttpServletResponse response){
		BufferedOutputStream bufferedOutPut = null;
		try{
    		String uuid = UUID.get();
			String fileName = uuid+".xls";
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);// 指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			OutputStream output = response.getOutputStream();
			bufferedOutPut = new BufferedOutputStream(output);
			
			Workbook wb=new HSSFWorkbook();
			Sheet sheet=wb.createSheet("场地编排");
			
			
			TreeMap<String,List<Map<String,Object>>> fields = enjoyRaceService.getScheduleFields(id);
			
			int skip = 0;
			
			for(Map.Entry<String, List<Map<String, Object>>> entry : fields.entrySet()){
				String fieldNo = entry.getKey();
				CellRangeAddress cra=new CellRangeAddress(0, 0, skip, skip+3);	
				sheet.addMergedRegion(cra);
				Row row = sheet.getRow(0)==null?sheet.createRow(0):sheet.getRow(0);
				Cell cell0 = row.createCell(skip);
				cell0.setCellValue(fieldNo+"号场地");
				int i = 1;
				for(Map<String, Object> schedule:entry.getValue()){
					Row row_ = sheet.getRow(i)==null?sheet.createRow(i):sheet.getRow(i);
					Cell cell1 = row_.createCell(skip);
					Integer gameType = (Integer)(schedule.get("game_type"));
					String gameType_ = "";
					if(gameType==1){
						gameType_ = "男子单打";
					}else if(gameType==2){
						gameType_ = "女子单打";
					}else if(gameType==3){
						gameType_ = "男子双打";
					}else if(gameType==4){
						gameType_ = "女子双打";
					}else if(gameType==5){
						gameType_ = "混合双打";
					}else if(gameType==6){
						gameType_ = "混合单打";
					}else if(gameType==7){
						gameType_ = "无性别限制双打";
					}
					cell1.setCellValue(gameType_+String.valueOf(schedule.get("group_id"))+"组"+String.valueOf(schedule.get("turn"))+"轮");
					Cell cell2 = row_.createCell(skip+1);
					cell2.setCellValue(String.valueOf(schedule.get("p1")));
					Cell cell3 = row_.createCell(skip+2);
					cell3.setCellValue("VS");
					Cell cell4 = row_.createCell(skip+3);
					cell4.setCellValue(String.valueOf(schedule.get("p2")));
					i++;
				}
				skip +=5;
			}
			
	        bufferedOutPut.flush();
	        wb.write(bufferedOutPut);
			return null;
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			if(bufferedOutPut!=null){
				try {
					bufferedOutPut.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}

	}


	@RequestMapping(value="exportEnjoyGame")
	public String exportEnjoyGame(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<EnjoyGameVo> onePage = enjoyRaceService.list(null, searchParams,false);
		List<EnjoyGameVo> data =  new ArrayList<EnjoyGameVo>();
		List<EnjoyGameVo> gameList =  onePage.getContent();
		for (EnjoyGameVo vo:gameList) {
			EnjoyGameVo game = new EnjoyGameVo();
			BeanUtils.copyProperties(vo,game);
			data.add(game);
			if(CollectionUtils.isNotEmpty(game.getGameVoList())){
				for (EnjoyGameVo vo2:game.getGameVoList()) {
					 EnjoyGameVo game2 = new EnjoyGameVo();
					BeanUtils.copyProperties(game,game2);
					game2.setGameTypeName(vo2.getGameTypeName());
					game2.setIsScoreNoticeName(vo2.getIsScoreNoticeName());
					game2.setApplicantNumber(vo2.getApplicantNumber());
					data.add(game2);
				}
			}
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("乐享赛列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String[] headers = {"赛事名称::name","城市站数::cityHoldTimesName","赛事级别::gameLevelName","比赛方式::gameTypeName","成绩公告状态::isScoreNoticeName",
				"开始时间::startTime","结束时间::endTime","报截止时间::expiryDate","站点::siteName","场馆::statiumName","报名人数::applicantNumber",
				"审核状态::stateName","赛事状态::stateValue","发布方::isOfficalName"};
		excelUtil.exportExcel("乐享赛列表", headers, data, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}

	@RequestMapping(value="exportBonusList")
	public String exportBonusList(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.debug(" exportBonusList method execute!");
		//根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<EnjoyGameVo> onePage = enjoyRaceService.bonusList(null, searchParams,false);
		List<EnjoyGameVo> data =  onePage.getContent();
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("奖金列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String[] headers = {"赛事名称::name","赛事级别::gameLevelName","比赛方式::gameTypeName","站点::siteName","场馆::statiumName","奖金状态::isIssueBonusName","开始时间::startTime","结束时间::endTime"};
		excelUtil.exportExcel("奖金列表", headers, data, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}
	
	public static void main(String[] args) {
		String str = "[[[\"A\",1,2,3,4,\"胜场\",\"名次\"],[\"1 然\",\"\",\"6:7\",\"7:5\",\"2:6\",\"1\",\" \"],[\"2 凤\",\"7:6\",\"\",\"5:7\",\"7:5\",\"2\",2],[\"3 白\",\"5:7\",\"7:5\",\"\",\"6:2\",\"2\",1],[\"4 张立宝\",\"6:2\",\"5:7\",\"2:6\",\"\",\"1\",\" \"]],[[\"B\",1,2,3,4,5,\"胜场\",\"名次\"],[\"1 旭\",\"\",\"6:3\",\"6:1\",\"7:5\",\"6:4\",\"4\",1],[\"2 敏\",\"3:6\",\"\",\"6:7\",\"5:7\",\"7:5\",\"1\",\" \"],[\"3 丹\",\"1:6\",\"7:6\",\"\",\"1:6\",\"6:7\",\"1\",\" \"],[\"4 凡\",\"5:7\",\"7:5\",\"6:1\",\"\",\"2:6\",\"2\",\" \"],[\"5 帅帅\",\"4:6\",\"5:7\",\"7:6\",\"6:2\",\"\",\"2\",2]]]";
		Type type = new TypeToken<ArrayList<ArrayList<ArrayList<String>>>>() {}.getType();
		ArrayList<ArrayList<ArrayList<String>>> list = MyGson.getInstance().fromJson(str, type);
		System.out.println(list.get(0).get(0).get(0));
	}

}
