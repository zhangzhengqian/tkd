<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户管理</title>
</head>
<body>
    <div class="panel panel-default">
        <div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
		        <li>场地管理</li>
		        <li class="active">
		          <c:if test="${'create' eq action }"> 新建场地</c:if>
		          <c:if test="${'update' eq action }"> 修改场地</c:if>
		        </li>
		    </ul>
        </div><!-- / 右侧标题 -->
        <div class="panel-body"><!-- 右侧主体内容 -->
            <form id="inputForm" action="${ctx}/statium/space/create" method="post" class="form-horizontal">
                <input type="hidden" name="statiumId" value="${space.statiumId}"/>
                <input type="hidden" name="id" value="${space.id}"/>
				<input type="hidden" name="priceTemps" id="priceTemps"/>
                <fieldset>
	                <legend><small>场地信息</small></legend>
	                <div id="cust-field" class="form-group form-group-sm">
	                    <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场地类型:</label>
	                    <div class="col-md-6 has-feedback">
	                        <select class="form-control" id="sportType" name="sportType">
	                        	<option value="" >请选择类型</option>
	                        <c:forEach items="${fn:split(sportTypes,';;') }" var="item">
	                            <option value="${item}" <c:if test="${space.sportType eq item }">selected</c:if>>
	                            <c:if test="${item eq 'BOWLING' }">保龄球</c:if>
	                            <c:if test="${item eq 'BILLIARDS' }">台球</c:if>
	                            <c:if test="${item eq 'TABLE_TENNIS' }">乒乓球</c:if>
	                            <c:if test="${item eq 'FOOTBALL' }">足球</c:if>
	                            <c:if test="${item eq 'BASKETBALL' }">篮球</c:if>
	                            <c:if test="${item eq 'TENNIS' }">网球</c:if>
	                            <c:if test="${item eq 'GOLF' }">高尔夫</c:if>
	                            <c:if test="${item eq 'BADMINTON' }">羽毛球</c:if>
	                            </option>
	                        </c:forEach>
	                        </select>
	                    </div>
	                </div>	 
	                
	                <c:if test="${action == 'create'}">
	                <div class="form-group form-group-sm">
	                    <label for="spaceNumber" class="col-md-3 control-label"><span class="text-red">* </span>场地数量:</label>
	                    <div class="col-md-6 has-feedback">
	                        <input type="text" class="form-control" id="spaceNumber" name="spaceNumber"/>
	                    </div>
	                </div>
	                </c:if>   
	               
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
	<script type="text/javascript">
		$(function() {
			menu.active('#statium-man');
			$('#inputForm').validate({
				submitHandler: function(form) {
					//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
					getData(function(data){
						app.disabled("#submit_btn");
						$("#priceTemps").val(data);
						Util.getData(ctx + '/statium/space/create', function(data){
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
			
			$("#sportType").change(function(){
				Util.getData(ctx + '/statium/space/spacePriceTmpl', function(data){
			      	 if(data.result){
			      		BindAndEditWrap(JSON.parse(data.data));
			      		$(".btn_close").hide();
				     }else{
				    	 myAlert(data.reason,"error");
				    	 return false;
					 }
			      }, null, {"statiumId":"${space.statiumId}","sportType":$(this).val()}, 'post');
			});
		});
		
		// 数据请求
		var Util = {
			/** 数据请求 **/
			getData : function(url, cb, errorcb, data, type) {
				//$.commLoading.show();
				$.ajax({
					url  : url,
					type : type,
					data : data,
					timeout:100000,
					dataType: "json",
					success : function(redata, s) {
						{
							cb.call(this, redata);
						}
						//$.commLoading.hide();
					},
					error : function() {
						//$.commLoading.hide();
						alertContent('失败提示', '请求失败！', 1500, 0);
						if(errorcb != null)
						errorcb.call(this);
					}
				});
			}
		};
	</script>
</body>
</html>
