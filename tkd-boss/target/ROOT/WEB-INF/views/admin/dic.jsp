<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<html>
<head>
	<title>字典管理</title>
	
	<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
  <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

	<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<style type="text/css">
	<!--

	.ztree * {
    	font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei UI", simsun, sans-serif;
    	font-size: 14px;
	}
	.ztree li {
		margin: 2px 0;
	}
	
	/*冻结根结节*/
	.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
	.ztree li ul.level0 {padding:0; background:none;}
	
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
        <li class="active">字典管理</li>
        <span id="loading" class="pull-right"><img src="${ctx}/static/img/loading.gif" /></span>
    </ul>
  </div><!-- / 右侧标题 -->
  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
    <div class="row">
    
			<div class="col-md-6"><!-- 字典类别 -->
				<div class="form-group form-inline">
					<div class="btn-group btn-group-sm">
						<button type="button" id="new-dic-btn" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 新字典</button>
						<button type="button" id="mod-dic-btn" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 修改</button>
						<button type="button" id="del-dic-btn" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> 删除</button>
					</div>
					<input type="text" class="form-control" id="searchDicTree" placeholder="查找字典类别"/>
				</div>
				
				<div class="tree-container">
				   <ul id="dicTree" class="ztree"></ul>
				</div>
			</div><!-- 字典类别  end-->
			
			<div class="col-md-6"><!-- 字典项 -->
				<div class="form-group form-inline">
				  	<div class="btn-group btn-group-sm">
						<button type="button" id="new-dicitem-btn" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 新字典项</button>
						<button type="button" id="mod-dicitem-btn" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 修改</button>
						<button type="button" id="del-dicitem-btn" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> 删除</button>
					</div>
				</div>
				
				<div class="tree-container">
				  <ul id="dicItemTree" class="ztree"></ul>
				</div>
			</div><!-- /字典项  end-->
		
		</div>
	</div>

</div>


<script type="text/javascript">

var dic = {};

/*
 * 调整包含树的容器的高度.
 */
