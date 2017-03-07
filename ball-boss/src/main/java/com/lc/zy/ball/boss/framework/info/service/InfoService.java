package com.lc.zy.ball.boss.framework.info.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.ZoneInfoCommentMapper;
import com.lc.zy.ball.domain.oa.mapper.ZoneInfoLikeMapper;
import com.lc.zy.ball.domain.oa.mapper.ZoneInfoMapper;
import com.lc.zy.ball.domain.oa.po.ZoneInfo;
import com.lc.zy.ball.domain.oa.po.ZoneInfoCriteria;

@Service
@Transactional(readOnly = true)
public class InfoService extends AbstractCacheService {

    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);

    @Autowired
	private ZoneInfoMapper infoMapper;
	
	@Autowired
	private ZoneInfoLikeMapper infoLikeMapper;
	
	@Autowired
	private ZoneInfoCommentMapper commentMapper;

    public Page<ZoneInfo> list(Pageable pageable) throws Exception {
        List<ZoneInfo> list = new ArrayList<ZoneInfo>();
        ZoneInfoCriteria criteria = new ZoneInfoCriteria();
        ZoneInfoCriteria.Criteria cri = criteria.createCriteria();
        cri.andDeleteFlagIsNull();
        int totalCount = infoMapper.countByExample(criteria);
        if(totalCount==0){
        	return new PageImpl<>(list, pageable, 0);
        }
        criteria.setMysqlLength(pageable.getPageSize());
        criteria.setMysqlOffset(pageable.getOffset());
        criteria.setOrderByClause("stick desc,send_time desc");
        list = infoMapper.selectByExample(criteria);
        return new PageImpl<>(list, pageable, totalCount);
    }

    @Transactional(readOnly = false)
	public void stick(String id) throws Exception {
		ZoneInfoCriteria criteria = new ZoneInfoCriteria();
        ZoneInfoCriteria.Criteria cri = criteria.createCriteria();
        cri.andStickEqualTo(1);
        List<ZoneInfo> list = infoMapper.selectByExample(criteria);
		for(ZoneInfo info : list){
			info.setStick(0);
			this.updateByPrimaryKey(info, info.getId());
		}
		ZoneInfo infoSaveing = new ZoneInfo();
		infoSaveing.setId(id);
		infoSaveing.setStick(1);
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}

    @Transactional(readOnly = false)
	public void revoke(String id) throws Exception {
		ZoneInfo infoSaveing = new ZoneInfo();
		infoSaveing.setId(id);
		infoSaveing.setSendType(2);
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}
    
    @Transactional(readOnly = false)
	public void delete(String id) throws Exception {
		ZoneInfo infoSaveing = new ZoneInfo();
		infoSaveing.setId(id);
		infoSaveing.setDeleteFlag(1);
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}
    
    public ZoneInfo view(String id) throws Exception {
		return this.selectByPrimaryKey(ZoneInfo.class,id);
	}
}
