package com.lc.zy.common.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;

/**
 * crm订单通知
 */
@Component
public class OrderNotifyUtil  implements QueueNames{
	
	static String QUEUE_NAME = APP_ORDER_QUEUE;
	
	@Autowired
	private QueueProducer producer ;

	public QueueProducer getProducer() {
		return producer;
	}

	public void setProducer(QueueProducer producer) {
		this.producer = producer;
	}

	public OrderNotifyUtil() {
		
	}
	
	
	public void notifyOrder(String message){
		producer.push(QUEUE_NAME, message);
	}
	
}
