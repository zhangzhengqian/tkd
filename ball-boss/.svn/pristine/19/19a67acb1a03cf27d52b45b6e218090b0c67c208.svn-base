package com.lc.zy.ball.boss.framework.statium.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lc.zy.ball.domain.oa.po.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.HttpPostUtils;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumPriceTmplService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumAccreditVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumDetailVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumAccreditMapper;
import com.lc.zy.ball.solr.StatiumBean;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.search.PageBean;
import com.lc.zy.common.search.Searcher;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.DateUtil;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.web.WebUtils;

/**
 * 场地操作Controller
 *
 * @author caiyongyou
 *
 */
@Controller
@RequestMapping(value = "/statium")
public class StatiumController extends AbstractController {

    @Autowired
    private StatiumDetailService statiumDetailService;

    @Autowired
    private StatiumPriceTmplService statiumPriceTmplService;

    @Autowired
    private QueueProducer queueProducer = null;

    @Autowired
    private HttpPostUtils httpPostUtils;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Resource(name = "configs")
    private Map<String, String> configs;
    
    @Autowired
    private StatiumAccreditMapper accreditMapper;

    private static Logger logger = LoggerFactory.getLogger(StatiumDetailService.class);

    /**
     *
     * <球馆列表><功能具体实现>
     *
     * @create：2015年8月4日 上午11:00:26
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            // 设置默认的组织，如果是 【 客服、客服经理、运营经理、root】，则忽略默认组织，可以看所有组织的数据
            User currentUser = SessionUtil.currentUser();
            if (!SecurityUtils.getSubject().hasRole(Roles.CUSTOMER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.CUSTOMER_MANAGER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.SUPPORT_MANAGER.getCode())
                    && !SecurityUtils.getSubject().hasRole(Roles.ADMIN.getCode())
                    && !"root".equals(currentUser.getLoginName())) {
                // 设置默认组织条件
                setDefaultOrgCode(searchParams);
            }

            parseDate(searchParams, "GTE_ct");
            parseDate(searchParams, "LTE_ct");

            String option = request.getParameter("option");
            String keyword = request.getParameter("keyword");
            String areaCode = request.getParameter("areaCode");
            if (StringUtils.isNotEmpty(areaCode)) {
                Map<String, String> zone = Zonemap.split(areaCode);
                String province = zone.get("province");
                searchParams.put("EQ_province", province);
                String city = zone.get("city");
                if (StringUtils.isNotEmpty(city)) {
                    searchParams.put("EQ_city", city);
                }
                String area = zone.get("area");
                if (StringUtils.isNotEmpty(area)) {
                    searchParams.put("EQ_area", area);
                }
            }

            if (StringUtils.isNotEmpty(option) && StringUtils.isNotEmpty(keyword)) {
                option = option.substring("search_".length());
                searchParams.put(option, keyword);
            }
            searchParams.put("EQ_isRating", 0);
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("user_list_search_params={}", searchParams);
//            Map<String, Object> searchParamsOld = Collections.synchronizedMap(searchParams);
            Page<StatiumDetailVo> onePage = statiumDetailService.find(new PageRequest(page, size), searchParams);
            model.addAttribute("currentUser", SessionUtil.currentUser());
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            logger.debug("user_list_search_paramsOld={}", searchParams);
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            model.addAttribute("areaCode", areaCode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/statiumList";
    }
    @RequestMapping(value = "sendMsg")
    public String sendMsg(HttpServletRequest request, Model model) {
        String username = SessionUtil.currentUsername();
        if(!"zhangying".equals(username)){
            return "redirect:";
        }
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        try {
            parseDate(searchParams, "GTE_ct");
            parseDate(searchParams, "LTE_ct");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String option = request.getParameter("option");
        String keyword = request.getParameter("keyword");
        String areaCode = request.getParameter("areaCode");
        String msg = request.getParameter("msg");
        String statiumIds = request.getParameter("statiumIds");
        if (StringUtils.isNotEmpty(areaCode)) {
            Map<String, String> zone = Zonemap.split(areaCode);
            String province = zone.get("province");
            searchParams.put("EQ_province", province);
            String city = zone.get("city");
            if (StringUtils.isNotEmpty(city)) {
                searchParams.put("EQ_city", city);
            }
            String area = zone.get("area");
            if (StringUtils.isNotEmpty(area)) {
                searchParams.put("EQ_area", area);
            }
        }
        if (StringUtils.isNotEmpty(option) && StringUtils.isNotEmpty(keyword)) {
            option = option.substring("search_".length());
            searchParams.put(option, keyword);
        }
        searchParams.put("EQ_isRating", 0);
        searchParams.put("EQ_isSigned", 1);
        logger.debug("user_list_search_params={}", searchParams);
        if(StringUtils.isNotBlank(statiumIds)){
            String ids[] = statiumIds.split(",");
            for(String id:ids){
                searchParams.put("EQ_id", id);
                statiumDetailService.sendMsg(searchParams,msg);
            }
        }else{
            statiumDetailService.sendMsg(searchParams,msg);
        }
        return "redirect:";
    }

    @RequestMapping(value = "setCta")
    public String setCta(HttpServletRequest request) {
        StatiumDetail detail = new StatiumDetail();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        detail.setId(id);
        detail.setCtaFlag(Integer.parseInt(type));
        try {
            statiumDetailService.updateByPrimaryKeySelective(detail,id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:";
    }


    /**
     *
     * <添加球馆><功能具体实现>
     *
     * @create：2015年8月4日 上午11:00:50
     * @author： CYY
     * @param model
     * @return
     */
    @RequestMapping(value = "createForm")
    public String createForm(Model model) {
        return "statium/statiumForm";
    }

