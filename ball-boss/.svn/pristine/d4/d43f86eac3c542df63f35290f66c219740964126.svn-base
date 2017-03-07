<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单管理</title>
	<style type="text/css">
	.point{cursor:pointer;}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
        <li class="active">
        订单详情 
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容  style="background-color: #CDFFFF;"  -->
		<div class="panel panel-default">
  <div class="panel-heading">订单信息</div>
  <div class="panel-body">
    <div class="row">
    	<div class="col-md-2 text-right">订单号：</div>
  		<div class="col-md-6">${order.id}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单状态：</div>
  		<div class="col-md-6">
  			<c:choose>
                <c:when test="${order.status == 'ORDER_NEW'}">待付款</c:when>
                <c:when test="${order.status == 'ORDER_CANCELED'}">交易关闭</c:when>
                <c:when test="${order.status == 'ORDER_PLAYING'}">交易成功</c:when>
                <c:when test="${order.status == 'ORDER_PAIED'}">已付款</c:when>
                <c:when test="${order.status == 'ORDER_REFUNDING'}">退款中</c:when>
                <c:when test="${order.status == 'ORDER_REFUNDED'}">已退款</c:when>
                <c:when test="${order.status == 'ORDER_VERIFY'}">已确认</c:when>
        	</c:choose>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单类型：</div>
  		<div class="col-md-6">
  			<c:choose>
  				<c:when test="${order.ordersType == '0'}">订场订单</c:when>
                <c:when test="${order.ordersType == '1'}">教陪练订单</c:when>
                <c:when test="${order.ordersType == '2'}">活动订单</c:when>
                <c:when test="${order.ordersType == '3'}">约球订单</c:when>
				<c:when test="${order.ordersType == '7'}">白金赛订单</c:when>
  			</c:choose>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单金额：</div>
  		<div class="col-md-6">${lf:formatMoney(order.fee) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">优惠券抵扣：</div>
  		<div class="col-md-6">
  			<c:if test="${empty order.couponAmount}">
  			0元
  			</c:if>
  			<c:if test="${!empty order.couponAmount}">
  			${lf:formatMoney(order.fee)-lf:formatMoney(order.qiuyouFee)-lf:formatMoney(order.finalFee)}元
  			</c:if>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">球友卡金额：</div>
    	<c:if test="${!empty order.qiuyouFee}">
	  		<div class="col-md-6">${lf:formatMoney(order.qiuyouFee) }元</div>
    	</c:if>
    </div>

	  <div class="row">
		  <div class="col-md-2 text-right">账户支付金额：</div>
		  <c:if test="${!empty order.qiuyouFee}">
			  <div class="col-md-6">${lf:formatMoney(order.acountFee) }元</div>
		  </c:if>
	  </div>
    <div class="row">
    	<div class="col-md-2 text-right">奖金账户支付金额：</div>
  		<div class="col-md-6">${lf:formatMoney(order.bounsAccountFee) }元</div>
    </div>
	  <div class="row">
		  <div class="col-md-2 text-right">实付金额：</div>
		  <div class="col-md-6">${lf:formatMoney(order.finalFee) }元</div>
	  </div>
    <div class="row">
    	<div class="col-md-2 text-right">成本金额：</div>
  		<div class="col-md-6">${lf:totalCostPrice(order.id) }元</div>
    </div>
   	<div class="row">
    	<div class="col-md-2 text-right">补贴金额：</div>
  		<div class="col-md-6">${lf:totalSubsidies(order.id) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单创建时间：</div>
  		<div class="col-md-6"><fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人：</div>
  		<div class="col-md-6">${order.userName}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人联系电话：</div>
  		<div class="col-md-2">${order.userPhone}</div>
  		<div  class="point col-md-2" style="width: 100px;" onclick="callPhone('${order.userPhone}')">呼叫</div>
  		<bgsound src="IP/xxx.mp3" loop="-1" volume="-5000" balance="0">
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">支付方式：</div>
  		<div class="col-md-6">
  		<c:choose>
  			<c:when test="${order.payType == '1'}">支付宝付款</c:when>
            <c:when test="${order.payType == '2'}">微信付款</c:when>
            <c:when test="${order.payType == '3'}">公众平台付款</c:when>
            <c:when test="${order.payType == '4'}">球友圈付款</c:when>
            <c:when test="${order.payType == '5'}">账户支付</c:when>
            <c:when test="${order.payType == '6'}">运动基金支付</c:when>
            <c:when test="${order.payType == '9'}">招行支付</c:when>
            <c:when test="${order.payType == '11'}">京东支付</c:when>
            <c:when test="${order.payType == '15'}">乐米支付</c:when>
  		</c:choose>
  		</div>
    </div>
    <c:if test="${order.payType == '1' or order.payType == '2'}">
    <div class="row">
    	<div class="col-md-2 text-right">
    		交易号：
    	</div>
  		<div class="col-md-6">
  			${order.number}
  		</div>
    </div>
    </c:if>
    <br/>
    <table class="table table-bordered table-condensed table-hover">
    <c:if test="${order.ordersType == '0'}">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">球馆名称</th>
						<th style="border-bottom-width: 0px;">预订场号</th>
						<th style="border-bottom-width: 0px;">预约时间</th>
						<th style="border-bottom-width: 0px;" colspan="2">球馆联系电话</th>
						<th style="border-bottom-width: 0px;">地址</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderItemVoList}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.statiumDetail.name }</td>
						<td>${item.spaceName }【${item.spaceCode }】</td>
						<td>${item.orderTimeStr }</td>
						<td>${item.statiumDetail.tel }</td><td  class="point" onclick="callPhone('${item.statiumDetail.tel}')">呼叫</a></td>
						<td>${item.statiumDetail.address}</td>
						</tr>
					</c:forEach>
				</tbody>
	</c:if>	
	<c:if test="${order.ordersType == '1'}">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">教陪练类型</th>
						<th style="border-bottom-width: 0px;">姓名</th>
						<th style="border-bottom-width: 0px;">预约时间</th>
						<th style="border-bottom-width: 0px;"  colspan="2">教陪练联系电话</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderItemVoList}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.coach.userType }</td>
						<td>${item.coach.name }</td>
						<td>${item.orderTimeStr }</td>
						<td>${item.coach.phone}</td><td  class="point" onclick="callPhone('${item.coach.phone}')">呼叫</a></td>
  		
						</tr>
					</c:forEach>
				</tbody>
	</c:if>	
	<c:if test="${order.ordersType == '2'}">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">活动名称</th>
						<th style="border-bottom-width: 0px;">活动地点</th>
						<th style="border-bottom-width: 0px;">活动开始时间</th>
						<th style="border-bottom-width: 0px;">报名人姓名</th>
						<th style="border-bottom-width: 0px;"  colspan="2">报名人联系方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderItemVoList}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.activity.name }</td>
						<td>${item.activity.address }</td>
						<td>${item.activity.startTime }</td>
						<td>${item.memberList.name }</td>
						<td>${item.memberList.phone }</td><td  class="point" onclick="callPhone('${item.coach.phone}')">呼叫</a></td>
						</tr>
					</c:forEach>
				</tbody>
	</c:if>
	<c:if test="${order.ordersType == '4'}">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">赛事名称</th>
						<th style="border-bottom-width: 0px;">赛事地点</th>
						<th style="border-bottom-width: 0px;">赛事开始时间</th>
						<th style="border-bottom-width: 0px;">赛事类别</th>
						<th style="border-bottom-width: 0px;">报名人/战队</th>
						<th style="border-bottom-width: 0px;"  colspan="2">报名联系方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderItemVoList}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.gamesVo.name }</td>
						<td>${item.gamesVo.address }</td>
						<td>${item.gamesVo.startTime }</td>
						<td>
							<c:choose>
				                <c:when test="${item.gamesVo.type=='1'}">团体</c:when>
				                <c:when test="${item.gamesVo.type=='0'}">个人</c:when>
				        	</c:choose>
						</td>
						<td>
							<c:choose>
				                <c:when test="${item.gamesVo.corpsName!=null}">${item.gamesVo.corpsName}</c:when>
				                <c:when test="${item.gamesVo.userName!=null}">${item.gamesVo.userName}</c:when>
				        	</c:choose>
						</td>
						<td>${order.userPhone}</td><td  class="point" onclick="callPhone('${item.coach.phone}')">呼叫</a></td>
						</tr>
					</c:forEach>
				</tbody>
	</c:if>	
	</table>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">流水日志</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered table-condensed table-hover">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">订单业务行为</th>
						<th style="border-bottom-width: 0px;">操作者</th>
						<th style="border-bottom-width: 0px;">时间</th>
						<th style="border-bottom-width: 0px;">描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderLogVoList}" var="log" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>
							<c:choose>
					  			<c:when test="${log.action == 'ORDER_ACTION_CREATE'}">下单</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_PLAY'}">开场</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_CANCEL'}">取消</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_TIMEOUT'}">超时</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_BILL'}">结账</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_DELETE'}">删除</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_PAY'}">支付成功</c:when>
					            <c:when test="${log.action == 'ORDER_ACTION_REFUND'}">确认退款</c:when>
					            <c:when test="${log.action == 'APPLY_ACTION_REFUND'}">申请退款</c:when>
					            <c:when test="${log.action == 'ORDER_VERIFY'}">已确认</c:when>
					  						            <c:when test="${log.action == 'ORDER_ACTION_UPDATE'}">修改订单</c:when>
					  	
					  		</c:choose>
						</td>
						<td>
						<c:if test="${!empty log.operatorType }">
							<c:if test="${log.operatorType == 1}">
								运营人员：${log.operatorName}
							</c:if>
							<c:if test="${log.operatorType == 2}">
								客户：${log.operatorName}
							</c:if>
						</c:if>
						<c:if test="${empty log.operatorType }">
							未记录
						</c:if>
						</td>
						<td><fmt:formatDate value="${log.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							${log.comment}
						</td>
						</tr>
					</c:forEach>
				</tbody>
    </table>
  </div>
  
  <div class="panel-footer">
			<div class="row">
				<div class="col-sm-12 text-right">
						  	 <a class="btn btn-sm btn-default" href="${ctx }/orders/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
						 <shiro:hasPermission name="orders:verify">
							<c:if test="${order.status == 'ORDER_PAIED' && order.ordersType == '0'  && order.channel != 4}">
		                     		<a href="javascript:orderVerify('${order.id}','${order.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 确认</a>
			                 </c:if>
		                 </shiro:hasPermission>
		                 <shiro:hasPermission name="orders:applyRefund">
			                 <c:if test="${order.status == 'ORDER_PAIED'  && order.channel != 4}">
			                 			<a href="javascript:applyRefund('${order.id}','${order.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 申请退款</a>
			                 </c:if>
			                 <c:if test="${order.status == 'ORDER_REFUNDING'  && order.payType==5   && order.channel != 4}">
			                 
                 			<a onclick="confirmRefund('${order.id}','${order.id}','1')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至账户</a>
		                 	</c:if>
		                 	<c:if test="${order.status == 'ORDER_REFUNDING' &&order.payType==6  && order.channel != 4}">
                 				<a onclick="confirmRefund('${order.id}','${order.id}','2')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 运动基金退款</a>
		                 	</c:if>
		                 	<c:if test="${order.status == 'ORDER_REFUNDING'  && order.payType==9  && order.channel != 4}">
			                 			<a onclick="confirmRefund('${order.id}','${order.id}',3)" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至招行</a>
			                 </c:if>
			                 <c:if test="${order.status == 'ORDER_REFUNDING'  && order.payType==11  && order.channel != 4}">
			                 			<a onclick="confirmRefund('${order.id}','${order.id}',4)" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至京东</a>
			                 </c:if>
		                 	</shiro:hasPermission>
		                 <shiro:hasPermission name="orders:confirmRefund">
			                 <c:if test="${order.status == 'ORDER_REFUNDING'  && order.payType!=5&& order.payType!=6&& order.payType!=9&& order.payType!=11  && order.channel != 4}">
			                 			<a onclick="confirmRefund('${order.id}','${order.id}',0)" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至微信或支付宝</a>
			                 </c:if>
		                 </shiro:hasPermission>
				</div>
			</div>	
	</div>
  
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">相关订单</h3>
  </div>
  <div class="panel-body">
    <table class="table table-bordered table-hover">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">订单号</th>
						<th style="border-bottom-width: 0px;">球馆名称</th>
						<th style="border-bottom-width: 0px;">预订场号</th>
						<th style="border-bottom-width: 0px;">预约时间</th>
						<th style="border-bottom-width: 0px;"  colspan="2">球馆联系电话</th>
						<th style="border-bottom-width: 0px;">所在城市</th>
						<th style="border-bottom-width: 0px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.relatedOrders}" var="relatedOrder" varStatus="stat1">
						<c:forEach items="${relatedOrder.orderItemVoList}" var="orderItem" varStatus="stat2">
							<tr>
								<c:if test="${stat2.count==1}"><td <c:if test="${fn:length(relatedOrder.orderItemVoList)>1}">style="line-height:8.57142858"  rowspan="${fn:length(relatedOrder.orderItemVoList)}" </c:if> class="text-center"><a href="${ctx }/orders/view/${relatedOrder.id}">${relatedOrder.id }</a></td></c:if>
								<td>${orderItem.statiumDetail.name }</td>
								<td>${orderItem.spaceName }【${item.spaceCode }】</td>
								<td>${orderItem.orderTimeStr }</td>
								<td>${orderItem.statiumDetail.tel }</td><td  class="point" onclick="callPhone('${orderItem.statiumDetail.tel}')">呼叫</a></td>
								<td>${order.areaStr }</td>
								<c:if test="${stat2.count==1}">
									<td <c:if test="${fn:length(relatedOrder.orderItemVoList)>1}">style="line-height:8.57142858"  rowspan="${fn:length(relatedOrder.orderItemVoList)}" </c:if> class="text-center">
									<shiro:hasPermission name="orders:verify">
									<c:if test="${relatedOrder.status == 'ORDER_PAIED'  && relatedOrder.ordersType == '0'  && order.channel != 4}">
				                     		<a href="javascript:orderVerify('${relatedOrder.id}','${order.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 确认</a>
					                 </c:if>
					                 </shiro:hasPermission>
					                 <shiro:hasPermission name="orders:applyRefund">
						                 <c:if test="${relatedOrder.status == 'ORDER_PAIED' && relatedOrder.finalFee !=0  && order.channel != 4}">
						                 			<a href="javascript:applyRefund('${relatedOrder.id}','${order.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 申请退款</a>
						                 </c:if>
						                  <c:if test="${relatedOrder.status == 'ORDER_REFUNDING' &&relatedOrder.payType==5 && order.channel != 4}">
			                 				<a onclick="confirmRefund('${relatedOrder.id}','${order.id}','1')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至账户</a>
					                 	</c:if>
					                 	<c:if test="${relatedOrder.status == 'ORDER_REFUNDING' &&relatedOrder.payType==6  && order.channel != 4}">
			                 				<a onclick="confirmRefund('${relatedOrder.id}','${order.id}','2')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 运动基金退款</a>
					                 	</c:if>
					                 	<c:if test="${relatedOrder.status == 'ORDER_REFUNDING' &&relatedOrder.payType==9  && order.channel != 4}">
			                 				<a onclick="confirmRefund('${relatedOrder.id}','${order.id}','3')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 招行退款</a>
					                 	</c:if>
					                 	<c:if test="${relatedOrder.status == 'ORDER_REFUNDING' &&relatedOrder.payType==11  && order.channel != 4}">
			                 				<a onclick="confirmRefund('${relatedOrder.id}','${order.id}','4')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 京东退款</a>
					                 	</c:if>
					                 </shiro:hasPermission>
					                 <shiro:hasPermission name="orders:confirmRefund">
						                 <c:if test="${relatedOrder.status == 'ORDER_REFUNDING' &&relatedOrder.payType!=5&&relatedOrder.payType!=6&&relatedOrder.payType!=9&&relatedOrder.payType!=11  && order.channel != 4}">
						                 			<a onclick="confirmRefund('${relatedOrder.id}','${order.id}','0')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至微信或支付宝</a>
						                 </c:if>
					                 </shiro:hasPermission>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
    </table>
  </div>
  
