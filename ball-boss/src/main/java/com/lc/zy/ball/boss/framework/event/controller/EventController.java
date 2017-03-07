package com.lc.zy.ball.boss.framework.event.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jasypt.commons.CommonUtils;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.activity.vo.ActivityVo;
import com.lc.zy.ball.boss.framework.corps.service.CorpsService;
import com.lc.zy.ball.boss.framework.event.service.EventService;
import com.lc.zy.ball.boss.framework.event.vo.GameScoreVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesCorpVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesScheduleVo;
import com.lc.zy.ball.boss.framework.event.vo.GamesVo;
import com.lc.zy.ball.boss.framework.event.vo.ScheduleVo;
import com.lc.zy.ball.boss.framework.event.vo.ScoreGroupVo;
import com.lc.zy.ball.boss.framework.event.vo.ScoreInfoVo;
import com.lc.zy.ball.boss.framework.event.vo.ScoreVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.CorpsInfo;
import com.lc.zy.ball.domain.oa.po.Games;
import com.lc.zy.ball.domain.oa.po.GamesCorps;
import com.lc.zy.ball.domain.oa.po.GamesSchedule;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.Constants.event;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.push.PushPacket;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.ExportExcelUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * 运营管理 - 赛事管理
 * 
 * @author liangsh
 *
 */
