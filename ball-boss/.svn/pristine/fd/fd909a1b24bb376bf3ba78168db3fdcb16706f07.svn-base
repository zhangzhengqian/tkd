select
	left(ct,10)		as ct, 
	sum(counter)	as cont
from 
	statistic_ualog
where 
	1=1
	<#if from??> 
	   and ct <= '${from} 23' 
	</#if> 
	<#if to??>
 	   and ct >= '${to} 00'
	</#if> 
	
group by left(ct,10)