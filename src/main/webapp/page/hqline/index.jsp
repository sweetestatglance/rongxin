<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="home"/></title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/dateUtils.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/page/hqline/hqline.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/page/hqline/layui/layui.all.js"></script>
	<link rel="stylesheet" href="${ctx}/page/hqline/layui/css/layui.css" media="all">
<style type="text/css">
.about_top ul li {
	cursor: pointer;
	
}
.about_top ul li:hover{
	-moz-opacity: 0.8; opacity: 0.8; filter: alpha(opacity=80);
}
</style>

</head>

<body>
    <input type="hidden" id="alarmCount" value="${todayAlarmCount}"></input>
	<div class="right_home">
		<%--铁索站信息--%>
		<div class="home_head" style="height: 120px">
			<jsp:include page="/page/hqline/tiesuo.jsp"></jsp:include>
		</div>
		<div class="home_content">
			<div class="right-about">
				<div class="about_graph" style="margin-top: 50px">
					<div class="graph1">
						<ul>
							<li style="margin-right:1%;">
								<div class="graph_title">&nbsp;&nbsp;<fmt:message key="deviceStatus"/></div>
								<div class="graph_content">
									<%-- <img src="${ctx}/content/skin/css/images/home/image2.jpg" /> --%>
									 <div id="main1" style="width: 100%;height:500px;float:left;margin-top:-30px;"></div>
								</div>
							</li>
							<li style="margin-left:0.5%;">
								<div class="graph_title">&nbsp;&nbsp;<fmt:message key="equipmentAlarmTrends"/></div>
								<div class="graph_content">
									<%-- <img src="${ctx}/content/skin/css/images/home/image2.jpg" /> --%>
									  <div id="main2" style="width: 100%;height:500px;float:left;margin-top:-20px;"></div>
								</div>
							</li>
						</ul>
					</div>
					<div style="clear:both"></div>
				</div>
				<div style="clear:both"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/content/skin/js/monitor.js"></script>
<script type="text/javascript">
	<!--
	$(function() {
		var devList = eval("(" + '${devListJson}' + ")");
		resultArray = devList;
		console.log(devList);
		var alarmList = eval("(" + '${alarmListJson}' + ")");
		alarmResultArray = alarmList;
		var noticeList = eval("(" + '${noticeListJson}' + ")");
		noticeResultArray = noticeList;
	})
	//-->
</script>
</html>