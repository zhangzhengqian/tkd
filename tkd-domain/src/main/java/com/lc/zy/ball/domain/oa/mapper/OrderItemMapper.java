package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* OrderItemMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-18 16:13:54
*/
public interface OrderItemMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(OrderItemCriteria orderItemCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(OrderItemCriteria orderItemCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderItem orderItem);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderItem orderItem);

    /**
     * 根据条件查询记录集
     */
    List<OrderItem> selectByExample(OrderItemCriteria orderItemCriteria);

    /**
     * 根据主键查询记录
     */
    OrderItem selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("orderItem") OrderItem orderItem, @Param("orderItemCriteria") OrderItemCriteria orderItemCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("orderItem") OrderItem orderItem, @Param("orderItemCriteria") OrderItemCriteria orderItemCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderItem orderItem);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderItem orderItem);
}