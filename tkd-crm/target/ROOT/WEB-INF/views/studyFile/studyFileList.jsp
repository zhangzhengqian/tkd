<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body >

<form  id="studyFileFrom" class="form-horizontal" action="${ctx }/studyFile/list" method="post" name="id">
    <div class="orderSearch myVipOrderSearch">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>文件名称</span>
                <input style="width: 200px" type="text" id="search_LIKE_fileName" name="search_LIKE_fileName" value="${param.search_LIKE_fileName }">
            </li>
            <li class="subSearch subSearchLi1">
                <a class="reset" type="reset" href="javascript:reset()">重置</a>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardUserSubmit()" >查询</a>
            </li>
            <li class="subSearch subSearchLi">
            </li>
            <li class="subSearch subSearchLi">
                <a class="submit" href="${ctx }/studyFile/createForm">添加学习资料</a>
            </li>
        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th class="text-center">序号</th>
			<th>文件名称</th>
			<th>上传人</th>
			<th>上传时间</th>
			<th>操作</th>
        </tr>

        <c:forEach items="${data.content}" var="study" varStatus="stat">
				<tr>
					<input type="hidden" id="id" value="${study.id }"/>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${study.fileName}</td>
					<td style="text-align: center;">${study.statiumName}</td>
					<td style="text-align: center;"><fmt:formatDate value="${study.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">
					  	<a href="#" data="${study.id }"  onclick="deleteById('${study.id}')"> 删除</a>
					  	<a href="${study.fileUrl }"  > 下载</a>
					</td>
				</tr>
			</c:forEach>
    </table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />
<form id="actionForm" action="" method="post">
    <input type="hidden" id="id" name="id">
</form>
<script src="${ctx}/static/lib/reset.js"></script>
<script type="text/javascript">
    $(function() {
        // 样式
        $('#study-man').addClass("active");
        $('#STUDY_FILE').addClass("active");
    });

    // 查询
    function cardUserSubmit() {
        $('#studyFileFrom').submit();
    }
	
    //重置
    function reset(){
    	$("#search_LIKE_fileName").val("");
    }
    
	//删除
	function deleteById(id){
		console.log(id);
		var tip = confirm("确定删除吗？");
		var $form =$("#actionForm");
		$form.attr("action","${ctx}/studyFile/delFile/"+id);
		if(tip){
			$form[0].submit();
		}
		/* bootbox.confirm("您确定要删除吗",function(result){
			if(result){
				$form[0].submit();
			}
		}); */
	}
	
</script>

</body>
</html>