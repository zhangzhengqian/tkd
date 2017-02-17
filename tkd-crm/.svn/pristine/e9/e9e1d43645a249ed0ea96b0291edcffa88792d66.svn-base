(function($) {
	var temp;
	$.fn.gameTree = function(temp_,gameTree) {
		temp = temp_;
		var html = temp.render({childrens:[gameTree]});
		$(this).html(html);
		getTreeNode(gameTree);
	};
	function getTreeNode(tree){
		var childrens = tree.childrens;
		var leftChild;
		var rightChild;
		if(childrens.length!=0){
			leftChild = childrens[0];
			rightChild = childrens[1];
			var html = temp.render({childrens:childrens});
			$("#"+tree.id).append(html);
		}else{
			return;
		}
		getTreeNode(leftChild);
		getTreeNode(rightChild);
	}
})(jQuery);
 