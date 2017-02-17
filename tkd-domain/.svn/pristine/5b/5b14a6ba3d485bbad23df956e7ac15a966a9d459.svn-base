package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.DicItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* DicItemMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface DicItemMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(DicItemCriteria dicItemCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(DicItemCriteria dicItemCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String itemId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(DicItem dicItem);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(DicItem dicItem);

    /**
     * 根据条件查询记录集
     */
    List<DicItem> selectByExample(DicItemCriteria dicItemCriteria);

    /**
     * 根据主键查询记录
     */
    DicItem selectByPrimaryKey(String itemId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("dicItem") DicItem dicItem, @Param("dicItemCriteria") DicItemCriteria dicItemCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("dicItem") DicItem dicItem, @Param("dicItemCriteria") DicItemCriteria dicItemCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(DicItem dicItem);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(DicItem dicItem);
}