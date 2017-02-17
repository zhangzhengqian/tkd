package com.lc.zy.ball.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * 同步场馆数据到 solr
 * 
 * @author sl
 */
public class StatiumBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 与指定坐标点的距离 由 solr 计算距离并返回，单位为 km ，数据类型为 double， 例如 8.579809864588546 表示
     * 8.58 公里
     */
    @Field
    private Double dist;

    @Field
    private String id;

    @Field
    private String city;

    @Field
    private String area;

    @Field
    private String name;

    @Field
    private String address;

    @Field
    private Integer rating;

    @Field
    private Double lat;

    @Field
    private Double lng;

    @Field
    private String phone;

    @Field
    private String photo_list;

    @Field
    private String originalPhoto_list;

    @Field
    private String geo;

    // 场馆信息介绍 add
    @Field
    private String introduce;

    // 场馆价格
    @Field
    private String statiumPrice;

    // 数据来源
    @Field
    private String stype;

    // 最低价格
    @Field
    private String minPrice;
    
    // 折扣
    @Field
    private String discount;
    
    // 折扣价
    @Field
    private String discountPrice;
    
    // 限时价
    @Field
    private String limitPrice;

    // 原价
    @Field
    private String costPrice;

    // 最低价
    @Field("*_min_price")
    private Map<String, Object> minPriceMap;

    // 原价
    @Field("*_cost_price")
    private Map<String, Object> costPriceMap;

    // 销量最高
    @Field
    private Integer sales;
    
    // 评价最好
    @Field
    private Double comment;

    // 球场评价级别
    private Integer grade;

    // 订单总数量
    private Integer orderTotal;

    // 是否置顶：0未置顶，1为置顶
    private Integer top;

	// 推荐
	@Field
	private Integer recommend;

    // 审核状态
    @Field
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Double getDist() {
		return dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto_list() {
		return photo_list;
	}

	public void setPhoto_list(String photo_list) {
		this.photo_list = photo_list;
	}

	public String getOriginalPhoto_list() {
		return originalPhoto_list;
	}

	public void setOriginalPhoto_list(String originalPhoto_list) {
		this.originalPhoto_list = originalPhoto_list;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getStatiumPrice() {
		return statiumPrice;
	}

	public void setStatiumPrice(String statiumPrice) {
		this.statiumPrice = statiumPrice;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public Map<String, Object> getMinPriceMap() {
		return minPriceMap;
	}

	public void setMinPriceMap(Map<String, Object> minPriceMap) {
		this.minPriceMap = minPriceMap;
	}

	public Map<String, Object> getCostPriceMap() {
		return costPriceMap;
	}

	public void setCostPriceMap(Map<String, Object> costPriceMap) {
		this.costPriceMap = costPriceMap;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(String limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Double getComment() {
		return comment;
	}

	public void setComment(Double comment) {
		this.comment = comment;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

}