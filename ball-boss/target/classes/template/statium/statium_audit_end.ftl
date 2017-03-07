<h3>${action}</h3>
<p><b>场馆名称：</b>${ statium.name}		</p>
<p><b>审核日期：</b>${ statium.et?string("yyyy-MM-dd HH:mm:ss") } 	</p>
<#if reason??>
	<p><b>原因：</b>${reason}<p>
</#if>