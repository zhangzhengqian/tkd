<%@tag import="com.lc.zy.ball.boss.framework.statium.service.*"%>
<%@tag import="com.lc.zy.common.util.SpringUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="id" type="java.lang.String" required="true"%>

<% 
StatiumDetailService userService = SpringUtils.getBean(StatiumDetailService.class);
com.lc.zy.ball.domain.oa.po.StatiumDetail po = userService.selectByPrimaryKey(com.lc.zy.ball.domain.oa.po.StatiumDetail.class, id);
if(po!=null){
	out.print(po.getName());
}
%>
