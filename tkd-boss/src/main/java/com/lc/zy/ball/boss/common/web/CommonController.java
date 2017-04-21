package com.lc.zy.ball.boss.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.service.CommonService;
import com.lc.zy.ball.domain.oa.mapper.CoachMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.mapper.videoGroupMapper;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.CoachCriteria;
import com.lc.zy.ball.domain.oa.po.Holiday;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.ball.domain.oa.po.videoGroup;
import com.lc.zy.ball.domain.oa.po.videoGroupCriteria;
import com.lc.zy.common.Constants;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * 公共方法
 * 
 * @author liangc
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService = null;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbc = null;

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private CoachMapper coachMapper;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
	@Autowired
	private videoGroupMapper videoGroupMapper;

	@RequestMapping(value = "search_org_dlg", method = RequestMethod.GET)
	public String search_org_dlg(Model model, ServletRequest request) throws Exception {
		String orgTree = commonService.getOrgTreeOfCurrentUser();
		logger.debug("search_org_dlg : {}", orgTree);
		model.addAttribute("orgTree", orgTree);
		return "common/search_org_dlg";
	}

	@RequestMapping(value = "init_resume__dlg")
	public String initResume(Model model, String name, ServletRequest request) {
		logger.debug("search_statium_dlg : {}", "");
		return "common/init_resume__dlg";
	}

	@RequestMapping(value = "qiuyou_query_dlg")
	public String queryQiuyou(Model model, ServletRequest request) throws Exception {
		try {
			SsoUserCriteria criteria = new SsoUserCriteria();
			criteria.setMysqlOffset(0);
			criteria.setMysqlLength(20);
			List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
			model.addAttribute("users", users);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return "corps/qiuyou_query_dlg";
	}
	
	/**
	 * 
	 * <教练list初始化><功能具体实现>
	 *
	 * @create：2016年5月3日 下午6:52:50
	 * @author：sl
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "coach_query_dlg")
	public String queryCoach(Model model, ServletRequest request,String  statiumId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Coach> users = new ArrayList<Coach>();
			CoachCriteria coachCriteria = new CoachCriteria();
			coachCriteria.setMysqlLength(20);
			coachCriteria.setMysqlOffset(0);
			CoachCriteria.Criteria cri = coachCriteria.createCriteria();
			cri.andDgIdEqualTo(Integer.valueOf(statiumId));
			users = coachMapper.selectByExample(coachCriteria);
			model.addAttribute("users", users);
			model.addAttribute("statiumId", statiumId);
			model.addAttribute("action", "detail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查看教练失败: {}", e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查看教练失败");
		}
		return "statiumClass/coach_query_dlg";
	}
	
	/**
	 * 
	 * <查询道馆dlg><功能具体实现>
	 *
	 * @create：2016年5月6日
	 * @author：wangp
	 * 
	 */
	@RequestMapping(value = "statium_query_dlg")
	public String queryStatiuminfo(Model model, ServletRequest request) throws Exception {
		try {
			StatiumInfosCriteria statiumInfosCriteria =new StatiumInfosCriteria();
			StatiumInfosCriteria.Criteria cri =statiumInfosCriteria.createCriteria();
			//页面显示dgId大于100000的道馆 根据sql判断 
			cri.andDgIdGreaterThan(1000000);
			statiumInfosCriteria.setMysqlLength(20);
			statiumInfosCriteria.setMysqlOffset(0);
		    List<StatiumInfos> statiums = statiumInfosMapper.selectByExample(statiumInfosCriteria);
		    List<StatiumInfos> newStatiums=new ArrayList<StatiumInfos>();
//			for(StatiumInfos statiumInfos:statiums){
//				if(statiumInfos.getDgId()>=1000000){
//					newStatiums.add(statiumInfos);
//				}
//			}
			model.addAttribute("users", statiums);
		} catch (Exception e) {
			logger.debug("道馆list初始化:{}", e.getMessage());
		}
		return "statiumClass/statium_query_dlg";
	}
	
	/**
	 * 
	 * <查询下载的视频集><功能具体实现>
	 *
	 * @create：2016年12月23日 下午4:55:39
	 * @author：zzq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="videoGroup_query_dlg")
	public String queryVideoGroup(HttpServletRequest request,Model model){
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		
		//不影响文档结构故数据库操作不单独建service了
		
		Map<String,SearchFilter> filters = SearchFilter.parse(searchParams);
		videoGroupCriteria v1 = new videoGroupCriteria();
		Criterias.bySearchFilter(v1, filters.values());
		v1.setMysqlLength(size);
		v1.setMysqlOffset(page);
		List<videoGroup> groupList = videoGroupMapper.selectByExample(v1);
		model.addAttribute("groupList", groupList);
		return "video/group_query_dlg";
	}
	
	
	/**
	 * 
	 * <查询教练dlg><功能具体实现>
	 *
	 * @create：2016年5月3日 下午6:25:36
	 * @author：sl
	 * @param model
	 * @param username
	 * @param phone
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ajax_coach_query_dlg")
	@ResponseBody
	public String ajaxQiuyouQuery(Model model, String username, String phone, String statiumId, ServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			CoachCriteria coachCriteria = new CoachCriteria();
			CoachCriteria.Criteria criteria = coachCriteria.createCriteria();

			if (StringUtils.isNotBlank(username)) {
				criteria.andNameLike("%" + username + "%");
			}
			if (StringUtils.isNotBlank(phone)) {
				criteria.andPhoneLike("%" + phone + "%");
			}
			if (StringUtils.isNotBlank(statiumId)) {
				criteria.andDgIdEqualTo(Integer.valueOf(statiumId));
			}
			if (StringUtils.isBlank(username) && StringUtils.isBlank(phone)) {
				coachCriteria.setMysqlOffset(0);
				coachCriteria.setMysqlLength(20);
			}
			List<Coach> coachs = coachMapper.selectByExample(coachCriteria);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, coachs);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查询教练失败！");
			return MyGson.getInstance().toJson(result);
		}
	}
	
	@RequestMapping(value = "ajax_statium_query_dlg")
	@ResponseBody
	public String ajaxstatiumQuery(Model model, String a2, ServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			StatiumInfosCriteria.Criteria criteria = statiumInfosCriteria.createCriteria();
			if (StringUtils.isNotBlank(a2)) {
				criteria.andDgNameLike("%" + a2 + "%");
			}
			
			if (StringUtils.isBlank(a2)) {
				statiumInfosCriteria.setMysqlOffset(0);
				statiumInfosCriteria.setMysqlLength(20);
			}
			
			List<StatiumInfos> statiums = statiumInfosMapper.selectByExample(statiumInfosCriteria);
			result.put(Constants.Result.RESULT, true);
			result.put(Constants.Result.DATA, statiums);
			return MyGson.getInstance().toJson(result);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			result.put(Constants.Result.RESULT, false);
			result.put(Constants.Result.REASON, "查询道馆失败！");
			return MyGson.getInstance().toJson(result);
		}
	}
	
	/**
	 * 
	 * <根据名称查询视频集><功能具体实现>
	 *
	 * @create：2016年12月26日 上午10:00:12
	 * @author：zzq
	 * @param a2
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ajax_group_query_dlg")
	@ResponseBody
	public String groupQuery(String a2,HttpServletRequest request){
		Map<String,Object> results = new HashMap<String,Object>();
		try {
			videoGroupCriteria v1 = new videoGroupCriteria();
			v1.createCriteria().andVideoNameLike("%" + a2 + "%");
			
			int page = WebUtils.getPage(request);
			int pageSize = WebUtils.getPageSize(request);
			
			if(StringUtils.isNotEmpty(a2)){
				v1.setMysqlLength(pageSize);
				v1.setMysqlOffset(page);
			}
			
			List<videoGroup> list = videoGroupMapper.selectByExample(v1);
			
			results.put(Constants.Result.RESULT, true);
			results.put(Constants.Result.DATA, list);
			
			return MyGson.getInstance().toJson(results);
			
		} catch (Exception e) {
			
			results.put(Constants.Result.RESULT, false);
			results.put(Constants.Result.REASON, "查询视频集失败！");
			
			return MyGson.getInstance().toJson(results);
			
		}
		
	}

	private Integer checkWorkday(String startDate) throws Exception {
		// 是否是工作日0：否，1： 是
		Integer isWorkday = 1;
		// holiday为null是工作日，不为null节假日
		Holiday holiday = commonService.selectByPrimaryKey(Holiday.class, startDate);
		if (holiday != null) {// 节假日
			isWorkday = 0;
		}
		return isWorkday;
	}

}
