package com.lc.zy.ball.boss.framework.comment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.comment.vo.commentVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.BookCommentMapper;
import com.lc.zy.ball.domain.oa.mapper.CoachCommentMapper;
import com.lc.zy.ball.domain.oa.mapper.CommentMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumCommentMapper;
import com.lc.zy.ball.domain.oa.po.BookComment;
import com.lc.zy.ball.domain.oa.po.BookCommentCriteria;
import com.lc.zy.ball.domain.oa.po.CoachComment;
import com.lc.zy.ball.domain.oa.po.CoachCommentCriteria;
import com.lc.zy.ball.domain.oa.po.Comment;
import com.lc.zy.ball.domain.oa.po.CommentCriteria;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.StatiumComment;
import com.lc.zy.ball.domain.oa.po.StatiumCommentCriteria;
import com.lc.zy.common.data.Criterias;

@Service
public class CommentService extends AbstractCacheService {
	
	@Autowired
	private StatiumCommentMapper statiumCommentMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private CoachCommentMapper coachCommentMapper;
	
	@Autowired
	private BookCommentMapper bookCommentMapper;
	
	/**
	 * 
	 * <><获取场馆评论>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @throws Exception
	 * @author liangsh
	 * @date 2015年12月24日 下午2:34:02
	 */
	public Page<commentVo> find(PageRequest pageable, Map<String,Object> searchParams,Integer type) throws Exception{
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		List<commentVo> voList = new ArrayList<commentVo>();
		int total = 0;
		if(type == 0){ //场馆
		    setOrderComment(); //场馆订单评论
			StatiumCommentCriteria c = new StatiumCommentCriteria();
			StatiumCommentCriteria.Criteria criteria = c.createCriteria();
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
		    Criterias.bySearchFilter(criteria, filters.values());
			c.setOrderByClause("ct desc");
			List<StatiumComment> list = statiumCommentMapper.selectByExample(c);
			total = statiumCommentMapper.countByExample(c);
			if(CollectionUtils.isNotEmpty(list)){
				for (StatiumComment statiumComment : list) {
					commentVo vo = new commentVo();
					vo.setId(statiumComment.getId());
					vo.setContent(statiumComment.getContent());
					vo.setCt(statiumComment.getCt());
					if(StringUtils.isNotBlank(statiumComment.getUid())){
						SsoUser user = this.selectByPrimaryKey(SsoUser.class,statiumComment.getUid());
						if(user != null){
							vo.setNickName(user.getNickName());
							vo.setPhone(user.getPhone());
						}
					}
					voList.add(vo);
				}
			}
		}else if(type == 1 || type == 2){//活动 //赛事
			CommentCriteria c = new CommentCriteria();
			CommentCriteria.Criteria criteria = c.createCriteria();
			if(type == 1){
				criteria.andTypeEqualTo(0); //0为活动  
			}else{
				criteria.andTypeEqualTo(1); // 1为赛事
			}
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
			Criterias.bySearchFilter(criteria, filters.values());
			c.setOrderByClause("create_time desc");
			List<Comment> list = commentMapper.selectByExample(c);
			total = commentMapper.countByExample(c);
			if(CollectionUtils.isNotEmpty(list)){
				for (Comment comment : list) {
					commentVo vo = new commentVo();
					vo.setId(comment.getId().toString());
					vo.setContent(comment.getContent());
					vo.setCt(comment.getCreateTime());
					if(StringUtils.isNotBlank(comment.getUid())){
						SsoUser user = this.selectByPrimaryKey(SsoUser.class,comment.getUid());
						if(user != null){
							vo.setNickName(user.getNickName());
							vo.setPhone(user.getPhone());
						}
					}
					voList.add(vo);
				}
			}
		}else if(type == 3){//教陪
			CoachCommentCriteria c = new CoachCommentCriteria();
			CoachCommentCriteria.Criteria criteria = c.createCriteria();
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
			Criterias.bySearchFilter(criteria, filters.values());
			c.setOrderByClause("ct desc");
			List<CoachComment> list = coachCommentMapper.selectByExample(c);
			total = coachCommentMapper.countByExample(c);
			if(CollectionUtils.isNotEmpty(list)){
				for (CoachComment comment : list) {
					commentVo vo = new commentVo();
					vo.setId(comment.getId().toString());
					vo.setContent(comment.getContent());
					vo.setCt(comment.getCt());
					if(StringUtils.isNotBlank(comment.getUid())){
						SsoUser user = this.selectByPrimaryKey(SsoUser.class,comment.getUid());
						if(user != null){
							vo.setNickName(user.getNickName());
							vo.setPhone(user.getPhone());
						}
					}
					voList.add(vo);
				}
			}
		}else if(type == 4){//约球
			BookCommentCriteria c = new BookCommentCriteria();
			BookCommentCriteria.Criteria criteria = c.createCriteria();
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
			Criterias.bySearchFilter(criteria, filters.values());
			c.setOrderByClause("ct desc");
			List<BookComment> list = bookCommentMapper.selectByExample(c);
			total = bookCommentMapper.countByExample(c);
			if(CollectionUtils.isNotEmpty(list)){
				for (BookComment comment : list) {
					commentVo vo = new commentVo();
					vo.setId(comment.getId().toString());
					vo.setContent(comment.getContent());
					vo.setCt(comment.getCt());
					if(StringUtils.isNotBlank(comment.getUserId())){
						SsoUser user = this.selectByPrimaryKey(SsoUser.class,comment.getUserId());
						if(user != null){
							vo.setNickName(user.getNickName());
							vo.setPhone(user.getPhone());
						}
					}
					voList.add(vo);
				}
			}
		}
        return new PageImpl<>(voList, pageable, total);
	}
	
	public void setOrderComment(){
	    StatiumCommentCriteria statiumCommentCriteria = new StatiumCommentCriteria();
	    statiumCommentCriteria.createCriteria().andOrderIdIsNotNull();
	    List<StatiumComment> commentLIst = statiumCommentMapper.selectByExample(statiumCommentCriteria);
	    Order order = null;
	    for(StatiumComment comment : commentLIst){
	        try {
                order = this.selectByPrimaryKey(Order.class, comment.getOrderId());
                if(order != null){
                    order.setIsComment(1);
                }
                this.updateByPrimaryKeySelective(order, order.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
	    }
	}
}
