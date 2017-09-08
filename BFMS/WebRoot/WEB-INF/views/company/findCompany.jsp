<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/company/findCompany.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/company/findCompany.js?t=${random_uuid}"></script>

</head>
<body>
	<div id="grid_tb" style="height:auto">
		<!-- <a id="btn_addCompany" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加企业</a> -->
		<a id="btn_showCompany" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查看企业</a>
		<a id="btn_editCompany" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑企业</a>
		<a id="btn_rmCompany" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除企业</a>
	</div>
	
	<table id="data_grid" style="width:100%;height:339px"></table>
	
	<div id="tabs" class="easyui-tabs" style="width:100%;"></div>
</body>
</html>
