<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预警配置页面</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/alarmConfigure.js"></script>
<style>
<!--
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

.sellquick{
	background-color: #489be2 !important;
    color: #FFF !important;
    border: none !important;
}
-->
</style>
</head>

<body>
	<div class="left">
		<div class="tree" style="padding-top:40px;">
			<ul id="ztree" class="ztree" style="position: absolute;top: 40px; bottom: 5px;width: 232px; overflow-y: auto;overflow-x: auto;"></ul>
		</div>
	</div>
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right" style="top:135px;">
		<div style="height:50px;">
			<div class="operateNew" style="padding-left: 10px;">
		      <div id="search">
			    <input type="text"  style="width:200px;color:#adadad;border:1px solid #d5d5d5;height:19px;margin-top:6px;"  value="${stcd==null?'':stcd}" id="stcd"  onfocus="if(this.value == '<fmt:message key="code"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="code"/>';" />&nbsp; <input
					type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="load()">
			</div>
			</div>
		</div>
		<div id="alarmConfigContent"></div>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#stcd").blur();
		ztreeFun($("#ztree"), "stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=false", configList);
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "247px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
				$(".right").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
				$(".right").css("left", "20px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
		
		$(".quick").click(function(){
			var m = $(this);var s = m.siblings();
			 m.addClass("sellquick");
			 s.removeClass("sellquick");
			
		});
	});
	function configList(node){
	    var aryIds = getParentNodeIdsByTreeId("ztree");
		$(".quick").removeClass("sellquick")
		var params = {
			nodeIds : aryIds.toString(),
			isDevice: node.isDevice
		};
		loadAlarmConfigList(params, false);
	}
	 function showArea(obj){
		 $("div.selDiv").hide();
		 $("#" + $(obj).find("option:selected").attr("area")).show();
	 }
</script>
