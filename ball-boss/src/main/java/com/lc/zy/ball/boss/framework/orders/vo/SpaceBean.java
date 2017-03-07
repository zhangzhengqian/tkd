package com.lc.zy.ball.boss.framework.orders.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SpaceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String statiumId;

    private String name;// 场馆名称

    private String prices;// 场地价格

    private String beginTime;// 场馆开场时间

    private String endTime;// 场馆闭关时间

    private String time;// 场馆闭关时间

    private List<Map<String, String>> spaceList;

    private Map<String, List<Long>> spaceUseMap;

    // 场地id map
    private Map<String, String> spaceIdMap;

    public String getStatiumId() {
        return statiumId;
    }

    public void setStatiumId(String statiumId) {
        this.statiumId = statiumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Map<String, String>> getSpaceList() {
        return spaceList;
    }

    public void setSpaceList(List<Map<String, String>> spaceList) {
        this.spaceList = spaceList;
    }

    public Map<String, List<Long>> getSpaceUseMap() {
        return spaceUseMap;
    }

    public void setSpaceUseMap(Map<String, List<Long>> spaceUseMap) {
        this.spaceUseMap = spaceUseMap;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, String> getSpaceIdMap() {
        return spaceIdMap;
    }

    public void setSpaceIdMap(Map<String, String> spaceIdMap) {
        this.spaceIdMap = spaceIdMap;
    }

}
