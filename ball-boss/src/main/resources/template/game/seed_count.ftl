SELECT 
	   count(CASE WHEN state=0 THEN 1 END) AS unsub,
       count(CASE WHEN state=1 THEN 1 END) AS subed,
       count(CASE WHEN state=2 THEN 1 END) AS rejected,
       count(CASE WHEN state=1&&is_seed=1 THEN 1 END) AS seed,
       count(CASE WHEN state=1&&(is_seed is null||is_seed=0) THEN 1 END) AS unseed
FROM oa_enjoy_member
where game_id='${gameId}'