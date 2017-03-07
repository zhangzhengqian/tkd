SELECT id, integral, rank
FROM (SELECT id, integral, @curRank := IF(@preRank = integral, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @preRank := integral
	FROM oa_corps_info p, (SELECT @curRank := 0, @preRank := NULL, @incRank := 1
		) r
	WHERE area = '${area}' and status=1
	ORDER BY integral DESC
	) s where id='${corpsId}'