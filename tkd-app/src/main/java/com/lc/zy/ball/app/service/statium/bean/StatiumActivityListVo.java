package com.lc.zy.ball.app.service.statium.bean;

/**
 * Created by sl on 16/7/28.
 */
public class StatiumActivityListVo {
    // 活动名称
    private String sAvtivityName;
    // 活动日期
    private String sActivityDate;
    // 报名人数
    private int signNum;
    // 活动报名状态中文
    private String sActivityStatus;
    // 活动状态
    private int status;
    // 活动id
    private String sActivityId;
    // 活动图片
    private String sActivityphoto;

    public String getsActivityphoto() {
        return sActivityphoto;
    }

    public void setsActivityphoto(String sActivityphoto) {
        this.sActivityphoto = sActivityphoto;
    }

    public String getsAvtivityName() {
        return sAvtivityName;
    }

    public void setsAvtivityName(String sAvtivityName) {
        this.sAvtivityName = sAvtivityName;
    }

    public String getsActivityDate() {
        return sActivityDate;
    }

    public void setsActivityDate(String sActivityDate) {
        this.sActivityDate = sActivityDate;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }

    public String getsActivityStatus() {
        return sActivityStatus;
    }

    public void setsActivityStatus(String sActivityStatus) {
        this.sActivityStatus = sActivityStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getsActivityId() {
        return sActivityId;
    }

    public void setsActivityId(String sActivityId) {
        this.sActivityId = sActivityId;
    }


}
