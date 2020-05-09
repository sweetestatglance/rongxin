<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>任务信息列表</title>
<meta http-equiv="pragma" content="no-cache">
</head>
<body>
	<div id="taskDiv" style="font-size:12px;">
        <input type="hidden" id="ids" name="ids" value="${dids}" ></input>
        <input type="hidden" id="flag" value="${flag}"></input>
		<div style="float: left;padding-left:20px;padding-bottom:10px;">
			<span><fmt:message key="command"/>:</span> <select id="query_rtucode" style="height:25px;width:150px;">
				<option value=""><fmt:message key="allCommand"/></option>
				<c:forEach items="${rtuCodeMap}" var="item">
					<option value="${item.key}" <c:if test="${query_rtucode==item.key}">selected</c:if>><fmt:message key="${item.key}"/></option>
				</c:forEach>
			</select> <span><fmt:message key="releaseTime"/> :</span> <input id="create_beginTime" class="Wdate" type="text" value="${beginTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'create_endTime\')||\'2150-10-01\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span><fmt:message key="toZ"/></span> <input
				id="create_endTime" class="Wdate" type="text" value="${endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'create_beginTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <input class="homebg popup-confirm" type="button"
				onclick="taskSearch('${dids}')" value="<fmt:message key="query"/>">
		</div>
		<div style="height:250px;width:840px;">
			<table id="taskList" cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<th widht="10%"><fmt:message key="stationCode"/></th>
					<th widht="20%"><fmt:message key="command"/></th>
					<th widht="15%"><fmt:message key="createTime"/></th>
					<th widht="15%"><fmt:message key="executionTime"/></th>
					<th widht="15%"><fmt:message key="completionTime"/></th>
					<th widht="10%"><fmt:message key="taskStatus"/></th>
					<th widht="15%"><fmt:message key="detailed"/></th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(taskList)==0}">
							<tr>
								<td colspan="7"><font color="red"><label style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="noData"/></label></font></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${taskList}" varStatus="vs">
								<tr class="${vs.index%2==0?'singular':'double'}">
									<td>${item.stcd}</td>
									<td>${rtuCodeMap[item.taskcode]}</td>
									<td><fmt:formatDate value="${item.taskcreatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${item.taskexecutiontime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${item.taskcomplatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><c:choose>
											<c:when test="${item.taskstatus==0}">
												<font color="#128839"><fmt:message key="taskNotImplemented"/></font>
											</c:when>
											<c:when test="${item.taskstatus==1}">
												<font color="#ea2e7e"><fmt:message key="inExecution"/></font>
											</c:when>
											<c:when test="${item.taskstatus==2}"><fmt:message key="endOfExecution"/></c:when>
											<c:when test="${item.taskstatus==3}">
												<font color="#fe7327"><fmt:message key="taskTimeout"/></font>
											</c:when>
											<c:when test="${item.taskstatus==4}">
												<font color="#ff0000"><fmt:message key="executionFailure"/></font>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose></td>
									<td><a href="javascript:void(0)" onclick="lookDetail('${item.id}')" style="color:blue"><fmt:message key="toView"/></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		    <div class="list-page">
				<div id="taskPagination"></div>
			</div>
		</div>
	</div>
</body>
</html>
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
		pagerid : "taskPagination",
		//当前页
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',
		click : function(n){
			//分页执行方法
			changeTaskPage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>