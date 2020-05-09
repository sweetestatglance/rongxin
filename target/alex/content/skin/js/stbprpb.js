/**********************************设备功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(".tree").height($(".left").outerHeight() - 10);
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#stBprpBDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
})

/**
 * 点击区域，加载右侧设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadStbprpList(params, flag){
	showMark();
	$.get("stStbprpB/list.do",params,function(data){
		$("#deviceContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 查询测站编码
 */
function searchStcd(){
	showMark();
	var dstcd = $("#dstcd").val();
	if(dstcd==$.i18n.map['code']){
		dstcd = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;

	$.ajax({
		url : "stStbprpB/list.do",
		data : {id:id,stcd:encodeURI(dstcd)},
		success : function(data){
			$("#deviceContent").html(data);
			hideMark();
		},
		error: function (xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	showMark();
	var dstcd = $("#dstcd").val();
	if(dstcd==$.i18n.map['code']){
		dstcd = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;

	$.ajax({
		url : "stStbprpB/list.do",
		data : {id:id,stcd:encodeURI(dstcd),pageNo:page},
		success : function(data){
			$("#deviceContent").html(data);
			hideMark();
		},
		error: function (xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 设备在线，离线刷新
 */
function refresh(){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;
	var enterpriseid = $("#enterpriseid").val();
	var params = {
			id:id,
			enterId:enterpriseid
	};
	loadStbprpList(params);
}

/**
 * 新增设备
 */
function addStBprpB(){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var name = "";
	var id = "";
	if(sNodes==""){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['selectAreaPrompt']});
		return false;
	}else{
		id = sNodes[0].id;
		name = sNodes[0].name;
	}
	var width=900;
	var userLogin =$.i18n.map['userLogin'];
    if(userLogin=="User login"){
    	width=1100;
    }
	var rt = sNodes[0];
	if(rt.children==null){
		//先判断企业允许添加最多的设备数量是否超过
		fnAjaxRequest(
				"stStbprpB/stbprpBCountResult.do",
				{},
				"json",
				"POST",
				function (data) {
					if(data.success==true){
						var contentMsg = {
								id:"addStBprpBID",
								title: $.i18n.map['addDevice']+"-"+$.i18n.map['affiliatedGroup']+"【"+name+"】",   
								url:"stStbprpB/addPage.do",
								width:width,
								paraData:{addvcdId:id},
								requestMethod: 'ajax',
								tbar: [{
									text: $.i18n.map['determine'],
									clsName: "homebg popup-confirm",
									handler: function (thisPop) {
										addAndEditStBprpB(thisPop,"stStbprpB/logAddStbprpB.do");
									}
								}]
						};
						$.Popup.create(contentMsg);
					}else{
						$.Popup.create({ title: $.i18n.map['prompt'], content: ""});
						return false;
					}
				});
	}else{
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['subAddPrompt']});
		return false;
	}
}
/**
 * 编辑设备
 */
function editStBprpB(stcd){
	var contentMsg = {
			id:"editStBprpBID",//为了调用共同的方法
			title: $.i18n.map['modifyQquipment'],   
			url:"stStbprpB/editPage.do",
			width:"1270",
			paraData:{id:stcd},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditStBprpB(thisPop,"stStbprpB/logEditStbprpB.do");
				}
			}]
	};
	$.Popup.create(contentMsg);
}
/**
 * 保存/编辑公用方法
 */
function addAndEditStBprpB(thisPop,url){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var addvcd = sNodes[0].id;
	var reportFactor = $("#factorList").val();
	if(reportFactor<=0){
		$.Popup.create({ title: $.i18n.map['prompt'], width:380, content: "报送类型为空，请先到系统管理下配置企业的通道！"});
		return false;
	}
	var param = $("#stStbprpBForm").serialize();
	if(validateStBprpBForm()){
		//报送类型
/*		var sendType = $("#sendType td.table-right input[type='checkbox']:checked");
		if(sendType.length==0){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseSelectType']});
			return false;
		}*/
		
		var chk_value="";

		$('input[type="checkbox"]').each(function () {
			if (this.checked) {
				chk_value += this.value + ",";
			}
		});
		if(chk_value==""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseSelectType']});
			return false;
		}
		
		

		//通道数
		var sendChannel = $("#sendChannel td.table-right input[type='checkbox']:checked");
		var radio = $('input[name="cameratype"]:checked ').val(); 
		if(radio==2 && sendChannel.length==0){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseSelectChannel']});
			return false;
		}

		var lgtd = $("#devicelng").val();
		var lttd = $("#devicelat").val();
		if(lgtd == "" && lttd == ""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['installLocation']});
			return false;
		}
		var sttp = $("#sttp").val();
		var wrz = $("#wrz").val();
		var grz = $("#grz").val();
		var dsflz = $("#dsflz").val();
		var normz = $("#normz").val();
		var ddz = $("#ddz").val();
		var damel = $("#damel").val();
		if(sttp=="0"){
			if(wrz==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseWarnLevel']});
				return false;
			}
			if(grz==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseGuaranteedLevel']});
				return false;
			}

		}
		if(sttp=="1"){
			if(dsflz==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseDesignLevel']});
				return false;
			}
			if(normz==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseHighLevel']});
				return false;
			}
			if(ddz==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseDeadLevel']});
				return false;
			}
			if(damel==""){
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseCrestLevel']});
				return false;
			}
		}
		var cameratype = $('#stStbprpBForm input[name="cameratype"]:checked').val(); 
		var dvraddr = $("#dvraddr").val();
		var dvrcode = $("#dvrcode").val();
		if(cameratype==2 && (dvraddr=="" || dvrcode=="")){//dvr摄像头
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['signalPrompt']});
			return false;
		}
		param = param+"&lgtd="+lgtd+"&lttd="+lttd;
		fnAjaxRequest(
				url,
				param,
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
					if(data.success){
						var params = {
								id:addvcd
						};
						loadStbprpList(params, true);
					}else{
						$.Popup.close(thisPop);
					}
				}
		);
	}
}

