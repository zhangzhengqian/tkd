update 
	oa_order_bill 
set
	status=${status}
where
	bill_id='${billId}' and status=${status-1}