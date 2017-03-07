package com.lc.zy.ball.boss.framework.event.vo;

import java.util.ArrayList;
import java.util.List;

import com.lc.zy.ball.domain.oa.po.EnjoyGame;

public class EnjoyGameVo extends EnjoyGame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5444111252110517151L;

	private int[] gameTypes;

	private String[] showPrices;

	private String[] levels;

	private int[] gameFormat2s;

	private List<EnjoyGame> games = new ArrayList<EnjoyGame>();

	private int gamesLength;
	
	private String statiumName;
	
	private String siteName;
	
	private int orderCnt;
	
	private int cbNum;
	
	public String getStatiumName() {
		return statiumName;
	}

	public void setStatiumName(String statiumName) {
		this.statiumName = statiumName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getGamesLength() {
		return gamesLength;
	}

	public void setGamesLength(int gamesLength) {
		this.gamesLength = gamesLength;
	}

	public List<EnjoyGame> getGames() {
		return games;
	}

	public void setGames(List<EnjoyGame> games) {
		this.games = games;
	}

	public int[] getGameFormat2s() {
		return gameFormat2s;
	}

	public void setGameFormat2s(int[] gameFormat2s) {
		this.gameFormat2s = gameFormat2s;
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

	public String getLngLat() {
		return lngLat;
	}

	public void setLngLat(String lngLat) {
		this.lngLat = lngLat;
	}

	private String lngLat;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private String stateValue;

	public String getStateValue() {
		return stateValue;
	}

	public void setStateValue(String stateValue) {
		this.stateValue = stateValue;
	}

	public int getOrderCnt() {
		return orderCnt;
	}

	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}

	public int getCbNum() {
		return cbNum;
	}

	public void setCbNum(int cbNum) {
		this.cbNum = cbNum;
	}

	public List<EnjoyGameVo> gameVoList = new ArrayList<EnjoyGameVo>();

	public List<EnjoyGameVo> getGameVoList() {
		return gameVoList;
	}

	public void setGameVoList(List<EnjoyGameVo> gameVoList) {
		this.gameVoList = gameVoList;
	}

	public String gameLevelName;

	public String gameTypeName;

	public String isScoreNoticeName;

	public String stateName;

	public String cbName;

	public String isOfficalName;

	public String isIssueBonusName;

	public String cityHoldTimesName;

	public String getCityHoldTimesName() {
		return cityHoldTimesName;
	}

	public void setCityHoldTimesName(String cityHoldTimesName) {
		this.cityHoldTimesName = cityHoldTimesName;
	}

	public String getIsIssueBonusName() {
		return isIssueBonusName;
	}

	public void setIsIssueBonusName(String isIssueBonusName) {
		this.isIssueBonusName = isIssueBonusName;
	}

	public String getGameLevelName() {
		return gameLevelName;
	}

	public void setGameLevelName(String gameLevelName) {
		this.gameLevelName = gameLevelName;
	}

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

	public String getIsScoreNoticeName() {
		return isScoreNoticeName;
	}

	public void setIsScoreNoticeName(String isScoreNoticeName) {
		this.isScoreNoticeName = isScoreNoticeName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCbName() {
		return cbName;
	}

	public void setCbName(String cbName) {
		this.cbName = cbName;
	}

	public String getIsOfficalName() {
		return isOfficalName;
	}

	public void setIsOfficalName(String isOfficalName) {
		this.isOfficalName = isOfficalName;
	}
}
