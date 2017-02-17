select 
DISTINCT
	o.id 			as id,
	o.ct 			as ct,
	o.fee 			as fee,
	o.final_fee 	as finalFee,
	o.reason 		as reason,
	o.order_type	as orderType,
	o.user_id 	    as userId,
	o.et 			as et,
	o.eb			as eb
from 
	oa_order o left join oa_order_item i on o.id=i.order_id
where
    o.status = 'ORDER_PAIED'
	and o.order_type='BOOK_APP'
	<#if statiumId??>
	and o.statium_id = '${statiumId}'
	</#if>
	<#if ordersType??>
	and o.orders_type = '2'
	</#if>
	<#if cardBuyDate??>
	and i.card_buy_date like '%${cardBuyDate}%'
	</#if>
	order by o.ct desc
	<#if offset??>
		limit 
		${offset}, ${pageSize}
	</#if>