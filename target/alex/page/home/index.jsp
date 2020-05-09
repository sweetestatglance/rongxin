<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="home"/></title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/dateUtils.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/home.js"></script>

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
		<div class="home_head">
			<ul>
				<li><table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7%" style="text-align:left"><img src="${ctx}/content/skin/css/images/home/user.png" width="51" height="62" /></td>
							<td width="93%" style="line-height:30px;text-align:left"><fmt:message key="welcome"/> <span style="font-weight:bold; color:#399db9">${login_user.username }&nbsp;[${login_user.issupermanager?'<fmt:message key="superAdministrator"/>': login_user.sysrole.rolename}]</span><br /> <fmt:message key="todayIs"/> 
							<span class="currTime" style="font-weight:bold; color:#399db9"></span>
							<input type="hidden" id="currTimeLong" value="${currTimeLong}">
							</td>
						</tr>
					</table></li>
				<li style="margin-top:25px; text-align:right"><iframe width="310" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=40&icon=1&num=3"></iframe></li>
			</ul>
		</div>
		<div class="home_content">
			<div class="right-about">
				<div class="about_top">
					<ul>
						<li style="background:#ffa304;" onclick="turnToAlarm()">
							<div class="about_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="todayAlarm"/></div>
							<div class="about_content" style="background:url(${ctx}/content/skin/css/images/home/2.png) no-repeat bottom right">&nbsp;&nbsp;${todayAlarmCount}</div>
						</li>
						<li style="background:#8bbb51" onclick="turnToDevice()">
							<div class="about_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="deviceNum"/></div>
							<div class="about_content" style="background:url(${ctx}/content/skin/css/images/home/3.png) no-repeat bottom right">&nbsp;&nbsp;${stcdCount}</div>
						</li>
						<li style="background:#fb7653" onclick="turnToDevice()">
							<div class="about_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="equipmentOnlineRate"/></div>
							<div class="about_content" style="background:url(${ctx}/content/skin/css/images/home/4.png) no-repeat bottom right">
								&nbsp;&nbsp;${onlineRate}<span style=" font-size:18px;">%</span>
							</div>
						</li>
						<li style="background:#73d0f7" onclick="turnToDevice()">
							<div class="about_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="deviceOnlieNum"/></div>
							<div class="about_content" style="background:url(${ctx}/content/skin/css/images/home/5.png) no-repeat bottom right">&nbsp;&nbsp;${onlineNum}</div>
						</li>
						<li style="background:#66a6fe;" onclick="turnToNotice()">
							<div class="about_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="unreadAnnounceNum"/></div>
							<div class="about_content" style="background:url(${ctx}/content/skin/css/images/home/1.png) no-repeat bottom right">
								&nbsp;&nbsp;${announceNum}<span style=" font-size:18px;"></span>
							</div>
						</li>
						
					</ul>
					<div style="clear:both"></div>
				</div>
				<div class="about_graph">
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
</html>