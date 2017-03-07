package com.lc.zy.ball.boss.framework.push.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.activity.service.ActivityService;
import com.lc.zy.ball.boss.framework.event.service.EventService;
import com.lc.zy.ball.boss.framework.push.service.PushService;
import com.lc.zy.ball.boss.framework.push.vo.PushFormVo;
import com.lc.zy.ball.boss.framework.push.vo.PushVo;
import com.lc.zy.ball.boss.framework.system.service.EmsgService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Activity;
import com.lc.zy.ball.domain.oa.po.Games;
import com.lc.zy.ball.domain.oa.po.Push;
import com.lc.zy.common.Constants;
import com.lc.zy.common.emsgclient.EmsgPushClient;
import com.lc.zy.common.push.PushPacket;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * 推送管理
 * 
 */
@Controller
@RequestMapping(value = "/push")
public class PushController {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);

	@Autowired
	private PushService pushService;

	@Autowired
	private EmsgService emsgService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EmsgPushClient client;

	@Autowired
	private PushPacket pushPacket;

	/**
	 * 
	 * <初始化><功能具体实现>
	 *
	 * @create：2015年9月25日 下午4:24:20
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String main(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 获取发送历史
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		logger.debug(searchParams.toString());
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<PushVo> pushPage = null;
		try {
			pushPage = pushService.findPushLogList(new PageRequest(page, size), searchParams);
		} catch (Exception e) {
			logger.error("PushLog error:" + e.getMessage());
		}
		model.addAttribute("data", pushPage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "push/pushList";
	}

	/**
	 * 
	 * <新增素材main页面><功能具体实现>
	 *
	 * @create：2015年12月1日 上午9:45:04
	 * @author： sl
	 * @return
	 */
	@RequestMapping("/newSource")
	public String newSource() {
		return "push/pushSource";
	}

	/**
	 * 
	 * <新增素材><功能具体实现>
	 *
	 * @create：2015年11月27日 下午6:13:00
	 * @author： sl
	 * @return
	 */
	@RequestMapping("/addSource")
	public String addSource() {
		return "push/pushSourceNew";
	}

	/**
	 * 
	 * <加载表单><功能具体实现>
	 *
	 * @create：2015年9月25日 上午11:36:07
	 * @author： sl
	 * @param id
	 * @param pid
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("sourceForm")
	public String sourceForm(String id, String groupId, Model model, HttpServletResponse response) {

		Push push = null;
		if (StringUtils.isNotBlank(id)) {
			push = pushService.getPush(id);
		}
		push = push == null ? new Push() : push;

		if (push.getGroupId() == null && StringUtils.isNotBlank(groupId)) {
			push.setGroupId(groupId);
			model.addAttribute("groupId", id);
		} else {
			model.addAttribute("groupId", groupId);
		}
		model.addAttribute("push", push);
		return "push/sourceForm";
	}

	/**
	 * 
	 * <新增/更新><功能具体实现>
	 *
	 * @create：2015年9月25日 上午10:25:51
	 * @author： sl
	 * @param org
	 * @param redirectAttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveForm")
	public String saveForm(Push push, Model model) throws Exception {
		Push p = pushService.savePush(push);
		if (StringUtils.isBlank(p.getGroupId())) {
			p.setGroupId(p.getId());
			model.addAttribute("push", p);
		} else {
			Map<String, List<Push>> pMap = pushService.getPushByPid(p.getGroupId());
			Push p_ = pMap.get("pPush").get(0);
			p_.setGroupId(p_.getId());
			model.addAttribute("push", p_);
			model.addAttribute("pushC", pMap.get("cPush"));
		}
		// 保存当前专题id
		model.addAttribute("currentId", p.getId());

		return "/push/pushSourceNew";
	}

	/**
	 * 
	 * <初始化素材信息><功能具体实现>
	 *
	 * @create：2015年10月12日 下午3:18:42
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushSource_dlg", method = RequestMethod.GET)
	public String pushSource_dlg(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws Exception {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<PushVo> pushPage = null;
		pushPage = pushService.findPushList(new PageRequest(page, size), 0);
		model.addAttribute("data", pushPage);
		return "push/pushSource_dlg";
	}

	/**
	 * 
	 * <获取更多素材><功能具体实现>
	 *
	 * @create：2015年10月12日 下午3:19:00
	 * @author： sl
	 * @param count
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushSource", method = RequestMethod.GET)
	@ResponseBody
	public String pushSource(String count, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		int num = 2;
		Map<String, Object> viewResult = new HashMap<String, Object>();
		try {
			num = Integer.valueOf(count);
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<PushVo> pushPage = null;
			pushPage = pushService.findPushList(new PageRequest(page, size), num);

			viewResult.put("pushs", pushPage);
		} catch (Exception e) {
			logger.debug("获取更多素材", e.getMessage());
		}

		return MyGson.getInstance().toJson(viewResult);
	}

	/**
	 * 
	 * <素材管理><功能具体实现>
	 *
	 * @create：2015年10月22日 下午2:27:00
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "sourceManage")
	public String sourceManage(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<PushVo> pushPage = null;
		pushPage = pushService.pushManageList(new PageRequest(page, size), searchParams);
		model.addAttribute("data", pushPage);
		return "push/pushSourceManage";
	}

	/**
	 * 
	 * <删除素材list><功能具体实现>
	 *
	 * @create：2015年10月23日 下午3:02:34
	 * @author： sl
	 * @param sourceId
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(String id, Model model) {
		Push p_ = new Push();
		try {
			Map<String, List<Push>> pMap = pushService.delete(id);
			if (pMap != null) {
				if (pMap.get("pPush") != null) {
					p_ = pMap.get("pPush").get(0);
				}
				p_.setGroupId(p_.getId());
				model.addAttribute("push", p_);
				model.addAttribute("pushC", pMap.get("cPush"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除素材list(PushService--delete)" + e.getMessage());
		}
		// 保存当前专题id
		model.addAttribute("currentId", p_.getId());

		return "/push/pushSourceNew";
	}

	/**
	 * 
	 * <删除素材组><功能具体实现>
	 *
	 * @create：2015年12月1日 上午9:30:45
	 * @author： sl
	 * @param sourceId
	 * @return
	 */
	@RequestMapping(value = "deleteSourceGroup")
	public String deleteSourceGroup(String sourceId) {
		try {
			pushService.deleteSourceGroup(sourceId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除素材组(PushService--deleteSourceGroup)" + e.getMessage());
		}
		return "redirect:/push/sourceManage";
	}

	/**
	 * 
	 * <专题推送><功能具体实现>
	 *
	 * @create：2015年10月23日 下午5:47:34
	 * @author： sl
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "sendPush")
	public String sendPush(PushFormVo pushFormVo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String pushType = pushFormVo.getPushType();
			// 专题id
			String pushId = pushFormVo.getGroupId();
			// 文本内容
			String content = pushFormVo.getContent();
			// 城市
			String areaCode = pushFormVo.getAreaCode();
			// 活动id
			String activityId = pushFormVo.getActivityId();
			// 赛事id
			String eventId = pushFormVo.getEventId();
			// 推送标题
			String sourceTitle = pushFormVo.getSourceTitle();
			String activityTitle = pushFormVo.getActivityTitle();
			String eventTitle = pushFormVo.getEventTitle();
			// 推送方式
			String pType = pushFormVo.getIsPush();
			// 定时推送时间
			String pushTime = pushFormVo.getPushTime();
			
			// log表推送对象id
			String pId = "";
			// log表推送对象title
			String pushTitle = "";
			if (Constants.pushMsgType.PUSH_TYPE_SOURCE.equals(pushType)) {
				pId = pushId;
				pushTitle = sourceTitle;
			} else if (Constants.pushMsgType.PUSH_TYPE_ACTIVITY.equals(pushType)) {
				pId = activityId;
				pushTitle = activityTitle;
			} else if (Constants.pushMsgType.PUSH_TYPE_EVENT.equals(pushType)) {
				pId = eventId;
				pushTitle = eventTitle;
			} else if (Constants.pushMsgType.PUSH_TYPE_TEXT.equals("text")){
				pId = pushService.saveText(content);
			}
			int py = Integer.valueOf(pType);
			// 推送方式
			if (Constants.isPush.PUSH_TYPE_UNLIMIT.equals(py)) {
				// 推送方式赋值3，为了区分活动推送和赛事推送推送方式(推送管理即时推送),4为推送管理定时推送
				pushPacket.sendPush(areaCode, pushType, pId, pushTitle, SessionUtil.currentUser().getUserId(), 3, "");
			} else if (Constants.isPush.PUSH_RYPE_TIME.equals(py)
					&& Constants.pushMsgType.PUSH_TYPE_SOURCE.equals(pushType)) {
				pushService.savePushLog(pId, areaCode, Constants.pushType.PUSH_SUBJECT, pushTitle, pushTime);
			} else if (Constants.isPush.PUSH_RYPE_TIME.equals(py)
					&& Constants.pushMsgType.PUSH_TYPE_ACTIVITY.equals(pushType)) {
				pushService.savePushLog(activityId, areaCode, Constants.pushType.PUSH_ACTIVITY, pushTitle, pushTime);
			} else if (Constants.isPush.PUSH_RYPE_TIME.equals(py)
					&& Constants.pushMsgType.PUSH_TYPE_EVENT.equals(pushType)) {
				pushService.savePushLog(eventId, areaCode, Constants.pushType.PUSH_EVENT, pushTitle, pushTime);
			}
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "推送成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("专题推送(PushService--sendPush)" + e.getMessage());
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "推送失败!"));
		}
		return "redirect:/push";
	}

	/**
	 * 
	 * <获取编辑列表><功能具体实现>
	 *
	 * @create：2015年11月27日 下午9:14:56
	 * @author： sl
	 * @param sourceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editSource")
	public String editSource(String sourceId, Model model) {
		Map<String, List<Push>> pMap = new HashMap<String, List<Push>>();
		try {
			pMap = pushService.getPushByPid(sourceId);
			Push p_ = pMap.get("pPush").get(0);
			p_.setGroupId(p_.getId());
			model.addAttribute("push", p_);
			model.addAttribute("pushC", pMap.get("cPush"));
			// 保存当前专题id
			model.addAttribute("currentId", p_.getId());
		} catch (Exception e) {
			logger.debug("获取编辑列表", e.getMessage());
		}
		return "/push/pushSourceNew";
	}

	/**
	 * 
	 * <根据id删除推送记录><功能具体实现>
	 *
	 * @create：2015年12月1日 下午2:48:51
	 * @author： sl
	 * @param id
	 */
	@RequestMapping(value = "deleteById")
	public String deleteById(String id) {
		try {
			pushService.deleteById(id);
		} catch (Exception e) {
			logger.debug("根据id删除推送记录", e.getMessage());
		}
		return "redirect:/push";
	}

	/**
	 * 
	 * <活动信息初始化><功能具体实现>
	 *
	 * @create：2015年12月2日 下午3:52:41
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "activity_dlg", method = RequestMethod.GET)
	public String activity_dlg(String areaCode, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<Activity> activityPage = null;
		activityPage = pushService.findActivityList(new PageRequest(page, size), 0, areaCode);
		model.addAttribute("data", activityPage);
		return "push/activity_dlg";
	}

	/**
	 * 
	 * <加载更多活动信息><功能具体实现>
	 *
	 * @create：2015年12月2日 下午3:54:18
	 * @author： sl
	 * @param count
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushActivity", method = RequestMethod.GET)
	@ResponseBody
	public String pushActivity(String count, String areaCode, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		int num = 2;
		Map<String, Object> viewResult = new HashMap<String, Object>();
		try {
			num = Integer.valueOf(count);
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<Activity> activityPage = null;
			activityPage = pushService.findActivityList(new PageRequest(page, size), num, areaCode);

			viewResult.put("pushs", activityPage);
		} catch (Exception e) {
			logger.debug("加载更多活动信息", e.getMessage());
		}

		return MyGson.getInstance().toJson(viewResult);
	}

	/**
	 * 
	 * <赛事信息初始化><功能具体实现>
	 *
	 * @create：2015年12月2日 下午4:15:12
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "event_dlg", method = RequestMethod.GET)
	public String event_dlg(String areaCode, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<Games> eventPage = null;
		eventPage = pushService.findEventList(new PageRequest(page, size), 0, areaCode);
		model.addAttribute("data", eventPage);
		return "push/event_dlg";
	}

	/**
	 * 
	 * <获取更多赛事信息><功能具体实现>
	 *
	 * @create：2015年12月2日 下午4:21:25
	 * @author： sl
	 * @param count
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pushEvent", method = RequestMethod.GET)
	@ResponseBody
	public String pushEvent(String count, String areaCode, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		int num = 2;
		Map<String, Object> viewResult = new HashMap<String, Object>();
		try {
			num = Integer.valueOf(count);
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<Games> eventPage = null;
			eventPage = pushService.findEventList(new PageRequest(page, size), num, areaCode);

			viewResult.put("pushs", eventPage);
		} catch (Exception e) {
			logger.debug("获取更多赛事信息", e.getMessage());
		}
		return MyGson.getInstance().toJson(viewResult);
	}
}
