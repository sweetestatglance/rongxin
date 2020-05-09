<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">
<!--
	$(function() {
	   $("#query_stcdName").blur();
	   var userLogin ="<fmt:message key='userLogin' />";
       if(userLogin=="User login"){
    	  $(".rainSearch-open").html("Click<br/>to<br/>Exp");
       }else{
    	  $(".rainSearch-open").html("点<br/>击<br/>展<br/>开"); 
       }
		hideMark();
		mapObj = $.Map.Init();
		var devList = eval("(" + '${devListJson}' + ")");
		resultArray = devList;
		var alarmList = eval("(" + '${alarmListJson}' + ")");
		alarmResultArray = alarmList;
		//console.log('${noticeListJson}'.replaceAll("\r\n", "\\r\\n"));
		var noticeList = eval("(" + '${noticeListJson}' + ")");
		noticeResultArray = noticeList;
		$.Map.SetMarker(mapObj, devList,'${maxPj}');
		sttpChange(null,false);
		//if(isNotEmpty('${rainRange}') && isFilterNode) {
		//	filterNode(devList);
		//}
	/* 	if(showtype != "2d"){ */
			changeShowType(showtype);
			
			var m = $("ul.show-type li[data='" + showtype + "']"), 
			s = m.siblings();
			m.addClass("sell");
			s.removeClass("sell");
	/* 	} */
		if(!showleft){
			$(".leftnav").css("left", "0px");
			$(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
			$(".right_map").css("left", "0px");
		}
		if(!showbottom){
			$(".tab_bottom").css("height","35px");
			$(".shrin").css("bottom","12px")
			$(".shrin").addClass("shrin_transform");
			$(".right_map").css("bottom","35px")
			$(".tree").css("bottom","35px")
		}
		if(showfull){
			$(".right_map").css("z-index","15");
			$(".right_map").css("bottom","0px");
			$(".right_map").css("left","0px");
			$(".right_map").css("top","0px").find("div.search_div a span").text("退出全屏");;
		}
		changeLeftNav();
	})
//-->
</script>

<input type="hidden" id="isSum" value="${isSum}"/>
<div class="right_map" style="bottom: 35px;">
	<div class="search">
		<div class="search_div" style="margin-left:2%">
			<fmt:message key="siteType"/>：
			
			<select id="sttp" onchange="sttpChange(this.value)" class="dropdown" style="height:23px">
					<option value=""><fmt:message key="all"/></option>
					<option value="3" ${sttp=='3'?'selected':''}><fmt:message key="sttp3"/></option>
					<option value="0" ${sttp=='0'?'selected':''}><fmt:message key="sttp0"/></option>
					<option value="1" ${sttp=='1'?'selected':''}><fmt:message key="sttp1"/></option>
					<option value="2" ${sttp=='2'?'selected':''}><fmt:message key="sttp2"/></option>
					<option value="4" ${sttp=='4'?'selected':''}><fmt:message key="sttp4"/></option>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" style="color:#adadad;width:148px; -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius:3px; height:19px; border:1px solid #d5d5d5"  id="query_stcdName" value="${query_stcdName==null||query_stcdName=='' ? '':query_stcdName}" onfocus="if(this.value == '<fmt:message key="codeOrName"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="codeOrName"/>';" />
			<input name="" type="button" class="btn-search" onclick="loadMapInfo()" value="<fmt:message key="query"/>" style="height: 22px;line-height: 22px;"/>
		</div>
		<div class="search_div" style="text-align:right; margin-right:2%">
			<a href="javascript:void(0)" onclick="mapFullScreen()" style="color: #565656;font-size: 12px;">
				<img src="content/skin/css/images/map/screen.png" width="13" height="13" style="margin-right: 5px;" />
				<span><fmt:message key="fullScreen"/></span></a>
		</div>
	</div>
	<div class="map_content">
	
		<ul class="show-type">
		    <li class="sell" data="list1"><fmt:message key="list"/></li>
			<li data="2d"><fmt:message key="map"/></li>
			<li data="satellite"><fmt:message key="satellite"/></li>
		</ul>
		
		<div id="map">
		<div style="position: absolute;top: 15px;right: 40px;z-index: 10;">
				<button onclick="loadMapInfo()" class="refresh">手动刷新</button>
			</div>
		<div class="map-searchLeg" style="width:20px">
			<div class="rain-search-tele"  onclick="changeRainSearch()" style="width: 35px; height: 100%; float: left;cursor:pointer; position: absolute; top: 0px; left: 0px;">
				<div class="rainSearch-open" style="left:10px">
				            点<br/>击<br/>展<br/>开
				</div>
			</div>
			<div class="rain-search" align="center">
			<span class="rainSearch-close xxx" onclick="changeRainSearch()" style="display:none"></span>
				<div class="rain-time" align="left">
					<h2><fmt:message key="timeCondition"/>：</h2>
					<span><fmt:message key="from"/> <input id="query_beginTime" type="text" class="Wdate" value="${query_beginTime}"
						onfocus="WdatePicker({minDate:'%y-%M-01',maxDate:'#F{$dp.$D(\'query_endTime\');}' ||'%y-%M-%ld',readOnly:true})"
						style="width:120px" /> 
						<jsp:include page="../commons/hourSelect.jsp">
							<jsp:param name="inputId" value="query_beginTime_hour" />
							<jsp:param name="index" value="${query_beginTime_hour }" />
						</jsp:include>
					</span> <span><fmt:message key="toZ"/> <input id="query_endTime" type="text" class="Wdate" value="${query_endTime}"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'query_beginTime\');}' ||'%y-%M-01',maxDate:'%y-%M-%ld',readOnly:true})"
						style="width:120px" /> <jsp:include page="../commons/hourSelect.jsp">
							<jsp:param name="inputId" value="query_endTime_hour" />
							<jsp:param name="index" value="${query_endTime_hour==null?23:query_endTime_hour}" />
						</jsp:include>
					</span>
				</div>
				<div class="rain-scope" style="display: none;" align="left">
					<h2><fmt:message key="rainfallRange"/>(mm)：</h2>
					<ul id="rainFallRange">
						<li><input id="rain-0" type="checkbox" value="0" ${fn:contains(rainRange,'0')?'checked':''} /><label
							for="rain-0">0</label></li>
						<li><input id="rain-1" type="checkbox" value="1" ${fn:contains(rainRange,'1')?'checked':''} /><label
							for="rain-1">0.1~2.9</label></li>
						<li><input id="rain-2" type="checkbox" value="2" ${fn:contains(rainRange,'2')?'checked':''} /><label
							for="rain-2">3~9.9</label></li>
						<li><input id="rain-3" type="checkbox" value="3" ${fn:contains(rainRange,'3')?'checked':''} /><label
							for="rain-3">10~19.9</label></li>
						<li><input id="rain-4" type="checkbox" value="4" ${fn:contains(rainRange,'4')?'checked':''} /><label
							for="rain-4">20~49.9</label></li>
						<li><input id="rain-5" type="checkbox" value="5" ${fn:contains(rainRange,'5')?'checked':''} /><label
							for="rain-5">50~99.9</label></li>
						<li><input id="rain-6" type="checkbox" value="6" ${fn:contains(rainRange,'6')?'checked':''} /><label
							for="rain-6">100以上</label></li>
					</ul>
				</div>
				<input type="button" value="<fmt:message key="determine"/>" class="rain-button"
					onclick="loadMapInfo()" />
			</div>
		</div>
		<div class="map-legend">
			<span class="legend-title"><fmt:message key="legend"/></span>
			<ul class="legend-icon">
		        <li class="map-legend-shuiku">
		        	<img src="content/skin/css/images/map/map-icon-1-1.png" width="22" height="28" style="float: left;">
		            <span><fmt:message key="sttp1"/></span>
		        </li>
		        <li class="map-legend-hedao">
		       		<img src="content/skin/css/images/map/map-icon-0-1.png" width="22" height="28" style="float: left;">
		            <span><fmt:message key="sttp0"/></span>
		        </li>
		        <li class="map-legend-yuliang">
		        	<img src="content/skin/css/images/map/map-icon-2-1.png" width="22" height="28" style="float: left;">
		            <span><fmt:message key="sttp2"/></span>
		        </li>
		    </ul>
			<ul class="legend-icon" style="padding-top:15px">
				<li class="map-legend-shuiyuqing">
					<img src="content/skin/css/images/map/map-icon-3-1.png" width="22" height="28" style="float: left;">
		            <span><fmt:message key="sttp3"/></span>
		        </li>
		        <li class="map-legend-shuizhijiance" style="margin-right: 0px;">
		        	<img src="content/skin/css/images/map/map-icon-4-1.png" width="22" height="28" style="float: left;">
		            <span><fmt:message key="sttp4"/></span>
		        </li>
				<li class="map-legend-offline" style="margin-right: 0px;">
					<img src="content/skin/css/images/map/map-legend-offline.png" width="20" height="28" style="float: left;">
					<span><fmt:message key="deviceOffice"/></span>
				</li>
			</ul>
		</div>
	
	</div>
	</div>
	
	<div id="deviceList" style="display:none;position: absolute;top: 45px;bottom:0px;left: 30px;right:35px">
    	<jsp:include page="deviceValueList.jsp"></jsp:include>
    </div>
</div>

<jsp:include page="bottomList.jsp"></jsp:include>

