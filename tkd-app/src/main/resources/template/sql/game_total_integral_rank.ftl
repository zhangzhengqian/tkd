SELECT <#if isPage>
			a.scoreId, a.integ, a.rank, a.playerId, a.name
		<#else>
			count(0)
		</#if>
FROM (SELECT s.id AS scoreId, s.integ, s.player_id AS playerId, p.name, if(@integral = integ, @rank := @rank + 0, @rank := @rank + 1) AS rank
		, @integral := integ
	FROM oa_games_score s left JOIN oa_games_player p ON s.player_id = p.id, (SELECT @rank := 0, @integral := 0
		) b
	WHERE 1 = 1
		<#if sportType??>
			AND sport_type = ?
		</#if>
		<#if gameType??>
			AND game_type = ?
		</#if>
		<#if keyword??>
			AND p.name LIKE ?
		</#if>
	ORDER BY integ DESC
	) a <#if isPage>
			limit ?,?
	    </#if>