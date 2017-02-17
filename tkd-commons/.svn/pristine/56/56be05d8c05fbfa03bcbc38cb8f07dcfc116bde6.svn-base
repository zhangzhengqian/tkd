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
 * 评分模块
 * 根据指定的资源 id，进行评分
 * 提供获取平均分的api
 * @author liangc
 */
@Component
public class Scores {

	private static Logger logger = LoggerFactory.getLogger(Scores.class);
	
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
	 * 针对给定的 pk 进行打分
	 * @param pk
	 * @param score 评分 
	 * @return
	 */
	public void inc(String pk,int score){
		try{
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			params.put("score", score);
			ClientRequest request = new ClientRequest();
			request.setService("score");
			request.setMethod("inc");
			request.setParams(params);
			String rtn = client.process(license,MyGson.getInstance().toJson(request));
			logger.debug(rtn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取 pk 对应的平均分
	 * @param pk
	 * @return
	 */
	public int avg(String pk){
		try {
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("pk", pk);
			ClientRequest request = new ClientRequest();
			request.setService("score");
			request.setMethod("avg");
			request.setParams(params);
			String rtn = client.process(license,MyGson.getInstance().toJson(request));
			logger.debug(rtn);
			JSONObject json = new JSONObject(rtn);
			boolean success = json.getBoolean("success");
			if(success){
				return json.getJSONObject("entity").getInt("avg");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	

}
