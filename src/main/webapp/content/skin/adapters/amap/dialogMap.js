
//定义全局变量
var key='b6c49003c13af335bfa5f923966cbde1';
var mapDiv = "mapContainer";
var map;
var isEdit = false;

$(function(){
	//加载地图
	loadScript();
});
/**
 * 加载地图
 */
function loadScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://webapi.amap.com/maps?v=1.3&key=" + key +"&callback=init";
	document.body.appendChild(script);
}
/**
 * 异步加载回调函数
 */
function init() {
	ZMap.createMap(mapDiv, {
		zoom: 13
	});
	//初始设置地图中心点
	var cityName = $("#addvcdName").val();
	var parentCityName = $("#parentAddvcdName").val();
	//console.log(ZMap.mapObj.setCity(cityName));
	//下面注释掉，是如果地址为空，定位对应的省份，否知以实际的位置来定位
   //ZMap.mapObj.setCity(cityName);
    
	var returnBtn = $("#returnBtn");
	if(returnBtn.length==0){
		isEdit = true;
	}
	
	//开启鼠标点击创建标注(只能创建一个)可拖拽
	var newM = null;
	ZMap.addListener(ZMap.getMap(),'click',function(e){
		ZMap.mapObj.clearMap();
		newM = ZMap.createMarker(e.lnglat.getLng(),e.lnglat.getLat(),{
			draggable : true,
			cursor : 'move',
			raiseOnDrag : true
		});
		$("#localLng").val(e.lnglat.getLng());
		$("#localLat").val(e.lnglat.getLat());
		getAddressByPoi(e.lnglat.getLng(),e.lnglat.getLat());
		
//		console.log("当前坐标为：" + e.lnglat.getLng(),e.lnglat.getLat());
		//拖拽结束事件监听
		ZMap.addListener(newM,'dragend',function(e){
//			console.log("拖拽后坐标为：" + e.lnglat.getLng(),e.lnglat.getLat());
			$("#localLng").val(e.lnglat.getLng());
			$("#localLat").val(e.lnglat.getLat());
			getAddressByPoi(e.lnglat.getLng(),e.lnglat.getLat());
		});
	});
	
	//编辑页面初始标注
	var devicelng = $("#devicelng").val();
	var devicelat = $("#devicelat").val();
	if(devicelng!=null && devicelng!="" && devicelat!=null && devicelat!=""){
		addmarkerByLngLat(devicelng,devicelat);
		$("#localLng").val(devicelng);
		$("#localLat").val(devicelat);
		$("#localAddress").val($("#stlc").val());
		$("#searchArea #keyword").val($("#stlc").val());
	}else{
		 ZMap.mapObj.setCity(cityName);
	}
	
//	
//	var localLng = $("#localLng").val();
//	var localLat = $("#localLat").val();
//	if(localLng!=null && localLng!="" && localLat!=null && localLat!=""){
//		addmarkerByLngLat(localLng,localLat);
//		$("#returnBtn").attr("onclick","submitLocal()");
//	}
}

/**
 * 根据经纬度获取具体地址
 * @param lng
 * @param lat
 */
function getAddressByPoi(lng,lat){
	var lnglatXY = new AMap.LngLat(lng,lat);
	var MGeocoder;
    //加载地理编码插件
    AMap.service(["AMap.Geocoder"], function() {        
        MGeocoder = new AMap.Geocoder({});
        //逆地理编码
        MGeocoder.getAddress(lnglatXY, function(status, result){
        	if(status === 'complete' && result.info === 'OK'){
        		geocoder_CallBack(result);
        	}
        });
    });
}

/**
 * 逆地理编码回调
 */
function geocoder_CallBack(data) {
    var resultStr = "";
    var poiinfo="";
    var address;
    //返回地址描述
    address = data.regeocode.formattedAddress;
    $("#localAddress").val(address);
    $("#searchArea #keyword").val(address);
    if(isEdit){
		returnValue($("#searchArea"));
	}
}  


/**
 * 输入提示
 */
