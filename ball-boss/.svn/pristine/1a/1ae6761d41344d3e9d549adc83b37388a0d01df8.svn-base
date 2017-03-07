$(document).ready(function() {
		//$('#addDiv').removeClass('hide');
		//$('#addAlert').removeClass('hide');
		
		/* $('#addDnsButton').click(function() {
			debugger;
			$('#addDiv').removeClass('hide');
			$('#addAlert').removeClass('hide');
			switchAlertInfo('dl1','');
	    });
		 */
	
		
		$('.glyphicon-question-sign').click(function() {
			$(this).parent().parent()
			.next('.alert').slideToggle();
			});
		$('.noshow').click(function() {
			$(this).parent().parent().parent('tr').hide();
		});
		
		$('#addButton').click(function() {
			$('#addDiv').removeClass('hide');
			$('#addAlert').removeClass('hide');
			switchAlertInfo('dl1','');
			});
		
		$('#cancelButton').click(function() {
			$('#addDiv').addClass('hide');
			$('#addAlert').addClass('hide');
			});
	});
	/**
	点击编辑按钮触发事件
	**/
	function editById(id){
	debugger;
		$('#addDiv').addClass('hide');
		$('#addAlert').addClass('hide');
		$('#list_'+id).addClass('hide');
		$('#edit_'+id).removeClass('hide');
		$('#alert_'+id).removeClass('hide');
		doEditOption('recordTypes_'+id,'recType_'+id);//处理记录类型
		doEditOption('lines_'+id,'lineType_'+id);//处理解析线路
		doEditOption('mxPriortys_'+id,'mxResp_'+id);//处理优先级
		doEditOption('ttls_'+id,'recordTime_'+id);//处理记录时间

		    
		
	}
	/**
	点击删除按钮触发事件
	**/
	function deleteById(id){
		//var ctx=$('#ctx').val();
		var delDomainName=$('#delDomainName').val();
		bootbox.confirm('您确定要删除吗？', function(result) {
			  if(result) {
		      location=ctx+"/agent/domain/resolvingList/deleteDns/"+id+"?delDomainName="+delDomainName;
			  }
			});
	}
	
/**
 * 新增域名解析时切换提示信息
 */
function turnRecords(type){
	debugger;
   var record=$('#recType').val();
	if('recordDiv'==type){
		switchAlertInfo('dl1','');
		if(('CNAME'==record)||('NS'==record)){
		$('#mainMachRec').val('');
		}else{
			$('#mainMachRec').val('@');
		}
	}if('mainmachDiv'==type){
		if(('CNAME'==record)){
			debugger;
		    var specText="<font color=\"red\">【注意】CNAME记录的主机记录（RR值）不能为空。</font>";
		    $('#spec_div').html(specText);
		}else if(('NS'==record)){
			debugger;
		    var specText="<font color=\"red\">【注意】NS记录的主机记录（RR值）不能为空，且NS记录不支持泛解析（泛解析：将所有子域名解析到同一地址）。</font>";
		    $('#spec_div').html(specText);
		}else if(('TXT'==record)){
			debugger;
		    var specText="<font color=\"red\">【注意】TXT记录不支持泛解析（泛解析：将所有子域名解析到同一地址）。</font>";
		    $('#spec_div').html(specText);
		}else{
			 var specText="";
			    $('#spec_div').html(specText);
		}
		switchAlertInfo('dl2','');
	}if('lineDiv'==type){
		switchAlertInfo('dl3','');
	}if(('IpDomainDiv'==type)&&('CNAME'==record)){
		switchAlertInfo('dl5','');
	}if(('IpDomainDiv'==type)&&('A'==record)){
		switchAlertInfo('dl4','');
	}if(('IpDomainDiv'==type)&&('MX'==record)){
		debugger;
		switchAlertInfo('dl6','');
	}if(('IpDomainDiv'==type)&&('NS'==record)){
		debugger;
		switchAlertInfo('dl9','');
	}if(('IpDomainDiv'==type)&&('TXT'==record)){
		debugger;
		switchAlertInfo('dl10','');
	}
	
	if('maxDiv'==type){
		switchAlertInfo('dl7','');
	}if('timeDiv'==type){
		switchAlertInfo('dl8','');
	}
}

/**
 * 编辑域名解析时切换提示信息
 */
