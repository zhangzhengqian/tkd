package com.lc.zy.common.jms.topic;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lc.zy.common.mq.TopicPublisher;

public class TopicPublisherTest {

	//@Test
	public void pushSend() throws Exception{
		//点对点消息生产者
		TopicPublisher p = new TopicPublisher();
		Map<String,Object> configs = new HashMap<String,Object>();
		configs.put("brokerUrl", "tcp://180.76.153.246:61616");
		p.setConfigs(configs);
		p.init();
		for(int i=0;i<10;i++) {
			// 向 foo 队列发送文本消息 bar
			p.push("foo", "bar");
		}
		System.out.println("hello world.");
		//关闭生产者，只在测试时使用
		Thread.sleep(2000);
		p.shutdown();
	}

}