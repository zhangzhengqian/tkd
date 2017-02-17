package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Order
* table:oa_order
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-30 11:17:12
*/
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 道馆id
     */
    private Integer statiumId;

    /**
     * 订单状态:ORDER_NEW：新订单，ORDER_PLAYING：已开场 ，ORDER_CANCELED：已撤销订单，已完成订单：ORDER_BILLED，已删除订单：ORDER_DELETED，已支付订单：ORDER_PAIED，退款中订单:ORDER_REFUNDING，已退款:ORDER_REFUNDED，已确认订单:ORDER_VERIFY
     */
    private String status;

    /**
     * 订单类型：现场消费、电话预订
     */
    private String orderType;

    /**
     * 订单金额，单位：分
     */
    private Integer fee;

    /**
     * 实收费用：
     */
    private Integer finalFee;

    /**
     * 订单说明，结算时可以用来增加说明
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date ct;

    /**
     * 创建人
     */
    private String cb;

    /**
     * 修改时间
     */
    private Date et;

    /**
     * 创建人
     */
    private String eb;

    /**
     * 支付宝或者微信支付订单号
     */
    private String number;

    /**
     * 订单类型：1为支付宝，2为微信
     */
    private Integer payType;

    /**
     * 订单类型：课程：0、活动：1
     */
    private Integer ordersType;

    /**
     * 退款批次号
     */
    private String refundBatchNo;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 地址
     */
    private String address;

    private Integer isCheck;

    private Date checkDate;

    /**
     * 0:app 1:web 2:微信 3:线下
     */
    private Integer channel;

    private Integer handleStatus;

    private String handler;

    /**
     * 是否确认
     */
    private Integer verifyFlag;

    /**
     * 是否评论：0未评论1已评论
     */
    private Integer isComment;

    /**
     * 课程类型：0:大课、1:私教
     */
    private Integer classType;

    /**
     * 是否是1元体验价 0:否 1:是
     */
    private Integer isExperience;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 道馆id
     */
    public Integer getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            道馆id
     */
    public void setStatiumId(Integer statiumId) {
        this.statiumId = statiumId;
    }

    /**
     * @return 订单状态:ORDER_NEW：新订单，ORDER_PLAYING：已开场 ，ORDER_CANCELED：已撤销订单，已完成订单：ORDER_BILLED，已删除订单：ORDER_DELETED，已支付订单：ORDER_PAIED，退款中订单:ORDER_REFUNDING，已退款:ORDER_REFUNDED，已确认订单:ORDER_VERIFY
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            订单状态:ORDER_NEW：新订单，ORDER_PLAYING：已开场 ，ORDER_CANCELED：已撤销订单，已完成订单：ORDER_BILLED，已删除订单：ORDER_DELETED，已支付订单：ORDER_PAIED，退款中订单:ORDER_REFUNDING，已退款:ORDER_REFUNDED，已确认订单:ORDER_VERIFY
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 订单类型：现场消费、电话预订
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            订单类型：现场消费、电话预订
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 订单金额，单位：分
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * @param fee 
	 *            订单金额，单位：分
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * @return 实收费用：
     */
    public Integer getFinalFee() {
        return finalFee;
    }

    /**
     * @param finalFee 
	 *            实收费用：
     */
    public void setFinalFee(Integer finalFee) {
        this.finalFee = finalFee;
    }

    /**
     * @return 订单说明，结算时可以用来增加说明
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment 
	 *            订单说明，结算时可以用来增加说明
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return 创建时间
     */
    public Date getCt() {
        return ct;
    }

    /**
     * @param ct 
	 *            创建时间
     */
    public void setCt(Date ct) {
        this.ct = ct;
    }

    /**
     * @return 创建人
     */
    public String getCb() {
        return cb;
    }

    /**
     * @param cb 
	 *            创建人
     */
    public void setCb(String cb) {
        this.cb = cb;
    }

    /**
     * @return 修改时间
     */
    public Date getEt() {
        return et;
    }

    /**
     * @param et 
	 *            修改时间
     */
    public void setEt(Date et) {
        this.et = et;
    }

    /**
     * @return 创建人
     */
    public String getEb() {
        return eb;
    }

    /**
     * @param eb 
	 *            创建人
     */
    public void setEb(String eb) {
        this.eb = eb;
    }

    /**
     * @return 支付宝或者微信支付订单号
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number 
	 *            支付宝或者微信支付订单号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return 订单类型：1为支付宝，2为微信
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * @param payType 
	 *            订单类型：1为支付宝，2为微信
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * @return 订单类型：课程：0、活动：1
     */
    public Integer getOrdersType() {
        return ordersType;
    }

    /**
     * @param ordersType 
	 *            订单类型：课程：0、活动：1
     */
    public void setOrdersType(Integer ordersType) {
        this.ordersType = ordersType;
    }

    /**
     * @return 退款批次号
     */
    public String getRefundBatchNo() {
        return refundBatchNo;
    }

    /**
     * @param refundBatchNo 
	 *            退款批次号
     */
    public void setRefundBatchNo(String refundBatchNo) {
        this.refundBatchNo = refundBatchNo;
    }

    /**
     * @return 退款原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason 
	 *            退款原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return 交易流水号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo 
	 *            交易流水号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * @return 0:app 1:web 2:微信 3:线下
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * @param channel 
	 *            0:app 1:web 2:微信 3:线下
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    /**
     * @return 是否确认
     */
    public Integer getVerifyFlag() {
        return verifyFlag;
    }

    /**
     * @param verifyFlag 
	 *            是否确认
     */
    public void setVerifyFlag(Integer verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    /**
     * @return 是否评论：0未评论1已评论
     */
    public Integer getIsComment() {
        return isComment;
    }

    /**
     * @param isComment 
	 *            是否评论：0未评论1已评论
     */
    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    /**
     * @return 课程类型：0:大课、1:私教
     */
    public Integer getClassType() {
        return classType;
    }

    /**
     * @param classType 
	 *            课程类型：0:大课、1:私教
     */
    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    /**
     * @return 是否是1元体验价 0:否 1:是
     */
    public Integer getIsExperience() {
        return isExperience;
    }

    /**
     * @param isExperience 
	 *            是否是1元体验价 0:否 1:是
     */
    public void setIsExperience(Integer isExperience) {
        this.isExperience = isExperience;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getFinalFee() == null ? other.getFinalFee() == null : this.getFinalFee().equals(other.getFinalFee()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getOrdersType() == null ? other.getOrdersType() == null : this.getOrdersType().equals(other.getOrdersType()))
            && (this.getRefundBatchNo() == null ? other.getRefundBatchNo() == null : this.getRefundBatchNo().equals(other.getRefundBatchNo()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIsCheck() == null ? other.getIsCheck() == null : this.getIsCheck().equals(other.getIsCheck()))
            && (this.getCheckDate() == null ? other.getCheckDate() == null : this.getCheckDate().equals(other.getCheckDate()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getHandleStatus() == null ? other.getHandleStatus() == null : this.getHandleStatus().equals(other.getHandleStatus()))
            && (this.getHandler() == null ? other.getHandler() == null : this.getHandler().equals(other.getHandler()))
            && (this.getVerifyFlag() == null ? other.getVerifyFlag() == null : this.getVerifyFlag().equals(other.getVerifyFlag()))
            && (this.getIsComment() == null ? other.getIsComment() == null : this.getIsComment().equals(other.getIsComment()))
            && (this.getClassType() == null ? other.getClassType() == null : this.getClassType().equals(other.getClassType()))
            && (this.getIsExperience() == null ? other.getIsExperience() == null : this.getIsExperience().equals(other.getIsExperience()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getFinalFee() == null) ? 0 : getFinalFee().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getOrdersType() == null) ? 0 : getOrdersType().hashCode());
        result = prime * result + ((getRefundBatchNo() == null) ? 0 : getRefundBatchNo().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIsCheck() == null) ? 0 : getIsCheck().hashCode());
        result = prime * result + ((getCheckDate() == null) ? 0 : getCheckDate().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getHandleStatus() == null) ? 0 : getHandleStatus().hashCode());
        result = prime * result + ((getHandler() == null) ? 0 : getHandler().hashCode());
        result = prime * result + ((getVerifyFlag() == null) ? 0 : getVerifyFlag().hashCode());
        result = prime * result + ((getIsComment() == null) ? 0 : getIsComment().hashCode());
        result = prime * result + ((getClassType() == null) ? 0 : getClassType().hashCode());
        result = prime * result + ((getIsExperience() == null) ? 0 : getIsExperience().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}