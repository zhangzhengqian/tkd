package com.lc.zy.ball.boss.framework.event.vo;

import java.util.ArrayList;
import java.util.List;

public class ScoreGroupVo {

	// 组数
	private String gameGroup;

	// score list
	List<GameScoreVo> scoreList = new ArrayList<GameScoreVo>();

	public String getGameGroup() {
		return gameGroup;
	}

	public void setGameGroup(String gameGroup) {
		this.gameGroup = gameGroup;
	}

	public List<GameScoreVo> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<GameScoreVo> scoreList) {
		this.scoreList = scoreList;
	}

}
