<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>课时列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 课时列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/statiumClassInfo/list" method="post">
						<input type="hidden" id="classId" name="classId"
							value="${param.classId }" /> <input type="hidden"
							name="statiumId" value="${param.statiumId }" /> <input
							type="hidden" name="id" value="${param.id}" />

						<%-- <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  	<div class="form-group form-group-sm">
				          	<lable class="control-label col-md-1 sr-only"></lable>
				          	<div class=" col-md-5 ">
				             	<input type="text" name="search_GTE_classDate" placeholder="起始上课日期" value="${param.search_GTE_classDate }" id="search_GTE_classDate" class="form-control" onclick="WdatePicker({'dateFmt':'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'search_LTE_classDate\')}'});">
				          	</div>
				          	<div class=" col-md-5">
				            	<input type="text" name="search_LTE_classDate" placeholder="结束上课日期" id="search_LTE_classDate" value="${param.search_LTE_classDate }" class="form-control" onclick="WdatePicker({'dateFmt':'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'search_GTE_classDate\')}'});">
				        	</div>  
				        	 <div class="col-md-12 text-center">
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				        </div>
			       	  </div> --%>
						<%--   <div class="col-md-5">
							<input  type="text" class="form-control input-sm" id="search_LIKE_coachName"  name="search_LIKE_coachName"  value="${param.search_LIKE_coachName }" placeholder="按教练名称查询">
			       	  </div>	 --%>
						<!--   </div>
			 	
				<div class="form-group form-group-sm">
				  
				</div> -->
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12 text-right">
					<a class="btn btn-default btn-sm"
						href="${ctx }/coachClassInfo/backClassList?id=${param.id}"><span
						class="glyphicon glyphicon-arrow-left"></span> 返回教练列表</a>
					<%-- 	    	<a class="btn btn-default btn-sm" href="${ctx }/statiumClassInfo/backClassList?classId=${param.classId}" ><span class="glyphicon glyphicon-arrow-left"></span> 返回课程列表</a>
 --%>
					<%--  <a class="btn btn-primary btn-sm" href="${ctx }/coachClassInfo/createForm?action=create&id=${param.id}" ><span class="glyphicon glyphicon-plus"></span> 新增课时</a> --%>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>课程</th>
								<th>上课日期</th>
								<th>上课时间</th>
								<th>下课时间</th>
								<!-- 	<th>操作</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="classInfo"
								varStatus="stat">
								<c:set var="status_class" value="" />
								<tr class="${status_class }">
									<td>${stat.count}</td>
									<td>${classInfo.classTitle}</td>
									<td><fmt:formatDate value="${classInfo.classDate}"
											pattern="YYYY-MM-dd" /></td>
									<td>${classInfo.classStartTime}</td>
									<td>${classInfo.classEndTime}</td>
									<%-- <td>
						<a class="btn btn-default btn-sm" href="${ctx }/coachClassInfo/detailForm?id=${classInfo.id}&classId=${param.classId }&statiumId=${param.statiumId }&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a>
						<a class="btn btn-default btn-sm" href="javascirpt:void(0);" onclick="deleteById('${classInfo.id}', '${classInfo.coachId }')"><i class="glyphicon glyphicon-edit"></i> 删除</a>
					</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="ids">
			</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#coach-man');

			$('#adminFooter').hide();

			$("button[type=reset]").click(function() {
				$("#search_GTE_classDate").attr("value", "");
				$("#search_LTE_classDate").attr("value", "");
				$("#search_LIKE_coachName").attr("value", "");
			});

		});

		// 删除
		function deleteById(id, classId) {
			var msg = "您确定要删除该课时信息吗？";
			bootbox.confirm(msg, function(result) {
				if (result) {
					var $form = $('#actionForm');
					$form.attr('action', '${ctx }/coachClassInfo/delete?id='
							+ id + "&coachId=" + coachId);
					$form[0].submit();
				}
			});

		}
	</script>

</body>
</html>