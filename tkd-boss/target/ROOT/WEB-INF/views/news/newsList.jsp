<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>轮播图管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 新闻资讯管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form id="search_form" class="form-horizontal" action="${ctx}/news/list" method="post">
				  <div class="form-group form-group-sm">
					  <label class="col-md-1 control-label"></label>
					  <div class="col-md-5">
						  <input type="text" class="form-control input-sm" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}" placeholder="按名称查询">
					  </div>
					  <div class="col-md-5">
						  <select class="form-control" name="search_EQ_type" id="search_EQ_type">
							  <option value="" >--请选择类型--</option>
							 <!--  <option value="0">--国际新闻--</option> -->
							  <option value="1">--新闻资讯--</option>
							  <option value="2">--赛事活动--</option>
						  </select>
					  </div>
				  </div>

				  <div class="form-group form-group-sm query-more">
					  <lable class="control-label col-md-1 sr-only"></lable>
					  <div class="col-md-5">
						  <input type="text" class="form-control Wdate "
								 id="search_GTE_pubDate" name="search_GTE_pubDate"
								 value="${param.search_GTE_pubDate }" placeholder="发布日期-开始"
								 onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_pubDate\')||\'%y-%M-%d\'}'})">
					  </div>
					  <div class="col-md-5">
						  <input type="text" class="form-control Wdate "
								 id="search_LTE_pubDate" name="search_LTE_pubDate"
								 value="${param.search_LTE_pubDate }" placeholder="发布日期-结束"
								 onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_pubDate\')}'})">
					  </div>
				  </div>

				<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
					  &nbsp;&nbsp;
				    <button type="button" class="btn btn-link btn-sm" id="btn-query-more">
						  <span class="glyphicon glyphicon-chevron-down"></span> 更多条件
					</button>
				  </div>
				</div>
   			 </form>
			</div>
			
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
		 <div class="col-md-12 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		      <shiro:hasPermission name="news:sign">
		        <a class="btn btn-primary" href="${ctx}/news/sign?action=create" ><span class="glyphicon glyphicon-plus"></span> 新闻资讯上传</a>
		      </shiro:hasPermission>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
			
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<%--<th style="width:50px;">id</th>--%>
				<th>名称</th>
				<th>新闻类型</th>
				<th>图片</th>
				<th style="width:50px;">URL</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="news" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<%--<td style="width:5%; white-space:normal">${news.id }</td>--%>
					<td>
					${news.title }
					</td>
					<td>
					<c:if test="${news.type==0 }">国际新闻</c:if>
					<c:if test="${news.type==1 }">行业新闻</c:if>
					<c:if test="${news.type==2 }">赛事活动</c:if>
					<td>
			         	<img alt="" src="${news.image }" height="100" onclick="getOriginal('${news.image }')">
	         		</td>
					<td style="width:5%; white-space:normal">${news.url }</td>
					<td><fmt:formatDate value="${news.pubDate}" pattern="YYYY-MM-dd HH:mm"/></td>
					<td style="width:350px;vertical-align:middle">
					<shiro:hasPermission name="news:updateNews">
					  <a class="btn btn-sm btn-default" href="${ctx}/news/updateNews/${news.id }?action=edit" id="editLink-${news.id }"> <span class="glyphicon glyphicon-edit">修改</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="news:deleteNews">
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${news.id }')"> <span class="glyphicon glyphicon-edit">删除</a>
					</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
	<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>
<script type="text/javascript">
$(function() {
	  menu.active('#news-man');
	  $('#adminFooter').hide();
	  var v_search_EQ_type="${param.search_EQ_type}";
	  if(v_search_EQ_type){
	  	  $("#search_EQ_type option[value="+v_search_EQ_type+"]").attr("selected","selected");
	  }
	  $("button[type=reset]").click(function(){
		  $("#search_GTE_pubDate").attr("value","");
		  $("#search_LTE_pubDate").attr("value","");
		  $("#search_LIKE_title").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected", false);
		  $(this).closest("form").find("option:first").attr("selected",true);
	  });
});
 // 删除
function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/news/deleteNews/' + id);
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}
 
// 预览图片
function getOriginal(url){
	if(url.substring(url.length-1,url.length)=="/"){
		document.getElementById("originalPicture").src=url.substring(0,url.length-1);
	}else{
		document.getElementById("originalPicture").src=url.substring(0,url.length);
	}
		document.getElementById("originalPicture").style.display="";
	}
 
// 关闭预览
function closeLogin(){
	document.getElementById("originalPicture").style.display="none";
}
</script>

</body>
</html>