package com.lc.zy.ball.boss.framework.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lc.zy.ball.domain.oa.mapper.FunctionMapper;
import com.lc.zy.ball.domain.oa.mapper.RoleFuncMapper;
import com.lc.zy.ball.domain.oa.mapper.ex.FunctionMapperEx;
import com.lc.zy.ball.domain.oa.po.Function;
import com.lc.zy.ball.domain.oa.po.FunctionCriteria;
import com.lc.zy.ball.domain.oa.po.RoleFuncCriteria;
import com.lc.zy.ball.domain.oa.po.ex.FunctionEx;
import com.lc.zy.ball.boss.common.security.ShiroCacheManager;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.common.exception.ServiceException;
import com.lc.zy.common.util.UUID;


@Component
//@Transactional(value="core", readOnly = false)
@Transactional(readOnly = false)
public class FunctionService extends AbstractCacheService {
	
	@Autowired
	private FunctionMapper functionMapper;

	@Autowired
	private RoleFuncMapper roleFuncMapper;
	
	@Autowired
	private ShiroCacheManager shiroCacheManager;
	
	@Autowired
	private FunctionProvider functionProvider;

//	@Transactional(value="core", readOnly = false)
	@Transactional(readOnly = false)
	public void saveFunction(Function f) {
		try {
			if (StringUtils.isNotBlank(f.getFuncId())) {
				updateByPrimaryKeySelective(f,f.getFuncId());
			} else {
				String fid = UUID.get();
				f.setFuncId(fid);
				functionMapper.insertSelective(f);
			}

			functionProvider.reload();
			
			shiroCacheManager.clear();
			
		} catch (Exception e) {
			throw new ServiceException("保存or更新Function时发生异常", e);
		}
	}
	
//	@Transactional(value="core", readOnly = false)
	@Transactional(readOnly = false)
	public void delFunction(String fid) throws Exception {
		delRoleFunc(fid);
		deleteByPrimaryKey(Function.class,fid);
		
		functionProvider.reload();
		
		shiroCacheManager.clear();
	}
	
	private void delRoleFunc(String fid) {
		RoleFuncCriteria rfc = new RoleFuncCriteria();
		RoleFuncCriteria.Criteria cri = rfc.createCriteria();
		cri.andFuncIdEqualTo(fid);
		
		roleFuncMapper.deleteByExample(rfc);
	}

	public Function getFunction(String fid) {
		try {
			return selectByPrimaryKey(Function.class,fid);
		} catch (Exception e) {
			throw new ServiceException("查询Function时发生异常", e);
		}
	}

	public List<Function> findByPId(String id) {
		FunctionCriteria fc = new FunctionCriteria();
		FunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(id);
		
		fc.setOrderByClause("seq_num");
		
		return functionMapper.selectByExample(fc);
	}
	
	public int countByPId(String id) {
		FunctionCriteria fc = new FunctionCriteria();
		FunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(id);
		
		return functionMapper.countByExample(fc);
	}

	public Function getByNameAndPId(String funcName, String pId) {
		FunctionCriteria fc = new FunctionCriteria();
		FunctionCriteria.Criteria cri = fc.createCriteria();
		cri.andParentIdEqualTo(pId);
		cri.andFuncNameEqualTo(funcName);
		
		List<Function> list = functionMapper.selectByExample(fc);
		
		return (list.size() > 0 ? list.get(0) : null);
	}

	public List<Function> findAll() {
		return functionMapper.selectByExample(null);
	}
	
	
	@Autowired
	private FunctionMapperEx functionMapperEx;
	
	public List<FunctionEx> findExByPId(String pid) {
		return functionMapperEx.selectByPid(pid);
	}
    //根据roleId查询function
	public List<Function> findExByRoleId(String roleId) {
		return functionMapperEx.selectByRoleId(roleId);
	}
}
