package com.lc.zy.ball.boss.framework.statium.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.domain.oa.mapper.SpacePriceMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumSpacePriceMapper;
import com.lc.zy.ball.domain.oa.po.SpacePrice;
import com.lc.zy.ball.domain.oa.po.SpacePriceCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumPriceTmpl;
import com.lc.zy.ball.domain.oa.po.StatiumSpace;
import com.lc.zy.ball.domain.oa.po.StatiumSpacePrice;
import com.lc.zy.ball.domain.oa.po.StatiumSpacePriceCriteria;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class StatiumSpacePriceService extends AbstractCacheService {

    @Autowired
    private StatiumSpacePriceMapper statiumSpacePriceMapper;

    @Autowired
    private SpacePriceMapper spacePriceMapper;

    private static Logger logger = LoggerFactory.getLogger(StatiumSpacePriceService.class);

    /**
     * 
     * <创建场地价格><功能具体实现>
     *
     * @create：2015年8月12日 下午7:43:17
     * @author： CYY
     * @param list
     * @param spaceId
     * @param sportType
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void create(List<StatiumPriceTmplVo> list, String spaceId, String sportType) throws Exception {
        // 保存价格之前先查询是否有历史记录，存在就删除
        StatiumSpacePriceCriteria statiumSpacePriceCriteria = new StatiumSpacePriceCriteria();
        StatiumSpacePriceCriteria.Criteria cri1 = statiumSpacePriceCriteria.createCriteria();
        cri1.andSpaceIdEqualTo(spaceId);
        this.deleteByExample(statiumSpacePriceCriteria);

        SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
        SpacePriceCriteria.Criteria cri2 = spacePriceCriteria.createCriteria();
        cri2.andSpaceIdEqualTo(spaceId);
        this.deleteBySpacePirceExample(spacePriceCriteria);

        if (CollectionUtils.isNotEmpty(list)) {
            for (StatiumPriceTmplVo vo : list) {
                saveStatiumPrice(vo.getWorkingDays(), 1, spaceId, sportType);
                saveStatiumPrice(vo.getNoWorkingDays(), 0, spaceId, sportType);
            }
        }
    }

    /**
     * 
     * <保存场地价格和场地具体价格><功能具体实现>
     *
     * @create：2015年8月12日 下午5:31:52
     * @author： CYY
     * @param list
     * @param isWorkday 是否工作日
     * @param spaceId 场地id
     * @throws Exception
     */
    public void saveStatiumPrice(List<StatiumPriceTmpl> list, Integer isWorkday, String spaceId, String sportType)
            throws Exception {
        Integer[] prices = new Integer[24];
        //增加成本价
        Integer[] costPrice = new Integer[24];
        SpacePrice sp = null;
        StatiumSpacePrice ssp = null;
        Date now = new Date();
        String userId = SessionUtil.currentUserId();
        String id = "";
        Collections.sort(list, new Comparator<StatiumPriceTmpl>() {
            public int compare(StatiumPriceTmpl o1, StatiumPriceTmpl o2) {
                return o1.getSeq().compareTo(o2.getSeq());
            }
        });
        for (StatiumPriceTmpl tmpl : list) {
            if (StringUtils.isNotEmpty(tmpl.getStartTime()) && StringUtils.isNotEmpty(tmpl.getEndTime())) {
                Integer start = Integer.parseInt(tmpl.getStartTime());
                Integer end = Integer.parseInt(tmpl.getEndTime());
                for (int i = start; i < end; i++) {
                    prices[i] = tmpl.getSignPrice()*100;
                    costPrice[i] = tmpl.getCostPrice()*100;
                }
            } else {
                for (int i = 0; i < prices.length; i++) {
                    if (prices[i] == null) {
//                        prices[i] = tmpl.getSignPrice();
                        prices[i] = tmpl.getSignPrice()*100;  //单位：分

                    }
                }
                
                //其他时间段的成本价
                for(int j =0; j < costPrice.length; j++){
                	if(costPrice[j] == null){
                		costPrice[j] = tmpl.getCostPrice()*100;
                	}
                }
            }
            ssp = new StatiumSpacePrice();
            BeanUtils.copyProperties(tmpl, ssp);
            id = UUID.get();
            ssp.setId(id);
            ssp.setCt(now);
            ssp.setEt(now);
            ssp.setCb(userId);
            ssp.setEb(userId);
            ssp.setSpaceId(spaceId);
            ssp.setStatus(1);
            ssp.setIsWorkday(isWorkday);
            ssp.setSpacePrice(ssp.getSignPrice()); //场地价格  
            
            this.insertSelective(ssp, id);
        }
        sp = new SpacePrice();
        id = UUID.get();
        sp.setId(id);
        sp.setCt(now);
        sp.setEt(now);
        sp.setCb(userId);
        sp.setEb(userId);
        sp.setIsWorkday(isWorkday);
        sp.setSportType(sportType);
        sp.setSpaceId(spaceId);
        sp.setPrice(StringUtils.join(prices, ","));
        sp.setCostPrice(StringUtils.join(costPrice,","));
        
        StatiumSpace space = super.selectByPrimaryKey(StatiumSpace.class, spaceId);
        logger.debug("space="+space.toString());
        sp.setStatiumId(space.getStatiumId());
        this.insertSelective(sp, id);
    }

    /**
     * 
     * <删除场地价格><功能具体实现>
     *
     * @create：2015年8月12日 下午5:47:45
     * @author： CYY
     * @param statiumSpacePriceCriteria
     * @return
     */
    public int deleteByExample(StatiumSpacePriceCriteria statiumSpacePriceCriteria) {
        try {
            return statiumSpacePriceMapper.deleteByExample(statiumSpacePriceCriteria);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <删除场地具体价格><功能具体实现>
     *
     * @create：2015年8月12日 下午5:47:45
     * @author： CYY
     * @param spacePriceCriteria
     * @return
     */
    public int deleteBySpacePirceExample(SpacePriceCriteria spacePriceCriteria) {
        try {
            return spacePriceMapper.deleteByExample(spacePriceCriteria);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * <获取场地价格><功能具体实现>
     *
     * @create：2015年8月12日 下午8:12:45
     * @author： CYY
     * @param statiumSpacePriceCriteria
     * @return
     */
    public List<StatiumSpacePrice> selectByExample(StatiumSpacePriceCriteria statiumSpacePriceCriteria) {
        return statiumSpacePriceMapper.selectByExample(statiumSpacePriceCriteria);
    }
}
