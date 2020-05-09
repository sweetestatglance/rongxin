/*************************首页脚本*************************************/

var DateInterval, currTimeLong;
$(function(){

	currTimeLong = parseInt($(".right_home .home_head #currTimeLong").val());
	dateRefresh();
	if(DateInterval != undefined) clearInterval(DateInterval);
	DateInterval = setInterval(dateRefresh, 1000);

})


function dateRefresh(){
	_$D = $(".right_home .home_head span.currTime");
	if(_$D!=null && _$D.length>0){
		_$D.text(new Date(currTimeLong).format('yyyy年MM月dd日  hh:mm:ss'));
		currTimeLong+=1000;
	}else{
		clearInterval(dateRefresh);
	}
}

var loadingOpt = {
		text: $.i18n.map['loading']+'...',
		color: '#65ccea',//
		textColor: '#C3C3C3',
		maskColor: 'rgba(255, 255, 255, 0.8)',//遮罩
		textStyle : {fontSize : 16}
};
$(function(){
	pie1();
	pie2();
})

function pie1(){
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main1'));
	var alarmCount =$("#alarmCount").val();
	myChart.showLoading(loadingOpt);
	$.getJSON("home/getDsflData.do",{alarmCount:alarmCount},function(data){
		// 指定图表的配置项和数据
		option = {
				title : {
					/*  text: '设备信息',*/
					/*subtext: '纯属虚构',*/
					x:'center'
				},
				toolbox: {
					show: true,
					top : 10,
					right : 10,
					feature: {
						dataView: {readOnly: false,show:(isIE8==undefined?true:false)},
						saveAsImage: {name:$.i18n.map['deviceStatus']}
					}
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					left: 'left',
					/*data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']*/
				},
				series : [
				          {
				        	  name: $.i18n.map['deviceStatus'],
				        	  type: 'pie',
				        	  radius : '55%',
				        	  center: ['50%', '45%'],
				        	  data: (function(){  
				        		  var res = [];  
				        		  for (var key in data.dataMap) { 
				        			  res.push({  
				        				  name: key,  
				        				  value: data.dataMap[key]  
				        			  });
				        		  }
				        		  return res; 
				        	  })(),
				        	  itemStyle: {
				        		  emphasis: {
				        			  shadowBlur: 10,
				        			  shadowOffsetX: 0,
				        			  shadowColor: 'rgba(0, 0, 0, 0.5)'
				        		  },
				        		  normal:{
				        			  label:{
				        				  show: true,
				        				  formatter: '{b} : {c} ({d}%)'
				        			  },
				        			  borderWidth:1.5,
				        			  borderType:'solid',
				        			  borderColor:'#FFF'

				        		  }
				        	  }
				          }
				          ],
				        /*  color:['#44c4e7','#7e7e7e','#35cb75','#7cb5ec','#f7a35b'] //
*/				 color:['#d3abfc','#56b3f5','#2ecbcd'] //
		};

		myChart.hideLoading();
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	});
}

function pie2(){
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main2'));
	myChart.showLoading(loadingOpt);
	$.getJSON("home/getAlarmData.do",{},function(data){
		// 指定图表的配置项和数据
		/*	option = {
		    title : {
		        text: '设备信息',
		        subtext: '纯属虚构',
		        x:'center'
		    },
		    toolbox: {
	            show: true,
	            top : 10,
	            right : 10,
	            feature: {
	                dataView: {readOnly: false,show:(isIE8==undefined?true:false)},
	                saveAsImage: {name:$.i18n.map['equipmentAlarmTrends']}
	            }
	        },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: $.i18n.map['equipmentAlarmTrends'],
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '45%'],
		            data: (function(){  
                        var res = [];  
                        for (var key in data.dataMap) { 
                            res.push({  
                                name: key,  
                                value: data.dataMap[key]  
                            });
                        }
                        return res; 
                        })(),
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                },
		                normal:{
		                	 label:{
		                         show: true,
		                         formatter: '{b} : {c} ({d}%)'
		                       },
		                    labelLine :{show:true},
		                	borderWidth:1.5,
		                	borderType:'solid',
		                	borderColor:'#FFF'
		                }
		            }
		        }
		    ],
		    color:['#cb31e5','#35cb75','#09DBF4','#f7a35b','#fa91d1','#fb7653','#4985f2']
		};
		myChart.hideLoading();
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);*/
		option = {
				color: ['#44c4e7'],
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'cross',
						crossStyle: {
							color: '#999'
						}
					}
				},
				toolbox: {
					feature: {
						dataView: {show:(isIE8==undefined?true:false), readOnly: false},
						magicType: {show:(isIE8==undefined?true:false), type: ['line', 'bar']},
						restore: {show:(isIE8==undefined?true:false)},
						saveAsImage: {show:(isIE8==undefined?true:false)}
					}
				},
				legend: {

				},
				xAxis: [
				        {
				        	type: 'category',
				        	data: (function(){  
				        		var res = [];  
				        		for (var key in data.dataMap) { 
				        			res.push({  
				        				name: key,  
				        				value: key 
				        			});
				        		}
				        		return res; 
				        	})(),
				        	axisPointer: {
				        		type: 'shadow'
				        	}
				        }
				        ],
				        yAxis: [
				                {
				                	type: 'value',
				                	name: $.i18n.map['alarmValue'],
				                	min: 0,
				                	max: data.gridLeft+5,
//				                	interval: (function(){
//				                		if(data.gridLeft>10){
//				                			return 5; 
//				                		}else{
//				                			return 1;
//				                		}
//				                	})(),
				                	axisLabel: {
				                		formatter: '{value} '
				                	}
				                }
				                ],
				                series: [

				                         {
				                        	 name:$.i18n.map['alarmValue'],
				                        	 type:'line',

				                        	 data:(function(){  
				                        		 var res = [];  
				                        		 for (var key in data.dataMap) { 
				                        			 res.push({  
				                        				 name: key,  
				                        				 value: data.dataMap[key]  
				                        			 });
				                        		 }
				                        		 return res; 
				                        	 })()
				                         }
				                         ]
		};

		myChart.hideLoading();
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	});
}