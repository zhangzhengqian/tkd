package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatisticRegisterUser;
import com.lc.zy.ball.domain.oa.po.StatisticRegisterUserCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* StatisticRegisterUserMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-10-25 16:04:42
*/
public interface StatisticRegisterUserMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatisticRegisterUserCriteria statisticRegisterUserCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatisticRegisterUserCriteria statisticRegisterUserCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatisticRegisterUser statisticRegisterUser);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatisticRegisterUser statisticRegisterUser);

    /**
     * 根据条件查询记录集
     */
    List<StatisticRegisterUser> selectByExample(StatisticRegisterUserCriteria statisticRegisterUserCriteria);

    /**
     * 根据主键查询记录
     */
    StatisticRegisterUser selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statisticRegisterUser") StatisticRegisterUser statisticRegisterUser, @Param("statisticRegisterUserCriteria") StatisticRegisterUserCriteria statisticRegisterUserCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statisticRegisterUser") StatisticRegisterUser statisticRegisterUser, @Param("statisticRegisterUserCriteria") StatisticRegisterUserCriteria statisticRegisterUserCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatisticRegisterUser statisticRegisterUser);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatisticRegisterUser statisticRegisterUser);
}