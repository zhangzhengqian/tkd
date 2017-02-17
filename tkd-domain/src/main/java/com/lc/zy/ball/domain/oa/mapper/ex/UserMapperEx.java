package com.lc.zy.ball.domain.oa.mapper.ex;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.UserMapper;
import com.lc.zy.ball.domain.oa.po.Role;
import com.lc.zy.ball.domain.oa.po.ex.UserEx;


public interface UserMapperEx extends UserMapper {

	UserEx getUserExByFilter(@Param("filters")Collection<SearchFilter> filters);
	
	List<Role> selectRoleByUserId(String userId);
	
	int countUserEx(@Param("page")PageRequest page, @Param("filters")Collection<SearchFilter> filters);
	
	List<UserEx> findUserEx(@Param("page")PageRequest page, @Param("filters")Collection<SearchFilter> filters, @Param("orderBy")String orderBy);

}
