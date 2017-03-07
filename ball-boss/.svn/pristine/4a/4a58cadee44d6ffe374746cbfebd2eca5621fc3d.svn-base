package com.lc.zy.ball.boss.framework.group.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.group.service.GroupService;
import com.lc.zy.ball.boss.framework.group.vo.GroupUserVo;
import com.lc.zy.ball.boss.framework.group.vo.GroupVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "group")
public class GroupController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    /**
     * 
     * <群组管理列表><功能具体实现>
     *
     * @create：2016年1月21日 下午4:39:35
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("group_list_search_params={}", searchParams);
            Page<GroupVo> onePage = groupService.find(new PageRequest(page, size), searchParams, true);
            model.addAttribute("data", onePage);
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "qiuyouzone/group/list";
    }

    /**
     * 
     * <群组详情><功能具体实现>
     *
     * @create：2016年1月21日 下午5:52:17
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "detail/{id}")
    public String detail(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        try {
            GroupVo groupVo = groupService.detail(id);
            model.addAttribute("group", groupVo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "qiuyouzone/group/groupDetail";
    }

    /**
     * 
     * <群成员列表><功能具体实现>
     *
     * @create：2016年1月21日 下午4:39:35
     * @author： CYY
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "userList/{id}")
    public String userList(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("group_list_search_params={}", searchParams);
            searchParams.put("EQ_statiumId", id);
            Page<GroupUserVo> onePage = groupService.userList(new PageRequest(page, size), searchParams, true);
            model.addAttribute("data", onePage);
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "qiuyouzone/group/groupUserList";
    }
    
    /**
     * 
     * <管理员解散群><功能具体实现>
     *
     * @create：2016年1月29日 上午10:09:45
     * @author： CYY
     * @param groupId
     * @return
     */
    @RequestMapping(value = "disband")
    @ResponseBody
    public String disband(String groupId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(groupId)) {
                groupService.disband(groupId);
                result.put(Constants.Result.RESULT, true);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, "id不能为空");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.put(Constants.Result.RESULT, false);
            result.put(Constants.Result.REASON, "服务器繁忙");
        }
        return MyGson.getInstance().toJson(result);
    }
}