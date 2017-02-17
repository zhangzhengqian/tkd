package com.lc.zy.ball.app.service.order.bean;

/**
 * Created by sl on 16/7/24.
 */
public class OrderDetailVo {
    // 课时id
    private String classInfoId;
    // 课程名称
    private String className;
    // 教练名称
    private String coachName;
    // 教练id
    private String coachId;
    // 价格
    private String price;
    //折扣价格
    private String discountPrice;
    //赠送金额 
    private Integer giftFee;
    //总价 包含赠送金额
    private Integer amount;
    public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	// 订单创建时间
    private String ct;
    //订单支付日期
    private String payDate;
    // 当前系统时间
    private String currentTime;
    // 预约时间
    private String signDate;
    // 订单id
    private String orderId;
    // 订单状态
    private String status;
    // 订单支付时长(秒)
    private int restTime;
    // 订单状态中文展示
    private String statusName;
    // 展示时间
    private String showTime;
    // 课程时间
    private String classTime;
    // 课程类型
    private String classType;
    // 道馆图片/活动图片
    private String photo;
    //卡片名称
    private String cardName;
    //卡片类型
    private Integer cardType;
    //卡片描述
    private String description;
    //卡片状态
    private Integer cardStatus;
    public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

    
	public Integer getCardType() {
		return cardType;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}


	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    // 活动id
    private String activityId;
    // 活动名称
    private String activityName;
    // 活动主办方
    private String hostName;
    // 活动地点
    private String activityAddr;
    // 活动时间
    private String activityTime;
    //  活动人数
    private int activityNum;

    public int getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(int activityNum) {
        this.activityNum = activityNum;
    }

    // 订单类型
    private int orderType;

    // 是否是1元体验
    private int isExperience;

    // 是否评价
    private int isComment;

    // 账户余额
    private String balance;

    // 是否设置密码
    private Integer isPwd;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Integer getIsPwd() {
        return isPwd;
    }

    public void setIsPwd(Integer isPwd) {
        this.isPwd = isPwd;
    }

    public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    public int getIsExperience() {
        return isExperience;
    }

    public void setIsExperience(int isExperience) {
        this.isExperience = isExperience;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    // 道馆id
    private String statiumId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getActivityAddr() {
        return activityAddr;
    }

    public void setActivityAddr(String activityAddr) {
        this.activityAddr = activityAddr;
    }

    public String getStatiumId() {
        return statiumId;
    }

    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCt() {
        return ct;
    }

    public Integer getGiftFee() {
		return giftFee;
	}

	public void setGiftFee(Integer giftFee) {
		this.giftFee = giftFee;
	}

	public void setCt(String ct) {
        this.ct = ct;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }
}
