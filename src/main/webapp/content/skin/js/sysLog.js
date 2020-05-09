/**********************************日志功能脚本***************************************************/
/**
 * 条件查询
 */
function searchLogList(){
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var query_keyword = $("#query_keyword").val();
	var query_area_keyword = $("#query_area_keyword").val();
	if(query_keyword==$.i18n.map['keyword']){
		query_keyword = "";
	}
	
	if(query_area_keyword==$.i18n.map['loginAddr']){
		query_area_keyword = "";
	}
	var params = {
			byenterpriseid:$("#currentEnterpriseId").val(),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			query_keyword:encodeURI(query_keyword),
			query_area_keyword:encodeURI(query_area_keyword)
			
	};
	loadList(params);
}
/**
 * 加载列表
 * @param params
 */
function loadList(params){
	showMark();
	$.get("sysLog/index.do",params,function(data){
		$("#twoContain").html(data);
	});
	hideMark();
}
/**
 * 重置
 */
function resetLog(){
	$("#query_beginTime").val("");
	$("#query_endTime").val("");
	$("#query_keyword").val("");
	$("#query_area_keyword").val("");
	searchLogList();
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var query_keyword = $("#query_keyword").val();
	var query_area_keyword = $("#query_area_keyword").val();
	if(query_keyword==$.i18n.map['keyword']){
		query_keyword = "";
	}
	
	if(query_area_keyword==$.i18n.map['loginAddr']){
		query_area_keyword = "";
	}
	var params = {
			byenterpriseid:$("#currentEnterpriseId").val(),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			query_keyword:encodeURI(query_keyword),
			query_area_keyword:encodeURI(query_area_keyword),
			pageNo:page
	};
	loadList(params);
}

/**
 * 设置开始时间
 */
function setHiddenBeginTime(dp){
	var date =  $dp.cal.date.y +"-"+ $dp.cal.date.M +"-"+ $dp.cal.date.d +" "+ $dp.cal.date.H +":"+ $dp.cal.date.m +":"+ $dp.cal.date.s;
	$("#hidden_beginTime").val(date);
	
}
	
/**
 * 设置结束时间
 */
function setHiddenEndTime(dp){
	var date =  $dp.cal.date.y +"-"+ $dp.cal.date.M +"-"+ $dp.cal.date.d +" "+ $dp.cal.date.H +":"+ $dp.cal.date.m +":"+ $dp.cal.date.s;
	$("#hidden_endTime").val(date);
}