select 
	count(DISTINCT o.id) 	as cont
from 
	oa_order o left join oa_order_item i on o.id=i.order_id
where 
	o.statium_id = '${statiumId}'
	<#if userId??>
		and o.user_id = '${userId}'
	</#if>
	<#if userIdIn??>
		and o.user_Id in
		(
			<#list userIdIn as sta>
				'${sta}'<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
	<#if orderId??>
		and o.id like '%${orderId}%'
	</#if>
	<#if payType??>
		and o.pay_type >= '${payType}'
	</#if>
	<#if order_type??>
		and o.order_type = '${order_type}'
	</#if>
	<#if GTE_ct??>
		and o.ct >= '${GTE_ct}'
	</#if>
	<#if LTE_ct??>
		and o.ct <= '${LTE_ct}'
	</#if>
	<#if GTE_et??>
		and o.et >= '${GTE_et}'
	</#if>
	<#if LTE_et??>
		and o.et <= '${LTE_et}'
	</#if>
	<#if status??>
		and o.status = '${status}'
	</#if>
	<#if statusIn??>
		and o.status in 
		(
			<#list statusIn as sta>
				'${sta}'<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>