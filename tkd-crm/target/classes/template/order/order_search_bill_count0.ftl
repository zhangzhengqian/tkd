select 
	count(o.id) as cont
from 
	oa_order o left join oa_order_item i on o.id=i.order_id
where
    o.status = 'ORDER_BILLED'
	and o.order_type='BOOK_APP'
	<#if statiumId??>
	and o.statium_id = '${statiumId}'
	</#if>
	<#if ordersTypeIn??>
		and o.orders_type in 
		(
			0,
			1
		)
	</#if>
	<#if signDate??>
	and i.sign_date='${signDate}'
	</#if>
	order by o.ct desc