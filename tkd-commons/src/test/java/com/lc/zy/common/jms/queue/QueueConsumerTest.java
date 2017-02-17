package com.lc.zy.common.jms.queue;

import org.junit.Test;

import com.lc.zy.common.mq.ConsumerHandler;
import com.lc.zy.common.mq.QueueConsumer;

public class QueueConsumerTest {

	String brokerUrl = "tcp://180.76.153.246:61616";
	String queueName = "foo";
	//@Test
	public void receiveTest() throws InterruptedException{
		//消费者对象
		QueueConsumer consumer = new QueueConsumer();
		//启动消费者，并传入消息处理器，监听 brokerUrl 服务器的 queueName 队列
		consumer.listener(new ConsumerHandler(brokerUrl,queueName ){
			//接收到指定的文本消息,可以是json、xml等结构化数据
			@Override
			public void receive(String body) throws Exception {
				System.out.println(body);
			}
		});
		//单元测试时，等3秒再结束，否则消费者线程会被强制回收，使用时不能这么用
		Thread.sleep(1000*3);
	}
}
