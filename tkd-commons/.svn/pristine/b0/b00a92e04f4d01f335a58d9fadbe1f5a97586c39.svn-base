package com.lc.zy.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lc.zy.common.Constants;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.mq.bean.EnvelopeTo;
import com.lc.zy.common.mq.bean.Evnelope;
import com.lc.zy.common.mq.bean.SmsBean;

/**
 * 短信发送
 */
@Component
public class MessageUtil implements QueueNames {
	private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    static String QUEUE_NAME = MESSAGE_QUEUE;

    @Autowired
    private QueueProducer producer;

    public QueueProducer getProducer() {
        return producer;
    }

    public void setProducer(QueueProducer producer) {
        this.producer = producer;
    }

    public MessageUtil() {

    }

    /**
     * 发送短信
     * 
     * @param phone 手机接收者
     * @param message 内容
     */
    public void sendSms(String phone, String message) {
        Gson gson = new Gson();
        List<EnvelopeTo> toList = new ArrayList<EnvelopeTo>();
        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(message) && phone.length() == 11) {
        	SmsBean smsBean = new SmsBean();
        	smsBean.setMessage(message);
        	smsBean.setPhone(phone);
        	logger.debug("短信入队:{}", smsBean.getPhone());
            producer.push(QUEUE_NAME, gson.toJson(smsBean));
        }
    }

    /**
     * 发送邮件
     * 
     * @param to 邮件接收者
     * @param message 邮件正文
     * @param subject 邮件主题
     */
    public void sendMail(String to, String message, String subject) {
        Gson gson = new Gson();
        List<EnvelopeTo> toList = new ArrayList<EnvelopeTo>();
        if (StringUtils.isNotBlank(to) && StringUtils.isNotBlank(message)) {
            Evnelope ee = new Evnelope();
            ee.setMessage(message);
            ee.setCt(String.valueOf(System.currentTimeMillis()));
            ee.setSubject(subject);

            EnvelopeTo et = new EnvelopeTo();
            et.setTo(to);
            et.setType(Constants.MessageType.EMAIL);
            toList.add(et);

            ee.setTo(toList);
            producer.push(QUEUE_NAME, gson.toJson(ee));
        }
    }

    /**
     * 发送站内信
     * 
     * @param fromId 发送者ID
     * @param toId 接受者ID
     * @param message 内容
     */
    public void sendLoc(String fromId, String toId, String subject, String message) {
        Gson gson = new Gson();
        List<EnvelopeTo> toList = new ArrayList<EnvelopeTo>();
        if (StringUtils.isNotBlank(fromId) && StringUtils.isNotBlank(toId) && StringUtils.isNotBlank(message)) {
            // 点对点消息生产者
            Evnelope ee = new Evnelope();
            ee.setSubject(subject);
            ee.setMessage(message);
            ee.setFrom(fromId);
            ee.setCt(String.valueOf(System.currentTimeMillis()));

            EnvelopeTo et = new EnvelopeTo();
            et.setTo(toId);
            et.setType(Constants.MessageType.LOC);
            toList.add(et);

            ee.setTo(toList);
            producer.push(QUEUE_NAME, gson.toJson(ee));
        }
    }

    /**
     * 群发站内信 邮件 短信
     * 
     * @param mailTo 邮件接收者
     * @param phoneTo 电话接收者
     * @param locTo 站内信接收者
     * @param locFrom 站内信发送者
     * @param message 消息
     * @param subject 邮件主题
     * 
     */
    public void sendAll(List<String> mailTo, List<String> phoneTo, List<String> locTo,
            String locFrom, String message, String subject) {
        Gson gson = new Gson();
        Evnelope ep = new Evnelope();
        if (StringUtils.isBlank(message)) {
            return;
        }
        ep.setMessage(message);
        ep.setCt(String.valueOf(System.currentTimeMillis()));
        List<EnvelopeTo> toList = new ArrayList<EnvelopeTo>();
        // 邮件
        if (mailTo != null && mailTo.size() != 0) {
            for (String mt : mailTo) {
                if (StringUtils.isNotBlank(mt)) {
                    EnvelopeTo et = new EnvelopeTo();
                    et.setTo(mt);
                    et.setType(Constants.MessageType.EMAIL);
                    toList.add(et);
                }
            }
            ep.setSubject(subject);
        }

        // 短信
        if (phoneTo != null && phoneTo.size() != 0) {
            for (String mt : phoneTo) {
                if (StringUtils.isNotBlank(mt)) {
                    EnvelopeTo et = new EnvelopeTo();
                    et.setTo(mt);
                    et.setType(Constants.MessageType.SMS);
                    toList.add(et);
                }
            }
        }

        // 站内信
        if (locTo != null && locTo.size() != 0 && StringUtils.isNotBlank(locFrom)) {
            for (String mt : locTo) {
                if (StringUtils.isNotBlank(mt)) {
                    EnvelopeTo et = new EnvelopeTo();
                    et.setTo(mt);
                    et.setType(Constants.MessageType.LOC);
                    toList.add(et);
                }
            }
            ep.setFrom(locFrom);
        }

        if (toList != null && toList.size() != 0) {
            ep.setTo(toList);
            producer.push(QUEUE_NAME, gson.toJson(ep));
        }
    }

    /**
     * 
     * <根据类型获取短信内容><功能具体实现>
     *
     * @create：2015年11月25日 下午4:45:46
     * @author： CYY
     * @param type
     * @return
     */
    public static String getSmsContext(Integer type) {
        String message = "";
        switch (type) {
        case 0: // 注册
            message = " （球友圈手机动态验证码，请输入验证码完成注册），如非本人操作请忽略此信息。";
            break;
        case 1:// 换绑手机
        case 5:
            message = " 您正在申请更换绑定手机（如非本人操作，请及时修改密码，以防账号被盗）。";
            break;
        case 2:// 找回密码
            message = " （球友圈手机动态验证码，请输入验证码完成找回密码）。";
            break;
        case 3:// 设置支付密码
            message = " （球友圈手机动态验证码，请输入验证码完成操作），如非本人操作请忽略此信息。";
            break;
        case 4:// 找回支付密码
            message = " 您正在找回支付密码（如非本人操作，请及时修改密码，以防账号被盗）。";
            break;
        case 6:// 报名cta赛事短信内容
            message = " 您正在报名cta赛事（如非本人操作，请及时修改密码，以防账号被盗）。";
            break;
        }
        
        return message;
    }
}
