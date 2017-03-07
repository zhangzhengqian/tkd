SELECT sum(m.`cta_integral`) integral
FROM `oa_enjoy_member` m
LEFT JOIN
(SELECT id,
site_id
FROM `oa_enjoy_game`
WHERE `delete_flag`=0) g ON m.`game_id`=g.id
LEFT JOIN
(SELECT id
FROM `oa_enjoy_game_site`
WHERE city = '${city}'
OR `province`='${city}') s ON g.`site_id`=s.id
WHERE m.`player_id`='${playId}'