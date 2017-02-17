package com.lc.zy.ball.app.service.order.bean;

/**
 * Created by sl on 16/7/25.
 */
public class OrderListVo {

    // 道馆名称
    private String statiumName;
    // 道馆图片
    private String photo;
    //道馆id
    private Integer dgId;
    // 订单日期
    private String classDate;
    // 订单名称
    private String orderName;
    // 教练
    private String coachName;
    // 订单id
    private String orderId;

	// 订单状态中文显示
    private String statusName;
    // 订单类型
    private int orderType;
    // 订单状态
    private String status;
    // 价格
    private int price;
    // 评价状态
    private int isComment;
    
    // 预约日期
    private String signDate;
    //预约时间
    private String startTime;
    //支付类型
    private Integer payType;
	//预约日期时间
    private String signTime;
    // 课程名称
    private String className;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

    public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getClassName() {
		return className;
	}

	public Integer getDgId() {
		return dgId;
	}

	public void setDgId(Integer dgId) {
		this.dgId = dgId;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatiumName() {
        return statiumName;
    }

    public void setStatiumName(String statiumName) {
        this.statiumName = statiumName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
