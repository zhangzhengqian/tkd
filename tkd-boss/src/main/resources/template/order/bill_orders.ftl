select 
	o.id 				as id,
	o.et 				as et,
	o.statium_id		as statiumId,
	o.final_Fee			as finalFee,
	o.cb				as cb,
	i.sign_date			as signDate
from 
	oa_order o 
left join 
	oa_order_item i
on
	o.id=i.order_id
where
	o.status='ORDER_BILLED'
	<#if GTE_editTime??>
		and i.sign_date >= '${GTE_editTime}'
	</#if>
	<#if LTE_editTime??>
		and i.sign_date <= '${LTE_editTime}'
	</#if>
	<#if EQ_ordersTypeIn??>
		and o.orders_type in 
		(
			<#list EQ_ordersTypeIn as sta>
				${sta}<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
group by o.id