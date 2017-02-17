package com.lc.zy.message.consumer;

import com.lc.zy.common.mq.ConsumerHandler;
import com.lc.zy.common.mq.QueueConsumer;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.message.consumer.solr.UserSolr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 
 * <保存channel值到数据库><功能具体实现>
 *
 * @create：2016年10月26日 上午10:36:27
 * @author：zzq
 */
@Component
public class SyncUserConsumer implements QueueNames {

    private static final Logger logger = LoggerFactory.getLogger(SyncUserConsumer.class);

    private final String queueName = SYNC_USER_MOBILE;


    @Resource(name = "brokerUrl")
    private String brokerUrl = null;

    @Autowired
    private UserSolr userSolr;

    @PostConstruct
    public void listener() throws InterruptedException {

        // 消费者对象
        QueueConsumer consumer = new QueueConsumer();
        // 启动消费者，并传入消息处理器，监听 brokerUrl 服务器的 queueName 队列
        consumer.listener(new ConsumerHandler(brokerUrl, queueName) {
            // 接收到指定的文本消息,可以是json、xml等结构化数据
            @Override
            public void receive(String body) {
                // 按照协议发送发送消息
                String key = body;
                logger.debug("receive message from mq , this body is [" + body + "]");
                try {
                    	//保存channel到数据库
                    	userSolr.saveChannel(key);
                } catch (Exception e) {
                    logger.error("receive message from mq , this body is [" + body + "]" + ",send result is error!", e);
                }
            }
        });
    }
}
