package com.lc.zy.common.zoo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

@Component("connectionWatcher")
public class ConnectionWatcher implements Watcher {

	//默认的池大小
	private static int DEF_POOL_SIZE = 2;
	private static Queue<ZooKeeper> pool = null;

	private static final int SESSION_TIMEOUT = 5000;
	
	public boolean STARTUP = false;
	
	public static ZooKeeper zk(){
		ZooKeeper zk = null;
		try {
			zk = pool.poll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(zk!=null){
				pool.add(zk);
			}
		}
		return zk;
	}

	private CountDownLatch connectedSignal = null;
	@Resource(name="configs")
	public Map<String,Object> configs = new HashMap<String,Object>();
	
	public Map<String, Object> getConfigs() {
		return configs;
	}
	public void setConfigs(Map<String, Object> configs) {
		this.configs = configs;
	}	
//	#ip:port,ip:port,... default port is 2181
//	zoo.server=192.168.2.109
	@PostConstruct
	public void connect() throws IOException, InterruptedException{
		System.setProperty("jute.maxbuffer", String.valueOf(4096 * 1024*10));
		String hosts = (String)configs.get("zoo.server");
		String poolSize = (String)configs.get("zoo.server.pool");
		int POOL_SIZE = DEF_POOL_SIZE;
		if (StringUtils.isNotEmpty(poolSize)){
			POOL_SIZE = Integer.parseInt(poolSize);
		}
		pool = new ArrayBlockingQueue<ZooKeeper>(POOL_SIZE);
		connectedSignal = new CountDownLatch(POOL_SIZE);
		try {
			if(StringUtils.isNotEmpty(hosts)){
				for (int i = 0; i < POOL_SIZE; i++) {
					ZooKeeper zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
					pool.add(zk);
				}
				//阻塞
				connectedSignal.await();
				STARTUP = true;
			}else{
				throw new Exception("在 configs 中缺少 [zoo.server] 配置项,无法正常启动服务");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void process(WatchedEvent event) {
		if( event.getState().equals(KeeperState.SyncConnected)){
			//解除阻塞：连接成功时会来解除阻塞
			connectedSignal.countDown();
		}
	}

	/**
	 * 废弃的方法，链接创建了，为什么还要关闭？
	 * @throws InterruptedException
	 */
	@Deprecated
	public void close() throws InterruptedException{
	}

}
