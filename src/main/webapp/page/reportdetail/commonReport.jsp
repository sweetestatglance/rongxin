<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--

.report-pop {
	background-color: #f0f5f6;
}
.popup-content{
	text-align: left;
}

.searchDiv {
    position: absolute;
    left: 0px;
    top: 0px;
    right: 0px;
    padding: 10px;
    height: 25px;
    /* background-color: #FFF; */
}
.imageDiv {
	position: absolute;
	left: 10px;
	top: 45px;
	background-color: #fff;
	height: 180px;
	width: 250px;
	border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px;
	/* padding: 10px; */
}

.listDiv {
	height: 182px;
	left: 10px;
	top: 45px;
	right: 10px;
	/* overflow-y: auto; */
	position: absolute;
	background-color: #ffffff;
	border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px;
}

.chartsDiv {
	position: absolute;
	left: 10px;
	top: 177px;
	right: 10px;
	bottom: 10px;
	/* border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px; */
	background-color: #ffffff;
    border: 1px solid #d8d8d8;
}

.chartsDiv a {
	font-size:12px;
}

#reportFactorList td,#reportFactorList th{
	height:33px;
}

#reportFactorList th a {
    font-size: 14px;
    color: #666666;
}
-->
</style>
<div class="popup-content"
	style="height:auto;padding:0;border-left:0px;border-right:0px;background-color:#f0f5f6;">
	<input type="hidden" id="hidden_query_beginTime" value="${query_beginTime}">
	<input type="hidden" id="hidden_query_endTime" value="${query_endTime}">
	<div class="searchDiv">
	    <select id="searchType" class="select" onchange="showArea(this)">
			<option value="2" area="daySearchArea"   <c:if test="${searchType==2}">selected='selected'</c:if>><fmt:message  key="dailyInquiry"/></option>
			<option value="3" area="monthSearchArea" <c:if test="${searchType==3}">selected='selected'</c:if>><fmt:message key="monthlyQuery"/></option>
			<option value="4" area="yearSearchArea"  <c:if test="${searchType==4}">selected='selected'</c:if>><fmt:message key="yearlyQuery"/></option>
		</select>
	    <div id="allArea" style="float:left;font-size:14px;">
            <div class="selDiv" id="daySearchArea" style="float:left">
				<span style="margin-left:10px;"><fmt:message key="date"/>：</span>
				<input class="Wdate query_Time" value="${preDayTime}" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
			</div>
			<div class="selDiv" id="monthSearchArea" style="display:none;float:left;">
				<span style="margin-left:10px;"><fmt:message key="month"/>：</span>
				<input class="Wdate query_Time" value="${preMonthTime}" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM',maxDate:'%y-{%M}'})" />
			</div>
			<div class="selDiv" id="yearSearchArea" style="display:none;float:left;">
				<span style="margin-left:10px;"><fmt:message key="years"/>：</span>
				<input class="Wdate query_Time" value="${preYearTime}" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',maxDate:'{%y}'})" />
			</div>
		</div>
		<button type="button" style="height:25px;margin-left: 20px;" onclick="getFactorTime(1)" class="btn btn-xs btn-default dropdown-toggle"><fmt:message key="today"/></button>
		<button type="button" style="height:25px;margin-left: 10px;" onclick="getFactorTime(2)" class="btn btn-xs btn-default dropdown-toggle"><fmt:message key="yesterday"/></button>
		<button type="button" style="height:25px;margin-left: 10px;" onclick="getFactorTime(4)" class="btn btn-xs btn-default dropdown-toggle"><fmt:message key="thisMonth"/></button>
		<button type="button" style="height:25px;margin-left: 10px;" onclick="getFactorTime(6)" class="btn btn-xs btn-default dropdown-toggle"><fmt:message key="lastYear"/></button>
		<input type="button" class="btn-search" style="margin-left:50px;" value="<fmt:message key="query"/>" onclick="refreshPage()">
	</div>

	<div class="listDiv">
		<table id="reportFactorList" cellspacing="0" cellpadding="3" class="list-table" style="">
			<thead>
				<tr>
					<th style="" width="20%"></th>
					<th width="16%"><fmt:message key="newest"/></th>
					<th width="16%"><fmt:message key="highest"/></th>
					<th width="16%"><fmt:message key="lowest"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>要素值</td>
					<td><c:choose><c:when test="${detailFactor.newFactorValue==null}">--</c:when><c:otherwise><fmt:formatNumber value="${detailFactor.newFactorValue}" pattern="0.0" maxFractionDigits="3"/></c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${detailFactor.maxFactorValue==null}">--</c:when><c:otherwise><fmt:formatNumber value="${detailFactor.maxFactorValue}" pattern="0.0" maxFractionDigits="3"/></c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${detailFactor.minFactorValue==null}">--</c:when><c:otherwise><fmt:formatNumber value="${detailFactor.minFactorValue}" pattern="0.0" maxFractionDigits="3"/></c:otherwise></c:choose></td>
				</tr>
				<tr>
					<td><fmt:message key="acquisitionTime"/></td>
					<td><c:choose><c:when test="${detailFactor.newDate==null}">--</c:when><c:otherwise> <fmt:formatDate value="${detailFactor.newDate}" pattern="yyyy-MM-dd HH:mm" /></c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${detailFactor.maxDate==null}">--</c:when><c:otherwise> <fmt:formatDate value="${detailFactor.maxDate}" pattern="yyyy-MM-dd HH:mm" /></c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${detailFactor.minDate==null}">--</c:when><c:otherwise> <fmt:formatDate value="${detailFactor.minDate}" pattern="yyyy-MM-dd HH:mm" /></c:otherwise></c:choose></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="chartsDiv" class="chartsDiv"></div>
	<div id="detailListMark" class="chartsDiv control-loading" style="display:none; z-index: 1;-moz-opacity: 0.4; opacity: 0.4; filter: alpha(opacity=40);background-color: #d8d8d8;"></div>
	<div id="detailListDiv" class="chartsDiv" style="padding-top: 8px;overflow-y: auto; display:none">
		<div class="month_choose" style="padding: 40px 0 0 10px; font-size: 12px;">
			 
			<span style="font-weight: bold;"><fmt:message key="monthSelection"/>：</span>
			
				<c:forEach items="${monthMap}" var="month" >
					<a href="javascript:initPage(1,${month.key})" class="<c:if test="${month.key eq viewMonth}">active</c:if>">${month.value}</a>
                </c:forEach> 
														
		<!-- 	<a href="javascript:initPage(1,12)" class="active">12月</a>
			<a href="javascript:initPage(1,11)">11月</a>
			<a href="javascript:initPage(1,10)">10月</a>
			<a href="javascript:initPage(1,9)">9月</a>
			<a href="javascript:initPage(1,8)">8月</a>
			<a href="javascript:initPage(1,7)">7月</a>
			<a href="javascript:initPage(1,6)">6月</a>
			<a href="javascript:initPage(1,5)">5月</a>
			<a href="javascript:initPage(1,4)">4月</a>
			<a href="javascript:initPage(1,3)">3月</a>
			<a href="javascript:initPage(1,2)">2月</a>
			<a href="javascript:initPage(1,1)">1月</a> -->
		</div>
		<div style="padding: 7px; text-align: center; font-weight: 700;">
			<span style="font-size: 16px;"><fmt:message key="detailData"/></span>
			<a class="btnReportExport" href="javascript:void(0)" onclick="exportReportForm(this)" style="float: right; font-weight: 500;color: #000;">报表导出</a>
		</div>
		<table id="detailTable" cellspacing='0' cellpadding='3' class='list-table'>
		 <thead>
			<tr>
				<th width="15%"><fmt:message key="stationCode"/></th>
				<th width="20%"><fmt:message key="stationName"/></th>
				<th width="15%"><fmt:message key="date"/></th>
				<th width="15%">${factorNameMap[flag].name}</th>
			</tr>
		</thead>
		<tbody>
		</tbody>

		</table>
		<div class="list-page" style="padding-top: 5px; padding-bottom: 10px;">
			<div id="factorPager"></div>
		</div>
	</div>
	<ul class="show-type" style="top:185px">
		<li data="chart" style=' height: 16px; line-height: 16px;'><fmt:message key="chart"/></li>
		<li class="sell" data="list" style=' height: 16px; line-height: 16px;'><fmt:message key="list"/></li>
	</ul>
	
	<div class="device_right_list_div">
		<div class="device_button" onclick="viewDevicePage('${stcd}')">
			<fmt:message key="switchDev"/>
		</div>
			<div class="device_right" style="z-index:110;">
				<div class="device_right_top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="98%" style="font-size:14px; font-weight:bold"><fmt:message key="deviceTitle"/></td>
							<td width="2%"><img src="${ctx}/content/skin/css/images/map-close.png" width="10" onclick="viewDevicePage()" style="cursor: pointer;"
								height="10" /></td>
						</tr>
					</table>
				</div>
				
			<div class="device_right_content" style="overflow-y:scroll;overflow-x:hidden;">
			  
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	var searchType = '${searchType}';
	$("div.selDiv").hide();
	$(".month_choose").hide();
	if(searchType==3){
		$("#monthSearchArea").show();
	}else if(searchType==4){
		$("#yearSearchArea").show();
		$(".month_choose").show();
	}else{
		$("#daySearchArea").show();
	}
	initEcharts(initPage);
	currentCall=getFactorPage;
});


