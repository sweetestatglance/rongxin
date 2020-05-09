$(function(){
	//设置ajax全局不缓存
	$.ajaxSetup({
		cache: false
	});
	loadProperties();
	//点击行选中第一个checkbox
	$(document).on('click', '.list-table tbody tr', function(event) {
		//取点击的对象
        var obj = event ? event.target : event.srcElement;
		var tag = obj.tagName;
		var checkbox = $(this).find("input[type='checkbox']:first");
		if(checkbox != undefined){
			var checked = $(checkbox).attr("checked");
			if(tag == 'TD' || tag == 'TR') {
				checkbox.click();
	        }
		}
	});
	
	window.console = window.console || (function(){ 
		var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile 
		= c.clear = c.exception = c.trace = c.assert = function(){}; 
		return c; 
	})();
	
});

function loadProperties() {
   jQuery.i18n.properties({//加载资浏览器语言对应的资源文件
        name : 'message_info', //资源文件名称
        path : 'i18n/', //资源文件路径
        mode : 'map', //用Map的方式使用资源文件中的值
        language : 'zh_CN',
        callback : function() {//加载成功后设置显示内容
        }
    });
}


/**
 * AJAX请求  
 * @param {} sUrl
 * @param {} aoParams
 * @param {} dataType
 * @param {} type
 * @param {} fnCallback
 */
function fnAjaxRequest(sUrl, aoParams, dataType, type, fnCallback) {
   return $.ajax({
        url: sUrl,
        data: aoParams,
        success: function (json) {
            if (json != null) {
                if (json.sError) {
                    console.log(json.sError);
                }
                fnCallback(json);
            }
        },
        dataType: dataType,
        cache: false,
        type: type,
        error: function (xhr, error, thrown) {
            if (error == "parsererror") {
                console.log("AjaxRequest warning: JSON data from " +
                    "server could not be parsed. This is caused by a JSON formatting error.");
            } else {
                console.log("request error");
            }
        }
    });
}

/**
 * 响应菜单，刷新内容
 * @param {} target 请求url
 * @param {} id 返回的html填充的div Id
 * @param {} params 请求所带的参数
 */
function showContent(target,id,params){
		if(params == undefined){
			params={};
		}
		showMark();
		$.ajax({
			url : target,
			data : params,
			success : function(data){
				$("#"+id).html(data);
				hideMark();
			},
			error: function (xhr, error, thrown) {
				if (error == "parsererror") {
					console.log("AjaxRequest warning: JSON data from " +
					"server could not be parsed. This is caused by a JSON formatting error.");
				} else {
					console.log("request error");
				}
				hideMark();
			}
		});
}

/**
 * 操作成功
 * @param {} data
 */
function fnDSuccess(data,thisPop) {
    if (data.success) {
    	$.Popup.close(thisPop);
    }
    if (data.msg) {
    	$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
    }
}

/**
 * 显示laoding
 */
function showMark(){
	 if (!$(".mask").hasClass("loading")) {
         $(".mask").addClass("loading");
     }
	 if ($(".mask").is(":hidden")) {
         $(".mask").show();
     }
}

/**
 * 隐藏laoding
 */
function hideMark(){
	if ($(".mask").hasClass("loading")) {
        $(".mask").removeClass("loading");
    }
	if ($(".mask").is(":visible")) {
    	$(".mask").hide();
    }
}

/**
 * 隐藏laoding(不关闭阴影遮罩)
 */
function hideMarkLoading(){
	if ($(".mask").hasClass("loading")) {
		$(".mask").removeClass("loading");
	}
}

/**
 * 表单验证不通过，设置错误样式及信息
 * @param errorMap
 * @param errorList
 */
function showErrors(errorMap, errorList){
	if (errorList && errorList.length > 0) {
		//console.log(errorList);
		$.each(errorList, function(index, obj) {
			var item = $(obj.element);
			// 给输入框添加出错样式
			item.addClass('input-validation-error');
			item.next('span').remove();//移除span
			item.after("<span class='field-validation-error' style='font-size:12px'>"+obj.message+"</span>");
		});
	} else {
		var item = $(this.currentElements);
		item.removeClass('input-validation-error');
		item.removeAttr("title");
		item.next("span.field-validation-error").remove();
	}
}

