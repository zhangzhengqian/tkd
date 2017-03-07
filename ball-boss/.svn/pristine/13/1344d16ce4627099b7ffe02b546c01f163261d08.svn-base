<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>预约情况</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 预约管理</li>
        <li class="active">预约情况</li>
    </ul>
  </div><!-- / 右侧标题 -->

	<% request.setAttribute("status",com.lc.zy.ball.boss.common.SessionUtil.currentUser().getStatus());	%>
    <c:if test="${'UNPASS' eq status }" >
  	<div class="panel-heading"><!-- 右侧标题 -->
  	
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1" align="center">
				<h3>
					<a href="${ctx }/member/org/companyForm">资料没有通过审核，请按照客服提示进行修改，并提交审核</a>
				</h3>
			</div>
		</div>
	</div>	
 	</c:if>  
 
<c:if test="${ 'USER_ENABLE' eq status }" >
    <c:set var="mySportTypes" value="${lf:mySportTypes() }" />
    <div class="tab-content panel-body">
        <div class="tab-pane active">
            <form class="form-inline" id="searchForm">
                  <div class="form-group form-group-sm">
                    <label class="sr-only" for="search_EQ_startDate">选择日期：</label>
                    <input placeholder="今天" value="${param.search_EQ_startDate }" name="search_EQ_startDate" id="startDate" class="form-control" data-date-format="yyyy-mm-dd">
	                <select name="search_EQ_sportType" id="sportType" class="form-control input-sm">
	                       <c:forEach items="${mySportTypes }" var="item">
	                           <option value="${item.itemCode }" <c:if test="${param.search_EQ_sportType eq item.itemCode }">selected</c:if> > ${item.itemName }
	                       </c:forEach>
                    </select>
                    <a href="javascript:void(0);" id="createOrderDialog" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 预约</a>  
                  </div>
                </form>
            <div class="timeline mt20">
                  <div class="cells">场地</div>
                  <div class="cells"></div>
                  <div class="cells"><strong>02:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>04:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>06:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>08:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>10:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>12:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>14:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>16:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>18:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>20:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>22:00</strong></div>
                  <div class="cells"></div>
                  <div class="cells"><strong>24:00</strong></div>
                </div>
                
<c:set var="sportType" value="${param.search_EQ_sportType }" />
<c:if test="${empty param.search_EQ_sportType }">
    <c:set var="sportType" value="${mySportTypes[0].itemCode }" />
</c:if>
<c:forEach items="${lf:mySpaces(sportType) }" var="space">
                <div class="ground">
                  <div title="${space.spaceCode }" class="cells" style="table-layout:fixed; word-break: break-all; overflow:hidden;line-height:20px;">${space.spaceCode }</div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <div class="cells">
                      <span></span>
                      <span></span>
                    </div>
                    <c:forEach items="${lf:orderItems(param.search_EQ_startDate, space.id) }" var="orderItem">
                   <!--  <a href="${ctx }/member/order/view/${orderItem.id}" class="segment" -->
                   		 <a href="#" class="segment"
                    style="left: ${lf:minuteOfDay(orderItem.startTime)/15 }%; width: ${((orderItem.endTime - orderItem.startTime))/60/15}%" 
                    id="segment3" data-toggle="tooltip" data-placement="right" 
                    <jsp:useBean id="startTime" class="java.util.Date"/>
                    <jsp:setProperty name="startTime" property="time" value="${orderItem.startTime * 1000}"/>
                    <jsp:useBean id="endTime" class="java.util.Date"/>
                    <jsp:setProperty name="endTime" property="time" value="${orderItem.endTime * 1000}"/>
					
                    title="<fmt:formatDate value="${startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${endTime}" pattern="HH:mm"/>"></a>
                    </c:forEach>
                </div> 
</c:forEach>                
        </div><!--/tab-pane -->
      </div><!-- /nav-tabs -->  
      

</c:if>
</div>
        <tags:errors />

<script type="text/javascript">
	$(function() {
		//如果查询场地列表为空，给予提示
		var userStatiumNum = '${userStatiumNum}';
		$("#createOrderDialog").click(function(){
			if(userStatiumNum && userStatiumNum == 0){
				alert("场馆列表为空，请完善用户场馆信息！");
				return;
			}
			
			location.href = "${ctx }/member/order/create";
		});
		menu.active('#order-man');
		
		$('[data-toggle="tooltip"]').tooltip();
		
        $("#startDate").focus(function() {
            WdatePicker({onpicked:function(){
            	$("#searchForm").submit();
            }});
        });		
		
		$('#startDate, #sportType').change(function() {
			$("#searchForm").submit();
		});
		$('#footer').hide();
		
		if(userStatiumNum && userStatiumNum == 0){
			alert("场馆列表为空，请完善用户场馆信息！");
		}
	});

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
