package com.lc.zy.ball.domain.oa.mapper;

import com.lc.zy.ball.domain.oa.po.OaContestActivity;
import com.lc.zy.ball.domain.oa.po.OaContestActivityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OaContestActivityMapper {
    int countByExample(OaContestActivityCriteria example);

    int deleteByExample(OaContestActivityCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(OaContestActivity record);

    int insertSelective(OaContestActivity record);

    List<OaContestActivity> selectByExample(OaContestActivityCriteria example);

    OaContestActivity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OaContestActivity record, @Param("example") OaContestActivityCriteria example);

    int updateByExample(@Param("record") OaContestActivity record, @Param("example") OaContestActivityCriteria example);

    int updateByPrimaryKeySelective(OaContestActivity record);

    int updateByPrimaryKey(OaContestActivity record);
}