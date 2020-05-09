<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>统计分析</title>
<meta http-equiv="X-UA-Compatible" content="IE=9;IE=8;IE=7;IE=EDGE">
<link href="${ctx}/js/bootstrap/bootstrap-select.css" rel="stylesheet" />
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/statistic.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/ztreeHistory.js"></script>

<style>
.operate div.setting:hover {
    color: #037390;
}
</style>
</head>

<body>
	<!--二级头部  -->
	<div class="header">
		<jsp:include page="/twoHeader.jsp"></jsp:include>
	</div>
	<!--内容  -->
	<div id="twoContain" ></div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#two_nav li").click(function() {
			var m = $(this), s = m.siblings();
			m.addClass("sell");
			s.removeClass("sell");
		});
		//默认载入第一个菜单
		if (${fn:length(menuList)>1 }) {
			$("#two_nav li").eq(1).click();
		} else {
			$("#two_nav li:first").click();
		}
	});
</script>
