package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumClassMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public interface StatiumClassMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumClassCriteria statiumClassCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumClassCriteria statiumClassCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumClass statiumClass);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumClass statiumClass);

    /**
     * 根据条件查询记录集
     */
    List<StatiumClass> selectByExample(StatiumClassCriteria statiumClassCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumClass selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumClass") StatiumClass statiumClass, @Param("statiumClassCriteria") StatiumClassCriteria statiumClassCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumClass") StatiumClass statiumClass, @Param("statiumClassCriteria") StatiumClassCriteria statiumClassCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumClass statiumClass);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumClass statiumClass);
}