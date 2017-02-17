package com.lc.zy.common.mq;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

	private static Logger logger = LoggerFactory.getLogger(QueueProducer.class);
	
	@Resource(name="configs")
	private Map<String,Object> configs = null;

	private Producer producer = null;

	private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(102400);
    
	private boolean _CUSTOMER_LOCK = false;

	private final static String KILL = "\01KILL\01";
    /**
     * 消息入队
     * @param alarmMessageVO
     * @return
     */
    public boolean push(String queueName , String textMessage) {
    	StringBuffer sb = new StringBuffer(queueName);
    	sb.append("\01");
    	sb.append(textMessage);
        return blockingQueue.offer(sb.toString());
    }

    public void shutdown() throws Exception {
    	logger.debug("loc_consumer kill ...");
    	Thread.sleep(2000);
    	blockingQueue.offer(KILL);
    }
    
    @PostConstruct
    public void init(){
    	String brokerUrl = null; 
    	try{
    		brokerUrl = (String)configs.get("brokerUrl");
    		if (brokerUrl==null) 
    			throw new Exception();
    		synchronized (this) {
    			if(_CUSTOMER_LOCK==false){
    				logger.debug("locCustomer was started ...");
    				_CUSTOMER_LOCK = true;
					logger.debug("brokerUrl="+brokerUrl);
    				producer = new Producer(brokerUrl);
    				new Thread(new Runnable() {
						@Override
						public void run() {
							while(true){
								try {
									String msg = blockingQueue.take();
									logger.debug("send:::>{}",msg);
									if(KILL.equals(msg)){
										return;
									}
									String[] arr = msg.split("\01");
									producer.sendTo(arr[0], arr[1]);
								} catch (InterruptedException e) {
									logger.error("loc_customer InterruptedException",e);
								}
							}
						}
					}).start();
    			}else{
    				logger.debug("loc_customer started redundant ...");
    			}
    		}
    	}catch(Exception e){
    		
    	}
    }
    
    class Producer {
    	private ActiveMQConnectionFactory connectionFactory = null;	
    	Connection connection = null;
    	Producer(String url){
    		try {
    			connectionFactory = new ActiveMQConnectionFactory(url);
    			connection = connectionFactory.createConnection();
    			connection.start();
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	public void sendTo(String queueName,String textMessage){
    		Session session = null;
    		MessageProducer producer = null;
    		try {
            	session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue(queueName);
                producer = session.createProducer(destination);

                TextMessage message = session.createTextMessage(textMessage);
                producer.send(message);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
            	try{
            		if(producer!=null){
            			producer.close();
            		}
            		if(session!=null){
            			session.close();
            		}
            	}catch(Exception e){
            		e.printStackTrace();
            	}
           }
    	}
    	
    	
    }

	public Map<String, Object> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, Object> configs) {
		this.configs = configs;
	}
    
}



