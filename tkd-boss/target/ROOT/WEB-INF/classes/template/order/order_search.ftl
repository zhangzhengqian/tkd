select 
	o.id 			as id,
	o.ct 			as ct,
	o.statium_id	as statiumId,
	o.final_fee 	as finalFee,
	o.pay_type 		as payType,
	o.reason 		as reason,
	o.status 		as status,
	o.et 			as et,
	o.orders_type	as ordersType,
	o.handle_status	as handleStatus,
	o.handler		as handler,
	o.verify_flag   as verifyFlag
from 
	oa_order o left join oa_statium_infos d on o.statium_id=d.dg_id
where 
	1=1
	and o.order_type='BOOK_APP'
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
		and d.areacode like '${areaCode}'
	</#if>
	<#if status??>
		and o.status = '${status}'
	</#if>
	<#if handler??>
		and o.handler = '${handler}'
	</#if>
	<#if verifyFlag??>
		and o.verify_flag = '${verifyFlag}'
	</#if>
	<#if statusIn??>
		and o.status in 
		(
			<#list statusIn as sta>
				'${sta}'<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
	<#if ordersType??>
		and o.orders_type = '${ordersType}'
	</#if>
	<#if ordersTypeIn??>
		and o.orders_type in 
		(
			<#list ordersTypeIn as sta>
				${sta}<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
	<#if orderId??>
		and o.id like '%${orderId}%'
	</#if>
	<#if statiumId??>
		and o.statium_id = '${statiumId}'
	</#if>
	<#if statiumIdIn??>
		and o.statium_id in 
		(
			<#list statiumIdIn as sta>
				'${sta}'<#if sta_has_next==true>,</#if>
			</#list>
		)
	</#if>
	order by o.verify_flag desc,o.ct desc
	<#if offset??>
limit 
	${offset}, ${pageSize}
	</#if>