function turnRecord(type,id){
	
	var record=$('#recType_'+id).val();
	if('recordDiv'==type){
		switchAlertInfo('dl11',id);
	}if('mainmachDiv'==type){
		switchAlertInfo('dl12',id);
	}if('lineDiv'==type){
		switchAlertInfo('dl13',id);
	}if(('IpDomainDiv'==type)&&('CNAME'==record)){
		switchAlertInfo('dl15',id);
	}if(('IpDomainDiv'==type)&&('A'==record)){
		switchAlertInfo('dl14',id);
	}if(('IpDomainDiv'==type)&&('MX'==record)){
		debugger;
		switchAlertInfo('dl16',id);
	}
	
	if('maxDiv'==type){
		switchAlertInfo('dl17',id);
	}if('timeDiv'==type){
		switchAlertInfo('dl18',id);
	}
}


/**
点击删除按钮触发事件
**/
function batchDeleteDomain(id){
	debugger;
	var delDomainName=$('#delDomainName_'+id).val();
	var str=$('#str').val();
	bootbox.confirm('您确定要删除吗？', function(result) {
		  if(result) {
	      location=ctx+"/agent/domain/resolvingList/deleteDns/"+id+"?delDomainName="+delDomainName+"&str="+str;
		  }
		});
}
/**str
 * 控制的开关
 */
function switchAlertInfo(showId,id){

	var addArray="dl1,dl2,dl3,dl4,dl5,dl6,dl7,dl8,dl9,dl10,";
	var editArray="dl11,dl12,dl13,dl14,dl15,dl16,dl17,dl18,";
	   if(null==id||''==id){
		
		var str=addArray.replace((showId+","),"");
		var a=str.split(","); 
		var b=[]; 
		for(var i=0;i<a.length;i++)
		{
		b[i]=a[i];
		}
		var c=b.join(",");
		var d=c.split(","); 
		for(var j=0;j<d.length;j++){
			$('#'+showId).show();
			$('#'+d[j]).hide();
		}
	 } else {
			
			var str=editArray.replace((showId+","),"");
			var a=str.split(","); 
			var b=[]; 
			for(var i=0;i<a.length;i++)
			{
			b[i]=a[i];
			}
			var c=b.join(",");
			var d=c.split(","); 
			for(var j=0;j<d.length;j++){
				$('#'+showId+'_'+id).removeClass('hide');
				$('#'+d[j]+'_'+id).addClass('hide');
			}
		 }
	
	
}

/**
 * 新增时验证表单的提交
 */
function checkForm(){
	debugger;
	var recType=$('#recType').val();
	var mainMachRec=$('#mainMachRec').val();//主机记录值
	var domainName=$('#domainName').val();
	var lineType=$('#lineType').val();
	var recordVal=$('#recordVal').val();
	var recordTime=$('#recordTime').val()
	var recordTime=$('#mxResp').val();
	if(null==recordVal||''==recordVal){
	
		$('#recordVal').trigger("click");
		bootbox.alert('请输入记录值');
		$('#recordVal').focus();
		return false;
}if(null==mainMachRec||''==mainMachRec){
    if("CNAME"==recType||"NS"==recType){
	$('#mainMachRec').trigger("click");
	bootbox.alert('请输入主机记录(RR值)');
	$('#mainMachRec').focus();
	return false;
    }else{
      $('#mainMachRec').val('@');
    }
	
}else{
	debugger;
	if("A"==recType){
	if(f_check_IP(recordVal)){
		return true;
	}else{
		
		$('#recordVal').trigger("click");
		bootbox.alert('请输入正确的IP地址');
		$('#recordVal').focus();
		return false;
	}
	}else if("MX"==recType){
		if(f_chckDomain(recordVal)){
			return true;
		}
		else{
	
		$('#recordVal').trigger("click");
		bootbox.alert('请输入正确的域名');
		$('#recordVal').focus();
		return false;
		}
	}else if("NS"==recType){
	    if('@'==trim(mainMachRec)){
	    	$('#mainMachRec').trigger("click");
			bootbox.alert('(主机记录)RR的值中不能包含@字符');
			$('#mainMachRec').focus();
			return false;
	    }if('www'==trim(mainMachRec)){
	    	$('#mainMachRec').trigger("click");
			bootbox.alert('www与其他记录冲突,不能添加');
			$('#mainMachRec').focus();
			return false;
	    }
		if(f_chckDomain(recordVal)){
			return true;
		}else{
	    $('#recordVal').trigger("click");
		bootbox.alert('请输入正确的域名');
		$('#recordVal').focus();
		return false;
		}
	}else if("CNAME"==recType){
	    if(!checkCnameRR(mainMachRec)){
	    	$('#mainMachRec').trigger("click");
			bootbox.alert('(主机记录)RR的值中不能包含a--z,A--Z,0--9,*,_,中文汉字,以外的字符');
			$('#mainMachRec').focus();
			return false;
	    }
		if(f_chckDomain(recordVal)){
			return true;
		}else{
	    $('#recordVal').trigger("click");
		bootbox.alert('请输入正确的域名');
		$('#recordVal').focus();
		return false;
		}
	}else if("TXT"==recType){
		if("*"==mainMachRec){

			$('#mainMachRec').trigger("click");
			bootbox.alert('TXT记录时不支持泛解析');
			$('#mainMachRec').focus();
			return false;
		}
		else{
	
		return true;
		}
	}
	
	
}
	return true;

	
}
/**
 * 校验CNAME时主机的输入[单个的RR值中不能包含a--z、A--Z、0--9、'*'、'_'、'中文汉字'以外的字符]
 */