</div>

	
  </div>
	<div class="modal fade" id="backorderModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
         <h4 class="modal-title">退款原因</h4>
      </div>
      <div class="modal-body" class=""> 	
         <form class="form-inline" method="POST" id="backorderForm">
      	<div id="backreason" class="form-group">
      	  <label>原因：</label>
      	   <select id="reason1" class="form-control input-sm" name="reason1">
      	            <option value="">请选择</option>
      	            <option value="用户要求退款">用户要求退款</option>
      	            <option value="场地被占用">场地被占用</option>
      	     </select>
      	</div>   
      	<div id="backtextDiv" class="form-group">
      	    <label >其他原因：</label>
             <input class="form-control input-sm" id="reason" type="text" name="reason"/>
      	</div>  
      	<input type="hidden" id="backOrderDetail" value="" name="orderId">
      	<input type="hidden" id="viewOrderDetail" value="" name="viewId">
      	<input type="hidden" value="ORDER_REFUNDING" name="status">
      	</form>
		 </div>
      <div class="modal-footer">
         <a href="javascript:;" class="btn" data-dismiss="modal" aria-hidden="true">取消</a>
      	<a href="javascript:;" class="btn btn-primary alert-to-ok" onClick="backForm()" data-dismiss="modal" aria-hidden="true">确定</a>
      </div>
   </div>
	</div>
