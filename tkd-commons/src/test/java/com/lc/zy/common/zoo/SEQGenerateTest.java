package com.lc.zy.common.zoo;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

public class SEQGenerateTest {
	
	public void testGetSequence() throws Exception {
		Map<String,Object> configs = new HashMap<String,Object>();
		configs.put("zoo.server", "180.76.153.246");
//		configs.put("zoo.server", "192.168.0.4");
		ConnectionWatcher c = new ConnectionWatcher();
		c.setConfigs(configs);
		c.connect();
		SEQGenerate g = new SEQGenerate();
		g.setConnectionWatcher(c);
		System.out.println(g.genOrderId());
		System.out.println(g.genQiuyouNumber());
		System.out.println(g.genTradeNo());
	}

	//@Test
	public void testOrderId() throws Exception {
		Map<String,Object> configs = new HashMap<String,Object>();
		configs.put("zoo.server", "192.168.12.213:2181,192.168.12.213:2182,192.168.12.213:2183");
		ConnectionWatcher c = new ConnectionWatcher();
		c.setConfigs(configs);
		c.connect();
		SEQGenerate g = new SEQGenerate();
		g.setConnectionWatcher(c);
		long s = System.currentTimeMillis();
		for (int i = 0; i < 2000 ; i++) {
			String oid = g.genQiuyouNumber();
		}
		long e = System.currentTimeMillis();
		System.out.printf("ts="+(e-s));
		Thread.sleep(1000);
	}

}
