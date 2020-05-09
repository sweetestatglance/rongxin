<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>监控图片</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/detailPicture.js"></script>
<style type="text/css">
span {
	font-size: 12px;
}
</style>
</head>

<body>
	<div class="popup-content" style="padding:0 0;border-left:0px;border-right:0px;">
		<!-- 	<div style="margin-left:10px;margin-right:10px; width:100%;border:1px solid green;height:800px;height:auto;min-height:500px;margin:0 auto;position:absolute;"> -->
		<div id="picSerach" style="height:40px;margin-top: 10px;">
			<c:if test="${stbprpb.iscamera==1}">
				<div class="search_div" style="margin-left:2%">
					<c:forEach var="i" items="${str}">
						<a href="javascript:void(0)" onclick="channelImg(${i})"><input type="button" name="channel" value="<fmt:message key="channel"/>${i}" class="btn-channel" style="float:left;margin-right: 10px;"></a>
					</c:forEach>
				</div>
				<div class="search_div" style="text-align:right;margin-right:2%;line-height:0px;">
					<span style="margin-left:10px;"><fmt:message key="beginTime"/>：</span> <input id="query_beginTime" value="${beginTime}" class="Wdate" value="" type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" /> <span>&nbsp;&nbsp;<fmt:message key="endTime"/>：</span> <input id="query_endTime" class="Wdate" value="${endTime}"
						type="text" onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'query_beginTime\');}',maxDate:'%y-%M-%d 23:59'})" /> <input type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="serach()">
				</div>
			</c:if>
			<c:if test="${stbprpb.iscamera==0}">
				<font color="#a8a8a8"><label style="float:left;padding-left:15px"><fmt:message key="doesCamera"/></label></font>
			</c:if>
		</div>
		<c:if test="${stbprpb.iscamera==1}">
			<div id="contentImg" style="bottom: 10px; background-color: #FFF;position: absolute; top: 55px; left: 10px; right: 0px;"></div>
		</c:if>
		
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
</body>
</html>
<script>
	$(function () {
		var userLogin ="<fmt:message key='userLogin' />";
	    if(userLogin=="User login"){
	   		$(".device_right_list_div .device_button").css("padding-top","20px");
	    }
	    
		currentUrl="reportDetail/monitorPicturePage.do";
		currentCall=undefined;
	    $(".search_div a").click(function () {
	        var m = $(this), s = m.siblings();
	        m.children("input").addClass("btn-channel-select");
	        m.siblings().children("input").removeClass("btn-channel-select");
	        
	    }); 
	    //默认载入第一个通道按钮
	    if(${num>1} && ${channelNum==1}){
	    	$(".search_div a:first").click();
	    }else{
	    	$("#picSerach .search_div a").eq(${channelNum-1}).click();
	    } 
	});
</script>