/**
 ** 预览
 **/
function doPreview(url){
	var imgdiv = "<div id='imgdiv' style='border-radius: 10px 10px 10px 10px;z-index:999;border:3px solid #e6e5e5; background-color:#ffffff;position: absolute;top: 50%;left: 70%;'><div onclick='closeImg()'  style='width:100%;height:30px;text-align:right;font-size:16px;'><img src='content/skin/css/images/image-close.png' style='margin-right:10px;margin-top:10px;'></img></div><div id='img_div' style='margin-left:10px;margin-right:10px;'><img src='"+url+"' style='height:100%;border-radius: 10px 10px 10px 10px;'></img></div></div>";
	$("#imgdiv").remove();
	var _w = parseInt($(window).width());//获取浏览器的宽度
	var _h = parseInt($(window).height());//获取浏览器的宽度
	var realWidth;//真实的宽度
	var realHeight;//真实的高度
	//$("<img/>")这里是创建一个临时的img标签，
	url = "http://192.168.8.251:9090/"+url;
	$("<img/>").attr("src", url).load(function() {
		/*
			如果要获取图片的真实的宽度和高度有三点必须注意
			1、需要创建一个image对象：如这里的$("<img/>")
			2、指定图片的src路径
			3、一定要在图片加载完成后执行如.load()函数里执行
		*/
		realWidth = this.width;
		realHeight = this.height;
		//如果真实的宽度大于浏览器的宽度就按照100%显示
		if(realWidth>=_w || realHeight>=_h){
			$("#imgdiv").css("height","auto");//.css("width","auto")
			var _wWidth = _w/2;
			var _hHeight = _h/2;
			$("#imgdiv").css("margin-top","-"+_hHeight+"px");
			$("#imgdiv").css("margin-left","-"+_wWidth+"px");
			_h = _h-40;
			$("#img_div").css("height",_h+'px');
			$("#imgdiv").width($("#img_div img").width()<=60?840:($("#img_div img").width()+40));
		}
		else{//如果小于浏览器的宽度按照原尺寸显示
			$("#img_div").css("height",realHeight+'px');
			var halfWidth = realWidth/2;
			var halfHeight = realHeight/2+20;
			$("#imgdiv").css("margin-top","-"+halfHeight+"px");
			$("#imgdiv").css("margin-left","-"+halfWidth+"px");
			realHeight = realHeight+40;
			$("#img_div img").css("width","100%");
			$("#imgdiv").css("width",realWidth+'px').css("height",realHeight+'px').css("left","50%");
		}
	});
	$("body").append(imgdiv);
}

function closeImg(){
	$("#imgdiv").remove();
}

/**
 ** 撤销
 **/
function doDropped(id,photoId,stcd){
	showMark();
	fnAjaxRequest(
            "task/photoDropped.do",
            {id:id,photoId:photoId,stcd:stcd},
            "json",
            "POST",
            function (data) {
              	hideMark();
              	if(data=="HttpError"){
              		     $.Popup.create({ title: "提示", content: "中心拒绝连接 Connection refused: connect，请联系管理员!"});
	           		     return false;
		           	}else if(data=="NullError"){
		           		 $.Popup.create({ title: "提示", content: "发送的内容中心无法解析，请联系管理员!"});
		           		 return false;
		           	}else if(data=="SocketError"){
		           		 $.Popup.create({ title: "提示", content: "Connection reset或者Connect reset by peer:Socket write error!"});
		           		 return false;
		           	}else if(data=="SecurityError"){
		           		 $.Popup.create({ title: "提示", content: "中心应答失败!"});
		           		 return false;
		           	}else if(data=="SocketTimeOutError"){
		           		 $.Popup.create({ title: "提示", content: "连接超时!"});
		           		 return false;
		           	}else if(data=="UnknownError"){
		           		 $.Popup.create({ title: "提示", content: "未知错误!"});
		           	     return false;
		           	}else{
		           	   if (data.msg) {
		           		   $.Popup.create({ title: "提示", content: data.msg});
			           }
		           	   loadPhotoList(stcd);
		            }
       });
   }
/**
 * 从新加载
 */
function loadPhotoList(deviceno){
	var url = "devicePhoto/list.do";
	showMark();
	$(".mask").css("z-index","205");
	$.ajax({
		url : url,
		data : {"deviceNo":deviceno},
		success :function(data){
			$(".mask").css("z-index","99");
			hideMarkLoading();
			$("#photoContent").html(data);
	
		}
	});
	
}
/**
 * 照片查询
 */
function photoQuery(){
	var url = "devicePhoto/list.do";
	var beginTime = $("#create_beginComplateTime").val();
	var endTime = $("#create_endComplateTime").val();
	var query_photoType = $("#query_photoType").val();
	var deviceno = $("#deviceno").val();
	showMark();
	$(".mask").css("z-index","205");
	$.ajax({
		url : url,
		data : {"deviceNo":deviceno,"beginTime":beginTime,"endTime":endTime,"query_photoType":query_photoType},
		success :function(data){
			$("#photoContent").html(data);
			$(".mask").css("z-index","99");
			hideMarkLoading();
		}
	});
}
/**
 * 参数重置
 */
function photoRefresh(){
	var deviceno = $("#deviceno").val();
	loadPhotoList(deviceno);
}
/**
 * 人工抓拍图片分页
 */
function changePhotoPage(page){
	var beginTime = $("#create_beginComplateTime").val();
	var endTime = $("#create_endComplateTime").val();
	var photoType = $("#query_photoType").val();
	var deviceno = $("#deviceno").val();
	var param = {"deviceNo":deviceno,"pageNo":page,"beginTime":beginTime,"endTime":endTime,"query_photoType":photoType};
	showMark();
	$.get("devicePhoto/list.do", param, function(data) {
		hideMarkLoading();
		$("#photoContent").html(data);
	});
}
