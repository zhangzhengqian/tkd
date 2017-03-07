package com.lc.zy.ball.boss.framework.accounts.vo;

/**
 * @author yankefei
 * @date 2015年11月27日 上午10:11:07
 */
public class UserAccountVo {
	
	//用户id
	private String userId;
	
	//球友号
	private String qiuyouNo;
	
	//用户昵称
	private String nickName;
	
	//用户类型
	private String userType;
	
	//手机号码
	private String phone;
	
	//冻结状态
	private String status;
	
	//账户总额
	private Double total;
	
	//账户被冻结金额
	private Double frozen;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQiuyouNo() {
		return qiuyouNo;
	}

	public void setQiuyouNo(String qiuyouNo) {
		this.qiuyouNo = qiuyouNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getFrozen() {
		return frozen;
	}

	public void setFrozen(Double frozen) {
		this.frozen = frozen;
	}
	
}
