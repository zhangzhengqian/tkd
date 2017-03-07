package com.lc.zy.ball.boss.framework.event.controller;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.FlashAttributeUtil;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.event.service.PlatinumMatchService;
import com.lc.zy.ball.boss.framework.event.vo.EnjoyMemberVo;
import com.lc.zy.ball.boss.framework.event.vo.PlatinumMatchVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.po.EnjoyGame;
import com.lc.zy.ball.domain.oa.po.PlatinumMatch;
import com.lc.zy.common.util.CommonOAUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;
import org.apache.commons.lang3.StringUtils;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: liangsh
 * Date: 2016/6/13
 * Time: 17:53
 */
@Controller
@RequestMapping(value = "platinumMatch")
public class PlatinumMatchController  extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(PlatinumMatchController.class);

    @Autowired
    private PlatinumMatchService platinumMatchService;

    @RequestMapping(value = "list")
    public String list(Model model, HttpServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<PlatinumMatchVo> onePage = platinumMatchService.list(new PageRequest(page, size), searchParams);
        model.addAttribute("data", onePage);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "platinummatch/list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        model.addAttribute("active", "create");
        return "platinummatch/form";
    }

    @RequestMapping(value = "save")
    public String save(PlatinumMatchVo gameVo, RedirectAttributes redirectAttributes) {
        try {
            if(gameVo != null){
                int size = 1;
                if(StringUtils.isBlank(gameVo.getId())){
                    size = gameVo.getGameTypes().length;
                }
                String eventId="";
                String id = "";
                for (int i = 0; i < size; i++) {
                    //获取原赛事项目类型
                    PlatinumMatch game = new PlatinumMatch();
                    if(i == 0){
                        BeanUtils.copyProperties(gameVo, game);
                    }

                    //只更新第一条赛事基本信息
                    if(StringUtils.isBlank(game.getId())){

                        game.setLevel(gameVo.getLevels()[gameVo.getGameTypes()[i]-1]);
                        game.setType(gameVo.getGameTypes()[i]);
                        game.setPrice(new Double(Double.valueOf(gameVo.getShowPrices()[gameVo.getGameTypes()[i]-1]) * 100).intValue());
                    }
                    eventId = platinumMatchService.createOrUpdate(game,id);
                    if(i == 0) {
                        id = eventId;
                    }
                }
            }
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(true, "保存赛事成功!"));
        } catch (Exception e) {
            logger.debug("保存赛事失败: {}", e.getMessage());
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", FlashAttributeUtil.build(false, "保存赛事失败！"));
        }
        return "redirect:/platinumMatch/list";
    }

    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable String id, Model model) {
        try {
            PlatinumMatch game = platinumMatchService.selectByPrimaryKey(PlatinumMatch.class,id);
            Map<Integer,PlatinumMatch> gameMap =  platinumMatchService.gameVo(game);
            StringBuilder sb = new StringBuilder(game.getType());
            for(Map.Entry<Integer,PlatinumMatch> entry:gameMap.entrySet()){
                sb.append(",").append(entry.getKey());
            }
            model.addAttribute("gameMap",gameMap);
            model.addAttribute("event", gameMap.get(game.getType()));
            model.addAttribute("mapKeys", sb.toString());
        } catch (Exception e) {
            logger.error("查看赛事失败！", e);
        }
        return "platinummatch/form";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
            throws Exception {
        PlatinumMatch game = platinumMatchService.selectByPrimaryKey(PlatinumMatch.class,id);
        Map<Integer,PlatinumMatch> gameMap =  platinumMatchService.gameVo(game);
        StringBuilder sb = new StringBuilder(game.getType());
        for(Map.Entry<Integer,PlatinumMatch> entry:gameMap.entrySet()){
            sb.append(",").append(entry.getKey());
        }
        model.addAttribute("gameMap",gameMap);
        model.addAttribute("event", gameMap.get(game.getType()));
        model.addAttribute("mapKeys", sb.toString());
        model.addAttribute("action", "update");
        return "platinummatch/form";
    }

    @RequestMapping(value = "delGames", method = RequestMethod.POST)
    @ResponseBody
    public String delGames(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(id)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            } else {
                PlatinumMatch game = platinumMatchService.selectByPrimaryKey(PlatinumMatch.class, id);
                if (game != null) {
                    game.setDeleteFlag(1);
                    game.setEb(SessionUtil.currentUserId());
                    game.setEt(new Date());
                    platinumMatchService.updateByPrimaryKeySelective(game, id);
                }
                result.put(Constants.Result.RESULT, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, e.getMessage());
        }
        return MyGson.getInstance().toJson(result);
    }

    @RequestMapping(value = "memberList/{id}")
    public String memberList(@PathVariable String id, Model model, HttpServletRequest request) throws Exception {
        logger.info("EnjoyRaceController memberList method execute!");
        // 根据查询条件查询数据
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        CommonOAUtils.paramesTrim(searchParams);// 参数去空
        int page = WebUtils.getPage(request);
        int size = WebUtils.getPageSize(request);
        Page<EnjoyMemberVo> onePage = platinumMatchService.findMemberList(new PageRequest(page, size), searchParams, id);
        PlatinumMatch game = platinumMatchService.selectByPrimaryKey(PlatinumMatch.class, id);
        model.addAttribute("data", onePage);
        model.addAttribute("id", id);
        if (onePage != null && onePage.getContent().size() > 0) {
            Integer doubleFlag = 0;
            for (EnjoyMemberVo enjoyMemberVo : onePage.getContent()) {
                if(enjoyMemberVo.getDoubleFlag() == 1){
                    doubleFlag = 1;
                }
            }

            model.addAttribute("doubleFlag", doubleFlag);
        }
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "platinummatch/memberList";
    }

    @RequestMapping(value = "updateMemberState/{eventId}/{id}/{status}/{reason}")
    public String updateMemberState(@PathVariable String eventId, @PathVariable String id,
                                    @PathVariable Integer status,@PathVariable String reason,RedirectAttributes redirectAttributes) {
        logger.info("成员审核 id={} , status={} ", id, status);
        try {
            eventId = platinumMatchService.updateMember(id, status,reason);
        } catch (Exception e) {
            logger.error("查看用户审核列表时报", e);
        }
        if (StringUtils.isBlank(eventId)) {
            redirectAttributes.addAttribute("error", "订单未支付请支付后再审核");
        }
        return "redirect:/platinummatch/memberList/" + eventId;
    }

    @RequestMapping(value = "/refuseSigned_dlg")
    public String refuseSigned(HttpServletRequest request, Model model) {
        String id  = request.getParameter("id").toString();
        String idA = request.getParameter("idA").toString();
        Integer state = Integer.valueOf(request.getParameter("state"));
        model.addAttribute("id",id);
        model.addAttribute("idA",idA);
        model.addAttribute("state",state);
        return "platinummatch/refuseSigned";
    }

    @RequestMapping(value = "auditMember", method = RequestMethod.POST)
    @ResponseBody
    public String auditMember(HttpServletRequest request) {
        String id  = request.getParameter("id").toString();
        String idA = request.getParameter("idA").toString();
        Integer state = Integer.valueOf(request.getParameter("state"));
        String reason = String.valueOf(request.getParameter("reason"));
        logger.info("auditMember id={}, idA={} , state={},reason={} ",id,idA,state,reason);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(id)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "订单未支付请支付后再审核");
            }else if (StringUtils.isBlank(idA)) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "memberId不能为空");
            } else  if (state == null) {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "state不能为空");
            }else {
                platinumMatchService.updateMember(idA,state,reason);
                result.put(Constants.Result.RESULT, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, e.getMessage());
        }
        return MyGson.getInstance().toJson(result);
    }
}
