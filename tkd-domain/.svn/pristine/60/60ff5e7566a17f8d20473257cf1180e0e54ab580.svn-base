package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.EmsgServer;
import com.lc.zy.ball.domain.oa.po.EmsgServerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* EmsgServerMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-16 17:30:32
*/
public interface EmsgServerMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(EmsgServerCriteria emsgServerCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(EmsgServerCriteria emsgServerCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(EmsgServer emsgServer);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(EmsgServer emsgServer);

    /**
     * 根据条件查询记录集
     */
    List<EmsgServer> selectByExample(EmsgServerCriteria emsgServerCriteria);

    /**
     * 根据主键查询记录
     */
    EmsgServer selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("emsgServer") EmsgServer emsgServer, @Param("emsgServerCriteria") EmsgServerCriteria emsgServerCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("emsgServer") EmsgServer emsgServer, @Param("emsgServerCriteria") EmsgServerCriteria emsgServerCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(EmsgServer emsgServer);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(EmsgServer emsgServer);
}