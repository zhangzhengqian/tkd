SELECT prize_type as prizeType,
       count(prize_type) as amount
FROM oa_user_prize
WHERE statium_id='${statiumId}'
GROUP BY prize_type