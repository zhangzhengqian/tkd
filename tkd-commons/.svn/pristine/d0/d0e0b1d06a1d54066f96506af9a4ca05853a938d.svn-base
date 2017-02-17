package com.lc.zy.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.lc.zy.common.Constants;
import com.lc.zy.common.Constants.Integral;
import com.lc.zy.common.mq.bean.IntegralBean;

public class CommonOAUtils {
    public static Log log = LogFactory.getLog(CommonOAUtils.class);

    public static final String ORDER_NUMBER_DATE_FORMAT = "yyMMdd";

    private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    private static final String[] qiuyouhao = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public static final String DATE_PATTERN = "yyyyMMdd";

    public static final String TIMES_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 首字母小写 2013-4-24 上午11:38:36
     * 
     * @author 刘涛
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写 2013-4-24 上午11:38:49
     * 
     * @author 刘涛
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 将字符串转换为 T string Integer Date Timestamp等 2013-4-24 上午11:51:32
     * 
     * @author 刘涛
     * @param <T>
     * @param value
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseStringToObject(String value, Class<T> type) {
        if (value == null || value.length() < 1) {
            return null;
        }
        // 如果是字符串直接返回
        if (String.class.getName().equals(type.getName())) {
            return (T) value;
        }
        // 如果是日期类型
        if (Date.class.getName().equals(type.getName())) {
            // 先用yyyy-MM-dd解析
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(value);
                return (T) date;
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            if (null == date) {
                try {
                    format = new SimpleDateFormat("yyyyMMdd");
                    date = format.parse(value);
                    return (T) date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        // 如果是Double类型
        if (Double.class.getName().equals(type.getName())) {
            return (T) new Double(Double.parseDouble(value));
        }
        // 如果是Integer类型
        if (Integer.class.getName().equals(type.getName())) {
            if (value != null && value.length() > 0) {
                return (T) new Integer(Integer.parseInt(value));
            } else {
                return (T) null;
            }
        }
        return null;
    }

    /**
     * 将字符串转为数组 2013-4-24 下午12:34:06
     * 
     * @author 刘涛
     * @param strOrigin 转换字符串
     * @param separator 切分标志 ","等
     * @return
     */
    public static String[] parseStringToArray(String strOrigin, String separator) {
        if (strOrigin == null || strOrigin.length() < 1) {
            return null;
        }
        String[] returnArray = strOrigin.split(separator);
        return returnArray;
    }

    /**
     * 将主键数组转为 sql中的in参数 2013-4-24 下午12:42:51
     * 
     * @author 刘涛
     * @param primaryKeys 主键id的数组
     * @return ('a','b','c','d')
     */
    public static String parseArrayToSql(String[] primaryKeys) {
        if (primaryKeys != null && primaryKeys.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (String id : primaryKeys) {
                sb.append(",'" + id + "'");
            }
            return "(" + sb.substring(1) + ")";
        } else {
            return "('')";
        }
    }

    /**
     * 判断字符串是否为空 2013-4-24 下午12:46:27
     * 
     * @author 刘涛
     * @param value 被判断的字符串
     * @return 不为空返回true 为空返回false
     */
    public static boolean isNotNull(String value) {
        if (value != null && !value.trim().equals("") && !"null".equalsIgnoreCase(value.trim())) {
            // 不为null 不为空 不为"null"
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author 刘涛 2014-4-23上午09:45:32
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return !isNotNull(value);
    }

    /**
     * 将数组转换为字符串 以指定的分隔符分割 2013-4-24 下午01:05:42
     * 
     * @author 刘涛
     * @param strArray 被转换数组
     * @param splitStr 分隔符
     * @return 分割完成字符串
     */
    public static String convertArrayToString(String[] strArray, String splitStr) {
        String returnStr = null;
        if (strArray != null && strArray.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (String t : strArray) {
                sb.append(splitStr).append(t);
            }
            returnStr = sb.substring(splitStr.length());
        }
        return returnStr;
    }

    /**
     * 根据文件名将属性文件解析为map
     * 
     * @author 刘涛 2013-11-14上午09:52:36
     * @param baseName
     * @return
     */
    public static Map<String, String> convertBundleToMap(String baseName) {
        Map<String, String> map = new HashMap<String, String>();
        ResourceBundle rb = ResourceBundle.getBundle(baseName);
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }
        return map;
    }

    /**
     * 获得当前项目的物理路径 ---- E:/workspaces/basic/WebRoot 2013-4-24 下午02:09:44
     * 
     * @author 刘涛
     * @return
     */
    public static String getProjectWebRootPath() {
        String physicalPath = "";
        String className = "CommonUtils.class";
        physicalPath = CommonOAUtils.class.getResource(className).getPath();
        int startPoint = 0;
        if (physicalPath.indexOf(":") != -1) {
            startPoint = 1;
        }
        physicalPath = physicalPath.substring(startPoint, physicalPath.indexOf("WEB-INF") - 1).replaceAll(":", "\\:");
        return physicalPath;

    }

    /**
     * 去掉换行符 2013-4-24 下午02:13:25
     * 
     * @author 刘涛
     * @param origStr
     * @return
     */
    public static String replaceSpecialStringCharForJs(String origStr) {
        String target = null;
        if (isNotNull(origStr)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(origStr);
            target = m.replaceAll("");
        }
        return target;
    }

    /**
     * 判断是否为邮箱 2013-7-27 下午02:38:50
     * 
     * @author 刘涛
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.length() <= 0) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 判断是否为手机号 2013-7-27 下午02:43:34
     * 
     * @author 刘涛
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        if (mobiles == null || mobiles.length() <= 0) {
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

    /**
     * 校验是否为身份证号码 * 身份证验证的工具（支持5位或18位省份证）<br/>
     * 身份证号码结构：
     * <ol>
     * <li>17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序号，1位校验码。</li>
     * <li>地址码（前6位）：表示对象常住户口所在县（市、镇、区）的行政区划代码，按GB/T2260的规定执行。</li>
     * <li>出生日期码，（第七位 至十四位）：表示编码对象出生年、月、日，按GB按GB/T7408的规定执行，年、月、日代码之间不用分隔符。</li>
     * <li>顺序码（第十五位至十七位）：表示在同一地址码所标示的区域范围内，对同年、同月、同日出生的人编订的顺序号，
     * 顺序码的奇数分配给男性，偶数分配给女性。</li>
     * <li>校验码（第十八位数）：<br/>
     * <ul>
     * <li>十七位数字本体码加权求和公式 s = sum(Ai*Wi), i = 0,,16，先对前17位数字的权求和；
     * Ai:表示第i位置上的身份证号码数字值.Wi:表示第i位置上的加权因.Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2；</li>
     * <li>计算模 Y = mod(S, 11)</li>
     * <li>通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2</li>
     * </ul>
     * </li>
     * </ol>
     * 
     * @author 刘涛 2013-11-14上午10:04:05
     * @param s
     * @return
     */
    public static boolean isIdCard(String s) {
        Map<Integer, String> zoneNum = new HashMap<Integer, String>();
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");

        int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

        if (s == null || (s.length() != 15 && s.length() != 18))
            return false;
        final char[] cs = s.toUpperCase().toCharArray();
        // 校验位数
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1 && cs[i] == 'X')
                break;// 最后一位可以 是X或x
            if (cs[i] < '0' || cs[i] > '9')
                return false;
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }

        // 校验区位码
        if (!zoneNum.containsKey(Integer.valueOf(s.substring(0, 2)))) {
            return false;
        }

        // 校验年份
        String year = s.length() == 15 ? "19" + s.substring(6, 8) : s.substring(6, 10);
        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;// 1900年的PASS，超过今年的PASS

        // 校验月份
        String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }

        // 校验天数

        String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31)
            return false;

