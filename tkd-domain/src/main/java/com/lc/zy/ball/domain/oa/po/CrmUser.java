package com.lc.zy.ball.domain.oa.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
* CrmUser
* table:crm_user
* 
* @author liangc [cc14514@icloud.com]
* @version v1.0
* @copy pet
* @date 2015-09-18 17:04:57
*/
public class CrmUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 组织编码
     */
    private String statiumId;

    /**
     * 用户账号对应的客户ID
     */
    private String custId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    private String loginName;

    private String password;

    private String salt;

    /**
     * 密码安全问题
     */
    private String pwdQuestion;

    /**
     * 密码安全问题答案
     */
    private String pwdAnswer;

    /**
     * 安全问题答案
     */
    private String secEmail;

    /**
     * 安全手机
     */
    private String secMobile;

    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date lastTime;

    /**
     * 上次登录IP
     */
    private String lastIp;

    /**
     * 最近登录时间
     */
    private Date latestTime;

    /**
     * 最近登录IP
     */
    private String latestIp;

    /**
     * 用户状态：启用、停用
     */
    private String status;

    /**
     * 创建人
     */
    private String cb;

    /**
     * 删除标志位
     */
    private Integer deleteFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 组织编码
     */
    public String getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            组织编码
     */
    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
    }

    /**
     * @return 用户账号对应的客户ID
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId 
	 *            用户账号对应的客户ID
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname 
	 *            用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return 真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname 
	 *            真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return 密码安全问题
     */
    public String getPwdQuestion() {
        return pwdQuestion;
    }

    /**
     * @param pwdQuestion 
	 *            密码安全问题
     */
    public void setPwdQuestion(String pwdQuestion) {
        this.pwdQuestion = pwdQuestion;
    }

    /**
     * @return 密码安全问题答案
     */
    public String getPwdAnswer() {
        return pwdAnswer;
    }

    /**
     * @param pwdAnswer 
	 *            密码安全问题答案
     */
    public void setPwdAnswer(String pwdAnswer) {
        this.pwdAnswer = pwdAnswer;
    }

    /**
     * @return 安全问题答案
     */
    public String getSecEmail() {
        return secEmail;
    }

    /**
     * @param secEmail 
	 *            安全问题答案
     */
    public void setSecEmail(String secEmail) {
        this.secEmail = secEmail;
    }

    /**
     * @return 安全手机
     */
    public String getSecMobile() {
        return secMobile;
    }

    /**
     * @param secMobile 
	 *            安全手机
     */
    public void setSecMobile(String secMobile) {
        this.secMobile = secMobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 上次登录时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * @param lastTime 
	 *            上次登录时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * @return 上次登录IP
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     * @param lastIp 
	 *            上次登录IP
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     * @return 最近登录时间
     */
    public Date getLatestTime() {
        return latestTime;
    }

    /**
     * @param latestTime 
	 *            最近登录时间
     */
    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    /**
     * @return 最近登录IP
     */
    public String getLatestIp() {
        return latestIp;
    }

    /**
     * @param latestIp 
	 *            最近登录IP
     */
    public void setLatestIp(String latestIp) {
        this.latestIp = latestIp;
    }

    /**
     * @return 用户状态：启用、停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            用户状态：启用、停用
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return 删除标志位
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            删除标志位
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        CrmUser other = (CrmUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getPwdQuestion() == null ? other.getPwdQuestion() == null : this.getPwdQuestion().equals(other.getPwdQuestion()))
            && (this.getPwdAnswer() == null ? other.getPwdAnswer() == null : this.getPwdAnswer().equals(other.getPwdAnswer()))
            && (this.getSecEmail() == null ? other.getSecEmail() == null : this.getSecEmail().equals(other.getSecEmail()))
            && (this.getSecMobile() == null ? other.getSecMobile() == null : this.getSecMobile().equals(other.getSecMobile()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getLastIp() == null ? other.getLastIp() == null : this.getLastIp().equals(other.getLastIp()))
            && (this.getLatestTime() == null ? other.getLatestTime() == null : this.getLatestTime().equals(other.getLatestTime()))
            && (this.getLatestIp() == null ? other.getLatestIp() == null : this.getLatestIp().equals(other.getLatestIp()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getPwdQuestion() == null) ? 0 : getPwdQuestion().hashCode());
        result = prime * result + ((getPwdAnswer() == null) ? 0 : getPwdAnswer().hashCode());
        result = prime * result + ((getSecEmail() == null) ? 0 : getSecEmail().hashCode());
        result = prime * result + ((getSecMobile() == null) ? 0 : getSecMobile().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getLastIp() == null) ? 0 : getLastIp().hashCode());
        result = prime * result + ((getLatestTime() == null) ? 0 : getLatestTime().hashCode());
        result = prime * result + ((getLatestIp() == null) ? 0 : getLatestIp().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}