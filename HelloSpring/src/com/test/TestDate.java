package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.utils.DateUtils;

public class TestDate {
	/*public static void main(String[] args) {
		
		
		//DateUtils.parse("2016-10-01",null);
		Date start = DateUtils.parse("2016-10-01".substring(0, 10),null);
		String str = DateUtils.formatDate(start, "yyyy-MM-dd");
		System.out.println(str);
		System.out.println(start.getTime());
		Date date = new Date(start.getTime());
		
		String a = "14725728"+"00000";
		long b = Long.valueOf(a);
		Date c = new Date(b);
		
		String dateStr = DateUtils.formatDate(c, "yyyy-MM-dd");
		System.out.println(dateStr);
		
		
		//String startTime = TestDate.timeFormat1(14752512);
		//System.out.println(startTime);
		
	}*/
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
	}
	
	
	public static String timeFormat1(long timeStamp){
		Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp * 1000);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String st = format.format(time.getTime());
		return st;
	}
}
/*
public class CalendarTest {
	
}*/
