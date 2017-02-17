package com.lc.zy.common.util;

public abstract class UUID {
	
	/**
	 * @return 唯一主键ID.
	 */
	public static String get() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

}
