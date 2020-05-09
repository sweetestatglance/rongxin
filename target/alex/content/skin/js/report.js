var chart;
var pagingBean,queryTime,searchType,query_month;
var loadingOpt = {
		text: $.i18n.map['loading'] + '...',
		color: '#65ccea',//
		textColor: '#C3C3C3',
	  	maskColor: 'rgba(255, 255, 255, 0.8)',//遮罩
	  	textStyle : {fontSize : 20}
};
$(function(){
	
	// 模式切换
	$(document).on('click', '.popup-content .show-type li', function() {
		var m = $(this), s = m.siblings();
		m.addClass("sell");
		s.removeClass("sell");
		var type = m.attr("data");
		switch (type) {
		case "chart":
			$("#chartsDiv").show();
			$("#detailListDiv").hide();
			break;
		case "list":
			// 地图隐藏 
			$("#detailListDiv").show();
			$("#chartsDiv").hide();
			break;
		}
	});
	
});

function initEcharts(callback){
	chart = echarts.init(document.getElementById('chartsDiv'));//, 'macarons'
	getOneChartsData(callback);
	
	//图表点击事件
	chart.on("click",function(params){
		var name = params.name;		//x轴的信息
		var value = params.value;	//y轴的信息
		var stcd = $("#stcd").val();	//当前设备编码
//		(searchType-1) 1表示按日查询		queryTime 当前的查询时间
		console.log(name+"\n"+value+"\n"+queryTime+"\n"+(searchType-1)+"\n"+stcd+"\n"+"\n");
		if(searchType == 3){	//当前为按月查询,要跳到按日查询
			name = name.substring(0,name.length-1);	//去掉日字	
			queryTime = queryTime + "-"+name
			var params = {
					searchType:searchType-1,
					queryTime:queryTime
			};
		}/*else if(searchType == 4){	//当前为按年查询,要跳到按月查询
			name = name.substring(0,name.length-1);	//去掉月字	
			queryTime = queryTime + "-"+name
			var params = {
					searchType:searchType-1,
					queryTime:queryTime
			};
		}*/
		$("#normalcon .control-loading").show();
		$("#normalcon .sublist").hide();
		getFactorPage(params);	//方法在index.jsp页面中
	});
}

function getOneChartsData(callback){
	chart.showLoading(loadingOpt);
	$.getJSON("aloneReport/getChartsData.do",{
		stcd:$("#stcd").val(),
		flag:$("#normaltab li.current a").attr("flag"),
		childFlag:$("#normaltab li.current a").attr("childFlag"),
		searchType:$("#searchType").val(),
		queryTime:$(".query_Time:visible").val()
		},function(data){
		var charts = data.echart;
		var factorMap = data.factorMap;
		pagingBean=data.pagingBean;
		queryTime=data.queryTime;
		searchType=data.searchType;
		
		var flagName = "";
		var flagJson = factorMap[$("#normaltab li.current a").attr("flag")];
		try {
			var obj = eval("("+flagJson+")");
			flagName = obj.name;
		} catch (e) {
			
		}
		
		var lineopt = {
		        title: {
		        	x: "center",
		            text: $.i18n.map['device'] +'[' + $("#stnm").val() + ']' + flagName + " ",
		        },
		        legend: {
		        	x: 'center', 
					y: 'bottom',  
					padding:20,
		            data:charts.legendDataList
		        },
		        toolbox: {
		            show: true,
		            top : 10,
		            right : 10,
		            feature: {
		                dataZoom: {
		                    yAxisIndex: 'none',
		                    title:{
		                    	zoom:$.i18n.map['dataZoom'],
		                    	back:$.i18n.map['dataZoomBack']
		                    }
		                },
		                magicType: {type: ['line', 'bar'],title:{line:$.i18n.map['magicTypeLine'],bar:$.i18n.map['magicTypeBar']}},
		                restore: {title:$.i18n.map['restore']},
		                saveAsImage: {title:$.i18n.map['saveAsImage']}
		            }
		        },
		        
		        tooltip:{
		            trigger: 'axis'  // 坐标轴触发 也可以item数据点触发
		        },
		        backgroundColor:'#FFF',
		        smooth:true,
		        grid: {
		        	left: charts.gridLeft,
		            right: charts.gridRight,
		            //top: '0',
		            containLabel: true
		        },
		        xAxis: [{
		            type: 'category',  // 有今日、昨日曲线叠加的 用category即字符串 
		            boundaryGap : true,
		            splitLine:{
		                show:false    
		            },
		            data:charts.xAxiDataList,
		            axisLine:{
		            	lineStyle:{
		            		color:'#868686'
		            	}
		            }
		        }],
		        yAxis:  charts.yAxis,
		        color:[
		               '#2ec7c9','#b6a2de','#5ab1ef','#ffb980','#d87a80',
		               '#8d98b3','#e5cf0d','#97b552','#95706d','#dc69aa',
		               '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
		               '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'
		           		],
		        series:charts.series,
		        label:{
		        	normal:{
		        		show:true,
		        		position:'top',
		        		textstyle:{
		        			color:'black'
		        		}
		        	}
		        }
		    };
		chart.hideLoading();
		//lineopt = newline(lineopt, 6, 'yAxis')
		chart.setOption(lineopt);
		if(callback) callback();
	});
	
}

