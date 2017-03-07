package com.lc.zy.ball.boss.framework.company.vo;

import com.lc.zy.ball.domain.oa.po.CompanyInfo;

public class CompanyInfoVo extends CompanyInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String balance;
	private Integer employees;
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Integer getEmployees() {
		return employees;
	}

	public void setEmployees(Integer employees) {
		this.employees = employees;
	}

}
