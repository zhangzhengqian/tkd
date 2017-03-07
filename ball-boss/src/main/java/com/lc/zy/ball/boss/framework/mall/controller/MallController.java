package com.lc.zy.ball.boss.framework.mall.controller;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.mall.service.MallService;
import com.lc.zy.ball.boss.framework.mall.vo.CrmMallActivityVo;
import com.lc.zy.ball.boss.framework.mall.vo.CrmMallOrderVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.CrmMallGoods;
import com.lc.zy.ball.domain.oa.po.CrmMallOrder;
import com.lc.zy.ball.domain.oa.po.CrmMallOrderLog;
import com.lc.zy.common.web.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liangsh
 * Date: 2016/9/12
 * Time: 17:50
 */
@Controller
@RequestMapping(value = "/mall")
public class MallController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(MallController.class);

    @Autowired
    private MallService mallService;

    private Gson gson = new Gson();

    @RequestMapping(value = "orderList")
    public String orderList(Model model, HttpServletRequest request) throws Exception {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("mall_orderList_params={}", searchParams);
            Page<CrmMallOrderVo> onePage = mallService.find(new PageRequest(page, size), searchParams);
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "mall/orderList";

    }


    @RequestMapping(value = "orderView/{id}")
    public String orderView(@PathVariable String id, Model model) {
        try {
            logger.debug("mall_orderView_id={}", id);
            CrmMallOrderVo order = mallService.orderView(id);
            List<CrmMallOrderLog> logs = mallService.orderLogs(id);
            model.addAttribute("data",order);
            model.addAttribute("logs",logs);
        } catch (Exception e) {
            logger.error("查看订单失败！", e);
        }
        return "mall/orderView";
    }

    /**
     * <申请退款></>
     * @param orderId
     * @param viewId
     * @param reason1
     * @param reason
     * @param request
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="applyRefund")
    public String applyRefund(String orderId,String viewId,String reason1,String reason,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
        try {
            mallService.applyRefund(orderId, CommonUtils.isNotEmpty(reason)?reason:reason1);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "申请退款成功!"));
        } catch (Exception e) {
            logger.error("申请退款失败！",e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "申请退款失败:" + e.getMessage()));
        }
        return "redirect:/mall/orderView/"+orderId;
    }

    /**
     * 确认退款
     * @param id
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="confirmRefund/{id}")
    @ResponseBody
    public String confirmRefund(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result.put(Constants.Result.RESULT, false);
            if (!CommonUtils.isNotEmpty(id)) {
                result.put(Constants.Result.REASON, "退款失败，订单号为空！");
                return gson.toJson(result);
            }
            return gson.toJson(mallService.confirmRefund(id));
        } catch (Exception e) {
            logger.error("确认退款失败！",e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "退款失败，请联系工作人员!");
            return gson.toJson(result);
        }
    }
    
    /**
     * 延期收货
     * @param id
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="delay/{id}")
    @ResponseBody
    public String delay(@PathVariable String id,RedirectAttributes redirectAttributes) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result.put(Constants.Result.RESULT, true);
            String shTime = mallService.delayOrder(id);
            result.put("data", shTime);
            return gson.toJson(result);
        } catch (Exception e) {
            logger.error("延期收货失败！",e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "延期收货失败，请联系工作人员!");
            return gson.toJson(result);
        }
    }


    @RequestMapping(value = "activityList")
    public String activityList(Model model, HttpServletRequest request) throws Exception {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("mall_activityList_params={}", searchParams);
            Page<CrmMallActivityVo> onePage = mallService.findActivity(new PageRequest(page, size), searchParams);
            model.addAttribute("data", onePage);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "mall/activityList";
    }

    @RequestMapping(value = "createActivity", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        model.addAttribute("active", "create");
        //获取所有商品
        model.addAttribute("goods",mallService.allGoods(0,"",""));
        return "mall/activityForm";
    }

    @RequestMapping(value = "saveActivity")
    public String saveActivity(CrmMallActivityVo activityVo, RedirectAttributes redirectAttributes){
        try {
            if(StringUtils.isNotEmpty(activityVo.getGoodsId())){
               mallService.saveOrUpdateActivity(activityVo);
            }
        } catch (Exception e) {
            logger.debug("保存失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存赛事失败！"));
        }
        return "redirect:/mall/activityList";
    }

    @RequestMapping(value = "activityForm/{id}")
    public String activityForm(@PathVariable String id, Model model) {
        try {
            logger.debug("mall_activityForm_id={}", id);
            CrmMallActivityVo vo = mallService.getByActivityId(id);
            model.addAttribute("data", vo);
            //获取所有商品
            model.addAttribute("goods",mallService.allGoods(0,"",""));
        } catch (Exception e) {
            logger.error("查看订单失败！", e);
        }
        return "mall/activityForm";
    }

    @RequestMapping(value = "allDnaGoods_dlg")
    public String allDnaGoods(HttpServletRequest request, Model model) {
        try {
            Integer type = Integer.valueOf(request.getParameter("type"));
            String activityId = String.valueOf(request.getParameter("activityId"));
            String goodsId = String.valueOf(request.getParameter("goodsId"));
            String delGoodsId = String.valueOf(request.getParameter("delGoodsId"));

            //type = 0 所有的商品   type = 1 没参加活动的商品 type=2 参加的商品
            if(StringUtils.isEmpty(activityId)){type = 0;}
            List<CrmMallGoods> goodsList = mallService.allGoods(type,goodsId,delGoodsId);
            model.addAttribute("data", goodsList);
            model.addAttribute("type",type);
        } catch (Exception e) {
            logger.error("添加或删除失败！", e);
        }
        return "mall/allDnaGoods";
    }

    @RequestMapping(value = "delActivity/{id}")
    public String actidelActivityvityForm(@PathVariable String id, Model model) {
        try {
            logger.debug("mall_delActivity_id={}", id);
            mallService.delByActivityId(id);
        } catch (Exception e) {
            logger.error("查看订单失败！", e);
        }
        return "redirect:/mall/activityList";
    }
}
