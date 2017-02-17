package com.lc.zy.ball.domain.oa.mapper.ex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lc.zy.ball.domain.oa.po.ex.OrganizationEx;


public interface OrganizationMapperEx {

    List<OrganizationEx> selectByPid(@Param("pid") String pid);

}