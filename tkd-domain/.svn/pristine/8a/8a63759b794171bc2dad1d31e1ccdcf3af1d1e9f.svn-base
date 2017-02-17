package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmFunction;
import com.lc.zy.ball.domain.oa.po.CrmFunctionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmFunctionMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-01 16:47:31
*/
public interface CrmFunctionMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmFunctionCriteria crmFunctionCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmFunctionCriteria crmFunctionCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String funcId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmFunction crmFunction);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmFunction crmFunction);

    /**
     * 根据条件查询记录集
     */
    List<CrmFunction> selectByExample(CrmFunctionCriteria crmFunctionCriteria);

    /**
     * 根据主键查询记录
     */
    CrmFunction selectByPrimaryKey(String funcId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmFunction") CrmFunction crmFunction, @Param("crmFunctionCriteria") CrmFunctionCriteria crmFunctionCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmFunction") CrmFunction crmFunction, @Param("crmFunctionCriteria") CrmFunctionCriteria crmFunctionCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmFunction crmFunction);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmFunction crmFunction);
}