function delStBprpB(stcd){
	var stId = [];
	if (stcd) {
		stId.push(stcd);
	}else{
		var selectRow = $("#stBprpBDiv tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			stId.push(selectRow[i].value);
		}
	}
	if (stId.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseSelectDevice']});
		return false;
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['deleteDevicePrompt'],
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					var confirmMsg2 = {
							title: $.i18n.map['prompt'],
							width:"500",
							content: $.i18n.map['deleteDevicePrompt2'],
							tbar: [{
								text: $.i18n.map['determine'],
								clsName: "homebg popup-confirm",
								handler: function (thisPop2) {
									$.Popup.close(thisPop);
									$.Popup.close(thisPop2);
									fnAjaxRequest(
											"stStbprpB/logDelStbprpB.do",
											{ids:stId.toString()},
											"json",
											"POST",
											function(data){
												fnDSuccess(data,thisPop2);
												if(data.success){
													var params = {
															id:id
													};
													loadStbprpList(params, true);
												}else{
													$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
												}
											}
									);
								}
							}, {
								text: $.i18n.map['cancel'],
								clsName: "homebg popup-cancel",
								handler: function (thisObj2) {
									$.Popup.close(thisObj2);
								}
							}]
					};
					$.Popup.create(confirmMsg2);
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
 * 移动设备
 */
function moveStBprpB(){
	var deId = [];
	var selectRow = $("#stBprpBDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		deId.push(selectRow[i].value);
	}
	if (deId.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseMoveDevice']});
		return false;
	}else{
		var contentMsg = {
				title: $.i18n.map['mobileDeviceArea'],   
				width:"350",
				url:"stStbprpB/movePage.do",
				paraData:{dids: deId.toString()},
				requestMethod: 'ajax',
				tbar: [{
					text: $.i18n.map['determine'],
					clsName: "homebg popup-confirm",
					handler: function (thisPop) {
						var confirmMsg = {
								title: $.i18n.map['prompt'],
								content: $.i18n.map['mobileDevicePromp'],
								width:"500",
								tbar: [{
									text: $.i18n.map['determine'],
									clsName: "homebg popup-confirm",
									handler: function (thisPop2) {
										$.Popup.close(thisPop2);
										moveAddvcdDSubmit(thisPop);
									}
								}, {
									text: $.i18n.map['cancle'],
									clsName: "homebg popup-cancel",
									handler: function (thisObj2) {
										$.Popup.close(thisObj2);
									}
								}]
						};
						$.Popup.create(confirmMsg);
					}
				}]
		};
		$.Popup.create(contentMsg);
	}
}
/**
 * 保存移动设备操作
 */
function moveAddvcdDSubmit(thisPop){
	var dids  = $("#dids").val();
	var treeObj = $.fn.zTree.getZTreeObj("ztreeMove");
	var sNodes = treeObj.getSelectedNodes();
	var rt = sNodes[0];
	var id = sNodes[0].id;
	if(rt.children==null){
		//进行分组移动操作
		fnAjaxRequest(
				"stStbprpB/logMoveStbprpB.do",
				{ids:dids,addvcdId:id},
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
					if(data.success){
						var treeAreaObj = $.fn.zTree.getZTreeObj("ztree");
						var sAreaNodes = treeAreaObj.getSelectedNodes();
						var addvcd = sAreaNodes[0].id;
						var params = {
								id:addvcd
						};
						loadStbprpList(params, true);
					}
				});
	}else{
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['onlyMoveSub']});
		return false;
	}
}
/**
 * 配置
 */
