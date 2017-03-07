<%@ tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="page" type="com.lc.zy.ball.common.data.pageable.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="false"%>

<%-- 分页按钮点击回调方法 --%>
<%@ attribute name="callback" type="java.lang.String" required="false"%>

<%
if (paginationSize == null) {
	paginationSize = 8;
}
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
int size = page.getSize();

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("size", size);
request.setAttribute("callback", callback);
%>

<div class="row"><!-- 分页 -->
  <div class="col-md-12">
  
  
	<ul class="list-inline pagination-info mtb10" style="width:150px;">
	  <li><small>共<strong class="text-danger"><%=page.getTotalPages() %></strong>页, <strong class="text-danger"><%=page.getTotalElements() %></strong>条</span></small></li>
	</ul>
	
	<c:choose>
	
	<c:when test="${empty callback }">
		<div class="btn-group dropup mtb10" style="margin-left: 15px;">
		  <button class="btn btn-sm btn-default dropdown-toggle" type="button" id="pageSizeMenu" data-toggle="dropdown">
		                每页${size}条  <span class="caret caret-sm"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu" aria-labelledby="pageSizeMenu">
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=10&${searchParams}">10</a></li>
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=15&${searchParams}">15</a></li>
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=20&${searchParams}">20</a></li>
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=30&${searchParams}">30</a></li>
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=50&${searchParams}">50</a></li>
		    <li role="presentation"><a role="menuitem" tabindex="-1" href="?page=1&size=100&${searchParams}">100</a></li>
		  </ul>
		</div>

		<ul class="pagination pagination-sm pull-left">
		<% if (page.hasPreviousPage()){%>
		 <li><a href="?page=${current-1}&size=${size}&${searchParams}">上一页</a></li>
		 <li><a href="?page=1&size=${size}&${searchParams}"><span class="glyphicon glyphicon-step-backward"></span> </a></li>
		<%}else{%>
		 <li class="disabled"><a href="#">上一页</a></li>
		 <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-backward"></span> </a></li>
		<%} %>
		
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
			    <c:when test="${i == current}">
			        <li class="active"><a href="?page=${i}&size=${size}&${searchParams}">${i}</a></li>
			    </c:when>
			    <c:otherwise>
			        <li><a href="?page=${i}&size=${size}&${searchParams}">${i}</a></li>
			    </c:otherwise>
			</c:choose>
		 </c:forEach>
		  
		<% if (page.hasNextPage()){%>
	     <li><a href="?page=${page.totalPages}&size=${size}&${searchParams}"><span class="glyphicon glyphicon-step-forward"></span> </a></li>
	     <li><a href="?page=${current+1}&size=${size}&${searchParams}">下一页</a></li>
		<%}else{%>
	     <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-forward"></span> </a></li>
	     <li class="disabled"><a href="#">下一页</a></li>
	     
		<%} %>
	
		</ul>
	
	</c:when>
	
	<c:otherwise>
        <ul class="pagination pagination-sm pull-left">
        <% if (page.hasPreviousPage()){%>
         <li><a href="#" onclick="${callback}('page=${current-1}&size=${size}&${searchParams}')">上一页</a></li>
         <li><a href="#" onclick="${callback}('page=1&size=${size}&${searchParams}')"><span class="glyphicon glyphicon-step-backward"></span> </a></li>
        <%}else{%>
         <li class="disabled"><a href="#">上一页</a></li>
         <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-backward"></span> </a></li>
        <%} %>
        
        <c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="#" onclick="${callback}('page=${i}&size=${size}&${searchParams}')">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="#" onclick="${callback}('page=${i}&size=${size}&${searchParams}')">${i}</a></li>
                </c:otherwise>
            </c:choose>
         </c:forEach>
          
        <% if (page.hasNextPage()){%>
         <li><a href="#" onclick="${callback}('page=${page.totalPages}&size=${size}&${searchParams}')"><span class="glyphicon glyphicon-step-forward"></span> </a></li>
         <li><a href="#" onclick="${callback}('page=${current+1}&size=${size}&${searchParams}')">下一页</a></li>
        <%}else{%>
         <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-forward"></span> </a></li>
         <li class="disabled"><a href="#">下一页</a></li>
         
        <%} %>
    
        </ul>	
	</c:otherwise>
	
	</c:choose>


	</div>
</div><!-- /分页 -->