</div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#orders-man');
	$('#adminFooter').hide();
});

function play(url)
{
    document.Player.URL=url
    document.Player.controls.play();          
}

function callPhone(phone){
	//200   4  parsererror  返回数据类型不对
	/* $.ajax({ 
	     url:'http://119.254.80.102/app?Action=Dialout&ActionID=1234567890&Account=N00000003412&Exten=13716895499&FromExten=8000&PBX=dh.pbx.4.5&ExtenType=Local', 
	     dataType:'jsonp', 
	     processData: false,  
	     type:'get', 
	     success:function(data){ 
	       alert(data); 
	     }, 
	     error:function(XMLHttpRequest, textStatus, errorThrown) { 
	       alert(XMLHttpRequest.status); 
	       alert(XMLHttpRequest.readyState); 
	       alert(textStatus); 
	     }});  */
	    
	//phone = "13811235799";
	if(phone.indexOf('-')>0){
		phone = phone.split('-')[0]+phone.split('-')[1];
	}
	 $.ajax({
        cache: true,
        type: "POST",
        url:'${ctx}/cph/callPhone/'+phone,
        data:{},
        async: false,
        error: function(request) {
        	alert("获取callId失败");
        },
        success: function(data) {
        	data = eval("("+data+")");
        	if(data.result || data.result == 'true'){
        		data = eval("("+data.data+")");
        		if(data.Succeed){
        			common.showMessage("呼叫成功");
        		}else{
        			common.showMessage(data.Message);
        		}
        	}else{
        		common.showMessage("呼叫失败");
        	}
        }
    }); 
}

