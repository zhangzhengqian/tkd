insert into 
	crm_statium_space_price
(
	id,space_id,title,start_time,end_time,price,sign_price,is_workday,space_price,status,seq,cb,eb,ct,et
) 
select 
	replace(uuid(),'-',''),space_id,title,start_time,end_time,price,sign_price,is_workday,space_price,status,seq,cb,eb,ct,et 
from 
	oa_statium_space_price
where
	space_id = '${spaceId}'