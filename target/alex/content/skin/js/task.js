/**********************************任务相关功能脚本***************************************************/
var currentTaskRequest;
/**
 * 任务列表
 */
var popOpt;
function taskList(stcd){
	var deId = [];
	if(stcd==null || stcd==undefined){
		var selectRow = $("#stBprpBDiv tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			deId.push(selectRow[i].value);
		}
	}else{
		deId = stcd;
	}
	var title = $.i18n.map['taskList'];
	if(stcd!=null)
		title = title+"【"+stcd+"】";
	if (deId.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['operateDevice']});
		return false;
	}else{
		var contentMsg = {
				title: title,   
				url:"deviceTask/list.do",
				width:"840",
				paraData:{dids: deId.toString()},
				requestMethod: 'ajax'
		};
		popOpt = $.Popup.create(contentMsg);
	}
}

/**
 * 任务查询
 */
function taskSearch(ids){
	var beginTime = $("#create_beginTime").val();
	var endTime = $("#create_endTime").val();
	var query_rtucode = $("#query_rtucode").val();
	var flag = $("#flag").val();
	var url = "deviceTask/list.do";
	var data = {"dids": ids,"beginTime":beginTime,"endTime":endTime,"query_rtucode":query_rtucode,"flag":flag};
	showMark();
	$(".mask").css("z-index","205");
	$.ajax({
		url : url,
		data : data,
		success :function(data){
			$("#taskDiv").html(data);
			$(".mask").css("z-index","99");
			hideMarkLoading();
		}
	});
}
/**
 * 查看
 */
function lookDetail(id){
	var contentMsg = {
			title: $.i18n.map['seeTaskDetails'],   
			url:"deviceTask/taskDetail.do",
			width:"500",
			paraData:{id:id},
			requestMethod: 'ajax'
	};
	$.Popup.create(contentMsg);
}

/**
 * 分页查询
 * @param page
 */
function changeTaskPage(page){
	var beginTime = $("#create_beginTime").val();
	var endTime = $("#create_endTime").val();
	var query_rtucode = $("#query_rtucode").val();
	var flag = $("#flag").val();
	var ids = $("#ids").val();
	var param = {"pageNo":page,"dids": ids,"beginTime":beginTime,"endTime":endTime,"query_rtucode":query_rtucode,"flag":flag};
	showMark();
	$.get("deviceTask/list.do", param, function(data) {
		hideMarkLoading();
		if(flag=="config"){
			$("#taskContent").html(data);
		}else{
			popOpt.popObj.find("div.popup-content").html(data);
		}
		
	});
}
/**
 * 人工抓拍-外面摄像头
 */
function capture(stcd){
	showMark();
	//先查看该设备是否有摄像头
	fnAjaxRequest(
			"task/isCamera.do",
			{stcd:stcd},
			"json",
			"POST",
			function (data) {
				hideMark();
				if(data.success){
					//有摄像头下发抓拍任务
					var contentMsg = {
							title: $.i18n.map['artificialCapture']+"【"+stcd+"】", 
							width:"500",
							url:"task/sbPhoto.do",
							paraData:{stcd:stcd},
							requestMethod: 'ajax',
							tbar: [{
								text: $.i18n.map['determine'],
								clsName: "homebg popup-confirm",
								handler: function (thisPop) {
									photoStbprpB(thisPop);
								}
							}]
					};
					$.Popup.create(contentMsg);
				}else{
					$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
				}
			});
}

/**
 * 拍照保存
 */
