package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.OrderBillItem;
import com.lc.zy.ball.domain.oa.po.OrderBillItemCriteria;
import com.lc.zy.ball.domain.oa.po.OrderBillItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* OrderBillItemMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 17:43:14
*/
public interface OrderBillItemMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(OrderBillItemCriteria orderBillItemCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(OrderBillItemCriteria orderBillItemCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(OrderBillItemKey key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderBillItem orderBillItem);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderBillItem orderBillItem);

    /**
     * 根据条件查询记录集
     */
    List<OrderBillItem> selectByExample(OrderBillItemCriteria orderBillItemCriteria);

    /**
     * 根据主键查询记录
     */
    OrderBillItem selectByPrimaryKey(OrderBillItemKey key);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("orderBillItem") OrderBillItem orderBillItem, @Param("orderBillItemCriteria") OrderBillItemCriteria orderBillItemCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("orderBillItem") OrderBillItem orderBillItem, @Param("orderBillItemCriteria") OrderBillItemCriteria orderBillItemCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderBillItem orderBillItem);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderBillItem orderBillItem);
}