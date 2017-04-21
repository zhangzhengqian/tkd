package com.lc.zy.ball.boss.framework.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatisticRegisterUserMapper;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUser;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUserCriteria;
import com.lc.zy.common.qiniu.util.StringUtils;

@Service
public class StatisticRegisterService extends AbstractCacheService{
	
	@Autowired
	private	StatisticRegisterUserMapper	statisticRegisterUserMapper;
	
	/**
	 * 
	 * <用户统计分页><功能具体实现>
	 *
	 * @create：2016年10月26日 下午2:21:10
	 * @author：zzq
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<StatisticRegisterUser> list(PageRequest pageRequest){
		StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
		statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
		statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
		statisticRegisterUserCriteria.setOrderByClause("ct desc");
		int total = 0;
		List<StatisticRegisterUser> StatisticRegisterUsers = statisticRegisterUserMapper.selectByExample(statisticRegisterUserCriteria);
		List<StatisticRegisterUser> newList = new ArrayList<StatisticRegisterUser>();
		Map<String,StatisticRegisterUser> maps = new HashMap<String,StatisticRegisterUser>();
		//封装一个map 把渠道相同的对象放入一个key中
		for(StatisticRegisterUser user:StatisticRegisterUsers){
			String channel = user.getChannel();
			if(maps.containsKey(channel)){
				StatisticRegisterUser statisticRegisterUser = new StatisticRegisterUser();
				StatisticRegisterUser statisticRegisterUser1 = maps.get(channel);
				statisticRegisterUser.setCounter(statisticRegisterUser1.getCounter()+user.getCounter());
				statisticRegisterUser.setChannel(channel);
				//更新某个元素
				maps.remove(channel);
				maps.put(channel, statisticRegisterUser);
			}
			else{
				StatisticRegisterUser statisticRegisterUser = new StatisticRegisterUser();
				statisticRegisterUser.setChannel(channel);
				statisticRegisterUser.setCounter(user.getCounter());
				maps.put(channel, statisticRegisterUser);
				++total;
			}
		}
		//将新的对象放入list中
		for(Map.Entry<String, StatisticRegisterUser> entry : maps.entrySet()){
			StatisticRegisterUser user = entry.getValue();
			newList.add(user);
		}
		return new PageImpl<StatisticRegisterUser>(newList,pageRequest,total);
	}
	
	/**
	 * 
	 * <统计注册用户总数><功能具体实现>
	 *
	 * @create：2016年10月26日 下午3:30:01
	 * @author：zzq
	 * @param list
	 * @return
	 */
	public int countTotal(Page<StatisticRegisterUser> list){
		List<StatisticRegisterUser> userList = list.getContent();
		int totalAmount = 0;
		for(StatisticRegisterUser user : userList){
			totalAmount+=user.getCounter();
		}
		return	totalAmount;
	}
	
	
	/**
	 * 
	 * <注册渠道注册详情><功能具体实现>
	 *
	 * @create：2016年10月26日 下午8:12:10
	 * @author：zzq
	 * @param channel
	 * @return
	 */
	public Page<StatisticRegisterUser> detailStatisticByChannel(PageRequest pageRequest,String channel){
		StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
		statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
		statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
		statisticRegisterUserCriteria.setOrderByClause("ct desc");
		//渠道名称不为空
		if(!"".equals(channel)){
			statisticRegisterUserCriteria.createCriteria().andChannelEqualTo(channel);
		}
		List<StatisticRegisterUser> list = statisticRegisterUserMapper.selectByExample(statisticRegisterUserCriteria);
		//返回的合并日期的新list
		List<StatisticRegisterUser> newList = new ArrayList<StatisticRegisterUser>();
		//封装新map key是 yyyy-MM-dd形式的时间
		Map<String,StatisticRegisterUser> maps = new HashMap<String,StatisticRegisterUser>();
		int total = 0 ;
		for(StatisticRegisterUser user : list){
			String ct = user.getCt().substring(0, 10);
			if(maps.containsKey(ct)){
				StatisticRegisterUser statisticRegisterUser = new StatisticRegisterUser();
				StatisticRegisterUser statisticRegisterUser1 = maps.get(ct);
				//注册人数
				statisticRegisterUser.setCounter(statisticRegisterUser1.getCounter()+user.getCounter());
				//注册时间
				statisticRegisterUser.setCt(ct);
				//注册渠道
				statisticRegisterUser.setChannel(channel);
				maps.remove(ct);
				maps.put(ct, statisticRegisterUser);
			}
			else{
				StatisticRegisterUser statisticRegisterUser = new StatisticRegisterUser();
				//创建时间
				statisticRegisterUser.setCt(ct);
				//注册人数
				statisticRegisterUser.setCounter(user.getCounter());
				//注册渠道
				statisticRegisterUser.setChannel(channel);
				maps.put(ct, statisticRegisterUser);
				++total;
			}
			
		}
		//将新的对象放入newlist中
			for(Map.Entry<String, StatisticRegisterUser> entry : maps.entrySet()){
				StatisticRegisterUser user = entry.getValue();
				newList.add(user);
			}
			//将newList反序排序 时间近的放前面 
			//Collections.reverse(newList);	构造一个匿名内部类 重写compare方法 进行ct降序排序
			Collections.sort(newList, new Comparator<StatisticRegisterUser>() {

				@Override
				public int compare(StatisticRegisterUser o1,
						StatisticRegisterUser o2) {
					String c1 = o1.getCt().trim();
					String c2 = o2.getCt().trim();
					return c2.compareTo(c1);
				}
				
			});
		return new PageImpl<>(newList, pageRequest, total);
	}
	
	/**
	 * 
	 * <某个渠道某一天的注册用户><功能具体实现>
	 *
	 * @create：2016年10月27日 下午2:26:01
	 * @author：zzq
	 * @param channel
	 * @param ct
	 * @return
	 */
	public Page<StatisticRegisterUser> selectByHourChannel(PageRequest pageRequest,String channel,String ct){
		StatisticRegisterUserCriteria statisticRegisterUserCriteria = new StatisticRegisterUserCriteria();
		statisticRegisterUserCriteria.setMysqlLength(pageRequest.getPageSize());
		statisticRegisterUserCriteria.setMysqlOffset(pageRequest.getOffset());
		statisticRegisterUserCriteria.setOrderByClause("ct desc");
		//所有渠道的当天注册用户
		if(StringUtils.isNullOrEmpty(channel)){
			statisticRegisterUserCriteria.createCriteria().andCtLike("%" + ct + "%");
		}
		//某个渠道当天的用户
		else{
			statisticRegisterUserCriteria.createCriteria().andChannelEqualTo(channel).andCtLike("%" + ct + "%");
		}
		int total = statisticRegisterUserMapper.countByExample(statisticRegisterUserCriteria);
		List<StatisticRegisterUser>	allUsers = statisticRegisterUserMapper.selectByExample(statisticRegisterUserCriteria);
		return new PageImpl<>(allUsers, pageRequest, total);
	}
}
