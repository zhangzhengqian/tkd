package com.lc.zy.ball.app.service.statium.bean;

import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfo;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;

import java.util.List;

/**
 * 
 * @author sl
 *
 */
public class StatiumInfoVo extends StatiumInfos {

	private static final long serialVersionUID = 1L;

	// 教练
	private List<Coach> coachs;

	// 课程时间安排
	private List<StatiumClassInfo> statiumClassInfos;

	private List<ClassInfoVo> classInfoVos;
	
	// 课程安排
	private String classNames;

	// 活动
	private List<StatiumActivityListVo> statiumActivityListVos;

	// 判断用户是否有1元体验次数
	private int isExperience;

	public int getIsExperience() {
		return isExperience;
	}

	public void setIsExperience(int isExperience) {
		this.isExperience = isExperience;
	}

	public List<StatiumActivityListVo> getStatiumActivityListVos() {
		return statiumActivityListVos;
	}

	public void setStatiumActivityListVos(List<StatiumActivityListVo> statiumActivityListVos) {
		this.statiumActivityListVos = statiumActivityListVos;
	}

	private List<ClassListVo> classListVos;

	public List<ClassListVo> getClassListVos() {
		return classListVos;
	}

	public void setClassListVos(List<ClassListVo> classListVos) {
		this.classListVos = classListVos;
	}

	public List<Coach> getCoachs() {
		return coachs;
	}

	public void setCoachs(List<Coach> coachs) {
		this.coachs = coachs;
	}

	public List<StatiumClassInfo> getStatiumClassInfos() {
		return statiumClassInfos;
	}

	public void setStatiumClassInfos(List<StatiumClassInfo> statiumClassInfos) {
		this.statiumClassInfos = statiumClassInfos;
	}

	public List<ClassInfoVo> getClassInfoVos() {
		return classInfoVos;
	}

	public void setClassInfoVos(List<ClassInfoVo> classInfoVos) {
		this.classInfoVos = classInfoVos;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

}
