package com.lc.zy.ball.crm.framework.system.statium.service.vo;

/**
 * Created by sl on 2016/11/24.
 */
public class ClassNavVo {
    // 课程id
    private String classId;
    // 课程名称
    private String className;
    // 课时id
    private String classInfoId;
    // 上课时间
    private String classTime;

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
