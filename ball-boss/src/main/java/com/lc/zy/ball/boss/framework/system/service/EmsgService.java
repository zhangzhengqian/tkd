package com.lc.zy.ball.boss.framework.system.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.domain.oa.mapper.EmsgServerMapper;
import com.lc.zy.ball.domain.oa.po.EmsgServer;
import com.lc.zy.ball.domain.oa.po.EmsgServerCriteria;
import com.lc.zy.common.Constants;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly = true)
public class EmsgService {

	@Autowired
	private EmsgServerMapper emsgServerMapper;

	@Autowired
	private RedisPool redisPool;

	/**
	 * 获取消息服务器信息
	 * 
	 * @return
	 * @author yankefei
	 */
	public EmsgServer get() {
		try {
			// 只维护一条记录
			List<EmsgServer> list = emsgServerMapper.selectByExample(new EmsgServerCriteria());
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改/新增emsg服务器信息
	 * 
	 * @param emsgServer
	 * @author yankefei
	 */
	@Transactional(readOnly = false)
	public void save(EmsgServer emsgServer) {
		try {
			if (StringUtils.isNotBlank(emsgServer.getId())) {
				emsgServer.setCb(SessionUtil.currentUserId());
				emsgServer.setCt(new Date());
				// 更新
				emsgServerMapper.updateByPrimaryKey(emsgServer);
				redisPool.setStr(Constants.EMSG_SERVER, MyGson.getInstance().toJson(emsgServer));
			} else {
				emsgServer.setId(UUID.get());
				emsgServer.setCb(SessionUtil.currentUserId());
				emsgServer.setCt(new Date());
				// 插入
				emsgServerMapper.insertSelective(emsgServer);
				redisPool.setStr(Constants.EMSG_SERVER, MyGson.getInstance().toJson(emsgServer));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
