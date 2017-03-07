SELECT  
	LEFT (ct, 10) ct,count(city) _count,city _city 
FROM 
	statistic_active_user_ip  
WHERE 
	province = '${searchProvince}' 
    AND  city = '${searchCity}'
 <#if searchDate??>
  AND LEFT (ct, 7) = '${searchDate}'
  GROUP BY  LEFT (ct, 10) 
 </#if>
ORDER BY LEFT (ct, 10) DESC;
