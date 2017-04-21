package com.lc.zy.ball.boss.framework.statium.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.common.web.UpImageController;
import com.lc.zy.ball.boss.framework.statium.service.StatiumOaInfoService;
import com.lc.zy.ball.boss.framework.statium.vo.CrmUserVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumAuditLogVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.StatiumAuditLog;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.common.web.WebUtils;


@Controller
@RequestMapping(value = "/statiumOa")
public class StatiumOaController extends AbstractController {

	@Autowired
	private StatiumOaInfoService statiumOaInfoService;
	
	@Autowired
	private StatiumInfosMapper statiumInfosMapper;
	
	@Autowired
	private UpImageController upImageController;
	
	private Gson gson = new Gson();
	
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
	public String list(HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		int page = WebUtils.getPage(request);
		int pageSize = WebUtils.getPageSize(request);
		
		Page<StatiumInfos> statiumInfos = null;
		try {
			statiumInfos = statiumOaInfoService.list(new PageRequest(page,
					pageSize), searchParams);
			List<StatiumInfos> list = statiumInfos.getContent();
			for(StatiumInfos statiumInfo:list){
				Integer flag = statiumOaInfoService.isCrmUser(statiumInfo.getDgId().toString());
				statiumInfo.setRecommend(flag);
			}
			// searchParam用于分页时进行查询
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
			model.addAttribute("position");
			model.addAttribute("data", statiumInfos);
			//返回页数
			model.addAttribute("page", page+1);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error("oa道馆list:{}" + e.getMessage());
		}
		return "statium/statiumOaList";
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
		
		return "statium/statiumOaForm";
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
}
