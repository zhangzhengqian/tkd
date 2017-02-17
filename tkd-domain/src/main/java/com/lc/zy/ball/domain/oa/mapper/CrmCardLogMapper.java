package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmCardLog;
import com.lc.zy.ball.domain.oa.po.CrmCardLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmCardLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-02 15:05:11
*/
public interface CrmCardLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmCardLogCriteria crmCardLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmCardLogCriteria crmCardLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmCardLog crmCardLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmCardLog crmCardLog);

    /**
     * 根据条件查询记录集
     */
    List<CrmCardLog> selectByExample(CrmCardLogCriteria crmCardLogCriteria);

    /**
     * 根据主键查询记录
     */
    CrmCardLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmCardLog") CrmCardLog crmCardLog, @Param("crmCardLogCriteria") CrmCardLogCriteria crmCardLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmCardLog") CrmCardLog crmCardLog, @Param("crmCardLogCriteria") CrmCardLogCriteria crmCardLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmCardLog crmCardLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmCardLog crmCardLog);
}