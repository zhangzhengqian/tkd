package com.lc.zy.ball.boss.framework.vote.service;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.vote.vo.VoteCandidateVo;
import com.lc.zy.ball.boss.framework.vote.vo.VoteParticipantsVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.VoteCandidateMapper;
import com.lc.zy.ball.domain.oa.mapper.VoteParticipantsMapper;
import com.lc.zy.ball.domain.oa.mapper.VoteThemeMapper;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.VoteCandidate;
import com.lc.zy.ball.domain.oa.po.VoteCandidateCriteria;
import com.lc.zy.ball.domain.oa.po.VoteParticipants;
import com.lc.zy.ball.domain.oa.po.VoteParticipantsCriteria;
import com.lc.zy.ball.domain.oa.po.VoteTheme;
import com.lc.zy.ball.domain.oa.po.VoteThemeCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liangsh
 * Date: 2016/7/8
 * Time: 16:15
 */
@Service
@Transactional(readOnly = true)
public class VoteService extends AbstractCacheService {

    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    public VoteThemeMapper voteThemeMapper;

    @Autowired
    public VoteCandidateMapper voteCandidateMapper;
    
    @Autowired
    public VoteParticipantsMapper participantsMapper;

    public Page<VoteTheme> list(Pageable pageable, Map<String, Object> searchParams) throws Exception {
        List<VoteTheme> list = new ArrayList<VoteTheme>();
        VoteThemeCriteria c = new VoteThemeCriteria();
        VoteThemeCriteria.Criteria cri = c.createCriteria();
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        c.setMysqlLength(pageable.getPageSize());
        c.setMysqlOffset(pageable.getOffset());
        c.setOrderByClause("ct desc");
        int total = voteThemeMapper.countByExample(c);
        if(total > 0){
            list = voteThemeMapper.selectByExample(c);
        }
        return new PageImpl<>(list, pageable, total);
    }
    
    /**
     * 获取投票人列表
     * @param pageRequest
     * @param searchParams
     * @param themeId
     * @param candidateId
     * @return
     */
	public Map<String, Object> participantList(Pageable pageable, Map<String, Object> searchParams,
			String themeId, String candidateId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>() {};
        List<VoteParticipantsVo> voList = new ArrayList<VoteParticipantsVo>();
        List<VoteParticipants> list = new ArrayList<VoteParticipants>();
        VoteParticipantsCriteria c=new VoteParticipantsCriteria();
        VoteParticipantsCriteria.Criteria cri=c.createCriteria();
        cri.andThemeIdEqualTo(themeId);
        cri.andCandidateIdEqualTo(candidateId);
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        c.setMysqlLength(pageable.getPageSize());
        c.setMysqlOffset(pageable.getOffset());
        c.setOrderByClause("ct desc");
        int total=participantsMapper.countByExample(c);
        if(total > 0){
            list = participantsMapper.selectByExample(c);
            for (VoteParticipants info: list ) {
                VoteParticipantsVo vo = new VoteParticipantsVo();
                BeanUtils.copyProperties(info,vo);
                VoteTheme theme = this.selectByPrimaryKey(VoteTheme.class,vo.getThemeId());
                VoteCandidate candidate=this.selectByPrimaryKey(VoteCandidate.class, vo.getCandidateId());
                vo.setCreateTime(DateUtil.formatDate(info.getCt(), "yyyy-MM-dd HH:mm:ss"));
                Date registTime=this.selectByPrimaryKey(SsoUser.class, vo.getUserId()).getRegistTime();
                if (registTime!=null) {
                	vo.setRegistTime(DateUtil.formatDate(registTime,"yyyy-MM-dd HH:mm:ss"));
				}
                vo.setTheme(theme.getTitle());
                vo.setCandidate(candidate.getUserName());
                String phone=this.selectByPrimaryKey(SsoUser.class, vo.getUserId()).getPhone();
                vo.setPhone(phone);
                voList.add(vo);
            }
        }
        map.put("totalParticipants",total);
        map.put("data",new PageImpl<>(voList, pageable, total));
        return map;
	}
    
