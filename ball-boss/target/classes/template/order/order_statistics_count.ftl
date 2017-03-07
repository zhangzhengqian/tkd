select 
	count(o.id) 	as cont
from 
	oa_order o left join oa_statium_detail d on o.statium_id=d.id
where 
	1=1
	and o.order_type='BOOK_APP'
	and o.orders_type in (0,1,2,4)
	<#if GTE_createTime??>
		and o.ct >= '${GTE_createTime}'
	</#if>
	<#if LTE_createTime??>
		and o.ct <= '${LTE_createTime}'
	</#if>
	<#if GTE_editTime??>
		and o.et >= '${GTE_editTime}'
	</#if>
	<#if LTE_editTime??>
		and o.et <= '${LTE_editTime}'
	</#if>
	<#if areaCode??>
		and d.area_code like '${areaCode}'
	</#if>
	<#if status??>
		and o.status = '${status}'
	</#if>
	<#if statusIn??>
		and o.status in 
		(
			<#list statusIn as sta>
				'${sta}'<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
	<#if statiumId??>
		and o.statium_id = '${statiumId}'
	</#if>
