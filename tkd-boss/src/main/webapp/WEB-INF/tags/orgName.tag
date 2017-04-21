<%@tag import="com.lc.zy.ball.boss.common.service.CommonService"%>
<%@tag import="com.lc.zy.common.util.SpringUtils"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="orgCode" type="java.lang.String" required="true"%>
<%
CommonService commonService = SpringUtils.getBean(CommonService.class);
String orgName = commonService.getOrgNameByCode(orgCode);
out.println(orgName);
%>
