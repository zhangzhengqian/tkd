<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">

	<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">按名称查询场馆</h3>
  </div>
  <div class="panel-body">
	<div class="row">
        <div class="col-sm-5 pull-right input-group">
           <input type="text" class="form-control" id="statiumName" placeholder="请输入场馆名称">
           <span class="input-group-btn">
               <button class="btn btn-primary" id="searchBtn" type="button">查询</button>
           </span>
        </div>
    </div>
	<br>
    <div class="row">
	    <div id="statiumTable" style="overflow: auto;" class="col-sm-12">
	   
	    </div>
    </div>
</div>
  </div>
   <textarea id="template" style="display:none">
	        <table class="table table-bordered table-striped table-hover" >
	            <thead>
						<tr>
							<th style="border-bottom-width: 0px;">球馆名称</th>
							<th style="border-bottom-width: 0px;">地区</th>
							<th style="border-bottom-width: 0px;">球馆地址</th>
						</tr>
					</thead>
					<tbody>
						{#foreach $T.table as record} 
						<tr id="{$T.record.id}_{$T.record.name}" class="statiumTr" style="cursor: pointer;">
							<td>{$T.record.name}</td>
							<td>{$T.record.area}</td>
							<td>{$T.record.address}</td>
						</tr>
						{#/for}  
					</tbody>
	        </table>
	     </textarea>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal" id="search_org_selected">确定</button>
</div>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#searchBtn").on("click",function(e){
		$.ajax({
	        cache: true,
	        type: "GET",
	        url:'${ctx}/common/dialog_statium_by_name?name='+$("#statiumName").val(),
	        data:{},
	        async: false,
	        error: function(request) {
	        	common.showMessage("查询场馆信息失败！");
	        },
	        success: function(data) {
	        	if(data!=null&&data!=""){
		        	data = eval("("+data+")");
	        		var tableDatas = {table:data};
	        		$("#statiumTable").setTemplateElement("template");
	                // 给模板加载数据
	                $("#statiumTable").processTemplate(tableDatas);
	        	}
	        }
	    });
	});
	
	$('#statiumTable').on('click', '.statiumTr', function (e) {
		var that = $(this);
		var statiumValue = that.attr("id");
		modalCallBack(statiumValue);
		$("#myDlg_lg").modal("hide");
	})
});
</script>