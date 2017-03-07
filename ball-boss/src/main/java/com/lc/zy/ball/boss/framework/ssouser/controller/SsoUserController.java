package com.lc.zy.ball.boss.framework.ssouser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.Sequence;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.accounts.service.AccountService;
import com.lc.zy.ball.boss.framework.ssouser.service.CoachService;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.boss.framework.ssouser.vo.AccountVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.FeedbackVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.SsoUserVo;
import com.lc.zy.ball.boss.framework.ssouser.vo.UserIntegralVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.Coacher;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.ExcelUtil;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.PostRequest;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;


@Controller
@RequestMapping(value = "/ssouser")
public class SsoUserController extends AbstractController {

	// log
	private static final Logger logger = LoggerFactory.getLogger(SsoUserController.class);

	// 注入service
	@Autowired
	private SsoUserService ssoUserService;

    @Autowired
    private CoachService coachService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageUtil messageUtil;
    
	@Resource(name="configs")
	private Map<String, String> configs;
	
	@Autowired
    private QueueProducer queueProducer;
	
	

	/**
	 * 
	 * <用户页面初始化>
	 *
	 * @create：2015年8月4日 上午10:25:10
	 * @author： sl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "user")
	public String ssoUserList(HttpServletRequest request,Model model)
			throws Exception {
		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
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
		//bhg 时间参数直接传递回去，不做类型转变
		// 将搜索条件编码成字符串，用于排序，分页的URL
		if (StringUtils.isNotEmpty(areaCodeRtn)) {
			searchParams.put("LIKE_city", areaCodeRtn);
		}
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

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
			ssoUserPage = ssoUserService.findSsoUserList(new PageRequest(page, size), searchParams, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssoUserList error:" + e.getMessage());
		}
		model.addAttribute("data", ssoUserPage);
		
		return "/ssouser/ssoUserList";
	}
	
	@RequestMapping(value="sendCoupon/{id}/{city}")
	@ResponseBody
	public String sendCoupon(@PathVariable String id,@PathVariable String city,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Map<String, Object> result = ssoUserService.receivingCouponRegister(id, city);
		return MyGson.getInstance().toJson(result);
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
				ssoUserService.deleteSsoUserById(id);
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
				ssoUserService.freezeSsoUser(id);
				//冻结用户时同时冻结教陪
	            Coacher info = coachService.findBySsoUserId(id);
	            if(info != null){
		            coachService.updateStatus(info.getId(),"1");
	            }
	            queueProducer.push(QueueNames.SYNC_COACH_QUEUE, "del:"+id);
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
				ssoUserService.unfreezeSsoUser(id);
				//解冻用户时同时解冻教陪
				//冻结用户时同时冻结教陪
	            Coacher info = coachService.findBySsoUserId(id);
	            if(info != null){
		            coachService.updateStatus(info.getId(),"2");
	            }
	            queueProducer.push(QueueNames.SYNC_COACH_QUEUE, id);
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
				ssoUserService.freezeSsoUser(id);
				//冻结用户时同时冻结教陪
	            Coacher info = coachService.findBySsoUserId(id);
	            if(info != null){
		            coachService.updateStatus(info.getId(),"1");
	            }

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("freezeSsoUser error:" + e.getMessage());
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
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
				ssoUserService.unfreezeSsoUser(id);
				//解冻用户时同时解冻教陪
				//冻结用户时同时冻结教陪
	            Coacher info = coachService.findBySsoUserId(id);
	            if(info != null){
		            coachService.updateStatus(info.getId(),"2");
	            }
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("unfreezeSsoUser error:" + e.getMessage());
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
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
	public String view(String id, Model model,HttpServletRequest request) {
		SsoUser ssoUser = new SsoUser();
		List<Order> orders = new ArrayList<Order>();
		// 用户消费金额
		Integer orderAmount = 0;
		if (StringUtils.isNotEmpty(id)) {
			try {
				// 获取用户信息
				ssoUser = ssoUserService.viewSsoUser(id);
				// 根据用户id获取订单表用户消费金额
				orders = ssoUserService.findOrdersById(id);
				
				Double accountAmount = accountService.totalAmountAccount(id);
				Double qiuyouAmount = accountService.totalAmountQiuyou(id);
				Double bonusAmount = accountService.totalAmountBonus(id);
				Integer integralAccount = accountService.getUserIntegral(id);
				List<UserIntegralVo> integrals = accountService.getUserIntegralLog(id, null, 0, 10);
				model.addAttribute("qiuyouAmount", qiuyouAmount);
				model.addAttribute("accountAmount", accountAmount+bonusAmount);
				model.addAttribute("integralAccount", integralAccount);
				model.addAttribute("integrals", integrals);
				
				if (orders != null) {
					for (Order order : orders) {
						orderAmount = orderAmount + order.getFinalFee();
					}
				}
				
				AccountVo data = accountService.viewAccount(id,null,null,null);
				model.addAttribute("data", data);
				model.addAttribute("id", id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("viewSsoUser error:" + e.getMessage());
			}
		}
		
		model.addAttribute("ssoUser", ssoUser);
		model.addAttribute("birth",ssoUser.getBirthday()!=null?DateUtil.formatDate(ssoUser.getBirthday(), "yyyy-MM-dd"):"");
		model.addAttribute("orderAmount", orderAmount);
		return "/ssouser/ssoUserView";
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
	public String ssoUserForm(SsoUserVo myForm, Model model, HttpServletRequest request, HttpServletResponse response) {
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
			ssoUser.setQiuyouno(Sequence.genQiuyouNumber());
			// 注册时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date time = sdf.parse(sdf.format(new Date()));
			ssoUser.setRegistTime(time);
			ssoUserService.ssoUserForm(ssoUser);
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
	public String updateSsoUser(SsoUserVo myForm, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			SsoUser ssoUser = new SsoUser();
			ssoUser.setId(myForm.getId());
			// 头像
			ssoUser.setPhoto(myForm.getPhoto());
			// 球友号
			ssoUser.setQiuyouno(myForm.getQiuyouno());
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
			ssoUserService.updateSsoUser(ssoUser);
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
	public void exportExcel(Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		// 根据查询条件查询数据
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
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
		Page<SsoUser> ssoUserPage = ssoUserService.findSsoUserList(null, searchParams, false, false);
		ssoUserList = ssoUserPage.getContent();

		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("导出用户数据");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		// 指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String title = "用户列表";
		String[] headers = { "球友号::qiuyouno", "用户昵称::nickName", "用户性别::sex", "注册手机::phone", "所在城市::city",
				"会员等级::level", "用户类型::property", "当前积分::integral", "金币余额::goldAmount", "用户状态::state",
				"注册来源::registSource", "注册时间::registTime", "激活状态::activeState", "激活时间::createTime" };

		excelUtil.exportExcel(title, headers, ssoUserList, response.getOutputStream(), "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * <反馈管理><功能具体实现>
	 * @param request
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年1月12日 下午2:36:05
	 */
	@RequestMapping(value = "feedback")
	public String feedbackList(HttpServletRequest request,Model model){
		// 分页
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<FeedbackVo> feedbackPage = null;
		try {
			feedbackPage = ssoUserService.findFeedbackList(new PageRequest(page, size));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("feedbackList error:" + e.getMessage());
		}
		model.addAttribute("data", feedbackPage);
		return "/ssouser/feedbackList";
	}
	
