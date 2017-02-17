package com.lc.zy.ball.app.service.activity.bean;

/**
 * Created by sl on 16/8/2.
 */
public class StatiumActivityMemberVo {

    // 用户id
    private String userId;
    // 用户头像
    private String photo;
    // 用户段位
    private String dan;
    // 性别
    private String sex;
    // 昵称
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }
}
