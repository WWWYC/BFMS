$(function() {
	
	function getDataGrid(param) {
		$("#data_grid").datagrid({
			url : basePath + "findGroupPageList",
//			queryParams: param,
			loadMsg : "正在加载...",
			pagination :  true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			loadFilter : function(data) {
				data = strToJson(data);
				if(data["status"] == 1) {
					okMsg(data["msg"]);
				} else if (data["status"] == 0 && data.rows.length == 0) {
					okMsg("无组合信息");
					return data;
				} else {
					return data;
				}
				return data;
				
			},toolbar: "#grid_tb",
			columns : [ [ {
				field : "groupNum",
				title : "组合编号",
				width : "60px",
				align : "center",
				halign : "center",
			}, {
				field : "groupName",
				title : "组合名称",
				align : "left",
				halign : "center",
			}, {
				field : "groupContacts",
				title : "联系人",
				align : "left",
				halign : "center",
			}, {
				field : "groupAddress",
				title : "地址",
				align : "left",
				halign : "center",
			}, {
				field : "groupPhone",
				title : "电话",
				align : "left",
				halign : "center",
			}, {
				field : "groupEmail",
				title : "邮箱",
				align : "left",
				halign : "center",
			}, {
				field : "groupFax",
				title : "传真",
				align : "left",
				halign : "center",
			}, {
				field : "comment",
				title : "备注",
				width : "170px",
				align : "left",
				halign : "center",
			} ] ],
		});
	}
	
	getDataGrid();
	
	// 查看组合信息
	$("#btn_showGroup").click(function(){
		// 获取当前选中行信息
		var group = $("#data_grid").datagrid("getSelected");
		// 获取组合ID
		var groupId = group["groupId"];
		// 获取组合名称
		var groupName = group["groupName"];
		// 获取组合编号
		var groupNum = group["groupNum"];
		
		addTab("查看 - " + groupNum + " - " + groupName, basePath + "showGroup?groupId=" + groupId, "icon-search");
	});
	
	// 编辑组合信息
	$("#btn_editGroup").click(function(){
		// 获取当前选中行信息
		var group = $("#data_grid").datagrid("getSelected");
		// 获取组合ID
		var groupId = group["groupId"];
		// 获取组合名称
		var groupName = group["groupName"];
		// 获取组合编号
		var groupNum = group["groupNum"];
		
		addTab("编辑 - " + groupNum + " - " + groupName, basePath + "editGroup?groupId=" + groupId, "icon-edit");
	});
	
	// 删除组合
	$("#btn_rmGroup").click(function(){
		yesMsg("确认删除？", "rm");
	});
	
	$("#yes").click(function(){
		if($("#callBack").val() == "rm") {
			// 获取当前选中行信息
			var group = $("#data_grid").datagrid("getSelected");
			console.log(JSON.stringify(group));
			$.ajax({
				url : basePath + "rmGroup",
				data : JSON.stringify(group),
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