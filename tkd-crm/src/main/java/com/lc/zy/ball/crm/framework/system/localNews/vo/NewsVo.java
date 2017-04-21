package com.lc.zy.ball.crm.framework.system.localNews.vo;

import com.lc.zy.ball.domain.oa.po.News;

public class NewsVo extends News{

	private static final long serialVersionUID = 1L;
	
	// image
	private String image;
	
	// pDate(发布时间)
	private String pDate;
	
	// 上传图片
	private String[] photo;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String[] getPhoto() {
		return photo;
	}

	public void setPhoto(String[] photo) {
		this.photo = photo;
	}

}
