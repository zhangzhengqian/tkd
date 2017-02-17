package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumClassInfoMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-26 17:49:14
*/
public interface StatiumClassInfoMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumClassInfoCriteria statiumClassInfoCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumClassInfoCriteria statiumClassInfoCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumClassInfo statiumClassInfo);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumClassInfo statiumClassInfo);

    /**
     * 根据条件查询记录集
     */
    List<StatiumClassInfo> selectByExample(StatiumClassInfoCriteria statiumClassInfoCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumClassInfo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumClassInfo") StatiumClassInfo statiumClassInfo, @Param("statiumClassInfoCriteria") StatiumClassInfoCriteria statiumClassInfoCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumClassInfo") StatiumClassInfo statiumClassInfo, @Param("statiumClassInfoCriteria") StatiumClassInfoCriteria statiumClassInfoCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumClassInfo statiumClassInfo);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumClassInfo statiumClassInfo);
}