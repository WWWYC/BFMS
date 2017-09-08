$(function(){
	
	// 提交数据，保存企业信息
	$("#btn_save").click(function(){
		$("#data_form").form({
		    url: basePath + "updateExpense",
		    success:function(data){
		    	console.log(data);
		    	data = strToJson(data);
		    	console.log(data);
		    	if(data["status"] == 0) {
		    		okMsg(data["msg"]);
		    	} else if (data["status"] == 1) {
		    		okMsg(data["msg"]);
		    	}
		    }
		});
		$("#data_form").submit();
	});
	
});