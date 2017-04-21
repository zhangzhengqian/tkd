<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<html>
<head>
	<title>角色管理:分配权限</title>
	
	<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

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
	
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
        <li ><a href="${ctx }/admin/role">角色管理</a></li>
        <li class="active">分配权限</li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
    <div class="row">
			<div class="col-md-4"><!-- 功能树 -->
			  <div class="tree-container">
				<ul id="funcTree" class="ztree"></ul>
			  </div>
			</div><!-- /功能树-->
			
			<div class="col-md-8" id="func-form" ><!-- 表单 -->
			
				<form id="form1" action="${ctx}/admin/role/perms/${role.roleId}" method="post" class="form-horizontal">
				
				    <fieldset>
				        <legend>
				            <small>角色名称：${role.roleName }</small>
				        </legend>
				
				        <div class="form-group">
				            <div class="col-md-3">
				                <a class="btn btn-default btn-block btn-lg" href="${ctx }/admin/role">
				                    <span class="glyphicon glyphicon-remove "></span> 返回
				                </a>
				            </div>
				            <div class="col-md-4">
				                <button type="button" class="btn btn-primary btn-block btn-lg" id="submit_btn">
				                    <span class="glyphicon glyphicon-ok"></span> 保存
				                </button>
				            </div>
				            
				        </div>
				
				    </fieldset>
				
				</form>			
			
			</div><!-- /表单 -->
		
		</div>
	</div>

</div>

<form id="actionForm" action="#" method="post">
    <input type="hidden" id="fids" name="fids" value=""/>
</form>

<script type="text/javascript">

var func = {};

/*
 * 调整包含树的容器的高度.
 */
func.adjustHeight = function() {
	  var ph = document.body.clientHeight;
	  
	  var $tc = $('.tree-container');
	  var $footer = $('#footer');
	  var fh = 0;
	  if ($footer.length) {
	    fh = $footer.outerHeight();
	  }
	  if ($tc.size() > 0) {
	    var th = ph - $tc.offset().top - fh - 38;
	    $tc.height(th);
	  }
}

/*
 * 初始化zTree.
 * @tid zTree的实例容器#id
 * @opt Ztree设置项.
 */
func.initTree = function(tid, opt) {
    //$.fn.zTree.destroy(tid);
    return $.fn.zTree.init($('#' + tid), opt);
}

$(function() {
	//左侧菜单高亮显示
	menu.active('#role-man');
	
	func.adjustHeight();

	/************************ 配置功能树 ************************/
	var settings = new TreeSetting('${ctx}/admin/func/tree');
	$.extend(true, settings, {
        check: {
            enable: true
            , chkboxType : { "Y": "ps", "N": "ps" }
        },
		
		async: {
		  dataFilter: dataFilter
		  , autoParam: ["id=pid"]
		},
		
		callback: {
		  onAsyncSuccess: onAsyncSuccess
		  , onAsyncError: onAsyncError
		  , onClick: onClick
		}
	});

	
	function dataFilter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
          if (childNodes[i].id == '0') {
              //修改根节点的样式
              childNodes[i].iconSkin = 'root';
              childNodes[i].nocheck = true;
          }
          
          if ($.inArray(childNodes[i].id, rfids) != -1) {
        	  childNodes[i].checked = true;
          }
        }
        return childNodes;
	}
	
	
	function onClick(event, treeId, treeNode, clickFlag) {
		funcTree.checkNode(treeNode, !treeNode.checked, true, true);
	}
	
	
	
	var asynCount = 0, asyncRoot = true;
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (asyncRoot) {
			asyncRoot = false;
		} else {
			asynCount--;
		}
		
		if (!!treeNode) {
            asyncNodes(treeNode.children);
        } else {
            asyncNodes(funcTree.getNodes());
        }
        
        //common.log('onAsyncSuccess : asynCount = ' + asynCount);
    }
	
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		//asynCount--;
        //common.log('onAsyncError : asynCount = ' + asynCount);
    }
	
	/*
	 * 异步加载并展开子节点.
	 */
	function asyncNodes(nodes) {
		if (!nodes)
			return;
		
		var zTree = funcTree;
		for (var i = 0, l = nodes.length; i < l; i++) {
			if (nodes[i].isParent && nodes[i].zAsync) {
				asyncNodes(nodes[i].children);
			} else {
				if (nodes[i].level < 99 && nodes[i].isParent) {
					asynCount++;
					zTree.reAsyncChildNodes(nodes[i], "refresh", false);
				}
			}
		}
	}
	
	/************************ 配置功能树 end ************************/

	//加载角色的权限id
	var rfids = [];
	$.ajax('${ctx}/admin/role/perms/${role.roleId}/json', {
		async : false
		, dataType : 'json'
		, success : function(data, textStatus, jqXHR) {
			rfids = data;
		}
	});
	
	//初始化zTree
	var funcTree = func.initTree('funcTree', settings);

	$('#submit_btn').on('click', function() {
		var url = '${ctx}/admin/role/perms/${role.roleId}';
		var fids = [];
		var nodes = funcTree.getCheckedNodes(true);
	    $.each(nodes, function() {
	    	fids.push(this.id);
	    });
	    common.log('checked fids: ' + fids);
	    
	    $('#fids').val(fids.join());
	    $('#actionForm').attr('action', url)[0].submit();
	});

});
</script>
	
</body>
</html>
