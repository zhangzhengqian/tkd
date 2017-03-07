<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
<!--
.ztree * {
	font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei UI",
		simsun, sans-serif;
	font-size: 14px;
}

.ztree li {
	margin: 3px 0;
}

.ztree li a.curSelectedNode {
	height: 18px;
}

/*冻结根结节*/
.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
}

.ztree li ul.level0 {
	padding: 0;
	background: none;
}

/*根节点图标样式*/
.ztree li span.button.root_ico_open,.ztree li span.button.root_ico_close
	{
	width: 0px;
	height: 0px;
}


/*编辑按钮图标样式*/
.ztree li a span.button.edit
, .ztree li a span.button.remove
, .ztree li a span.button.add {
	margin-left: 10px;
	margin-right: -5px;
}
.ztree li span.button.add {
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}

.tree-container {
	border: #efefef 1px solid;
	overflow: auto;
}
-->
</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title" id="role_form_dlg_title">按组织查询</h4>
</div>
<div class="modal-body">

	<div class="row">
		<div class="col-sm-12" style="overflow-y:auto; height: 500px;">
			<ul id="orgTreeUl" class="ztree"></ul>
		</div>
	</div>

	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal" id="search_org_selected">确定</button>
</div>
<script type="text/javascript">


var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
			onClick: zTreeOnClick
		}
	};

var zNodes = {};
if('${orgTree}'){
	zNodes = JSON.parse('${orgTree}'); 
}

function zTreeOnClick(event, treeId, treeNode) {
	//alert(treeId+", "+treeNode.id + ", " + treeNode.name);
};

$(function(){
	$.fn.zTree.init($("#orgTreeUl"), setting, zNodes);	
	
	$("#search_org_selected").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("orgTreeUl");
		var nodes = treeObj.getCheckedNodes(true);		
		try{
			var orgName = nodes[0].name;
			var orgCode = nodes[0].orgCode;
		}catch(err){
			var orgName = null;
			var orgCode = null;	
		}
		if(orgCode){
			$("#orgName").val(orgName);
			$("#search_EQ_orgCode").val(orgCode);
			//myAlert(orgCode);
		}else{
			myAlert("请选择组织","error");
		}
	});

});

</script>