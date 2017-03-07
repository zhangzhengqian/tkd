<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>运营管理/推送管理</title>
</head>

<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title" id="role_form_dlg_title">赛事信息</h4>
</div>
<div class="container" style="width:800px;">
	<div class="row">
		<div class="span6" id="selContain">
			<ul class="thumbnails">
				<c:forEach items="${data.content}" var="event" varStatus="stat">
				<c:if test="${stat.last }" >
					<input id="dataCount" type="hidden" name="dataCount" value="${stat.count }" />	
					<input type="hidden" id="areaCode" name="areaCode" value="${event.areaCode }">
				</c:if>
					<li class="span4">
						<div class="thumbnail" style="width:500px;">
							<input id="sourceId" type="hidden" name="sourceId" value="${event.id }" />
							<img alt="300x200" src="${event.showLogo }" />
							<div style=" width: 100%;
							  height: 20px;
							  background-color: rgba(0, 0, 0, 0.5);
							  position: relative;
							  top: -20px;
							  color: floralwhite;
							  line-height: 20px;
							  font-size: 12px;
							  text-overflow: ellipsis;
							  white-space: nowrap;
							  overflow: hidden;">
							  ${event.name }
							</div>
							<div>
								<p>
									<button type="button" class="btn btn-default source_selected" data-dismiss="modal" data-title="${event.name }" data-id="${event.id }">选择</button>
								</p>
								
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			
		</div>
	</div>
</div>
<div style="text-align:center;">
	<a href="javascript:void(0);" onclick="loadPush();">加载更多</a>
</div>		 

<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	  menu.active('#push-man');
	  
	  $("#selContain").on("click",".source_selected",function(){
			var name = $(this).attr('data-title');
			var id = $(this).attr('data-id');
			$("#eventName").val(name);
			$("#eventId").val(id);
		});
});

function loadPush(){
	var count = $('#dataCount').val();
	var areaCode = $("#areaCode").val();
	var listCount = 0;
	$.get('${ctx}/push/pushEvent?count='+count + '&areaCode=' + areaCode,function(data){
		if(data!=null){
			var htmlData = "";
			var pushData = data["pushs"].content;
			listCount = pushData.length;
			if (listCount == 0){
				alert("没有更多赛事");
			}
			for(var i = 0; i < pushData.length; i ++){
				htmlData = htmlData + 
				"<li class=\"span4\">" +
				"<div class=\"thumbnail\" style=\"width:500px;\">" +
					"<input id=\"sourceId\" type=\"hidden\" name=\"sourceId\" value=\"" + pushData[i].id + "\" />" +
					"<img alt=\"300x200\" src=\"" + pushData[i].showLogo + "\" />" + 
					"<div style=\" width: 100%;height: 20px;background-color: rgba(0, 0, 0, 0.5);position: relative;top: -20px;color: floralwhite;line-height: 20px;font-size: 12px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;\">" +
					pushData[i].name +
					"</div>" +
				"<div>" + 
				"<p>";
				htmlData = htmlData + "<button type=\"button\" class=\"btn btn-default source_selected\" data-dismiss=\"modal\" data-title=\"" + pushData[i].name +"\" data-id=\"" + pushData[i].id +"\">选择</button>" +
				"</p>" +
				"</div>" +
				"</div>" +
				"</li>";
			}
			$(".thumbnails").append(htmlData);
			$('#dataCount').val(Number(count) + Number(listCount));
    	}
	},"json")
}

</script>

</body>
</html>