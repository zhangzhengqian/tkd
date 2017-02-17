package com.lc.zy.common.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 队列消费者
 * @author liangc
 */
public class QueueConsumer {

	
	public static void main(String[] args) throws Exception {
		String queueName = "foo";
		String brokerUrl = "tcp://180.76.153.246:61616";
		QueueConsumer consumer = new QueueConsumer();
		
		consumer.listener(new ConsumerHandler(brokerUrl,queueName ){
			@Override
			public void receive(String body) throws Exception {
				System.out.println(body);
			}
		});
	}
	
	private static Logger logger = LoggerFactory.getLogger(QueueConsumer.class);
	
	/**
	 * 启动一个消费者
	 * @param handler
	 * @throws Exception 
	 */
	public void listener(final ConsumerHandler handler) {
		logger.debug("queue_consumer_start_with url="+handler.getUrl()+" ; queueName="+handler.getQueueName());
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.debug("queue_consumer_thread_start.");
				try {
					ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(handler.getUrl());
					Connection connection = connectionFactory.createConnection();
					connection.start();
					logger.debug("queue_consumer_connectioned...");
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					logger.debug("queue_consumer_open_session...");
					Destination destination = session.createQueue(handler.getQueueName());
					MessageConsumer consumer = session.createConsumer(destination);
					while (true) {
						try {
							Message message = consumer.receive();
							if (message != null) {
								if (message instanceof TextMessage) {
									String text = ((TextMessage) message)
											.getText();
									handler.receive(text);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}