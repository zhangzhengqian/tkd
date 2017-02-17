package com.lc.zy.common.mq;

/**
 * 消息处理者
 * @author liangc
 *
 */
public abstract class ConsumerHandler {
	
	private String url = null;
	private String queueName = null;
	
	public ConsumerHandler(String url,String queueName){
		this.url = url;
		this.queueName = queueName;
	}
	/**
	 * 接受指定队列的消息
	 * @param body
	 * @throws Exception
	 */
	public abstract void receive(String body) throws Exception;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	
}
