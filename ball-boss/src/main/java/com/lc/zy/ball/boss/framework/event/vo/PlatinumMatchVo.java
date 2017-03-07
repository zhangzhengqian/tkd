package com.lc.zy.ball.boss.framework.event.vo;

import com.lc.zy.ball.domain.oa.po.PlatinumMatch;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liangsh
 * Date: 2016/6/13
 * Time: 17:59
 */
public class PlatinumMatchVo extends PlatinumMatch {

    private int[] gameTypes;

    private String[] showPrices;

    private String[] levels;

    private int gamesLength;

    private List<PlatinumMatch> games = new ArrayList<PlatinumMatch>();

    private String lngLat;

    private Integer status;

    private String stateValue;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public int[] getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(int[] gameTypes) {
        this.gameTypes = gameTypes;
    }

    public String[] getShowPrices() {
        return showPrices;
    }

    public void setShowPrices(String[] showPrices) {
        this.showPrices = showPrices;
    }

    public String[] getLevels() {
        return levels;
    }

    public void setLevels(String[] levels) {
        this.levels = levels;
    }

    public int getGamesLength() {
        return gamesLength;
    }

    public void setGamesLength(int gamesLength) {
        this.gamesLength = gamesLength;
    }

    public List<PlatinumMatch> getGames() {
        return games;
    }

    public void setGames(List<PlatinumMatch> games) {
        this.games = games;
    }

    public String getLngLat() {
        return lngLat;
    }

    public void setLngLat(String lngLat) {
        this.lngLat = lngLat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }
}
