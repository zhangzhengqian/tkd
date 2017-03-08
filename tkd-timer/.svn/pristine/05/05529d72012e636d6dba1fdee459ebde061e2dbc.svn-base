package com.lc.zy.ball.timer.quartz;

import com.lc.zy.ball.timer.service.PmwMemberTimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 同步pmwmember
 */
@Component
public class PmwMemberTimersTask {
	@Autowired
	private PmwMemberTimerService pmwMemberTimerService;

	private Logger logger = LoggerFactory.getLogger(PmwMemberTimersTask.class);

	// cron表达式格式为：秒 分 时 日 月 周 年，“日”和“周”只能有一个生效，另外一个用?表示不使用，“年”可以不设置。
	// 样例：
	// 0 0 */1 * * ? 每小时执行一次
	// 0 */1 * * * ? 每分钟执行一次
	// 0/1 * * * * ? 每秒执行一次
	// 0 0 1 * * ? 每天凌晨1点执行一次
//	@Scheduled(cron = "0 0 1 * * ?")
//	public void deleteMessage() {
//		try {
//			pmwMemberTimerService.synPmwMember();
//			logger.debug("=====>>同步pmwmember");
//		} catch (Exception e) {
//
//		}
//	}

}
