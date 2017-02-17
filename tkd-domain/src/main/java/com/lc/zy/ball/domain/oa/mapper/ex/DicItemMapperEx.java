package com.lc.zy.ball.domain.oa.mapper.ex;

import java.util.List;

import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.DicItemCriteria;

/**
* DicItemMapper扩展类
* 
* @author Wu.Yanhong
* @version v1.0
* @date 2014-10-28 15:41:03
*/
public interface DicItemMapperEx {
  

    /**
     * 根据条件查询记录集
     */
    List<DicItem> selectDicItemByExample(DicItemCriteria dicItemCriteria);
    


    /**
     * 根据条件查询记录集
     */
    List<DicItem> selectDomainObjByExample(DicItemCriteria dicItemCriteria);
    /**
     * 根据字典ID查询字典项里排序字段最大项
     */
   Integer selectDicItemMaxSeqNum(String dicId);

}