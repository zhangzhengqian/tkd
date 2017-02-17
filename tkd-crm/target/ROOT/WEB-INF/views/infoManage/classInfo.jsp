<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="infoNav.jsp"%>

<form  id="classInfoForm" class="form-horizontal" action="${ctx }/infoManage/classInfo" method="post" name="id">
	<div class="modulHead">
		<p>课程管理 》课时管理</p>
	</div>
	<div class="orderSearch myVipOrderSearch">
		<input type="hidden" id="classId" name="classId" value="${param.classId }" />
		<input type="hidden" name="id" value="${param.id }" />
		<ul>
			<li class="timeLi subSearchLi1">
				<span>教练名称</span>
				<input type="text" id="search_LIKE_coachName" name="search_LIKE_coachName" value="${param.search_LIKE_coachName }">
			</li>
			<li class="timeLi subSearchLi1">
				<span>查询时间</span>
				<input type="text" id="search_GTE_classDate" name="search_GTE_classDate" value="${param.search_GTE_classDate }"
					   onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'search_LTE_classDate\')}'})">
				<em>到</em>
				<input type="text" id="search_LTE_classDate" name="search_LTE_classDate" value="${param.search_LTE_classDate }"
					   onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'search_GTE_classDate\')}'})">
			</li>
			<li class="subSearch subSearchLi1">
				<a class="reset" type="reset" href="javascript:;">重置</a>
			</li>
			<li class="subSearch subSearchLi1">
				<a class="submit" href="javascript:searchClassInfo()" >查询</a>
			</li>
			<li class="subSearch subSearchLi1">
				<a class="submit" href="${ctx }/infoManage/statiumClass" ng-click="search()">返回</a>
			</li>
			<li class="subSearch subSearchLi">
			</li>
			<li class="subSearch subSearchLi">
				<a class="submit" href="${ctx }/infoManage/createInfoForm?classId=${param.classId }">添加课时</a>
			</li>

		</ul>
	</div>
</form>

<div class="orderResult">
	<table>
		<tr>
			<th>教练</th>
			<th>上课日期</th>
			<th>上课时间</th>
			<th>下课时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${data.content }" var="classInfo" varStatus="stat">
			<tr>
				<td>${classInfo.coachName }</td>
				<td><fmt:formatDate value="${classInfo.classDate }" pattern="YYYY-MM-dd" />
				</td>
				<td>${classInfo.classStartTime }</td>
				<td>${classInfo.classEndTime }</td>
				<td>
					<a class="btn btn-default btn-sm" href="${ctx }/infoManage/classInfoForm?id=${classInfo.id }"><i class="glyphicon glyphicon-edit"></i> 修改</a>
					<a class="btn btn-default btn-sm" href="javascirpt:void(0);" onclick="deleteClassInfo('${classInfo.id}')"><i class="glyphicon glyphicon-edit"></i> 删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 分页 -->
<tags:pagination page="${data }" />
<tags:errors />

<script src="${ctx }/static/lib/reset.js"></script>
<script type="text/javascript">
	$(function() {
		// 样式
		$('#info-man').addClass("active");
		$('#STATIUM_CLASS').addClass("active");
	});

	// 删除课程
	function deleteClassInfo(v) {
		swal({
			title: "",
			text: "您确定删除课时？",
			type: "warning",
			showCancelButton: "true",
			showConfirmButton: "true",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			animation: "slide-from-top"
		}, function () {
			$('#loading').show();
			$.ajax({
				url : "${ctx }/infoManage/deleteClassInfo",
				method : "POST",
				data : {"classInfoId" : v},
				dataType: 'json',
				success: function(data){
					$('#loading').hide();
					if(data.result=='success'){
						swal({
							title: "提示",
							text: "课时删除成功",
							showConfirmButton: "true",
							confirmButtonText: "确定",
							animation: "slide-from-top"
						}, function () {
							location.href = "${ctx }/infoManage/classInfo?classId=${param.classId }";
						})
					} else {
						swal({
							title: "警告",
							text: data.data
						})
					}
				}
			});
		})
	}
	
	// 查询课时
	function searchClassInfo() {
		$('#classInfoForm').submit();
	}


</script>

</body>
</html>