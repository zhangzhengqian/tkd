select 
	DATE_FORMAT(o.et,'%Y-%m-%d') perdate,
	count(o.id) ordercount,
	sum(o.final_fee) totalfinalfee,
	sum(o.fee) totalfee  
from 
	oa_order o
where 
	o.statium_id = '${statiumId}'
	and o.et >= '${startDate}'
	and o.et <= '${endDate}'
	and o.status='ORDER_PLAYING'
group by 
	DATE_FORMAT(et,'%Y-%m-%d')