package com.lc.zy.common.mq.bean;

import java.util.List;

public class OrdeNotifyrMessage {
	private String orderDate; //预约日期 2008-02-06
	private String type;  //new 新场地预约  cancel 取消预约    pay 已支付订单   
	private List<SpaceBean> spaces;  //场地id   time 预约 时间10:00
	private String orderId;  //订单号 
	private String statiumId;
	private String billTitle;
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SpaceBean> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<SpaceBean> spaces) {
		this.spaces = spaces;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatiumId() {
		return statiumId;
	}
	public void setStatiumId(String statiumId) {
		this.statiumId = statiumId;
	}
	
	public String getBillTitle() {
		return billTitle;
	}
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public static class SpaceBean{
		private String spaceId;
		private String time;
		public String getSpaceId() {
			return spaceId;
		}
		public void setSpaceId(String spaceId) {
			this.spaceId = spaceId;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
	}
}

