select 
	distinct CONCAT('${searchDate}',c.province,c.city) cpc,'${searchDate}' ct,c.province,c.city,m._count 
from statistic_user_mobile c 
left join (
	SELECT 
		CONCAT(ct,province,city) cpc,ct,province,city,_count 
	FROM (
			(SELECT
				LEFT (ct, 10) ct, province,count(city) _count, city 
			 FROM
				statistic_user_mobile 
			 WHERE
				province NOT IN ('中国','北京','天津','上海','重庆')
			 AND LEFT (ct, 10) = '${searchDate}'
			 GROUP BY  LEFT (ct, 10) ,city
			 )
			 UNION ALL
			 (SELECT  
				LEFT (ct, 10) ct, province,count(city) _count, city
			 FROM 
				statistic_user_mobile 
			 WHERE 
				province IN ('北京','天津','上海','重庆') 
			 AND LEFT (ct, 10) = '${searchDate}'
			 GROUP BY  LEFT (ct, 10) ,city
			)
	) b
 ) m
on c.province = m.province and c.city = m.city
where c.province != '中国' 
order by c.province asc,c.city asc;