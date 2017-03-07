SELECT 
	count(t.id) cont
FROM 
	oa_qiuyouzone t
where
	status!=2
	<#if userId??&&userId?length gt 0>
		and user_id='${userId}'
	</#if>
	<#if content??&&content?length gt 0>
		and content like '%${content}%'
	</#if>