package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.OrderLog;
import com.lc.zy.ball.domain.oa.po.OrderLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* OrderLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public interface OrderLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(OrderLogCriteria orderLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(OrderLogCriteria orderLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderLog orderLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderLog orderLog);

    /**
     * 根据条件查询记录集
     */
    List<OrderLog> selectByExample(OrderLogCriteria orderLogCriteria);

    /**
     * 根据主键查询记录
     */
    OrderLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("orderLog") OrderLog orderLog, @Param("orderLogCriteria") OrderLogCriteria orderLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("orderLog") OrderLog orderLog, @Param("orderLogCriteria") OrderLogCriteria orderLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderLog orderLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderLog orderLog);
}