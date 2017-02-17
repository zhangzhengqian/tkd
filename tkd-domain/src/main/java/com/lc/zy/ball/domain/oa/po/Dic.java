package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Dic
* table:c_dic
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class Dic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    private String dicId;

    /**
     * 字典编码
     */
    private String dicCode;

    /**
     * 字典名称
     */
    private String dicName;

    /**
     * 上级字典ID
     */
    private String parentId;

    private Long seqNum;

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
     * @return 字典编码
     */
    public String getDicCode() {
        return dicCode;
    }

    /**
     * @param dicCode 
	 *            字典编码
     */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    /**
     * @return 字典名称
     */
    public String getDicName() {
        return dicName;
    }

    /**
     * @param dicName 
	 *            字典名称
     */
    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    /**
     * @return 上级字典ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            上级字典ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Long seqNum) {
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
        Dic other = (Dic) that;
        return (this.getDicId() == null ? other.getDicId() == null : this.getDicId().equals(other.getDicId()))
            && (this.getDicCode() == null ? other.getDicCode() == null : this.getDicCode().equals(other.getDicCode()))
            && (this.getDicName() == null ? other.getDicName() == null : this.getDicName().equals(other.getDicName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getSeqNum() == null ? other.getSeqNum() == null : this.getSeqNum().equals(other.getSeqNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDicId() == null) ? 0 : getDicId().hashCode());
        result = prime * result + ((getDicCode() == null) ? 0 : getDicCode().hashCode());
        result = prime * result + ((getDicName() == null) ? 0 : getDicName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getSeqNum() == null) ? 0 : getSeqNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}