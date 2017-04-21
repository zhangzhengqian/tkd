<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
                <c:when test="${order.ordersType == '0'}">课程订单</c:when>
                <c:when test="${order.ordersType == '1'}">活动订单</c:when>
                <c:when test="${order.ordersType == '2'}">会员卡充值订单</c:when>
  			</c:choose>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单金额: </div>
    	<div class="col-md-6">${order.finalFee}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单创建时间: </div>
    	<div class="col-md-6"><fmt:formatDate value="${order.ct }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人: </div>
    	<div class="col-md-6">${order.userName}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人联系电话: </div>
    	<div class="col-md-6">${order.phone }</div>
  </div>
  <div class="row">
    	<div class="col-md-2 text-right">支付方式: </div>
    	<div class="col-md-6">
    	<c:if test="${order.payType==1 }">支付宝</c:if>
    	<c:if test="${order.payType==2 }">微信</c:if>
    	<c:if test="${order.payType==3 }">会员卡支付</c:if>
    	</div>
  </div>
  <div class="row">
    	<div class="col-md-2 text-right">退款原因: </div>
    	<div class="col-md-6">${order.reason }</div>
  </div>
  </div>
  </div>
  <table class="table table-bordered table-condensed table-hover">
  <c:if test="${order.ordersType==0 }">
  	<thread>
  	<tr>
  	<th style="border-bottom-width: 0px;">编号</th>
  	<th style="border-bottom-width: 0px;">球馆名称</th>
  	<th style="border-bottom-width: 0px;">预约时间</th>
  	<th style="border-bottom-width: 0px;">球馆联系电话</th>
  	<th style="border-bottom-width: 0px;">地址</th>
  	<th style="border-bottom-width: 0px;">教练姓名</th>
  	<th style="border-bottom-width: 0px;">教练电话</th>
  	</tr>
  	</thread>
  	<tbody>
  			<tr>
  				<td class="text-center">1</td>
  				<td>${statiumInfo.dgName}</td>
  				<td>${statiumInfo.signDate}</td>
  				<td>${statiumInfo.tel}</td>
  				<td>${statiumInfo.address}</td>
  				<td>${statiumInfo.coachName}</td>
  				<td>${statiumInfo.coachPhone}</td>
  			</tr>
  	</tbody>
  </c:if>
  <c:if test = "${order.ordersType == 1 }">
  	<thread>
  	<tr>
  	<th style="border-bottom-width: 0px;">编号</th>
  	<th style="border-bottom-width: 0px;">活动名称</th>
  	<th style="border-bottom-width: 0px;">活动地点</th>
  	<th style="border-bottom-width: 0px;">活动开始时间</th>
  	</tr>
  	</thread>
  	<tbody>
  			<tr>
  				<td>1</td>
  				<td>${activity.activityTopic }</td>
  				<td>${activity.dgName }</td>
  				<td>${activity.startTime }</td>
  			</tr>
  	</tbody>
  </c:if>
  <c:if test="${order.ordersType == 2 }">
  <thread>
  	<tr>
  	<th style="border-bottom-width: 0px;">编号</th>
  	<th style="border-bottom-width: 0px;">卡片名称</th>
  	<th style="border-bottom-width: 0px;">卡片金额</th>
  	<th style="border-bottom-width: 0px;">赠送金额</th>
  	<th style="border-bottom-width: 0px;">所属道馆</th>
  	<th style="border-bottom-width: 0px;">购买日期</th>
  	<th style="border-bottom-width: 0px;">卡片类型</th>
  	</tr>
  	</thread>
  	<tbody>
  			<tr>
  				<td>1</td>
  				<td>${rechargeVo.cardName }</td>
  				<td>${rechargeVo.finalFee }</td>
  				<td>${rechargeVo.giftFee }</td>
  				<td>${rechargeVo.statiumName }</td>
  				<td>${rechargeVo.cardBuyDate }</td>
  				<c:if test = "${rechargeVo.cardType==1 }"><td>储值卡</td></c:if>
  				<c:if test = "${rechargeVo.cardType==2 }"><td>期限卡</td></c:if>
  			</tr>
  	</tbody>
  </c:if>
  </table>
  <div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">流水日志</h3>
  </div>
  <table class="table table-bordered table-condensed table-hover">
  	<thread>
  		<tr>
  			<th style="border-bottom-width: 0px;">编号</th>
  			<th style="border-bottom-width: 0px;">订单业务行为</th>
  			<th style="border-bottom-width: 0px;">操作者</th>
  			<th style="border-bottom-width: 0px;">时间</th>
  			<th style="border-bottom-width: 0px;">描述</th>
  		</tr>
  	</thread>
  	<tbody>
  		<c:forEach var="orderLog" items="${orderLogList }" varStatus="stat">
  		<tr>
  			<td>${stat.count }</td>
  			<c:if test="${orderLog.action=='ORDER_ACTION_CREATE' }"><td>下单</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_PAY' }"><td>支付成功</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_REFUNDING' }"><td>申请退款</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_REFUNDED' }"><td>退款成功</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_CANCELED' }"><td>订单取消</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_FNISHED' }"><td>已完成</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_TIMEOUT' }"><td>订单超时，已取消</td></c:if>
  			<c:if test="${orderLog.action=='ORDER_ACTION_CANCEL' }"><td>取消订单</td></c:if>
			<c:if test="${orderLog.action=='ORDER_ACTION_SIGN' }"><td>app签到</td></c:if>
  			<td>${orderLog.userId }</td>
  			<td><fmt:formatDate value="${orderLog.ct }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  			<td>${orderLog.comment}</td>
  		</tr>
  		</c:forEach>
  	</tbody>
  </table>
  <div class="panel-footer">
  	<div class="row">
  		<div class="col-sm-12 text-right">
  			<a class="btn btn-sm btn-default" href="${ctx }/order/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
  			 <shiro:hasPermission name="order:confirmRefund">
  			 <c:if test="${order.status == 'ORDER_REFUNDING'}">
             <a onclick="javascript:backForm(${order.id })" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 
             <c:if test="${order.payType==1 }">退款至支付宝</c:if>
    		<c:if test="${order.payType==2 }">退款至微信</c:if>
    		<c:if test="${order.payType==3 }">退款至会员卡账户</c:if>
             </a>
		     </c:if>
		     </shiro:hasPermission>
  		</div>
  	</div>
  </div>
  </div>
			<form id="backorderForm" type="hidden" method="post">
				</form>
<script type="text/javascript">
$(function(){
	 menu.active('#order-man');
	
});
function backForm(id){
		bootbox.confirm('是否确认退款'+id+'订单?！',function(result){
				if(result){
					$("#backorderForm").attr("action","${ctx}/order/confirmRefund/"+id);
					$("#backorderForm").submit();
				}
		});
}

</script>
</body>
</html>