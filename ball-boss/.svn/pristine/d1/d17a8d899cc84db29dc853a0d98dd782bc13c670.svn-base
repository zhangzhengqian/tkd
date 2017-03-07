package com.lc.zy.ball.boss.framework.assessment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.AssessmentMapper;
import com.lc.zy.ball.domain.oa.po.Assessment;
import com.lc.zy.ball.domain.oa.po.AssessmentCriteria;

@Service
@Transactional(readOnly = true)
public class AssessmentService extends AbstractCacheService {

    @Autowired
	private AssessmentMapper assessmentMapper;
	
    public Page<Assessment> list(Pageable pageable) throws Exception {
        List<Assessment> list = new ArrayList<Assessment>();
        AssessmentCriteria criteria = new AssessmentCriteria();
        AssessmentCriteria.Criteria cri = criteria.createCriteria();
        cri.andDeleteFlagIsNull();
        int totalCount = assessmentMapper.countByExample(criteria);
        if(totalCount==0){
        	return new PageImpl<>(list, pageable, 0);
        }
        criteria.setMysqlLength(pageable.getPageSize());
        criteria.setMysqlOffset(pageable.getOffset());
        criteria.setOrderByClause("send_time desc");
        list = assessmentMapper.selectByExample(criteria);
        return new PageImpl<>(list, pageable, totalCount);
    }

    @Transactional(readOnly = false)
	public void revoke(String id) throws Exception {
		Assessment infoSaveing = new Assessment();
		infoSaveing.setId(id);
		infoSaveing.setSendType(0);
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}
    
    @Transactional(readOnly = false)
	public void send(String id) throws Exception {
		Assessment infoSaveing = new Assessment();
		infoSaveing.setId(id);
		infoSaveing.setSendType(1);
		infoSaveing.setSendTime(new Date());
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}
    
    @Transactional(readOnly = false)
	public void delete(String id) throws Exception {
		Assessment infoSaveing = new Assessment();
		infoSaveing.setId(id);
		infoSaveing.setDeleteFlag(1);
		infoSaveing.setEb(SessionUtil.currentUserId());
		infoSaveing.setEt(new Date());
		this.updateByPrimaryKeySelective(infoSaveing, id);
	}
    
    public Assessment view(String id) throws Exception {
		return this.selectByPrimaryKey(Assessment.class,id);
	}
}
