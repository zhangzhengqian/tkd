package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.BillTerm;
import com.lc.zy.ball.domain.oa.po.BillTermCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* BillTermMapper
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-18 14:06:21
*/
public interface BillTermMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(BillTermCriteria billTermCriteria);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(BillTermCriteria billTermCriteria);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(BillTerm billTerm);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(BillTerm billTerm);

    /**
     * 根据条件查询记录集
     */
    List<BillTerm> selectByExample(BillTermCriteria billTermCriteria);

    /**
     * 根据主键查询记录
     */
    BillTerm selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("billTerm") BillTerm billTerm, @Param("billTermCriteria") BillTermCriteria billTermCriteria);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("billTerm") BillTerm billTerm, @Param("billTermCriteria") BillTermCriteria billTermCriteria);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(BillTerm billTerm);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(BillTerm billTerm);
}