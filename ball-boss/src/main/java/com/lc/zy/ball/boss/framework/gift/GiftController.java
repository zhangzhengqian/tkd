package com.lc.zy.ball.boss.framework.gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.GiftItemMapper;
import com.lc.zy.ball.domain.oa.po.GiftItem;
import com.lc.zy.ball.domain.oa.po.GiftItemCriteria;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.web.WebUtils;

@Controller
@RequestMapping(value = "gift")
public class GiftController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftItemMapper giftItemMapper;
    
    @Autowired
    private MessageUtil messageUtil;
    
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            int page = WebUtils.getPage(request);
            int size = WebUtils.getPageSize(request);
            logger.debug("group_list_search_params={}", searchParams);
            Pageable pageable = new PageRequest(page, size);
            List<GiftItem> items = new ArrayList<GiftItem>();
            GiftItemCriteria criteria = new GiftItemCriteria();
            int totalPage = giftItemMapper.countByExample(criteria);
            items = giftItemMapper.selectByExample(criteria);
            model.addAttribute("data", new PageImpl<>(items, pageable, totalPage));
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "gift/list";
    }
    
    @RequestMapping(value = "view/{id}")
    public String view(Model model,@PathVariable String id, HttpServletRequest request) throws Exception {
    	model.addAttribute("gift", giftItemMapper.selectByPrimaryKey(id));
    	return "gift/add";
    }
    
    @RequestMapping(value = "save")
    public String save(Model model,GiftItem item, HttpServletRequest request) throws Exception {
    	giftItemMapper.updateByPrimaryKeySelective(item);
    	return "redirect:/gift";
    }
    
    @RequestMapping(value = "sendMsg/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String sendMsg(String phone,@PathVariable String id,String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	String username = SessionUtil.currentUsername();
        	List<String> allowUsernames = new ArrayList<String>();
        	allowUsernames.add("zhangying");
        	allowUsernames.add("liuxu1");
        	allowUsernames.add("houxiaomin");
        	allowUsernames.add("kfsuijinfeng");
        	if(!allowUsernames.contains(username)){
        		throw new Exception("账户非法");
        	}
        	GiftItem item = new GiftItem();
        	item.setId(id);
        	item.setIsNotice(1);
        	giftItemMapper.updateByPrimaryKeySelective(item);
        	messageUtil.sendSms(phone, msg);
        	logger.debug("操作者{},手机号{},内容{}", username,phone,msg);
			result.put("result", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("result", false);
		}
        return MyGson.getInstance().toJson(result);
    }

}