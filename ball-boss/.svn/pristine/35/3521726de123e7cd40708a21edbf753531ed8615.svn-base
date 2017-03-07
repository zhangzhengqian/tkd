SELECT  
	LEFT (ct, 10) ct,count(city) _count,substring_index(city, '市',1) _city 
FROM 
	statistic_active_user_ip  
WHERE 
	case when instr(province,'省') > 0 then substring_index(province, '省',1) = '${searchProvince}' 
     when instr(province,'市') > 0 then substring_index(province, '市',1) = '${searchProvince}' 
	else province = '${searchProvince}' 
    end 
    AND substring_index(city, '市',1) = '${searchCity}'
 <#if searchDate??>
  AND LEFT (ct, 7) = '${searchDate}'
  GROUP BY  LEFT (ct, 10) 
 </#if>
 
ORDER BY LEFT (ct, 10) DESC;
