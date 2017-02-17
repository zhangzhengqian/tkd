package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmUserCardAccount;
import com.lc.zy.ball.domain.oa.po.CrmUserCardAccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CrmUserCardAccountMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-05 14:53:27
*/
public interface CrmUserCardAccountMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmUserCardAccountCriteria crmUserCardAccountCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmUserCardAccountCriteria crmUserCardAccountCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmUserCardAccount crmUserCardAccount);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmUserCardAccount crmUserCardAccount);

    /**
     * 根据条件查询记录集
     */
    List<CrmUserCardAccount> selectByExample(CrmUserCardAccountCriteria crmUserCardAccountCriteria);

    /**
     * 根据主键查询记录
     */
    CrmUserCardAccount selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmUserCardAccount") CrmUserCardAccount crmUserCardAccount, @Param("crmUserCardAccountCriteria") CrmUserCardAccountCriteria crmUserCardAccountCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmUserCardAccount") CrmUserCardAccount crmUserCardAccount, @Param("crmUserCardAccountCriteria") CrmUserCardAccountCriteria crmUserCardAccountCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmUserCardAccount crmUserCardAccount);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmUserCardAccount crmUserCardAccount);
}