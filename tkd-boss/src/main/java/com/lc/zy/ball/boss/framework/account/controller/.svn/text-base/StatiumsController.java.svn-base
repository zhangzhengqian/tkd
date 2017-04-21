package com.lc.zy.ball.boss.framework.account.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.Sequence;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.common.web.UpImageController;
import com.lc.zy.ball.boss.framework.account.service.StatiumsService;
import com.lc.zy.ball.boss.framework.ssouser.vo.SsoUserVo;
import com.lc.zy.ball.boss.framework.statium.service.StatiumOaInfoService;
import com.lc.zy.ball.boss.framework.statium.vo.CrmUserVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumAuditLogVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumAuditLog;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value="/statiums")
public class StatiumsController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(StatiumsController.class);
	
	@Autowired
	private StatiumOaInfoService statiumOaInfoService;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
	@Autowired
	private UpImageController upImageController;
	
	@Autowired
	private StatiumsService statiumsService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	private Gson gson = new Gson();
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
	@RequestMapping(value = { "", "/","/list" })
	public String list(HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search");
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		
		Page<StatiumInfosVo> statiumInfos = null;
		try {
			statiumInfos = statiumsService.list(new PageRequest(page,
					pageSize), searchParams);
			List<StatiumInfosVo> list = statiumInfos.getContent();
			for(StatiumInfosVo statiumInfo:list){
				Integer flag = statiumOaInfoService.isCrmUser(statiumInfo.getDgId().toString());
				statiumInfo.setRecommend(flag);
			}
			model.addAttribute("position");
			model.addAttribute("data", statiumInfos);
			//返回页数
			model.addAttribute("page", page+1);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error("oa道馆list:{}" + e.getMessage());
		}
		return "accountMagger/statiumsList";
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
		System.out.println("创建新的道馆");
		return "statium/statiumOaForm";
	}

	/**
	 * 
	 * <道馆编辑信息初始化><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:15:19
	 * @author：sl
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detailForm")
	public String detailForm(HttpServletRequest request, Model model, String id,Integer page,Integer pageSize) {
		System.out.println("场馆的详细信息");
		StatiumInfos statiumInfos = new StatiumInfos();
		StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
		List<StatiumAuditLog> auditLog = new ArrayList<StatiumAuditLog>();
		try {
			statiumInfos = statiumOaInfoService.getStatiumInfosById(id);
			statiumAuditLog = statiumOaInfoService.getauditLog(id);
			auditLog = statiumOaInfoService.getAuditLogList(id);
		} catch (Exception e) {
			logger.debug("道馆编辑信息初始化:{}", e.getMessage());
		}
     	model.addAttribute("auditLog", auditLog);
		model.addAttribute("statium", statiumInfos);
		model.addAttribute("statiumAuditLog", statiumAuditLog);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		
		return "accountMagger/statiumInfoForm";
	}

	/**
	 * 
	 * <OA道馆信息保存><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:11:35
	 * @author：sl
	 * @param myForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(StatiumInfosVo myForm, HttpServletRequest request) {
		//返回分页的效果
		Integer page = 1;
		Integer pageSize = 10;
		if(myForm.getPage()!=null&&myForm.getPageSize()!=null){
			 page = myForm.getPage();
			 pageSize = myForm.getPageSize();
		}
		
		try {
			logger.debug("保存道馆");
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
			statiumOaInfoService.save(myForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("OA道馆信息保存:{}", e.getMessage());
		}
		return "redirect:/statiumOa?page="+page+"&size="+pageSize;
	}
	
	/**
	 * 
	 * <返回道馆列表页面带回分页效果><功能具体实现>
	 *
	 * @create：2017年2月4日 下午12:00:05
	 * @author：zzq
	 * @param myForm
	 * @return
	 */
	@RequestMapping(value = "/returnToList")
	public String returnToList(Integer page,Integer pageSize){
		return "redirect:/statiumOa?page="+page+"&size="+pageSize;
	}

	/**
	 * 
	 * <删除OA场馆><功能具体实现>
	 *
	 * @create：2016年5月18日 下午6:46:10
	 * @author：sl
	 * @param statiumId
	 * @return
	 */
	@RequestMapping(value = "/deleteStatiumOa/{statiumId}", method = RequestMethod.POST)
	public String deleteStatiumOa(@PathVariable String statiumId, Model model) {
		try {
			statiumOaInfoService.deleteStatiumOa(statiumId);
			System.out.println("删除场馆a");
		} catch (Exception e) {
			logger.debug("删除OA场馆:{}", e.getMessage());
		}
		return "redirect:/statiumOa";
	}

	/**
	 * 
	 * <设置场馆为推荐><功能具体实现>
	 *
	 * @create：2016年8月12日 上午11:39:41
	 * @author：zzq
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/recommend")
	public String recommend(HttpServletRequest request, String dgid,
			Model model, Integer recommend) {
		try {
			statiumOaInfoService.setRecommend(dgid, recommend);
		} catch (Exception e) {
			logger.debug("设置推荐场馆", e.getMessage());
		}
		return "redirect:/statiumOa";
	}
	
	/**
	 * 
	 * <保存审核流水><功能具体实现>
	 *
	 * @create：2016年9月5日 上午11:49:58
	 * @author：zzq
	 * @param statiumAuditLog
	 * @return
	 */
	@RequestMapping(value="/statiumAudit",method=RequestMethod.POST)
	public String statiumAudit(StatiumAuditLogVo statiumAuditLogVo){
		logger.debug(statiumAuditLogVo.toString());
		Integer page = statiumAuditLogVo.getPage();
		Integer pageSize = statiumAuditLogVo.getPageSize();
		page = page == null?0:page;
		pageSize = pageSize == null?10:pageSize;
		if(statiumAuditLogVo.getStatiumId()!=null){
			StatiumAuditLog audit = new StatiumAuditLog();
			BeanUtils.copyProperties(statiumAuditLogVo, audit);
			statiumOaInfoService.saveAuditLog(audit);
		}
		return "redirect:/statiumOa?page="+page+"&size="+pageSize;
	}
	
	/**
	 * 
	 * <生成二维码><功能具体实现>
	 *
	 * @create：2016年10月14日 下午5:39:44
	 * @author：zzq
	 * @param dgId
	 * @return
	 */
	@RequestMapping(value="/buildQrCode")
	public String buildQrCode(String dgId,Model model){
		logger.debug(dgId);
		String id ="";
		try {
			String barUri = upImageController.uploadQRCode("tkd" + dgId);
			//保存二维码url
			id = statiumOaInfoService.addQrCode(barUri, dgId);
		} catch (Exception e) {
			logger.error("上传二维码失败"+e.getMessage());
		}
		return "redirect:/statiumOa/detailForm?id="+id;
	}
	
	/**
     * 
     * <查看或添加场馆管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午4:33:52
     * @author： liangsh
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "crmManagerForm", method = RequestMethod.GET)
    public String crmManagerForm(Model model, String statiumId,Integer page,Integer pageSize) {
        logger.debug("crmManagerForm : " + statiumId.toString());
        try {
            // 查看该道馆是否存在管理员，不存在进入添加页，存在则进入展示页
            CrmUser user = statiumOaInfoService.detail(statiumId);
            //modify by zzq 2017-2-4 增加道馆名称显示
            String dgName = statiumOaInfoService.findDgName(statiumId);
            if(user==null){
            	user = new CrmUser();
            	user.setStatiumId(statiumId);
            	model.addAttribute("action", "add");
            }
            else{
            	model.addAttribute("action", "update");
            	
            }
            logger.debug("道馆管理员是否存在，{}",user!=null);
            model.addAttribute("user", user);
            model.addAttribute("dgName", dgName);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "statium/crmUserForm";
    }
	
    /**
     * 
     * <添加道馆管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午5:24:42
     * @author： liangsh
     * @param myForm
     * @param priceTemps
     * @param request
     * @return
     */
    @RequestMapping(value = "saveCrmUser", method = RequestMethod.POST)
    public String saveCrmUser(CrmUserVo vo, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        logger.debug("saveCrmUser : " + vo.toString());
        //返回分页的效果
      		Integer page = 1;
      		Integer pageSize = 10;
      		if(StringUtils.isNotEmpty(vo.getPage().toString())&&StringUtils.isNotEmpty(vo.getPageSize().toString())){
      			 page = vo.getPage();
      			 pageSize = vo.getPageSize();
      		}
        try {
        	CrmUser myForm = new CrmUser();
        	BeanUtils.copyProperties(vo, myForm);
        	
            Date now = new Date();
            String userId = SessionUtil.currentUserId();
            if (StringUtils.isBlank(myForm.getUserId())) {
                myForm.setCb(userId);
                myForm.setCreateTime(now);
                myForm.setStatus(Constants.UserStatus.active);
                statiumOaInfoService.addCrmUser(myForm);
            }else{
            	myForm.setCb(userId);
                myForm.setCreateTime(now);
                myForm.setStatus(Constants.UserStatus.active);
                statiumOaInfoService.upateUser(myForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redirect:/statiumOa?page="+page+"&size="+pageSize;
    }
    
    /**
     * 
     * <判断用户名是否存在><功能具体实现>
     *
     * @create：2016年11月3日 下午3:35:43
     * @author：zzq
     * @param loginName
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="checkLoginName",method=RequestMethod.POST)
    @ResponseBody
    public String checkLoginName(String loginName,String userId,RedirectAttributes redirectAttributes){
    	Map<String,Object> map = new HashMap<String,Object>();
    	logger.debug("{loginName}"+loginName);
    	try {
			if(!statiumOaInfoService.checkCrmUser(loginName,userId)){
				map.put(Constants.Result.RESULT, false);
				map.put(Constants.Result.REASON, "用户名重复，请重新输入");
			}
			else{
				map.put(Constants.Result.RESULT, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Constants.Result.RESULT, false);
			return gson.toJson(map);
		}
    	logger.debug(""+map);
    	logger.debug(""+gson.toJson(map));
    	return gson.toJson(map);
    }
    
    /**
     * 
     * <馆长电话是否录入过><功能具体实现>
     *
     * @create：2017年2月4日 下午5:02:55
     * @author：zzq
     * @param tel
     * @return
     */
    @RequestMapping(value="/checkPhone")
    @ResponseBody
    public boolean checkPhone(String tel,Integer dgId){
    	boolean flag = true;
    	if(StringUtils.isNotEmpty(tel)){
    		flag = statiumOaInfoService.checkTel(tel,dgId);
    	}
    	return flag;
    }
    
    /*@RequestMapping(value="/createUserForm")
    public String createForm(Model model,Integer statiumId){
    	model.addAttribute("statiumId", statiumId);
    	
		return "/accountMagger/statiumsUserList";
    }*/
    
    //@RequestMapping(value = "user")
    @RequestMapping(value="/createUserForm")
	public String ssoUserList(HttpServletRequest request, Model model, Integer statiumId)
			throws Exception {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		// 处理城市
		String areaCode = (String) searchParams.get("LIKE_city");
		String areaCodeRtn = null;
		if (StringUtils.isNotEmpty(areaCode)) {
			areaCodeRtn = new String(areaCode);
			if (areaCode.endsWith("0000")) {
				searchParams.put("LIKE_city", areaCode.substring(0, 2));
			} else if (areaCode.endsWith("00")) {
				searchParams.put("LIKE_city", areaCode.substring(0, 4));
			}
		}
		// bhg 时间参数直接传递回去，不做类型转变
		// 将搜索条件编码成字符串，用于排序，分页的URL
		if (StringUtils.isNotEmpty(areaCodeRtn)) {
			searchParams.put("LIKE_city", areaCodeRtn);
		}
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));

		// 将注册日期字符串转换成对象,这里注意，
		// 页面上 name=search_GTE_registTime ,到了这里，需要去掉前缀
		parseDate(searchParams, "GTE_registTime");
		parseDate(searchParams, "LTE_registTime");

		// 将激活日期字符串转换成对象,这里注意，
		// 页面上 name=search_GTE_createTime ,到了这里，需要去掉前缀
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");

		logger.debug(searchParams.toString());
		// 分页
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<SsoUser> ssoUserPage = null;
		try {
			ssoUserPage = statiumsService.findSsoUserList(new PageRequest(page,
					size), searchParams, true, true,statiumId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssoUserList error:" + e.getMessage());
		}
		model.addAttribute("data", ssoUserPage);

		return "/accountMagger/statiumsUserList";
	}
    
    
    
    /**
	 * 
	 * <删除用户>
	 *
	 * @create：2015年8月4日 下午2:26:44
	 * @author： sl
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String deleteSsoUser(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				statiumsService.deleteSsoUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("deleteSsoUser error:" + e.getMessage());
			}
		}
		return "redirect:/ssouser/user";
	}

	/**
	 * 
	 * <冻结用户>
	 *
	 * @create：2015年8月4日 下午3:26:55
	 * @author： sl
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "freezeSsoUser/{id}", method = RequestMethod.POST)
	public String freezeSsoUser(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				statiumsService.freezeSsoUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("freezeSsoUser error:" + e.getMessage());
			}
		}
		return "redirect:/ssouser/user";
	}

	/**
	 * 
	 * <解冻用户>
	 *
	 * @create：2015年8月4日 下午3:27:08
	 * @author： sl
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "unfreezeSsoUser/{id}", method = RequestMethod.POST)
	public String unfreezeSsoUser(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				statiumsService.unfreezeSsoUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("unfreezeSsoUser error:" + e.getMessage());
			}
		}
		return "redirect:/ssouser/user";
	}

	/**
	 * 
	 * <冻结用户>
	 *
	 * @create：2015年8月4日 下午3:26:55
	 * @author： sl
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "frozen/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String frozen(@PathVariable String id) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				statiumsService.freezeSsoUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("freezeSsoUser error:" + e.getMessage());
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.Result.RESULT, true);
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <解冻用户>
	 *
	 * @create：2015年8月4日 下午3:27:08
	 * @author： sl
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "unfrozen/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String unfrozen(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				statiumsService.unfreezeSsoUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("unfreezeSsoUser error:" + e.getMessage());
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.Result.RESULT, true);
		return MyGson.getInstance().toJson(result);
	}

	/**
	 * 
	 * <查看用户信息>
	 *
	 * @create：2015年8月4日 下午5:25:59
	 * @author： sl
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view")
	public String view(String id, Model model, HttpServletRequest request) {
		SsoUser ssoUser = new SsoUser();
		if (StringUtils.isNotEmpty(id)) {
			try {
				// 获取用户信息
				ssoUser = statiumsService.viewSsoUser(id);
				model.addAttribute("id", id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("viewSsoUser error:" + e.getMessage());
			}
		}
		model.addAttribute("ssoUser", ssoUser);
		return "/accountMagger/userView";
	}

	/**
	 * 
	 * <用户添加页面初始化>
	 *
	 * @create：2015年8月4日 下午8:06:57
	 * @author： sl
	 * @return
	 */
	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public String sign() {
		return "/ssouser/ssoUserForm";
	}

	/**
	 * 
	 * <用户添加>
	 *
	 * @create：2015年8月4日 下午8:50:51
	 * @author： sl
	 * @param myForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ssoUserForm")
	public String ssoUserForm(SsoUserVo myForm, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			SsoUser ssoUser = new SsoUser();
			// 头像
			ssoUser.setPhoto(myForm.getPhoto());
			// 昵称
			ssoUser.setNickName(myForm.getNickName());
			// 真实姓名
			ssoUser.setName(myForm.getName());
			// 性别
			ssoUser.setSex(myForm.getSex());
			// 所在地区
			ssoUser.setCity(myForm.getCity());
			// 家庭住址
			ssoUser.setAddress(myForm.getAddress());
			// 身份证号
			ssoUser.setCardId(myForm.getCardId());
			// 手机号
			ssoUser.setPhone(myForm.getPhone());
			// 电子邮箱
			ssoUser.setEmail(myForm.getEmail());
			// 激活状态
			ssoUser.setActiveState(0);
			// 球友号
			ssoUser.setTkdNo(Sequence.genQiuyouNumber());
			// 注册时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			ssoUser.setRegistTime(time);
			statiumsService.ssoUserForm(ssoUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssoUserForm error:" + e.getMessage());
		}
		return "redirect:/ssouser/user";
	}

	/**
	 * 
	 * <更新>
	 *
	 * @create：2015年8月5日 下午8:32:00
	 * @author： sl
	 * @param myForm
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateSsoUser")
	public String updateSsoUser(SsoUserVo myForm, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(myForm.getId());
			// 头像
			ssoUser.setPhoto(myForm.getPhoto());
			// 球友号
			ssoUser.setTkdNo(myForm.getTkdNo());
			// 昵称
			ssoUser.setNickName(myForm.getNickName());
			// 真实姓名
			ssoUser.setName(myForm.getName());
			// 性别
			ssoUser.setSex(myForm.getSex());
			// 会员等级
			ssoUser.setLevel(myForm.getLevel());
			// 出生日期
			ssoUser.setBirthday(myForm.getBirthday());
			// 所在地区
			ssoUser.setCity(myForm.getCity());
			// 家庭住址
			ssoUser.setAddress(myForm.getAddress());
			// 身份证号
			ssoUser.setCardId(myForm.getCardId());
			// 手机号
			ssoUser.setPhone(myForm.getPhone());
			// 电子邮箱
			ssoUser.setEmail(myForm.getEmail());
			// 爱好
			ssoUser.setSign(myForm.getSign());
			// 更新时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			ssoUser.setUpdateTime(time);
			statiumsService.updateSsoUser(ssoUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssoUserForm error:" + e.getMessage());
		}
		return "redirect:/ssouser/user";
	}

	/**
	 * 
	 * <导出excel>
	 *
	 * @create：2015年8月6日 上午11:42:26
	 * @author： sl
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value = "exportExcel")
	public void exportExcel(Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		// 处理城市
		String areaCode = (String) searchParams.get("LIKE_city");
		if (StringUtils.isNotEmpty(areaCode)) {
			if (areaCode.endsWith("0000")) {
				searchParams.put("LIKE_city", areaCode.substring(0, 2));
			} else if (areaCode.endsWith("00")) {
				searchParams.put("LIKE_city", areaCode.substring(0, 4));
			}
		}

		// 将注册日期字符串转换成对象,这里注意，
		// 页面上 name=search_GTE_registTime ,到了这里，需要去掉前缀
		parseDate(searchParams, "GTE_registTime");
		parseDate(searchParams, "LTE_registTime");

		// 将激活日期字符串转换成对象,这里注意，
		// 页面上 name=search_GTE_createTime ,到了这里，需要去掉前缀
		parseDate(searchParams, "GTE_createTime");
		parseDate(searchParams, "LTE_createTime");

		logger.debug(searchParams.toString());

		List<SsoUser> ssoUserList = new ArrayList<SsoUser>();
		Page<SsoUser> ssoUserPage = statiumsService.findSsoUser(null,
				searchParams, false, false);
		ssoUserList = ssoUserPage.getContent();

		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("导出用户数据");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		// 指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String title = "用户列表";
		String[] headers = { "编号::qiuyouno", "用户昵称::nickName", "用户性别::sex",
				"注册手机::phone", "所在城市::city", "用户状态::state",
				"注册来源::registSource", "注册时间::registTime", "激活状态::activeState",
				"激活时间::createTime" };

		excelUtil.exportExcel(title, headers, ssoUserList,
				response.getOutputStream(), "yyyy-MM-dd");
	}

	@RequestMapping(value = "sendMsg", method = RequestMethod.POST)
	@ResponseBody
	public String sendMsg(String phone, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String username = SessionUtil.currentUsername();
			List<String> allowUsernames = new ArrayList<String>();
			allowUsernames.add("zhangying");
			allowUsernames.add("liuxu1");
			allowUsernames.add("houxiaomin");
			allowUsernames.add("kfsuijinfeng");
			if (!allowUsernames.contains(username)) {
				throw new Exception("账户非法");
			}
			messageUtil.sendSms(phone, msg);
			logger.debug("操作者{},手机号{},内容{}", username, phone, msg);
			result.put("result", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", false);
		}
		return MyGson.getInstance().toJson(result);
	}

	@RequestMapping(value = "addMarketUser/{id}")
	public String addMarketUser(@PathVariable String id, Model model,
			RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				if (statiumsService.checkMarketUser(id)) {
					statiumsService.addMarketUser(id);
				} else {
					redirectAttributes.addFlashAttribute("message",
							FlashAttributeUtil.build(true, "添加失败！"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("deleteSsoUser error:" + e.getMessage());
			}
		}
		return "redirect:/ssouser/user";
	}
	
	/**
	 * 
	 * <解绑道馆><功能具体实现>
	 *
	 * @create：2016年8月17日 下午6:37:29
	 * @author：zzq
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="unbundling/{id}")
	public String unbundling(@PathVariable String id, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			SsoUser ssoUser = statiumsService.unbundling(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/ssouser/user";
	}
}
