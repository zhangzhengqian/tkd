package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* EmsgServer
* table:oa_emsg_server
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-16 17:30:32
*/
public class EmsgServer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 接受的域
     */
    private String domain;

    private String license;

    /**
     * 创建人id
     */
    private String cb;

    /**
     * 创建时间
     */
    private Date ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 主机地址
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host 
	 *            主机地址
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return 端口
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port 
	 *            端口
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return 接受的域
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain 
	 *            接受的域
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * @return 创建人id
     */
    public String getCb() {
        return cb;
    }

    /**
     * @param cb 
	 *            创建人id
     */
    public void setCb(String cb) {
        this.cb = cb;
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
        EmsgServer other = (EmsgServer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getDomain() == null ? other.getDomain() == null : this.getDomain().equals(other.getDomain()))
            && (this.getLicense() == null ? other.getLicense() == null : this.getLicense().equals(other.getLicense()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getDomain() == null) ? 0 : getDomain().hashCode());
        result = prime * result + ((getLicense() == null) ? 0 : getLicense().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}