function autoSearch() {
    var keywords = document.getElementById("keyword").value;
    var auto;
    //加载输入提示插件
    AMap.service(["AMap.Autocomplete"], function() {
	    auto = ZMap.createAutocomplete();
	    //查询成功时返回查询结果
	    if ( keywords.length > 0) {
	        auto.search(keywords, function(status, result){
	        	autocomplete_CallBack(result);
	        });
	    }
	    else {
	        document.getElementById("result1").style.display = "none";
	    }
    });
}

/**
 * 输出输入提示结果的回调函数
 */
function autocomplete_CallBack(data) {
    var resultStr = "";
    var tipArr = data.tips;
    if (tipArr&&tipArr.length>0) {                 
        for (var i = 0; i < tipArr.length; i++) {
            resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
                        + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
                        + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\"" + "data=" + tipArr[i].adcode + ">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
        }
    }
    else  {
        resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
    }
    document.getElementById("result1").curSelect = -1;
    document.getElementById("result1").tipArr = tipArr;
    document.getElementById("result1").innerHTML = resultStr;
    document.getElementById("result1").style.display = "block";
}
 
/**
 * 输入提示框鼠标滑过时的样式
 */
function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip 
    thiss.style.background = '#CAE1FF';
}
 
/**
 * 输入提示框鼠标移出时的样式
 */
function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复 
    thiss.style.background = "";
}
 
/**
 * 从输入提示框中选择关键字并查询
 */
function selectResult(index) {
    if(index<0){
        return;
    }
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("keyword").onpropertychange = null;
        document.getElementById("keyword").onfocus = focus_callback;
    }
    //截取输入提示的关键字部分
    var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");
	var cityCode = document.getElementById("divid" + (index + 1)).getAttribute('data');
    document.getElementById("keyword").value = text;
    document.getElementById("result1").style.display = "none";
    //根据选择的输入提示关键字查询
    ZMap.mapObj.plugin(["AMap.PlaceSearch"], function() {       
        var msearch = new AMap.PlaceSearch();  //构造地点查询类
        AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
		msearch.setCity(cityCode);
        msearch.search(text);  //关键字查询查询
    });
}

function keywordSearch(){
	var keyword = $("#keyword").val();
	 document.getElementById("result1").style.display = "none";
    //根据选择的输入提示关键字查询
    ZMap.mapObj.plugin(["AMap.PlaceSearch"], function() {       
        var msearch = new AMap.PlaceSearch();  //构造地点查询类
        AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
        msearch.search(keyword);  //关键字查询查询
    });
}

/**
 * 定位选择输入提示关键字
 */
function focus_callback() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.getElementById("keyword").onpropertychange = autoSearch;
   }
}
 
/**
 * 输出关键字查询结果的回调函数
 */
function placeSearch_CallBack(data) {
    //清空地图上的InfoWindow和Marker
    windowsArr = [];
    marker     = [];
    ZMap.mapObj.clearMap();
    var resultStr1 = "";
    var poiArr = data.poiList.pois;
    var resultCount = poiArr.length;
    for (var i = 0; i < resultCount; i++) {
    	if(i==0){
    		resultStr1 += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h3>";
    		resultStr1 += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
    		addmarker(i, poiArr[i]);
    		$("#localLng").val(poiArr[i].location.getLng());
			$("#localLat").val(poiArr[i].location.getLat());
			getAddressByPoi(poiArr[i].location.getLng(),poiArr[i].location.getLat());
    	}
    }
    ZMap.mapObj.setFitView();
}
 
/**
 * 鼠标滑过查询结果改变背景样式，根据id打开信息窗体
 */
function openMarkerTipById1(pointid, thiss) {
    thiss.style.background = '#CAE1FF';
    windowsArr[pointid].open(ZMap.mapObj, marker[pointid]);
}

/**
 * 根据经纬度添加标注
 */
