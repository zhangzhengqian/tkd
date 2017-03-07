select   service,method,sum(counter) as total from statistic_ualog
group by service,method  
order by sum(counter)  desc
limit 0,10