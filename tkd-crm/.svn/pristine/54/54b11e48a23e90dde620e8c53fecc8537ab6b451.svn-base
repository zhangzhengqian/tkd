angular.module('crm.gameview', []).directive('crmGameview',['messageService','GameService',function(messageService,GameService){
    return{
        restrict: 'EA',
        template:'<div></div>',
        replace:true,
        scope:{
            conf:'='
        },
        link:function(scope, element, attrs){
        	var game = scope.conf;
        	var promise = GameService.getScheduleByType(game.id);
        	promise.success(function(res){
        		if(res["result"]=="success"){
        			var data = res["data"];
        			if(data.hasOwnProperty("groups")){
        				var schedule = {};
        				schedule["gameFormat"] = 1;
        				schedule["groupScheduleList"] = data["groups"];
        				var group_temp = new EJS({url: 'static/js/app/game/groupSchedule.ejs?ver=1.6'});
        				var group_html = group_temp.render({schedule:schedule});
        				var div = $("<div id='group_schedule' style='width:100%;position:relative;'></div>");
        				div.append(group_html);
        				$(element).append(div);
        				$(element).find("table td").css({"width":"120px","height":"40px"});
        				var downloadBtn = $('<a class="downloadBtn"style="position: absolute;" href="/admin/game/exportSchedule/'+game.id+'">下载</a>');
        				div.append(downloadBtn);
        			}
        			if(data.hasOwnProperty("tree")){
        				var canvasObj =  document.createElement("canvas");
        				gameTreeForCanvas(data["tree"],canvasObj);
        				var div = $("<div id='tree_schedule' style='width:100%; position:relative;'></div>");
        				div.append(canvasObj);
        				$(element).append(div);
        				var strDataURI = canvasObj.toDataURL("image/jpeg");
        				var downloadBtn = $('<a class="downloadBtn" style="position:absolute;" href="javascript:;">下载</a>');
        				downloadBtn.on("click",function(){
        					saveFile(strDataURI,"淘汰赛程.jpg");
        				})
        				$(div).append(downloadBtn);
        			}
        		}else{
        			messageService.publish('notifyMessage',["获取赛程失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["获取赛程失败！","error"]);
            });
        }
    }
}]);

function saveFile(data, filename){
    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
    save_link.href = data;
    save_link.download = filename;
   
    var event = document.createEvent('MouseEvents');
    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
    save_link.dispatchEvent(event);
}