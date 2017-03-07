<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
        <li class="active">教陪管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="coachForm" class="form-horizontal" action="${ctx }/ssouser/coach" method="post">
				<input type="hidden" name="search_EQ_top" value="${param.search_EQ_top}" id="search_EQ_top"  />		      
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按姓名查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="按注册手机号查询">
       	  			</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_cardId" name="search_LIKE_cardId" value="${param.search_LIKE_cardId }" placeholder="按身份证号查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_qiuyouno" name="search_LIKE_qiuyouno" value="${param.search_LIKE_qiuyouno }" placeholder="按球友号查询">
       	  			</div>
		         </div>
		         
		          <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_GTE_submitTime" name="search_GTE_submitTime" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_submitTime\')||\'%y-%M-%d\'}'})" value="${param.search_GTE_submitTime }" placeholder="提交审核时间-开始">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LTE_submitTime" name="search_LTE_submitTime" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_submitTime\')}'})" value="${param.search_LTE_submitTime }" placeholder="提交审核时间-结束">
       	  			</div>
		         </div>
		         
		          <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_GTE_passTime" name="search_GTE_passTime" value="${param.search_GTE_passTime }" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_passTime\')||\'%y-%M-%d\'}'})" value="${param.search_GTE_submitTime }" placeholder="审核通过时间-开始">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LTE_passTime" name="search_LTE_passTime" value="${param.search_LTE_passTime }" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_GTE_passTime\')||\'%y-%M-%d\'}'})" value="${param.search_LTE_passTime }" placeholder="审核通过时间-结束">
       	  			</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         <label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
						<select class="form-control" id="search_LIKE_sportType" name="search_LIKE_sportType">		
						<option  value="" >--请选择运动类别--</option>
						<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
							<option  value="${item.itemCode }" >--${item.itemName }--</option>
						</c:forEach>
					</select>
			  		</div>
			       	<div class="col-md-5">
						<select class="form-control" id="search_EQ_sex" name="search_EQ_sex">		
						<option  value="" >--请选择性别--</option>
						<option  value="男" >--男--</option>
						<option  value="女" >--女--</option>
					</select>
			  		</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         <label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_registSource" name="search_EQ_registSource">		
						<option  value="" >--请选择注册来源--</option>
						<c:forEach items="${lf:dicItems('USER_SOURCE') }" var="item">
							<option  value="${item.itemCode }" >--${item.itemName }--</option>
						</c:forEach>
					</select>
			  		</div>
			       	<div class="col-md-5">
						<select class="form-control" id="search_LIKE_userType" name="search_LIKE_userType">		
						<option  value="" >--请选择用户类型--</option>
						<option  value="教练" >--教练--</option>
						<option  value="陪练" >--陪练--</option>
					</select>
			  		</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         <label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_occType" name="search_EQ_occType">		
						<option  value="" >--请选择职业类型--</option>
						<option  value="个人" >--个人--</option>
						<option  value="驻场" >--驻场--</option>
					</select>
			  		</div>
			       	<div class="col-md-5">
						<select class="form-control" id="search_EQ_status" name="search_EQ_status">		
						<option  value="" >--请选择审核状态--</option>
						<option  value="1" >--待审核--</option>
						<option  value="2" >--已通过--</option>
						<option  value="3" >--未通过--</option>
						<option  value="4" >--修改待审核--</option>
					</select>
			  		</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         	 <label class="control-label col-md-1 sr-only" for="custName"></label>
			         <div class="col-md-5 form-inline">
			          	地区：<tags:zone name="search_EQ_areaCode" value="${param.search_EQ_areaCode }"  />
			         </div>
		         </div>
		        
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="button" onclick="search()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				<shiro:hasPermission name="coach:exportData">
	    				&nbsp;&nbsp;
	    					<button type="button" onclick="exportData()" id="export_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 导出Excel</button>
	    				</shiro:hasPermission>
	    				&nbsp;&nbsp;
	    				<button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		      <div class="btn-group-sm pull-right mtb10">
		      <shiro:hasPermission name="coach:create">
		        <a class="btn btn-sm btn-primary" href="${ctx }/ssouser/coach/create" ><span class="glyphicon glyphicon-plus"></span> 新增教/陪</a>
		      </shiro:hasPermission>
		   <!--    <shiro:hasPermission name="coach:batchDelete">
		        <a class="btn btn-sm btn-primary" href="javascript:void(0)" onclick="batchDelete()"><span class="glyphicon glyphicon-minus"></span> 批量删除</a>
		      </shiro:hasPermission> -->
		      <shiro:hasPermission name="coach:batchExportData">
		        <a class="btn btn-sm btn-primary" href="javascript:void(0)" onclick="exportData()"><span class="glyphicon glyphicon-export"></span> 批量导出</a>
		      </shiro:hasPermission>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
			<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
			    <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>姓名</th>
				<th>用户类型</th>
				<th>性别</th>
				<th>联系电话</th>
				<th>所在地区</th>
				<th>运动类型</th>
				<!-- <th>所属场馆</th> -->
				<th>职业类型</th>
				<!-- <th>教练资质</th> -->
				<th>注册来源</th>
				<th>审核状态</th>
				<th>提交审核时间</th>
				<th>审核通过时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="coach" varStatus="stat">
				<tr>
				    <td class="text-center"><input type="checkbox" value="${coach.id}" name="chk_list"/></td>
					<td class="text-center">${stat.count }</td>
					<td><a href="${ctx}/ssouser/coach/view/${coach.id }" >${coach.qiuyouno }</a></td>
					<td>${coach.name}</td>
					<td>${coach.userType}</td>
					<td>${coach.sex}</td>
					<td>${coach.linkphone}</td>
					<td>${coach.areaStr}</td>
					<td>
					${coach.sprotTypeZWStr}
					</td>
					<%-- <td>${coach.statiumName}</td> --%>
					<td>${coach.occType}</td>
					<%-- <td>${coach.coachQual}</td> --%>
					<td>${coach.registSourceStr}</td>
					<td>
					<c:if test="${coach.status == 1}" >
							待审核
					</c:if>
					<c:if test="${coach.status == 2}" >
							已通过
					</c:if>
					<c:if test="${coach.status == 3}" >
							未通过
					</c:if>
					<c:if test="${coach.status == 4}" >
							修改待审核
					</c:if>
					</td>
					<td><fmt:formatDate value="${coach.submitTime}" pattern="yyyy-MM-dd" /> </td>
					<td><fmt:formatDate value="${coach.passTime}" pattern="yyyy-MM-dd" /> </td>
					<td>
					  <a href="${ctx}/ssouser/coach/view/${coach.id}" class="btn btn-default btn-sm" id="editLink-${coach.id}"> 查看</a>
					   <span class="cutline"></span> 
					 <!--  <shiro:hasPermission name="coach:delete">
					  	<a href="#" data="${coach.name }" id="removeLink-${coach.id}" onclick="deleteById('${coach.id}')"> 删除</a>
					  </shiro:hasPermission> -->
					   <span class="cutline"></span> 
					  	<a href="#" data="${coach.name }" onclick="updateStatus('${coach.id}')" class="resetPwd btn btn-default btn-sm" id="resetLink-${coach.id}">
						  <shiro:hasPermission name="coach:updateStatus">
						  <c:if test="${coach.userState == 1 }">冻结</c:if>
						  <c:if test="${coach.userState == 2 }">解冻</c:if>
					  </shiro:hasPermission>
					  </a>
					  <shiro:hasPermission name="statium:top">
					   <c:if test="${coach.top == 0}">
							<a href="javaScript:" onclick="coachTop('${coach.id}',0);" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-arrow-up"></i> 置顶</a>					   
					   </c:if>
					   <c:if test="${coach.top == 1}">
							<a href="javaScript:" onclick="coachTop('${coach.id}',1);" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>					   
					   </c:if>
					   </shiro:hasPermission>
					</td>
					
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		<tags:errors />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">

  $(function() {
	  menu.active('#coach-man');
	  var v_search_LIKE_sportType = '${param.search_LIKE_sportType}';
	  if(v_search_LIKE_sportType){
		  $('#search_LIKE_sportType option[value='+v_search_LIKE_sportType+']').attr('selected','selected');
	  }
	  var v_search_EQ_sex = '${param.search_EQ_sex}';
	  if(v_search_EQ_sex){
		  $('#search_EQ_sex option[value='+v_search_EQ_sex+']').attr('selected','selected');
	  }
	  var v_search_EQ_registSource = '${param.search_EQ_registSource}';
	  if(v_search_EQ_registSource){
		  $('#search_EQ_registSource option[value='+v_search_EQ_registSource+']').attr('selected','selected');
	  }
	  var v_search_LIKE_userType = '${param.search_LIKE_userType}';
	  if(v_search_LIKE_userType){
		  $('#search_LIKE_userType option[value='+v_search_LIKE_userType+']').attr('selected','selected');
	  }
	  var v_search_EQ_occType = '${param.search_EQ_occType}';
	  if(v_search_EQ_occType){
		  $('#search_EQ_occType option[value='+v_search_EQ_occType+']').attr('selected','selected');
	  }
	  var v_search_EQ_status = '${param.search_EQ_status}';
	  if(v_search_EQ_status){
		  $('#search_EQ_status option[value='+v_search_EQ_status+']').attr('selected','selected');
	  }
	  
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  
	  $('#adminFooter').hide();
  });
  
  function exportData() {
	  var ids = getSelected();
	  if (ids.length != 0) {
		   $('#ids').val(ids.join(';'));
		   var $form = $('#actionForm');
		   $form.attr("action","${ctx }/ssouser/coach/export");
		   $form[0].submit();
	  }else{
	       var $form = $('#coachForm');
		   $form.attr("action","${ctx }/ssouser/coach/export");
		   $form[0].submit();
	  }
  }
  
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
	  
	function deleteById(id) {
	  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
	    if(result) {
	    	$.ajax({
                cache: true,
                type: "POST",
                url:'${ctx}/ssouser/coach/delete/' + id,
                data:{},
                async: false,
                error: function(request) {
                	common.showMessage("删除教陪练失败！");
                },
                success: function(data) {
                	data = eval("("+data+")");
                	if(!data.result || data.result != 'success'){
                		common.showMessage("删除教陪练失败！");
                	}else{
                		common.showMessage("删除教陪练成功！");
                		setTimeout(function(){
		                	var $form = $('#coachForm');
		          	        $form[0].submit();
                		},1000);
                	}
                }
            });
	    }
	  });
	  return false;
	}
	
	function updateStatus(id) {
		  var status = $('#resetLink-' + id).html().indexOf("冻结")>=0?"1":"2";
		  var msg = $('#resetLink-' + id).html().indexOf("冻结")>=0?"冻结":"解冻";
		  bootbox.confirm('您确定要'+msg+' [' + $('#resetLink-' + id).attr('data') + '] 吗？', function(result) {
		    if(result) {
		    	$.ajax({
	                cache: true,
	                type: "POST",
	                url:'${ctx}/ssouser/coach/updateStatus/' + id,
	                data:{"status":status},
	                async: false,
	                error: function(request) {
	                	common.showMessage(msg + "教陪练失败！");
	                },
	                success: function(data) {
	                	data = eval("("+data+")");
	                	if(!data.result || data.result != 'success'){
	                		common.showMessage(msg + "教陪练失败！");
	                	}else{
	                		common.showMessage(msg + "教陪练成功！");
	                		$('#resetLink-' + id).html(status =='1'?"解冻":"冻结");
	                	}
	                }
	            });
		    }
		  });
		  return false;
		}
	
	function batchDelete() {
		var ids = getSelected();
		  if (ids.length == 0) {
		    bootbox.alert('请选择要删除的教陪练！');
		    return false;
		  }else{
			  $('#ids').val(ids.join(';'));
		  }
		bootbox.confirm('您确定要批量删除吗？', function(result) {
		    if(result) {
		    	$.ajax({
	                cache: true,
	                type: "POST",
	                url:'${ctx}/ssouser/coach/batchDelete',
	                data:{"ids":$("#ids").val()},
	                async: false,
	                error: function(request) {
	                	common.showMessage("批量删除教陪练失败！");
	                },
	                success: function(data) {
	                	data = eval("("+data+")");
	                	if(!data.result || data.result != 'success'){
	                		common.showMessage("批量删除教陪练失败！");
	                	}else{
	                		common.showMessage("批量删除教陪练成功！");
	                		setTimeout(function(){
			                	var $form = $('#coachForm');
			          	        $form[0].submit();
	                		},1000);
	                	}
	                }
	            });
		    }
		  });
		  return false;
	}
	
	function search(){
		var $form = $('#coachForm');
		$form.attr("action","${ctx }/ssouser/coach");
		$form[0].submit();
	}
	
	  // 置顶
	  function coachTop(id,type){
		  if(!id){
			  myAlert("教陪id不能为空","error");
			  return false;
		  }
		  Util.getData(ctx + '/ssouser/coach/top', function(data){
	      	 if(data.result){
		      	 myAlert("置顶成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert(data.reason,"error");
		    	 return false;
			 }
	      }, null, {"id":id,"type":type}, 'get');
	  }
</script>

</body>
</html>
