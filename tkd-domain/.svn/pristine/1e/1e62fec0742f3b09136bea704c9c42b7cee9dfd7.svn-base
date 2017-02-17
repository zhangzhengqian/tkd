package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumClassInfo
* table:oa_statium_class_info
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-26 17:49:14
*/
public class StatiumClassInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 课程id
     */
    private String classId;

    /**
     * 教练id
     */
    private String coachId;

    /**
     * 教练名称
     */
    private String coachName;

    /**
     * 课程日期
     */
    private Date classDate;

    /**
     * 课程开始时间
     */
    private String classStartTime;

    /**
     * 课程结束时间
     */
    private String classEndTime;

    private Date ct;

    private String cb;

    private Date et;

    private String eb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 课程id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId 
	 *            课程id
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * @return 教练id
     */
    public String getCoachId() {
        return coachId;
    }

    /**
     * @param coachId 
	 *            教练id
     */
    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    /**
     * @return 教练名称
     */
    public String getCoachName() {
        return coachName;
    }

    /**
     * @param coachName 
	 *            教练名称
     */
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    /**
     * @return 课程日期
     */
    public Date getClassDate() {
        return classDate;
    }

    /**
     * @param classDate 
	 *            课程日期
     */
    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    /**
     * @return 课程开始时间
     */
    public String getClassStartTime() {
        return classStartTime;
    }

    /**
     * @param classStartTime 
	 *            课程开始时间
     */
    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    /**
     * @return 课程结束时间
     */
    public String getClassEndTime() {
        return classEndTime;
    }

    /**
     * @param classEndTime 
	 *            课程结束时间
     */
    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getEb() {
        return eb;
    }

    public void setEb(String eb) {
        this.eb = eb;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StatiumClassInfo other = (StatiumClassInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getCoachId() == null ? other.getCoachId() == null : this.getCoachId().equals(other.getCoachId()))
            && (this.getCoachName() == null ? other.getCoachName() == null : this.getCoachName().equals(other.getCoachName()))
            && (this.getClassDate() == null ? other.getClassDate() == null : this.getClassDate().equals(other.getClassDate()))
            && (this.getClassStartTime() == null ? other.getClassStartTime() == null : this.getClassStartTime().equals(other.getClassStartTime()))
            && (this.getClassEndTime() == null ? other.getClassEndTime() == null : this.getClassEndTime().equals(other.getClassEndTime()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getCoachId() == null) ? 0 : getCoachId().hashCode());
        result = prime * result + ((getCoachName() == null) ? 0 : getCoachName().hashCode());
        result = prime * result + ((getClassDate() == null) ? 0 : getClassDate().hashCode());
        result = prime * result + ((getClassStartTime() == null) ? 0 : getClassStartTime().hashCode());
        result = prime * result + ((getClassEndTime() == null) ? 0 : getClassEndTime().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}