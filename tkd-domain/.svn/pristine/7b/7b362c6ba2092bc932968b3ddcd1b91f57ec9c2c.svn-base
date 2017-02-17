package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumComment;
import com.lc.zy.ball.domain.oa.po.StatiumCommentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumCommentMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:36:28
*/
public interface StatiumCommentMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumCommentCriteria statiumCommentCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumCommentCriteria statiumCommentCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumComment statiumComment);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumComment statiumComment);

    /**
     * 根据条件查询记录集
     */
    List<StatiumComment> selectByExample(StatiumCommentCriteria statiumCommentCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumComment selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumComment") StatiumComment statiumComment, @Param("statiumCommentCriteria") StatiumCommentCriteria statiumCommentCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumComment") StatiumComment statiumComment, @Param("statiumCommentCriteria") StatiumCommentCriteria statiumCommentCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumComment statiumComment);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumComment statiumComment);
}