select 
	DATE_FORMAT(u.ct,'%Y-%m-%d')	as title,
	sum(u.counter)					as num
from statistic_register_user u
where 
	DATE_FORMAT(u.ct,'%Y-%m-%d') BETWEEN '${start}' and '${end}'
GROUP BY
	 DATE_FORMAT(u.ct,'%Y-%m-%d');
	 