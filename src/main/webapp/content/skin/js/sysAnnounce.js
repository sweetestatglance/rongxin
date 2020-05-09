/**********************************系统公告脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(document).on('click', '#checkAll', function(event) {
		var check = $(this).attr("checked");
		var ckList = $("#sysAnounceList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	})
})

function addAnounce(){
	var contentMsg = {
			title: $.i18n.map['noticeAddTitle'],   
			url:"sysAnnounce/addPage.do",
			width:"600",
			paraData:{},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					addAndEditAnounce(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
}

function viewAnnounce(Id){
	var contentMsg = {
			title: $.i18n.map['viewTheAnnouncement'],   
			url:"sysAnnounce/view.do",
			width:"600",
			paraData:{Id:Id},
			requestMethod: 'ajax',
			tbar: [{
				text: $.i18n.map['close'],
				clsName: "homebg popup-cancel",
				handler: function (thisPop) {
					$.Popup.close(thisPop);
				}
			}]
	};
	$.Popup.create(contentMsg);
	$("#" + Id).parent().parent("tr").removeClass("unread");
}

/**
 * 增加和编辑的公用方法
 */
function addAndEditAnounce(thisPop){
	var url = "sysAnnounce/logAddAnnounce.do";;
	var param = $("#sysAnnounceForm").serialize();
   	if(validAnounceForm()){
         	  fnAjaxRequest(
         			    url,
        				param,
        				"json",
        				"POST",
        				function(data){
         			    	fnDSuccess(data,thisPop);
         			    	if(data.success){
         			    		loadAnnounceList(null,true);
          					} 
        				}
        	   );
   	  }
}

/**
 * 删除公告
 */
function delAnounce(delId){
	var delIds = [];
	
	if(delId != undefined){
		delIds.push(delId);
	}else{
		var selectRow = $("#sysAnounceList tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			delIds.push(selectRow[i].value);
		}
	}
	if (delIds.length == 0) {
		$.Popup.create({ title: $.i18n.map['prompt'], content: $.i18n.map['deleteNoticeSelMsg']});
		return;
	}
	var confirmMsg = {
			title: $.i18n.map['prompt'],
			content: $.i18n.map['deleteNoticeMsg'],
			tbar: [{
				text: $.i18n.map['determine'],
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					       fnAjaxRequest(
								"sysAnnounce/logDelAnnounce.do",
								{items: delIds.toString()},
								"json",
								"POST",
								function (data) {
									fnDSuccess(data,thisPop);
									if(data.success){
										loadAnnounceList(null,true);
									}
							});
				}
			}, {
				text: $.i18n.map['cancel'],
				clsName: "homebg popup-cancel",
				handler: function (thisObj) {
					$.Popup.close(thisObj);
				}
			}]
	};
	$.Popup.create(confirmMsg);
}

/**
 * 从新加载
 */
function loadAnnounceList(params,flag){
	var isopt = $("#isopt").val();
	if(params==null) params={isRead:$("#isRead").val(),isopt:isopt};
	var url = "sysAnnounce/index.do";
	showMark();
	$.ajax({
		url : url,
		data : params,
		success :function(data){
			if(isopt=="false"){
				$("#contain").html(data);
			}else{
				$("#twoContain").html(data);
			}
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
	
}

function validAnounceForm(){
	return $("#sysAnnounceForm").validate({
        rules: {
        	title: {
        		required: true
        	},
        	content:  {
        		required: true
        	}
  		},
        messages: {
        	title: {
        		required: $.i18n.map['required']
        	},
        	content:  {
        		required: $.i18n.map['required']
        	}
  		},
  		showErrors:showErrors,
		onkeyup: function( element, event ) {
			if ( event.which === 9 && this.elementValue( element ) === "" ) {
				return;
			} else if ( element.name in this.submitted || element === this.lastElement ) {
				this.element( element );
				$(element).next('span').remove();//移除span
			}
		}
    }).form();
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var params = {
			pageNo:page,
			isRead:$("#isRead").val(),
			isopt:$("#isopt").val()
	};
	loadAnnounceList(params);
}
