select c.prize_type,c.prize_name,c.amount,c.money,s.lottery
from (select p.prize_type,count(p.prize_type) as lottery
       from oa_prize_pool p 
       where p.lottery_month="${lotteryMonth}" AND p.status=1 
       group by p.amount,p.prize_type) as s
right join (select * from oa_prize_config where lottery_month="${lotteryMonth}") as c
on (s.prize_type=c.prize_type)