SELECT
	 DISTINCT `year`,province,city,count(id) _count 
from 
	oa_enjoy_game_site 
 WHERE `status` = 0 
 GROUP BY province,city;
