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
	    <h3 class="panel-title">添加执教经历 </h3>
	  </div>
  		<div class="panel-body" id="resumeDiv">
  		<textarea id="resumeTemplate" style="display:none">
      	 	<div class="form-group form-group-sm form-inline">
       	 	<div class="input-group">
       	 		<input type="text"  id="begin" value="{$T.startTimeStr}" placeholder="教习开始时间" class="form-control" onclick="WdatePicker({"dateFmt":"yyyy-MM-dd"});">
    	 		<label for="stime0" class="input-group-addon" style="height:30px;"><i class="fa fa-calendar"></i></label>
       	 	</div>
       	 	<div class="input-group">
     	 			<input type="text" id="end" value="{$T.endTimeStr}" placeholder="教习结束时间" class="form-control" onclick="WdatePicker({"dateFmt":"yyyy-MM-dd"});">
     	 			<label for="etime0" class="input-group-addon" style="height:30px;"><i class="fa fa-calendar"></i></label>
     	 		</div>
      	 	</div>
     	 	<div class="form-group form-group-sm">
     	 		<textarea  cols="100" rows="7" placeholder="请输入您的执教经历内容" id="content">{$T.resume}</textarea>
     	 	</div> 
      	 </textarea>
       </div>
   </div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal" id="search_org_selected">确定</button>
</div>
<script type="text/javascript">
$(function(){
	var table = null;
	if(resumeIndex!=null){
		table = resumes[resumeIndex];
	}else{
		table = {};
	}
	$("#resumeDiv").setTemplateElement("resumeTemplate");
    // 给模板加载数据
    $("#resumeDiv").processTemplate(table);
    $("#search_org_selected").click(function(){
    	var begin = $("#begin").val();
    	var end = $("#end").val();
    	var content = $("#content").val();
    	if(!begin || !end){
    		alert("开始结束时间不能为空！");
    		return;
    	}
    	if(!content){
    		alert("执教经历不能为空！");
    		return;
    	}
    	var resumeSave = {startTimeStr:begin,endTimeStr:end,resume:content};
    	if(resumeIndex == null){
	    	resumes.push(resumeSave);
    	}else{
			resumes.splice(resumeIndex,1,resumeSave);    		
    	}
    	resumeCallBack();
    })
});

</script>