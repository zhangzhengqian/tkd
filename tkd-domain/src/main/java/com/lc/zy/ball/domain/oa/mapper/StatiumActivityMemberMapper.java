package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.StatiumActivityMember;
import com.lc.zy.ball.domain.oa.po.StatiumActivityMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* StatiumActivityMemberMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 12:01:12
*/
public interface StatiumActivityMemberMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(StatiumActivityMemberCriteria statiumActivityMemberCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(StatiumActivityMemberCriteria statiumActivityMemberCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(StatiumActivityMember statiumActivityMember);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(StatiumActivityMember statiumActivityMember);

    /**
     * 根据条件查询记录集
     */
    List<StatiumActivityMember> selectByExample(StatiumActivityMemberCriteria statiumActivityMemberCriteria);

    /**
     * 根据主键查询记录
     */
    StatiumActivityMember selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("statiumActivityMember") StatiumActivityMember statiumActivityMember, @Param("statiumActivityMemberCriteria") StatiumActivityMemberCriteria statiumActivityMemberCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("statiumActivityMember") StatiumActivityMember statiumActivityMember, @Param("statiumActivityMemberCriteria") StatiumActivityMemberCriteria statiumActivityMemberCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(StatiumActivityMember statiumActivityMember);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(StatiumActivityMember statiumActivityMember);
}