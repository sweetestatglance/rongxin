<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--
div.devVList{
	position: absolute;
    left: 10px;
    top: 50px;
    right: 10px;
    bottom: 5px;
    overflow: auto;
}

table.list-table thead tr th, table.list-table tbody tr td {
    font-size: 12px;
    height: 30px;
    border-bottom: 1px solid #d0d9de;
    border-right: 1px solid #d0d9de;
}

button.refresh{
	height:28px;
	border: 1px solid #abceec;
    padding: 3px 10px;
    background-color: #ffffff;
    color: #666666;
    cursor: pointer;
}

button.refresh:hover{
    background-color: #44c4e7;
    border: 1px solid #44c4e7;
    color: #ffffff;
}
.changeNum{
    border: 1px solid #ccc;
    display: inline;
    padding: 0px 0px 0px 0px;
    margin-right: 2px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    cursor: pointer;
    background: #fff;
    color: #999;
    }
-->
</style>

<div style="position: absolute;top: 6px;right: 10px;">
<div class="setting" style="cursor: pointer;float: left;margin-top:4px;margin-right:10px;" onclick="config()">
	<img src="content/skin/css/images/icon/setting_tools.png" style="vertical-align: sub;">&nbsp;<fmt:message key="listConfig"/>
</div>
<button onclick="loadMapInfo()" class="refresh"><fmt:message key="manualRefresh"/></button>
</div>
<div class="devVList" id="stBprpBDiv">
<table id="dvDevList" cellspacing='0' cellpadding='3' class='list-table' style="width: 100%;">
	<thead>
		<tr>
			<th style="height:25px;" width="70px" gls="70"><fmt:message key="status"/></th>
			<th width="100px" gls="100"><fmt:message key="stationCode"/></th>
			<th width="120px" gls="120"><fmt:message key="stationName"/></th>
			<th width="100px" gls="100">水文站</th>
			<th width="100px" gls="100">水文局</th>
			<th width="100px" gls="100">通讯方式</th>
			<th width="150px" gls="150"><fmt:message key="acquisitionTime"/></th>
			<c:forEach var="item"  items="${viewHead}" varStatus="vs">
				 <th width="80px" gls="80">${item}</th>
			</c:forEach>
			<%-- <th width="5%"><fmt:message key="waterLevel"/></th>
			<th width="5%"><fmt:message key="rainfall"/></th>
			<th width="5%"><fmt:message key="flow"/></th>
			<th width="5%"><fmt:message key="voltage"/></th>
			<th width="5%"><fmt:message key="ph"/></th>
			<th width="5%"><fmt:message key="turbidity"/></th>
			<th width="5%"><fmt:message key="dissolvedOxygen"/></th>
			<th width="5%"><fmt:message key="conductivity"/></th>
			<th width="5%"><fmt:message key="temperature"/></th>--%>
			<th width="70px" gls="70"><fmt:message key="callThePolice"/></th> 
			<th width="120px" gls="120"><fmt:message key="operating"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${devList}" varStatus="vs">
			<tr class="${vs.index%2==0?'singular':'double'}">
				<td>
					<c:choose>
						<c:when test="${item.dsfl==0}">
							<img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'/>
						</c:when>
						<c:when test="${item.dsfl==1}">
							<img src='${ctx}/content/skin/css/images/icon/icon-accept.png'/>
						</c:when>
						<c:when test="${item.dsfl==2}">
							<img src='${ctx}/content/skin/css/images/icon/icon-dormant.png'/>
						</c:when>
						<c:when test="${item.dsfl==3}">
							<img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'/>
						</c:when>
						<c:otherwise>
							<img src='images/icon/icon-upgrade.gif'/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${item.stcd}</td>
				<td>${item.stnm}</td>
				<td>${item.addvnm1}</td><!-- 水文站 -->
				<td>${item.addvnm2}</td><!-- 水文局 -->
				<td>${item.commode}</td>
				<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${item.water}</td>
				<td>${item.rain}</td>
				<td>${item.flow}</td>
				<td>${item.voltage}</td>
				<td>${item.ph}</td>
				<td>${item.turbidimeter}</td>
				<td>${item.oxygen}</td>
				<td>${item.surfactants}</td>
				<td>${item.temperature}</td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>

