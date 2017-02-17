package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.UserRoleCriteria;
import com.lc.zy.ball.domain.oa.po.UserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* UserRoleMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface UserRoleMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(UserRoleCriteria userRoleCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(UserRoleCriteria userRoleCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(UserRoleKey key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserRoleKey userRoleKey);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UserRoleKey userRoleKey);

    /**
     * 根据条件查询记录集
     */
    List<UserRoleKey> selectByExample(UserRoleCriteria userRoleCriteria);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("userRoleKey") UserRoleKey userRoleKey, @Param("userRoleCriteria") UserRoleCriteria userRoleCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("userRoleKey") UserRoleKey userRoleKey, @Param("userRoleCriteria") UserRoleCriteria userRoleCriteria);
}