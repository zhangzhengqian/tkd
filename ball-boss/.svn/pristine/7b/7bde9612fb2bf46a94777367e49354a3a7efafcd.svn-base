package com.lc.zy.ball.boss.framework.qiuyouzone.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.qiuyouzone.controller.QiuyouZoneController;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ManageSsoMapper;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneCommentMapper;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneLabelJoinMapper;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneLabelMapper;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneLikeMapper;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.ManageSso;
import com.lc.zy.ball.domain.oa.po.ManageSsoCriteria;
import com.lc.zy.ball.domain.oa.po.Qiuyouzone;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneComment;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneCommentCriteria;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneCriteria;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabel;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabelCriteria;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabelJoin;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabelJoinCriteria;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLike;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLikeCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly=true)
public class QiuyouZoneService extends AbstractCacheService{

	@Autowired
	private QiuyouzoneMapper qiuyouzoneMapper;
	
	@Autowired
	private QiuyouzoneCommentMapper qiuyouzoneCommentMapper;
	
	@Autowired
	private QiuyouzoneLikeMapper qiuyouzoneLikeMapper;
	
	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SsoUserMapper ssoUserMapper;
	
	@Autowired
	private ManageSsoMapper manageSsoMapper;
	
	@Autowired
	private QiuyouzoneLabelMapper qiuyouzoneLabelMapper;
	
	@Autowired
	private QiuyouzoneLabelJoinMapper qiuyouzoneLabelJoinMapper;
	
	private static Logger logger = LoggerFactory.getLogger(QiuyouZoneController.class);
	
