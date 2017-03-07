select 
	ifnull(sum(g.fee),0) as fee,ifnull(sum(g.final_fee),0) as finalFee
from (
	select DISTINCT
		trade_no,
		order_id,
		user_id,
		fee,
		final_fee,
		STATUS,
		pay_type,
		out_trade_no
	from
	oa_pay_log 
	where
	1=1
	<#if GTE_createTime??>
		and create_time >= '${GTE_createTime}'
	</#if>
	<#if LTE_createTime??>
		and create_time <= '${LTE_createTime}'
	</#if>
	<#if EQ_status??>
		and status = '${EQ_status}'
	</#if>
) g