function photoStbprpB(thisPop){
	var photo = "";
	var photo1 = $('#photoTable input[name="photo1"]:checked').val(); 
	var photo2 = $('#photoTable input[name="photo2"]:checked').val(); 
	var photo3 = $('#photoTable input[name="photo3"]:checked').val(); 
	var photo4 = $('#photoTable input[name="photo4"]:checked').val();
	var stcd = $("#deviceId").val();
	if(photo1 == undefined && photo2 == undefined && photo3 == undefined && photo4 == undefined){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectChannel']});
		return false;
	}else{
		if(photo1!= undefined){
			photo = photo+photo1+",";
		}
		if(photo2!= undefined){
			photo = photo+photo2+",";
		}
		if(photo3!= undefined){
			photo = photo+photo3+",";
		}
		if(photo4!= undefined){
			photo = photo+photo4+",";
		}
		showMark();
		$(".mask").css("z-index","205");
		fnAjaxRequest(
				"task/doCaptureTask.do",
				{photo:photo,stcd:stcd},
				"json",
				"POST",
				function (data) {
					$.Popup.close(thisPop);
					$(".mask").css("z-index","99");
					hideMark();
					if(data=="HttpError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
						return false;
					}else if(data=="NullError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
						return false;
					}else if(data=="SocketError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
						return false;
					}else if(data=="SecurityError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
						return false;
					}else if(data=="SocketTimeOutError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
						return false;
					}else if(data=="UnknownError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
						return false;
					}else{

						$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
					}
				});
	}
}
/**
 * 版本信息
 */
function version(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	//终止前一个请求，防止冲突
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = fnAjaxRequest(
			"task/readVersion.do",
			{stcd:stcd},
			"json",
			"POST",
			function (data) {
				if(data=="HttpError"){
					$("#taskContent").html("Connection refused: Please contact administrator!");
					return false;
				}else if(data=="NullError"){
					$("#taskContent").html($.i18n.map['cannotResolved']);
					return false;
				}else if(data=="SocketError"){
					$("#taskContent").html("Connection reset Or Connect reset by peer:Socket write error!");
					return false;
				}else if(data=="SecurityError"){
					$("#taskContent").html($.i18n.map['responseFailed']);
					return false;
				}else if(data=="SocketTimeOutError"){
					$("#taskContent").html($.i18n.map['timeOut']);
					return false;
				}else if(data=="UnknownError"){
					$("#taskContent").html($.i18n.map['unknownError']);
					return false;
				}else{
					$("#taskContent").html(data.msg);
				}
			});
}
/**
 * 查询状态
 */
function status(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	//终止前一个请求，防止冲突
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = fnAjaxRequest(
			"task/readStatus.do",
			{stcd:stcd},
			"json",
			"POST",
			function (data) {
				if(data=="HttpError"){
					$("#taskContent").html("Connection refused: Please contact administrator!");
					return false;
				}else if(data=="NullError"){
					$("#taskContent").html($.i18n.map['cannotResolved']);
					return false;
				}else if(data=="SocketError"){
					$("#taskContent").html("Connection reset Or Connect reset by peer:Socket write error!");
					return false;
				}else if(data=="SecurityError"){
					$("#taskContent").html($.i18n.map['responseFailed']);
					return false;
				}else if(data=="SocketTimeOutError"){
					$("#taskContent").html($.i18n.map['timeOut']);
					return false;
				}else if(data=="UnknownError"){
					$("#taskContent").html($.i18n.map['unknownError']);
					return false;
				}
				else{
					if(data.success == true){
						currentTaskRequest = $.ajax({
							url : "task/statusPage.do",
							data : {stcd:stcd,factoryStr:data.msg,requestContent:data.obj},
							success : function(data){
								$("#taskContent").html(data);
							},
							error: function (xhr, error, thrown) {
							}
						});
					}else{ 
						$("#taskContent").html(data.msg);
						return false;
					}
				}			
			});
}
/**
 * 设备重启
 */
