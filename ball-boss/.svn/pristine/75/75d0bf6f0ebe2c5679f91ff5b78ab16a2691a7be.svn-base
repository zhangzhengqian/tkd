<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>站点列表</title>
</head>
<body>
<c:set var="colseCity" value="false" />
<shiro:hasRole name="admin">
	<c:set var="colseCity" value="true" />
</shiro:hasRole>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 站点列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/enjoyRace/siteList" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_city" name="search_LIKE_city"
									value="${param.search_LIKE_city}" placeholder="按城市查询">
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
							</div>
						</div>
						<input type="hidden" name="search_EQ_sportType" id="search_EQ_sportType" value="2" >
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				 <div class="col-md-12 text-right">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="enjoyrace:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/enjoyRace/createSite?action=create"><span
								class="glyphicon glyphicon-plus"></span> 添加站点</a>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>城市  ↓</th>
								<th>场馆</th>
								<th>授权次数</th>
								<th>赛型类别</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="site" varStatus="stat">
							   <tr>
							  		 <td>${stat.count}</td>
							   		<td>${site.province }</td>
							    	<td>${site.statiumName }</td>
							    	<td>${site.num}</td>
										<td style="text-align: center;">
												<c:if test="${'1' == site.typeLevel}">乐享赛一级</c:if> 
												<c:if test="${'2' == site.typeLevel}">乐享赛二级</c:if> 
												<c:if test="${'3' == site.typeLevel}">乐享赛三级</c:if> 
												<c:if test="${'4' == site.typeLevel}">乐享赛四级</c:if> 
												<c:if test="${'5' == site.typeLevel}">白银赛</c:if> 
												<c:if test="${'6' == site.typeLevel}">黄金赛</c:if> 
												<c:if test="${'7' == site.typeLevel}">白金赛</c:if> 
												<c:if test="${'8' == site.typeLevel}">钻石赛</c:if> 
												<c:if test="${'9' == site.typeLevel}">皇冠赛</c:if>
											    <c:if test="${'10' == site.typeLevel}">年终总决赛</c:if>
										</td>
										<td>${site.status}</td>
								<td>
									<a href="${ctx }/enjoyRace/siteView/${site.id}?action=view" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i>查看</a>
									<shiro:hasPermission name="enjoyrace:update">
											<a href="${ctx }/enjoyRace/siteView/${site.id}?action=edit" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i>修改</a>
									 <c:if test="${site.status == 0 }"> 
										<a href="${ctx }/enjoyRace/colse?id=${site.id}&status=one&state=1" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-cog"></i> 关闭该场馆</a>
									 </c:if>
									<c:if test="${site.status == 1 }"> 
										<a href="${ctx }/enjoyRace/colse?id=${site.id}&status=one&state=0" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-cog"></i> 开启该场馆</a>
									 </c:if>
									 <c:if test="${site.cityStatus == 1 }">
										<a href="${ctx }/enjoyRace/colse?id=${site.id}&status=all&state=0" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-cog"></i>开启该城市</a>
									</c:if>
								</shiro:hasPermission>
									<c:if test="${colseCity}">
										<c:if test="${site.cityStatus == 0 }">
											<a href="${ctx }/enjoyRace/colse?id=${site.id}&status=all&state=1" class="btn btn-default btn-sm"><i
													class="glyphicon glyphicon glyphicon-cog"></i>关闭该城市</a>
										</c:if>
									</c:if>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></form>
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
			menu.active('#enjoyraceSite-man');
			$('#adminFooter').hide();
			
			  /* 按状态查询 */
			  $(".searchStatus").click(function(){
				  var v = $(this).val();
				  $("#search_EQ_state").val(v);
				  $("#search_form").submit();
			  });
			
			var isOffical = '${param.search_LIKE_isOffical }';

			if (isOffical) {
				$("select[name=search_LIKE_isOffical] option[value=" + isOffical + "]").attr("selected", "selected");
			}

			$("button[type=reset]").click(
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

		});

		function getSelected() {
			var ids = [];
			var checked = $('input:checked');
			if (checked.length) {
				checked.each(function() {
					if ($(this).attr('name') != 'chk_all') {
						ids.push($(this).val());
					}
				});
			}
			return ids;
		}
		
		function deleteById(id){
			  var $form = $('#actionForm');
			  bootbox.confirm('您确定要删除该赛事吗？', function(result) {
			    if(result) {
			      Util.getData(ctx + '/enjoyRace/delGames', function(data){
			      	 if(data.result){
				      	 myAlert("删除赛事成功");
				      	 window.location.reload()
				     }else{
				    	 myAlert("赛事删除失败","error");
					 }
			      }, null, {"id":id}, 'post');
			    }
			  });
			  return false;
			}
	</script>
</body>
</html>