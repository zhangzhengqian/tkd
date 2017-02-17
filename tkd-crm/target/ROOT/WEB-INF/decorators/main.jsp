<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 设定禁止浏览器从本地缓存中调阅页面内容 -->
<meta http-equiv="pragma" content="no-cache">
<!-- 清除缓存 -->
<meta http-equiv="cache-control" content="no-cache">
<!-- 网页到期时间 -->
<meta http-equiv="expires" content="0">
<title>道馆通CRM</title>

<script>
    this.ctx = "${ctx }";
</script>

<!-- Mainly scripts -->
<script src="${ctx }/static/lib/jquery-2.1.1.js"></script>
<script src="${ctx }/static/lib/bootstrap.min.js"></script>
<script src="${ctx }/static/lib/html2canvas.min.js"></script>
<script src="${ctx }/static/lib/base64.min.js"></script>
<script src="${ctx }/static/lib/canvas2image.js"></script>
<script src="${ctx }/static/lib/iscroll-probe.js"></script>
<script src="${ctx }/static/lib/ejs_production.js"></script>
<script src="${ctx }/static/lib/gameTree.js"></script>
<script src="${ctx }/static/lib/gameTreeForCanvas.js?ver=1.2"></script>
<script src="${ctx }/static/lib/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx }/static/lib/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx }/static/lib/plugins/uploadify/jquery.uploadify.min.js"></script>
<script src="${ctx }/static/lib/plugins/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/static/lib/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${ctx }/static/lib/plugins/toastr/toastr.min.js"></script>
<script src="${ctx }/static/lib/plugins/ueditor/ueditor.config.js"></script>
<script src="${ctx }/static/lib/plugins/ueditor/ueditor.all.min.js"></script>
<script src="${ctx }/static/lib/plugins/bootstrap-validation/validate.js"></script>
<script src='${ctx }/static/lib/jquery.autocomplete.min.js'></script>
<script src="${ctx }/static/lib/numValidation.js"></script>
<!--<script src="static/lib/angular.min.js"></script>-->
<!--<script src="static/lib/angular-route.min.js"></script>-->
<!--<script src="static/lib/angular-cookies.min.js"></script>-->
<script src="${ctx }/static/lib/jquery-jtemplates.js"></script>
<script src="${ctx }/static/lib/jquery.dragsort-0.5.2.min.js"></script>
<script src="${ctx}/static/uploadify/jquery.uploadify.min.js"></script>
<%--
<script src="${ctx}/static/js/jquery-1.8.3.js" type="text/javascript"></script>
--%>

<%--<script src="${ctx}/static/ueditor/third-party/highcharts/highcharts.js" type="text/javascript"></script>
<script src="${ctx}/static/ueditor/third-party/highcharts/modules/exporting.js" type="text/javascript"></script>--%>
<%--
<link href="${ctx}/static/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
--%>

<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/css/reset.css" rel="stylesheet">
<link href="${ctx}/static/css/common.css?ver=2598" rel="stylesheet">
<link href="${ctx}/static/css/animate.css" rel="stylesheet">
<link href="${ctx}/static/css/tree.css" rel="stylesheet">
<link href="${ctx}/static/lib/plugins/uploadify/uploadify.css" rel="stylesheet">
<link href="${ctx}/static/css/baiduMap.css" rel="stylesheet">
<link href="${ctx}/static/css/price.css" rel="stylesheet">
<!-- <link href="static/css/statiumUse.css" rel="stylesheet"> -->
<link href="${ctx}/static/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css"
	  rel="stylesheet">
<link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<%-- <link href="${ctx}/static/uploadify/jquery.uploadify.min.js"  rel="stylesheet"> --%>
<link href="${ctx }/static/css/upImage.css" rel="stylesheet">
<link href="${ctx }/static/css/jquery.autocomplete.css" rel="stylesheet">
<style type="text/css">
	.myUploadBox #SWFUpload_0{
		left: 0px;
	}
	.loading{
		display:none;
		width:160px;
		height:56px;
		position:fixed;
		top:50%;
		left:50%;
		line-height:56px;
		color:#fff;
		padding-left:60px;
		font-size:15px;
		background: #000 url(${ctx }/static/img/loader.gif) no-repeat 10px 50%;
		opacity: 0.7;
		z-index:9999;
		border-radius:20px;
		filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);
	}
</style>

<decorator:head />
</head>

