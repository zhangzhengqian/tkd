SELECT channel from(
              select version,channel,sum(counter) as y 
    from statistic_register_user
    where channel !=''
   <#if from??> 
		   and left(ct,10) < '${from?string("yyyy-MM-dd")}' 
	</#if> 
	<#if to??>
	 	  and left(ct,10) >= '${to?string("yyyy-MM-dd")}'
	</#if> 
    group by version,channel
    order by  y  desc
    limit 10) aa