package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumInfosMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-10-14 11:31:29
*/
public interface StatiumInfosMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumInfosCriteria statiumInfosCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumInfosCriteria statiumInfosCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumInfos statiumInfos);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumInfos statiumInfos);

    /**
     * 根据条件查询记录集
     */
    List<StatiumInfos> selectByExample(StatiumInfosCriteria statiumInfosCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumInfos selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumInfos") StatiumInfos statiumInfos, @Param("statiumInfosCriteria") StatiumInfosCriteria statiumInfosCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumInfos") StatiumInfos statiumInfos, @Param("statiumInfosCriteria") StatiumInfosCriteria statiumInfosCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumInfos statiumInfos);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumInfos statiumInfos);
}