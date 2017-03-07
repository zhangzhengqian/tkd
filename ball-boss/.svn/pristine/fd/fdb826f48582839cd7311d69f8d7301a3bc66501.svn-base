$(document).ready(function() {
	  
//为表单注册validate函数
$("#form1")
	.validate({
			rules : {
				prodType: {
					required : true,
					rangelength : [ 2, 20 ],
					remote : ctx+"/admin/product/checkProdType?oldProdType="
					+ encodeURIComponent($('#oldProdType').val())
				},
				prodName: {
					required : true,
					rangelength : [ 2, 20 ],
					remote : ctx+"/admin/product/checkProdName?oldProdName="
					+ encodeURIComponent($('#oldProdName').val())
				}
			},
			messages : {
				prodType : {
					remote : "产品编码已经存在！"
				},
				prodName : {
					remote : "产品名称已经存在！"
				}
			}
		});
					  
});


