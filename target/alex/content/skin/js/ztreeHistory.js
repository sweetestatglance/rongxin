/**
 * 公用的ztree
 * @param id  tree id
 * @param url 请求的路径
 * @param fun 执行的方法
 * 
 */
 var ztreeHistoryFun = function ztreeInit(id,url,fun){
	var setting = {  
			   view: {nameIsHTML: true},
		    //获取json数据  
	        async : {    
	            enable : true,   
	            url : url, 
	            autoParam : ["id","pId","name"]
	        },    
	        data:{  
	        	   key: {
	     	   	   title:"name"
	     	   },
	            simpleData : {    
	            	 enable : true,    
                     idKey : "id", // id   
                     pIdKey : "pId", // 父id      
                     rootId : 0 
	            }  
	         }, 
	      // 回调函数    
	         callback : {    
	             onClick : function nodeClick(event, treeId, treeNode, clickFlag){
	            	
	            	 if (treeNode.isParent) { 
	            		 $.Popup.create({ title : "提示", content : "请选择设备！" });
	            		 return false;
	            	 }else{
	            		  if(treeNode.isDevice){
	            			  if(fun!=null){
			            		   //通过点击左侧获取右侧内容
				       	            fun(treeNode);
			            	   } 
	            		  }else{
	            			  //最子集不是设备，不做处理
	            		  }
		            	  
	            	 }
	               },
	             onAsyncSuccess: onAsyncSuccessH
	         }
	}; 
	
	 return $.fn.zTree.init(id, setting);
}
 


$(".treeOpt div.operate div.selection").click(function () {
    if ($(this).children("ul").is(":hidden")) {
        $(this).children("ul").show();
    } else {
        $(this).children("ul").hide();
    }
});
$(".treeOpt div.operate div.selection").bind("mouseleave", function () {
    $(this).children("ul").hide();
});


// 异步加载成功回调函数
function onAsyncSuccessH(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	// 获取节点
	var nodes = treeObj.getNodes();
	if (nodes.length > 0) {
		//treeObj.selectNode(nodes[0]);
		// 默认点击第一个节点
		//treeObj.setting.callback.onClick(null, treeObj.setting.treeId, nodes[0]);
		
		// 展开第一父节点
		var cNodes = treeObj.getNodes()[0].children;
		if (cNodes!=undefined && cNodes.length > 0) {
			
			//treeObj.expandNode(treeObj.getNodes()[0],true);
			
			getAllChildrenNodes(cNodes[0],treeObj);
			
			//treeObj.setting.callback.onClick(null, treeObj.setting.treeId, nodes[0]);
			
		}
	}
	
	// 展开所有节点
	//treeObj.expandAll(true);
}  


//递归，得到叶子节点的数据
function getAllChildrenNodes(treeNode,treeObj){ 
	
   if (treeNode.isParent) { 
     var childrenNodes = treeNode.children; 
     var len = childrenNodes.length;
     if (childrenNodes) { 
    	 if(childrenNodes.length>1){
    		 len = 1;
    	 }
         for (var i = 0; i < len; i++) { 
          if(childrenNodes[i].isParent){
             getAllChildrenNodes(childrenNodes[i],treeObj); 
          }else{
        	  treeObj.selectNode(childrenNodes[0]);
        	  treeObj.expandNode(childrenNodes[0], true, true, true);
        	  treeObj.setting.callback.onClick(null, treeObj.setting.treeId, childrenNodes[0]);
        	 // break;
          }
         } 
     } 
  }else{
	  treeObj.selectNode(treeNode);
	  treeObj.expandNode(treeNode, true, true, true);
	  treeObj.setting.callback.onClick(null, treeObj.setting.treeId, treeNode);
  } 
}
