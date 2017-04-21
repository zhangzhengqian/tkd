<%@tag import="com.lc.zy.ball.domain.oa.po.User"%>
<%@tag import="com.lc.zy.ball.boss.framework.system.service.UserService"%>
<%@tag import="com.lc.zy.common.util.SpringUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="field" type="java.lang.String" required="false"%>


<% 
try{
	UserService userService = SpringUtils.getBean(UserService.class);
	User user = userService.getUser(id);
	out.println(user!=null?user.getNickname():"");
}catch(Exception e){
	out.println("");
}
%>
