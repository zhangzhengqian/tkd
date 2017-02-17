package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.CrmUser;
import com.lc.zy.ball.domain.oa.po.CrmUserCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* CrmUserMapper
* 
* @author liangc [cc14514@icloud.com]
* @version v1.0
* @copy pet
* @date 2015-09-18 17:04:57
*/
public interface CrmUserMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CrmUserCriteria crmUserCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CrmUserCriteria crmUserCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String userId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CrmUser crmUser);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CrmUser crmUser);

    /**
     * 根据条件查询记录集
     */
    List<CrmUser> selectByExample(CrmUserCriteria crmUserCriteria);

    /**
     * 根据主键查询记录
     */
    CrmUser selectByPrimaryKey(String userId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("crmUser") CrmUser crmUser, @Param("crmUserCriteria") CrmUserCriteria crmUserCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("crmUser") CrmUser crmUser, @Param("crmUserCriteria") CrmUserCriteria crmUserCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CrmUser crmUser);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CrmUser crmUser);
}