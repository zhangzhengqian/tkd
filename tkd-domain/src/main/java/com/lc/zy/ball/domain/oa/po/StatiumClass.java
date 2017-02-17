package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumClass
* table:oa_statium_class
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public class StatiumClass implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 道馆id
     */
    private Integer statiumId;

    /**
     * 课程名称
     */
    private String classTitle;

    /**
     * 课程介绍
     */
    private String classIntroduce;

    /**
     * 原价
     */
    private Integer price;

    /**
     * 折扣
     */
    private Integer discount;

    /**
     * 折扣价
     */
    private Integer discountPrice;

    /**
     * 限时价
     */
    private Integer limitPrice;

    private Date ct;

    private String cb;

    private Date et;

    private String eb;

    /**
     * 最少人数
     */
    private Integer minPeople;

    /**
     * 最多人数
     */
    private Integer maxPeople;

    /**
     * 课程类型
     */
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 道馆id
     */
    public Integer getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            道馆id
     */
    public void setStatiumId(Integer statiumId) {
        this.statiumId = statiumId;
    }

    /**
     * @return 课程名称
     */
    public String getClassTitle() {
        return classTitle;
    }

    /**
     * @param classTitle 
	 *            课程名称
     */
    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    /**
     * @return 课程介绍
     */
    public String getClassIntroduce() {
        return classIntroduce;
    }

    /**
     * @param classIntroduce 
	 *            课程介绍
     */
    public void setClassIntroduce(String classIntroduce) {
        this.classIntroduce = classIntroduce;
    }

    /**
     * @return 原价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            原价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return 折扣
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount 
	 *            折扣
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * @return 折扣价
     */
    public Integer getDiscountPrice() {
        return discountPrice;
    }

    /**
     * @param discountPrice 
	 *            折扣价
     */
    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * @return 限时价
     */
    public Integer getLimitPrice() {
        return limitPrice;
    }

    /**
     * @param limitPrice 
	 *            限时价
     */
    public void setLimitPrice(Integer limitPrice) {
        this.limitPrice = limitPrice;
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
     * @return 最少人数
     */
    public Integer getMinPeople() {
        return minPeople;
    }

    /**
     * @param minPeople 
	 *            最少人数
     */
    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    /**
     * @return 最多人数
     */
    public Integer getMaxPeople() {
        return maxPeople;
    }

    /**
     * @param maxPeople 
	 *            最多人数
     */
    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    /**
     * @return 课程类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            课程类型
     */
    public void setType(Integer type) {
        this.type = type;
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
        StatiumClass other = (StatiumClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getClassTitle() == null ? other.getClassTitle() == null : this.getClassTitle().equals(other.getClassTitle()))
            && (this.getClassIntroduce() == null ? other.getClassIntroduce() == null : this.getClassIntroduce().equals(other.getClassIntroduce()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
            && (this.getDiscountPrice() == null ? other.getDiscountPrice() == null : this.getDiscountPrice().equals(other.getDiscountPrice()))
            && (this.getLimitPrice() == null ? other.getLimitPrice() == null : this.getLimitPrice().equals(other.getLimitPrice()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getMinPeople() == null ? other.getMinPeople() == null : this.getMinPeople().equals(other.getMinPeople()))
            && (this.getMaxPeople() == null ? other.getMaxPeople() == null : this.getMaxPeople().equals(other.getMaxPeople()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getClassTitle() == null) ? 0 : getClassTitle().hashCode());
        result = prime * result + ((getClassIntroduce() == null) ? 0 : getClassIntroduce().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
        result = prime * result + ((getDiscountPrice() == null) ? 0 : getDiscountPrice().hashCode());
        result = prime * result + ((getLimitPrice() == null) ? 0 : getLimitPrice().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getMinPeople() == null) ? 0 : getMinPeople().hashCode());
        result = prime * result + ((getMaxPeople() == null) ? 0 : getMaxPeople().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}