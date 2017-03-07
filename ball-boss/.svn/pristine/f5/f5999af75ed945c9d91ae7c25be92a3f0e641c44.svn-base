SELECT
	c.id			as id,
	c.image			as image,
	c.name			as name,
	c.position		as position,
	c.type			as type,
	c.resourceId	as resourceId,
	c.sort			as sort,
	c.province		as province,
	c.city			as city,
	c.areaCode		as areaCode
FROM
(
SELECT
	c1.id			as id,
	c1.image		as image,
	c1.name			as name,
	c1.position		as position,
	c1.type			as type,
	c1.resource_id	as resourceId,
	c1.sort			as sort,
	c1.province		as province,
	'通用'			as city,
	c1.area_code	as areaCode
FROM
(
select 
	*
from 
	oa_carousel
where 
	area_code is null
	<#if name??>
		and name like '%${name}%'
	</#if>
	<#if position??>
		and position = '${position}'
	</#if>
	<#if type??>
		and type = '${type}'
	</#if>
order by 
	sort
) c1
Union All
select 
	c2.id			as id,
	c2.image		as image,
	c2.name			as name,
	c2.position		as position,
	c2.type			as type,
	c2.resource_id	as resourceId,
	c2.sort			as sort,
	c2.province		as province,
	c2.city			as city,
	c2.area_code	as areaCode
from
(
select 
	* 
from
	oa_carousel
where 
	1=1
	<#if name??>
		and name like '%${name}%'
	</#if>
	<#if position??>
		and position = '${position}'
	</#if>
	<#if type??>
		and type = '${type}'
	</#if>
	<#if areaCode??>
		and area_code = '${areaCode}'
	</#if>
order by 
	sort
)c2
)c
<#if offset??>
limit 
	${offset}, ${pageSize}
</#if>