package com.lc.zy.message.consumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.zy.common.mq.ConsumerHandler;
import com.lc.zy.common.mq.QueueConsumer;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.message.consumer.solr.StatiumSolr;

/**
 * 同步场馆信息到solr
 * 
 * @author sl
 */
@Component
public class SyncStatiumConsumer implements QueueNames {

    private static final Logger logger = LoggerFactory.getLogger(SyncStatiumConsumer.class);

    private final String queueName = SYNC_STATIUM_QUEUE;

    // 当发送命令为all的时候更新全部的solr数据
    public final String ALL = "all";

    @Resource(name = "brokerUrl")
    private String brokerUrl = null;

    @Autowired
    private StatiumSolr statiumSolr;

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
                    // TODO 这就是要同步的那个场馆信息
                    if (ALL.equalsIgnoreCase(key)) {
                        // XXX modify 这里之前传了个 null ，后面会报错，为啥呢
                        statiumSolr.startium(key);
                        statiumSolr.optimize();
                    } else {
                        statiumSolr.startium(key);
                    }
                } catch (Exception e) {
                    logger.error("receive message from mq , this body is [" + body + "]" + ",send result is error!", e);
                }
            }
        });
    }
}
