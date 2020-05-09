<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<style type="text/css">
#mapContainer {
	height: 500px;
	width: 100%;
}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/mapDialog.css">
</head>
<body>
	<input type="hidden" id="addvcdName" value="${addvcdName}">
	<input type="hidden" id="parentAddvcdName" value="${parentAddvcdName}">
	<input type="hidden" id="localLng" value="">
	<input type="hidden" id="localLat" value="">
	<input type="hidden" id="localAddress" value="">
	<div id="mapContainer"></div>
	<!-- 关键字查询 -->
	<div id="searchArea">
		<b><fmt:message key="keyWords"/>：</b> <br> <input type="text" id="keyword" name="keyword" onkeydown='keydown(event)' style="width:350px\9;" /> <input type="button" value="<fmt:message key="query"/>" onclick="keywordSearch()"> <input type="button" value="<fmt:message key="determine"/>" id="returnBtn"
			onclick="returnValue(this)">
		<div id="result1" name="result1"></div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/content/skin/adapters/amap/ZMap.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/amap/dialogMap.js"></script>
</html>
