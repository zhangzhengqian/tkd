package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmUserFunc;
import com.lc.zy.ball.domain.oa.po.CrmUserFuncCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmUserFuncMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-01 16:47:31
*/
public interface CrmUserFuncMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmUserFuncCriteria crmUserFuncCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmUserFuncCriteria crmUserFuncCriteria);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmUserFunc crmUserFunc);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmUserFunc crmUserFunc);

    /**
     * 根据条件查询记录集
     */
    List<CrmUserFunc> selectByExample(CrmUserFuncCriteria crmUserFuncCriteria);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmUserFunc") CrmUserFunc crmUserFunc, @Param("crmUserFuncCriteria") CrmUserFuncCriteria crmUserFuncCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmUserFunc") CrmUserFunc crmUserFunc, @Param("crmUserFuncCriteria") CrmUserFuncCriteria crmUserFuncCriteria);
}