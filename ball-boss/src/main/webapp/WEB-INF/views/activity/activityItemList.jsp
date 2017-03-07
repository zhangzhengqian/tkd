<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">活动控制</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="row">
    	<div class="col-table col-md-12" >
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">序号</th>
					<th>活动日期</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${itemList }" var="item" varStatus="stat">
					<tr>
						<td style="text-align: center;">${stat.count }</td>
						<td style="text-align: center;">
							<fmt:formatDate value="${item.date }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td style="text-align: center;">
							<c:if test="${item.state == 0 }">
								可用
							</c:if>
							<c:if test="${item.state == 1 }">
								不可用
							</c:if>
						</td>
						<td style="text-align: center;">
							<c:if test="${item.state == 0}">
								<a href="javascript:void(0)" data="${item.date }" onclick="editState('${item.id }')"> 隐藏</a>
							</c:if>
							<c:if test="${item.state == 1}">
								<a href="javascript:void(0)" data="${item.date }" onclick="editState('${item.id }')"> 激活</a>
							</c:if>
							<span class="cutline"></span>					   	
						</td>
					</tr>
				</c:forEach>
				</tbody>		
			</table>
		 </div><!-- end col-table -->
	</div><!-- end row -->
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
  $(function() {
	  menu.active('#activity-man');
	  $('#adminFooter').hide();
  });
  
	function editState(itemId){
	  	$.ajax({
	        cache: true,
	        type: "POST",
	        url:'${ctx}/activity/itemState/' + itemId,
	        dataType:'json',
	        error: function(request) {
	        	common.showMessage("更新活动详情状态失败！");
	        },
	        success: function(data) {
	        	common.showMessage(data.desc);
	        	location.reload();
	        }
	    });
	}
</script>
</body>
</html>