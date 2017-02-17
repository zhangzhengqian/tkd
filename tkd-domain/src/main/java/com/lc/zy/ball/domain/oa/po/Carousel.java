package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Carousel
* table:oa_carousel
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-20 13:53:11
*/
public class Carousel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 图片链接
     */
    private String image;

    /**
     * 新版轮播图
     */
    private String imageNew;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 轮播图使用的位置:0为首页轮播
     */
    private Integer position;

    /**
     * 类型:活动,比赛,场馆，url 视频
     */
    private String type;

    /**
     * 资源主键:如活动id,比赛idType为url的时候值为链接地址
     */
    private String resourceId;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 微信公众平台轮播内容
     */
    private String contentWx;

    private String province;

    private String city;

    /**
     * 时长
     */
    private String duration;

    private String areaCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 图片链接
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image 
	 *            图片链接
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return 新版轮播图
     */
    public String getImageNew() {
        return imageNew;
    }

    /**
     * @param imageNew 
	 *            新版轮播图
     */
    public void setImageNew(String imageNew) {
        this.imageNew = imageNew;
    }

    /**
     * @return 图片名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            图片名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 轮播图使用的位置:0为首页轮播
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position 
	 *            轮播图使用的位置:0为首页轮播
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return 类型:活动,比赛,场馆，url 视频
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型:活动,比赛,场馆，url 视频
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 资源主键:如活动id,比赛idType为url的时候值为链接地址
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId 
	 *            资源主键:如活动id,比赛idType为url的时候值为链接地址
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return 排序值
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            排序值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 微信公众平台轮播内容
     */
    public String getContentWx() {
        return contentWx;
    }

    /**
     * @param contentWx 
	 *            微信公众平台轮播内容
     */
    public void setContentWx(String contentWx) {
        this.contentWx = contentWx;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return 时长
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration 
	 *            时长
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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
        Carousel other = (Carousel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getImageNew() == null ? other.getImageNew() == null : this.getImageNew().equals(other.getImageNew()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContentWx() == null ? other.getContentWx() == null : this.getContentWx().equals(other.getContentWx()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getImageNew() == null) ? 0 : getImageNew().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContentWx() == null) ? 0 : getContentWx().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}