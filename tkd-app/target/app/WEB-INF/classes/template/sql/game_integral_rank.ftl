SELECT a.memberId, a.integral, a.rank, a.playerId
FROM (SELECT id AS memberId, integral, player_id AS playerId, if(@integral = integral, @rank := @rank + 0, @rank := @rank + 1) AS rank, @integral := integral
	FROM oa_enjoy_member, (SELECT @rank := 0, @integral := 0
		) b
	WHERE game_id = ?
	ORDER BY integral DESC
	) a