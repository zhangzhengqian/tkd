package com.lc.zy.common.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
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
public class TopicListener {

	
	public static void main(String[] args) throws Exception {
		String queueName = "foo";
		String brokerUrl = "tcp://180.76.153.246:61616";
		TopicListener consumer = new TopicListener();
		
		consumer.listener(new ConsumerHandler(brokerUrl,queueName ){
			@Override
			public void receive(String body) throws Exception {
				System.out.println(body);
			}
		});
		System.out.println("什么啊");
		Thread.sleep(2000);
		consumer.shutdown();
	}
	
	private static Logger logger = LoggerFactory.getLogger(TopicListener.class);

	private ActiveMQConnectionFactory connectionFactory = null; 
	private Connection connection = null;
	private Session session = null; 
	/**
	 * 关闭并停止监听
	 */
	public void shutdown(){
		try{
			session.close();
			connection.close();
		}catch(Exception e){}
	}
	/**
	 * 启动一个消费者
	 * @param handler
	 * @throws JMSException 
	 * @throws Exception 
	 */
	public void listener(final ConsumerHandler handler) throws JMSException {
		logger.debug("queue_consumer_start_with url="+handler.getUrl()+" ; queueName="+handler.getQueueName());
		connectionFactory = new ActiveMQConnectionFactory(handler.getUrl());
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.debug("queue_consumer_thread_start.");
				try {
					logger.debug("queue_consumer_connectioned...");
					logger.debug("queue_consumer_open_session...");
					Destination destination = session.createTopic(handler.getQueueName());
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
						} catch (javax.jms.IllegalStateException e0) {
							System.out.println("a topic listener stoped");
							break ;
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