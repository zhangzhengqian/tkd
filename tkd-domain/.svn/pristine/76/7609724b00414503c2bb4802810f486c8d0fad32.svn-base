package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.PayLog;
import com.lc.zy.ball.domain.oa.po.PayLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* PayLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-31 11:07:31
*/
public interface PayLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(PayLogCriteria payLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(PayLogCriteria payLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(PayLog payLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(PayLog payLog);

    /**
     * 根据条件查询记录集
     */
    List<PayLog> selectByExample(PayLogCriteria payLogCriteria);

    /**
     * 根据主键查询记录
     */
    PayLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("payLog") PayLog payLog, @Param("payLogCriteria") PayLogCriteria payLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("payLog") PayLog payLog, @Param("payLogCriteria") PayLogCriteria payLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(PayLog payLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(PayLog payLog);
}