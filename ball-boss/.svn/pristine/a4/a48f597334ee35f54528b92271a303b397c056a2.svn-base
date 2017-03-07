package com.lc.zy.ball.boss.framework.bigcitygame.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BigCityGameMapper;
import com.lc.zy.ball.domain.oa.po.BigCityGame;
import com.lc.zy.ball.domain.oa.po.BigCityGameCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.util.MessageUtil;

@Service
@Transactional(readOnly = true)
public class BigCityGameService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(BigCityGameService.class);
    
    @Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private BigCityGameMapper bigCityGameMapper;
    
    @Autowired
    private MessageUtil msgUtils;

    /**
     * @param request
     * @return
     * @throws Exception 
     */
    public Page<BigCityGame> games(Map<String, Object> searchParams,Integer page,Integer size) throws Exception {
    	BigCityGameCriteria criteria = new BigCityGameCriteria();
    	BigCityGameCriteria.Criteria cri = criteria.createCriteria();
    	logger.debug(searchParams.toString());
		int total = 0;
		PageRequest pageable = new PageRequest(page, size);
		
		int count = bigCityGameMapper.countByExample(criteria);
		if(count==0){
			return new PageImpl<>(new ArrayList<BigCityGame>(),pageable,0);
		}
		total = count;
		criteria.setOrderByClause("audit_state");
    	criteria.setMysqlLength(pageable.getPageSize());
    	criteria.setMysqlOffset(pageable.getOffset());
    	List<BigCityGame> games = bigCityGameMapper.selectByExample(criteria);
    	return new PageImpl<>(games, pageable, total);
    }
    
    /**
     * 审核
     * @param id
     * @param type
     * @param msg
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void audit(String id,int type,String msg) throws Exception{
    	BigCityGame game = this.selectByPrimaryKey(BigCityGame.class, id);
    	BigCityGame game_ = new BigCityGame();
    	game_.setId(game.getId());
    	if(game.getAuditState()!=0){
    		throw new Exception();
    	}
    	if(type==0){
    		game_.setAuditState(2);
    	}else{
    		game_.setAuditState(1);
    	}
    	this.updateByPrimaryKeySelective(game_, id);
    	msgUtils.sendSms(game.getLinkPhone(), msg);
    }
    
    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    public BigCityGame getDetail(String id) throws Exception{
    	BigCityGame game = this.selectByPrimaryKey(BigCityGame.class, id);
    	return game;
    }
    
    /**
     * 更新
     * @param game
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void update(BigCityGame game) throws Exception{
    	game.setEt(new Date());
    	game.setEb(SessionUtil.currentUserId());
    	game.setAuditState(0);
    	this.updateByPrimaryKeySelective(game, game.getId());
    }

}
