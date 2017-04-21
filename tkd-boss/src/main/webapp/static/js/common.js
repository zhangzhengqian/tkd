var common = {};
common.debug = true;

common.log = function(msg){
	if (window.console && window.console.log && common.debug) {
		window.console.log(msg);
	}
}

common.hideModal = function(mId) {
	$(mId).modal('hide');
}

common.showModal = function(mId, _backdrop) {
	var _backdrop = _backdrop ? _backdrop : 'static';
	$(mId).modal({backdrop:_backdrop});
}

/**
 * jGrowl 1.2.12
 *
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php)
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *
 * Written by Stan Lemon <stosh1985@gmail.com>
 * Last updated: 2013.02.14
 *
 * jGrowl is a jQuery plugin implementing unobtrusive userland notifications.  These
 * notifications function similarly to the Growl Framework available for
 * Mac OS X (http://growl.info).
 *
 **/
!function(e){var t=function(){return!1===e.support.boxModel&&e.support.objectAll&&e.support.leadingWhitespace}();e.jGrowl=function(t,i){0==e("#jGrowl").size()&&e('<div id="jGrowl"></div>').addClass(i&&i.position?i.position:e.jGrowl.defaults.position).appendTo("body"),e("#jGrowl").jGrowl(t,i)},e.fn.jGrowl=function(t,i){if(e.isFunction(this.each)){var o=arguments;return this.each(function(){void 0==e(this).data("jGrowl.instance")&&(e(this).data("jGrowl.instance",e.extend(new e.fn.jGrowl,{notifications:[],element:null,interval:null})),e(this).data("jGrowl.instance").startup(this)),e.isFunction(e(this).data("jGrowl.instance")[t])?e(this).data("jGrowl.instance")[t].apply(e(this).data("jGrowl.instance"),e.makeArray(o).slice(1)):e(this).data("jGrowl.instance").create(t,i)})}},e.extend(e.fn.jGrowl.prototype,{defaults:{pool:0,header:"",group:"",sticky:!1,position:"top-right",glue:"after",theme:"default",themeState:"highlight",corners:"10px",check:250,life:3e3,closeDuration:"normal",openDuration:"normal",easing:"swing",closer:!0,closeTemplate:"&times;",closerTemplate:"<div>[ close all ]</div>",log:function(){},beforeOpen:function(){},afterOpen:function(){},open:function(){},beforeClose:function(){},close:function(){},animateOpen:{opacity:"show"},animateClose:{opacity:"hide"}},notifications:[],element:null,interval:null,create:function(t,i){var i=e.extend({},this.defaults,i);i.speed!==void 0&&(i.openDuration=i.speed,i.closeDuration=i.speed),this.notifications.push({message:t,options:i}),i.log.apply(this.element,[this.element,t,i])},render:function(t){var i=this,o=t.message,n=t.options;n.themeState=""==n.themeState?"":"ui-state-"+n.themeState;var t=e("<div/>").addClass("jGrowl-notification "+n.themeState+" ui-corner-all"+(void 0!=n.group&&""!=n.group?" "+n.group:"")).append(e("<div/>").addClass("jGrowl-close").html(n.closeTemplate)).append(e("<div/>").addClass("jGrowl-header").html(n.header)).append(e("<div/>").addClass("jGrowl-message").html(o)).data("jGrowl",n).addClass(n.theme).children("div.jGrowl-close").bind("click.jGrowl",function(){e(this).parent().trigger("jGrowl.beforeClose")}).parent();e(t).bind("mouseover.jGrowl",function(){e("div.jGrowl-notification",i.element).data("jGrowl.pause",!0)}).bind("mouseout.jGrowl",function(){e("div.jGrowl-notification",i.element).data("jGrowl.pause",!1)}).bind("jGrowl.beforeOpen",function(){n.beforeOpen.apply(t,[t,o,n,i.element])!==!1&&e(this).trigger("jGrowl.open")}).bind("jGrowl.open",function(){n.open.apply(t,[t,o,n,i.element])!==!1&&("after"==n.glue?e("div.jGrowl-notification:last",i.element).after(t):e("div.jGrowl-notification:first",i.element).before(t),e(this).animate(n.animateOpen,n.openDuration,n.easing,function(){e.support.opacity===!1&&this.style.removeAttribute("filter"),null!==e(this).data("jGrowl")&&(e(this).data("jGrowl").created=new Date),e(this).trigger("jGrowl.afterOpen")}))}).bind("jGrowl.afterOpen",function(){n.afterOpen.apply(t,[t,o,n,i.element])}).bind("jGrowl.beforeClose",function(){n.beforeClose.apply(t,[t,o,n,i.element])!==!1&&e(this).trigger("jGrowl.close")}).bind("jGrowl.close",function(){e(this).data("jGrowl.pause",!0),e(this).animate(n.animateClose,n.closeDuration,n.easing,function(){e.isFunction(n.close)?n.close.apply(t,[t,o,n,i.element])!==!1&&e(this).remove():e(this).remove()})}).trigger("jGrowl.beforeOpen"),""!=n.corners&&void 0!=e.fn.corner&&e(t).corner(n.corners),e("div.jGrowl-notification:parent",i.element).size()>1&&0==e("div.jGrowl-closer",i.element).size()&&this.defaults.closer!==!1&&e(this.defaults.closerTemplate).addClass("jGrowl-closer "+this.defaults.themeState+" ui-corner-all").addClass(this.defaults.theme).appendTo(i.element).animate(this.defaults.animateOpen,this.defaults.speed,this.defaults.easing).bind("click.jGrowl",function(){e(this).siblings().trigger("jGrowl.beforeClose"),e.isFunction(i.defaults.closer)&&i.defaults.closer.apply(e(this).parent()[0],[e(this).parent()[0]])})},update:function(){e(this.element).find("div.jGrowl-notification:parent").each(function(){void 0!=e(this).data("jGrowl")&&void 0!==e(this).data("jGrowl").created&&e(this).data("jGrowl").created.getTime()+parseInt(e(this).data("jGrowl").life)<(new Date).getTime()&&e(this).data("jGrowl").sticky!==!0&&(void 0==e(this).data("jGrowl.pause")||e(this).data("jGrowl.pause")!==!0)&&e(this).trigger("jGrowl.beforeClose")}),this.notifications.length>0&&(0==this.defaults.pool||e(this.element).find("div.jGrowl-notification:parent").size()<this.defaults.pool)&&this.render(this.notifications.shift()),2>e(this.element).find("div.jGrowl-notification:parent").size()&&e(this.element).find("div.jGrowl-closer").animate(this.defaults.animateClose,this.defaults.speed,this.defaults.easing,function(){e(this).remove()})},startup:function(i){this.element=e(i).addClass("jGrowl").append('<div class="jGrowl-notification"></div>'),this.interval=setInterval(function(){e(i).data("jGrowl.instance").update()},parseInt(this.defaults.check)),t&&e(this.element).addClass("ie6")},shutdown:function(){e(this.element).removeClass("jGrowl").find("div.jGrowl-notification").trigger("jGrowl.close").parent().empty(),clearInterval(this.interval)},close:function(){e(this.element).find("div.jGrowl-notification").each(function(){e(this).trigger("jGrowl.beforeClose")})}}),e.jGrowl.defaults=e.fn.jGrowl.prototype.defaults}(jQuery);