function restart(stcd){
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['deviceRestart'],
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					showMark();
					fnAjaxRequest(
							"task/logTaskRestart.do",
							{stcd:stcd},
							"json",
							"POST",
							function (data) {
								$.Popup.close(thisPop);
								if(data=="HttpError"){
	        						 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
				           		     return false;
					           	}else if(data=="NullError"){
					           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
					           		 return false;
					           	}else if(data=="SocketError"){
					           		 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
					           		 return false;
					           	}else if(data=="SecurityError"){
					           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
					           		 return false;
					           	}else if(data=="SocketTimeOutError"){
					           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
					           		 return false;
					           	}else if(data=="UnknownError"){
					           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
					           	     return false;
					           	}else{
									$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
								}
							});
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
 * 远程配置页面
 */
function distance(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = fnAjaxRequest(
			"task/findDeviceDistance.do",
			{stcd:stcd},
			"json",
			"POST",
			function (data) {
				if(data=="HttpError"){
					$("#taskContent").html("Connection refused: Please contact administrator!");
					return false;
				}else if(data=="NullError"){
					$("#taskContent").html($.i18n.map['cannotResolved']);
					return false;
				}else if(data=="SocketError"){
					$("#taskContent").html("Connection reset Or Connect reset by peer:Socket write error!");
					return false;
				}else if(data=="SecurityError"){
					$("#taskContent").html($.i18n.map['responseFailed']);
					return false;
				}else if(data=="SocketTimeOutError"){
					$("#taskContent").html($.i18n.map['timeOut']);
					return false;
				}else if(data=="UnknownError"){
					$("#taskContent").html($.i18n.map['unknownError']);
					return false;
				}else{
					if(data.success == true){
						currentTaskRequest = $.ajax({
							url : "task/taskDistancePage.do",
							data : {disStr:data.msg,ids:stcd},
							success : function(data){
								$("#taskContent").html(data);
							},
							error: function (xhr, error, thrown) {
							}
						});
					}else{
						$("#taskContent").html(data.msg);
						return false;
					}
				}
			});
}

/**
 * 保存远程配置
 */
function saveDistance(){
	var main1Type = $("#main1Type").val();
	var main1Address  = $("#main1Address").val();
	var centerAddress = $("#centerAddress").val();
	var pwd = $("#pwd").val();
	var number = $("#number").val();
	var workModel = $("#workModel").val();
	var main2Type = $("#main2Type").val();
	var main2Address = $("#main2Address").val();
	var main3Type = $("#main3Type").val();
	var main3Address = $("#main3Address").val();
	var main4Type = $("#main4Type").val();
	var main4Address = $("#main4Address").val();
    if(centerAddress=="" && pwd=="" && number=="" && workModel==0 && main1Type==-1 && main1Address=="" && main2Type==-1 && main2Address=="" && main3Type==-1 && main3Address=="" && main4Type==-1 && main4Address==""){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['noConfigured']});
        return false;
	}
    if(centerAddress==""){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['addressNoEmpty']});
		return false;
    }
    if(pwd==""){
    	$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['passwordNoEmpty']});
        return false;
    }
    if(main1Type!=-1){
    	if(main1Type!=0 && main1Address==""){
    		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['centralStation1']});
    		return false;
    	}
    }
	if(main2Type!=-1){
		if(main2Type!=0 && main2Address==""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['centralStation2']});
			return false;
		}
	}
	if(main3Type!=-1){
		if(main3Type!=0 && main3Address==""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['centralStation3']});
			return false;
		}
	}
	if(main4Type!=-1){
		if(main4Type!=0 && main4Address==""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['centralStation4']});
			return false;
		}
	}
	if(!$('#distanceForm').data('changed')) { 
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['noOperation']});
		return false; 
	}
	var groupId = $("#groupId").val();
    param ="&groupId="+groupId;
	var updateForm = $("#updateForm").val();
	var newStr = updateForm.split(",");
    $.each(newStr,function(n,id) {
    	var val = $("#"+id).val();
    	param = param+"&"+id+"="+val; 
    	if(id=="main1Type" && updateForm.indexOf("main1Address")<0){
    		param = param+"&main1Address="+main1Address; 
    	}
    	if(id=="main1Address" && updateForm.indexOf("main1Type")<0){
    		param = param+"&main1Type="+main1Type; 
    	}
    	if(id=="main2Type" && updateForm.indexOf("main2Address")<0){
    		param = param+"&main2Address="+main2Address; 
    	}
    	if(id=="main2Address" && updateForm.indexOf("main2Type")<0){
    		param = param+"&main2Type="+main2Type; 
    	}
    	if(id=="main3Type" && updateForm.indexOf("main3Address")<0){
    		param = param+"&main3Address="+main3Address; 
    	}
    	if(id=="main3Address" && updateForm.indexOf("main3Type")<0){
    		param = param+"&main3Type="+main3Type; 
    	}
    	if(id=="main4Type" && updateForm.indexOf("main4Address")<0){
    		param = param+"&main4Address="+main4Address; 
    	}
    	if(id=="main4Address" && updateForm.indexOf("main4Type")<0){
    		param = param+"&main4Type="+main4Type; 
    	}
    });  
	
	 if(distanceValid()){
			var confirmMsg = {
		            title: $.i18n.map['prompt'],
		            width:"400",
		            content: $.i18n.map['deviceRestartPrompt'],
		            tbar: [{
		                text: $.i18n.map['determine'],
		                clsName: "homebg popup-confirm",
		                handler: function (thisPop) {
		                	 if(currentTaskRequest) currentTaskRequest.abort();
		                		currentTaskRequest = fnAjaxRequest(
		 	         			    "task/logDiDeviceSetting.do",
		 	         			     param,
		 	        				"json",
		 	        				"POST",
		 	        				function(data){
		 	        					 $.Popup.close(thisPop);
		 	        					if(data=="HttpError"){
		 	        						 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
						           		     return false;
							           	}else if(data=="NullError"){
							           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
							           		 return false;
							           	}else if(data=="SocketError"){
							           		 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
							           		 return false;
							           	}else if(data=="SecurityError"){
							           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
							           		 return false;
							           	}else if(data=="SocketTimeOutError"){
							           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
							           		 return false;
							           	}else if(data=="UnknownError"){
							           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
							           	     return false;
							           	}else{
										    if (data.msg) {
										    	$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
										    }
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
}


/**
 * 远程升级--页面
 */
function upgrade(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = $.ajax({
		url : "task/taskUpgradePage.do",
		data : {ids:stcd},
		success : function(data){
			$("#taskContent").html(data);
		},
		error: function (xhr, error, thrown) {
		}
	});
}

/**
 * 保存远程升级的参数
 */
function upgradeDevice(){
	var address = $("#address").val();
	var port = $("#port").val();
	var file = $("#file").val();
	var deviceId = $("#deviceId").val();
	var param = $("#upgradeForm").serialize();
	param=param+"&ids="+deviceId;
	if(address=="" && port=="" && file==""){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['noParamConfig']});
        return false;
	}else{
		 if(upgradeValid()){
		   	 fnAjaxRequest(
			            "task/logUpgradeToTask.do",
			            param,
			            "json",
			            "POST",
			            function (data) {
			            	if(data=="HttpError"){
			              		 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
			              		 return false;
			   	           	}else if(data=="NullError"){
			   	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
			   	           		 return false;
			   	           	}else if(data=="SocketError"){
			   	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
			   	           		 return false;
			   	           	}else if(data=="SecurityError"){
			   	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
				           		 return false;
				           	}else if(data=="SocketTimeOutError"){
				           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
				           		 return false;
				           	}else if(data=="UnknownError"){
				           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
				           	     return false;
				           	}else{
				           		$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
			   	           	}
			            });
             }
	    }
}
/**
 * 远程升级 验证
 */
function upgradeValid(){ 
	return $("#upgradeForm").validate({
		rules:{
			port : {
			    digits:true,
			    range: [1, 65535] 
			   },
		   address : {
			   byteMaxLength:20
			   },
		   file :{
			   byteMaxLength:40
		   }
			
		},
		messages:{
			port : {
				required: $.i18n.map['required'],
				number: $.i18n.map['number'],
				digits:"1~65535"+$.i18n.map['digits'],
				range:"1~65535"+$.i18n.map['digits']
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

/**
 * 人工抓拍,在远程配置里面
 */
function captureTask(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	//先查看该设备是否有摄像头
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = fnAjaxRequest(
			"task/isCamera.do",
			{stcd:stcd},
			"json",
			"POST",
			function (data) {
				if(data.success){
					//有摄像头下发抓拍任务
					$.ajax({
						url : "devicePhoto/list.do",
						data : {deviceNo:stcd},
						success : function(data){
							$("#taskContent").html(data);
						},
						error: function (xhr, error, thrown) {
						}
					});
				}else{
					$("#taskContent").html(data.msg);
				}
			});
}

/**
 * 拍照保存,在远程配置里面
 */
function photoSave(){
	var photo = "";
	var photo1 = $('#photoTable input[name="photo1"]:checked').val(); 
	var photo2 = $('#photoTable input[name="photo2"]:checked').val(); 
	var photo3 = $('#photoTable input[name="photo3"]:checked').val(); 
	var photo4 = $('#photoTable input[name="photo4"]:checked').val();
	var stcd = $("#deviceId").val();
	if(photo1 == undefined && photo2 == undefined && photo3 == undefined && photo4 == undefined){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectChannel']});
		return false;
	}else{
		if(photo1!= undefined){
			photo = photo+photo1+",";
		}
		if(photo2!= undefined){
			photo = photo+photo2+",";
		}
		if(photo3!= undefined){
			photo = photo+photo3+",";
		}
		if(photo4!= undefined){
			photo = photo+photo4+",";
		}
		fnAjaxRequest(
				"task/doCaptureTask.do",
				{photo:photo,stcd:stcd},
				"json",
				"POST",
				function (data) {
					if(data=="HttpError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
						return false;
					}else if(data=="NullError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
						return false;
					}else if(data=="SocketError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
						return false;
					}else if(data=="SecurityError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
						return false;
					}else if(data=="SocketTimeOutError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
						return false;
					}else if(data=="UnknownError"){
						$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
						return false;
					}else{
						$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
						captureTask(stcd);
					}
				});
	}
}

/**
 * 预警设定
 */
function alarmSetting(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = fnAjaxRequest(
            "task/findAlarmSetting.do",
            {id:stcd},
            "json",
            "POST",
            function (data) {
            	if(data=="HttpError"){
					$("#taskContent").html("Connection refused: Please contact administrator!");
					return false;
				}else if(data=="NullError"){
					$("#taskContent").html($.i18n.map['cannotResolved']);
					return false;
				}else if(data=="SocketError"){
					$("#taskContent").html("Connection reset Or Connect reset by peer:Socket write error!");
					return false;
				}else if(data=="SecurityError"){
					$("#taskContent").html($.i18n.map['responseFailed']);
					return false;
				}else if(data=="SocketTimeOutError"){
					$("#taskContent").html($.i18n.map['timeOut']);
					return false;
				}else if(data=="UnknownError"){
					$("#taskContent").html($.i18n.map['unknownError']);
					return false;
				}else{
	           		if(data.success == true){
	           			$.ajax({
							url : "task/alarmPage.do",
							data : {disStr:data.msg,stcdId:stcd},
							success : function(data){
								$("#taskContent").html(data);
							},
							error: function (xhr, error, thrown) {
							}
						});
	            	}else{
	            		$("#taskContent").html(data.msg);
	            	}
	           	}
         });
}

/**
 * 修改报警设定要素
 */
function updateAlarm(){
	var rain20 = $("#rain20").val();
	var water39 = $("#water39").val();
	var stcdId = $("#stcdId").val();
	if(rain20 == "" && water39 == ""){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['noConfigured']});
        return false;
	}
	if(!$('#alarmForm').data('changed')) { 
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['noOperation']});
		return false; 
	}
	var updateAlarmForm = $("#updateAlarmForm").val();
	var newStr = updateAlarmForm.split(",");
	var param = "&stcdId="+stcdId;
    $.each(newStr,function(n,id) {
    	var val = $("#"+id).val();
    	param = param+"&"+id+"="+val; 
    });
    if(alarmValid()){
    	if(rain20 == "0"){
    		var confirmMsg = {
        			title: $.i18n.map['prompt'],
        			content: $.i18n.map['warnPromt1']+"<br>"+$.i18n.map['warnPromt2'],
        			tbar: [{
        				text: $.i18n.map['determine'],
        				clsName: "homebg popup-confirm",
        				handler: function (thisPop) {
        					$.Popup.close(thisPop);
        					requestTaskAlarm(param);
        				}
        			}, {
        				text: $.i18n.map['canecl'],
        				clsName: "homebg popup-cancel",
        				handler: function (thisObj) {
        					$.Popup.close(thisObj);
        				}
        			}]
        	};
        	$.Popup.create(confirmMsg);
    		
    	}else{
    		requestTaskAlarm(param);
    	}
    	
    }
}

function requestTaskAlarm(param){
	fnAjaxRequest(
            "task/logAlarmSetting.do",
            param,
            "json",
            "POST",
            function (data) {
            	if(data=="HttpError"){
             		 $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection refused: Please contact administrator!"});
             		 return false;
  	           	}else if(data=="NullError"){
  	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['cannotResolved']});
  	           		 return false;
  	           	}else if(data=="SocketError"){
  	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: "Connection reset Or Connect reset by peer:Socket write error!"});
  	           		 return false;
  	           	}else if(data=="SecurityError"){
  	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['responseFailed']});
	           		 return false;
	           	}else if(data=="SocketTimeOutError"){
	           		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['timeOut']});
	           		 return false;
	           	}else if(data=="UnknownError"){
	           	     $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['unknownError']});
	           	     return false;
	           	}else{
	           		$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
	           		$('#alarmForm').data('changed',false);
  	           	}
            });
}

