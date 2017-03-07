SELECT 
	   count(id) as cont,
	   count(CASE WHEN is_use = 1 THEN 1 END) AS used,
       count(CASE WHEN is_use = 0 THEN 1 END) AS unused,
       sum(CASE WHEN is_use = 1 THEN (CASE WHEN order_time IS NULL THEN amount ELSE amount*order_time END)END) AS usedamount,
       sum(CASE WHEN is_use = 0 THEN amount*${couponType} END) AS unusedamount,
       sum(amount) AS sumamount
FROM oa_coupon_history
WHERE coupon_id='${couponId}'