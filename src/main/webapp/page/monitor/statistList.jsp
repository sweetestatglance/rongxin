<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<table cellspacing='0' cellpadding='3' class='list-table'>
	<thead>
		<tr style="border-bottom: 1px solid #d0d9de;">
			<th style="border-right:1px solid #d0d9de" height="35"><fmt:message key="siteName"/></th>
			<c:forEach var="item" items="${enterFactorlist}" varStatus="vs">
				<th>${item.factorname}</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody id="list-content">
		<c:forEach var="item" items="${factorList}" varStatus="vs">
			<tr class="${vs.index%2==0?'singular':'double'}">
				<td><a href="javascript:void(0)"
					onclick="showModule('${item.stcd}')"
					style="font-size: 12px; color: #348AE2;">${item.stcd}</a></td>
				<c:forEach var="factorItem" items="${item.factorMap}"
					varStatus="factorVs">
					<c:forEach var="mapItem" items="${factorItem}" varStatus="efVs">
						<td>${mapItem.value}</td>
					</c:forEach>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="list-page">
	<div id="pagination"></div>
</div>
<script type="text/javascript">
	$(function() {
		var totalPage = ${pagingBean.pageNum};
		var totalRecords = ${pagingBean.totalItems};
		var pageNo = ${pagingBean.pageNo};
		if (!pageNo) {
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
			click : function(n) {
				//分页执行方法
				changePage(n);
				//this.selectPage(n);
				return false;
			}
		});
	});
</script>
