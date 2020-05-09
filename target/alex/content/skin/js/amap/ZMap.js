/**
 * 地图 api 功能封装
 */
ZMap = {
	mapObj : null,
	mapDiv : '',
	opts : ''
}

/**
 * 创建地图实例
 * @param mapDiv 
 * @param opts
 */
ZMap.createMap = function(mapDiv, opts) {
	if(ZMap.mapObj){
		//ZMap.mapObj.clearOverlay();
	}
	ZMap.mapObj = new AMap.Map(mapDiv,{
		resizeEnable: true,
		layers: [
					new AMap.TileLayer({ tileUrl: "http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil" })
          ]
		//加载热点
		//isHotspot: true
	}); 
	ZMap.mapDiv = mapDiv;
	ZMap.opts = opts;
	if (opts) {
		ZMap.mapObj.setZoom(opts.zoom || 13);
		if (opts.lng && opts.lat) {
			var point = new AMap.LngLat(opts.lng, opts.lat);
			ZMap.mapObj.setZoomAndCenter(zooms, point);
		} else if(opts.cityName){
			ZMap.mapObj.setCity(cityName);
		}
	}
}

/**
 * 获取当前地图实例
 */
ZMap.getMap = function() {
	return ZMap.mapObj;
};

/**
 * 地图控件添加
 */
ZMap.controls = {
	//加载比例尺插件
	addScale : function(opts) {
		ZMap.mapObj.plugin(["AMap.Scale"], function(){		
			ZMap.mapObj.addControl(new AMap.Scale(opts));
		});
	},
	//地图类型切换
	addMapType : function(opts) {
		ZMap.mapObj.plugin(["AMap.MapType"],function(){
			ZMap.mapObj.addControl(new AMap.MapType(opts));
	    });	
		
	},
	//在地图中添加ToolBar插件
	addToolBar : function(opts) { //mobi
		ZMap.mapObj.plugin(["AMap.ToolBar"],function(){		
			ZMap.mapObj.addControl(new AMap.ToolBar(opts));
		});
	}
}
/**
 * 创建点标记实例(默认图标)
 * lng 
 * lat
 * opts 参数数组，包含title, draggable, cursor, raiseOnDrag等
 * title 鼠标经过文字提示
 * draggable 点标记可拖拽 true/false
 * cursor 鼠标悬停点标记时的鼠标样式 pointer/move
 * raiseOnDrag 鼠标拖拽点标记时开启点标记离开地图的效果
 */
ZMap.createMarker = function(lng, lat, opts) {
	var title, draggable, cursor, raiseOnDrag, icon;
	if(opts){
		if(opts.title){
			title = opts.title;
		}
		if(opts.draggable){
			draggable = opts.draggable;
		}
		if(opts.cursor){
			cursor = opts.cursor;
		}
		if(opts.raiseOnDrag){
			raiseOnDrag = opts.raiseOnDrag;
		}
		if(opts.icon){
			icon = opts.icon;
		}
	}
	var marker = new AMap.Marker({				  
		position:new AMap.LngLat(lng, lat),
		icon:icon==undefined?"":icon,
		title:title==undefined?"":title,
		draggable:draggable==undefined?false:draggable,
		cursor:cursor==undefined?'pointer':cursor,
		raiseOnDrag:raiseOnDrag==undefined?false:raiseOnDrag
	});
	marker.setMap(ZMap.mapObj);  //在地图上添加点
	return marker;
};

/**
 * 创建信息窗体
 * info 信息内容[]
 */
ZMap.createInfoWindow = function(info) {
	var inforWindow = new AMap.InfoWindow({                 
		  offset:new AMap.Pixel(0,-23),                 
		  content:info.join("<br>"),
		  autoMove:true, 
		  closeWhenClickMap: true
	});   
	return inforWindow;
}

/**
 * 地图添加监听事件
 * obj 添加监听的对象
 * type 监听的类型
 * callback 监听的回调函数
 */
ZMap.addListener = function(obj, type, callback) {
	var eventListener = AMap.event.addListener(obj, type, function(e){
		callback(e);
	});
	return eventListener;
}

/**
 * 打开信息窗口
 * infoWindow 信息窗口
 * LngLat 经纬度坐标
 */
ZMap.openInforWindow = function(infoWindow,LngLat){
	infoWindow.open(ZMap.mapObj,LngLat);	
}

/**
 * 地图自适应显示
 * overlayList 地图包含的覆盖物Array,默认所有覆盖物
 */
ZMap.setFitView = function(overlayList) {
	var newCenter = ZMap.mapObj.setFitView(overlayList);
}

/**
 * 从地图上移除指定控件
 * obj 
 */
ZMap.removeControl = function(obj) {
	ZMap.mapObj.removeControl(obj);
}

/**
 * 从地图上移除指定对象
 * obj 
 */
ZMap.removeObj = function(obj) {
	obj.setMap(null);
}

/**
 * 清除地图所有覆盖物
 */
ZMap.clearMap = function(){
	ZMap.mapObj.clearMap();
}

/**
 * 清除地图所有信息窗体
 */
ZMap.clearInfoWindow = function(){
	ZMap.mapObj.clearInfoWindow();
}

/**
 * 创建带提示的输入框
 * @param city
 */
ZMap.createAutocomplete = function(city){
    var autoOptions = {
        city: city==undefined?"":city //城市，默认全国
    };
    var auto = new AMap.Autocomplete(autoOptions);
	return auto;
}
