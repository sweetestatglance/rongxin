/******************************监控图片*****************************************/
$(function() {
	$("#contentImg").height(
		($(".report-pop").height()) -90-($("#serach").outerHeight()));
	
	$("#contentList").height(
			($(".report-pop").height()) -110-($("#serach").outerHeight()));
});
function searchImage(params){
	showMark();
	$.post("reportDetail/monitorPictureList.do",params,function(data){
		hideMark();
		$("#contentImg").html(data);
	});
}
/**
 * 通道点击切换
 * @param channelNum
 */
function channelImg(channelNum){
	var stcd = $("#stcd").val();
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var params = {
			channelNum:channelNum,
			stcd:stcd,
			beginTime:query_beginTime,
			endTime:query_endTime
	}
	searchImage(params);
}

/**
 * 图片分页
 */
function changeImgPage(page){
	var stcd = $("#stcd").val();
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var channelNum = $("input[name='channel'].btn-channel-select").attr('value');
	if(channelNum.indexOf($.i18n.map['channel'])==0){
		channelNum =channelNum.replace($.i18n.map['channel'],"");
	}
	var params = {
			channelNum:channelNum,
			stcd:stcd,
			beginTime:query_beginTime,
			endTime:query_endTime,
			pageNo:page
	}
	searchImage(params);
}
/**
 * 查询
 */
function serach(){
	var channelNum = $("input[name='channel'].btn-channel-select").attr('value');
	if(channelNum.indexOf($.i18n.map['channel'])==0){
		channelNum =channelNum.replace($.i18n.map['channel'],"");
	}
	channelImg(channelNum);
}

