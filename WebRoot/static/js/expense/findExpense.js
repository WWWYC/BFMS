$(function() {
	
	function getDataGrid(param) {
		$("#data_grid").datagrid({
			url : basePath + "findExpenseByCriteria",
			queryParams: param,
			loadMsg : "正在加载...",
			pagination :  true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			loadFilter : function(data) {
				data = strToJson(data);
				console.log(data);
				if(data["status"] == 1) {
					okMsg(data["msg"]);
				} else if (data["status"] == 0 && data.rows.length == 0) {
					okMsg("无费用信息");
					return data;
				} else {
					return data;
				}
			},toolbar: "#grid_tb",
			columns : [ [ {
				field : "stuNum",
				title : "学号",
				width : "100px",
				align : "right",
				halign : "center",
			}, {
				field : "nameZh",
				title : "姓名",
				align : "left",
				halign : "center",
			}, {
				field : "expenseSt",
				title : "第一次缴费",
				width : "",
				align : "left",
				halign : "center",
			}, {
				field : "payDateSt",
				title : "第一次缴费日期",
				width : "",
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
//					return new Date(value).toLocaleDateString();
					return new Date(value).format("yyyy-MM-dd");
					
				},
			}, {
				field : "expenseNd",
				title : "第二次缴费",
				align : "left",
				halign : "center",
			}, {
				field : "payDateNd",
				title : "第二次缴费日期",
				width : "",
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
					return new Date(value).format("yyyy-MM-dd");
				},
			}, {
				field : "expenseRd",
				title : "第三次缴费",
				width : "",
				align : "left",
				halign : "center",
			}, {
				field : "payDateRd",
				title : "第三次缴费日期",
				width : "",
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
					return new Date(value).format("yyyy-MM-dd");
				},
			}, {
				field : "expenseTh",
				title : "第四次缴费",
				align : "left",
				halign : "center",
			}, {
				field : "payDateTh",
				title : "第四次缴费日期",
				width : "",
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
					return new Date(value).format("yyyy-MM-dd");
				},
			}, {
				field : "totalExp",
				title : "应交",
				align : "left",
				halign : "center",
			}, {
				field : "sumExp",
				title : "已交",
				align : "left",
				halign : "center",
			}, {
				field : "debt",
				title : "欠费",
				align : "left",
				halign : "center",
			}, {
				field : "comment",
				title : "备注",
				align : "left",
				halign : "center",
			} ] ],
		});
	}
	
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
					$("#group_list").css("color", "white");
					$("#group_list").html("<option value='' >请先录入组合信息</option>");
				} else {
					$("#group_list").css("background-color", "white	");
					$("#group_list").css("color", "black");
					var html = [];
					html.push("<option value=''>不选择</option>");
					data.forEach(function(group){
						html.push("<option value='" + group.groupId + "'>" + group.groupName + "</option>");
					});
					$("#group_list").html(html.join());
					getCompanyList();
				}
			}
		});
	}
	getDataGrid();
	
	// 获取全部企业信息，放入下拉列表中
	function getCompanyList(){
		var groupId = $("#group_list").val();
		$.ajax({
			url : basePath + "getCompanyList?groupId=" + groupId,
			type : "get",
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				// 如果没有组合数据，则提示先选择企业信息
				if(data.length == 0) {
					$("#company_list").css("background-color", "orange");
					$("#company_list").css("color", "white");
					$("#company_list").html("<option value=''>请先选择组合信息</option>");
				} else {
					$("#company_list").css("background-color", "white");
					$("#company_list").css("color", "black");
					var html = [];
					html.push("<option value=''>不选择</option>");
					data.forEach(function(company){
						html.push("<option value='" + company.companyId + "'>" + company.companyName + "</option>");
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
	
	// 高级查询
	$("#btn_search").click(function(){
		// 获取查询参数
		var param = eval("({})");
		var jsonObject = $("#data_form").serializeArray();
		for(var index in jsonObject) {
			var obj = jsonObject[index];
			param[obj['name']] = obj['value'];
		}
		param["groupId"] = $("#group_list").val();
		param["companyId"] = $("#company_list").val();
		
		getDataGrid(param);
	});
	
	// 查看管理费信息
	$("#btn_showExpense").click(function(){
		// 获取当前选中行信息
		var expense = $("#data_grid").datagrid("getSelected");
		// 获取管理费ID
		var uuid = expense["uuid"];
/*		// 获取学生ID
		var stuId = expense["stuId"];*/
		
		// 获取学生姓名
		var nameZh = expense["nameZh"];
		// 获取学生学号
		var stuNum = expense["stuNum"];
		
		addTab("查看 - " + stuNum.substring(8, 12) + " - " + nameZh, basePath + "showExpense?uuid=" + uuid, "icon-search");
	});
	
	// 编辑管理费信息
	$("#btn_editExpense").click(function(){
		// 获取当前选中行信息
		var expense = $("#data_grid").datagrid("getSelected");
		// 获取学生ID
		var stuId = expense["stuId"];
		// 获取费用ID
		var uuid = expense["uuid"];
		// 获取学生姓名
		var nameZh = expense["nameZh"];
		// 获取学生学号
		var stuNum = expense["stuNum"];
		
		addTab("编辑 - " + stuNum.substring(8, 12) + " - " + nameZh, basePath + "editExpense?uuid=" + uuid, "icon-edit");
	});
	
	// 删除费用信息
	$("#btn_rmExpense").click(function(){
		yesMsg("确认删除？", "rm");
	});
	
	$("#yes").click(function(){
		if($("#callBack").val() == "rm") {
			// 获取当前选中行信息
			var expense = $("#data_grid").datagrid("getSelected");
			$.ajax({
				url : basePath + "rmExpense",
				data : JSON.stringify(expense),
				type : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					if(data["status"] == "0") {
						okMsg(data["msg"]);
						$("#data_grid").datagrid("reload");
					} else if(data["status"] == "1") {
						okMsg(data["msg"]);
					}
				},
				error : function(data) {
					okMsg(data["msg"]);
				}
			});
		}
	});
	
	// 刷新当前数据表格
	function refreshGrid() {
		$("#data_grid").datagrid("reload");
	}
	
	// 调整布局
	$(".datagrid,#data_form,#data_table").css("margin-bottom", "0px");
});