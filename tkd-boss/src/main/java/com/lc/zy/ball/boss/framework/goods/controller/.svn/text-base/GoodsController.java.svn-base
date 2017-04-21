package com.lc.zy.ball.boss.framework.goods.controller;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.boss.framework.goods.service.GoodsService;
import com.lc.zy.ball.boss.framework.goods.vo.GoodVo;
import com.lc.zy.ball.domain.oa.po.CrmMallGoods;
import com.lc.zy.common.util.UUID;

@Controller
@RequestMapping(value = "goods")
public class GoodsController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value = { "", "/" })
    public String index(HttpServletRequest request, Model model) throws Exception {
    	List<GoodVo> goods = new ArrayList<GoodVo>();
    	List<CrmMallGoods> goods_ = goodsService.list();
    	GoodVo good = null;
    	for(CrmMallGoods good_ :goods_){
    		good = new GoodVo();
    		BeanUtils.copyProperties(good_, good);
    		good.setFee(new BigDecimal(good.getPrice()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue());
    		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
    		good.setFeeView(df.format(good.getFee()));
    		goods.add(good);
    	}
    	model.addAttribute("data", goods);
        return "goods/list";
    }
    
    @RequestMapping(value = "add")
    public String add(HttpServletRequest request, Model model) throws Exception {
        return "goods/add";
    }
    
    
    @RequestMapping(value = "save")
    public String save(HttpServletRequest request,GoodVo good_, Model model) throws Exception {
    	CrmMallGoods good = new CrmMallGoods();
    	BeanUtils.copyProperties(good_, good);
    	good.setPrice((new BigDecimal(good_.getFee()).multiply(new BigDecimal(100))).divide(new BigDecimal(1), 1, BigDecimal.ROUND_HALF_UP).intValue());
    	if(StringUtils.isBlank(good.getId())){
    		good.setId(UUID.get());
    		good.setCt(new Date());
    		good.setCb(SessionUtil.currentUserId());
    		goodsService.insertSelective(good, good.getId());
    	}else{
    		good.setEt(new Date());
    		good.setEb(SessionUtil.currentUserId());
    		goodsService.updateByPrimaryKeySelective(good, good.getId());
    	}
        return "redirect:/goods";
    }
    
    @RequestMapping(value = "update/{id}")
    public String update(HttpServletRequest request,@PathVariable String id, Model model) throws Exception {
    	CrmMallGoods good_ = goodsService.selectByPrimaryKey(CrmMallGoods.class, id);
    	GoodVo good = new GoodVo();
		BeanUtils.copyProperties(good_, good);
		good.setFee(new BigDecimal(good.getPrice()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue());
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
		good.setFeeView(df.format(good.getFee()));
    	model.addAttribute("good", good);
        return "goods/add";
    }
    
    @RequestMapping(value = "set/{id}/{status}")
    public String set(HttpServletRequest request,@PathVariable String id,@PathVariable Integer status, Model model) throws Exception {
    	CrmMallGoods good = goodsService.selectByPrimaryKey(CrmMallGoods.class, id);
    	if(status==1||status==0){
    		good.setStatus(status);
    		goodsService.updateByPrimaryKeySelective(good, id);
    	}
    	return "redirect:/goods";
    }
    
    @RequestMapping(value = "delete/{id}")
    public String delete(HttpServletRequest request,@PathVariable String id, Model model) throws Exception {
    	if(goodsService.deletable(id)){
    		CrmMallGoods good = goodsService.selectByPrimaryKey(CrmMallGoods.class, id);
        	good.setStatus(2);
        	goodsService.updateByPrimaryKeySelective(good, id);
    	}
    	return "redirect:/goods";
    }
    
    public static void main(String[] args) {
		System.out.println((new BigDecimal(588.88d).multiply(new BigDecimal(100))).divide(new BigDecimal(1), 1, BigDecimal.ROUND_HALF_UP).intValue());
	}
    
}