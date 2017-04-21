package com.lc.zy.ball.crm.common.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Providers {
	
	private static Map<Class<?>, Provider> providers = new HashMap<Class<?>, Provider>();
	
	public static void add(Provider instance) {
		providers.put(instance.getClass(), instance);
	}
	
	public static void reload() {
		reload(null);
	}
	
	public static void reload(Class<?> clazz) {
		if (clazz != null) {
			Provider provider = providers.get(clazz);
			if (provider != null)
				provider.reload();
		} else {
			Collection<Provider> c = providers.values();
			for (Provider p : c) {
				executor.execute(new Worker(p));
			}
		}
	}
	
	static class Worker implements Runnable {
		Provider p;
		Worker(Provider p) {
			this.p = p;
		}

		@Override
		public void run() {
			p.reload();
		}
	}
	
	private static ExecutorService executor = Executors.newCachedThreadPool();

}
