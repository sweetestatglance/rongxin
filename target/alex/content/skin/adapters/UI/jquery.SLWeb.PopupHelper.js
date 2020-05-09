/**************************************************************************
*
* 作者：谷雨
* 
* 创始时间：2015-09-23
* 
* 描述：水利Web弹出层插件，调用方式：var mapObj = $.Map.Init();
*
* 修改人：
*
* 修改时间：
*
* ************************************************************************/
jQuery(function (jQuery) {
	$.extend({
		Popup: {
			/* 
                用途：创建弹出层 
                输入：opt：参数 
            */
			create: function (opt) {
				//参数和默认值
				var defaults = {
					id: '',//弹出层Id，选填
					title: '默认弹窗',
					width: '300', //默认宽度，选填
					height: 'auto', //默认高度，选填
					closeButton: true,
					requestMethod: 'normal',//请求方式：normal--用于直接弹出层，content为直接传递时填充内容；ajax--用于异步加载弹出层，content为异步执行后加载填充的内容
					content: '',//填充内容
					url: '',//请求地址
					paraData: {},//传递参数
					isVideo:false,//判断是否是视频播放器
					tbar: []//按钮控件
					//回调后生成参数
					//formObj：表单对象
					//zIndex:当前弹出层层级
					//popObj:当前弹出层对象
					//contentObj:当前弹出层内容对象
				};
				//合并参数
				var options = $.extend(defaults, opt);
				var obj = $(".popup");
				//--------------创建弹窗开始
				var popupHtml = "";
				//计算弹窗层级
				var zIndex = obj.find("div.popup-mask").length;
				options.zIndex = zIndex;
				if (options.id == "") options.id = "popup_" + zIndex;
				$.Popup.loading(options);

				//弹窗遮罩层
				popupHtml += "<div class='popup-mask' id='" + options.id + "' style='z-index: " + (200 + zIndex) + ";'>";
				popupHtml += "<div class='popup-box' style='position: absolute; width: " + options.width + "px;'>";
				if (options.closeButton) popupHtml += "<a class='homebg popup-close'></a>";
				popupHtml += "<h2 class='popup-move'><span>" + options.title + "</span></h2>";
				popupHtml += "<div class='popup-content' style='height: " + options.height + "px;'></div>";
				popupHtml += "<div class='popup-control'></div>";
				popupHtml += "</div></div>";
				obj.append(popupHtml);
				options.popObj = $("#" + options.id);
				//--------------创建弹窗结束

				//--------------私有方法封装开始
				fn = {
					_normal: function () {
						//绑定填充内容
						options.popObj.find("div.popup-content").html(options.content);
						options.formObj = options.popObj.find("div.popup-content").find("form");
						//绑定按钮
						if (options.tbar && options.tbar.length > 0) {
							var controlHtml = "";
							for (var i = 0; i < options.tbar.length; i++) {
								controlHtml += "<input type='button' sort='" + i + "' value='" + options.tbar[i].text + "' class='" + options.tbar[i].clsName + "' />";
							}
							options.popObj.find("div.popup-control").html(controlHtml);
							options.popObj.find("div.popup-control").find(":button").each(function () {
								$(this).click(function () {
									var tmplen = $(this).attr("sort");
									options.tbar[tmplen].handler(options);
								});
							});
						}
						options.popObj.find("div.popup-box").css({ "left": (($(document).width() - options.popObj.find("div.popup-box").outerWidth()) / 2) + "px", "top": (($(document).height() - options.popObj.find("div.popup-box").outerHeight()) / 3) + "px" });
					},
					_ajax: function () {
						$.ajax({
							type: "POST",
							url: options.url,
							data: options.paraData,
							beforeSend: function () {
								if(options.height=="auto"){
									options.popObj.find("div.popup-content").css("height", "100");
								}
								options.popObj.find("div.popup-content").addClass("control-loading");
								options.popObj.find("div.popup-box").css({ "left": (($(document).width() - options.popObj.find("div.popup-box").outerWidth()) / 2) + "px", "top": (($(document).height() - options.popObj.find("div.popup-box").outerHeight()) / 3) + "px" });
							},
							success: function (result) {
								
								if(options.height=="auto"){
									options.popObj.find("div.popup-content").css("height", "auto");
								}else{
									options.popObj.find("div.popup-content").css("height", options.height);
								}
								options.popObj.find("div.popup-content").removeClass("control-loading");
								if (result) {
									if (result.tag == -1) {
										options.popObj.find("div.popup-content").html(result.message);
									} else if (result.tag == -2) {
										options.popObj.find("div.popup-content").html(result.message);
									} else {
										options.content = result;
										options.contentObj = options.popObj.find("div.popup-content");
										options.popObj.find("div.popup-content").html(result);
										options.formObj = options.popObj.find("div.popup-content").find("form");
										//绑定按钮
										if (options.tbar && options.tbar.length > 0) {
											var controlHtml = "";
											for (var i = 0; i < options.tbar.length; i++) {
												controlHtml += "<input type='button' sort='" + i + "' value='" + options.tbar[i].text + "' class='" + options.tbar[i].clsName + "' />";
											}
											options.popObj.find("div.popup-control").html(controlHtml);
											options.popObj.find("div.popup-control").find(":button").each(function () {
												$(this).click(function () {
													var tmplen = $(this).attr("sort");
													options.tbar[tmplen].handler(options);
												});
											});
										}
									}
								} else {
									options.popObj.find("div.popup-content").html("加载失败");
								}
								options.popObj.find("div.popup-box").css({ "left": (($(document).width() - options.popObj.find("div.popup-box").outerWidth()) / 2) + "px", "top": (($(document).height() - options.popObj.find("div.popup-box").outerHeight()) / 4) + "px" });
							}
						});
					}
				};
				//--------------私有方法封装结束

				//--------------绑定内容开始
				switch (options.requestMethod) {
					case "normal":
						fn._normal();
						break;
					case "ajax":
						fn._ajax();
						break;
				}
				//--------------绑定内容结束

				//--------------绑定方法开始
				//关闭当前弹出窗体事件
				options.popObj.find("a.popup-close").click(function () {
					if(options.isVideo && (navigator.userAgent.indexOf('IE') >= 0)){
						SetParam();//针对视频
					}
					$.Popup.close(options);
				});
				//拖拽操作
				var _move = false;
				var _x, _y, moveObj = options.popObj.find("div.popup-box");//定义移动后坐标点  
				options.popObj.find("h2.popup-move").mousedown(function (e) {
					_move = true;
					_x = e.pageX - parseInt(moveObj.css("left"));
					_y = e.pageY - parseInt(moveObj.css("top"));
					moveObj.fadeTo(20, 0.8);//移动时弹出层透明化  
				});
				$(document).mousemove(function (e) {
					if (_move) {
						var x = e.pageX - _x;//移动时横坐标变化
						if (x < 0) {
							x = 0;
						} else if (x > ($(document).width() - moveObj.outerWidth())) {
							x = $(document).width() - moveObj.outerWidth();
						}
						var y = e.pageY - _y;//移动时纵坐标变化
						if (y < 0) {
							y = 0;
						} else if (y > ($(document).height() - moveObj.outerHeight())) {
							y = $(document).height() - moveObj.outerHeight();
						}
						moveObj.css({ top: y, left: x });//定位 
					}
				}).mouseup(function () {
					_move = false;
					moveObj.fadeTo("fast", 1);
				});
				//绑定填充按钮事件
				if ($(".mask").hasClass("loading")) {
			        $(".mask").removeClass("loading");
			    }
				//--------------绑定方法结束
				return options;
			},
			loading: function (options) {
				if ($(".mask").is(":hidden") && options.zIndex == 0) {
					showMark();
				}
			},
			close: function (options) {
				if (!$(".mask").is(":hidden") && options.zIndex == 0) {
					hideMark();
				}
				$("#" + options.id).remove();
			}
		}
	});
}, window, document)