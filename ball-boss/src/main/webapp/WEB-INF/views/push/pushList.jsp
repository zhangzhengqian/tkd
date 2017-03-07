<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>运营管理/推送管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 推送管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/push/sendPush" method="post">
			  		<input type="hidden" id="pushType" name="pushType" value="source">
					<div class="form-group form-group-sm">
						<label for="action" class="col-md-3 control-label">地区:</label>
						<div class="col-md-6 has-feedback form-inline">
							<tags:zoneCity id="areaCode" name="areaCode" value="${param.areaCode }" />
						</div>
					</div>
		
					<div class="form-group form-group-sm">
						<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
						<div class="col-md-6 has-feedback">
							<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
							<a class="label label-default label-primary" id="imageText" onclick="imageTextSet();">图文</a>
							&nbsp;&nbsp;
							<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
							<a class="label label-default" id="activityText" onclick="activityTextSet();">活动</a>
							&nbsp;&nbsp;
							<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
							<a class="label label-default" id="eventText" onclick="eventTextSet();">赛事</a>
							&nbsp;&nbsp;
							<!-- <span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
							<a class="label label-default" id="text" onclick="textSet();">文本</a> -->
						</div>
					</div>
					
					<div id="textArea" style="display:none" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<textarea class="form-control" style="height:70px;resize:none;" id="content" name="content" placeholder="请填写文本信息" name="content"></textarea>
							</div>
						</div>
					</div>
					
					<div id="imageTextButton" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<button type="button" id="selectSource" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 选择素材</button>
							</div>
						</div>
					</div>
					
					<div id="imageTextArea">
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="title" name="title" value="${param.title }" placeholder="专题标题" readonly>
								<input  type="hidden" class="form-control input-sm" id="groupId" name="groupId" value="${param.groupId }" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="sourceTitle" name="sourceTitle" value="${param.sourceTitle }" placeholder="推送标题：不超过128字（必输）">
							</div>
						</div>
					</div>
					
					<div id="activityTextButton" style="display:none" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<button type="button" id="selectActivity" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 选择活动</button>
							</div>
						</div>
					</div>
					
					<div id="activityTextArea" style="display:none" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="activityName" name="activityName" value="${param.activityName }" placeholder="活动名称" readonly>
								<input  type="hidden" class="form-control input-sm" id="activityId" name="activityId" value="${param.activityId }" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="activityTitle" name="activityTitle" value="${param.activityTitle }" placeholder="推送标题：不超过128字（必输）">
							</div>
						</div>
					</div>
					
					<div id="eventTextButton" style="display:none" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<button type="button" id="selectEvent" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 选择赛事</button>
							</div>
						</div>
					</div>
					
					<div id="eventTextArea" style="display:none" >
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="eventName" name="eventName" value="${param.eventName }" placeholder="赛事名称" readonly>
								<input  type="hidden" class="form-control input-sm" id="eventId" name="eventId" value="${param.eventId }" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
							<div class="col-md-6 has-feedback">
								<input  type="text" class="form-control input-sm" id="eventTitle" name="eventTitle" value="${param.eventTitle }" placeholder="推送标题：不超过128字（必输）">
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
				         <label for="push" class="col-md-3 control-label text-danger"style="font-size:18px"><span class="text-danger">! </span> 是否推送:</label>
				         <div class="col-md-6 has-feedback form-inline">
					           	 <div class="btn-group" id="isPushDiv" data-toggle="buttons">
					           	 	  <label class="btn btn-default<c:if test="${param.isPush == 0}"> active</c:if>">
									  		<input type="radio" name="isPush" id="isPush" value="0"  autocomplete="off"
									  		<c:if test='${param.isPush == 0}'>checked="checked"</c:if> >
									      	即时推送
									  </label>
									  <label class="btn btn-default<c:if test="${param.isPush == 1}"> active</c:if>">
									  		<input type="radio" name="isPush" id="isPush" value="1" autocomplete="off"
									  			<c:if test='${param.isPush == 1}'>checked="checked"</c:if> >
									     	定时推送
									  </label>
								</div>
								<input type="text" id="pushTime" name="pushTime" class="form-control"
									style="<c:if test="${param.isPsuh != 1}">display: none;</c:if>" 
									value="${param.pushTime }" />
				         </div>
				    </div>
				    
					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 发送</button>
							&nbsp;&nbsp;
							<a id="addSource" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span> 添加素材</a>
							&nbsp;&nbsp;
							<a id="sourceManage" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit"></span> 素材管理</a>
						</div>
					</div>
   			 </form>
			</div>
		</div>
	<br>
	
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>推送标题</th>
				<th>推送名称</th>
				<th>推送图片</th>
				<th>推送类型</th>
				<th>推送范围</th>
				<th>推送方式</th>
				<th>推送状态</th>
				<th>推送日期</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="pushVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td style="text-align: center;">${pushVo.pushTitle }</td>
					<td style="text-align: center;">${pushVo.title }</td>
					<td style="text-align: center;">
			         	<img alt="" width="200" height="50" src="${pushVo.image }">
	         		</td>
					<td style="text-align: center;">${pushVo.type }</td>
					<td style="text-align: center;">${pushVo.pushRange }</td>
					<td style="text-align: center;">
						<c:if test="${'0' == pushVo.isPush}">即时推送</c:if>
						<c:if test="${'1' == pushVo.isPush}">定时推送</c:if>
						<c:if test="${'2' == pushVo.isPush || empty pushVo.isPush}">未推送</c:if>
						<c:if test="${'3' == pushVo.isPush}">推送管理即时推送</c:if>
						<c:if test="${'4' == pushVo.isPush}">推送管理定时推送</c:if>
					</td>
					<td style="text-align: center;">
						<c:if test="${'0' ==  pushVo.pState}">未发送</c:if>
						<c:if test="${'1' ==  pushVo.pState}">已发送</c:if>
					</td>
					<%-- <td style="text-align: center;"><fmt:formatDate value="${pushVo.factPushTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
					<td style="text-align: center;">${pushVo.factPushTime }</td>
					<td style="text-align: center;">
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${pushVo.logId }')"> <span class="glyphicon glyphicon-edit">删除</a>
					  <c:if test="${pushVo.type == '专题' }">
					  	<a href="${ctx }/push/editSource?sourceId=${pushVo.id }" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-search"></span> 详情</a> 
					  </c:if>
					  <c:if test="${pushVo.type == '活动' }">
					  	<a href="${ctx }/activity/view/${pushVo.id }" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-search"></span> 详情</a> 
					  </c:if>
					  <c:if test="${pushVo.type == '赛事' }">
					  	<a href="${ctx }/event/view/${pushVo.id }" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-search"></span> 详情</a> 
					  </c:if>
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />	
				
	
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#push-man');
	
	$('#search_form').validate({
		rules : {
			"title":{
				required: true
			},
			"activityName":{
				required: true
			},
			"eventName":{
				required: true
			},
			"sourceTitle":{
				required: true
			},
			"activityTitle":{
				required: true
			},
			"eventTitle":{
				required: true
			},
			"pushType" : {
				required: true
			},
			"isPush" : {
				required: true
			}
		},
		messages : {

		}
	}); 
	
	// 是否推送
	$("#isPushDiv").on("click","label",function(){
		if($(this).find("input[name=isPush]").val() == 1){
			$("#pushTime").bind({click:function(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});
			}}).removeAttr("disabled").attr("placeholder","请输入推送日期").show();
			$('#pushTime').rules( "add",{required: true});
			$("#isPush").val('1');
		} else {
			$("#pushTime").rules("remove");
			$("#pushTime").attr("disabled","disabled");
			$("#pushTime").hide();
			$("#isPush").val('0');
		}
	});
	
	/* 素材查询  */
	$("#selectSource").click(function() {
		$("#myDlgBody").load("${ctx}/push/pushSource_dlg");
		$("#myDlg").modal();
	});
	
	/* 活动查询  */
	$("#selectActivity").click(function() {
		var areaCode = $("#areaCode").val();
		$("#myDlgBody").load("${ctx}/push/activity_dlg?areaCode=" + areaCode);
		$("#myDlg").modal();
	});
	
	/* 赛事查询  */
	$("#selectEvent").click(function() {
		var areaCode = $("#areaCode").val();
		$("#myDlgBody").load("${ctx}/push/event_dlg?areaCode=" + areaCode);
		$("#myDlg").modal();
	});
	
	
	 
	$('#adminFooter').hide();
	
	$('#addSource').click(function() {
	 location.href="${ctx}/push/addSource";
	});
	
	$('#sourceManage').click(function() {
	 location.href="${ctx}/push/sourceManage";
	});
		
});

	function imageTextSet(){
		$('#imageText').toggleClass('label-primary');
		$('#activityText').removeClass('label-primary');
		$('#eventText').removeClass('label-primary');
		$('#text').removeClass('label-primary');
		$('#imageTextArea').toggle();
		$('#imageTextButton').toggle();
		$('#activityTextArea').hide();
		$('#activityTextButton').hide();
		$('#eventTextArea').hide();
		$('#eventTextButton').hide();
		$('#textArea').hide();
		$('#activityName').val('');
		$('#activityId').val('');
		$('#eventName').val('');
		$('#eventId').val('');
		$("#pushType").val("source");
		$("#activityTitle").val('');
		$("#eventTitle").val('');
	}
	
	function activityTextSet(){
		$('#imageText').removeClass('label-primary');
		$('#activityText').toggleClass('label-primary');
		$('#eventText').removeClass('label-primary');
		$('#text').removeClass('label-primary');
		$('#imageTextArea').hide();
		$('#imageTextButton').hide();
		$('#activityTextArea').toggle();
		$('#activityTextButton').toggle();
		$('#eventTextArea').hide();
		$('#eventTextButton').hide();
		$('#textArea').hide();
		$('#title').val('');
		$('#groupId').val('');
		$('#eventName').val('');
		$('#eventId').val('');
		$("#pushType").val("activity");
		$("#pushTitle").toggle();
		$("#sourceTitle").val('');
		$("#eventTitle").val('');
	}
	
	function eventTextSet(){
		$('#imageText').removeClass('label-primary');
		$('#activityText').removeClass('label-primary');
		$('#eventText').toggleClass('label-primary');
		$('#text').removeClass('label-primary');
		$('#imageTextArea').hide();
		$('#imageTextButton').hide();
		$('#activityTextArea').hide();
		$('#activityTextButton').hide();
		$('#eventTextArea').toggle();
		$('#eventTextButton').toggle();
		$('#textArea').hide();
		$('#title').val('');
		$('#groupId').val('');
		$('#activityName').val('');
		$('#activityId').val('');
		$("#pushType").val("event");
		$("#activityTitle").val('');
		$("#sourceTitle").val('');
	}
	
	function textSet(){
		$('#text').toggleClass('label-primary');
		$('#imageText').removeClass('label-primary');
		$('#activityText').removeClass('label-primary');
		$('#eventText').removeClass('label-primary');
		$('#imageTextArea').hide();
		$('#imageTextButton').hide();
		$('#activityTextArea').hide();
		$('#activityTextButton').hide();
		$('#eventTextArea').hide();
		$('#eventTextButton').hide();
		$('#textArea').toggle();
		$('#title').val('');
		$('#groupId').val('');
		$('#activityName').val('');
		$('#activityId').val('');
		$('#eventName').val('');
		$('#eventId').val('');
		$("#pushType").val("text");
		$("#activityTitle").val('');
		$("#eventTitle").val('');
		$("#sourceTitle").val('');
	}
	
	function deleteById(id){
		var msg = "您确定要删除？";
		  bootbox.confirm(msg, function(result) {
			    if(result) {
					var $form = $('#search_form');
					$form.attr('action', '${ctx }/push/deleteById?id='+id);
					$form[0].submit();
			    }
			  }) ;
	}
	
	/* $("#pushBtn").click(function(){
			var $form = $('#search_form');
			var pushType = $("#actionType").val();
			$form.attr('action', '${ctx}/push/sendPush?pushType='+pushType);
			$form[0].submit();
	})  */
	
</script>

</body>
</html>