<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
  </head>
  <body>
  	  <input id="currentEnterpriseId" value="${enterpriseid}" type="hidden">
	  <input id="currentOrganizationId" value="${byorganizationid}" type="hidden">
	  <input id="currentLevel" value="${level}" type="hidden">
	  <input id="pid" value="${pid}" type="hidden">
		<div class="operate">
			<ul>
				<li class="add" onclick="addUser()"><fmt:message key="added"/></li>
				<li class="remote" onclick="editUser()"><fmt:message key="edit"/></li>
				<li class="move" onclick="moveUser()"><fmt:message key="move"/></li>
				<li class="del" onclick="deleteUser()"><fmt:message key="delete"/></li>
				<li class="remote" onclick="initPwdUser()" style="width:85px;"><fmt:message key="pwdInit"/></li>
			</ul>
			<div class="search">
				<input type="text" value="${query_username==null?'':query_username }" id="query_username" onfocus='if(this.value == &quot;<fmt:message key="userName"/>&quot;) this.value = "";' onblur="if(this.value =='') this.value = '<fmt:message key="userName"/>';" /><a id="btnSearch" onclick="searchUserList()"></a>
			</div>
		</div>
		<div class="device" id="sysUserDiv">
			<table cellspacing='0' cellpadding='3' class='list-table' style=" border:1px solid #d0d9de; border-right:0px; border-bottom:0px;">
				<thead>
					<tr style="border-bottom: 1px solid #d0d9de;">
						<th><input type="checkbox" id="checkAll"/></th>
                        <th><fmt:message key="userCode"/></th>
						<th><fmt:message key="userName"/></th>
						<th><fmt:message key="userState"/></th>
						<th><fmt:message key="role"/></th>
						<th><fmt:message key="byOrganization"/></th>

					</tr>
				</thead>
				<tbody id="list-content">
				  <c:forEach var="item" items="${userList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td><input type="checkbox" id="${item.id}" value="${item.id}" /></td>
						<td>${item.usercode}</td>
						<td>${item.username}</td>
						<td>
							<c:choose>
								<c:when test="${item.enabledstate==0}">
									<font color="red"><fmt:message key="disabled"/></font>
								</c:when>
								<c:when test="${item.enabledstate==1}">
									<fmt:message key="enabled"/>
								</c:when>	
							</c:choose>
						</td>
						<td>
							<a href="javascript:void(0)" style="color:#122bc1" onclick="setRoleInfo('${item.id}','${item.username}','${item.enterpriseid}');return false;">
								<fmt:message key="configuration"/>
							</a>
						</td>
						<td>${item.byorganizationname}</td>
					</tr>

				</c:forEach>
				</tbody>
			</table>
			<div class="list-page"><div id="pagination"></div></div>
		</div>
		<script type="text/javascript">
$(function(){
	$("#query_username").blur();
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