</table>
<div class="list-page">
	<div id="dvPagination"></div>
	<div class="changeNum" style="color:#adadad;float:right;margin-top:-27px;">
		<input id="selectNums" type="hidden" value="${selectNum }">
		<select id="selectNum" style="height: 26px" onchange="changeNum(this.options[this.options.selectedIndex].value)">
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
		</select>
	</div>
</div>

<script type="text/javascript">
	var selectNum = document.getElementById("selectNum");
	var selectNums = $("#selectNums").val();
	for(var i = 0;i<=selectNum.options.length;i++){
	    if(selectNum.options[i].value == selectNums){
	    	selectNum.options[i].selected = true;break;
	    }
	}
	/* var dvPSize=10; */
	var dvPSize =$('#selectNum option:selected').val();
	var alarmStcd = '${alarmStcd}';
$(function(){
	var totalRecords = resultArray==undefined?0:resultArray.length;
	var totalPage = totalRecords%dvPSize==0?totalRecords/dvPSize:(totalRecords/dvPSize)+1;
	var pageNo = 1;
	if(!pageNo){
		pageNo = 1;
	}
	resizeTable();
	
	//生成分页
	$.Pagination.generPageHtml({
		//填充的id
		 pagerid : "dvPagination",
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
			$.Pagination.selectPage(n,"dvPagination");
			dvPageselectCallback(n-1);
			return true
			;
		}
	});
	dvPageselectCallback(0);
});

/**
 * 重置表格宽度
 */
function resizeTable(){
	
	var maxWidth = $("#stBprpBDiv").width();
	
	var realWidth=0;
	$("#dvDevList thead tr th").each(function(){
		realWidth += parseInt($(this).attr("gls"));
	})
	
	if(realWidth>maxWidth){
		$("#dvDevList").css("width",realWidth+"px");
	}
}

/**
 * 分页
 * @param page_index 当前页数
 * @param jq
 * @returns {Boolean}
 */