<body>
      <jsp:include page="/WEB-INF/decorators/header.jsp">
          <jsp:param name="systemName" value="IDC管理系统后台" />
      </jsp:include>

	  <div class="mainCont animated fadeInRight ng-scope" ng-view>
		  <decorator:body />
	  </div>

	  <%--<div class="container-fluid mt20" style="padding:0 5px;margin-top:10px; position:relative;">
		  <div class="row" style="margin:0;"><!-- Row -->
			  <div class="col-md-12">
				  <decorator:body />
			  </div>
		  </div>
	  </div>--%>
	  <%--<div class="container-fluid mt20" style="padding:0 5px;margin-top:10px; position:relative;"><!-- Container -->
	  	<a class="showBtn" id="showBtn" href="javascript:;">展开</a>
	    <div class="row" style="margin:0;"><!-- Row -->
	    
		    &lt;%&ndash;<div id="leftbar" class="col-md-2" style="padding:0;"><!-- 左侧内容 -->&ndash;%&gt;
	        &lt;%&ndash;<%@include file="/WEB-INF/decorators/left.jsp" %>     &ndash;%&gt;
		    &lt;%&ndash;</div><!-- / 左侧内容 -->&ndash;%&gt;
		    
		    <div id="maincontent" class="col-md-10" style="padding:0 0 0 5px;"><!-- 右侧内容 -->
		 		  <decorator:body />
		    </div><!-- / 右侧内容 -->
	    
	    </div><!-- / Row -->
	  </div><!-- / Container -->--%>
		
		
		<%--<!-- footer -->   --%>
		<%--<%@include file="/WEB-INF/decorators/footer.jsp" %>--%>
		<%--<!-- /footer -->--%>
		
		


	<%-- 
	模式窗口 
	使用时，通过 remote 加载div 片段，例如
	
	$("#myDlg").modal({
		remote:"${ctx}/admin/role_form_dlg?id="+id
	});
	--%>
	<!-- Modal -->
	<!-- 小 -->
	<div id="myDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog ">
			<div id="myDlgBody" class="modal-content">
			
			</div>
		</div>
	</div>
	<!-- 大 -->
	<div id="myDlg_lg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" id="myDlgBody_lg">
			
			</div>
		</div>
	</div>
    <div id="loading" class="loading">加载中...</div>

<script type="text/javascript">
$(function(){
	var mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
  	var webkit = /webkit/.test(navigator.userAgent.toLowerCase());
  	if(!mozilla&&!webkit){
  		alert("请使用FireFox或Chrome浏览器");
  		var tip = '<dd class="ddDesTel" id="browser">'+
				'<span><a target="_blank" href="http://rj.baidu.com/soft/detail/14744.html?ald">chrome浏览器下载</a>'+
			    '<a target="_blank" href="http://rj.baidu.com/soft/detail/11843.html?ald">Firefox浏览器下载</a></span>'+
				'</dd>'
  		$("body").html(tip);
  	}
	$("#leftBtn").click(function(){
		$("#leftbar").hide();
		$("#maincontent").width("95%");
		$("#showBtn").show();
		$(this).hide();
	});
	$("#showBtn").click(function(){
		$("#leftbar").show();
		$("#maincontent").width("78%");
		$(this).hide();
		$("#leftBtn").show();
	});
});
//alert
function myAlert(msg,type){
	if(!type){
		type = 'success';
	}
	
	/*
	http://github.hubspot.com/messenger/
	message: The text of the message
	type: info, error or success are understood by the provided themes. You can also pass your own string, and that class will be added.
	theme: What theme class should be applied to the message? Defaults to the theme set for Messenger in general.
	id: A unique id. If supplied, only one message with that ID will be shown at a time.
	singleton: Hide the newer message if there is an id collision, as opposed to the older message.
	actions: Action links to put in the message, see the 'Actions' section on this page.
	hideAfter: Hide the message after the provided number of seconds
	hideOnNavigate: Hide the message if Backbone client-side navigation occurs
	showCloseButton: Should a close button be added to the message?
	closeButtonText: Specify the text the close button should use (default ×)
	 */
	Messenger().post({
		message: msg,
		type: type,
		hideAfter: 3,
		singleton: true,
 		showCloseButton: true
	});
}

//confirm
/*function myConfirm(title,msg,func){
	$.messager.confirm(title, msg, func);
}

$(function() {
	//alert config
	Messenger.options = {
	    extraClasses: 'messenger-fixed messenger-on-top',
	    theme: 'air'
	}
	
	//confirm config
	$.messager.model = {
		ok : {
			text : "确认",
			classed : 'btn-info'
		},
		cancel : {
			text : "取消",
			classed : 'btn-error'
		}
	};
	
});*/


var app = {};
/*
 * 为页面元素增加 disabled 属性.
 */
app.disabled = function(dom) {
	$(dom).attr({disabled: true});
	$(dom).addClass("disabled");
}

/*
 * 取消页面元素的 disabled 属性.
 */
app.enabled = function(dom) {
	$(dom).removeAttr("disabled");
	$(dom).removeClass("disabled");
}

$(function() {
	$("#nav_admin").attr("class","active");


});



	// bootstrap 图表插件封装
	var myChart = {
		line : function(canvasId,dataUrl,dataForm) {
			var ctx = $(canvasId)[0].getContext("2d");
			var chart = new Chart(ctx);
			var rtn = null;
			$.ajax({
				type : "post",
				url : dataUrl,
				dataType : "text",
				data : dataForm,
				success : function(data) {
					data = JSON.parse(data);
					rtn = chart.Line(data, {responsive : true });
				}
			});
			return rtn;
		},
		bar : function(canvasId,dataUrl,dataForm) {
			var ctx = $(canvasId)[0].getContext("2d");
			var chart = new Chart(ctx);
			var rtn = null;
			$.ajax({
				type : "post",
				url : dataUrl,
				dataType : "text",
				data : dataForm,
				success : function(data) {
					data = JSON.parse(data);
					rtn = chart.Bar(data, {responsive : true });
				}
			});
			return rtn;
		},
		pie : function(canvasId,dataUrl,dataForm) {
			var ctx = $(canvasId)[0].getContext("2d");
			var chart = new Chart(ctx);
			var rtn = null;
			$.ajax({
				type : "post",
				url : dataUrl,
				dataType : "text",
				data : dataForm,
				success : function(data) {
					data = JSON.parse(data);
					rtn = chart.Pie(data, {responsive : true });
				}
			});
			return rtn;
		}
	}

	/* 重置按钮 */
	$("#resetButton").click(function() {
	});
</script>

</body>
</html>