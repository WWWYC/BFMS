<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/vacation/findVacation.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/vacation/findVacation.js?t=${random_uuid}"></script>
<script type="text/javascript" src="${basePath }/static/jslib/flatpickr.min.js?t=${random_uuid}"></script>


</head>
<body>
	<div id="grid_tb" style="height:auto">
		<a id="btn_addVacation" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加请假信息</a>
		<a id="btn_showVacation" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查看请假信息</a>
		<a id="btn_editVacation" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑请假信息</a>
		<a id="btn_rmVacation" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除请假信息</a>
	</div>
	
	<form id="data_form">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>中文名</td>
				<td><input  type="text" name="name" /></td>
				<td>请假日期</td>
				<td colspan="2" >
					<!-- <input type="datetime" name="startDate" />～<input type="datetime" name="endDate" /> -->
					<input class="easyui-datetimebox" name="startDate" />～<input class="easyui-datetimebox" name="endDate" />
				</td>
				<!-- <td><input id="btn_search" type="button" class="btn btn-primary" value="查询" style="width: 180px;margin-left: 200px;"></td> -->
			</tr>
			<tr>
				<td colspan="3"><input id="btn_search" type="button" class="btn btn-primary" value="查询" style="width: 180px;margin-left: 200px;"></td>
				<td colspan="3"><input type="reset" class="btn btn-primary" value="重置" style="width: 180px;margin-left: 200px;"></td>
			</tr>
		</table>
	</form>
	 <!-- data-options="url:'datagrid_data.json',fitColumns:true,singleSelect:true" -->
	<table id="data_grid" style="width:100%;height:339px"></table>
	<div id="tabs" class="easyui-tabs" style="width:100%;"></div>
	
	
</body>

</html>