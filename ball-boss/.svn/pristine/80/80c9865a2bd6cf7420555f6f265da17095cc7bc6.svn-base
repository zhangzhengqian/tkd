package com.lc.zy.ball.boss.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.domain.oa.mapper.DicItemMapper;
import com.lc.zy.ball.domain.oa.mapper.DicMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.DicItemMapperEx;
import com.lc.zy.ball.domain.oa.po.Dic;
import com.lc.zy.ball.domain.oa.po.DicCriteria;
import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.DicItemCriteria;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly = true)
public class DicService {

    @Autowired
    private DicMapper dicMapper;

    @Autowired
    private DicItemMapper dicItemMapper;

    @Autowired
    private DicItemMapperEx dicItemMapperEx;

    @Autowired
    private DicProvider dicProvider;

    @Transactional(readOnly = false)
    public void saveDic(Dic dic) throws Exception {
        if (StringUtils.isBlank(dic.getDicId())) {
            String pk = UUID.get();
            dic.setDicId(pk);
            dicMapper.insertSelective(dic);
        } else {
            dicMapper.updateByPrimaryKeySelective(dic);
        }

        dicProvider.reload();
    }

    public Dic getDic(String dicId) throws Exception {
        return dicMapper.selectByPrimaryKey(dicId);
    }

    public Dic findDicByName(String dicName) {
        DicCriteria dc = new DicCriteria();
        DicCriteria.Criteria cri = dc.createCriteria();
        cri.andDicNameEqualTo(dicName);
        List<Dic> list = dicMapper.selectByExample(dc);
        return list == null || list.size() == 0 ? null : list.get(0);
    }

    public Dic findDicByCode(String dicCode) {
        DicCriteria dc = new DicCriteria();
        DicCriteria.Criteria cri = dc.createCriteria();
        cri.andDicCodeEqualTo(dicCode);
        List<Dic> list = dicMapper.selectByExample(dc);
        return list.size() == 0 ? null : list.get(0);
    }

    @Transactional(readOnly = false)
    public void deleteDic(String dicId) throws Exception {
        List<DicItem> items = findItemByDicId(dicId);
        for (DicItem item : items) {
            dicItemMapper.deleteByPrimaryKey(item.getItemId());
        }
        dicMapper.deleteByPrimaryKey(dicId);

        dicProvider.reload();
    }

    public List<Dic> findDicByPId(String pId) {
        pId = pId == null ? "0" : pId;

        DicCriteria dc = new DicCriteria();
        DicCriteria.Criteria cri = dc.createCriteria();
        cri.andParentIdEqualTo(pId);
        dc.setOrderByClause("dic_name, seq_num");

        return dicMapper.selectByExample(dc);
    }

    /**
     * 根据codeType查询DICITEM
     * 
     * @ClassName:DicService.java
     * @Author:Li.Xiaochao
     * @Description:
     * @CreateDate:2014年10月30日
     */
    public List<DicItem> findItemByCodeType(String codeType) {
        String dicId = findDicByCode(codeType).getDicId();
        return findItemByDicId(dicId);
    }

    public List<DicItem> findItemByDicId(String dicId) {
        DicItemCriteria dc = new DicItemCriteria();
        DicItemCriteria.Criteria cri = dc.createCriteria();
        cri.andDicIdEqualTo(dicId);
        dc.setOrderByClause("seq_num asc");

        return dicItemMapper.selectByExample(dc);
    }

    public List<DicItem> findAllItem() {
        DicItemCriteria dc = new DicItemCriteria();
        dc.setOrderByClause("seq_num asc");
        return dicItemMapper.selectByExample(dc);
    }

    /**
     * 
     * @ClassName:DicService.java
     * @Author:Li.Xiaochao
     * @Description:
     * @CreateDate:2014年10月29日
     */
    public List<DicItem> findItemByType(String custType, String custAgent) {
        String dicId = findDicByCode(custType).getDicId();
        DicItemCriteria dc1 = new DicItemCriteria();
        DicItemCriteria.Criteria cri1 = dc1.createCriteria();
        cri1.andDicIdEqualTos(dicId);
        cri1.andItemCodeNotEqualTo(custAgent);
        dc1.setOrderByClause("item_id asc");

        return dicItemMapperEx.selectDicItemByExample(dc1);
    }

    public DicItem findItemByNameAndDicId(String itemName, String dicId) {
        DicItemCriteria dc = new DicItemCriteria();
        DicItemCriteria.Criteria cri = dc.createCriteria();
        cri.andDicIdEqualTo(dicId).andItemNameEqualTo(itemName);

        List<DicItem> list = dicItemMapper.selectByExample(dc);
        return list.size() == 0 ? null : list.get(0);
    }

    @Transactional(readOnly = false)
    public void saveDicItem(DicItem newItem) throws Exception {
        if (StringUtils.isBlank(newItem.getItemId())) {
            String pk = UUID.get();
            newItem.setItemId(pk);
            if (null == newItem.getSeqNum()) {
                // 给字典项保存时排序字段设置值
                Integer maxSeqNum = dicItemMapperEx.selectDicItemMaxSeqNum(newItem.getDicId());
                if (null == maxSeqNum) {
                    newItem.setSeqNum(10);
                } else {
                    newItem.setSeqNum((maxSeqNum + 10));
                }
            }
            dicItemMapper.insertSelective(newItem);
        } else {
            dicItemMapper.updateByPrimaryKeySelective(newItem);
        }

        dicProvider.reload();
    }

    @Transactional(readOnly = false)
    public void deleteDicItem(String itemId) throws Exception {
        dicItemMapper.deleteByPrimaryKey(itemId);

        dicProvider.reload();
    }

    /**
     * 根据code取name
     * 
     * @MethodName findItemByCode
     * @Description
     * @Author Li.XiaoChao
     * @param itemCode
     * @return
     * @CreatDate 2014年11月4日
     */
    public DicItem findItemByCode(String itemCode) {
        DicItemCriteria dc = new DicItemCriteria();
        DicItemCriteria.Criteria cri = dc.createCriteria();
        cri.andItemCodeEqualTo(itemCode);

        List<DicItem> list = dicItemMapper.selectByExample(dc);
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * 根据字典ID和字典项code检查字典项code在同一个字典里是否有重复
     * 
     * @MethodName checKDicItemCode
     * @Description
     * @Author Li.XiaoChao
     * @param itemCode
     * @return
     * @CreatDate 2014年11月4日
     */
    public DicItem checKDicItemCode(String itemCode, String dicId) {
        DicItemCriteria dc = new DicItemCriteria();
        DicItemCriteria.Criteria cri = dc.createCriteria();
        cri.andDicIdEqualTo(dicId).andItemCodeEqualTo(itemCode);
        List<DicItem> list = dicItemMapper.selectByExample(dc);
        return list.size() == 0 ? null : list.get(0);
    }

    public DicItem getItem(String itemId) throws Exception {
        return dicItemMapper.selectByPrimaryKey(itemId);
    }

    public List<Dic> findAll() {
        return dicMapper.selectByExample(null);
    }

    /**
     * 通过code获取Name
     * 
     * @param itemCode
     * @return
     */
    public String getNameByCode(String itemCode) {
        DicItemCriteria dc = new DicItemCriteria();
        DicItemCriteria.Criteria cri = dc.createCriteria();
        cri.andItemCodeEqualTo(itemCode);

        List<DicItem> list = dicItemMapper.selectByExample(dc);

        if (null == list || list.size() == 0) {
            return "";
        }

        return list.get(0).getItemName();
    }

}
