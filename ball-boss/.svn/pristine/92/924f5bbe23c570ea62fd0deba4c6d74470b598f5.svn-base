<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 奖品列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>时间</th>
								<th>奖品</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="statium" varStatus="stat">
								<tr>
									<td align="center">${statium.time }</td>
									<td align="center">${statium.prize }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
		</div>
		<!-- /右侧主体内容 -->
	</div>

	<script>
	$(function() {
		menu.active('#prize-list-man');
	});
	</script>