function dvPageselectCallback(page_index){
	 
	pagerDvVNum=page_index;
	var $tableTbody = $("#dvDevList tbody");
	$tableTbody.html("");
	
    var max_elem = Math.min((page_index+1) * dvPSize, resultArray.length);
    var newcontent = '';

    for(var i=page_index*dvPSize;i<max_elem;i++)
    {
    	newcontent += "<tr class='" + (i%2==0?'singular':'double') + "'>";
		newcontent += "<td>" ;
		if(resultArray[i].dsfl==0){
			newcontent += "<img src='content/skin/css/images/icon/icon-exclamation.png'>" ;
		}else if(resultArray[i].dsfl==1){
			newcontent += "<img src='content/skin/css/images/icon/icon-accept.png'>" ;
		}else if(resultArray[i].dsfl==2){
			newcontent += "<img src='content/skin/css/images/icon/icon-dormant.png'>" ;
		}else{
			newcontent += "<img src='content/skin/css/images/icon/icon-upgrade.gif'>" ;
		}
		newcontent += "</td>";
		newcontent += "<td>" + resultArray[i].stcd + "</td>";
		newcontent += "<td>" + resultArray[i].stnm + "</td>";
		newcontent += "<td>" +  (isNotEmpty(resultArray[i].addvnm1)?resultArray[i].addvnm1:'-')+ "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].addvnm2 )?resultArray[i].addvnm2 :'-')+ "</td>";
		if(resultArray[i].commode==0){
			newcontent += "<td>GPRS</td>";
		}else{
			newcontent += "<td>北斗通信</td>";
		}
		newcontent += "<td>" + (isNotEmpty(resultArray[i].tm)?new Date(resultArray[i].tm).format('yyyy-MM-dd hh:mm'):'-') + "</td>";
		
		newcontent +="<c:forEach var='factorItem' items='${sysUserFactorList}' varStatus='dvs'>";
		if(${factorItem.factor=="voltage"}){
			if(isNotEmpty(resultArray[i].${factorItem.factor})){
				if(resultArray[i].${factorItem.factor}<5 || resultArray[i].${factorItem.factor}>36)
				    newcontent += "<td>异常</td>";
				else if(resultArray[i].${factorItem.factor}>=5 || resultArray[i].${factorItem.factor}<=36)
					newcontent += "<td><img src='${ctx}/content/skin/css/images/state/battery1.png' />&nbsp;<font style='vertical-align:middle'>("+(resultArray[i].${factorItem.factor}).toFixed(1)+")</font></td>";
			}else{
				newcontent += "<td>-</td>";
			}
		}else if(${factorItem.factor=="signalinten"}){
			if(isNotEmpty(resultArray[i].${factorItem.factor})){
				if(resultArray[i].${factorItem.factor}<1 || resultArray[i].${factorItem.factor}>31)
				    newcontent += "<td>异常</td>";
				else if(resultArray[i].${factorItem.factor}>=1 || resultArray[i].${factorItem.factor}<=10)
					newcontent += "<td><img src='${ctx}/content/skin/css/images/state/wifi3.png' />&nbsp;<font style='vertical-align:middle'>("+(resultArray[i].${factorItem.factor}).toFixed(1)+")</font></td>";
				else if(resultArray[i].${factorItem.factor}>=11 || resultArray[i].${factorItem.factor}<=20)
					newcontent += "<td><img src='${ctx}/content/skin/css/images/state/wifi2.png' />&nbsp;<font style='vertical-align:middle'>("+(resultArray[i].${factorItem.factor}).toFixed(1)+")</font></td>";
				else if(resultArray[i].${factorItem.factor}>20 || resultArray[i].${factorItem.factor}<=31)
					newcontent += "<td><img src='${ctx}/content/skin/css/images/state/wifi1.png' />&nbsp;<font style='vertical-align:middle'>("+(resultArray[i].${factorItem.factor}).toFixed(1)+")</font></td>";
			}else{
				newcontent += "<td>-</td>";
			}
		}else{
			if('${factorItem.factor}'=='td11'){
				if(isNotEmpty(resultArray[i].${factorItem.factor})){
					 if(resultArray[i].${factorItem.factor}==0){
						 newcontent += "<td><img src='${ctx}/content/skin/css/images/state/rain.png' /></td>";
					 }else if(resultArray[i].${factorItem.factor}==1){
						 newcontent += "<td><img src='${ctx}/content/skin/css/images/state/snow.png' /></td>";
					 }else{
						 newcontent += "<td>" + (resultArray[i].${factorItem.factor}).toFixed(1) + "</td>";
					 }
				}else{
					newcontent += "<td>-</td>";
				}
			}else{
				newcontent += "<td>" + (isNotEmpty(resultArray[i].${factorItem.factor})?resultArray[i].${factorItem.factor}.toFixed(1):'-') + "</td>"; 
			}
			
		}
		newcontent +="</c:forEach>";
		
		/* 	newcontent += "<td>" + (isNotEmpty(resultArray[i].water)?resultArray[i].water:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].rain)?resultArray[i].rain:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].flow)?resultArray[i].flow:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].voltage)?resultArray[i].voltage:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].ph)?resultArray[i].ph:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].turbidimeter)?resultArray[i].turbidimeter:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].oxygen)?resultArray[i].oxygen:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].surfactants)?resultArray[i].surfactants:'-') + "</td>";
		newcontent += "<td>" + (isNotEmpty(resultArray[i].temperature)?resultArray[i].temperature:'-') + "</td>"; */
		newcontent += "<td>" + (alarmStcd.indexOf(resultArray[i].stcd)>-1?"<img src='content/skin/css/images/icon/icon-have-alarm.png' onclick='turnToAlarm()'>":"<img src='content/skin/css/images/icon/icon-no-alarm.png'>") + "</td>";
		
		var optHtml = "<td>" + 
		"<button class='report-enable' " + "title='<fmt:message key='report'/>' onclick='" + "showReportDialog(\"" + resultArray[i].stcd + "\")'></button>" ;
	 	if(resultArray[i].cameratype == 1) {
			optHtml += "<button class='image-enable' " + "title='<fmt:message key='image'/>' style='margin-left:5px;' onclick='" + "showReportDialog(\"" + resultArray[i].stcd + "\",2)'></button>";
			 optHtml += "<button class='video-unenable' " + "title='<fmt:message key='deviceNoAccess'/>' style='margin-left:5px;'></button>";
		} else if (resultArray[i].cameratype == 2) {
			optHtml += "<button class='video-enable' " + "title='<fmt:message key='video'/>' style='margin-left:5px;' onclick='" + "showVideoDialog(\"" + resultArray[i].stcd + "\",\"" + resultArray[i].dvraddr + "\",\"" + resultArray[i].dvrcode + "\",\""+resultArray[i].dsfl +"\",\""+resultArray[i].videochannel+"\")'></button>";
			optHtml += "<button class='image-enable' " + "title='<fmt:message key='image'/>' style='margin-left:5px;' onclick='" + "showReportDialog(\"" + resultArray[i].stcd + "\",2)'></button>";
		} else {
			optHtml += "<button class='image-unenable' " + "title='<fmt:message key='deviceNoAccess'/>' style='margin-left:5px;'></button>";
			optHtml += "<button class='video-unenable' " + "title='<fmt:message key='deviceNoAccess'/>' style='margin-left:5px;'></button>"; 
		} 
		optHtml +="</td>";
				
		newcontent += optHtml;
		
		newcontent += "</tr>";
    	
    }
    $tableTbody.html(newcontent)
    
  	//若存在时间条件则为累加值且不显示时间
	var isSum = $("#isSum").val();
	if(isSum == "true" || isSum==true){
		$("#dvDevList>tbody>tr>td:nth-child(7),#dvDevList>thead>tr>th:nth-child(7)").hide();
	}else{
		$("#dvDevList>tbody>tr>td:nth-child(7),#dvDevList>thead>tr>th:nth-child(7)").show();
	}
    return false;
}
 /**
  * 视频弹出框
  */
