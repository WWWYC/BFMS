$(function() {
	
	function getDataGrid(param) {
		$("#data_grid").datagrid({
			url : basePath + "findCompanyByCriteria",
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
					okMsg("无企业信息");
					return data;
				} else {
					return data;
				}
				return data;
				
			},toolbar: "#grid_tb",
			columns : [ [ {
				field : "companyNum",
				title : "企业编号",
				width : "60px",
				align : "center",
				halign : "center",
			}, {
				field : "companyName",
				title : "企业名称",
				align : "left",
				halign : "center",
			}, {
				field : "groupName",
				title : "组合名称",
				align : "left",
				halign : "center",
			}, {
				field : "companyContacts",
				title : "联系人",
				align : "left",
				halign : "center",
			}, {
				field : "companyAddress",
				title : "地址",
				align : "left",
				halign : "center",
			}, {
				field : "companyPhone",
				title : "电话",
				align : "left",
				halign : "center",
			}, {
				field : "companyEmail",
				title : "邮箱",
				align : "left",
				halign : "center",
			}, {
				field : "companyFax",
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
	
	// 查看企业信息
	$("#btn_showCompany").click(function(){
		// 获取当前选中行信息
		var company = $("#data_grid").datagrid("getSelected");
		// 获取企业ID
		var companyId = company["companyId"];
		// 获取企业名称
		var companyName = company["companyName"];
		// 获取企业编号
		var companyNum = company["companyNum"];
		
		addTab("查看 - " + companyNum + " - " + companyName, basePath + "showCompany?companyId=" + companyId, "icon-search");
	});
	
	// 编辑企业信息
	$("#btn_editCompany").click(function(){
		// 获取当前选中行信息
		var company = $("#data_grid").datagrid("getSelected");
		// 获取组合ID
		var companyId = company["companyId"];
		// 获取组合名称
		var companyName = company["companyName"];
		// 获取组合编号
		var companyNum = company["companyNum"];
		
		addTab("编辑 - " + companyNum + " - " + companyName, basePath + "editCompany?companyId=" + companyId, "icon-edit");
	});
	
	// 删除企业
	$("#btn_rmCompany").click(function(){
		yesMsg("确认删除？", "rm");
	});
	
	$("#yes").click(function(){
		if($("#callBack").val() == "rm") {
			// 获取当前选中行信息
			var company = $("#data_grid").datagrid("getSelected");
			console.log(JSON.stringify(company));
			$.ajax({
				url : basePath + "rmCompany",
				data : JSON.stringify(company),
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