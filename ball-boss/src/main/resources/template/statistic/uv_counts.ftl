select
	id		as ct, 
	counter	as cont
from 
	statistic_uv_day
where 
	1=1
	<#if from??> 
	   and id <= '${from}' 
	</#if> 
	<#if to??>
 	   and id >= '${to}'
	</#if> 