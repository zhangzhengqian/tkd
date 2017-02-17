package com.lc.zy.ball.app.service.coach.bean;

import java.util.List;

import com.lc.zy.ball.app.service.statium.bean.ClassDetalVo;
import com.lc.zy.ball.domain.oa.po.Coach;

public class CoachVo extends Coach {

	private static final long serialVersionUID = 1L;
	
	// 课程
	private List<ClassDetalVo> classDetalVos;
	
	// 场馆介绍
	private String introduce;
	
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public List<ClassDetalVo> getClassDetalVos() {
		return classDetalVos;
	}
	public void setClassDetalVos(List<ClassDetalVo> classDetalVos) {
		this.classDetalVos = classDetalVos;
	}

}