/**
 * 获取当前选中节点的所有子节点
 * @param treeId 树id,默认为ztree
 */
function getNodeIdsByTreeId(treeId){
	var defaultId = "ztree";
	if(treeId != undefined && treeId != "") defaultId = treeId;
	var treeObj = $.fn.zTree.getZTreeObj(defaultId);
	var selectdNode = treeObj.getSelectedNodes()[0];
	var ids = [];
	
	ids = getChildren(ids,selectdNode);
	
	return ids;
}


/**
 * 获取当前选中节点的所有最子集父节点
 * @param treeId
 * @returns
 */
function getParentNodeIdsByTreeId(treeId){
	var defaultId = "ztree";
	if(treeId != undefined && treeId != "") defaultId = treeId;
	var treeObj = $.fn.zTree.getZTreeObj(defaultId);
	var selectdNode = treeObj.getSelectedNodes()[0];
	
	var ids = [];
	
	ids = getChildrenParent(treeObj,ids,selectdNode);
	return ids;
}
function getChildrenParent(treeObj,ids,treeNode){
	if(treeNode!=undefined){
		var cNodes = treeObj.getNodesByParam("isDevice",false,treeNode);
		if(cNodes==undefined || cNodes.length==0){
			ids.push(treeNode.id);
		}else{
			for(var obj in cNodes){
				getChildrenParent(treeObj,ids,cNodes[obj]);
			}
		}
	}
	return ids;
}

/**
 * 递归获取树节点下的所有叶子节点
 * @param ids 存放叶子节点id数组
 * @param treeNode 节点
 * @returns
 */
function getChildren(ids,treeNode){
	if (treeNode.isParent){
		for(var obj in treeNode.children){
			getChildren(ids,treeNode.children[obj]);
		}
	}else{
		if(treeNode.isDevice==true) ids.push(treeNode.id);
	}
	return ids;
}

/**
 * 判断str是否不为空
 * @returns {Boolean}
 */
function isNotEmpty(str){
	//非string类型则转为string类型
	if(str != undefined && typeof str != "string") str=str.toString();
	var flag = false;
	if(str!="" && str!=undefined){
		flag = true;
	}
	return flag;
}
/**
 * 判断浏览器是否为ie(ie11以下)
 * @returns {Boolean}
 */
function isIE() { 
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}
/**
 * 报表弹出框
 * @param {} settings
 */
function fnReportDialog(settings) {
    
    //$(".mask").show();
	
    var name = settings.name;
    var dialog = document.createElement("div");
    dialog.id = "reprotDialog";
    if(name!=undefined){
    	dialog.setAttribute("name",name);
    }
    $(dialog).append("<div class='markDialog'></div>");
    
    var pop = document.createElement("div");
    $(pop).addClass("report-pop");
    $(pop).addClass("control-loading");
    
    var title = settings.title;
    $(pop).append("<h2 class='report-pop-title'><span>" + (title==undefined?"":title) + "</span></h2>");
    $(pop).append("<a id='btnReportClose'></a>");

    var content = document.createElement("div");
    content.className = "";
    content.id = "reportDialog_content";
    if (settings.url) {
        fnAjaxRequest(settings.url, settings.param, "html", "GET", function (data) {
        	$(pop).removeClass("control-loading");
            $(content).html(data);
        });
    }
    $(pop).append(content);
    $(dialog).append(pop);
    $("body").prepend(dialog);

    $("#btnReportClose").click(function () {
        $(dialog).remove();
        //$(".markDialog").hide();
    });
}


/**
 * 详细报表展示
 * 
 * @param deviceNo
 *            设备编码
 */
