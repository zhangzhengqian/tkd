select 
	sum(o.fee) 	as totalAmount
from 
	oa_order o
where 
	o.statium_id = '${statiumId}'
	<#if customerId??>
		and o.customer_id = '${customerId}'
	</#if>
	<#if ordersType??>
		and o.orders_type >= '${ordersType}'
	</#if>
	<#if sportType??>
		and i.sport_type >= '${sportType}'
	</#if>
	<#if orderType??>
		and o.order_type >= '${orderType}'
	</#if>
	<#if GTE_createTime??>
		and o.ct >= '${GTE_createTime}'
	</#if>
	<#if LTE_createTime??>
		and o.ct <= '${LTE_createTime}'
	</#if>
	<#if GTE_editTime??>
		and o.et >= '${GTE_editTime}'
	</#if>
	<#if LTE_editTime??>
		and o.et <= '${LTE_editTime}'
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