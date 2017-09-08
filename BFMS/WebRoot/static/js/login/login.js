$(function() {
	
	// 当输入框发生变化时，给input添加校验属性
	$("#f input").change(function() {
		$(this).validatebox({
			required : true,
		});
	});
	
	// 当输入框获取焦点时，删除错误提示信息
	$("#f input").focus(function() {
		$("#msg").text("");
	});
	
	// 刷新验证码
	$(".verifyCode").click(function(){
		var url = basePath + "verifyCode?t=" + new Date().getTime();
		$(this).attr("src", url);
	});
	
	// 登录
	function login(){
		$('#f').form({    
		    url: basePath + "login",    
		    onSubmit: function(){    
		    	console.log("登录操作");
		    },    
		    success:function(data){
		    	data = strToJson(data);	        
		    	if(data["msg"] != "ok"){
		    		// 登录失败，显示错误信息
		    		$("#msg").html("<span style='color:red;width:300px;'>" + data['msg'] + "</span>");
		    	} else {
		    		// 登录成功
		    		window.location.href = basePath +"info";
		    	}
		    }    
		});
		$("#f").submit();
	}
	
	// 登录
	$("#btn_login").click(function(){
		login();
	});
	
	// 按下回车后调用登录方法
	$(document).keyup(function(event){
		if(event.keyCode ==13){
			login();
		}
	});
	
	

});