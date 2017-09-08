$(function() {
	
	function getDataGrid(param) {
		$("#data_grid").datagrid({
			url : basePath + "vacation/getVacationListByCriteria",
			queryParams: param,
			loadMsg : "正在加载...",
			pagination :  true,
			fitColumns : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			loadFilter : function(data) {
				data = strToJson(data);
				console.log(data);
				if(data["status"] == 1) {
					okMsg(data["msg"]);
				} else if (data["status"] == 0 && data.rows.length == 0) {
					okMsg("无请假信息");
					return data;
				} else {
					return data;
				}
			},toolbar: "#grid_tb",
			columns : [ [ {
				field : "stuNum",
				title : "学号",
				width : "100px",
				align : "center",
				halign : "center",
			}, {
				field : "nameZh",
				title : "姓名",
				width : 100,
				align : "center",
				halign : "center",
			}, {
				field : "reason",
				title : "请假原因",
				align : "left",
				halign : "center",
			}, {
				field : "startDate",
				title : "开始日期",
				width : 140,
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
					return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				},
			}, {
				field : "endDate",
				title : "结束日期",
				width : 140,
				align : "center",
				halign : "center",
				formatter: function(value,row,index){
					if(value == "" || value == null) {
						return "";
					}
					return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				},
			}, {
				field : "duration",
				title : "请假时长（天）",
				width : "",
				align : "right",
				halign : "center",
			}, {
				field : "comment",
				title : "备注",
				width : "",
				align : "center",
				halign : "center",
			} ] ],
		});
	}
	
	getDataGrid();
	
	// 高级查询
	$("#btn_search").click(function(){
		// 获取查询参数
		var param = eval("({})");
		var jsonObject = $("#data_form").serializeArray();
		for(var index in jsonObject) {
			var obj = jsonObject[index];
			param[obj['name']] = obj['value'];
		}
		getDataGrid(param);
	});
	
	// 添加请假信息
	$("#btn_addVacation").click(function(){
		addTab("添加请假信息 - ", basePath + "vacation/addVacation", "icon-add");
	});
	
	// 查看请假信息
	$("#btn_showVacation").click(function(){
		// 获取当前选中行信息
		var vacation = $("#data_grid").datagrid("getSelected");
		// 获取请假信息ID
		var uuid = vacation["uuid"];
		
		// 获取学生姓名
		var nameZh = vacation["nameZh"];
		// 获取学生学号
		var stuNum = vacation["stuNum"];
		
		addTab("查看 - " + stuNum.substring(8, 12) + " - " + nameZh, basePath + "vacation/showVacation?uuid=" + uuid, "icon-search");
	});
	
	// 编辑请假信息
	$("#btn_editVacation").click(function(){
		// 获取当前选中行信息
		var vacation = $("#data_grid").datagrid("getSelected");
		// 获取请假信息ID
		var uuid = vacation["uuid"];
		// 获取学生姓名
		var nameZh = vacation["nameZh"];
		// 获取学生学号
		var stuNum = vacation["stuNum"];
		
		addTab("编辑 - " + stuNum.substring(8, 12) + " - " + nameZh, basePath + "vacation/editVacation?uuid=" + uuid, "icon-edit");
	});
	
	// 删除请假信息
	$("#btn_rmVacation").click(function(){
		yesMsg("确认删除？", "rm");
	});
	
	$("#yes").click(function(){
		if($("#callBack").val() == "rm") {
			// 获取当前选中行信息
			var vacation = $("#data_grid").datagrid("getSelected");
			$.ajax({
				url : basePath + "vacation/rmVacation",
				data : JSON.stringify(vacation),
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