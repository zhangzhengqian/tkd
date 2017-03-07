package com.lc.zy.ball.boss.framework.company.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyServiceForm {
	private String id;
	private String begin;
	private String end;
	private Integer costFee;
	private Integer fee;
	private String sportType;
	private Integer payType;
	private Integer serviceType;
	private String companyId;
	private Integer amount;
	private String statiumId;
	private Integer unitPrice;
	private Integer price;
	private Integer costPrice;
	private String companyName;
	private String statiumName;
	private Map<Integer,List<Integer>> activityDate = new HashMap<Integer, List<Integer>>();
	private List<AddProject> addProjects = new ArrayList<AddProject>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getSportType() {
		return sportType;
	}
	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public List<AddProject> getAddProjects() {
		return addProjects;
	}
	public void setAddProjects(List<AddProject> addProjects) {
		this.addProjects = addProjects;
	}
	public Integer getCostFee() {
		return costFee;
	}
	public void setCostFee(Integer costFee) {
		this.costFee = costFee;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Map<Integer, List<Integer>> getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Map<Integer, List<Integer>> activityDate) {
		this.activityDate = activityDate;
	}
	public String getStatiumId() {
		return statiumId;
	}
	public void setStatiumId(String statiumId) {
		this.statiumId = statiumId;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStatiumName() {
		return statiumName;
	}
	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}
	
	
}