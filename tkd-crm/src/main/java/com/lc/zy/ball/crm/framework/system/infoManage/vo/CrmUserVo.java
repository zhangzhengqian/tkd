package com.lc.zy.ball.crm.framework.system.infoManage.vo;

import com.lc.zy.ball.domain.oa.po.CrmUser;

/**
 * Created by sl on 2016/11/28.
 */
public class CrmUserVo extends CrmUser{
    // 新密码
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
