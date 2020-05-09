<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 二级菜单 -->
<div class="top">
	<div class="top_nav">
		<div style="width:1000px; float:left; padding-left:270px; padding-top:4px;">
			<ul id="two_nav">
			 <c:forEach items="${sysMenuList}" var="item" varStatus="vs">
				<li onclick="showContent('${item.menuurl}','twoContain')" style="cursor: pointer;"><span>${item.menuname}</span></li>
			 </c:forEach>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function () {
	    $("#two_nav  li").click(function () {
	        var m = $(this), s = m.siblings();
	        m.addClass("sell");
	        s.removeClass("sell");
	    }); 
	    var menu = '${menu}';
	    if(isNotEmpty(menu) && $("#two_nav li[onclick*='" + menu + "']").length>0){
	    	$("#two_nav li[onclick*='" + menu + "']").click();
	    }else{
		    //默认载入第一个菜单
		    if(${fn:length(menuList)>1 }){
		    	$("#two_nav li").eq(1).click();
		    }else{
		    	$("#two_nav li:first").click();
		    } 
	    }
	    
	});
</script>