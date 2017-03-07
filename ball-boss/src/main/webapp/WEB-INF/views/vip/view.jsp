<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球馆会员卡</title>
	<style type="text/css">.point{cursor:pointer;}</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 球馆会员卡</li>
        <li class="active">详情</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容  style="background-color: #CDFFFF;"  -->
  <div class="panel panel-default">
  <div class="panel-heading">球馆会员卡</div>
  <div class="panel-body">
    <div class="row">
    	<div class="col-md-2 text-right">场馆名称：</div>
  		<div class="col-md-6">${vipCard.statiumName}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">会员卡名称：</div>
  		<div class="col-md-6">${vipCard.name}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">会员卡号：</div>
  		<div class="col-md-6">${vipCard.cardNumber}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">面额：</div>
  		<div class="col-md-6">${lf:formatMoney(vipCard.amount) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">余额：</div>
  		<div class="col-md-6">${lf:formatMoney(vipCard.balance) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">支出：</div>
  		<div class="col-md-6">${lf:formatMoney(vipCard.expenditure) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">收入：</div>
  		<div class="col-md-6">${lf:formatMoney(vipCard.revenue) }元</div>
    </div>
   	<div class="row">
    	<div class="col-md-2 text-right">盈利：</div>
  		<div class="col-md-6">${lf:formatMoney(vipCard.profit) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">办理人：</div>
  		<div class="col-md-6">${vipCard.transactor }</div>
    </div>
    	<div class="row">
    	<div class="col-md-2 text-right">办理人电话：</div>
  		<div class="col-md-6">${vipCard.transactorTel }</div>
    </div>
	</table>
  </div>
</div>

<div class="row"><!-- 操作按钮组 -->
   <div class="col-md-12">
     <div class="btn-group-sm pull-right mtb10">
       <a class="btn btn-primary" href="${ctx}/vip/exportVipCardLog/${vipCard.id}"><span class="glyphicon"></span> 导出Excel</a>
     </div>
   </div>
</div><!-- /操作按钮组 -->
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${vipCard.statiumName}-订单流水</h3>
  </div>
  <div class="panel-body">
     <table class="table table-bordered table-hover">
	    <thead>
			<tr>
				<th class="text-center">序号</th>
				<th>会员卡号</th>
				<th>会员卡名称</th>
				<th>场馆名称</th>
				<th>会员卡余额</th>
				<th>支出</th>
				<th>收入</th>
				<th>事件</th>
				<th>盈利</th>
				<th>订单号</th>
				<th>使用时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${data.content}" var="log" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${log.cardNumber }</td>
					<td style="text-align: center;">${log.name }</td>
					<td style="text-align: center;">${log.statiumName }</td>
					<td style="text-align: center;">${lf:formatMoney(log.balance)}</td>
					<td style="text-align: center;">${lf:formatMoney(log.expenditure)}</td>
					<td style="text-align: center;">${lf:formatMoney(log.revenue)}</td>
					<td style="text-align: center;">${log.event }</td>
					<td style="text-align: center;">${lf:formatMoney(log.profit)}</td>
					<td style="text-align: center;"><a title="查看详情" href="${ctx }/orders/view/${log.orderId }">${log.orderId }</a></td>
					<td style="text-align: center;"><fmt:formatDate value="${log.ct}" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
		</tbody>
     </table>
     <div class="form-group form-group-sm">
		<hr>
		<div class="col-md-offset-3 col-md-2">
			<button type="button" class="btn btn-primary btn-block" id="recharge"><span class="glyphicon"></span> 充值</button>
		</div>
		<div class="col-md-2">
			<a href="${ctx}/vip/findViplogList/${vipCard.id}" class="btn btn-primary btn-block"><span class="glyphicon"></span> 充值记录</a>
		</div>
		<div class="col-md-2">
			<a class="btn btn-default btn-block" href="${ctx}/vip"><span class="glyphicon glyphicon-remove"></span> 返回</a>			  
		</div>
     </div>		
  </div>
    <tags:pagination page="${data}" />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
	</div>
  </div>
</div>

<div id="rechargeDiv" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">充值</h4>
         </div>
         <div class="modal-body">
         <form id="inputForm" action="${ctx}/vip/saveRecharge" method="post" class="form-horizontal">
         		<input type="hidden" id="vipCardId" name="vipCardId" value="${vipCard.id}"/>
            	<div class="row">
		            <div class="form-group form-group-sm" style="line-height: 40px;">
				    	<div class="col-md-4 text-right">会员卡号</div>
				  		<div class="col-md-6">${vipCard.cardNumber}</div>
				    </div>
			   </div> 
			   <div class="row">
			    <div class="form-group form-group-sm" style="line-height: 40px;">
			    	<div class="col-md-4 text-right">卡内余额</div>
			  		<div class="col-md-6">${lf:formatMoney(vipCard.balance) }元</div>
			    </div>
			   </div> 
			   <div class="row">
				<div class="form-group form-group-sm" style="line-height: 40px;">
					<label for="rechargeAmount" class="col-md-4 text-right"><span class="text-red"></span>充值金额</label>
				    <div class="col-md-3 form-inline">
				    	<input placeholder="请输入充值金额" type="text" class="form-control" id="rechargeAmount" name="rechargeAmount"/>
				    </div>
				</div>
				</div> 
			  <div class="row">
			    <div class="form-group form-group-sm" style="line-height: 40px;">
					<label for="rechargePerson" class="col-md-4 text-right"><span class="text-red"></span>充值人员</label>
				    <div class="col-md-3 form-inline">
				    	<input placeholder="请输入充值人员" type="text" class="form-control" id="rechargePerson" name="rechargePerson"/>
				    </div>
				</div>
	         </div>
	         </form>
         </div>
         <div class="modal-footer" style="text-align:center;">
            <button type="button" class="btn btn-primary" id="submit_btn">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	menu.active('#vip-man');
	// 充值
	$("#recharge").click(function() {
		$("#rechargeDiv").modal();
	});
	
	$("#submit_btn").click(function(){
		$("#inputForm").submit();
	});
	
	$('#inputForm').validate({
		rules: {
			"rechargeAmount" : {
				required: true,
				digits:true
			},
			"rechargePerson" : {
				required: true,
			}
		},
		messages: {
			
		},
		errorPlacement : function(error, element) {
			error.appendTo($(element).parent());
		}
	});
});
</script>
</body>
</html>