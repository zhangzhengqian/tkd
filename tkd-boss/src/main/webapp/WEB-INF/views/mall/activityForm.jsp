<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>
<style>
	/*.input-group{*/
		/*height:30px;*/
		/**/
	/*}*/
	.input-group input:nth-child(1){
		margin:0;padding:0;
	}


	.input-group #input-group{
		background:none;
		border:none;
		height:10px;
	}
	.input-group #buyAmount{
		width:60px;
		height:30px;
		padding:0;
		position: absolute;
		left: 78px;
		top: 27px;
	}
	#input-group{
		margin:0;
		padding:0;
	}
	#giveAmount{
		width:33px;
		height:30px;
		margin-bottom:30px;
		margin-left:5px;
		padding:0;
	}
	#goodId{
		width:160px;
		height:30px;
		padding:0;
		margin:0;
	}
	#ttt{
		display: inline-block;
		width:40px;
		height:36px;
		margin-left:25px;
	}
	.input-group{
		height:16px;
	}
	.input-group #one{
		display: inline-block;
	}
	.input-group #two{
		display: inline-block;
		margin-top:-20px;
		margin-left:10px;
	}
	.input-group #one p{
		display: inline-block;
		margin-top:30px;
	}
	.input-group #one input{
		display: inline-block;
	}

	.goodNamesP{
		width:500px; margin: 0;padding: 0;margin-top: 6px;
	}
</style>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 商城管理</li>
	        <li class="active">活动管理</li>
	        <li class="active">活动添加</li>
	    </ul>
  	</div><!-- / 右侧标题 -->

  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>活动信息</small></legend>
	<form id="inputForm" action="${ctx}/mall/saveActivity" method="post" class="form-horizontal">
		<input type="hidden" name="id" id="dataId" value="${data.id}">
		<fieldset>
			<div class="form-group form-group-sm">
				<label   class="col-md-3 control-label"><span class="text-red"></span>活动商品:</label>
			    <div class="col-md-6 has-feedback">
					<a class="btn btn-primary btn-sm" href="#" onclick="addgoods(1)"><span
							class="glyphicon glyphicon-plus">新增活动商品</span></a>
					<div  id="goodNamesDiv">
						<c:forEach items="${data.crmMallGoodsList}" var="good" >
							<p class="goodNamesP">
							<input style="display: inline-block" type="text" value="${good.name}" id="${good.id}"  class="form-control" name = "goodNames"><a  style="display: inline-block;position: absolute;" class="btn btn-primary btn-sm" href="#" onclick="delgoods(this,'${good.id}')"><span
								class="glyphicon glyphicon-minus"></span></a>
							</p>
						</c:forEach>
					</div>
					<input type="hidden" name="goodsId" id="goodsId" value="${data.goodsId}">
					<input type="hidden" id="delGoodsId" >
				</div>
			</div>

			<div class="form-group form-group-sm">
				<label for="startTime" class="col-md-3 control-label"><span
						class="text-red">* </span>开始时间:</label>
				<div class="col-md-6 has-feedback">
					<div class="input-group">
						<input ${readonly }   type="text" id="startTime" name="startTime"
											  placeholder="请填开始时间" value="${data.startTime}"
											  class="form-control" data-date-format="yyyy-mm-dd"
											  onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" />
						<label for="startTime" class="input-group-addon"><i
								class="fa fa-calendar"></i></label>
					</div>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label for="endTime" class="col-md-3 control-label"><span
						class="text-red">* </span>结束时间:</label>
				<div class="col-md-6 has-feedback">
					<div class="input-group">
						<input ${readonly }   type="text" id="endTime" name="endTime"
											  placeholder="请填结束时间" value="${data.endTime}"
											  class="form-control"
											  onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" />
						<label for="endTime" class="input-group-addon"><i
								class="fa fa-calendar"></i></label>
					</div>
				</div>
			</div>
		
			<div class="form-group form-group-sm">
				<label  class="col-md-3 control-label"><span class="text-red">*</span>规则类型:</label>
			    <div class="col-md-6 has-feedback form-inline">
					<select class="form-control" name="ruleType" id="ruleType" >
						<option id="option1" value="0" <c:if test="${data.ruleType == '0'}">selected="selected"</c:if>>--买赠水--</option>
						<option id="option2" value="1" <c:if test="${data.ruleType == '1'}">selected="selected"</c:if>>--买赠其他--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm" id="goodsDiv1" >
				<label  class="col-md-3 control-label"><span class="text-red">*</span>赠送条件：</label>
				<div class="col-md-6 has-feedback">
					<div class="input-group"style="margin-top: -22px;">
						<p id="one">
						<p  class="input-group-addon" id="input-group">订购数量满</p>
						<input ${readonly } type="text" id="buyAmount" name="buyAmount" onkeyup="this.value=this.value.replace(/\D/g,'')"  placeholder="请填数量" value="${data.buyAmount}"  class="form-control"   style="height:30px;top:27px;"/>
						<p id="ttt"></p>
						<p  class="input-group-addon" id="input-group">箱，获赠商品</p>
						</p>
						<p id="two">
						<select class="form-control" name="goodId" id="goodId" >
							<c:forEach items="${goods}" var="goodsInfo" >
								<option id="option1" value="${goodsInfo.id}" <c:if test="${data.goodId == goodsInfo.id}">selected="selected"</c:if>>${goodsInfo.name}  (${goodsInfo.bulk}*${goodsInfo.amount} ${goodsInfo.unit})</option>
						    </c:forEach>
						</select>
							<input ${readonly } type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" class="give" id="giveAmount" name="giveAmount"  placeholder="请填数量" value="${data.giveAmount}"  class="form-control"  />  箱
					    </p>
					</div>
				</div>
		    </div>
			<div class="form-group form-group-sm" id="goodsDiv2">
				<label class="col-md-3 control-label"><span class="text-red">*</span>赠送条件：</label>
				<div class="col-md-6 has-feedback" style="margin-top: -22px;">
					<div class="input-group">
						<label   style="margin-top: 31px;font-weight: normal;" >订购数量满</label>
						<input ${readonly } type="text" id="buyAmount1" name="buyAmount1" onkeyup="this.value=this.value.replace(/\D/g,'')"  placeholder="请填数量" value="${data.buyAmount}"  class="form-control"style="width: 60px; height: 30px; position: absolute;top:23px;left: 73px; padding: 0;"  />
						<label  style="margin-left: 70px;margin-right: 260px;font-weight: normal;">箱，获赠商品</label>
						<input ${readonly } type="text" id="goodsName1" name="goodsName"  placeholder="请填赠品" value="${data.goodsName}"  class="form-control"style="padding:0;position:absolute;left:232px;top:25px;width:180px;"  />
						<input ${readonly } type="text" id="giveAmount1" name="giveAmount1"  onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="请填数量" value="${data.giveAmount}"  class="form-control" style="margin-left:20px;padding:0;position:absolute;left:400px;top:25px;width:56px;" />个
					</div>
				</div>
			</div>
		</fieldset>
	</form>
		<div class="form-group">
			<hr>
			<div class="col-md-offset-3 col-md-2">
				<a class="btn btn-default btn-block" href="${ctx}/mall/activityList"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary btn-block"
						id="submit_btn">
					<span class="glyphicon glyphicon-ok"></span> 保存
				</button>
			</div>
		</div>
  </div>

