package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* Message
* table:c_message
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String content;

    /**
     * 发消息人，中文名字，显示在页面上
     */
    private String fromUser;

    /**
     * 收消息人，用户id，取消息时的查询条件
     */
    private String toUser;

    /**
     * 消息的发送时间，按照这个字段倒序
     */
    private Date ct;

    /**
     * 消息发送人的 id
     */
    private String cb;

    /**
     * 消息状态：未读 new、已读 read
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return 发消息人，中文名字，显示在页面上
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * @param fromUser 
	 *            发消息人，中文名字，显示在页面上
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * @return 收消息人，用户id，取消息时的查询条件
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * @param toUser 
	 *            收消息人，用户id，取消息时的查询条件
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * @return 消息的发送时间，按照这个字段倒序
     */
    public Date getCt() {
        return ct;
    }

    /**
     * @param ct 
	 *            消息的发送时间，按照这个字段倒序
     */
    public void setCt(Date ct) {
        this.ct = ct;
    }

    /**
     * @return 消息发送人的 id
     */
    public String getCb() {
        return cb;
    }

    /**
     * @param cb 
	 *            消息发送人的 id
     */
    public void setCb(String cb) {
        this.cb = cb;
    }

    /**
     * @return 消息状态：未读 new、已读 read
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            消息状态：未读 new、已读 read
     */
    public void setStatus(String status) {
        this.status = status;
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
        Message other = (Message) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getFromUser() == null ? other.getFromUser() == null : this.getFromUser().equals(other.getFromUser()))
            && (this.getToUser() == null ? other.getToUser() == null : this.getToUser().equals(other.getToUser()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getFromUser() == null) ? 0 : getFromUser().hashCode());
        result = prime * result + ((getToUser() == null) ? 0 : getToUser().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}