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
  
	<h3>参赛信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/bigcitygame/update" method="post" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
			<div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red"></span>团队名称:</label>
	         <div class="col-md-3 has-feedback">
				<input type="text" class="form-control" name="name" value="${data.name }"/>
	         </div>
	     	</div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>领队姓名:</label>
		       <div class="col-md-3 has-feedback">
		         	<input type="text" class="form-control" name="leader" value="${data.leader }"/>
		       </div>
		    </div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>教练员:</label>
		       <div class="col-md-3 has-feedback">
		         	<input type="text" class="form-control" name="coach" value="${data.coach }"/>
		       </div>
		    </div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>球队赞助单位:</label>
		       <div class="col-md-3 has-feedback">
		         	<input type="text" class="form-control" name="supportOrg" value="${data.supportOrg }"/>
		       </div>
		    </div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>联系人:</label>
		       <div class="col-md-3 has-feedback">
		         	<input type="text" class="form-control" name="linkMan" value="${data.linkMan }"/>
		       </div>
		    </div>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red"></span>联系电话:</label>
		       <div class="col-md-3 has-feedback">
		         	<input type="text" class="form-control" name="linkPhone" value="${data.linkPhone }"/>
		       </div>
		    </div>
		</fieldset>
		<hr>
		<div id="game_data">
		
		</div>
		<input type="hidden" name="details" id="details" value=""/>
		<input type="hidden" name="id" value="${data.id}"/>
	<div class="form-group form-group-sm">
	  	<div class="col-md-offset-1 col-md-2">
		   <a class="btn btn-default btn-block" href="javascript:history.go(-1)"><span class="glyphicon"></span> 返回</a>
		</div>
		<div class="col-md-2">
		   <a class="btn btn-default btn-block btn-warning modify"><span class="glyphicon"></span> 修改</a>
		</div>
		<c:if test="${data.auditState==0}">
			<div class="col-md-2">
			    <a class="btn btn-default btn-block btn-success" href="${ctx}/bigcitygame/audit/${data.id}/1"><span class="glyphicon"></span> 通过</a>
			</div>
			<div class="col-md-2">
		   		<a class="btn btn-default btn-block btn-danger" href="${ctx}/bigcitygame/audit/${data.id}/0"><span class="glyphicon"></span> 不通过</a>
			</div>
		</c:if>
	</div>	
	</form>
  </div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#bigcitygame-man');
	var temp = new EJS({url: '${ctx}/static/template/team_member.ejs?ver=1.2'});
	var gameData = {};
	if('${data.details}'!=''){
		gameData = JSON.parse('${data.details}');
	}
	console.log('${data.details}');
	var html = temp.render({data:gameData});
	$("#game_data").html(html);
});

$(".modify").on("click",function(){
	var gameData = {};
	$("#game_data").children("fieldset").each(function(){
		var id = $(this).attr("id");
		var grp1 = $(this).children(".grp1");
		var data_ = [];
		var data1 = {};
		grp1.find("input").each(function(){
			data1[$(this).attr("attr")] = $(this).val();		
		})
		var grp2 = $(this).children(".grp2");
		var data2 = {};
		grp2.find("input").each(function(){
			data2[$(this).attr("attr")] = $(this).val();
		})
		data_[0] = data1;		
		data_[1] = data2;		
		gameData[id] = data_;
	});
	$("#details").val(JSON.stringify(gameData));
	$("#inputForm").submit();
	return false;
})
</script>