common.showMessage = function(msg, flag) {
	var opts = {life: 10000};
	var warn = (flag != undefined && 'warn' === flag) ? true : false;
	if (warn) {
		opts.theme = 'warning';
		$.jGrowl(msg, opts);
	} else {
		$.jGrowl(msg, opts);
	}
}



/**
 * jQuery blockUI plugin
 * Version 2.64.0-2013.07.18
 * @requires jQuery v1.7 or later
 *
 * Examples at: http://malsup.com/jquery/block/
 * Copyright (c) 2007-2013 M. Alsup
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Thanks to Amir-Hossein Sobhi for some excellent contributions!
 **/
!function(){"use strict";function a(a){function i(e,i){var k,m,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,q=e==window,r=i&&void 0!==i.message?i.message:void 0;i=a.extend({},a.blockUI.defaults,i||{}),i.ignoreIfBlocked&&a(e).data("blockUI.isBlocked")||(i.overlayCSS=a.extend({},a.blockUI.defaults.overlayCSS,i.overlayCSS||{}),k=a.extend({},a.blockUI.defaults.css,i.css||{}),i.onOverlayClick&&(i.overlayCSS.cursor="pointer"),m=a.extend({},a.blockUI.defaults.themedCSS,i.themedCSS||{}),r=void 0===r?i.message:r,q&&g&&j(window,{fadeOut:0}),r&&"string"!=typeof r&&(r.parentNode||r.jquery)&&(s=r.jquery?r[0]:r,t={},a(e).data("blockUI.history",t),t.el=s,t.parent=s.parentNode,t.display=s.style.display,t.position=s.style.position,t.parent&&t.parent.removeChild(s)),a(e).data("blockUI.onUnblock",i.onUnblock),u=i.baseZ,v=c||i.forceIframe?a('<iframe class="blockUI" style="z-index:'+u++ +';display:none;border:none;margin:0;padding:0;position:absolute;width:100%;height:100%;top:0;left:0" src="'+i.iframeSrc+'"></iframe>'):a('<div class="blockUI" style="display:none"></div>'),w=i.theme?a('<div class="blockUI blockOverlay ui-widget-overlay" style="z-index:'+u++ +';display:none"></div>'):a('<div class="blockUI blockOverlay" style="z-index:'+u++ +';display:none;border:none;margin:0;padding:0;width:100%;height:100%;top:0;left:0"></div>'),i.theme&&q?(y='<div class="blockUI '+i.blockMsgClass+' blockPage ui-dialog ui-widget ui-corner-all" style="z-index:'+(u+10)+';display:none;position:fixed">',i.title&&(y+='<div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'+(i.title||"&nbsp;")+"</div>"),y+='<div class="ui-widget-content ui-dialog-content"></div>',y+="</div>"):i.theme?(y='<div class="blockUI '+i.blockMsgClass+' blockElement ui-dialog ui-widget ui-corner-all" style="z-index:'+(u+10)+';display:none;position:absolute">',i.title&&(y+='<div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'+(i.title||"&nbsp;")+"</div>"),y+='<div class="ui-widget-content ui-dialog-content"></div>',y+="</div>"):y=q?'<div class="blockUI '+i.blockMsgClass+' blockPage" style="z-index:'+(u+10)+';display:none;position:fixed"></div>':'<div class="blockUI '+i.blockMsgClass+' blockElement" style="z-index:'+(u+10)+';display:none;position:absolute"></div>',x=a(y),r&&(i.theme?(x.css(m),x.addClass("ui-widget-content")):x.css(k)),i.theme||w.css(i.overlayCSS),w.css("position",q?"fixed":"absolute"),(c||i.forceIframe)&&v.css("opacity",0),z=[v,w,x],A=q?a("body"):a(e),a.each(z,function(){this.appendTo(A)}),i.theme&&i.draggable&&a.fn.draggable&&x.draggable({handle:".ui-dialog-titlebar",cancel:"li"}),B=f&&(!a.support.boxModel||a("object,embed",q?null:e).length>0),(d||B)&&(q&&i.allowBodyStretch&&a.support.boxModel&&a("html,body").css("height","100%"),!d&&a.support.boxModel||q||(C=p(e,"borderTopWidth"),D=p(e,"borderLeftWidth"),E=C?"(0 - "+C+")":0,F=D?"(0 - "+D+")":0),a.each(z,function(a,b){var d,e,c=b[0].style;c.position="absolute",2>a?(q?c.setExpression("height","Math.max(document.body.scrollHeight, document.body.offsetHeight) - (jQuery.support.boxModel?0:"+i.quirksmodeOffsetHack+') + "px"'):c.setExpression("height",'this.parentNode.offsetHeight + "px"'),q?c.setExpression("width",'jQuery.support.boxModel && document.documentElement.clientWidth || document.body.clientWidth + "px"'):c.setExpression("width",'this.parentNode.offsetWidth + "px"'),F&&c.setExpression("left",F),E&&c.setExpression("top",E)):i.centerY?(q&&c.setExpression("top",'(document.documentElement.clientHeight || document.body.clientHeight) / 2 - (this.offsetHeight / 2) + (blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "px"'),c.marginTop=0):!i.centerY&&q&&(d=i.css&&i.css.top?parseInt(i.css.top,10):0,e="((document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "+d+') + "px"',c.setExpression("top",e))})),r&&(i.theme?x.find(".ui-widget-content").append(r):x.append(r),(r.jquery||r.nodeType)&&a(r).show()),(c||i.forceIframe)&&i.showOverlay&&v.show(),i.fadeIn?(G=i.onBlock?i.onBlock:b,H=i.showOverlay&&!r?G:b,I=r?G:b,i.showOverlay&&w._fadeIn(i.fadeIn,H),r&&x._fadeIn(i.fadeIn,I)):(i.showOverlay&&w.show(),r&&x.show(),i.onBlock&&i.onBlock()),l(1,e,i),q?(g=x[0],h=a(i.focusableElements,g),i.focusInput&&setTimeout(n,20)):o(x[0],i.centerX,i.centerY),i.timeout&&(J=setTimeout(function(){q?a.unblockUI(i):a(e).unblock(i)},i.timeout),a(e).data("blockUI.timeout",J)))}function j(b,c){var d,m,e=b==window,f=a(b),i=f.data("blockUI.history"),j=f.data("blockUI.timeout");j&&(clearTimeout(j),f.removeData("blockUI.timeout")),c=a.extend({},a.blockUI.defaults,c||{}),l(0,b,c),null===c.onUnblock&&(c.onUnblock=f.data("blockUI.onUnblock"),f.removeData("blockUI.onUnblock")),m=e?a("body").children().filter(".blockUI").add("body > .blockUI"):f.find(">.blockUI"),c.cursorReset&&(m.length>1&&(m[1].style.cursor=c.cursorReset),m.length>2&&(m[2].style.cursor=c.cursorReset)),e&&(g=h=null),c.fadeOut?(d=m.length,m.stop().fadeOut(c.fadeOut,function(){0===--d&&k(m,i,c,b)})):k(m,i,c,b)}function k(b,c,d,e){var g,h,i,f=a(e);f.data("blockUI.isBlocked")||(b.each(function(){this.parentNode&&this.parentNode.removeChild(this)}),c&&c.el&&(c.el.style.display=c.display,c.el.style.position=c.position,c.parent&&c.parent.appendChild(c.el),f.removeData("blockUI.history")),f.data("blockUI.static")&&f.css("position","static"),"function"==typeof d.onUnblock&&d.onUnblock(e,d),g=a(document.body),h=g.width(),i=g[0].style.width,g.width(h-1).width(h),g[0].style.width=i)}function l(b,c,d){var h,e=c==window,f=a(c);(b||(!e||g)&&(e||f.data("blockUI.isBlocked")))&&(f.data("blockUI.isBlocked",b),e&&d.bindEvents&&(!b||d.showOverlay)&&(h="mousedown mouseup keydown keypress keyup touchstart touchend touchmove",b?a(document).bind(h,d,m):a(document).unbind(h,m)))}function m(b){var c,d,e,f,i;return"keydown"===b.type&&b.keyCode&&9==b.keyCode&&g&&b.data.constrainTabKey&&(c=h,d=!b.shiftKey&&b.target===c[c.length-1],e=b.shiftKey&&b.target===c[0],d||e)?(setTimeout(function(){n(e)},10),!1):(f=b.data,i=a(b.target),i.hasClass("blockOverlay")&&f.onOverlayClick&&f.onOverlayClick(),i.parents("div."+f.blockMsgClass).length>0?!0:0===i.parents().children().filter("div.blockUI").length)}function n(a){if(h){var b=h[a===!0?h.length-1:0];b&&b.focus()}}function o(a,b,c){var d=a.parentNode,e=a.style,f=(d.offsetWidth-a.offsetWidth)/2-p(d,"borderLeftWidth"),g=(d.offsetHeight-a.offsetHeight)/2-p(d,"borderTopWidth");b&&(e.left=f>0?f+"px":"0"),c&&(e.top=g>0?g+"px":"0")}function p(b,c){return parseInt(a.css(b,c),10)||0}var b,c,d,f,g,h;a.fn._fadeIn=a.fn.fadeIn,b=a.noop||function(){},c=/MSIE/.test(navigator.userAgent),d=/MSIE 6.0/.test(navigator.userAgent)&&!/MSIE 8.0/.test(navigator.userAgent),document.documentMode||0,f=a.isFunction(document.createElement("div").style.setExpression),a.blockUI=function(a){i(window,a)},a.unblockUI=function(a){j(window,a)},a.growlUI=function(b,c,d,e){var g,f=a('<div class="growlUI"></div>');b&&f.append("<h1>"+b+"</h1>"),c&&f.append("<h2>"+c+"</h2>"),void 0===d&&(d=3e3),g=function(b){b=b||{},a.blockUI({message:f,fadeIn:"undefined"!=typeof b.fadeIn?b.fadeIn:700,fadeOut:"undefined"!=typeof b.fadeOut?b.fadeOut:1e3,timeout:"undefined"!=typeof b.timeout?b.timeout:d,centerY:!1,showOverlay:!1,onUnblock:e,css:a.blockUI.defaults.growlCSS})},g(),f.css("opacity"),f.mouseover(function(){g({fadeIn:0,timeout:3e4});var b=a(".blockMsg");b.stop(),b.fadeTo(300,1)}).mouseout(function(){a(".blockMsg").fadeOut(1e3)})},a.fn.block=function(b){if(this[0]===window)return a.blockUI(b),this;var c=a.extend({},a.blockUI.defaults,b||{});return this.each(function(){var b=a(this);c.ignoreIfBlocked&&b.data("blockUI.isBlocked")||b.unblock({fadeOut:0})}),this.each(function(){"static"==a.css(this,"position")&&(this.style.position="relative",a(this).data("blockUI.static",!0)),this.style.zoom=1,i(this,b)})},a.fn.unblock=function(b){return this[0]===window?(a.unblockUI(b),this):this.each(function(){j(this,b)})},a.blockUI.version=2.6,a.blockUI.defaults={message:"<h1>Please wait...</h1>",title:null,draggable:!0,theme:!1,css:{padding:0,margin:0,width:"30%",top:"40%",left:"35%",textAlign:"center",color:"#000",border:"3px solid #aaa",backgroundColor:"#fff",cursor:"wait"},themedCSS:{width:"30%",top:"40%",left:"35%"},overlayCSS:{backgroundColor:"#000",opacity:.6,cursor:"wait"},cursorReset:"default",growlCSS:{width:"350px",top:"10px",left:"",right:"10px",border:"none",padding:"5px",opacity:.6,cursor:"default",color:"#fff",backgroundColor:"#000","-webkit-border-radius":"10px","-moz-border-radius":"10px","border-radius":"10px"},iframeSrc:/^https/i.test(window.location.href||"")?"javascript:false":"about:blank",forceIframe:!1,baseZ:1e3,centerX:!0,centerY:!0,allowBodyStretch:!0,bindEvents:!0,constrainTabKey:!0,fadeIn:200,fadeOut:400,timeout:0,showOverlay:!0,focusInput:!0,focusableElements:":input:enabled:visible",onBlock:null,onUnblock:null,onOverlayClick:null,quirksmodeOffsetHack:4,blockMsgClass:"blockMsg",ignoreIfBlocked:!1},g=null,h=[]}"function"==typeof define&&define.amd&&define.amd.jQuery?define(["jquery"],a):a(jQuery)}();

