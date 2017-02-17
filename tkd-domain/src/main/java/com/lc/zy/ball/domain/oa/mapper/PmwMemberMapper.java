package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.PmwMember;
import com.lc.zy.ball.domain.oa.po.PmwMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* PmwMemberMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-23 19:01:14
*/
public interface PmwMemberMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(PmwMemberCriteria pmwMemberCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(PmwMemberCriteria pmwMemberCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(PmwMember pmwMember);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(PmwMember pmwMember);

    /**
     * 根据条件查询记录集
     */
    List<PmwMember> selectByExample(PmwMemberCriteria pmwMemberCriteria);

    /**
     * 根据主键查询记录
     */
    PmwMember selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("pmwMember") PmwMember pmwMember, @Param("pmwMemberCriteria") PmwMemberCriteria pmwMemberCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("pmwMember") PmwMember pmwMember, @Param("pmwMemberCriteria") PmwMemberCriteria pmwMemberCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(PmwMember pmwMember);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(PmwMember pmwMember);
}