package com.lc.zy.common.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Arrays;

/**
 * 对称加密解密
 * Created by liangc on 16/1/15.
 */
public class AES {

    private static final String PASSWORD = "@EgT%Rrv#Ns6!Pm&";
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";

    private static final byte PADDING = 0x20;


    /**
     * 加密给定字符串
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        byte[] buff = encrypt(content.getBytes(), PASSWORD);
        return new BASE64Encoder().encode(buff);
    }

    /**
     * 解密给定 BASE64 字符串
     *
     * @param content 用 encrypt 方法加密过的字符串
     * @return
     * @throws IOException
     */
    public static String decrypt(String content) throws IOException {
        byte[] buff = new BASE64Decoder().decodeBuffer(content);
        buff = decrypt(buff, PASSWORD);
        return new String(buff, "UTF-8");
    }


    /**
     * 加密给定字节流
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt(byte[] content, String password) {
        return cipher(padding(content), password, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密给定字节流
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        return cipher(content, password, Cipher.DECRYPT_MODE);
    }

    private static byte[] padding(byte[] content) {
        int len = content.length;
        System.out.println("len=" + len);
        int app_len = 0;
        if (len < 16) {
            app_len = 0x10 - len;
        } else {
            // mod
            app_len = 16 - len & 0xF;
        }
        System.out.println("app_len : " + app_len);
        if (app_len == 0) {
            return content;
        } else {
            byte[] buff = new byte[len + app_len];
            System.arraycopy(content, 0, buff, 0, len);
            Arrays.fill(buff, len, len + app_len, PADDING);
            return buff;
        }
    }

    /**
     * 执行算法
     *
     * @param content
     * @param password
     * @param mode
     * @return
     */
    private static byte[] cipher(byte[] content, String password, int mode) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(mode, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //String content = "本周销售部工作总结   1.本周活动及比赛  北京 3场   深圳  7场   二线城市  13场   2.全国新签球馆  9家     3.西安出差  已初步与合伙人，羽毛球赛事活动组织者马老师口头达成合作协议  并提供我们20家除台球外  其他球类的球馆签约信息  4.企业服务方案  目前已制定服务内容框架  正在进行北京市场价格及利润的制定     下周工作计划  16-22号  1.组织赛事活动  北京8场   二线  20场   2.球馆新签10家   目前100元优惠券只在大连   西安   太原发放   下周是否可以普及全国   3.进一步完善企业服务方案   根据方案初步制定销售话术   4.回访西安合作成果  目标签约5家羽毛球或其他综合性球馆";
        //System.out.println(content.getBytes().length);
        //加密
        //String text = encrypt(content);
        //System.out.println(text);
        //System.out.println(text.length());
        //解密
        String text_src = decrypt("MqOfbbPZZweICRwnS+yyuHET5wCimMiLe3yVjES2sgU=");
        System.out.println(text_src);
    }
}