function showReportDialog(deviceNo,initIndex){
	
	fnReportDialog({
		name:"reportForm_Dialog",
		title: $.i18n.map['detailInfo'] +"【"+deviceNo+"】",
		url:"reportDetail/index.do",
		param:{
			deviceNo:deviceNo==undefined?"":deviceNo,
			initIndex:initIndex==undefined?"":initIndex
		}
	});
}


/**
 * 显示设备
 */
function viewDevicePage(stcd){
	var right = $(".device_right_list_div").css("right");
	if($(".device_right_list_div").css("right")=="-350px"){
		$(".device_right_list_div").animate({right:"0px"});
	}else{
		$(".device_right_list_div").animate({right:"-350px"});
	}
	$.post("reportDetail/stbprpbListPage.do",{stcd:stcd},function(data){
		$(".device_right .device_right_content").html(data);
	});
}


/**
 * 视频弹出框
 * @param {} settings
 */
function fnVideoDialog(settings) {
    var name = settings.name;
    var dialog = document.createElement("div");
    dialog.id = "videoToIEDiv";
    if(name!=undefined){
    	dialog.setAttribute("name",name);
    }
    $(dialog).append("<div class='markDialog'></div>");
    
    var pop = document.createElement("div");
    pop.id="videoId";
    $(pop).addClass("report-pop-video");
    var title = settings.title;
    $(pop).append("<h2 class='report-pop-title'><span>" + (title==undefined?"":title) + "</span></h2>");
    $(pop).append("<a id='btnVideoMagnified' style='cursor:pointer'></a><a id='btnVideoletting' style='cursor:pointer'></a><a id='btnVideoClose' style='cursor:pointer'></a>");

    var content = document.createElement("div");
    content.className = "";
    content.id = "reportDialog_content";
    if (settings.url) {
        fnAjaxRequest(settings.url, settings.param, "html", "GET", function (data) {
            $(content).html(data);
        });
    }
    $(pop).append(content);
    $(dialog).append(pop);
    $("body").prepend(dialog);
    
    $("#btnVideoClose").click(function () {
    	 if(settings.isVideo && (navigator.userAgent.indexOf('IE') >= 0)){
    		   SetParam();//针对视频
    	    }
        $(dialog).remove();
        //$(".markDialog").hide();
    });
    //放大
    $("#btnVideoMagnified").click(function () {
       $("#videoId").css("top","0px");
       $("#videoId").css("bottom","0px");
       $("#videoId").css("left","1px");
       $("#videoId").css("right","1px");
   });
   //缩小
    $("#btnVideoletting").click(function () {
    	$("#videoId").css("top","20px");
        $("#videoId").css("bottom","40px");
        $("#videoId").css("left","150px");
        $("#videoId").css("right","150px");
   });
    
}



/**
 * 开始时间验证
 * @param dp
 * @returns
 */
function vilidBeginTime(dp){
	var oldM = dp.cal.oldValue.split('-')[1];
	var newM = dp.cal.getNewDateStr().split('-')[1];
	if(oldM != newM){
		$('#query_endTime').val('');
		query_endTime.focus();
	}else{
		var bT = $dp.$("query_beginTime").value;
		var eT = $dp.$("query_endTime").value;
		if(bT>eT){
			$('#query_endTime').val('');
			query_endTime.focus();
		}
	}
}
/**
 * 获取当前月最大日期
 */
function limitMonthDate() {  
    var DateString;  
    var beginTime = $dp.$("query_beginTime").value;  
    var date = new Date();
    var nowtimes = date.getTime();
    if (beginTime != "" && beginTime != null) {  
        var beginDate = new Date(beginTime);  
        
        beginDate.setDate(new Date(beginDate.getFullYear(), beginDate.getMonth() + 1, 0).getDate()); //获取此月份的天数  
        var starttimes = beginDate.getTime();
        var limitDate=beginDate;
        if(starttimes>nowtimes) limitDate=date;
        
        DateString = limitDate.getFullYear() + '-' + (limitDate.getMonth()+1 ) + '-' + limitDate.getDate() + " 23:59:59";
    }  
    return DateString;  
}