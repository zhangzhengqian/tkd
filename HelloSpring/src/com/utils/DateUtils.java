package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
public abstract class DateUtils {
	
	/**
	 * @return 唯一主键ID.
	 */
	/*public static String get() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}*/


	/**
	 * 日期工具类
	 */
	    /** 日期格式 */
	    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	    /** 日期时间格式 */
	    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	    public static final String DEFAULT_DATETIME2_FORMAT = "yyyy-MM-dd HH:mm";

	    /** 时间格式 */
	    public static final String DEFAULT_TIME_FORMAT = "HH:mm";

	    /** 每天小时数 */
	    private static final long HOURS_PER_DAY = 24;

	    /** 每小时分钟数 */
	    private static final long MINUTES_PER_HOUR = 60;

	    /** 每分钟秒数 */
	    private static final long SECONDS_PER_MINUTE = 60;

	    /** 每秒的毫秒数 */
	    private static final long MILLIONSECONDS_PER_SECOND = 1000;

	    /** 每分钟毫秒数 */
	    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;

	    /** 每天毫秒数 */
	    private static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
	            * MILLIONSECONDS_PER_SECOND;

	    public static TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

	    public static final String DATE_EN_FORMAT = "M月d日";

	    public static final String DATE_EN_WEEK = "E";

	    private DateUtils() {
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
	    
	    
	    /**
	     * 向日期后面添加月份
	     * 
	     * @param date
	     * @param addMonth
	     * @return
	     */
	    public static Date addMonth(Date date, int addMonth) {
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.MONTH, addMonth);
	        return calender.getTime();
	    }