    /**
     *
     * <添加球馆><功能具体实现>
     *
     * @create：2015年8月4日 上午11:00:50
     * @author： CYY
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "detailForm")
    public String detailForm(Model model, String id) {
        try {
            StatiumDetail statiumDetail = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, id);
            // modify by liangc 150902 : 页面上开始时间和结束时间都用整数了，所以这里处理一下字符串
            try {
                if (statiumDetail != null) {
                    String st = statiumDetail.getStartTime();
                    String et = statiumDetail.getEndTime();
                    if (StringUtils.isNotEmpty(st)) {
                        statiumDetail.setStartTime(st.split(":")[0]);
                    } else {
                        statiumDetail.setStartTime("0");
                    }
                    if (StringUtils.isNotEmpty(st)) {
                        statiumDetail.setEndTime(et.split(":")[0]);
                    } else {
                        statiumDetail.setEndTime("24");
                    }
                }
            } catch (Exception e) {
            }

            String spacePrices = statiumPriceTmplService.spacePriceTmplToJson(statiumDetail.getId(),
                    statiumDetail.getSportType());
            // modify by bhg： 解析区号和电话
            /*
             * XXX liangc : 没有做非空判断，上线后有异常 ,不能在 po 里加属性，除非改表 String allTel =
             * statiumDetail.getTel();
             * statiumDetail.setTelHead(allTel.split("-")[0]);
             * statiumDetail.setTel(allTel.split("-")[1]);
             */

            // modify by liangc 150823 : 显示审核日志
            if (StringUtils.isNotEmpty(id)) {
                model.addAttribute("auditLog", statiumDetailService.getAuditLog(id));
                model.addAttribute("signUser", statiumDetailService.getSignUser());
            }

            // modify by sl 151204 : 显示回访记录
            if (StringUtils.isNotEmpty(id)) {
                model.addAttribute("visitLog", statiumDetailService.getVisitLog(id));
            }
            model.addAttribute("spacePrices", spacePrices);
            model.addAttribute("statium", statiumDetail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/statiumForm";
    }

