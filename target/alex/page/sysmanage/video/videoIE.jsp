<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>视频页面</title>
  </head>
<body>
<div style="position: absolute;top: 50px;left: 0px;right: 0px;bottom:10px;">
<object id="CardAccessor" 
    classid="CLSID:F3063AFC-5FBE-4f21-879F-D7F1938A0606"     
    width="100%" 
    height="100%">
    <PARAM NAME="DvrHost" VALUE="${dvrAdd}">
    <PARAM NAME="ChannelMask" VALUE="${binaryVideochannel}">
    <PARAM NAME="DvrPort" VALUE="${dvrIp}">
    <PARAM NAME="DefaultDvrId" VALUE="${dvrCode}">
    <PARAM NAME="AdminRole" VALUE="${isAdmin}">
    <PARAM NAME="LocalMode" VALUE="0">
    <PARAM NAME="Customer" VALUE="shuili">
</object>
</div>
</body>
<script>
var videoTicket;
var objCard = document.getElementById("CardAccessor");
if (objCard.object==null) {
	 //关闭弹出框
	$("#videoToIEDiv").remove();
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			width:"400",
			content: "四信视频插件未安装，需要安装才能播放视频，安装后请手动重启浏览器!",
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					$.Popup.close(thisPop);
				    window.location.href='${videoUrl}';
				    window.event.returnValue = false;
				}
			}, {
				text: $.i18n.map['cancel'],
				clsName: "homebg popup-cancel",
				handler: function (thisObj) {
					$.Popup.close(thisObj);
				}
			}]
	};
	$.Popup.create(confirmMsg);
	
}else{
	 //alert("已检测到CardAccessor插件！");
}
function printMsg(funcCode,msg){
	//alert(funcCode);
	//alert(msg);
}
CardAccessor.SetCallBackFunc(printMsg); 
 function SetParam(){
	 var objCard = document.getElementById("CardAccessor");
	 if(CardAccessor.object!=null){
		 //objCard.IF_EixtActiveX(1);
		 objCard.PluginExit();
	 }
 }
try {
	if (CardAccessor.GetActiveXVersion() < "2.1.8.0") {
		$("#videoToIEDiv").hide();
		var confirmMsg = {
				title: $.i18n.map['prompt'],
				width:"400",
				content: "检测到新的视频插件，需要进行升级更新吗？(安装后请手动重启浏览器!)",
				tbar: [{
					text: $.i18n.map['determine'],
					clsName: "homebg popup-confirm",
					handler: function (thisPop) {
						$.Popup.close(thisPop);
						SetParam();
						$("#videoToIEDiv").remove();
						window.location.href = '${videoUrl}';
						window.event.returnValue = false;
					}
				}, {
					text: $.i18n.map['cancel'],
					clsName: "homebg popup-cancel",
					handler: function (thisObj) {
						$("#videoToIEDiv").show();
						$.Popup.close(thisObj);
					}
				}]
		};
		$.Popup.create(confirmMsg);
	}
} catch (e) {
	SetParam();
	$("#videoToIEDiv").remove();
	//最早版本没有获取当前版本的方法
	alert("当前视频插件不是最新的，请下载更新");
	window.location.href = '${videoUrl}';
	window.event.returnValue = false;
}
</script>

</html>
