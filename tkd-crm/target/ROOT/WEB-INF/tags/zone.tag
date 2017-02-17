<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ attribute name="disabled" type="java.lang.Boolean" required="false"%>
<%@ attribute name="clazz" type="java.lang.String" required="false"%>


<script src="${ctx }/static/lib/zone.js"></script>
<% 
request.setAttribute("tagskey", java.util.UUID.randomUUID().toString().replace("-", ""));
if(clazz==null||"".equals(clazz)){
	clazz = "form-control";
}
%>

<%--<select class="<%=clazz %> zone_coll_1${tagskey}" <c:if test="${disabled }">disabled</c:if> ></select>--%>
<select class="provinceSel  zone_coll_1${tagskey}" <c:if test="${disabled }">disabled</c:if> ></select>
<select class="citySel zone_coll_2${tagskey}" <c:if test="${disabled }">disabled</c:if> ></select>
<select class="areaSel zone_coll_3${tagskey}" <c:if test="${disabled }">disabled</c:if> ></select>
<input type="hidden" class="${tagskey }" id="${id }" name="${name }" value="" />
	
<script>
$(function() {
	var zone${tagskey} = (function() {
		var disabled = '${disabled }';
		var getNodes = function(code) {
			if(code){
				return zone_tree[code];
			}else{
				return zone_tree['000000'];
			}
		};
		var getName = function(code) {
			return zone_map[code]
		};
		var setColl1 = function(code){
			$(".zone_coll_1${tagskey}").empty();
			$(".zone_coll_2${tagskey}").empty();
			$(".zone_coll_3${tagskey}").empty();
			
			var nodes1 = getNodes().sort();
			$(".zone_coll_1${tagskey}").append('<option value="">请选择省</option>');
			for ( i in nodes1 ){
				code = nodes1[i];
				$(".zone_coll_1${tagskey}").append('<option value="'+code+'">'+getName(code)+'</option>');
			}
			
			return nodes1[0];
		};
		var setColl2 = function(code){
			$(".zone_coll_2${tagskey}").empty();
			$(".zone_coll_2${tagskey}").append('<option value="">请选择市</option>');
			if("" != code){
				$(".zone_coll_3${tagskey}").empty();
				var nodes2 = getNodes(code).sort();
				for ( i in nodes2 ){
					code = nodes2[i];
					$(".zone_coll_2${tagskey}").append('<option value="'+code+'">'+getName(code)+'</option>');
				}
				return nodes2[0];
			}else{
				return "";
			}
		};
		var setColl3 = function(code){
			$(".zone_coll_3${tagskey}").empty();
			$(".zone_coll_3${tagskey}").append('<option value="">请选择区</option>');
			if("" != code && code!=null && code!='00' && code!='' ){
				var nodes3 = getNodes(code).sort();
				for ( i in nodes3 ){
					code = nodes3[i];
					$(".zone_coll_3${tagskey}").append('<option value="'+code+'">'+getName(code)+'</option>');
				}
				return nodes3[0];
			}else{
				return "";
			}
		};
		
		var init = function(code) {
			if(code){
				c1 = code.substr(0,2)+'0000';
				c2 = code.substr(0,4)+'00';
				c3 = code;
				
				$(".zone_coll_1${tagskey}").val(c1);

				setColl2(c1);
				if(c2.substring(2,4)!='00'){
					$(".zone_coll_2${tagskey}").val(c2);
					setColl3(c2);
				}
				if(c3.substring(4,6)!='00'){
					$(".zone_coll_3${tagskey}").val(c3);
				}
			}
			if('true' == disabled){
				var v1 = $(".zone_coll_1${tagskey}").val();
				var v2 = $(".zone_coll_2${tagskey}").val();
				var v3 = $(".zone_coll_3${tagskey}").val();
				if(v3=='' || v3==null)
					$(".zone_coll_3${tagskey}").hide();
				if(v2=='' || v2==null)
					$(".zone_coll_2${tagskey}").hide();
				if(v1=='' || v1==null)
					$(".zone_coll_1${tagskey}").hide();
			}
			
		};
		var setValue = function(v) {
			if(""!=v){
				$(".${tagskey}").val(v);	
			}else{
				var v1 = $(".zone_coll_1${tagskey}").val();
				var v2 = $(".zone_coll_2${tagskey}").val();
				var v3 = $(".zone_coll_3${tagskey}").val();
				if(""!=v3){
					$(".${tagskey}").val(v3);	
				}else if(""!=v2){
					$(".${tagskey}").val(v2);	
				}else if(""!=v1){
					$(".${tagskey}").val(v1);	
				}else{
					$(".${tagskey}").val('');	
				}
			}
		};
		
		return {
			start : function(code) {
				setColl1();
				setColl2("");
				setColl3("");
				setValue(code);
				//setColl3(setColl2(setColl1("")));
				
				$(".zone_coll_1${tagskey}").change(function(){
					var obj = $(this);
					//obj.css("background-color","#FFFFCC");
					setColl2(obj.val());
					setColl3("");
					//setColl3(setColl2(obj.val()));
					setValue($(this).val());
				});
				$(".zone_coll_2${tagskey}").change(function(){
					var obj = $(this);  
					//obj.css("background-color","#FFFFCC");
					setColl3(obj.val());
					setValue($(this).val());
				});
				$(".zone_coll_3${tagskey}").change(function(){
					setValue($(this).val());
				});

				$(".zone_coll_1${tagskey}").css("background-color","#FFFFFF");
				$(".zone_coll_2${tagskey}").css("background-color","#FFFFFF");
				$(".zone_coll_3${tagskey}").css("background-color","#FFFFFF");
				init(code);
			}
		};
		
	})();
	zone${tagskey}.start('${value}');
});
</script>