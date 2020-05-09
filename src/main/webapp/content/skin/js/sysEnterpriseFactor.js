/**********************************企业要素功能脚本***************************************************/
/**
 * 列显示
 */
function saveFactorOrder(){
	var enterpriseid = $("#enterpriseid").val();
	//获取所有右列数据
    var array = new Array(); //定义数组
    $("#right_select option").each(function(){ //遍历全部option
        //var txt = $(this).text(); //获取option的内容
        var value = $(this).val(); 
        array.push(value); //添加到数组中
    });
	fnAjaxRequest(
		    "sysEnterpriseFactor/saveFactorOrder.do",
			{array:encodeURI(array),enterpriseid:enterpriseid},
			"json",
			"POST",
			function(data){
				 loadEnterFactorView();
			}
	 );
}

/**
 *企业要素配置 
 */
function saveFactorlist(){
	var enterpriseid = $("#enterpriseid").val();
	var factor_list =[];//定义一个数组      
    $('input[name="factorlist"]:checked').each(function(){     
    	factor_list.push($(this).val());    
    }); 
    fnAjaxRequest(
		    "sysEnterpriseFactor/saveFactorlist.do",
			{array:encodeURI(factor_list),enterpriseid:enterpriseid},
			"json",
			"POST",
			function(data){
				loadEnterFactorView();
			}
	 );
    saveFactorOrder();
     
}

/**
 * 加载table显示顺序
 */
function loadEnterFactorView(){
	showMark();
	$.get("sysEnterpriseFactor/index.do","",function(data){
		$("#twoContain").html(data);
		hideMark();
	});
}

