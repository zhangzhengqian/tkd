package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Dic;
import com.lc.zy.ball.domain.oa.po.DicCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* DicMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface DicMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(DicCriteria dicCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(DicCriteria dicCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String dicId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Dic dic);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Dic dic);

    /**
     * 根据条件查询记录集
     */
    List<Dic> selectByExample(DicCriteria dicCriteria);

    /**
     * 根据主键查询记录
     */
    Dic selectByPrimaryKey(String dicId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("dic") Dic dic, @Param("dicCriteria") DicCriteria dicCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("dic") Dic dic, @Param("dicCriteria") DicCriteria dicCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Dic dic);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Dic dic);
}