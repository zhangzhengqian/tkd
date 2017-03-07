 SELECT
 	LEFT (ct, 10) ct, city,count(city) _count 
 FROM
 	statistic_user_mobile 
 WHERE
 	province = '${searchProvince}'
 <#if searchCity??>
 AND city = '${searchCity}'
 </#if>
 <#if searchDate??>
 AND LEFT (ct, 7) = '${searchDate}'
 GROUP BY  LEFT (ct, 10)
 </#if>
 ORDER BY  LEFT (ct, 10) DESC;