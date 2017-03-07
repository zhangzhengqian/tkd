package com.lc.zy.ball.boss.framework.qiuyouzone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.Pageable;
import com.lc.zy.ball.domain.oa.mapper.QiuyouzoneLabelMapper;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabel;
import com.lc.zy.ball.domain.oa.po.QiuyouzoneLabelCriteria;
import com.lc.zy.common.data.Criterias;

@Service
@Transactional(readOnly = true)
public class QiuyouzoneLabelService extends AbstractCacheService {

	private static Logger logger = LoggerFactory.getLogger(QiuyouzoneLabelService.class);

	@Autowired
	private QiuyouzoneLabelMapper qiuyouzoneLabelMapper;

	/**
	 * 
	 * <球友标签列表><功能具体实现>
	 * @param searchParams
	 * @param pageable
	 * @return
	 * @author liangsh
	 * @date 2016年1月20日 下午5:41:27
	 */
	public Page<QiuyouzoneLabel> find(Map<String, Object> searchParams, Pageable pageable) {
		List<QiuyouzoneLabel> list = new ArrayList<QiuyouzoneLabel>();
		logger.debug(searchParams.toString());
		int total = 0;
		try {
			QiuyouzoneLabelCriteria c = new QiuyouzoneLabelCriteria();
			QiuyouzoneLabelCriteria.Criteria cri = c.createCriteria();
			cri.andStatusNotEqualTo(2);
			c.setMysqlLength(pageable.getPageSize());
			c.setMysqlOffset(pageable.getOffset());
			c.setOrderByClause("top_time desc,use_number desc,ct desc");
			if (searchParams != null) {
				Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
				Criterias.bySearchFilter(cri, filters.values());
			}
			list = qiuyouzoneLabelMapper.selectByExample(c);
			total = qiuyouzoneLabelMapper.countByExample(c);
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<QiuyouzoneLabel>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PageImpl<QiuyouzoneLabel>(list, pageable, total);
	}
}
