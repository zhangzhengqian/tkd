package com.lc.zy.common.mq.bean;

public class IntegralBean {

    private String userId; // 用户id
    
    private Integer type;// 积分类型

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
