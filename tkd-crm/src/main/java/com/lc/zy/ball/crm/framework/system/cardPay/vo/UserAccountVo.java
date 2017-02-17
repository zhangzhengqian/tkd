package com.lc.zy.ball.crm.framework.system.cardPay.vo;

import com.lc.zy.ball.domain.oa.po.CrmUserCardAccount;

/**
 * Created by sl on 2016/12/23.
 */
public class UserAccountVo extends CrmUserCardAccount{

    private static final long serialVersionUID = 1L;

    // 用户名称
    private String name;
    // 手机号
    private String phone;
    // 期限
    private String cardDate;

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
