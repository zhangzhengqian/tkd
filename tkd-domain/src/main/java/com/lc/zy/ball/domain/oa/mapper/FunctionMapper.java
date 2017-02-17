package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Function;
import com.lc.zy.ball.domain.oa.po.FunctionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* FunctionMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface FunctionMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(FunctionCriteria functionCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(FunctionCriteria functionCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String funcId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Function function);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Function function);

    /**
     * 根据条件查询记录集
     */
    List<Function> selectByExample(FunctionCriteria functionCriteria);

    /**
     * 根据主键查询记录
     */
    Function selectByPrimaryKey(String funcId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("function") Function function, @Param("functionCriteria") FunctionCriteria functionCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("function") Function function, @Param("functionCriteria") FunctionCriteria functionCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Function function);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Function function);
}