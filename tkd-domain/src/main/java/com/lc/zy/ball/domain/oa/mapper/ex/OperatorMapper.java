package com.lc.zy.ball.domain.oa.mapper.ex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lc.zy.ball.domain.oa.po.User;


public interface OperatorMapper {

	List<User> find(@Param("roleId")String roleId, @Param("statiumId")String statiumId);

}
