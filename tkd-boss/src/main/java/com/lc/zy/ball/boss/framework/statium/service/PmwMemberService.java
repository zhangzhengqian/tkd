package com.lc.zy.ball.boss.framework.statium.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.PmwMemberMapper;
import com.lc.zy.ball.domain.oa.po.PmwMember;
import com.lc.zy.ball.domain.oa.po.PmwMemberCriteria;
import com.lc.zy.common.data.Criterias;

/**
 * 道馆管理Service
 * 
 * @author 20160422 fanlq
 *
 */
@Service
@Transactional(readOnly = true)
public class PmwMemberService extends AbstractCacheService {

	private static final Logger logger = LoggerFactory
			.getLogger(PmwMemberService.class);

	@Autowired
	private PmwMemberMapper pmwMemberMapper;

	/**
	 * 
	 * <道馆列表><功能具体实现>
	 *
	 * @create：2016年5月3日 下午2:55:35
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<PmwMember> findPmwMemberlList(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<PmwMember> list = new ArrayList<PmwMember>();
		try {
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			PmwMemberCriteria pmwMemberCriteria = new PmwMemberCriteria();
			pmwMemberCriteria.setMysqlLength(pageRequest.getPageSize());
			pmwMemberCriteria.setMysqlOffset(pageRequest.getOffset());
			PmwMemberCriteria.Criteria cri = pmwMemberCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			pmwMemberCriteria.setOrderByClause("id desc");
			cri.andA2NotEqualTo("");
			total = pmwMemberMapper.countByExample(pmwMemberCriteria);
			list = pmwMemberMapper.selectByExample(pmwMemberCriteria);
		} catch (Exception e) {
			logger.error("获取道馆list(PmwMemberService--findPmwMemberlList)"
					+ e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

}