function configuration(stcd,dsfl,commode){
	if(commode==1){
		$.Popup.create({ title: $.i18n.map['prompt'], content: "北斗通信不提供远程配置的功能!"});
		return false;
	}else{
		if(dsfl==0){
			$.Popup.create({ title: $.i18n.map['prompt'], content:"设备离线，无法进行操作!"});
			return false;
		}else{
			var contentMsg = {
					title: $.i18n.map['remoteConfiguration']+"【"+stcd+"】",   
					width:"1100",
					url:"stStbprpB/configInfo.do",
					paraData:{stcd:stcd},
					requestMethod: 'ajax'
					/*requestMethod: 'ajax',
					tbar: [{
						text: "确定",
						clsName: "homebg popup-confirm",
						handler: function (thisPop) {
							//addAndEditStBprpB(thisPop,"stStbprpB/logEditStbprpB.do");
						}
					}, {
						text: "取消",
						clsName: "homebg popup-cancel",
						handler: function (thisObj) {
							$.Popup.close(thisObj);
						}
					}]*/
			};
			$.Popup.create(contentMsg);
		}
	}
}

/**
 * 导入设备页面
 */
function importStBprpB(){
	var contentMsg = {
			id:"staddvcdSelectPage",
			title: $.i18n.map['importDevice'],  
			width:"350",
			url:"stStbprpB/movePage.do",
			paraData:{},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					importStbprpBData();
				}
			}]
	};
	$.Popup.create(contentMsg);
}
/**
 * 导入设备操作
 */
function importStbprpBData(){
	var treeObj = $.fn.zTree.getZTreeObj("ztreeMove");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;
	var rt = sNodes[0];
	if(rt.children==null){
		var contentMsg = {
				id:"pathImportStbprpB",
				title: $.i18n.map['bulkImportDevice'],   
				width:"500",
				closeButton:false,
				url:"stStbprpB/importStbprpBPage.do",
				paraData:{id:id},
				requestMethod: 'ajax'
		};
		$.Popup.create(contentMsg);
	}else{
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['subImportDevice']});
		return false;
	}
}

/**
 * 文件上传
 */
function ajaxFileUpload(id) {
	var file = $("#file").val();
	if(file==""){
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['fileImport']});
		return false;
	}else{
		showMark();
		$(".mask").css("z-index","205");
		$("#submitForm").ajaxSubmit({  
			type: 'post',  
			dataType:"json",
			url: "stStbprpB/logStbprpBFileUpload.do" ,  
			data : {addvcdId:id},
			success: function(data){ 
				$(".mask").css("z-index","99");
				hideMarkLoading();
				//采用ajaxUpload和@ResponseBody返回会乱码，所以采用返回数字的形式
				$("#upload").css("display","none");
				$("#view").css("display","block");
				if(data.msg == "100010"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['templateFormatError']+"</font>");
				}
				if(data.msg == "100020"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['withoutContent']+"</font>");
				}
				if(data.msg == "100030"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['100030Prompt1']+"</br>"+$.i18n.map['100030Prompt2']+"</br>"+$.i18n.map['100030Prompt3']+"</font>");
				}
				if(data.msg == "100040"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['importSucces']+"</font>");
				}
				if(data.msg == "100050"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['rowExistBegin']+""+data.obj+""+$.i18n.map['rowExistEnd']+"</font>");
				}
				if(data.msg == "100070"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['uploadOnly5Mb']+"</font>");
				}
				if(data.msg == "100080"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['uploadFormatError']+"</font>");
				}
				if(data.msg == "100090"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['exceededMaxDevice']+""+data.obj+"</font>");
				}
				if(data.msg == "110000"){
					$("#viewContent").html("<font style='font-size:12pt'>"+$.i18n.map['unknownError']+"</font>");
				}
				if(data.msg == "120000"){
					$("#viewContent").html("<font style='font-size:12pt'>通知dvr添加设备异常，请联系管理员</font>");
				}
			},  
			error: function(XmlHttpRequest, textStatus, errorThrown){  
				$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['uploadError']});
				$(".mask").css("z-index","99");
				hideMark();
			}  
		});  

		return false;
	}
	return false;
}

