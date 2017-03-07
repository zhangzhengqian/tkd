package com.lc.zy.ball.boss.framework.company.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.CacheControl;

import com.lc.zy.ball.boss.framework.company.vo.*;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyGameVo;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.framework.accounts.service.AccountService;
import com.lc.zy.ball.boss.framework.company.service.CompanyService;
import com.lc.zy.ball.boss.framework.ssouser.service.SsoUserService;
import com.lc.zy.ball.boss.framework.ssouser.vo.UserIntegralVo;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.common.web.WebUtils;
import com.lc.zy.common.zoo.SEQGenerate;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends CacheControl {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StatiumDetailService statiumDetailService;
	
	@Autowired
	private SEQGenerate seqGenerate;
	
	@Autowired
	private SsoUserService ssoUserService;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 
	 * <获取企业用户列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月2日 下午4:52:55
	 */
	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CompanyInfoVo> onePage = companyService.list(new PageRequest(page, size), searchParams);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "company/list";
	}

	/**
	 * <企业积分列表></>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = "integraLlist")
	public String integraLlist(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CompanyMonthIntegralVo> onePage = companyService.integraLlist(new PageRequest(page, size), searchParams);
		model.addAttribute("data", onePage);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "company/monthIntegralList";
	}

	/**
	 * <去企业积分修改页></>
	 * @param request
	 * @param model
     * @return
     */
	@RequestMapping(value = "/updateIntegral_dlg")
	public String refuse(HttpServletRequest request, Model model) {
		try {
			String id = request.getParameter("id");
			CompanyMonthIntegral integral = companyService.selectByPrimaryKey(CompanyMonthIntegral.class,id);
			model.addAttribute("info", integral);
		} catch (Exception e) {
			logger.error("获取积分失败！", e);
		}
		return "company/updateMonthIntegral";
	}

	/**
	 * <保存企业积分></>
	 * @param id
	 * @param integral
     * @return
     */
	@RequestMapping(value = "saveCompanyMonthIntegral", method = RequestMethod.POST)
	@ResponseBody
	public String saveRefuse(String id,Integer integral) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(id)){
				CompanyMonthIntegral monthIntegral = companyService.selectByPrimaryKey(CompanyMonthIntegral.class,id);
				monthIntegral.setIntegral(integral);
				companyService.updateByPrimaryKeySelective(monthIntegral,id);
				CompanyInfo info = companyService.selectByPrimaryKey(CompanyInfo.class,monthIntegral.getCompanyId());
				if(info != null){
					info.setIntegral(monthIntegral.getIntegral());
					companyService.updateByPrimaryKeySelective(info,info.getId());
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
	 * <企业提交的活动列表></>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "companyActivityList")
	public String companyActivityList(Model model, HttpServletRequest request) throws Exception {
		//活动列表
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		int type = 1;
		logger.debug("searchParams={}",searchParams);
		if(searchParams.isEmpty()){
			type = 1;
		}else{
			if(searchParams.get("EQ_type").toString().equals("1")) {
				type = 1;
			}else{
				type = 2;
			}
		}

		if(type == 1) {
			logger.debug("EQ_type={}",1);
			type = 1;
			searchParams.remove("EQ_type");
			Page<CompanyActivityVo> onePage = companyService.companyActivityList(new PageRequest(page, size), searchParams);
			model.addAttribute("data", onePage);
		}else{
			logger.debug("EQ_type={}",2);
			type = 2;
			//赛事列表
			int page2 = WebUtils.getPage(request);
			int size2 = WebUtils.getPageSize(request);
			Page<CompanyGameVo> onePage2 = companyService.companyGameList(new PageRequest(page2, size2), searchParams);
			model.addAttribute("data", onePage2);
		}
		// 将搜索条件编码成字符串，用于排序，分页的URL
		searchParams.put("EQ_type",type);
		model.addAttribute("type",type);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "company/companyActivityList";
	}

	/**
	 *<去创建企业活动赛事服务订单页></>
	 * @param model
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = "createServicesAuto/{id}", method = RequestMethod.GET)
	public String createServicesAuto(@PathVariable String id, Model model,RedirectAttributes redirectAttributes) throws Exception {
		CompanyActivity info = companyService.selectByPrimaryKey(CompanyActivity.class,id);
		info.setPrice(Integer.valueOf(info.getPrice()/100));
		model.addAttribute("info",info);
		return "company/serviceForm_auto";
	}

	/**
	 * 审核企业提交的赛事
	 * @param request
	 * @return
     */
	@RequestMapping(value = "audiCompanyGame", method = RequestMethod.POST)
	@ResponseBody
	public String audiCompanyGame(ServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String id =  request.getParameter("id").toString();
			Integer state = Integer.valueOf(request.getParameter("state").toString());
			CompanyGame info =  companyService.selectByPrimaryKey(CompanyGame.class,id);
			if(info != null){
				info.setStatus(state);
				info.setEt(new Date());
				info.setEb(SessionUtil.currentUserId());
				companyService.updateByPrimaryKeySelective(info,info.getId());
				if(state == 2){
					//企业月赛事总数加1
					CompanyActivityStatistic statistic = companyService.searchCompanyActivityStatistic(info.getCompanyId(),2);
					if(statistic != null){
						statistic.setHoldTimes(statistic.getHoldTimes() +1);
						companyService.updateByPrimaryKeySelective(statistic,statistic.getId());
					}else{
						statistic = new CompanyActivityStatistic();
						statistic.setId(UUID.get());
						statistic.setCt(new Date());
						statistic.setCb(SessionUtil.currentUserId());
						statistic.setCompanyId(info.getCompanyId());
						statistic.setMonth(DateUtil.formatDate(new Date(),"yyyy-MM"));
						statistic.setType(2);
						statistic.setHoldTimes(1);
						statistic.setEt(new Date());
						companyService.insertSelective(statistic,statistic.getId());
					}
					CompanyInfo companyInfo = companyService.selectByPrimaryKey(CompanyInfo.class,info.getCompanyId());
					companyInfo.setGameNum(statistic.getHoldTimes());
					companyService.updateByPrimaryKeySelective(companyInfo,companyInfo.getId());
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
	 * <企业员工列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月4日 下午5:50:15
	 */
	@RequestMapping(value = "employeeList")
	public String List(Model model,HttpServletRequest request)throws Exception{
		String companyId = request.getParameter("id");
		CompanyInfo info = companyService.selectByPrimaryKey(CompanyInfo.class,companyId);
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<EmsgGroupUserVo> onePage = companyService.GroupUsers(new PageRequest(page, size),info);
		model.addAttribute("data", onePage);
		return "company/employeeList";
	}
	
	/**
	 * 
	 * <企业员工详情><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月9日 上午10:36:08
	 */
	@RequestMapping(value = "employeeView")
	public String employeeView(Model model,HttpServletRequest request)throws Exception{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
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
					Integer integralAccount = accountService.getUserIntegral(id);
					List<UserIntegralVo> integrals = accountService.getUserIntegralLog(id, null, 0, 10);
					model.addAttribute("qiuyouAmount", qiuyouAmount);
					model.addAttribute("accountAmount", accountAmount);
					model.addAttribute("integralAccount", integralAccount);
					model.addAttribute("integrals", integrals);
					StaffAccount staffAccount = companyService.selectByPrimaryKey(StaffAccount.class,id);
					CompanyInfo info = companyService.selectByPrimaryKey(CompanyInfo.class,staffAccount.getCompanyAccountId());
					model.addAttribute("limitAmount",staffAccount.getLimitAmount());
				    model.addAttribute("useAmount",staffAccount.getUseAmout());
				    model.addAttribute("companyName", info.getName());
				    model.addAttribute("orders", orders);
				    model.addAttribute("staffAccount",companyService.userStaffAccountVoList(id, ssoUser));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("viewSsoUser error:" + e.getMessage());
				}
			}
			model.addAttribute("ssoUser", ssoUser);
			model.addAttribute("orderAmount", orderAmount);
		}
		return "company/employeeView";
	}
	
	/**
	 * 
	 * <创建企业用户><功能具体实现>
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月2日 下午4:53:06
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		return "company/form";
	}
	
	/**
	 * 
	 * <保存企业用户><功能具体实现>
	 * @param info
	 * @param redirectAttributes
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:53:17
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(CompanyInfo info, RedirectAttributes redirectAttributes) {
		try {
			Map<String, Object> map = companyService.createOrUpdate(info);
			if(Boolean.valueOf(map.get(Constants.Result.RESULT).toString())){
				redirectAttributes.addFlashAttribute("message", "保存成功!");
			}else{
				redirectAttributes.addFlashAttribute("message", String.valueOf(map.get(Constants.Result.REASON)));
			}
		} catch (Exception e) {
			logger.debug("保存失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存失败！"));
		}
		return "redirect:/company/list";
	}
	
	/**
	 * 
	 * <企业用户详情><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月6日 下午1:49:59
	 */
	@RequestMapping(value = "view")
	public String view(Model model,HttpServletRequest request){
		CompanyInfo info;
		try {
			String id = request.getParameter("id");
			info = companyService.selectByPrimaryKey(CompanyInfo.class, id);
			logger.debug("info={}",info.toString());
			//根据企业id获取企业账户
			CompanyAccount account = companyService.selectByPrimaryKey(CompanyAccount.class,id);
			List<SsoUser> usersList = companyService.getGroupUsers(info);
			Integer useAmout = companyService.useAmout(id);
			//账户
			model.addAttribute("account", account);
			model.addAttribute("useAmout", useAmout);
			model.addAttribute("usersCount", usersList.size());
			model.addAttribute("servicesList", companyService.servicesList(id));
			model.addAttribute("withDrawAndChargeList", companyService.withDrawAndChargeList(id));
	        model.addAttribute("serviceLogs",companyService.serviceLogs(id));
			model.addAttribute("info", info);
		} catch (Exception e) {
			logger.error("查看失败！", e);
		}
		return "company/form";
	}
	
	/**
	 * 
	 * <获取某企业员工运动基金消费记录><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月6日 下午1:49:31
	 */
	@RequestMapping(value = "employeeOrderList")
	public String employeeOrderList(Model model,HttpServletRequest request){
		try {
			String companyId = request.getParameter("id");
			model.addAttribute("data", companyService.staffAccountVoList(companyId));
		} catch (Exception e) {
			logger.error("查看失败！", e);
		}
		return "company/employeeOrderList";
	}
	
	/**
	 * 
	 * <新增或修改服务信息><功能具体实现>
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年3月6日 下午1:52:32
	 */
	@RequestMapping(value = "services_dlg")
	public String createServices(Model model,HttpServletRequest request)throws Exception{
		String id = request.getParameter("id");
		String companyId = request.getParameter("companyId");
		if(StringUtils.isNotBlank(id)){
			CompanyServiceLog log = companyService.selectByPrimaryKey(CompanyServiceLog.class, id);
			model.addAttribute("info", log);
		}
		model.addAttribute("companyId", companyId);
		return "company/servicesForm";
	}
	
	/**
	 * 
	 * <保存服务记录信息><功能具体实现>
	 * @param log
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2016年3月6日 下午3:00:35
	 */
	@RequestMapping(value = "saveServices")
	public String saveServices(CompanyServiceLog log)throws Exception{
			if(log != null){
				if(StringUtils.isNotBlank(log.getId())){
//					log.setEb(SessionUtil.currentUserId());
//					log.setEt(DateUtils.forDatetime(new Date()));
					companyService.updateByPrimaryKeySelective(log,log.getId());
				}else{
					log.setId(UUID.get());
					log.setCt(new Date());
					log.setCb(SessionUtil.currentUserId());
					companyService.insertSelective(log,log.getId());
				}
			}
		return "redirect:/company/view?id="+log.getCompanyId();
	}
	
	/**
	 * 
	 * <冻结或解冻><功能具体实现>
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "freeze")
	public String show(RedirectAttributes redirectAttributes, Model model, ServletRequest request) {
		try {
			String id = request.getParameter("id");
			Integer freeze = Integer.valueOf(request.getParameter("freeze").toString());
			if (StringUtils.isNotBlank(id)) {
				CompanyInfo info = companyService.selectByPrimaryKey(CompanyInfo.class, id);
				info.setState(freeze);
				companyService.updateByPrimaryKeySelective(info, id);
			}
		} catch (Exception e) {
			logger.debug("修改失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
			redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "修改失败:" + e.getMessage()));
		}
		redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "修改成功"));
		return "redirect:/company/list";
	}
	
	/**
	 * 
	 * <企业服务列表><功能具体实现>
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "services")
	public String services(RedirectAttributes redirectAttributes, Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CompanyServicesVo> onePage = null;
		try {
			onePage = companyService.services(new PageRequest(page, size), searchParams,true);
		} catch (Exception e) {
			logger.debug("查询公司服务列表失败: {}", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		logger.debug("====={}",MyGson.getInstance().toJson(onePage));
		model.addAttribute("data", onePage);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "company/services";
	}

	@RequestMapping(value="exportServices")
	public String exportServices(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int page = WebUtils.getPage(request);
		int size = WebUtils.getPageSize(request);
		Page<CompanyServicesVo> onePage = companyService.services(new PageRequest(page, size), searchParams,false);
		List<CompanyServicesVo> data =  new ArrayList<CompanyServicesVo>();
		List<CompanyServicesVo> companyServicesList =  onePage.getContent();
		for (CompanyServicesVo vo:companyServicesList) {
			CompanyServicesVo companyServices = new CompanyServicesVo();
			BeanUtils.copyProperties(vo,companyServices);
			logger.debug(companyServices.toString());
			if(companyServices.getFee() != null){
				companyServices.setFee(companyServices.getFee()/100);
			}else{
				companyServices.setFee(0);
			}
			if(companyServices.getCostFee() != null){
				companyServices.setCostFee(companyServices.getCostFee()/100);
			}else{
				companyServices.setCostFee(0);
			}

			companyServices.setTotalMoney(companyServices.getFee()-companyServices.getCostFee());
			data.add(companyServices);

		}
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.createtFileName("企业服务列表");
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String[] headers = {"企业名称::name","创建时间::ct","套餐类型::serviceType_","订单总额(元)::fee","成本总额(元)::costFee",
				"利润总额(元)::totalMoney"};
		excelUtil.exportExcel("企业服务列表", headers, data, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
		return null;
	}
	
	/**
	 * 
	 * <新增企业服务><功能具体实现>
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "service/create")
	public String serviceCreate(RedirectAttributes redirectAttributes, Model model, ServletRequest request) {
		
		return "company/serviceForm";
	}
	
	/**
	 * 
	 * <根据登录角色获取待审核订单列表><功能具体实现>
	 * @param model
	 * @param request
	 * @return
	 * @author liangsh
	 * @date 2016年3月7日 下午3:04:40
	 */
	@RequestMapping(value = "orderList")
	public String orderList(Model model, ServletRequest request){
	  try{
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			int page = WebUtils.getPage(request);
			int size = WebUtils.getPageSize(request);
			Page<CompanyServicesVo> onePage = companyService.servicesOrders(new PageRequest(page, size), searchParams);
			model.addAttribute("data", onePage);
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
	  } catch (Exception e) {
          e.printStackTrace();
          logger.error(e.getMessage(), e);
      }
      return "company/orderList";
	}
	
	/**
	 * 
	 * <财务审批><功能具体实现>
	 *
	 * @create：2016年3月9日 下午7:49:43
	 * @author： CYY
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "financeAudit")
	public String financeAudit(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            String tempStatus = request.getParameter("status");//0提现1收款
            Integer status = 0;
            if(StringUtils.isNotEmpty(tempStatus)){
                status = Integer.valueOf(tempStatus);
            }
            if (StringUtils.isNotBlank(id)) {
                companyService.financeAudit(id,status);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/company/orderList";
	}
	/**
	 * 
	 * <客服审批><功能具体实现>
	 *
	 * @create：2016年3月9日 下午7:49:43
	 * @author： CYY
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "customerAudit")
	public String customerAudit(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			String tempStatus = request.getParameter("status");//0不通过 1通过
			Integer status = 0;
			if(StringUtils.isNotEmpty(tempStatus)){
				status = Integer.valueOf(tempStatus);
			}
			if (StringUtils.isNotBlank(id)) {
				companyService.customerAudit(id,status);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/company/orderList";
	}
	
		/**
	 * 
	 * <新增企业服务><功能具体实现>
	 * @param form
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "service/save")
	public @ResponseBody String saveService(@RequestBody CompanyServiceForm form) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			int serviceType = form.getServiceType();
			CompanyServices service = new CompanyServices();
			BeanUtils.copyProperties(form, service);
			service.setId(seqGenerate.genOrderId());
			CompanyServiceItem item = null;
			Date now = new Date();
			String cb = SessionUtil.currentUserId();
			service.setCb(cb);
			service.setCt(now);
			service.setFlowState(1);
			service.setStatus(0);
			List<CompanyServiceItem> items = new ArrayList<CompanyServiceItem>();
			if(serviceType==0||serviceType==1){
				String companyId = form.getCompanyId();
				CompanyAccount account = companyService.selectByPrimaryKey(CompanyAccount.class, companyId);
				int balance = account.getBalance();
				if(balance<form.getFee()&&form.getPayType()==0){
					result.put("result", "fail");
					result.put("reason", "账户余额不足！");
					return MyGson.getInstance().toJson(result);
				}
				item = new CompanyServiceItem();
				item.setId(UUID.get());
				item.setServiceId(service.getId());
				item.setCt(now);
				item.setCb(cb);
				item.setManFlag(0);
				item.setPrice(form.getPrice());
				item.setCostPrice(form.getCostPrice());
				if(serviceType==0){
					item.setActivityTime(MyGson.getInstance().toJson(form.getActivityDate()));
					item.setAmount(form.getAmount());
					item.setBegin(DateUtil.parse(form.getBegin(), null));
					item.setEnd(DateUtil.parse(form.getEnd(), null));
					item.setUnitPrice(form.getUnitPrice());
				}else{
					item.setBegin(DateUtil.parse(form.getBegin(),"yyyy-MM-dd HH:mm:00", null));
					item.setEnd(DateUtil.parse(form.getEnd(),"yyyy-MM-dd HH:mm:00", null));
				}
				item.setStatiumId(form.getStatiumId());
				StatiumDetail detail = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, form.getStatiumId());
				item.setTitle(detail.getName());
				item.setContent(detail.getAddress());
				items.add(item);
				List<AddProject> addProjects = form.getAddProjects();
				for(AddProject pj:addProjects){
					item = new CompanyServiceItem();
					BeanUtils.copyProperties(pj, item);
					item.setId(UUID.get());
					item.setServiceId(service.getId());
					item.setCt(now);
					item.setCb(cb);
					items.add(item);
				}
			}else if (serviceType==2){//充值
				service.setFlowState(3);
			}else if (serviceType==3){//提现
				String companyId = form.getCompanyId();
				CompanyAccount account = companyService.selectByPrimaryKey(CompanyAccount.class, companyId);
				int balance = account.getBalance();
				if(balance<form.getFee()){
					result.put("result", "fail");
					result.put("reason", "账户余额不足！");
					return MyGson.getInstance().toJson(result);
				}
			}
			companyService.saveService(service, items);
			result.put("result", "success");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", "fail");
		}
		return MyGson.getInstance().toJson(result);
	}
	
	/**
	 * 
	 * <审核企业服务><功能具体实现>
	 * @param id
	 * @param type
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "service/auditService/{id}/{type}")
	public String auditService(@PathVariable String id,@PathVariable int type) {
		try {
			CompanyServices service = companyService.selectByPrimaryKey(CompanyServices.class, id);
			companyService.auditServcie(service, type);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/company/services";
	}
	
	/**
	 * 
	 * <服务详情><功能具体实现>
	 * @param id
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "service/view/{id}")
	public String view(@PathVariable String id,Model model) {
		try {
			CompanyServices service = companyService.selectByPrimaryKey(CompanyServices.class, id);
			CompanyServiceForm form = new CompanyServiceForm();
			BeanUtils.copyProperties(service, form);
			List<CompanyServiceItem> items = companyService.getServiceItems(id);
			String companyId = service.getCompanyId();
			CompanyInfo company = companyService.selectByPrimaryKey(CompanyInfo.class, companyId);
			form.setCompanyName(company.getName());
			if(CollectionUtils.isNotEmpty(items)){
				CompanyServiceItem manItem = items.get(0);
				String activityTime = manItem.getActivityTime();
				BeanUtils.copyProperties(manItem, form);
				String statiumId = form.getStatiumId();
				StatiumDetail detail = companyService.selectByPrimaryKey(StatiumDetail.class, statiumId);
				form.setStatiumName(detail.getName());
				Date begin = manItem.getBegin();
				Date end = manItem.getEnd();
				if(service.getServiceType()==0){
					form.setBegin(DateUtil.formatDate(begin, "yyyy-MM-dd"));
					form.setEnd(DateUtil.formatDate(end, "yyyy-MM-dd"));
				}else{
					form.setBegin(DateUtil.formatDate(begin, "yyyy-MM-dd HH:mm:00"));
					form.setEnd(DateUtil.formatDate(end, "yyyy-MM-dd HH:mm:00"));
				}
				if(StringUtils.isNotBlank(activityTime)){
					Type type = new TypeToken<Map<Integer,List<Integer>>>() {}.getType();
					Map<Integer,List<Integer>> activityDate = MyGson.getInstance().fromJson(manItem.getActivityTime(), type);
					form.setActivityDate(activityDate);
				}
				List<AddProject> addProjects = new ArrayList<AddProject>();
				if(items.size()>1){
					for(int i=1;i<items.size();i++){
						CompanyServiceItem item = items.get(i);
						AddProject addProject = new AddProject();
						BeanUtils.copyProperties(item, addProject);
						addProjects.add(addProject);
					}
				}
				form.setAddProjects(addProjects);
			}
			model.addAttribute("data", form);
			List<CompanyServiceFlow> flows = companyService.getServiceFlows(id);
			for(CompanyServiceFlow flow:flows){
				String handler = flow.getHandler();
				User user = companyService.selectByPrimaryKey(User.class, handler);
				flow.setHandler(user.getNickname());
			}
			model.addAttribute("flows",flows);
			logger.debug(MyGson.getInstance().toJson(form));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "company/serviceView";
	}
	
	/**
	 * 
	 * <审核企业服务><功能具体实现>
	 * @param model
	 * @return
	 * @author liangsh
	 * @date 2016年3月2日 下午4:57:06
	 */
	@RequestMapping(value = "service/statistic")
	public String statistic(Model model) {
		try {
			List<CompanyServicesVo> vos = companyService.getServicesStatistic();
			model.addAttribute("data",vos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "company/statistic";
	}
	
	public static void main(String[] args) {
		CompanyServiceForm form = new CompanyServiceForm();
		CompanyServiceItem item = new CompanyServiceItem();
		item.setBegin(new Date());
		item.setEnd(new Date());
		BeanUtils.copyProperties(item, form);
		System.out.println(form.getBegin());
	}
}
