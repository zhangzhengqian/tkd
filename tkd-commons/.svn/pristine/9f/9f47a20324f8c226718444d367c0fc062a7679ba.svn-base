package com.lc.zy.common.coreservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.coreservice.client.CoreServiceClient;
import com.lc.zy.common.util.MyGson;

/**
 * 用户在某个场馆的消费记录，计数器
 * @author liangc
 */
@Component
public class FreeSpaceCounter {

	private static Logger logger = LoggerFactory.getLogger(FreeSpaceCounter.class);
	
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
			logger.warn("FreeSpaceCounter and coreServiceOrdersLicense not define");
		}
	}
	
	/**
	 * 场地被占用了，就要调用这个函数，占用或者释放 
	 * 
	 * @param startDate
	 * @param statiumId
	 * @param spaceId
	 * @param sportType
	 * @param counter 占用场地传 正数 1 ，释放场地 传 负 1 
	 */
	public void inc(String startDate,String statiumId,String spaceId,String sportType,Integer counter){
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("start_date",startDate);
			params.put("space_id",spaceId);
			params.put("statium_id",statiumId);
			params.put("sport_type",sportType);
			if(counter!=null){
				params.put("counter",counter);
			}

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("service", "freeSpaceCounter");
			map.put("method", "inc");
			map.put("params", params);

			String args = MyGson.getInstance().toJson(map);
			String rtn = client.process(license,args);
			logger.debug(rtn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取场馆某个运动类型，指定日期的空场 字典 
	 * @param startDate 开始日期 格式 yyyy-MM-dd
	 * @param days 取几天的数据就传几
	 * @param statiumId 场馆id
	 * @param sportType 运动类型，英文编码
	 * @return {"日期":总数,...}
	 */
	public HashMap<String,Integer> fetch(String startDate,Integer days,String statiumId,String sportType){
		try {
			/*
		    args = {
		        "start_date":"2015-01-01",
		        "days":3,
		        "statium_id":statium_id,
		        "sport_type":sport_type
		    }
		    */
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("start_date",startDate);
			params.put("days",days);
			params.put("statium_id",statiumId);
			params.put("sport_type",sportType);

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("service", "freeSpaceCounter");
			map.put("method", "fetch");
			map.put("params", params);
			
			String rtn = client.process(license,MyGson.getInstance().toJson(map));

			logger.debug("fetch_response = {}",rtn);
			JSONObject json = new JSONObject(rtn);
			boolean success = json.getBoolean("success");
			//rtn={"success": true, "entity": {"data": {"20150101": 155, "20150103": 156, "20150102": 156}}}
			if(success){
				JSONObject obj = json.getJSONObject("entity").getJSONObject("data");
				HashMap<String, Integer> m = MyGson.getInstance().fromJson(obj.toString(),  new TypeToken<HashMap<String, Integer>>(){}.getType());
				return m;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new HashMap<String,Integer>();
	}

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

}
