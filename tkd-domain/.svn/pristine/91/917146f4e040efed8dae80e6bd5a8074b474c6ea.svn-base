package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.videoGroup;
import com.lc.zy.ball.domain.oa.po.videoGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* videoGroupMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-22 15:20:02
*/
public interface videoGroupMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(videoGroupCriteria videoGroupCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(videoGroupCriteria videoGroupCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(videoGroup videoGroup);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(videoGroup videoGroup);

    /**
     * 根据条件查询记录集
     */
    List<videoGroup> selectByExample(videoGroupCriteria videoGroupCriteria);

    /**
     * 根据主键查询记录
     */
    videoGroup selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("videoGroup") videoGroup videoGroup, @Param("videoGroupCriteria") videoGroupCriteria videoGroupCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("videoGroup") videoGroup videoGroup, @Param("videoGroupCriteria") videoGroupCriteria videoGroupCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(videoGroup videoGroup);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(videoGroup videoGroup);
}