<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--
div.devList{
	position: absolute;
    left: 10px;
    top: 45px;
    right: 10px;
    bottom: 5px;
    overflow: hidden;
    overflow-y: auto;
}

table.list-table thead tr th, table.list-table tbody tr td {
    font-size: 12px;
    height: 30px;
    border-bottom: 1px solid #d0d9de;
    border-right: 1px solid #d0d9de;
}
-->
</style>

<div class="devList" id="stBprpBDiv">
		<table id="monAlarmList" cellspacing='0' id="reportTable" cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th width="20%"><fmt:message key="stationCode"/></th>
					<th width="25%"><fmt:message key="stationName"/></th>
					<th width="15%"><fmt:message key="siteType"/></th>
					<th width="20%"><fmt:message key="alarmTime"/></th>
					<%-- <th width="15%"><fmt:message key="alarmType"/></th> --%>
				</tr>
			</thead>
			<tbody id="list-content">
				<c:forEach var="item" items="${alarmList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.stcd}</td>
						<td>${item.stnm}</td>
						<td><c:choose>
								<c:when test="${item.sttp==0}"><fmt:message key="sttp0"/></c:when>
								<c:when test="${item.sttp==1}"><fmt:message key="sttp1"/></c:when>
								<c:when test="${item.sttp==2}"><fmt:message key="sttp2"/></c:when>
								<c:when test="${item.sttp==3}"><fmt:message key="sttp3"/></c:when>
								<c:when test="${item.sttp==4}"><fmt:message key="sttp4"/></c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><c:choose>
								<c:when test="${item.type==0}"><fmt:message key="waterLevelAlarm"/></c:when>
								<c:when test="${item.type==1}"><fmt:message key="rainfallAlarm"/></c:when>
								<c:when test="${item.type==2}"><fmt:message key="waterLevelSensorFailure"/></c:when>
								<c:when test="${item.type==3}"><fmt:message key="lowVoltageAlarm"/></c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<div class="list-page" style="padding-top: 5px; padding-bottom: 10px;">
	<div id="alarmPager"></div>
</div>

<script type="text/javascript">
	var pageSize=2;
$(function(){
	var totalRecords = alarmResultArray==undefined?0:alarmResultArray.length;
	var totalPage = totalRecords%pageSize==0?totalRecords/pageSize:(totalRecords/pageSize)+1;
	var pageNo = 1;
	if(!pageNo){
		pageNo = 1;
	}
	
	//生成分页
	$.Pagination.generPageHtml({
		//填充的id
		 pagerid : "alarmPager",
		//当前页
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',
		click : function(n){
			//分页执行方法
			//changePage(n);
			$.Pagination.selectPage(n,"alarmPager");
			alarmPageselectCallback(n-1);
			return true;
		}
	});
	alarmPageselectCallback(0);
});

/**
 * 分页
 * @param page_index 当前页数
 * @param jq
 * @returns {Boolean}
 */
function alarmPageselectCallback(page_index){
	var $tableTbody = $("#monAlarmList tbody");
	$tableTbody.html("");
	//若存在时间条件则为累加值且不显示时间
	var hidden_query_beginTime = $("#hidden_query_beginTime").val();
	var hidden_query_endTime = $("#hidden_query_endTime").val();
	var isSum = false;
	if(hidden_query_beginTime!=""&& hidden_query_endTime!=""){
		isSum=true;
		$("#ValueTitle").text("<fmt:message key='cumulativePrecipitation'/>(mm)");
		$("#TimeTitle").hide();
		isTimeSearch=true;
	}else{
		$("#ValueTitle").text("<fmt:message key='currentPrecipitation'/>(mm)");
		$("#TimeTitle").show();
		isTimeSearch=false;
	}
	//var resultArray = devList;
    var max_elem = Math.min((page_index+1) * pageSize, alarmResultArray.length);
    var newcontent = '';

    for(var i=page_index*pageSize;i<max_elem;i++)
    {
    	newcontent += "<tr class='" + (i%2==0?'singular':'double') + "'>";
		newcontent += "<td>" + alarmResultArray[i].stcd + "</td>";
		newcontent += "<td>" + alarmResultArray[i].stnm + "</td>";
		if(alarmResultArray[i].sttp==0){
			newcontent += "<td><fmt:message key='sttp0'/></td>";
		}else if(alarmResultArray[i].sttp==1){
			newcontent += "<td><fmt:message key='sttp1'/></td>";
			
		}else if(alarmResultArray[i].sttp==2){
			newcontent += "<td><fmt:message key='sttp2'/></td>";
		}else if(alarmResultArray[i].sttp==3){
			newcontent += "<td><fmt:message key='sttp3'/></td>";
		}else{
			newcontent += "<td><fmt:message key='sttp4'/></td>";
		} 
		
		newcontent += "<td>";
		newcontent += alarmResultArray[i].tm!=undefined?new Date(alarmResultArray[i].tm).format('yyyy-MM-dd hh:mm:ss'):'';
		newcontent += "</td>";
		/* 去掉告警类型 */
		/* if(alarmResultArray[i].type==0){
			newcontent += "<td><fmt:message key='waterLevelAlarm'/></td>";
		}else if(alarmResultArray[i].type==1){
			newcontent += "<td><fmt:message key='rainfallAlarm'/></td>";
			
		}else if(alarmResultArray[i].type==2){
			newcontent += "<td><fmt:message key='waterLevelSensorFailure'/></td>";
		}else if(alarmResultArray[i].type==3){
			newcontent += "<td><fmt:message key='lowVoltageAlarm'/></td>";
		} */
		
		/* var optHtml = "<td><ul>" + 
					"<li class='report-enable' " + "title='报表' onclick='" + "showReportDialog(\"" + resultArray[i].id + "\")'></li>" ;
		if(resultArray[i].video == 1) {
			optHtml += "<li class='image-enable' " + "title='图像' onclick='" + "showImageDialog(\"" + resultArray[i].id + "\")'></li>";
			optHtml += "<li class='video-unenable' " + "title='该设备未接入'></li>";
		} else if (resultArray[i].video == 2) {
			optHtml += "<li class='video-enable' " + "title='视频' onclick='" + "showVideoDialog(\"" + resultArray[i].id + "\",\"" + resultArray[i].dvraddr + "\",\"" + resultArray[i].dvrcode + "\")'></li>";
			optHtml += "<li class='image-enable' " + "title='图像' onclick='" + "showImageDialog(\"" + resultArray[i].id + "\")'></li>";
		} else {
			optHtml += "<li class='image-unenable' " + "title='该设备未接入'></li>";
			optHtml += "<li class='video-unenable' " + "title='该设备未接入'></li>";
		}
		optHtml +="</ul></td>";
					
		newcontent += optHtml; */
		newcontent += "</tr>";
    	
    }
    $tableTbody.html(newcontent)
    return false;
}
 
</script>
</div>