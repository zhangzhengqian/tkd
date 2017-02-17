package com.lc.zy.common.push;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liangc
 */
@Component
public class PushApnQueue {
	
	private static Logger apns_logger = LoggerFactory.getLogger(PushApnQueue.class);
	
	private static BlockingQueue<PushApnBean> q = new ArrayBlockingQueue<PushApnBean>(100000);
	
	private Integer process = 100 ;

	public void push(PushApnBean obj){
		try {
			PushApnQueue.q.put(obj);
		} catch (InterruptedException e) {
			apns_logger.error("push_queue_error",e);
			e.printStackTrace();
		}
	}
	
	
	public PushApnQueue() {
		init();
	}

	public void init(){
		for(int i=100;i<100+process;i++){
			Thread t = new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						try {
							PushApnBean obj = q.take();
							String name = Thread.currentThread().getName();
							int size = q.size();
							apns_logger.debug("current_thread_name="+name+" ; queue_size="+size);
							PushApn.push(obj);
						} catch (InterruptedException e) {
							apns_logger.error("push_thread_error",e);
							e.printStackTrace();
						} catch (Exception e) {
							apns_logger.error("push_thread_error",e);
							e.printStackTrace();
						}
					}
				}
			});
			t.setName("push_apn_thread_"+i);
			t.start();
		}
	}


	public Integer getProcess() {
		return process;
	}


	public void setProcess(Integer process) {
		this.process = process;
	}
	
		
}

