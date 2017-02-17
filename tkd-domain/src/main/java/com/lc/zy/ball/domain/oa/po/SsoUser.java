package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* SsoUser
* table:sso_user
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:37
*/
public class SsoUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名：手机号或者第三方账号
     */
    private String username;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String photo;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份类型
     */
    private Integer identityType;

    /**
     * 身份号
     */
    private String cardId;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户身份：普通，教练，陪练
     */
    private String property;

    /**
     * 运动标签多个用逗号隔开
     */
    private String tags;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 球友号
     */
    private String tkdNo;

    /**
     * 是否认证：1为认证，0为未认证
     */
    private Integer isattestation;

    /**
     * 签名
     */
    private String sign;

    /**
     * 工作
     */
    private String job;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否安装app：0为安装，1为未安装
     */
    private Integer app;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 用户状态：1为正常用户，2删除，3屏蔽用户
     */
    private Integer state;

    /**
     * 身份证照片
     */
    private String certList;

    /**
     * 个人风采
     */
    private String elegantList;

    /**
     * 城市
     */
    private String city;

    /**
     * 激活状态(激活:1,未激活:0)
     */
    private Integer activeState;

    /**
     * 注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    private Integer registSource;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 段位
     */
    private String dan;

    /**
     * 1元体验
     */
    private Integer experience;

    /**
     * 绑定道馆id
     */
    private Integer statiumId;

    /**
     * @return 用户id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            用户id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 用户名：手机号或者第三方账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username 
	 *            用户名：手机号或者第三方账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 
	 *            手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
	 *            密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
	 *            邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo 
	 *            头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName 
	 *            昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex 
	 *            性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday 
	 *            生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return 身份类型
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * @param identityType 
	 *            身份类型
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    /**
     * @return 身份号
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId 
	 *            身份号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * @return 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age 
	 *            年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 用户身份：普通，教练，陪练
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property 
	 *            用户身份：普通，教练，陪练
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return 运动标签多个用逗号隔开
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags 
	 *            运动标签多个用逗号隔开
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return 真实姓名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            真实姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 球友号
     */
    public String getTkdNo() {
        return tkdNo;
    }

    /**
     * @param tkdNo 
	 *            球友号
     */
    public void setTkdNo(String tkdNo) {
        this.tkdNo = tkdNo;
    }

    /**
     * @return 是否认证：1为认证，0为未认证
     */
    public Integer getIsattestation() {
        return isattestation;
    }

    /**
     * @param isattestation 
	 *            是否认证：1为认证，0为未认证
     */
    public void setIsattestation(Integer isattestation) {
        this.isattestation = isattestation;
    }

    /**
     * @return 签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign 
	 *            签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return 工作
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job 
	 *            工作
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return 是否安装app：0为安装，1为未安装
     */
    public Integer getApp() {
        return app;
    }

    /**
     * @param app 
	 *            是否安装app：0为安装，1为未安装
     */
    public void setApp(Integer app) {
        this.app = app;
    }

    /**
     * @return 用户等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level 
	 *            用户等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser 
	 *            修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 用户状态：1为正常用户，2删除，3屏蔽用户
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            用户状态：1为正常用户，2删除，3屏蔽用户
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 身份证照片
     */
    public String getCertList() {
        return certList;
    }

    /**
     * @param certList 
	 *            身份证照片
     */
    public void setCertList(String certList) {
        this.certList = certList;
    }

    /**
     * @return 个人风采
     */
    public String getElegantList() {
        return elegantList;
    }

    /**
     * @param elegantList 
	 *            个人风采
     */
    public void setElegantList(String elegantList) {
        this.elegantList = elegantList;
    }

    /**
     * @return 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 
	 *            城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return 激活状态(激活:1,未激活:0)
     */
    public Integer getActiveState() {
        return activeState;
    }

    /**
     * @param activeState 
	 *            激活状态(激活:1,未激活:0)
     */
    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    /**
     * @return 注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    public Integer getRegistSource() {
        return registSource;
    }

    /**
     * @param registSource 
	 *            注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    public void setRegistSource(Integer registSource) {
        this.registSource = registSource;
    }

    /**
     * @return 注册时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * @param registTime 
	 *            注册时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * @return 段位
     */
    public String getDan() {
        return dan;
    }

    /**
     * @param dan 
	 *            段位
     */
    public void setDan(String dan) {
        this.dan = dan;
    }

    /**
     * @return 1元体验
     */
    public Integer getExperience() {
        return experience;
    }

    /**
     * @param experience 
	 *            1元体验
     */
    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    /**
     * @return 绑定道馆id
     */
    public Integer getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            绑定道馆id
     */
    public void setStatiumId(Integer statiumId) {
        this.statiumId = statiumId;
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
        SsoUser other = (SsoUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getIdentityType() == null ? other.getIdentityType() == null : this.getIdentityType().equals(other.getIdentityType()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getProperty() == null ? other.getProperty() == null : this.getProperty().equals(other.getProperty()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTkdNo() == null ? other.getTkdNo() == null : this.getTkdNo().equals(other.getTkdNo()))
            && (this.getIsattestation() == null ? other.getIsattestation() == null : this.getIsattestation().equals(other.getIsattestation()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getJob() == null ? other.getJob() == null : this.getJob().equals(other.getJob()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getApp() == null ? other.getApp() == null : this.getApp().equals(other.getApp()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCertList() == null ? other.getCertList() == null : this.getCertList().equals(other.getCertList()))
            && (this.getElegantList() == null ? other.getElegantList() == null : this.getElegantList().equals(other.getElegantList()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getActiveState() == null ? other.getActiveState() == null : this.getActiveState().equals(other.getActiveState()))
            && (this.getRegistSource() == null ? other.getRegistSource() == null : this.getRegistSource().equals(other.getRegistSource()))
            && (this.getRegistTime() == null ? other.getRegistTime() == null : this.getRegistTime().equals(other.getRegistTime()))
            && (this.getDan() == null ? other.getDan() == null : this.getDan().equals(other.getDan()))
            && (this.getExperience() == null ? other.getExperience() == null : this.getExperience().equals(other.getExperience()))
            && (this.getStatiumId() == null ? other.getStatiumId() == null : this.getStatiumId().equals(other.getStatiumId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getIdentityType() == null) ? 0 : getIdentityType().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getProperty() == null) ? 0 : getProperty().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTkdNo() == null) ? 0 : getTkdNo().hashCode());
        result = prime * result + ((getIsattestation() == null) ? 0 : getIsattestation().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getJob() == null) ? 0 : getJob().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getApp() == null) ? 0 : getApp().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCertList() == null) ? 0 : getCertList().hashCode());
        result = prime * result + ((getElegantList() == null) ? 0 : getElegantList().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getActiveState() == null) ? 0 : getActiveState().hashCode());
        result = prime * result + ((getRegistSource() == null) ? 0 : getRegistSource().hashCode());
        result = prime * result + ((getRegistTime() == null) ? 0 : getRegistTime().hashCode());
        result = prime * result + ((getDan() == null) ? 0 : getDan().hashCode());
        result = prime * result + ((getExperience() == null) ? 0 : getExperience().hashCode());
        result = prime * result + ((getStatiumId() == null) ? 0 : getStatiumId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}