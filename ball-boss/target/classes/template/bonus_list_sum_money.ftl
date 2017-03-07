	select
		type,ifnull(sum(amount),0) as amount
	from
		sso_user_bonus_account_log
	where
		1=1
	<#if GTE_ct??>
		and ct >= '${GTE_ct}'
	</#if>
	<#if LTE_ct??>
		and ct <= '${LTE_ct}'
	</#if>
	<#if userId??>
    and user_id = '${userId}'
	</#if>
	GROUP BY type
