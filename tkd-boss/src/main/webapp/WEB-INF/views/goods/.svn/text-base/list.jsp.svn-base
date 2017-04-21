<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票主题</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 商品管理</li>
				<li class="active">列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<div class="col-table col-md-12">
					<a class="btn btn-primary btn-sm pull-right" href="${ctx}/goods/add"><span class="glyphicon glyphicon-plus"></span> 新增商品</a>
				</div>
			</div>
			<hr>
<div class="row">
    <c:forEach items="${data }" var="good">
	<div class="col-sm-6 col-md-3">
        <div class="thumbnail" style="height:350px;">
            <img src="${good.photo }bigPicture" style="width: 160px;height: 120px" alt="通用的占位符缩略图">
            <div class="caption">
                <h3>${good.name}</h3>
                <p>整${good.unit}(${good.bulk}*${good.amount})</p>
                <p>￥${good.feeView }</p>
                <p>
                    <a href="${ctx }/goods/update/${good.id}" class="btn btn-primary">
                        修改
                    </a>
                    <a href="${ctx }/goods/delete/${good.id}" class="btn btn-default">
                        删除
                    </a>
                    <c:if test="${good.status==0 }">
                    	                    <a href="${ctx }/goods/set/${good.id}/1" class="btn btn-warning">
                        下架
                    </a>
                    </c:if>
                    <c:if test="${good.status==1 }">
                    	                    <a href="${ctx }/goods/set/${good.id}/0" class="btn btn-warning">
                        上架
                    </a>
                    </c:if>
                </p>
            </div>
        </div>
    </div>
</c:forEach>
</div>
			<!-- end row -->
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#goods');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>