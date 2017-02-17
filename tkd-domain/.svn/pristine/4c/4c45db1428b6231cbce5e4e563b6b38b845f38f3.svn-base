package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.SsoUserAccount;
import com.lc.zy.ball.domain.oa.po.SsoUserAccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* SsoUserAccountMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-08-30 15:02:41
*/
public interface SsoUserAccountMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(SsoUserAccountCriteria ssoUserAccountCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(SsoUserAccountCriteria ssoUserAccountCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String userId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SsoUserAccount ssoUserAccount);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SsoUserAccount ssoUserAccount);

    /**
     * 根据条件查询记录集
     */
    List<SsoUserAccount> selectByExample(SsoUserAccountCriteria ssoUserAccountCriteria);

    /**
     * 根据主键查询记录
     */
    SsoUserAccount selectByPrimaryKey(String userId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("ssoUserAccount") SsoUserAccount ssoUserAccount, @Param("ssoUserAccountCriteria") SsoUserAccountCriteria ssoUserAccountCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("ssoUserAccount") SsoUserAccount ssoUserAccount, @Param("ssoUserAccountCriteria") SsoUserAccountCriteria ssoUserAccountCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SsoUserAccount ssoUserAccount);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SsoUserAccount ssoUserAccount);
}