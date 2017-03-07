<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆结账</title>
	<style type="text/css">
		<!--
		.tree-container {
		 	overflow: auto;
		}
	-->
	</style>
</head>

<body>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
  	<div class="row">
  		<div class="col-md-5">
			<div class="btn-group-sm">
				  <ul class="breadcrumb" style="display:inline;">
			        <li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
			        <li class="active">订单外呼</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	<div class="row">
  		<div class="col-md-6 tree-container" style="min-height:802.556px;">
  			<br>
  			<div class="row">
	  			<div class="col-md-12 text-right">
					<a class="btn btn-primary" id="handle_order">获取任务</a>
				</div>
			</div>
			<br>
	  		<div class="row">
	  			<div class="col-md-12">
		  			<table class="table table-bordered table-condensed table-hover">
		  				<thead>
		  					<tr>
		  						<th>订单号</th>
		  						<th>场馆</th>
		  						<th>状态</th>
		  						<th>操作</th>
		  					</tr>
		  				</thead>
		  				<tbody id="tasks">
		  					<c:forEach items="${HandleOrder}" var="order">
			  					<tr style="cursor: pointer;" statium_id="${order.statiumId}" class="selOrder" order_id="${order.id}">
			  						<td>${order.id}</td>
			  						<td>${order.name}</td>
			  						<td>待处理</td>
			  						<td><a order_id="${order.id}" class="discard_order" href="javascript:;">丢弃</a></td>
			  					</tr>
		  					</c:forEach>
		  					<c:forEach items="${HandlingOrder}" var="order">
		  						<tr  style="cursor: pointer;" statium_id="${order.statiumId}" class="selOrder <c:choose>
							                <c:when test="${order.handleStatus == null}"></c:when>
							                <c:when test="${order.handleStatus == 2}">danger</c:when>
							        	</c:choose>" order_id="${order.id}">
			  						<td>${order.id}</td>
			  						<td>${order.name}</td>
			  						<td>
			  							<c:choose>
							                <c:when test="${order.handleStatus == null}">待处理</c:when>
							                <c:when test="${order.handleStatus == 2}">稍后处理</c:when>
							        	</c:choose>
			  						</td>
			  						<td>
			  							<c:choose>
							                <c:when test="${order.handleStatus == null}"><a order_id="${order.id}" class="discard_order" href="javascript:;">丢弃</a></c:when>
							                <c:when test="${order.handleStatus == 2}"></c:when>
							        	</c:choose>
							        </td>
			  					</tr>
		  					</c:forEach>
		  				</tbody>
		  			</table>
	  			</div>
	  		</div>
  		</div>
  		<div class="col-md-6 tree-container"  style="min-height:802.556px;">
  			<div class="row">
	  			<div id="historyTable" class="col-md-12">
	  			
	  			
	  			</div>
  			</div>
  	
  		</div>
  	</div>
   </div><!-- /右侧主体内容 -->
