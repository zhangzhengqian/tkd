package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumClassMember;
import com.lc.zy.ball.domain.oa.po.StatiumClassMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumClassMemberMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public interface StatiumClassMemberMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumClassMemberCriteria statiumClassMemberCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumClassMemberCriteria statiumClassMemberCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumClassMember statiumClassMember);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumClassMember statiumClassMember);

    /**
     * 根据条件查询记录集
     */
    List<StatiumClassMember> selectByExample(StatiumClassMemberCriteria statiumClassMemberCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumClassMember selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumClassMember") StatiumClassMember statiumClassMember, @Param("statiumClassMemberCriteria") StatiumClassMemberCriteria statiumClassMemberCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumClassMember") StatiumClassMember statiumClassMember, @Param("statiumClassMemberCriteria") StatiumClassMemberCriteria statiumClassMemberCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumClassMember statiumClassMember);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumClassMember statiumClassMember);
}