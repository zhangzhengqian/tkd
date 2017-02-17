<%@tag import="com.lc.zy.ball.crm.framework.system.statium.service.StatiumService"%>
<%@ tag import="com.lc.zy.common.util.SpringUtils" %>
<%@ tag import="com.lc.zy.ball.domain.oa.po.StatiumClass" %>
<%@ tag import="com.lc.zy.ball.domain.oa.po.StatiumClass" %>
<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="statiumId" type="java.lang.String" required="false"%>
<%@ attribute name="field" type="java.lang.String" required="false"%>


<%
    try{
        StatiumService statiumService = SpringUtils.getBean(StatiumService.class);
        List<StatiumClass> list = statiumService.getClass(statiumId);
        out.println(list!=null?list:null);
    }catch(Exception e){
        out.println("");
    }
%>
