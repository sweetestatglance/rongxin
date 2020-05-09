/**********************************菜单功能脚本***************************************************/

/**
 * 初始化页面
 */
$(function(){
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#sysMenuList tbody input[type='checkbox']");
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
function changePage(page){
	var params = {
			pageNo:page
			
	};
	loadMenuList(params);
}

/**
 * 从新加载
 */
function loadMenuList(params,flag){
	var url = "sysMenu/index.do";
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
 * 子菜单管理
 */
function menuManager(menuId,menuName,menuCode){
	if(menuCode=="6" || menuCode=="deviceAlarm"){
	  var contentMsg = {
	           title: $.i18n.map['subMenuMan'],   
	           url:"sysMenu/menuPage.do",
	           paraData:{id:menuId},
	           width:"500",
	           requestMethod: 'ajax',
	       };
        $.Popup.create(contentMsg);
	}else{
		$.Popup.create({ title: $.i18n.map['prompt'], content: ""+menuName+$.i18n.map['noSubMenuMsg']});
		return false;
	}
}

/**
 * 查询父菜单名称
 */
function searchMenu(){
	var menuName = $("#menuName").val();
	if(menuName==$.i18n.map['menuName']){
		menuName = "";
	}
	showMark();
	$.ajax({
		url : "sysMenu/index.do",
		data : {menuName:encodeURI(menuName)},
		success : function(data){
			$("#twoContain").html(data);
			hideMark();
	}});
}
/**
 * 编辑菜单
 */
function editMenu(){
	  var chk_value =[];   
	  $('input[name="chk"]:checked').each(function(){    
		   chk_value.push($(this).val());    
	  });
	  
	  if(chk_value.length>1 || chk_value==""){
		  $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectEditMsg'] });
		  return false;
	  }
	  var parentName = "";
	  editMethod(chk_value.toString(),parentName,1,"");
}
/**
 * 新增子菜单
 */
function addChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = sNodes[0].name;
  	var id = sNodes[0].id;
  	var level = sNodes[0].level;
  	var pid = sNodes[0].pId;
  	if(pid!=null){
  		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['menuAddMsg'] });
    	return false;
  	}
	var contentMsg = {
	           title: $.i18n.map['menuAddTitle'],   
	           url:"sysMenu/addPage.do",
	           width:"500",
	           paraData:{id:id,level:level,menuName:encodeURI(name)},
	           requestMethod: 'ajax',
	           tbar: [{
	               text: $.i18n.map['determine'],
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            		  var param = $("#sysMenuForm").serialize();
	            	   	  if(validMenuForm()){
	            	         	  fnAjaxRequest(
	            	         			    "sysMenu/addMenu.do",
	            	        				param,
	            	        				"json",
	            	        				"POST",
	            	        				function(data){
	            	         			    	fnDSuccess(data,thisPop);
	            	         			    	if(data.success){
	            	         			    		ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId="+menuId,null);
	            	          					} 
	            	        				}
	            	        	   );
	            	   	  }
	               }
	           }]
	       };
    $.Popup.create(contentMsg);
}
/**
 * 子菜单修改
 */
function editChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var level = sNodes[0].level;
  	var id = sNodes[0].id;
  	if(level==0){
  		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['menuEditMsg'] });
  		return false;
  	}else{
  		 var parentName = sNodes[0].getParentNode().name;
  		 editMethod(id,parentName,0,menuId);
  	}
}
/**
 * 删除子菜单
 */
function delChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = sNodes[0].name;
  	var id = sNodes[0].id;
  	var level = sNodes[0].level;
    if(level==0){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['menuDelMsg'] });
    	return false;
    }else{
    	var rt = sNodes[0];
      	if(rt.children==null){
      		var confirmMsg = {
                    title: $.i18n.map['menuAddTitle'],
                    content:  $.i18n.map['confirmDelMenuMsg'] + "["+name+"]?",
                    tbar: [{
                        text: $.i18n.map['determine'],
                        clsName: "homebg popup-confirm",
                        handler: function (thisPop) {
                        	 fnAjaxRequest(
         	         			    "sysMenu/delMenu.do",
         	        				{id:id},
         	        				"json",
         	        				"POST",
         	        				function(data){
         	        					fnDSuccess(data,thisPop);
        	         			    	if(data.success){
        	         			    		ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId="+menuId,null);
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
      	}else{
      		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['confirmDelMenuErr'] });
	  		return false;
  	    }
    }
}

/**
 * 编辑公用方法
 * @param id
 * @param parentName
 * @param load
 * @param menuId
 */

function editMethod(id,parentName,load,menuId){
	  var contentMsg = {
	           title: $.i18n.map['menuEditTitle'],   
	           url:"sysMenu/editPage.do",
	           width:"500",
	           paraData:{id:id,parentName:parentName},
	           requestMethod: 'ajax',
	           tbar: [{
	               text: $.i18n.map['determine'],
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            		  var param = $("#sysMenuForm").serialize();
	            	   	  if(validMenuForm()){
	            	         	  fnAjaxRequest(
	            	         			    "sysMenu/editMenu.do",
	            	        				param,
	            	        				"json",
	            	        				"POST",
	            	        				function(data){
	            	         			    	fnDSuccess(data,thisPop);
	            	          					if(data.success){
	            	          						if(load==1){
		            	        						loadMenuList(null,true);
		            	        					}else{
		            	        						ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId="+menuId,null);
		            	        					}
	            	          					} 
	            	        				}
	            	        	   );
	            	   	  }
	               }
	           }]
	       };
    $.Popup.create(contentMsg);
}

/**
 * 表单验证
 */
function validMenuForm(){ 
	return $("#sysMenuForm").validate({
		rules:{
			menucode : {
			    required: true,
			    menuCodeExist: true
			},
			menuname : {
			    required: true,
			    specialCharValidate: true
			},
			menuorder : {
			    required: true,
			    digits: true
			},
			menuurl : {
			    required: true
			}
		},
		messages:{
			menucode : {
			    required: $.i18n.map['required'],
			    menuCodeExist:$.i18n.map['exist']
            },
			menuname : {
			    required: $.i18n.map['required'],
			    specialCharValidate: $.i18n.map['noSpecialChar']
            },
			menuorder :{
			    required: $.i18n.map['required'],
			    digits:$.i18n.map['digits']
            },
		    menuurl : {
			    required: $.i18n.map['required']
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
 * ajax 验证菜单编码是否重复
 */
$.validator.addMethod("menuCodeExist",function(value,element){
    var menuCode = value;
	var id = $("#id").val();
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("sysMenu/checkMenuCodeExist.do",{
		menuCode : menuCode,
		id:id
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
},$.i18n.map['exist']);