/* function hangup(hpone){
	$.ajax({
        cache: true,
        type: "POST",
        url:'${ctx}/cph/hangup/'+hpone+"/"+actionId,
        data:{},
        async: false,
        error: function(request) {
        	common.showMessage("获取callId失败");
        },
        success: function(data) {
        	data = eval("("+data+")");
        	if(data.result || data.result == 'true'){
        		data = eval("("+data.data+")");
        		if(!data.data.Message){
        			common.showMessage("挂机成功");
        		}else{
        			common.showMessage("挂机失败");
        		}
        	}else{
        		common.showMessage("挂机失败");
        	}
        }
    });
} */

function confirmRefund(v,view_id,type){
	if(type==0){
		bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
	             cache: true,
	             type: "POST",
	             url:'${ctx}/orders/confirmRefund/'+v,
	             data:{},
	             async: false,
	             error: function(request) {
	             	common.showMessage("确认退款失败！");
	             },
	             success: function(data) {
	             	data = eval("("+data+")");
	             	if(!data.result || data.result == 'false'){
	             		if(!data.reason){
	             			common.showMessage("确认退款失败！");
	             		}else{
	             			common.showMessage(data.reason);
	             		}
	             		
	             	}else{
	             		common.showMessage("确认退款成功！");
	             		window.location.href="${ctx}/orders/view/"+view_id;
	             	}
	             }
	         });
		    }
		});
	}else if(type==1){
		//退款至账户
		bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
	             cache: true,
	             type: "POST",
	             url:'${ctx}/orders/confirmRefundNew/'+v,
	             data:{},
	             async: false,
	             error: function(request) {
	             	common.showMessage("确认退款至账户失败！");
	             },
	             success: function(data) {
	             	data = eval("("+data+")");
	             	if(!data.result || data.result == 'false'){
	             		if(!data.reason){
	             			common.showMessage("确认退款至账户失败！");
	             		}else{
	             			common.showMessage(data.reason);
	             		}
	             		
	             	}else{
	             		common.showMessage("确认退款至账户成功！");
	             		window.location.href="${ctx}/orders/view/"+view_id;
	             	}
	             }
	         });
		    }
		});
	}else if(type==2){
		//退款运动基金
		bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
	             cache: true,
	             type: "POST",
	             url:'${ctx}/orders/confirmRefundCompany/'+v,
	             data:{},
	             async: false,
	             error: function(request) {
	             	common.showMessage("运动基金退款失败！");
	             },
	             success: function(data) {
	             	data = eval("("+data+")");
	             	if(!data.result || data.result == 'false'){
	             		if(!data.reason){
	             			common.showMessage("运动基金退款失败！");
	             		}else{
	             			common.showMessage(data.reason);
	             		}
	             		
	             	}else{
	             		common.showMessage("运动基金退款成功！");
	             		window.location.href="${ctx}/orders/view/"+view_id;
	             	}
	             }
	         });
		    }
		});
	}else if(type==3){
		bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
	             cache: true,
	             type: "POST",
	             url:'${ctx}/orders/confirmRefundCmb/'+v,
	             data:{},
	             async: false,
	             error: function(request) {
	             	common.showMessage("招行退款失败！");
	             },
	             success: function(data) {
	            	 console.log(data);
	             	data = eval("("+data+")");
	             	if(!data.result || data.result == 'false'){
	             		if(!data.reason){
	             			common.showMessage("招行退款失败！");
	             		}else{
	             			common.showMessage(data.reason);
	             		}
	             		
	             	}else{
	             		common.showMessage("招行退款成功！");
	             		window.location.href="${ctx}/orders/view/"+view_id;
	             	}
	             }
	         });
		    }
		});
	}else{
		bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
		    if(result) {
		    	$.ajax({
	             cache: true,
	             type: "POST",
	             url:'${ctx}/orders/confirmRefundJd/'+v,
	             data:{},
	             async: false,
	             error: function(request) {
	             	common.showMessage("京东退款失败！");
	             },
	             success: function(data) {
	            	 console.log(data);
	             	data = eval("("+data+")");
	             	if(!data.result || data.result == 'false'){
	             		if(!data.reason){
	             			common.showMessage("京东退款失败！");
	             		}else{
	             			common.showMessage(data.reason);
	             		}
	             		
	             	}else{
	             		common.showMessage("京东退款成功！");
	             		window.location.href="${ctx}/orders/view/"+view_id;
	             	}
	             }
	         });
		    }
		});
	}
	return false;
}

function orderVerify(v,view_id){
	bootbox.confirm('是否确认'+v+'订单?', function(result) {
		if (result) {
			window.location.href="${ctx}/orders/orderVerify/"+v+"/"+view_id;
		}
	});
}

function applyRefund(v,view_id){
$("#backOrderDetail").val(v);
$("#viewOrderDetail").val(view_id);
$("#backorderModel").modal();
};

function backForm(){
 if(!$("#reason1").val() && !$("#reason").val()){
	bootbox.alert('请填写退款原因！');
	//myAlert("请填写退款原因！","error");
 	return false;
 }
	$("#backorderForm").attr("action","${ctx}/orders/applyRefund");
	$("#backorderForm").submit();
};
	
</script>
</body>
</html>
