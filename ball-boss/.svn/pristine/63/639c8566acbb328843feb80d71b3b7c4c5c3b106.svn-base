select 
    x, sport_type, count(sport_type) as y
from
    (select distinct
        order_id, date_format(ct, '%H') as x, sport_type
    from
        oa_order_item
    where
        date_format(ct, '%Y-%m-%d') = '${now?string("yyyy-MM-dd")}') a
group by x , sport_type