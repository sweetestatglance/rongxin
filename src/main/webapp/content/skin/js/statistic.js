var caTreeObj;
var timeDataTicket;
$(function() {
    //enableCloseLeft();
	//getGroupTree();
});

//获取行政树
function getGroupTree(flag) {
//	$(".tree").height($(".left").outerHeight() - ($(".title").outerHeight() * 2)- 10);
	if(flag=="history"){
		caTreeObj = ztreeHistoryFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true",historyList);
	}else if(flag=="generalReport"){
		caTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true",generalReportList);
	}else{
		caTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true",almanacReportList);
	}
}
function consList(){
	dataList();
	autoDataRefresh();
}
/*******************************数据报表脚本 begin*****************************/
/**
 * 数据报表
 */
function generalReportList() {
	searchGeneralReportList();
}

/**
 * 查询
 */
function searchGeneralReportList(params) {
	if (params == undefined) {
		params = {};
	}
	var searchType = $("#searchType").val();
	if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
//	var aryIds = getNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	params['isDevice'] = selectdNode.isDevice;
	var aryIds = getParentNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;

	showMark();
	$.get("generalReport/generalList.do", params, function(data) {
		hideMark();
		$("#rightContain").html(data);
		// loadFactorList();
	});
}

function changePage(n){
	var params = {
			pageNo:n
	}
	searchGeneralReportList(params);
}

function showArea(obj){
	var m = $("#allArea #" + $(obj).find("option:selected").attr("area")).show();
	var s = m.siblings("div").hide();
	
}



var popOpt;
function showDetail(stcd){
	var contentMsg = {
			title: "查看详情",   
			url:"generalReport/detailList.do",
			width:"1200",
			height:"450",
			paraData:{
				stcd: stcd,
				query_beginTime:$("#hidden_query_beginTime").val(),
				query_endTime:$("#hidden_query_endTime").val()
				},
			requestMethod: 'ajax'
	};
	popOpt = $.Popup.create(contentMsg);
}

/**
 * 导出报表
 */
function exportReportForm(obj){
	$(obj).attr("disabled","disabled");
	$(obj).removeAttr("onclick");
	
	var params = {};
	var searchType = $("#searchType").val();
	if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
//	var aryIds = getNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	params['isDevice'] = selectdNode.isDevice;
	var aryIds = getParentNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;
	
	$.post("generalReport/exportExcelData.do", params, function(data){
		if(data.success){
			window.location.href="generalReport/download.do?fileName=" +  encodeURI(encodeURI(data.fileName));
		}else{
			alert("数据导出失败!");
		}
		$(obj).attr("onclick","exportReportForm(this)");
		$(obj).removeAttr("disabled");
	},"json")
}
/*******************************数据报表脚本 end*****************************/

/**
 *  历史报表
 */
function historyList() {
	searchHistoryList();
}


/**
 * 历史报表列表
 * @param node
 */
function searchHistoryList(params){
	if (params == undefined) {
		params = {};
	}
	 var dstcd = $("#dstcd").val();
	 var beginTime = $("#query_beginTime").val();
	 var endTime = $("#query_endTime").val();
	 if(dstcd=='请输入测站编码'){
		dstcd = "";
	 }
	 var treeObj = $.fn.zTree.getZTreeObj("ztree");
	 var selectdNode = treeObj.getSelectedNodes()[0];
     var aryIds = getParentNodeIdsByTreeId("ztree");
     params['nodeIds'] = aryIds.toString();
     params['isDevice'] = selectdNode.isDevice;
     params['stcd'] = dstcd;
     params['beginTime'] = beginTime;
     params['endTime'] = endTime;
	 loadHistoryData(params);
}

function changeDetailPage(n){
	var params = {
			pageNo:n
	}
	searchHistoryList(params);
}
/**
 * 加载历史报表数据
 */