function addmarkerByLngLat(lng, lat) {
    var markerOption = {
        map:ZMap.mapObj,
        draggable : true,
		cursor : 'move',
		raiseOnDrag : true,
        //icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
        position:new AMap.LngLat(lng, lat)
    };
    var mar2 = new AMap.Marker(markerOption);         
    ZMap.addListener(mar2,'dragend',function(e){
//		console.log("拖拽后坐标为：" + e.lnglat.getLng(),e.lnglat.getLat());
		$("#localLng").val(e.lnglat.getLng());
		$("#localLat").val(e.lnglat.getLat());
		getAddressByPoi(e.lnglat.getLng(),e.lnglat.getLat());
	});
    ZMap.mapObj.setFitView();
    ZMap.mapObj.setZoom(9);
}

/**
 * 添加查询结果的marker&infowindow   
 */
function addmarker(i, d) {
    var lngX = d.location.getLng();
    var latY = d.location.getLat();
    var markerOption = {
        map:ZMap.mapObj,
        draggable : true,
		cursor : 'move',
		raiseOnDrag : true,
        //icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
        position:new AMap.LngLat(lngX, latY)
    };
    var mar = new AMap.Marker(markerOption);         
    marker.push(new AMap.LngLat(lngX, latY));
    ZMap.addListener(mar,'dragend',function(e){
//		console.log("拖拽后坐标为：" + e.lnglat.getLng(),e.lnglat.getLat());
		$("#localLng").val(e.lnglat.getLng());
		$("#localLat").val(e.lnglat.getLat());
		getAddressByPoi(e.lnglat.getLng(),e.lnglat.getLat());
	});
    
//    var infoWindow = new AMap.InfoWindow({
//        content:"<h3><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h3>" + TipContents(d.type, d.address, d.tel),
//        size:new AMap.Size(300, 0),
//        autoMove:true, 
//        offset:new AMap.Pixel(0,-30)
//    });
//    windowsArr.push(infoWindow);
//    var aa = function (e) {infoWindow.open(ZMap.mapObj, mar.getPosition());};
//    AMap.event.addListener(mar, "mouseover", aa);
}
 
/**
 * 构造infowindow显示内容
 */
function TipContents(type, address, tel) {  //窗体内容
    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
        type = "暂无";
    }
    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
        address = "暂无";
    }
    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
        tel = "暂无";
    }
    var str = "  地址：" + address + "<br />  电话：" + tel + " <br />  类型：" + type;
    return str;
}

/**
 * 关键字查询输入框发生输入事件时触发
 * @param event
 */
function keydown(event){
    var key = (event||window.event).keyCode;
    var result = document.getElementById("result1")
    var cur = result.curSelect;
    if(key===40){//down
        if(cur + 1 < result.childNodes.length){
            if(result.childNodes[cur]){
                result.childNodes[cur].style.background='';
            }
            result.curSelect=cur+1;
            result.childNodes[cur+1].style.background='#CAE1FF';
            document.getElementById("keyword").value = result.tipArr[cur+1].name;
        }
    }else if(key===38){//up
        if(cur-1>=0){
            if(result.childNodes[cur]){
                result.childNodes[cur].style.background='';
            }
            result.curSelect=cur-1;
            result.childNodes[cur-1].style.background='#CAE1FF';
            document.getElementById("keyword").value = result.tipArr[cur-1].name;
        }
    }else if(key === 13){
        var res = document.getElementById("result1");
		if(res && res['curSelect'] !== -1){
			selectResult(document.getElementById("result1").curSelect);
		}
    }else{
        autoSearch();
    }
}

/**
 * 返回参数
 */
function returnValue(obj){
	var localLng = $("#localLng").val();
	var localLat = $("#localLat").val();
	var localAddress = $("#localAddress").val();
	if(isNotEmpty(localLng) && isNotEmpty(localLat) && isNotEmpty(localAddress)){
		var dialogDiv = $(obj).closest("div[class='popup-box']").parent("div");
		$("#lgtd").html(localLng);
		$("#lttd").html(localLat);
		$("#devicelng").val(localLng);
		$("#devicelat").val(localLat);
		$("#stlc").val(localAddress);
		if($(dialogDiv).attr("id")=="popup_1"){
			//$("#dialog").show();
			$(dialogDiv).remove();
			$("#stlc").focusout();
		}
	}else{
		alert("请选择或查询位置信息!");
	}
	
}
