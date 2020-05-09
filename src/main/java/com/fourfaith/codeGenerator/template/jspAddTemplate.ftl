<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div id="add${entityPackage}Window" style="display: none;">
	<form id="${entityPackage}Form">
	    <input type="hidden" name="id" id="id">
	      <div class="popup">
	        <div class="window">
	          <div class="popup_top">
	             <fmt:message key=""></fmt:message>
	             <div class="close">
		             <img src="../Content/skins/report/images/close.png" width="12"
								height="13" onclick="closeWindow('#add${entityPackage}Window');" />
				 </div>
			  </div>
			  <div class="popup_content">
			   <ul>
			   <#list columns as po>
				    <li>
					     <div class="popup_title">
					     		<fmt:message key=""></fmt:message>:
					     </div>
					     <div class="popup_edit">
					            <input name="${po.fieldName}" id="${po.fieldName}" type="text" class="ck" />
					     </div>
				     </li>
			     </#list>
			  </ul>
			 </div>
			<div class="popup_button">
					<input name="" type="submit" class="set_sure"
						value="<fmt:message key="save"></fmt:message>" /><input name=""
						type="button" class="set-reset" onclick="emptyForm();"
						value="<fmt:message key="reset"></fmt:message>" />
				</div>
	        </div>
	      </div>	 
	</form>
</div>
