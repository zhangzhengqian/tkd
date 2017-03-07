<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

        <input type="hidden" id="result" value="${result}"/>
		<input type="hidden" id="msg" value="${msg}"/>
	    <c:if test="${result != 0}">
		<table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
                    <th class="text-center">编号</th>
	                <th class="text-center">名称</th>
	                <th class="text-center">类型</th>
	                <th class="text-center">时间</th>
	                <th class="text-center">场地价格（元/小时）</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data}" var="statium" varStatus="status">
			<!-- spaceprice 场地价位 -->
			  <!-- 场地按时间段展示 -->
					<tr>
	                    <input type="hidden" name="orderItemEx[${status.index}].spaceId" value="${statium[0]}" />
	                    <input type="hidden" name="orderItemEx[${status.index}].waitTime" value="10" />
                       <input type="hidden" name="orderItemEx[${status.index}].strStartTime" value="${statium[4]}" />
                       <input type="hidden" name="orderItemEx[${status.index}].strEndTime" value="${statium[5]}" />
                       <input type="hidden" name="orderItemEx[${status.index}].spaceCode" value="${statium[1]}" />
                       <input type="hidden" name="orderItemEx[${status.index}].spaceName" value="${statium[2] }" />
                       <input type="hidden" name="orderItemEx[${status.index}].douPrice" value="${statium[6] }" />
                       <input type="hidden" name="orderItemEx[${status.index}].sportType" value="${statium[3] }" /> 
                       <input type="hidden" name="orderItemEx[${status.index}].strStartDate" value="${sqdate}" /> 
	                    
	                    <td class="text-center">${statium[1] }</td>
	                    <td class="text-center">${statium[2] }</td>
	                    <td class="text-center">${lf:dicItem(statium[3]).itemName }</td>
	                    <td class="text-center">${statium[4] } -- ${statium[5] }</td>
	                    <td class="text-center">${statium[6] }</td>
					</tr>
			</c:forEach>
			</tbody>		
		</table>
		</c:if>
	
	<script type="text/javascript">
		$(function() {
			if($("#result").val() == 0){
				alert($("#msg").val());
				$("#submit_btn").attr("disabled",true);
			}else{
				if($("#submit_btn").attr("disabled")){
					$("#submit_btn").attr("disabled",false);
				}
			}
		});
	</script>
</body>
</html>