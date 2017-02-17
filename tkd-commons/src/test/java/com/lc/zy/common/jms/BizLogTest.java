package com.lc.zy.common.jms;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lc.zy.common.mq.BizLog;
import com.lc.zy.common.mq.TopicPublisher;

/**
 * bizLogColl 参数，相当于日志所存放的表，可以通过 coll 参数来重写
 * @author liangc
 *
 */
public class BizLogTest {
	
	//@Test
	public void testWrite() throws InterruptedException{
		// 构造对象，实际使用时，不必如此 >>>>
		TopicPublisher p = new TopicPublisher();
		Map<String,Object> configs = new HashMap<String,Object>();
		configs.put("brokerUrl", "tcp://180.76.153.246:61616");
		p.setConfigs(configs);
		p.init();
		BizLog log = new BizLog();
		log.setConfigs(configs);
		log.setTopicPublisher(p);
		// 构造对象，实际使用时，不必如此 <<<<
		
		log.write("test", "18612013831:你好么？我很好，你呢？");
		log.write("test", "13810987660:你好么？我很好，你呢？");
		Thread.sleep(2000);
	}
	
}
