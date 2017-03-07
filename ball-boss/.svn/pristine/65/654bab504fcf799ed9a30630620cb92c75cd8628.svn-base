<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单管理</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home active"></span> 订单管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline">
		        <div class="form-group">
		          <label class="sr-only" for="search_EQ_startDate">订单日期：</label>
		          <input class="form-control input-sm" type="text" name="search_EQ_startDate" id="search_EQ_startDate" value="${param.search_EQ_startDate }" data-date-format="yyyy-mm-dd" placeholder="预约日期" />
		        </div>
                <div class="form-group">
                  <label class="sr-only" for="search_EQ_phone">联系电话：</label>
                  <input class="form-control input-sm" type="text" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="联系电话" />
                </div>
		        <div class="form-group">
                  <label class="sr-only" for="search_EQ_sportType">类型：</label>
                    <c:set var="mySportTypes" value="${lf:dicItems('SPORT_TYPE') }" />
			          <select name="search_EQ_sportType" class="form-control input-sm">
			               <option value="" >所有类型</option>
			               <c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
			                   <option value="${item.itemCode }"  <c:if test="${param.search_EQ_sportType eq item.itemCode }">selected</c:if> > ${item.itemName }</option>
			               </c:forEach>
			          </select>
		        </div>
		        <div class="form-group">
                  <label class="sr-only" for="search_EQ_status">状态：</label>
			          <select name="search_EQ_status" class="form-control input-sm">
			               <option value="" >所有状态</option>
		                   <option value="ORDER_NEW" <c:if test="${param.search_EQ_status == 'ORDER_NEW'}">selected</c:if>>新订单</option>
		                   <option value="ORDER_PLAYING" <c:if test="${param.search_EQ_status == 'ORDER_PLAYING'}">selected</c:if>>已开场</option>
		                   <option value="ORDER_CANCELED" <c:if test="${param.search_EQ_status == 'ORDER_CANCELED'}">selected</c:if>>已撤销</option>
		                   <option value="ORDER_BILLED" <c:if test="${param.search_EQ_status == 'ORDER_BILLED'}">selected</c:if>>已完成</option>
		                   <option value="ORDER_DELETED" <c:if test="${param.search_EQ_status == 'ORDER_DELETED'}">selected</c:if>>已删除</option>
		                   <option value="ORDER_PAIED" <c:if test="${param.search_EQ_status == 'ORDER_PAIED'}">selected</c:if>>已支付</option>
			          </select>
		        </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	    
		<table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">订单号</th>
	                <th class="text-center">场地名称</th>
	                <th class="text-center">场地类型</th>
	                <th class="text-center">场地电话</th>
	                <th class="text-center">预约日期</th>
	                <th class="text-center">会员姓名</th>
	                <th class="text-center">联系电话</th>
                    <th class="text-center">消费金额</th>
                    <th class="text-center">实付金额</th>
                    <th class="text-center">打折返现</th>
                    <th class="text-center">优惠券金额</th>
                    <th class="text-center">下单时间</th>
                    <th class="text-center">订单状态</th>
                    <th class="text-center">支付方式</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="order">
				<tr>
					<td class="text-center">${order.id }</td>
					<td class="text-center">${order.name }</td>
					<td class="text-center">
						<c:choose>
                    		<c:when test="${order.sportType == 'BASKETBALL'}">篮球</c:when>
                    		<c:when test="${order.sportType == 'FOOTBALL'}">足球</c:when>
                    		<c:when test="${order.sportType == 'BADMINTON'}">羽毛球</c:when>
                    		<c:when test="${order.sportType == 'BILLIARDS'}">台球</c:when>
                    		<c:when test="${order.sportType == 'BOWLING'}">保龄球</c:when>
                    		<c:when test="${order.sportType == 'GOLF'}">高尔夫</c:when>
                    		<c:when test="${order.sportType == 'TABLE_TENNIS'}">乒乓球</c:when>
                    		<c:when test="${order.sportType == 'TENNIS'}">网球</c:when>
                    	</c:choose></td>
                   	<td class="text-center">${order.masterTel}</td>
                    <td class="text-center">
                   	 <fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td class="text-center">${order.realName }</td>
                    <td class="text-center">${order.phone }</td>
                    <td class="text-center">${lf:formatMoney(order.fee) }</td>
                    <c:choose>
                    		<c:when test="${order.qiuyouRating != null && order.qiuyouRating != 0.0}">
                    		<c:set var="paid" value="${order.fee * order.qiuyouRating}" />
                    		</c:when>
                    		<c:otherwise>
                    		<c:set var="paid" value="${order.fee}" />
                    		</c:otherwise>
                    </c:choose>		
                    
                    <c:set var="discount" value="${order.fee - paid}" />
                    <%-- <c:if test="${order.subAmount != null}">
                    	<c:set var="pay" value="${paid - order.subAmount*100}" />
                    </c:if>
                    <c:if test="${order.subAmount == null}">
     					<c:set var="pay" value="${paid}"/>               
                    </c:if> --%>
                     <c:if test="${order.finalFee == null}">
     					<c:set var="pay" value="${order.fee}"/>               
                     </c:if> 
                     <c:if test="${order.finalFee != null}">
     					<c:set var="pay" value="${order.finalFee}"/>               
                     </c:if>
                    <td class="text-center">${lf:formatMoney(pay)}</td>
                    <td class="text-center">${lf:formatMoney(discount)}</td>
                    <td class="text-center">${order.subAmount}</td>
                    <td class="text-center">
                   	 <fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td class="text-center">
                    	<c:choose>
                    		<c:when test="${order.status == 'ORDER_NEW'}">新订单</c:when>
                    		<c:when test="${order.status == 'ORDER_PLAYING'}">已开场</c:when>
                    		<c:when test="${order.status == 'ORDER_CANCELED'}">已撤销</c:when>
                    		<c:when test="${order.status == 'ORDER_BILLED'}">已完成</c:when>
                    		<c:when test="${order.status == 'ORDER_DELETED'}">已删除</c:when>
                    		<c:when test="${order.status == 'ORDER_PAIED'}">已支付</c:when>
                    	</c:choose>
                    </td>                    
                    <td class="text-center">
                    	<c:choose>
                    		<c:when test="${order.payType == '1'}">支付宝付款</c:when>
                    		<c:when test="${order.payType == '2'}">微信付款</c:when>
                    		<c:when test="${order.payType == '3'}">公众平台付款</c:when>
                    		<c:when test="${order.payType == '4'}">球友圈付款</c:when>
                    		<c:when test="${order.payType == null}"></c:when>
                    	</c:choose>
                    </td>
                    <td class="text-center">
                        <a href="${ctx }/admin/order/view/${order.id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 详情</a>
                    </td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		
        <tags:pagination page="${data}" />
        <tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">

 
	$(function() {
		menu.active('#order-manager-item');
		$("#adminFooter").hide();
        $("#search_EQ_startDate").focus(function() {
            WdatePicker({		
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
            });
        });
	});

	function myrefresh() 
	{ 
	       window.location.reload(); 
	} 
	setTimeout('myrefresh()',300000); //指定1秒刷新一次 
	
	function deleteById(id) {
		var $form = $('#actionForm');
		$form.attr('action', '${ctx}/admin/space/delete/' + id);
		//bootbox.setDefaults({size:'large'});
		bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data')
				+ '] 吗？', function(result) {
			if (result) {
				$form[0].submit();
			}
		});
		return false;
	}
</script>

</body>
</html>
