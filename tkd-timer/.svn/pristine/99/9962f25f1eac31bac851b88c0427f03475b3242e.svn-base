package com.lc.zy.ball.timer.quartz;

import com.lc.zy.ball.timer.service.OrderTimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderTimersTask {
	@Autowired
    private OrderTimerService orderTimerService;
	
    private static final AtomicInteger COUNT = new AtomicInteger();
    
    private static final AtomicInteger FINISH_ORDER_COUNT = new AtomicInteger();

    private Logger logger = LoggerFactory.getLogger(OrderTimersTask.class);
    
    // cron表达式格式为：秒 分 时 日 月 周 年，“日”和“周”只能有一个生效，另外一个用?表示不使用，“年”可以不设置。
    // 样例：
    // 0 0 */1 * * ? 每小时执行一次
    // 0 */1 * * * ? 每分钟执行一次
    // 0/1 * * * * ? 每秒执行一次
    /**
     *
     * <遍历过时订单><功能具体实现>
     *
     * @create：16/9/6 11:32
     * @author：sl
     * @param
     * @return void
     */
    @Scheduled(cron="0 */2 * * * ?")
    public void work() {
    	try {
			orderTimerService.updateOrderStatus();
			logger.debug("=====>> 第 {} 次遍历过时订单...结束", COUNT.getAndIncrement() + 1);
		} catch (Exception e) {

		}

    }

    /**
     *
     * <开场前2小时发送短信提醒><功能具体实现>
     *
     * @create：16/9/6 11:32
     * @author：sl
     * @param
     * @return void
     */
    @Scheduled(cron="0 0 */1 * * ?")
    public void tipOrderWork() {
        try {
            orderTimerService.tipOrder();
            logger.debug("=====>> 第 {} 次开场前2小时发送短信提醒...结束", COUNT.getAndIncrement() + 1);
        } catch (Exception e) {

        }

    }

    /**
     *
     * <遍历消费完成订单><功能具体实现>
     *
     * @create：16/9/6 11:36
     * @author：sl
     * @param
     * @return void
     */
    @Scheduled(cron="0 */1 * * * ?")
    public void orderFinishwork() {
        try {
            orderTimerService.updateOrderFinishStatus();
            logger.debug("=====>> 第 {} 次遍历消费完成订单...结束", COUNT.getAndIncrement() + 1);
        } catch (Exception e) {

        }

    }
    
}
