package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmTurnCardLog;
import com.lc.zy.ball.domain.oa.po.CrmTurnCardLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmTurnCardLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-13 10:04:58
*/
public interface CrmTurnCardLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmTurnCardLogCriteria crmTurnCardLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmTurnCardLogCriteria crmTurnCardLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmTurnCardLog crmTurnCardLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmTurnCardLog crmTurnCardLog);

    /**
     * 根据条件查询记录集
     */
    List<CrmTurnCardLog> selectByExample(CrmTurnCardLogCriteria crmTurnCardLogCriteria);

    /**
     * 根据主键查询记录
     */
    CrmTurnCardLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmTurnCardLog") CrmTurnCardLog crmTurnCardLog, @Param("crmTurnCardLogCriteria") CrmTurnCardLogCriteria crmTurnCardLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmTurnCardLog") CrmTurnCardLog crmTurnCardLog, @Param("crmTurnCardLogCriteria") CrmTurnCardLogCriteria crmTurnCardLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmTurnCardLog crmTurnCardLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmTurnCardLog crmTurnCardLog);
}