	@RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    @ResponseBody
    public String sendMsg(String phone,String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	String username = SessionUtil.currentUsername();
        	List<String> allowUsernames = new ArrayList<String>();
        	allowUsernames.add("zhangying");
        	allowUsernames.add("liuxu1");
        	allowUsernames.add("houxiaomin");
        	allowUsernames.add("ranxuejiao");
        	allowUsernames.add("kfsuijinfeng");
        	if(!allowUsernames.contains(username)){
        		throw new Exception("账户非法");
        	}
        	messageUtil.sendSms(phone, msg);
        	logger.debug("操作者{},手机号{},内容{}", username,phone,msg);
			result.put("result", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", false);
		}
        return MyGson.getInstance().toJson(result);
    }
	
	
	@RequestMapping(value = "viewAccount")
	public String viewAccount(String id,String start,String end, String type,Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				AccountVo data = accountService.viewAccount(id,start,end,type);
				model.addAttribute("data", data);
				model.addAttribute("id", id);
				model.addAttribute("start", start);
				model.addAttribute("end", end);
				model.addAttribute("type", type);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("viewAccount error:" + e.getMessage());
			}
		}
		return "/ssouser/account";
	}
	
	@RequestMapping(value = "viewQiuyouAccount/{id}")
	public String viewQiuyouAccount(@PathVariable String id, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			try {
//				ssoUserService.deleteSsoUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("deleteSsoUser error:" + e.getMessage());
			}
		}
		return "/ssouser/qiuyouAccount";
	}
	
	@RequestMapping(value = "addMarketUser/{id}")
	public String addMarketUser(@PathVariable String id, Model model,RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(id)) {
			try {
				if(ssoUserService.checkMarketUser(id)){
					ssoUserService.addMarketUser(id);
				}else{
					redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "添加失败！"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("deleteSsoUser error:" + e.getMessage());
			}
		}
		return "redirect:/ssouser/user";
	}
  
	@RequestMapping(value = "/importPhone_dlg")
	public String importPhone(HttpServletRequest request, Model model){
		return "ssouser/importPhone";
	}
	
	
   /**
    * 
    * <批量导入电话号码创建用户><功能具体实现>
    * @param myfile
	* @param redirectAttributes
    * @author liangsh
    * @date 2016年5月9日 下午5:01:23
    */
   @RequestMapping(value = "/readTxtFile", method = RequestMethod.POST)
   public String readTxtFile(@RequestParam("myfile") MultipartFile myfile,RedirectAttributes redirectAttributes) throws Exception {
       try {
    	   StringBuilder msg = new StringBuilder();
    	    if (!myfile.isEmpty()) {
	   			logger.info(myfile.getName());
	   			InputStream is;
   				is = myfile.getInputStream();
   				InputStreamReader isr = new InputStreamReader(is,"UTF-8");
   				BufferedReader bufferedReader = new BufferedReader(isr);
                   String lineTxt = null;
				   int num = 0 ;
                   while((lineTxt = bufferedReader.readLine()) != null && num < 1000){
					   num ++;
                    System.out.println(lineTxt);
                    // 参数
       				HashMap<String, Object> params = new HashMap<String, Object>();
       				params.put("_import_", "5edae37c0e884ba3b9efad0071e71945");
       				params.put("phone", lineTxt);
       				params.put("code", "code");
       				params.put("imei", "imei");
       				params.put("password", lineTxt);
       				params.put("city",ssoUserService.getProvincCityByPhone(lineTxt));
       				ClientRequest args = new ClientRequest();
       				// 全局唯一的UUID
       				args.setSn(UUID.get());
       				// 渠道
       				args.setChannel("android");
       				// 版本号
       				args.setVersion("1.0");
       				// 业务模块
       				args.setService("user");
       				// 方法
       				args.setMethod("register");
       				args.setParams(params);
       				String rn = PostRequest.postText(configs.get("app_id"), "body", MyGson.getInstance().toJson(args));
       				Type type = new TypeToken<Success>() {
       				}.getType();
       				Success success = MyGson.getInstance().fromJson(rn, type);
       				@SuppressWarnings("unchecked")
					Map<String, Object> entity = (Map<String, Object>) success.getEntity();
       				if (!success.isSuccess()) {
       					msg.append(lineTxt).append(":").append(entity.get("reason").toString()).append(";");
       				}
		           }
				String tipMsg = "";
				if(num == 1000){
					tipMsg = "前1000个手机号码已导入。";
				}else{
					tipMsg = num+"个号码已导入。";
				}
			    redirectAttributes.addFlashAttribute("tips", tipMsg+msg.toString());
                bufferedReader.close();
				isr.close();
				is.close();
    	     }
    	    
			} catch (IOException e) {
				e.printStackTrace();
			}
	   return "redirect:/ssouser/user";
   }
   
   
   
   public static void main(String argv[]){
       String filePath = "E:\\aa.txt";
   }
   
}
