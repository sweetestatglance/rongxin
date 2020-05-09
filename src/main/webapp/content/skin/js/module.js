

$(function() {
	
	changeReportType($("#select-type").val());
	
	$.get("monitor/staticList.do",{addvcdId:$("#topNodeId").val()},function(data){
		$(".map_right_content").html(data);
	});
});



/********************************模拟图相关begin********************************/

/********************************模拟图相关end********************************/


/********************************报表相关begin********************************/
var detailResultMap;//存放列表数组

var detailRMap;



/**
 * 切换日期查询类型 日/月/年
 * @param value
 */
function changeSearchType(value){
	
	var obj = document.getElementById("searchTime"); 
	obj.value="";
	if(value=="3"){
		
		obj.onfocus=function(){ 
			WdatePicker({
				dateFmt:'yyyy-MM-dd',
				maxDate:'%y-%M-%d'
			}); 
		};
		
	}else if(value=="4"){
		
		obj.onfocus=function(){ 
			WdatePicker({
				dateFmt:'yyyy-MM',
				maxDate:'%y-{%M-1}'
			}); 
		};
		
	}
	
}

/**
 * 切换报送类型
 * @param value
 */
function changeReportType(value){
	$.getJSON("module/getDeviceByType.do",{type:value},function(data){
		
		var sList = data.stbprpBList;
		var optionHtm = "<span>设备名称：</span> ";
		
		for ( var key in sList) {
//			console.log(key);
			var tmp = "<input type='checkbox' id='" + sList[key].stcd + "' value='" + sList[key].stnm + "'/>";
			
			tmp += "<label for='" + sList[key].stcd + "'>" ;
			tmp += sList[key].stnm + "</label>&nbsp;"
			optionHtm += tmp;
		}
		$("#device-checkbox").html(optionHtm);
		
	});
}

/**
 * 查询报表数据
 * @returns {Boolean}
 */
function searchReportForm(){
	detailRMap={};
	var chart;
	
	var searchTime = $("#searchTime").val();
	if(searchTime=="" || searchTime==undefined){
		$.Popup.create({ title: "提示", content: "请选择时间"});
 		return false;
	}
	
	var stId = [];
	var stName = [];
    var selectRow = $("#device-checkbox input[type='checkbox']:checked");
    for (var i = 0; i < selectRow.length; i++) {
    	stId.push(selectRow[i].id);
    	stName.push(selectRow[i].value);
    }
    if (stId.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择设备"});
  	  	return false;
    }
	var params={
			deviceNos : stId.toString(),
			deviceNames : stName.toString(),
			searchType : $("#search-type").val(),
			beginTime : searchTime,
			reportType : $("#select-type").find("option:selected").attr("hvalue"),
			detailReportType : $("#query_detailReportType").val() 
	};
	
	showMark();
	
	$.ajax({
		type : "POST",
		url : "reportManage/reportForm.do",
		data : params,
		success : function(data) {
			
			hideMark();
			$("#report-content-data").html(data);
			
		},
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
 * 导出报表
 */
function exportReportForm(obj){
	$(obj).addClass("disabled");
	$(obj).removeAttr("onclick");
	$(obj).html("正在导出...");
	
//	var beginTime = $("#hidden_query_beginTime").val();
//	var endTime = $("#hidden_query_endTime").val();
//	
//	if(beginTime==undefined || endTime==undefined){
//		$.Popup.create({ title: "提示", content: "请先查询报表数据"});
//		$(obj).attr("onclick","exportReportForm(this)");
//		$(obj).html("报表导出")
//  	  	return false;
//	}
	
	
	var searchTime = $("#searchTime").val();
	if(searchTime=="" || searchTime==undefined){
		$.Popup.create({ title: "提示", content: "请选择时间"});
 		return false;
	}
	
	var stId = [];
	var stName = [];
    var selectRow = $("#device-checkbox input[type='checkbox']:checked");
    for (var i = 0; i < selectRow.length; i++) {
    	stId.push(selectRow[i].id);
    	stName.push(selectRow[i].value);
    }
    if (stId.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择设备"});
    	$(obj).attr("onclick","exportReportForm(this)");
		$(obj).html("报表导出")
  	  	return false;
    }
	var params={
			deviceNos : stId.toString(),
			deviceNames : stName.toString(),
			searchType : $("#search-type").val(),
			beginTime : searchTime,
			reportType : $("#select-type").find("option:selected").attr("hvalue"),
			detailReportType : $("#query_detailReportType").val(),
			reportTypeTitle : $("#select-type option:selected").text()
			
	};
	
//	var params = {
//			reportTypeTitle : $("#select-type option:selected").text()/*$('#waterDiv input[name="reportType"]:checked').next("label").text()*/,
//			detailResultMap:JSON.stringify(detailResultMap),
//			detailType:$("#select-type").find("option:selected").attr("hvalue"),
//			deviceNo : $("#select-device").val(),
//			beginTime : $("#hidden_query_beginTime").val(),
//			endTime : $("#hidden_query_endTime").val()
//	};
	
//	console.log(params);
	$.post("reportManage/exportExcelData.do", params, function(data){
		if(data.success){
			window.location.href="reportManage/download.do?fileName=" +  encodeURI(encodeURI(data.fileName));
		}else{
			alert("数据导出失败!");
		}
		$(obj).attr("onclick","exportReportForm(this)");
		$(obj).html("报表导出")
	},"json")
}

/********************************报表相关end********************************/
