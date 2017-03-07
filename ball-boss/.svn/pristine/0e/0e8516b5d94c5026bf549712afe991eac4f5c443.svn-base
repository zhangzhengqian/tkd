select 
	o.id 				as id,
	o.et 				as et,
	if((select gid from oa_enjoy_game where id=o.statium_id) is null,(select statium_id from oa_enjoy_game where id=o.statium_id),(select statium_id from oa_enjoy_game where id=(select gid from oa_enjoy_game where id=o.statium_id))) as statiumId,
	o.subsidies			as subsidies,
	sum(i.cost_price)	as costPrice,
	i.sport_type		as sportType
from 
	oa_order o 
left join 
	oa_order_item i
on
	o.id=i.order_id
where
	o.status='ORDER_PLAYING'
	and o.orders_type=6
	<#if GTE_editTime??>
		and o.et >= '${GTE_editTime}'
	</#if>
	<#if LTE_editTime??>
		and o.et <= '${LTE_editTime}'
	</#if>
group by o.id
	 