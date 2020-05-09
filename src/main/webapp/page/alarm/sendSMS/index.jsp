<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预警短信记录首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/alarmSmsLog.js"></script>
</head>

<body>
	<div class="right_user" style="left:15px;">
		<div class="operate" style="padding-top:5x;padding-left:10px;">
			<div id="search" style="margin-top:0px">
			    <input type="text" style="color:#adadad;width:200px;border:1px solid #d5d5d5;height:19px;"  value="${name==null?'':name}" id="name"  onfocus="if(this.value == '<fmt:message key="alarmName"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="alarmName"/>';" />&nbsp;
			    <input type="text" style="color:#adadad;width:200px;border:1px solid #d5d5d5;height:19px;"  value="${phone==null?'':phone}" id="phone"  onfocus="if(this.value == '<fmt:message key="phone"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="phone"/>';" />&nbsp;  
			    <input type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchSmsLog()">
			    <button type="button" class="btn-reset" onclick="resetSmsLog()" style="height:25px;"><fmt:message key="reset"/></button>
			</div>
		</div>
		<div class="device" id="smsLogist">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" id="checkAll" /></th>
						<th width="15%"><fmt:message key="user"/></th>
						<th width="10%"><fmt:message key="isMobile"/></th>
						<th width="60%"><fmt:message key="content"/></th>
						<th width="10%"><fmt:message key="time"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${smsLogList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}"  value="${item.id}" /></td>
							<td>${item.username}</td>
							<td>${item.phone}</td>
							<td>${item.content}</td>
							<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
	$("#name").blur();
	$("#phone").blur();
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
