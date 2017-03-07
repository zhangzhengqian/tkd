SELECT 
 	ct,city,sum(num) _count
FROM
 	statistics_registered_user_date 
WHERE
 	province = '${searchProvince}'
<#if searchCity??>
 AND city = '${searchCity}'
</#if>
 <#if searchDate??>
 AND LEFT (ct, 7) = '${searchDate}'
 GROUP BY  ct
 </#if>
 ORDER BY ct DESC;