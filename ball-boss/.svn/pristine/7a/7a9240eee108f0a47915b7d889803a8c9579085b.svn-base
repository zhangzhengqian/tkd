<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>预约情况</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 场馆管理</li>
        <li class="active">场地管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
  		<div class="row mtb10"><!-- 查询条件 -->
		<div class="col-md-10">
	      <form class="form-inline" action="${ctx }/statium/space">
	           <input type="hidden" name="search_EQ_sportType" id="search_EQ_sportType" value="${search_EQ_sportType}"/>
	           <label class="sr-only" for="search_EQ_startDate">选择日期：</label>
               <input placeholder="今天" value="${param.search_EQ_startDate }" name="search_EQ_startDate" id="startDate" class="form-control" readOnly>
              <!--  <div class="form-group">
                 <button type="button" id="search" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
               </div> -->
               
               <ul style="float:right;list-style: none;">
               		<li style="float:left;width:80px;line-height: 20px;vertical-align: middle;">新订单:<div style="width:20px;height:20px;background-color:#ccc;display:inline-block;"></div></li>
               		<li style="float:left;width:80px;line-height: 20px;vertical-align: middle;">已完成:<div style="width:20px;height:20px;background-color:#5cb85c;display:inline-block;"></div></li>
               		<li style="float:left;width:80px;line-height: 20px;vertical-align: middle;">已支付:<div style="width:20px;height:20px;background-color:#337ab7;display:inline-block;"></div></li>
               		<li style="float:left;width:80px;line-height: 20px;vertical-align: middle;">已确认:<div style="width:20px;height:20px;background-color:#5bc0de;display:inline-block;padding-top:2px;"></div></li>
               </ul>
	      </form>
		</div>
	</div><!-- /查询条件 -->
  
  	<div>
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
		<c:forEach items="${fn:split(sportTypes,';;') }" var="item">
			<li role="presentation" <c:if test="${item == sportType}">class="active"</c:if> ><a href="#${item}" aria-controls="${item}" role="tab" data-toggle="tab">${lf:dicItem(item).itemName}</a></li>
		</c:forEach>
	  </ul>
	  <!-- Tab panes -->
	  <div class="tab-content">
  		   <div role="tabpanel" id="usageList" class="tab-pane active" id="${sportType}">
  			
  		   </div>
	  </div>
	</div>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  $("#startDate").focus(function() {
          WdatePicker({		
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			onpicked:function(){
				$("li[role='presentation']").each(function(){
					  if($(this).attr("class") == "active"){
						  data['sportType'] = $(this).find("a").attr("aria-controls");
						  data['startDate'] = $("#startDate").val();
						  //alert(JSON.stringify(data));
						  usage(data);
					  }
				  });
			}
          });
      });
	  
	  menu.active('#statium-man');
	  var data = {"statiumId":"${statiumId}"};
	  <c:if test="${not empty sportType}">
	  	data['sportType'] = "${sportType}";
	  </c:if>
	  usage(data,function(){
		  $("li[role=presentation]").find("a").click(function(){
			  var sportType = $(this).attr("aria-controls");
			  usage({"sportType":sportType,"statiumId":"${statiumId}"});
		  });
	  });
	  
	  $("#search").click(function(){
		  $("li[role='presentation']").each(function(){
			  if($(this).attr("class") == "active"){
				  data['sportType'] = $(this).find("a").attr("aria-controls");
				  data['startDate'] = $("#startDate").val();
				  //alert(JSON.stringify(data));
				  usage(data);
			  }
		  });
	  });
  });
  
  function usage(data,cb){
	  $.commLoading.show();
	  $("#usageList").load("${ctx }/statium/space/usageList_dlg",data,function(){
		  $.commLoading.hide();
		  cb.call(this);
	  });
  }
  
</script>
</body>
</html>