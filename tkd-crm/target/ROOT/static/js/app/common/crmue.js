angular.module('crm.ue', []).directive('ueditor', [function (uiLoad, $compile) {
    return {
        restrict: 'EA',
        require: '?ngModel',
        link: function (scope, element, attrs, ctrl) {
        	var _self = this,
            _initContent,
            editor,
            editorReady = false
	        var fexUE = {
	           initEditor: function () {
	               var _self = this;
	               if (typeof UE != 'undefined') {
	                   editor = new UE.ui.Editor({
	                       initialContent: _initContent,
	                       autoHeightEnabled: false,
	                       autoFloatEnabled: false
	                   });
	                   editor.render(element[0]);
	                   editor.ready(function () {
	                       editorReady = true;
	                       _self.setContent(_initContent);
	                       editor.addListener('contentChange', function () {
	                           scope.$apply(function () {
	                               ctrl.$setViewValue(editor.getContent());
	                           });
	                       });
	                   });
	               }
	           },
	           setContent: function (content) {
	               if (editor && editorReady) {
	                   editor.setContent(content);
	               }
	           }
	       };
	       if (!ctrl) {
	           return;
	       }
	       _initContent = ctrl.$viewValue;
	       ctrl.$render = function () {
	           _initContent = ctrl.$isEmpty(ctrl.$viewValue) ? '' : ctrl.$viewValue;
	           fexUE.setContent(_initContent);
	       };
	       fexUE.initEditor();
	   }
    };
}]);