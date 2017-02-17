package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmCard;
import com.lc.zy.ball.domain.oa.po.CrmCardCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmCardMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-17 14:33:14
*/
public interface CrmCardMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmCardCriteria crmCardCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmCardCriteria crmCardCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmCard crmCard);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmCard crmCard);

    /**
     * 根据条件查询记录集
     */
    List<CrmCard> selectByExample(CrmCardCriteria crmCardCriteria);

    /**
     * 根据主键查询记录
     */
    CrmCard selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmCard") CrmCard crmCard, @Param("crmCardCriteria") CrmCardCriteria crmCardCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmCard") CrmCard crmCard, @Param("crmCardCriteria") CrmCardCriteria crmCardCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmCard crmCard);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmCard crmCard);
}