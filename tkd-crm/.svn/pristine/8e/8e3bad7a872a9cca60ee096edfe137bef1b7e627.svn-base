select 
	count(CASE WHEN DATE_FORMAT(ct,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') THEN 1 END) AS today_order,
    count(CASE WHEN DATE_FORMAT(ct,'%Y-%m-%d') <= DATE_FORMAT(now(),'%Y-%m-%d') THEN 1 END) AS total_order
from oa_order where statium_id='${statiumId}' and `status` in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING');