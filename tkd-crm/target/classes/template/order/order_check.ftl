select  
	o.id			as id,
	o.status		as status,
	o.customer_id	as customerId,
	o.is_check		as isCheck,
	o.check_date	as checkDate 
from 
	oa_order o
where 
	o.`status` in ('ORDER_PLAYING','ORDER_PAIED','ORDER_VERIFY')
and
	o.statium_id='${statiumId}'
<#if orderId??>
and 
	o.id='${orderId}'
</#if>
<#if customerId??>
and 
	o.customer_id='${customerId}'
</#if>
<#if customerIdIn??>
	and o.customer_Id in 
	(
		<#list customerIdIn as sta>
			'${sta}'<#if sta_has_next==true>,</#if>
		</#list>
	)
</#if>
 
	