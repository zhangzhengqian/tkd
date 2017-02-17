package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.Message;
import com.lc.zy.ball.domain.oa.po.MessageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* MessageMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public interface MessageMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(MessageCriteria messageCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(MessageCriteria messageCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Message message);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Message message);

    /**
     * 根据条件查询记录集
     */
    List<Message> selectByExample(MessageCriteria messageCriteria);

    /**
     * 根据主键查询记录
     */
    Message selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("message") Message message, @Param("messageCriteria") MessageCriteria messageCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("message") Message message, @Param("messageCriteria") MessageCriteria messageCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Message message);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Message message);
}