insert into 
	crm_statium_price_tmpl
(
	id,statium_id,is_workday,title,start_time,end_time,price,seq,sport_type,cb,eb,et,ct
) 
select 
	replace(uuid(),'-',''),statium_id,is_workday,title,start_time,end_time,price,seq,sport_type,cb,eb,et,ct 
from 
	oa_statium_price_tmpl 
where 
	statium_id='${statiumId}'