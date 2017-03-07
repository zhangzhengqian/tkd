select 
	CONCAT(o.space_id,'_',o.is_Workday) k,o.cost_price v
from
	oa_space_price o
where o.space_id is not null