package com.lc.zy.ball.boss.framework.label.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jasypt.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.LabelMapper;
import com.lc.zy.ball.domain.oa.po.Label;
import com.lc.zy.ball.domain.oa.po.LabelCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Component
@Transactional(readOnly=true)
public class LabelService extends AbstractCacheService{

	@Autowired
	private LabelMapper labelMapper;
	
	/**
	 * 页面查询对象
	 * @param searchParams
	 * @param page
	 * @param size
	 * @param isPage
	 * @param isHasCount
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	public Page<Label> find(Map<String, Object> searchParams, int page, int size,boolean isPage,boolean isHasCount) 
			throws Exception {
		PageRequest pageable = new PageRequest(page, size);
		
		LabelCriteria cc = new LabelCriteria();
		if(isPage){
			cc.setMysqlOffset(pageable.getOffset());
			cc.setMysqlLength(pageable.getPageSize());
		}
		
		LabelCriteria.Criteria cri = cc.createCriteria();
		cri.andParentIsNull();	//只取公用标签
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		cc.setOrderByClause("ct desc");
		List<Label> list = labelMapper.selectByExample(cc);
		int total = labelMapper.countByExample(cc);
		return new PageImpl<>(list, pageable, total);
	}
	
	/**
	 * 新建或更新标签
	 * @param label
	 * @return
	 * @throws Exception
	 * @author yankefei
	 */
	@Transactional(readOnly=false)
	public void insertOrUpdateLabel(Label label) throws Exception {
		String id = "";
		if(CommonUtils.isNotEmpty(label.getId())){//更新
			id = label.getId();
			label.setEt(new Date());
			this.updateByPrimaryKeySelective(label, id);
		}else{//新增
			id = UUID.get();
			label.setId(id);
			label.setStatus(1);
			label.setParent(null);
			label.setCt(new Date());
			label.setEt(new Date());
			this.insert(label, id);
		}
	}
	
	/**
	 * 删除标签
	 * @param id
	 * @author yankefei
	 */
	@Transactional(readOnly = false)
	public void deleteLabel(String id) throws Exception{
		Label label = this.selectByPrimaryKey(Label.class, id);
		label.setId(id);
		
		this.deleteByPrimaryKey(Label.class, id);
	}
	
	/**
	 * 获取标签信息
	 * @param id
	 * @return
	 * @author yankefei
	 */
	public Label getLabel(String id) throws Exception{
		try {
			return this.selectByPrimaryKey(Label.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 校验同类型标签（公用）名称是否存在
	 * @param name
	 * @return
	 * @author yankefei
	 */
	public boolean checkName(String name, Integer type){
		LabelCriteria labelCriteria = new LabelCriteria();
		LabelCriteria.Criteria criteria = labelCriteria.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andTypeEqualTo(type);
		criteria.andParentIsNull();
		List<Label> labels = labelMapper.selectByExample(labelCriteria);
		if(labels!=null && labels.size()>0)
			return false;
		else
			return true;
	}
}