</div>
<div class="modal fade" id="backHandleModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">备注</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group">
						<input class="form-control input-sm" id="reason" type="text" name="reason" />
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="backForm()"
					data-dismiss="modal" aria-hidden="true">确定</a>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="nearByStatium" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">附近场馆</h4>
				</div>
				<div class="modal-body" id="statiumTable">
					
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">关闭</a>
				</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
	$(function(){
		menu.active('#orders-handle');
		$('#adminFooter').hide();
		$("#tasks").on('click','.selOrder',function(){
			var statiumId = $(this).attr("statium_id");
			var curOrderId = $(this).attr("order_id");
			$(this).parent().children().removeClass("success");
			var orders = new Array();
			$(this).parent().children("[statium_id="+statiumId+"]").each(function(){
				var id = $(this).attr("order_id");
				if(curOrderId==id&&orders.length!=0){
					var first = orders[0];
					orders[0] = id;
					orders.push(first);
				}else{
					orders.push(id);
				}
			});
			var orderId = orders.join('-');
			$(this).parent().children("[statium_id="+statiumId+"]").addClass("success");
			$.post(ctx + '/orders/item/'+orderId,function(res){
				if(res.result){
					var data = res.data;
					var temp = new EJS({url: '${ctx}/static/template/orderItem.ejs?ver='+Math.random()});
					var html = temp.render({order:data});
					$('#historyTable').html(html);
			     }else{
			    	 myAlert(res.reason,"error");
			    	 return false;
				 }
			},'json');
		});
		$("#tasks").on('click','.discard_order',function(event){
			event.stopPropagation();
			var that = $(this);
			var orderId = $(this).attr("order_id");
			$.post(ctx + '/orders/discard/'+orderId,function(res){
				console.log(res.result);
				if(res.result){
					$('#historyTable').html('');
					that.parent().parent().remove();
			     }else{
			    	 myAlert(res.reason,"error");
			    	 return false;
				 }
			},'json');
		});
		$('#handle_order').on('click',function(){
			$(this).attr("disabled",true);
			setTimeout(function(){
            	$("#handle_order").attr("disabled",false);
    		},5000);
			$.post(ctx + '/orders/handleTask',function(res){
				if(res.result){
					if(res["data"]=="not_get_task"){
						return false;
					}
					var orders = res["data"];
					var temp = new EJS({url: '${ctx}/static/template/handleOrders.ejs?ver='+Math.random()});
					var html = temp.render({orders:orders});
	                // 给模板加载数据
	                $("#tasks").prepend(html);
			     }else{
			    	 myAlert(res.reason,"error");
			    	 return false;
				 }
			},'json');
		});
		
		$(document).on('mouseover','.phone',function(){
			var scrollHeight = $(document).scrollTop();
			var point = $(this).offset();
			var width = $(this).width();
			var height = $(this).outerHeight();
			var phone = $(this).text();
			var body_width = $(document.body).width();
			var alert_left = point.left+width;
			if(body_width-alert_left<200){
				alert_left = point.left-width;
			}
			$.bootstrapGrowl('<div class="form-inline"><span>'+phone+'</span><a href="javascript:callOut(\''+phone+'\')" class="btn btn-primary btn-sm pull-right">呼叫</a></div>', {
				position:'absolute',
	            type: 'info',
	            align: (alert_left)+','+(point.top-scrollHeight)
	        });
		});
		
	});
	
	function callOut(phone){
		callPhone(phone);
	}
	
	function simpVerify(id){
		bootbox.confirm('是否确认'+id+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
		             cache: true,
		             type: "POST",
		             url:'${ctx}/orders/orderVerifyAjx/'+id,
		             data:{},
		             async: false,
		             error: function(request) {
		             	common.showMessage("确认订单失败！");
		             },
		             success: function(data) {
		             	data = eval("("+data+")");
		             	if(!data.result){
		             		if(data.reason){
		             			common.showMessage("确认订单失败！");
		             		}else{
		             			common.showMessage(data.reason);
		             		}
		             		
		             	}else{
		             		common.showMessage("确认订单成功！");
		             		$("#v_"+id).hide();
		             		$("#h_"+id).hide();
		             		$("#tasks").find("tr[order_id="+id+"]").hide();
		             		$("#hs_"+id).text("已处理");
		             	}
		             }
		         });
		    }
		  });
	}
	
	function simpHandling(id){
		$("#backHandleModel").attr("order_id",id);
		$("#backHandleModel").modal();
	}
	
	function selNear(id){
		var dis = $("#distance").val();
		if(dis==""){
			myAlert("请输入查询距离","error");
			return;
		}
		 $.ajax({
	            cache: true,
	            type: "POST",
	            url:'${ctx}/statium/nearByStatiums/'+id+'/'+dis,
	            data:{},
	            async: false,
	            error: function(request) {
	            	common.showMessage("查询附近场馆失败！");
	            },
	            success: function(data) {
	            	if(data!=null&&data!=''){
		            	data = eval("("+data+")");
						var temp = new EJS({url: '${ctx}/static/template/nearByStatium.ejs?ver=1.0'});
						var html = temp.render({statiums:data});
						$('#statiumTable').html(html);
						$("#nearByStatium").modal();
	            	}
	            	console.log(data);
	            }
	        });
	}
	
	function backForm(){
		var reason = $("#reason").val();
		if(reason==''){
			myAlert("请填写备注！","error");
		}
		var orderId = $("#backHandleModel").attr("order_id");
		 $.ajax({
            cache: true,
            type: "POST",
            url:'${ctx}/orders/backHandel?orderId='+orderId+"&reason="+reason,
            data:{},
            async: false,
            error: function(request) {
            	common.showMessage("确认订单失败！");
            },
            success: function(data) {
            	data = eval("("+data+")");
            	if(!data.result){
            		if(data.reason){
            			common.showMessage("确认订单失败！");
            		}else{
            			common.showMessage(data.reason);
            		}
            	}else{
            		$("#hs_"+orderId).text("稍后处理");
			        $("#tasks").find("tr[order_id="+orderId+"]").removeClass("success").addClass("danger");
			        $("#tasks").find("tr[order_id="+orderId+"]").children("td:eq(2)").text("稍后处理");
            	}
            }
        }); 
	}
</script>
</body>
</html>