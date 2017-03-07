select 
		s.id		as title,
		s.counter	as num
	from 
		statistic_uv_day s
	where 
		s.id BETWEEN '${start}' and '${end}'