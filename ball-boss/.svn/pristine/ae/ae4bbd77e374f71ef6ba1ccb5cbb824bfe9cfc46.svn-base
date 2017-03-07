<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="renderer" content="webkit" />
<title></title>

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



<decorator:head />
</head>

<body style="min-height: 800px; background-color: rgba(0,255,255,0.05)" >
      <jsp:include page="/WEB-INF/decorators/header.jsp">
          <jsp:param name="systemName" value="IDC管理系统后台" />
      </jsp:include>
		
	  <div class="container-fluid mt20" style="padding:0 5px;margin-top:10px; position:relative;"><!-- Container -->
	  	<a class="showBtn" id="showBtn" href="javascript:;">展开</a>
	    <div class="row" style="margin:0;"><!-- Row -->
	    
		    <div id="leftbar" class="col-md-2" style="padding:0;"><!-- 左侧内容 -->
	        <%@include file="/WEB-INF/decorators/left.jsp" %>     
		    </div><!-- / 左侧内容 -->
		    
		    <div id="maincontent" class="col-md-10" style="padding:0 0 0 5px;"><!-- 右侧内容 -->
		 		  <decorator:body />
		    </div><!-- / 右侧内容 -->
	    
	    </div><!-- / Row -->
	  </div><!-- / Container -->
		
		
		<!-- footer -->   
		<%@include file="/WEB-INF/decorators/footer.jsp" %>
		<!-- /footer -->
		
		


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
function myConfirm(title,msg,func){
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
	
});


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
	
	
	$("table").on("click","tr:gt(0)",function(){
		$(this).addClass("info");
		$(this).siblings().removeClass("info");
	})

	/* 消息模块 */
	$("#message_btn").click(function() {
		$("#myDlgBody_lg").load("${ctx}/message/index_dlg");
		$("#myDlg_lg").modal();
	});
	
	var loopMessage = function(){
		$.ajax({
			type: 'POST',
			url : "${ctx}/message/badge",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					var badge = data.entity.badge;
					var message = data.entity.message;
					$("#message_badge").html(badge);
					if(message){
						var audioElement = document.createElement('audio');
				        audioElement.setAttribute('src', '${ctx }/static/messenger/4.mp3');
				        audioElement.setAttribute('autoplay', 'autoplay'); 
				        audioElement.addEventListener("load", function() { audioElement.play();}, true);
						myAlert(message);
					}
				}else{
					myAlert(data.entity,"error");
				}
			}
		});
	};
	//loopMessage();
	setInterval(function() {
		loopMessage();
	}, 30000);
	
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
	
	function callPhone(phone){
		// phone = '13811235799';
		if(phone.indexOf('-')>0){
			phone = phone.split('-')[0]+phone.split('-')[1];
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'${ctx}/cph/callPhone/'+phone,
	        data:{},
	        async: false,
	        error: function(request) {
	        	common.showMessage("获取callId失败");
	        },
	        success: function(data) {
	        	data = eval("("+data+")");   
	        	if(data.result || data.result == 'true'){
	        		data = eval("("+data.data+")");
	        		if(data.Succeed){
	        			common.showMessage("呼叫成功");
	        		}else{
	        			common.showMessage(data.Message);
	        		}
	        	}else{
	        		common.showMessage("呼叫失败");
	        	}
	        }
	    }); 
	}
</script>

</body>
</html>