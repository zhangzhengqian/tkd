package com.lc.zy.ball.app.service.statium.bean;

/**
 * Created by sl on 16/7/19.
 */
public class ClassListVo {

    // 课时ID
    private String classInfoId;
    // 课程名称
    private String className;
    // 课程开始时间
    private String classStartTime;
    // 课程结束时间
    private String classEndTime;
    // 价格
    private int classPrice;
    // 可预约人数
    private int signNum;
    // 可报名状态
    private int status;

    public int getSignPrice() {
        return signPrice;
    }

    public void setSignPrice(int signPrice) {
        this.signPrice = signPrice;
    }

    // 签约价
    private int signPrice;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public int getClassPrice() {
        return classPrice;
    }

    public void setClassPrice(int classPrice) {
        this.classPrice = classPrice;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }
}
