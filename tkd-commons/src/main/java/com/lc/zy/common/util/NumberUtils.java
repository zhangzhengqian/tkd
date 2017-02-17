package com.lc.zy.common.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * 进制转换算法
 * @author liangc
 */
public class NumberUtils {
	
	//进制基数
	private static String N = "36";
	protected static Map<String,String> nsMap = new LinkedHashMap<String,String>();
	protected static Map<String,String> _nsMap = new LinkedHashMap<String,String>();
	static {
		for(int i=10;i<36;i++){
			_nsMap.put((char)(i+55)+"", i+"");
			nsMap.put(i+"", (char)(i+55)+"");
		}
		for(int i=36;i<(36+26);i++){
			_nsMap.put((char)(i+61)+"", i+"");
			nsMap.put(i+"", (char)(i+61)+"");
		}
		System.out.println(_nsMap);
		System.out.println(nsMap);
		
	}
	/**
	 * 10进制到N进制运算
	 * @param num
	 * @return
	 */
	public static String num10ToN(String num){
		BigInteger a = new BigInteger(num);
		BigInteger m = new BigInteger(N);
		List<BigInteger> tmp = new ArrayList<BigInteger>();
		//进制算法
		while(a.compareTo(m)>0){
			BigInteger x = a.mod(m);
			a = a.divide(m);
			tmp.add(x);
		}
		tmp.add(a);
		//ENCODE
		StringBuffer sb = new StringBuffer();
		for(int i=tmp.size()-1;i>-1;i--){
			int ir = tmp.get(i).intValue();
			if(ir>=0 && ir<=9){
				sb.append(ir);
			}else{
				String p = nsMap.get(ir+"");
				sb.append(p);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 10进制到39进制逆运算
	 * @param num
	 * @return
	 */
	public static String numNTo10(String id){
		
		char[] arr = id.toCharArray();
		List<String> tl = new ArrayList<String>();
		//DECODE
		for(char c : arr){
			String cs = String.valueOf(c);
			int a = c;
			if(a>=48&&a<=57){
				tl.add(cs);
			}else{
				cs = _nsMap.get(cs);
				tl.add(cs);
			}
		}
		
		//进制逆算法
		int i=tl.size();
		BigInteger y = new BigInteger("0");
		BigInteger m = new BigInteger(N);
		for(String n : tl){
			i--;
			BigInteger x = new BigInteger(n);
			BigInteger mi = m.pow(i);
			y = y.add(x.multiply(mi));
		}
		
		return y.toString();
	}
	/**
	 * 获取验证码，4位
	 * @return
	 */
	public static String getIdentifyingCode(){
		int x = new Random().nextInt();
		x = x<0?x*-1:x;
		String res = (x+"").substring(0, 6);
		String num39 = num10ToN(res);
		return num39;
	}
	
	public static String getIdentifyingCodeNum(){
		int x = new Random().nextInt();
		x = x<0?x*-1:x;
		String res = (x+"").substring(0, 4);
		return res;
	}
	
	public static int random(int f,int t){
		double d = Math.random();
		int s = t-f;
		s = (int)(s*d)+f;
		return s;
	}
	
	public static void main(String[] args) throws Exception {
		int x = new Random().nextInt();
		x = x<0?x*-1:x;
		String res = (x+"").substring(0, 7);
		System.out.println(x);
		System.out.println(res);
		String num39 = num10ToN(res);
		System.out.println("num39:"+num39);
		String num10 = numNTo10(num39);
		System.out.println("num10:"+num10);
		
		System.out.println("=========================");
		
		
		double d = Math.random();
		int f = 2000 , t = 5000;
		int s = t-f;
		s = (int)(s*d)+f;
		System.out.println(d);
		System.out.println(s);
	}
	
}
