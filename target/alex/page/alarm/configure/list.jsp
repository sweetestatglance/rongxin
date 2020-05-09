<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
	<div class="device" id="alarmConfigDiv">
		<table cellspacing='0' id="reportTable" cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th width="3%"><input type="checkbox" id="checkAll" /></th>
					<th width="10%"><fmt:message key="stationCode" /></th>
					<th width="15%"><fmt:message key="stationName" /></th>
					<th width="10%"><fmt:message key="siteType" /></th>
					<th width="10%"><fmt:message key="waterLevelVar" /></th>
					<th width="10%"><fmt:message key="rainFallAmp" /></th>
					<th width="8%"><fmt:message key="whetherToOpen" /></th>
					<th width="10%"><fmt:message key="time" /></th>
					<th width="5%"><fmt:message key="operating"/></th>
				</tr>
			</thead>
			<tbody id="list-content">
				<c:choose>
					<c:when test="${fn:length(alarmConfigList)==0}">
						<tr height="40">
							<td colspan="9"><font color="#a8a8a8"> <label style="float:left;padding-left:15px"><fmt:message key="noData" /></label></font></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${alarmConfigList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="id" value="${item.id}" /></td>
								<td>${item.stcd}</td>
								<td>${item.stnm}</td>
								<td><c:choose>
										<c:when test="${item.sttp==0}">
											<fmt:message key="sttp0" />
										</c:when>
										<c:when test="${item.sttp==1}">
											<fmt:message key="sttp1" />
										</c:when>
										<c:when test="${item.sttp==2}">
											<fmt:message key="sttp2" />
										</c:when>
										<c:when test="${item.sttp==3}">
											<fmt:message key="sttp3" />
										</c:when>
										<c:when test="${item.sttp==4}">
											<fmt:message key="sttp4" />
										</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose></td>
								<td>${item.waterranges}</td>
								<td>${item.rainranges}</td>
								<td><c:choose>
										<c:when test="${item.isopen==0}">开启</c:when>
										<c:when test="${item.isopen==1}">关闭</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose></td>
								<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<ul style="width:50px;">
										<li class="alarm" title="短信报警" onclick="smsAlarmSet('${item.stcd}')"></li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

		<div class="list-page">
			<div id="pagination"></div>
		</div>
	</div>
</body>
</html>
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
