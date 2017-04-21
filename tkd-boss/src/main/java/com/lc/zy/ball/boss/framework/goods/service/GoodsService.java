package com.lc.zy.ball.boss.framework.goods.service;



import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.CrmMallActivityGoodsMapper;
import com.lc.zy.ball.domain.oa.mapper.CrmMallGoodsMapper;
import com.lc.zy.ball.domain.oa.po.CrmMallActivityGoods;
import com.lc.zy.ball.domain.oa.po.CrmMallActivityGoodsCriteria;
import com.lc.zy.ball.domain.oa.po.CrmMallGoods;
import com.lc.zy.ball.domain.oa.po.CrmMallGoodsCriteria;
import com.lc.zy.common.util.DateUtil;

@Service
@Transactional(readOnly = true)
public class GoodsService extends AbstractCacheService {

    private static Logger logger = LoggerFactory.getLogger(GoodsService.class);
    
    @Autowired
    private CrmMallActivityGoodsMapper activityGoodsMapper;
    
    @Autowired
    private CrmMallGoodsMapper goodsMapper;
    
    public List<CrmMallGoods> list() throws Exception{
    	CrmMallGoodsCriteria criteria = new CrmMallGoodsCriteria();
    	CrmMallGoodsCriteria.Criteria cri = criteria.createCriteria();
    	cri.andStatusNotEqualTo(2);
    	return goodsMapper.selectByExample(criteria);
    }

	public boolean deletable(String id) {
		String now = DateUtil.nowDateString();
		CrmMallActivityGoodsCriteria criteria = new CrmMallActivityGoodsCriteria();
		CrmMallActivityGoodsCriteria.Criteria cri = criteria.createCriteria();
		cri.andGoogIdEqualTo(id);
		cri.andStartTimeLessThanOrEqualTo(now);
		cri.andEndTimeGreaterThanOrEqualTo(now);
		cri.andDeleteFlagEqualTo(0);
		List<CrmMallActivityGoods> activityGoods = activityGoodsMapper.selectByExample(criteria);
		if(CollectionUtils.isNotEmpty(activityGoods)){
			return false;
		}else{
			return true;
		}
		
	}

}
