<%@tag import="com.lc.zy.ball.boss.common.Zonemap"%>
<%@tag pageEncoding="UTF-8"%>

<%@ attribute name="code" type="java.lang.String" required="true"%>

<%
if(code!=null&&!"".equals(code)){
	String c2 = code.substring(0, 4) + "00";
	String c1 = code.substring(0, 2) + "0000";
	
	StringBuffer sb = new StringBuffer(Zonemap.dict.get(c1));
	if(!code.substring(0, 4).endsWith("00")){
		sb.append("-").append(Zonemap.dict.get(c2));
		if(!code.endsWith("00")){
			sb.append("-").append(Zonemap.dict.get(code));
		}
	}
	out.println(sb.toString());
}else{
	out.println("");
}

%>
