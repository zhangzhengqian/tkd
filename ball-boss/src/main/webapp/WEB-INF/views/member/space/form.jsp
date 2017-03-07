<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户管理</title>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
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
            <form id="inputForm" action="${ctx}/member/space/${action }" method="post" class="form-horizontal">
                <zy:token/>
                <input type="hidden" name="id" value="${statium.id}"/>
                <fieldset>
	                <legend><small>场地信息</small></legend>
	                <div id="cust-field" class="form-group form-group-sm">
	                    <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场地类型:</label>
	                    <div class="col-md-6 has-feedback">
	                        <select class="form-control" id="sportType" name="sportType"
	                        <c:if test="${action eq 'update' }">disabled</c:if>
	                        >
	                        <c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
	                            <option value="${item.itemCode }" 
	                            <c:if test="${statium.sportType eq item.itemCode }">selected</c:if>
	                            >${item.itemName }</option>
	                        </c:forEach>
	                        </select>
	                    </div>
	                </div>	    
	                <div class="form-group form-group-sm">
	                    <label for="spaceCode" class="col-md-3 control-label"><span class="text-red">* </span>场地编号:</label>
	                    <div class="col-md-6 has-feedback">
	                        <input type="text" class="form-control" id="spaceCode" name="spaceCode" value="${statium.spaceCode}" />
	                    </div>
	                </div>
	      
	                <div class="form-group form-group-sm">
	                    <label for="spaceName" class="col-md-3 control-label"><span class="text-red">* </span>场地名称:</label>
	                    <div class="col-md-6 has-feedback">
	                        <input type="text" class="form-control" id="spaceName" name="spaceName" value="${statium.spaceName}" />
	                    </div>
	                </div>
	                <div class="form-group form-group-sm">
	                    <label for="comment" class="col-md-3 control-label"><span class="text-red"></span>场地备注:</label>
	                    <div class="col-md-6 has-feedback">
	                        <textarea class="form-control" id="comment" name="comment" rows="3">${statium.comment}</textarea>
	                    </div>
	                </div>
	            </fieldset>
	            <br/>
	            <div class="form-group">
	                <div class="col-md-offset-3 col-md-2">
	                    <a class="btn btn-default btn-block" href="${ctx}/member/space"><span class="glyphicon glyphicon-remove"></span> 返回</a>
	                </div>
	                <div class="col-md-2">
	                    <button type="submit" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#statium-space-man');

			$('#inputForm').validate({
				rules : {
					sportType : {
						required : true
					},
					spaceCode : {
						required : true
					},
					spaceName : {
						required : true
					}
				},
				messages : {
					sportType : {
						required : '请输入场地类型！'
					},
					spaceCode : {
						required : '请输入场地编码！'
					},
					spaceName : {
						required : '请输入场地名称！'
					}
				}
			});
			$('#footer').hide();
		});
	</script>
</body>
</html>
