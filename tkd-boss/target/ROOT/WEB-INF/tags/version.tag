<%@tag import="com.lc.zy.common.util.SpringUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%
try{	
	String version = (String)SpringUtils.getBean("version");
	out.println("vsn."+version);
}catch(Exception e){
	out.println("");
}
%>
