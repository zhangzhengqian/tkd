package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* DicItem
* table:c_dic_item
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class DicItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典项ID
     */
    private String itemId;

    /**
     * 字典ID
     */
    private String dicId;

    /**
     * 字典项编码
     */
    private String itemCode;

    /**
     * 字典项名称
     */
    private String itemName;

    /**
     * 顺序号
     */
    private Integer seqNum;

    /**
     * @return 字典项ID
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId 
	 *            字典项ID
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return 字典ID
     */
    public String getDicId() {
        return dicId;
    }

    /**
     * @param dicId 
	 *            字典ID
     */
    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    /**
     * @return 字典项编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode 
	 *            字典项编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return 字典项名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName 
	 *            字典项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return 顺序号
     */
    public Integer getSeqNum() {
        return seqNum;
    }

    /**
     * @param seqNum 
	 *            顺序号
     */
    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
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
        DicItem other = (DicItem) that;
        return (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getDicId() == null ? other.getDicId() == null : this.getDicId().equals(other.getDicId()))
            && (this.getItemCode() == null ? other.getItemCode() == null : this.getItemCode().equals(other.getItemCode()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getSeqNum() == null ? other.getSeqNum() == null : this.getSeqNum().equals(other.getSeqNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getDicId() == null) ? 0 : getDicId().hashCode());
        result = prime * result + ((getItemCode() == null) ? 0 : getItemCode().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getSeqNum() == null) ? 0 : getSeqNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}