package com.lc.zy.common.coreservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lc.zy.ball.coreservice.client.CoreServiceClient;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.util.MyGson;

/**
 * 订单计数器
 * 在一个 指定的 key 上自增，并且可以得到这个key 对应的总数
 * @author liangc
 */
@Component
public class OrdersCounter {

	private static Logger logger = LoggerFactory.getLogger(OrdersCounter.class);
	
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
	 * @param pk
	 * @param counter
	 * @return
	 */
	public void inc(String pk){
		inc(pk,1);
	}
	public void inc(String pk,int counter){
		try{
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			params.put("counter", counter);
			ClientRequest request = new ClientRequest();
			request.setService("orderCounter");
			request.setMethod("inc");
			request.setParams(params);
			String rtn = client.process(license,MyGson.getInstance().toJson(request));
			logger.debug(rtn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取 pk 对应的订单总数 
	 * @param pk
	 * @return
	 */
	public int fetch(String pk){
		try {
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			ClientRequest request = new ClientRequest();
			request.setService("orderCounter");
			request.setMethod("fetch");
			request.setParams(params);
			String rtn = client.process(license,MyGson.getInstance().toJson(request));
			logger.debug(rtn);
			JSONObject json = new JSONObject(rtn);
			boolean success = json.getBoolean("success");
			if(success){
				return json.getJSONObject("entity").getInt("counter");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}
}
