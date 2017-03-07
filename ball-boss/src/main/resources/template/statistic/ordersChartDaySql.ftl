select 
    x, sport_type, count(sport_type) as y
from (
	select distinct
        order_id, date_format(ct, '%Y-%m-%d') as x, sport_type
    from
        oa_order_item
    where 
		sport_type != '' 
		<#if from??> 
		    and ct < '${from?string("yyyy-MM-dd")}' 
		</#if> 
		<#if to??>
	 	   and ct >= '${to?string("yyyy-MM-dd")}'
		</#if> 
) a
group by x , sport_type