package com.lc.zy.ball.app.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页用
 * 
 * @author cyy
 *
 * @param <T>
 */
public class Page<T> {

    private int pageSize = 10;

    private int pageNo = 0;

    private long totalCount = 0;

    private int totalPage = 0;

    private List<T> data = new ArrayList<T>();

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
        setTotalPage((int) ((totalCount + pageSize - 1) / pageSize));
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

    // 每页开始数
    public int getPageBeginNo() {
        return this.pageNo * this.pageSize;
    }

    @Override
    public String toString() {
        return "Page [pageSize=" + pageSize + ", pageNo=" + pageNo + ", totalCount=" + totalCount + ", totalPage="
                + totalPage + ", data=" + data + "]";
    }

}
