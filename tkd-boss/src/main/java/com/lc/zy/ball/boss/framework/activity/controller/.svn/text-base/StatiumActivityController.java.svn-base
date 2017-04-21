package com.lc.zy.ball.boss.framework.activity.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.activity.service.StatiumActivityService;
import com.lc.zy.ball.boss.framework.activity.vo.StatiumActivityVo;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.boss.framework.system.service.UserService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.OaContestActivity;
import com.lc.zy.ball.domain.oa.po.StatiumActivity;
import com.lc.zy.common.cache.RedisService;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.web.WebUtils;

  /**
   * @create：2016年7月22日 下午4:42:21
   * @author：zzq
   */
	@Controller
	@RequestMapping(value = "/activity")
	public class StatiumActivityController extends AbstractController {

	    private static final Logger logger = LoggerFactory.getLogger(StatiumActivityController.class);

	    @Autowired
	    private StatiumActivityService activityService;

	    @Autowired
	    private RedisService redisService;

	    @Autowired
	    private SsoUserService ssoUserService;
	    
	    @Autowired
	    private UserService userService;
	    @Autowired
	    private QueueProducer queueProducer;
	    
	    
	    /**
	     * <活动列表><功能具体实现>
	     * 
	     * @param model
	     * @param request
	     * @param redirectAttributes
	     * @return
	     * @throws Exception
	     * @author zzq
	     * @date 2015年10月15日 上午11:30:28
	     */
	    @RequestMapping(value = {"","/","/list"})
	    public String list(HttpServletRequest request, Model model) {
			Map<String,Object> searchParams=Servlets.getParametersStartingWith(request, "search_");
			logger.debug(searchParams.toString());
			int page=WebUtils.getPage(request);
			int pagesize=WebUtils.getPageSize(request);
			Page<StatiumActivity> statiumActivity=null;
			try {
				//传 page pagesize用来分页
				 statiumActivity=activityService.list(new PageRequest(page, pagesize), searchParams);
				 System.out.println(statiumActivity.getNumber()+"***"+statiumActivity.getNumberOfElements()+"***"+statiumActivity.getSize()+"***"
						 +statiumActivity.getTotalElements()+"***"+statiumActivity.getTotalPages());
				 model.addAttribute("data", statiumActivity);
				 String searchParamsStr = Servlets.encodeParameterStringWithPrefix(searchParams, "search_");
				 model.addAttribute("searchParams", searchParamsStr);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("活动list:{}" + e.getMessage());
			}
			return "/activity/activityList";
		}
	    
	    /**
	     * <增加活动><功能具体实现>
	     * @create：2016年7月22日 下午7:08:57
	     * @author：zzq
	     * @param request
	     * @param model
	     * @return
	     */
	    @RequestMapping(value="/createForm")
	    public String createForm(HttpServletRequest request,Model model){
	    	System.out.println("创建新的活动");
	    	System.out.println(model);
	    	model.addAttribute("action", "create");
	    	return "activity/activityForm";
	    }
	    
	    /**
	     * <保存活动><功能具体实现>
	     *
	     * @create：2016年7月23日 上午11:34:58
	     * @author：zzq
	     * @param myForm
	     * @param request
	     * @return
	     */
	    @RequestMapping(value="save",method=RequestMethod.POST)
	    public String save(StatiumActivityVo myForm,HttpServletRequest request){
	    	System.out.println("进入保存活动");
	    	try {
	    		if (myForm.getPhotos() != null && myForm.getPhotos().length> 0) {
					String photos = "";
					for (String p : myForm.getPhotos()) {
						if (StringUtils.isNotEmpty(p)) {
							photos = photos + p + "__";
						}
					}
					if (StringUtils.isNotEmpty(photos)) {
						photos = photos.substring(0, photos.length());
					}
					myForm.setPhoto(photos);
				}
	    		if(myForm.getExpiryData().length()>19){
	    			String expiryData=myForm.getExpiryData();
	    			myForm.setExpiryData(expiryData.substring(0, 19));
	    		}
				activityService.createOrUpdateActivity(myForm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 logger.error("创建活动失败: {}", e.getMessage());
		         logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
	        return "redirect:/activity";
	    }
	    
	    /**
	     * <删除活动><功能具体实现>
	     * @author zzq
	     * @param myForm
	     * @param request
	     * @return
	     */
	    @RequestMapping(value="/deleteActivity/{activityId}" ,method=RequestMethod.POST)
	    public String delete(@PathVariable String activityId, Model model){
	    	System.out.println("删除的id为"+activityId);
	    	activityService.deleteActivity(activityId);
	    	return "redirect:/activity/list";
	    }
	    
	    /**
	     * <获取活动><功能具体实现>
	     * @create：2016年7月23日 下午3:54:41
	     * @author：zzq
	     * @param id
	     * @param model
	     * @return
	     */
	    @RequestMapping(value="/detailActivity",method=RequestMethod.GET)
	    public String detailActivity(String id,Model model,HttpServletRequest request){
	    	StatiumActivity statiumActivity =new StatiumActivity();
	    	try {
	    		statiumActivity = activityService.getActivityById(id);
	    		if(statiumActivity.getPrice()!=0){
	    			int oldPrice = statiumActivity.getPrice();
	    			statiumActivity.setPrice(oldPrice/100);
	    		}
	    		model.addAttribute("activity", statiumActivity);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return "activity/activityForm";
	    }
	    
	    /**
	     * 
	     * <获取赛事活动列表><功能具体实现>
	     *
	     * @create：2017年2月13日 下午6:30:50
	     * @author：zzq
	     * @return
	     */
	    @RequestMapping(value="/contestList")
	    public String getContestList(Model model,HttpServletRequest request){
	    	int page=WebUtils.getPage(request);
			int pagesize=WebUtils.getPageSize(request);
	    	Page<OaContestActivity> list = activityService.getContestActivityList(new PageRequest(page, pagesize));
	    	model.addAttribute("data", list);
	    	return "/activity/contestList";
	    }
	    
	    /**
	     * 
	     * <竞赛活动页面初始化><功能具体实现>
	     *
	     * @create：2017年2月14日 上午9:34:28
	     * @author：zzq
	     * @return
	     */
	    @RequestMapping(value="/contestForm")
	    public String initContestForm(){
	    	return "/activity/contestForm";
	    }
	    
	    /**
	     * 
	     * <保存竞赛活动><功能具体实现>
	     *
	     * @create：2017年2月14日 下午2:11:53
	     * @author：zzq
	     * @param myForm
	     * @return
	     */
	    @RequestMapping(value="/saveContestForm",method=RequestMethod.POST)
	    public String saveContest(OaContestActivity myForm){
	    	logger.debug("竞赛活动"+myForm);
	    	activityService.saveContest(myForm);
	    	return "redirect:/activity/contestList";
	    }
	    
	    /**
	     * 
	     * <修改竞赛页面初始化><功能具体实现>
	     *
	     * @create：2017年2月14日 下午2:12:30
	     * @author：zzq
	     * @param id
	     * @return
	     */
	    @RequestMapping(value="/detailContest")
	    public String detailContest(String id,Model model){
	    	OaContestActivity contest = new OaContestActivity();
	    	contest = activityService.getContest(id);
			model.addAttribute("contest", contest);
	    	return "/activity/contestForm";
	    }
	    
	    /**
	     * 
	     * <删除竞赛活动><功能具体实现>
	     *
	     * @create：2017年2月14日 下午3:14:25
	     * @author：zzq
	     * @param id
	     * @return
	     */
	    @RequestMapping(value="/deleteContest/{id}")
	    public String deleteContest(@PathVariable String id){
	    	activityService.deleteContest(id);
	    	return "redirect:/activity/contestList";
	    }
	    
	}
	
	

