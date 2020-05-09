<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="alarmTitle"/></title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/alarm.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/date.js"></script>

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
		<div class="tree" style="">
			<ul id="ztree" class="ztree" style="position: absolute;top: 40px; bottom: 5px;width: 232px; overflow-y: auto;overflow-x: auto;"></ul>
		</div>
	</div>
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right" style="top:135px;">
		<div style="height:50px;">
			<div class="operateNew" style="padding-left: 10px;">
			
				<select id="searchType" class="select" onchange="showArea(this)">
					<option value="2" area="daySearchArea" ><fmt:message  key="dailyInquiry"/></option>
					<option value="3" area="monthSearchArea"><fmt:message key="monthlyQuery"/></option>
					<option value="4" area="yearSearchArea" ><fmt:message key="yearlyQuery"/></option>
				</select>
			    <div id="allArea" style="float:left;font-size:14px; margin-right: 10px;">
		            <div class="selDiv" id="daySearchArea" style="float:left">
						<span style="margin-left:10px;"><fmt:message key="date"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
					</div>
					<div class="selDiv" id="monthSearchArea" style="display:none;float:left;">
						<span style="margin-left:10px;"><fmt:message key="month"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM',maxDate:'%y-{%M}'})" />
					</div>
					<div class="selDiv" id="yearSearchArea" style="display:none;float:left;">
						<span style="margin-left:10px;"><fmt:message key="years"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',maxDate:'{%y}'})" />
					</div>
				</div>
			
				<!-- <button type="button"  style="height:25px;margin-left: 10px;" onclick="todayYesSearch(0)" class="btn btn-xs btn-default dropdown-toggle quick">
					今天
				</button>
			
				<button type="button"  style="height:25px;margin-left: 10px;" onclick="todayYesSearch(1)" class="btn btn-xs btn-default dropdown-toggle quick" >
					昨天
				</button> 
				
				<button type="button"  style="height:25px;margin-left: 10px;margin-right:10px;" onclick="todayYesSearch(2)" class="btn btn-xs btn-default dropdown-toggle quick" >
					前7天
				</button>  -->
				 
				 <fmt:message key="alarmType"/>： 
				   <select id="type"  style="top: 2px \9;" >
					<option value=""> <fmt:message key="whole"/></option>
					<c:forEach var='factorItem' items='${factorNameMap}' varStatus='dvs'>
					    <option value="${factorItem.key}">${factorItem.value.name}报警</option>
					</c:forEach>
				  </select> &nbsp;&nbsp;&nbsp; 
					<input type="text" style="color:#adadad;width:110px;border:1px solid #d5d5d5;height:19px;"  value="${stcd==null?'':stcd}" id="dstcd"  onfocus="if(this.value == '<fmt:message key="code"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="code"/>';" />&nbsp; <input
					type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchAlarm()">&nbsp;&nbsp; <input type="button" class="btn-export" value="<fmt:message key="export"/>" onclick="exportAlarm();">
				&nbsp;
					<button type="button" onclick="settled();" class="btn-warn">
			               <fmt:message key="solve"/>
					</button>
			</div>
		</div>
		<div id="alarmContent"></div>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#dstcd").blur();
		ztreeFun($("#ztree"), "stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=false", alarmList);
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
	function alarmList(node) {
		var aryIds = getParentNodeIdsByTreeId("ztree");
		
		$(".quick").removeClass("sellquick")
		query_beginTime=undefined;
		query_endTime=undefined;
		
		var params = {
			searchType:$("#searchType").val(),
			queryTime:$(".query_Time:visible").val(),
			nodeIds : aryIds.toString(),
			isDevice: node.isDevice
		};
		loadAlarmList(params, false);
	}
	
	 function showArea(obj){
		 $("div.selDiv").hide();
		 $("#" + $(obj).find("option:selected").attr("area")).show();
	 }
</script>
