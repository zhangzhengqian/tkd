package com.lc.zy.ball.boss.framework.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OpLoggingMapper;
import com.lc.zy.ball.domain.oa.po.OpLogging;
import com.lc.zy.ball.domain.oa.po.OpLoggingCriteria;
import com.lc.zy.ball.boss.common.UserContext;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Component
//@Transactional(value="core", readOnly = true)
@Transactional(readOnly=true)
public class OpLoggingService {

	@Autowired
	private OpLoggingMapper loggingMapper;
	
	/**
	 * 分页查找日志.
	 * 
	 * @throws Exception
	 */
	public Page<OpLogging> find(Map<String, Object> searchParams, int page, int size) throws Exception {
		
		PageRequest pageable = new PageRequest(page, size);
		
		OpLoggingCriteria oc = new OpLoggingCriteria();
		oc.setMysqlOffset(pageable.getOffset());
		oc.setMysqlLength(pageable.getPageSize());
		
		if (searchParams != null) {
			OpLoggingCriteria.Criteria cri = oc.createCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		
		oc.setOrderByClause("create_time desc");
		
		int total = loggingMapper.countByExample(oc);
		List<OpLogging> list = loggingMapper.selectByExample(oc);
		
		return new PageImpl<>(list, pageable, total);
	}
	
	public OpLogging get(String logId) throws Exception {
		return loggingMapper.selectByPrimaryKey(logId);
	}
	
	/**
	 * 保存操作日志.
	 * @param log
	 * @throws Exception
	 */
	@Transactional(readOnly=false)
	public void save(OpLogging log) {
		log.setLogId(UUID.get());
		loggingMapper.insertSelective(log);
	}
	
	/**
	 * 助手方法，根据ServletRequest创建一个OpLogging实例.
	 * @return 返回的实例自动填充了ip/userId/createTime属性.
	 * 
	 */
	public static OpLogging newOpLogging(ServletRequest request) {
		OpLogging log = new OpLogging();
		log.setIp(request.getRemoteAddr());
		String userId = UserContext.getCurrent().getUserId();
		log.setUserId(userId);
		log.setCreateTime(new Date());
		return log;
	}
	
}
