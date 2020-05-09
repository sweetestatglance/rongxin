/**********************************角色功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#sysRoleList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
})

/**
 * 分页查询
 * @param page
 */
function changePage(page,roleName){
	if(roleName==$.i18n.map['roleName']){
		roleName = "";
	}
	var params = {
			pageNo:page,
			query_rolename:roleName
	};
	loadRoleList(params);
}

/**
 * 菜单配置全选
 */
function selectMenuAll(){
	var treeObj = $.fn.zTree.getZTreeObj("enterMenuInfoTree");
    if ($("#selectMenuAll").attr("checked")) {  
         treeObj.checkAllNodes(true);
    } else {  
    	 treeObj.checkAllNodes(false);  
    }  
}

/**
 * 区域配置全选
 */
function selectAreaAll(){
	var treeObj = $.fn.zTree.getZTreeObj("addvcdInfoTree");
    if ($("#selectAreaAll").attr("checked")) {  
         treeObj.checkAllNodes(true);
    } else {  
    	 treeObj.checkAllNodes(false);  
    }  
}

/**
 * 从新加载
 */
function loadRoleList(params,flag){
	var url = "sysRole/index.do";
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



/**
 * 查询角色名称
 */
function searchRole(){
	var roleName = $("#roleName").val();
	if(roleName==$.i18n.map['roleName']){
		roleName = "";
	}
	$.ajax({
		url : "sysRole/index.do",
		data : {query_rolename:encodeURI(roleName)},
		success : function(data){
			$("#twoContain").html(data);
		}});
}

/**
 * 新增角色
 */
function addRole(enterpriseid){
	var contentMsg = {
			title: $.i18n.map['roleAddTitle'],   
			url:"sysRole/addPage.do",
			width:"500",
			paraData:{enterpriseid: enterpriseid},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditRole(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}
/**
 * 编辑角色
 */
function editRole(enterpriseid){
	var selectRow = $("#sysRoleList tbody input[type='checkbox']:checked");
	if (selectRow.length != 1) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectEditMsg']});
		return;
	}
	var id = $(selectRow[0]).val();
	/*var rolecode = $(selectRow[0]).attr("name");
	if(rolecode.indexOf("_admin")>0){
		$.Popup.create({ title: $.i18n.map['prompt'], content: "企业管理员不允许进行修改"});
		return;
	}*/
	var contentMsg = {
			title: $.i18n.map['roleEditTitle'],   
			url:"sysRole/editPage.do",
			width:"500",
			paraData:{enterpriseid: enterpriseid,id:id},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditRole(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除角色
 */
function delRole(){
	var delIds = [];
	var selectRow = $("#sysRoleList tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		delIds.push(selectRow[i].value);
	}
	if (delIds.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['deleteRoleSelMsg']});
		return;
	}
	/*var rolecode = $(selectRow[0]).attr("name");
	if(rolecode.indexOf("_admin")>0){
		$.Popup.create({ title: $.i18n.map['prompt'], content: "企业管理员不允许进行删除"});
		return;
	}*/
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['deleteRoleMsg'] +"?",
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					fnAjaxRequest(
							"sysRole/checkUserRoleBy.do",
							{items: delIds.toString()},
							"json",
							"POST",
							function(data){
								if(data.exist){
									var confirmMsg1 = {
											title: $.i18n.map['prompt'],
											content: $.i18n.map['deleteRoleCofirmMsg'] + "?",
											tbar: [{
												text: $.i18n.map['determine'],
												clsName: "homebg popup-confirm",
												handler: function (thisPop) {
													fnAjaxRequest(
															"sysRole/logDelRole.do",
															{items: delIds.toString()},
															"json",
															"POST",
															function(data){
																fnDSuccess(data,thisPop);
																if(data.success){
																	loadRoleList(null,true);
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
									$.Popup.create(confirmMsg1);
								}else{
									fnAjaxRequest(
											"sysRole/logDelRole.do",
											{items: delIds.toString()},
											"json",
											"POST",
											function (data) {
												fnDSuccess(data,thisPop);
												if(data.success){
													loadRoleList(null,true);
												}
											});
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
function addAndEditRole(thisPop){
	var did = $("input[name='id']").val();
	var url;
	if(did==null || did==undefined){
		url = "sysRole/logAddRole.do";
	}else{
		url = "sysRole/logEditRole.do";
	}
	  var param = $("#sysRoleForm").serialize();
   	  if(validRoleForm()){
         	  fnAjaxRequest(
         			    url,
        				param,
        				"json",
        				"POST",
        				function(data){
         			    	fnDSuccess(data,thisPop);
         			    	if(data.success){
         			    		loadRoleList(null,true);
          					} 
        				}
        	   );
   	  }
}


/**
 * 配置角色权限
 * @param roleId
 * @param roleName
 * @param enterId 所属企业id
 */
function setPermissionInfo(roleId,enterId){
	var contentMsg = {
			title: $.i18n.map['permissionEdit'],
            url :"sysRole/setPermissionInfo.do",
            paraData:{roleId: roleId,enterId:enterId},
            width:"400",
            requestMethod: 'ajax',
            tbar: [{
                text: $.i18n.map['determine'],
                clsName: "homebg popup-confirm",
                handler: function (thisPop) {
                	rolePermissionFnAddOrEditSubmit(thisPop);
                }
            }]
        };
      $.Popup.create(contentMsg);
}
/**
 * 菜单Tree配置
 */
var enterMenuTreeObj;
function enterMenuTreeInit(roleId, enterId){
	var params = {
			roleId: roleId,
			enterId:enterId
	};
	$.getJSON(
			"sysRole/getEnterMenuTree.do",params,
			function(data){
				var setting = {
					 	data:{ 
					        simpleData : {  
					            enable : true 
					        }  
					    },  
						check: {
							enable: true
						},
						callback : {
							onClick : enterMenuTreeOnClick
						}
				}; 
				var zNodes = data;
				enterMenuTreeObj = $.fn.zTree.init($("#enterMenuInfoTree"), setting, zNodes);
				//展开选中节点
				var parentNodes = enterMenuTreeObj.getNodesByParam("isParent",true);
				for(var i=0;i<parentNodes.length;i++){
			    	var node = parentNodes[i];
			    	if(node.check_Child_State == 1 || node.check_Child_State==2){
			    		enterMenuTreeObj.expandNode(node, true);
			    	}
			    }
			});
	
}
/**
 * 特殊应用——区域树初始化
 * @param roleId
 * @param roleName
 * @param enterId
 */
var addvcdTreeObj;
function addvcdTreeInit(roleId,enterId){
	var params = {
			roleId: roleId,
			enterId:enterId
	};
	$.getJSON(
			"sysRole/getAddvcdTree.do",params,
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
			                renameTitle: $.i18n.map['areaEditTitle'],
			                removeTitle: $.i18n.map['areaDelTitle']
			            },
						
						check: {
							enable: true
						},
						callback : {
							beforeRemove: beforeRemove,
			                beforeEditName: beforeEditName,
			                onRemove: onRemove,
							onClick : addvcdTreeOnClick,
							beforeDrag:function(){return false;} 
						}
				}; 
				var zNodes = data;
				
				addvcdTreeObj = $.fn.zTree.init($("#addvcdInfoTree"), setting, zNodes);
				//展开选中节点
				var parentNodes = addvcdTreeObj.getNodesByParam("isParent",true);
				for(var i=0;i<parentNodes.length;i++){
			    	var node = parentNodes[i];
			    	if(node.check_Child_State == 1 || node.check_Child_State==2){
			    		addvcdTreeObj.expandNode(node, true);
			    	}
			    }
				
			});
}

function rolePermissionFnAddOrEditSubmit(thisPop){
	var roleId = $("#roleId").val();
	/** 菜单树处理  */
	var menuIds = {};
	//获取选中的顶级菜单节点
	var topSelectMenuNodes = enterMenuTreeObj.getNodesByFilter(getTopNodes);
	//循环选中顶级菜单节点，获取其下选中的菜单
	for(var i=0;i<topSelectMenuNodes.length;i++){
		var menuIdArray = [];
		var parentNode = topSelectMenuNodes[i];
		menuIdArray.push(parentNode.id);
		var selectChildNodes = enterMenuTreeObj.getNodesByParam("checked", true, parentNode);
		
		for (var j=0; j < selectChildNodes.length; j++) {
			var childNode = selectChildNodes[j];
			menuIdArray.push(childNode.id);
		}
		menuIds[parentNode.id] = menuIdArray;
	}
	/** 区域配置处理  */
	//获取顶级分组
	var addvcdDIds = {};
	//选中的顶级菜单
	var topSelectAreaNodes = addvcdTreeObj.getNodesByFilter(getGroupTopNodes);
	//循环选中的顶级菜单，获取其下选中的子菜单
	for(var i=0;i<topSelectAreaNodes.length;i++){
		var parentNode = topSelectAreaNodes[i];
		var selectChildNodes = addvcdTreeObj.getNodesByParam("checked", true, parentNode);
		var addvcdDIdArray = [];
		for (var j=0; j < selectChildNodes.length; j++) {
			var childNode = selectChildNodes[j];
			addvcdDIdArray.push(childNode.id);
		}
		addvcdDIds[parentNode.id] = addvcdDIdArray;
	}
    var params = {
    		roleId: roleId,
    		menuIds : JSON.stringify(menuIds),
    		areaIds : JSON.stringify(addvcdDIds)
    };
	$.post("sysRole/logSaveRoleMenuAreaPermission.do",params,function(data){
		fnDSuccess(data,thisPop);
	},"json");
}


/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function enterMenuTreeOnClick(event, treeId, treeNode) {
	//console.log(treeNode);
	enterMenuTreeObj.checkNode(treeNode,null,true);
}

/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function addvcdTreeOnClick(event, treeId, treeNode) {
	//console.log(treeNode);
	addvcdTreeObj.checkNode(treeNode,null,true);
}
var _newId, _newName;

function addHoverDom(treeId, treeNode) {
	if(treeNode.level==0 || treeNode.level==1 || treeNode.level==2 || treeNode.level==3){
	    var sObj = $("#" + treeNode.tId + "_span");
	    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0 || treeNode.tag == 0) return;
	    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增分组' onfocus='this.blur();'></span>";
	/*    if(treeNode.level != 0 && (treeNode.groupFlag == 1 || treeNode.nocheck==true)){
	    	sObj.after(addStr);
	    }*/
	    sObj.after(addStr);
	    var btn = $("#addBtn_" + treeNode.tId);
	    if (btn) {
	        btn.bind("click", function () {
	        	addADDvcdD(treeNode);
	            return false;
	        });
	    }
	}
}        

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
}

function beforeRemove(treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("addvcdInfoTree");
    treeObj.selectNode(treeNode);
    var flag = false;
		var confirmMsg = {
                title: $.i18n.map['prompt'],
                content: $.i18n.map['deleteAreaMsg'] +"["+treeNode.name+"]?",
                tbar: [{
                    text: $.i18n.map['determine'],
                    clsName: "homebg popup-confirm",
                    handler: function (thisPop) {
                    	 fnAjaxRequest(
     	         			    "stAddvcdD/delAddvcdD.do",
     	        				{id:treeNode.id},
     	        				"json",
     	        				"POST",
     	        				function(data){
	     	        				if(!data.success){
	     	       	            		$.Popup.create({ title: $.i18n.map['prompt'], content:  data.msg });
	     	       	            	}else{
	     	       	            	    $.Popup.close(thisPop);
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
	editAddvcdD(treeNode);
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
 * 自定义过滤方法——获取所有选中的最顶级父类
 * @param node
 * @returns {Boolean}
 */
function getTopNodes(node) {
    return ((node.checked == true || node.check_Child_State == 1 || node.check_Child_State==2)&&(node.level==0));
}
function getGroupTopNodes(node) {
	return (node.level==0);
}
/**
 * 判断节点是否选中或存在被选中的子节点
 * @param node
 * @returns {Boolean}
 */
function isCheckedOrHasCheckedChild(node){
	return (node.checked == true || node.check_Child_State == 1 || node.check_Child_State==2);
}
/**
 * 获取子内容
 * @param nTreeObj
 * @param node
 */
function getChilds(nTreeObj, node){
	var childNodes = nTreeObj.getNodesByParam("pId",node.id);
	var array = [];
	
	for(var i=0;i<childNodes.length;i++){
		var cNode = childNodes[i];
		if(isCheckedOrHasCheckedChild(cNode)){
			//console.log(cNode);
			var tmp = {};
			var mod = getChilds(nTreeObj,cNode);
			tmp[cNode.id]=mod;
			array.push(tmp);
		}
	}
	return array;
}

/**
 * 表单验证
 * @returns boolean
 */
function validRoleForm(){
	return $("#sysRoleForm").validate({
        rules: {
        	rolecode: {
        		required: true,
        		specialCharValidate: true,
        		roleCodeExist:true
        	},
        	rolename:  {
        		required: true,
        		specialCharValidate: true
        	},
        	roleremark:{
        		maxlength:100
        	}
        	
  		},
        messages: {
        	rolecode: {
        		required: $.i18n.map['required'],
        		specialCharValidate: $.i18n.map['noSpecialChar'],
        		roleCodeExist: $.i18n.map['exist']
        	},
        	rolename:  {
        		required: $.i18n.map['required'],
        		specialCharValidate: $.i18n.map['noSpecialChar']
        	},
        	roleremark:{
        		maxlength:"长度不能超过100位"
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
 * ajax 验证编码
 */
$.validator.addMethod("roleCodeExist",function(value,element){
    var rolecode = value;
    var id = $("#id").val();
    var enterpriseid = $("#currentEnterpriseId").val();
    var rusult = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
    
	$.post("sysRole/checkRoleCodeExist.do",{
		rolecode : rolecode,
		id : id,
		enterpriseid : enterpriseid
	},function(data){
		if(data.success){
			rusult = false;
    	}else{
    		rusult = true;
    	} 
	},"json");
	  // 恢复异步
    $.ajaxSetup({
        async: true
    });
	return rusult;
},$.i18n.map['exist']);