	    public static Date addMonth(String date, int addMonth) throws ParseException {
	        try {
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	            Date d = simpleDateFormat.parse(date);
	            return addMonth(d, addMonth);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 将yyyy-MM-dd格式的字符串转换为日期对象
	     * 
	     * @param date 待转换字符串
	     * @return 转换后日期对象
	     * @see #getDate(String, String, Date)
	     */
	    public static Date getDate(String date) {
	        return getDate(date, DEFAULT_DATE_FORMAT, null);
	    }

	    /**
	     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
	     * 
	     * @param date 待转换字符串
	     * @return 转换后日期对象
	     * @see #getDate(String, String, Date)
	     */
	    public static Date getDateTime(String date) {
	        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
	    }

	    /**
	     * 将指定格式的字符串转换为日期对象
	     * 
	     * @param date 待转换字符串
	     * @param format 日期格式
	     * @return 转换后日期对象
	     * @see #getDate(String, String, Date)
	     */
	    public static Date getDate(String date, String format) {
	        return getDate(date, format, null);
	    }

	    /**
	     * 将指定格式的字符串转换为日期对象
	     * 
	     * @param date 日期对象
	     * @param format 日期格式
	     * @param defVal 转换失败时的默认返回值
	     * @return 转换后的日期对象
	     */
	    public static Date getDate(String date, String format, Date defVal) {
	        Date d;
	        try {
	            d = new SimpleDateFormat(format).parse(date);
	        } catch (ParseException e) {
	            d = defVal;
	        }
	        return d;
	    }

	    /**
	     * 将日期对象格式化成yyyy-MM-dd格式的字符串
	     * 
	     * @param date 待格式化日期对象
	     * @return 格式化后的字符串
	     * @see #formatDate(Date, String, String)
	     */
	    public static String formatDate(Date date) {
	        return formatDate(date, DEFAULT_DATE_FORMAT, null);
	    }

	    /**
	     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
	     * 
	     * @param date 待格式化日期对象
	     * @return 格式化后的字符串
	     * @see #formatDate(Date, String, String)
	     */
	    public static String forDatetime(Date date) {
	        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
	    }

	    /**
	     * 将日期对象格式化成HH:mm:ss格式的字符串
	     * 
	     * @param date 待格式化日期对象
	     * @return 格式化后的字符串
	     * @see #formatDate(Date, String, String)
	     */
	    public static String formatTime(Date date) {
	        return formatDate(date, DEFAULT_TIME_FORMAT, null);
	    }

	    /**
	     * 将日期对象格式化成指定类型的字符串
	     * 
	     * @param date 待格式化日期对象
	     * @param format 格式化格式
	     * @return 格式化后的字符串
	     * @see #formatDate(Date, String, String)
	     */
	    public static String formatDate(Date date, String format) {
	        return formatDate(date, format, null);
	    }

	    /**
	     * 带时区的格式化时间
	     * 
	     * @param date
	     * @param format
	     * @param timeZone
	     * @return
	     */
	    public static String formatDateTimeZone(Date date, String format, TimeZone timeZone) {
	        String ret = null;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(format);
	            sdf.setTimeZone(timeZone);
	            ret = sdf.format(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ret;
	    }

	    /**
	     * 将日期对象格式化成指定类型的字符串
	     * 
	     * @param date 待格式化日期对象
	     * @param format 格式化格式
	     * @param defVal 格式化失败时的默认返回空
	     * @return 格式化后的字符串
	     */
	    public static String formatDate(Date date, String format, String defVal) {
	        String ret;
	        try {
	            ret = new SimpleDateFormat(format).format(date);
	        } catch (Exception e) {
	            ret = defVal;
	        }
	        return ret;
	    }

	    /**
	     * 返回指定日期加上days天后的日期
	     * 
	     * @param date
	     * @param days
	     * @return
	     */
	    public static Date plusDays(Date date, int days) {
	        return changeDays(date, days);
	    }

	    public static Date plusDaysToday(int days) {
	        return plusDays(getToday(), days);
	    }

	    public static Date minusDaysToday(int days) {
	        return minusDays(getToday(), days);
	    }

	    /**
	     * 返回指定日期减去days天后的日期
	     * 
	     * @param date
	     * @param days
	     * @return
	     */
	    public static Date minusDays(Date date, int days) {
	        return changeDays(date, -days);
	    }

	    private static Date changeDays(Date date, int days) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DAY_OF_YEAR, days);
	        return cal.getTime();
	    }

	    /**
	     * 获取当前日期加时间
	     * 
	     * @return
	     */
	    public static Date getToday() {
	        return new Date();
	    }

	    public static Date now() {
	        return getToday();
	    }

	    public static long currentTimeMillis() {
	        return new Date().getTime();
	    }

	    /**
	     * 获得当前时间sql.date
	     */
	    public static java.sql.Date getTodaySqlDate() {
	        return new java.sql.Date(getToday().getTime());
	    }

	    /**
	     * 获取今天日期, 格式: YYYY-MM-DD
	     * 
	     * @return
	     */
	    public static String getTodayStr() {
	        return formatDate(getToday(), DEFAULT_DATE_FORMAT);
	    }

	    /**
	     * 比较传入日期与当前日期相差的天数
	     * 
	     * @param d
	     * @return
	     */
	    public static int intervalDay(Date d) {
	        return intervalDay(getToday(), d);
	    }

	    /**
	     * 比较两个日期相差的天数
	     * 
	     * @param d1
	     * @param d2
	     * @return
	     */
	    public static int intervalDay(Date d1, Date d2) {
	        long intervalMillSecond = setToDayStartTime(d1).getTime() - setToDayStartTime(d2).getTime();
	        // 相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
	        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
	    }

	    /**
	     * 将时间调整到当天0:0:0
	     * 
	     * @param date
	     * @return
	     */
	    private static Date setToDayStartTime(Date date) {
	        Calendar calendar = Calendar.getInstance();

	        calendar.setTimeInMillis(date.getTime());
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);

	        return calendar.getTime();
	    }

	    /**
	     * 判断当前时间
	     * 
	     * @return
	     */
	    public static String getDateStatus() {
	        Calendar cal = Calendar.getInstance();
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        if (hour >= 6 && hour < 12) {
	            return "morning";
	        } else if (hour >= 12 && hour < 18) {
	            return "noon";
	        } else if (hour >= 18 && hour < 24) {
	            return "evning";
	        } else {
	            return "midnight";
	        }
	    }

