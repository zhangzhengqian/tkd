package com.lc.zy.common.zoo;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class SecurityLockTest {

	//@Test
	public void testLock() throws Exception {
		try {
			Watcher w = new Watcher(){
				@Override
				public void process(WatchedEvent event) {}
			};
			ZooKeeper zooKeeper = new ZooKeeper("192.168.12.213", 20000, w);
			final SecurityLock fooLock = new SecurityLock(zooKeeper);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						if (fooLock.lock("foo")) {
							System.err.println("获得锁---------------");
							Thread.sleep(1000 * 3);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						fooLock.unlock();
						System.err.println("释放锁---------------");
					}
				}
			}).start();
			Thread.sleep(100);
			final SecurityLock barLock = new SecurityLock(zooKeeper);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						if (barLock.lock("foo")) {
							System.err.println("获得锁++++++++++++++");
						}else{
							System.err.println("+++++++没有拿到锁+++++");
						}
					}finally{
						barLock.unlock();
						System.err.println("释放锁+++++++++++++");
					}
				}
			}).start();
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
