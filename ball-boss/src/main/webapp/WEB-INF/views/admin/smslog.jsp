<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
			<div class="row">
				<div class="col-sm-6 text-left">
						<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span> 短信日志检索
				</div>
			</div>
	</div>
	<div class="panel-body">

		<div class="row">
			<div class="col-lg-12">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="手机号" id="phone" >
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="query_btn">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							&nbsp;查询&nbsp;&nbsp;
						</button>
					</span>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
				  <ul class="list-group" id="log-body" >
				    <li class="list-group-item"></li>
				  </ul>
				</div>			
			</div>
		</div>
		
	</div>
</div>


<script>
	$(function() {
		menu.active('#smslog-man');
		
		var smslogQuery = function( phone ){
			$.ajax({
				type : "post",
				url : "${ctx}/admin/smslog/query",
				dataType : "text",
				data : {phone:phone},
				success : function(msg) {
					var msg = JSON.parse(msg);
					var data = msg.entity.data;
					$("#log-body").html("");
					if(data.length>0){
						for( i in data ){
							var itm = data[i];
							var line = '<li class="list-group-item"><b>'+itm.ct+'</b> : '+itm.comment+'</li>';
							$("#log-body").append(line);
						}
					}else{
						var line = '<li class="list-group-item">没有找到数据</li>';
						$("#log-body").append(line);				
					}
					
				}
			});
		}
		
		$("#query_btn").on('click',function(){
			var phone = $("#phone").val();
			smslogQuery(phone);
		});
		
	});
</script>

