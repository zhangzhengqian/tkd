<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆列表</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 场馆列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/statiumFree" method="post">
			  		<input type="hidden" name="search_EQ_status" value="${param.search_EQ_status }" id="search_EQ_status"  />
			  		<input type="hidden" name="search_EQ_sb" value="${param.search_EQ_sb}" id="search_EQ_sb"  />
			  	
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_LIKE_address"  name="search_LIKE_address"  value="${param.search_LIKE_address }" placeholder="按场馆地址查询">
			       	  </div>
			       	  <div class="col-md-5">
			       	  		<input  type="text" class="form-control" id="statiumName"  name="statiumName"  value="${param.statiumName }" placeholder="按场馆名称查询">
			       	  		<input  type="hidden" class="form-control" id="statiumId"  name="search_EQ_id"  value="${param.search_EQ_id }">
			       	  </div>	
			        </div>
			        <div class="form-group form-group-sm">
			          <lable class="control-label col-md-1 sr-only"></lable>
			          	<div class=" col-md-5 ">
			          		
			          		<div class="input-group">
					          <input  type="text" class="form-control input-sm" id="orgName"  value="<tags:orgName orgCode="${param.search_EQ_orgCode }" />" placeholder="按组织查询">
					          <span class="input-group-btn">
					            <button class="btn btn-default btn-sm" type="button"  id="orgNameClean">清除</button>
					          </span>
					        </div>
							<input  type="hidden" class="form-control input-sm" id="search_EQ_orgCode" name="search_EQ_orgCode" value="${param.search_EQ_orgCode }" />
							
							
			          	</div>
			          	<div class=" col-md-5 form-inline">
			              <tags:zone id="areaCode" name="areaCode" value="${param.areaCode }"  disabled="${disable }" />
			        	</div>         
			        </div>			 		
			 	
				<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				</div>
   			 </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-12 text-right">
		    <shiro:hasPermission name="statium:createForm">
		    	<a class="btn btn-primary btn-sm" href="${ctx }/statiumFree/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加球馆</a>
		    </shiro:hasPermission>
	    </div>
	  </div><!-- /操作按钮组 -->
	<br>	
				
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球馆名称</th>
				<th>所在地区</th>
				<th>地址</th>
				<th>录入人</th>
				<th>录入时间</th>
				<th>组织</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="statium" varStatus="stat">
				
				<c:set var="status_class" value="" />
				<c:if test="${statium.status == 0}">
					<c:set var="status_class" value="warning" />
				</c:if>
				<c:if test="${statium.status == 1}">
					<c:set var="status_class" value="success" />
				</c:if>
				<c:if test="${statium.status == 2}">
					<c:set var="status_class" value="info" />
				</c:if>

				<tr class="${status_class }" >
				
					<td class="text-center">${stat.count}</td>
					<td>
						 ${statium.name}
					</td>
					<td> <tags:zonemap code="${statium.areaCode }" /></td>
					<td>${statium.address }</td>
					<td>
						<tags:getUserById id="${statium.cb }" />
					</td>
					<td><fmt:formatDate value="${statium.ct}" pattern="yyyy-MM-dd"/></td>
					<td>
						<tags:orgName orgCode="${statium.orgCode }" />
					</td>
					<td>
					   <shiro:hasPermission name="statium:detailForm">
					   		<a class="btn btn-default btn-sm" href="${ctx }/statiumFree/detailForm?id=${statium.id}&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a></a>
					   </shiro:hasPermission>
					   	<a class="btn btn-default btn-sm" href="#" onclick="updateRating('${statium.id}',0)"><i class="glyphicon glyphicon-edit"></i>更为付费</a></a>
					   <shiro:hasPermission name="statium:delete">
					   		<a class="btn btn-danger btn-sm" href="#" onclick="deleteById('${statium.id}')"> <i class="glyphicon glyphicon-trash"></i> 删除</a></a>
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
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	  menu.active('#statium-man-free');
	  
	  $(".searchSb").click(function(){
		  var v = $(this).val();
		  $("#search_EQ_sb").val(v);
		  $("#search_form").submit();
	  });
	  
	  
		/* 按照组织查询  */
		$("#orgName").click(function() {
			$("#myDlgBody").load("${ctx}/common/search_org_dlg");
			$("#myDlg").modal();
		});
		$("#orgNameClean").click(function(){
			$("#orgName").val('');
			$("#search_EQ_orgCode").val('');
	 	});
	  
	  $('#adminFooter').hide();
		
	  var typeValue = '${param.search_EQ_type }';
	
	  if(typeValue){
	  	$("select[name=search_EQ_type] option[value="+typeValue+"]").attr("selected","selected");
	  }
	  
	  
	//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
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
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
		  		
});

  function getSelected() {
	    var ids = [];
	    var checked = $('input:checked');
	    if (checked.length) {
	      checked.each(function() {
	        if ($(this).attr('name') != 'chk_all') {
	          ids.push($(this).val());
	        }
	      });
	    }
	    return ids;
	}
	  
  //更新免费状态
  function updateRating(id,rating){
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要将场馆改成付费的吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/statium/updateRating', function(data){
	      	 if(data.result){
		      	 myAlert("修改成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("场馆修改失败","error");
			 }
	      }, null, {"id":id,"isRating":rating}, 'post');
	    }
	  });
	  return false;
  }
  
  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除该场馆吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/statium/delete', function(data){
	      	 if(data.result){
		      	 myAlert("场馆删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("场馆删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
</script>

</body>
</html>