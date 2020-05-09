<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统公告首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysAnnounce.js"></script>

<style type="text/css">

.unread {
    font-weight: 700;
}
</style>
</head>

<body>
	<input type="hidden" id="isopt" value="${isopt}">
	<div class="right_user" style="left:15px;${isopt==false?'top:100px':''}" >
		<div class="operate" style="padding-top:10px">
			<c:if test="${isopt==true }">
				<ul style="margin-top:0px">
					<li class="add" id="show" onclick="addAnounce()"><fmt:message key="added"/></li>
					<li class="del" onclick="delAnounce()"><fmt:message key="delete"/></li>
				</ul>
			</c:if>
			
			<div class="" style="text-align: left;margin:-1px 0 0 10px;">
			
				<span><fmt:message key="noticeState"/>:</span>
				<select id="isRead" onchange="loadAnnounceList()" class="dropdown" style="width: 80px;">
					<option value=""><fmt:message key="all"/></option>
					<option value="0" ${isRead==0?'selected':'' }><fmt:message key="unread"/></option>
					<option value="1" ${isRead==1?'selected':'' }><fmt:message key="read"/></option>
				</select>
			</div>
		</div>
		<div class="device" id="sysAnounceList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th width="3%"><input type="checkbox" id="checkAll" /></th>
						<th width="15%"><fmt:message key="theme"/></th>
						<th width="10%"><fmt:message key="publisher"/></th>
						<th width="15%"><fmt:message key="time"/></th>
						<th width="45%"><fmt:message key="content"/></th>
						<th width="10%"><fmt:message key="operating"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${announceList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'} ${item.isread==0?'unread':''}">
							<td><input type="checkbox" id="${item.id}"  value="${item.id}" /></td>
							<td>${item.title}</td>
							<td>${item.admin}</td>
							<td><fmt:formatDate value="${item.moditime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td title="${item.content}">
								<c:choose>
									<c:when test="${fn:length(item.content)>40}">${fn:substring(item.content,0,40)}...</c:when>
									<c:otherwise>${item.content}</c:otherwise>
								</c:choose>
							</td>
							<td>
								<button class="view" type="button" title='<fmt:message key="toView"/>' onclick="viewAnnounce('${item.id}')"></button>
								<c:if test="${isopt==true }">
									<button class="del" type="button" title='<fmt:message key="delete"/>' onclick="delAnounce('${item.id}')"></button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<div class="list-page">
				<div id="pagination"></div>
			</div>
		</div>
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
</body>
</html>
