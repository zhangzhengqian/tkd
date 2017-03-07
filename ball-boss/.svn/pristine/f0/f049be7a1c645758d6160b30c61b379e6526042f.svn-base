<#list messageList as message >
	<a id="message_item_${message.id}" onclick="message_func.view('${message.id}')" href="#" class="message-item list-group-item <#if message.status == 'new' >list-group-item-success<#else>list-group-item-default</#if>" > 
		<p class="list-group-item-heading"><b>${message.title}</b></p>
		<p class="list-group-item-text">${message.ct?string("yyyy-MM-dd HH:mm:ss")}</p>
	</a>
</#list>