function initPage(pageNo,month){
	if(month==undefined) month=$(".month_choose a").length;
	if(pageNo == undefined) pageNo=1;
	/* $("#detailListMark").show(); */
	$("#detailListDiv").show();
	var flag = $("#normaltab li.current a").attr("flag");
	$.getJSON("stAllDetailsFactor/getList.do",{
		pageNo:pageNo,
		stcd:$("#stcd").val(),
		queryTime:queryTime,
		searchType:searchType,
		flag:flag,
		month:month
	},function(data){
		query_month = month;
		var list = data.list;
		var newcontent = '';
		
		if(list==null || list.length==0){
			newcontent +="<tr height='40'><td colspan='6'><font color='#a8a8a8'> <label style='float:left;padding-left:15px'>暂无数据</label></font></td></tr>";
		}else{
			for (var i in list) {
				if(!isNaN(parseInt(i))){
					newcontent += "<tr class='" + (i%2==0?'singular':'double') + "'>";
					newcontent += "<td>" + list[i].stcd + "</td>";
					newcontent += "<td>" + $("#stnm").val() + "</td>";
					
					newcontent += "<td>";
					newcontent += list[i].tm!=undefined?new Date(list[i].tm).format('yyyy-MM-dd hh:mm:ss'):'';
					newcontent += "</td>";
					
					newcontent += "<td>"+ (list[i][flag]==undefined?'--':list[i][flag]) + "</td>";
					
					newcontent += "</tr>";
				}
			}
		}
		$("#detailListMark").hide();
		$("#detailTable tbody").html(newcontent);
		
		var pagingBean = data.pagingBean;
		var totalPage = pagingBean.pageNum;
		var totalRecords = pagingBean.totalItems;
		var pageNo = pagingBean.pageNo;
		if (!pageNo) {
			pageNo = 1;
		}
		
		//生成分页
		$.Pagination.generPageHtml({
			//填充的id
			 pagerid : "factorPager",
			//当前页
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			mode : 'click',
			click : function(n){
				//分页执行方法
				initPage(n,data.month)
				return true;
			}
		});
		
	});
	
}
 
</script>
