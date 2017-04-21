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
	<h4 class="modal-title" id="role_form_dlg_title">素材信息</h4>
</div>
<div class="container" style="width:800px;">
	<div class="row">
		<div class="span6" id="selContain">
			<ul class="thumbnails">
				<c:forEach items="${data.content}" var="pushVo" varStatus="stat">
				<c:if test="${stat.last }" >
					<input id="dataCount" type="hidden" name="dataCount" value="${stat.count }" />	
				</c:if>
					<li class="span4">
						<div class="thumbnail" style="width:500px;">
							<input id="sourceId" type="hidden" name="sourceId" value="${pushVo.id }" />
							<img alt="300x200" src="${pushVo.image }" />
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
							  ${pushVo.title }
							</div>
							<div>
								<p>
									<c:forEach items="${pushVo.childPush }" var="push" varStatus="stat">
									<div style="top:-30px;position: relative;">
										<hr>
										<lable style="float:left;width:380px">${push.title }</lable>
										<img alt="" src="${push.image }" style="width:100px;height:100px;display:block;margin-left: auto;"/>
									</div>
									</c:forEach>
									<button type="button" class="btn btn-default source_selected" data-dismiss="modal" data-title="${pushVo.title }" data-id="${pushVo.id }">选择</button>
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
			var title = $(this).attr('data-title');
			var groupId = $(this).attr('data-id');
			$("#title").val(title);
			$("#groupId").val(groupId);
		});
});

function loadPush(){
	var count = $('#dataCount').val();
	var listCount = 0;
	$.get('${ctx}/push/pushSource?count='+count,function(data){
		if(data!=null){
			var htmlData = "";
			var pushData = data["pushs"].content;
			listCount = pushData.length;
			if (listCount == 0){
				alert("没有更多素材");
			}
			for(var i = 0; i < pushData.length; i ++){
				htmlData = htmlData + 
				"<li class=\"span4\">" +
				"<div class=\"thumbnail\" style=\"width:500px;\">" +
					"<input id=\"sourceId\" type=\"hidden\" name=\"sourceId\" value=\"" + pushData[i].id + "\" />" +
					"<img alt=\"300x200\" src=\"" + pushData[i].image + "\" />" + 
					"<div style=\" width: 100%;height: 20px;background-color: rgba(0, 0, 0, 0.5);position: relative;top: -20px;color: floralwhite;line-height: 20px;font-size: 12px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;\">" +
					pushData[i].title +
					"</div>" +
				"<div>" + 
				"<p>";
				var push = pushData[i].childPush;
				for(var j=0; j<push.length; j ++){
					htmlData = htmlData + "<div style=\"top:-30px;position: relative;\">" +
					"<hr>" +
					"<lable style=\"float:left;width:380px\">" + push[j].title + "</lable>" +
					"<img alt=\"\" src=\"" + push[j].image + "\" style=\"width:100px;height:100px;display:block;margin-left: auto;\"/>" +
					"</div>"
				}
				
				htmlData = htmlData + "<button type=\"button\" class=\"btn btn-default source_selected\" data-dismiss=\"modal\" data-title=\"" + pushData[i].title +"\" data-id=\"" + pushData[i].id +"\">选择</button>" +
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