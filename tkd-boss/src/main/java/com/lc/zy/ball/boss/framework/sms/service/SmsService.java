package com.lc.zy.ball.boss.framework.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.util.MessageUtil;

@Service
public class SmsService extends AbstractCacheService {

	// log
	private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Autowired
	private MessageUtil messageUtil;

	public List<SsoUser> findSsoUserList(String statiumm, String name) {
		SsoUserCriteria ssoUserCriteria=new SsoUserCriteria();
		SsoUserCriteria.Criteria cri=ssoUserCriteria.createCriteria();
		cri.andStateNotEqualTo(2);
		if (name!=null && !name.trim().equals("")){	
			cri.andNickNameLike("%"+name.trim()+"%");
			SsoUserCriteria.Criteria cri1=ssoUserCriteria.createCriteria();
			cri1.andStateNotEqualTo(2);
			cri1.andUsernameLike("%"+name.trim()+"%");
			ssoUserCriteria.or(cri1);
			SsoUserCriteria.Criteria cri2=ssoUserCriteria.createCriteria();
			cri2.andStateNotEqualTo(2);
			cri2.andPhoneLike("%"+name.trim()+"%");
			ssoUserCriteria.or(cri2);
		}
		return ssoUserMapper.selectByExample(ssoUserCriteria);
	}

	public void sendSms(String content, String linkmans) {
		content+="【道馆通】";
		String[] links=linkmans.split(";");
		for (String link:links){
			messageUtil.sendSms(link.split("/")[1], content);
		}
	}

}
