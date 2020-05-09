/**********************************设备型号脚本***************************************************/

/**
 * 新增设备型号
 */
function addModel(treeNode){
	if(treeNode.nocheck){
		pId="0";
	}else{
		pId = treeNode.id;
	}
	var contentMsg = {
			title: $.i18n.map['addDeviceModel'],   
			url:"stModel/addPage.do",
			width:"450",
			requestMethod: 'ajax',
			paraData:{pId:pId},
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					modelAddOrEdit(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
	
}
/**
 * 编辑设备型号
 */
function editModel(treeNode){
	var contentMsg = {
			title:$.i18n.map['editDeviceModel']+"-【"+treeNode.name+"】",   
			url:"stModel/editPage.do",
			width:"450",
			requestMethod: 'ajax',
			paraData:{id:treeNode.id},
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					modelAddOrEdit(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存和编辑共用的方法
 */
function modelAddOrEdit(thisPop){
	var id = $("#id").val();
	if(id==null || id==undefined){
		url = "stModel/logAddModel.do";
	}else{
		url = "stModel/logEditModel.do"; 
	}
	var treeObj = $.fn.zTree.getZTreeObj("modelInfoTree");
	var param = $("#stModelForm").serialize();
	if(validateModelForm()){
		fnAjaxRequest(
				url,
				param,
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
					$.Popup.close(thisPop);
   				    if(data.success){
   				    	modelTreeInit(data.obj);
				    }
				}
		);
	}
}

/**
 * 设备型号
 * @param roleId
 * @param roleName
 * @param enterId
 */
var modelTreeObj;
function modelTreeInit(enterId){
	var params = {
			enterId:enterId
	};
	$.getJSON(
			"stModel/list.do",params,
			function(data){
				var setting = {
						data:{ 
							simpleData : {  
								enable : true 
							}  
						},  
						view: {
			                addHoverDom: addHoverDom,
			                removeHoverDom: removeHoverDom,
			                dblClickExpand: dblClickExpand,
			                selectedMulti: false
			            },
			            edit: {
			                enable: true,
			                editNameSelectAll: false,
			                showRenameBtn: showRenameBtn,
			                showRemoveBtn: showRemoveBtn,
			                renameTitle: $.i18n.map['editModel'],
			                removeTitle: $.i18n.map['deleteModel']
			            },
						
						check: {
							enable: false
						},
						callback : {
							beforeRemove: beforeRemove,
			                beforeEditName: beforeEditName,
			                onRemove: onRemove,
							onClick : modelTreeOnClick,
							onDblClick : function(event, treeId, treeNode) {
								if(treeNode.isParent){
									$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectModel']});
							  	  	return false;
								}else{
									var name = treeNode.name;
									$("#modelName").val(name);
									$("#model").val(treeNode.id);
									$("#popup_1").remove();
									//$.Popup.close(thisPop);
								}
					        },
							beforeDrag:function(){return false;} 
						}
				}; 
				var zNodes = data;
				
				modelTreeObj = $.fn.zTree.init($("#zModelTree"), setting, zNodes);
				//展开选中节点
				var parentNodes = modelTreeObj.getNodesByParam("isParent",true);
				for(var i=0;i<parentNodes.length;i++){
			    	var node = parentNodes[i];
			    	if(node.check_Child_State == 1 || node.check_Child_State==2){
			    		modelTreeObj.expandNode(node, true);
			    	}
			    }
				
			});
}
/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function modelTreeOnClick(event, treeId, treeNode) {
	//console.log(treeNode);
	modelTreeObj.checkNode(treeNode,null,true);
}
var _newId, _newName;

function addHoverDom(treeId, treeNode) {
	if(treeNode.level==0 || treeNode.level==1){
	    var sObj = $("#" + treeNode.tId + "_span");
	    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0 || treeNode.tag == 0) return;
	    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增型号' onfocus='this.blur();'></span>";
	/*    if(treeNode.level != 0 && (treeNode.groupFlag == 1 || treeNode.nocheck==true)){
	    	sObj.after(addStr);
	    }*/
	    sObj.after(addStr);
	    var btn = $("#addBtn_" + treeNode.tId);
	    if (btn) {
	        btn.bind("click", function () {
	        	addModel(treeNode);
	            return false;
	        });
	    }
	}
}        

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
}

function beforeRemove(treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("zModelTree");
    treeObj.selectNode(treeNode);
    var flag = false;
		var confirmMsg = {
                title: $.i18n.map['prompt'],
                content: $.i18n.map['okDeleteModel']+"["+treeNode.name+"]?",
                tbar: [{
                    text: $.i18n.map['determine'],
                    clsName: "homebg popup-confirm",
                    handler: function (thisPop) {
                    	 fnAjaxRequest(
     	         			    "stModel/delModel.do",
     	        				{id:treeNode.id},
     	        				"json",
     	        				"POST",
     	        				function(data){
     	        					$.Popup.close(thisPop);
	     	        				if(!data.success){
	     	       	            		$.Popup.create({ title: $.i18n.map['prompt'], content:  data.msg });
	     	       	            	}else{
	     	       	            		flag=true;
	     	       	            		treeObj.removeNode(treeNode,null);
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
            return false;
} 

function onRemove(event, treeId, treeNode) {
	
}
function beforeEditName(treeId, treeNode, newName) {
	editModel(treeNode);
    return false;
}
function dblClickExpand(treeId, treeNode) {
    return treeNode.level > 0;
}
function showRenameBtn(treeId, treeNode) {
    return treeNode.level != 0;
}
function showRemoveBtn(treeId, treeNode) {
    return !treeNode.isParent && treeNode.level != 0;
}


/**
 * 设备型号脚本验证
 */
function validateModelForm(){ 
	return $("#stModelForm").validate({
		rules:{
			name : {
				required: true,
				nmExist:true
			}

		},
		messages:{
			name : {
				required: $.i18n.map['required'],
				nmExist:$.i18n.map['exist']
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
$.validator.addMethod("nmExist",function(value,element){
	var name = $.trim(value);
	var id = $("#id").val();
	var result= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("stModel/checkNmExist.do",{
		name : name,
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
},$.i18n.map['nameAlreadyExists']);