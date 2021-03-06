package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.SsoUserAccountLog;
import com.lc.zy.ball.domain.oa.po.SsoUserAccountLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* SsoUserAccountLogMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-08-30 15:02:41
*/
public interface SsoUserAccountLogMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(SsoUserAccountLogCriteria ssoUserAccountLogCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(SsoUserAccountLogCriteria ssoUserAccountLogCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SsoUserAccountLog ssoUserAccountLog);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SsoUserAccountLog ssoUserAccountLog);

    /**
     * 根据条件查询记录集
     */
    List<SsoUserAccountLog> selectByExample(SsoUserAccountLogCriteria ssoUserAccountLogCriteria);

    /**
     * 根据主键查询记录
     */
    SsoUserAccountLog selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("ssoUserAccountLog") SsoUserAccountLog ssoUserAccountLog, @Param("ssoUserAccountLogCriteria") SsoUserAccountLogCriteria ssoUserAccountLogCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("ssoUserAccountLog") SsoUserAccountLog ssoUserAccountLog, @Param("ssoUserAccountLogCriteria") SsoUserAccountLogCriteria ssoUserAccountLogCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SsoUserAccountLog ssoUserAccountLog);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SsoUserAccountLog ssoUserAccountLog);
}