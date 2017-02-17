package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CoachComment;
import com.lc.zy.ball.domain.oa.po.CoachCommentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CoachCommentMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:36:28
*/
public interface CoachCommentMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CoachCommentCriteria coachCommentCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CoachCommentCriteria coachCommentCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CoachComment coachComment);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CoachComment coachComment);

    /**
     * 根据条件查询记录集
     */
    List<CoachComment> selectByExample(CoachCommentCriteria coachCommentCriteria);

    /**
     * 根据主键查询记录
     */
    CoachComment selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("coachComment") CoachComment coachComment, @Param("coachCommentCriteria") CoachCommentCriteria coachCommentCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("coachComment") CoachComment coachComment, @Param("coachCommentCriteria") CoachCommentCriteria coachCommentCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CoachComment coachComment);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CoachComment coachComment);
}