/**
 * 关闭上传设备页面
 */
function closeImportPage(){
	$("#pathImportStbprpB").remove();
}

/**
 * 返回上传
 */
function returnUpload(){
	$("#upload").css("display","block");
	$("#view").css("display","none");
}
/**
 * 关闭上传界面
 */
function closeUpload(){
	$("#pathImportStbprpB").remove();
	$("#staddvcdSelectPage").remove();
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;
	var params = {
			id:id
	};
	loadStbprpList(params);
}

/**
 * 选择报送类型为水质
 */
function reportWaterQuality(){
	if($("#wq").attr("checked")){
		$("#waterQuality").css("display","block");
		$("#viewFactor").css("height","30px");
		$("[name = phfl]:checkbox").attr("checked", true);
		$("[name = tufl]:checkbox").attr("checked", true);
		$("[name = dofl]:checkbox").attr("checked", true);
		$("[name = sufl]:checkbox").attr("checked", true);
		$("[name = tefl]:checkbox").attr("checked", true);
	}else{
		$("#waterQuality").css("display","none");
		$("#viewFactor").css("height","0px");
		$("[name = phfl]:checkbox").attr("checked", false);
		$("[name = tufl]:checkbox").attr("checked", false);
		$("[name = dofl]:checkbox").attr("checked", false);
		$("[name = sufl]:checkbox").attr("checked", false);
		$("[name = tefl]:checkbox").attr("checked", false);
	}
}


/**
 * 是否显示摄像头
 */
function viewCamera(val,methodName){
	if(val=="1"){
		/*	if(methodName=="add"){
    		$("#addStBprpBID").children(".popup-box").css("width", "800");
    	}else{
    		$("#stStbprpBTb").css("width","750");
    		$("#editStBprpBID").children(".popup-box").css("width", "1270");
    	}*/
		$("td.cameraParm").removeClass("isCamera");
	}else{
		/*	if(methodName=="add"){
		   $("#addStBprpBID").children(".popup-box").css("width", "800");
		}else{
			$("#stStbprpBTb").css("width","450");
			$("#editStBprpBID").children(".popup-box").css("width", "930");
		}*/
		$("td.cameraParm").addClass("isCamera");
		if(!$("td.dvrParm").hasClass("isdvrCamera")){
			$("#cameratype1").attr("checked","checked");
			$("td.dvrParm").addClass("isdvrCamera");
		}
	}
} 

/**
 * 动态显示dvr的配置参数
 */
function viewDvrParams(val){
	if(val=="2"){
		$("td.dvrParm").removeClass("isdvrCamera");
	}else{
		$("td.dvrParm").addClass("isdvrCamera");
	}
}

/**
 * 根据站类的选择，动态显示的对应的文本框
 */
function sttpCha(val){
	if(val=="0" ||  val=="3"){
		$("td.hdParm").removeClass("ishd");
		$("td.skParm").addClass("issk");
	}else if(val=="1"){
		$("td.skParm").removeClass("issk");
		$("td.hdParm").addClass("ishd");
	}else{
		$("td.hdParm").addClass("ishd");
		$("td.skParm").addClass("issk");
	}
}


/**
 * 设备新增时定位
 */
function getLocation(addvcdDId){
	if(addvcdDId == undefined){
		addvcdDId == "";
	}
	var contentMsg = {
			title: $.i18n.map['devicePosition'],   
			url:"stStbprpB/mapDialog.do",
			width:"900",
			paraData:{addvcdDId: addvcdDId},
			requestMethod: 'ajax'
	};
	var videoObj = $.Popup.create(contentMsg);
	videoObj.popObj.find("div.popup-content").css({"padding":"0"});
	videoObj.popObj.find("div.popup-control").css({"padding-bottom":"0"});

}

/**
 * 产品型号
 */
