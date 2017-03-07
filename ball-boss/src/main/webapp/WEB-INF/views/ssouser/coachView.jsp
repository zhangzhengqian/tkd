<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>教陪管理</title>
	<style type="text/css">
		.label2{
		text-align: left; 
		font-weight: normal;
		}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
        <li>教陪管理</li>
        <li class="active">
          查看教陪
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>教陪信息</small></legend>
<div class="row">
	<div class="col-sm-2">
		<div class="panel">
			<div id="title" class="panel-body" style="min-height: 350px;">
				<img id="icon_img" src="<c:if test='${empty coach.photo}'>http://www.chfax.com/public/avatar/noavatar_middle.gif</c:if><c:if test='${!empty coach.photo}'>${coach.photo}</c:if>" style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
			</div>
		</div>
	</div>
	<div class="col-sm-10">
		<form id="inputForm" action="${ctx}/ssouser/coach/${action }" method="post" class="form-horizontal">
		<zy:token/>
		<div class="form-group form-group-sm">
	       <label for="phone" class="col-md-2 control-label"><span class="text-red"></span>注册手机号:</label>
	       <label for="phone" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.phone}</label>
	    </div>
	    <div class="form-group form-group-sm">
	       <label for="name" class="col-md-2 control-label"><span class="text-red"> </span>姓名:</label>
	       <label for="name" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.name}</label>
	    </div>
	    <div class="form-group form-group-sm">
         <label for="nickname" class="col-md-2 control-label"><span class="text-red"> </span>用户昵称:</label>
         <label for="nickname" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.nickName}</label>
      </div>      
      <div class="form-group form-group-sm">
         <label for="sex" class="col-md-2 control-label"><span class="text-red"> </span>性别:</label>
         <label for="sex" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.sex}</label>
      </div>
	  <div class="form-group form-group-sm">
         <label for="birthday" class="col-md-2 control-label"><span class="text-red"> </span>出生日期:</label>
         <label for="birthday" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.birthday}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="areaCode" class="col-md-2 control-label"><span class="text-red"></span>所在地区:</label>
         <label for="birthday" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span><tags:zonemap code="${coach.area }" /></label>
	  </div>
	  <div class="form-group form-group-sm">
         <label for="userType" class="col-md-2 control-label"><span class="text-red"> </span>用户类型:</label>
         <label for="userType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.userType}</label>
      </div>
      <div class="form-group form-group-sm">
	         <label for="sportType" class="col-md-2 control-label"><span class="text-red"> </span>运动类别:</label>
	         <label for="sportType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.sprotTypeZWStr}</label>
	      </div>
	  <div class="form-group form-group-sm">
         <label for="occType" class="col-md-2 control-label"><span class="text-red"> </span>职业类型:</label>
         <label for="occType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.occType}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="occType" class="col-md-2 control-label"><span class="text-red"> </span>教习开始时间:</label>
         <label for="occType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.startTime}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="occType" class="col-md-2 control-label"><span class="text-red"> </span>教习结束时间:</label>
         <label for="occType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.endTime}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="occType" class="col-md-2 control-label"><span class="text-red"> </span>价格:</label>
         <label for="occType" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.price}元</label>
      </div>
<%--       <div class="form-group form-group-sm">
         <label for="school" class="col-md-2 control-label"><span class="text-red"> </span>所属学院:</label>
         <label for="school" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.school}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="bestRace" class="col-md-2 control-label"><span class="text-red"> </span>最好成绩:</label>
         <label for="bestRace" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.bestRace}</label>
      </div> --%>
      <div class="form-group form-group-sm">
         <label for="trainingYears" class="col-md-2 control-label"><span class="text-red"> </span>球龄:</label>
         <label for="trainingYears" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.trainingYears}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="coachLevel" class="col-md-2 control-label"><span class="text-red"> </span>教练级别:</label>
         <label for="coachLevel" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.coachLevel}</label>
      </div>
      <div class="form-group form-group-sm">
         <label for="teachingMethod" class="col-md-2 control-label"><span class="text-red"> </span>授课方式:</label>
         <label for="coachLevel" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>
         <c:if test="${coach.teachingMethod == 0}">一对一</c:if>
         <c:if test="${coach.teachingMethod == 1}">一对多</c:if>
         </label>
      </div>
	  <div class="form-group form-group-sm">
         <label for="statiums" class="col-md-2 control-label"><span class="text-red"> </span>驻场场馆:</label>
         <label for="statiums" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.zhcStatiums}</label>
      </div>      
      <div class="form-group form-group-sm">
         <label for="teachArea" class="col-md-2 control-label"><span class="text-red"> </span>教习区域:</label>
         <label for="teachArea" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.areaStr}</label>
      </div>
