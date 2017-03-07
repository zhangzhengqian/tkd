<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>会员管理</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 会员管理</li>
        <li class="active" >消费记录</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
 		
 		
 		<div class="panel panel-default">
  			<div class="panel-body">
				<form class="form-horizontal">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">姓名:</label>
				    <div class="col-sm-10">
				      <input type="text" readonly="readonly" class="form-control" value="${vo.realName }" />
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">电话:</label>
				    <div class="col-sm-10">
				      <input type="text" readonly="readonly" class="form-control" value="${vo.phone }" />
				    </div>
				  </div>
				
				</form>
  			</div>
		</div>	
 		<div class="panel panel-default">
  			<div class="panel-body">
				<table id="contentTable" class="table table-bordered table-condensed table-hover">
					<thead class="thead">
						<tr>
	    		            <th class="text-center">场馆</th>
	    		            <th class="text-center">消费日期</th>
	    		            <th class="text-center">金额(元)</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${data.content}" var="itm" varStatus="stat">
						<tr >
							<td>${lf:spaceFullName(itm.spaceId)}</td>
        		            <td class="text-center">
        		            	<fmt:formatDate value="${itm.et }" pattern="yyyy-MM-dd HH:mm:ss"/>
        		            </td>
							<td align="center">
								${lf:formatMoney(itm.finalFee)}
							</td>
						</tr>
					</c:forEach>
					
					</tbody>		
				</table>
		
        		<tags:pagination page="${data}" />
        		<tags:errors />
        	</div>
        </div>
  </div><!-- /右侧主体内容 -->
  
        <div class="panel-footer">
	        <div class="text-right">
                <a href="${ctx }/member/customer" class="btn btn-default btn-sm">返回</a>
	        </div>
        </div>
  
</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#customer-man');
	  $('#footer').hide();
  });
	  
	function deleteById(id,realName) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/member/customer/delete/' + id);
	  bootbox.confirm('您确定要删除 [' + realName + '] 吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>

</body>
</html>
