/**********************************用户功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(".tree").height($(".left").outerHeight()- $(".orgTitle").outerHeight()- 10);
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#sysUserDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
})
/**
 * 条件查询
 */
function searchUserList(){
	var query_username = $("#query_username").val();
	if(query_username == $.i18n.map['userName']){
		query_username="";
	}
	var params = {
			pid:$("#pid").val(),
			currentLevel:$("#currentLevel").val(),
			enterId:$("#currentEnterpriseId").val(),			
			query_username:encodeURI(query_username)
	};
	loadUserList(params);
}
function loadUserList(params,flag){
	showMark();
	$.get("sysUser/list.do",params,function(data){
		$("#userRight").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

function userList(node){
	var enterId = $("#enterId").val();
	var params={pid:node.id,currentLevel:node.level,
			enterId:enterId			
	};
    loadUserList(params);
}

function userList2(node){
	var enterId = $("#enterId").val();
	var params={pid:node.id,currentLevel:node.level,
			enterId:enterId			
	};
	$.get("sysUser/list.do",params,function(data){
		$("#userRight").html(data);
	});
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var query_username = $("#query_username").val();
	if(query_username == $.i18n.map['userName']){
		query_username="";
	}
	var params = {
			pageNo:page,
			pid:$("#pid").val(),
			currentLevel:$("#currentLevel").val(),
			enterId:$("#currentEnterpriseId").val(),			
			query_username:encodeURI(query_username)
	};
	loadUserList(params);
}

/**
 * 新增 
 */
function addUser() {
	var byorganizationid = $("#currentOrganizationId").val();
	var byenterpriseid  = $("#currentEnterpriseId").val();
	if(byorganizationid=="" || byorganizationid==undefined){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['userAddMsg'],width:"380" });
		return false;
	}else{
		//发起请求判断是否为最子集
		$.getJSON("sysUser/isHasChildOrg.do",{
			orgId : byorganizationid
		},function(data){
			if(data.result){
				var contentMsg = {
						title: $.i18n.map['userAddTitle'],   
						url:"sysUser/addPage.do",
						paraData:{byenterpriseid: byenterpriseid,byorganizationid:byorganizationid},
						width:"500",
						requestMethod: 'ajax',
						tbar: [{
							text: $.i18n.map['determine'],
							clsName: "homebg popup-confirm",
							handler: function (thisPop) {
								addAndEditUser(thisPop)
							}
						}]
				};
				$.Popup.create(contentMsg);
			}else{
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectOrgMsg']});
				return false;
			}
		});
	}
}
/**
 * 编辑
 * @param thisPop
 */
function editUser(){
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	if (selectRow.length != 1) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectEditMsg']});
		return;
	}
	var id = $(selectRow[0]).val();
	var contentMsg = {
			title: $.i18n.map['userEditTitle'],   
			url:"sysUser/editPage.do",
			paraData:{id: id},
			width:"500",
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditUser(thisPop)
				}
			}]
	};
	$.Popup.create(contentMsg);
}

function addAndEditUser(thisPop){
	var id = $("#id").val();
	if(id==null || id==undefined){
		url = "sysUser/logAddUser.do";
	}else{
		url = "sysUser/logEditUser.do"; 
	}
	var param = $("#sysUserForm").serialize();
	if(validUserForm()){
		fnAjaxRequest(
				url,
				param,
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
					if(data.success){
						var params = {
								pid:$("#pid").val(),
								currentLevel:$("#currentLevel").val(),
								enterId:$("#currentEnterpriseId").val()			
						};
						loadUserList(params,true);
					}
				}
		);
	}
}
/**
 * 删除用户
 */
function deleteUser(){
	var aId = [];
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		aId.push(selectRow[i].value);
	}
	if (aId.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['initPwdSelMsg']});
		return false;
	}
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['deleteUserMsg'] + "?",
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					fnAjaxRequest(
							"sysUser/logDelUser.do",
							{items: aId.toString()},
							"json",
							"POST",
							function(data){
								fnDSuccess(data,thisPop);
								var params = {
										pid:$("#pid").val(),
										currentLevel:$("#currentLevel").val(),
										enterId:$("#currentEnterpriseId").val()		
								};
								loadUserList(params,true);
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
 * 密码初始化
 */
function initPwdUser(){

	var aId = [];
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		aId.push(selectRow[i].value);
	}
	if (aId.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['initPwdSelMsg']});
		return;
	}
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['initPwdMsg'],
			width:"400",
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					fnAjaxRequest(
							"sysUser/logInitPwdUser.do",
							{items: aId.toString()},
							"json",
							"POST",
							function(data){
								fnDSuccess(data,thisPop);
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
 * 移动到对应机构的页面
 */
function moveUser(){
	var enterId = $("#currentEnterpriseId").val();
	var deId = [];
    var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
    for (var i = 0; i < selectRow.length; i++) {
    	deId.push(selectRow[i].value);
    }
    if (deId.length == 0) {
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['initPwdSelMsg']});
        return;
    }else{
    	var contentMsg = {
                title: $.i18n.map['moveUser'],
                url :"sysUser/movePage.do",
                paraData:{dids: deId.toString(),enterId:enterId},
                width:"400",
                requestMethod: 'ajax',
                tbar: [{
                    text: $.i18n.map['determine'],
                    clsName: "homebg popup-confirm",
                    handler: function (thisPop) {
                    	var treeObj = $.fn.zTree.getZTreeObj("ztreeMoveUser");
                      	var sNodes = treeObj.getSelectedNodes();
                      	var rt = sNodes[0];
                      	var organId = rt.id;
                      	if(rt.children==null){
                      	    //进行分组移动操作
                    	      fnAjaxRequest(
                    	    	"sysUser/logSaveMoveUser.do",
                  				{ids:deId.toString(),organId:organId},
                  				"json",
                  				"POST",
                  				function(data){
                  					if(data.success){
                  						$.Popup.close(thisPop);
                  						var params = {
                  								pid:$("#pid").val(),
                  								currentLevel:$("#currentLevel").val(),
                  								enterId:$("#currentEnterpriseId").val(),			
                  						};
                  						loadUserList(params);
                  					} 
                  				});
                      	}else{
                      	   $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['moveUserMsg']});
               	  		   return false;
                        }
                    }
                }]
            };
          $.Popup.create(contentMsg);
    }
}


