select
    IF(score1 is NULL,0,score1) AS score1,
    IF(score2 is NULL,0,score2) AS score2,
    clasli1 AS clasli1,
    clasli2 AS clasli2
from
    oa_enjoy_group_schedule
where
    game_id = '${game_id}'
    and
    group_id = '${group_id}'