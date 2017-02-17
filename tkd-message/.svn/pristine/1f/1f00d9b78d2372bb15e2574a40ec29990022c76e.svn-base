package com.lc.zy.message.consumer.solr;

import com.lc.zy.ball.domain.oa.mapper.StatisticRegisterUserMapper;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUser;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUserCriteria;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;
import com.lc.zy.message.common.AbstractCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserSolr extends AbstractCacheService{
	 private static Logger logger = LoggerFactory.getLogger(UserSolr.class);
	 
	 @Autowired
	 private StatisticRegisterUserMapper statisticRegisterUserMapper;
	 
	 /**
	  * 
	  * <添加用户统计><功能具体实现>
	  *
	  * @create：2016年10月25日 下午6:54:29
	  * @author：zzq
	  * @param channel
	  */
	 public void saveChannel(String channel){
		 	//日期 2016-09-11 8
	        Date date = new Date();
	        String d = DateUtils.formatDate(date, "yyyy-MM-dd HH");
	        StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
	        statisticRegisterUserCriteria.createCriteria().andChannelEqualTo(channel).andCtEqualTo(d);
	        List<StatisticRegisterUser> vos = statisticRegisterUserMapper.selectByExample(statisticRegisterUserCriteria);
	        int counter = 0;
	        //新增渠道 相同渠道 小时不一样 添加记录 
	        if(vos.size()==0){
	        	logger.debug("获取版本号"+channel);
		        logger.debug(d);
		        StatisticRegisterUser statisticRegisterUser = new StatisticRegisterUser();
		        statisticRegisterUser.setCt(d);
		        statisticRegisterUser.setChannel(channel);
	        	counter = 1;
	        	statisticRegisterUser.setCounter(counter);
	        	String id = UUID.get();
	 	        statisticRegisterUser.setId(id);
	        	try {
					this.insertSelective(statisticRegisterUser, statisticRegisterUser.getId());
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("增加记录失败"+e.getMessage());
				}
	        
	        }
	        //渠道已有 同一小时注册用户+1 更新记录
	        else{
		        	StatisticRegisterUser statisticRegisterUser = vos.get(0);
	        		int current = statisticRegisterUser.getCounter();
		        	counter = ++current;
		        	statisticRegisterUser.setCounter(counter);
		        	try {
						this.updateByPrimaryKeySelective(statisticRegisterUser, statisticRegisterUser.getId());
					} catch (Exception e) {
						logger.error("更新记录失败"+e.getMessage());
					}
		        
	        }
	 }
	 
}
