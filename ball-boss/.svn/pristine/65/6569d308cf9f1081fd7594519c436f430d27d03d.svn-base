package com.lc.zy.ball.boss.framework.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lc.zy.ball.domain.oa.po.Function;
import com.lc.zy.ball.boss.framework.system.service.FunctionService;
import com.lc.zy.common.cache.RedisService;

/**
 * 用户类相关操作
 * 
 * @author cyy
 *
 */
@Controller
@RequestMapping(value = "/manu")
public class LeftMenuController {

    private static Logger logger = LoggerFactory.getLogger(LeftMenuController.class);

    @Autowired
    private FunctionService functionService = null;

    @Autowired
    private RedisService redisService = null;

    private final Gson gson = new Gson();

    /**
     * <手机验证码登入>
     * 
     * @param roleId
     *            角色id
     * @param deviceType
     *            设备类型：1为ios，2为android，3为winphone
     * @Exception 异常对象
     */
    @RequestMapping(value = "/getLeftMenu", method = RequestMethod.POST)
    @ResponseBody
    public String getLeftMenu(String roleId) {
        try {
            Map<String,String> map=new HashMap<String,String>();
        	//通过roleId检索出menu
        	List<Function> funcitonList = functionService.findExByRoleId(roleId);
        	String function=gson.toJson(funcitonList);
        	map.put("function", function);
        	map.put("success", "true");
        	//功能map
        	String functionMap=gson.toJson(map);
        	
            return functionMap;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return String.format("{\"success\": false, \"reason\": \"%s\"}", e.getMessage());
        }
    }

 
    
    
}
