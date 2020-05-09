<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysRole.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/stAddvcdD.js"></script>
</head>

<body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<li class="add" id="show" onclick="addRole('${enterpriseid}')"><fmt:message key="added"/></li>
				<li class="remote" onclick="editRole('${byenterpriseid}')"><fmt:message key="edit"/></li>
				<li class="del" onclick="delRole()"><fmt:message key="delete"/></li>
			</ul>
			<div class="search">
				<input type="text" value='${query_rolename==null?"":query_rolename}' id="roleName" onfocus='if(this.value == &quot;<fmt:message key="roleName"/>&quot;) this.value = "";' onblur="if(this.value =='') this.value = '<fmt:message key="roleName"/>';" /><a id="btnSearch" onclick="searchRole()"></a>
			</div>
		</div>
		<div class="device" id="sysRoleList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" id="checkAll" /></th>
						<th width="8%"><fmt:message key="roleCode"/></th>
						<th width="10%"><fmt:message key="roleName"/></th>
						<th width="10%"><fmt:message key="roleState"/></th>
						<th width="8%"><fmt:message key="rolePermission"/></th>
						<th width="10%"><fmt:message key="byEnterprise"/></th>
						<th width="10%"><fmt:message key="updateTime"/></th>
						<th width="25%"><fmt:message key="remarks"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${roleList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}" name="${item.rolecode}"  value="${item.id}" /></td>
							<td>${item.rolecode}</td>
							<td>${item.rolename}</td>
							<td><c:choose>
									<c:when test="${item.enabledstate==0}">
										<font color="red"><fmt:message key="disabled"/></font>
									</c:when>
									<c:when test="${item.enabledstate==1}">
											<fmt:message key="enabled"/>
										</c:when>
								</c:choose></td>
							<td><a href="javascript:void(0)" style="color:#122bc1" onclick="setPermissionInfo('${item.id}','${item.enterpriseid}');return false;"><fmt:message key="configuration"/></a></td>
							<td>${enterprise.enterprisename}</td>
							<td><fmt:formatDate value="${item.updatetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${item.roleremark}</td>
							
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
	
	$("#roleName").blur();
	var roleName = $("#roleName").val();
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
			changePage(n,roleName);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>
</body>
</html>
