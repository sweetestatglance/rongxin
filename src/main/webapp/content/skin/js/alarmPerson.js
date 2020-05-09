/**********************************预警人员脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#sysPersonList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
})
function addAlarmPerson(){
	var contentMsg = {
			title: "新增预警人员",   
			url:"stAlarmPerson/addPage.do",
			width:"400",
			paraData:{},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditPerson(thisPop,"stAlarmPerson/logAddAlarmPerson.do");
				}
			}]
	};
	$.Popup.create(contentMsg);
}

function editAlarmPerson(id){
	var contentMsg = {
			title: "编辑预警人员",   
			url:"stAlarmPerson/editPage.do",
			width:"400",
			paraData:{id:id},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditPerson(thisPop,"stAlarmPerson/logEditAlarmPerson.do");
				}
			}]
	};
	$.Popup.create(contentMsg);
}
function delAlarmPerson(id){
	var ids = [];
	if (id) {
		ids.push(id);
	}else{
		var selectRow = $("#sysPersonList tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			ids.push(selectRow[i].value);
		}
	}
	if (ids.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: "请选择预警人员"});
		return false;
	}
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			width:"500",
			content: "确定要删除该预警人员吗？",
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					fnAjaxRequest(
							"stAlarmPerson/logDelAlarmPerson.do",
							{ids:ids.toString()},
							"json",
							"POST",
							function(data){
								$.Popup.close(thisPop);
								fnDSuccess(data,thisPop);
								if(data.success){
									loadAlarmPersonList(null,true);
								}
							}
					);
				}
			}, {
				text: $.i18n.map['cancel'],
				clsName: "homebg popup-cancel",
				handler: function (thisObj) {
					$.Popup.close(thisObj);
				}
			}]
	};
	$.Popup.create(confirmMsg);
}


/**
 * 增加和编辑的公用方法
 */
function addAndEditPerson(thisPop,url){
	var param = $("#sysAlarmPersonForm").serialize();
   	if(validAlarmPersonForm()){
         	  fnAjaxRequest(
         			    url,
        				param,
        				"json",
        				"POST",
        				function(data){
         			    	fnDSuccess(data,thisPop);
         			    	if(data.success){
         			    		loadAlarmPersonList(null,true);
          					} 
        				}
        	   );
   	  }
}

function resetSmsPerson(){
	  $("#name").val("请输入预警用户");
	  $("#phone").val($.i18n.map['phone']);
}

function changePage(page){
	var name = $("#name").val();
	if(name=="请输入预警用户"){
		name = "";
	}
	var phone = $("#phone").val();
	if(phone==$.i18n.map['phone']){
		phone = "";
	}
	var params = {
			pageNo:page,
			phone_query:encodeURI(phone),
			name_query:encodeURI(name)
	};
	loadAlarmPersonList(params,false);
}
/**
 * 从新加载
 */
function loadAlarmPersonList(params,flag){
	var url = "stAlarmPerson/index.do";
	showMark();
	$.ajax({
		url : url,
		data : params,
		success :function(data){
			$("#twoContain").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
	
}

function searchAlarmPerson(){
	var name = $("#name").val();
	if(name=="请输入预警用户"){
		name = "";
	}
	var phone = $("#phone").val();
	if(phone==$.i18n.map['phone']){
		phone = "";
	}
	var params = {
		phone_query:encodeURI(phone),
		name_query:encodeURI(name)
	};
	loadAlarmPersonList(params,false);
}

function validAlarmPersonForm(){
	return $("#sysAlarmPersonForm").validate({
        rules: {
        	name: {
        		required: true
        	},
        	phone:  {
        		required: true,
        		isMobile: true
        	}
  		},
        messages: {
        	name: {
        		required: $.i18n.map['required']
        	},
        	phone:  {
        		required: $.i18n.map['required'],
        		isMobile: $.i18n.map['isMobile']
        	}
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
