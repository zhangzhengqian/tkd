package com.lc.zy.ball.boss.framework.statium.vo;

import java.util.ArrayList;
import java.util.List;

import com.lc.zy.ball.domain.oa.po.StatiumDetail;

public class StatiumDetailVo extends StatiumDetail {
    private static final long serialVersionUID = 1L;

    // public MultipartFile orgImgFile= null;
    public String next_page = null;
    
    public String[] photo;
    
    public String[] noticeImgs;
    
    public List<String> sportTypes = new ArrayList<String>();

    public String lnglat = null;
    
    // add by sl 针对场馆列表中场馆都回访记录展示
    private String visitDate;// 回访时间
    
    private String isVisit;// 回访时间是否大于30天(0:否,1:是)

    public List<String> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<String> sportTypes) {
        this.sportTypes = sportTypes;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
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

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(String isVisit) {
		this.isVisit = isVisit;
	}

    public String[] getNoticeImgs() {
        return noticeImgs;
    }

    public void setNoticeImgs(String[] noticeImgs) {
        this.noticeImgs = noticeImgs;
    }
    
}
