
var monitorTreeObj;
var mapObj;
var resultArray,alarmResultArray,noticeResultArray;
var pagerSelpagerNum = 0;
var pagerDvVNum = 0;

var showleft = true;
var showbottom = true;
var showfull = false;
var showtype = "list1";
$(function() {
	setInterval("LoadStFacInfo()",5*60*1000);//每5分钟加载一次地图要素信息
	
	getGroupTree();
	
	$(document).on('click', '.shrin', function() {
		$this = $(this);
		if($this.hasClass("shrin_transform")){
			$(".tab_bottom").animate({height:"210px"},function(){$this.removeClass("shrin_transform");});
			$this.animate({bottom: "188px"});
			$(".right_map").animate({bottom:"210px"});
			$(".tree").animate({bottom:"210px"});
			showbottom = true;
			
		}else{
			$(".tab_bottom").animate({height:"35px"},function(){$this.addClass("shrin_transform");});
			$this.animate({bottom: "12px"});
			$(".right_map").animate({bottom:"35px"});
			$(".tree").animate({bottom:"35px"});
			showbottom = false;
		}
	});
	
	$(".leftnav").click(function() {
		if ($(".left").css("display") == "none") {
			$(".leftnav").css("left", "247px");
			$(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
			$(".right_map").css("left", "247px");
			showleft = true;
		} else {
			$(".leftnav").css("left", "0px");
			$(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
			$(".right_map").css("left", "0px");
			showleft = false;
		}
		$(".left").toggle();
		$(".left-bottom").toggle();
	});
	
	// 地图模式切换
	$(document).on('click','.map_content .show-type li',function(){
		var m = $(this), s = m.siblings();
		m.addClass("sell");
		s.removeClass("sell");
		var type = m.attr("data");
		showtype = type;
		changeShowType(type);
	});
	
	showleft = true;
	showbottom = true;
	showfull = false;
	showtype = "list1";
});

/**
 * 
 */
function LoadStFacInfo(){
	console.log("定时..");
	var params = getParams();
	var selectNum = $("#selectNum option:selected").val();
	params.selectNum = selectNum;
	if(params != undefined){
		$.post("monitor/mapInfo.do",params,function(data){
			$("#map_index").html(data);
		});
	}
}

function changeLeftNav(){
	$(".leftnav").css("top",($(".left").height()-$(".tab_bottom").height())/2-$(".leftnav").height()/2+80)
}

function changeShowType(type){
	switch (type) {
		case "2d":
			$(".map-legend,#map").show();
			$("#deviceList").hide();
			$.Map.SetLayers(mapObj, type);
			break;
		case "satellite":
			$(".map-legend,#map").show();
			$("#deviceList").hide();
			$.Map.SetLayers(mapObj, type);
			break;
		case "list1":
			// 地图隐藏
			$(".map-legend,#map").hide();
			$("#deviceList").show();
			break;
	}
}

//获取行政树
function getGroupTree() {
//	$(".tree").height($(".left").outerHeight()- 10);
	var showOnLineDevice = $("#showOnLineDevice").is(':checked');
	var url = "stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=" + showOnLineDevice;
	monitorTreeObj = ztreeFun($("#ztree"),url,treeOnClick);
}

function treeOnClick() {
	loadMapInfo();
}

function changeOnLine() {
	getGroupTree();
}


/**
 * 获取查询条件
 * @returns {}
 */
function getParams(flag){
	var params = {};
	var sttp,query_stcdName,query_beginTime,query_beginTime_hour,query_endTime,query_endTime_hour,rainRangeStr;

	sttp = $("#sttp").val();
	var query_stcdName = $("#query_stcdName").val();
	if(query_stcdName==$.i18n.map['codeOrName']){
		query_stcdName = "";
	}
	
//	query_stcdName = $("#hidden_query_stcdName").val();
	query_beginTime = $("#query_beginTime").val();
	query_beginTime_hour = $("#query_beginTime_hour").val();
	query_endTime = $("#query_endTime").val();
	query_endTime_hour = $("#query_endTime_hour").val();
	
	var selectRow = $("#rainFallRange input[type='checkbox']:checked:visible");
	var rainRange = [];
	for (var i = 0; i < selectRow.length; i++) {
		rainRange.push(selectRow[i].value);
	}
	rainRangeStr = rainRange.toString();
	
	if(query_beginTime!="" || query_endTime!=""){
		if(query_beginTime==""){
			$.Popup.create({ title: $.i18n.map['prompt'], content: "请输入开始时间!"});
			return;
		}
		if(query_endTime==""){
			 $.Popup.create({ title: $.i18n.map['prompt'], content: "请输入结束时间!"});
			return;
		}
	}
	
	var showOnLineDevice = $("#showOnLineDevice").is(':checked');
	
	var selectdNode = monitorTreeObj.getSelectedNodes()[0];
	
	params['nodeIds'] = getParentNodeIdsByTreeId().toString();
	params['isDevice'] = selectdNode.isDevice;
	//params['groupIds'] = groupIds;
	params['rainRange'] = rainRangeStr;
	params['query_beginTime'] = query_beginTime;
	params['query_beginTime_hour'] = query_beginTime_hour;
	params['query_endTime'] = query_endTime;
	params['query_endTime_hour'] = query_endTime_hour;
	params['showOnLineDevice'] = showOnLineDevice;
	
	params['query_stcdName'] = query_stcdName;
	params['sttp'] = sttp;
	
	return params;
}

/**
 * 加载地图信息
 */
function loadMapInfo(){
	showMark();
//	var aryIds = getNodeIdsByTreeId("ztree");
//	var showOnLineDevice = $("#showOnLineDevice").is(':checked');
	var params = getParams();
	if(params != undefined){
		$.post("monitor/mapInfo.do",params,function(data){
			$("#map_index").html(data);
			hideMark();
			//loadFactorList();
		});
	}
	
//	showContent("monitor/mapInfo.do","",{nodeIds:aryIds.toString()});
	
	
	
//	$.getJSON("monitor/getMarkInfo.do",{nodeIds:aryIds.toString()},function(data){
//		hideMark();
//		// 地图初始化
//		var mapObj = $.Map.Init();
//		var attributes = data.attributes;
//		var devList = attributes.devList;
//		console.log(devList);
//		$.Map.SetMarker(mapObj,devList);
//		
////		setTimeout("changeListPage()",1500);
//	});
}


function loadFactorList(params){
	
	if(params==undefined) params = {};
	
	var selectNodeId = "";
	
	//获取选中节点信息
	var selectNode = monitorTreeObj.getSelectedNodes()[0];
	//获取最顶级节点
//	selectdNode = getTopNode(selectNode);
	var topNode = getTopNode(selectNode);
	if(topNode!=undefined) selectNodeId = topNode.id;
	params['addvcdId'] = selectNodeId;
	$.get("monitor/staticList.do",params,function(data){
		$(".map_right .map_right_content").html(data);
	});
}

/**
* 分页查询
* @param page
*/
function changePage(page){
	var params = {
			pageNo:page
	};
	loadFactorList(params);
}



/**
 * 获取最顶级节点
 * @param node
 * @returns
 */
function getTopNode(node){
	
	if(node.getParentNode()==null){
		return node;
	}else{
		node =node.getParentNode();
		return getTopNode(node)
	}
}

/**
 * 隐藏/显示 列表页面
 */
function changeListPage(){
	$(".map_button").removeAttr("onclick");
	var width = $(".map_right").width();
	
	//切换
	$(".map_right").animate({width:'toggle',opacity:'1',height: 'toggle'},function(){
		$(".map_button").attr("onclick","changeListPage()");
	});
	
}

/**
 * 显示module模块
 * @param stcd
 */
function showModule(stcd){
	//去除menu样式
	$("ul.button li a").removeClass("sell");
	
	var selectNodeId = "";
	//获取选中节点信息
	var selectNode = monitorTreeObj.getSelectedNodes()[0];
	//获取最顶级节点
//	selectdNode = getTopNode(selectNode);
	var topNode = getTopNode(selectNode);
	if(topNode!=undefined) selectNodeId = topNode.id;
	showContent("module/index.do","contain",{stcd:stcd,topNodeId:selectNodeId})
}

function sttpChange(sttp,flag){
	if(sttp==undefined) sttp = $("#sttp").val();
	if(sttp==2 || sttp==3){
		$(".rain-scope").css("display","inline-block");
		$(".rainSearch-open").css("line-height","77.5px");
	}else{
		$(".rainSearch-open").css("line-height","45px");
		$(".rain-scope").css("display","none");
	}
	
	if(flag == undefined || flag) loadMapInfo();
}

/**
 * 数组update
 * @param obj
 */
function updateAry(obj){
	if(obj != undefined && obj.stcd!=undefined){
		for (var i = 0; i < resultArray.length; i++) {
			if(resultArray[i].stcd==obj.stcd) {
				resultArray[i]=obj;
				//列表刷新
				pageselectCallback(pagerSelpagerNum);
				dvPageselectCallback(pagerDvVNum);
				break;
			}
		}
	}
}


/**
 * 点击确定查询后,根据查询结果显示节点
 * 
 */
function filterNode(coordinates){
	var nodes = monitorTreeObj.getNodesByParam("isDevice", true);
	monitorTreeObj.hideNodes(nodes);
	for (var i=0; i<coordinates.length; i++) {
		
		var stcd = resultArray[i].stcd;
		
		var node = monitorTreeObj.getNodesByParam("id", stcd, null);
		monitorTreeObj.showNodes(node);
	}
}

function mapFullScreen(){
	if($(".right_map").css("bottom")!="0px"){
		$(".right_map").css("z-index","15").animate({
			//z-index:"15",
			bottom:"0px",
			left:"0px",
			top:"0px"
		}).find("div.search_div a span").text($.i18n.map['exitFullScreen']);
		showfull = true;
		//full
		
	}else{
		//exit
		var left = "238px";
		var bottom = "210px";
		if($(".leftnav").css("left")=="0px") left="0px";
		if($(".tab_bottom").css("height")=="35px") bottom="35px";
		$(".right_map").animate({
			zIndex:"0",
			bottom:bottom,
			left:left,
			top:"80px"
		}).end().find("div.search_div a span").text($.i18n.map['fullScreen']);
		showfull = false;
	}
}

function changeRainSearch(){
		
	if($(".map-searchLeg").css("width")=="280px"){
		$(".rainSearch-close").hide();
		$(".map-searchLeg").animate({width:"20px"},function(){$(".rain-search-tele").show();});
	}else{
		$(".rain-search-tele").hide();
		$(".map-searchLeg").animate({width:"280px"},function(){$(".rainSearch-close").show();});
	}
}

function config(){
	var contentMsg = {
			id : "columnViewPop",
			title: $.i18n.map['selectColumn'],   
			url:"sysUserFactor/optionPage.do",
			width:"800",
			requestMethod: 'ajax'
	};
	$.Popup.create(contentMsg);
}
function columnViewSubmit(){
	//获取所有右列数据
    var rightAry = new Array(); //定义数组
    $("#right_select option").each(function(){ //遍历全部option
        rightAry.push($(this).val()); //添加到数组中
    });
    
    if(rightAry.length==0){
    	$.Popup.create({ title : $.i18n.map['prompt'], content : "至少要展示一个要素!" });
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
    		$.Popup.create({ title : $.i18n.map['prompt'], content : data.msg });
    	}
    	
    },"json");
    
}

function changeNum(num){
	showMark();
	var params = getParams();
	params.selectNum = num;
	
	if(params != undefined){
		$.post("monitor/mapInfo.do",params,function(data){
			$("#map_index").html(data);
			hideMark();
			//loadFactorList();
		});
	}
}