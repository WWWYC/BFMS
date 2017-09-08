<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/group/findGroup.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/group/findGroup.js?t=${random_uuid}"></script>

</head>
<body>
	<div id="grid_tb" style="height:auto">
		<!-- <a id="btn_addGroup" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加组合</a> -->
		<a id="btn_showGroup" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查看组合</a>
		<a id="btn_editGroup" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑组合</a>
		<a id="btn_rmGroup" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除组合</a>
	</div>
	
	<table id="data_grid" style="width:100%;height:339px"></table>
	
	<div id="tabs" class="easyui-tabs" style="width:100%;"></div>
</body>
</html>