function getModel(enterpriseid){
	var contentMsg = {
			title: $.i18n.map['deviceModel'],   
			url:"stStbprpB/modelList.do",
			width:"300",
			height:"300",
			paraData:{enterpriseid: enterpriseid},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					var treeObj = $.fn.zTree.getZTreeObj("zModelTree");
					var selectdNode = treeObj.getSelectedNodes()[0];
					if(selectdNode!=null){
						if(selectdNode.isParent){
							$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['deviceModel']});
							return false;
						}else{
							var name = selectdNode.name;
							$("#modelName").val(name);
							$("#model").val(selectdNode.id);
							$("#modelName").focusout();
							$.Popup.close(thisPop);
						}
					
					}else{
						$.Popup.close(thisPop);
					}
				}
			}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 导出设备页面
 */
function exportStBprpB(){
	var contentMsg = {
			title: $.i18n.map['exportDevice'],  
			width:"350",
			url:"stStbprpB/movePage.do",
			paraData:{},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					exportStbprpBData();
				}
			}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 导出设备操作
 */
function  exportStbprpBData(){
	var treeObj = $.fn.zTree.getZTreeObj("ztreeMove");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;
	var rt = sNodes[0];
	window.location.href="stStbprpB/stbprpBDownload.do?addvcdId="+id;
}


/**
 * 属性校验
 * @returns
 */
function validateStBprpBForm(){ 
	return $("#stStbprpBForm").validate({
		rules:{
			stcd : {
				required: true,
				isTenNum: true,
				stcdExist: true
			},
			stnm : {
				required: true,
				specialCharValidate: true,
				maxlength:10
			},
			modelName:{
				required: true
			},
			pwd :{
				required: true,
				digits:true,
				range: [1, 65535]
			},

			center : {
				required: true,
				number: true,
				digits:true,
				range: [1, 255] 
			},
			tel : {
				required: true,
				isMobile: true,
				deviceTelExist:true
			},
			stlc : {
				required: true
			},
			wrz : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			grz : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			normz : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			dsflz : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			ddz : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			damel : {
				number:true,
				range: [0.01, 9999.999],
				decimals: true
			},
			workerphone:{
				isMobile: true,
			},
			comments:{
				specialCharValidate: true
			},
			workername:{
				maxlength:20
			}
		},
		messages:{
			stcd : {
				required: $.i18n.map['10Digit'],
				isTenNum: $.i18n.map['noMore10Words'],
				stcdExist:$.i18n.map['exist']
			},
			stnm : {
				required: $.i18n.map['required'],
				specialCharValidate: $.i18n.map['noSpecialChar'],
				maxlength:$.i18n.map['noMore10Words']
			},
			modelName:{
				required: $.i18n.map['required']
			},
			pwd : {
				required: $.i18n.map['required'],
				digits:"1~65535"+$.i18n.map['digits'],
				range:"1~65535"+$.i18n.map['digits']
			},
			center : {
				required: $.i18n.map['required'],
				number: "1~255"+$.i18n.map['digits'],
				digits:"1~255"+$.i18n.map['digits'],
				range:"1~255"+$.i18n.map['digits']
			},
			tel : {
				required: $.i18n.map['required'],
				isMobile: $.i18n.map['isMobile'],
				deviceTelExist:$.i18n.map['exist']
			},	
			stlc : {
				required: $.i18n.map['required']
			},
			wrz  : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			grz  : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			normz  : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			dsflz  : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			ddz : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			damel : {
				number:$.i18n.map['number'],
				range: "0.01~9999.999"
			},
			workerphone:{
				isMobile: $.i18n.map['phoneNumber']
			},
			comments:{
				specialCharValidate: $.i18n.map['noSpecialChar']
			},
			workername:{
				maxlength:"长度不能超过20位"
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
 * ajax 验证测站编码
 */
$.validator.addMethod("stcdExist",function(value,element){
	var stcd = value;
	var result= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("stStbprpB/checkStcdExist.do",{
		stcd : stcd
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
},$.i18n.map['enterCodeExists']);

/***
 * ajax 验证通信识别码
 */
$.validator.addMethod("deviceTelExist",function(value,element){
	var deviceTel = value;
	var stcd = $("#stcd").val();
	var data3= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("stStbprpB/checkDeviceTelExist.do",{
		deviceTel : deviceTel,
		stcd : stcd
	},function(data){
		if(data.success){
			data3 = false;
		}else{
			data3 = true;
		} 
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
	//data3 false 已经存在了
	return data3;
},$.i18n.map['comIdentNumExists']);

/**
 * 3小数位限制
 */
$.validator.addMethod("decimals", function(value, element) {         
	return this.optional(element) || /^\d+(\.\d{1,3})?$/.test(value);         
}, $.i18n.map['digitsThreeBits']); 
