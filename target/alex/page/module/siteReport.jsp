<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--

.report-search a:hover input{
	background:#3BA3BF;
}
.report-search input[type='button']{
	border: 0;
    background-color: #44C4E7;
    width: 80px;
    height: 30px;
    font-size: 12px;
    color: #FFF;
    /* font-weight: bold; */
    border-radius: 6px;
    font-family: '微软雅黑';
    cursor: pointer;
}
-->
</style>
<!-- 站点报表 -->
<DIV class=tagContent id=tagContent1>
	<div class="report-box">
		<h1 class="sub-title">关键指标</h1>
		<table border="0" cellspacing="0" cellpadding="0"
			class="report-search">
			<tr>
				<td class="search-part1">
					<div style="float: left; margin: 15px 0 0 25px; width: 100%;">
						<span>报送类型：</span> 
						<select id="select-type" class="search-select" onchange="changeReportType(this.value)">
							<c:forEach items="${FactorFlagMap}" var="item">
								<option value="${item.value}" hvalue="${item.key}">${FactorNameMap[item.key]}</option>
							</c:forEach>
						</select> 
						<span>查询类型：</span> 
						<select class="search-select" id="search-type" onchange="changeSearchType(this.value)">
							<option value="3">日报表数据</option>
							<option value="4">月报表数据</option>
						</select> 
						<span>查询时间：</span> 
						<input id="searchTime" type="text" class="Wdate search-input" readonly="readonly" value="${today}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">
					</div>
					<div id="device-checkbox" style="float: left; margin: 15px 0 0 25px; width: 100%;"></div>
					<div style="float: left; margin: 15px 0 0 25px; width: 100%; padding-bottom: 30px; text-align: center;">
						<a href="javascript:void(0)">
							<input type="button" class="btnreport" onclick="searchReportForm()" value="查 询" /> 
						</a>
						<a href="javascript:void(0)">
							<input type="button" class="btnexport" onclick="exportReportForm(this)" value="导 出" style="margin-left: 10px;" />
						</a>
					</div>
				</td>
				<td class="search-part2" style="display:none">
					<div style="float: left; margin: 5px 0 0 25px;">
						<p>超出警戒线次数</p>
						<h2>0次</h2>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="report-box" id="report-content-data">
		<h4 class="sub-title">趋势图</h4>
		<div class="report-chart"></div>
	</div>
	<div style="clear:both"></div>
</DIV>