package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* ManageSso
* table:oa_manage_sso
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class ManageSso implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * c_user
     */
    private String userId;

    private String ssoUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return c_user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            c_user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSsoUserId() {
        return ssoUserId;
    }

    public void setSsoUserId(String ssoUserId) {
        this.ssoUserId = ssoUserId;
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
        ManageSso other = (ManageSso) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSsoUserId() == null ? other.getSsoUserId() == null : this.getSsoUserId().equals(other.getSsoUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSsoUserId() == null) ? 0 : getSsoUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}