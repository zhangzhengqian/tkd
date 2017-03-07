select
 clasli1,
 clasli2,
 field_no,
 group_id,
 game_id,
 turn 
from 
	oa_enjoy_group_schedule 
where 
	game_id 
in
	(
		<#list gameIds as sta>
			'${sta}'<#if sta_has_next==true>,</#if>
		</#list>
	)
order by 
	field_no,
	turn,
	FIELD(game_id,
	<#list gameIds as sta>
		'${sta}'<#if sta_has_next==true>,</#if>
	</#list>
	),
	game_id,
	group_id