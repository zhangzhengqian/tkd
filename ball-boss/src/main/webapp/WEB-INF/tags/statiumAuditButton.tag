<%@tag import="com.lc.zy.ball.boss.common.Constants.UserStatus"%>
<%@tag import="com.lc.zy.ball.boss.framework.system.po.User"%>
<%@tag import="com.lc.zy.ball.boss.framework.system.service.UserService"%>
<%@tag import="com.lc.zy.common.util.SpringUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="cb" type="java.lang.String" required="false"%>

<% 
UserService userService = SpringContextHelper.getBean(UserService.class);

if(cb!=null&&!"".equals(cb)){
	User user = userService.selectByPrimaryKey(User.class, cb);
	if(UserStatus.AUDIT.equalsIgnoreCase(user.getStatus())){
%>
		<a href="${ctx }/admin/statium/audit/${cb}" class="btn btn-sm btn-primary" > <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 审核</a>

<%
	}
}
%>
