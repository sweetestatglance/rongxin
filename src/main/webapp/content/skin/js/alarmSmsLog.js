function searchSmsLog(){
	var name = $("#name").val();
	if(name==$.i18n.map['alarmName']){
		name = "";
	}
	var phone = $("#phone").val();
	if(phone==$.i18n.map['phone']){
		phone = "";
	}
	var params = {
		phone_query:encodeURI(phone),
		name_query:encodeURI(name)
	};
	loadSmsLogList(params,false);
}
function changePage(page){
	var name = $("#name").val();
	if(name==$.i18n.map['alarmName']){
		name = "";
	}
	var phone = $("#phone").val();
	if(phone==$.i18n.map['phone']){
		phone = "";
	}
	var params = {
			pageNo:page,
			phone_query:encodeURI(phone),
			name_query:encodeURI(name)
	};
	loadSmsLogList(params,false);
}
/**
 * 从新加载
 */
function loadSmsLogList(params,flag){
	var url = "stAlarmSmsSendLog/index.do";
	showMark();
	$.ajax({
		url : url,
		data : params,
		success :function(data){
			$("#twoContain").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
	
}
function resetSmsLog(){
	  $("#name").val($.i18n.map['alarmName']);
	  $("#phone").val($.i18n.map['phone']);
}