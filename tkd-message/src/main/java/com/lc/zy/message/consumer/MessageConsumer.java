package com.lc.zy.message.consumer;

import com.google.gson.Gson;
import com.lc.zy.common.mq.ConsumerHandler;
import com.lc.zy.common.mq.QueueConsumer;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.bean.SmsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class MessageConsumer implements QueueNames {
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	private final String queueName = MESSAGE_QUEUE;
	@Resource(name = "brokerUrl")
	private String brokerUrl = null;

	@Resource(name = "configs")
	private Map<String, String> configs;

	final Gson gson = new Gson();

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
				logger.debug("receive message from mq , this body is [" + body + "]");
				try {
					SmsBean t = null;
					if (StringUtils.isNotBlank(body)) {
						t = gson.fromJson(body, SmsBean.class);
						
						String to = t.getPhone();
						if (to != null) {

							this.sendMessage(t.getMessage(), t.getPhone());
						}
					}
				} catch (Exception e) {
					logger.error("receive message from mq , this body is [" + body + "]" + ",send result is error!", e);
				}
			}

			private void sendMessage(String message, String to) {
				try {
					// 创建StringBuffer对象用来操作字符串
					StringBuffer sb = new StringBuffer(configs.get("sms.server"));

					// 向StringBuffer追加手机号码
					sb.append("&mobile=" + to);

					// 向StringBuffer追加消息内容转URL标准码
					sb.append("&content=" + URLEncoder.encode(message));

					// 创建url对象
					URL url = new URL(sb.toString());

					// 打开url连接
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();

					// 设置url请求方式 ‘get’ 或者 ‘post’
					connection.setRequestMethod("POST");

					// 发送
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

					// 返回发送结果
					String inputline = in.readLine();

					logger.debug("发送短信状态 {}", inputline);

					// 返回结果为‘100’ 发送成功
					// if (inputline.equals("100")) {
					// return true;
					// } else {
					// return false;
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			// 创建StringBuffer对象用来操作字符串
			StringBuffer sb = new StringBuffer(
					"http://http.yunsms.cn/tx/?uid=21822&pwd=fc4b5e01dc4a50dd6aa1d818b8d118f0&encode=utf8");

			// 向StringBuffer追加手机号码
			sb.append("&mobile=" + "13521832729");

			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&content=" + URLEncoder.encode("张正乾已预订成功！08月8日（周一）(09:00-10:00)北京乾正隆跆拳道馆，精英班课程，订单号1000022222。如需退款，请提前24小时申请，申请电话4000121256，晚于24小时不可申请退款，感谢您对我们的支持~ 【道馆通】"));

			// 创建url对象
			URL url = new URL(sb.toString());

			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			// 返回发送结果
			String inputline = in.readLine();
			System.out.println(inputline);
			// 返回结果为‘100’ 发送成功
			if (inputline.equals("100")) {
				System.out.println("成功");
			} else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
