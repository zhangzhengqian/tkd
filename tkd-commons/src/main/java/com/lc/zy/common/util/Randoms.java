/**
 * 
 */
package com.lc.zy.common.util;

import java.util.Random;

/**
 * 产生随机数的助手类.
 * 
 * @author wu
 *
 */
public class Randoms {

    /**
     * 返回长度为 0 < len < 7 的随机数，长度不足在前面补0.
     * 如果需要长度超过6的随机数可多次调用，再把结果拼起来.
     */  
	public static String getFixLengthString(int len) {
		if (len <= 0 || len > 6) {
			throw new IllegalArgumentException("Argument 'len' limit to greater than 0 and less than 7 (0 < len < 7).");
		}

		Random random = new Random();

		// 获得随机数
		double d = (1 + random.nextDouble()) * Math.pow(10, len);
		
		// 将获得的获得随机数转化为字符串
		String s = String.valueOf(d);

		// 返回固定的长度的随机数
		return s.substring(1, len + 1);
	}
	
	
	/**
	 * 
	 * 返回一个不超过 max 的随机整数.
	 */
	public static int nextInt(int max) {
		Random random = new Random();
		return random.nextInt(max) + 1;
	}
    
    public static void main(String[] args) {
    	for (int i=0; i < 10; i++) {
    		System.out.println(getFixLengthString(2));
    		System.out.println(getFixLengthString(6));
    		//System.out.println(getFixLengthString(7));
    	}
    	System.out.println("===========================");
    	int max = 99;
    	for (int i=0; i < 10; i++) {
    		System.out.println(nextInt(max));
    		System.out.println(nextInt(max));
    		System.out.println(nextInt(max));
    	}
    }
}
