$(function() {
	
	// 保存后台获取的FamilyList数据
	var families = [];
	
	// 保存当前要操作的行的索引
	var trIndex = -1;
	
	// 获取全部家庭成员信息，生成DOM
	function getFamilyList(){
		var stuId = $("#stuId").val();
		$.ajax({
			url : basePath + "family/getFamilyList?stuId=" + stuId,
			type : "get",
			async : false,
			contentType : "application/json",
			dataType:"json",
			success : function(data){
				
				// 创建添加按钮
				$("table>tbody").html("<tr id='add'><td colspan='6'><a id='btn_addFamily' href='javascript:void(0);' class='easyui-linkbutton'>添加成员信息</a></td></tr>");
				$("#btn_addFamily").linkbutton({
					iconCls:'icon-add',
					plain:true
				});
				
				// 如果已经存在家庭成员信息，则列出信息，并保存后台返回的json对象
				if(data["familyList"] == null || data["familyList"].length == 0) {
					// return ;
				} else {
					
					var familyList = data["familyList"];
					families = familyList;
					
					familyList.forEach(function(family){
						
						// 获取当前input个数，作为数组下标
						var index = $("table>tbody").children().length - 1;
						var str = "";
						
						str += "<tr id='" + index + "'>";
						str += "<td><input id='familyName' name='familyName' type='text' value='" + family["familyName"] + "'/></td>";
						str += "<td><input id='familyRelation' name='familyRelation' type='text' value='" + family["familyRelation"] + "'/></td>";
						str += "<td><input id='familyNum' name='familyNum' type='number' value='" + family["familyNum"] + "'/></td>";
						str += "<td><input id='familyAddress' name='familyAddress' type='text' value='" + family["familyAddress"] + "'/></td>";
						str += "<td><input id='comment' name='comment' type='text' value='" + family["comment"] + "'/></td>";
						str += "<td><a href='javascript:void(0);' class='easyui-linkbutton rm' index='" + index +"'>删除</a></td>";
						str += "</tr>";
						
						$("#add").before($(str));
						
						$(".rm").linkbutton({
							iconCls:'icon-add',
							plain:true
						});
					});
				}
				
				inputChange();
				$(".rm").unbind("click");
				rm();
				
				$("#btn_addFamily").unbind("click");
				addFamily();
			}
		});
	}
	getFamilyList();
	
	// 添加一条成员信息
	function addFamily(){
		
		$("#btn_addFamily").click(function(){
			
			// 获取当前input个数，作为数组下标
			var index = $("table>tbody").children().length - 1;
			var str = "";
			
			str += "<tr id='" + index + "'>";
			str += "<td><input id='familyName' name='familyName' type='text' /></td>";
			str += "<td><input id='familyRelation' name='familyRelation' type='text' /></td>";
			str += "<td><input id='familyNum' name='familyNum' type='number' /></td>";
			str += "<td><input id='familyAddress' name='familyAddress' type='text' /></td>";
			str += "<td><input id='comment' name='comment' type='text' /></td>";
			str += "<td><a href='javascript:void(0);' class='easyui-linkbutton rm' index='" + index +"'>删除</a></td>";
			str += "</tr>";
			
			$("#add").before($(str));
			
			$(".rm").linkbutton({
				iconCls:'icon-add',
				plain:true
			});
			
			// 绑定之前先取消上次绑定，否则会调用两次click方法
			$(".rm").unbind("click");
			rm();
			
			inputChange();
			debugger;
			// 在已保存的全局json对象中添加一个成员
			families.push(eval("({})"));
		});
	}
//	addFamily();
	
	// 删除当前行
	function rm(){
		$(".rm").click(function(){
			yesMsg("确认删除？", "rm");
			trIndex = $(this).attr("index");
		});
	}
	rm();
	
	// 当表单数据发生变化时，同步修改全局json对象
	function inputChange(){
		$("table>tbody>tr>td>input").change(function(){
			// 获取所有tr
			var trs = $("table>tbody>tr");
			for(var i = 0; i < trs.length - 1; i++) {
				// 获取当前tr下的所有td
				var tds = $(trs[i]).children();
				// 遍历td
				for(var j = 0; j < tds.length; j++) {
					var familyName = $(tds[0]).children("input[name=familyName]").val();
					var familyRelation = $(tds[1]).children("input[name=familyRelation]").val();
					var familyNum = $(tds[2]).children("input[name=familyNum]").val();
					var familyAddress = $(tds[3]).children("input[name=familyAddress]").val();
					var comment = $(tds[4]).children("input[name=comment]").val();

					families[i]["familyName"] = familyName;
					families[i]["familyRelation"] = familyRelation;
					families[i]["familyNum"] = familyNum;
					families[i]["familyAddress"] = familyAddress;
					families[i]["comment"] = comment;
				}
			}
			console.log(families);
		});
	}
	inputChange();
	
	$("#yes").click(function(){

		if($("#callBack").val() == "rm") {
			var uuid = families[trIndex]["uuid"];
			
			// 如果是页面上新添加的数据，则直接删除页面中的内容
			if(uuid == null || uuid == '') {
				$("tr[id=" + trIndex + "]").remove();
				
				// 从全局json对象中删除指定元素
				families.splice(trIndex, 1);
				console.log(families);
				return;
			}
			
			// 如果是从数据库中获取的数据，则同时删除数据库中的信息
			$.ajax({
				url : basePath + "family/rmFamily?uuid=" + uuid,
				type : "get",
				contentType : "application/json",
				dataType:"json",
				success : function(data){
					if(data["status"] == "0") {
						okMsg(data["msg"]);
						// 删除成功，显示提示信息
						$("tr[id=" + trIndex + "]").remove();
						
						// 从全局json对象中删除指定元素
						families.splice(trIndex, 1);
					} else if(data["status"] == "1") {
						okMsg(data["msg"]);
					}
				}
			});
			// 按顺序恢复tr的id属性
			var trs = $("table>tbody>tr");
			for(var i = 0; i < trs.length - 1; i++) {
				var tr = $(trs[i]);
				$(tr).attr("id", i);
			}
		} else if ($("#callBack").val() == "save") {
			debugger;
			// 为所有的对象设置stuId
			families.forEach(function(family){
				family["stuId"] = $("#stuId").val();
			});
			$.ajax({
				url : basePath + "family/updateFamily",
				type : "post",
				data : JSON.stringify(families),
				contentType : "application/json",
				dataType:"json",
				success : function(data){
					if(data["status"] == "0") {
						okMsg(data["msg"]);
						getFamilyList();
//						window.location.reload();
					} else if(data["status"] == "1") {
						okMsg(data["msg"]);
					}
				},
				error : function(data) {
					
				}
			});
		}
	});
	
	// 提交更新数据
	$("#btn_saveFamily").click(function(){
		yesMsg("确认提交吗？", "save");
	});
	
	// 调整布局
	$(".datagrid,#data_form,#data_table").css("margin-bottom", "0px");
});