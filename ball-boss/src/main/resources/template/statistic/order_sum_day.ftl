select 
	t.ct						as title,
	sum(t.fee)					as num1,
	sum(t.fee-t.costPrice)		as num2,
	sum(t.subsidies)			as num3,
	sum(subamount)				as num4 
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
		DATE_FORMAT(o.ct,'%Y-%m-%d') BETWEEN '${start}' and '${end}'
	and
		o.status in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING') 
	group by 
		o.id
	) t
group by DATE_FORMAT(t.ct,'%Y-%m-%d');