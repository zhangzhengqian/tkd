select 
	count(o.id) as cont
from
(
select 
	*
from 
	oa_games_schedule
where 
	marines_id_a = '${corpsId}'
union
select 
	*
from 
	oa_games_schedule
where 
	marines_id_b = '${corpsId}'
) o