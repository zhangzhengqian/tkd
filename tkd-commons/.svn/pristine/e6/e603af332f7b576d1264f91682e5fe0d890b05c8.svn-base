package com.lc.zy.common.zoo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import com.lc.zy.common.util.SpringUtils;
import com.lc.zy.common.zoo.lock.WriteLock;

public class SecurityLock {

	private WriteLock lock;
	private String basePath = "/lock";
	private ZooKeeper zooKeeper = null;
	private String lockPath = "";
	public SecurityLock(){
		if(zooKeeper==null){
			ConnectionWatcher c = (ConnectionWatcher)SpringUtils.getBean("connectionWatcher");
			zooKeeper = c.zk();
		}
		init();
	}
	public SecurityLock(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
		init();
	}
	private void init(){
		try {
			if(zooKeeper.exists(basePath, null) == null){
				zooKeeper.create(basePath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得锁
	 * @return 获得锁是否成功
	 */
	public boolean lock(String lockPath) {
		this.lockPath = lockPath;
		try {
			lockPath = basePath + "/" + lockPath;
			lock = new WriteLock(zooKeeper, lockPath, null);
			if (lock.lock()) {
				return true;
			}else{
				return false;
			}
		} catch (KeeperException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 解锁
	 */
	public void unlock() {
		System.err.println("释放锁="+lockPath);
		lock.unlock();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Watcher w = new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				
			}
		};
		ZooKeeper zooKeeper = new ZooKeeper("180.76.153.246", 20000, w);
		final SecurityLock securityLock = new SecurityLock(zooKeeper);
		
    	new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (securityLock.lock("foo")) {
						System.out.println("获得锁---------------");
						Thread.sleep(20000 * 5);
						securityLock.unlock();
						System.out.println("释放锁---------------");
					}else{
						System.out.println("没有拿到---------------");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
    	Thread.sleep(200);
    	//ZooKeeper zooKeeper1 = new ZooKeeper("180.76.153.246", 20000, w);
		final SecurityLock securityLock1 = new SecurityLock(zooKeeper);
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (securityLock1.lock("foo")) {
					System.out.println("获得锁++++++++++++++");
					securityLock1.unlock();
					System.out.println("释放锁+++++++++++++");
				}else{
					System.out.println("没有拿到++++++++++++");
				}
			}
		}).start();
	}

}
