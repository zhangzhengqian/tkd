package com.lc.zy.common.coreservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lc.zy.ball.coreservice.client.CoreServiceClient;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.coreservice.bean.UserStatiumCounterBean;
import com.lc.zy.common.util.MyGson;

/**
 * 用户在某个场馆的消费记录，计数器
 * @author liangc
 */
@Component
public class UserStatiumCounter {

	private static Logger logger = LoggerFactory.getLogger(UserStatiumCounter.class);
	
	@Resource(name="configs")
	private Map<String,String> configs = null;

	private String license = null;
	private CoreServiceClient client = null;
	
	@PostConstruct
	void init(){
		try{
			String host = configs.get("coreServiceOrdersHost");
			license = configs.get("coreServiceOrdersLicense");
			String[] arr = host.split(":");
			String ip = arr[0];
			String port = arr[1].trim();
			client = new CoreServiceClient(ip,Integer.parseInt(port));
		}catch(Exception e){
			logger.warn("coreServiceOrdersHost and coreServiceOrdersLicense not define");
		}
	}
	
	/**
	 * 订单总数自增，针对给定的 pk ，自增订单总数
	 * @param userId 用户标示
	 * @param statiumId 场馆标示
	 * @param counter 非必填，默认为 1，即这个用户的这个场馆自增1
	 * @return
	 */
	public void inc(String userId,String statiumId){
		inc(userId,statiumId,1);
	}
	public void inc(String pk,String statiumId,int counter){
		try{
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			params.put("id", statiumId);
			params.put("counter", counter);
			ClientRequest request = new ClientRequest();
			request.setService("userStatiumCounter");
			request.setMethod("inc");
			request.setParams(params);
			String args = MyGson.getInstance().toJson(request);
			String rtn = client.process(license,args);
			logger.debug(rtn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取 pk 对应的订单总数 
	 * @param userId
	 * @param statiumId 
	 * @return
	 */
	public List<UserStatiumCounterBean> fetch(String userId){
		return fetch(userId,null);
	}
	public List<UserStatiumCounterBean> fetch(String pk,String statiumId){
		try {
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			if(StringUtils.isNotEmpty(statiumId)){
				params.put("id", statiumId);
			}
			ClientRequest request = new ClientRequest();
			request.setService("userStatiumCounter");
			request.setMethod("fetch");
			request.setParams(params);
			String rtn = client.process(license,MyGson.getInstance().toJson(request));
/*
			{
				"pk" : 1,
				"statium" : [
					{
						"id" : "hello",
						"counter" : 3
					},
					{
						"id" : "world",
						"counter" : 1
					}
				]
			} 
 */
			logger.debug("fetch_response = {}",rtn);
			JSONObject json = new JSONObject(rtn);
			boolean success = json.getBoolean("success");
			List<UserStatiumCounterBean> list = new ArrayList<UserStatiumCounterBean>();
			if(success){
				JSONArray array = json.getJSONObject("entity").getJSONArray("statiumCounter");
				for(int i=0;i<array.length();i++){
					JSONObject obj = array.getJSONObject(i);
					UserStatiumCounterBean bean = MyGson.getInstance().fromJson(obj.toString(), UserStatiumCounterBean.class);
					list.add(bean);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

}
