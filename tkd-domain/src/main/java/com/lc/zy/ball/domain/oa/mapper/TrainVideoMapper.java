package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.TrainVideo;
import com.lc.zy.ball.domain.oa.po.TrainVideoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* TrainVideoMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2017-01-01 09:10:37
*/
public interface TrainVideoMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(TrainVideoCriteria trainVideoCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(TrainVideoCriteria trainVideoCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TrainVideo trainVideo);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TrainVideo trainVideo);

    /**
     * 根据条件查询记录集
     */
    List<TrainVideo> selectByExample(TrainVideoCriteria trainVideoCriteria);

    /**
     * 根据主键查询记录
     */
    TrainVideo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("trainVideo") TrainVideo trainVideo, @Param("trainVideoCriteria") TrainVideoCriteria trainVideoCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("trainVideo") TrainVideo trainVideo, @Param("trainVideoCriteria") TrainVideoCriteria trainVideoCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TrainVideo trainVideo);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TrainVideo trainVideo);
}