@Controller
@RequestMapping(value = "/event")
public class EventController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private CorpsService corpsService;

	@Autowired
	private PushPacket pushPacket;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * <赛事列表><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:05:12
	 * @author： liangsh
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String EventList(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("EventController EventList method execute!");
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		List<Games> list = eventService.find(searchParams, true);
		model.addAttribute("data", list);
		model.addAttribute("size", list.size());
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "event/eventList";
	}

	/**
	 * 
	 * <创建赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:05:47
	 * @author： liangsh
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) throws Exception {
		model.addAttribute("action", "create");
		return "event/eventForm";
	}

	/**
	 * 
	 * <保存赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:06:04
	 * @author： liangsh
	 * @param gamesVo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String create(GamesVo gamesVo, RedirectAttributes redirectAttributes) {
		try {
			if (gamesVo.getLngLat() != null && !"".equals(gamesVo.getLngLat())) {
				String[] lnglat = gamesVo.getLngLat().split(",");
				gamesVo.setLng(Double.parseDouble(lnglat[0]));
				gamesVo.setLat(Double.parseDouble(lnglat[1]));
			}
			Games games = new Games();
			BeanUtils.copyProperties(gamesVo, games);
			games.setMoney(new Double(Double.valueOf(gamesVo.getAvgMoney()) * 100).intValue());
			if(games.getRealNameSys() == 1){
				games.setType(0);
			}
			String eventId = eventService.createOrUpdateEvent(games);
			// 发送赛事推送 add by sl 2015-12-14
			if (gamesVo.getIsPush() == 0) {
				String areaCode = gamesVo.getAreaCode();
				pushPacket.sendPush(areaCode, com.lc.zy.common.Constants.pushMsgType.PUSH_TYPE_EVENT, eventId,
						gamesVo.getName(), SessionUtil.currentUserId(),
						com.lc.zy.common.Constants.isPush.PUSH_TYPE_UNLIMIT, "");
			}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存赛事成功!"));
		} catch (Exception e) {
			logger.debug("保存赛事失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存赛事失败！"));
		}
		return "redirect:/event/list";
	}

	/**
	 * 
	 * <查看赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:06:17
	 * @author： liangsh
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable String id, Model model) {
		Games games;
		try {
			games = eventService.getEvent(id);
			games.setMoney(games.getMoney());
			model.addAttribute("event", games);
		} catch (Exception e) {
			logger.error("查看赛事失败！", e);
		}
		return "/event/eventView";
	}

	/**
	 * 
	 * <更新赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:06:30
	 * @author： liangsh
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		Games games = eventService.getEvent(id);
		games.setMoney(games.getMoney());
		model.addAttribute("event", games);
		model.addAttribute("action", "update");
		return "event/eventForm";
	}
	
	@RequestMapping(value="show/{id}/{show}")
	public String show(RedirectAttributes redirectAttributes,Model model,ServletRequest request,
			@PathVariable String id,@PathVariable Integer isShow){
		try {
			if(StringUtils.isNotBlank(id)){
				Games games = eventService.getEvent(id);
				games.setIsShow(isShow);
				eventService.updateByPrimaryKeySelective(Games.class, id);
			}
		} catch (Exception e) {
			logger.debug("修改失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message",
					FlashAttributeUtil.build(false, "修改失败:" + e.getMessage()));
		}
	redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "修改成功"));
	return "redirect:/event/list";
	}

	/**
	 * 
	 * <赛事置顶><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:06:46
	 * @author： liangsh
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "stick/{id}")
	public String EventStick(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id) {
		if (CommonUtils.isNotEmpty(id)) {
			Games games = new Games();
			games.setId(id);
			Date updateTime = null;
			try {
				updateTime = sdf.parse(sdf.format(new Date()));
				games.setStick(Integer.MAX_VALUE);
				User user = SessionUtil.currentUser();
				games.setEb(user.getUserId());
				games.setEt(updateTime);
				eventService.createOrUpdateEvent(games);
			} catch (Exception e) {
				logger.debug("置顶赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message",
						FlashAttributeUtil.build(false, "置顶赛事失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "赛事置顶成功"));
		return "redirect:/event/list";
	}

	/**
	 * 
	 * <取消赛事置顶><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:07:03
	 * @author： liangsh
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "unstick/{id}")
	public String EventUnStick(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id) {
		if (CommonUtils.isNotEmpty(id)) {
			Games games = new Games();
			games.setId(id);
			Date updateTime = null;
			try {
				updateTime = sdf.parse(sdf.format(new Date()));
				games.setStick(0);
				User user = SessionUtil.currentUser();
				games.setEb(user.getUserId());
				games.setEt(updateTime);
				eventService.createOrUpdateEvent(games);
			} catch (Exception e) {
				logger.debug("取消置赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
				redirectAttributes.addFlashAttribute("message",
						FlashAttributeUtil.build(false, "取消置顶赛事失败:" + e.getMessage()));
			}
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "取消赛事置顶成功"));
		return "redirect:/event/list";
	}

	/**
	 * 
	 * <上移赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:07:25
	 * @author： liangsh
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "up/{id}/{preId}")
	@ResponseBody
	public String EventUp(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id, @PathVariable String preId) {
		Gson gson = new Gson();
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "error");
		if (CommonUtils.isNotEmpty(id)) {
			Date updateTime = null;
			try {
				updateTime = sdf.parse(sdf.format(new Date()));
				User user = SessionUtil.currentUser();

				Games games = eventService.selectByPrimaryKey(Games.class, id);
				Games gamesPre = eventService.selectByPrimaryKey(Games.class, preId);
				int seq = games.getSeq();
				int seq_pre = gamesPre.getSeq();
				games.setEt(updateTime);
				games.setEb(user.getUserId());
				games.setSeq(seq_pre);
				gamesPre.setEt(updateTime);
				gamesPre.setEb(user.getUserId());
				gamesPre.setSeq(seq);
				eventService.createOrUpdateEvent(games);
				eventService.createOrUpdateEvent(gamesPre);
				result.put("result", "success");
			} catch (Exception e) {
				logger.debug("上移赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
			}
		}
		return gson.toJson(result);
	}

	/**
	 * 
	 * <下移赛事><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:07:40
	 * @author： liangsh
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "down/{id}/{nextId}")
	@ResponseBody
	public String EventDown(RedirectAttributes redirectAttributes, Model model, ServletRequest request,
			@PathVariable String id, @PathVariable String nextId) {
		Gson gson = new Gson();
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "error");
		if (CommonUtils.isNotEmpty(id)) {
			Date updateTime = null;
			try {

				updateTime = sdf.parse(sdf.format(new Date()));
				User user = SessionUtil.currentUser();

				Games games = eventService.selectByPrimaryKey(Games.class, id);
				Games gamesNext = eventService.selectByPrimaryKey(Games.class, nextId);
				int seq = games.getSeq();
				int seq_pre = gamesNext.getSeq();
				games.setEt(updateTime);
				games.setEb(user.getUserId());
				games.setSeq(seq_pre);
				gamesNext.setEt(updateTime);
				gamesNext.setEb(user.getUserId());
				gamesNext.setSeq(seq);
				eventService.createOrUpdateEvent(games);
				eventService.createOrUpdateEvent(gamesNext);
			} catch (Exception e) {
				logger.debug("下移赛事失败: {}", e.getMessage());
				logger.error(e.getMessage(), e);
			}
		}
		return gson.toJson(result);
	}

	/**
	 * 
	 * <个人赛成员列表><功能具体实现>
	 *
	 * @create：2015年10月14日 上午10:20:45
	 * @author： liangsh
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberList/{id}")
	public String memberList(@PathVariable String id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("EventController memberList method execute!");
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		CommonOAUtils.paramesTrim(searchParams);// 参数去空
		Games games = eventService.selectByPrimaryKey(Games.class,id);
		List<GamesMemberVo> memberList = new ArrayList<GamesMemberVo>();
		if(games != null){
			memberList = eventService.findMemberList(searchParams, id);
		}
		model.addAttribute("isVerify",games.getIsVerify());
		model.addAttribute("memberList", memberList);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "event/memberList";
	}

	/**
	 * 
	 * <查看成员><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:07:54
	 * @author： liangsh
	 * @param eventId
	 * @param memberId
	 * @param userId
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberView/{eventId}/{memberId}/{userId}/{status}", method = RequestMethod.GET)
	public String memberForm(@PathVariable String eventId, @PathVariable String memberId, @PathVariable String userId,
			@PathVariable String status, Model model) throws Exception {
		try {
			SsoUser user = eventService.getUserByKey(userId);
			model.addAttribute("user", user)  ;
			model.addAttribute("memberId", memberId);
			model.addAttribute("status", status);
			model.addAttribute("eventId", eventId);
		} catch (Exception e) {
			logger.error("查看成员失败！", e);
		}
		return "event/memberView";
	}

	/**
	 * 
	 * <审核成员><功能具体实现>
	 *
	 * @create：2015年10月15日 下午3:08:10
	 * @author： liangsh
	 * @param id
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateMemberState/{id}/{status}")
	public String updateState(@PathVariable String id, @PathVariable Integer status, Model model) {
		logger.info("成员审核 id={} , status={} ", id, status);
		String eventId = "";
		try {
			eventId = eventService.updaeMember(id, status);
		} catch (Exception e) {
			logger.error("查看用户审核列表时报", e);
		}
		if (eventId.contains("/")) {
			return "redirect:/event/memberView/" + eventId;
		} else {
			return "redirect:/event/memberList/" + eventId;
		}
	}

	/**
	 * 
	 * <团队赛参赛战队列表><功能具体实现>
	 *
	 * @create：2015年12月4日 下午5:55:43
	 * @author： liangsh
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "eventCorps/{id}")
	public String eventCorps(@PathVariable String id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		logger.info("EventController eventCorps method execute!");
		try {
			// 根据查询条件查询数据
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			CommonOAUtils.paramesTrim(searchParams);// 参数去空
			List<GamesCorpVo> eventCorps = eventService.findGamesCorpsList(searchParams, id);
			model.addAttribute("eventCorps", eventCorps);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.error("查看参赛战队列表失败！", e);
		}

		return "event/eventCorps";
	}

	/**
	 * 
	 * <查看战队详情><功能具体实现>
	 *
	 * @create：2015年12月4日 下午6:45:06
	 * @author： liangsh
	 * @param corps
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "corpsView/{corpsId}", method = RequestMethod.GET)
	public String corpsForm(@PathVariable String corpsId, Model model) {
		try {
			CorpsInfo info = this.eventService.selectByPrimaryKey(CorpsInfo.class, corpsId);
			if(info != null){
				model.addAttribute("info", info);
				return "event/corpsView";
			}else {
			   GamesCorps gamesCorps = this.eventService.selectByPrimaryKey(GamesCorps.class,corpsId);
			   SsoUser user = this.eventService.selectByPrimaryKey(SsoUser.class,gamesCorps.getCorpsId());
			   model.addAttribute("info", user);
			   return "event/userView";
			}
			
		} catch (Exception e) {
			logger.error("查看战队详情失败！", e);
		}
		return "event/corpsView";
	}

	/**
	 * 
	 * <获取赛程><功能具体实现>
	 *
	 * @create：2015年12月7日 下午4:50:43
	 * @author： liangsh
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "scheduleList/{gamesId}")
	public String scheduleForm(@PathVariable String gamesId, Model model) {
		try {
			// List<GamesScheduleVo> list =
			// eventService.getScheduleList(gamesId);
			List<ScheduleVo> list = eventService.getScheduleVoList(gamesId);
			if (CollectionUtils.isEmpty(list)) {
				new ArrayList<ScheduleVo>();
			}
			model.addAttribute("list", list);
			model.addAttribute("evnetId", gamesId);
		} catch (Exception e) {
			logger.error("获取赛程失败", e);
		}
		return "event/eventScheduleList";
	}

	@RequestMapping(value = "creatSchedule/{gamiesId}", method = RequestMethod.GET)
	public String createSchedule(Model model, @PathVariable String gamiesId) {
		model.addAttribute("action", "create");
		model.addAttribute("evnetId", gamiesId);
		return "event/scheduleForm";
	}

	@RequestMapping(value = "scheduleForm", method = RequestMethod.POST)
	public String saveSchedule(ScheduleVo vo, RedirectAttributes redirectAttributes) {
		try {
			GamesSchedule info = new GamesSchedule();
			BeanUtils.copyProperties(vo, info);
			if (StringUtils.isBlank(vo.getId())) {
				info.setId(UUID.get());
				info.setCt(new Date());
				info.setCb(SessionUtil.currentUserId());
				info.setEt(new Date());
				info.setEb(SessionUtil.currentUserId());
				eventService.insertSelective(info, vo.getId());
			} else {
				info.setEb(SessionUtil.currentUserId());
				info.setEt(new Date());
				eventService.updateByPrimaryKeySelective(info, vo.getId());
			}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存赛程成功!"));
		} catch (Exception e) {
			logger.debug("保存赛程失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存赛程失败！"));
		}
		return "redirect:/event/scheduleList/" + vo.getGamesId();
	}

	@RequestMapping(value = "updateSchedule/{id}", method = RequestMethod.GET)
	public String updateScheduleForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		GamesSchedule schedule = eventService.selectByPrimaryKey(GamesSchedule.class, id);
		ScheduleVo vo = new ScheduleVo();
		BeanUtils.copyProperties(schedule, vo);
		if (StringUtils.isNotBlank(schedule.getMarinesIdA())) {
			CorpsInfo A = corpsService.selectByPrimaryKey(CorpsInfo.class, schedule.getMarinesIdA());
			if (A != null) {
				vo.setLogoA(A.getLogo());
				vo.setNameA(A.getName());
			}
		}
		if (StringUtils.isNotBlank(schedule.getMarinesIdB())) {
			CorpsInfo B = corpsService.selectByPrimaryKey(CorpsInfo.class, schedule.getMarinesIdB());
			if (B != null) {
				vo.setLogoB(B.getLogo());
				vo.setNameB(B.getName());
			}
		}
		model.addAttribute("schedule", vo);
		model.addAttribute("evnetId", schedule.getGamesId());
		model.addAttribute("action", "update");
		return "event/scheduleForm";
	}

	@RequestMapping(value = "delGames", method = RequestMethod.POST)
	@ResponseBody
	public String delGames(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				eventService.deleteByPrimaryKey(Games.class, id);
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

	@RequestMapping(value = "delCorps", method = RequestMethod.POST)
	@ResponseBody
	public String delCorps(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				eventService.deleteByPrimaryKey(GamesCorps.class, id);
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

	@RequestMapping(value = "delSchedule", method = RequestMethod.POST)
	@ResponseBody
	public String delSchedule(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(id)) {
				result.put(Constants.Result.RESULT, false);
				result.put(Constants.Result.REASON, "id不能为空");
			} else {
				eventService.deleteByPrimaryKey(GamesSchedule.class, id);
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
	 * <保存赛程><功能具体实现>
	 *
	 * @create：2015年12月8日 下午12:34:24
	 * @author： liangsh
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveSchedule/{gamesId}")
	public String saveSchedule(@PathVariable String gamesId, Model model, String allCorps) {
		try {
			List<GamesScheduleVo> list = new ArrayList<GamesScheduleVo>();
			if (StringUtils.isNotEmpty(allCorps)) {
				Gson g = new Gson();
				list = g.fromJson(allCorps, new TypeToken<List<GamesScheduleVo>>() {
				}.getType());
				eventService.saveScheduleList(list, gamesId);
			}
		} catch (Exception e) {
			logger.error("保存赛程失败", e);
		}
		return "redirect:/event/scheduleList/" + gamesId;
	}

	@RequestMapping(value = "/corpsFrame_dlg")
	public String corpsFrame(HttpServletRequest request, Model model) {
		String eventId = request.getParameter("eventId");
		logger.debug("加载 corpsFrame。。。");
		model.addAttribute("eventId", eventId);
		return "event/corpsFrame";
	}

	/**
	 * 
	 * <获取战队><功能具体实现>
	 *
	 * @create：2015年12月9日 下午5:14:51
	 * @author： liangsh
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/corpsList_dlg")
	public String corpsList(HttpServletRequest request, Model model) {
		try {
			String eventId = request.getParameter("eventId");
			// 根据查询条件查询数据
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			CommonOAUtils.paramesTrim(searchParams);// 参数去空
			List<GamesCorpVo> eventCorps = eventService.findGamesCorpsList(searchParams, eventId);
			model.addAttribute("eventCorps", eventCorps);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			logger.error("查看参赛战队列表失败！", e);
		}
		return "event/corpsList";
	}

	/**
	 * 
	 * <比分录入初始化><功能具体实现>
	 *
	 * @create：2015年12月8日 下午4:31:03
	 * @author： sl
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "score")
	public String score(String gameId, Model model) {
		List<ScoreVo> scoreVos = new ArrayList<ScoreVo>();
		try {
			// 获取比赛轮数
			int turnNum = eventService.getTurnNum(gameId);
			for (int i = 1; i <= turnNum; i++) {
				List<ScoreGroupVo> scoreGroupVos = new ArrayList<ScoreGroupVo>();
				ScoreVo scoreVo = new ScoreVo();
				// 赛事类型
				String gameType = "";
				// 轮数
				scoreVo.setTurnNum(i);
				// 组数
				int groupNum = eventService.getGroupNum(gameId, i);
				List<GamesSchedule> groups = eventService.getGroups(gameId, i);
				// 封装组成员
				for (int k = 0; k < groupNum; k++) {
					ScoreGroupVo scoreGroupVo = new ScoreGroupVo();
					// 组名
					String groupName = groups.get(k).getGameGroup();
					scoreGroupVo.setGameGroup(groupName);
					// 组成员
					List<GamesSchedule> g = eventService.getGroupsByName(gameId, i, groupName);
					gameType = g.get(0).getGameType();
					List<GameScoreVo> gameScoreVos = new ArrayList<GameScoreVo>();
					for (GamesSchedule gs : g) {
						GameScoreVo gameScoreVo = new GameScoreVo();
						// 获取A战队信息
						CorpsInfo corpsInfoA = eventService.getCorpsInfoById(gs.getMarinesIdA());
						if (corpsInfoA != null) {
							// A队队徽
							if (StringUtils.isNotBlank(corpsInfoA.getLogo())) {
								gameScoreVo.setLogoA(corpsInfoA.getLogo());
							}
							// A队id
							gameScoreVo.setIdA(corpsInfoA.getId());
							// A队名称
							gameScoreVo.setNameA(corpsInfoA.getName());
							// A队积分
							if (gs.getIntegrationA() == null) {
								gameScoreVo.setIntegrationA("0");
							} else {
								gameScoreVo.setIntegrationA(String.valueOf(gs.getIntegrationA()));
							}
						}

						// 获取B战队信息
						CorpsInfo corpsInfoB = eventService.getCorpsInfoById(gs.getMarinesIdB());
						if (corpsInfoB != null) {
							// B队队徽
							if (StringUtils.isNotBlank(corpsInfoB.getLogo())) {
								gameScoreVo.setLogoB(corpsInfoB.getLogo());
							}
							// B队id
							gameScoreVo.setIdB(corpsInfoB.getId());
							// B队名称
							gameScoreVo.setNameB(corpsInfoB.getName());
							// B队积分
							if (gs.getIntegrationB() == null) {
								gameScoreVo.setIntegrationB("0");
							} else {
								gameScoreVo.setIntegrationB(String.valueOf(gs.getIntegrationB()));
							}
						}

						// id
						gameScoreVo.setId(gs.getId());

						// 比分
						if (gs.getScoreA() == null) {
							gameScoreVo.setScore(null);
						} else {
							gameScoreVo.setScore(String.valueOf(gs.getScoreA()) + ":" + String.valueOf(gs.getScoreB()));
						}

						// 比赛时间
						gameScoreVo.setGameTime(gs.getGameTime());

						gameScoreVos.add(gameScoreVo);
					}
					scoreGroupVo.setScoreList(gameScoreVos);

					scoreGroupVos.add(scoreGroupVo);
				}
				scoreVo.setGameType(gameType);
				scoreVo.setScoreGroupList(scoreGroupVos);

				scoreVos.add(scoreVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("比分录入初始化", e.getMessage());
		}
		model.addAttribute("data", scoreVos);
		model.addAttribute("gameId", gameId);

		return "event/score";
	}

	/**
	 * 
	 * <保存比分><功能具体实现>
	 *
	 * @create：2015年12月9日 下午12:09:25
	 * @author： sl
	 * @param score
	 * @param id
	 * @return
	 */
	@RequestMapping("saveScore")
	@ResponseBody
	public String saveScore(ScoreInfoVo myForm) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			eventService.saveScore(myForm);
			result.put("result", true);
		} catch (Exception e) {
			result.put("result", false);
			logger.debug("保存比分", e.getMessage());
		}
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <><赛事统计>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2015年12月24日 下午5:02:24
	 */
	@RequestMapping(value = "statisticsList")
	public String eventStatisticsList(Model model, HttpServletRequest request) {
		logger.debug("EventController eventStatisticsList method execute!");
		try {
			// 根据查询条件查询数据
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			CommonOAUtils.paramesTrim(searchParams);
			Page<Games> onePage = eventService.eventStatisticsList(new PageRequest(page, size), searchParams, true);
			model.addAttribute("data", onePage);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("赛事统计失败", e.getMessage());
		}
		return "event/eventStatisticsList";
	}

	/**
	 * 
	 * <><获取统计详情>
	 * 
	 * @param eventId
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午5:33:17
	 */
	@RequestMapping(value = "updateForm/{eventId}")
	public String updateForm(@PathVariable String eventId, Model model) {
		try {
			Games games = eventService.selectByPrimaryKey(Games.class, eventId);
			model.addAttribute("event", games);
		} catch (Exception e) {
			logger.error("修改统计页面", e);
		}
		return "/event/eventStatisticsForm";
	}

	/**
	 * 
	 * <><更新统计信息>
	 * 
	 * @param model
	 * @param vo
	 * @return
	 * @author liangsh
	 * @date 2015年12月24日 下午5:18:19
	 */
	@RequestMapping(value = "updateEventStatistics")
	public String updateStatistisc(Games vo) {
		logger.debug("EventController updateEventStatistics method execute!");
		try {
			Games info = new Games();
			BeanUtils.copyProperties(vo, info);
			info.setCostPrice(vo.getCostPrice() * 100);
			info.setExpenditure(vo.getExpenditure() * 100);
			info.setProfit(vo.getProfit() * 100);
			eventService.update(info);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("赛事统计修改失败", e.getMessage());
		}
		return "redirect:/event/statisticsList";
	}

	@RequestMapping(value = "export")
	public void export(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		try {
			// 根据查询条件查询数据
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			CommonOAUtils.paramesTrim(searchParams);
			Page<Games> onePage = eventService.eventStatisticsList(new PageRequest(page, size), searchParams, false);
			if (onePage != null && onePage.getSize() > 0) {
				writeExcel(response, onePage);
			}
		} catch (Exception e) {
			logger.error("导出exce出错", e);
		}
	}

	/**
	 * 
	 * <><功能具体实现>
	 * 
	 * @param response
	 * @param onePage
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2015年12月25日 上午10:40:44
	 */
	public Map<String, Object> writeExcel(HttpServletResponse response, Page<Games> onePage) throws Exception {
		String uuid = UUID.get();
		String fileName = uuid + ".xls";
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = "赛事统计(" + DateUtil.formatDate(new Date(), "yyyy-MM-dd") + ")";

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

		HSSFSheet sheet = wb.createSheet();
		ExportExcelUtil exportExcel = new ExportExcelUtil(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 8);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = row1.createCell(0);

		// 第一行第1列
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("赛事名称"));

		// 第一行第2列
		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("运营成本（总）"));

		// 第一行第3列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("报名费"));

		// 第一行第4列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("报名人数"));

		// 第一行第5列
		cell1 = row1.createCell(4);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("场地支出"));

		// 第一行第6列
		cell1 = row1.createCell(5);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("盈利"));

		// 第一行第7列
		cell1 = row1.createCell(6);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("发布人"));

		// 第一行第8列
		cell1 = row1.createCell(7);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("发布时间"));

		// 第一行第9列
		cell1 = row1.createCell(8);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("备注"));

		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		int i = 1;
		HSSFCellStyle dateCellStyle = wb.createCellStyle();
		short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
		dateCellStyle.setDataFormat(df);
		HSSFCellStyle dateCellStyle_ = wb.createCellStyle();
		short df_ = wb.createDataFormat().getFormat("yyyy-mm-dd");
		dateCellStyle_.setDataFormat(df_);
		for (Games games : onePage.getContent()) {
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellValue(new HSSFRichTextString(games.getName()));
			i += 1;
			cell.setCellStyle(cellStyle);

			cell = row.createCell(1);
			cell.setCellStyle(dateCellStyle);
			if (games.getCostPrice() != null) {
				cell.setCellValue(new HSSFRichTextString((games.getCostPrice()*0.01) + "元"));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

			cell = row.createCell(2);
			cell.setCellStyle(dateCellStyle);
			if (games.getMoney() != null) {
				cell.setCellValue(new HSSFRichTextString((games.getMoney()*0.01) + "元"));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

			cell = row.createCell(3);
			cell.setCellStyle(dateCellStyle);
			if (games.getApplicantNumber() != null) {
				cell.setCellValue(new HSSFRichTextString(games.getApplicantNumber()+""));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			if (games.getExpenditure() != null) {
				cell.setCellValue(new HSSFRichTextString((games.getExpenditure()*0.01) + "元"));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			if (games.getProfit() != null) {
				cell.setCellValue(new HSSFRichTextString((games.getProfit()*0.01) + "元"));
			} else {
				cell.setCellValue(new HSSFRichTextString(""));
			}

			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			String userName = "";
			if(StringUtils.isNotBlank(games.getCb())){
				User user = eventService.selectByPrimaryKey(User.class,games.getCb());
				if(user != null){
					userName = user.getNickname();
				}
			}
			cell.setCellValue(new HSSFRichTextString(userName));

			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(DateUtils.formatDate(games.getCt(), "yyyy-MM-dd"));

			cell = row.createCell(8);
			cell.setCellStyle(dateCellStyle_);
			cell.setCellValue(new HSSFRichTextString(games.getRemark()));
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			return null;
		} catch (IOException e) {
			logger.error("导出exce表格失败", e);
			throw new RuntimeException(e);
		} finally {
			bufferedOutPut.close();
		}
	}
}
