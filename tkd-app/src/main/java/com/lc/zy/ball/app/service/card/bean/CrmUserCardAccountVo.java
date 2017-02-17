package com.lc.zy.ball.app.service.card.bean;

public class CrmUserCardAccountVo {
	    //主键
		private String id;
		//用户id
		private String userId;
		//可用金额
		private Integer balance;
		//冻结金额
		private Integer frozenFee;
		//绑定道馆
		private Integer	statiumId;
		//是否需要设置密码 0 不需要设置 已有密码 1需要设置 无密码
		private Integer	isPwd;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public Integer getBalance() {
			return balance;
		}
		public void setBalance(Integer balance) {
			this.balance = balance;
		}
		public Integer getFrozenFee() {
			return frozenFee;
		}
		public void setFrozenFee(Integer frozenFee) {
			this.frozenFee = frozenFee;
		}
		public Integer getStatiumId() {
			return statiumId;
		}
		public void setStatiumId(Integer statiumId) {
			this.statiumId = statiumId;
		}
		public Integer getIsPwd() {
			return isPwd;
		}
		public void setIsPwd(Integer isPwd) {
			this.isPwd = isPwd;
		}
}
