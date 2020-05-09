<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>列的显示和隐藏</title>
	<meta http-equiv="pragma" content="no-cache">
	<style type="text/css">
	.Popup_content {
	    /* width: 780px; */
	    height: 317px;
	    float: left;
	    background: #FFF none repeat scroll 0% 0%;
	    padding-left: 15px;
	    padding-bottom: 5px;
   }

	.Popup_content_left {
	    width: 350px;
	    float: left;
	    text-align: left;
	}
	.Popup_content_middle {
	    width: 60px;
	    float: left;
	}
	.Popup_content_right {
	    text-align: left;
	    width: 350px;
	    float: left;
    }
    .Popup_content_right .rightTitle {
	    width: 350px;
	    height: 30px;
	    float: left;
	    background: #FFF none repeat scroll 0% 0%;
	    line-height: 30px;
	    font-size: 12px;
	    color: #4b9ec5;
	    font-weight: bold;
     }
    .left_content {
	    text-align: left;
	    width: 350px;
	    float: left;
	    padding-top: 5px;
	    padding-bottom: 10px;
	    background: #FFF none repeat scroll 0% 0%; /* F1FAFF */
	    overflow-y: scroll;
	    overflow-x: hidden;
	    height: 270px;
	    border: 1px solid #c3c3c3;
    }
    .right_content {
	    width: 350px;
	    float: left;
	    padding-top: 5px;
	    padding-bottom: 10px;
	    background: #FFF none repeat scroll 0% 0%; /* F1FAFF */
	    overflow-y: scroll;
	    height: 270px;
	    overflow-x: hidden;
	    border: 1px solid #c3c3c3;
     }
     .popup_bottom {
	    width: 760px;
	    height: 27px;
	    float: left;
	    text-align: center;
	    padding-top: 12px;
    }
    .right_content ul li a {
	    font-size: 12px;
	    color: #6C6C6C;
	    text-decoration: none;
	    display: block;
	    line-height: 24px;
    }
    .cell  {
        background: #E2F2FB none repeat scroll 0% 0%;
        height: 24px; 
    }
	.Popup_content_left .leftTitle {
	    width: 350px;
	    height: 30px;
	    float: left;
	    background: #FFF none repeat scroll 0% 0%;
        line-height: 30px;
	    font-size: 12px;
	    color: #4b9ec5;
	    font-weight: bold;
	    
	}
	
	.Popup_content input{
		outline: none;
	    background: none;
	    color: #666666;
	    line-height: 22px;
	    vertical-align: middle;
	    margin-top: -2px;
	    margin-bottom: 1px;
	    border: 1px solid #d1d0d0;
	}
	    
	    
	.Popup_content .button {
	    border-radius: 8px;
	    width: 90px;
	    height: 28px;
	    background-color: #2ea6c8;
	    border: none;
	    margin: 8px;
	    font-size: 15px;
	    color: #e6eff5;
	    font-weight: bold;
	}
	</style>

  </head>
  
  <body>
  <div class="Popup_content">
	  <div class="Popup_content_left">
	   <div class="leftTitle"><fmt:message key="notShowColumn"/></div>
	         <select name="left_select" id="left_select" multiple="multiple" class="left_content">
                  <c:forEach var="item"  items="${noViewColumn}" varStatus="vs">
                     <option value="${item.key}"><c:choose><c:when test="${fn:contains(item.key,'td') || item.value.unit==''}">${item.value.name}</c:when><c:otherwise>${item.value.name}(${item.value.unit})</c:otherwise></c:choose></option>
          		  </c:forEach>
	         </select>
	  </div>
	  <div class="Popup_content_middle" style="margin-left:15px;">
	        <p style="padding-top: 40px; "> 
	         	<input type="button" value="&gt;" style="font-size:10pt;width:35px;cursor: pointer" onclick="right()"><br>
	        </p>
	         <p style="padding-top: 20px; ">
	            <input type="button" value="&gt;&gt;" style="font-size:10pt;width:35px;cursor: pointer" onclick="right(true)">
	         </p>
	          <p style="padding-top: 20px; ">
	            <input type="button" value="&lt;" style="font-size:10pt;width:35px;cursor: pointer" onclick="left()">
	         </p>
	         <p style="padding-top: 20px; ">
	            <input type="button" value="&lt;&lt;" style="font-size:10pt;width:35px;cursor: pointer" onclick="left(true)">
	         </p>
	          <p style="padding-top: 20px; ">
	            <input type="button" value="↑" id="btnMoveUp" style="font-size:10pt;width:35px;cursor: pointer">
	         </p>
	          <p style="padding-top: 20px; ">
	            <input type="button" value="↓" id="btnMoveDown" style="font-size:10pt;width:35px;cursor: pointer">
	         </p>

	  </div>
	  <div class="Popup_content_right">
	    <div class="rightTitle"><fmt:message key="showColumn"/></div>
          	<select name="right_select" id="right_select" multiple="multiple" id="right_select" class="right_content">
           		<c:forEach var="item"  items="${viewColumn}" varStatus="vs">
                      <option value="${item.key}"><c:choose><c:when test="${fn:contains(item.key,'td')  || item.value.unit==''}">${item.value.name}</c:when><c:otherwise>${item.value.name}(${item.value.unit})</c:otherwise></c:choose></option>
           		</c:forEach>
           	</select>
	  </div>
      <div class="popup_bottom">
           <input class="button" type="button" onclick="columnViewSubmit()" value="<fmt:message key="determine"/>" style="margin: 0px;border: none;cursor: pointer;">
      </div>
    </div>
      <script type="text/javascript">
      //isAll 是否全部移动
      function left(isAll){
        var os = new Array();
        os = $("#right_select").find("option");
        for(i=0;i<os.length;i++){
          if(isAll){
            //var o = new Option(os[i].text,os[i].value);
            //o.setAttribute("ondblclick", "move(1)");
            var o = "<option value='" + os[i].value + "'>" + os[i].text + "</option>";
            $("#left_select").append(o);
            $("#right_select").find("option").remove();
          }else{
            if(os[i].selected){
              //var o = new Option(os[i].text,os[i].value);
              var o = "<option value='" + os[i].value + "'>" + os[i].text + "</option>";
              $("#left_select").append(o);
              $("#right_select").find("option:selected").remove();
            }
          }
        }
      }
      function right(isAll){
        var os = new Array();
        os = $("#left_select").find("option");
        for(i=0;i<os.length;i++){
          if(isAll){
            //var o = new Option(os[i].text,os[i].value);
            //o.setAttribute("ondblclick", "move(1)");
            var o = "<option value='" + os[i].value + "'>" + os[i].text + "</option>";
            $("#right_select").append(o);
            $("#left_select").find("option").remove();
          }else{
            if(os[i].selected){
              //var o = new Option(os[i].text,os[i].value);
              var o = "<option value='" + os[i].value + "'>" + os[i].text + "</option>";
              $("#right_select").append(o);
              $("#left_select").find("option:selected").remove();
            }
          }
          
        }
      }
      //上下移动
      $("#btnMoveUp,#btnMoveDown").click(function() {   
     	  var $opt = $("#right_select option:selected:first");   
     	  if (!$opt.length) return;   
     	  if (this.id == "btnMoveUp") $opt.prev().before($opt);   
     	  else $opt.next().after($opt);   
     	});  
      $("#left_select").dblclick(function(){
    	  right();
    	});
      $("#right_select").dblclick(function(){
    	  left();
    	});
       </script>
  </body>
</html>