common.loading = function(msg) {
	var _msg = '<img src="' + ctx +'/static/img/loading.gif" />处理中，请稍候……';
	$.blockUI({ 
		css: {
			width: '20%',
			border: '3px solid #D6E9C6', 
			padding: '15px', 
			backgroundColor: '#DFF0D8', 
			'border-radius': '10px',
			'line-height' : '20px',
			'-webkit-border-radius': '10px', 
			'-moz-border-radius': '10px', 
			opacity: .5, 
			color: '#000' 
		}
		, message : _msg
	}); 
}

common.unloading = function() {
	$.unblockUI();
}



/**
 * bootbox.js v4.3.0
 *
 * http://bootboxjs.com/license.txt
 */
!function(a,b){"use strict";"function"==typeof define&&define.amd?define(["jquery"],b):"object"==typeof exports?module.exports=b(require("jquery")):a.bootbox=b(a.jQuery)}(this,function a(b,c){"use strict";function d(a){var b=q[o.locale];return b?b[a]:q.en[a]}function e(a,c,d){a.stopPropagation(),a.preventDefault();var e=b.isFunction(d)&&d(a)===!1;e||c.modal("hide")}function f(a){var b,c=0;for(b in a)c++;return c}function g(a,c){var d=0;b.each(a,function(a,b){c(a,b,d++)})}function h(a){var c,d;if("object"!=typeof a)throw new Error("Please supply an object of options");if(!a.message)throw new Error("Please specify a message");return a=b.extend({},o,a),a.buttons||(a.buttons={}),a.backdrop=a.backdrop?"static":!1,c=a.buttons,d=f(c),g(c,function(a,e,f){if(b.isFunction(e)&&(e=c[a]={callback:e}),"object"!==b.type(e))throw new Error("button with key "+a+" must be an object");e.label||(e.label=a),e.className||(e.className=2>=d&&f===d-1?"btn-primary":"btn-default")}),a}function i(a,b){var c=a.length,d={};if(1>c||c>2)throw new Error("Invalid argument length");return 2===c||"string"==typeof a[0]?(d[b[0]]=a[0],d[b[1]]=a[1]):d=a[0],d}function j(a,c,d){return b.extend(!0,{},a,i(c,d))}function k(a,b,c,d){var e={className:"bootbox-"+a,buttons:l.apply(null,b)};return m(j(e,d,c),b)}function l(){for(var a={},b=0,c=arguments.length;c>b;b++){var e=arguments[b],f=e.toLowerCase(),g=e.toUpperCase();a[f]={label:d(g)}}return a}function m(a,b){var d={};return g(b,function(a,b){d[b]=!0}),g(a.buttons,function(a){if(d[a]===c)throw new Error("button key "+a+" is not allowed (options are "+b.join("\n")+")")}),a}var n={dialog:"<div class='bootbox modal' tabindex='-1' role='dialog'><div class='modal-dialog'><div class='modal-content'><div class='modal-body'><div class='bootbox-body'></div></div></div></div></div>",header:"<div class='modal-header'><h4 class='modal-title'></h4></div>",footer:"<div class='modal-footer'></div>",closeButton:"<button type='button' class='bootbox-close-button close' data-dismiss='modal' aria-hidden='true'>&times;</button>",form:"<form class='bootbox-form'></form>",inputs:{text:"<input class='bootbox-input bootbox-input-text form-control' autocomplete=off type=text />",textarea:"<textarea class='bootbox-input bootbox-input-textarea form-control'></textarea>",email:"<input class='bootbox-input bootbox-input-email form-control' autocomplete='off' type='email' />",select:"<select class='bootbox-input bootbox-input-select form-control'></select>",checkbox:"<div class='checkbox'><label><input class='bootbox-input bootbox-input-checkbox' type='checkbox' /></label></div>",date:"<input class='bootbox-input bootbox-input-date form-control' autocomplete=off type='date' />",time:"<input class='bootbox-input bootbox-input-time form-control' autocomplete=off type='time' />",number:"<input class='bootbox-input bootbox-input-number form-control' autocomplete=off type='number' />",password:"<input class='bootbox-input bootbox-input-password form-control' autocomplete='off' type='password' />"}},o={locale:"en",backdrop:!0,animate:!0,className:null,closeButton:!0,show:!0,container:"body"},p={};p.alert=function(){var a;if(a=k("alert",["ok"],["message","callback"],arguments),a.callback&&!b.isFunction(a.callback))throw new Error("alert requires callback property to be a function when provided");return a.buttons.ok.callback=a.onEscape=function(){return b.isFunction(a.callback)?a.callback():!0},p.dialog(a)},p.confirm=function(){var a;if(a=k("confirm",["cancel","confirm"],["message","callback"],arguments),a.buttons.cancel.callback=a.onEscape=function(){return a.callback(!1)},a.buttons.confirm.callback=function(){return a.callback(!0)},!b.isFunction(a.callback))throw new Error("confirm requires a callback");return p.dialog(a)},p.prompt=function(){var a,d,e,f,h,i,k;if(f=b(n.form),d={className:"bootbox-prompt",buttons:l("cancel","confirm"),value:"",inputType:"text"},a=m(j(d,arguments,["title","callback"]),["cancel","confirm"]),i=a.show===c?!0:a.show,a.message=f,a.buttons.cancel.callback=a.onEscape=function(){return a.callback(null)},a.buttons.confirm.callback=function(){var c;switch(a.inputType){case"text":case"textarea":case"email":case"select":case"date":case"time":case"number":case"password":c=h.val();break;case"checkbox":var d=h.find("input:checked");c=[],g(d,function(a,d){c.push(b(d).val())})}return a.callback(c)},a.show=!1,!a.title)throw new Error("prompt requires a title");if(!b.isFunction(a.callback))throw new Error("prompt requires a callback");if(!n.inputs[a.inputType])throw new Error("invalid prompt type");switch(h=b(n.inputs[a.inputType]),a.inputType){case"text":case"textarea":case"email":case"date":case"time":case"number":case"password":h.val(a.value);break;case"select":var o={};if(k=a.inputOptions||[],!k.length)throw new Error("prompt with select requires options");g(k,function(a,d){var e=h;if(d.value===c||d.text===c)throw new Error("given options in wrong format");d.group&&(o[d.group]||(o[d.group]=b("<optgroup/>").attr("label",d.group)),e=o[d.group]),e.append("<option value='"+d.value+"'>"+d.text+"</option>")}),g(o,function(a,b){h.append(b)}),h.val(a.value);break;case"checkbox":var q=b.isArray(a.value)?a.value:[a.value];if(k=a.inputOptions||[],!k.length)throw new Error("prompt with checkbox requires options");if(!k[0].value||!k[0].text)throw new Error("given options in wrong format");h=b("<div/>"),g(k,function(c,d){var e=b(n.inputs[a.inputType]);e.find("input").attr("value",d.value),e.find("label").append(d.text),g(q,function(a,b){b===d.value&&e.find("input").prop("checked",!0)}),h.append(e)})}return a.placeholder&&h.attr("placeholder",a.placeholder),a.pattern&&h.attr("pattern",a.pattern),f.append(h),f.on("submit",function(a){a.preventDefault(),a.stopPropagation(),e.find(".btn-primary").click()}),e=p.dialog(a),e.off("shown.bs.modal"),e.on("shown.bs.modal",function(){h.focus()}),i===!0&&e.modal("show"),e},p.dialog=function(a){a=h(a);var c=b(n.dialog),d=c.find(".modal-dialog"),f=c.find(".modal-body"),i=a.buttons,j="",k={onEscape:a.onEscape};if(g(i,function(a,b){j+="<button data-bb-handler='"+a+"' type='button' class='btn "+b.className+"'>"+b.label+"</button>",k[a]=b.callback}),f.find(".bootbox-body").html(a.message),a.animate===!0&&c.addClass("fade"),a.className&&c.addClass(a.className),"large"===a.size&&d.addClass("modal-lg"),"small"===a.size&&d.addClass("modal-sm"),a.title&&f.before(n.header),a.closeButton){var l=b(n.closeButton);a.title?c.find(".modal-header").prepend(l):l.css("margin-top","-10px").prependTo(f)}return a.title&&c.find(".modal-title").html(a.title),j.length&&(f.after(n.footer),c.find(".modal-footer").html(j)),c.on("hidden.bs.modal",function(a){a.target===this&&c.remove()}),c.on("shown.bs.modal",function(){c.find(".btn-primary:first").focus()}),c.on("escape.close.bb",function(a){k.onEscape&&e(a,c,k.onEscape)}),c.on("click",".modal-footer button",function(a){var d=b(this).data("bb-handler");e(a,c,k[d])}),c.on("click",".bootbox-close-button",function(a){e(a,c,k.onEscape)}),c.on("keyup",function(a){27===a.which&&c.trigger("escape.close.bb")}),b(a.container).append(c),c.modal({backdrop:a.backdrop,keyboard:!1,show:!1}),a.show&&c.modal("show"),c},p.setDefaults=function(){var a={};2===arguments.length?a[arguments[0]]=arguments[1]:a=arguments[0],b.extend(o,a)},p.hideAll=function(){return b(".bootbox").modal("hide"),p};var q={br:{OK:"OK",CANCEL:"Cancelar",CONFIRM:"Sim"},cs:{OK:"OK",CANCEL:"Zrušit",CONFIRM:"Potvrdit"},da:{OK:"OK",CANCEL:"Annuller",CONFIRM:"Accepter"},de:{OK:"OK",CANCEL:"Abbrechen",CONFIRM:"Akzeptieren"},el:{OK:"Εντάξει",CANCEL:"Ακύρωση",CONFIRM:"Επιβεβαίωση"},en:{OK:"OK",CANCEL:"Cancel",CONFIRM:"OK"},es:{OK:"OK",CANCEL:"Cancelar",CONFIRM:"Aceptar"},et:{OK:"OK",CANCEL:"Katkesta",CONFIRM:"OK"},fi:{OK:"OK",CANCEL:"Peruuta",CONFIRM:"OK"},fr:{OK:"OK",CANCEL:"Annuler",CONFIRM:"D'accord"},he:{OK:"אישור",CANCEL:"ביטול",CONFIRM:"אישור"},id:{OK:"OK",CANCEL:"Batal",CONFIRM:"OK"},it:{OK:"OK",CANCEL:"Annulla",CONFIRM:"Conferma"},ja:{OK:"OK",CANCEL:"キャンセル",CONFIRM:"確認"},lt:{OK:"Gerai",CANCEL:"Atšaukti",CONFIRM:"Patvirtinti"},lv:{OK:"Labi",CANCEL:"Atcelt",CONFIRM:"Apstiprināt"},nl:{OK:"OK",CANCEL:"Annuleren",CONFIRM:"Accepteren"},no:{OK:"OK",CANCEL:"Avbryt",CONFIRM:"OK"},pl:{OK:"OK",CANCEL:"Anuluj",CONFIRM:"Potwierdź"},pt:{OK:"OK",CANCEL:"Cancelar",CONFIRM:"Confirmar"},ru:{OK:"OK",CANCEL:"Отмена",CONFIRM:"Применить"},sv:{OK:"OK",CANCEL:"Avbryt",CONFIRM:"OK"},tr:{OK:"Tamam",CANCEL:"İptal",CONFIRM:"Onayla"},zh_CN:{OK:"OK",CANCEL:"取消",CONFIRM:"确认"},zh_TW:{OK:"OK",CANCEL:"取消",CONFIRM:"確認"}};return p.init=function(c){return a(c||b)},p});
bootbox.setDefaults({
	/**
	* @optional String
	* @default: en
	* which locale settings to use to translate the three
	* standard button labels: OK, CONFIRM, CANCEL
	*/
	locale: "zh_CN",
	/**
	* @optional Boolean
	* @default: true
	* whether the dialog should be shown immediately
	*/
	show: true,
	/**
	* @optional Boolean
	* @default: true
	* whether the dialog should be have a backdrop or not
	*/
	backdrop: true,
	/**
	* @optional Boolean
	* @default: true
	* show a close button
	*/
	closeButton: true,
	/**
	* @optional Boolean
	* @default: true
	* animate the dialog in and out (not supported in < IE 10)
	*/
	animate: true,
	/**
	* @optional String
	* @default: null
	* an additional class to apply to the dialog wrapper
	*/
	className: "my-modal",
	
	/**
	* @optional String
	* @default: small
	* 设置对话框的大小，small:小，large:大，normal:中。
	*/
	size: "small"
});

bootbox.custom = function(title, message, callback, danger, size) {
	var className = "btn-primary";
	if (danger && danger == true) className="btn-danger";
	
	var style = 'small';
	if (size) style = size;
	
    bootbox.dialog({
    	title: title,
        message: message,
        size : style,
        buttons: {
          cancel: {
            label: "<span class='glyphicon glyphicon-remove'></span> 取消",
            className: "btn-default"
          },
          ok: {
            label: "<span class='glyphicon glyphicon-ok'></span> 确定",
            className: className,
            callback: callback
          }
        }
     });
};
