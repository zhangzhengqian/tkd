select 
	DATE_FORMAT(u.ct,'%Y-%m-%d')	as title,
	sum(u.counter)					as num
from statistic_active_user u
where 
	DATE_FORMAT(u.ct,'%Y-%m-%d') BETWEEN '${start}' and '${end}'
GROUP BY
	 DATE_FORMAT(u.ct,'%Y-%m-%d');