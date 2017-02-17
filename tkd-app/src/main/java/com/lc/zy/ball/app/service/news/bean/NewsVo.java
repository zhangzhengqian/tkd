package com.lc.zy.ball.app.service.news.bean;

import com.lc.zy.ball.domain.oa.po.News;

public class NewsVo extends News {
	
	private static final long serialVersionUID = 1L;
	
	// 发布日期
	private String pDate;

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

}
