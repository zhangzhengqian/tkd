<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="modal-header">
	<button type="button" class="close" style="margin:-7px 0px 0px 0px" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">用户列表</h3>
	  </div>
  		<div class="panel-body">
  		
  		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			        <div class="form-group form-group-sm">
				  	  <div class="col-md-6">
				  	  		<input  type="text" class="form-control input-sm" id="username"   placeholder="按用户名查询">
			       	  </div>
			       	  <div class="col-md-6">
				  	  		<input  type="text" class="form-control input-sm" id="userphone"   placeholder="按手机号查询">
			       	  </div>		        
			       	</div>
			</div>
		</div>
		<br>
			<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" id="reset_btn" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" id="sub_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				</div>
		<hr>
  		<div class="row">
			<div class="col-table col-md-12">
  		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th>序号</th>
				<th>球友号</th>
				<th>用户名</th>
				<th>性别</th>
				<th>手机号</th>
				<th>用户类型</th>
				<th>用户状态</th>
				<th>注册时间</th>
				<th>激活状态</th>
				<th>激活时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody id="content_table">
  			<c:forEach items="${users}" var="item" varStatus="stat">
  				<tr>
  					 <td>${stat.count }</td>
  					 <td>${item.qiuyouno }</td>
					 <td>${item.nickName}</td> 				
  					 <td>${item.sex}</td>
  					 <td>${item.phone}</td>
  					 <td>${item.property}</td>
  					 <td>
  					 	<c:if test="${item.state == '1'}" >
								正常
							</c:if>
							<c:if test="${item.state == '3'}" >
								冻结
							</c:if>
  					 </td>
  					 <td><fmt:formatDate value="${item.registTime}" pattern="yyyy-MM-dd HH:mm"/></td>
  					 <td>
						<c:if test="${item.activeState == '0'}" >
								未激活
							</c:if>
							<c:if test="${item.activeState == '1'}" >
								已激活
							</c:if>
					</td>
  					 <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
  					 <td><a href="javascript:selUser('${item.id}','${item.nickName }')">添加</a></td>
  				</tr>
  		    </c:forEach>
  		    </tbody>
  		 </table>
  		 </div>
  		 </div>
       </div>
   </div>
</div>
<script>
	$(function(){
		$("#reset_btn").on("click",function(e){
			$("#username").val("");
			$("#phone").val("");
		});
		
		$("#sub_btn").on("click",function(e){
			var username = $("#username").val();
			var phone = $("#userphone").val();
			$.get('${ctx}/common/ajax_qiuyou_query_dlg?username='+username+'&phone='+phone,function(data){
				if(data!=null){
					var temp = new EJS({url: '${ctx}/static/template/qiuyou_users.ejs?ver='+Math.random()});
					var html = temp.render({items:data["data"]});
					$('#content_table').html(html);
	        	}
			},"json");
		});
	});
	function selUser(userId,name){
		captainAddCallBack(userId,name);
		$("#myDlg_lg").modal("hide");
	}
</script>