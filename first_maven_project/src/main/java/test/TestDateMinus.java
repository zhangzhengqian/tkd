package test;

import java.util.Date;

import utils.DateUtils;

public class TestDateMinus {
	public static void main(String[] args) {
		String str1 = "2016-10-20 13:21:54";
		String str2 = "2016-10-20 13:21:14";
		Date date1 = DateUtils.parse(str1, "yyyy-MM-dd HH:mm:ss",null);
		Date date2 = DateUtils.parse(str2, "yyyy-MM-dd HH:mm:ss",null);
		long minus1 = date1.getTime()-date2.getTime();
		System.out.println(minus1/(1000*60*60*24));
		System.out.println(minus1/(1000*60*60));
		System.out.println(minus1/(1000*60));
		System.out.println(minus1/(1000));
		System.out.println(date1.getTime()/(1000*24*60*60));
		System.out.println(date2.getTime()/(1000*24*60*60));
		System.out.println(minus1/(1000*24*60*60));
		
		/*System.out.println(date1.getTime()/(1000*60*60*24));
		System.out.println(minus1/(1000*60*60*24));
		
		System.out.println(date1.getTime()-date2.getTime());
		System.out.println(minus1/(1000*60*60));*/
	}
}
