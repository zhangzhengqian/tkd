 SELECT  
	LEFT (ct, 10) ct,count(province) _count,substring_index(province, '市',1) _city 
	 FROM 
		statistic_active_user_ip 
	 WHERE 
		substring_index(province, '市',1) = '${searchProvince}'
	  <#if searchDate??>
		  AND LEFT (ct, 7) = '${searchDate}'
		  GROUP BY  LEFT (ct, 10) 
	 </#if>
 ORDER BY LEFT (ct, 10) DESC;
  
   