package com.lc.zy.common.mq;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * bizLogColl 参数，相当于日志所存放的表，可以通过 coll 参数来重写
 * @author liangc
 *
 */
@Component
public class BizLog {

	private static Logger logger = LoggerFactory.getLogger(BizLog.class);
	
	@Resource(name="configs")
	private Map<String,Object> configs = null;
	@Autowired
	private TopicPublisher topicPublisher = null;
	
	public Map<String, Object> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, Object> configs) {
		this.configs = configs;
	}

	public TopicPublisher getTopicPublisher() {
		return topicPublisher;
	}

	public void setTopicPublisher(TopicPublisher topicPublisher) {
		this.topicPublisher = topicPublisher;
	}

	/**
	 * 日志服务监听的主题
	 */
	private String BALL_LOG_TOPIC = "BALL_LOG_TOPIC";
	
	@PostConstruct
	public void init(){
		System.out.println("######################");
		System.out.println("## BizLog started.");
		System.out.println(topicPublisher.toString());
		System.out.println("######################");
	}
	
	/**
	 * 将日志写入指定集合中
	 * @param coll 日志存放的集合
	 * @param text 日志内容
	 */
	public void write(String coll,String text){
		logger.debug("coll={} , text={}",coll,text);
		JSONObject json = null;
		try{
			try{
				json = new JSONObject(text);
			}catch(Exception e){
				json = new JSONObject();
				json.put("comment", text);
			}
			json.put("coll", coll);
			topicPublisher.push(BALL_LOG_TOPIC, json.toString());
		}catch(Exception e){
			logger.error("biz_log_write_error",e);
		}
	}

	/**
	 * 写入日志到 configs.bizLogColl 参数对应的集合中
	 * @param text
	 */
	public void write(String text){
		String bizLogColl = (String)configs.get("bizLogColl");
		write(bizLogColl,text);
	}

}
