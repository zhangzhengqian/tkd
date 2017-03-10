package com.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDAte1 {
	 
	    public static void main(String[] args) {
	        String str = "Thu Dec 01 00:00:00 CST 2016";
	        Date date = parse(str, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
	        String datStr = formatDate(date, "yyyy-MM-dd");
	        System.out.println(datStr);
	        System.out.printf("%tF %<tT%n", date);
	    }
	    
	    public static String formatDate(Date date, String format) {
	        return formatDate(date, format, null);
	    }
	    
	    public static String formatDate(Date date, String format, String defVal) {
	        String ret;
	        try {
	            ret = new SimpleDateFormat(format).format(date);
	        } catch (Exception e) {
	            ret = defVal;
	        }
	        return ret;
	    }
	    
	    public static Date parse(String str, String pattern, Locale locale) {
	        if(str == null || pattern == null) {
	            return null;
	        }
	        try {
	            return new SimpleDateFormat(pattern, locale).parse(str);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    public static String format(Date date, String pattern, Locale locale) {
	        if(date == null || pattern == null) {
	            return null;
	        }
	        return new SimpleDateFormat(pattern, locale).format(date);
	    }
	}
