<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/tag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/login/login.js"></script>
</head>
<body>
	<div id="login_title">欢迎使用学员信息管理系统</div>
	<hr />
	<div id="login_box">
		<form id="f">
			<div>
				<span for="username">账&nbsp;&nbsp;&nbsp;号</span>
				<input class="easyui-validatebox" type="text" name="username" />
			</div>
			<div>
				<span for="password">密&nbsp;&nbsp;&nbsp;码</span>
				<input class="easyui-validatebox" type="password" name="password" />
			</div>
			<div>
				<span for="verify_code">验证码</span>
				<input id="verifyCode" class="easyui-validatebox" type="text" name="verifyCode" style="width: 60px;"/>
				<%-- <div id="verifyCode" style="vertical-align: middle;background-image: url('${basePath}verifyCode');width:70px;height:20px;display: inline-block;"></div> --%>
				<img class="verifyCode" alt="验证码" src="${basePath}verifyCode" style="vertical-align: middle;margin-left: 25px;border: 1px #CCC dashed;">
			</div>
			<div>
				<span> </span>
				<input id="remember" type="checkbox" name="rememberMe" />
				<label for="remember"><span for="rememberMe">自动登录</span></label>
			</div>
			<div>
				<!-- <span><button class="" style="width:100px;height:40px;">登录</button></span>
				<span><button class="" style="width:100px;height:40px;margin-left: 80px;"</span> -->
				<input id="btn_login" type="button" value="登录"/>
				<input type="reset" value="重置"/>
			</div>
		</form>  
	</div>
	<%@include file="/WEB-INF/shared/footer.jsp"%>
</body>

</html>
