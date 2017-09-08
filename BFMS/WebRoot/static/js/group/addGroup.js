$(function(){
	// 设置默认组合编号
	function setGroupNum(){
		$.ajax({
			url : basePath + "getGroupNum",
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				data = strToJson(data);
				console.log(typeof(data));
				
				$("input[name=groupNum]").val(data["groupNum"]);
			}
		});
	}
	
	setGroupNum();
	
	// 手动获取编号
	$("#btn_getGroupNum").click(function(){
		setGroupNum();
	});
	
	// 提交数据，保存组合信息
	$("#btn_save").click(function(){
		$("#data_form").form({
		    url: basePath + "insertGroup",
		    onSubmit: function(){
		    },
		    success:function(data){
		    	data = strToJson(data);
		    	if(data["status"] == 0) {
		    		okMsg(data["msg"]);
		    		setGroupNum();
		    	} else if (data["status"] == 1) {
		    		okMsg(data["msg"]);
		    	}
		    }
		});
		$("#data_form").submit();
	});
});