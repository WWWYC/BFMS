$(function(){
	
	// 提交数据，保存组合信息
	$("#btn_save").click(function(){
		$("#data_form").form({
		    url: basePath + "updateGroup",
		    onSubmit: function(){
		    },
		    success:function(data){
		    	data = strToJson(data);
		    	okMsg(data["msg"]);
		    }
		});
		$("#data_form").submit();
	});
});