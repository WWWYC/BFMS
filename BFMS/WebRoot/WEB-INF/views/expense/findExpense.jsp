<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/expense/findExpense.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/expense/findExpense.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>组合</td>
				<td>
					<select id="group_list" style="width: 199px;height: 30px;">
						<option></option>
					</select>
				</td>
				<td>企业</td>
				<td>
					<select id="company_list" style="width: 199px;height: 30px;">
						<option></option>
					</select>
				</td>
				<td><input id="btn_search" type="button" class="btn btn-primary" value="查询" style="width: 180px;"></td>
			</tr>
		</table>
	</form>
	<div id="grid_tb" style="height:auto">
		<!-- <a id="btn_addExpense" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加费用信息</a> -->
		<a id="btn_showExpense" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查看费用明细</a>
		<a id="btn_editExpense" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑费用信息</a>
		<a id="btn_rmExpense" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除费用信息</a>
	</div>
	
	<table id="data_grid" style="width:100%;height:339px"></table>
	
	<div id="tabs" class="easyui-tabs" style="width:100%;"></div>
</body>
</html>