/**
 * 配置用户角色
 * @param userId
 * @param userName
 * @param enterId 所属企业id
 */
function setRoleInfo(userId, userName, enterId){
	  var contentMsg = {
	           title: $.i18n.map['configuration'] + "【"+userName+"】",   
	           url:"sysUser/setRoleInfo.do",
	           paraData:{userId:userId,userName:userName,enterId:enterId},
	           width:"500",
	           requestMethod: 'ajax',
	           tbar: [{
	               text: $.i18n.map['determine'],
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            		var nodes = roleTreeObj.getCheckedNodes(true);
	            		var roleIds = [];
	            	    for(var i=0;i<nodes.length;i++){
	            	    	var node = nodes[i];
	            	    	if(!node.isParent && node.level!=0){
	            	    		roleIds.push(node.id);
	            	    	}
	            	    }
	            	    var params = {
	            	    		userId: userId,
	            	    		roleIds : roleIds.join(",")
	            	    };
	            	    showMark();
	            		$.post("sysUser/logSaveUserRole.do",params,function(data){
	            			fnDSuccess(data,thisPop);
	            			hideMarkLoading();
	            		},"json");
	               }
	           }]
	       };
       $.Popup.create(contentMsg);
}

/**
 * 用户角色树初始化
 * @param userId
 * @param userName
 * @param enterId
 */
var roleTreeObj;
function roleTreeInit(userId, userName, enterId){
	var params = {
			userId: userId,
			userName: userName,
			enterId:enterId
	};
	$.getJSON(
			"sysUser/getEnterpriseRole.do",params,
			function(data){
				var setting = {
					 	data:{ 
					        simpleData : {  
					            enable : true 
					        }  
					    },  
						check: {
							enable: true,
							chkStyle: "radio"
						},
						callback : {
							onClick : roleTreeOnClick
						}
				}; 
				var zNodes = data;
				
				roleTreeObj = $.fn.zTree.init($("#roleInfoTree"), setting, zNodes);
				var nodes = roleTreeObj.getNodes(); 
				roleTreeObj.expandNode(nodes[0], true);
			});
}
/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function roleTreeOnClick(event, treeId, treeNode) {
	roleTreeObj.checkNode(treeNode,null,true);
}



/**
 * 表单验证
 * @returns boolean
 */
function validUserForm(){
	return $("#sysUserForm").validate({
		rules: {
				usercode: {
		    		required: true,
		    		specialCharValidate: true,
		    		userCodeExist:true
		    	},
		    	username: {
		    		required:true,
		    		specialCharValidate: true,
		    		checkUserNameExist:true
		    	},
		    	userpwd: {
                    required: true,
                    minlength: 5  
                },
                confirm_userpwd: {
                    required: true,
                    minlength: 5,
                    equalTo: "#userpwd" //自带判断当前文本框值与指定ID为userpwd的文本框的值是否相同
                },
                usertel: {
		    		specialCharValidate: true
		    	}
			},
	    messages: {
		    	usercode: {
		    		required: $.i18n.map['required'],
		    		specialCharValidate: $.i18n.map['noSpecialChar'],
		    		userCodeExist: $.i18n.map['exist']
		    	},
		    	username: {
		    		required:$.i18n.map['required'],
		    		specialCharValidate: $.i18n.map['noSpecialChar'],
		    		checkUserNameExist:$.i18n.map['exist']
		    	},
		    	userpwd: {
                    required: $.i18n.map['required'],
                    minlength: $.validator.format($.i18n.map['minlength']+"{0}")
                },
                confirm_userpwd: {
                    required: $.i18n.map['required'],
                    minlength: $.i18n.map['minlength']+"5",
                    equalTo: $.i18n.map['equalTo']
                },
                usertel: {
		    		specialCharValidate: $.i18n.map['noSpecialChar']
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
$.validator.addMethod("userCodeExist",function(value,element){
    var usercode = value;
    var id = $("#id").val();
    var byenterpriseid = $("#currentEnterpriseId").val();
    var rusult = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
    
	$.post("sysUser/checkUserCodeExist.do",{
		usercode : usercode,
		id : id,
		byenterpriseid : byenterpriseid
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

/***
 * ajax 验证名称
 */
$.validator.addMethod("checkUserNameExist",function(value,element){
    var username = value;
    var id = $("#id").val();
    var byenterpriseid = $("#currentEnterpriseId").val();
    var rusult = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
    
	$.post("sysUser/checkUserNameExist.do",{
		username : username,
		id : id
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
