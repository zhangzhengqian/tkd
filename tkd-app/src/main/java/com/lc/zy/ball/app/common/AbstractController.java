package com.lc.zy.ball.app.common;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.mq.BizLog;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.SpringUtils;

/**
 * Controller 扩展此类
 *
 * @author liangc
 */
public abstract class AbstractController {

    protected CommonService commonService = SpringUtils.getBean(CommonService.class);

    protected BizLog bizLog = SpringUtils.getBean(BizLog.class);

    protected List<String> localIps = null;

    public boolean checkToken(Class<?> clazz, Method method, String token) {
        Auth authService = clazz.getAnnotation(Auth.class);
        Auth authMethod = method.getAnnotation(Auth.class);
        if (authMethod != null) {
            if (authMethod.value()) {
                return commonService.checkToken(token);
            } else {
                return true;
            }
        }
        if (authService != null && authService.value()) {
            return commonService.checkToken(token);
        }
        return true;
    }

    public void writeLog(String level, String sn, String service, String method, String message) {
        Map<String, Object> ualog = new HashMap<String, Object>();
        ualog.put("service", service);
        ualog.put("method", method);
        ualog.put("sn", sn);
        ualog.put("level", level);
        ualog.put("message", message);
        String log = MyGson.getInstance().toJson(ualog);
        bizLog.write(log);
    }



    public List<String> getLocalIps() throws SocketException {
        if(localIps!=null){
            return localIps;
        }
        localIps = new ArrayList<String>();
        Enumeration<?> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            System.out.println(ni.getName());
            Enumeration<?> ips = ni.getInetAddresses();
            while(ips.hasMoreElements()){
                InetAddress ip = (InetAddress) ips.nextElement();
                System.out.println("本机的ip= " + ip.getHostAddress());
                localIps.add(ip.getHostAddress());
            }
        }
        return getLocalIps();
    }

    public static boolean checkUa(String ua) {
        //"Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; 125LA; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)";
//        ua = ua.toLowerCase();
//        if (ua.indexOf("windows nt") > 0 || ua.indexOf("msie") > 0 || ua.indexOf(".net") > 0 ){
//            return false;
//        }
        return true;
    }

}
