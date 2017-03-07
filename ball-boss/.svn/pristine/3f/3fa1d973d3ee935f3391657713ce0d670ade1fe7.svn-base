select 
		DATE_FORMAT(o.ct,'%Y-%m-%d')		as title,
		count(o.id)							as num
	from 
		oa_order o 
	where 
		DATE_FORMAT(o.ct,'%Y-%m-%d') BETWEEN '${start}' and '${end}'
	and
		o.status in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING') 
	group by 
		DATE_FORMAT(o.ct,'%Y-%m-%d')