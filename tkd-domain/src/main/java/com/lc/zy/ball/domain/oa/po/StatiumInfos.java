package com.lc.zy.ball.domain.oa.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* StatiumInfos
* table:oa_statium_infos
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-10-14 11:31:29
*/
public class StatiumInfos implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 道馆名称
     */
    private String dgName;

    /**
     * 道馆ID
     */
    private Integer dgId;

    /**
     * 道馆相册
     */
    private String photos;

    /**
     * 场馆公告
     */
    private String remark;

    /**
     * 场馆经度
     */
    private Double lng;

    /**
     * 场馆纬度
     */
    private Double lat;

    /**
     * 场馆地址
     */
    private String address;

    /**
     * 道馆联系人
     */
    private String contact;

    /**
     * 馆长电话
     */
    private String tel;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 场馆所在地市
     */
    private String areacode;

    /**
     * 销售数量
     */
    private Integer salecount;

    /**
     * 营业开始时间
     */
    private String starttime;

    /**
     * 营业结束时间
     */
    private String endtime;

    /**
     * 道馆logo
     */
    private String logo;

    private Date ct;

    private String cb;

    private Date et;

    private String eb;

    /**
     * 道馆是否被推荐 0不被推荐 1被推荐
     */
    private Integer recommend;

    /**
     * 客服电话
     */
    private String serviceTel;

    /**
     * 开户人姓名
     */
    private String bankAccountName;

    /**
     * 开户人账户
     */
    private String bankAccountNo;

    /**
     * 开户银行
     */
    private String bankAccountBranchName;

    /**
     * 道馆审核状态 0：待审核 1 ：已审核 2 ：审核不通过
     */
    private Integer status;

    /**
     * 评星数
     */
    private Integer grade;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 道馆名称
     */
    public String getDgName() {
        return dgName;
    }

    /**
     * @param dgName 
	 *            道馆名称
     */
    public void setDgName(String dgName) {
        this.dgName = dgName;
    }

    /**
     * @return 道馆ID
     */
    public Integer getDgId() {
        return dgId;
    }

    /**
     * @param dgId 
	 *            道馆ID
     */
    public void setDgId(Integer dgId) {
        this.dgId = dgId;
    }

    /**
     * @return 道馆相册
     */
    public String getPhotos() {
        return photos;
    }

    /**
     * @param photos 
	 *            道馆相册
     */
    public void setPhotos(String photos) {
        this.photos = photos;
    }

    /**
     * @return 场馆公告
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            场馆公告
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 场馆经度
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @param lng 
	 *            场馆经度
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * @return 场馆纬度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat 
	 *            场馆纬度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * @return 场馆地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            场馆地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 道馆联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact 
	 *            道馆联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return 馆长电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel 
	 *            馆长电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province 
	 *            省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return 市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 
	 *            市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return 区
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area 
	 *            区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return 场馆所在地市
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * @param areacode 
	 *            场馆所在地市
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * @return 销售数量
     */
    public Integer getSalecount() {
        return salecount;
    }

    /**
     * @param salecount 
	 *            销售数量
     */
    public void setSalecount(Integer salecount) {
        this.salecount = salecount;
    }

    /**
     * @return 营业开始时间
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime 
	 *            营业开始时间
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return 营业结束时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime 
	 *            营业结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * @return 道馆logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo 
	 *            道馆logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getEb() {
        return eb;
    }

    public void setEb(String eb) {
        this.eb = eb;
    }

    /**
     * @return 道馆是否被推荐 0不被推荐 1被推荐
     */
    public Integer getRecommend() {
        return recommend;
    }

    /**
     * @param recommend 
	 *            道馆是否被推荐 0不被推荐 1被推荐
     */
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    /**
     * @return 客服电话
     */
    public String getServiceTel() {
        return serviceTel;
    }

    /**
     * @param serviceTel 
	 *            客服电话
     */
    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    /**
     * @return 开户人姓名
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * @param bankAccountName 
	 *            开户人姓名
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     * @return 开户人账户
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * @param bankAccountNo 
	 *            开户人账户
     */
    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    /**
     * @return 开户银行
     */
    public String getBankAccountBranchName() {
        return bankAccountBranchName;
    }

    /**
     * @param bankAccountBranchName 
	 *            开户银行
     */
    public void setBankAccountBranchName(String bankAccountBranchName) {
        this.bankAccountBranchName = bankAccountBranchName;
    }

    /**
     * @return 道馆审核状态 0：待审核 1 ：已审核 2 ：审核不通过
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            道馆审核状态 0：待审核 1 ：已审核 2 ：审核不通过
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 评星数
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade 
	 *            评星数
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * @return 二维码
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * @param qrCode 
	 *            二维码
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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
        StatiumInfos other = (StatiumInfos) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDgName() == null ? other.getDgName() == null : this.getDgName().equals(other.getDgName()))
            && (this.getDgId() == null ? other.getDgId() == null : this.getDgId().equals(other.getDgId()))
            && (this.getPhotos() == null ? other.getPhotos() == null : this.getPhotos().equals(other.getPhotos()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getLng() == null ? other.getLng() == null : this.getLng().equals(other.getLng()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getAreacode() == null ? other.getAreacode() == null : this.getAreacode().equals(other.getAreacode()))
            && (this.getSalecount() == null ? other.getSalecount() == null : this.getSalecount().equals(other.getSalecount()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getCb() == null ? other.getCb() == null : this.getCb().equals(other.getCb()))
            && (this.getEt() == null ? other.getEt() == null : this.getEt().equals(other.getEt()))
            && (this.getEb() == null ? other.getEb() == null : this.getEb().equals(other.getEb()))
            && (this.getRecommend() == null ? other.getRecommend() == null : this.getRecommend().equals(other.getRecommend()))
            && (this.getServiceTel() == null ? other.getServiceTel() == null : this.getServiceTel().equals(other.getServiceTel()))
            && (this.getBankAccountName() == null ? other.getBankAccountName() == null : this.getBankAccountName().equals(other.getBankAccountName()))
            && (this.getBankAccountNo() == null ? other.getBankAccountNo() == null : this.getBankAccountNo().equals(other.getBankAccountNo()))
            && (this.getBankAccountBranchName() == null ? other.getBankAccountBranchName() == null : this.getBankAccountBranchName().equals(other.getBankAccountBranchName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getQrCode() == null ? other.getQrCode() == null : this.getQrCode().equals(other.getQrCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDgName() == null) ? 0 : getDgName().hashCode());
        result = prime * result + ((getDgId() == null) ? 0 : getDgId().hashCode());
        result = prime * result + ((getPhotos() == null) ? 0 : getPhotos().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getLng() == null) ? 0 : getLng().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getAreacode() == null) ? 0 : getAreacode().hashCode());
        result = prime * result + ((getSalecount() == null) ? 0 : getSalecount().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getCb() == null) ? 0 : getCb().hashCode());
        result = prime * result + ((getEt() == null) ? 0 : getEt().hashCode());
        result = prime * result + ((getEb() == null) ? 0 : getEb().hashCode());
        result = prime * result + ((getRecommend() == null) ? 0 : getRecommend().hashCode());
        result = prime * result + ((getServiceTel() == null) ? 0 : getServiceTel().hashCode());
        result = prime * result + ((getBankAccountName() == null) ? 0 : getBankAccountName().hashCode());
        result = prime * result + ((getBankAccountNo() == null) ? 0 : getBankAccountNo().hashCode());
        result = prime * result + ((getBankAccountBranchName() == null) ? 0 : getBankAccountBranchName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getQrCode() == null) ? 0 : getQrCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}