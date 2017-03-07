select 
	CONCAT(o.statium_Id,'_',o.sport_Type) k,o.subsidies v
from
	oa_statium_price_tmpl o
group by
	o.statium_Id,o.subsidies,o.sport_Type 