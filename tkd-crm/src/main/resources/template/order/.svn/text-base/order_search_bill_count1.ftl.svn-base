select 
	count(o.id) as cont
from 
	oa_order o left join oa_order_item i on o.id=i.order_id
where
    o.status = 'ORDER_PAIED'
	and o.order_type='BOOK_APP'
	<#if statiumId??>
	and o.statium_id = '${statiumId}'
	</#if>
	<#if ordersType??>
	and o.orders_type = '${ordersType}'
	</#if>
	<#if signDate??>
	and i.sign_date='${signDate}'
	</#if>
	<#if cardBuyDate??>
	and i.card_buy_date like '%${cardBuyDate}%'
	</#if>
	order by o.ct desc