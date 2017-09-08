$(function(){
	// 设置默认组合编号
	function setCompanyNum(){
		$.ajax({
			url : basePath + "getCompanyNum",
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				console.log(data);
				
				$("input[name=companyNum]").val(data["companyNum"]);
			}
		});
	}
	
	setCompanyNum();
	
	// 手动获取编号
	$("#btn_getCompanyNum").click(function(){
		setCompanyNum();
	});
	
	// 提交数据，保存组合信息
	$("#btn_save").click(function(){
		// 获取组合ID
		var groupId = $("#group_list").val();
		$("#data_form").form({
		    url: basePath + "insertCompany?groupId=" + groupId,
		    dataType : "json",
		    success:function(data){
		    	data = strToJson(data);
		    	console.log(data);
		    	if(data["status"] == 0) {
		    		okMsg(data["msg"]);
		    		setCompanyNum();
		    	} else if (data["status"] == 1) {
		    		okMsg(data["msg"]);
		    	}
		    }
		});
		$("#data_form").submit();
	});
	
	// 获取全部组合信息，放入下拉列表中
	function getGroupList(){
		$.ajax({
			url : basePath + "getGroupList",
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				data = strToJson(data);
				console.log(data);
				// 如果没有组合数据，则提示先录入组合信息
				if(data.length == 0) {
					$("#group_list").css("background-color", "orange");
					$("#group_list").html("<option>请先录入组合信息</option>");
					return;
				}
				var html = [];
				data.forEach(function(group){
					html.push("<option value='" + group.groupId + "'>" + group.groupName + "</option>");
				});
				$("#group_list").html(html.join());
			}
		});
	}
	getGroupList();
});