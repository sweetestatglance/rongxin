<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
  <head>
    <title>传感器排序</title>
    	<style type="text/css">
	.Popup_content {
	    width: 100%;
	    height: 317px;
	    padding-top:20px;
	    padding-left: 15px;
	    padding-bottom: 5px;
   }

	.Popup_content_middle {
	    width: 80px;
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
	    line-height: 30px;
	    color: #686969;
	    font-size: 12px;
     }
    .right_content {
	    width: 500px;
	    float: left;
	    padding-top: 5px;
	    padding-bottom: 10px;
	    background: #F1FAFF none repeat scroll 0% 0%;
	   /*  overflow-y: scroll; */
	    height: 270px;
	    overflow-x: hidden;
     }
     .popup_bottom {
	    width: 760px;
	    height: 27px;
	    float: left;
	    text-align: center;
	    padding-top: 8px;
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

	</style>
	 <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysEnterpriseFactor.js"></script>
  </head>
  
<body>
    <input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterpriseid}"></input>
    <div class="right_user" style="left:15px;">
    <font style="color:#2f2f2f;font-size:14px;font-weight:bold">列表顺序如下</font>
		<div class="device" style="top:25px;">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<c:forEach var="item" items="${enterFactorlist}" varStatus="vs">
						   <th>${item.factorname}</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		
		<div style="top:60px;position:relative; width:600px;float:left">
			<div style="top:30px; position: absolute;left:250px;">
			         <img src="${ctx}/content/skin/css/images/up.png"></img>
			</div>
			<div style="top:60px; position: absolute;">
			<font style="color:#2f2f2f;font-size:14px;font-weight:bold">传感器排序操作</font>
			<div class="Popup_content">
			  <div class="Popup_content_middle">
			          <p style="padding-top: 50px; margin-left: 2px; cursor: pointer">
			            <input type="button" class="btn-up" value="上移" id="btnMoveUp" style="font-size:10pt;width:62px">
			         </p>
			          <p style="padding-top: 20px; margin-left: 2px; cursor: pointer">
			            <input type="button" class="btn-down" value="下移" id="btnMoveDown" style="font-size:10pt;width:62px">
			         </p>
		
			  </div>
			  <div class="Popup_content_right">
		          	<select name="right_select" style="border:1px solid #b9baba" id="right_select" multiple="multiple" id="right_select" class="right_content">
		           		<c:forEach var="item"  items="${enterFactorlist}" varStatus="vs">
		                      <option value="${item.factortable}">${item.factorname}</option>
		           		</c:forEach>
		           	</select>
			  </div>
		    </div>
		      <div class="popup_bottom">
		           <input class="homebg popup-confirm" style="margin: 0px" type="button" onclick="saveFactorOrder()" value="确定" style="margin-left: 40px; border: none;">
		      </div>
		    </div>
	    </div>
	    <div style="top:80px;position:relative; width:650px;left:680px;">
	    	<h2 style="padding-bottom:20px;">应用要素配置</h2>
	    	<div style="border:solid 1px #c8c8c8;margin-bottom:40px;padding:5px;line-height:25px;">
		    <c:forEach var="item"  items="${factorlist}" varStatus="vs">  
		    	<span style="margin:5px"><input type="checkbox" <c:forEach var="entitem"  items="${enterFactorlist}" varStatus="vs"><c:if test="${entitem.factortable eq item.tableName }">checked</c:if></c:forEach>   value="${item.tableName}" name="factorlist"/>${item.name}</span>	    	
		    </c:forEach>
		    </div>
		    <div>
		         <input class="homebg popup-confirm" style="margin: 0px" type="button" onclick="saveFactorlist()" value="确定" style="margin-left: 40px; border: none;">
		    </div>
		</div>
    </div>
    <script>
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