	    /**
	     * 获得两个日期之间相差的分钟数。（date1 - date2）
	     * 
	     * @param date1
	     * @param date2
	     * @return 返回两个日期之间相差的分钟数值
	     */
	    public static int intervalMinutes(Date date1, Date date2) {
	        long intervalMillSecond = date1.getTime() - date2.getTime();

	        // 相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
	        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1
	                : 0));
	    }

	    /**
	     * 获得两个日期之间相差的秒数差（date1 - date2）
	     * 
	     * @param date1
	     * @param date2
	     * @return
	     */
	    public static int intervalSeconds(Date date1, Date date2) {
	        long intervalMillSecond = date1.getTime() - date2.getTime();

	        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1
	                : 0));
	    }

	    public static int getAge(Date birthday) {
	        Calendar now = Calendar.getInstance();
	        Calendar birth = Calendar.getInstance();
	        birth.setTime(birthday);
	        // 取得生日年份
	        int year = birth.get(Calendar.YEAR);
	        // 年龄
	        int age = now.get(Calendar.YEAR) - year;
	        // 修正
	        now.set(Calendar.YEAR, year);
	        age = (now.before(birth)) ? age - 1 : age;
	        return age;
	    }

	    /**
	     * d1 和 d2 是同一天
	     * 
	     * @param d1
	     * @param d2
	     * @return
	     */
	    public static boolean isSameDate(Date d1, Date d2) {
	        if (d1 == null || d2 == null)
	            return false;
	        Calendar c1 = Calendar.getInstance();
	        c1.setTimeInMillis(d1.getTime());
	        Calendar c2 = Calendar.getInstance();
	        c2.setTimeInMillis(d2.getTime());

	        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
	                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
	    }

	    /**
	     * 判断是否d2是d1的后一天
	     * 
	     * @param d1
	     * @param d2
	     * @return
	     */
	    public static boolean isContinueDay(Date d1, Date d2) {
	        if (d1 == null || d2 == null)
	            return false;
	        if (intervalDay(d1, d2) == 1)
	            return true;
	        return false;
	    }

	    /**
	     * 得到没有时间的日期
	     * 
	     * @param date
	     * @return
	     */
	    public static Date truncDate(Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        return c.getTime();
	    }

	    /**
	     * 
	     * 得到旬.
	     * 
	     * @param input
	     * @return
	     * @author <a href="mailto:wangxin@knet.cn">北京王欣</a>
	     */
	    public static String getCnDecade(Date input) {
	        String day = formatDate(input);
	        String decade = day.replaceAll("01日", "上旬").replaceAll("11日", "中旬").replaceAll("21日", "下旬");
	        return decade;
	    }

	    public static Date getTodayZero() {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(new Date());
	        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        c.set(java.util.Calendar.MINUTE, 0);
	        c.set(java.util.Calendar.SECOND, 0);
	        return c.getTime();
	    }

	    public static Date getTheDayBefore(Date date) {
	        return new Date(date.getTime() - (long) 24 * (long) 60 * (long) 60 * (long) 1000);
	    }

	    public static Date[] getTenDayBefore() {// 计算之前一旬的起止时间
	        Date[] ret = new Date[2];
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(new Date());
	        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        c.set(java.util.Calendar.MINUTE, 0);
	        c.set(java.util.Calendar.SECOND, 0);// 0点0分0秒
	        int day = c.get(java.util.Calendar.DAY_OF_MONTH);
	        if (day < 10) {// 今天处在某月的上旬，起始时间是前一个月的21号，终止时间是本月的1号
	            c.set(java.util.Calendar.DAY_OF_MONTH, 1);// 本月的1号
	            ret[1] = new Date(c.getTime().getTime());
	            c.setTime(getTheDayBefore(c.getTime()));// 往前翻一天，到上一个月
	            c.set(java.util.Calendar.DAY_OF_MONTH, 21);
	            ret[0] = new Date(c.getTime().getTime());
	        } else {//

	            if (10 < day && day <= 20) {// 今天处在某月的中旬，起始时间是本月的1号，终止时间是本月的11号
	                c.set(java.util.Calendar.DAY_OF_MONTH, 1);
	                ret[0] = new Date(c.getTime().getTime());
	                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
	                ret[1] = new Date(c.getTime().getTime());
	            } else {// 今天处在某月的下旬，起始时间是本月的11号，终止时间是本月的21号
	                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
	                ret[0] = new Date(c.getTime().getTime());
	                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
	                ret[1] = new Date(c.getTime().getTime());
	            }
	        }
	        return ret;
	    }

	    public static Date[] getCurrentTenDay(Date input) {// 计算某个输入时间的当前旬起止时间
	        Date[] ret = new Date[2];
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(input);
	        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        c.set(java.util.Calendar.MINUTE, 0);
	        c.set(java.util.Calendar.SECOND, 0);// 0点0分0秒
	        int day = c.get(java.util.Calendar.DAY_OF_MONTH);
	        if (day < 10) {// 今天处在某月的上旬，起始时间是本月的1号，终止时间是本月的11号
	            c.set(java.util.Calendar.DAY_OF_MONTH, 1);// 本月的1号
	            ret[0] = new Date(c.getTime().getTime());
	            c.set(java.util.Calendar.DAY_OF_MONTH, 11);
	            ret[1] = new Date(c.getTime().getTime());
	        } else {//

	            if (10 < day && day <= 20) {// 今天处在某月的中旬，起始时间是本月的11号，终止时间是本月的21号
	                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
	                ret[0] = new Date(c.getTime().getTime());
	                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
	                ret[1] = new Date(c.getTime().getTime());
	            } else {// 今天处在某月的下旬，起始时间是本月的21号，终止时间是下个月的1号
	                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
	                ret[0] = new Date(c.getTime().getTime());
	                ret[1] = getNextMonthFirst(c.getTime());
	            }
	        }
	        return ret;
	    }

	    public static Date getNextMonthFirst(Date date) {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        c.set(java.util.Calendar.MINUTE, 0);
	        c.set(java.util.Calendar.SECOND, 0);// 0点0分0秒
	        c.add(java.util.Calendar.MONTH, 1);// 加一个月
	        c.set(java.util.Calendar.DATE, 1);// 把日期设置为当月第一天
	        return c.getTime();
	    }

	    public static Date[] getTheMonthBefore(Date date) {// 计算之前一旬的起止时间
	        Date[] ret = new Date[2];
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        c.set(java.util.Calendar.MINUTE, 0);
	        c.set(java.util.Calendar.SECOND, 0);// 0点0分0秒
	        c.set(java.util.Calendar.DAY_OF_MONTH, 1);// 本月的1号
	        ret[1] = new Date(c.getTime().getTime());
	        c.setTime(getTheDayBefore(c.getTime()));// 往前翻一天，到上一个月
	        c.set(java.util.Calendar.DAY_OF_MONTH, 1);// 上月的1号
	        ret[0] = new Date(c.getTime().getTime());
	        return ret;
	    }

	    /**
	     * 
	     * <获取中文月份和日期><功能具体实现>
	     *
	     * @create：2015年8月28日 下午2:58:38
	     * @author： CYY
	     * @param date
	     * @return
	     */
	    public static String getChineseDate(Date date) {
	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_EN_FORMAT);
	        return sdf.format(date);
	    }
	    
	    /**
	     * 
	     * <获取中文年、月份和日期><功能具体实现>
	     *
	     * @create：2015年8月28日 下午2:58:38
	     * @author： sl
	     * @param date
	     * @return
	     */
	    public static String getChineseDate(Date date, String format) {
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        return sdf.format(date);
	    }

	    /**
	     * 
	     * <获取中文星期几><功能具体实现>
	     *
	     * @create：2015年8月28日 下午2:58:38
	     * @author： CYY
	     * @param date
	     * @return
	     */
	    public static String getWeek(Date date) {
	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_EN_WEEK, Locale.CHINESE);
	        return sdf.format(date);
	    }

	    /**
	     * 时间格式化 （07月11日 04:38） timestamp = 1405067931;
	     * 
	     * @param timestamp
	     * @return
	     */
	    public static String timeFormat1(long timestamp) {
	        long curTime = System.currentTimeMillis() / 1000; // 秒
	        long space = curTime - timestamp;
	        // 1分钟
	        String string = "";
	        if (space < 60) {
	            string = "刚刚";
	        } else if (space < 3600) // 一小时前
	        {
	            string = (int) Math.floor(space / 60) + "分钟前";
	        } else {
	            Calendar currtime = Calendar.getInstance();
	            currtime.setTimeInMillis(curTime * 1000);
	            Calendar time = Calendar.getInstance();
	            time.setTimeInMillis(timestamp * 1000);
	            if (currtime.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
	                if (currtime.get(Calendar.DAY_OF_MONTH) == time.get(Calendar.DAY_OF_MONTH)) {
	                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	                    string = "今天 " + format.format(time.getTime());
	                } else if (currtime.get(Calendar.DAY_OF_MONTH) - 1 == time.get(Calendar.DAY_OF_MONTH)) {
	                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	                    string = "昨天 " + format.format(time.getTime());
	                } else {
	                    SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
	                    string = format.format(time.getTime());
	                }
	            } else {
	                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	                string = format.format(time.getTime());
	            }
	        }
	        return string;
	    }
	    
	    /**
	     * 
	     * <获取当前日期时间的本周日期时间(周一、周日)><功能具体实现>
	     *
	     * @create：2015年11月2日 上午10:32:02
	     * @author： sl
	     * @return
	     */
	    public static Map<String, String> getWeekEndandStart() {
	    	Map<String, String> dateMape = new HashMap<String, String>();
			Calendar cal =Calendar.getInstance();
	                  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	                  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
	        // 星期一
	        dateMape.put("monday", df.format(cal.getTime()));          
			//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			//增加一个星期，才是我们中国人理解的本周日的日期
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			// 星期日
			dateMape.put("sunday", df.format(cal.getTime()));
			return dateMape;
		}
	    
	    /**
	     * <获取月份第一天和最后一天><功能具体实现>
	     * @param date
	     * @return
	     * @author yankefei
	     * @date 2015年11月19日 下午5:50:25
	     */
	    public static int[] getMonthFirstLastDay(Date date){
	    	int[] result = {0,0};
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	        int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	        result[0] = first;
	        result[1] = last;
	        return result;
	    }
	    
	    /**
	     * 
	     * <得到本月第一天的日期 ><功能具体实现>
	     *
	     * @create：2016年3月3日 下午3:33:30
	     * @author： sl
	     * @param date
	     * @return
	     */
	    public static Date getFirstDayOfMonth(Date date)   {     
	        Calendar cDay = Calendar.getInstance();     
	        cDay.setTime(date);  
	        cDay.set(Calendar.DAY_OF_MONTH, 1);  
	        System.out.println(cDay.getTime());  
	        return cDay.getTime();     
	    }     
	    
	    /**
	     * 
	     * <得到本月最后一天的日期><功能具体实现>
	     *
	     * @create：2016年3月3日 下午3:33:43
	     * @author： sl
	     * @param date
	     * @return
	     */
	    public static Date getLastDayOfMonth(Date date)   {     
	        Calendar cDay = Calendar.getInstance();     
	        cDay.setTime(date);  
	        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        System.out.println(cDay.getTime());  
	        return cDay.getTime();     
	    }  
	    
	    /**
	     * 
	     * <得到本季度第一天的日期><功能具体实现>
	     *
	     * @create：2016年3月3日 下午3:34:02
	     * @author： sl
	     * @param date
	     * @return
	     */
	    public static Date getFirstDayOfQuarter(Date date)   {     
	        Calendar cDay = Calendar.getInstance();     
	        cDay.setTime(date);  
	        int curMonth = cDay.get(Calendar.MONTH);  
	        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){    
	            cDay.set(Calendar.MONTH, Calendar.JANUARY);  
	        }  
	        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){    
	            cDay.set(Calendar.MONTH, Calendar.APRIL);  
	        }  
	        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {    
	            cDay.set(Calendar.MONTH, Calendar.JULY);  
	        }  
	        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {    
	            cDay.set(Calendar.MONTH, Calendar.OCTOBER);  
	        }  
	        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));  
	        System.out.println(cDay.getTime());  
	        return cDay.getTime();     
	    }  
	    
	    /**
	     * 
	     * <得到本季度最后一天的日期><功能具体实现>
	     *
	     * @create：2016年3月3日 下午3:34:15
	     * @author： sl
	     * @param date
	     * @return
	     */
	    public static Date getLastDayOfQuarter(Date date)   {     
	        Calendar cDay = Calendar.getInstance();     
	        cDay.setTime(date);  
	        int curMonth = cDay.get(Calendar.MONTH);  
	        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){    
	            cDay.set(Calendar.MONTH, Calendar.MARCH);  
	        }  
	        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){    
	            cDay.set(Calendar.MONTH, Calendar.JUNE);  
	        }  
	        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {    
	            cDay.set(Calendar.MONTH, Calendar.AUGUST);  
	        }  
	        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {    
	            cDay.set(Calendar.MONTH, Calendar.DECEMBER);  
	        }  
	        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        System.out.println(cDay.getTime());  
	        return cDay.getTime();     
	    }  
	    
	    public static Map<String, Long> getDatePoor(Date endDate, Date nowDate) {
	    	Map<String, Long> dateMap = new HashMap<String, Long>();

	    	long nd = 1000 * 24 * 60 * 60;
	    	long nh = 1000 * 60 * 60;
	    	long nm = 1000 * 60;
	    	// long ns = 1000;
	    	// 获得两个时间的毫秒时间差异
	    	long diff = endDate.getTime() - nowDate.getTime();
	    	// 计算差多少天
	    	long day = diff / nd;
	    	dateMap.put("day", day);
	    	// 计算差多少小时
	    	long hour = diff % nd / nh;
	    	dateMap.put("hour", hour);
	    	// 计算差多少分钟
	    	long min = diff % nd % nh / nm;
	    	dateMap.put("min", min);
	    	// 计算差多少秒//输出结果
	    	// long sec = diff % nd % nh % nm / ns;
	    	return dateMap;
	    }


	    /**
	     *
	     * <获取当前年><功能具体实现>
	     *
	     * @create：2016-07-23 21:16:58
	     * @author：sl
	     * @param
	     * @return java.lang.String
	     */
	    public static String getYear () {
	        Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);
	        return String.valueOf(year);
	    }

	    public static void main(String[] args) {
	        Date d = DateUtils.getDate("2016-01-01", "yyyy-MM-dd");
	        for (int i = 0; i < 40; i++) {
	            Date dd = DateUtils.plusDays(d, i);
	            String w = DateUtils.getWeek(dd);
	            String s = DateUtils.formatDate(dd);
	            System.out.println(s + " : " + w);
	        }
	        // Integer day =
	        // DateUtils.intervalDay(DateUtils.getDateTime("2015-01-01 00:00:00"),DateUtils.getDateTime("2015-01-5 00:00:00"));
	        // System.out.println(day);
	        System.out.println("2015-01-01 00:00:00".compareTo("2015-01-05 00:00:00"));

	        System.out.println(getDateTime("2016-08-09 12:00"));
	        System.out.println(getDate("2016-08-09 12:00", "yyyy-MM-dd HH:mm"));
	    }


}
