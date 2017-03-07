<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球友圈管理</title>
	<style>
		.row{
			margin-top:10px;
		}
		.row input[type=radio]{
			margin-left:10px;
		}
		.img_close{
			position: relative;
			top: -110px;
			right: -115px;
			cursor: pointer;
			font-size: 25px;
			background-color: #FF6F00;
			height: 20px;
			width: 20px;
			border-radius: 50%;
			line-height: 20px;	
		}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 球友圈</li>
        <li class="active">球友圈管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/qiuyouzone/market/save" method="post">
		<div class="row">
			<div class="col-md-12">
		      发布信息
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      发布人
			</div>
			<div class="col-md-8">
		      <select name="userId">
		      <c:forEach items="${users}" var="user">
		      	<option value="${user.id }">${user.qiuyouno}-${user.phone }-${user.nickName }</option>
		      </c:forEach>
		      </select> (球友号－手机号－昵称)
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      图片内容
			</div>
			<div class="col-md-8">
		      <div class="has-feedback" id="photo_content">
					<input id="photoFile" type="file" multiple="false" />
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	           		<div style="float:left;margin-right:10px;">
						<input id="photo6" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img6"/>
	           		</div>
	           		<div style="float:left;margin-right:10px;">
						<input id="photo7" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img7"/>
	           		</div>
	           		<div style="float:left;margin-right:10px;">
						<input id="photo8" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img8"/>
	           		</div>
	           		<div style="float:left;margin-right:10px;">
						<input id="photo9" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img9"/>
	           		</div>
	         </div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      发布内容
			</div>
			<div class="col-md-6">
		      <textarea rows="5" name="content" class="form-control"></textarea>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      所在位置
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" id="statiumName" placeholder="请输入所在场馆">
			  <input  type="hidden" class="form-control input-sm" id="statiumId"  name="statiumId">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      标签
			</div>
			<div class="col-md-6">
		      <select name="labelId">
		      <c:forEach items="${labels}" var="label">
		      	<option value="${label.id }">${label.name}</option>
		      </c:forEach>
		      </select>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      发送时间
			</div>
			<div class="col-md-6 form-inline">
			<input type="radio"  name="sendType" value="1"> 立即发布
			<input type="radio"  name="sendType" value="0"> 延时发布
			<input type="text" id="sendTime" style="margin-left:10px;display:none;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH',firstDayOfWeek:1,minDate:'%y-%M-%d {%H+1}'})" class="form-control" name="tempTime">
			</div>
		</div>
		<hr>
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center">
   				<a href="${ctx }/qiuyouzone/market/list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 返回</a>
  	 				&nbsp;&nbsp;
   				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 保存</button>
 				</div>
		</div>
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyouzone_market-list');
	  $('#adminFooter').hide();
	  multipleUpload({'id':'photoFile','icon_img':'photo_img','icon':'photo','limit':9});  //
	  $("#photo_content").on("click",'.img_close',function(){
		  $(this).parent().find("input").val("");
		  $(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
		  $(this).remove();
	  });
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
		
		$("input[name=sendType]").on("click",function(e){
			var sendType = $(this).val();
			if(sendType==0){
				$("#sendTime").show();
			}else{
				$("#sendTime").hide();
			}
		})
  });
  
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
		    //按钮名称	
			buttonText    : '选择图片',
			height        : 30,
			swf           : ctx + '/static/uploadify/uploadify.swf',
		    //提交到指定的 controller,写死即可，已封装
		    uploader      : ctx + '/uploader',
			width         : 100,
			fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
			overrideEvents : [ 'onDialogClose','onSelectError' ],
			//上传成功后回调此函数
		    onUploadSuccess : function(file, data, response) {
		        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if(data.success==true){
					$("input[name="+icon+"]").each(function(i){
						var photo = $(this).val();
						if(!photo){
							var index = i+1;
							$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
							$('#'+ icon + index ).val(data.url);
							$('#'+ icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
							return false;
						}
					});
				}
		    },
		   onSelectError : uploadify_onSelectError,  
		});
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
