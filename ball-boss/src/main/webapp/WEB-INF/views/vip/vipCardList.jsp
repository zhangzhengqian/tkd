<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球馆会员卡</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">球馆会员卡</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
 	<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="vipForm" class="form-horizontal" action="${ctx}/vip" method="post">
      			<div class="form-group form-group-sm">
      				<label class="col-md-1 control-label"></label>
					<div class="col-md-5">
						<input type="text" class="form-control input-sm" id="search_LIKE_statiumName" name="search_LIKE_statiumName" value="${param.search_LIKE_statiumName}" placeholder="按场馆名称查询">
						<input  type="hidden" class="form-control input-sm" id="statiumId"  name="search_EQ_statiumId"  value="${param.search_EQ_statiumId }">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				&nbsp;&nbsp;
						<button type="button" id="export_btn" class="btn btn-default btn-sm">
							<span class="glyphicon"></span> 导出Excel
						</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-primary" href="${ctx}/vip/createForm" ><span class="glyphicon glyphicon-plus"></span> 新增会员卡</a>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->		 
  
	<div class="row">
		<div class="col-md-12" style="margin-bottom: 10px;">
		    <div class="btn-group" role="group" aria-label="...">
		    	  <c:forEach items="${activityItemList }" var="item" varStatus="stat">
				  	  <button id="${item.id }" value="${item.date }" type="button" onclick="active_btn(this)"
				  	  		class="btn btn-default btn-sm <c:if test='${item.date == activityItem.date }'>active</c:if>">
				  	  <span class="glyphicon" aria-hidden="true"></span>
				  	  ${item.date }</button>
		    	  </c:forEach>
			</div>
	    </div>
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>场馆名称</th>
				<th>会员卡号</th>
				<th>卡名</th>
				<th>面额（元）</th>
				<th>余额（元）</th>
				<th>支出</th>
				<th>收入</th>
				<th>盈利</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="vip" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${vip.statiumName }</td>
					<td style="text-align: center;">${vip.cardNumber }</td>
					<td style="text-align: center;">${vip.name }</td>
					<td style="text-align: center;">${lf:formatMoney(vip.amount)}</td>
					<td style="text-align: center;">${lf:formatMoney(vip.balance)}</td>
					<td style="text-align: center;">${lf:formatMoney(vip.revenue)}</td>
					<td style="text-align: center;">${lf:formatMoney(vip.expenditure)}</td>
					<td style="text-align: center;">${lf:formatMoney(vip.profit)}</td>
					<td style="text-align: center;"><a href="${ctx}/vip/view/${vip.id}"> 查看</a></td>
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
<script type="text/javascript">
  $(function() {
	  menu.active('#vip-man');
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
	  });
	  
	  $("#export_btn").click(function(){
	   		$("#vipForm").attr("action","${ctx}/vip/exportVipCardList");
			$("#vipForm").submit();
			setTimeout("modify()",1000);
	  }); 
	  
	//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#search_LIKE_statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
          max: 30,
          autoFill: false,
          mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
          matchContains: true, 
          scrollHeight: 220,
          width: $('#statiumName').outerWidth(),
          multiple: false,
          formatItem: function (row, i, max) {                    //显示格式
              return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
          },
          formatResult: function (row) {                      //返回结果
              return row.name;
          },
          handleValue:function(id){
          	$('#statiumId').val(id);
          },
          parse:function(data){
          	var parsed = [];
      		var rows = data;
      		for (var i=0; i < rows.length; i++) {
      			var row = rows[i];
      			if (row) {
      				parsed[parsed.length] = {
      					data: row,
      					value: row["id"],
      					result: this.formatResult(row)
      				};
      			}
      		}
      		return parsed;
          }
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================
  });
  
  function active_btn(obj){
	  var activityItemId = $(obj).attr("id");
	  $("button[class='btn btn-default btn-sm active']").each(function(){
		  //先清除上一个已选择button
		  $(this).removeClass("active");
	  })
	  $(obj).addClass("active");
	  
	  location.href = "${ctx }/activity/userList/"+activityItemId;
  }
  
  function modify(){
	  $("#vipForm").attr("action","${ctx}/vip");
  }
</script>
</body>
</html>