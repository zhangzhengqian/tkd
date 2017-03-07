select 
	c.id,
	c.ct,
	c.statiumId,
	c.finalFee,
	c.fee,
	c.qiuyouFee,
	c.reason,
	c.couponId,
	c.customerId,
	c.status,
	c.et,
	c.ordersType,
	c.payType
from (
SELECT 
	o.id 			as id,
	o.fee			as fee,
	o.ct 			as ct,
	o.statium_id	as statiumId,
	o.final_fee 	as finalFee,
	o.qiuyou_fee	as qiuyouFee,
	o.reason 		as reason,
	o.coupon_id 	as couponId,
	o.customer_id 	as customerId,
	o.status 		as status,
	o.et 			as et,
	o.orders_type	as ordersType,
	o.pay_type		as payType
FROM oa_order o
WHERE status in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING','ORDER_REFUNDING','ORDER_REFUNDED')
	and statium_id IN
    (SELECT g1.id
     FROM oa_enjoy_game g1
     LEFT JOIN oa_enjoy_game_site s1 ON g1.site_id=s1.id
     WHERE g1.gid IS NULL
     <#if startTime??>
		AND g1.start_time >= '${startTime}'
	 </#if>
	 <#if endTime??>
		AND g1.start_time <= '${endTime}'
	 </#if>
	 <#if gameLevel??>
		AND g1.game_level = '${gameLevel}'
	 </#if>
	 <#if gameType??>
		AND g1.game_type = '${gameType}'
	 </#if>
	 <#if city??>
		AND (s1.province = '${city}' or s1.city = '${city}')
	 </#if>
	 <#if name??>
		AND g1.short_title like '%ball-boss%'
	 </#if>
	 <#if statiumId??>
		AND g1.statium_id like '%${statiumId}%'
	 </#if>
     )
and id in 
	(select order_id 
		from 
			oa_enjoy_member m1 
		LEFT JOIN 
			oa_games_player p1 
		on 
			m1.player_id=p1.id 
		where
			1=1
			<#if pName??>
				AND p1.name='${pName}'
			</#if>
			<#if pCard??>
				AND p1.card_no='${pCard}'
			</#if>
			<#if phone??>
				and p1.user_id=(select id from sso_user where phone='${phone}' or username = '${phone}')
			</#if>
	)
<#if orderId??>
	and id='${orderId}'
</#if>
<#if ct0??>
	and ct >= '${ct0}'
</#if>
<#if ct1??>
	and ct <= '${ct1}'
</#if>
UNION
SELECT 
	o.id 			as id,
	o.fee			as fee,
	o.ct 			as ct,
	o.statium_id	as statiumId,
	o.final_fee 	as finalFee,
	o.qiuyou_fee	as qiuyouFee,
	o.reason 		as reason,
	o.coupon_id 	as couponId,
	o.customer_id 	as customerId,
	o.status 		as status,
	o.et 			as et,
	o.orders_type	as ordersType,
	o.pay_type		as payType
FROM oa_order o
WHERE status in ('ORDER_PAIED','ORDER_VERIFY','ORDER_PLAYING','ORDER_REFUNDING','ORDER_REFUNDED')
	and statium_id IN
    (SELECT t.id
     FROM oa_enjoy_game t
     WHERE t.gid IS NOT NULL
       AND EXISTS
         (SELECT *
          FROM oa_enjoy_game s
          LEFT JOIN oa_enjoy_game_site s2 ON s.site_id=s2.id
          WHERE s.id=t.gid
          <#if startTime??>
			AND s.start_time >= '${startTime}'
		  </#if>
		  <#if endTime??>
			AND s.start_time <= '${endTime}'
		  </#if>
		  <#if gameLevel??>
			AND s.game_level = '${gameLevel}'
		 </#if>
		 <#if gameType??>
			AND s.game_type = '${gameType}'
		 </#if>
		  <#if city??>
			AND (s2.province = '${city}' or s2.city = '${city}')
		  </#if>
		  <#if name??>
			AND s.short_title like '%ball-boss%'
		  </#if>
		  <#if statiumId??>
			AND s.statium_id like '%${statiumId}%'
		  </#if>
          )
     )
and id in 
	(select order_id 
		from 
			oa_enjoy_member m1 
		LEFT JOIN 
			oa_games_player p1 
		on 
			m1.player_id=p1.id 
		where
			1=1
			<#if pName??>
				AND p1.name='${pName}'
			</#if>
			<#if pCard??>
				AND p1.card_no='${pCard}'
			</#if>
			<#if phone??>
				and p1.user_id=(select id from sso_user where phone='${phone}' or username = '${phone}')
			</#if>
	)
<#if orderId??>
	and id='${orderId}'
</#if>
<#if ct0??>
	and ct >= '${ct0}'
</#if>
<#if ct1??>
	and ct <= '${ct1}'
</#if>
) c 
<#if offset??>
	limit 
	${offset}, ${pageSize}
</#if>