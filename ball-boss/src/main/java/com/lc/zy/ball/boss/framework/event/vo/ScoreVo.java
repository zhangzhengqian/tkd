package com.lc.zy.ball.boss.framework.event.vo;

import java.util.ArrayList;
import java.util.List;

public class ScoreVo {

	// 赛事id
	private String gameId;

	// 轮数
	private Integer turnNum;
	
	// 赛事类型
	private String gameType;

	// score list
	List<ScoreGroupVo> scoreGroupList = new ArrayList<ScoreGroupVo>();
	
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Integer getTurnNum() {
		return turnNum;
	}

	public void setTurnNum(Integer turnNum) {
		this.turnNum = turnNum;
	}

	public List<ScoreGroupVo> getScoreGroupList() {
		return scoreGroupList;
	}

	public void setScoreGroupList(List<ScoreGroupVo> scoreGroupList) {
		this.scoreGroupList = scoreGroupList;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

}
