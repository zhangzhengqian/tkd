select 
	o.id 				as id,
	o.et 				as et,
	o.statium_id		as statiumId,
	o.final_Fee			as finalFee,
	o.cb				as cb,
	i.card_buy_date			as signDate
from 
	oa_order o 
left join 
	oa_order_item i
on
	o.id=i.order_id
where
	o.status='ORDER_PAIED'
	<#if GTE_editTime??>
		and i.card_buy_date >= '${GTE_editTime}'
	</#if>
	<#if LTE_editTime??>
		and i.card_buy_date <= '${LTE_editTime}'
	</#if>
	<#if EQ_ordersType??>
		and o.orders_type = '${EQ_ordersType}'
	</#if>
group by o.id