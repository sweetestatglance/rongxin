/**********************************告警管理脚本***************************************************/
$(function() {
	$(".tree").height($(".left").outerHeight()- 10);
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#warnDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
});
var query_beginTime,query_endTime;
function loadAlarmList(params, flag){
	  showMark();
	$.get("stAlarmInfo/list.do",params,function(data){
		$("#alarmContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 查询
 */
function searchAlarm(page){
	$(".quick").removeClass("sellquick")
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var type = $("#type").val();
	var dstcd = $("#dstcd").val();
	if(dstcd==$.i18n.map['code']){
		dstcd = "";
	}
	
	query_beginTime=undefined;
	query_endTime=undefined;
	
	var params = {
			type:type,
			stcd_query:encodeURI(dstcd),
			searchType:$("#searchType").val(),
			queryTime:$(".query_Time:visible").val(),
			nodeIds:aryIds.toString(),
			pageNo:page,
			isDevice:selectdNode.isDevice
	};
	loadAlarmList(params);
}
/**
 * 今天/昨天
 */
function todayYesSearch(val){
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var type = $("#type").val();
	var dstcd = $("#dstcd").val();
	if(dstcd==$.i18n.map['code']){
		dstcd = "";
	}
	
	query_beginTime = getToDay();
	query_endTime = getToDay();
	if(val==1){
		//昨天
		query_beginTime = getYesterDay();
		query_endTime = getYesterDay();
	}else if(val==2){
		//前7天
		query_beginTime = get7DayAgo();
		query_endTime = getToDay();
	}
	
	
	$("#query_beginTime").val(query_beginTime);
	$("#query_endTime").val(query_endTime);
	
	var params = {
			type:type,
			stcd_query:encodeURI(dstcd),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			nodeIds:aryIds.toString(),
			isDevice: selectdNode.isDevice
	};
	loadAlarmList(params);
}


/**
 * 解决
 */
function settled(mcode){
	 var ids = [];
     if(mcode == null || mcode == undefined){
	    var selectRow = $("#warnDiv tbody input[type='checkbox']:checked");
	    for (var i = 0; i < selectRow.length; i++) {
	    	ids.push(selectRow[i].value);
	    }
	 }else{
		  ids = mcode;
	 }
	 if (ids.length == 0) {
		 $.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['pleaseSelect']});
	     return false;
	 }
	 fnAjaxRequest(
			"stAlarmInfo/settled.do",
			{ids:ids.toString()},
			"json",
			"POST",
			function(data){
				if(data.success){
					searchAlarm();
				}else{
					$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
					return false;
				}
			}
	);
}

/**
 * 撤销
 */
function warnRecall(mcode){
	fnAjaxRequest(
			"stAlarmInfo/warnRecall.do",
			{ids:mcode},
			"json",
			"POST",
			function(data){
				if(data.success){
					searchAlarm();
				}else{
					$.Popup.create({ title: $.i18n.map['prompt'], content: data.msg});
					return false;
				}
			}
	);
}

/**
 * 导出
 */
function  exportAlarm(){
	/*var today = $("#today").css("background-color");
	alert(today);
	return false;*/
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var type = $("#type").val();
	var dstcd = $("#dstcd").val();
	if(dstcd==$.i18n.map['code']){
		dstcd = "";
	}
	var params = {
			type:type,
			stcd_query:encodeURI(dstcd),
			searchType:$("#searchType").val(),
			queryTime:$(".query_Time:visible").val(),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			isDevice:selectdNode.isDevice,
			nodeIds:aryIds.toString()
	};
	$.post("stAlarmInfo/alarmExport.do",params,function(data){
		window.location.href = "stAlarmInfo/download.do?fileName=" +  encodeURI(encodeURI(data));
	});
}

/**
 * 告警分页
 * @param page
 */
function changePage(page){
	searchAlarm(page);
}