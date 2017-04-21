<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>

  <body>
<!--    <form action="file/uploadImg" method="post" enctype="multipart/form-data">  
	<input type="file" name="file" /> 
	<input type="submit" value="上传图片" />
  </form> -->
  <%-- <div >
  	<form action="http://127.0.0.1:8080/store/goods/insertComment?goods_id=2&userName=ceshi&user_id=8aefe1c8213147549fef43ba271c6497&content=11111111111111&deliver_rank=5&is_show=1&order_id=1&goods_rank=5&service_rank=5&order_sn=20161209162846761525" method="post" enctype="multipart/form-data" >
            <input name="file" id="file" type="file">
             <input name="file" id="file" type="file">
            <input type="submit" value="上传图片" />
            <!-- target="form" <iframe name="form" style="display:none"></iframe> -->
     </form>
  </div>--%>
    <%--<div >
  	<form action="http://127.0.0.1:8080/store/user/insertProduce?user_id=8aefe1c8213147549fef43ba271c6497&user_name=ceshi&number=147&model=xinghao&address=address&producer=BAT" method="post" enctype="multipart/form-data" >
            <input name="file" id="file" type="file">
            <input type="submit" value="上传图片" />
            <!-- target="form" <iframe name="form" style="display:none"></iframe> -->
     </form>
  </div>--%>
  <%--
  <div >
  	<form action="http://127.0.0.1:8080/store/user/insertBusiness?company=company&person=person&applicant_person=person&operation=1&cards=412756&user_id=8aefe1c8213147549fef43ba271c6497" method="post" enctype="multipart/form-data" >
            <input name="file" id="file" type="file"><br/>
             <input name="file" id="file" type="file"><br/>
             <input name="file" id="file" type="file"><br/>
            <input type="submit" value="上传图片" />
            <!-- target="form" <iframe name="form" style="display:none"></iframe> -->
     </form>
  </div>
  --%>
  
  <%--<div >
  	<form action="http://127.0.0.1:8080/store/user/addUserShop?shop_name=shopName&describe=describe&user_id=8aefe1c8213147549fef43ba271c6497" method="post" enctype="multipart/form-data" >
            <input name="file" id="file" type="file"><br/>
            <input type="submit" value="上传图片" />
            <!-- target="form" <iframe name="form" style="display:none"></iframe> -->
     </form>
  </div>
  --%>
  <div >
  	<form action="http://127.0.0.1:8080/tkd-crm/uploadFile" method="post" enctype="multipart/form-data" >
            <input name="file" id="file" type="file"><br/>
            <input type="submit" value="上传文件" />
            <!-- target="form" <iframe name="form" style="display:none"></iframe> -->
     </form>
  </div>
  
  </body>
</html>
