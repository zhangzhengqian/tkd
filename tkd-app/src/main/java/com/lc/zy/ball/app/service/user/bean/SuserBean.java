package com.lc.zy.ball.app.service.user.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

import com.lc.zy.common.search.SearchBean;

/**
* SsoUser
* table:sso_user
* 
* @author liangc [cc14514@icloud.com]
* @version v1.0
* @copy pet
* @date 2015-08-04 11:42:31
*/
public class SuserBean implements SearchBean {
    private static final long serialVersionUID = 1L;
    
    @Field
    private Double dist;
    
    @Field
    private Double lat;

    @Field
    private Double lng;
    
    @Field
    private String geo;

    /**
     * 用户id
     */
    @Field
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
    @Field
    private String photo;

    /**
     * 昵称
     */
    @Field
    private String nickName;

    /**
     * 性别
     */
    @Field
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
    @Field
    private String property;

    /**
     * 运动标签多个用逗号隔开
     */
    @Field
    private String tags;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 积分：默认为0
     */
    private Integer integral;

    /**
     * 球友号
     */
    @Field
    private String qiuyouno;

    /**
     * 是否认证：1为认证，0为未认证
     */
    private Integer isattestation;

    /**
     * 签名
     */
    @Field
    private String sign;

    /**
     * 工作
     */
    @Field
    private String job;

    /**
     * 是否安装app：0为安装，1为未安装
     */
    private Integer app;

    /**
     * 用户等级
     */
    @Field
    private Integer level;

    /**
     * 账户类型：1支付宝，2微信支付等等
     */
    private Byte accountType;

    /**
     * 微信账号
     */
    private String weixinAccount;

    /**
     * 收款账号
     */
    private String receivablesAccount;

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
     * 设备类型：1为ios，2为android，3为winphone
     */
    private Integer deviceType;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 场馆id
     */
    private String statiumId;

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
    @Field
    private String elegantList;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 城市
     */
    @Field
    private String city;

    /**
     * 金币金额
     */
    private Integer goldAmount;

    /**
     * 激活状态(激活:1,未激活:0)
     */
    private String activeState;

    /**
     * 注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    private String registSource;

    /**
     * 注册时间
     */
    private Date registTime;

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
     * @return 积分：默认为0
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral 
	 *            积分：默认为0
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return 球友号
     */
    public String getQiuyouno() {
        return qiuyouno;
    }

    /**
     * @param qiuyouno 
	 *            球友号
     */
    public void setQiuyouno(String qiuyouno) {
        this.qiuyouno = qiuyouno;
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
     * @return 账户类型：1支付宝，2微信支付等等
     */
    public Byte getAccountType() {
        return accountType;
    }

    /**
     * @param accountType 
	 *            账户类型：1支付宝，2微信支付等等
     */
    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    /**
     * @return 微信账号
     */
    public String getWeixinAccount() {
        return weixinAccount;
    }

    /**
     * @param weixinAccount 
	 *            微信账号
     */
    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    /**
     * @return 收款账号
     */
    public String getReceivablesAccount() {
        return receivablesAccount;
    }

    /**
     * @param receivablesAccount 
	 *            收款账号
     */
    public void setReceivablesAccount(String receivablesAccount) {
        this.receivablesAccount = receivablesAccount;
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
     * @return 设备类型：1为ios，2为android，3为winphone
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType 
	 *            设备类型：1为ios，2为android，3为winphone
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return 设备id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId 
	 *            设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return 场馆id
     */
    public String getStatiumId() {
        return statiumId;
    }

    /**
     * @param statiumId 
	 *            场馆id
     */
    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
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
     * @return 金币金额
     */
    public Integer getGoldAmount() {
        return goldAmount;
    }

    /**
     * @param goldAmount 
	 *            金币金额
     */
    public void setGoldAmount(Integer goldAmount) {
        this.goldAmount = goldAmount;
    }

    /**
     * @return 激活状态(激活:1,未激活:0)
     */
    public String getActiveState() {
        return activeState;
    }

    /**
     * @param activeState 
	 *            激活状态(激活:1,未激活:0)
     */
    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }

    /**
     * @return 注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    public String getRegistSource() {
        return registSource;
    }

    /**
     * @param registSource 
	 *            注册来源(App:0,微信、qq、新浪微博:1、网站:2、平台:3、球馆:4)
     */
    public void setRegistSource(String registSource) {
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
    

    public Double getDist() {
		return dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	@Override
    public String toString() {
        return "SuserBean [id=" + id + ", photo=" + photo + ", nickName=" + nickName + ", sex=" + sex + ", property="
                + property + ", tags=" + tags + ", qiuyouno=" + qiuyouno + ", sign=" + sign + ", job=" + job
                + ", level=" + level + ", elegantList=" + elegantList + "]";
    }

    @Override
    public String getRestUrl() {
        return "http://192.168.12.213:8081/solr/core1";
    }

}