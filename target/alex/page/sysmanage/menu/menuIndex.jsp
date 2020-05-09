<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单管理首页</title>
    <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysMenu.js"></script>
  </head>
  
  <body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<li class="remote" onclick="editMenu()"><fmt:message key="edit"/> </li>
			</ul>
			<div class="search">
				<input type="text" value="<fmt:message key="menuName"/>" id="menuName" name="menuName" onfocus='if(this.value == &quot;<fmt:message key="menuName"/>&quot;) this.value=""'; onblur="if(this.value =='') this.value = '<fmt:message key="menuName"/>';" /><a id="btnSearch" onclick="searchMenu()"></a>
			</div>
		</div>
		<div class="device" id="sysMenuList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr style="border-bottom: 1px solid #d0d9de;">
						<th><input type="checkbox" id="checkAll"/></th>
                        <th><fmt:message key="menuCode"/></th>
				 	    <th><fmt:message key="menuName"/></th>
				 	    <th><fmt:message key="menuOrder"/></th>
				 	    <th><fmt:message key="menuLink"/></th>
				 	    <th><fmt:message key="menuIcon"/></th>
				 	    <th><fmt:message key="subMenuMan"/></th>
				 	    <th><fmt:message key="remarks"/></th>
				 	    <th><fmt:message key="updateTime"/></th>
					</tr>
				</thead>
				<tbody id="list-content">
					<c:forEach var="item"  items="${menuList}" varStatus="vs">
				 		 <tr class="${vs.index%2==0?'singular':'double'}">
				             <td><input type="checkbox" id="${item.id}" name="chk" value="${item.id}"></td>
				 			 <td>${item.menucode}</td>
				 			 <td>${item.menuname}</td>
				 			 <td>${item.menuorder}</td>
				 			 <td>${item.menuurl}</td>
				 			 <td>${item.menuicon}</td>
				 			 <td><a href="javascript:void(0)" onclick="menuManager('${item.id}','${item.menuname}','${item.menucode}')"><font color="blue"><fmt:message key="manage"/></font></a></td>
				 			 <td>${item.menuremark}</td>
				 			 <td><fmt:formatDate value="${item.menuupdatetime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				    </tr>
				</c:forEach> 
				</tbody>

			</table>
			<div class="list-page"><div id="pagination"></div></div>
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
