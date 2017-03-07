<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<%--
--%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title" id="role_form_dlg_title">站内信</h4>
</div>
<div class="modal-body">

	<div class="row">
		<div class="col-sm-4" style="overflow-y: auto; height: 500px;">
			<div class="msg-panel">

				<div onclick="searchMsg(1)" class="no-read-msg">
					<h5 class="text-center" id="msg_badge_noRead">未读(0)</h5>
				</div>
				<div onclick="searchMsg(0)" class="all-msg">
					<h5 class=" text-center" id="msg_badge">全部(0)</h5>
					</button>
				</div>
			</div>

			<div class="list-group" id="message_itm_div">
				<!-- 异步加载 -->
			</div>
			<div class="list-group" id="no_message_itm_div">
				<!-- 异步加载 -->
			</div>
			<div class="list-group">
				<a href="#" class="list-group-item list-group-item-warning"
					id="message_load_more" title="0">
					<h5 class="list-group-item-heading text-center">加载更多</h5>
				</a>
				<a href="#" class="list-group-item list-group-item-warning" onclick="read_all()"
					id="read_all" title="0">
					<h5 class="list-group-item-heading text-center">全部设为已读</h5>
				</a>
			</div>
		</div>
		<div class="col-sm-8" style="overflow-y: auto; height: 500px;"
			id="message_content_div">
			<!-- 异步加载 -->
		</div>
	</div>



</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<script type="text/javascript">
	this.message_func = (function() {
		var show_view = function(id) {
			$(".message-item").removeClass("active");
			$("#message_item_" + id).removeClass("list-group-item-success");
			$("#message_item_" + id).addClass("list-group-item-default active");
			$.ajax({
				url : "${ctx}/message/content",
				data : {
					"id" : id
				},
				success : function(rtn) {
					$("#message_content_div").html(rtn);
				}
			});
		};
		return {
			//加载更多
			loadMore : function(pageNo, type) {
				$.ajax({
					url : "${ctx}/message/load_more",
					data : {
						"type" : type,
						"pageNo" : pageNo
					},
					success : function(rtn) {
						if (!rtn) {
							$.globalMessenger().hideAll();
							myAlert('暂无更多消息.');
							app.disabled('#message_load_more');
						}
						if (type == 0) {
							if(pageNo == 0){
								$("#message_itm_div").html(rtn);
							}else{
								$("#message_itm_div").append(rtn);
							}
						} else {
							if(pageNo == 0){
								$("#no_message_itm_div").html(rtn);
							}else{
								$("#no_message_itm_div").append(rtn);
							}
						}
						refreshCount();
						$("#message_load_more").attr('title', pageNo + 1);
						//默认选中第一条，当第一页时
						if (pageNo == 0) {
							var a = $(".message-item");
							var id = $(a[0]).attr('id');
							id = id.split('_')[2];
							show_view(id);
						}
					}
				});
			},
			//在 items.ftl 中调用了这个方法
			view : function(id) {
				show_view(id);
			}
		};
	})();

	$(function() {
		message_func.loadMore(0, 1);
		$("#message_load_more").click(function() {
			var pageNo = $(this).attr('title');
			if ($("#message_itm_div").css("display") == "block") {
				message_func.loadMore(pageNo, 0);
			} else {
				message_func.loadMore(pageNo, 1);
			}
		});

		//默认打开未读消息
		$("#message_itm_div").hide();
		$("#no_message_itm_div").show();
		$("#read_all").show();
	});

	//切换消息列表
	function searchMsg(v) {
		if (v == 0) {
			$("#message_itm_div").show();
			$("#no_message_itm_div").hide();
			$("#read_all").hide();
		} else {
			$("#message_itm_div").hide();
			$("#no_message_itm_div").show();
			$("#read_all").show();
		}
		message_func.loadMore(0, v);
	}
	
	function refreshCount(){
		$.ajax({
			url : "${ctx}/message/load_count",
			success : function(rtn) {
				var msgCount = rtn.split("_")[0];	
				if(msgCount == 0){
					$("#read_all").hide();
				}
				$("#msg_badge_noRead").html("未读（"+msgCount+"）");
				$("#msg_badge").html("全部（"+rtn.split("_")[1]+"）");
			}
		});
	}
	
	function read_all(){
		$.ajax({
			url : "${ctx}/message/read_all",
			success : function(data) {
				if(data == "success"){
					message_func.loadMore(0, 1);
					refreshCount();
					alert("更新成功。");
				}else{
					alert("更新失败！");
				}
			}
		});

	}
	

</script>