function showVideoDialog(name,dvrAdd,dvrCode,dsfl,videochannel){
 	 var url = "";
 	 if ((navigator.userAgent.indexOf('Firefox') >= 0) ){
 		 //url = "devicePhoto/videoFirefoxPage.do";
 		 $.Popup.create({ title: "<fmt:message key='prompt'/>", content: "<fmt:message key='ieVideoPlay'/>"});
 		 return;
 	 }else if(navigator.userAgent.indexOf('Chrome') >= 0){
 		 $.Popup.create({ title: "<fmt:message key='prompt'/>", content: "<fmt:message key='ieVideoPlay'/>"});
 		 return;
 	 }
 	 else{
 		 url = "devicePhoto/videoIEPage.do";
 	 }
 		 
 	 var viewStatus = "<fmt:message key='deviceOffice'/>";
 	 if(status==1){
 		 viewStatus = "<fmt:message key='equipmentOnline'/>";
 	 }else if(status==2){
 		 viewStatus = "<fmt:message key='deviceSleep'/>";
 	 }else if(status==3){
 		 viewStatus = "<fmt:message key='deviceUpgrade'/>";
 	 }
 	 fnVideoDialog({
 		name:"video_Dialog",
 		title: "【"+name+"】<fmt:message key='videoView'/>",
 		url:url,
 		isVideo:true,
 		param:{
 			name:name,dvrAdd:dvrAdd,dvrCode:dvrCode,videochannel:videochannel,
 		}
 	});
 }
</script>
</div>