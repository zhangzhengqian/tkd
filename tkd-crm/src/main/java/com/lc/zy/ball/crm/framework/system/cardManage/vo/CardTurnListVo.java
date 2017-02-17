package com.lc.zy.ball.crm.framework.system.cardManage.vo;

import com.lc.zy.ball.domain.oa.po.CrmTurnCardLog;

/**
 * Created by sl on 2016/12/13.
 */
public class CardTurnListVo extends CrmTurnCardLog {
    // new用户名称
    private String newName;
    // old用户名称
    private String oldName;
    // new phone
    private String newPhone;
    // old phone
    private String oldPhone;
    // 查询phone
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }
}
