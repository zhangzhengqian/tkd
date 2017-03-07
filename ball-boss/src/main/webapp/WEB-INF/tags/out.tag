<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ attribute name="len" type="java.lang.Integer" required="true"%>

<c:out value="${fn:substring(value,0,len) }" />
<c:if test="${fn:length(value) > len }">...</c:if>