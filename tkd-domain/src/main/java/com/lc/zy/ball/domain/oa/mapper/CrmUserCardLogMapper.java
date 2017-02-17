package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmUserCardLog;
import com.lc.zy.ball.domain.oa.po.CrmUserCardLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmUserCardLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-14 17:13:45
*/
public interface CrmUserCardLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmUserCardLogCriteria crmUserCardLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmUserCardLogCriteria crmUserCardLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmUserCardLog crmUserCardLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmUserCardLog crmUserCardLog);

    /**
     * 根据条件查询记录集
     */
    List<CrmUserCardLog> selectByExample(CrmUserCardLogCriteria crmUserCardLogCriteria);

    /**
     * 根据主键查询记录
     */
    CrmUserCardLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmUserCardLog") CrmUserCardLog crmUserCardLog, @Param("crmUserCardLogCriteria") CrmUserCardLogCriteria crmUserCardLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmUserCardLog") CrmUserCardLog crmUserCardLog, @Param("crmUserCardLogCriteria") CrmUserCardLogCriteria crmUserCardLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmUserCardLog crmUserCardLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmUserCardLog crmUserCardLog);
}