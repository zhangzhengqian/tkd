select 
	sum(o.fee) totalFee
from 
	oa_order o
where 
	o.statium_id = '${statiumId}'
	and o.et >= '${startDate}'
	and o.et <= '${endDate}'
	and o.status='ORDER_PLAYING'
