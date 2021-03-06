$(function(){
	// 提交数据，保存学生信息
	$("#btn_save").click(function(){
		// 获取企业ID
		var companyId = $("#company_list").val();
		$("#data_form").form({
		    url: basePath + "updateStudent?companyId=" +companyId,
		    onSubmit: function(){
		    	return validateForm();
		    },
		    success:function(data){
		    	data = strToJson(data);
		    	console.log(data);
		    	if(data["status"] == 0) {
		    		okMsg(data["msg"]);
		    	} else if(data["status"] == 1) {
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
				
				// 获取该学生的组合，默认显示该组合
				var groupId = $("#group_list").val();
				
				// 如果没有组合数据，则提示先录入组合信息
				if(data.length == 0) {
					$("#group_list").css("background-color", "orange");
					$("#group_list").html("<option>请先录入组合信息</option>");
				} else {
					$("#group_list").css("background-color", "white	");
					var html = [];
					data.forEach(function(group){
						if(group.groupId == groupId) {
							html.push("<option value='" + group.groupId + "' selected='selected'>" + group.groupName + "</option>");
						} else {
							html.push("<option value='" + group.groupId + "'>" + group.groupName + "</option>");
						}
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
				
				// 获取该学生的组合，默认显示该组合
				var companyId = $("#company_list").val();
				
				// 如果没有选择组合，则提示先选择信息
				if(data.length == 0) {
					$("#company_list").css("background-color", "orange");
					$("#company_list").html("<option>请先选择组合信息</option>");
				} else {
					$("#company_list").css("background-color", "white");
					var html = [];
					data.forEach(function(company){
						if(company.companyId == companyId) {
							html.push("<option value='" + company.companyId + "' selected='selected'>" + company.companyName + "</option>");
						} else {
							html.push("<option value='" + company.companyId + "'>" + company.companyName + "</option>");
						}
						
					});
					$("#company_list").html(html.join());
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
	

	$("input[name=stuPhoto]").change(function(event) {
		var files = event.target.files, file;
		if (files && files.length > 0) {
			// 获取目前上传的文件
			file = files[0];// 文件大小校验的动作
			if (file.size > 1024 * 1024 * 2) {
				alert('图片大小不能超过 2MB!');
				return false;
			}
			// 获取 window 的 URL 工具
			var URL = window.URL || window.webkitURL;
			// 通过 file 生成目标 url
			var imgURL = URL.createObjectURL(file);
			//用attr将img的src属性改成获得的url
			$("#img_photo").attr("src", imgURL);
			// 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
			// URL.revokeObjectURL(imgURL);
		}
	});
	
	// 表单验证
	function validateForm(){
		
		var stuNum = $.trim($("input[name=stuNum]").val());
		var nameZh = $.trim($("input[name=nameZh]").val());
		var age = $.trim($("input[name=age]").val());
		var craft = $.trim($("input[name=craft]").val());
		var height = $.trim($("input[name=height]").val());
		var weight = $.trim($("input[name=weight]").val());
		var stuPhone = $.trim($("input[name=stuPhone]").val());
		var entryDate = $.trim($("input[name=entryDate]").val());
		var sameClass = $.trim($("input[name=sameClass]").val());
		var identityNum = $.trim($("input[name=identityNum]").val());
		var companyId = $.trim($("select[name=companyId]").val());
		var eyeLeft = $.trim($("input[name=eyeLeft]").val());
		var eyeRight = $.trim($("input[name=eyeRight]").val());
		
		// 数字正则表达式
		var numRegex = /^[0-9]*$/;
		
		// 中文正则表达式
		var zhRegex = /^[\u4e00-\u9fa5]+$/;
		
		/**
		 * 判断年龄
		 *   必须是正整数
		 */
		if(age != ""){
			if(!numRegex.test(age)) {
				okMsg("年龄必须是正整数");
				return false;
			} else if (!(age > 0 && age < 130)) {
				okMsg("年龄必须大于0并且小于130");
				return false;
			}
		}
		
		/**
		 * 判断身高
		 *   必须是正整数
		 */
		if(height != ""){
			if(!numRegex.test(height)) {
				okMsg("身高必须是正整数");
				return false;
			} else if (!(height > 0 && height < 250)) {
				okMsg("身高必须大于0并且小于250");
				return false;
			}
		}
		
		/**
		 * 判断体重
		 *   必须是正整数
		 */
		if(weight != ""){
			if(!numRegex.test(weight)) {
				okMsg("体重必须是正整数");
				return false;
			} else if (!(weight > 0 && weight < 250)) {
				okMsg("体重必须大于0并且小于250");
				return false;
			}
		}
		
		/**
		 * 判断同期生
		 *  必须是正整数
		 *  必须大于0并且小于等于100
		 */
		if(sameClass != ""){
			if(!numRegex.test(sameClass)) {
				okMsg("同期生必须是正整数");
				return false;
			} else if (!(sameClass > 0 && sameClass < 250)) {
				okMsg("同期生必须大于0并且小于100");
				return false;
			}
		}
		
		/**
		 * 判断左眼视力
		 *   必须大于0
		 */
		if(eyeLeft != "") {
			if(eyeLeft <= 0){
				okMsg("左眼视力必须大于0");
				return false;
			}
		}
		
		/**
		 * 判断右眼视力
		 *   必须大于0
		 */
		if(eyeRight != "") {
			if(eyeRight <= 0){
				okMsg("右眼视力必须大于0");
				return false;
			}
		}
		return true;
	};
});