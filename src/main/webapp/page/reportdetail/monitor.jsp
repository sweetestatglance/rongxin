<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>实时监控页</title>
<script type="text/javascript">
$(function(){
	$("#monitor").height(($(".report-pop").height()) -110);
})
FusionCharts.ready(function () {
    // Create a new instance of FusionCharts for rendering inside an HTML
    // `<div>` element with id `my-chart-container`.
    var myChart = new FusionCharts({
        type: 'Thermometer',
        renderAt: 'chart-tempcontainer',
        width: '130',
        height: '330',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
                "manageresize": "1",
                "showborder": "0",
                "bgcolor": "FFFFFF",
                "bgalpha": "0",
                "lowerlimit": "0",
                "upperlimit": "60",
                "majortmnumber": "11",
                "majortmheight": "2",
                "minortmnumber": "9",
                "decmials": "0",
                "thmfillcolor": "FF5904",
                "chartleftmargin": "20",
                "chartrightmargin": "20",
                "charttopmargin": "40",
                "chartbottommargin": "40",
                "numbersuffix": "°",
                "borderthickness": "2",
                "thmbulbradius": "30",
                "gaugeoriginx": "55"
            },
            "value": '${lastest.ai}',
            "annotations": {
                "groups": [
                    {
                        "showbelow": "1",
                        "items": [
                            {
                                "type": "rectangle",
                                "x": "$chartStartX",
                                "y": "$chartStartY",
                                "tox": "$chartEndX",
                                "toy": "$chartEndY",
                                "radius": "15",
                                "showborder": "0",
                                "borderthickness": "2",
                                "fillcolor": "914800,000000",
                                "fillalpha": "100",
                                "fillasgradient": "1",
                                "fillangle": "45",
                                "fillpattern": "linear"
                            },
                            {
                                "type": "rectangle",
                                "x": "$chartStartX+10",
                                "y": "$chartStartY+10",
                                "tox": "$chartEndX-10",
                                "toy": "$chartEndY-10",
                                "radius": "10",
                                "showborder": "1",
                                "borderthickness": "2",
                                "fillcolor": "FFBC79,FFBA75",
                                "fillalpha": "50",
                                "fillangle": "45"
                            },
                            {
                                "type": "text",
                                "x": "77",
                                "y": "433",
                                "label": "C",
                                "fontsize": "12",
                                "fontcolor": "5D5D5D",
                                "bold": "1"
                            }
                        ]
                    }
                ]
            }
        }
    });

    // Render the chart.
    myChart.render();
    
    
    var myChart2 = new FusionCharts({
        type: 'cylinder',
        renderAt: 'chart-raincontainer',
        width: '200',
        height: '330',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
        		"theme": "fint",
                "manageresize": "1",
                "upperlimit": "100",
                "lowerlimit": "0",
                "tickmarkgap": "5",
                "numbersuffix": "mm",
                "bgcolor": "FFFFFF",
                
               // "datastreamurl": "/DataProviders/Cylinder.php",
                "refreshinterval": "3",
                "showborder": "0"
            },
            "value": '${lastest.pn05}'
        }
    });

    // Render the chart.
    myChart2.render();
    
    var myChart3 = new FusionCharts({
        type: 'vled',
        renderAt: 'chart-llcontainer',
        width: '200',
        height: '330',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
                "manageresize": "1",
                "upperlimit": "40",
                "lowerlimit": "0",
                "numbersuffix": "m³/s",
                "majortmnumber": "11",
                "majortmcolor": "646F8F",
                "majortmheight": "9",
                "minortmnumber": "2",
                "minortmcolor": "646F8F",
                "minortmheight": "3",
                "majortmthickness": "1",
                "decimals": "3",
                "ledgap": "0",
                "ledsize": "1",
                "ledborderthickness": "4",
                "bgcolor": "FFFFFF",
                "showborder": "0"
            },
            "colorrange": {
                "color": [
                    {
                        "minvalue": "0",
                        "maxvalue": "15",
                        "code": "cf0000"
                    },
                    {
                        "minvalue": "15",
                        "maxvalue": "30",
                        "code": "ffcc33"
                    },
                    {
                        "minvalue": "30",
                        "maxvalue": "40",
                        "code": "99cc00"
                    }
                ]
            },
            "value": '${lastest.q}'
        	
        }
    });
    // Render the chart.
    myChart3.render();
    
    
    var myChart4 = new FusionCharts({
        type: 'angulargauge',
        renderAt: 'chart-voltagecontainer',
        width: '350',
        height: '250',
        dataFormat: 'json',
        dataSource: {
        	 "chart": {
                 /* "caption": "Customer Satisfaction Score", */
                /*  "subcaption": "电压："+${lastest.voltage}, */
                 "lowerLimit": "0",
                 "upperLimit": "36",
                /*  "lowerLimitDisplay": "Bad",
                 "upperLimitDisplay": "Good", */
                 "showValue": "1",
                 "valueBelowPivot": "1",
                 "numbersuffix": "V",
                 "bgcolor": "FFFFFF",
                 "theme": "fint"
             },
             "colorRange": {
                 "color": [{
                     "minValue": "0",
                     "maxValue": "12",
                     "code": "#e44a00"
                 }, {
                     "minValue": "12",
                     "maxValue": "24",
                     "code": "#f8bd19"
                 }, {
                     "minValue": "24",
                     "maxValue": "36",
                     "code": "#6baa01"
                 }]
             },
             "dials": {
                 "dial": [{
                     "value": '${lastest.voltage}'
                 }]
             }
        }
    
    });

    // Render the chart.
    myChart4.render();
});
</script>
</head>

