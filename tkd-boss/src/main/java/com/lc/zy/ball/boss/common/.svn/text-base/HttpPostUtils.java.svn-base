package com.lc.zy.ball.boss.common;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.PostRequest;
import com.lc.zy.common.util.UUID;

/**
 * 
 * @author sl
 *
 */
@Component
public class HttpPostUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpPostUtils.class);
	
	@Resource(name="configs")
	private Map<String,String> configs;

	public Success postRequestText(String service, String method, Boolean token, HashMap<String, Object> params) {
		Success success = null;
		String rn = "";
		try {
			ClientRequest args = new ClientRequest();
			// 全局唯一的UUID
			args.setSn(UUID.get());
			// 渠道
			args.setChannel("web");
			// 版本号
			args.setVersion("1.0");
			// 业务模块
			args.setService(service);
			// 方法
			args.setMethod(method);
			// 参数
			args.setParams(params);
			rn = PostRequest.postText(configs.get("app_id"), "body", MyGson.getInstance().toJson(args));
			Type type = new TypeToken<Success>() {
			}.getType();
			success = MyGson.getInstance().fromJson(rn, type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(service + "--" + method, e.getMessage());
		}
		return success;
	}

}
