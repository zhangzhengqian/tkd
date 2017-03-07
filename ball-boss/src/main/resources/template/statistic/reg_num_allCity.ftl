SELECT distinct CONCAT(ct,province,city) cpc,ct,province,city,num _count
	 FROM statistics_registered_user_date 
where ct = '${searchDate}' 

GROUP BY ct,city

ORDER BY province asc,city asc;