<body>
<div id="monitor" style="overflow-x:hidden;overflow-y:scroll">
    <div class="detail_info">
		<div class="detail_monitor_title"><b><fmt:message key="livePictures"/></b></div>
		<div  class="detail_monitor_content" style="width:300px;height:330px;">
		<c:choose>
		<c:when test="${stImgMonit==null}">
					&nbsp;
		</c:when>
		<c:otherwise>
		      <c:choose>
					<c:when test="${stImgMonit.iscamera==1}">
						<c:choose>
							<c:when test="${stImgMonit.imgurl==null}">
								<img src="${ctx}/content/skin/css/images/gap300.png" title="无图片" width="100%" height="100%"></img>
							</c:when>
							<c:otherwise>
								<img src="${stImgMonit.imgurl}"  onerror="javascript:this.src='${ctx}/content/skin/css/images/fail300.png';javascript:this.title='图片加载失败'" width="100%" height="100%">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img src="${ctx}/content/skin/css/images/no-camera300.png" title="未接摄像头" width="100%" height="100%" ></img>
					</c:otherwise>
				</c:choose>
		</c:otherwise>
		</c:choose>
		</div>
		 <div class="detail_monitor_time" style="width:300px;"><fmt:formatDate value="${stImgMonit.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	</div>
	<c:if test="${stsmtaskB.ai==1}">
	<div class="detail_info">
		<div class="detail_monitor_title" style="width:130px;"><b><fmt:message key="temperature"/></b></div>
		<div id="chart-tempcontainer" class="detail_monitor_content" style="width: 100%;text-align: center;"><fmt:message key="loading"/>...</div>
		<div class="detail_monitor_time" style="width:130px;"><fmt:formatDate value="${lastest.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	</div>
	</c:if>
	<c:if test="${stsmtaskB.pn05==1}">
		<div class="detail_info">
			<div class="detail_monitor_title" style="width:200px;"><b><fmt:message key="rainfall"/></b></div>
			<div id="chart-raincontainer" class="detail_monitor_content" style="width: 100%;text-align: center;"><fmt:message key="loading"/>...</div>
		    <div class="detail_monitor_time"><fmt:formatDate value="${lastest.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
		</div>
	</c:if>
	<c:if test="${stsmtaskB.q==1}">
		<div class="detail_info">
			<div class="detail_monitor_title" style="width:200px;"><b><fmt:message key="flow"/></b></div>
			<div id="chart-llcontainer" class="detail_monitor_content" style="width: 100%;text-align: center;"><fmt:message key="loading"/>...</div>
		    <div class="detail_monitor_time"><fmt:formatDate value="${lastest.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
		</div>
	</c:if>
	<c:if test="${stsmtaskB.voltage==1}">
		<div  class="detail_info" >
			<div class="detail_monitor_title" style="width:350px;"><b><fmt:message key="voltage"/></b></div>
			<div id="chart-voltagecontainer"  class="detail_monitor_content" style="width: 100%;text-align: center;"><fmt:message key="loading"/>...</div>
		    <div class="detail_monitor_time" style=""><fmt:formatDate value="${lastest.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
		</div>
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

/**
 * 实时监测切换
 */
$(function(){
	currentUrl="reportDetail/monitorPage.do";
	currentCall=undefined;
	
	var userLogin ="<fmt:message key='userLogin' />";
    if(userLogin=="User login"){
   		$(".device_right_list_div .device_button").css("padding-top","20px");
    }
})

</script>
