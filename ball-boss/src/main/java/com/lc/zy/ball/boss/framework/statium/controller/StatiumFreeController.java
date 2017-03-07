package com.lc.zy.ball.boss.framework.statium.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.Zonemap;
import com.lc.zy.ball.boss.common.enums.Roles;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.statium.service.StatiumDetailService;
import com.lc.zy.ball.boss.framework.statium.service.StatiumPriceTmplService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumDetailVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumDetailCriteria;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

/**
 * 免费场馆Controller
 * 
 *
 */
@Controller
@RequestMapping(value = "/statiumFree")
public class StatiumFreeController extends AbstractController {

    @Autowired
    private StatiumDetailService statiumDetailService;

    @Autowired
    private StatiumPriceTmplService statiumPriceTmplService;
    
    @Autowired
    private QueueProducer queueProducer = null;
    
    private static Logger logger = LoggerFactory.getLogger(StatiumFreeController.class);

 
    /**
     * 
     * <场馆列表><免费>
     *
     * @create：2015年9月6日 下午7:39:22
     * @author： liangsh
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
            searchParams.put("EQ_isRating", "1");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("user_list_search_params={}", searchParams);
            Page<StatiumDetailVo> onePage = statiumDetailService.find(new PageRequest(page, size), searchParams);

            model.addAttribute("currentUser", SessionUtil.currentUser());
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            model.addAttribute("areaCode", areaCode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/statiumFreeList";
    }

    /**
     * 
     * <添加球馆><功能具体实现>
     *
     * @create：2015年8月4日 上午11:00:50
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "createForm")
    public String createForm(Model model) {
        return "statium/statiumFreeForm";
    }

    /**
     * 
     * <添加球馆><功能具体实现>
     *
     * @create：2015年8月4日 上午11:00:50
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "detailForm")
    public String detailForm(Model model, String id) {
        try {
            StatiumDetail statiumDetail = statiumDetailService.selectByPrimaryKey(StatiumDetail.class, id);
            //modify by liangc 150902 : 页面上开始时间和结束时间都用整数了，所以这里处理一下字符串
            try{
            	if(statiumDetail!=null){
            		String st = statiumDetail.getStartTime();
            		String et = statiumDetail.getEndTime();
            		if(StringUtils.isNotEmpty(st)){
            			statiumDetail.setStartTime(st.split(":")[0]);
            		}else{
            			statiumDetail.setStartTime("0");
            		}
					if(StringUtils.isNotEmpty(st)){
						statiumDetail.setEndTime(et.split(":")[0]);	
					}else{
						statiumDetail.setEndTime("24");
					}
            	}
            }catch(Exception e){}
            
            
            String spacePrices = statiumPriceTmplService.spacePriceTmplToJson(statiumDetail.getId(),
                    statiumDetail.getSportType());
            // modify by liangc 150823 : 显示审核日志
            if (StringUtils.isNotEmpty(id)) {
                model.addAttribute("auditLog", statiumDetailService.getAuditLog(id));
                model.addAttribute("signUser", statiumDetailService.getSignUser());
            }
            model.addAttribute("spacePrices", spacePrices);
            model.addAttribute("statium", statiumDetail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "statium/statiumFreeForm";
    }

    /**
     * 
     * <保存场馆信息><免费>
     *
     * @create：2015年9月6日 下午8:17:51
     * @author： liangsh
     * @param myForm
     * @param priceTemps
     * @param request
     * @return
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
            logger.debug("save_statium_free : " + myForm.toString());
        	try{
        		int deleteFlag = myForm.getDeleteFlag();
        		if(deleteFlag==0){
        			myForm.setDeleteFlag(null);
        		}
        	}catch(Exception e){
        	}
            User user = SessionUtil.currentUser();
            StatiumDetail statiumDetail = new StatiumDetail();
            BeanUtils.copyProperties(myForm, statiumDetail);
            statiumDetailService.insertOrUpdate(statiumDetail, user);
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
                //add by liangc : 同步删除搜索引擎
                /**
                 * 同步场馆信息到 solr. 异步提交到 MQ，统一处理  暂时不同步
                 */
//                queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, id);
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
                //cri.andDeleteFlagEqualTo("0");
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
                //cri.andDeleteFlagEqualTo("0");
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
}
