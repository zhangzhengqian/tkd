select  b.cpc,'${searchDate}' ct,b._province province,b._city,e._count
from (
	SELECT 
		 distinct CONCAT('${searchDate}',_province,_city) cpc,_province,_city,_count
    FROM 
		((SELECT  
				 
				case when instr(province,'省') > 0 then substring_index(province, '省',1)
				 when instr(province,'市') > 0 then substring_index(province, '市',1)
				else province end as _province,
				count(city) _count,substring_index(city, '市',1) _city 
			FROM 
				statistic_active_user_ip  
			WHERE 
				province NOT IN ('中国','北京市','天津市','上海市','重庆市') 
			GROUP BY   _city )
			UNION ALL
			(SELECT  
				 substring_index(province, '市',1) _province,count(province) _count,substring_index(province, '市',1) _city 
			 FROM 
				statistic_active_user_ip 
			 WHERE 
				province IN ('北京市','天津市','上海市','重庆市')
			 GROUP BY  _province )
		 ) a 
  ) b
 left join (
	SELECT 
		CONCAT(ct,_province,_city) cpc,ct,_province,_city,_count
	FROM 
		((SELECT  
				LEFT (ct, 10) ct, 
				case when instr(province,'省') > 0 then substring_index(province, '省',1)
				 when instr(province,'市') > 0 then substring_index(province, '市',1)
				else province end as _province,
				count(city) _count,substring_index(city, '市',1) _city 
			FROM 
				statistic_active_user_ip  
			WHERE 
				province NOT IN ('中国','北京市','天津市','上海市','重庆市') 
			 AND LEFT (ct, 10) = '${searchDate}'
			GROUP BY  LEFT (ct, 10),_city )
			UNION ALL
			(SELECT  
				LEFT (ct, 10) ct,substring_index(province, '市',1) _province,count(province) _count,substring_index(province, '市',1) _city 
			 FROM 
				statistic_active_user_ip 
			 WHERE 
				province IN ('北京市','天津市','上海市','重庆市')
			 AND LEFT (ct, 10) = '${searchDate}' 
			 GROUP BY  LEFT (ct, 10),_province )
		 ) d
   ) e
on b._province = e._province and b._city = e._city order by b._province asc,b._city asc;