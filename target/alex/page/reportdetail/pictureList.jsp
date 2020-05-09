<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>监控图片列表</title>
<link href="${ctx}/content/skin/css/piccontent/piccontext.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/css/baguettebox/baguetteBox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/baguettebox/baguetteBox.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/piccontent/piccontent.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/detailPicture.js"></script>
<%-- <link href="${ctx}/content/skin/adapters/zoom/zoom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/zoom/zoom.min.js"></script> --%>
 <style>

pre {
    width: 300px;
    margin: 0 auto;
    border: dashed 1px #aaa;
    padding: 12px;
    margin-bottom: 10px;
}

header, footer {
    padding: .2em 0;
    background: #555;
    box-shadow: 0 0 8px #222;
}

footer {
    margin-top: 1em;
}

.content {
    max-width: 1160px;
    margin: 0 auto;
}

.gallery img {
    /* height: 200px; */
    height: 100%;
    width:100%;
    transition: -webkit-filter .4s, filter .4s;
    filter: grayscale(0%);
    -webkit-filter: grayscale(0%);
}

.gallery img:hover {
    filter: grayscale(100%);
    -webkit-filter: grayscale(100%);
}

.gallery a {
   /*  width: 250px;
    height: 200px; */
    width: 140px;
    height: 100px;
    display: inline-block;
    overflow: hidden;
    margin: 4px;
    box-shadow: 0 0 4px -1px #000;
}
    </style>
</head>

  
  <body>
            <!-- 内容 -->
			<div  id="contentList" style="overflow-y:scroll;overflow-x:hidden;/* padding-bottom: 20px; */margin-right:30px;position: absolute; bottom: 0px; top: 0px; left: 10px; right: 0px;">
				<!-- 左边内容 -->
				<div style="float:left;width:62%;">
					<c:if test="${fn:length(leftphotoList)>0}">
						<!--主体内容-->
						<div class="main">
							<!--弹出层开始-->
							<div class="bodymodal"></div>
							<!--播放到第一张图的提示-->
							<div class="firsttop">
								<div class="firsttop_left"></div>
								<div class="firsttop_right"></div>
							</div>
							<!--播放到最后一张图的提示-->
							<div class="endtop">
								<div class="firsttop_left"></div>
								<div class="firsttop_right"></div>
							</div>
							<!--弹出层结束-->
							<!--图片特效内容开始-->
							<div class="piccontext">

								<!--大图展示-->
								<div class="picshow">
									<div class="picshowtop">
										<a href="javascript:void(0)"><img src="" alt="" id="pic1" curindex="0" style="height:400px;/*width:750px;*/" /></a> <a id="preArrow" href="javascript:void(0)" class="contextDiv" title="<fmt:message key="previousPage"/>"><span id="preArrow_A"></span></a> <a id="nextArrow"
											href="javascript:void(0)" class="contextDiv" title="<fmt:message key="nextPage"/>"><span id="nextArrow_A"></span></a>
									</div>
									<div class="picshowtxt">
										<div class="picshowtxt_left">
											<!-- <span>1</span>/<i>12</i> -->
										</div>
										<div class="picshowtxt_right"></div>
									</div>
									<div class="picshowlist">
										<div class="picshowlist_mid">
											<div class="picmidleft">
												<a href="javascript:void(0)" id="preArrow_B"><img src="${ctx}/content/skin/css/test/left1.jpg" alt="<fmt:message key="previousPage"/>" /></a>
											</div>
											<div class="picmidmid">
												<ul>
													<c:forEach var="item" items="${leftphotoList}" varStatus="vs">
														<li><a href="javascript:void(0);"><img src="${item.photourl}" alt="" bigimg="${item.photourl}" text="<fmt:formatDate value="${item.photoseetime}" pattern="MM-dd HH:mm:ss" />" /></a></li>
													</c:forEach>
												</ul>
											</div>
										    <div class="picmidright">
											   <a href="javascript:void(0)" id="nextArrow_B"><img src="${ctx}/content/skin/css/test/right1.jpg" alt="<fmt:message key="nextPage"/>" /></a>
										     </div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${fn:length(leftphotoList)==0}">
				   	 <fmt:message key="noPicture"/>
				 </c:if>
				</div>
				<!--右边内容 -->
				<div style="width:35%;height:100px;float: right;margin-right:10px;">
					<c:if test="${fn:length(photoList)>0}">
						<!-- 代码 开始 -->
						<div class="baguetteBoxOne gallery" >

							<c:forEach var="item" items="${photoList}" varStatus="vs">
								<div style="width:150px;float: left;">
									<a href="${item.photourl}" data-caption="<fmt:formatDate value='${item.photoseetime}' pattern='MM-dd HH:mm:ss' />"><img src="${item.photourl}"></a> <span style="color:black"><fmt:formatDate value="${item.photoseetime}" pattern="MM-dd HH:mm:ss" /></span>
								</div>
							</c:forEach>
						</div>
						<div class="list-page">
							<div id="picPagination"></div>
						</div>
					</c:if>
					
				</div>
			</div>
  </body>
</html>
<script>
	$(function () {
		baguetteBox.run('.baguetteBoxOne', {
	        buttons: false
	    });
		
	    var totalPage = ${pagingBean.pageNum};
		var totalRecords = ${pagingBean.totalItems};
		var pageNo = ${pagingBean.pageNo};
		if(!pageNo){
			pageNo = 1;
		}
		//生成分页
		$.Pagination.generPageHtml({
			//填充的id
			 pagerid : "picPagination",
			//当前页
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			mode : 'click',
			click : function(n){
				//分页执行方法
				changeImgPage(n);
				//this.selectPage(n);
				return false;
			}
		});
	});
</script>
