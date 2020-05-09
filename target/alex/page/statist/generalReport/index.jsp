<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--
.select {
	float: left;
	height: 24px;
	margin: 0px;
	width: 120px;
	font-size: 12px;
	color: #555;
	vertical-align: middle;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 3px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

-->
</style>
<!-- ztree -->
<div class="left" style="top: 115px;">
	<div class="tree" style="position: absolute; top: 0px; bottom: 20px;overflow-x: auto;"><ul id="ztree" class="ztree"></ul></div>
</div>

<div class="leftnav">
	<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
</div>
<div class="menu_content">
<div class="right" style="top: 135px;">

	<div style="height:50px;">
			<div class="operateNew" style="padding-left: 10px;">
			
				<select id="searchType" class="select" onchange="showArea(this)">
					<option value="1" area="timeSearchArea"><fmt:message  key="byTime"/></option>
					<option value="2" area="daySearchArea"><fmt:message  key="dailyInquiry"/></option>
					<option value="3" area="monthSearchArea"><fmt:message key="monthlyQuery"/></option>
					<!--  <option value="4" area="yearSearchArea"><fmt:message key="yearlyQuery"/></option>-->
				</select>
			    <div id="allArea" style="float:left;font-size:14px; margin-right: 10px;">
			    	<div class="selDiv" id="timeSearchArea" style="float: left;">
					 <span style="margin-left:10px;">起始时间：</span>
					<input class="Wdate query_Time"  id="query_beginTime"  value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
						onfocus="WdatePicker({onpicked:vilidBeginTime,isShowClear:false,readOnly:true,startDate:'%y',maxDate:'#F{\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm'})" />
						<span>&nbsp;&nbsp;结束时间：</span>
						<input class="Wdate query_Time" id="query_endTime" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 23:59" />" type="text"
						onfocus="var dates = limitMonthDate();WdatePicker({isShowClear:false,readOnly:true,startDate:'#F{$dp.$D(\'query_beginTime\',{m:+5})}',minDate:'#F{$dp.$D(\'query_beginTime\')}',dateFmt:'yyyy-MM-dd HH:mm',maxDate:dates})" />
					</div>
		            <div class="selDiv" id="daySearchArea" style="display:none;float:left">
						<span style="margin-left:10px;"><fmt:message key="date"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-{%d}'})" />
					</div>
					<div class="selDiv" id="monthSearchArea" style="display:none;float:left;">
						<span style="margin-left:10px;"><fmt:message key="month"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime1}" pattern="yyyy-MM" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM',maxDate:'%y-{%M-1}'})" />
					</div>
		<%-- 			<div class="selDiv" id="yearSearchArea" style="display:none;float:left;">
						<span style="margin-left:10px;"><fmt:message key="years"/>：</span>
						<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',maxDate:'{%y}'})" />
					</div> --%>
				</div>
			
				<input type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchGeneralReportList()">
				
				<input type="button" class="btn-export" value="<fmt:message key="export"/>" onclick="exportReportForm(this)">
				
				<div style="float:right;margin-right:30px;height:25px;line-height:25px;">
					<div class="setting" style="cursor: pointer;float: left;" onclick="config()">
						<img src="content/skin/css/images/icon/setting_tools.png" style="vertical-align: sub;">&nbsp;<fmt:message key="configuration"/>
					</div>
				</div>
			</div>
		</div>

	<div id="rightContain"></div>
</div>
</div>
<script type="text/javascript">
	$(function() {
		
		$(".leftnav").click(function () {
            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "247px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
                $(".right").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
                $(".right").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
		
		getGroupTree('generalReport');
	});
</script>
