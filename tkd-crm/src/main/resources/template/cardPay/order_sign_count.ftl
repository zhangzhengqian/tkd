select 
	count(DISTINCT o.id) 	as cont
from 
	oa_order o left join oa_order_item i on o.id=i.order_id
where 
	o.statium_id = '${statiumId}'
	and o.orders_type in (0 , 1)
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