<%--       <div class="form-group form-group-sm">
         <label for="maserLang" class="col-md-2 control-label"><span class="text-red"> </span>掌握语种:</label>
         <label for="maserLang" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.maserLang}</label>
      </div> --%>
      <div class="form-group form-group-sm">
         <label for="cardId" class="col-md-2 control-label"><span class="text-red"> </span>身份证号:</label>
         <label for="cardId" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.cardId}</label>
      </div>
<%--       <div class="form-group form-group-sm">
         <label for="coachQual" class="col-md-2 control-label"><span class="text-red"> </span>教练资质:</label>
         <label for="coachQual" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.coachQual}</label>
      </div> --%>
      <div class="form-group form-group-sm">
				<label for="phoneFront" class="col-md-2 control-label"><span class="text-red"></span>身份证正面照:</label>
			    <div class="col-md-5 has-feedback">
			    	<c:if test='${!empty coach.phoneFront}'>
			    		<img style="width:128px;height:128px;display:block;" src="${coach.phoneFront}" >
			    	</c:if>
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="phoneBack" class="col-md-2 control-label"><span class="text-red"></span>身份证反面照:</label>
			    <div class="col-md-5 has-feedback">
			    	<c:if test='${!empty coach.phoneBack}'>
			    		<img style="width:128px;height:128px;display:block;" src="${coach.phoneBack}" >
			    	</c:if>
			    </div>
			</div>
		<div class="form-group form-group-sm">
	         <label for="introduce" class="col-md-2 control-label"><span class="text-red"> </span>教练/陪练介绍:</label>
	         <label for="introduce" class="col-md-2 control-label label2" style="text-align:left;"><span class="text-red"></span>${coach.introduce}</label>
	      </div>
      </form>
	</div>
	 
</div><!-- end row -->	
		 
		</fieldset>
		
		 <div class="form-group">
		 	<hr>
		 	<div class="col-md-offset-3 col-md-2">
				<button type="button" class="btn btn-primary btn-block" style="background-color: green;border-color: green;" 
		  			id="audit_btn" <c:if test="${coach.status==2 }">disabled="disabled"</c:if> ><span class="glyphicon glyphicon-ok"></span> 审核通过</button>
			</div>
			<div class="col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/ssouser/coach"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
				<shiro:hasPermission name="coach:update">
				  <button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 修改</button>
				</shiro:hasPermission>
			</div>
		</div>
  </div>
	
</div>

<div class="modal fade" id="auditModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">教练等级</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group">
						<select class="form-control" id="coachLevel">	
							<option value="1" >初级教练</option>
							<option value="2" >中级教练</option>
							<option value="3" >高级教练</option>
							<option value="4" >金牌教练</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="audit()">通过</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#coach-man');
    $("#submit_btn").click(function(){
		location.href = "${ctx }/ssouser/coach/update/${coach.id }";
  	});
	$('#adminFooter').hide();
});

$("#audit_btn").click(function(){
	$("#auditModel").modal();
});

function audit(){
	var coachLevel = $("#coachLevel").val();
	$.ajax({
		url: "${ctx }/ssouser/coach/audit/${coach.id}/"+coachLevel,
		type: 'post',
		dataType: 'json',
		success: function(msg) {
			if(msg.result){
				alert("审核通过！");
				location.reload();
			}else{
				alert(msg.result.reason);
			}
		},
		error: function() {
			//$('#match').html("网络繁忙，请刷新重试！");
		}
	})
}
</script>
</body>
</html>
