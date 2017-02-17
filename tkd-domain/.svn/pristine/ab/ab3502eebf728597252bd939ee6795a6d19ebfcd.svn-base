package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.RoleFuncCriteria;
import com.lc.zy.ball.domain.oa.po.RoleFuncKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* RoleFuncMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface RoleFuncMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(RoleFuncCriteria roleFuncCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(RoleFuncCriteria roleFuncCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(RoleFuncKey key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(RoleFuncKey roleFuncKey);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(RoleFuncKey roleFuncKey);

    /**
     * 根据条件查询记录集
     */
    List<RoleFuncKey> selectByExample(RoleFuncCriteria roleFuncCriteria);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("roleFuncKey") RoleFuncKey roleFuncKey, @Param("roleFuncCriteria") RoleFuncCriteria roleFuncCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("roleFuncKey") RoleFuncKey roleFuncKey, @Param("roleFuncCriteria") RoleFuncCriteria roleFuncCriteria);
}