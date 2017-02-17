package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* videoGroup
* table:oa_video_group
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-22 15:20:02
*/
public class videoGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 视频集名称
     */
    private String videoName;

    private Date ct;

    private String cb;

    private Date et;

    private String eb;

    /**
     * 视频集数
     */
    private Integer videoNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 视频集名称
     */
    public String getVideoName() {
        return videoName;
    }

    /**
     * @param videoName 
	 *            视频集名称
     */
    public void setVideoName(String videoName) {
        this.videoName = videoName;
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

    /**
     * @return 视频集数
     */
    public Integer getVideoNum() {
        return videoNum;
    }

    /**
     * @param videoNum 
	 *            视频集数
     */
    public void setVideoNum(Integer videoNum) {
        this.videoNum = videoNum;
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
        videoGroup other = (videoGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVideoName() == null ? other.getVideoName() == null : this.getVideoName().equals(other.getVideoName()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getVideoNum() == null ? other.getVideoNum() == null : this.getVideoNum().equals(other.getVideoNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVideoName() == null) ? 0 : getVideoName().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getVideoNum() == null) ? 0 : getVideoNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}