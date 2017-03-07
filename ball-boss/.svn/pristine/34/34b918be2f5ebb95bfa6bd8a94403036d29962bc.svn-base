insert into 
crm_space_price
(
	id,sport_type,is_workday,price,ct,cb,et,eb,space_id
) 
select 
	replace(uuid(),'-',''),sport_type,is_workday,price,ct,cb,et,eb,space_id 
from 
	oa_space_price
where 
	space_id='${spaceId}'