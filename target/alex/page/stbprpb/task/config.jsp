<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="remoteConfiguration"/></title>
<link href="${ctx}/content/skin/css/config/config.css" rel="stylesheet" />
<%-- <script type="text/javascript" src="${ctx}/content/skin/js/config/switch.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/config/config.js"></script> --%>
<style>
/* .popup-content{
  padding:0px 0;
} */

.bj {
	/* width: 100%; */
	left: 0px;
	right: 0px;
	bottom: 0px;
	top: 0px;
	padding-left:20px;
	padding-right:20px;
}

.set {
	width: 99.9%;
	border: 1px solid #dddcdc;
}

.restart {
	width: 108px;
	height: 72px;
	float: left;
	background: url(content/skin/css/images/restart_button.png) no-repeat center;
	text-align: center;
	line-height: 68px;
	color: #fff;
	border-right: 1px solid #dddcdc;
	border-bottom: 0px;
	border-left: 0px;
	border-top: 0px;
	cursor: pointer;
	outline: none;
	font-size:12px;
}

.restart:hover {
	background: url(content/skin/css/images/restart_hoverbutton.png) no-repeat center
}
</style>
</head>
<body>
	<div class="bj">
		<div class="set">
			<div class="soft_tab clearfix">
				<div style="width:107px; float:left; background:#f9f9f9" id="configDiv">
					<ul class="list7" id="config">
						<li rel="cat_all" onclick="version('${stcd}')"><fmt:message key="versionInfo"/></li>
						<li rel="cat151"  onclick="status('${stcd}')"><fmt:message key="queryStatus"/></li>
						<li rel="cat152"  onclick="distance('${stcd}')"><fmt:message key="remoteConfiguration"/></li>
						<li rel="cat153"  onclick="upgrade('${stcd}')"><fmt:message key="remoteUpgrade"/></li>
						<li rel="cat154"  onclick="captureTask('${stcd}')"><fmt:message key="artificialCapture"/></li>
						<li rel="cat155"  onclick="alarmSetting('${stcd}')" style="display:none"><fmt:message key="alarmSetting"/></li>
						<li rel="cat155"  onclick="taskInfo('${stcd}')"><fmt:message key="taskList"/></li>
					</ul>
					<button type="button" class="restart" onclick="restart('${stcd}')" style="border-bottom: 1px solid #dddcdc;"><fmt:message key="remoteReboot"/></button>
				</div>
				<div class="tab_con clearfix" id="taskContent">
				</div>
			</div>
		</div>
		<div style="height:20px;"></div>
	</div>
</body>
</html>
<script>
$(function () {
	 var userLogin ="<fmt:message key='userLogin' />";
     if(userLogin=="User login"){
    	 $("#configDiv").css("width","157px");
    	 $(".list7 li").css("width","157px");
    	 $(".restart").css("width","157px");
     }
    $(".list7  li").click(function () {
        var m = $(this), s = m.siblings();
        m.addClass("cur");
        m.siblings().removeClass("cur");
    }); 
    $(".list7 li ").eq(0).click();
});
</script>
