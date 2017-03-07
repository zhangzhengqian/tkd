<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span><a href="${ctx }/corps/"> 战队管理 </a></li>
	        <li class="active">战队信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<h3>战队信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/corps/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
			<div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red"></span>战队LOGO:</label>
	         <div class="col-md-6 has-feedback">
				<img alt="" src="${result.info.logo}" id="logo_img">
	         </div>
	     	</div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>战队名称:</label>
		       <div class="col-md-6">
		         	${result.info.name}
		       </div>
		    </div>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>战队队长:</label>
		       <div class="col-md-6">
		       		${result.info.captain}
		       </div>
		    </div>
   		  <div class="form-group form-group-sm">
			 <label for="areaCode" class="col-md-3 control-label"><span class="text-red"></span>活动区域:</label>
		     <div class="col-md-6 has-feedback form-inline">
		     	${result.info.area}
		     </div>
		 </div>
		 <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>详细地址:</label>
	         <div class="col-md-6 has-feedback ">
	           ${result.info.activityArea}
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>运动类型:</label>
	         <div class="col-md-3 has-feedback ">
	           ${result.info.sportType}
	         </div>
	     </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>联系电话:</label>
	         <div class="col-md-6 has-feedback ">
	           ${result.info.phone}
	         </div>
	     </div>
	     
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>战队介绍:</label>
	         <div class="col-md-6 has-feedback ">
	           ${result.info.introduction}
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>战队积分:</label>
	         <div class="col-md-6 has-feedback ">
	           ${result.info.integral}
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>城市排名:</label>
	         <div class="col-md-6 has-feedback ">
	           ${result.integralNum}
	         </div>
	     </div>
	    <div class="form-group form-group-sm">
	    	<label class="col-md-3 control-label">成员(${result.info.currentNumber}人)</label>
	    	<div class="col-md-5 has-feedback">
	    	<table id="member_table" class="table table-bordered">
	    		<thead>
	    			<tr>
	    				<th>logo</th>
	    				<th>姓名</th>
	    				<th>性别</th>
	    				<th>年龄</th>
	    				<th>联系方式</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    		<c:forEach items="${result.mem}" var="member">
 					<tr>
						<td><img width="40" height="36" src="${member.logo}"></td>
						<td>${member.name}</td>
						<td>${member.sex==0?"女":"男"}</td>
						<td>${member.age}</td>
						<td>${member.phone}</td>
					</tr>
				</c:forEach>
				</tbody>
	    	</table>
	    	</div>
	    </div>
	    <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>参赛记录(${result.gameCount}次):</label>
	         <div class="col-md-6 has-feedback ">
	           <a class="lookAt btn btn-primary"><span class="glyphicon glyphicon-search"></span>查看详情</a>
	         </div>
	     </div>
		</fieldset>
	<div class="form-group form-group-sm">
	  	<div class="col-md-offset-3 col-md-2">
		   <a class="btn btn-default btn-block" href="${ctx}/corps"><span class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>         
	</div>	
	</form>
  </div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#corps-man');
	$(".lookAt").on("click",function(){
		var corpsId = '${result.info.id}';
		$("#myDlgBody_lg").load("${ctx}/corps/games_dlg/"+corpsId);
		$("#myDlg_lg").modal();
	});
});

</script>