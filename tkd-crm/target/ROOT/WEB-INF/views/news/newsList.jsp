<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<form id="search_form" class="form-horizontal" action="${ctx}/localNews/list" method="post">
<div class="orderSearch">
		 <ul>
            <li class="timeLi subSearchLi1">
                <span>按名称查询</span>
                <input style="width: 200px" type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }">
            </li>

            <li class="subSearch subSearchLi1">
                <a class="reset" type="reset" href="javascript:reset()">重置</a>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardUserSubmit()" >查询</a>
            </li>
            <li class="subSearch subSearchLi">
                <a class="submit" href="${ctx }/localNews/sign?action=create">添加新闻</a>
            </li>

        </ul>
	</div>
	</form>		
    	<div class="orderResult" >		
		<table id="" class="">
			<tr>
				<th class="text-center">序号</th>
				<%--<th style="width:50px;">id</th>--%>
				<th>名称</th>
				<th>图片</th>
				<th style="width:50px;">URL</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
			
			
			<c:forEach items="${data.content}" var="news" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<%--<td style="width:5%; white-space:normal">${news.id }</td>--%>
					<td>
					<a href="${ctx}/localNews/updateNews/${news.id }">${news.title }</a>
					</td>
					<td>
			         	<img alt="" src="${news.image }" height="100" onclick="getOriginal('${news.image }')">
	         		</td>
					<td style="width:5%; white-space:normal">${news.url }</td>
					<td><fmt:formatDate value="${news.ct}" pattern="YYYY-MM-dd HH:mm"/></td>
					<td style="width:350px;vertical-align:middle">
					  <a class="btn btn-sm btn-default" href="${ctx}/localNews/updateNews/${news.id }?action=edit" id="editLink-${news.id }"> <span class="glyphicon glyphicon-edit">修改</a>
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${news.id }')"> <span class="glyphicon glyphicon-edit">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />
		<tags:errors />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    

	<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>
<script type="text/javascript">
$(function() {
		$('#news-man').addClass("active");
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

function reset(){
	$("#search_LIKE_title").val("");
}

function cardUserSubmit(){
	$("#search_form").submit();
}



 // 删除
function deleteById(id) {
	{
		swal({
			title: "",
			text: "您确定删除新闻？",
			type: "warning",
			showCancelButton: "true",
			showConfirmButton: "true",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			animation: "slide-from-top"
		}, function () {
			$('#loading').show();
			$.ajax({
				url : "${ctx }/localNews/deleteNews",
				method : "POST",
				data : {"id" : id},
				dataType: 'json',
				success: function(data){
					$('#loading').hide();
					if(data.result=='success'){
						swal({
							title: "提示",
							text: "新闻删除成功",
							showConfirmButton: "true",
							confirmButtonText: "确定",
							animation: "slide-from-top"
						}, function () {
							location.href = "${ctx }/localNews/list";
						})
					} else {
						swal({
							title: "警告",
							text: data.data
						})
					}
				}
			});
		})
	}
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