package com.lc.zy.ball.boss.framework.system.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CphMapper;
import com.lc.zy.ball.domain.oa.po.Cph;
import com.lc.zy.ball.domain.oa.po.CphCriteria;
import com.lc.zy.common.data.Criterias;

@Component
@Transactional(readOnly = true)
public class CphService extends AbstractCacheService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private CphMapper cphMapper;
	
	/**
	 * 
	 * <存储呼叫中心接通记录><功能具体实现>
	 *
	 * @create：2015年11月26日 下午3:25:55
	 * @author： liangsh
	 * @param cph
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void saveLinkRecord(Cph cph) throws Exception {
		try {
			 this.insertSelective(cph,cph.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
	
	public Page<Cph> find(PageRequest pageable, Map<String, Object> searchParams ) throws Exception {
		//如果有角色这个条件，则先查出对应角色
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		CphCriteria cphCriteria = new CphCriteria();
		cphCriteria.setMysqlLength(pageable.getPageSize());
		cphCriteria.setMysqlOffset(pageable.getOffset());
		CphCriteria.Criteria cri = cphCriteria.createCriteria();
		System.out.println(searchParams);
		Criterias.bySearchFilter(cri, filters.values());
		cphCriteria.setOrderByClause("ct desc");
		int total = cphMapper.countByExample(cphCriteria);
		List<Cph> list = cphMapper.selectByExample(cphCriteria);
		return new PageImpl<>(list, pageable, total);
	}
	
	/**
	 * 
	 * <根据电话获取最后通话><功能具体实现>
	 *
	 * @create：2015年11月26日 下午3:24:37
	 * @author： liangsh
	 * @param phone
	 * @return
	 */
	public Cph getCphByPhone(String phone,String actionId){
		CphCriteria c = new CphCriteria();
		CphCriteria.Criteria cri = c.createCriteria();
		cri.andActionidEqualTo(actionId);
		c.setOrderByClause("ct desc"); //只取最后通话记录
		List<Cph> list = cphMapper.selectByExample(c);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
	}
}
