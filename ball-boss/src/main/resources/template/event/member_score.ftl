select
	sum(s.win) win,sum(s.draw) draw,sum(loose) loose 
from
	(select 
	   count(CASE WHEN score1>score2 THEN 1 END) AS win,
       count(CASE WHEN score1=score2 THEN 1 END) AS draw,
       count(CASE WHEN score1<score2 THEN 1 END) AS loose
from 
	oa_enjoy_group_schedule 
where 
	clasli1='${mem}' 
and 
	score1 is not null
union all
	select 
	   count(CASE WHEN score2>score1 THEN 1 END) AS win,
       count(CASE WHEN score2=score1 THEN 1 END) AS draw,
       count(CASE WHEN score2<score1 THEN 1 END) AS loose
from 
	oa_enjoy_group_schedule 
where 
	clasli2='${mem}' 
and 
	score1 is not null) s