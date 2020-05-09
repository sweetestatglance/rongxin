<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>历史报表页面</title>
</head>

<body>
  <!-- ztree -->
	<div class="left" style="top: 115px;">
		<div class="tree" style="position: absolute; top: 0px; bottom: 20px;overflow-x: auto;"><ul id="ztree" class="ztree"></ul></div>
	</div>
	
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right"  style="top: 135px;">
	
		<div style="height:50px;">
			<div class="operateNew" style="padding-left: 10px;">
			
			    <div id="allArea" style="float:left;font-size:14px; margin-right: 10px;">
			    	
			    	<div class="selDiv" id="timeSearchArea" style="float: left;">
						<span style="margin-left:10px;"><fmt:message key="startTime"/>：</span>
						<%-- <input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
							onfocus="WdatePicker({onpicked:vilidBeginTime,isShowClear:false,readOnly:true,startDate:'%y',maxDate:'#F{\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm'})"/> --%>
						<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text" onfocus="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss',maxDate:'#F{\'%y-%M-%d\'}'})"/>
						<span>&nbsp;&nbsp;
						<fmt:message key="endTime"/>：</span> 
						<%-- <input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 23:59" />" type="text" 
						onfocus="var date = limitMonthDate();WdatePicker({isShowClear:false,readOnly:true,startDate:'#F{$dp.$D(\'query_beginTime\',{m:+5})}',minDate:'#F{$dp.$D(\'query_beginTime\')}',dateFmt:'yyyy-MM-dd HH:mm',maxDate:date})"/> --%>
						<input id="query_endTime" class="Wdate"  value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 23:59" />" type="text" onfocus="var date = limitMonthDate();WdatePicker({dateFmt:'yyyy-M-d H:mm:ss',minDate:'#F{$dp.$D(\'query_beginTime\')}',maxDate:date})"/>
					</div>
				</div>
			
				<input type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="historyList()">
				
				<input type="button" class="btn-export" value="<fmt:message key="export"/>" onclick="historyExport(this)">
				
				<div style="float:right;margin-right:30px;height:25px;line-height:25px;">
					<div class="setting" style="cursor: pointer;float: left;" onclick="config()">
						<img src="content/skin/css/images/icon/setting_tools.png" style="vertical-align: sub;">&nbsp;<fmt:message key="configuration"/>
					</div>
				</div>
			</div>
		</div>

		<div id="rightContain"></div>
	</div>
</body>
</html>
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
		
		getGroupTree('history');
	});
	
	/**
	 * 开始时间验证
	 * @param dp
	 * @returns
	 */
	function vilidBeginTime(dp){
		var oldM = dp.cal.oldValue.split('-')[1];
		var newM = dp.cal.getNewDateStr().split('-')[1];
		if(oldM != newM){
			$('#query_endTime').val('');
			query_endTime.focus();
		}else{
			var bT = $dp.$("query_beginTime").value;
			var eT = $dp.$("query_endTime").value;
			if(bT>eT){
				$('#query_endTime').val('');
				query_endTime.focus();
			}
		}
	}
	/**
	 * 获取当前月最大日期
	 */
	function limitMonthDate() {  
	    var DateString;  
	    var beginTime = $dp.$("query_beginTime").value;  
	    var date = new Date();
	    var nowtimes = date.getTime();
	    if (beginTime != "" && beginTime != null) {  
	        var beginDate = new Date(beginTime);  
	        
	        beginDate.setDate(new Date(beginDate.getFullYear(), beginDate.getMonth() + 1, 0).getDate()); //获取此月份的天数  
	        var starttimes = beginDate.getTime();
	        var limitDate=beginDate;
	        if(starttimes>nowtimes) limitDate=date;
	        
	        DateString = limitDate.getFullYear() + '-' + (limitDate.getMonth()+1 ) + '-' + limitDate.getDate() + " 23:59:59";
	    }  
	    return DateString;  
	}
</script>
