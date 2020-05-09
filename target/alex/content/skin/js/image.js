/**********************************图像监视脚本***************************************************/
$(function() {
	$(".tree").height($(".left").outerHeight()- 10);
});
/**
 * 点击区域，加载右侧设备要素列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadImageList(params, flag){
	  showMark();
	$.get("stImage/list.do",params,function(data){
		$("#imageContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}
/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var query_stcdName = $("#query_stcdName").val();
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	if(query_stcdName==$.i18n.map['codeOrName']){
		query_stcdName = "";
	}
	var params = {
			pageNo:page,
			sttp: $("#sttp").val(),
			nodeIds : aryIds.toString(),
			query_stcdName:encodeURI(query_stcdName),
			isDevice:selectdNode.isDevice
	};
	loadImageList(params);
}
/**
 * 查询
 */
function searchImg(){
	var sttp = $("#sttp").val();
	var aryIds = getParentNodeIdsByTreeId("ztree");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var selectdNode = treeObj.getSelectedNodes()[0];
	var query_stcdName = $("#query_stcdName").val();
	if(query_stcdName==$.i18n.map['codeOrName']){
		query_stcdName = "";
	}
	var params = {
			sttp:sttp,
			nodeIds : aryIds.toString(),
			query_stcdName:encodeURI(query_stcdName),
			isDevice:selectdNode.isDevice
			
	};
	loadImageList(params);
}

