/*
  kkpager V1.3
  https://github.com/pgkk/kkpager
  Licensed under the GNU GENERAL PUBLIC LICENSE
  
     示例：
  $.Pagination.generPageHtml({
			//填充的id
			pagerid : t_id,
			//当前页
			pno : t_pageNo,
			//总页码
			total : t_totalPage,
			//总数据条数
			totalRecords : t_totalRecords,
			mode : 'click',
			click : function(n) {
				//分页执行方法
				t_fun(n);
				//this.selectPage(n);
				return false;
			}
	});
*/
var _qCache = {};
jQuery(function (jQuery) {
	$.extend({
		Pagination : {
				//生成控件代码
				generPageHtml : function(config,enforceInit){
					var options = {
							pagerid 			: 'tpager', //divID
							mode				: 'link', //模式(link 或者 click)
							pno					: 1, //当前页码
							total				: 1, //总页码
							totalRecords		: 0, //总数据条数
							isShowFirstPageBtn	: true, //是否显示首页按钮
							isShowLastPageBtn	: true, //是否显示尾页按钮
							isShowPrePageBtn	: true, //是否显示上一页按钮
							isShowNextPageBtn	: true, //是否显示下一页按钮
							isShowTotalPage 	: true, //是否显示总页数
							isShowCurrPage		: true,//是否显示当前页
							isShowTotalRecords 	: false, //是否显示总记录数
							isGoPage 			: true,	//是否显示页码跳转输入框
							isWrapedPageBtns	: true,	//是否用span包裹住页码按钮
							isWrapedInfoTextAndGoPageBtn : true, //是否用span包裹住分页信息和跳转按钮
							hrefFormer			: '', //链接前部
							hrefLatter			: '', //链接尾部
							gopageWrapId		: 'kkpager_gopage_wrap',
							gopageButtonId		: 'kkpager_btn_go',
							gopageTextboxId		: 'kkpager_btn_go_input',
							click	: function(n){
								//页码单击事件处理函数（当处于mode模式）,参数n为页码
								return false;
							},
							getHref	: function(n){
								//获取href的值（当处于mode模式）,参数n为页码
								//默认返回'#'
								return '#';
							},
							lang				: {
								firstPageText			: $.i18n.map['firstPage'],
								firstPageTipText		: $.i18n.map['firstPage'],
								lastPageText			: $.i18n.map['lastPage'],
								lastPageTipText			: $.i18n.map['lastPage'],
								prePageText				: $.i18n.map['previousPage'],
								prePageTipText			: $.i18n.map['previousPage'],
								nextPageText			: $.i18n.map['nextPage'],
								nextPageTipText			: $.i18n.map['nextPage'],
								totalPageBeforeText		: '共',
								totalPageAfterText		: '页',
								currPageBeforeText		: '当前第',
								currPageAfterText		: '页',
								totalInfoSplitStr		: '/',
								totalRecordsBeforeText	: '共',
								totalRecordsAfterText	: '条数据',
								gopageBeforeText		: '&nbsp;转到',
								gopageButtonOkText		: '确定',
								gopageAfterText			: '页',
								buttonTipBeforeText		: '第',
								buttonTipAfterText		: '页'
							},
					};
					
					options = $.Pagination.init(config,options);
					
					_qCache[options.pagerid]=options;
					//合并参数
//					var options = $.extend(defaults, config);
					
					pfn = {
							_clickHandler	: function(n,pagerid){
								var res = false;
								var opt = _qCache[pagerid];
								if(opt.click && typeof opt.click == 'function'){
									res = opt.click.call(opt,n) || false;
								}
								return res;
							},
							_getHandlerStr : function(n){
								if(options.mode == 'click'){
									return 'href="javascript:void(0)" onclick="return pfn._clickHandler('+n + ',\'' + options.pagerid  +'\')"';
								}
								//link模式，也是默认的
								return 'href="'+pfn.getLink(n)+'"';
							},
							//链接算法（当处于link模式）,参数n为页码
							getLink	: function(n){
								//这里的算法适用于比如：
								//hrefFormer=http://www.xx.com/news/20131212
								//hrefLatter=.html
								//那么首页（第1页）就是http://www.xx.com/news/20131212.html
								//第2页就是http://www.xx.com/news/20131212_2.html
								//第n页就是http://www.xx.com/news/20131212_n.html
								if(n == 1){
									return options.hrefFormer + options.hrefLatter;
								}
								return options.hrefFormer + '_' + n + options.hrefLatter;
							},
							//跳转框得到输入焦点时
							focus_gopage : function (){
								var btnGo = $('#'+options.gopageButtonId);
								$('#'+options.gopageTextboxId).attr('hideFocus',true);
								btnGo.show();
								btnGo.css('left','10px');
								$('#'+options.gopageTextboxId).addClass('focus');
								btnGo.animate({left: '+=30'}, 50);
							},
							//跳转框失去输入焦点时
							blur_gopage : function(){
								var _this = options;
								setTimeout(function(){
									var btnGo = $('#'+_this.gopageButtonId);
									btnGo.animate({
									    left: '-=25'
									  }, 100, function(){
										  btnGo.hide();
										  $('#'+_this.gopageTextboxId).removeClass('focus');
									  });
								},400);
							},
							//跳转输入框按键操作
							keypress_gopage : function(){
								var event = arguments[0] || window.event;
								var code = event.keyCode || event.charCode;
								//delete key
								if(code == 8) return true;
								//enter key
								if(code == 13){
									pfn.gopage();
									return false;
								}
								//copy and paste
								if(event.ctrlKey && (code == 99 || code == 118)) return true;
								//only number key
								if(code<48 || code>57)return false;
								return true;
							},
							//跳转框页面跳转
							gopage : function(){
								var str_page = $('#'+options.gopageTextboxId).val();
								if(isNaN(str_page)){
									$('#'+options.gopageTextboxId).val(options.next);
									return;
								}
								var n = parseInt(str_page);
								if(n < 1) n = 1;
								if(n > options.total) n = options.total;
								if(options.mode == 'click'){
									pfn._clickHandler(n,options.pagerid);
								}else{
									window.location = options.getLink(n);
								}
							},
					}
					
					var str_first='',str_prv='',str_next='',str_last='';
					if(options.isShowFirstPageBtn){
						if(options.hasPrv){
							str_first = '<a '+pfn._getHandlerStr(1)+' title="'
								+(options.lang.firstPageTipText || options.lang.firstPageText)+'">'+options.lang.firstPageText+'</a>';
						}else{
							str_first = '<span class="disabled">'+options.lang.firstPageText+'</span>';
						}
					}
					if(options.isShowPrePageBtn){
						if(options.hasPrv){
							str_prv = '<a '+pfn._getHandlerStr(options.prv)+' title="'
								+(options.lang.prePageTipText || options.lang.prePageText)+'">'+options.lang.prePageText+'</a>';
						}else{
							str_prv = '<span class="disabled">'+options.lang.prePageText+'</span>';
						}
					}
					if(options.isShowNextPageBtn){
						if(options.hasNext){
							str_next = '<a '+pfn._getHandlerStr(options.next)+' title="'
								+(options.lang.nextPageTipText || options.lang.nextPageText)+'">'+options.lang.nextPageText+'</a>';
						}else{
							str_next = '<span class="disabled">'+options.lang.nextPageText+'</span>';
						}
					}
					if(options.isShowLastPageBtn){
						if(options.hasNext){
							str_last = '<a '+pfn._getHandlerStr(options.total)+' title="'
								+(options.lang.lastPageTipText || options.lang.lastPageText)+'">'+options.lang.lastPageText+'</a>';
						}else{
							str_last = '<span class="disabled">'+options.lang.lastPageText+'</span>';
						}
					}
					var str = '';
					var dot = '<span class="spanDot">...</span>';
					var total_info='<span class="totalText">';
					var total_info_splitstr = '<span class="totalInfoSplitStr">'+options.lang.totalInfoSplitStr+'</span>';
					if(options.isShowCurrPage){
						total_info += options.lang.currPageBeforeText + '<span class="currPageNum">' + options.pno + '</span>' + options.lang.currPageAfterText;
						if(options.isShowTotalPage){
							total_info += total_info_splitstr;
							total_info += options.lang.totalPageBeforeText + '<span class="totalPageNum">'+options.total + '</span>' + options.lang.totalPageAfterText;
						}else if(options.isShowTotalRecords){
							total_info += total_info_splitstr;
							total_info += options.lang.totalRecordsBeforeText + '<span class="totalRecordNum">'+options.totalRecords + '</span>' + options.lang.totalRecordsAfterText;
						}
					}else if(options.isShowTotalPage){
						total_info += options.lang.totalPageBeforeText + '<span class="totalPageNum">'+options.total + '</span>' + options.lang.totalPageAfterText;;
						if(options.isShowTotalRecords){
							total_info += total_info_splitstr;
							total_info += options.lang.totalRecordsBeforeText + '<span class="totalRecordNum">'+options.totalRecords + '</span>' + options.lang.totalRecordsAfterText;
						}
					}else if(options.isShowTotalRecords){
						total_info += options.lang.totalRecordsBeforeText + '<span class="totalRecordNum">'+options.totalRecords + '</span>' + options.lang.totalRecordsAfterText;
					}
					total_info += '</span>';
					
					var gopage_info = '';
					if(options.isGoPage){
						gopage_info = '<span class="goPageBox">'+options.lang.gopageBeforeText+'<span id="'+options.gopageWrapId+'">'+
							'<input type="button" id="'+options.gopageButtonId+'" onclick="pfn.gopage()" value="'
								+options.lang.gopageButtonOkText+'" />'+
							'<input type="text" id="'+options.gopageTextboxId+'" onfocus="pfn.focus_gopage()"  onkeypress="return pfn.keypress_gopage(event);"   onblur="pfn.blur_gopage()" value="'+options.next+'" /></span>'+options.lang.gopageAfterText+'</span>';
					}
					
					//分页处理
					if(options.total <= 8){
						for(var i=1;i<=options.total;i++){
							if(options.pno == i){
								str += '<span class="curr">'+i+'</span>';
							}else{
								str += '<a '+pfn._getHandlerStr(i)+' title="'
									+options.lang.buttonTipBeforeText + i + options.lang.buttonTipAfterText+'">'+i+'</a>';
							}
						}
					}else{
						if(options.pno <= 5){
							for(var i=1;i<=7;i++){
								if(options.pno == i){
									str += '<span class="curr">'+i+'</span>';
								}else{
									str += '<a '+pfn._getHandlerStr(i)+' title="'+
										options.lang.buttonTipBeforeText + i + options.lang.buttonTipAfterText+'">'+i+'</a>';
								}
							}
							str += dot;
						}else{
							str += '<a '+pfn._getHandlerStr(1)+' title="'
								+options.lang.buttonTipBeforeText + '1' + options.lang.buttonTipAfterText+'">1</a>';
							str += '<a '+pfn._getHandlerStr(2)+' title="'
								+options.lang.buttonTipBeforeText + '2' + options.lang.buttonTipAfterText +'">2</a>';
							str += dot;
							
							var begin = options.pno - 2;
							var end = options.pno + 2;
							if(end > options.total){
								end = options.total;
								begin = end - 4;
								if(options.pno - begin < 2){
									begin = begin-1;
								}
							}else if(end + 1 == options.total){
								end = options.total;
							}
							for(var i=begin;i<=end;i++){
								if(options.pno == i){
									str += '<span class="curr">'+i+'</span>';
								}else{
									str += '<a '+pfn._getHandlerStr(i)+' title="'
										+options.lang.buttonTipBeforeText + i + options.lang.buttonTipAfterText+'">'+i+'</a>';
								}
							}
							if(end != options.total){
								str += dot;
							}
						}
					}
					var pagerHtml = '<div>';
					if(options.isWrapedPageBtns){
						pagerHtml += '<span class="pageBtnWrap">' + str_first + str_prv + str + str_next + str_last + '</span>'
					}else{
						pagerHtml += str_first + str_prv + str + str_next + str_last;
					}
					if(options.isWrapedInfoTextAndGoPageBtn){
						pagerHtml += '<span class="infoTextAndGoPageBtnWrap">' + total_info + gopage_info + '</span>';
					}else{
						pagerHtml += total_info + gopage_info;
					}
					pagerHtml += '</div><div style="clear:both;"></div>';
					$("#"+options.pagerid).html(pagerHtml).addClass("pagination");
				},
				//分页按钮控件初始化
				init : function(config,options){
					options.pno = isNaN(config.pno) ? 1 : parseInt(config.pno);
					options.total = isNaN(config.total) ? 1 : parseInt(config.total);
					options.totalRecords = isNaN(config.totalRecords) ? 0 : parseInt(config.totalRecords);
					if(config.pagerid){options.pagerid = config.pagerid;}
					if(config.mode){options.mode = config.mode;}
					if(config.gopageWrapId){options.gopageWrapId = config.gopageWrapId;}
					if(config.gopageButtonId){options.gopageButtonId = config.gopageButtonId;}
					if(config.gopageTextboxId){options.gopageTextboxId = config.gopageTextboxId;}
					if(config.isShowFirstPageBtn != undefined){options.isShowFirstPageBtn=config.isShowFirstPageBtn;}
					if(config.isShowLastPageBtn != undefined){options.isShowLastPageBtn=config.isShowLastPageBtn;}
					if(config.isShowPrePageBtn != undefined){options.isShowPrePageBtn=config.isShowPrePageBtn;}
					if(config.isShowNextPageBtn != undefined){options.isShowNextPageBtn=config.isShowNextPageBtn;}
					if(config.isShowTotalPage != undefined){options.isShowTotalPage=config.isShowTotalPage;}
					if(config.isShowCurrPage != undefined){options.isShowCurrPage=config.isShowCurrPage;}
					if(config.isShowTotalRecords != undefined){options.isShowTotalRecords=config.isShowTotalRecords;}
					if(config.isWrapedPageBtns){options.isWrapedPageBtns=config.isWrapedPageBtns;}
					if(config.isWrapedInfoTextAndGoPageBtn){options.isWrapedInfoTextAndGoPageBtn=config.isWrapedInfoTextAndGoPageBtn;}
					if(config.isGoPage != undefined){options.isGoPage=config.isGoPage;}
					if(config.lang){
						for(var key in config.lang){
							options.lang[key] = config.lang[key];
						}
					}
					options.hrefFormer = config.hrefFormer || '';
					options.hrefLatter = config.hrefLatter || '';
					if(config.getLink && typeof(config.getLink) == 'function'){options.getLink = config.getLink;}
					if(config.click && typeof(config.click) == 'function'){options.click = config.click;}
					if(config.getHref && typeof(config.getHref) == 'function'){options.getHref = config.getHref;}
					if(!options._config){
						options._config = config;
					}
					//validate
					if(options.pno < 1) options.pno = 1;
					options.total = (options.total <= 1) ? 1: options.total;
					if(options.pno > options.total) options.pno = options.total;
					options.prv = (options.pno<=2) ? 1 : (options.pno-1);
					options.next = (options.pno >= options.total-1) ? options.total : (options.pno + 1);
					options.hasPrv = (options.pno > 1);
					options.hasNext = (options.pno < options.total);
					
					options.inited = true;
					return options;
				},
				
				
				//不刷新页面直接手动调用选中某一页码
				selectPage : function(n,pagerid){
					var opt = _qCache[pagerid];
					opt._config['pno'] = n;
					$.Pagination.generPageHtml(opt._config,true);
				}
		}
		
	});
}, window, document)