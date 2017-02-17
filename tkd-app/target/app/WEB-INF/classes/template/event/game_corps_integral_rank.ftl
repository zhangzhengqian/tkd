SELECT corpsId,integral,rank 
FROM(
SELECT corpsId,integral, @curRank := IF(@preRank = integral, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @preRank := integral
 FROM 
    (SELECT g corpsId ,SUM(s) integral 
			from (
				SELECT SUM(g.integration_a) AS s,g.marines_id_a g FROM oa_games_schedule g WHERE g.games_id='${gamesId}' GROUP BY g.marines_id_a
				union all
				SELECT SUM(g.integration_b) AS s,g.marines_id_b g FROM oa_games_schedule g WHERE g.games_id='${gamesId}' GROUP BY g.marines_id_b) g    
			GROUP BY g.g) l ,(SELECT @curRank := 0, @preRank := NULL, @incRank := 1
	) r
    ORDER BY integral DESC) o LIMIT ${begin},${size}