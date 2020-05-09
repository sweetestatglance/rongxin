<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预警人员首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/alarmPerson.js"></script>
</head>

<body>
	<div class="right_user" style="left:15px;">
		<div class="operate" style="padding-top:5x">
			<ul style="margin-top:5px">
				<li class="add" id="show" onclick="addAlarmPerson()"><fmt:message key="added"/></li>
				<li class="del" onclick="delAlarmPerson()"><fmt:message key="delete"/></li>
			</ul>
			<div id="search" style="margin-top:0px">
			    <input type="text" style="color:#adadad;width:200px;border:1px solid #d5d5d5;height:19px;"  value="${name==null?'':name}" id="name"  onfocus="if(this.value == '请输入预警用户') this.value = '';" onblur="if(this.value =='') this.value = '请输入预警用户';" />&nbsp;&nbsp;
			    <input type="text" style="color:#adadad;width:200px;border:1px solid #d5d5d5;height:19px;"  value="${phone==null?'':phone}" id="phone"  onfocus="if(this.value == '<fmt:message key="phone"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="phone"/>';" />&nbsp;
			    <input type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchAlarmPerson()">
			    <button type="button" class="btn-reset" onclick="resetSmsPerson()" style="height:25px;"><fmt:message key="reset"/></button>
			</div>
		</div>
		<div class="device" id="sysPersonList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" id="checkAll" /></th>
						<th width="25%"><fmt:message key="warnUser"/></th>
						<th width="20%"><fmt:message key="isMobile"/></th>
						<th width="15%"><fmt:message key="time"/></th>
						<th width="15%"><fmt:message key="operating"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${personList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}"  value="${item.id}" /></td>
							<td>${item.name}</td>
							<td>${item.phone}</td>
							<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
								<button class="edit" type="button" title='<fmt:message key="edit"/>' onclick="editAlarmPerson('${item.id}')"></button>
								<button class="del" type="button" title='<fmt:message key="delete"/>' onclick="delAlarmPerson('${item.id}')"></button>
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
