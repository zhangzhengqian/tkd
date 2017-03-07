package com.lc.zy.ball.boss.common.service;

public interface Provider {

	/**
	 * 重新加载数据.
	 */
	void reload();
	
	
	/**
	 * 清除已加载的数据.
	 */
	void clear();
}
