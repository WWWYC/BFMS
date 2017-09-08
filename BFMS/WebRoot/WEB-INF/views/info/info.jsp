<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/info/info.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/info/info.js?t=${random_uuid}"></script>

<title>劳务外派信息管理系统</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:30px;">
	<div style="height: 25px; width: 100%; background-color: #D1EEEE;">
		<div>
			<a href="${basePath }logout">退出登录</a>
		</div>
	</div>
	</div>
	<!-- <div data-options="region:'south'" style="height:30px;"></div> -->
	<!-- <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div> -->
	<div data-options="region:'west'" style="width:150px;">
		<ul id="menu"></ul>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;"></div>
	</div>

</body>

</html>
