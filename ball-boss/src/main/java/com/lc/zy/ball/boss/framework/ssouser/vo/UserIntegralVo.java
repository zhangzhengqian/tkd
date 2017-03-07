package com.lc.zy.ball.boss.framework.ssouser.vo;

import java.io.Serializable;

public class UserIntegralVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户积分
     */
    private Integer integral;

    /**
     * 积分类型：0消费1增加
     */
    private Integer type;

    /**
     * 积分描述
     */
    private String description;

    /**
     * 积分类型：0签到、1订场订单完成、2完成后发布评论、3参与活动订单完成、4教陪练订单完成、5发布教陪练订单评论、6分享、7完成约球（合作）、8
     * 完成约球（未合作）、9绑定任意第三方账号、10完善全部基本信息、11商品兑换
     */
    private Integer integralType;

    /**
     * 创建时间
     */
    private String ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

}
