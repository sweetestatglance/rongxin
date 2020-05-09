<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>通知公告首页</title>
</head>

<body>
	<div class="right_notice">
		<div class="notice_div">
			<div class="notice_div_title">
				<img src="${ctx}/content/skin/css/images/home/icon4.png" width="16" height="14" />&nbsp;停水通告
			</div>
			<div class="notice_div_content">
			    <c:forEach var="item"  items="${withoutList}" varStatus="vs">
					<ul>
						<li class="contet_notice"><a href="http://news.xiamenwater.com/${item.href}" target="_blank">·${item.title}</a></li>
						<li class="time_notice">${item.time}</li>
					</ul>
				</c:forEach>
			</div>
		</div>
		<div class="notice_div">
			<div class="notice_div_title">
				<img src="${ctx}/content/skin/css/images/home/icon4.png" width="16" height="14" />&nbsp;水质通告
			</div>
			<div class="notice_div_content">
			    <c:forEach var="item"  items="${WaterQualityList}" varStatus="vs">
					<ul>
						<li class="contet_notice"><a href="http://news.xiamenwater.com/${item.href}" target="_blank">·${item.title}</a></li>
						<li class="time_notice">${item.time}</li>
					</ul>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
