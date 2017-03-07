SELECT 
	t.id AS id, 
	t.content AS content,
	t.ct AS ct,
	t.status AS status, 
	(
	SELECT phone
	FROM sso_user
	WHERE id = t.user_id
	) AS phone, 
	(
	SELECT nick_name
	FROM sso_user
	WHERE id = t.user_id
	) AS nickName, 
	(
	SELECT qiuyouNo
	FROM sso_user
	WHERE id = t.user_id
	) AS qiuyouNo, 
	(
	SELECT COUNT(id)
	FROM oa_qiuyouzone_comment
	WHERE qiuyouzone_id = t.id
	) AS cont1, 
	(
	SELECT COUNT(id)
	FROM oa_qiuyouzone_like
	WHERE qiuyouzone_id = t.id
	) AS cont2
FROM 
	oa_qiuyouzone t
where
	status!=2
	<#if userId??&&userId?length gt 0>
		and user_id='${userId}'
	</#if>
	<#if content??&&content?length gt 0>
		and content like '%${content}%'
	</#if>
<#if offset??>
limit 
	${offset}, ${pageSize}
</#if>