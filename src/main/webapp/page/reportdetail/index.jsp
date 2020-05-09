<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>详细信息</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/tab/jquery.tabso_yeso.js"></script>
<%-- <script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.charts.js"></script> --%>

<link href="${ctx}/content/skin/css/tab.css" rel="stylesheet" type="text/css" />
<style>
.report-pop {
	background-color: #f0f5f6;
}
.div {
	width: 90%;
	background: #fff;
	border: 1px solid #d7d7d7;
	margin-bottom: 20px;
	float: left;
	margin-left: 50px;
}

.div-title {
	width: 100%;
	line-height: 30px;
	margin: 1px;
	background: #44c4e7;
	font-size: 14px;
	color: #fff;
	float: left
}

.div-tab {
	width: 98%;
	margin: 15px;
	float: left
}
body {
	font: 12px;
}

.tabbtn a,img {
	border: 0;
}

.tabbtn a {
	color: #333;
	text-decoration: none;
	font-size: 12px;
}

.tabbtn a:hover {
	color: #3366cc;
	text-decoration: underline;
}

.tabbtn a:link {
	color: black;
	text-decoration: none;
}

#normalcon {
	position: absolute;
	left: 0px;
	top: 80px;
	right: 0px;
	bottom: 0px;
}
.select {
    float: left;
    height: 26px;
    margin: 0px;
    width: 120px;
    font-size: 12px;
    color: #555;
    vertical-align: middle;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
    -webkit-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}

.month_choose a{
    color: #4e4e4e;
    text-decoration: none;
    margin-left: 5px;
}

.month_choose a.active {
    color: #ff6306;
}

.month_choose a:hover {
    text-decoration: none;
    color: #0085d0;
}
</style>
</head>

<body>
    <input type="hidden" id="stnm" name="stnm" value="${stbprpB.stnm}"/>
    <input type="hidden" id="stcd" name="stcd" value="${stbprpB.stcd}"/>
    <input type="hidden" id="sttp" name="sttp" value="${stbprpB.sttp}"/>
	<ul class="tabbtn" id="normaltab" style="overflow: auto;">
		<li class="current"><a href="javascript:void(0)"><fmt:message key="realTime"/></a></li>
		<li><a href="javascript:void(0)"><fmt:message key="factorOverview"/></a></li>
		<c:if test="${stbprpB.iscamera==1}">
		   <li><a href="javascript:void(0)"><fmt:message key="imageMonitor"/></a></li>
		</c:if>
		
		<!-- <li><a href="javascript:void(0)">监控视频</a></li> -->
		<c:forEach var="item" items="${factorlist}" varStatus="vs">
			<li><a href="javascript:void(0)" flag="${factorFlaglist[vs.index]}" childFlag="${childFactorMap[factorFlaglist[vs.index]]}">${item}</a></li>
		</c:forEach>
	</ul>
<!-- tabbtn end -->
	<div class="tabcon" id="normalcon" style="border-style:none">
		<div class="control-loading" style="height:100%"></div>
		<div class="sublist" style="margin-bottom:30px;"></div>
	</div>
	
<!-- tabcon end -->
</body>
</html>
<script type="text/javascript">
	var currentUrl,currentCall;
	var currentRequest;
	
	$(document).ready(function($) {
		var initIndex = '${initIndex}'
		//默认选项卡切换
		$("#normaltab").tabso({
			cntSelect : "#normalcon",
			//tabEvent : "mouseover",
			tabEvent : "click",
			tabStyle : "normal",
			clickCallBack :getListPage,
			initIndex:initIndex
		});
		
		$(document).on('click', '.month_choose a', function(event) {
			var m = $(this), s = m.siblings("a");
	        m.addClass("active");
	        s.removeClass("active");
		});
	});
	function getListPage(i){
		
		$("#normalcon .control-loading").show();
		$("#normalcon .sublist").hide();
		var stcd = $("#stcd").val();
		var params = {
				stcd:stcd
		};
		var url = "reportDetail/monitorPage.do";
		if(i==0){
			url = "reportDetail/monitorPage.do";
		}else if(i==1){
			url = "reportDetail/monitorFactorPage.do";
		}else if(i==2 && ${stbprpB.iscamera==1}){
			url = "reportDetail/monitorPicturePage.do";
		}/* else if(i>2){
			getFactorPage();
			return;
		} */else{
			getFactorPage();
			return;
		}
		requestUrl(url,params);
	}
	
	function getFactorPage(params){
		if(params==undefined) params={};
		var sttp = $("#sttp").val();
		var fl = $("#normaltab li.current a").attr("flag");
		var chfl = $("#normaltab li.current a").attr("childFlag");
		params['stcd']=$("#stcd").val();
		params['flag']=fl;
		params['childFlag']=chfl;
		params['sttp']=sttp;
		url = "aloneReport/reportPage.do";
		requestUrl(url,params);
	}
	
	function requestUrl(url,params){
		//终止前一个请求，防止冲突
		if(currentRequest) currentRequest.abort();
		currentRequest = $.post(url,params,function(data){
			$("#normalcon .control-loading").hide();
			$("#normalcon .sublist").show();
			$("#normalcon .sublist").html(data);
		});
	}
	
	/**
	 * 设备图片切换
	 */
	function stbprpImgChange(stcd, stnm, sttp){
		$("#normalcon .control-loading").show();
		$("#normalcon .sublist").hide();
		var param = {
				stcd:stcd
		};
		
		$("#stcd").val(stcd);
		$("#stnm").val(stnm);
		$("h2.report-pop-title span").html($.i18n.map['detailInfo'] +"【" + stcd + "】");
		if(sttp==undefined) sttp="";
		$("#sttp").val(sttp);
		var url = currentUrl;//reportDetail/monitorPicturePage.do
		if(currentCall) {
			currentCall();
		} else{
			$.post(url,param,function(data){
				$("#normalcon .control-loading").hide();
				$("#normalcon .sublist").show();
				$("#normalcon .sublist").html(data);
			});
		}
	}

</script>

<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/report.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/dateUtils.js"></script>