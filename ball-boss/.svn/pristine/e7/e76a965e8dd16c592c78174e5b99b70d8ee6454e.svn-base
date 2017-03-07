select 
	COUNT(t.id)					as orderCount,
	sum(t.fee)					as fee,
	sum(t.fee-t.costPrice)		as profit,
	sum(t.subsidies)			as subsidies,
	sum(subamount)				as subamount 
from 
	(
	select 
		o.id 					as id,
		o.ct 					as ct,
		o.fee					as fee,
		o.fee-o.final_fee		as subamount,
		o.subsidies				as subsidies,
		sum(i.cost_price)		as costPrice 
	from 
		oa_order o 
	LEFT JOIN 
		oa_order_item i 
	on 
		o.id=i.order_id
	where 
		DATE_FORMAT(o.ct,'%Y-%m-%d')='${searchDay}'
	and
		o.status in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING') 
	group by 
		o.id
	) t