	/**
	 * qiuyouzone列表页
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String,Object>> find(Map<String, Object> searchParams, int page, int size) 
			throws Exception {
		int total = 0;
		PageRequest pageable = new PageRequest(page, size);
        String qiuyouNo = "";
        String name = "";
        if(searchParams.get("qiuyouNo")!=null&&StringUtils.isNotBlank((String)searchParams.get("qiuyouNo"))){
        	qiuyouNo = (String)searchParams.get("qiuyouNo");
        	SsoUserCriteria criteria = new SsoUserCriteria();
        	SsoUserCriteria.Criteria cri = criteria.createCriteria();
        	cri.andQiuyounoEqualTo(qiuyouNo);
        	List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
        	if(CollectionUtils.isNotEmpty(users)){
        		searchParams.put("userId", users.get(0).getId());
        		searchParams.remove("qiuyouNo");
        	}
        }
        if(searchParams.get("name")!=null&&StringUtils.isNotBlank((String)searchParams.get("name"))){
        	name = (String)searchParams.get("name");
        	SsoUserCriteria criteria = new SsoUserCriteria();
        	SsoUserCriteria.Criteria cri = criteria.createCriteria();
        	cri.andNickNameEqualTo(name);
        	List<SsoUser> users = ssoUserMapper.selectByExample(criteria);
        	if(CollectionUtils.isNotEmpty(users)){
        		searchParams.put("userId", users.get(0).getId());
        		searchParams.remove("name");
        	}
        }
        String sql = FreeMarkerUtils.format("/template/qiuyouzone/qiuyouzonecount.ftl", searchParams);
		jdbcTemplate.queryForList(sql);
		logger.debug(sql);
        logger.debug(searchParams.toString());
       	List<Map<String,Object>> countMap = jdbcTemplate.queryForList(sql);
    	total = new BigDecimal((long)countMap.get(0).get("cont")).intValue();
		
    	if(pageable.getOffset()>= 1000){
    		searchParams.put("offset", String.valueOf(pageable.getOffset()).replace(",",""));
        }else{
        	searchParams.put("offset", pageable.getOffset());
        }
    	searchParams.put("pageSize", pageable.getPageSize());
    	
    	sql = FreeMarkerUtils.format("/template/qiuyouzone/qiuyouzonelist.ftl", searchParams);
    	
    	List<Map<String,Object>> qiuyouzones = jdbcTemplate.queryForList(sql);
    	if(StringUtils.isNotBlank(name)){
    		searchParams.put("name", name);
    	}
    	if(StringUtils.isNotBlank(qiuyouNo)){
    		searchParams.put("qiuyouNo", qiuyouNo);
    	}
		return new PageImpl<>(qiuyouzones, pageable, total);
	}
	
	
	/**
	 * 营销列表页
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public Page<Qiuyouzone> findMarkets(Map<String, Object> searchParams, int page, int size) 
			throws Exception {
		int total = 0;
		PageRequest pageable = new PageRequest(page, size);
        String qiuyouNo = "";
        String name = "";    
        if(searchParams.get("qiuyouNo")!=null&&StringUtils.isNotBlank((String)searchParams.get("qiuyouNo"))){
        	qiuyouNo = (String)searchParams.get("qiuyouNo");
        	SsoUserCriteria criteria_ = new SsoUserCriteria();
        	SsoUserCriteria.Criteria cri_ = criteria_.createCriteria();
        	cri_.andQiuyounoEqualTo(qiuyouNo);
        	List<SsoUser> users = ssoUserMapper.selectByExample(criteria_);
        	if(CollectionUtils.isNotEmpty(users)){
        		searchParams.put("EQ_userId", users.get(0).getId());
        	}else{
        		return new PageImpl<>(new ArrayList<Qiuyouzone>(), pageable, total);
        	}
        	searchParams.remove("qiuyouNo");
        }
        if(searchParams.get("name")!=null&&StringUtils.isNotBlank((String)searchParams.get("name"))){
        	name = (String)searchParams.get("name");
        	SsoUserCriteria criteria_ = new SsoUserCriteria();
        	SsoUserCriteria.Criteria cri_ = criteria_.createCriteria();
        	cri_.andNickNameEqualTo(name);
        	List<SsoUser> users = ssoUserMapper.selectByExample(criteria_);
        	if(CollectionUtils.isNotEmpty(users)){
        		searchParams.put("EQ_userId", users.get(0).getId());
        	}else{
        		return new PageImpl<>(new ArrayList<Qiuyouzone>(), pageable, total);
        	}
        	searchParams.remove("name");
        }
        QiuyouzoneCriteria criteria = new QiuyouzoneCriteria();
        QiuyouzoneCriteria.Criteria cri = criteria.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Criterias.bySearchFilter(cri, filters.values());
        cri.andStatusNotEqualTo(2);
        cri.andSendManEqualTo(SessionUtil.currentUserId());
        total = qiuyouzoneMapper.countByExample(criteria);
        logger.debug(searchParams.toString());
       	List<Qiuyouzone> qiuyouzones = qiuyouzoneMapper.selectByExample(criteria);
		
    	if(StringUtils.isNotBlank(name)){
    		searchParams.put("name", name);
    	}
    	if(StringUtils.isNotBlank(qiuyouNo)){
    		searchParams.put("qiuyouNo", qiuyouNo);
    	}
		return new PageImpl<>(qiuyouzones, pageable, total);
	}
	
	
	/**
	 * 营销账户列表页
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public Page<ManageSso> findMarketUsers(Map<String, Object> searchParams, int page, int size) 
			throws Exception {
		int total = 0;
		PageRequest pageable = new PageRequest(page, size);
        ManageSsoCriteria criteria = new ManageSsoCriteria();
        ManageSsoCriteria.Criteria cri = criteria.createCriteria();
        cri.andUserIdEqualTo(SessionUtil.currentUserId());
        criteria.setMysqlOffset(pageable.getOffset());
        criteria.setMysqlLength(pageable.getPageSize());
        
        total = manageSsoMapper.countByExample(criteria);
        
        List<ManageSso> users = manageSsoMapper.selectByExample(criteria);
		return new PageImpl<>(users, pageable, total);
	}
	
	
	
	/**
	 * 获取球友圈评论列表
	 * @param zoneId
	 * @return
	 */
	public List<QiuyouzoneComment> getComments(String zoneId){
		QiuyouzoneCommentCriteria commentCriteria = new QiuyouzoneCommentCriteria();
		QiuyouzoneCommentCriteria.Criteria commentCri = commentCriteria.createCriteria();
		commentCri.andQiuyouzoneIdEqualTo(zoneId);
		commentCri.andStateNotEqualTo(1);
		return qiuyouzoneCommentMapper.selectByExample(commentCriteria);
	}
	
	/**
	 * 获取点赞列表
	 * @param zoneId
	 * @return
	 */
	public List<QiuyouzoneLike> getLikes(String zoneId){
		QiuyouzoneLikeCriteria likeCriteria = new QiuyouzoneLikeCriteria();
		QiuyouzoneLikeCriteria.Criteria likeCri = likeCriteria.createCriteria();
		likeCri.andQiuyouzoneIdEqualTo(zoneId);
		return qiuyouzoneLikeMapper.selectByExample(likeCriteria);
				
	}
	
