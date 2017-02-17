package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Organization
* table:c_organization
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 父节点id
     */
    private String pid;

    /**
     * 组织编码，预留
     */
    private String orgCode;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 备注
     */
    private String comment;

    private Integer seq;

    /**
     * 合作者身份ID:签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
     */
    private String partner;

    /**
     * 卖家支付宝账号:卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
     */
    private String sellerId;

    /**
     * 微信公众账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    private String appid;

    /**
     * 微信商户号:微信支付分配的商户号(1900000109)
     */
    private String mchId;

    /**
     * 微信公众平台账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    private String wxAppid;

    /**
     * 微信商户号:微信支付分配的商户号(1900000109)
     */
    private String wxMchId;

    /**
     * 商户的私钥
     */
    private String privateKey;

    /**
     * 支付宝的公钥
     */
    private String aliPublicKey;

    /**
     * 微信密钥
     */
    private String partnerKey;

    /**
     * 微信应用id密钥
     */
    private String appSecret;

    /**
     * 微信公用key
     */
    private String appKey;

    /**
     * 微信公众平台公用key
     */
    private String wxAppKey;

    /**
     * 微信公众平台HTTPS证书的本地路径
     */
    private String wxCertlocalPath;

    /**
     * 微信公众平台HTTPS证书密码，默认密码等于商户号MCHID
     */
    private String wxCertPassword;

    /**
     * HTTPS证书的本地路径
     */
    private String certlocalPath;

    /**
     * HTTPS证书密码，默认密码等于商户号MCHID
     */
    private String certPassword;

    /**
     * 微信公众平台应用id密钥
     */
    private String wxAppSecret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 父节点id
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid 
	 *            父节点id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return 组织编码，预留
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode 
	 *            组织编码，预留
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return 组织名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName 
	 *            组织名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment 
	 *            备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * @return 合作者身份ID:签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
     */
    public String getPartner() {
        return partner;
    }

    /**
     * @param partner 
	 *            合作者身份ID:签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
     */
    public void setPartner(String partner) {
        this.partner = partner;
    }

    /**
     * @return 卖家支付宝账号:卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * @param sellerId 
	 *            卖家支付宝账号:卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * @return 微信公众账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid 
	 *            微信公众账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return 微信商户号:微信支付分配的商户号(1900000109)
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * @param mchId 
	 *            微信商户号:微信支付分配的商户号(1900000109)
     */
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     * @return 微信公众平台账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    public String getWxAppid() {
        return wxAppid;
    }

    /**
     * @param wxAppid 
	 *            微信公众平台账号ID:微信分配的公众账号ID(wx8888888888888888)
     */
    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    /**
     * @return 微信商户号:微信支付分配的商户号(1900000109)
     */
    public String getWxMchId() {
        return wxMchId;
    }

    /**
     * @param wxMchId 
	 *            微信商户号:微信支付分配的商户号(1900000109)
     */
    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }

    /**
     * @return 商户的私钥
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey 
	 *            商户的私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return 支付宝的公钥
     */
    public String getAliPublicKey() {
        return aliPublicKey;
    }

    /**
     * @param aliPublicKey 
	 *            支付宝的公钥
     */
    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    /**
     * @return 微信密钥
     */
    public String getPartnerKey() {
        return partnerKey;
    }

    /**
     * @param partnerKey 
	 *            微信密钥
     */
    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    /**
     * @return 微信应用id密钥
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * @param appSecret 
	 *            微信应用id密钥
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * @return 微信公用key
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * @param appKey 
	 *            微信公用key
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * @return 微信公众平台公用key
     */
    public String getWxAppKey() {
        return wxAppKey;
    }

    /**
     * @param wxAppKey 
	 *            微信公众平台公用key
     */
    public void setWxAppKey(String wxAppKey) {
        this.wxAppKey = wxAppKey;
    }

    /**
     * @return 微信公众平台HTTPS证书的本地路径
     */
    public String getWxCertlocalPath() {
        return wxCertlocalPath;
    }

    /**
     * @param wxCertlocalPath 
	 *            微信公众平台HTTPS证书的本地路径
     */
    public void setWxCertlocalPath(String wxCertlocalPath) {
        this.wxCertlocalPath = wxCertlocalPath;
    }

    /**
     * @return 微信公众平台HTTPS证书密码，默认密码等于商户号MCHID
     */
    public String getWxCertPassword() {
        return wxCertPassword;
    }

    /**
     * @param wxCertPassword 
	 *            微信公众平台HTTPS证书密码，默认密码等于商户号MCHID
     */
    public void setWxCertPassword(String wxCertPassword) {
        this.wxCertPassword = wxCertPassword;
    }

    /**
     * @return HTTPS证书的本地路径
     */
    public String getCertlocalPath() {
        return certlocalPath;
    }

    /**
     * @param certlocalPath 
	 *            HTTPS证书的本地路径
     */
    public void setCertlocalPath(String certlocalPath) {
        this.certlocalPath = certlocalPath;
    }

    /**
     * @return HTTPS证书密码，默认密码等于商户号MCHID
     */
    public String getCertPassword() {
        return certPassword;
    }

    /**
     * @param certPassword 
	 *            HTTPS证书密码，默认密码等于商户号MCHID
     */
    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    /**
     * @return 微信公众平台应用id密钥
     */
    public String getWxAppSecret() {
        return wxAppSecret;
    }

    /**
     * @param wxAppSecret 
	 *            微信公众平台应用id密钥
     */
    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
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
        Organization other = (Organization) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
            && (this.getPartner() == null ? other.getPartner() == null : this.getPartner().equals(other.getPartner()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getMchId() == null ? other.getMchId() == null : this.getMchId().equals(other.getMchId()))
            && (this.getWxAppid() == null ? other.getWxAppid() == null : this.getWxAppid().equals(other.getWxAppid()))
            && (this.getWxMchId() == null ? other.getWxMchId() == null : this.getWxMchId().equals(other.getWxMchId()))
            && (this.getPrivateKey() == null ? other.getPrivateKey() == null : this.getPrivateKey().equals(other.getPrivateKey()))
            && (this.getAliPublicKey() == null ? other.getAliPublicKey() == null : this.getAliPublicKey().equals(other.getAliPublicKey()))
            && (this.getPartnerKey() == null ? other.getPartnerKey() == null : this.getPartnerKey().equals(other.getPartnerKey()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getAppKey() == null ? other.getAppKey() == null : this.getAppKey().equals(other.getAppKey()))
            && (this.getWxAppKey() == null ? other.getWxAppKey() == null : this.getWxAppKey().equals(other.getWxAppKey()))
            && (this.getWxCertlocalPath() == null ? other.getWxCertlocalPath() == null : this.getWxCertlocalPath().equals(other.getWxCertlocalPath()))
            && (this.getWxCertPassword() == null ? other.getWxCertPassword() == null : this.getWxCertPassword().equals(other.getWxCertPassword()))
            && (this.getCertlocalPath() == null ? other.getCertlocalPath() == null : this.getCertlocalPath().equals(other.getCertlocalPath()))
            && (this.getCertPassword() == null ? other.getCertPassword() == null : this.getCertPassword().equals(other.getCertPassword()))
            && (this.getWxAppSecret() == null ? other.getWxAppSecret() == null : this.getWxAppSecret().equals(other.getWxAppSecret()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getPartner() == null) ? 0 : getPartner().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());
        result = prime * result + ((getWxAppid() == null) ? 0 : getWxAppid().hashCode());
        result = prime * result + ((getWxMchId() == null) ? 0 : getWxMchId().hashCode());
        result = prime * result + ((getPrivateKey() == null) ? 0 : getPrivateKey().hashCode());
        result = prime * result + ((getAliPublicKey() == null) ? 0 : getAliPublicKey().hashCode());
        result = prime * result + ((getPartnerKey() == null) ? 0 : getPartnerKey().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getAppKey() == null) ? 0 : getAppKey().hashCode());
        result = prime * result + ((getWxAppKey() == null) ? 0 : getWxAppKey().hashCode());
        result = prime * result + ((getWxCertlocalPath() == null) ? 0 : getWxCertlocalPath().hashCode());
        result = prime * result + ((getWxCertPassword() == null) ? 0 : getWxCertPassword().hashCode());
        result = prime * result + ((getCertlocalPath() == null) ? 0 : getCertlocalPath().hashCode());
        result = prime * result + ((getCertPassword() == null) ? 0 : getCertPassword().hashCode());
        result = prime * result + ((getWxAppSecret() == null) ? 0 : getWxAppSecret().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}