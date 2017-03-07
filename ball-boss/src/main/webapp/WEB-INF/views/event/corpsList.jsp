<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>赛程管理</title>
<script>
this.ctx = "${ctx}";
</script>
<script src="${ctx }/static/js/jquery.min.js"></script>
<script src="${ctx }/static/js/jquery.bootstrap.min.js"></script>
<script src="${ctx }/static/bootstrap/js/bootstrap.min.js"></script>

<script src="${ctx }/static/bootstrap/typeahead/typeahead.bundle.min.js"></script>

<script src="${ctx }/static/messenger/js/messenger.min.js"></script>
<script src="${ctx }/static/messenger/js/messenger-theme-future.js"></script>

<script src="${ctx}/static/js/custom.js" type="text/javascript"></script><!-- 自定义：包含全选/取消全选脚本 -->
<script src="${ctx}/static/js/carousel.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script><!-- 日期控件 -->
<script src="${ctx}/static/js/bootstrap-validation/validate.js?ver=1.1" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/echarts.js"></script>
<script src="${ctx }/static/Chart.js/Chart.min.js"  type="text/javascript"></script>
<script src="${ctx}/static/js/iscroll-probe.js"></script>
<script src="${ctx}/static/js/jQuery.dragmove.js"></script>
<script type='text/javascript' src='${ctx}/static/js/jquery.autocomplete.min.js'></script>
<script type='text/javascript' src='${ctx}/static/js/jquery.bootstrap-growl.js'></script>
<script src="${ctx}/static/js/ejs_production.js"></script>
<%--
<script src="${ctx}/static/js/jquery-1.8.3.js" type="text/javascript"></script>
--%>

<script src="${ctx}/static/ueditor/third-party/highcharts/highcharts.js" type="text/javascript"></script>
<script src="${ctx}/static/ueditor/third-party/highcharts/modules/exporting.js" type="text/javascript"></script>
<%--
<link href="${ctx}/static/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
--%>

<link href="${ctx }/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="${ctx }/static/css/plugins/bootstrap.css" rel="stylesheet" />
<link href="${ctx }/static/bootstrap/typeahead/typeahead.css" rel="stylesheet" />
<link href="${ctx }/static/css/jquery.autocomplete.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="${ctx}/static/css/common.css" rel="stylesheet" /><!-- 自定义样式 -->
<link href="${ctx }/static/css/style.css" rel="stylesheet" />
<link href="${ctx }/static/css/space.css" rel="stylesheet" />

<link rel="stylesheet" href="${ctx}/static/uploadify/uploadify.css">
<script src="${ctx}/static/uploadify/jquery.uploadify.min.js"></script>

<%-- 弹出窗口组件 --%>
<link rel="stylesheet" href="${ctx }/static/messenger/css/messenger.css">
<link rel="stylesheet" href="${ctx }/static/messenger/css/messenger-theme-future.css">
<link rel="stylesheet" href="${ctx }/static/messenger/css/messenger-theme-air.css">
<link rel="stylesheet" href="${ctx }/static/messenger/css/messenger-theme-flat.css">
<link rel="stylesheet" href="${ctx }/static/messenger/css/messenger-theme-block.css">


<link rel="stylesheet" href="${ctx }/static/css/boss.css">
</head>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">参赛队伍</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="col-md-12">
	      <div class="btn-group-sm pull-right mtb10">
	      	   <a class="btn btn-default btn-block" href="" onclick="reback()"><span class="glyphicon"></span> 返回</a>
	      </div>
    </div>
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">选择</th>
				<th>队伍名称</th>
				<th>队长</th>
				<th>队员数</th>
				<th>报名时间</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${eventCorps}" var="corps" varStatus="stat">
				<tr>
					<td class="text-center"><input type="radio" name="corpsId" value="${corps.id}_${corps.name}_${corps.logo}"></td>
					<td class="text-center">${corps.name}</td>
					<td class="text-center">${lf:getSsouserNameById(corps.captain)} </td>
					<td class="text-center">${corps.currentNumber}</td>
					<td class="text-center"><fmt:formatDate value="${corps.ct}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		<div class="form-group">
		<hr>
		<div class="col-md-offset-3 col-md-2">
			<a class="btn btn-default btn-block" href="#" onclick="reback()"><span
				class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-primary btn-block"
				id="submit_btn">
				<span class="glyphicon glyphicon-ok"></span> 确定
			</button>
		</div>
	</div>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
  </div><!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
		$(function() {
			
			$("#submit_btn").click(function(){
				var returnValue = $('input:radio[name="corpsId"]:checked').val();
				 if(returnValue != null){
					 window.parent.checkCorps(returnValue);
				} 
				 window.parent.colseMyDlg();
			});
		});
		
		function reback(){
			window.parent.colseMyDlg();
		}
	</script>
</body>
</html>