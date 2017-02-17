package com.lc.zy.common.search;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 同步场馆数据到 solr
 * 
 * @author caiyy
 */
public class StatiumBeanTest implements Serializable, SearchBean {
	private static final long serialVersionUID = 1L;

	/**
	 * 与指定坐标点的距离
	 * 由 solr 计算距离并返回，单位为 km ，数据类型为 double，
	 * 例如 8.579809864588546 表示 8.58 公里
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
	private String sport_type;

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

	// 服务类型添加（可以使多个）
	@Field
	private String serviceList;

	// 场馆开始时间
	@Field
	private String beginTime;

	// 场馆结束时间
	@Field
	private String endTime;

	// 场馆价格
	@Field
	private String statiumPrice;

	// 周末场馆价格
	@Field
	private String holidayStatiumPrice;

	// 球友圈价格 折扣价格
	@Field
	private String qiuyouPrice;

	// 数据来源
	@Field
	private String stype;

	// 最低价格
	@Field
	private String minPrice;

	// 是否免费 1:是 0:否
	@Field
	private String isRating;

	// 门市价
	@Field
	private String retailPrice;

	// 原价
	@Field
	private String costPrice;
	
	@Field("*_min_price")
	private Map<String,Object> dmap = new HashMap<String,Object>();


	public Map<String, Object> getDmap() {
		return dmap;
	}

	public void setDmap(Map<String, Object> dmap) {
		this.dmap = dmap;
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

	public String getSport_type() {
		return sport_type;
	}

	public void setSport_type(String sport_type) {
		this.sport_type = sport_type;
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

	public String getServiceList() {
		return serviceList;
	}

	public void setServiceList(String serviceList) {
		this.serviceList = serviceList;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatiumPrice() {
		return statiumPrice;
	}

	public void setStatiumPrice(String statiumPrice) {
		this.statiumPrice = statiumPrice;
	}

	public String getQiuyouPrice() {
		return qiuyouPrice;
	}

	public void setQiuyouPrice(String qiuyouPrice) {
		this.qiuyouPrice = qiuyouPrice;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getOriginalPhoto_list() {
		return originalPhoto_list;
	}

	public void setOriginalPhoto_list(String originalPhoto_list) {
		this.originalPhoto_list = originalPhoto_list;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getIsRating() {
		return isRating;
	}

	public void setIsRating(String isRating) {
		this.isRating = isRating;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getHolidayStatiumPrice() {
		return holidayStatiumPrice;
	}

	public void setHolidayStatiumPrice(String holidayStatiumPrice) {
		this.holidayStatiumPrice = holidayStatiumPrice;
	}

	public Double getDist() {
		return dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "StatiumBean [id=" + id + ", city=" + city + ", area=" + area + ", sport_type=" + sport_type + ", name=" + name + ", address=" + address + ", lat=" + lat + ", lng=" + lng
				+ ", phone=" + phone + ", photo_list=" + photo_list + ", geo=" + geo + ",introduce=" + introduce + ", serviceList=" + serviceList + ", beginTime=" + beginTime
				+ ",endTime" + endTime + ", statiumPrice=" + statiumPrice + ", qiuyouPrice=" + qiuyouPrice + ",stype=" + stype + ",originalPhoto_list=" + originalPhoto_list
				+ ",minPrice=" + minPrice + ",isRating=" + isRating + ",retailPrice=" + retailPrice + "]";
	}

	@Override
	public String getRestUrl() {
		return "http://192.168.12.213:8081/solr/core0";
	}
}