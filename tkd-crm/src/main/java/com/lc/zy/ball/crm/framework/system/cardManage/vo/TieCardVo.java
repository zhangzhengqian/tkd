package com.lc.zy.ball.crm.framework.system.cardManage.vo;

public class TieCardVo {
	// 姓名
    private String name;
    // 手机
    private String phone;
    //头像
    private String photo;
    //性别
    private String sex;
    //地址
    private String address;
    //身份证号
    private String cardNo;
    //邮箱
    private String email;
    // 卡片类型
    private Integer cardType;
    // 余额
    private int balance;
    // 期限
    private String cardTime;
    // 截止日期
    private String oldEndDate;
    // 用户id
    private String userId;
    // 卡片id
    private String cardId;
    // 期限卡开始日期
    private String startDate;
    // 期限卡到期日期
    private String endDate;
    // 面值
    private int cardAmount;
    // 赠送金额
    private int cardGift;
    // 订单号
    private String orderId;
    // 账户id
    private String accountId;
    // 开始日期
    private String oldStartDate;
    // 卡号
    private String cardNum;
    public String getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(String oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public String getOldEndDate() {
        return oldEndDate;
    }

    public void setOldEndDate(String oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public int getCardGift() {
        return cardGift;
    }

    public void setCardGift(int cardGift) {
        this.cardGift = cardGift;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
