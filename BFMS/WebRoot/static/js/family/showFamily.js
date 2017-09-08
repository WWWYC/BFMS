$(function() {
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
				
				// 如果已经存在家庭成员信息，则列出信息，并保存后台返回的json对象
				if(data["familyList"] == null || data["familyList"].length == 0) {
					// return ;
				} else {
					
					var familyList = data["familyList"];
					
					familyList.forEach(function(family){
						
						var str = "";
						
						str += "<tr>";
						str += "<td><input type='text' value='" + family["familyName"] + "' readonly /></td>";
						str += "<td><input type='text' value='" + family["familyRelation"] + "' readonly /></td>";
						str += "<td><input type='number' value='" + family["familyNum"] + "' readonly /></td>";
						str += "<td><input type='text' value='" + family["familyAddress"] + "' readonly /></td>";
						str += "<td><input type='text' value='" + family["comment"] + "' readonly /></td>";
						str += "</tr>";
						
						$("table>tbody").append($(str));
						
					});
				}
			}
		});
	}
	getFamilyList();
});