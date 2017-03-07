(select 
	games_id 		as gameId,
	marines_id_a	as a,
	marines_id_b	as b,
	score_a			as score_a,
	score_b			as score_b,
	integration_a	as integration_a,
	integration_b	as integration_b,
	victory_a		as victory_a,
	victory_b		as victory_b,
	game_time		as game_time
from 
	oa_games_schedule
where 
	marines_id_a = '${corpsId}'
)
union all
(select 
	games_id 		as gameId,
	marines_id_a	as a,
	marines_id_b	as b,
	score_a			as score_a,
	score_b			as score_b,
	integration_a	as integration_a,
	integration_b	as integration_b,
	victory_a		as victory_a,
	victory_b		as victory_b,
	game_time		as game_time
from 
	oa_games_schedule
where 
	marines_id_b = '${corpsId}'
)
order by game_time
