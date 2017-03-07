select 
	sum(counter)	as cont
from 
	statistic_ualog
where 
	ct like '${searchDay}%'