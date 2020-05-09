/**********************************预警配置脚本***************************************************/
var personaList={};

var personalIds=[];
var personals=[];
$(function() {
	$(".tree").height($(".left").outerHeight()- 10);
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#alarmConfigDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
	$(document).on('click', '#checkPersonAll', function(event) {
		var check = $(this).attr("checked");
		var ckPersonList = $("#personList tbody input[type='checkbox']");
		if(check){
			ckPersonList.attr("checked",true);
		}else{
			ckPersonList.removeAttr("checked");
		}
	})
});

function loadAlarmConfigList(params, flag){
	  showMark();
	$.get("stAlarmConfigure/configList.do",params,function(data){
		$("#alarmConfigContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 预警配置分页
 * @param page
 */
function changePage(page){
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var params = {
			nodeIds : aryIds.toString(),
			isDevice: selectdNode.isDevice,
			pageNo:page
	};
	loadAlarmConfigList(params,false);
}
/**
 * 预警人员分页
 */
function changePersonPage(page){
	var ChoosedPersonList = $("#ChoosedPersonList").val();
	var name = $("#name").val();
	if(name=="请输入预警用户"){
		name = "";
	}
	var params = {
			pageNo:page,
			name_query:encodeURI(name),
			userIdList:ChoosedPersonList
	};
	$.get("stAlarmConfigure/personList.do",params,function(data){
		$("#personList").html(data);
			//hideMark();
		//cookie渲染点击过的图标
        $("input[type='checkbox']:checkbox").each(function(){
            // if (undefined!=this.id&&null!=this.id){
             //    var tempPersonalIs=$.cookie("personalIds");
             //    if (tempPersonalIs!=undefined&&tempPersonalIs!=null){
             //        if (tempPersonalIs.indexOf(",")!=-1){
             //            var tempUserIds=tempPersonalIs.split(",");
             //            for (var i=0;i<tempUserIds.length;i++){
             //                var tempId=tempUserIds[i];
             //                if (this.id==tempId){
             //                    $(this).attr("checked","true");
             //                }
             //            }
             //        }
             //    }
			// }
			//对象
            if (undefined!=this.id&&null!=this.id){
                var tempPersonals=$.cookie("personals");
                if (tempPersonals!=undefined&&tempPersonals!=null){
                    if (tempPersonals.indexOf(",")!=-1){
                        var tempUsers=tempPersonals.split(",");
                        for (var i=0;i<tempUsers.length;i++){
                            var tempUser=tempUsers[i];
                            var tempUserSubs=tempUser.split(",");
                            if (this.id==tempUserSubs[0]){
                                $(this).attr("checked","true");
                            }
                        }
                    }
                }
            }
        })
	});
}
function load(){
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var stcd = $("#stcd").val();
	if(stcd==$.i18n.map['code']){
		stcd = "";
	}
	var params = {
			nodeIds : aryIds.toString(),
			stcd_query:encodeURI(stcd),
			isDevice: selectdNode.isDevice,
	};
	loadAlarmConfigList(params,false);
}
/**
 * 设备配置
 * @param stcd
 */
function smsAlarmSet(stcd){
	var contentMsg = {
			   id:"smsAlarmSetPage",
	           title: "设备【"+stcd+"】短信报警参数设置", 
	           width:"800",
	           url:"stAlarmConfigure/smsAlarmSetPage.do",
	           paraData:{stcd:stcd},
	           requestMethod: 'ajax',
	           tbar: []
	       };
  $.Popup.create(contentMsg);
}
/**
 * 选择预警人员
 */
function selectAlarmPerson(stcd,userIdList,userNameList){
	personalIds=[];
	personals=[];
	var tempPersonalNames=[];
    $.cookie("personalIds",null);
    $.cookie("personals",null);
    if (userIdList!=undefined&&userIdList!=null){
        if (userIdList.indexOf(",")!=-1){
        	var userIds=userIdList.split(",");
            for(var i=0;i<userIds.length;i++){
                var tempId=userIds[i];
                personalIds.push(tempId);
            }
        }else{
            personalIds.push(userIdList);
		}
        $.cookie("personalIds",personalIds);
	}

    if (userNameList!=undefined&&userNameList!=null){
        if (userNameList.indexOf(",")!=-1){
            var userNames=userNameList.split(",");
            for(var i=0;i<userNames.length;i++){
                var tempName=userNames[i];
                tempPersonalNames.push(tempName);
            }
        }else{
            tempPersonalNames.push(userNameList);
        }
    }

    for (var i=0;i<personalIds.length;i++){
    	personals.push(personalIds[i]+":"+tempPersonalNames[i]);
	}
	$.cookie("personals",personals);


	var contentMsg = {
			title: "设备【"+stcd+"】选择预警人员",   
			url:"stAlarmConfigure/personList.do",
			width:"950",
			height:"500",
			paraData:{userIdList:userIdList},
			requestMethod: 'ajax',
			tbar: [{
				text: "选择",
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					var personId = [];
					var personName =[];
					/*
					var selectRow = $("#personList tbody input[type='checkbox']:checked");
					if(selectRow.length>10){
						$.Popup.create({ title: $.i18n.map['prompt'], content: "最多选择10个用户，已选择了"+selectRow.length});
						return false;
					}
					for (var i = 0; i < selectRow.length; i++) {
						personId.push(selectRow[i].value);
						personName.push(selectRow[i].name);
					}*/
					var tempPersonals=$.cookie("personals");
                    if (tempPersonals.indexOf(",")!=-1){
                        var tempUsers=tempPersonals.split(",");
                        if(tempUsers.length>10){
                            $.Popup.create({ title: "提示", content: "最多选择10个用户，已选择了"+tempUsers.length});
                            return false;
                        }
                        for(var i=0;i<tempUsers.length;i++){
                            var tempUser=tempUsers[i];
                            var tempUserSubs=tempUser.split(":");
                            if("" != tempUserSubs[0] && ""!=tempUserSubs[0]){
                            	personId.push(tempUserSubs[0]);
                            	personName.push(tempUserSubs[1]);
                            }
                        }
                    }else{
                        var tempUser=tempPersonals;
                        var tempUserSubs=tempUser.split(":");
                       	personId.push(tempUserSubs[0]);
                        personName.push(tempUserSubs[1]);
                    }
					$("#personId").val(personId.toString());
					$("#person").val(personName.toString());
					$.Popup.close(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}

//选择预警人员
personaList.selectAlarmPerson=function () {
	$(".selectPersonCheckBox").change(function () {
		personalIds=[];
		personalNames=[];
		personals=[];
        var thisPersonCheckBox=this;
		var isSelect=thisPersonCheckBox.checked;
	    if (isSelect==true){
			//之前未选中，准备选中
            var tempPersonals=$.cookie("personals");
            if (tempPersonals!=undefined&&tempPersonals!=null){
                if (tempPersonals.indexOf(",")!=-1){
                    var tempUsers=tempPersonals.split(",");
                    for (var i=0;i<tempUsers.length;i++){
                        var tempUser=tempUsers[i];
                        personals.push(tempUser)
                    }
                }else{
                    personals.push(tempPersonals);
                }
            }
            personals.push(this.id+":"+this.name);
            $.cookie("personals",personals);

	    }else{
            //对象
            var tempPersonals=$.cookie("personals");
            if (tempPersonals!=undefined&&tempPersonals!=null){

                if (tempPersonals.indexOf(",")!=-1){
                    var tempUsers=tempPersonals.split(",");
                    for (var i=0;i<tempUsers.length;i++){
                        var tempUser=tempUsers[i];
                        var tempUserSubs=tempUser.split(":");
                        if (tempUserSubs[0]!=this.id){
                            personals.push(tempUser);
                        }
                    }
                }else{
                    var tempUser=tempPersonals;
                    var tempUserSubs=tempUser.split(":");
                    if (tempUserSubs[0]!=this.id){
                        personals.push(tempUser);
                    }
                }
            }
            $.cookie("personals",personals);
		}
	    	console.log("ids:"+$.cookie("personalIds"));
	    	console.log("names:"+$.cookie("personalNames"));
    })
};

/**
 * 保存预警配置
 */
function saveAlarmConfig(){
	var param = $("#alarmConfigForm").serialize();
	var url = "stAlarmConfigure/logSaveSmsAlarm.do"
	param = encodeURI(param);
	if(validateAlarmConfigForm()){
		showMark();
		fnAjaxRequest(
   			    url,
  				param,
  				"json",
  				"POST",
  				function(data){
	   				if(data.success){
	   				  hideMark();
	   				  $.Popup.close({id:"smsAlarmSetPage"});
	   				  load();
    				}else{
    					$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg  });
    				}
  				}
  	   );
	}
}

/**
 * 属性校验
 * @returns
 */
function validateAlarmConfigForm(){ 
	return $("#alarmConfigForm").validate({
		rules:{
			   waterranges : {
			        required: true,
			        number:true
			   },
			   rainranges :{
				    required: true,
				    number:true
			   },
			   person:{
				   required: true,
			   },
			   content : {
					required: true,
					maxlength:150
			   }
		},
		messages:{
			  waterranges : {
			        required: "必填项",
			        number: "数字"
              },
              rainranges : {
  				    required: "必填项",
  				    number: "数字"
              },
              person:{
				   required: "必填项",
			  },
              content : {
				    required: "必填项",
				    maxlength:"不超过150字"
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

function searchAlarmPerson(){
	 //showMark();
	 var name = $("#name").val();
	 if(name=="请输入预警用户"){
		name = "";
	 }
	 var params = {
		name_query:encodeURI(name)
	 };
	$.get("stAlarmConfigure/personList.do",params,function(data){
		$("#personList").html(data);
			//hideMark();
	});
}

