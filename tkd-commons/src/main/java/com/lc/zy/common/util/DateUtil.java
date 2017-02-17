package com.lc.zy.common.util;

import org.jasypt.commons.CommonUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    private static final String ISODATE_FORMAT = "yyyy-MM-dd";

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATETIME_MIL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String nowDateString() {
        return DateTime.now().toString(ISODATE_FORMAT);
    }

    public static String nowDateTimeString() {
        return DateTime.now().toString(DATETIME_FORMAT);
    }

    public static String nowDateTimeMilString() {
        return DateTime.now().toString(DATETIME_MIL_FORMAT);
    }

    public static String nowToString(String format_pattern) {
        return DateTime.now().toString(format_pattern);
    }

    /**
     * 字符串转换成日期 "yyyy-MM-dd"
     * 
     * @作者：wang.haibin
     * @日期：2015-03-27
     * @param str
     * @param defaultValue
     * @return
     */
    public static Date parse(String str, Date defaultValue) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
    
    /**
     * 字符串转换成日期
     * 
     * @作者：wang.haibin
     * @日期：2015-03-27
     * @param str
     * @param pattern
     * @param defaultValue
     * @return
     */
    public static Date parse(String str, String pattern, Date defaultValue) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    /***
     * 比较两个日期的大小
     * 
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return -1 ： 开始日期小于结束日期 0 ： 开始日期等于结束日期 1 ： 开始日期大于结束日期
     */
    public static int compareDate(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        if (startTime < endTime) {
            return -1;
        }
        if (startTime == endTime) {
            return 0;
        }
        return 1;
    }
    
    /***
     * 格式化日期的方法
     *
     * @param date
     *            带格式化的日期
     * @param pattern
     *            格式化的表达式 比如 yyyy-MM-dd  
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String pattern) {
        // 建立日期FORMAT的实例
    	if(CommonUtils.isEmpty(pattern)){
    		pattern = "yyyy-MM-dd";
    	}
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
    
    /**
     * 
     * <字符串格式化转日期"yyyy-MM-dd HH:mm:ss"><功能具体实现>
     *
     * @create：2015年12月4日 下午3:41:29
     * @author： sl
     * @param strDate
     * @param defaultValue
     * @return
     */
    public static Date parseStr(String strDate, Date defaultValue){  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
    	try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return defaultValue;
		}
    }  


    public static void main(String[] args) {
        System.out.println("foo bar"+DateUtil.nowDateTimeString());
        
        System.out.println(DateUtil.formatDate(
                        DateUtil.parse("2015-12-06 15:11", null), "yyyy年MM月dd日 HH:mm"));
        
        
        System.out.println(DateUtil.parse("2015-12-06 15:11", null));
        
        System.out.println(DateUtil.parseStr("2015-12-05 15:58", null));

    }
}
