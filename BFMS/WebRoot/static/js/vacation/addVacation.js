$(function() {
	// 获取全部组合信息，放入下拉列表中
	function getGroupList(){
		$.ajax({
			url : basePath + "getGroupList",
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				// 如果没有组合数据，则提示先录入组合信息
				if(data.length == 0) {
					$("#group_list").css("background-color", "orange");
					$("#group_list").html("<option>请先录入组合信息</option>");
				} else {
					$("#group_list").css("background-color", "white	");
					var html = [];
					data.forEach(function(group){
						html.push("<option value='" + group.groupId + "'>" + group.groupName + "</option>");
					});
					$("#group_list").html(html.join());
					getCompanyList();
				}
			}
		});
	}
	
	// 获取全部企业信息，放入下拉列表中
	function getCompanyList(){
		var groupId = $("#group_list").val();
		$.ajax({
			url : basePath + "getCompanyList?groupId=" + groupId,
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				// 如果没有企业数据，则提示先录入企业信息
				if(data.length == 0) {
					$("#company_list").css("background-color", "orange");
					$("#company_list").html("<option>请先录入企业信息</option>");
				} else {
					$("#company_list").css("background-color", "white");
					var html = [];
					data.forEach(function(company){
						html.push("<option value='" + company.companyId + "'>" + company.companyName + "</option>");
					});
					$("#company_list").html(html.join());
				}
				// 级联获取学生列表
				getStudentList()
			}
		});
	}
	
	function getStudentList() {
		var companyId = $("#company_list").val();
		$.ajax({
			url : basePath + "getStudentList?companyId=" + companyId,
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				console.log(data);
				// 如果没有学生数据，则提示先录入学生信息
				if(data.length == 0) {
					$("#student_list").css("background-color", "orange");
					$("#student_list").html("<option>请先录入学生信息</option>");
				} else {
					$("#student_list").css("background-color", "white");
					var html = [];
					data.forEach(function(student){
						html.push("<option value='" + student.stuId + "'>" + student.nameZh + "</option>");
					});
					$("#student_list").html(html.join());
				}
			}
		});
	}
	
	// 获取组合列表
	getGroupList();
	
	// 当组合列表发生变化时，级联更新企业列表
	$("#group_list").change(function(){
		getCompanyList();
	});
	
	// 当企业列表发生变化时，级联更新学生列表
	$("#company_list").change(function(){
		getStudentList();
	});
	
	// 提交数据，保存请假信息
	$("#btn_save").click(function(){
		$("#data_form").form({
		    url: basePath + "vacation/insertVacation",
		    success:function(data){
		    	console.log(data);
		    	try {
		    		data = strToJson(data);
		    	} catch (e) {
		    		okMsg(e);
		    		return;
		    	}
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