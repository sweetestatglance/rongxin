﻿/**
 * 
 * 描述：MapHelper地图加载插件，调用方式：var mapObj = $.Map.Init();
 */
(function ($, window, document) {
    $.extend({
        Map: {
            /* 
				用途：初始化地图 
				输入：id图层Id  
				返回：mapObj实例化后地图对象 
			*/
            Init: function () {
                //初始化地图
                var mapObj = new AMap.Map("map", {
                    layers: [
						new AMap.TileLayer({ tileUrl: "http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil" })
                    ],
                	resizeEnable: true
                });
                $(".amap-logo,.amap-copyright").hide();
                
                $(".amap-copyright").html($.i18n.map['headerTitle']+" &copy 2018-2019 ").css({ "left": "30px", "bottom": "10px", "color": "#666666", "font-family": "微软雅黑" });
                return mapObj;
            },
            /* 
				用途：设置地图显示坐标集合 
				输入：map实例化后地图对象  coordinates坐标集合[{ id: 测站编码, name: 测站名称, addr: 测站地址, code: 测站类型, lat: 纬度, lng: 经度, value: 采集值, unit: 单位, time: 采集时间, video: 摄像头类型（0表示无1表示普通摄像头2表示DVR）, status: 当前状态 }]
				返回：无 
			*/
            SetMarker: function (map, coordinates,maxPj) {
                fn = {
                    _mouseover: function (map, marker, coordinates) {//鼠标移近点标记时触发事件
                    	
                    },
                    _click: function (map, marker, coordinates) {//鼠标点击点标记时触发事件
                    	
                        //监听鼠标点击事件
//                        AMap.event.addListener(marker, 'mouseover', function () {
                    	AMap.event.addListener(marker, 'click', function () {
                        	
                        	
                        	if(isIE8==undefined){
                        		
                        		//请求最新数据
                            	$.ajax({
                                    type: "GET",
                                    url: "stDeviceFactor/getDetail.do",
                                    dataType:"json",
                                    data: { 
                                    	stcd: coordinates.stcd,
                                    	query_beginTime : $("#hidden_query_beginTime").val(),
    	                            	query_beginTime_hour : $("#hidden_query_beginTime_hour").val(),
    	                            	query_endTime : $("#hidden_query_endTime").val(),
    	                            	query_endTime_hour : $("#hidden_query_endTime_hour").val()
                                    },
                                    beforeSend: function () {
                                    	
                                    	var info = $("<div><div>");
                                        info.attr("class", "mapInfo");
                                        var infoHtml = "<div class=\"map-title map-skin\">";
                                        infoHtml += "<h1>" + coordinates.stnm + "</h1>";
                                        infoHtml += "<a class=\"map-close\"></a>";
                                        infoHtml += "</div>";
                                        infoHtml += "<div class=\"map-content control-loading\" style='height: 50px;'>";
                                        
                                        infoHtml += "</div>";
                                        infoHtml += "<div class=\"map-bottom\"></div>";
                                        info.html(infoHtml);
                                        //绑定地图关闭事件
                                        info.find("a.map-close").click(function () {
                                            map.clearInfoWindow();
                                        });
                                    	
                                        var infoWindow = new AMap.InfoWindow({
                                            isCustom: true,  //使用自定义窗体
                                            content: info[0],
                                            offset: new AMap.Pixel(18, -20)
                                        });
                                        infoWindow.open(map, marker.getPosition());
                                    },
                                    success: function (data) {
                                        if (data.success) {
                                        	var result = data.obj;
                                        	var mapResult = data.attributes;
                                        	updateAry(result);
                                        	//var info = $("<div><div>");
                                        	var info = $(".mapInfo");
                                            info.attr("class", "mapInfo");
                                        	   
                                            var infoHtml = "<div class=\"map-title map-skin\">";
                                            infoHtml += "<h1>" + coordinates.stnm + "</h1>";
                                            infoHtml += "<a class=\"map-close\"></a>";
                                            infoHtml += "</div>";
                                            infoHtml += "<div class=\"map-content\">";
                                            
                                            infoHtml += $.i18n.map['deviceName']+"：" + coordinates.stnm + "<br />";
                                            infoHtml += $.i18n.map['deviceCode']+"：" + coordinates.stcd + "<br />";
                                            infoHtml += $.i18n.map['deviceAddress']+"：" + coordinates.stlc + "<br />";
                                            infoHtml += "水文局"+"：" + (isNotEmpty(result.addvnm2)?result.addvnm2:'--') + "<br />";
                                            infoHtml += "水文站"+"：" + (isNotEmpty(result.addvnm1)?result.addvnm1:'--') + "<br />";
                                            infoHtml += "通讯方式"+"：" + (result.commode==1?'北斗通信':'GPRS') + "<br />";
//                                            infoHtml += "电压"+"：" + (isNotEmpty(result.voltage)?result.voltage:'--') + " V<br />";
//                                            infoHtml += "信号强度"+"：" + (isNotEmpty(result.signalinten)?result.signalinten:'--') + " dBm<br />";
                                            infoHtml += "日降雨量"+"：" + (isNotEmpty(result.pJ)?result.pJ:'--') + "<br />";
                                            //采集时间
                                            infoHtml += $.i18n.map['acquisitionTime']+"：" + (isNotEmpty(result.tm)?new Date(result.tm).format('yyyy-MM-dd hh:mm:ss'):'--') + "<br />";
                                            if(JSON.stringify(mapResult)!='{}'){
                                            	infoHtml += "<div style=\"background-color: #ffffff;height: 110px;overflow: auto;border-bottom:0;\">";
                                            	for(var key in mapResult){ 
                                            		if("雨雪标识" == key){
                                            			if(null!=mapResult[key] && "0"==mapResult[key]){
                                            				infoHtml += key+"：<img src='/content/skin/css/images/state/rain.png' /><br />";
                                            			}else if(null!=mapResult[key] && "1"==mapResult[key]){
                                            				infoHtml += key+"：<img src='/content/skin/css/images/state/snow.png' /><br />";
                                            			}else{
                                            				infoHtml += key+"：" + mapResult[key] + "<br />";
                                            			}
                                            		}else{
                                            			infoHtml += key+"：" + mapResult[key] + "<br />";
                                            		}
                                            	} 
                                            	infoHtml += "</div>";
                                            }
                                           /* infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.z)?result.z:'--') + " m<br />";
                                        	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.pn05)?result.pn05:'--') + " mm<br />";*/
                                            
                                /*            var isHasTm = isNotEmpty($("#query_beginTime").val());
                                            if(!isHasTm)
                                            	infoHtml += $.i18n.map['acquisitionTime']+"：" + (isNotEmpty(result.tm)?new Date(result.tm).format('yyyy-MM-dd hh:mm:ss'):'--') + "<br />";
                                            if(coordinates.sttp==0 || coordinates.sttp==1){
                                            	infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.water)?result.water:'--') + " m<br />";
                                            }else if(coordinates.sttp==2){
                                            	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.rain)?result.rain:'--') + " mm<br />";
                                            }else if(coordinates.sttp==3){
                                            	infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.water)?result.water:'--') + " m<br />";
                                            	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.rain)?result.rain:'--') + " mm<br />";
                                            }else if(coordinates.sttp==4){
                                            	infoHtml += $.i18n.map['ph']+"：" + (isNotEmpty(result.ph)?result.ph:'--') + " <br />";
                                            	infoHtml += $.i18n.map['turbidity']+"：" + (isNotEmpty(result.turbidimeter)?result.turbidimeter:'--') + " <br />";
                                            	infoHtml += $.i18n.map['dissolvedOxygen']+"：" + (isNotEmpty(result.oxygen)?result.oxygen:'--') + " <br />";
                                            	infoHtml += $.i18n.map['conductivity']+"：" + (isNotEmpty(result.surfactants)?result.surfactants:'--') + " <br />";
                                            	infoHtml += $.i18n.map['temperature']+"：" + (isNotEmpty(result.temperature)?result.temperature:'--') + " <br />";
                                            	
                                            }*/
                                            
                                            infoHtml += "<ul class=\"map-button\">";
                	                        infoHtml += "<li class=\"map-report\">"+$.i18n.map['reportDetails']+"</li>";
                	                        if (coordinates.cameratype==1){
                	                        	infoHtml += "<li class=\"map-photo\">"+$.i18n.map['monitorPicture']+"</li>";
                	                        }
                	                        if (coordinates.cameratype==2){
                	                        	infoHtml += "<li class=\"map-video\">"+$.i18n.map['monitorVideo']+"</li>";
                	                        }
                	                        infoHtml += "</ul>";
                	                        infoHtml += "</div>";
                                            infoHtml += "<div class=\"map-bottom\"></div>";
                                            info.html(infoHtml);
                                            
                                            //绑定地图关闭事件
                                            info.find("a.map-close").click(function () {
                                                map.clearInfoWindow();
                                            });
                                            
                                            info.find("ul li.map-report").click(function () {
            		                            //alert("绑定报表事件,设备号：" + coordinates.stcd);
            		                            showReportDialog(coordinates.stcd);
            		                        });
            		                        //绑定拍照弹出层事件
            		                        info.find("ul li.map-photo").click(function () {
            		                            //alert("绑定拍照弹出层事件,设备号：" + coordinates.stcd);
            		                        	  showReportDialog(coordinates.stcd,2);
            		                        });
            		                        //绑定视频插件弹出层事件
            		                        info.find("ul li.map-video").click(function () {
            		                            //alert("绑定视频插件弹出层事件,设备号：" + coordinates.stcd);
            		                        	 showVideoDialog(coordinates.stcd,coordinates.dvraddr,coordinates.dvrcode,coordinates.dsfl,coordinates.videochannel);
            		                        });
                                        	
                                            var infoWindow = new AMap.InfoWindow({
                                                isCustom: true,  //使用自定义窗体
                                                content: info[0],
                                                offset: new AMap.Pixel(18, -20)
                                            });
                                            infoWindow.open(map, marker.getPosition());
                                            
                                        } else {
                                        	
                                        	var info = $("<div><div>");
                                            info.attr("class", "mapInfo");
                                        	   
                                            var infoHtml = "<div class=\"map-title map-skin\">";
                                            infoHtml += "<h1>error</h1>";
                                            infoHtml += "<a class=\"map-close\"></a>";
                                            infoHtml += "</div>";
                                            infoHtml += "<div class=\"map-content\">";
                                            
                                            infoHtml += "</div>";
                                            infoHtml += "<div class=\"map-bottom\"></div>";
                                            info.html(infoHtml);
                                            //绑定地图关闭事件
                                            info.find("a.map-close").click(function () {
                                                map.clearInfoWindow();
                                            });
                                            
                                        	var infoWindow = new AMap.InfoWindow({
                                                isCustom: true,  //使用自定义窗体
                                                content: info[0],
                                                offset: new AMap.Pixel(18, -20)
                                            });
                                            infoWindow.open(map, marker.getPosition());
                                        }
                                    }
                                });
                        		
                        	}else{
                        		
//                        		var info = $("<div><div>");
//                                info.attr("class", "mapInfo");
//                            	   
//                                var infoHtml = "<div class=\"map-title map-skin\">";
//                                infoHtml += "<h1>error</h1>";
//                                infoHtml += "<a class=\"map-close\"></a>";
//                                infoHtml += "</div>";
//                                infoHtml += "<div class=\"map-content\">";
//                                
//                                infoHtml += "</div>";
//                                infoHtml += "<div class=\"map-bottom\"></div>";
//                                info.html(infoHtml);
//                                //绑定地图关闭事件
//                                info.find("a.map-close").click(function () {
//                                    map.clearInfoWindow();
//                                });
//                                
//                            	var infoWindow = new AMap.InfoWindow({
//                                    isCustom: true,  //使用自定义窗体
//                                    content: info[0],
//                                    offset: new AMap.Pixel(18, -20)
//                                });
//                                infoWindow.open(map, marker.getPosition());
                        		
                        		var result = coordinates;
                        		var info = $("<div><div>");
                                info.attr("class", "mapInfo");
                        		
                                var infoHtml = "<div class=\"map-title map-skin\">";
                                infoHtml += "<h1>" + coordinates.stnm + "</h1>";
                                infoHtml += "<a class=\"map-close\"></a>";
                                infoHtml += "</div>";
                                infoHtml += "<div class=\"map-content\">";
                                
                                infoHtml += $.i18n.map['deviceName']+"：" + coordinates.stnm + "<br />";
                                infoHtml += $.i18n.map['deviceCode']+"：" + coordinates.stcd + "<br />";
                                infoHtml += $.i18n.map['deviceAddress']+"：" + coordinates.stlc + "<br />";
                                infoHtml += "电压"+"：" + (isNotEmpty(result.voltage)?result.voltage:'--') + " V<br />";
                            	infoHtml += "信号强度"+"：" + (isNotEmpty(result.signalinten)?result.signalinten:'--') + " dBm<br />";
                                
                                infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.z)?result.z:'--') + " m<br />";
                            	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.pn05)?result.pn05:'--') + " mm<br />";
                            	infoHtml += $.i18n.map['acquisitionTime']+"：" + (isNotEmpty(result.tm)?new Date(result.tm).format('yyyy-MM-dd hh:mm:ss'):'--') + "<br />";
                            	
                            	if(JSON.stringify(mapResult)!='{}'){
                                	infoHtml += "<div style=\"background-color: #ffffff;height: 120px;overflow: auto;border-bottom:0;\">";
                                	for(var key in mapResult){ 
                                		infoHtml += key+":" + mapResult[key] + "<br />";
                                	} 
                                	infoHtml += "</div>";
                                }
                    
                            	/*     
                            	var isHasTm = isNotEmpty($("#query_beginTime").val());
                                if(!isHasTm)
                                	infoHtml += $.i18n.map['acquisitionTime'] +"：" + (isNotEmpty(result.tm)?new Date(result.tm).format('yyyy-MM-dd hh:mm:ss'):'--') + "<br />";
                                if(coordinates.sttp==0 || coordinates.sttp==1){
                                	infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.water)?result.water:'--') + " m<br />";
                                }else if(coordinates.sttp==2){
                                	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.rain)?result.rain:'--') + " mm<br />";
                                }else if(coordinates.sttp==3){
                                	infoHtml += $.i18n.map['waterLevel']+"：" + (isNotEmpty(result.water)?result.water:'--') + " m<br />";
                                	infoHtml += $.i18n.map['rainfall']+"：" + (isNotEmpty(result.rain)?result.rain:'--') + " mm<br />";
                                }else if(coordinates.sttp==4){
                                	infoHtml += $.i18n.map['ph']+"：" + (isNotEmpty(result.ph)?result.ph:'--') + " <br />";
                                	infoHtml += $.i18n.map['turbidity']+"：" + (isNotEmpty(result.turbidimeter)?result.turbidimeter:'--') + " <br />";
                                	infoHtml += $.i18n.map['dissolvedOxygen']+"：" + (isNotEmpty(result.oxygen)?result.oxygen:'--') + " <br />";
                                	infoHtml += $.i18n.map['conductivity']+"：" + (isNotEmpty(result.surfactants)?result.surfactants:'--') + " <br />";
                                	infoHtml += $.i18n.map['temperature']+"：" + (isNotEmpty(result.temperature)?result.temperature:'--') + " <br />";
                                	
                                }*/
                                infoHtml += "<ul class=\"map-button\">";
    	                        infoHtml += "<li class=\"map-report\">"+$.i18n.map['reportDetails']+"</li>";
    	                        if (coordinates.cameratype==1){
    	                        	infoHtml += "<li class=\"map-photo\">"+$.i18n.map['monitorPicture']+"</li>";
    	                        }
    	                        if (coordinates.cameratype==2){
    	                        	infoHtml += "<li class=\"map-video\">"+$.i18n.map['monitorVideo']+"</li>";
    	                        }
    	                        infoHtml += "</ul>";
    	                        infoHtml += "</div>";
                                infoHtml += "<div class=\"map-bottom\"></div>";
                                info.html(infoHtml);
                                
                                //绑定地图关闭事件
                                info.find("a.map-close").click(function () {
                                    map.clearInfoWindow();
                                });
                                
                                info.find("ul li.map-report").click(function () {
		                            //alert("绑定报表事件,设备号：" + coordinates.stcd);
		                            showReportDialog(coordinates.stcd);
		                        });
		                        //绑定拍照弹出层事件
		                        info.find("ul li.map-photo").click(function () {
		                            //alert("绑定拍照弹出层事件,设备号：" + coordinates.stcd);
		                        	  showReportDialog(coordinates.stcd,2);
		                        });
		                        //绑定视频插件弹出层事件
		                        info.find("ul li.map-video").click(function () {
		                            //alert("绑定视频插件弹出层事件,设备号：" + coordinates.stcd);
		                        	 showVideoDialog(coordinates.stcd,coordinates.dvraddr,coordinates.dvrcode,coordinates.dsfl,coordinates.videochannel);
		                        });
                            	
                                var infoWindow = new AMap.InfoWindow({
                                    isCustom: true,  //使用自定义窗体
                                    content: info[0],
                                    offset: new AMap.Pixel(18, -20)
                                });
                                infoWindow.open(map, marker.getPosition());
                        		
                        	}
                        	
                        });
                    },
                    _mouseout : function(map, marker){//鼠标移出点标记时触发事件
                    	/*AMap.event.addListener(marker, 'mouseout', function () {
                    		map.clearInfoWindow();
                        });*/
                    }
                    
                }
                if(coordinates != undefined){
                	var cluster, markers = [];
                	for (var i = 0; i < coordinates.length; i++) {
                		if(coordinates[i].lgtd != undefined && coordinates[i].lttd != undefined){
                			var icon = "content/skin/css/images/map/map-icon-" + coordinates[i].sttp;
                			var dsfl = coordinates[i].dsfl==1?1:0;
                			var contents = null;
                			if(coordinates[i].td11 == "0"){
                				contents = "&nbsp;" +coordinates[i].stnm +"&nbsp;<br /> &nbsp;"+"<img src='/content/skin/css/images/state/rain1.png' />"+":"+(isNotEmpty(coordinates[i].pJ)?coordinates[i].pJ:'--');
                			}else{
                				contents = "&nbsp;" +coordinates[i].stnm +"&nbsp;<br /> &nbsp;"+"<img src='/content/skin/css/images/state/snow1.png' />"+":"+(isNotEmpty(coordinates[i].pJ)?coordinates[i].pJ:'--');
                			}
                			var marker = new AMap.Marker({
                				//自定义图标
//                				icon: new AMap.Icon({
//                					//图标大小
//                					size: new AMap.Size(30, 38),
//                					//图标地址
//                					image: "content/skin/css/images/map/map-" + coordinates[i].code + "-" + coordinates[i].status + ".png"
//                				}),
//                				icon: coordinates[i].state==1?"http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png":"http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
                				extData:coordinates[i].stcd,
                				//icon: coordinates[i].dsfl==1?"content/skin/css/images/map_icon.png":"content/skin/css/images/map_icon.png",
                				icon: icon + "-" + dsfl + ".png",
                				//在地图上添加点
                			 	position: new AMap.LngLat(coordinates[i].lgtd, coordinates[i].lttd),
                			 	label:{
                    				content:contents,
                    				offset: new AMap.Pixel(coordinates[i].dsfl==1?26:30, 20)
                    			}
                			});
                			marker.setMap(map);
                			fn._mouseover(map, marker, coordinates[i]);
                			fn._mouseout(map, marker);
                			fn._click(map, marker, coordinates[i]);
                			markers.push(marker);
                			if(maxPj!=null && maxPj!=0 && coordinates[i].pJ==maxPj){
                				 marker.setAnimation('AMAP_ANIMATION_BOUNCE');	//设置点标记跳弹
                			}
                		}
                	}
                	
//            	    map.plugin(["AMap.MarkerClusterer"], function() {
//                       var cluster = new AMap.MarkerClusterer(map, markers);
//                    });
                	
                	map.setFitView();//地图自适应
                	if(map.getZoom()>16) map.setZoom(16);	
                }
            },
            /* 
				用途：设置地图类型切换 
				输入：map地图对象 layers地图类型 
				返回：无 
			*/
            SetLayers: function (map, layers) {
                if (layers == "satellite") {
                    map.setDefaultLayer(new AMap.TileLayer.Satellite({ zIndex: 6 }), new AMap.TileLayer.RoadNet({ zIndex: 11 }));
                } else {
                    map.setDefaultLayer(new AMap.TileLayer({ tileUrl: "http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil" }));
                }
            },
        }
    });
})(jQuery)