package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.CoachCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CoachMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:49:02
*/
public interface CoachMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CoachCriteria coachCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CoachCriteria coachCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Coach coach);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Coach coach);

    /**
     * 根据条件查询记录集
     */
    List<Coach> selectByExample(CoachCriteria coachCriteria);

    /**
     * 根据主键查询记录
     */
    Coach selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("coach") Coach coach, @Param("coachCriteria") CoachCriteria coachCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("coach") Coach coach, @Param("coachCriteria") CoachCriteria coachCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Coach coach);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Coach coach);
}