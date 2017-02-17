package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.PmwInfoimg;
import com.lc.zy.ball.domain.oa.po.PmwInfoimgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* PmwInfoimgMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-23 18:50:05
*/
public interface PmwInfoimgMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(PmwInfoimgCriteria pmwInfoimgCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(PmwInfoimgCriteria pmwInfoimgCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(PmwInfoimg pmwInfoimg);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(PmwInfoimg pmwInfoimg);

    /**
     * 根据条件查询记录集
     */
    List<PmwInfoimg> selectByExample(PmwInfoimgCriteria pmwInfoimgCriteria);

    /**
     * 根据主键查询记录
     */
    PmwInfoimg selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("pmwInfoimg") PmwInfoimg pmwInfoimg, @Param("pmwInfoimgCriteria") PmwInfoimgCriteria pmwInfoimgCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("pmwInfoimg") PmwInfoimg pmwInfoimg, @Param("pmwInfoimgCriteria") PmwInfoimgCriteria pmwInfoimgCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(PmwInfoimg pmwInfoimg);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(PmwInfoimg pmwInfoimg);
}