function loadHistoryData(params, flag){
	showMark();
	$.post("history/historyReportList.do",params,function(data){
		$("#rightContain").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}
function treeInitComplete(event, treeId, treeNode){
	
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var node = treeObj.getNodeByParam("isDevice", true);
	
	if(node != null){
		treeObj.selectNode(node);
		//默认点击第一个节点
	   treeObj.setting.callback.onClick(null, treeObj.setting.treeId, node);
//	   searchCurve();
	}else{
	   //获取节点
	   var nodes = treeObj.getNodes();
	   if (nodes.length>0) 
	   {
		   treeObj.selectNode(nodes[0]);
		   //默认点击第一个节点
		   treeObj.setting.callback.onClick(null, treeObj.setting.treeId, nodes[0]);
	   }
	}
}

Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    str = str.replace(/MM/, this.getMonth() > 8 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}

/**
 * 历史查询导出
 */
function historyExport(){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var stcd = $("#dstcd").val();
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	if (stcd == '请输入测站编码') {
		stcd = "";
	}
	var params = {
			nodeIds:aryIds.toString(),
			stcd_query:encodeURI(stcd),
			query_beginTime:$("#query_beginTime").val(),
			query_endTime:$("#query_endTime").val()
	};
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	params['isDevice'] = selectdNode.isDevice;
	$.post("history/export.do",params,function(data){
		window.location.href = "history/historyDownload.do?fileName=" +  encodeURI(encodeURI(data));
	});
}

/**
 * 数据概览重置
 */
function reset(){
	$("#dstcd").val("请输入测站编码");
	$("#dstnm").val("请输入测站名称");
	dataList();
	
}
/**
 * 历史报表重置
 */
function resetHistory(){
	$("#dstcd").val("请输入测站编码");
	var myDate = new Date();
	var time = myDate.toLocaleDateString().replace(/[年]/g,'-').replace(/[月]/g,'-').replace(/[日]/g,'');//可以获取当前日期
	var beginTime = time+" 00:00";
	var endTime = time+" 23:59";
	$("#query_beginTime").val(beginTime);
	$("#query_endTime").val(endTime);
	historyList();
}

function config(){
	var contentMsg = {
			id : "columnViewPop",
			title: "选择要显示(隐藏)的要素列",   
			url:"sysUserFactor/optionPage.do",
			width:"800",
			requestMethod: 'ajax'
	};
	popOpt = $.Popup.create(contentMsg);
}

function columnViewSubmit(){
	
	//获取所有右列数据
    var rightAry = new Array(); //定义数组
    $("#right_select option").each(function(){ //遍历全部option
        rightAry.push($(this).val()); //添加到数组中
    });
    
    if(rightAry.length==0){
    	$.Popup.create({ title : "提示", content : "至少要展示一个要素!!" });
    	return false;
    }
    
	//获取左列数据
    var leftAry = new Array(); 
    $("#left_select option").each(function(){ //遍历全部option
        leftAry.push($(this).val()); //添加到数组中
    });
    
    $.post("sysUserFactor/columnViewSave.do",{rightAry:rightAry.toString(),leftAry:leftAry.toString()},function(data){
    	
    	if(data.success){
    		$("#columnViewPop").remove();
        	
        	var factorMap = data.attributes.factorMap;
        	var factorUnitMap = data.attributes.factorUnitMap;
        	
        	var html = "";
        	for ( var key in factorMap) {
        		var optHtm = "<option value='" + key + "' unit='" + factorUnitMap[key] + "'>" + factorMap[key] + "</option>";
        		
        		html += optHtm;
    		}
        	$("#curveContent #factorType").html(html);
        	
        	$(".btn-search").click();
    	}else{
    		$.Popup.create({ title : "提示", content : data.msg });
    	}
    	
    },"json");
    
}

/*--------------------------------------------年鉴报表  begin-----------------------------------------*/
/**
 * 数据报表
 */
function almanacReportList() {
	searchAlmanacReportList();
}

/**
 * 查询
 */
function searchAlmanacReportList(params) {

	if (params == undefined) {
		params = {};
	}
	var searchType = $("#searchType").val();
	if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
//	var aryIds = getNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	params['isDevice'] = selectdNode.isDevice;
	var aryIds = getParentNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;

	showMark();
	$.post("almanacReport/list.do", params, function(data) {
		hideMark();
		$("#rightContain").html(data);
		// loadFactorList();
	});
}

function changePage1(n){
	var params = {
			pageNo:n
	}
	searchAlmanacReportList(params);
}

function showArea1(obj){
	var m = $("#allArea #" + $(obj).find("option:selected").attr("area")).show();
	var s = m.siblings("div").hide();
}

/*var popOpt1;
function showDetail1(stcd){
	var contentMsg = {
			title: "查看详情",   
			url:"almanacReport/detailList.do",
			width:"1200",
			height:"450",
			paraData:{
				stcd: stcd,
				query_beginTime:$("#hidden_query_beginTime").val(),
				query_endTime:$("#hidden_query_endTime").val()
				},
			requestMethod: 'ajax'
	};
	popOpt1 = $.Popup.create(contentMsg);
}*/

/**
 * 导出报表
 */
function exportReportForm1(obj){
	$(obj).attr("disabled","disabled");
	$(obj).removeAttr("onclick");
	
	var params = {};
	var searchType = $("#searchType").val();
	if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
//	var aryIds = getNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	params['isDevice'] = selectdNode.isDevice;
	var aryIds = getParentNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;
	
	$.post("almanacReport/exportExcelData.do", params, function(data){
		if(data.success){
			window.location.href="almanacReport/download.do?fileName=" +  encodeURI(encodeURI(data.fileName));
		}else{
			alert("数据导出失败!");
		}
		$(obj).attr("onclick","exportReportForm1(this)");
		$(obj).removeAttr("disabled");
	},"json")
}


/*--------------------------------------------年鉴报表  end-----------------------------------------*/