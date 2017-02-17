package com.lc.zy.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.TopicPublisher;

@Component
public class SynBadWordsUtil implements QueueNames {

	static String QUEUE_NAME = TOPIC_RELOAD_BAD_WORDS;

	@Autowired
	private TopicPublisher producer;

	public TopicPublisher getProducer() {
		return producer;
	}

	public void setProducer(TopicPublisher producer) {
		this.producer = producer;
	}

	public SynBadWordsUtil() {

	}

	public void synBadWords(String message) {
		producer.push(QUEUE_NAME, message);
	}
}
