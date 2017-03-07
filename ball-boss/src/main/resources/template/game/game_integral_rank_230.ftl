SELECT a.memberId, a.integral, a.rank, a.playerId
FROM (SELECT m.id AS memberId, m.integral, m.player_id AS playerId, @rank := if(@integral = m.integral,@rank,@incRank ) AS rank, @integral := m.integral,@incRank := @incRank +1
	FROM oa_enjoy_member m left join oa_enjoy_game g on m.game_id = g.id, (SELECT @rank := 1, @integral := 0,@incRank := 1
		) b
	WHERE m.game_id = ?
    and g.game_type = ?
	ORDER BY m.integral DESC
	) a