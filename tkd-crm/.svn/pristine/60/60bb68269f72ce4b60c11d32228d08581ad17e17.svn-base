
/*
 * 构造zTree setting对象类.
 * url   : string  异步加载的url.
 * type  : string  ajax请求类型，get | post.
 */
function TreeSetting(url, type) {
	
	var _type = type ? type : 'get';
	var _url  = url ? url : null;
	
	this.view = {
		selectedMulti : false,
		dblClickExpand: zTree.lockRootNode
	}
	
	this.edit = {}
	
	this.data = {
		simpleData: {
			enable: true
		}
	}
	
	this.async = {
		enable: true,
		type  : _type,
		url   : _url,
		autoParam:['id']
	}
	
	this.callback = {
		beforeClick: zTree.disableSelectRootNode
	}
	
	this.data = {
		keep: {
			parent: true
		}
	}
}

TreeSetting.prototype.setCallback = function(name, fn) {
	this.callback[name] = fn;
}


var zTree = {}

//冻结根节点
zTree.lockRootNode = function(treeId, treeNode) {
	return treeNode.level > 0;
}

//禁止选中根节点
zTree.disableSelectRootNode = function(treeId, treeNode, clickFlag) {
	return treeNode.level != 0;
}

//返回第一个被选中的节点或者null
zTree.getSelectedTreeNode = function(treeId) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length > 0)
		return nodes[0];
	else
		return null;
}

zTree.getZTreeObj = $.fn.zTree.getZTreeObj;

