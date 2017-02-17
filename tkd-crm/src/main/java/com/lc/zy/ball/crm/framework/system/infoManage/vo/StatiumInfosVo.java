package com.lc.zy.ball.crm.framework.system.infoManage.vo;

import com.lc.zy.ball.domain.oa.po.StatiumInfos;

public class StatiumInfosVo extends StatiumInfos {

	private static final long serialVersionUID = 1L;
	//教练姓名
	private String coachName;
	
	//教练电话
	private String coachPhone;
	
	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getCoachPhone() {
		return coachPhone;
	}

	public void setCoachPhone(String coachPhone) {
		this.coachPhone = coachPhone;
	}

	//预定时间
	public String signDate;
	
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String next_page = null;

	public String[] photo;

	public String lnglat = null;
	public String a2;

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String a3;

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public String cnname;

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String a5;

	public String getA5() {
		return a5;
	}

	public void setA5(String a5) {
		this.a5 = a5;
	}

	public String getNext_page() {
		return next_page;
	}

	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}

	public String[] getPhoto() {
		return photo;
	}

	public void setPhoto(String[] photo) {
		this.photo = photo;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

}
