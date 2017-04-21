package com.lc.zy.ball.boss.framework.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.web.AbstractController;
import com.lc.zy.ball.coreservice.client.CoreServiceClient;
import com.lc.zy.ball.domain.oa.po.User;

/**
 * 员工管理
 * @author liangc
 */
@Controller
@RequestMapping(value = "/admin/smslog")
public class SmslogController extends AbstractController {
	
	private static Logger logger = LoggerFactory.getLogger(SmslogController.class);
	
	@Resource(name="configs")
	private Map<String,String> configs = null;
	
	@RequestMapping(value={"", "/"})
	public String index(String year,HttpServletRequest request) throws Exception {
		User u = SessionUtil.currentUser();
		logger.debug("查短信 userId={} ; userName={}",u.getUserId(),u.getNickname());
		return "admin/smslog";
	}
	
	/*		   
	日志查询服务,纯 mongo 查询语法，按照下列参数
	    输入：
	    {
	        "coll":"要查的日志集合",
	        "find":{
	            参数映射，直接送给mongo的，所以是个json
	        },
	        "skip":"分页，跳过多少条",
	        "limit":"分页，返回多少条",
	        "sort":{"field":"-1/1 降/升"}
	    }
	    输出：
	    {
	        "success":true/false,
	        "entity":{...}
	    }
	*/
	@RequestMapping(value="query")
	@ResponseBody
	public String query( String phone, HttpServletRequest request ) throws Exception {
		String[] mlogHost = configs.get("mlogHost").split(":");
		String host = mlogHost[0];
		String port = mlogHost[1];
		String mlogLicense = configs.get("mlogLicense");
		
		Map<String, Object> find = new HashMap<String, Object>();
		Map<String, Object> comment = new HashMap<String, Object>();
		comment.put("$regex", phone);
		find.put("comment", comment);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coll", "sms_log");
		map.put("find", find);
		map.put("skip", 0);
		map.put("limit", 10);
		Map<String, Object> sort = new HashMap<String, Object>();
		sort.put("ts", -1);
		map.put("sort", sort);
		Gson g = new Gson();
		String j = g.toJson(map);
		logger.debug("mlog_query = {}",j);
		CoreServiceClient client = new CoreServiceClient(host, Integer.parseInt(port));
		String rtn = client.process(mlogLicense, j);
		logger.debug("mlog_query_result = {}",rtn);
		return rtn;
	}
	
}
