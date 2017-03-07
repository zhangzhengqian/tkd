package com.lc.zy.ball.boss.framework.qiuyouzone.vo;

import com.lc.zy.ball.domain.oa.po.Qiuyouzone;

public class QIuyouzoneVo extends Qiuyouzone{
	
	private static final long serialVersionUID = 2234695103947287188L;
	
	private String[] photo;
	
	private String labelId;
	
	private String tempTime;
	
	private String statiumName;
	
	public String[] getPhoto() {
		return photo;
	}
	public void setPhoto(String[] photo) {
		this.photo = photo;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getTempTime() {
		return tempTime;
	}
	public void setTempTime(String tempTime) {
		this.tempTime = tempTime;
	}
	public String getStatiumName() {
		return statiumName;
	}
	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}
	
}
