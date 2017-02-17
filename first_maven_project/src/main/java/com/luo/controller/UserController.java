package com.luo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.luo.domain.User;
import com.luo.domain.mapper.TUserMapper;
import com.luo.domain.po.TUser;
import com.luo.service.UserService;

@Controller
@RequestMapping(value="")
public class UserController {
	
	@Autowired
	private TUserMapper tMapper;
	
    @RequestMapping(value = "/login")    
    public String login(HttpServletRequest request,Model model,User user){      
        Integer id = user.getUserId();
        //User loginUser  = userService.selectUserById(1);  
        TUser loginUser = tMapper.selectByPrimaryKey(1);
        model.addAttribute("user", loginUser);
        return "user/success";    
    }
    
    @RequestMapping(value = "/index")
    public ModelAndView getIndex(){
    	ModelAndView view = new ModelAndView("/user/index");
    	return view;
    }
}  