dic.adjustHeight = function() {
	  var ph = document.body.clientHeight;
	  //common.log('clientHeight:' + ph);
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
dic.initTree = function(tid, opt) {
	$.fn.zTree.destroy(tid);
	return $.fn.zTree.init($('#' + tid), opt);
}


$(function() {
	
	//左侧菜单高亮显示
	menu.active('#dic-man');
	
	dic.adjustHeight();

	var settings = new TreeSetting('${ctx}/admin/dic/tree');
	settings.setCallback('onClick', function(event, treeId, treeNode) {
		var opt = new TreeSetting('${ctx}/admin/dic/tree/' + treeNode.id + '/item');
		dic.initTree('dicItemTree', opt);
	});
	
	$.extend(settings, {
        view: {
            fontCss: getFontCss
        }
	});
	
    function getFontCss(treeId, treeNode) {
        return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
    }
	
	//初始化zTree
	var dicTree = dic.initTree('dicTree', settings);
	
	
	
	/******************************** 查找字典分类 ********************************/
	var lastValue = "", nodeList = [];
	
    function searchNode(e) {
        var value = $.trim($('#searchDicTree').val());
        if (lastValue === value) return;
        lastValue = value;
        updateNodes(false);
        if (value === "") return;
        nodeList = dicTree.getNodesByParamFuzzy('name', value);
        updateNodes(true);

    }
	
    function updateNodes(highlight) {
        for( var i=0, l=nodeList.length; i<l; i++) {
            nodeList[i].highlight = highlight;
            dicTree.updateNode(nodeList[i]);
        }
        if(highlight && nodeList.length > 0) {
        	scrollTop(nodeList[0]);
        }
    }
    
    function scrollTop(treeNode) {
    	var $node =  $('#'+treeNode.tId);
    	var $container = $node.parents('.tree-container');
    	var ch =  $container.height();
    	var th = $('#dicTree').height();
    	var offset = $node.offset().top - $container.offset().top;
    	
    	if ( offset > 0 ) {
   			$container.scrollTop(offset);
    	} else if (offset < 0) {
    	    $container.scrollTop($container.scrollTop() + offset);
    	}
    }
    
    $('#searchDicTree').on('change', searchNode);
    
    /******************************** 查找字典分类  end********************************/
    
    

	/****************************** 字典分类 表单处理代码 *****************************/
	
	//新建字典
	$('#new-dic-btn').click(function( event ) {
		$.get('${ctx}/admin/dic/create?plain', function(data) {
			if ($('#dic-modal').length > 0) {
			    $('#dic-modal').remove();//从DOM删除已有的对话框
			}
			$('body').append(data);
			common.showModal('#dic-modal');
		});
	});

	//修改字典
	$('#mod-dic-btn').click(function( event ) {
		var node = zTree.getSelectedTreeNode('dicTree');
		if (node) {
	    $.get('${ctx}/admin/dic/update/' + node.id + '?plain', function(data) {
	    	if ($('#dic-modal').length > 0) {
                $('#dic-modal').remove();//从DOM删除已有的对话框
            }
	        $('body').append(data);
	        common.showModal('#dic-modal');
	      });
		} else {
			common.showMessage('请选择一个字典类别!', 'warn');
		}
	});
	
	//删除字典
	$('#del-dic-btn').click(function( event ) {
		var node = zTree.getSelectedTreeNode('dicTree');
		if (node) {
			bootbox.confirm('您确定要删除字典 '+node.name+' 吗？', function(result) {
				if (result) {
					var url = '${ctx}/admin/dic/delete/' + node.id;
					$.post(url, function(data){
						if(data) {
							zTree.getZTreeObj('dicTree').removeNode(node);
							dic.initTree('dicItemTree', null);
						} else {
							common.showMessage('无法删除系统保留值!', 'warn');
						}
					});
				}
			});
		} else {
			common.showMessage('请选择一个字典类别!', 'warn');
		}
	});
	
	/****************************** 字典分类 表单处理代码  end *****************************/
	
	
	/****************************** 字典项 表单处理代码  *****************************/
	
	//新建字典项
	$('#new-dicitem-btn').click(function( event ) {
		var dicNode = zTree.getSelectedTreeNode('dicTree');
		if(dicNode) {
	    $.get('${ctx}/admin/dic/item/create?plain',{dicId:dicNode.id}, function(data) {
	        $('#dic-item-modal').remove();//从DOM删除已有的对话框
	        $('body').append(data);
	        common.showModal('#dic-item-modal');
	    });
		} else {
			common.showMessage('请选择一个字典类别!');
		}
	});

	//修改字典项
	$('#mod-dicitem-btn').click(function( event ) {
		var node = zTree.getSelectedTreeNode('dicItemTree');
		if (node) {
      $.get('${ctx}/admin/dic/item/update/' + node.id + '?plain', function(data) {
        $('#dic-item-modal').remove();//从DOM删除已有的对话框
        $('body').append(data);
        common.showModal('#dic-item-modal');
      });
		} else {
			common.showMessage('请选择一个字典项!', 'warn');
		}
	});
	
	//删除字典项
	$('#del-dicitem-btn').click(function( event ) {
		var node = zTree.getSelectedTreeNode('dicItemTree');
		if (node) {
			bootbox.confirm('您确定要删除字典项 '+node.name+' 吗？', function(result) {
				if (result) {
					var url = '${ctx}/admin/dic/item/delete/' + node.id;
					$.post(url, function(data){
						if(data) {
							zTree.getZTreeObj('dicItemTree').removeNode(node);
						} else {
							common.showMessage('无法删除系统保留值!', 'warn');
						}
					});
				}
			});
		} else {
			common.showMessage('请选择一个字典项!', 'warn');
		}
	});
	
	/****************************** 字典项 表单处理代码  end *****************************/


});
</script>
	
</body>
</html>
