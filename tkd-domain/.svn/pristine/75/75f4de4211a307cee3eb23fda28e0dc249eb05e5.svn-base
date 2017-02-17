package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Carousel;
import com.lc.zy.ball.domain.oa.po.CarouselCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* CarouselMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-20 13:53:11
*/
public interface CarouselMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(CarouselCriteria carouselCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(CarouselCriteria carouselCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Carousel carousel);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Carousel carousel);

    /**
     * 根据条件查询记录集
     */
    List<Carousel> selectByExample(CarouselCriteria carouselCriteria);

    /**
     * 根据主键查询记录
     */
    Carousel selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("carousel") Carousel carousel, @Param("carouselCriteria") CarouselCriteria carouselCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("carousel") Carousel carousel, @Param("carouselCriteria") CarouselCriteria carouselCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Carousel carousel);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Carousel carousel);
}