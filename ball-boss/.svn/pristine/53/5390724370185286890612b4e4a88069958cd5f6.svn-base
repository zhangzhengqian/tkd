select  b.cpc,'${searchDate}' ct,b.province,b._city,e._count
from (
	SELECT 
		 distinct CONCAT('${searchDate}',province,_city) cpc,province,_city,_count
    FROM 
		((SELECT  province province,count(city) _count,
			case when instr(city,'市') > 0 then CONCAT(substring_index(city, '市',1),'市')
				when instr(city,'州') > 0 then  CONCAT(substring_index(city, '州',1),'州')
				when instr(city,'县') > 0 then  CONCAT(substring_index(city, '县',1),'县')
				 when instr(city,'区') > 0 then  CONCAT(substring_index(city, '区',1),'区')
				 when instr(city,'盟') > 0 then  CONCAT(substring_index(city, '盟',1),'盟')
				else city end as _city 
			FROM 
				statistic_active_user_ip  
			WHERE 
				province NOT IN ('中国','北京市','天津市','上海市','重庆市','澳门特别行政区','香港特别行政区') 
			GROUP BY _city )
			UNION ALL
			(SELECT  
				 province province,count(province) _count,province _city 
			 FROM 
				statistic_active_user_ip 
			 WHERE 
				province IN ('北京市','天津市','上海市','重庆市','澳门特别行政区','香港特别行政区')
			 GROUP BY province )
		 ) a 
  ) b
 left join (
	SELECT 
		CONCAT(ct,province,_city) cpc,ct,province,_city,_count
	FROM 
		((SELECT LEFT (ct, 10) ct,province,count(city) _count,
		   case when instr(city,'市') > 0 then CONCAT(substring_index(city, '市',1),'市')
				when instr(city,'州') > 0 then  CONCAT(substring_index(city, '州',1),'州')
				when instr(city,'县') > 0 then  CONCAT(substring_index(city, '县',1),'县')
				 when instr(city,'区') > 0 then  CONCAT(substring_index(city, '区',1),'区')
				 when instr(city,'盟') > 0 then  CONCAT(substring_index(city, '盟',1),'盟')
				else city end as  _city 
			FROM 
				statistic_active_user_ip  
			WHERE 
				province NOT IN ('中国','北京市','天津市','上海市','重庆市','澳门特别行政区','香港特别行政区') 
			 AND LEFT (ct, 10) = '${searchDate}'
			GROUP BY  LEFT (ct, 10),_city )
			UNION ALL
			(SELECT  
				LEFT (ct, 10) ct,province,count(province) _count,province  _city 
			 FROM 
				statistic_active_user_ip 
			 WHERE 
				province IN ('北京市','天津市','上海市','重庆市','澳门特别行政区','香港特别行政区') 
			 AND LEFT (ct, 10) = '${searchDate}' 
			 GROUP BY  LEFT (ct, 10),province )
		 ) d
   ) e
on b.province = e.province and b._city = e._city order by b.province asc,b._city asc;