/**********************************行政区域脚本***************************************************/
/**
 * 新增行政区域
 */
function addADDvcdD(treeNode){
	if(treeNode.nocheck){
		pId="0";
	}else{
		pId = treeNode.id;
	}
	var contentMsg = {
			title: $.i18n.map['areaAddTitle'],   
			url:"stAddvcdD/addPage.do",
			width:"500",
			requestMethod: 'ajax',
			paraData:{pId:pId},
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addvcdDAddOrEdit(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}
/**
 * 编辑行政区域
 */
function editAddvcdD(treeNode){
	var contentMsg = {
			title:$.i18n.map['areaEditTitle'] +"【"+treeNode.name+"】",   
			url:"stAddvcdD/editPage.do",
			width:"500",
			requestMethod: 'ajax',
			paraData:{id:treeNode.id},
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addvcdDAddOrEdit(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}
/**
 * 保存和编辑共用的方法
 */
function addvcdDAddOrEdit(thisPop){
	var id = $("#id").val();
	if(id==null || id==undefined){
		url = "stAddvcdD/addAddvcdD.do";
	}else{
		url = "stAddvcdD/editAddvcdD.do"; 
	}
	var treeObj = $.fn.zTree.getZTreeObj("addvcdInfoTree");
	var param = $("#stAddvcdDForm").serialize();
	if(validateAddvcdDForm()){
		fnAjaxRequest(
				url,
				param,
				"json",
				"POST",
				function(data){
					if(data.success){
						$.Popup.close(thisPop);
						var obj = data.obj;
						var pId;
						if(obj.faddvcd=="0"){
							pId = obj.enterpriseid;
						}else{
							pId = obj.faddvcd;
						}
						var nodeObj = {
								id:obj.id,
								name:obj.addvnm,
								pId:pId,
								checked:true
						};
						if(id==null || id==undefined){
							var node = treeObj.getNodeByParam("id", pId, null);
							treeObj.addNodes(node,nodeObj);
						}else{
							var node = treeObj.getNodeByParam("id", obj.id, null);
							node.name = obj.addvnm;
							treeObj.updateNode(node);
						}
					}
				}
		);
	}
}
/**
 * 行政区域脚本验证
 */
function validateAddvcdDForm(){ 
	return $("#stAddvcdDForm").validate({
		rules:{
			/*			addvcd : {
			    required: true,
			    isSixNum: true,
			    specialCharValidate: true,
			    addvcdExist: true
			   },
			addnum :{
			    required: true,
			    number: true,
			    specialCharValidate: true,
			    maxlength: [10]
			   },*/
			addvnm : {
				required: true,
				specialCharValidate: true,
				addvnmExist:true
			}

		},
		messages:{
			/*			addvcd : {
			    required: "必填项",
			    isSixNum: "6位数字",
			    specialCharValidate: "不能包含特殊字符",
			    addvcdExist:"存在了"
              },
              addnum:{
  			    required: "必填项",
  			    number: "数字",
  			    specialCharValidate: "不能包含特殊字符",
  			    maxlength:"最多输入10位数字"
                },*/
			addvnm : {
				required: $.i18n.map['required'],
				specialCharValidate: $.i18n.map['noSpecialChar'],
				addvnmExist:$.i18n.map['exist']
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

/***
 * ajax 验证名称
 */
$.validator.addMethod("addvnmExist",function(value,element){
	var addvnm = $.trim(value);
	var id = $("#id").val();
	var result= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("stAddvcdD/checkAddvnmExist.do",{
		addvnm : addvnm,
		id : id
	},function(data){
		if(data.success){
			result = false;
		}else{
			result = true;
		} 
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
	return result;
},$.i18n.map['required']);

/***
 * ajax 验证行政区划码
 */
/*$.validator.addMethod("addvcdExist",function(value,element){
	var addvcd = value;
	var id = $("#id").val();
	var result= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("stAddvcdD/checkAddvcdDExist.do",{
		addvcd : addvcd,
		id : id
	},function(data){
		if(data.success){
			result = false;
		}else{
			result = true;
		} 
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
	return result;
},"行政区划码，已经存在了!");*/