    public Map<String,Object> memberlist(Pageable pageable, Map<String, Object> searchParams,String themeId) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>() {};
        List<VoteCandidateVo> voList = new ArrayList<VoteCandidateVo>();
        List<VoteCandidate> list = new ArrayList<VoteCandidate>();
        VoteCandidateCriteria c = new VoteCandidateCriteria();
        VoteCandidateCriteria.Criteria cri = c.createCriteria();
        cri.andThemeIdEqualTo(themeId);
        if (searchParams != null) {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
        }
        c.setMysqlLength(pageable.getPageSize());
        c.setMysqlOffset(pageable.getOffset());
        c.setOrderByClause("votes_number desc,ct desc");
        int total = voteCandidateMapper.countByExample(c);
        int totalParticipants = 0;
        if(total > 0){
            list = voteCandidateMapper.selectByExample(c);
            for (VoteCandidate info: list ) {
                VoteCandidateVo vo = new VoteCandidateVo();
                BeanUtils.copyProperties(info,vo);
                VoteTheme theme = this.selectByPrimaryKey(VoteTheme.class,vo.getThemeId());
                vo.setTheme(theme.getTitle());
                if(vo.getVotesNumber() != null) {
                    totalParticipants += vo.getVotesNumber();
                }
                voList.add(vo);
            }
        }
        map.put("totalParticipants",this.totalNumber(themeId));
        map.put("data",new PageImpl<>(voList, pageable, total));
        return map;
    }
    
    @Transactional(readOnly = false)
    public void insertVote(VoteParticipants participants) throws Exception{
		participantsMapper.insert(participants);
		String id = participants.getCandidateId();
		VoteCandidate candidate = this.selectByPrimaryKey(VoteCandidate.class, id);
		VoteParticipantsCriteria criteria = new VoteParticipantsCriteria();
		VoteParticipantsCriteria.Criteria cri = criteria.createCriteria();
		cri.andCandidateIdEqualTo(id);
		int cnt = participantsMapper.countByExample(criteria);
		candidate.setVotesNumber(cnt);
		this.updateByPrimaryKey(candidate, id);
	}

    public  int totalNumber(String themeId){
        VoteCandidateCriteria c = new VoteCandidateCriteria();
        VoteCandidateCriteria.Criteria cri = c.createCriteria();
        cri.andThemeIdEqualTo(themeId);
        c.setOrderByClause("ct desc");
        int totalParticipants = 0;
        if( voteCandidateMapper.countByExample(c) > 0){
            List<VoteCandidate>  list = voteCandidateMapper.selectByExample(c);
            for (VoteCandidate info: list ) {
                if(info.getVotesNumber() != null) {
                    totalParticipants += info.getVotesNumber();
                }
            }
        }
        return  totalParticipants;
    }
    
    /**
     * 更改候选人状态 
     * @param candidate
     */
    @Transactional(readOnly = false)
	public void insertUserState(String themeId,String ids) throws Exception{
		VoteCandidateCriteria c = new VoteCandidateCriteria();
        VoteCandidateCriteria.Criteria cri = c.createCriteria();
        cri.andThemeIdEqualTo(themeId);
		List<VoteCandidate> list=voteCandidateMapper.selectByExample(c);
		for (VoteCandidate voteCandidate : list) {
			voteCandidate.setUserState(0);
			voteCandidateMapper.updateByPrimaryKey(voteCandidate);
		}
		String[] idArray = ids.split(",");
    	for(String id:idArray){
    		VoteCandidate candidate=voteCandidateMapper.selectByPrimaryKey(id);
    		candidate.setUserState(1);
    		voteCandidateMapper.updateByPrimaryKey(candidate);
    	}
	}
}