	/**
	 * 获取评论详情
	 * @param id
	 * @return
	 */
	public QiuyouzoneComment getComment(String id){
		QiuyouzoneComment comment = qiuyouzoneCommentMapper.selectByPrimaryKey(id);
		return comment;
	}
	
	/**
	 * 修改评论
	 * @param comment
	 */
	@Transactional(readOnly=false)
	public void updateComment(QiuyouzoneComment comment){
		qiuyouzoneCommentMapper.updateByPrimaryKeySelective(comment);
	}
	
	/**
	 * 删除营销用户
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteMarketUser(String id){
		manageSsoMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 营销用户下拉列表
	 * @param id
	 */
	public List<SsoUser> getMarketUsers() throws Exception{
		ManageSsoCriteria criteria = new ManageSsoCriteria();
		ManageSsoCriteria.Criteria cri = criteria.createCriteria();
		cri.andUserIdEqualTo(SessionUtil.currentUserId());
		List<ManageSso> managers = manageSsoMapper.selectByExample(criteria);
		List<SsoUser> users = new ArrayList<SsoUser>();
		SsoUser user = null;
		for(ManageSso manage:managers){
			String userid = manage.getSsoUserId();
			SsoUser user_ = this.selectByPrimaryKey(SsoUser.class, userid);
			if(user_!=null){
				user = user_;
			}
			users.add(user);
		}
		return users;
	}
	
	/**
	 * 获取系统标签
	 * @return
	 */
	public List<QiuyouzoneLabel> getLabels(){
		QiuyouzoneLabelCriteria qiuyouzoneLabelCriteria = new QiuyouzoneLabelCriteria();
        qiuyouzoneLabelCriteria.setOrderByClause(" seq desc ");
        QiuyouzoneLabelCriteria.Criteria cri = qiuyouzoneLabelCriteria.createCriteria();
        cri.andStatusEqualTo(1); // 查询可用的标签
        cri.andTypeEqualTo(0);// 系统标签
        List<QiuyouzoneLabel> labelList = qiuyouzoneLabelMapper.selectByExample(qiuyouzoneLabelCriteria);
        return labelList;
	}
	
	/**
	 * 保存球友圈文章
	 * @param zone
	 * @throws Exception
	 */
	@Transactional(readOnly=false)
	public void saveQiuyouzone(Qiuyouzone zone,String labelId) throws Exception{
		try {
			if(StringUtils.isNotBlank(zone.getId())){
				this.updateByPrimaryKey(zone, zone.getId());
			}else{
				zone.setId(UUID.get());
				zone.setStatus(0);
				this.insert(zone, zone.getId());
			}
			//删除原有标签对应关系
			QiuyouzoneLabelJoinCriteria criteria = new QiuyouzoneLabelJoinCriteria();
			QiuyouzoneLabelJoinCriteria.Criteria cri = criteria.createCriteria();
			cri.andQiuyouzoneIdEqualTo(zone.getId());
			qiuyouzoneLabelJoinMapper.deleteByExample(criteria);
			
			//保存标签对应关系
			QiuyouzoneLabelJoin qiuyouzoneLabelJoin = new QiuyouzoneLabelJoin();
	        qiuyouzoneLabelJoin.setId(UUID.get());
	        qiuyouzoneLabelJoin.setCt(new Date());
	        qiuyouzoneLabelJoin.setSeq(0);
	        qiuyouzoneLabelJoin.setLabelId(labelId);
	        qiuyouzoneLabelJoin.setQiuyouzoneId(zone.getId());
	        qiuyouzoneLabelJoinMapper.insertSelective(qiuyouzoneLabelJoin);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取球友圈使用的标签
	 * @param id
	 * @return
	 */
	public String getLabelId(String id){
		QiuyouzoneLabelJoinCriteria criteria = new QiuyouzoneLabelJoinCriteria();
		QiuyouzoneLabelJoinCriteria.Criteria cri = criteria.createCriteria();
		cri.andQiuyouzoneIdEqualTo(id);
		List<QiuyouzoneLabelJoin> labels = qiuyouzoneLabelJoinMapper.selectByExample(criteria);
		if(CollectionUtils.isNotEmpty(labels)){
			return labels.get(0).getLabelId();
		}
		return "";
	}
	
}
