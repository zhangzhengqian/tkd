package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumActivity;
import com.lc.zy.ball.domain.oa.po.StatiumActivityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumActivityMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public interface StatiumActivityMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumActivityCriteria statiumActivityCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumActivityCriteria statiumActivityCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumActivity statiumActivity);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumActivity statiumActivity);

    /**
     * 根据条件查询记录集
     */
    List<StatiumActivity> selectByExample(StatiumActivityCriteria statiumActivityCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumActivity selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumActivity") StatiumActivity statiumActivity, @Param("statiumActivityCriteria") StatiumActivityCriteria statiumActivityCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumActivity") StatiumActivity statiumActivity, @Param("statiumActivityCriteria") StatiumActivityCriteria statiumActivityCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumActivity statiumActivity);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumActivity statiumActivity);
}