package com.lc.zy.common.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页用
 * @author liangc
 *
 * @param <T>
 */
public class PageBean<T> {
	
	public int pageSize = 10;
	
	public int pageNo = 0;
	
	public long totalCount = 0;
	
	public int totalPage = 0;
	
	public List<T> data = new ArrayList<T>();

	
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		setTotalPage((int)((totalCount+pageSize-1)/pageSize));
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageBean [pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", data=" + data + "]";
	}
	
}
