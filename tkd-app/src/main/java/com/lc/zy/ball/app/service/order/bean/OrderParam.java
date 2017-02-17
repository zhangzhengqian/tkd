package com.lc.zy.ball.app.service.order.bean;

/**
 * Created by sl on 16/7/24.
 */
public class OrderParam {

    // 课时id
    private String classInfoId;
    // 课程日期/活动日期
    private String classDate;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
    // 道馆id
    private String statiumId;
    // 课程价格
    private String price;
    //课程折扣价格
    private String discountPrice;
    // 活动id
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatiumId() {
        return statiumId;
    }

    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
