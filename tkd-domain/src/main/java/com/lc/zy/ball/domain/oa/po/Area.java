package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Area
* table:c_area
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 地域ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    private String english;

    /**
     * 级别
     */
    private String level;

    /**
     * 国家
     */
    private String country;

    /**
     * 上级ID
     */
    private String parentId;

    /**
     * 区号
     */
    private String areaCode;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 排序
     */
    private Short seqNum;

    /**
     * @return 地域ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            地域ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            名称
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     * @return 级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level 
	 *            级别
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country 
	 *            国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return 上级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 区号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode 
	 *            区号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return 邮编
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode 
	 *            邮编
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return 排序
     */
    public Short getSeqNum() {
        return seqNum;
    }

    /**
     * @param seqNum 
	 *            排序
     */
    public void setSeqNum(Short seqNum) {
        this.seqNum = seqNum;
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
        Area other = (Area) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEnglish() == null ? other.getEnglish() == null : this.getEnglish().equals(other.getEnglish()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getZipCode() == null ? other.getZipCode() == null : this.getZipCode().equals(other.getZipCode()))
            && (this.getSeqNum() == null ? other.getSeqNum() == null : this.getSeqNum().equals(other.getSeqNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEnglish() == null) ? 0 : getEnglish().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getZipCode() == null) ? 0 : getZipCode().hashCode());
        result = prime * result + ((getSeqNum() == null) ? 0 : getSeqNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}