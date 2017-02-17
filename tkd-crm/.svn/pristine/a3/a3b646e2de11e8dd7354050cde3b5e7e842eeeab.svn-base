/**
 * 
 */
package com.lc.zy.ball.crm.common.service;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wu.Yanhong
 *
 */
public abstract class AbstractProvider implements Provider {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AtomicBoolean loading = new AtomicBoolean();
	
	private AtomicBoolean initialized = new AtomicBoolean();

	/**
	 * 如果数据还没准备好就等待加载数据，否则什么也不做.
	 */
	protected void tryLoad() {
		if (!initialized.get() && !loading.getAndSet(true)) {
			reload();
		} else if (loading.get()) {
			log.debug("===>> Waitting for load data...");
			try {
				synchronized (this) {
					this.wait();
				}
			} catch (InterruptedException e) {
				log.warn(e.getMessage(), e);
			}
		}
	}
	
	@Override
	public void reload() {
		loading.set(true);
		
		log.debug("===>> Load data from database...");
		load();
		
		loading.set(false);
		initialized.set(true);
		
		synchronized (this) {
			this.notifyAll();
		}
	}

	/**
	 * 加载数据的逻辑，由子类实现.
	 */
	protected abstract void load();

	@Override
	public void clear() {}

}
