<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>场地管理</title>
	<script src="${ctx}/static/js/utils.js"></script>
</head>
<body>
    <div class="panel panel-default">
       <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" style="margin:-7px 0px 0px 0px">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
		</div>
        <div class="panel-body"><!-- 右侧主体内容 -->
            <form id="inputForm" method="post" class="form-horizontal">
            <input type="hidden" name="priceTemps" id="priceTemps"/>
	        <c:forEach items="${spaces}" var="space" varStatus="stat">
                <input type="hidden" name="statiumId" value="${space.statiumId}"/>
                <input type="hidden" name="id" value="${space.id}"/>
                <fieldset>
	                <legend><small>${space.spaceCode}场地信息</small></legend>
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
	                        <input type="text" class="form-control" id="spaceName" name="spaceName" value="${space.spaceName}" <c:if test="${action=='detail' }">disabled="true"</c:if>/>
	                    </div>
	                </div>
	                <div class="form-group form-group-sm">
	                    <label for="comment" class="col-md-3 control-label"><span class="text-red"></span>场地备注:</label>
	                    <div class="col-md-6 has-feedback">
	                        <textarea class="form-control" id="comment" name="comment" <c:if test="${action=='detail' }">disabled="true"</c:if> rows="3" ${disable}>${space.comment}</textarea>
	                    </div>
	                </div>
	               
	               <div class="form-group form-group-sm">
			         <label class="col-md-3 control-label"></label>
			         <div class="col-md-6 has-feedback" >
						 <%@ include file="/WEB-INF/views/statium/batchPriceTemp.jsp" %>
			         	<script type="text/javascript">
			         		Util.getData(ctx + '/statium/space/spacePrice', function(data){
					      	 if(data.result){
					      		 BindAndEditWrap(JSON.parse(data.data),'${space.id}','${action}');
					      		 $(".btn_close").hide();
						     }else{
						    	 myAlert(data.reason,"error");
						    	 app.disabled("#submit_btn");
						    	 return false;
							 }
					      }, null, {"spaceId":"${space.id}","statiumId":"${space.statiumId}","sportType":"${space.sportType}"}, 'post');
			         	</script>
			         </div>
			      </div>
	               
	            </fieldset>
			    </c:forEach>
	        </form>
	    </div>
	</div>

	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/batchPriceTemp.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#statium-man');
			$('#inputForm').validate({
				submitHandler: function(form) {
					//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
					getData(function(data){
						app.disabled("#submit_btn");
						$("#priceTemps").val(data);
						Util.getData(ctx + '/statium/space/batchModify', function(data){
					      	 if(data.result){
						      	 window.location.href = "${ctx}/statium/space?statiumId=${statiumId}";
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
			
		});
	</script>
</body>
</html>
