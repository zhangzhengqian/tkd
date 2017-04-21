angular.module('crm.pagination', []).directive('crmPagination',['messageService',function(messageService){
    return{
        restrict: 'EA',
        template:'<div class="row">'+
        '<div ng-show="conf.totalItems > 0" class="col-sm-2"><ul class="pagination"><li>共{{conf.totalItems}}条记录</li></ul></div>'+
        '<div class="col-sm-10 text-right">'+
        '<ul class="pagination" ng-show="conf.totalItems > 0">'+
        '<li ng-click="prevPage()" ng-class="{disabled:conf.currentPage == 1}">'+
        '<a href="javascript:;">前一页</a>'+
        '</li>'+
        '<li ng-repeat="item in pageList track by $index" ng-class="{active: item == conf.currentPage, separate: item == \'...\'}">'+
        '<a href="javascript:;" class="paginate_button active" ng-click="changeCurrentPage(item)">{{item}}</a>'+
        '</li>'+
        '<li ng-class="{disabled: conf.currentPage == conf.numberOfPage}">'+
        '<a href="javascript:;" ng-click="nextPage()">后一页</a>'+
        '</li>'+
        '</ul>'+
        '</div>'+
        '</div>',
        replace:true,
        scope:{
            conf:'='
        },
        link:function(scope, element, attrs){
            if(scope.conf.onChange){
                scope.conf.onChange();
            }
            scope.getPageList = function(){
                var pagePromise = scope.conf.pagePromise;
                pagePromise.success(function(pageVo){
                	if(pageVo["result"]=="success"){
                		scope.$parent.objs = pageVo.data["objs"];
                        scope.$parent.searcher = pageVo.data["searcher"];
                        scope.conf.numberOfPage = pageVo.data["totalPages"];
                        scope.conf.totalItems = pageVo.data["totalElemnt"];
                        scope.pageList = [];
                        if(scope.conf.numberOfPage <= scope.conf.pagesLength){
                            // 判断总页数如果小于等于分页的长度，若小于则直接显示
                            for(var i =1; i <= scope.conf.numberOfPage; i++){
                                scope.pageList.push(i);
                            }
                        }else{
                            // 总页数大于分页长度（此时分为三种情况：1.左边没有...2.右边没有...3.左右都有...）
                            // 计算中心偏移量
                            var offset = (scope.conf.pagesLength - 1)/2;
                            if(scope.conf.currentPage <= offset){
                                // 左边没有...
                                for(i =1; i <= offset +1; i++){
                                    scope.pageList.push(i);
                                }
                                scope.pageList.push('...');
                                scope.pageList.push(scope.conf.numberOfPage);
                            }else if(scope.conf.currentPage > scope.conf.numberOfPage - offset){
                                scope.pageList.push(1);
                                scope.pageList.push('...');
                                for(i = offset + 1; i >= 1; i--){
                                    scope.pageList.push(scope.conf.numberOfPage - i);
                                }
                                scope.pageList.push(scope.conf.numberOfPage);
                            }else{
                                // 最后一种情况，两边都有...
                                scope.pageList.push(1);
                                scope.pageList.push('...');

                                for(i = Math.ceil(offset/2) ; i >= 1; i--){
                                    scope.pageList.push(scope.conf.currentPage - i);
                                }
                                scope.pageList.push(scope.conf.currentPage);
                                for(i = 1; i <= offset/2; i++){
                                    scope.pageList.push(scope.conf.currentPage + i);
                                }

                                scope.pageList.push('...');
                                scope.pageList.push(scope.conf.numberOfPage);
                            }
                        }
                	}else{
                		messageService.publish('notifyMessage',["获取列表失败！","error"]);
                	}
                    
                }).error(function(error){
                	messageService.publish('notifyMessage',["获取列表失败！","error"]);
                });
            }
            scope.getPageList();
            scope.changeCurrentPage = function(item){
                if(item == '...'){
                    return;
                }else{
                    scope.conf.currentPage = item;
                }
                scope.conf.onChange();
                scope.getPageList();
            };

            scope.prevPage = function(){
                if(scope.conf.currentPage > 1){
                    scope.conf.currentPage -= 1;
                    scope.conf.onChange();
                    scope.getPageList();
                }
            }

            scope.nextPage = function(){
                if(scope.conf.currentPage < scope.conf.numberOfPage){
                    scope.conf.currentPage += 1;
                    scope.conf.onChange();
                    scope.getPageList();
                }
            };

            scope.changeItemsPerPage = function(pagesLength){
                scope.conf.itemsPerPage = pagesLength;
                scope.conf.onChange();
                scope.getPageList();
            };
            
            scope.$parent.search = function(pageSize){
            	scope.conf.currentPage=1;
            	scope.conf.itemsPerPage=10;
            	if(pageSize){
            		scope.conf.itemsPerPage=pageSize;
            	}
            	scope.conf.onChange();
                scope.getPageList();
            }
            scope.$parent.reset = function(pageSize){
            	scope.$parent.searcher = {};
            	scope.conf.currentPage=1;
            	scope.conf.itemsPerPage=10;
            	if(pageSize){
            		scope.conf.itemsPerPage=pageSize;
            	}
            	scope.conf.onChange();
                scope.getPageList();
            }
            
            scope.$parent.first = function(){
            	scope.conf.currentPage=1;
            	scope.conf.itemsPerPage=20;
            	scope.conf.onChange();
                scope.getPageList();
            }
        }
    }
}]);