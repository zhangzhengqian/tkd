<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>素材管理</title>
	
	<link href="${ctx }/static/css/pushSourceStyle.css" rel="stylesheet">
	 <style>


	</style>
</head>

<body>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 推送管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
	  <div class="row"><!-- 查询条件 -->
		<div class="col-md-12">
			<form id="search_form" class="form-horizontal" action="${ctx}/push/sourceManage" method="post">
				<div class="form-group form-group-sm">
					<label for="sex" class="col-md-3 control-label"><span class="text-red"></span></label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control input-sm" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}" placeholder="按标题查询">
					</div>
				</div>
	
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
						&nbsp;&nbsp;
						<button id="addSource" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span> 添加素材</button>
						&nbsp;&nbsp;
						<button id="backSourceManage" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-import"></span> 返回</button>
					</div>
				</div>
	   		</form>
		</div>
	  </div>
	
	<div class="row">
	<div id="wrapper">
	<!-- 开始 -->
	  <div class="brand-list">
	    <div class="brand-bd cle" id="brand-waterfall">
	        <!-- 循环模块 item -->
	        <c:forEach items="${data.content}" var="pushVo" varStatus="stat">
				<c:if test="${stat.last }" >
					<input id="dataCount" type="hidden" name="dataCount" value="${stat.count }" />	
				</c:if>
				<c:if test="${!empty pushVo.childPush }" >
					<div class="item" id="brand-a">
						<div class="appmsg_info">
			                <em class="appmsg_date">${pushVo.pushCt }</em>
			            </div>
			            <div class="cover_appmsg_item">
			                <h4 class="appmsg_title js_title" style=" position: relative;bottom: -75px;"><a href="http://mp.weixin.qq.com/s?__biz=MzAwOTI4MjM0Ng==&amp;mid=206757139&amp;idx=1&amp;sn=d044536b28c53c09c2f6dad0d7cd5b82#rd" target="_blank">${pushVo.title }</a></h4>
			                <div class="appmsg_thumb_wrp"><img src="${pushVo.image }" alt="" class="appmsg_thumb" style="width:262px;height:64px;display:block;margin-left: auto;margin-right: auto;"></div>
			            </div>
			            <c:forEach items="${pushVo.childPush }" var="push" varStatus="stat">
							<div class="appmsg_item">
				                <img src="${push.image }" alt="" class="appmsg_thumb" style="width:78px;height:78px;">
				                <h4 class="appmsg_title js_title">
				                <a href="http://mp.weixin.qq.com/s?__biz=MzAwOTI4MjM0Ng==&amp;mid=206757139&amp;idx=3&amp;sn=60879726cd60ea3ffa0e7fb447b12aff#rd" target="_blank">${push.title }</a></h4>
				            </div>
						</c:forEach>
						<p><a href="${ctx }/push/editSource?sourceId=${pushVo.id }" class="btn btn-primary" role="button">编辑</a> <a href="${ctx }/push/deleteSourceGroup?sourceId=${pushVo.id }" class="btn btn-default" role="button">删除</a></p>
	        		</div>
				</c:if>
				
				<c:if test="${empty pushVo.childPush }" >
					<div class="item" id="brand-a">
			            <h4 class="appmsg_title js_title"><a href="#" target="_blank">${pushVo.title }</a></h4>
						 <div class="appmsg_info">
			                <em class="appmsg_date">${pushVo.pushCt }</em>
			            </div>
			            <div class="appmsg_thumb_wrp"><img src="${pushVo.image }" alt="" class="appmsg_thumb" style="width:262px;height:64px;display:block;margin-left: auto;margin-right: auto;"></div>
			            <p class="appmsg_desc">${pushVo.title }</p>
						<p><a href="${ctx }/push/editSource?sourceId=${pushVo.id }" class="btn btn-primary" role="button">编辑</a> <a href="${ctx }/push/deleteSourceGroup?sourceId=${pushVo.id }" class="btn btn-default" role="button">删除</a></p>
		        	</div>
				</c:if>
				
			</c:forEach>
	        
	     </div>
	  </div>
	  <!-- end -->
	</div>
	</div>
	
	<tags:pagination page="${data}" />
	
  </div>
</div>
<script src="${ctx}/static/js/jquery/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap/js/bootstrap.min.js"></script>
        
<script>

$(function(){
	menu.active('#push-man');
	
	$('#backSourceManage').click(function() {
		  $("#search_form").attr("action","${ctx}/push");
		  $("#search_form").attr("method","get");
	  });
	
	$('#brand-waterfall').waterfall();
	});
	
	$('#addSource').click(function() {
		  $("#search_form").attr("action","${ctx}/push/addSource");
		  $("#search_form").attr("method","post");
	});
	// 瀑布流插件
	// pannysp 2013.4.9
	;(function ($) {
	    $.fn.waterfall = function(options) {
	        var df = {
	            item: '.item',
	            margin: 15,
	            addfooter: true
	        };
	        options = $.extend(df, options);
	        return this.each(function() {
	            var $box = $(this), pos = [],
	            _box_width = $box.width(),
	            $items = $box.find(options.item),
	            _owidth = $items.eq(0).outerWidth() + options.margin,
	            _oheight = $items.eq(0).outerHeight() + options.margin,
	            _num = Math.floor(_box_width/_owidth);

	            (function() {
	                var i = 0;
	                for (; i < _num; i++) {
	                    pos.push([i*_owidth,0]);
	                } 
	            })();

	            $items.each(function() {
	                var _this = $(this),
	                _temp = 0,
	                _height = _this.outerHeight() + options.margin;

	                _this.hover(function() {
	                    _this.addClass('hover');
	                },function() {
	                    _this.removeClass('hover');
	                });

	                for (var j = 0; j < _num; j++) {
	                    if(pos[j][1] < pos[_temp][1]){
	                        //暂存top值最小那列的index
	                        _temp = j;
	                    }
	                }
	                this.style.cssText = 'left:'+pos[_temp][0]+'px; top:'+pos[_temp][1]+'px;';
	                //插入后，更新下该列的top值
	                pos[_temp][1] = pos[_temp][1] + _height;
	            });

	            // 计算top值最大的赋给外围div
	            (function() {
	                var i = 0, tops = [];
	                for (; i < _num; i++) {
	                    tops.push(pos[i][1]);
	                }
	                tops.sort(function(a,b) {
	                    return a-b;
	                });
	                $box.height(tops[_num-1]);

	                

	            })();

	            function addfooter(max) {
	                var addfooter = document.createElement('div');
	                addfooter.className = 'item additem';
	                for (var i = 0; i < _num; i++) {
	                    if(max != pos[i][1]){
	                        var clone = addfooter.cloneNode(),
	                        _height = max - pos[i][1] - options.margin;
	                        clone.style.cssText = 'left:'+pos[i][0]+'px; top:'+pos[i][1]+'px; height:'+_height+'px;';
	                        $box[0].appendChild(clone);
	                    }
	                }
	            }

	        });
	    }
	})(jQuery);
</script>
</body>
</html>
