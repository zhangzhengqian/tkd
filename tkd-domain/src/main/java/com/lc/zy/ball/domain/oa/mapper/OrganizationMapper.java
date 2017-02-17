package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Organization;
import com.lc.zy.ball.domain.oa.po.OrganizationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* OrganizationMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface OrganizationMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(OrganizationCriteria organizationCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(OrganizationCriteria organizationCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Organization organization);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Organization organization);

    /**
     * 根据条件查询记录集
     */
    List<Organization> selectByExample(OrganizationCriteria organizationCriteria);

    /**
     * 根据主键查询记录
     */
    Organization selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("organization") Organization organization, @Param("organizationCriteria") OrganizationCriteria organizationCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("organization") Organization organization, @Param("organizationCriteria") OrganizationCriteria organizationCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Organization organization);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Organization organization);
}