</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#mallctivity-man');
	// 低栏隐藏
	$("#adminFooter").hide();

	// 控件校验
 	$('#inputForm').validate({
		submitHandler : function(form) {
			//表单验证成功时，锁住提交按钮
			if ($("#goodsId").val().replace(",","") == ""){
				common.showMessage("请填选择参加活动商品！");
				return;
			}
			app.disabled("#submit_btn");
			//提交表单
			form.submit();
		},
		rules: {
			// 名称
			"startTime":{
				required:true,
			},
			// 手机号
			"endTime": {
				required: true,
			}
		},
		messages: {
		}
	});

	$("#submit_btn").click(function() {
		$("#inputForm").submit();
	});

	changeType();

	$("#ruleType").change(function(){
		changeType();
	});

});

 function changeType(){
	if($('#ruleType option:selected').val() == 1){
		$("#goodsDiv1").css("display","none");
		$("#goodsDiv2").css("display","block");
		$("#buyAmount1").addClass("required",true);
		$("#goodsName1").addClass("required",true);
		$("#giveAmount1").addClass("required",true);
		$("#buyAmount").removeClass("required");
		$("#goodId").removeClass("required");
		$("#giveAmount").removeClass("required");
	}else{
		$("#goodsDiv1").css("display","block");
		$("#goodsDiv2").css("display","none");
		$("#buyAmount").addClass("required",true);
		$("#goodId").addClass("required",true);
		$("#giveAmount").addClass("required",true);
		$("#buyAmount1").removeClass("required");
		$("#goodsName1").removeClass("required");
		$("#giveAmount1").removeClass("required");
	}
}
	function addgoods(type){
		$("#myDlgBody_lg").load("${ctx}/mall/allDnaGoods_dlg?type="+type+"&activityId="+$("#dataId").val()+"&goodsId="+$("#goodsId").val()+"&delGoodsId="+$("#delGoodsId").val());
		$("#myDlgBody_lg").css("width","900px");
		$("#myDlg_lg").modal();
	}

	function setGoodIds(type,v){
		var goodsId = $("#goodsId").val();
		for(var i=0;i<v.length;i++){
			var goodId =  v[i].split("_")[0];
			var goodName =  v[i].split("_")[1];
			if(goodsId!= null && goodsId != ""){
				goodsId += ","+goodId;
			}else{
				goodsId = goodId;
			}
			var newdiv = document.getElementById("goodNamesDiv");
			newdiv.innerHTML +=    "<p class=\"goodNamesP\"><input style=\"display: inline-block\" type=\"text\" value=\""+goodName+"\"  " +
					" id = \""+goodId+"\"   class=\"form-control\" name = \"goodNames\"><a  style=\"display: inline-block;position: absolute;\"" +
					" class=\"btn btn-primary btn-sm\" href=\"#\" onclick=\"delgoods(this,'"+goodId+"')\"><span class=\"glyphicon glyphicon-minus\">" +
					"</span></a></p>";
			}
		$("#delGoodsId").val($("#delGoodsId").val().replace(goodId,""));
		$("#goodsId").val(goodsId);
	}

	function delgoods(e,id){
		var goodsId = $("#goodsId").val();
		$("#delGoodsId").val($("#delGoodsId").val()+","+id);
		$("#goodsId").val(goodsId.replace(id,""));
		$("#"+id).parent().remove();
	}


///**
// * 获取相邻元素
// * @param ele 参考物元素
// * @param type 类型，上一个(1)or下一个(0)
// * @return 返回查找到的元素Dom对象，无则返回null
// */
//function getNearEle(ele, type) {
//	type = type == 1 ? "previousSibling" : "nextSibling";
//	var nearEle = ele[type];
//	while(nearEle) {
//		if(nearEle.nodeType === 1) {
//			return nearEle;
//		}
//		nearEle = nearEle[type];
//		if(!nearEle) {
//			break;
//		}
//	}
//	return null;
//}
</script>