function checkCnameRR(value){
	 debugger;
	 field=trim(value);
	if(/^[\u0391-\uFFE5]+$/.test(field)||(/^[0-9]+$/.test(field))||(/^[A-Za-z0-9]+$/.test(field))||(/^[*_]+$/.test(field))) 
	{ 

	return true; //包含正确
	} 
	return false;//错误
}

/**
 * 修改时验证表单的提交
 */
function checkFormEditSave(id){
	debugger;
	var recType=$('#recType_'+id).val();
	var mainMachRec=$('#mainMachRec_'+id).val();
	var domainName=$('#domainName_'+id).val();
	var lineType=$('#lineType_'+id).val();
	var recordVal=$('#recordVal_'+id).val();
	var recordTime=$('#recordTime_'+id).val()
	var recordTime=$('#mxResp_'+id).val();
	if(null==recordVal||''==recordVal){
		$('#recordVal_'+id).trigger("click");
		$('#recordVal_'+id).focus();
		return false;
}else{
	if("MX"!=recType){
	if(f_check_IP(recordVal)){
		return true;
	}else{
		$('#recordVal_'+id).trigger("click");
		$('#recordVal_'+id).focus();
		return false;
	}
	}
	
}
	return true;
	
}


/**
 * 处理编辑域名解析时默认值的问题‘
 * @author Li.XiaoChao
 * @Date:2014-11-07
 * @param defalultHiddenValueId
 * @param selectId
 */
function doEditOption(defalultHiddenValueId,selectId){
	var defalultValue=$('#'+defalultHiddenValueId).val();//编辑之前的value
	var  defalultText;//编辑之前的text
	  var array = new Array(); //定义数组
	  var recType=document.getElementById(selectId);//得到下拉框的ID
	  var textTemp;
	  var valueTemp;
	  if(isNullStr(defalultValue)){//只有不为空的时候
	  for(var i=0;i<recType.length;i++){
		  textTemp=recType[i].text;
		  valueTemp=recType[i].value;
		 
		 array.push(valueTemp+'-'+textTemp); //添加到数组中
		  
	  }
	  $('#'+selectId).empty();//清空数组
	  var len=array.length;
	  var temp;
	  var textTemp1;
	  var valueTemp1;
	  for(var j = 0; j < len; j++){
		  temp=array[j];
		  valueTemp1=temp.split("-")[0];
		 textTemp1=temp.split("-")[1];
		 if(defalultValue==valueTemp1){
			 defalultText=textTemp1;//动态给默认的text赋值
		 }
		  var op = document.createElement("option");
		  op.setAttribute("value",valueTemp1);//赋value值
		  op.innerHTML=textTemp1;//赋text值
		  recType.appendChild(op);
		 }
	  $('#'+selectId).val(defalultValue);
	  //$('#'+selectId).append("<option value='"+defalultValue+"' selected='selected'>"+defalultText+"</option>"); 
	  }else{
		  return 
	  }
}


/**
 * 根据操作类型重新设置优先级的值
 * 如果是编辑时需要传递对应的ID
 * @param type
 * @param ID
 * @author Li.XiaoChao
 * @Date:2014-11-07
 */
function setValue(type,id){
	debugger;
	if('add'==type){//当是新增时开始
	var record=$('#recType').val();
	if("MX"==record){
      for(var i=1;i<=10;i++){
      $('#mxResp').append("<option value='"+i+"'>"+i+"</option>");  
      }
     //$("#mxResp option").eq(0).attr('selected', 'true');
	}else{
		$("#mxResp").empty();
	}
}//当是新增时结束
	
	
	if('edit'==type&&isNullStr(id)){//当是编辑时开始
		var record=$('#recType_'+id).val();
		if("MX"==record){
	      for(var i=1;i<=10;i++){
	      $('#mxResp_'+id).append("<option value='"+i+"'>"+i+"</option>");  
	   
	      }
	   $("#mxResp_"+id+"  option[value='']").remove(); //移除空的option
		}else{
			 $('#mxResp_'+id).empty();
		}
	}//当是编辑时结束	
	
	
}