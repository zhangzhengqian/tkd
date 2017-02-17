<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<style type="text/css">
	.divFont {
		font-size: 14px;
	}
	#divHead {
		background-color: #d9e2ee;
		height: 40px;
		font-size: 16px;
	}
</style>
<div class="modal-header">
	<button type="button" class="close" style="margin: -7px 0px 0px 0px"
		data-dismiss="modal">
		<span aria-hidden="true">X</span><span class="sr-only">Close</span>
	</button>
</div>

<div class="panel-body">
		<form  id="cardPayForm" class="form-horizontal" action="${ctx }/cardPay/userAccount" method="post" name="id">
			<input type="hidden" id="classInfoId" name="classInfoId" value="${classInfoId }">
			<input type="hidden" id="cardTypeValue" name="cardTypeValue">
			<div class="orderSearch myVipOrderSearch">
				<ul>
					<li class="timeLi subSearchLi1">
						<span style="width: 100px;">手机号：</span>
						<input type="text" style="width: 300px;" name="search_LIKE_userPhone" id="userPhone" value="${param.search_LIKE_userPhone }"
							   placeholder="请输入手机号等待匹配(至少4位)" onkeypress="keyPress()" maxlength="11">
						<input  type="hidden" class="form-control input-sm" id="search_EQ_userId"  name="search_EQ_userId"  value="${param.search_EQ_userId }">
					</li>
					<li class="subSearch subSearchLi1" id="searchBtn" style="display: none">
						<a class="submit" href="javascript:cardPaySubmit();">查询</a>
					</li>
					<li class="timeLi subSearchLi1">
						<span style="width: 100px;">&nbsp;</span>
						<label style="color: red">*注：选择匹配用户后(无匹配用户，则无此用户)，方可查询</label>
					</li>
				</ul>
			</div>
		</form>
</div>

<div class="panel-body">
	<div class="panel panel-default">
		<div class="formTable">
			<div class="panel-heading" id="divHead" style="background-color: #d9e2ee;font-size: 16px; margin-top: -20px;">用户账户信息</div>
			<div id="accountDiv" style="display: none">
				<ul>
					<li>
						<span class="title">姓名：</span>
						<label class="title" id="name" name="name"></label>
					</li>
					<li>
						<span class="title">手机号：</span>
						<label class="title" id="phone"></label>
					</li>
					<li>
						<span class="title">余额：</span>
						<label class="title" style="color: red;" id="balance"></label>
					</li>
					<div id="dateCardDiv" style="display: none">
						<li>
							<span class="title">期限卡：</span>
							<label class="title" id="cardDate"></label>
						</li>
						<li>
							<span class="title">类型：</span>
							<div class="btn-group" id="teachDiv" data-toggle="buttons" >
								<label class="btn btn-default" id="cType1">
									<input type="radio" id="cardType" name="cardType" value="1"  autocomplete="off">
									储值卡
								</label>
								<label class="btn btn-default" id="cType2">
									<input type="radio" id="cardType" name="cardType" value="2" autocomplete="off">
									期限卡
								</label>
								<label style="margin-top: 10px; margin-left:10px; color: red">* 选择支付类型</label>
							</div>
						</li>
					</div>
				</ul>
			</div>
			<div class="formSubDiv">
				<a href="javascript:backBtn()" class="saveBtnBot">返回</a>
				<a class="saveBtnBot" href="javascript:confirmPay()">刷卡</a>
			</div>

		</div>
	</div>
</div>

<script>
	$(function () {
		//====================================================
		// 自动匹配 用户 >>>>
		//====================================================
		$('#userPhone').autocomplete('${ctx}/cardPay/userInfo',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
			max: 30,
			autoFill: false,
			mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
			matchContains: true,
			scrollHeight: 220,
			width: $('#name').outerWidth(),
			multiple: false,
			formatItem: function (row, i, max) {                    //显示格式
				return "【"+row.name+"】【"+row.phone+"】";
			},
			formatResult: function (row) {                      //返回结果
				return row.name;
			},
			handleValue:function(userId){
				$('#search_EQ_userId').val(userId);
				$('#searchBtn').show();
			},
			parse:function(data){
				var parsed = [];
				var rows = data;
				for (var i=0; i < rows.length; i++) {
					var row = rows[i];
					if (row) {
						parsed[parsed.length] = {
							data: row,
							value: row["userId"],
							result: this.formatResult(row)
						};
					}
				}
				return parsed;
			}
		});
		//====================================================
		// 自动匹配 用户 <<<<
		//====================================================
	})

	// 返回
	function backBtn() {
		$("#myDlg_lg").modal("hide");
	}
	
	// 查询
	function cardPaySubmit() {
		if ($('#userPhone').val() == undefined || $('#userPhone').val() == ''){
			swal({
				title: "警告",
				text: "请输入手机号！"
			});
			return;
		}
		if ($('#search_EQ_userId').val() == undefined || $('#search_EQ_userId').val() == ''){
			swal({
				title: "警告",
				text: "请选择匹配手机号！"
			});
			return;
		}
		$.ajax({
			url : "${ctx }/cardPay/userAccount",
			method : "POST",
			data : $('#cardPayForm').serialize(),
			dataType: 'json',
			success: function(data){
				if(data.result=='success'){
					// 页面赋值
					$('#name').text(data.data.name);
					$('#phone').text(data.data.phone);
					$('#balance').text(toThousands(data.data.balance/100));
					$('#cardDate').text(data.data.cardDate);
					$('#accountDiv').show();
					if (data.data.cardDate) {
						$('#dateCardDiv').show();
					} else {
						$('#cardTypeValue').val(1);
						$('#dateCardDiv').hide();
					}
				} else {
					swal({
						title: "警告",
						text: data.reason
					})
				}
			}

		})
	}

	// 数字千分化
	function toThousands(num) {
		return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
	}

	// 刷卡
	function confirmPay() {
		var cardType = 1;
		if ($('#cardTypeValue').val()) {
			cardType = $('#cardTypeValue').val();
		} else {
			cardType = $("input[name='cardType']:checked").val();
		}
		if(cardType) {
			var userId = $("#search_EQ_userId").val();
			var classInfoId = $('#classInfoId').val();
			$('#loading').show();
			$.ajax({
				url : "${ctx }/cardPay/paySubmit/" + userId + "/" + classInfoId + "/" + cardType,
				method : "POST",
				dataType : 'json',
				success: function (data) {
					$('#loading').hide();
					if (data.result=='success') {
						swal({
							title: "提示",
							text: "刷卡成功",
							showConfirmButton: "true",
							confirmButtonText: "确定",
							animation: "slide-from-top"
						}, function () {
							$("#myDlg_lg").modal("hide");
							location.href = "${ctx }/cardPay/classPay/" + classInfoId;
						})
					} else {
						swal({
							title: "警告",
							text: data.reason
						})
					}
				}
			})
		} else {
			swal({
				title: "警告",
				text: "请选择支付类型！"
			});
			return;
		}
	}
</script>