/**
 * 配置-里面的任务列表
 * @param stcd
 */
function taskInfo(stcd){
	$("#taskContent").html("<div class='control-loading' style='height:330px;width:580px;'></div>");
	if(currentTaskRequest) currentTaskRequest.abort();
	currentTaskRequest = $.ajax({
		url : "deviceTask/list.do",
		data : {dids: stcd,flag:'config'},
		success : function(data){
			$("#taskContent").html(data);
		},
		error: function (xhr, error, thrown) {
		}
	});
}

/**
 * 远程配置 验证
 */
function distanceValid(){ 
	return $("#distanceForm").validate({
		rules:{
			centerAddress:{
				required: true,
				digits: true
			},
			number : {
				isMobile: true
			},
			mainAddress : {
				ip: true
			},
			bakAddress :{
				ip: true
		    },
			pwd : {
				required: true,
			    digits: true,
			    range: [1, 65535] 
			}
			
		},
		messages:{
			centerAddress:{
				required: $.i18n.map['required'],
				digits: $.i18n.map['digits']
			},
			number : {
				isMobile: $.i18n.map['isMobile']
            },
		    mainAddress : {
			    ip:$.i18n.map['ipAddressPort']
		    },
		    bakAddress :{
				ip:$.i18n.map['ipAddressPort']
		    },
		    pwd : {
		    	required: $.i18n.map['required'],
				digits:"1~65535"+$.i18n.map['digits'],
				range:"1~65535"+$.i18n.map['digits']
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


function alarmValid(){
	return $("#alarmForm").validate({
		rules:{
			rain20 : {
				number:true,
				digits:true,
				range: [0, 99]
			},
			water39 : {
				number:true,
				range: [0.01, 99.99],
				twoDecimalPlaces:true
			}
		},
		messages:{
			rain20 : {
				number: $.i18n.map['number'],
				digits: "1~99"+$.i18n.map['digits'],
				range:"1~99"
            },
            water39 : {
            	number: $.i18n.map['number'],
            	range:"0.01~99.99",
            	twoDecimalPlaces:$.i18n.map['twoDecimal']
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
