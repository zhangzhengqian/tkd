package com.lc.zy.ball.boss.framework.statium.vo;

import com.lc.zy.ball.domain.oa.po.StatiumAuditLog;

public class StatiumAuditLogVo extends StatiumAuditLog{
	private Integer page;
	
	private Integer pageSize;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
