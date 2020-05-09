/**********************************运维监控脚本***************************************************/
/**
 * 点击区域，加载右侧设备要素列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadOperationList(params, flag){
	  showMark();
	$.get("operation/list.do",params,function(data){
		$("#operationContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}
