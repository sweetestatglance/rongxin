/**********************************通道配置脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	selCheck();
})

function toggle(divId){
	//切换
	$("#" + divId).animate({opacity:'1',height: 'toggle'},function(){
		
		if($("#" + divId + "Img").hasClass("shrin_transform")){
			$("#" + divId + "Img").removeClass("shrin_transform");
		}else{
			$("#" + divId + "Img").addClass("shrin_transform");
		}
	});
}

function selCheck(){
	var length = $("#waterRain input:checked,#weather input:checked, #soli input:checked, #water input:checked").length;
	if(length >= 10){
		$("#selCount").parent("div").css("color","#d68640");
		$("#waterRain input:checkbox,#weather input:checkbox, #soli input:checkbox, #water input:checkbox").not("input:checked").attr("disabled","disabled");
	}else{
		$("#selCount").parent("div").css("color","#40b5d6");
		$("#waterRain input:checkbox,#weather input:checkbox, #soli input:checkbox, #water input:checkbox").not("input:checked").removeAttr("disabled");
	}
	
	$("#selCount").text(length);
}

function saveChannelManage(){
	var sfId = $("#sfId").val();
	var seId = $("#seId").val();
	var param = $("#channelManage").serialize();
	var url = "channelManage/logSave.do";
	if(sfId!="" && seId!="")
		url = "channelManage/logUpdate.do";
	 if(validInfoForm()){
		 showMark();
		 $.ajax({
			 url : url,
			 data : param,
			 success : function(data){
				 hideMark();
				 if(data.success){
					 $.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
				 }
//			loadFactorList();
			 },
			 error: function (xhr, error, thrown) {
				 hideMark();
			 }
		 });
	 }
}

/**
 * 表单验证
 * @returns boolean
 */
function validInfoForm(){
	return $("#channelManage").validate({
		 rules: {
	        	factorviewname11: {maxlength:10},
	        	factorviewname12: {maxlength:10},
	        	factorviewname13: {maxlength:10},
	        	factorviewname14: {maxlength:10},
	        	factorviewname15: {maxlength:10},
	        	factorviewname16: {maxlength:10},
	        	factorviewname17: {maxlength:10},
	        	factorviewname18: {maxlength:10},
	        	factorviewname19: {maxlength:10},
	        	factorviewname20: {maxlength:10},
	        	factorviewname21: {maxlength:10},
	        	factorviewname22: {maxlength:10},
	        	factorviewname23: {maxlength:10},
	        	factorviewname24: {maxlength:10}
	  		},
	        messages: {
	        	factorviewname11: {maxlength: "长度不能超过10位"},
	        	factorviewname12: {maxlength: "长度不能超过10位"},
	        	factorviewname13: {maxlength: "长度不能超过10位"},
	        	factorviewname14: {maxlength: "长度不能超过10位"},
	        	factorviewname15: {maxlength: "长度不能超过10位"},
	        	factorviewname16: {maxlength: "长度不能超过10位"},
	        	factorviewname17: {maxlength: "长度不能超过10位"},
	        	factorviewname18: {maxlength: "长度不能超过10位"},
	        	factorviewname19: {maxlength: "长度不能超过10位"},
	        	factorviewname20: {maxlength: "长度不能超过10位"},
	        	factorviewname21: {maxlength: "长度不能超过10位"},
	        	factorviewname22: {maxlength: "长度不能超过10位"},
	        	factorviewname23: {maxlength: "长度不能超过10位"},
	        	factorviewname24: {maxlength: "长度不能超过10位"}
	  		},
	showErrors:showErrors,
	onkeyup: function( element, event ) {
		if ( event.which === 9 && this.elementValue( element ) === "" ) {
			return;
		} else if ( element.name in this.submitted || element === this.lastElement ) {
			this.element( element );
			$(element).next('span').remove();//移除span
		}
	}
}).form();
}
