<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>教陪管理</title>
	<style type="text/css">
		#title div{margin-left: auto;margin-right: auto;}
		.blank {clear: both;height: 10px;line-height: 10px;visibility: hidden;}
		.img_close{position: relative;top: -110px;right: -115px;cursor: pointer;font-size: 25px;background-color: #FF6F00;height: 20px;width: 20px;border-radius: 50%;line-height: 20px;}
	</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
        <li>教陪管理</li>
        <li class="active">
          <c:if test="${'create' eq action }"> 新建教陪</c:if>
          <c:if test="${'update' eq action }"> 修改教陪</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
  <h3>教陪信息</h3>
  <hr>
  <div style="margin-left: 10%;">
  <form id="inputForm" action="${ctx}/ssouser/coach/${action}" method="post" class="form-horizontal">
	  <input id="id" name="id" value="${coach.id}" type="hidden"/>
	  <fieldset>
		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label"><span class="text-red">* </span>教练头像:</label>
	         <div class="col-md-5" id="coach_img">
	         	<c:if test="${'update' eq action}">
	         		<input id="photo1File" type="file" multiple="false" />
	         	<c:if test="${not empty coach.personStyle}">
        			
	         		<c:forEach items="${fn:split(coach.personStyle,';') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 1}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="photo${s.index + 1}" name="personStyles" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
	         				<div aria-hidden="true" class="img_close">&times;</div>
	         			</div>
	         		</c:forEach>
	         	</c:if>
	         		<c:forEach var="i" begin="${count+1}" end="8" step="1"> 
	         		<div style="float:left;margin-right:10px;">
        				<input id="photo${i}" name="personStyles" type="hidden" />
						<img alt="" src="" id="photo_img${i}" />
	         		</div>
					</c:forEach>
	         	</c:if>
	         	<c:if test="${'create' eq action}">
		         	<div class="has-feedback">
						<input id="photo1File" type="file" multiple="false" />
						<div style="float:left;margin-right:10px;">
							<input id="photo1" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img1"/>
						</div>
						<div style="float:left;margin-right:10px;">
							<input id="photo2" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img2"/>
						</div>
						<div style="float:left;margin-right:10px;">
							<input id="photo3" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img3"/>
						</div>
						<div style="float:left;margin-right:10px;">
							<input id="photo4" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img4"/>
						</div>
						<div style="float:left;margin-right:10px;">
							<input id="photo5" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img5"/>
		           		</div>
		           		<div style="float:left;margin-right:10px;">
							<input id="photo6" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img6"/>
		           		</div>
		           		<div style="float:left;margin-right:10px;">
							<input id="photo7" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img7"/>
		           		</div>
		           		<div style="float:left;margin-right:10px;">
							<input id="photo8" name="personStyles" type="hidden" />
							<img alt="" src="" id="photo_img8"/>
		           		</div>
		           	</div>				
	    		</c:if>
	         </div>
	      </div>
	      <input type="hidden" name="statiumIds" id="statiumIds" value="${coach.statiumIds}"/>
	      <input type="hidden" name="teachArea" id="teachArea" value="${coach.teachArea}"/>
	      <input type="hidden" name="resumeJsons" id="resumeJsons"value="${coach.resumeJsons}"/>
	      <%-- <input type="hidden" name="sportType" id="sportType" value="${coach.sportType}"/> --%>
	      <input type="hidden" name="oldPhone" value="${coach.phone}"/>
		
		<div class="form-group form-group-sm form-inline">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>教练姓名:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" placeholder="请填写教练姓名"  id="name" name="name" value="${coach.name}"  />
	       </div>
	    </div>
	    
	    <div class="form-group form-group-sm form-inline">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>教练简介:</label>
	       <div class="col-md-6 has-feedback">
	         <textarea class="form-control" style="height: 100px;" placeholder="请填写教练简介"  id="introduce" name="introduce">
	         ${coach.introduce}
	         </textarea>
	       </div>
	    </div>
	    
		<div class="form-group form-group-sm form-inline">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>注册手机号:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" placeholder="请填写教练手机号"  id="phone" name="phone" value="${coach.phone}" />
	       </div>
	    </div>
	    
	    <div class="form-group form-group-sm form-inline">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>联系手机号:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" placeholder="请填写教练手机号"  id="linkphone" name="linkphone" value="${coach.linkphone}" />
	       </div>
	    </div>
	    
      <div class="form-group form-group-sm">
         <label for=coach class="col-md-3 control-label"><span class="text-red">* </span>用户类型:${coach.userType}</label>
         <div class="col-md-6 has-feedback">
	          <div class="btn-group" data-toggle="buttons">
				  <label class="btn btn-default<c:if test="${coach.userType=='教练'}"> active abc</c:if>">
				    <input type="radio" name="userType" value="教练" <c:if test="${coach.userType=='教练'}">checked="checked"</c:if> autocomplete="off">教练
				  </label>
				  <label class="btn btn-default<c:if test="${coach.userType=='陪练'}"> active abc</c:if>">
				    <input type="radio" name="userType" value="陪练" <c:if test="${coach.userType=='陪练'}">checked="checked"</c:if> autocomplete="off">陪练
				  </label>
			 </div>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
	     <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>运动类别:</label>
         <div class="col-md-6 has-feedback">
         	<div class="btn-group">
         	<select class="form-control" id="sportType" name="sportType">
	           	<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
					<option <c:if test="${coach.sportType eq item.itemCode}">selected</c:if> value="${item.itemCode }">${item.itemName }</option>
		       	</c:forEach>
	       	</select>
			</div>
         </div>
	  </div>
	  
      <div class="form-group form-group-sm form-inline">
         <label for="teachingMethod" class="col-md-3 control-label">授课方式:</label>
         <div class="col-md-6 has-feedback">
		   <select class="form-control" id="teachingMethod" name="teachingMethod">		
				<option value="0" >一对一</option>
				<option value="1" >一对多</option>
			</select>
         </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="price" class="col-md-3 control-label"><span class="text-red">* </span>执教价格:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" placeholder="请填写执教价格"  id="price" name="price" value="${coach.price}"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for=sex class="col-md-3 control-label"><span class="text-red"> </span>* 教陪性别:</label>
         <div class="col-md-6 has-feedback">
	          <div class="btn-group" data-toggle="buttons">
				  <label class="btn btn-default<c:if test="${coach.sex=='男'}"> active abc</c:if>">
				    <input type="radio" name="sex" value="男" <c:if test="${coach.sex=='男'}"> checked="checked" </c:if> autocomplete="off">男
				  </label>
				  <label class="btn btn-default<c:if test="${coach.sex=='女'}"> active abc</c:if>">
				    <input type="radio" name="sex" value="女" <c:if test="${coach.sex=='女'}"> checked="checked" </c:if> autocomplete="off">女
				  </label>
			 </div>
         </div>
      </div>

	  <div class="form-group form-group-sm form-inline">
         <label for="birthday" class="col-md-3 control-label">出生日期:</label>
         <div class="col-md-6 has-feedback">
           <div class="input-group">
		   <input type="text" id="birthday" name="birthday" readOnly placeholder="请填写出生日期"  value="${coach.birthday}"
		   		class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		   <label for="birthday" class="input-group-addon"><i class="fa fa-calendar"></i></label>
		   </div>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
		<label for="area" class="col-md-3 control-label">所在地区：</label>
		<div class="col-md-6 has-feedback form-inline">
			<tags:zone id="area" name="area"  value="${coach.area }" />
	    </div>
	   </div>
            
      <div class="form-group form-group-sm form-inline">
         <label for="occType" class="col-md-3 control-label"><span class="text-red">* </span>职业类型:</label>
         <div class="col-md-6 has-feedback">
		   <select class="form-control" id="occType" name="occType">	
				<option <c:if test="${coach.occType=='驻场'}">selected</c:if> value="驻场" >驻场</option>
				<option <c:if test="${coach.occType=='个人'}">selected</c:if> value="个人" >个人</option>
			</select>
         </div>
      </div>
      
      <div id="statiumDiv" class="form-group form-group-sm form-inline" >
        <label for="stationed" class="col-md-3 control-label">驻场场馆:</label>
        <div class="col-md-6 has-feedback">
            <button type="button" id="stationed" class="btn btn-default">添加驻场场馆</button>
        </div>
     </div>
     
     <div class="form-group form-group-sm form-inline">
        <label for="areaCode" class="col-md-3 control-label">服务区域:</label>
        <div class="col-md-6 has-feedback">
		 	<select class="form-control" id="province">		
				<option value="" >--请选择--</option>
			</select>
			<select class="form-control" id="city">		
				<option value="" >--请选择--</option>
			</select>
        </div>
     </div>
     
     <div class="form-group form-group-sm form-inline" style="padding-left:20px;">
        <div id="areaStr" class="col-md-offset-3 col-md-6 has-feedback table-bordered" style="min-height:100px;width:40%;padding-top:10px;padding-bottom:10px;">
		 	
        </div>
     </div>
     
     <div class="zhcStatiumwrap form-group form-group-sm form-inline">
        <div id="zhcStatium" class="col-md-offset-3 col-md-6 has-feedback">

        </div>
     </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="coachLevel" class="col-md-3 control-label">教陪级别:</label>
         <div class="col-md-6 has-feedback">
		   <select class="form-control" id="coachLevel" name="coachLevel">	
		        <option <c:if test="${coach.coachLevel==''}">selected</c:if> value="">请选则教陪级别</option>	
				<option <c:if test="${coach.coachLevel=='初级教练'}">selected</c:if> value="初级教练" >初级教练</option>
				<option <c:if test="${coach.coachLevel=='中级教练'}">selected</c:if> value="中级教练" >中级教练</option>
				<option <c:if test="${coach.coachLevel=='高级教练'}">selected</c:if> value="高级教练" >高级教练</option>
				<option <c:if test="${coach.coachLevel=='金牌教练'}">selected</c:if> value="金牌教练" >金牌教练</option>
			</select>
         </div>
      </div>

      <div class="form-group form-group-sm form-inline">
         <label for="trainingYears" class="col-md-3 control-label">执教年龄:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" style="width:26%;" placeholder="请填写执教年龄" maxlength="2"  id="trainingYears" name="trainingYears" value="${coach.trainingYears}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="startTime" class="col-md-3 control-label"><span class="text-red">* </span>教习时间:</label>
         <div class="col-md-6 has-feedback">
           <div class="input-group">
	           <div class="row">
	           		  <div class="col-md-6">
					   	   <input type="text" id="startTime" name="startTime" readOnly placeholder="教习开始时间" value="${coach.startTime}" class="form-control" onclick="WdatePicker({dateFmt:'HH:00'})"/>
					   	   <label for="startTime" class="input-group-addon" style="height:30px;"><i class="fa fa-calendar"></i></label>
					  </div>
					  <div class="col-md-6">
					       <input type="text" id="endTime" name="endTime" readOnly placeholder="教习结束时间" value="${coach.endTime}" class="form-control" onclick="WdatePicker({dateFmt:'HH:00'})"/>
			               <label for="endTime" class="input-group-addon" style="height:30px;"><i class="fa fa-calendar"></i></label>
		         	  </div>
	         	</div>
		   </div>
         </div>
      </div>    
      
      <div class="form-group form-group-sm form-inline">
         <label for="payee" class="col-md-3 control-label">收款人:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" style="width:26%;" placeholder="请填写收款人姓名" id="payee" name="payee" value="${coach.payee }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="cardNo" class="col-md-3 control-label">银行卡号:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" style="width:45%;" placeholder="请填写银行卡号" id="cardNo" name="cardNo" value="${coach.cardNo }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="bankName" class="col-md-3 control-label">开户银行:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" style="width:45%;" placeholder="请填写开户银行全称" id="bankName" name="bankName" value="${coach.bankName }" />
         </div>
      </div>      

      <div class="form-group form-group-sm form-inline">
         <label for="bankCity" class="col-md-3 control-label">开户行所在城市:</label>
		 <div class="col-md-6 has-feedback form-inline">
			<input type="text" class="form-control" style="width:45%;" placeholder="请填写开户银行城市" id="bankCity" name="bankCity" value="${coach.bankCity }" />
	     </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
       	 <label class="col-md-3 control-label"></label>
       	 <div class="col-md-6 has-feedback" id="resumesDiv">
       	 
       	 </div>
     </div>
     
     <textarea id="resumesTemplate" style="display:none">
       		 <!-- <table class="table table-bordered table-striped table-hover" >
            	<thead>
					<tr>
						<th style="border-bottom-width: 0px;">教习开始时间</th>
						<th style="border-bottom-width: 0px;">教习结束时间</th>
						<th style="border-bottom-width: 0px;">执教经历内容</th>
						<th style="border-bottom-width: 0px;">操作</th>
					</tr>
				</thead>
				<tbody>
					{#foreach $T.table as record} 
						<tr>
							<td>{$T.record.startTimeStr}</td>
							<td>{$T.record.endTimeStr}</td>
							<td>{$T.record.resume}</td>
							<td style="text-align: center"><a onclick="updateResume({$T.record$index})" class="btn btn-default btn-sm btn-update">修改</a>&nbsp;<a onclick="deleteResume({$T.record$index})" class="btn btn-default btn-sm btn-delete">删除</a></td>
						</tr>
					{#/for}  
				</tbody>
	        </table> -->
	      </textarea>
      
      <div class="form-group form-group-sm">
         <label for="price" class="col-md-3 control-label">身份证号:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" placeholder="请填写教练身份证"  id="cardId" name="cardId" value="${coach.cardId}"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
		<label for="icon_upload_phoneFront" class="col-md-3 control-label">身份证正面照:</label>
	    <div class="col-md-6 has-feedback">
	    	<input type="file" class="form-control" id="icon_upload_phoneFront" name="icon_upload_phoneFront" multiple="false" />
	    	<input type="hidden" id="phoneFront" name="phoneFront" value="${coach.phoneFront}"/>
	    	<img alt="" style="<c:if test='${!empty coach.phoneFront}'>width:128px;height:128px;</c:if>" src="${coach.phoneFront}" id="icon_img_phoneFront" >
	    </div>
	 </div>
	 
	<div class="form-group form-group-sm">
		<label for="icon_upload_phoneBack" class="col-md-3 control-label">身份证反面照:</label>
	    <div class="col-md-6 has-feedback">
	    	<input type="file" class="form-control" id="icon_upload_phoneBack" name="icon_upload_phoneBack" multiple="false" />
	    	<input type="hidden" id="phoneBack" name="phoneBack" value="${coach.phoneBack}"/>
	    	<img style="<c:if test='${!empty coach.phoneBack}'>width:128px;height:128px;</c:if>" alt="" src="${coach.phoneBack}" id="icon_img_phoneBack" >
	    </div>
	</div>
      
		</fieldset>
	</form>
	</div>
	<div class="form-group">
	 	<hr>
		<div class="col-md-offset-3 col-md-2">
		   <a class="btn btn-default btn-block" href="${ctx}/ssouser/coach"><span class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>
		<div class="col-md-2">
		  <button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
		</div>
	</div>
	
</div>
</div><!-- end row -->	
</div>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
var statiumIds = "";
var resumes = [];
var resumeIndex=null;
var areaCodes = "";
$(function() {
	menu.active('#coach-man');
	if('create'!='${action}'){
		if('${coach.resumeJsons}'!=''){
			resumes = eval('${coach.resumeJsons}');
			resumeCallBack(resumes);
		}
		if('${coach.statiumViews}'!=''){
			var statiumViews = '${coach.statiumViews}'.split(";;");
			for(var i=0;i<statiumViews.length;i++){
				modalCallBack(statiumViews[i]);
			}
		}
	}
	
	var zoneTree = null;
	var zoneMap = null;
	var zoneTreePromise = $.getJSON("${ctx}/static/js/zoneTree.json");
	var zoneMapPromise = $.getJSON("${ctx}/static/js/zoneMap.json");
	$.when(zoneTreePromise,zoneMapPromise).then(function(data1,data2){
		zoneTree = data1[0];
		zoneMap = data2[0];
		var provinces = zoneTree["000000"].sort();
		$.each(provinces,function(index,val){
			$("#province").append("<option value="+val+">"+zoneMap[val]+"</option>")
		})
		if('create'!='${action}'){
			areaCodes = '${coach.teachArea}';
			if(areaCodes==""){
				return;
			}
			var areaCodeArray = areaCodes.split(";;");
			var teachArea = areaCodeArray[0];
			var provinceSel = teachArea.substring(0,teachArea.length-4)+"0000";
			var citySel = teachArea.substring(0,teachArea.length-2)+"00";
			$("#province").val(provinceSel);
			$("#city").empty().append("<option value=''>--请选择--</option>");
			$.each(zoneTree[provinceSel],function(index,val){
				$("#city").append("<option value="+val+">"+zoneMap[val]+"</option>")
			})
			$("#city").val(citySel);
			
			$.each(zoneTree[citySel],function(index,val){
				if(areaCodes.indexOf(val)!=-1){
					$("#areaStr").append("<label class='btn btn-default active' style='margin-right:2px;margin-bottom:2px;width:200px;' val="+val+">"+zoneMap[val]+"</label>")
				}else{
					$("#areaStr").append("<label class='btn btn-default' style='margin-right:2px;margin-bottom:2px;width:200px;' val="+val+">"+zoneMap[val]+"</label>")
				}
			});
		}
	});
	 if($("#occType").val()=="个人"){
		 $("#statiumDiv").hide();
		 $(".zhcStatiumwrap").hide();
	  }
	$("#province").bind("change",function(){
		if($(this).val()==""){
			$("#city").empty().append("<option value=''>--请选择--</option>");
			$("#areaStr").empty();
			$("#teachArea").val("");
			return;
		}
		var citys = zoneTree[$(this).val()];
		$("#city").empty().append("<option value=''>--请选择--</option>");
		$.each(citys,function(index,val){
			$("#city").append("<option value="+val+">"+zoneMap[val]+"</option>")
		})
	});
	
	$("#city").bind("change",function(){
		if($(this).val()==""){
			$("#areaStr").empty();
			$("#teachArea").val("");
			return;
		}
		var areas = zoneTree[$(this).val()];
		$("#areaStr").empty();
		$.each(areas,function(index,val){
			$("#areaStr").append("<label class='btn btn-default' style='margin-right:2px;margin-bottom:2px;width:200px;' val="+val+">"+zoneMap[val]+"</label>")
		})
	});
	
	$("#areaStr").on("click",".btn",function(){
		var areacode = $(this).attr("val");
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			areaCodes = areaCodes.replace(areacode+";;","");
			$("#teachArea").val(areaCodes);
		}else{
			$(this).addClass("active");
			areaCodes+=areacode+";;";
			$("#teachArea").val(areaCodes);
		}
	});
	
	
	$("#coach_img").on("click",'.img_close',function(){
		$(this).parent().find("input").val("");
		$(this).parent().find("img").removeAttr("src").removeAttr("height").removeAttr("width");
		$(this).remove();
	});
	
	$("#zhcStatium").on("click",".close",function(){
		var that = $(this);
		that.parent().hide(500);
		statiumIds = statiumIds.replace(that.attr("id")+";;","");
		$("#statiumIds").val(statiumIds);
	});
	
	$("#stationed").click(function() {
		$("#myDlgBody_lg").load("${ctx}/common/search_statium_dlg");
		$("#myDlg_lg").modal();
	});
	
	multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':8});
	
	//
	$("#addResume").click(function(){
		resumeIndex = null;
		$("#myDlgBody_lg").load("${ctx}/common/init_resume__dlg");
		$("#myDlg_lg").modal();
	});
	
	var ids = ["icon_upload","icon_upload_phoneFront","icon_upload_phoneBack"];
	  $.each(ids,function(n,value) {  
			$("#"+value).uploadify({
				fileObjName   : 'upfile',
				buttonText    : '选择图片',
				height        : 30,
				multi         :false,
				fileSizeLimit : 2*1024,
	            fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
				swf: '${ctx}/static/uploadify/uploadify.swf',
			    uploader: '${ctx}/uploader;jsessionid='+'${pageContext.session.id}',
				width: 100,
			    onUploadSuccess : function(file, data, response) {
					data = JSON.parse(data);
					if(data.success==true){
					    $("#"+value).parent().find("input[type=hidden]").val(data.url);
						$("#"+value).parent().find("img").attr('src',data.url);
						$("#"+value).parent().find("img").css({width:"243px",height:"153px"});
						$('#photo').val(data.url);
					}
			       },onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {
			    	   this.queueData.errorMsg = '上传失败';
			       },onSelectError : function(file,errorCode,errorMsg) {
			    	   switch(errorCode){
			    	   case -110:
			    		   this.queueData.errorMsg = '请上传不超过2M的图片';
			    	   }
			       }
			});
      });  
      
	  $("#occType").change(function(){
		  if($("#occType").val()=="个人"){
			 $("#statiumDiv").hide();
			 $(".zhcStatiumwrap").hide();
		  }else{
			 $("#statiumDiv").show();
			 $(".zhcStatiumwrap").show();
		  }
	  })
	  
      $("#submit_btn").click(function(){
    	  var resumeJsons = JSON.stringify(resumes);
    	  $("#resumeJsons").val(resumeJsons);
		  $("#inputForm").submit();
	  });

	  $('#inputForm').validate({
	    ignore: "",
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮
			app.disabled("#submit_btn");
			//提交表单
			form.submit(); 
		},
		rules: {
			"phone" : {
				required: true,
				isMobile: true
	            ,remote: "${ctx}/ssouser/coach/checkPhone?oldPhone=" + encodeURIComponent('${coach.phone}')
			},
			"linkphone" : {
				required: true,
				isMobile: true
			},
			"name" : {
				required: true,
				minlength: 2,
				maxlength: 15
			},
/* 			"nickName" : {
				minlength: 3,
				maxlength: 15
			}, */
			"sign" : {
				maxlength: 50
			},
			"userType" : {
				required: true
			},
			"occType" : {
				required: true
			},
			"sex" : {
				required: true
			},
/* 			"birthday" : {
				required: true
			}, */
			"school" : {
				maxlength: 20
			},
			"bestRace" : {
				maxlength: 20
			},
			"trainingYears" : {
				digits: true
			},
			"cardId" : {
				/* required: true, */
				isIdCard: true
			},
			"introduce" : {
				maxlength: 3000
			},
			"signatory" : {
				required: true
			},
/* 			"sportTypes" : {
			    required: true
			}, */
			"photo":{
				required: true
			},
			"startTime" : {
				required: true
			},
			"endTime" : {
				required: true
			},
			"price" : {
				required: true,
				isMoney: true
			},
			"area":{
				required: true
			}
		},
		messages: {
			phone: {
				remote: '手机号已经存在，请重新输入！'
			}
		}
    });
	$('#adminFooter').hide();
});

function modalCallBack(data){
	var statiums = data.split("_");
	if(statiumIds.indexOf(statiums[0])!=-1){
		return;
	}
	statiumIds+=statiums[0]+";;";
	$("#zhcStatium").append("<div class='alert alert-success col-md-3'>"+
							"<button type='button' id='"+statiums[0]+"' class='close'>&times;</button>"+statiums[1]+"</div>");
	$("#statiumIds").val(statiumIds);
}

function resumeCallBack(){
	$("#resumesDiv").setTemplateElement("resumesTemplate");
    // 给模板加载数据
    var table = {table:resumes};
    $("#resumesDiv").processTemplate(table);
}
	
function multipleUpload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	var limit = option.limit || 1;
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
		queueSizeLimit: limit,
		multi         :true,
		buttonText    : '选择图片', //按钮名称
		height        : 30,
		swf           : ctx + '/static/uploadify/uploadify.swf',
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploader',
		width         : 100,
		overrideEvents : [ 'onDialogClose','onSelectError' ],
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$("input[name=personStyles]").each(function(i){
					var photo = $(this).val();
					if(!photo){
						var index = i+1;
						$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
						$('#'+ icon + index ).val(data.url);
						$('#'+ icon + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
						return false;
					}
				});
			}
	    },
	   onSelectError : uploadify_onSelectError,  
	});
}

function updateResume(index){
	resumeIndex = index;
	$("#myDlgBody_lg").load("${ctx}/common/init_resume__dlg");
	$("#myDlg_lg").modal();
}
function deleteResume(index){
	resumes.splice(index,1);
	if(resumes.length==0){
		$("#resumesDiv").html("");
	}else{
		$("#resumesDiv").setTemplateElement("resumesTemplate");
	    // 给模板加载数据
	    var table = {table:resumes};
	    $("#resumesDiv").processTemplate(table);
	}
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
      var msgText = "上传失败\n";
      switch (errorCode) {
          case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
              //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
              msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
              break;
          case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
              msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
              break;
          case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
              msgText += "文件大小为0";
              break;
          case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
              msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
              break;
          default:
              msgText += "错误代码：" + errorCode + "\n" + errorMsg;
      }
      myAlert(msgText);	
};	
</script>
</body>
</html>