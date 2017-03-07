<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>卡劵管理</title>
</head>
<body>


	<div class="panel panel-default">
		<div class="panel-heading">
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home active"></span> 运营管理</li>
				<li class="active">卡券管理</li>
				<li class="active">领取信息</li>
			</ul>
		</div>

		<div class="panel-body">
			<div class="row"><!-- 查询条件 -->
				<div class="col-md-12">
					<form class="form-horizontal" action="${ctx }/coupon/couponReceivingsByCouponId/${couponid}/${couponInfoid}" method="post">
						<div class="form-group form-group-sm">
		          			<lable class="control-label col-md-1 sr-only"></lable>
		          			<div class="col-md-5">
		            			<input type="text" class="form-control Wdate " id="search_GTE_receiveTime" name="search_GTE_receiveTime" value="${param.search_GTE_receiveTime }" placeholder="开始时间" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_receiveTime\')||\'%y-%M-%d\'}'})">
		          			</div>
		          			<div class="col-md-5">
		            			<input type="text" class="form-control Wdate " id="search_LTE_receiveTime" name="search_LTE_receiveTime" value="${param.search_LTE_receiveTime }" placeholder="结束时间" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_receiveTime\')}'})">
		          			</div>         
	        			</div>
	        			
	        			<div class="form-group form-group-sm query-more">
				         	<label class="control-label col-md-1 sr-only" for="custName"></label>
				         	<div class="col-md-5">
								<select class="form-control" name="search_EQ_state" id="search_EQ_state" >
									<option id="option1" value="" >--优惠券状态--</option>
									<option id="status_0" value="0" <c:if test="${param.status==0}">selected='selected'</c:if> >已领取</option>
									<option id="status_1" value="1" <c:if test="${param.status==1}">selected='selected'</c:if> >已使用</option>
									<option id="status_2" value="2" <c:if test="${param.status==2}">selected='selected'</c:if> >已过期</option>
								</select>
					  		</div>
		         		</div>
	        			
	        			<div class="form-group form-group-sm">
			  				<div class="col-md-12 text-center">
			    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
			   	 				&nbsp;&nbsp;
			    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
			    				&nbsp;&nbsp;
                				<a href="${ctx}/coupon/couponInfosByCouponId/${couponid}"  class="btn btn-default btn-sm">返回</a>
			    				&nbsp;&nbsp;
			    				<button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
			  				</div>
						</div>
					</form>
				</div>
			</div>
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
	                <th class="text-center">用户名称</th>
	                <th class="text-center">手机号</th>
	                <th class="text-center">优惠券名称</th>
	                <th class="text-center">领取时间</th>
	                <th class="text-center">消费场馆</th>
	                <th class="text-center">优惠券状态</th>
				</tr>
			</thead>
			<tbody>
			       <c:forEach items="${data.content}" var="item" varStatus="stat">
			               <tr>
			               		<td class="text-center">${stat.count }</td>
			                    <td>${item.username}</td>
			                    <td>${item.userphone}</td>
			                    <td>${item.couponname}</td>
			                    <td>${item.gettime}</td>
			                    <td>${item.statiumname}</td>
			                    <td>
			                        <c:if test="${item.statue==0}">已领取</c:if>
			                        <c:if test="${item.statue==1}">已使用</c:if>
			                        <c:if test="${item.statue==2}">已过期</c:if>
			                    </td>
			               </tr>
			       </c:forEach>
			</tbody>
			</table>
			<tags:pagination page="${data}" />
            <tags:errors />
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#coupon-man');
			$("#adminFooter").hide();
		});
	</script>

</body>
</html>