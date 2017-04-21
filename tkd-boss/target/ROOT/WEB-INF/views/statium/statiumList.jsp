<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>道馆列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 道馆列表</li>
			</ul>
		</div> 	
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/statium" method="post">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="option"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_a3" name="search_LIKE_a3"
									value="${param.search_LIKE_a3 }" placeholder="按道馆地址查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_cnname" name="search_LIKE_cnname"
									value="${param.search_LIKE_cnname }" placeholder="按道馆负责人名称查询">
							</div>
						</div>

						<div class="form-group form-group-sm">
							<lable class="control-label col-md-1 sr-only"></lable>
							<div class=" col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_a2" name="search_LIKE_a2"
									value="${param.search_LIKE_a2}" placeholder="按场馆名称查询">
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
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12 text-right">
					 <a class="btn btn-primary btn-sm" href="${ctx }/statium/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加道馆</a> 
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
								<th>道馆名称</th>
								<th>地址</th>
								<th>联系人姓名</th>
								<th>联系电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${data.content}" var="statium" varStatus="stat">
								<c:set var="status_class" value="" />
								<tr class="${status_class }">
									<td class="text-center">${stat.count}</td>
									<td><a href="${ctx }/statium/detailForm?id=${statium.id}">
											${statium.a2}</a></td>
									<td>${statium.a3 }</td>
									<td>${statium.cnname}</td>
									<td>${statium.a5}</td>
									<td><a class="btn btn-default btn-sm"
										href="${ctx }/statium/detailForm?id=${statium.id}&action=edit"><i
											class="glyphicon glyphicon-edit"></i> 修改</a> <a
										class="btn btn-default btn-sm"
										href="${ctx }/statiumClass/list?dgid=${statium.id}"><i
											class="glyphicon glyphicon-cog"></i> 课程设置</a></a></td>
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
			menu.active('#statium-man');

			var v_search_LIKE_sportType = '${param.search_LIKE_sportType}';
			if (v_search_LIKE_sportType) {
				$(
						'#search_LIKE_sportType option[value='
								+ v_search_LIKE_sportType + ']').attr(
						'selected', 'selected');
			}

			/* 按状态查询 */
			/*  $(".searchStatus").click(function(){
			  var v = $(this).val();
			  $("#search_EQ_status").val(v);
			  $("#search_form").submit();
			 });
			 */
			/* 按状态查询 */
			/* $(".searchSb").click(function(){
			  var v = $(this).val();
			  $("#search_EQ_sb").val(v);
			  $("#search_form").submit();
			}); */

			/* 按置顶场馆查询 */
			/*  $(".searchTop").click(function(){
			  var v = $(this).val();
			  $("#search_EQ_top").val(v);
			  $("#search_form").submit();
			 }); */

			/* 按照组织查询  */
			/* $("#orgName").click(function() {
			$("#myDlgBody").load("${ctx}/common/search_org_dlg");
			$("#myDlg").modal();
			});
			$("#orgNameClean").click(function(){
			$("#orgName").val('');
			$("#search_EQ_orgCode").val('');
			}); */

			$('#adminFooter').hide();

			var typeValue = '${param.search_EQ_type }';

			if (typeValue) {
				$("select[name=search_EQ_type] option[value=" + typeValue + "]")
						.attr("selected", "selected");
			}

			//====================================================
			// 自动匹配 场馆名称 >>>>
			//====================================================
			$('#statiumName').autocomplete(
					'${ctx}/common/search_statium_by_name?flag=true',
					{
						dataType : 'json',
						minChars : 2, //最少输入字条
						max : 30,
						autoFill : false,
						mustMatch : false, //是否全匹配, 如数据中没有此数据,将无法输入
						matchContains : true,
						scrollHeight : 220,
						width : $('#statiumName').outerWidth(),
						multiple : false,
						formatItem : function(row, i, max) { //显示格式
							return "【" + row.name + "】【" + row.area + "】【"
									+ row.address + "】";
						},
						formatResult : function(row) { //返回结果
							return row.name;
						},
						handleValue : function(id) {
							$('#statiumId').val(id);
						},
						parse : function(data) {
							var parsed = [];
							var rows = data;
							for (var i = 0; i < rows.length; i++) {
								var row = rows[i];
								if (row) {
									parsed[parsed.length] = {
										data : row,
										value : row["id"],
										result : this.formatResult(row)
									};
								}
							}
							return parsed;
						}
					});
			//====================================================
			// 自动匹配 场馆名称 <<<<
			//====================================================

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

		function deleteById(id) {
			var $form = $('#actionForm');
			bootbox.confirm('您确定要删除该场馆吗？', function(result) {
				if (result) {
					Util.getData(ctx + '/statium/delete', function(data) {
						if (data.result) {
							myAlert("场馆删除成功");
							window.location.reload()
						} else {
							myAlert("场馆删除失败", "error");
						}
					}, null, {
						"id" : id
					}, 'post');
				}
			});
			return false;
		}

		//更新免费状态
		/* function updateRating(id,rating){
		  var $form = $('#actionForm');
		  bootbox.confirm('您确定要将场馆改成免费的吗？', function(result) {
		    if(result) {
		      Util.getData(ctx + '/statium/updateRating', function(data){
		      	 if(data.result){
			      	 myAlert("修改成功");
			      	 window.location.reload()
			     }else{
			    	 myAlert("场馆修改失败","error");
				 }
		      }, null, {"id":id,"isRating":rating}, 'post');
		    }
		  });
		  return false;
		} */

		// 置顶
		/* function statiumTop(id,type){
		  if(!id){
			  myAlert("场馆id不能为空","error");
			  return false;
		  }
		  Util.getData(ctx + '/statium/top', function(data){
		    	 if(data.result){
		      	 myAlert("置顶成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert(data.reason,"error");
		    	 return false;
			 }
		    }, null, {"id":id,"type":type}, 'get');
		} 
		
		function updateStatium(){
		  Util.getData(ctx + '/statium/updateStatium', function(data){
		    	 if(data.result){
		      	 myAlert("修改成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert(data.reason,"error");
		    	 return false;
			 }
		    }, null, {"statiumId":""}, 'get');
		}  */
	</script>

</body>
</html>