SELECT 
	DISTINCT t.space_id,t.space_code 
FROM 
	oa_order_item t 
WHERE 
	t.space_id = '${spaceId}'
AND
	t.order_id in (
					SELECT
						 r.id
					FROM
						 oa_order r
					WHERE
					 	 r.status not in ('ORDER_REFUNDED','ORDER_CANCELED','ORDER_PLAYING','ORDER_DELETED')
					  AND 
					 	 r.statium_id = '${statiumId}' 
		         )