        // 校验一个合法的年月日
        // 比如考虑闰月，大小月等
        // if(!validate(iyear, imonth, iday))
        // return false;

        // 校验"校验码"
        if (s.length() == 15)
            return true;
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    /**
     * 生成数字随机数
     * 
     * @author 刘涛 2013-11-14上午09:22:46
     * @param length 需要生成随机数的长度
     * @return 生成的随机数
     */
    public static String generatorNumber(int length) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(qiuyouhao[r.nextInt(10)]);
        }
        return sb.toString();
    }

    /**
     * 生成数字字母随机串 可以用来生成token,验证码等
     * 
     * @author 刘涛 2013-11-14上午09:23:49
     * @param length 需要生成的随机字符串的长度
     * @return 生成的随机字符串
     */
    public static String generatorString(int length) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars[r.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成json字符串
     * 
     * @author 刘涛 2013-11-14上午09:27:49
     * @param obj 被打印的对象,一般为map
     * @return
     */
    public static String toJson(Object obj) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Date.class, new DateSerializer());
        gb.registerTypeAdapter(Timestamp.class, new TimeSerializer());
        gb.serializeNulls(); // 处理空值
        Gson g = gb.create();
        return g.toJson(obj);

    }

    @SuppressWarnings("rawtypes")
    public static final List<Class> clsList = new ArrayList<Class>();
    static {
        clsList.add(String.class);
    }

    // /**
    // * @author 刘涛 2013-11-14上午09:40:53
    // * @param obj
    // * 需要输出的对象
    // * @param needList
    // * 仅输出对象的这些属性
    // * @return
    // */
    // public static String toJson(Object obj, final List<String> needList) {
    // GsonBuilder gb = new GsonBuilder();
    // gb.registerTypeAdapter(Date.class, new DateSerializer());
    // gb.registerTypeAdapter(Timestamp.class, new TimeSerializer());
    // gb.setExclusionStrategies(new ExclusionStrategy() {
    // public boolean shouldSkipField(FieldAttributes attr) {
    // // 返回true就表示需要过滤
    // Class cls = attr.getDeclaringClass();
    // if (clsList.contains(cls)) {
    // return !needList.contains(attr.getName());
    // }
    // return false;
    // }
    // public boolean shouldSkipClass(Class<?> cls) {
    // return false;
    // }
    // });
    // Gson g = gb.create();
    // // log.info("toJson:"+g.toJson(obj));
    // return g.toJson(obj);
    // }

    /**
     * 输出错误json 会自动识别resCode如果有就不设置 如果没有就设置为1
     * 
     * @author 刘涛 2013-11-14上午09:43:18
     * @param out
     * @param map
     * @param msg
     */
    public static void errorMsg(PrintWriter out, Map<String, Object> map, String msg) {
        if (!map.containsKey("resCode")) {
            map.put("resCode", "1");
        }
        map.put("resMsg", msg);
        out.print(toJson(map));
    }

    /**
     * 打印错误信息 只有一条错误描述信息时使用
     * 
     * @author 刘涛 2013-11-15下午02:56:18
     * @param out
     * @param msg
     */
    public static void errorMsg(PrintWriter out, Object msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resCode", "1");
        map.put("resMsg", msg);
        out.print(toJson(map));
    }

    /**
     * 输入成功信息 自动添加resCode为0
     * 
     * @author 刘涛 2013-11-14上午09:44:43
     * @param out
     * @param map
     */
    public static void successMsg(PrintWriter out, Map<String, Object> map) {
        map.put("resCode", "0");
        out.print(toJson(map));
    }

    /**
     * 输出成功信息 只有一条描述信息或者一个object对象
     * 
     * @author 刘涛 2013-11-15下午02:53:13
     * @param out
     * @param msg
     */
    public static void successMsg(PrintWriter out, Object msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resCode", "0");
        map.put("resMsg", msg);
        out.print(toJson(map));
    }

    // /**
    // * 输出成功信息 传入需要输出的字段list
    // *
    // * @author 刘涛 2013-11-14上午09:45:06
    // * @param out
    // * @param map
    // * @param needList
    // */
    // public static void successMsg(PrintWriter out, Map<String, Object> map,
    // final List<String> needList) {
    // map.put("resCode", "0");
    // out.print(toJson(map, needList));
    // }

    /**
     * 处理date类型的json
     * 
     * @author yangkai
     */
    public static class DateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
        // java-->json
        public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(new SimpleDateFormat(DATE_PATTERN).format(date));
        }

        // json-->java
        public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return new SimpleDateFormat(DATE_PATTERN).parse(json.getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    /**
     * 处理时间类型的json
     * 
     * @author yangkai
     * 
     */
    public static class TimeSerializer implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
        // java-->json
        public JsonElement serialize(Timestamp date, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(new SimpleDateFormat(TIMES_PATTERN).format(date));
        }

        // json-->java
        public Timestamp deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return new Timestamp(new SimpleDateFormat(TIMES_PATTERN).parse(json.getAsString()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static String defaultProperties = "systemparameters";

    /**
     * 
     * 2013-6-25 上午10:58:17
     * 
     * @author 刘涛
     * @param key
     * @return
     */
    public static String getString(String key) {
        String value = null;
        try {
            value = getString(defaultProperties, key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return value;
    }

    /**
     * 从资源包获取给定键的字符串。
     * 
     * @param baseName --资源文件的文件名(不包含扩展名) 资源文件位于项目的classpath下
     * @param key --资源文件中的指定键值
     * @throws Exception
     */
    public static String getString(String baseName, String key) {
        String value = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle(baseName);
            try {
                value = rb.getString(key);
            } catch (Exception e2) {
                log.error("获取值失败");
                e2.printStackTrace();
            }
        } catch (NullPointerException e) {
            log.error("资源文件名称为空");
            log.error(e.getMessage(), e);
        } catch (MissingResourceException e1) {
            log.error("找不到资源文件");
            // e1.printStackTrace();
            log.error(e1.getMessage());
        }
        return value;
    }

    /**
     * 获取整数的值
     * 
     * @param key
     * @param baseName
     * @return
     * @throws Exception
     */
    public static Integer getInteger(String key) {
        Integer rs = null;
        try {
            rs = getInteger(key, defaultProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取字符数值
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static char getChar(String key) {
        Character rs = null;
        try {
            rs = getChar(key, defaultProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;

    }

    /**
     * 获取长整形的数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Long getLong(String key) {
        Long rs = null;
        try {
            rs = getLong(key, defaultProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取浮点型数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Float getFloat(String key) {
        Float rs = null;
        try {
            rs = getFloat(key, defaultProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取Double数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Double getDouble(String key) {
        Double rs = null;
        try {
            rs = getDouble(key, defaultProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取整数的值
     * 
     * @param key
     * @param baseName
     * @return
     * @throws Exception
     */
    public static Integer getInteger(String key, String baseName) {
        Integer rs = null;
        try {
            String value = getString(baseName, key);
            rs = Integer.valueOf(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取字符数值
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static char getChar(String key, String baseName) {
        Character rs = null;
        try {
            String value = getString(baseName, key);
            if (value != null || !"".equals(value)) {
                if (value.toCharArray().length != 1) {
                    throw new Exception();
                } else {
                    rs = Character.valueOf(value.charAt(0));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;

    }

    /**
     * 获取长整形的数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Long getLong(String key, String baseName) {
        Long rs = null;
        try {
            String value = getString(baseName, key);
            rs = Long.valueOf(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取浮点型数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Float getFloat(String key, String baseName) {
        Float rs = null;
        try {
            String value = getString(baseName, key);
            rs = Float.valueOf(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 获取Double数据
     * 
     * @param key
     * @param baseName
     * @return
     */
    public static Double getDouble(String key, String baseName) {
        Double rs = null;
        try {
            String value = getString(baseName, key);
            rs = Double.valueOf(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * @author 刘涛 2013-11-14上午10:07:04
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static final String HTML = "html";

    public static final String JSON = "json";

    public static final String XML = "xml";

    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response,
            String responseType) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        if (responseType.equals(HTML)) {
            response.setContentType("text/html;charset=utf-8");// 设置响应类型
        } else if (responseType.equals(JSON)) {
            response.setContentType("application/json;charset=utf-8");// 设置响应类型
        } else if (responseType.equals(XML)) {
            response.setContentType("text/xml;charset=utf-8");// 设置响应类型
        }
        // 设置浏览器不缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
    }

    /**
     * 获取属性名集合list
     * 
     * @author 刘涛 2013-11-14上午10:12:50
     * @param <T>
     * @param cls
     * @return
     */
    public static <T> List<String> generFieldNameList(Class<T> cls) {
        Field[] fields = cls.getDeclaredFields();
        List<String> fieldNameList = new ArrayList<String>();
        for (Field field : fields) {
            fieldNameList.add(field.getName());
        }
        return fieldNameList;
    }

    /**
     * 获取方法map key为方法名 value为method对象
     * 
     * @author 刘涛 2013-11-14上午10:13:22
     * @param <T>
     * @param cls
     * @return
     */
    public static <T> Map<String, Method> generMethodsMap(Class<T> cls) {
        Method[] methods = cls.getMethods();
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for (Method m : methods) {
            methodMap.put(m.getName(), m);
        }
        return methodMap;
    }

    /**
     * 获取属性map key为属性名 value为属性对象
     * 
     * @author 刘涛 2013-11-14上午10:14:29
     * @param <T>
     * @param cls
     * @return
     */
    public static <T> Map<String, Field> generFieldsMap(Class<T> cls) {
        Field[] fields = cls.getDeclaredFields();
        // 获取实体bean的属性
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * 获取md5加密字符串
     * 
     * @author 刘涛 2013-11-14上午10:20:16
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * @author 刘涛 2013-11-4上午10:13:05
     * @param requestParam 请求参数
     * @param address 请求地址
     * @param encoding 获取返回数据的编码方式
     * @return 返回请求的结果
     */
    public static String postURL(String requestParam, String address, String encoding) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            /* 得到url地址的URL类 */
            url = new URL(address);
            /* 获得打开需要发送的url连接 */
            urlConn = (HttpURLConnection) url.openConnection();
            /* 设置连接超时时间 */
            urlConn.setConnectTimeout(30000);
            /* 设置读取响应超时时间 */
            urlConn.setReadTimeout(30000);
            /* 设置post发送方式 */
            urlConn.setRequestMethod("POST");
            /* 发送requestParam */
            urlConn.setDoOutput(true);
            OutputStream out = urlConn.getOutputStream();
            out.write(requestParam.getBytes());
            out.flush();
            out.close();
            /* 发送完毕 获取返回流，解析流数据 */
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), encoding));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            /* 解析完毕关闭输入流 */
            rd.close();
        } catch (Exception e) {
            /* 异常处理 */
            rec_string = "-107";
            System.out.println(e);
        } finally {
            /* 关闭URL连接 */
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        /* 返回响应内容 */
        return rec_string;
    }

    // 增删改查处理
    public static String handle(String address, String requestParam) {
        String info = null;
        address = address.replaceAll("\\{", "%7B").replaceAll("\\}", "%7D").replace("\"", "%22")
                .replaceAll("\\[", "%5B").replaceAll("\\]", "%5D").replaceAll("\\\\", "%5");
        String encoding = "UTF-8";
        info = CommonOAUtils.postURL(requestParam, address, encoding);
        return info;
    }

    public static ApplicationContext getApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[] { "/applicationContext-resources.xml",
                "/applicationContext-dao.xml", "/applicationContext-service.xml" });
    }

    public static <T> T getBean(Class<T> cls) {
        return getApplicationContext().getBean(cls);
    }

    // /**
    // * 将两个对象作比较后复制属性值
    // *
    // * @param targetEntity
    // * 目标对象：为更新对象
    // * @param originalEntity
    // * 原对象
    // * @return
    // * @throws Exception
    // */
    // public static <T extends BaseObject> T updateEntity(T targetEntity,
    // T originalEntity) throws Exception {
    // Class cls = targetEntity.getClass();
    // Field[] fields = cls.getDeclaredFields();
    // for (Field t : fields) {
    // if("serialVersionUID".equals(t.getName())){
    // continue;
    // }
    // Method getMethod = cls.getDeclaredMethod("get"
    // + CommonUtils.toUpperCaseFirstOne(t.getName()), null);
    // Object targetValue = getMethod.invoke(targetEntity, null);
    // Object originalValue = getMethod.invoke(originalEntity, null);
    // if (targetValue != null && !targetValue.equals(originalValue)) {
    // // 如果目标对象的值不为空 且不同于原来目标的值,说明值有更新
    // String setMethodName = "set"
    // + CommonUtils.toUpperCaseFirstOne(t.getName());
    // Method setMethod = cls.getDeclaredMethod(setMethodName,t.getType());
    // // 将目标值注入到原来对象中
    // setMethod.invoke(originalEntity, targetValue);
    // }
    // }
    // return originalEntity;
    // }

    // 短信发送
    public static boolean sendMessage(String phone, String code) {

        String username = "zhongx1";
        String password = "zhongx1";
        String[] phones = { phone };// 发送的手机号码

        /* 将内容用URLEncoder编两次GBK */
        String EncoderContent = "";
        try {
            EncoderContent = URLEncoder.encode(code, "GBK");
            EncoderContent = URLEncoder.encode(EncoderContent, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /* 将手机号从数组转变成一个用逗号分开的字符串，字符串末尾有逗号不影响消息下发。 */
        String DesMobile = "";
        for (int i = 0; i < phones.length; i++) {
            if (!"".equals(DesMobile)) {
                DesMobile = phones[i] + "," + DesMobile;
            } else {
                DesMobile = phones[i];
            }
        }
        /* url地址 */
        String address = "http://221.179.180.158:9004/HttpQuickProcess/submitMessageAll";
        String sendTime = "";
        String appendID = "";
        String requestParam = "OperID=zhongx1&OperPass=zhongx1&SendTime=&ValidTime="
                + "&AppendID=&DesMobile=18210600278&Content=nanx2%25B2%25E2%25CA%25D41&ContentType=8";
        requestParam = "OperID=" + username + "&OperPass=" + password + "&SendTime=" + sendTime + "&AppendID="
                + appendID + "&DesMobile=" + DesMobile.trim() + "&ValidTime=&ContentType=8&Content=" + EncoderContent;
        // System.out.println("发送的内容为："+str);
        /* 使用post方式发送消息 */
        String result = postURL(requestParam, address, "GBK");
        if (result.startsWith("03")) {
            return true;
        } else {
            return false;
        }
    }

    // 邮件发送

    public static boolean sendEmail(String email, String title, String content) {
        Properties props = new Properties();
        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", CommonOAUtils.getString("email", "email.smtp.host"));
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");
        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);
        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(CommonOAUtils.getString("email", "email.from.address")));
            /**
             * 加载收件人地址 邮件地址以分号分割,分号前为发送地址，分好后为抄送地址，地址之间以逗号分割
             * */
            // 发送
            message.setRecipients(Message.RecipientType.TO, email);
            // String[] emails =
            // CommonUtils.getString("email.jkl.address").split(";");
            // message.setRecipients(Message.RecipientType.TO,emails[0]);
            // if(emails.length > 1){
            // message.setRecipients(Message.RecipientType.CC, emails[1]);
            // }
            // 加载标题
            message.setSubject(title);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            StringBuffer sb = new StringBuffer();
            sb.append("   尊敬的商户，您好：\n");
            sb.append("\n");
            sb.append("      你的初始密码为：" + content);
            sb.append("\n");
            sb.append("      联系电话：67077820 / 67077890  蔡清红  曹珊珊\n");
            sb.append("\n");
            sb.append("传真：    67077769\n");
            sb.append("Email: qingsuan@joiest.com\n");
            contentPart.setText(sb.toString());
            multipart.addBodyPart(contentPart);
            // 添加附件
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(CommonOAUtils.getString("email", "email.smtp.host"),
                    CommonOAUtils.getString("email", "email.from.username"),
                    CommonOAUtils.getString("email", "email.from.password"));
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 补齐不足长度
     * 
     * @param length 长度
     * @param number 数字
     * @return
     */
    public static String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }

    public static void main(String[] args) {
        String address = "http://localhost:8080/uvc/services/api/auditTask/getTask/123/11111/3";
        String requestParam = "11";
        String encoding = "UTF-8";
        String out = postURL(requestParam, address, encoding);
        System.out.print(out);
    }

    /**
     * ASCII转换为字符串
     * 
     * @param ascii
     * @return
     */
    public static String asciiToChar(String ascii) {
        StringBuilder charName = new StringBuilder();
        String[] chars = ascii.split("-");// 差分ascii码
        for (int i = 0; i < chars.length; i++) {
            charName.append((char) Integer.parseInt(chars[i]));
        }
        return charName.toString();
    }

    /**
     * 字符串转换为ASCII码
     * 
     * @param str
     * @return
     */
    public static String charToAscii(String str) {
        StringBuilder ascii = new StringBuilder();
        char[] chars = str.toCharArray(); // 把字符中转换为字符数组
        for (int i = 0; i < chars.length; i++) {
            ascii.append((int) chars[i]).append("-");
        }
        return ascii.toString();
    }

    /**
     * 提供精确的加法运算。
     * 
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static Double add(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
        return b1.add(b2).doubleValue();
    }

    /**
     * map 转换为 xml字符串
     * 
     * @param map
     * @return
     */
    public static String converteToXml(Map<String, String> map) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<xml>");
        for (String key : map.keySet()) {
            strBuilder.append("<").append(key.toString()).append(">");
            strBuilder.append("<![CDATA[");
            String value = map.get(key);
            strBuilder.append(value);
            strBuilder.append("]]>");
            strBuilder.append("</").append(key.toString()).append(">");
        }
        strBuilder.append("</xml>");
        return strBuilder.toString();
    }

    /**
     * <xml字符串 转换为 map>
     * 
     * @param xml
     * @return
     */
    public static Map<String, String> converteToMap(String xml) {
        Map<String, String> map = new TreeMap<String, String>();
        try {
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List<?> node = nodeElement.elements();
            for (Iterator<?> it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                map.put(elm.getName(), elm.getText());
                elm = null;
            }
            node = null;
            nodeElement = null;
            document = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, Object> paramesTrim(Map<String, Object> map) {
        if (map != null) {
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
                if (entry.getValue() instanceof String) {
                    String key = entry.getKey();
                    String v = (String) entry.getValue();
                    map.put(key, v != null ? v.trim() : null);
                }
            }
        }
        return map;
    }

    public static String getRequestBody(ServletRequest request) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                sb.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 
     * <功能描述><把优惠券类英语转换对应的数字>
     *
     * @create：2015年9月2日 下午2:28:28
     * @author： liangsh
     * @param sportType
     * @return
     */
    public static Integer ballTransform(String sportType) {
        int ballType = 0;
        if (sportType.equals("BADMINTON")) {
            ballType = 1;
        } else if (sportType.equals("TENNIS")) {
            ballType = 2;
        } else if (sportType.equals("BASKETBALL")) {
            ballType = 3;
        } else if (sportType.equals("TABLE_TENNIS")) {
            ballType = 4;
        } else if (sportType.equals("GOLF")) {
            ballType = 5;
        } else if (sportType.equals("FOOTBALL")) {
            ballType = 6;
        } else if (sportType.equals("BILLIARDS")) {
            ballType = 7;
        } else if (sportType.equals("BOWLING")) {
            ballType = 8;
        } else if (sportType.equals("COACH")) { // 预约教练
            ballType = 9;
        } else if (sportType.equals("ACTIVITY")) { // 活动报名
            ballType = 10;
        } else if (sportType.equals("APPOINTMENT")) {// 赛事报名
            ballType = 11;
        } else {
            ballType = 0;
        }
        return ballType;
    }

    /**
     * 
     * <功能描述><把数字转换对应的球类英语>
     *
     * @create：2015年9月2日 下午2:28:46
     * @author： liangsh
     * @return
     */
    public static String couponTypeTransform(Integer ballType) {
        String ballCode = "";
        switch (ballType) {
        case 1:
            ballCode = "BADMINTON";
            break;
        case 2:
            ballCode = "TENNIS";
            break;
        case 3:
            ballCode = "BASKETBALL";
            break;
        case 4:
            ballCode = "TABLE_TENNIS";
            break;
        case 5:
            ballCode = "GOLF";
            break;
        case 6:
            ballCode = "FOOTBALL";
            break;
        case 7:
            ballCode = "BILLIARDS";
            break;
        case 8:
            ballCode = "BOWLING";
            break;
        default:
            break;
        }
        return ballCode;
    }

    public static String sportTypeTransform(Integer ballType) {
        String ballCode = "通用";
        switch (ballType) {
        case 1:
            ballCode = "羽毛球";
            break;
        case 2:
            ballCode = "网球";
            break;
        case 3:
            ballCode = "篮球";
            break;
        case 4:
            ballCode = "乒乓球";
            break;
        case 5:
            ballCode = "高尔夫";
            break;
        case 6:
            ballCode = "足球";
            break;
        case 7:
            ballCode = "台球";
            break;
        case 8:
            ballCode = "保龄球";
            break;
        case 9:
            ballCode = "高尔夫下场";
            break;
        default:
            break;
        }
        return ballCode;
    }

    public static String sportsEToC(String sportType) {
        if ("BASKETBALL".equals(sportType)) {
            sportType = "篮球";
        } else if ("FOOTBALL".equals(sportType)) {
            sportType = "足球";
        } else if ("BADMINTON".equals(sportType)) {
            sportType = "羽毛球";
        } else if ("TENNIS".equals(sportType)) {
            sportType = "网球";
        } else if ("TABLE_TENNIS".equals(sportType)) {
            sportType = "乒乓球";
        } else if ("BILLIARDS".equals(sportType)) {
            sportType = "台球";
        } else if ("BOWLING".equals(sportType)) {
            sportType = "保龄球";
        } else if ("GOLF".equals(sportType)) {
            sportType = "高尔夫";
        }
        return sportType;
    }

    public static String sportsCToE(String sportType) {
        if ("篮球".equals(sportType)) {
            sportType = "BASKETBALL";
        } else if ("足球".equals(sportType)) {
            sportType = "FOOTBALL";
        } else if ("羽毛球".equals(sportType)) {
            sportType = "BADMINTON";
        } else if ("网球".equals(sportType)) {
            sportType = "TENNIS";
        } else if ("乒乓球".equals(sportType)) {
            sportType = "TABLE_TENNIS";
        } else if ("台球".equals(sportType)) {
            sportType = "BILLIARDS";
        } else if ("保龄球".equals(sportType)) {
            sportType = "BOWLING";
        } else if ("高尔夫".equals(sportType)) {
            sportType = "GOLF";
        }
        return sportType;
    }
    
    public static String transStatusToChineseForApp(String status) {
        if (Constants.OrderStatus.CANCELED.equals(status)) {
        	status = "已取消";
        } else if (Constants.OrderStatus.NEW.equals(status)) {
        	status = "待支付";
        } else if (Constants.OrderStatus.PAIED.equals(status)) {
        	status = "已支付";
        } else if (Constants.OrderStatus.BILLED.equals(status)) {
        	status = "已完成";
        } else if (Constants.OrderStatus.REFUNDED.equals(status)) {
        	status = "已退款";
        } else if (Constants.OrderStatus.REFUNDING.equals(status)) {
        	status = "退款中";
        } else if (Constants.OrderStatus.VERIFY.equals(status)) {
        	status = "已确认";
        } 
        return status;
    }
    
    public static String transStatusToChineseFor(String status) {
        if (Constants.OrderStatus.CANCELED.equals(status)) {
        	status = "已取消";
        } else if (Constants.OrderStatus.NEW.equals(status)) {
        	status = "待支付";
        } else if (Constants.OrderStatus.PAIED.equals(status)) {
        	status = "已支付";
        } else if (Constants.OrderStatus.BILLED.equals(status)) {
        	status = "已完成";
        } else if (Constants.OrderStatus.REFUNDED.equals(status)) {
        	status = "已退款";
        } else if (Constants.OrderStatus.REFUNDING.equals(status)) {
        	status = "退款中";
        } else if (Constants.OrderStatus.VERIFY.equals(status)) {
        	status = "已确认";
        } 
        return status;
    }

    /**
     * 
     * <把积分对象转换成字符串><功能具体实现>
     *
     * @create：2015年11月24日 下午4:42:55
     * @author： CYY
     * @param userId
     * @param integral
     * @return
     */
    public static String genIntegralToString(String userId,Integral integral){
        IntegralBean bean = new IntegralBean();
        bean.setType(integral.type);
        bean.setUserId(userId);
        return MyGson.getInstance().toJson(bean);
    }
}
