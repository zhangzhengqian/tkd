<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.lc.zy.ball.crm.common.UeditorCustomUpload"
    pageEncoding="UTF-8"%>

<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	out.write( new UeditorCustomUpload( request, rootPath ).exec() );
	
%>