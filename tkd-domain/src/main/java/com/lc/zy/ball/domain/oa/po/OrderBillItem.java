package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* OrderBillItem
* table:oa_order_bill_item
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 17:43:14
*/
public class OrderBillItem extends OrderBillItemKey implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账单关联
     */
    private String orderBillId;

    /**
     * 统计日期
     */
    private String startDate;

    /**
     * 实付金额
     */
    private Integer finalFee;

    /**
     * 优惠券补贴
     */
    private Integer subAmount;

    /**
     * 球友圈补贴
     */
    private Integer subsidyAmount;

    /**
     * 总金额
     */
    private Integer totalFee;

    /**
     * @return 账单关联
     */
    public String getOrderBillId() {
        return orderBillId;
    }

    /**
     * @param orderBillId 
	 *            账单关联
     */
    public void setOrderBillId(String orderBillId) {
        this.orderBillId = orderBillId;
    }

    /**
     * @return 统计日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate 
	 *            统计日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return 实付金额
     */
    public Integer getFinalFee() {
        return finalFee;
    }

    /**
     * @param finalFee 
	 *            实付金额
     */
    public void setFinalFee(Integer finalFee) {
        this.finalFee = finalFee;
    }

    /**
     * @return 优惠券补贴
     */
    public Integer getSubAmount() {
        return subAmount;
    }

    /**
     * @param subAmount 
	 *            优惠券补贴
     */
    public void setSubAmount(Integer subAmount) {
        this.subAmount = subAmount;
    }

    /**
     * @return 球友圈补贴
     */
    public Integer getSubsidyAmount() {
        return subsidyAmount;
    }

    /**
     * @param subsidyAmount 
	 *            球友圈补贴
     */
    public void setSubsidyAmount(Integer subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    /**
     * @return 总金额
     */
    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee 
	 *            总金额
     */
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
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
        OrderBillItem other = (OrderBillItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderCount() == null ? other.getOrderCount() == null : this.getOrderCount().equals(other.getOrderCount()))
            && (this.getOrderBillId() == null ? other.getOrderBillId() == null : this.getOrderBillId().equals(other.getOrderBillId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getFinalFee() == null ? other.getFinalFee() == null : this.getFinalFee().equals(other.getFinalFee()))
            && (this.getSubAmount() == null ? other.getSubAmount() == null : this.getSubAmount().equals(other.getSubAmount()))
            && (this.getSubsidyAmount() == null ? other.getSubsidyAmount() == null : this.getSubsidyAmount().equals(other.getSubsidyAmount()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderCount() == null) ? 0 : getOrderCount().hashCode());
        result = prime * result + ((getOrderBillId() == null) ? 0 : getOrderBillId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getFinalFee() == null) ? 0 : getFinalFee().hashCode());
        result = prime * result + ((getSubAmount() == null) ? 0 : getSubAmount().hashCode());
        result = prime * result + ((getSubsidyAmount() == null) ? 0 : getSubsidyAmount().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}