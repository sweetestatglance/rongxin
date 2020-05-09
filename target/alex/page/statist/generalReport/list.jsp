<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=9;IE=8;IE=7;IE=EDGE">

<style>
<!--
#grTableDiv table.list-table tbody td {
    height: 30px;
}

#grTableDiv table.list-table tbody tr.singular:hover { background-color: #ffffff; }
#grTableDiv table.list-table tbody tr.double:hover { background-color: #e6eff5; }


#grTableDiv table.list-table tbody tr a:hover {
    color: #44C4E7;
}
#grTableDiv table.list-table tbody tr a {
    font-size: 12px;
    color: #4ba2be;
}
-->
</style>
<div class="data-grid" id="grTableDiv" style="overflow: auto;">
	<input type="hidden" id="hidden_query_beginTime" value="${query_beginTimeStr}">
	<input type="hidden" id="hidden_query_endTime" value="${query_endTimeStr}">
	<font style="float: right;color:#fb7653;margin-right:10px;margin-bottom: 5px;"><fmt:message key="note"/></font>
	<table cellspacing='0' cellpadding='3' class='list-table' style="min-width: 1300px;">
		<thead>
			<tr>
				<th width="100px" rowspan="2"><fmt:message key="stationName"/></th>
				<c:forEach var="item" items="${viewHead}" varStatus="vs">
					<th colspan="3">${item}</th>
		 		</c:forEach>
				<th width="100px" rowspan="2"><fmt:message key="operating"/></th>
			</tr>
			<tr>
				<c:forEach var="item" items="${viewHead}" varStatus="vs">
					<th style="font-size: 12px; font-weight: 300;width: 35px;"><fmt:message key="small"/></th>
					<th style="font-size: 12px; font-weight: 300;width: 35px;"><fmt:message key="big"/></th>
					<c:choose>
						<c:when test="${item=='时段降水量'}"><th style="font-size: 12px; font-weight: 300;width: 35px;"><fmt:message key="grandTotal"/></th></c:when>
						<c:otherwise><th style="font-size: 12px; font-weight: 300;width: 35px;"><fmt:message key="average"/></th></c:otherwise>
					</c:choose>
		 		</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list)==0}">
					<tr height="40">
						<td colspan="${fn:length(viewHead) * 3 +2}">
							<font color="#a8a8a8">
								<label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${list}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td>${item.stnm}</td>
								
								<c:forEach var="factorItem" items="${sysUserFactorList}" varStatus="dvs">
								   <c:if test="${factorItem.factor!='td11'}">
										<td>
											<c:set var="factor" scope="page" value="min${factorItem.factor}"/> 
											<c:choose>
												<c:when test="${item[factor]!=null}">
													<fmt:formatNumber value="${item[factor]}" maxFractionDigits="3" />
												</c:when>
												<c:otherwise>--</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:set var="factor" scope="page" value="max${factorItem.factor}"/>
											<c:choose>
												<c:when test="${item[factor]!=null}">
													<fmt:formatNumber value="${item[factor]}" maxFractionDigits="3" />
												</c:when>
												<c:otherwise>--</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:set var="factor" scope="page" value="${factorItem.factor}"/>
											<c:choose>
												<c:when test="${item[factor]!=null}">
													<fmt:formatNumber value="${item[factor]}" maxFractionDigits="3" />
												</c:when>
												<c:otherwise>--</c:otherwise>
											</c:choose>
										</td>
									</c:if>
						 		</c:forEach>
						 		
								<td><a href="javascript:void(0)" onclick="showReportDialog('${item.stcd}',1)" style="">查看详情</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="list-page"><div id="pagination"></div></div>
</div>

<script type="text/javascript">
$(function(){
	var totalPage = ${pagingBean.pageNum};
	var totalRecords = ${pagingBean.totalItems};
	var pageNo = ${pagingBean.pageNo};
	if(!pageNo){
		pageNo = 1;
	}
	//生成分页
	$.Pagination.generPageHtml({
		//填充的id
		pagerid : "pagination",
		//当前页
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',
		click : function(n){
			//分页执行方法
			changePage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>