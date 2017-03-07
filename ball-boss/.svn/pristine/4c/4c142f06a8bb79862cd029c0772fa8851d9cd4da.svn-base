<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>CTA-Open授权</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> CTA-Open授权管理</li>
        <li class="active">CTA-Open授权维护</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/statium/accredit/save" method="post" >
		<input name="id" type="hidden" value="${acc.id }">
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     场馆
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" id="statiumName" name="statiumName" value="${acc.statiumName}"  placeholder="场馆名称">
			  <input  type="hidden" class="form-control input-sm" id="statiumId"  name="statiumId"  value="${acc.statiumId }">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     场馆名称
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" id="statiumNames" name="statiumNames" value="${acc.statiumNames}"  placeholder="场馆名称">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     地区
			</div>
			<div class="col-md-6">
		     <input  type="text" class="form-control input-sm" id="area" name="area" value="${acc.area}"  placeholder="地区">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     授权编号
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" id="number" name="number" value="${acc.number}"  placeholder="授权编号">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     授权日期开始
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')}'})" id="start" name="start" value="${acc.start}"  placeholder="授权日期开始">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     授权日期结束
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" id="end" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\')}'})" name="end" value="${acc.end}"  placeholder="授权日期结束">
			</div>
		</div>
		<hr>
    </form>
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center">
 					<button type="botton" onclick="sub()" class="btn btn-primary btn-sm"> 保存</button> 
 					<button type="botton" class="btn btn-primary btn-sm back"> 取消</button> 
 				</div>
		</div>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#statium-accredit');
	  $('#adminFooter').hide();
		
	$(".back").on("click",function(){
		history.go(-1);
	})
	$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
		dataType:'json',
		minChars: 2,                   //最少输入字条
        max: 30,
        autoFill: false,
        mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
        matchContains: true, 
        scrollHeight: 220,
        width: $('#statiumName').outerWidth(),
        multiple: false,
        formatItem: function (row, i, max) {                    //显示格式
            return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
        },
        formatResult: function (row) {                      //返回结果
            return row.name;
        },
        handleValue:function(id){
        	$('#statiumId').val(id);
        },
        parse:function(data){
        	var parsed = [];
    		var rows = data;
    		for (var i=0; i < rows.length; i++) {
    			var row = rows[i];
    			if (row) {
    				parsed[parsed.length] = {
    					data: row,
    					value: row["id"],
    					result: this.formatResult(row)
    				};
    			}
    		}
    		return parsed;
        }
	});
  });
  
  function sub(){
	  if(!$("#statiumId").val()){
		  return;
	  }
	  if(!$("#statiumNames").val()){
		  return;
	  }
	  if(!$("#area").val()){
		  return;
	  }
	  if(!$("#number").val()){
		  return;
	  }
	  if(!$("#start").val()){
		  return;
	  }
	  if(!$("#end").val()){
		  return;
	  }
	  $("#actionForm").submit();
  }
  
</script>

</body>
</html>
