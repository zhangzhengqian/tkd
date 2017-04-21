<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
//System.out.println("=================================== page : " + pageContext.getAttribute("message")) ;
//System.out.println("=================================== request : " + request.getAttribute("message")) ;
//System.out.println("=================================== session : " + session.getAttribute("message")) ;
//System.out.println("=================================== application : " + application.getAttribute("message")) ;

String msg = (String)request.getAttribute("message");
if ( msg != null && !msg.startsWith("\"") && !msg.startsWith("'") && !msg.startsWith("{") ) {
	msg = "'" + msg + "'";
	request.setAttribute("message", msg);
}
%>

<c:if test="${not empty message}">
<script type="text/javascript">

$(function() {
	
	var msg = ${message};
	if (!$.isPlainObject(msg)) {
		var content = msg;
		msg = {};
		msg.result = 1;
		msg.content = content;
	}
	
	if (msg.result) {
		common.showMessage(msg.content);
	} else {
		common.showMessage(msg.content, 'warn');
	}

});
</script>
</c:if>

