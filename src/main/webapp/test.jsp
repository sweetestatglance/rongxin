<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>实时监控页</title>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.charts.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.widgets.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.gantt.js"></script>
<script type="text/javascript">
FusionCharts.ready(function () {
    // Create a new instance of FusionCharts for rendering inside an HTML
    // `<div>` element with id `my-chart-container`.
    var myChart = new FusionCharts({
        type: 'Thermometer',
        renderAt: 'chart-tempcontainer',
        width: '180',
        height: '350',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
                "manageresize": "1",
                "lowerlimit": "-40",
                "upperlimit": "120",
                "majortmnumber": "11",
                "majortmcolor": "A4D1FF",
                "minortmcolor": "A4D1FF",
                "majortmwidth": "4",
                "minortmnumber": "3",
                "majortmthickness": "1",
                "basefontcolor": "A4D1FF",
                "decmials": "0",
                "tickmarkdecmials": "0",
                "thmfillcolor": "FF5904",
                "chartleftmargin": "30",
                "charttopmargin": "40",
                "chartbottommargin": "40",
                "numbersuffix": "°",
                "borderthickness": "2",
                "thmbulbradius": "20",
                "gaugeoriginx": "65",
                "showborder": "0"
            },
            "value": "32",
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
                                "radius": "0",
                                "showborder": "0",
                                "borderthickness": "1",
                                "fillcolor": "666666,CCCCCC",
                                "fillalpha": "100",
                                "fillasgradient": "1",
                                "fillangle": "90",
                                "fillpattern": "linear"
                            },
                            {
                                "type": "rectangle",
                                "x": "$chartStartX+5",
                                "y": "$chartStartY+5",
                                "tox": "$chartEndX-5",
                                "toy": "$chartEndY-5",
                                "radius": "0",
                                "fillcolor": "CCCCCC,888888",
                                "fillalpha": "100,100",
                                "fillasgradient": "1",
                                "fillangle": "90",
                                "fillpattern": "linear"
                            },
                            {
                                "type": "rectangle",
                                "x": "$chartStartX+10",
                                "y": "$chartStartY+10",
                                "tox": "$chartEndX-10",
                                "toy": "$chartEndY-10",
                                "radius": "0",
                                "fillcolor": "004F9D"
                            },
                            {
                                "type": "text",
                                "x": "78",
                                "y": "403",
                                "label": "F",
                                "fontsize": "12",
                                "fontcolor": "A4D1FF"
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
        height: '350',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
                "manageresize": "1",
                "upperlimit": "100",
                "lowerlimit": "0",
                "tickmarkgap": "5",
                "numbersuffix": "%",
                
               // "datastreamurl": "/DataProviders/Cylinder.php",
                "refreshinterval": "3",
                "showborder": "0"
            },
            "value": "32"
        }
    });

    // Render the chart.
    myChart2.render();
    
    
    var myChart3 = new FusionCharts({
        type: 'angulargauge',
        renderAt: 'chart-voltagecontainer',
        width: '200',
        height: '200',
        dataFormat: 'json',
        dataSource: {
        	"chart": {
                "manageresize": "1",
                "origw": "250",
                "origh": "250",
                "palette": "4",
                "lowerlimit": "0",
                "upperlimit": "100",
                "gaugestartangle": "220",
                "gaugeendangle": "-40",
                "numbersuffix": "%",
                "bgcolor": "FFFFFF",
                "showborder": "0",
                "basefontcolor": "FFFFFF",
                "gaugeouterradius": "80",
                "gaugeinnerradius": "60",
                "charttopmargin": "10",
                "chartleftmargin": "5",
                "tooltipbgcolor": "AEC0CA",
                "tooltipbordercolor": "FFFFFF",
                "pivotradius": "6",
                "gaugeoriginx": "125",
                "gaugeoriginy": "130",
                "refreshinterval": "5"
            },
            "colorrange": {
                "color": [
                    {
                        "minvalue": "0",
                        "maxvalue": "99.99",
                        "code": "F6BD0F"
                    },
                    {
                        "minvalue": "99.99",
                        "maxvalue": "100",
                        "code": "F6BD0F",
                        "alpha": "0"
                    }
                ]
            },
            "trendpoints": {
                "point": [
                    {
                        "startvalue": "70",
                        "endvalue": "100",
                        "color": "E10000",
                        "radius": "60",
                        "innerradius": "55",
                        "alpha": "70"
                    }
                ]
            },
            "dials": {
                "dial": [
                    {
                        "value": "62",
                        "rearextension": "10",
                        "basewidth": "6"
                    }
                ]
            },
            "annotations": {
                "groups": [
                    {
                        "id": "Grp1",
                        "showbelow": "1",
                        "items": [
                            {
                                "type": "rectangle",
                                "x": "$chartStartX",
                                "y": "$chartStartY",
                                "tox": "$chartEndX",
                                "toy": "$chartEndY",
                                "radius": "10",
                                "fillcolor": "333333,555555,333333",
                                "fillangle": "90"
                            },
                            {
                                "type": "rectangle",
                                "x": "$chartStartX+5",
                                "y": "$chartStartY+5",
                                "tox": "$chartEndX-5",
                                "toy": "$chartEndY-5",
                                "radius": "10",
                                "fillcolor": "777777,C3D0D8,777777",
                                "fillangle": "90"
                            },
                            {
                                "type": "rectangle",
                                "x": "$chartStartX+10",
                                "y": "$chartStartY+10",
                                "tox": "$chartEndX-10",
                                "toy": "$chartEndY-10",
                                "radius": "10",
                                "fillcolor": "333333,ADB0B2,333333",
                                "fillangle": "180"
                            }
                        ]
                    }
                ]
            }
        }
    });

    // Render the chart.
    myChart3.render();
});
</script>
</head>

<body>
   <div  style="float: left;background:#fff;border:1px solid red;width:400px;margin-right: 50px;">
		<div  style="margin:0 auto;width:180px;"> <img src="${ctx}/content/skin/css/images/imgTest/04.jpg">
		</div>
	</div>
	<div style="float: left;background:#fff;border:1px solid #e0dede;width:350px;margin-right: 50px;">
		<div id="chart-tempcontainer" style="margin:0 auto;width:180px;">水温加载中...</div>
	</div>
	<div style="float: left;background:#fff;border:1px solid #e0dede;width:350px;margin-right: 50px;">
		<div id="chart-raincontainer" style="margin:0 auto;width:200px;">雨量加载中...</div>
	</div>
	<div  style="float: left;background:#fff;border:1px solid #e0dede;width:350px;">
		<div id="chart-voltagecontainer" style="margin:0 auto;width:200px;">电压加载中...</div>
	</div>
</body>
</html>
