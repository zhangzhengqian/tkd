package com.lc.zy.ball.boss.framework.system.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.MessageMapper;
import com.lc.zy.ball.domain.oa.po.Message;
import com.lc.zy.ball.domain.oa.po.MessageCriteria;

@Service
public class MessageService extends AbstractCacheService {

	private static Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private MessageMapper messageMapper = null;
	
	public int badge(String userId){
		MessageCriteria messageCriteria = new MessageCriteria();
		MessageCriteria.Criteria criteria = messageCriteria.createCriteria();
		criteria.andToUserEqualTo(userId);
		criteria.andStatusEqualTo("new");
		return messageMapper.countByExample(messageCriteria);
	}
	
	public String getMessageForEntity(String Messageid){
		MessageCriteria messageCriteria = new MessageCriteria();
		messageCriteria.setOrderByClause("ct desc");
		messageCriteria.createCriteria().andToUserEqualTo(Messageid).andStatusEqualTo("new");
		Message message = messageMapper.selectByExample(messageCriteria).get(0);
		Date now = new Date();
		//logger.debug("now====>"+now);
		Date ct = message.getCt();
		long t1 = now.getTime()/1000;
		long t2 = ct.getTime()/1000;
		if((t1-t2)<= 29){
			logger.debug("now="+now+" ; ct="+ct+" ;t1="+t1+" ; t2="+t2+" ; t1-t2="+(t1-t2)); 
			return "您有新消息，请注意查收。";
		}
		return null;
	}
	
	public List<Message> select(int pageNo,int type){
		int pageSize = 20;
		String uid = SessionUtil.currentUserId();
		logger.debug("message_loadMore_uid={}",uid);
		MessageCriteria messageCriteria = new MessageCriteria();
		MessageCriteria.Criteria cri = messageCriteria.createCriteria();
		messageCriteria.setMysqlOffset(pageNo*pageSize);
		messageCriteria.setMysqlLength(pageSize);
		cri.andToUserEqualTo(uid);
		//--ADD--20150921--消费分全部、未读展示--SATART--
		if(type == 1){
			cri.andStatusEqualTo("new");
		}
		//--ADD--20150921--消费分全部、未读展示--END---
		messageCriteria.setOrderByClause("ct desc");
		List<Message> list = messageMapper.selectByExample(messageCriteria);
		return list;
	}
	
	/**
	 * 
	 * <站内消息><获取消息数量>
	 *
	 * @create：2015年9月21日 下午5:57:04
	 * @author： liangsh
	 * @param type
	 * @return
	 */
	public int msgCount(int type){
		String uid = SessionUtil.currentUserId();
		MessageCriteria messageCriteria = new MessageCriteria();
		MessageCriteria.Criteria cri = messageCriteria.createCriteria();
		cri.andToUserEqualTo(uid);
		if(type == 1){ //未读
			cri.andStatusEqualTo("new");
		}
		return messageMapper.countByExample(messageCriteria);
	}
	
	/**
	 * 
	 * <站内消息><获取所有未读消息>
	 *
	 * @create：2015年9月22日 下午3:04:09
	 * @author： liangsh
	 * @return
	 */
	public List<Message> selectAll(){
		MessageCriteria c = new MessageCriteria();
		c.createCriteria().andStatusEqualTo("new");
		return messageMapper.selectByExample(c);
	}
	
}
