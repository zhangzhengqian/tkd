package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.News;
import com.lc.zy.ball.domain.oa.po.NewsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* NewsMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-05-05 15:59:55
*/
public interface NewsMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(NewsCriteria newsCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(NewsCriteria newsCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(News news);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(News news);

    /**
     * 根据条件查询记录集
     */
    List<News> selectByExample(NewsCriteria newsCriteria);

    /**
     * 根据主键查询记录
     */
    News selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("news") News news, @Param("newsCriteria") NewsCriteria newsCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("news") News news, @Param("newsCriteria") NewsCriteria newsCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(News news);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(News news);
}