package com.lc.zy.ball.boss.framework.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.domain.oa.mapper.SysConfigMapper;
import com.lc.zy.ball.domain.oa.po.SysConfig;


@Service
//@Transactional(value="core", readOnly=true)
@Transactional(readOnly=true)
public class SysConfigService {
	
	private static final Logger log = LoggerFactory.getLogger(SysConfigService.class);

	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	/**
	 * 查询系统配置项.
	 * 
	 * @param name 配置项名称.
	 * @return 系统配置项的值.
	 */
	public SysConfig get(String name) {
		return sysConfigMapper.selectByPrimaryKey(name);
	}
	

	/**
	 * 设置系统配置项.
	 * 
	 * @param name 配置项名称.
	 * @param value 配置项的值.
	 */
	@Transactional(readOnly=false)
	public void set(String name, String value) {
		SysConfig old = get(name);
		if (old != null) {
			log.debug("更新系统配置项 '{}' 的值，原始值={}, 更新新值={}", name, old.getValue(), value);
			old.setValue(value);
			sysConfigMapper.updateByPrimaryKey(old);
		} else {
			log.debug("设置系统配置项： {} = {}", name, value);
			SysConfig config = new SysConfig();
			config.setName(name);
			config.setValue(value);
			sysConfigMapper.insert(config);
		}
	}
}
