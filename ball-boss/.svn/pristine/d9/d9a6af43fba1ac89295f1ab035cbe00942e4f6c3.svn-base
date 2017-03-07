package com.lc.zy.ball.boss.framework.website.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.WebsiteSeoMapper;
import com.lc.zy.ball.domain.oa.po.WebsiteSeo;
import com.lc.zy.ball.domain.oa.po.WebsiteSeoCriteria;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.wxap.util.MD5Util;

@Service
@Transactional(readOnly = true)
public class SEOService extends AbstractCacheService {
	private static Logger logger = LoggerFactory.getLogger(SEOService.class);

	@Autowired
	private WebsiteSeoMapper websiteSeoMapper;
	
	@Autowired
	private RedisPool redisPool = null;
	
	/**
	 * 
	 * <列表><功能具体实现>
	 * @param pageable
	 * @param searchParams
	 * @return
	 * @author liangsh
	 * @date 2016年1月15日 下午12:24:00
	 */
	public PageImpl<WebsiteSeo> find(Pageable pageable,Map<String,Object> searchParams,Integer type){
		logger.info("NoticeService find Method");
		logger.info(searchParams.toString());
		List<WebsiteSeo> list;
		int total;
		try {
			list = new ArrayList<WebsiteSeo>();
			WebsiteSeoCriteria c = new WebsiteSeoCriteria();
			WebsiteSeoCriteria.Criteria criteria = c.createCriteria();
			if(searchParams != null){
				Map<String,SearchFilter> filters = SearchFilter.parse(searchParams);
				Criterias.bySearchFilter(criteria,filters.values());
			}
			criteria.andTypeEqualTo(type);
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
			c.setOrderByClause("ct desc");
			list = websiteSeoMapper.selectByExample(c);
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<WebsiteSeo>();
			}
			total = websiteSeoMapper.countByExample(c);
		 } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e.getMessage(), e);
	            throw new RuntimeException(e);
	        }
		return new PageImpl<>(list, pageable, total);
	}
	
	/**
	 * 
	 * <保存><功能具体实现>
	 * @param info
	 * @author liangsh
	 * @date 2016年1月15日 下午3:23:49
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(WebsiteSeo info){
		try {
			if(StringUtils.isNotBlank(info.getId())){
				info.setEb(SessionUtil.currentUserId());
				info.setEt(new Date());
				this.updateByPrimaryKeySelective(info,info.getId());
			}else{
				String key = MD5Util.MD5Encode(info.getType()+"_"+(info.getPageType()==null?-1:info.getPageType())+"_"+info.getSeoType(), "utf-8");
				info.setId(key);
				info.setCt(new Date());
				info.setEb(SessionUtil.currentUserId());
				info.setEt(new Date());
				info.setCb(SessionUtil.currentUserId());
				this.insertSelective(info,info.getId());
			}
		 } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e.getMessage(), e);
	            throw new RuntimeException(e);
	        }
	}
	
}
