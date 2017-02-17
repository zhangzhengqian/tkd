angular.module('crm.uploadify', []).directive('crmUploadify',[function(){
	return {  
        require: '?ngModel',  
        restrict: 'A',  
        link: function ($scope, element, attrs, ngModel) {
        	var opts = angular.extend({}, $scope.$eval(attrs.crmUploadify));
        	var num = 0;
            element.uploadify({  
                'fileObjName': 'upfile',  
                'swf':'/static/lib/plugins/uploadify/uploadify.swf',  
                'uploader':'/uploader',//图片上传方法  
                'buttonText':'选择图片',  
                'width':100,  
                'height':30,
                'multi':opts.multi||false,
                'queueSizeLimit':5,
                'fileTypeDesc': 'Image Files',
                'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
                'overrideEvents' : [ 'onDialogClose','onSelectError' ],
                'onSelectError':function(file, errorCode, errorMsg){
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
                     alert(msgText);
                 },
                'onUploadSuccess': function (file, d, response) {
                	var isMulti = opts.multi;
                    if (ngModel) {
                        var result = eval("[" + d + "]")[0];
                        if (result.success == true) {
                            $scope.$apply(function() {
                            	if(isMulti){
                            		num = num % 6 == 0 ? 0:num;
                            		var urlTemp = ngModel.$modelValue;
                                	if(!urlTemp){
                                		urlTemp = [];
                                	}
                                	urlTemp[num] = result.url;
                                	num++;
                            		ngModel.$setViewValue(urlTemp);  
                            	}else{
                            		ngModel.$setViewValue(result.url);  
                            	}
                            });  
                        }  
                    }  
                }  
            });
        }  
    };
}]);