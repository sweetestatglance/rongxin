<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>


<input type="hidden" id="hidden_query_beginTime" value="${beginTime}">
<input type="hidden" id="hidden_query_endTime" value="${endTime}">

<h4 class="sub-title">趋势图</h4>
<div id="reportFormContainer" class="report-chart"></div>

<script type="text/javascript">
	$(".report-chart").width($($(".report-box")[0]).width()-20);
	
	var selectRow = $("#device-checkbox input[type='checkbox']:checked");
	/* if($("#search-type").val()=="4" && selectRow.length==1){
		$(".search-part2").show();
		var beyongLength = '${beyongLength}';
		$(".search-part2 div h2").text( beyongLength +"天");
	}else{
		$(".search-part2").hide();
	} */
	
	var beyongLength = '${beyongLength}';
	$(".search-part2 div h2").text( beyongLength +"次");
	
	var typ = $("#select-type").find("option:selected").attr("hvalue");
	detailResultMap = eval("(" + '${allDetailMap}' + ")");
	//detailRMap[typ] = detailResultMap;

	var deviceNameStr = '${deviceNameStr}';
	deviceNameStr = deviceNameStr.replace(/,/g, "、");
	var title = '';
	if($("#search-type").val()=="4"){
		
		var selectType = $("#select-type").val();
		var month = $("#searchTime").val().substring($("#searchTime").val().length-2,$("#searchTime").val().length);
		month = "";//no
		if(selectType == "izfl"){
			//月××测点外江平均水位曲线图
			title = month + "月 " + deviceNameStr + " 测点内江平均水位曲线图";
		}else if(selectType == "ozfl"){
			title = month + "月 " + deviceNameStr + " 测点外江平均水位曲线图";
		}else if(selectType == "sfl"){
			title = month + "月 " + deviceNameStr + " 测点盐度平均盐度曲线图";
		}
		
	}
	
	var hightChar = eval("(" + '${hightChar}' + ")");
	var chart = new Highcharts.Chart({
		chart : {

	renderTo : 'reportFormContainer',
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : hightChar.chartType,
			backgroundColor : ''
		},
		colors : [ '#2f7ed8', '#44c4e7', '#8bbc21', '#8F6AF2', '#DD4DE1',
				'#492970', '#a6c96a', '#77a1e5', '#c42525', '#f28f43' ],
		title : {
			text : title,
			style: {
                fontWeight: 'bold'
            }
		},
		xAxis : hightChar.xAxi,
		yAxis : {
			title : {
				text : hightChar.yAxisTitle
			},
			plotLines : [ { //一条延伸到整个绘图区的线，标志着轴中一个特定值。
				color : '#808080',
				//dashStyle: 'Dash', //Dash,Dot,Solid,默认Solid
				width : 1,
				value : 0
			//y轴显示位置
			} ]
		},
		tooltip : {
			valueSuffix : ''
		},
		plotOptions : {
			column : {
				pointPadding : 0.2,
				borderWidth : 0,
				pointWidth : 25
			}
		},
		legend : {
			layout : 'horizontal',
			align : 'center',
			verticalAlign : 'bottom',
			x : -10,
			y : 0
		},
		series : hightChar.dataList
	});
</script>
