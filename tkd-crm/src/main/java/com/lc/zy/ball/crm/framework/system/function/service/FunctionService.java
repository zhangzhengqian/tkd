package com.lc.zy.ball.crm.framework.system.function.service;

import com.lc.zy.ball.crm.common.security.FunctionProvider;
import com.lc.zy.ball.crm.common.security.ShiroCacheManager;
import com.lc.zy.ball.crm.common.service.AbstractCacheService;
import com.lc.zy.ball.domain.oa.mapper.CrmFunctionMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.CrmFunctionMapperEx;
import com.lc.zy.ball.domain.oa.po.CrmFunction;
import com.lc.zy.ball.domain.oa.po.CrmFunctionCriteria;
import com.lc.zy.ball.domain.oa.po.ex.CrmFunctionEx;
import com.lc.zy.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional(readOnly = false)
public class FunctionService extends AbstractCacheService {
	
	@Autowired
	private CrmFunctionMapper crmFunctionMapper;

	@Autowired
	private ShiroCacheManager shiroCacheManager;
	
	@Autowired
	private FunctionProvider functionProvider;
	

	public CrmFunction getFunction(String fid) {
		try {
			return selectByPrimaryKey(CrmFunction.class,fid);
		} catch (Exception e) {
			throw new ServiceException("查询Function时发生异常", e);
		}
	}

	public List<CrmFunction> findByPId(String id) {
		CrmFunctionCriteria fc = new CrmFunctionCriteria();
		CrmFunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(id);
		
		fc.setOrderByClause("seq_num");
		
		return crmFunctionMapper.selectByExample(fc);
	}
	
	public int countByPId(String id) {
		CrmFunctionCriteria fc = new CrmFunctionCriteria();
		CrmFunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(id);
		
		return crmFunctionMapper.countByExample(fc);
	}

	public CrmFunction getByNameAndPId(String funcName, String pId) {
		CrmFunctionCriteria fc = new CrmFunctionCriteria();
		CrmFunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(pId);
		cri.andFuncNameEqualTo(funcName);
		
		List<CrmFunction> list = crmFunctionMapper.selectByExample(fc);
		
		return (list.size() > 0 ? list.get(0) : null);
	}

	public List<CrmFunction> findAll() {
		return crmFunctionMapper.selectByExample(null);
	}
	
	@Autowired
	private CrmFunctionMapperEx crmFunctionMapperEx;
	
	public List<CrmFunctionEx> findExByPId(String pid) {
		return crmFunctionMapperEx.selectByPid(pid);
	}
    //根据roleId查询function
	public List<String> selectByUserId(String userId) {
		return crmFunctionMapperEx.selectByUserId(userId);
	}
}
