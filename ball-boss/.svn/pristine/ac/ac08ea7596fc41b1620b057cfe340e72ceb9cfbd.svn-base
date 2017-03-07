<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>场地管理</title>
</head>
<body>
	  <c:choose>
	  	<c:when test="${action == 'edit' }">
	  		<c:set var="disable" value=""/>
	  	</c:when>
	  	<c:otherwise>
			<c:set var="disable" value="disabled='true'"/>
			<c:set var="readonly" value="readonly='readonly'"/>
	  	</c:otherwise>
	  </c:choose> 
    <div class="panel panel-default">
        <div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
		        <li>场地管理</li>
		        <li class="active">
		          <c:if test="${'edit' eq action }"> 修改场地</c:if>
		          <c:if test="${'detail' eq action }"> 场地信息</c:if>
		        </li>
		    </ul>
        </div><!-- / 右侧标题 -->
        <div class="panel-body"><!-- 右侧主体内容 -->
            <form id="inputForm" action="${ctx}/statium/space/update" method="post" class="form-horizontal">
                <input type="hidden" name="statiumId" value="${space.statiumId}"/>
                <input type="hidden" name="id" value="${space.id}"/>
                <input type="hidden" name="priceTemps" id="priceTemps"/>
                <fieldset>
	                <legend><small>场地信息</small></legend>
	                <div id="cust-field" class="form-group form-group-sm">
	                    <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场地类型:</label>
	                    <div class="col-md-6 has-feedback">
	                    	<input type="text" class="form-control" id="sportType" name="sportType" value="${lf:dicItem(space.sportType).itemName }"  disabled="true"/>
	                    </div>
	                </div>	 
	                
	                <div class="form-group form-group-sm">
	                    <label for="spaceCode" class="col-md-3 control-label"><span class="text-red">* </span>场地编号:</label>
	                    <div class="col-md-6 has-feedback">
	                        <input type="text" class="form-control" id="spaceCode" name="spaceCode" value="${space.spaceCode}" disabled="true"/>
	                    </div>
	                </div>
	      
	                <div class="form-group form-group-sm">
	                    <label for="spaceName" class="col-md-3 control-label"><span class="text-red">* </span>场地名称:</label>
	                    <div class="col-md-6 has-feedback">
	                        <input type="text" class="form-control" id="spaceName" name="spaceName" value="${space.spaceName}" ${disable}/>
	                    </div>
	                </div>
	                <%-- <div class="form-group form-group-sm">
	                    <label for="comment" class="col-md-3 control-label"><span class="text-red"></span>场地备注:</label>
	                    <div class="col-md-6 has-feedback">
	                        <textarea class="form-control" id="comment" name="comment" rows="3" ${disable}>${space.comment}</textarea>
	                    </div>
	                </div> --%>
	               
	               <div class="form-group form-group-sm">
			         <label class="col-md-3 control-label"></label>
			         <div class="col-md-6 has-feedback" >
						 <%@ include file="/WEB-INF/views/statium/priceTemp.jsp" %>
			         </div>
			      </div>
	               
	            </fieldset>
	            <br/>
	            <div class="form-group">
	                <div class="col-md-offset-3 col-md-2">
	                    <a class="btn btn-default btn-block" href="${ctx}/statium/space?statiumId=${space.statiumId}"><span class="glyphicon glyphicon-remove"></span> 返回</a>
	                </div>
	                <div class="col-md-2">
	                    <button type="submit" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>

	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/project_js.js"></script>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#statium-man');
			$('#inputForm').validate({
				submitHandler: function(form) {
					//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
					getData(function(data){
						app.disabled("#submit_btn");
						$("#priceTemps").val(data);
						Util.getData(ctx + '/statium/space/update', function(data){
					      	 if(data.result){
						      	 window.location.href = "${ctx}/statium/space?statiumId=${space.statiumId}";
						     }else{
						    	 myAlert(data.reason,"error");
						    	 app.enabled("#submit_btn");
						    	 return false;
							 }
				      }, null, $('#inputForm').serialize(), 'post');
					});
				},
				rules : {
					sportType : {
						required : true
					},
					spaceNumber : {
						required : true,
						digits:true,
						range:[1,99]
					},
				},
				messages : {
					sportType : {
						required : '请选择场地类型！'
					},
					spaceNumber : {
						required : '请输入场地数量！',
						digits:"请数输入整数",
						range:"场地数量请输入1到99之间的整数",
					}
				}
			});
			
			Util.getData(ctx + '/statium/space/spacePrice', function(data){
		      	 if(data.result){
		      		 BindAndEditWrap(JSON.parse(data.data));
		      		 $(".btn_close").hide();
		      		<c:if test="${not empty readonly}">
			    		$("#contentByTemplate").find("input,select,button").attr("disabled",true);
			    	</c:if>
			     }else{
			    	 myAlert(data.reason,"error");
			    	 app.disabled("#submit_btn");
			    	 return false;
				 }
		      }, null, {"spaceId":"${space.id}","statiumId":"${space.statiumId}","sportType":"${space.sportType}"}, 'post');
		});
	</script>
</body>
</html>
