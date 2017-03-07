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
				<li class="active">礼包信息</li>
			</ul>
		</div>

		<div class="panel-body">
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="actionForm" class="form-horizontal"
						action="${ctx }/coupon/couponInfosByCouponId/${couponid}"
						method="post">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_couponType"
									name="couponType">
									<option value="">优惠券类型</option>
									<option value="0">--通用--</option>
									<option value="1">--羽毛球--</option>
									<option value="2">--网球--</option>
									<option value="3">--篮球--</option>
									<option value="4">--乒乓球--</option>
									<option value="5">--高尔夫--</option>
									<option value="6">--足球--</option>
									<option value="7">--台球--</option>
									<option value="8">--保龄球--</option>
									<option value="9">--预约教练--</option>
									<option value="10">--活动报名--</option>
									<option value="11">--赛事报名--</option>
								</select>
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_state" name="status">
									<option value="">优惠券状态</option>
									<option value="0">--未开始--</option>
									<option value="1">--启用--</option>
									<option value="2">--停用--</option>
									<option value="3">--结束--</option>
								</select>
							</div>
						</div>

						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="reset" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								&nbsp;&nbsp; <a href="${ctx}/coupon/list"
									class="btn btn-default btn-sm">返回</a>
							</div>

						</div>
					</form>
				</div>
			</div>
			<table id="contentTable"
				class="table table-bordered table-condensed table-hover">
				<thead class="thead">
					<tr>
						<th class="text-center">编号</th>
						<th class="text-center">优惠券id</th>
						<th class="text-center">优惠券类型</th>
						<th class="text-center">优惠券面值</th>
						<th class="text-center">分享赠送面值</th>
						<th class="text-center">下单成功分享</th>
						<th class="text-center">优惠券数量</th>
						<th class="text-center">优惠券状态</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data.content}" var="item" varStatus="stat">
						<tr>
							<td class="text-center">${stat.count }</td>
							<td><a
								href="${ctx}/coupon/couponReceivingsByCouponId/${couponid}/${item.id}">${item.id}</a></td>
							<td><c:if test="${item.couponType==0}">通用</c:if> <c:if
									test="${item.couponType==1}">羽毛球</c:if> <c:if
									test="${item.couponType==2}">网球</c:if> <c:if
									test="${item.couponType==3}">篮球</c:if> <c:if
									test="${item.couponType==4}">乒乓球</c:if> <c:if
									test="${item.couponType==5}">高尔夫</c:if> <c:if
									test="${item.couponType==6}">足球</c:if> <c:if
									test="${item.couponType==7}">台球</c:if> <c:if
									test="${item.couponType==8}">保龄球</c:if> <c:if
									test="${item.couponType==9}">预约教练</c:if> <c:if
									test="${item.couponType==10}">活动报名</c:if> <c:if
									test="${item.couponType==11}">赛事报名</c:if></td>
							<td>${item.couponValue}</td>
							<td>${item.giveValue}</td>
							<td>${item.orderValue}</td>
							<td>${item.couponCount}</td>
							<td><c:if test="${item.status==0}">未开始</c:if> <c:if
									test="${item.status==1}">启用</c:if> <c:if
									test="${item.status==2}">停用</c:if> <c:if
									test="${item.status==3}">结束</c:if></td>
							<td><a
								href="${ctx}/coupon/couponReceivingsByCouponId/${couponid}/${item.id}"></span>查看</a>
								<shiro:hasPermission name="coupon:updateCouponInfoStatus">
									<c:if test="${item.status == 0 || item.status == 2 }">
										<a href="#" onclick="changeStatus('${item.id}','1');"></span>启动</a>
									</c:if>
									<c:if test="${item.status == 1 }">
										<a href="#" onclick="changeStatus('${item.id}','2');"></span>停止</a>
									</c:if>
								</shiro:hasPermission> <shiro:hasPermission name="coupon:deleteCoupon">
									<c:if test="${item.status == 3 }">
										<a href="#" onclick="deleteCoupon('${item.id}');"></span>删除</a>
									</c:if>
								</shiro:hasPermission></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tags:pagination page="${data}" />
			<tags:errors />
			<form id="actionForm1" method="post"></form>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#coupon-man');
			$("#adminFooter").hide();
			//重置查询条件
			$("button[type=reset]")
					.click(
							function() {
								$(this).closest("form").find("input").attr(
										"value", "");
								$(this).closest("form").find(
										"select option:selected").attr(
										"selected", false);
								$(this).closest("form").find(
										"select option:first").attr("selected",
										true);
							});
			
			var vcouponType = '${param.couponType}';
			var vstatus = '${param.status}';
		$('#search_EQ_couponType option[value='+ vcouponType+']').attr('selected','selected');
		$('#search_EQ_state option[value='+vstatus+']').attr('selected','selected');

		});

		function changeStatus(couponid, statusValue) {
			var msg = "您确定要停止优惠券吗？"
			if (statusValue == 1) {
				msg = "您确定要启动优惠券吗？"
			}
			bootbox.confirm(msg, function(result) {
				if (result) {
					var $form = $('#actionForm1');
					$form.attr('action',
							'${ctx }/coupon/updateCouponInfoStatus?couponInfoId='
									+ couponid + '&status=' + statusValue);
					$form[0].submit();
				}
			});

		}

		function deleteCoupon(couponid) {
			var msg = "您确定要删除优惠券吗？";
			bootbox.confirm(msg, function(result) {
				if (result) {
					var $form = $('#actionForm1');
					$form.attr('action',
							'${ctx }/coupon/deleteCouponInfo?couponInfoId='
									+ couponid);
					$form[0].submit();
				}
			});

		}
	</script>

</body>
</html>