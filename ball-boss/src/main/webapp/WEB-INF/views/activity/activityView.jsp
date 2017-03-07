<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动管理</title>
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
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li>活动管理</li>
        <li class="active">
          查看活动
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>活动信息</small></legend>
<div class="row">
<form id="inputForm" method="post" class="form-horizontal">
	<div class="col-sm-12">
		<div class="form-group form-group-sm">
	       <label class="col-md-3 control-label">活动名称:</label>
	       <label class="col-md-6 control-label label2" style="text-align:left;">${activity.name}</label>
	    </div>
	    <div class="form-group form-group-sm">
	       <label class="col-md-3 control-label">活动类型:</label>
	       <label class="col-md-6 control-label label2" style="text-align:left;">
				<c:if test="${'1' == activity.type}">羽毛球</c:if>
				<c:if test="${'2' == activity.type}">网球</c:if>
				<c:if test="${'3' == activity.type}">篮球</c:if>
				<c:if test="${'4' == activity.type}">乒乓球</c:if>
				<c:if test="${'5' == activity.type}">高尔夫</c:if>
				<c:if test="${'6' == activity.type}">足球</c:if>
				<c:if test="${'7' == activity.type}">台球</c:if>
				<c:if test="${'8' == activity.type}">保龄球</c:if>
	       </label>
	    </div>
	      <div class="form-group form-group-sm">
				<label class="col-md-3 control-label">活动图片:</label>
			    <div class="col-md-1 has-feedback">
			    	<img src="${activity.photo}" style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
			    </div>
			</div>
	    <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">开始时间:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.startTime}</label>
      </div>      
      <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">结束时间:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.endTime}</label>
      </div>
	  <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">主办方:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.undertake}</label>
      </div>
      <div class="form-group form-group-sm">
	  		<label class="col-md-3 control-label">报名金额:</label>
	  		<label class="col-md-6 control-label label2" style="text-align:left;">${activity.price }</label>
	  </div>
	  <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">已报人数:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">
         	  <span class="text-red"></span>${activity.registrationNumber}
         	  <a style="margin-left: 50px;" href="${ctx }/activity/userList/${firstItem.id }" >点此查看报名人员列表</a>
         </label>
      </div>
      <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">活动地点:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.address }</label>
	  </div>
      <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">报名截止:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.expiryDate}</label>
      </div>
	  <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">活动描述:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">${activity.briefdesc}</label>
      </div>
      <div class="form-group form-group-sm">
         <label class="col-md-3 control-label">主办方介绍:</label>
         <label class="col-md-6 control-label label2" style="text-align:left;">
         	${activity.introduction}
         </label>
      </div>
      <c:if test='${!empty activity.qrUrl}'>
      <div class="form-group form-group-sm">
         	<label class="col-md-3 control-label"><span class="text-red"></span> 活动二维码:</label>
         	<div class="col-md-6 has-feedback">
             	<img alt="" style="width:300px;height:300px;" src="${activity.qrUrl}bigPicture">
         	</div>
      </div>
      </c:if>
	</div>
	 </form>
</div><!-- end row -->	
		</fieldset>
		 <div class="form-group">
		 	<hr>
		 	<div class="col-md-offset-2 col-md-2">
			     <button type="button" class="btn btn-default btn-block" id="submit_btn"><span class="glyphicon"></span> 活动控制</button>
			</div>
		 	
		 	<shiro:hasPermission name="activity:update">
				<div class="col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/activity/update/${activity.id}"><span class="glyphicon"></span> 修改</a>
				</div>
			</shiro:hasPermission>
			<div class="col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/activity/list"><span class="glyphicon"></span> 返回</a>
			</div>
		</div>
  </div>
</div>
<script type="text/javascript">
$(function() {
	menu.active('#activity-man');
    $("#submit_btn").click(function(){
		location.href = "${ctx }/activity/itemList/${activity.id }";
    });
});
</script>
</body>
</html>