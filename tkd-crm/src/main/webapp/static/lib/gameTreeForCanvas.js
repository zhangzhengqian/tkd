function gameTreeForCanvas(tree,canvasObj) {
			var canvas = canvasObj||document.getElementById("gameTreeCanvas");
			var labelMap = tree.labelMap;
			var turn = tree.turn;
	        var per = 65;
	        canvas.width = turn * 2*per + 10 * (turn - 2);
	        canvas.height = (Math.pow(2, turn - 1) + 1) * per;
	        var lastLength = Math.pow(2, turn - 2) * per;
	        var context = canvas.getContext("2d");
	        
	        //填充颜色
	      	context.rect(0,0,canvas.width,canvas.height);
	        context.fillStyle = "#f1f1f1";
	        context.fill();
	        
	        context.beginPath();

	        context.lineWidth = 1;
	        context.strokeStyle = '#ccc';
	        context.font = "12px 微软雅黑"//其中文字特殊样式可选，如果不填写，默认为normal，还可以使用italic表示斜体等等
	        context.fillStyle = '#000';//文字颜色
	        
	        context.moveTo(canvas.width, canvas.height / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2);
	        context.stroke();

	        if(tree.name!=undefined&&tree.name!=''){
	        	fillText(labelMap[tree.winner]+"　"+tree.name, {x: canvas.width - 0.75*per, y: (canvas.height / 2) - 5}, context, 'center','middle','#000');
	        	fillText('第一名', {x: canvas.width - 0.75*per, y: (canvas.height / 2) +10}, context, 'center','middle','#000');
	        	//文字
		        var second = {};
		        if(tree.lscore>tree.rscore){
		            second = tree.childrens[1];
		        }else{
		            second = tree.childrens[0];
		        }
		        if(second.name){
		        	fillText(labelMap[second.winner]+"　"+second.name, {x: canvas.width - 2.2 * per, y: (canvas.height / 2) - 5}, context, 'center','middle','#000');
		        	fillText('第二名', {x: canvas.width - 2.2 * per, y: (canvas.height / 2) +10}, context, 'center','middle','#000');
		        }
	        }

	        if(tree.smallscore){
	        	fillText(tree.lscore+":"+tree.rscore+"("+tree.smallscore+")", {x: canvas.width - 3 * per-10, y: (canvas.height / 2) - 5}, context, 'center','hanging','#FF8402');
	        }else{
	        	fillText(tree.lscore+":"+tree.rscore, {x: canvas.width - 3 * per-10, y: (canvas.height / 2) - 5}, context, 'center','hanging','#FF8402');
	        }
	        context.moveTo(canvas.width - per*1.5, canvas.height / 2);
	        context.lineTo(canvas.width - per*1.5, canvas.height / 2 - lastLength / 2);
	        context.stroke();

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2);
	        context.lineTo(canvas.width - per*1.5, canvas.height / 2 + lastLength / 2);
	        context.stroke();

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2 - lastLength / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2 - lastLength / 2);
	        context.stroke();
	        var left = tree.childrens[0];
	        fillText(labelMap[left.winner]+"　"+left.name, {x: canvas.width - 2.5 * per+10, y: canvas.height / 2 - lastLength / 2-5}, context, 'center','alphabetic','#000');

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2 + lastLength / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2 + lastLength / 2);
	        context.stroke();
	        var right = tree.childrens[1];
	        fillText(labelMap[right.winner]+"　"+right.name, {x: canvas.width - 2.5 * per+10, y: canvas.height / 2 + lastLength / 2+5}, context, 'center','hanging','#000');

	        var point = {};
	        point.x = canvas.width - 3 * per - 10;
	        point.y = canvas.height / 2 - lastLength / 2;
	        strokeChildren(point, left, per, context,labelMap);


	        point.x = canvas.width - 3 * per - 10;
	        point.y = canvas.height / 2 + lastLength / 2;
	        strokeChildren(point, right, per, context,labelMap);
	        
	        context.restore();
		};
		function fillText(text, point, context, align,base,fillStyle) {
			if(text==undefined||text=='undefined:undefined'||text=='undefined　undefined'){
				return;
			}
			context.fillStyle = fillStyle;
	        context.textAlign = align;//文本水平对齐方式
	        context.textBaseline = base;//文本垂直方向，基线位置
	        context.fillText(text, point.x, point.y);//创建文字，控制文件的起始位置
	    }

	    function strokeChildren(point, child, per, context,labelMap) {
	    	context.lineWidth = 1;
	        context.strokeStyle = '#ccc';
	        //下一轮
	        var turn = child.turn;
	        var lastLength = Math.pow(2, turn - 2) * per;

	        context.moveTo(point.x, point.y);
	        context.lineTo(point.x, point.y - lastLength / 2);
	        context.stroke();


	        context.moveTo(point.x, point.y);
	        context.lineTo(point.x, point.y + lastLength / 2);
	        context.stroke();


	        var left = child.childrens[0];
	        var point_l = {};
	        context.moveTo(point.x, point.y - lastLength / 2);
	        if (turn == 2) {
	            context.lineTo(point.x - 3 * per, point.y - lastLength / 2);
	        } else {
	            context.lineTo(point.x - 2*per, point.y - lastLength / 2);
	            point_l.x = point.x - 2*per - 10;
	            point_l.y = point.y - lastLength / 2;
	        }
	        context.stroke();


	        var right = child.childrens[1];
	        var point_r = {};
	        context.moveTo(point.x, point.y + lastLength / 2);
	        if (turn == 2) {
	            context.lineTo(point.x - 3 * per, point.y + lastLength / 2);
	        } else {
	            context.lineTo(point.x - 2*per, point.y + lastLength / 2);
	            point_r.x = point.x - 2*per - 10;
	            point_r.y = point.y + lastLength / 2;
	        }
	        context.stroke();

	        if (turn > 2) {
	        	var point_l_ = {x:point_l.x+per,y:point_l.y-3};
	            fillText(labelMap[left.winner]+"　"+left.name, point_l_, context, 'center','alphabetic','#000');
	            var point_r_ = {x:point_r.x+per,y:point_r.y+3};
	            fillText(labelMap[right.winner]+"　"+right.name, point_r_, context, 'center','hanging','#000');

	            var point_ = point;
	            point_.x = point.x-per/2;
	            if(child.smallscore){
	            	fillText(child.lscore+":"+child.rscore+"("+child.smallscore+")", point_, context, 'center','hanging','#FF8402');
	            }else{
	            	fillText(child.lscore+":"+child.rscore, point_, context, 'center','hanging','#FF8402');
	            }

	            strokeChildren(point_l, left, per, context,labelMap);

	            strokeChildren(point_r, right, per, context,labelMap);
	        }else{
	            point_l = {x:point.x-3*per+30,y:point.y - lastLength / 2-3};
	            fillText(left.name==''?'轮空':left.label+"　"+left.name, point_l, context, 'left','alphabetic','#000');
	            point_r = {x:point.x-3*per+30,y:point.y +lastLength / 2-3};
	            fillText(right.name==''?'轮空':right.label+"　"+right.name, point_r, context, 'left','alphabetic','#000');

	            if(left.name!=''&&right.name!=''){
	                var point_ = point;
	                point_.x = point.x-2*per;
	                point_.y = point.y-3;
	                if(child.smallscore){
	                	fillText(child.lscore+":"+child.rscore+"("+child.smallscore+")", point_, context, 'center','hanging','#FF8402');
	                }else{
	                	fillText(child.lscore+":"+child.rscore, point_, context, 'center','hanging','#FF8402');
	                }
	            }

	        }
	    }