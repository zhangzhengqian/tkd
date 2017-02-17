select 
DISTINCT
	 id, title, from_user, to_user, ct, cb, status, content
from 
	 crm_message
where 
	1 = 1
	<#if title??>
		and title >= '${title}'
	</#if>
	<#if from_user??>
		and from_user = '${from_user}'
	</#if>
	<#if to_user??>
		and to_user = '${to_user}'
	</#if>
	<#if ct??>
		and ct >= '${ct}'
	</#if>
	<#if cb??>
		and cb >= '${cb}'
	</#if>
	<#if status??>
		and status = '${status}'
	</#if>
	<#if content??>
		and content = '${content}'
	</#if>
	order by status asc,ct desc
	limit 
	${offset}, ${pageSize}