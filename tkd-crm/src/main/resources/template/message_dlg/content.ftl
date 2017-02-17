<div class="panel panel-default">
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12"> 
				<p><b>标题:</b>${message.title}</p>	
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6"> <b>发送人:</b> ${message.fromUser} </div>
			<div class="col-sm-6 text-right"> <b>${message.ct?string("yyyy-MM-dd HH:mm:ss")}</b> </div>
		</div>
		<hr>
		<!-- 站内信内容 -->
		<div class="row">
			<div class="col-sm-12">
				${message.content}	
			</div>
		</div>
	</div>
</div>