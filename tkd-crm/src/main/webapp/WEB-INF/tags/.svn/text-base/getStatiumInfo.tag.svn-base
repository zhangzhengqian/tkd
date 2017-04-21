<%@tag import="com.lc.zy.ball.crm.framework.system.statium.service.StatiumService"%>
<%@ tag import="com.lc.zy.common.util.SpringUtils" %>
<%@ tag import="com.lc.zy.ball.domain.oa.po.StatiumInfos" %>
<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="statiumId" type="java.lang.String" required="false"%>
<%@ attribute name="field" type="java.lang.String" required="false"%>


<%
    try{
        StatiumService statiumService = SpringUtils.getBean(StatiumService.class);
        StatiumInfos statiumInfos = statiumService.getStatiumInfoById(statiumId);
        out.println(statiumInfos!=null?statiumInfos.getDgName():"");
    }catch(Exception e){
        out.println("");
    }
%>
