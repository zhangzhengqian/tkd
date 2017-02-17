package com.lc.zy.common.util;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Passwords {
	
	private static String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String d = "0123456789";
	private static String p = "!@#$%^&*()_+";
	
	
	/**
	 * 生成随机密码.
	 * 
	 * @param len 密码长度.
	 * @return 返回指定向度的随机密码.
	 */
	public static String randomPassword(int len) {
		if (len < 6) {
			throw new RuntimeException("len=" + len + ", 密码长度太短！");
		}
		
		int dl = (len < 8) ? 1 : 2;
		int pl = (len < 8) ? 1 : 2;
		int cl = len - dl - pl;
		
		StringBuilder sb = new StringBuilder();
		sb.append(random(c, cl)).append(random(d, dl)).append(random(p, pl));
		
		return confuse(sb.toString());
	}
	
	/**
	 * 从源字符串生成长度=len的随机的字符串.
	 * @param src
	 * @param len
	 * @return 生成的字符串.
	 */
	public static String random(String src, int len) {
		Random rand = new Random();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < len; i++) {
			result.append(src.charAt(rand.nextInt(src.length())));
		}
		return result.toString();
	}
	
	
	/**
	 * 打乱原始字符串的顺序.
	 * 
	 * @param src 原始字符串.
	 * @return 返回打乱顺序的字符串.
	 */
	public static String confuse(String src) {
		if (StringUtils.isBlank(src))
			return src;
		
		StringBuilder sb = new StringBuilder();
		int len = src.length();
		Random rand = new Random();
		
		boolean[] flag = new boolean[len];
		int idx = 0;
		for (int i = 0; i < len; i++) {
			
			do {
				idx = rand.nextInt(len);
			} while (flag[idx] == true);
			
			sb.append(src.charAt(idx));
			flag[idx] = true;
		}
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		//System.out.printf("%s\n", randomPassword(5));
		long start, end;
		
		start = System.nanoTime();
		String s = randomPassword(6);
		end = System.nanoTime();
		System.out.printf("%s, %d\n", s, end - start);
		
		start = System.nanoTime();
		s = randomPassword(7);
		end = System.nanoTime();
		System.out.printf("%s, %d\n", s, end - start);
		
		start = System.nanoTime();
		s = randomPassword(8);
		end = System.nanoTime();
		System.out.printf("%s, %d\n", s, end - start);
		
		start = System.nanoTime();
		s = randomPassword(16);
		end = System.nanoTime();
		System.out.printf("%s, %d\n", s, end - start);
		
	}

}
