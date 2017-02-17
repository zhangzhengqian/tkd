package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.OpLogging;
import com.lc.zy.ball.domain.oa.po.OpLoggingCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* OpLoggingMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface OpLoggingMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(OpLoggingCriteria opLoggingCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(OpLoggingCriteria opLoggingCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String logId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OpLogging opLogging);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OpLogging opLogging);

    /**
     * 根据条件查询记录集
     */
    List<OpLogging> selectByExample(OpLoggingCriteria opLoggingCriteria);

    /**
     * 根据主键查询记录
     */
    OpLogging selectByPrimaryKey(String logId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("opLogging") OpLogging opLogging, @Param("opLoggingCriteria") OpLoggingCriteria opLoggingCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("opLogging") OpLogging opLogging, @Param("opLoggingCriteria") OpLoggingCriteria opLoggingCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OpLogging opLogging);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OpLogging opLogging);
}