function getTime(type,callback){
	$.getJSON("aloneReport/getTime.do",{type:type},function(data){
		if(data.success){
			$("#query_beginTime").val(data.attributes.beginTime);
			$("#query_endTime").val(data.attributes.endTime);
			//if(callback) callback();
			refreshPage();
		}else{
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['getTimeErr']});
		}
	})
}

function getFactorTime(type,callback){
	$.getJSON("aloneReport/getFactorTime.do",{type:type},function(data){
		if(data.success){
			//if(callback) callback();
			var params = {
					searchType:data.attributes.searchType,
					queryTime:data.attributes.time
			};
			$("#normalcon .control-loading").show();
			$("#normalcon .sublist").hide();
			getFactorPage(params);
		}else{
			$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['getTimeErr']});
		}
	})
}

function refreshPage(){
	
	//日月年
	var query_Time = $(".query_Time:visible").val();
	if (query_Time == "") {
		$.Popup.create({
			title : $.i18n.map['prompt'],
			content : $.i18n.map['pleaseEnterQueryTime']
		});
		return;
	}
	var params = {
			searchType:$("#searchType").val(),
			queryTime:query_Time
	};
	
	$("#normalcon .control-loading").show();
	$("#normalcon .sublist").hide();
	getFactorPage(params);
}

 /**
  * 导出报表
  */
 function exportReportForm(obj){
 	$(obj).addClass("disabled");
 	$(obj).removeAttr("onclick");
 	$(obj).html($.i18n.map['exporting']+"...");
 	var params = {
 			stcd : $("#stcd").val(),
 			stnm : $("#stnm").val(),
 			queryTime : queryTime,
 			searchType:searchType,
 			month:query_month,
 			flag:$("#normaltab li.current a").attr("flag"),
 			childFlag:$("#normaltab li.current a").attr("childFlag")
 	};
 	
// 	console.log(params);
 	$.post("aloneReport/exportExcelData.do", params, function(data){
 		if(data.success){
 			window.location.href="aloneReport/download.do?fileName=" +  encodeURI(encodeURI(data.fileName));
 		}else{
 			alert($.i18n.map['exportFailure']);
 		}
 		$(obj).attr("onclick","exportReportForm(this)");
 		$(obj).html($.i18n.map['reportExport'])
 	},"json")
 }
 
 function showArea(obj){
	 $("div.selDiv").hide();
	 $("#" + $(obj).find("option:selected").attr("area")).show();
 }