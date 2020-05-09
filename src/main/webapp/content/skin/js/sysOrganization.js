/**********************************机构功能脚本***************************************************/
/**
 * 新增机构信息
 */
function addOrgan(enterId){
		var treeObj = $.fn.zTree.getZTreeObj("organTree");
	  	var sNodes = treeObj.getSelectedNodes();
	  	var name = sNodes[0].name;
	  	var id = sNodes[0].id;
	  	var level = sNodes[0].level;
		var contentMsg = {
		           title: $.i18n.map['organAddTitle'],   
		           url:"sysOrganization/addPage.do",
		           width:"550",
		           paraData:{enterId:enterId,organName:encodeURI(name),organId:id,level:level},
		           requestMethod: 'ajax',
		           tbar: [{
		               text: $.i18n.map['determine'],
		               clsName: "homebg popup-confirm",
		               handler: function (thisPop) {
		            	   organAddOrEditSubmit(thisPop,enterId);
		               }
		           }]
		       };
	    $.Popup.create(contentMsg);
}

/**
 * 编辑机构信息
 */
function editOrgan(enterId){
	var treeObj = $.fn.zTree.getZTreeObj("organTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	var name = sNodes[0].name;
  	var level = sNodes[0].level;
    if(level==0){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['topNodeEditMsg'] });
    	return false;
    }
	var parentName = sNodes[0].getParentNode().name;
    var contentMsg = {
		           title: $.i18n.map['organEditTitle'],   
		           url:"sysOrganization/editPage.do",
		           width:"500",
		           paraData:{id:id,enterId:enterId,parentName:encodeURI(parentName)},
		           requestMethod: 'ajax',
		           tbar: [{
		               text: $.i18n.map['determine'],
		               clsName: "homebg popup-confirm",
		               handler: function (thisPop) {
		            	   organAddOrEditSubmit(thisPop,enterId);
		               }
		           }]
		       };
	 $.Popup.create(contentMsg);
}
function organAddOrEditSubmit(thisPop,enterId){
	  var url = "";
	  var id = $("#id").val();
	  if(id==null || id==undefined){
			url = "sysOrganization/logAddOrgan.do";
	  }else{
			url = "sysOrganization/logEditOrgan.do"; 
	  }
	  var param = $("#sysOrganizationForm").serialize();
  	  if(validOrganForm()){
        	  fnAjaxRequest(
        			url,
       				param,
       				"json",
       				"POST",
       				function(data){
        			    	fnDSuccess(data,thisPop);
        			    	/*loadOrgUserList();*/
        			    	if(data.success){
        			    		ztreeFun($("#organTree"),"sysOrganization/organListToUser.do?enterId="+enterId,userList2);
         					} 
        			    	
       				}
       	   );
  	  }
}

/**
 * 删除机构信息
 */
function delOrgan(enterId){
	var treeObj = $.fn.zTree.getZTreeObj("organTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = sNodes[0].name;
  	var id = sNodes[0].id;
  	var level = sNodes[0].level;
    if(level==0){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['topNodeDelMsg'] });
    	return false;
    }
	var rt = sNodes[0];
  	if(rt.children==null){
 		var confirmMsg = {
                title: $.i18n.map['prompt'],
                content:  $.i18n.map['deleteOrganMsg'] + "["+name+"]?",
                tbar: [{
                    text: $.i18n.map['determine'],
                    clsName: "homebg popup-confirm",
                    handler: function (thisPop) {
                    	 fnAjaxRequest(
     	         			    "sysOrganization/logDelOrgan.do",
     	        				{id:id},
     	        				"json",
     	        				"POST",
     	        				function(data){
     	        					fnDSuccess(data,thisPop);
     	        					if(data.success==true)
     	        					treeObj.removeNode(rt,null);
//    	         			    	if(data.success){
//    	         			    		ztreeFun($("#organTree"),"sysOrganization/organListToUser.do?enterId="+enterId,null);
//    	          					} 
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
  		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['parentNodeDelMsg'] });
	  	 return false;
  	}
    
}

function validOrganForm(){ 
	return $("#sysOrganizationForm").validate({
		rules:{
			organcode : {
			    required: true,
			    specialCharValidate: true,
			    organCodeExist: true
			   },
			   organname : {
			    required: true,
			    specialCharValidate: true,
			    organNameExist: true
			   }
		},
		messages:{
			organcode : {
			    required: $.i18n.map['required'],
			    specialCharValidate: $.i18n.map['noSpecialChar'],
			    organCodeExist:$.i18n.map['exist']
			    	
              },
              organname :{
			    required: $.i18n.map['required'],
			    specialCharValidate: $.i18n.map['noSpecialChar'],
			    organNameExist:$.i18n.map['exist']
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
 * ajax 验证机构编码，不同企业可以相同
 */
$.validator.addMethod("organCodeExist",function(value,element){
    var organCode = value;
	var enterId = $("#enterpriseid").val();
	var id = $("#id").val();
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("sysOrganization/checkOrganCodeExist.do",{
		organCode : organCode,
		enterId : enterId,
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

/***
 * ajax 验证机构名称，不同企业可以相同
 */
$.validator.addMethod("organNameExist",function(value,element){
    var organName = value;
	var enterId = $("#enterpriseid").val();
	var id = $("#id").val();
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("sysOrganization/checkOrganNameExist.do",{
		organName : organName,
		enterId : enterId,
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