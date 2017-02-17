package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatisticRegisterUser
* table:statistic_register_user
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-10-25 16:04:42
*/
public class StatisticRegisterUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String version;

    private String channel;

    /**
     * 日期字符串，粒度到小时，例如 2015-10-01 12<br>
	 * 
     */
    private String ct;

    private Integer counter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return 日期字符串，粒度到小时，例如 2015-10-01 12<br>
	 *         
     */
    public String getCt() {
        return ct;
    }

    /**
     * @param ct 
	 *            日期字符串，粒度到小时，例如 2015-10-01 12<br>
	 *            
     */
    public void setCt(String ct) {
        this.ct = ct;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
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
        StatisticRegisterUser other = (StatisticRegisterUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCounter() == null ? other.getCounter() == null : this.getCounter().equals(other.getCounter()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCounter() == null) ? 0 : getCounter().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}