    /**
     *
     * <保存场馆信息><功能具体实现>
     *
     * @create：2015年8月4日 下午8:33:58
     * @author： CYY
     * @param myForm
     * @param priceTemps
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(StatiumDetailVo myForm, String priceTemps, HttpServletRequest request) {
        try {

            if (myForm.getLnglat() != null && !"".equals(myForm.getLnglat())) {
                String[] lnglat = myForm.lnglat.split(",");
                myForm.setLng(Double.parseDouble(lnglat[0]));
                myForm.setLat(Double.parseDouble(lnglat[1]));
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

            if (myForm.getNoticeImgs() != null && myForm.getNoticeImgs().length > 0) {
                String noticeImgs = "";
                for (String p : myForm.getNoticeImgs()) {
                    if (StringUtils.isNotEmpty(p)) {
                        noticeImgs = noticeImgs + p + "__";
                    }
                }
                if (StringUtils.isNotEmpty(noticeImgs)) {
                    noticeImgs = noticeImgs.substring(0, noticeImgs.length() - 2);
                }
                myForm.setNoticeImg(noticeImgs);
            }

            logger.debug("save_statium : " + myForm.toString());
            // 场馆价格模板操作
            if (StringUtils.isNotEmpty(priceTemps)) {
                Type type = new TypeToken<List<StatiumPriceTmplVo>>() {
                }.getType();
                List<StatiumPriceTmplVo> list = MyGson.getInstance().fromJson(priceTemps, type);
                // modify by liangc 150823: 新增/修改 的球馆信息，需要客服人员审核；
                // 是否审核：0：待审核 1 ：已审核 2 ：审核不通过
                logger.debug("新增修改，都要设置为待审核");
                myForm.setStatus(0);
                statiumDetailService.save(list, myForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redirect:";
    }

    /**
     *
     * <删除场馆><功能具体实现>
     *
     * @create：2015年8月5日 下午7:30:11
     * @author： CYY
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(id)) {
                StatiumDetail po = new StatiumDetail();
                po.setId(id);
                po.setDeleteFlag(1);
                User user = SessionUtil.currentUser();
                statiumDetailService.insertOrUpdate(po, user);
                // add by liangc : 同步删除搜索引擎
                /**
                 * 同步场馆信息到 solr. 异步提交到 MQ，统一处理
                 */
                queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, id);
                result.put(Constants.Result.RESULT, true);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
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
     * <场馆提交审核><功能具体实现>
     *
     * @create：2015年8月5日 下午7:30:11
     * @author： CYY
     * @param id 场馆id
     * @param status 是否审核：0：待审核 1 ：已审核 2 ：审核不通过 4：未审核 5:冻结6：解冻
     * @return
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(String id, Integer status) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(id)) {
                StatiumDetail po = new StatiumDetail();
                po.setId(id);
                po.setStatus(status);
                User user = SessionUtil.currentUser();
                statiumDetailService.insertOrUpdate(po, user);
                result.put(Constants.Result.RESULT, true);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
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
     * <检查名字是否存在><功能具体实现>
     *
     * @create：2015年8月5日 下午7:30:11
     * @author： CYY
     * @param id 场馆id
     * @param name 场馆名称
     * @return
     */
    @RequestMapping(value = "checkName", method = RequestMethod.GET)
    @ResponseBody
    public String checkName(String id, String name) {
        String result = "false";
        try {
            StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
            StatiumDetailCriteria.Criteria cri = statiumDetailCriteria.createCriteria();
            StatiumDetail sd = null;
            if (StringUtils.isNotEmpty(id)) {
                // cri.andDeleteFlagEqualTo("0");
                cri.andDeleteFlagIsNull();
                List<StatiumDetail> sdList = statiumDetailService.find(statiumDetailCriteria);
                if (CollectionUtils.isNotEmpty(sdList)) {
                    sd = sdList.get(0);
                }
            }
            if (StringUtils.isNotEmpty(name)) {
                statiumDetailCriteria = new StatiumDetailCriteria();
                cri = statiumDetailCriteria.createCriteria();
                cri.andNameEqualTo(name);
                // cri.andDeleteFlagEqualTo("0");
                cri.andDeleteFlagIsNull();
                List<StatiumDetail> sdList = statiumDetailService.find(statiumDetailCriteria);
                if (CollectionUtils.isEmpty(sdList) || (sd != null && name.equals(sd.getName()))) { // 当数据库为空时返回true
                    result = "true";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 场馆审核
     *
     * @author liangc 150823
     * @param id
     * @return
     */
    @RequestMapping(value = "audit")
    public String audit(String id, String audit, String userId, String reason) {
        logger.info("球馆审核 id={} , audit={} , reason={} , userId={}", id, audit, reason, userId);
        try {
            statiumDetailService.audit(id, audit, userId, reason);
            if ("1".equals(audit)) {
                /**
                 * 同步场馆信息到 solr. 异步提交到 MQ，统一处理
                 */
                queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redirect:";
    }

    /**
     *
     * <更新场馆是否付费><功能具体实现>
     *
     * @create：2015年9月10日 下午12:05:34
     * @author： liangsh
     * @param id
     * @param isRating
     * @return
     */
    @RequestMapping(value = "updateRating", method = RequestMethod.POST)
    @ResponseBody
    public String update(String id, Integer isRating) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(id)) {
                StatiumDetail po = new StatiumDetail();
                po.setId(id);
                po.setIsRating(isRating);
                // 如果更成付费需要审核
                if (isRating == 0) {
                    po.setStatus(0);
                }
                User user = SessionUtil.currentUser();
                po.setEb(user.getUserId());
                po.setEt(new Date());
                statiumDetailService.updateRating(po);
                // 如果更成免费的需要重新同步场馆信息，如果更成付费的需要审核 后才同步，这里不做同步
                /**
                 * 同步场馆信息到 solr. 异步提交到 MQ，统一处理
                 */
                if (isRating == 1) {
                    queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, id);
                }
                result.put(Constants.Result.RESULT, true);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
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
     * <查看或添加场馆管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午4:33:52
     * @author： liangsh
     * @param model
     * @param statiumId
     * @return
     */
    @RequestMapping(value = "crmManagerForm", method = RequestMethod.GET)
    public String crmManagerForm(Model model, String statiumId) {
        logger.debug("crmManagerForm : " + statiumId.toString());
        try {
            // 查看该场馆是否存在管理员，不存在进入添加页，存在则进入展示页
            CrmUser user = statiumDetailService.detail(statiumId);
            logger.debug("场馆管理员是否存在，{}",user!=null);
            StatiumDetail statium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
            if (user == null) {
                user = new CrmUser();
                user.setSecMobile(statium.getMasterTel());
                user.setStatiumId(statiumId);
                //user.setLoginName(statium.getMasterTel());
            }
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/crmUserForm";
    }

    /**
     *
     * <添加场馆管理员><功能具体实现>
     *
     * @create：2015年9月17日 下午5:24:42
     * @author： liangsh
     * @param myForm
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "saveCrmUser", method = RequestMethod.POST)
    public String saveCrmUser(CrmUser myForm, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        logger.debug("saveCrmUser : " + myForm.toString());
        try {
            Date now = new Date();
            String userId = SessionUtil.currentUserId();
            String username = myForm.getLoginName();
            if(!statiumDetailService.checkCrmUser(username,myForm.getUserId())){
                redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "用户名已存在!"));
                return "redirect:/statium/crmManagerForm?statiumId=" + myForm.getStatiumId();
            }
            if (StringUtils.isBlank(myForm.getUserId())) {
                myForm.setCb(userId);
                myForm.setCreateTime(now);
                myForm.setStatus(Constants.UserStatus.ENABLE);
                statiumDetailService.addCrmUser(myForm);
            }else{
                myForm.setCb(userId);
                myForm.setCreateTime(now);
                myForm.setStatus(Constants.UserStatus.ENABLE);
                statiumDetailService.upateUser(myForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redirect:";
    }

    @RequestMapping(value = "checkLoginName")
    @ResponseBody
    public boolean checkLoginName(String oldName, String loginName) {
        if (loginName.equals(oldName)) {
            return true;
        } else {
            return statiumDetailService.getByLoginName(loginName) == null;
        }
    }

    /**
     *
     * <置顶球馆><功能具体实现>
     *
     * @create：2015年10月10日 下午2:34:09
     * @author： CYY
     * @param id 场馆id
     * @return
     */
    @RequestMapping(value = "top")
    @ResponseBody
    public String top(String id, Integer type) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(id)) {
                result = statiumDetailService.top(id, result, type);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "statiumSpaceList/{statiumId}/{sportType}/{startDate}")
    @ResponseBody
    public String statiumSpaceList(@PathVariable("statiumId") String statiumId,
                                   @PathVariable("sportType") String sportType, @PathVariable("startDate") String startDate) {
        Map<String, Object> entity = null;
        try {
            // 参数
            HashMap<String, Object> params = new HashMap<String, Object>();
            // 场馆id
            params.put("statiumId", statiumId);
            // 运动类型
            params.put("sportType", sportType);
            // 场地时间
            params.put("startDate", startDate);
            Success success = httpPostUtils.postRequestText("statium", "spaceList", true, params);
            if (success.isSuccess()) {
                entity = (Map<String, Object>) success.getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("场馆订场列表", e.getMessage());
        }
        return MyGson.getInstance().toJson(entity.get("spaceBean"));
    }

    @RequestMapping(value = "nearByStatiums/{orderId}/{d}")
    @ResponseBody
    public String nearByStatiums(@PathVariable("orderId") String orderId,@PathVariable("d") String d) {

        List<String> statiumList = new ArrayList<String>();
        try {
            if(StringUtils.isBlank("d")){
                d = "5";
            }
            Integer distance = Integer.parseInt(d);
            Order order = statiumDetailService.selectByPrimaryKey(Order.class, orderId);
            String statiumId = order.getStatiumId();
            StatiumDetail curStatium = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
            OrderItemCriteria itemCri = new OrderItemCriteria();
            OrderItemCriteria.Criteria cri = itemCri.createCriteria();
            cri.andOrderIdEqualTo(orderId);
            List<OrderItem> items = orderItemMapper.selectByExample(itemCri);
            OrderItem item = items.get(0);
            String sportType = item.getSportType();
            Double lat = curStatium.getLat();
            Double lng = curStatium.getLng();
            final String URL = configs.get("services.solr_server") + "core0";
            String q = "";
            if (StringUtils.isNotEmpty(sportType)) {
                q = "sport_type:*" + CommonOAUtils.sportsEToC(sportType) + "*";
            } else {
                q = "*:*";
            }
            List<String> filterQueriesList = new ArrayList<String>();
            String[] fields = new String[] { "id", "name", "dist:geodist()", "address", "geo", "sport_type", "isRating", "isSigned" };
            filterQueriesList.add("isRating:" + 0);
            filterQueriesList.add("isSigned:" + 1);
            Map<String, String> params = new HashMap<String, String>();
            if (lat != null && lat != -1 && lng != null && lng != -1) {
                filterQueriesList.add("{!geofilt}"); //
                params.put("sfield", "geo");
                params.put("pt", lat + "," + lng);
                params.put("d", "" + distance/1.0d);
            }
            params.put("wt", "json");
            params.put("indent", "true");
            List<SortClause> sortList = new ArrayList<SortClause>();
            SortClause sort = null;
            sort = SortClause.create("sales", ORDER.desc);
            sortList.add(SortClause.create("geodist()", ORDER.asc));
            sortList.add(sort);
            PageBean<StatiumBean> page = Searcher.query(StatiumBean.class, URL, 0, 20, q,
                    filterQueriesList.toArray(new String[filterQueriesList.size()]), fields, params, sortList);
            List<StatiumBean> statiums = page.getData();
            for(StatiumBean statium:statiums){
                int dis = new Double(statium.getDist()*1000d).intValue();
                statiumList.add(statium.getName()+"【"+statium.getAddress()+"】"+"【距离当前球馆"+dis+"米】");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("查询附近场馆失败", e.getMessage());
        }
        return MyGson.getInstance().toJson(statiumList);
    }
    
    @RequestMapping(value = "accredits")
    public String accredits(HttpServletRequest request, Model model) {
        try {
            List<StatiumAccredit> items = new ArrayList<StatiumAccredit>();
            StatiumAccreditCriteria criteria = new StatiumAccreditCriteria();
            StatiumAccreditCriteria.Criteria cri = criteria.createCriteria();
            cri.andFlagEqualTo(1);
            items = accreditMapper.selectByExample(criteria);
            List<StatiumAccreditVo> accredits = new ArrayList<StatiumAccreditVo>();
            StatiumAccreditVo accredit = null;
            for(StatiumAccredit item:items){
            	accredit = new StatiumAccreditVo();
            	BeanUtils.copyProperties(item, accredit);
            	String statiumId = item.getStatiumId();
            	if(StringUtils.isNotBlank(statiumId)){
            		StatiumDetail detail = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, statiumId);
            		String name = detail.getName();
            		accredit.setStatiumName(name);
            	}
            	accredits.add(accredit);
            }
            model.addAttribute("data", accredits);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "statium/accredits";
    }
    
    @RequestMapping(value = "accredit/{id}")
    public String accredit(HttpServletRequest request, Model model,@PathVariable("id") String id) {
        try {
        	StatiumAccreditVo accredit = statiumDetailService.getAccredit(id);
        	model.addAttribute("acc", accredit);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "statium/accredit";
    }
    @RequestMapping(value = "accredit/delete/{id}")
    public String delete(HttpServletRequest request, Model model,@PathVariable("id") String id) {
    	try {
    		StatiumAccredit accredit = statiumDetailService.selectByPrimaryKey(StatiumAccredit.class, id);
    		accredit.setArea(null);
    		accredit.setEnd(null);
    		accredit.setStart(null);
    		accredit.setNumber(null);
    		accredit.setStatiumId(null);
    		accredit.setStatiumNames(null);
    		statiumDetailService.updateByPrimaryKey(accredit, id);
    		deleteFiles(id);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    	}
    	return "redirect:/statium/accredits";
    }
    
    @RequestMapping(value = "accredit/save")
    public String save(HttpServletRequest request, Model model,StatiumAccreditVo accredit) {
        try {
        	StatiumAccredit saved = new StatiumAccredit();
        	BeanUtils.copyProperties(accredit, saved);
        	statiumDetailService.updateByPrimaryKeySelective(saved, saved.getId());
        	Map<String, Object> paras = new HashMap<String, Object>();
        	paras.put("area", saved.getArea());
        	paras.put("statiumName", accredit.getStatiumNames());
        	paras.put("start", DateUtil.formatDate(DateUtil.parse(accredit.getStart(), "yyyy-MM-dd", null),"yyyy年M月d日"));
        	paras.put("end", DateUtil.formatDate(DateUtil.parse(accredit.getEnd(), "yyyy-MM-dd", null),"yyyy年M月d日"));
        	paras.put("number", saved.getNumber());
        	String html = FreeMarkerUtils.format("/template/accredit/index.ftl", paras);
        	logger.debug("html={}",html);
        	generateFiles(html,saved.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/statium/accredits";
    }
    
    public void generateFiles(String html,String id) throws Exception{
    	String fileFix = "/home/appusr/www/accreditInfo/";
    	File folder = new File(fileFix+id);
    	File newFile = new File(fileFix+id+"/index.html");
    	if(folder.exists()){
    		
    	}else{
    		folder.mkdir();
    	}
    	FileOutputStream fos = new FileOutputStream(newFile);
    	fos.write(html.getBytes("utf-8"));
    	fos.flush();
    	fos.close();
    }
    
    public void deleteFiles(String id) throws Exception{
    	String fileFix = "/home/appusr/www/accreditInfo/";
    	File folder = new File(fileFix+id);
    	File newFile = new File(fileFix+id+"/index.html");
    	logger.debug("newFile={}",fileFix+id+"/index.html");
    	logger.debug("newFile is exists?,{}",newFile.exists());
    	if(folder.exists()){
    		newFile.delete();
    		folder.delete();
    	}
    }

    /**
     *
     * <场馆回访记录><功能具体实现>
     *
     * @create：2015年12月4日 上午10:46:31
     * @author： sl
     * @param id
     * @param visitReason
     * @param visitResult
     * @return
     */
    @RequestMapping(value = "visit")
    public String visit(String id, String visitReason, String visitResult) {
        logger.info("球馆回访 id={} , visitReason={} , visitResult={}", id, visitReason, visitResult);
        try {
            statiumDetailService.visit(id, visitReason, visitResult);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redirect:";
    }


    /**
     * <打开同步页></>
     * @param model
     * @return
     */
    @RequestMapping(value = "openUpload" )
    public String openUpload(Model model,String statiumId) {
        try {
            List<NuomiTpDealId> list =  statiumDetailService.getUploadData(statiumId);
            model.addAttribute("list",list);
            model.addAttribute("statiumId",statiumId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/uploadToNuomi";
    }

    /**
     * <上单></>
     * @param sportType
     * @param statiumId
     * @return
     */
    @RequestMapping(value = "uploadToNuomi")
    @ResponseBody
    public String uploadToNuomi( String sportType,String statiumId,Integer isHidden) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isEmpty(sportType)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "sportType不能为空");
            }else if(StringUtils.isEmpty(statiumId)){
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "statiumId不能为空");
            }

            result = statiumDetailService.uploadData(sportType,statiumId,isHidden);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }

    @RequestMapping(value = "nuomiOrderCancel")
    @ResponseBody
    public String nuomiOrderCancel( String orderId,String reason,String reason1) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isEmpty(orderId)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "orderId不能为空");
            }
            result = statiumDetailService.nuomiOrderCancel(orderId, CommonUtils.isNotEmpty(reason)?reason:reason1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }

    /**
     * <审核糯米申请退款的订单></>
     * @param orderId
     * @param reason
     * @param status
     * @return
     */
    @RequestMapping(value = "nuomiOrderAudi")
    @ResponseBody
    public String nuomiOrderAudi( String orderId,String reason,Integer status) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isEmpty(orderId)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "orderId不能为空");
            }
            result = statiumDetailService.nuomiOrderAudi(orderId,reason,status);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }

    public static void main(String[] args) {
        String url = "http://119.254.80.102:8000/monitor/dh.pbx.4.5/20151127/20151127-155357_N00000003412__913311112021_dh.pbx.4.5-1448610837.473126.mp3";
        HttpClient client = new HttpClient();
        GetMethod httpGet = new GetMethod(url);
        try {
            client.executeMethod(httpGet);
            InputStream in = httpGet.getResponseBodyAsStream();
            FileOutputStream out = new FileOutputStream(new File("/home/appusr/var/callcenter/"+UUID.get()+".mp3"));
            byte[] b = new byte[1024];
            int len = 0;
            while((len=in.read(b))!= -1){
                out.write(b,0,len);
            }
            in.close();
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
