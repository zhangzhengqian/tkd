<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业服务</title>
<style type="text/css">
	.row{
		margin-top:20px;
	}
</style>
</head>
<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>企业服务</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/company/service/save" method="post"
			class="form-horizontal">
			<input id="id" name="id" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>服务信息</small>
					</legend>
					<div class="row">
						<div class="col-md-3 text-right">
							企业名称:
						</div>
						<div class="col-md-6">
							${data.companyName}
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 text-right">
							服务类型:
						</div>
						<div class="col-md-6">
						<c:choose>
			                <c:when test="${data.serviceType==0}">固定包场</c:when>
			                <c:when test="${data.serviceType==1}">活动赛事</c:when>
			                <c:when test="${data.serviceType==2}">账户充值</c:when>
			                <c:when test="${data.serviceType==3}">账户提现</c:when>
			        	</c:choose>
						</div>
					</div>
					<c:if test="${data.serviceType==1 }">
					<div  class="row activityModel">
						<div class="col-md-3 text-right">
							时间:
							</div>
							<div class="col-md-2">
								${data.begin}
							</div>
							<div class="col-md-2">
								${data.end}
							</div>
					</div>
					<div   class="row activityModel">
						<div class="col-md-3 text-right">
							场馆名称:
						</div>
						<div class="col-md-6">
							${data.statiumName}
						</div>
					</div>
					<div   class="row activityModel">
						<div class="col-md-3 text-right">
								运动类型:
							</div>
							<div class="col-md-6">
								<c:choose>
				                <c:when test="${data.sportType=='BADMINTON'}">羽毛球</c:when>
				                <c:when test="${data.sportType=='TENNIS'}">网球</c:when>
				                <c:when test="${data.sportType=='BASKETBALL'}">篮球</c:when>
				                <c:when test="${data.sportType=='TABLE_TENNIS'}">乒乓球</c:when>
				                <c:when test="${data.sportType=='BILLIARDS'}">台球</c:when>
				                <c:when test="${data.sportType=='GOLF'}">高尔夫</c:when>
				                <c:when test="${data.sportType=='BOWLING'}">保龄球</c:when>
				                <c:when test="${data.sportType=='FOOTBALL'}">足球</c:when>
				        	</c:choose>
							</div>
					</div>
					<div   class="row activityModel">
						<div class="col-md-3 text-right">
								支付方式:
							</div>
							<div class="col-md-6">
								<c:choose>
				                <c:when test="${data.payType==0}">线上</c:when>
				                <c:when test="${data.payType==1}">线下</c:when>
				        	</c:choose>
							</div>
					</div>
					<div   class="row activityModel">
						<div class="col-md-3 text-right">
							活动费用:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.price)}
						</div>
					</div>
					<div   class="row activityModel">
						<div class="col-md-3 text-right">
							活动成本:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.costPrice)}
						</div>
					</div>
					</c:if>
					<c:if test="${data.serviceType==0 }">
					<div   class="row bookSpaceModel">
						<div class="col-md-3 text-right">
							场馆名称:
						</div>
						<div class="col-md-6">
							${data.statiumName}
						</div>
					</div>
					<div   class="row bookSpaceModel">
							<div class="col-md-3 text-right">
								运动类型:
							</div>
							<div class="col-md-6">
								<c:choose>
				                <c:when test="${data.sportType=='BADMINTON'}">羽毛球</c:when>
				                <c:when test="${data.sportType=='TENNIS'}">网球</c:when>
				                <c:when test="${data.sportType=='BASKETBALL'}">篮球</c:when>
				                <c:when test="${data.sportType=='TABLE_TENNIS'}">乒乓球</c:when>
				                <c:when test="${data.sportType=='BILLIARDS'}">台球</c:when>
				                <c:when test="${data.sportType=='GOLF'}">高尔夫</c:when>
				                <c:when test="${data.sportType=='BOWLING'}">保龄球</c:when>
				                <c:when test="${data.sportType=='FOOTBALL'}">足球</c:when>
				        	</c:choose>
							</div>
					</div>
					<div   class="row bookSpaceModel">
						<div class="col-md-3 text-right">
								支付方式:
							</div>
							<div class="col-md-6">
								<c:choose>
				                <c:when test="${data.payType==0}">线上</c:when>
				                <c:when test="${data.payType==1}">线下</c:when>
				        	</c:choose>
							</div>
					</div>
					<div   class="row bookSpaceModel">
						<div class="col-md-3 text-right">
							场地时租费:
						</div>
						<div class="col-md-6">
							${data.unitPrice}元
						</div>
					</div>
					<div  class="row bookSpaceModel">
							<div class="col-md-3 text-right">
							周期:
							</div>
							<div class="col-md-1">
								${data.begin}
							</div>
							<div class="col-md-1">
								${data.end}
							</div>
							<div class="col-md-1">
								${data.amount}个场地
							</div>
					</div>
					<c:forEach items="${data.activityDate}" var="actDate">
						<div  class="row bookSpaceModel">
								<div class="col-md-3 text-right">
								</div>
									<div class="col-md-1">
										 周
										 <c:choose>
											<c:when test="${actDate.key==1}">一</c:when>
											<c:when test="${actDate.key==2}">二</c:when>
											<c:when test="${actDate.key==3}">三</c:when>
											<c:when test="${actDate.key==4}">四</c:when>
											<c:when test="${actDate.key==5}">五</c:when>
											<c:when test="${actDate.key==6}">六</c:when>
											<c:when test="${actDate.key==7}">日</c:when>
						                </c:choose>
									</div>
									<div class="col-md-1">
										${actDate.value[0]}:00
									</div>
									<div class="col-md-1">
										${actDate.value[1]}:00
									</div>
						</div>
					</c:forEach>
					<div   class="row bookSpaceModel">
						<div class="col-md-3 text-right">
								包场费用:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.price)}元
						</div>
					</div>
					<div   class="row bookSpaceModel">
						<div class="col-md-3 text-right">
								包场成本:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.costPrice)}元
						</div>
					</div>
					</c:if>
					<c:if test="${data.serviceType==3 }">
					<div   class="row withDrawModel">
						<div class="col-md-3 text-right">
							提现金额:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.fee) }元
						</div>
					</div>
					</c:if>
					<c:if test="${data.serviceType==2 }">
					<div  class="row chargeModel">
						<div class="col-md-3 text-right">
							充值金额:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.fee) }元
						</div>
					</div>
					</c:if>
					<c:if test="${fn:length(data.addProjects)!=0}">
						<hr>
					<div class="row">
						<div class="col-md-3 text-right">
							附加项目
						</div>
						<div class="col-md-5">
						<table id="contentTable" class="table table-bordered table-hover">
						<thead class="thead">
							<tr>
								<th>项目名称</th>
								<th>项目费用</th>
								<th>项目成本</th>
								<th>项目备注</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.addProjects}" var="addProject">
								<tr>
									<td>${addProject.title}</td>
									<td>${lf:formatMoney(addProject.price) }元</td>
									<td>${lf:formatMoney(addProject.costPrice) }元</td>
									<td>${addProject.content}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
						</div>
					</div>
					</c:if>
					<c:if test="${data.serviceType==0||data.serviceType==1 }">
				<hr>
				<div class="row">
					<div class="col-md-3 text-right">
								总费用:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.fee)}元
						</div>
				</div>
				<div class="row">
					<div class="col-md-3 text-right">
								总费用:
						</div>
						<div class="col-md-6">
							${lf:formatMoney(data.costFee)}元
						</div>
				</div>
				</c:if>
			<hr>
			<c:if test="${fn:length(flows)!=0}">
			<div class="row">
				<div class="col-md-3 text-right">
					审核日志：
				</div>
				<div class="col-md-6">
					<table id="contentTable" class="table table-bordered table-hover">
						<thead class="thead">
							<tr>
								<th>处理人</th>
								<th>处理时间</th>
								<th>具体操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${flows}" var="flow">
								<tr>
									<td>${flow.handler}</td>
									<td><fmt:formatDate value="${flow.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${flow.operation}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
				</div>
			</div>
			<hr>
			</c:if>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="javascript:history.go(-1)"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
			</div>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#company-services');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>
