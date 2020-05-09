<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--

.popup-content{
	text-align: left;
}

.imageDiv {
	position: absolute;
	left: 10px;
	top: 10px;
	background-color: #fff;
	height: 230px;
	width: 350px;
	border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px;
	/* padding: 10px; */
}

.listDiv {
	height: 230px;
	left: 372px;
	top: 10px;
	right: 10px;
	overflow-y: auto;
	position: absolute;
	background-color: #ffffff;
	border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px;
}

.chartsDiv {
	position: absolute;
	left: 10px;
	top: 252px;
	right: 10px;
	bottom: 10px;
	/* border-style: solid;
	border-color: rgb(244, 244, 244);
	border-width: 1px; */
	background-color: #ffffff;
    border: 1px solid #d8d8d8;
}

#reportFactorList td,#reportFactorList th{
	height:33px;
}

.listDiv table.list-table tbody tr.singular:hover { background-color: #ffffff; }
.listDiv table.list-table tbody tr.double:hover { background-color: #e6eff5; }

.listDiv table.list-table tbody tr{cursor: pointer;}
.tableTrSel { background-color: #cbe6f7 !important; }

#reportFactorList th a {
    font-size: 14px;
    color: #666666;
}
-->
</style>

<div class="popup-content"
	style="height:auto;padding:0;border-left:0px;border-right:0px;background-color:#f0f5f6;">

	<div class="imageDiv">
		<c:choose>
			<c:when test="${lastest==null}">
						&nbsp;
			</c:when>
			<c:otherwise>
			      <c:choose>
						<c:when test="${stImgMonit.iscamera==1}">
							<c:choose>
								<c:when test="${stImgMonit.imgurl==null}">
									<img src="${ctx}/content/skin/css/images/gap350.png" title="无图片" width="100%" height="100%"></img>
								</c:when>
								<c:otherwise>
									<img src="${stImgMonit.imgurl}" onerror="javascript:this.src='${ctx}/content/skin/css/images/fail350.png';javascript:this.title='图片加载失败'" width="100%" height="100%">
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img src="${ctx}/content/skin/css/images/no-camera350.png" title="未接摄像头" width="100%" height="100%" ></img>
						</c:otherwise>
					</c:choose>
			</c:otherwise>
			</c:choose>
	</div>
	<div class="listDiv">
		<table id="reportFactorList" cellspacing="0" cellpadding="3" class="list-table" style="height: 100%;">
			<thead>
				<tr>
					<th style="" width="20%"><fmt:message key="type"/></th>
					<th width="16%"><fmt:message key="unit"/></th>
					<th width="16%"><fmt:message key="newest"/></th>
					<th width="16%"><a href="javascript:void(0)" onclick="changeType(2)" style="text-decoration: underline;"><fmt:message key="today"/></a></th>
					<th width="16%"><a href="javascript:void(0)" onclick="changeType(3)" style="text-decoration: underline;"><fmt:message key="yesterday"/></a></th>
					<th width="16%"><a href="javascript:void(0)" onclick="changeType(4)" style="text-decoration: underline;"><fmt:message key="thisMonth"/></a></th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${factorFlaglist}" var="item">
				
					<tr class="${item} singular" onclick="showChart('${item}')">
						<td>${factorMap[item].name}</td>
						<td>${factorMap[item].unit}</td>
						<td><c:choose><c:when test="${ lastest[item]==null}">--</c:when><c:otherwise><fmt:formatNumber value="${lastest[item]}" maxFractionDigits="3"/></c:otherwise></c:choose></td>
						<td><c:choose><c:when test="${ today[item]==null}">--</c:when><c:otherwise><fmt:formatNumber value="${today[item]}" maxFractionDigits="3"/></c:otherwise></c:choose></td>
						<td><c:choose><c:when test="${ sevenDay[item]==null}">--</c:when><c:otherwise><fmt:formatNumber value="${sevenDay[item]}" maxFractionDigits="3"/></c:otherwise></c:choose></td>
						<td><c:choose><c:when test="${ thirtyDay[item]==null}">--</c:when><c:otherwise><fmt:formatNumber value="${thirtyDay[item]}" maxFractionDigits="3"/></c:otherwise></c:choose></td>
					</tr>
				
				</c:forEach>
			
			</tbody>
		</table>
	</div>
	<div id="chartsDiv" class="chartsDiv">
	</div>
	
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

<script type="text/javascript">
var chart;
var type = 2,flag;
var loadingOpt = {
		text: $.i18n.map['loading']+'...',
		color: '#65ccea',//
		textColor: '#C3C3C3',
	  	maskColor: 'rgba(255, 255, 255, 0.8)'//遮罩
};
$(function(){
	
	var userLogin ="<fmt:message key='userLogin' />";
    if(userLogin=="User login"){
   		$(".device_right_list_div .device_button").css("padding-top","20px");
    }
    
	currentUrl="reportDetail/monitorFactorPage.do";
	currentCall=undefined;
	$("#reportFactorList tbody tr:even").addClass("singular")
	$("#reportFactorList tbody tr:odd").addClass("double");
	
	if($("#reportFactorList tbody tr").length<3){
		$(".listDiv").height(41+40*$("#reportFactorList tbody tr").length);
		$(".listDiv").css("overflow-y","inherit");
	}
	
	chart = echarts.init(document.getElementById('chartsDiv'));//, 'macarons'
	
	$("#reportFactorList tr").eq(1).click();
});

function changeType(searchType){
	type=searchType;
	getChartsData();
}

function showChart(cls){
	flag = cls;
	var m = $("." + cls), s = m.siblings();
	s.removeClass("tableTrSel");
    m.addClass("tableTrSel");
    getChartsData();
}

function getChartsData(){
	if(type==undefined) type = 2;
	
	var typeStr;
	if(type==2){
		typeStr = $.i18n.map['today'];
	}else if(type==3){
		typeStr = $.i18n.map['yesterday'];
	}else if(type==4){
		typeStr = $.i18n.map['thisMonth'];
	}
	chart.showLoading(loadingOpt);
	$.getJSON("reportDetail/getChartsData.do",{flag:flag,type:type,stcd:$("#stcd").val()},function(data){
		chart.hideLoading();
		if(data.legendDataList.length!=0){
			var lineopt = {
			        title: {
			        	textAlign:'left',
			        	left : 10,
			        	top : 10,
			            text: $.i18n.map['device'] +'[' + $("#stnm").val() + ']' + typeStr + " " + $.i18n.map['curve']
			        },
			        legend: {
			        	x: 'center', 
						y: 'bottom',  
						padding:20,
			            data:data.legendDataList
			        },
			        toolbox: {
			            show: true,
			            top : 10,
			            right : 10,
			            feature: {
			                dataZoom: {
			                    yAxisIndex: 'none'
			                },
			                magicType: {type: ['line', 'bar']},
			                restore: {},
			                saveAsImage: {}
			            }
			        },
			        tooltip:{
			            trigger: 'axis',  // 坐标轴触发 也可以item数据点触发
			            /* formatter:function (params){ // tip的样式
			                var res = '时间 : ' + params[0].name +'<br/>';
			                for (var i = 0, l = params.length; i < l; i++) {
			                    res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
			                }    
			                return res;
			            } */
			        },
			        backgroundColor:'#FFF',
			        smooth:true,
			        grid: {
			            left: data.gridLeft,
			            right: data.gridRight,
			            //bottom: '50',
			            containLabel: true
			        },
			        xAxis: [{
			            type: 'category',  // 有今日、昨日曲线叠加的 用category即字符串 
			            boundaryGap : true,
			            splitLine:{
			                show:false    
			            },
			            data:data.xAxiDataList,
			            axisLine:{
			            	lineStyle:{
			            		color:'#868686'
			            	}
			            }
			        }],
			        yAxis: data.yAxis/* [{
			            name:'',
			            nameLocation:'end',
			            type: 'value',
			            scale:true,
			            axisLabel: {
			                formatter: '{value}'
			            },
			            axisLine:{
			            	lineStyle:{
			            		color:'#868686'
			            	}
			            }
			        }] */,
			        
			        color:[
			               '#2ec7c9','#b6a2de','#5ab1ef','#ffb980','#d87a80',
			               '#8d98b3','#e5cf0d','#97b552','#95706d','#dc69aa',
			               '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
			               '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'
			           		],
			        series:data.series
			    };
			chart.setOption(lineopt);
		}
	});
	
}
</script>
