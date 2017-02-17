select 
	i.start_time as startTime,
	i.space_code as spaceCode
from
	oa_order o
left join
	oa_order_item i
ON 
	i.order_id=o.id
where
	1=1
	<#if statiumId??>
		and o.statium_id='${statiumId}' 
	</#if>
	<#if sportType??>
		and i.sport_type='${sportType}'
	</#if>
	<#if startDate??>
		and i.start_date='${startDate}'
	</#if>
and 
	o.status in ('ORDER_PAIED','ORDER_PLAYING','ORDER_NEW','ORDER_VERIFY')