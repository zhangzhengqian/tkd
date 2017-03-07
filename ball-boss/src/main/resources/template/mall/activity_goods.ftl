select g.goog_id
    from
    crm_mall_activity_goods g
where
    g.activity_id
in
    (select
         a.id
    from
        crm_mall_activity a
    where
        a.delete_flag = 0);